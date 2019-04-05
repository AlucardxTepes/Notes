package com.alucard.notes.foundations

import com.alucard.notes.notes.INoteModel
import com.alucard.notes.notes.NoteLocalModel
import com.alucard.notes.tasks.ITaskModel
import com.alucard.notes.tasks.TaskLocalModel
import toothpick.Toothpick
import toothpick.config.Module

object ApplicationScope {

    val scope = Toothpick.openScope(this).apply {
        installModules(ApplicationModule)
    }

}

object ApplicationModule : Module() {

    init {
        bind(INoteModel::class.java).toInstance(NoteLocalModel())
        bind(ITaskModel::class.java).toInstance(TaskLocalModel())
    }

}
