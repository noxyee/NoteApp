package com.noxyee.notification.service

import org.keycloak.admin.client.Keycloak
import org.keycloak.representations.idm.UserRepresentation
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class KeycloakUserService(
    @Value("\${keycloak.server-url}") private val serverUrl: String,
    @Value("\${keycloak.realm}") private val realm: String,
    @Value("\${keycloak.client-id}") private val clientId: String,
    @Value("\${keycloak.username}") private val username: String,
    @Value("\${keycloak.password}") private val password: String
) {

    private val keycloak: Keycloak = Keycloak.getInstance(serverUrl, realm, username, password, clientId)

    fun getUserById(userId: String): UserRepresentation? {
        return keycloak.realm(realm).users().get(userId).toRepresentation()
    }
}