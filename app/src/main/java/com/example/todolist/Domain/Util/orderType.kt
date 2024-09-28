package com.example.todolist.Domain.Util

sealed class orderType {
    object ascendingOrder : orderType()
    object descendingOrder : orderType()
}