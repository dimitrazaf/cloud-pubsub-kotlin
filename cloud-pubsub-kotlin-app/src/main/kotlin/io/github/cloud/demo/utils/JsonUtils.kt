package io.github.cloud.demo.utils

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule

val mapper = ObjectMapper()
        .registerModules(KotlinModule(), JavaTimeModule())
        .setSerializationInclusion(JsonInclude.Include.NON_NULL)!!

fun <T> fromJson(value: String?, type: Class<T>): T? {
    return when (value) {
        null -> return null
        else -> mapper.readValue(value, type)
    }
}