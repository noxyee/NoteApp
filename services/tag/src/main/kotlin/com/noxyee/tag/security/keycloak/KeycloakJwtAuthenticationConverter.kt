package com.noxyee.tag.security.keycloak

import jakarta.validation.constraints.NotNull
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import org.springframework.stereotype.Component
import java.util.stream.Collectors
import java.util.stream.Stream

@Component
class KeycloakJwtAuthenticationConverter : Converter<Jwt, AbstractAuthenticationToken> {

    override fun convert(@NotNull source: Jwt): AbstractAuthenticationToken {
        return JwtAuthenticationToken(
            source,
            Stream.concat(
                JwtGrantedAuthoritiesConverter().convert(source)!!.stream(),
                extractRoles(source).stream()
            ).collect(Collectors.toSet())
        )
    }

    private fun extractRoles(jwt: Jwt): Collection<GrantedAuthority> {
        var resourceAccess = HashMap<String, Any>(jwt.getClaim<Map<String, Any>>("resource_access"))
        var eternal: Map<String, List<String>> = resourceAccess["account"] as Map<String, List<String>>
        var roles = eternal["roles"]
        return roles!!.stream()
            .map { role -> SimpleGrantedAuthority("ROLE_" + role.replace("-", "_")) }
            .collect(Collectors.toList())
    }
}