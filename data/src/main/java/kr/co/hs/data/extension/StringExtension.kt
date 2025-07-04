package kr.co.hs.data.extension

import java.math.BigInteger
import java.security.MessageDigest

@Suppress("unused")
object StringExtension {

    fun String.toMD5() = this.toByteArray().toMD5()

    fun ByteArray.toMD5() = MessageDigest.getInstance("MD5")
        .runCatching {
            update(this@toMD5, 0, this@toMD5.size)
            val raw = BigInteger(1, digest()).toString(16)
            val padding = "00000000000000000000000000000000".substring(0, 32 - raw.length)
            padding + raw
        }
        .getOrDefault("00000000000000000000000000000000")

    fun String.toJwtFragmentsOrNull() = runCatching {
        this.replace(" ", "+")
            .replace("_", "/")
            .replace("-", "+")
            .split(".")
            .takeIf { it.size > 2 }
    }.getOrNull()
}