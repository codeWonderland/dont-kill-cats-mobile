package us.cyosp.codewonderland.dontkillurcats.MODEL

import android.os.AsyncTask

import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import android.net.Proxy.getHost



class NetworkTask(
    private val mAddress: String,
    private val mApiKey: String,
    private val mMethod: METHODS,
    private val mSuccess: (String) -> Unit,
    private val mFailure: () -> Unit,
    private val mData: String? = null
) : AsyncTask<String, String, String>() {

    companion object {
        enum class METHODS {
            GET, POST
        }
    }

    private var mResponse = ""

    // is run upon execute()
    override fun doInBackground(vararg params: String): String? {
        // create initial api url with auth params
        var urlString = "$mAddress?key=$mApiKey"

        // if we are posting data we append that as well
        if (mData != null) {
            urlString += "&message=$mData"
        }

        // create url from strong
        val url = URL(mAddress)

        // open connection to url
        val con = url.openConnection() as HttpURLConnection

        try {
            // establish the current request method
            con.requestMethod = this.mMethod.name

            // make request and grab resp code
            val responseCode = con.responseCode
            println("Sending ${mMethod.name} Request to URL: $mAddress")
            println("Response Code: $responseCode")

            // create input stream for request data
            val inStream = BufferedReader(
                InputStreamReader(con.inputStream)
            )

            // establish variables for data
            var inputLine: String? = inStream.readLine()
            val servResponse = StringBuilder()

            // get data while there is data to grab
            while (inputLine != null) {
                servResponse.append(inputLine)
                inputLine = inStream.readLine()
            }

            // close stream when finished
            inStream.close()

            //print result
            println("Server responded with " + servResponse.toString())
            this.mResponse = servResponse.toString()

        } catch (e: Exception) {
            // if there is an issue we call the failure event
            mFailure()
        }

        return this.mResponse
    }

    override fun onPostExecute(resp: String) {
        // send response data to callback
        this.mSuccess(mResponse)
    }
}
