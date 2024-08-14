package com.dk.auth.domain

interface PatternValidator {

    fun matches(value: String): Boolean
}