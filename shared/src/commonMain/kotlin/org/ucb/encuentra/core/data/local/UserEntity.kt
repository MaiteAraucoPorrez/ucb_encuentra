package org.ucb.encuentra.core.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey

/**
 * TEMPORAL: la contraseña se guarda en texto plano solo porque no hay backend/SSO real.
 * Cuando exista backend, aquí iría un hash (o nada: el token de sesión).
 */
@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey val email: String,
    val fullName: String,
    val password: String
)
