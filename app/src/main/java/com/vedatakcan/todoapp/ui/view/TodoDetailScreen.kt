package com.vedatakcan.todoapp.ui.view

import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vedatakcan.todoapp.data.model.Todo
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoDetailScreen(
    todo: Todo,
    onBack: () -> Unit
) {
    val formattedDate = remember(todo.createdAt){
        val sdf = SimpleDateFormat("dd MMM yyyy - HH:mm", Locale.getDefault())
        sdf.format(Date(todo.createdAt))
    }

    Scaffold(
       topBar = {
           TopAppBar(title = { Text("Görev Detayı") })
       }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(text = "Başlık:", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = todo.title, style = MaterialTheme.typography.bodyLarge)

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Durum: ${if (todo.isDone) "Tamamlandı" else "devam ediyor"}")

            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Oluşturulma Tarihi:", style = MaterialTheme.typography.labelLarge)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = formattedDate, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
