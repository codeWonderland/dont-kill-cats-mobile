package us.cyosp.codewonderland.dontkillurcats.CONTROLLER

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.SeekBar
import org.json.JSONObject
import us.cyosp.codewonderland.dontkillurcats.MODEL.Diffuser
import us.cyosp.codewonderland.dontkillurcats.MODEL.NetworkTask
import us.cyosp.codewonderland.dontkillurcats.R

class MainActivity : AppCompatActivity() {

    // Light Toggle Button
    private var mLightToggle: ImageButton? = null

    // Color Picker Button
    private var mColorPicker: ImageButton? = null

    // Night Mode Toggle Button
    private var mNightToggle: ImageButton? = null

    // Light Brightness Slider
    private var mBrightnessSlider: SeekBar? = null

    // Pet Mode Toggle Button
    private var mPetToggle: ImageButton? = null

    // Power Toggle Button
    private var mPowerToggle: ImageButton? = null

    // Diffuser State
    private var mDiffuser: Diffuser = Diffuser()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Grab References to UI components
        mLightToggle = findViewById(R.id.lightToggle)
        mColorPicker = findViewById(R.id.colorChange)
        mNightToggle = findViewById(R.id.nightMode)
        mBrightnessSlider = findViewById(R.id.lightSlider)
        mPetToggle = findViewById(R.id.petToggle)
        mPowerToggle = findViewById(R.id.powerToggle)

        mLightToggle?.setOnClickListener {
            val lightState = mDiffuser.toggleLight()

            toggleLights(lightState)
        }
    }


    private fun toggleLights(on: Boolean) {
        netTask(
            "/lights",
            NetworkTask.Companion.METHODS.POST,
            mapOf(Pair("lights", on))
        ) { print(it) }
    }

    private fun netTask(
        path: String,
        method: NetworkTask.Companion.METHODS,
        data: Map<String, Any>,
        callback: (Map<String, Any>) -> Unit
    ) {
        val netTask = NetworkTask(
            "localhost:3000/",
            "testApiKey",
            NetworkTask.Companion.METHODS.POST,
            { print(it) },
            { print("error") }
        )

        netTask.execute()
    }
}
