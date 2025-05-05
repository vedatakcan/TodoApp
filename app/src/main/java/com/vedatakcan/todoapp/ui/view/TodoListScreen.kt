package com.vedatakcan.todoapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check

import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vedatakcan.todoapp.data.model.Todo
import com.vedatakcan.todoapp.ui.viewmodel.TodoViewModel


@Composable
fun TodoListScreen(
    viewModel: TodoViewModel,
    onNavigateToAddTodo: () -> Unit
) {
    val todos by viewModel.todos.collectAsState()
    var showDialog by remember { mutableStateOf(false) }
    var todoToEdit by remember { mutableStateOf<Todo?>(null) }

    if (showDialog && todoToEdit != null) {
        EditTodoDialog(
            todo = todoToEdit!!,
            onDismiss = {showDialog = false },
            onSave = {updatedTitle ->
                val updated =todoToEdit!!.copy(title = updatedTitle)
                viewModel.updateTodo(updated)
                showDialog = false
            }
        )
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { onNavigateToAddTodo() }) {
                Icon(Icons.Default.Add, contentDescription = "Ekle")
            }
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(todos) { todo ->
                TodoItem(
                    todo = todo,
                    onDelete = {
                        viewModel.deleteTodo(todo)
                    },
                    onToggleDone = { updatedTodo ->
                        val toggled = updatedTodo.copy(isDone = !updatedTodo.isDone)
                        viewModel.updateTodo(toggled)
                    },
                    onEdit = {
                        todoToEdit = it
                        showDialog = true
                    }
                )
            }
        }
    }
}

@Composable
fun TodoItem(
    todo: Todo,
    onDelete: () -> Unit,
    onToggleDone: (Todo) -> Unit,
    onEdit: (Todo) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (todo.isDone) "✅ ${todo.title}" else todo.title,
                modifier = Modifier.weight(1f)
            )
            IconButton(onClick = { onToggleDone(todo) }) {
                Icon(Icons.Default.Check, contentDescription = "Tamamlandı")
            }
            IconButton(onClick = {onEdit(todo)}) {
                Icon(Icons.Default.Edit, contentDescription = "Düzenle")
            }
            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Sil")
            }
        }
    }
}