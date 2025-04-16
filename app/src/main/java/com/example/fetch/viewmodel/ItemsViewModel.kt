package com.example.fetch.viewmodel


import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fetch.model.Item
import com.example.fetch.repo.ItemRepository
import com.example.fetch.utils.extractItemNumber
import kotlinx.coroutines.launch


class ItemsViewModel : ViewModel() {
    private val _groupedItems = mutableStateOf<Map<Int, List<Item>>>(emptyMap())
    val groupedItems: Map<Int, List<Item>> get() = _groupedItems.value

    private val _errorMessage = mutableStateOf<String?>(null)
    val errorMessage: String? get() = _errorMessage.value

    init {
        viewModelScope.launch {
            try {
                val items = ItemRepository.getItems()
                val filtered = items
                    .filter { !it.name.isNullOrBlank() }
                    .sortedWith(
                        compareBy(
                            { it.listId },
                            { extractItemNumber(it.name) }
                        )
                    )
                _groupedItems.value = filtered.groupBy { it.listId }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage ?: "An unknown error occurred."
            }
        }
    }
}