package com.example.singletonp

class User (var name : String = "Laura AM", var email : String = "lalvmun@hotmail.es", var password : String = "123456") {
    override fun toString(): String {
        return "$name, $email"
    }
    companion object{
        val user = User()
    }
}