package com.ysn.serverotp.service

interface OtpService {

    fun createOtp(): String

    fun updateOtp(code: String): Boolean

    fun deleteOtp(code: String): Boolean

}