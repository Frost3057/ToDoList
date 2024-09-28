package com.example.todolist.Domain.Util

sealed class basisof(val orderType: orderType) {
    class time(orderType: orderType) : basisof(orderType)
}