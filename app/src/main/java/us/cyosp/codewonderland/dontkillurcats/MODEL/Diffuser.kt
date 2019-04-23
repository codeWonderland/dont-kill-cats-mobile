package us.cyosp.codewonderland.dontkillurcats.MODEL

class Diffuser {
    companion object {
        val sServerAddress = "localhost"
        val sApiKey = "testKey"
    }
    var mLightOn: Boolean = false

    var mAlpha: Int = 0
    var mRed: Int = 0
    var mGreen: Int = 0
    var mBlue: Int = 0

    val mColor: Int
        get() = android.graphics.Color.argb(mAlpha, mRed, mGreen, mBlue)

    fun updateColor(red: Int, green: Int, blue: Int) {
        mRed = red
        mGreen = green
        mBlue = blue
    }

    fun toggleLight(): Boolean {
        mLightOn = !mLightOn
        return mLightOn
    }

    fun updateDiffuser(resp: String) {
        print(String)
    }
}