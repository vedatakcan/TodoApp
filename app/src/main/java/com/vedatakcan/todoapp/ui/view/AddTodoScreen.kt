package com.vedatakcan.todoapp.ui.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vedatakcan.todoapp.data.model.Todo
import com.vedatakcan.todoapp.ui.view.AddTodoScreen
import com.vedatakcan.todoapp.ui.viewmodel.TodoViewModel

@Composable
fun AddTodoScreen(
    viewModel: TodoViewModel,
    onTodoAdded: () -> Unit
) {
   // val viewModel: TodoViewModel = hiltViewModel()  // Hilt ile ViewModel alımı
    var title by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Yeni görev ekle", fontSize = 20.sp, modifier = Modifier.padding(bottom = 8.dp))

        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = {Text("Başlık")},
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (title.isNotBlank()){
                    val todo = Todo(title=title)
                    viewModel.addTodo(todo)
                    title = ""
                    onTodoAdded()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Ekle")
        }
    }
}