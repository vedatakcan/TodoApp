package com.vedatakcan.todoapp.ui.view
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
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
