package us.cyosp.codewonderland.dontkillurcats

import org.junit.Test

import org.junit.Assert.*
import org.junit.jupiter.api.BeforeEach
import us.cyosp.codewonderland.dontkillurcats.MODEL.Diffuser
import us.cyosp.codewonderland.dontkillurcats.MODEL.NetworkTask

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class NetworkTaskUnitTest {
    private var diffuser: Diffuser? = null

    @BeforeEach
    fun initDiffuser() {
        diffuser = Diffuser()
    }

    @Test
    fun testUpdateLights() {
        val netTask = NetworkTask(
            "localhost:3000",
            "testApiKey",
            NetworkTask.Companion.METHODS.POST,
            { print(it) },
            { print("error") }
        )

        netTask.execute()
    }
}
