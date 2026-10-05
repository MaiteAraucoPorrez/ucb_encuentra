package error

// Mismo esqueleto que usa el docente (rama feature/koin-viewmodel).
// No se usa todavía en este proyecto (seguimos con Result<T>, como en
// el proyecto de movies), queda listo por si migran a manejo de
// errores tipado con Arrow más adelante.
sealed interface AppError

sealed interface DomainError : AppError {
    data object InvalidUser : DomainError
}

sealed interface ApplicationError : AppError

sealed interface DataError : AppError
