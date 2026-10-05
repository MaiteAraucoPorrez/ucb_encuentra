package org.ucb.encuentra.feature.matchlist.data.mapper

import org.ucb.encuentra.feature.matchlist.data.local.MatchEntity
import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel
import org.ucb.encuentra.feature.matchlist.domain.model.MatchStatus

fun MatchEntity.toDomain(): MatchModel = MatchModel(
    id = id,
    itemTitle = itemTitle,
    category = category,
    foundDate = foundDate,
    matchScore = matchScore,
    status = MatchStatus.valueOf(status),
    reportedBrand = reportedBrand,
    foundBrand = foundBrand,
    reportedColor = reportedColor,
    foundColor = foundColor,
    reportedLocation = reportedLocation,
    foundLocation = foundLocation,
    foundNote = foundNote
)
