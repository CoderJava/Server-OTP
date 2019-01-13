package com.ysn.serverotp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@SpringBootApplication
class ServerOtpApplication {

    @GetMapping("/api/index")
    fun index(): ResponseEntity<Map<String, Any>> {
        val responseData = HashMap<String, Any>()
        responseData["time_server"] = System.currentTimeMillis()
        return ResponseEntity(responseData, HttpStatus.OK)
    }

}

fun main(args: Array<String>) {
    runApplication<ServerOtpApplication>(*args)
}
