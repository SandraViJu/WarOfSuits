package com.sandra.data.mapper

interface Mapper<T, R> {
    fun map(input: T): R
}