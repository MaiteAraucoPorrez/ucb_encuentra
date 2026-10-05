package org.ucb.encuentra

import org.ucb.encuentra.feature.matchlist.domain.model.MatchModel
import org.ucb.encuentra.feature.matchlist.domain.model.MatchStatus
import org.ucb.encuentra.feature.matchlist.domain.usecase.FilterMatchesByCategoryUseCase
import kotlin.test.Test
import kotlin.test.assertEquals

class FilterMatchesByCategoryUseCaseTest {

    private fun match(id: String, category: String) = MatchModel(
        id = id, itemTitle = "Objeto $id", category = category, foundDate = "01 Oct 2023",
        matchScore = 50, status = MatchStatus.PENDING,
        reportedBrand = "", foundBrand = "", reportedColor = "", foundColor = "",
        reportedLocation = "", foundLocation = "", foundNote = ""
    )

    private val matches = listOf(match("1", "Electrónicos"), match("2", "Llaves"), match("3", "Electrónicos"))
    private val filter = FilterMatchesByCategoryUseCase()

    @Test
    fun nullCategoryReturnsAll() {
        assertEquals(matches, filter(matches, null))
    }

    @Test
    fun filtersByCategory() {
        assertEquals(listOf("1", "3"), filter(matches, "Electrónicos").map { it.id })
    }

    @Test
    fun unknownCategoryReturnsEmpty() {
        assertEquals(emptyList(), filter(matches, "Libros"))
    }
}
