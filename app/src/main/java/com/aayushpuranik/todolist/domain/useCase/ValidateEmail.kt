package com.aayushpuranik.todolist.domain.useCaseimport android.util.Patternsclass ValidateEmail {    fun execute(email: String?): ValidateResult {        email?.let {            if (Patterns.EMAIL_ADDRESS.matcher(email).matches()) {                return ValidateResult(true)            } else {                return ValidateResult(false, "Please enter proper email")            }        }?.also {            return ValidateResult(false, "Please enter proper email")        }        return ValidateResult(false, "Please enter proper email")    }}