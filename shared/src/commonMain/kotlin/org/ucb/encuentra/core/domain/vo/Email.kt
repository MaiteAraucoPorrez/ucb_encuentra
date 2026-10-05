package org.ucb.encuentra.core.domain.vo

import kotlin.jvm.JvmInline

/**
 * El mockup de Login pide correo institucional (@ucb.edu.bo), así que
 * a diferencia del proyecto de movies, aquí sí se valida el dominio.
 */
@JvmInline
value class Email(val value: String) {
    fun isValid(): Boolean = value.endsWith("@ucb.edu.bo", ignoreCase = true) &&
        value.substringBefore("@").isNotBlank()
}
