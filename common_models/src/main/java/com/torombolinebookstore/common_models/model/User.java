package com.torombolinebookstore.common_models.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NonNull
    private byte[] user_id;// BINARY(16) default (UUID_TO_BIN(UUID())),
    @NonNull
    private String email;// VARCHAR(255) UNIQUE NOT NULL,
    @NonNull
    private String salt;// VARCHAR(24) NOT NULL,
    @NonNull
    private String passwordHash;// VARCHAR(255) NOT NULL,
    private Timestamp createdAt;// TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
}
