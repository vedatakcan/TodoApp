package com.vedatakcan.todoapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vedatakcan.todoapp.data.model.Todo
import com.vedatakcan.todoapp.data.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository
): ViewModel() {

    // UI'da to do listesini göstermek
    private val _todos = MutableStateFlow<List<Todo>>(emptyList())
    val todos: StateFlow<List<Todo>> = _todos

    init {
        // view model başlatıldığında verileri yükle
        loadTodos()
    }

    private fun loadTodos() {
        // viewModelScope kullanarak asenkron işlemleri yönet
        viewModelScope.launch {
            todoRepository.getTodos().collect {todos->
                // Veri tabanı todosları aldıktan sonra stateflowları güncelle
                _todos.value = todos
            }
        }
    }

    fun addTodo(todo: Todo){
        viewModelScope.launch {
            todoRepository.insert(todo)
        }
    }

    fun deleteTodo(todo: Todo){
        viewModelScope.launch {
            todoRepository.delete(todo)
        }
    }

    fun updateTodo(todo: Todo){
        viewModelScope.launch {
            todoRepository.update(todo)
        }
    }

}