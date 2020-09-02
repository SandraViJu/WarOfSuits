package com.sandra.domain.mapper

interface Mapper<T, R> {
    fun map(input: T): R
}