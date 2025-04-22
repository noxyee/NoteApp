--liquibase formatted sql
--changeset tborkowski:1742575699
CREATE TABLE "tag"
(
    id         VARCHAR(36) PRIMARY KEY NOT NULL,
    name       VARCHAR(20)             NOT NULL,
    color      VARCHAR(7)              NOT NULL,
    user_id    VARCHAR(36)             NOT NULL,
    created_at TIMESTAMP               NOT NULL,
    updated_at TIMESTAMP               NOT NULL
)