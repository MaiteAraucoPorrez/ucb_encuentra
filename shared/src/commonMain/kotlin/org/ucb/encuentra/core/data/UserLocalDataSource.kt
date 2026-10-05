package org.ucb.encuentra.core.data

import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import org.ucb.encuentra.core.data.local.UserDao
import org.ucb.encuentra.core.data.local.UserEntity
import org.ucb.encuentra.core.domain.model.UserModel
import org.ucb.encuentra.core.domain.vo.Email

/**
 * Usuarios guardados en Room. Reemplaza al InMemoryAuthStore: ahora los registros
 * sobreviven al cierre de la app. Usuario de prueba: mateo@ucb.edu.bo / 123456
 */
class UserLocalDataSource(private val dao: UserDao) {

    private val seedMutex = Mutex()
    private var seeded = false

    private suspend fun ensureSeeded() = seedMutex.withLock {
        if (!seeded) {
            if (dao.count() == 0) {
                dao.insert(UserEntity(email = DEMO_EMAIL, fullName = "Mateo Rojas", password = "123456"))
            }
            seeded = true
        }
    }

    suspend fun register(fullName: String, email: Email, password: String): Result<UserModel> {
        ensureSeeded()
        val key = email.value.lowercase()
        if (dao.findByEmail(key) != null) {
            return Result.failure(IllegalStateException("Ya existe una cuenta con ese correo"))
        }
        val entity = UserEntity(email = key, fullName = fullName, password = password)
        dao.insert(entity)
        return Result.success(entity.toDomain())
    }

    suspend fun login(email: Email, password: String): Result<UserModel> {
        ensureSeeded()
        val entity = dao.findByEmail(email.value.lowercase())
        return if (entity != null && entity.password == password) {
            Result.success(entity.toDomain())
        } else {
            Result.failure(IllegalArgumentException("Usuario o contraseña incorrectos"))
        }
    }

    private fun UserEntity.toDomain() = UserModel(id = email, fullName = fullName, email = Email(email))

    private companion object {
        const val DEMO_EMAIL = "mateo@ucb.edu.bo"
    }
}
