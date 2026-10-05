package org.ucb.encuentra.feature.matchlist.data.seed

import org.ucb.encuentra.feature.matchlist.data.local.MatchEntity
import org.ucb.encuentra.feature.matchlist.domain.model.MatchStatus

/** Datos semilla tomados del mockup "Resultados de Matching". Se insertan una sola vez, con la tabla vacía. */
val matchSeed: List<MatchEntity> = listOf(
    MatchEntity(
        id = "1",
        itemTitle = "Calculadora Científica",
        category = "Electrónicos",
        foundDate = "12 Oct 2023",
        matchScore = 92,
        status = MatchStatus.PENDING.name,
        reportedBrand = "Casio", foundBrand = "Casio",
        reportedColor = "Negro", foundColor = "Negro/Gris",
        reportedLocation = "Biblioteca Central, Piso 2", foundLocation = "Cafetería Central",
        foundNote = "Se encontró sobre una mesa cerca de la entrada principal."
    ),
    MatchEntity(
        id = "2",
        itemTitle = "Termo Azul Oscuro",
        category = "Accesorios",
        foundDate = "10 Oct 2023",
        matchScore = 65,
        status = MatchStatus.PENDING.name,
        reportedBrand = "Stanley", foundBrand = "Sin marca visible",
        reportedColor = "Azul oscuro", foundColor = "Azul",
        reportedLocation = "Bloque D, Aula 104", foundLocation = "Canchas Deportivas",
        foundNote = "Encontrado junto a las gradas, sin tapa."
    ),
    MatchEntity(
        id = "3",
        itemTitle = "Llavero con 3 llaves",
        category = "Llaves",
        foundDate = "08 Oct 2023",
        matchScore = 30,
        status = MatchStatus.PENDING.name,
        reportedBrand = "N/A", foundBrand = "N/A",
        reportedColor = "Plateado", foundColor = "Dorado",
        reportedLocation = "Bloque A", foundLocation = "Bloque C, Pasillo 1",
        foundNote = "Llavero simple sin distintivos claros."
    )
)
