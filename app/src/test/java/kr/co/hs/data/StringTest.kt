package kr.co.hs.data

import kotlinx.coroutines.test.runTest
import kr.co.hs.data.extension.StringExtension.toMD5
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.robolectric.annotation.Config
import tech.apter.junit.jupiter.robolectric.RobolectricExtension

@ExtendWith(RobolectricExtension::class)
@Config(sdk = [Config.OLDEST_SDK])
class StringTest {
    @Test
    fun do_test_md5() = runTest {
        val md5Hash = "com.neowiz.games.newmatgoKakao".toMD5()
        assertEquals("55cb925f58fdcecf66a6adecc2c5149e", md5Hash)
    }
}