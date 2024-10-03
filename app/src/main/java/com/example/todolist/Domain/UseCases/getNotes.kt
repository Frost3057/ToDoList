package com.example.todolist.Domain.UseCases

import com.example.todolist.Data.Task
import com.example.todolist.Domain.TaskRepo
import com.example.todolist.Domain.Util.basisof
import com.example.todolist.Domain.Util.orderType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class getNotes( val TaskRepo:TaskRepo){
    operator fun invoke(Basisof: basisof = basisof.time(orderType.ascendingOrder)): Flow<List<Task>> {
        return TaskRepo.getTasks().map {
            tasks->
            when(Basisof.orderType){
                is orderType.ascendingOrder->{
                    tasks.sortedBy { it.timeStamp }
                }
                is orderType.descendingOrder->{
                    tasks.sortedBy { it.timeStamp }
                }
            }
        }
    }
}