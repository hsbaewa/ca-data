package kr.co.hs.data

import io.ktor.serialization.JsonConvertException
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.jsonArray
import kotlinx.serialization.json.jsonObject

class KotlinJson {
    val json: Json = kotlinJson

    inline fun <reified T> fromJson(json: String): T = this.json.decodeFromString<T>(json)

    inline fun <reified T> fromJsonOrNull(json: String): T? =
        this.json.runCatching { decodeFromString<T>(json) }.getOrNull()

    fun toJson(obj: JsonElement): String = json.encodeToString(obj)

    inline fun <reified T> toJson(value: T): JsonElement = json.encodeToJsonElement(value)

    inline fun <reified T> toJsonString(value: T): String = json.encodeToString(value)

    companion object {
        val kotlinJson = Json {
            isLenient = true
            ignoreUnknownKeys = true
            explicitNulls = false
        }

        inline fun <reified T> JsonElement.get(key: String): T = jsonObject[key]
            ?.let { kotlinJson.decodeFromJsonElement<T>(it) }
            ?: throw JsonConvertException("not found value from key : $key")

        inline fun <reified T> JsonElement.getOrNull(key: String): T? = jsonObject[key]
            ?.let { kotlinJson.runCatching { decodeFromJsonElement<T>(it) }.getOrNull() }

        inline fun <reified T> JsonElement.getList(key: String): List<T> = jsonObject[key]
            ?.jsonArray
            ?.map { kotlinJson.decodeFromJsonElement<T>(it) }
            ?: throw JsonConvertException("not found value from key : $key")

        inline fun <reified T> JsonElement.getListOrNull(key: String): List<T>? = jsonObject[key]
            ?.runCatching { jsonArray }
            ?.getOrNull()
            ?.map { kotlinJson.decodeFromJsonElement<T>(it) }
    }
}