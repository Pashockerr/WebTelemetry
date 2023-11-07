package com.github.pashockerr.webtelemetry

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Request.Builder
import okhttp3.Response
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableScheduling
import java.io.IOException
import java.io.PrintWriter
import java.net.SocketTimeoutException
import java.time.Duration
import java.util.concurrent.TimeUnit
import kotlin.time.DurationUnit

@EnableScheduling
@SpringBootApplication
open class WebTelemetryApplication {
    @Value("\${request.search}")
    private val searchMask: String? = null

    @Value("\${request.delay}")
    private val requestDelay: Long? = null


    @Bean
    open fun okHttpClient(): OkHttpClient {
        return OkHttpClient().newBuilder().connectTimeout(10000, TimeUnit.MILLISECONDS).build()
    }

    //Scanning for telemetry server
    @Bean
    @Throws(Exception::class)
    open fun requestUrl(okHttpClient: OkHttpClient, logger: Logger): String? {
        return searchMask
    }

    @Bean
    open fun logger(): Logger? {
        return logger
    }

    @Bean
    open fun requestDelay(): Long{
        return requestDelay ?: 5000
    }


    companion object {
        private var logger: Logger? = null
        private var url: String? = null
        @JvmStatic
        fun main(args: Array<String>) {
            logger = LoggerFactory.getLogger(WebTelemetryApplication::class.java)
            SpringApplication.run(WebTelemetryApplication::class.java, *args)
        }
    }
}
