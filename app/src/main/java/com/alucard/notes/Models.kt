package com.alucard.notes

data class Task @JvmOverloads constructor (
    var title: String,
    val todos: MutableList<Todo> = mutableListOf(),
    var tag: Tag? = null
)

class Todo(
    var description: String,
    var isComplete: Boolean
)

class Note(
    var description: String,
    var tag: Tag? = null
)

class Tag(
    val name: String,
    val colourResId: Int
)