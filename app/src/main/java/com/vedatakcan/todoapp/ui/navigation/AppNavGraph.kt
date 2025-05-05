package com.vedatakcan.todoapp.ui.view

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.vedatakcan.todoapp.data.model.Todo
import com.vedatakcan.todoapp.ui.viewmodel.TodoViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = "todo_list"
    ) {
        // Görev listesi ekranı
        composable("todo_list") {
            // Hilt ile ViewModel'i çekiyoruz
            val viewModel: TodoViewModel = hiltViewModel()

            TodoListScreen(
                viewModel = viewModel,
                navController = navController,
                onNavigateToAddTodo = {
                    navController.navigate("add_todo")
                }
            )
        }

        // Yeni görev ekleme ekranı
        composable("add_todo") {
            val viewModel: TodoViewModel = hiltViewModel()

            AddTodoScreen(
                viewModel = viewModel,
                onTodoAdded = {
                    navController.popBackStack() // Geri dön
                }
            )
        }

        composable("detail/{todoId}") { backStackEntry ->
            val viewModel: TodoViewModel = hiltViewModel()
            val todoId = backStackEntry.arguments?.getString("todoId")?.toIntOrNull()
            val todos by viewModel.todos.collectAsState()

            val todo = todos.find { it.id == todoId }

            if (todo != null) {
                TodoDetailScreen(todo = todo, onBack = { navController.popBackStack() })
            } else {
                Text("Görev bulunamadı veya yükleniyor...")
            }
        }

    }
}
