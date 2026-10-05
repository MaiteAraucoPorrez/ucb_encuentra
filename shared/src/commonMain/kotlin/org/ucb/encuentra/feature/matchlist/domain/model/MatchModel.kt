package org.ucb.encuentra.feature.matchlist.domain.model

enum class MatchStatus { PENDING, CLAIMED, REJECTED }

data class MatchModel(
    val id: String,
    val itemTitle: String,
    val category: String,
    val foundDate: String,
    val matchScore: Int, // 0..100, "% de coincidencia" (nunca un match binario ciego)
    val status: MatchStatus,
    // comparación "lo que reportaste" vs "lo que se encontró", como en el mockup de Detalle de Match
    val reportedBrand: String,
    val foundBrand: String,
    val reportedColor: String,
    val foundColor: String,
    val reportedLocation: String,
    val foundLocation: String,
    val foundNote: String
)
