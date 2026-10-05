package org.ucb.encuentra.feature.matchlist.data.local

import androidx.room3.Entity
import androidx.room3.PrimaryKey

/** `status` se guarda como texto (nombre del enum MatchStatus) para no necesitar TypeConverters. */
@Entity(tableName = "matches")
data class MatchEntity(
    @PrimaryKey val id: String,
    val itemTitle: String,
    val category: String,
    val foundDate: String,
    val matchScore: Int,
    val status: String,
    val reportedBrand: String,
    val foundBrand: String,
    val reportedColor: String,
    val foundColor: String,
    val reportedLocation: String,
    val foundLocation: String,
    val foundNote: String
)
