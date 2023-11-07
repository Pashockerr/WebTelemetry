package com.github.pashockerr.webtelemetry.components

import com.github.pashockerr.webtelemetry.models.Values
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.Builder;
import org.slf4j.Logger
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.io.IOException

@Component
class ValuesComponent(
    private val okHttpClient: OkHttpClient,
    private val requestUrl: String,
    private val requestTiming: Long,
    private val logger: Logger
) {
    var cachedValues: Values? = null
        private set

    @Scheduled(fixedDelayString = "\${request.delay}")
    @Throws(IOException::class)
    private fun updateCachedValues() {
        val request: Request = Builder().url(requestUrl).header("Connection", "close").build()
        val call = okHttpClient.newCall(request)
        val response = call.execute()
        val stream = response.body!!.charStream()
        val stringBuilder = StringBuilder()
        val body = stream.readText()
        //Random rand = new Random();
        if(cachedValues == null){
            cachedValues = Values(body)
        }
        else{
            cachedValues!!.data = body
        }
        println(body)
        response.close()
    }
}
