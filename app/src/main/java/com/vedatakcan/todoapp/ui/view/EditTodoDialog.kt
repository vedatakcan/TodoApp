package com.vedatakcan.todoapp.ui.view

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.vedatakcan.todoapp.data.model.Todo

@Composable
fun EditTodoDialog(
    todo: Todo,
    onDismiss: () -> Unit,
    onSave: (String) -> Unit
) {
    var updatedTitle by remember { mutableStateOf(todo.title) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Görevi Düzenle") },
        text = {
            OutlinedTextField(
                value = updatedTitle,
                onValueChange = { updatedTitle = it },
                label = { Text("Başlık") }
            )
        },
        confirmButton = {
            TextButton(onClick = { onSave(updatedTitle) }) {
                Text("Kaydet")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("İptal")
            }
        }
    )
}
