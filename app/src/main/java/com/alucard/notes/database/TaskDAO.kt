package com.alucard.notes.database

import androidx.room.*
import com.alucard.notes.models.Task
import com.alucard.notes.models.TaskEntity
import com.alucard.notes.models.Todo

@Dao
interface TaskDAO {

    @Insert
    fun addTask(taskEntity: TaskEntity)

    @Insert
    fun addTodo(todo: Todo)

    @Update
    fun updateTask(taskEntity: TaskEntity)

    @Delete
    fun deleteTask(taskEntity: TaskEntity)

    @Query("SELECT * FROM tasks")
    fun retrieveTasks(): List<Task>


}