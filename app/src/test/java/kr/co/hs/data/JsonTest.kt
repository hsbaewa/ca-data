package kr.co.hs.data

import kotlinx.coroutines.test.runTest
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.add
import kotlinx.serialization.json.buildJsonArray
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import kr.co.hs.data.KotlinJson.Companion.getListOrNull
import kr.co.hs.data.KotlinJson.Companion.getOrNull
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.robolectric.annotation.Config
import tech.apter.junit.jupiter.robolectric.RobolectricExtension

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@ExtendWith(RobolectricExtension::class)
@Config(sdk = [Config.OLDEST_SDK])
class JsonTest {
    @Test
    fun do_test_json() = runTest {
        val kotlinJson = KotlinJson()
        val jsonObject = buildJsonObject {
            put("textKey", "text")
            put("intKey", 1)
            put(
                "arr",
                buildJsonArray {
                    add("a")
                    add("b")
                }
//                null
            )
        }

        val json = kotlinJson.toJson(jsonObject)
        assertNotNull(json)

        val jsonObject2 = kotlinJson.fromJson<JsonObject>(json)
        assertEquals("text", jsonObject2.getOrNull<String>("textKey"))
        assertEquals(1, jsonObject2.getOrNull<Int>("intKey"))

        val arr = jsonObject2.getListOrNull<String>("arr")
        assertNotNull(arr)
    }
}