package com.example.todolist.Presentation.ViewModels.Home

import com.example.todolist.Data.Task
import com.example.todolist.Domain.Util.basisof
import com.example.todolist.Domain.Util.orderType

data class TASKSTATE(
    val tasks : List<Task> = emptyList(),
    val orderType1: basisof = basisof.time(orderType.ascendingOrder)
)