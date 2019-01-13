package com.ysn.serverotp.controller

import com.ysn.serverotp.service.OtpService
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/otp")
class OtpController {

    @Autowired
    private lateinit var otpService: OtpService

    @PostMapping("/send")
    fun sendOtp(@RequestParam phoneNumber: String): ResponseEntity<Map<String, Any>> {
        val codeOtp = otpService.createOtp()
        val formRequestBody = FormBody.Builder()
                .add("apikey", "nilai-api-key")
                .add("message", codeOtp)
                .add("sender", "System")
                .add("numbers", phoneNumber)
                .build()
        val request = Request.Builder()
                .url("https://api.txtlocal.com/send/")
                .post(formRequestBody)
                .build()
        val call = OkHttpClient().newCall(request)
        val response = call.execute()
        val responseData = HashMap<String, Any>()
        responseData["time_server"] = System.currentTimeMillis()

        val isSuccessful = response.isSuccessful
        val responseCode = response.code()
        val jsonObjectResponseFromServer = JSONObject(response.body().string())
        val responseStatusMessage = jsonObjectResponseFromServer.getString("status")
        System.out.println("responseDataFromServer: $jsonObjectResponseFromServer")

        if (isSuccessful && responseCode == 200 && responseStatusMessage == "success") {
            responseData["success"] = true
        } else {
            responseData["success"] = false
            otpService.deleteOtp(codeOtp)
        }
        return ResponseEntity(responseData, HttpStatus.OK)
    }

    @PostMapping("/update")
    fun updateOtp(@RequestParam codeOtp: String): ResponseEntity<Map<String, Any>> {
        val responseData = HashMap<String, Any>()
        responseData["time_server"] = System.currentTimeMillis()
        responseData["success"] = otpService.updateOtp(codeOtp)
        return ResponseEntity(responseData, HttpStatus.OK)
    }

}