package com.vedatakcan.todoapp.data.repository

import com.vedatakcan.todoapp.data.database.TodoDao
import com.vedatakcan.todoapp.data.model.Todo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TodoRepository @Inject constructor(private val dao: TodoDao) {

    fun getTodos(): Flow<List<Todo>> = dao.getTodos()

    suspend fun insert(todo: Todo) = dao.insert(todo)
    suspend fun delete(todo: Todo) = dao.delete(todo)
    suspend fun update(todo: Todo) = dao.update(todo)

}