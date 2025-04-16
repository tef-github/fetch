package com.example.fetch

import com.example.fetch.model.Item
import com.example.fetch.utils.extractItemNumber
import org.junit.Assert.*
import org.junit.Test


class SortingUtilsTest {

    @Test
    fun testExtractItemNumber_valid() {
        assertEquals(41, extractItemNumber("Item 41"))
        assertEquals(201, extractItemNumber("Item 201"))
        assertEquals(5, extractItemNumber("Item 5"))
    }

    @Test
    fun testExtractItemNumber_invalidOrNull() {
        assertEquals(Int.MAX_VALUE, extractItemNumber(""))
        assertEquals(Int.MAX_VALUE, extractItemNumber(null))
        // When no digits are present, assume it sorts to the end.
        assertEquals(Int.MAX_VALUE, extractItemNumber("No number"))
    }

    @Test
    fun testSortingOrder() {
        val items = listOf(
            Item(id = 1, listId = 1, name = "Item 201"),
            Item(id = 2, listId = 1, name = "Item 20"),
            Item(id = 3, listId = 1, name = "Item 5")
        )
        // Sort using listId first, then by numeric value extracted from the name.
        val sorted = items.sortedWith(compareBy({ it.listId }, { extractItemNumber(it.name) }))

        // Expected order for listId 1:
        // "Item 5" -> "Item 20" -> "Item 201"
        assertEquals("Item 5", sorted[0].name)
        assertEquals("Item 20", sorted[1].name)
        assertEquals("Item 201", sorted[2].name)
    }
}
