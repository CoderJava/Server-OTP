package com.ysn.serverotp.service.impl

import com.ysn.serverotp.domain.Otp
import com.ysn.serverotp.repository.OtpRepository
import com.ysn.serverotp.service.OtpService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service(value = "otpService")
class OtpServiceImpl : OtpService {

    @Autowired
    private lateinit var otpRepository: OtpRepository

    override fun createOtp(): String {
        var newCodeOtp: String?
        while (true) {
            newCodeOtp = ""
            for (a in 1..4) {
                newCodeOtp += Random().nextInt(10)
            }
            otpRepository.findByCode(newCodeOtp) ?: break

        }
        val newOtp = Otp(code = newCodeOtp!!, isActive = true)
        otpRepository.save(newOtp)
        return newCodeOtp
    }

    override fun updateOtp(code: String): Boolean {
        val otpLocal = otpRepository.findByCode(code)
        otpLocal?.isActive = false
        return if (otpLocal != null) {
            otpRepository.save(otpLocal)
            true
        } else {
            false
        }
    }

    override fun deleteOtp(code: String): Boolean {
        val otpLocal = otpRepository.findByCode(code)
        otpLocal?.isActive = true
        return if (otpLocal != null) {
            otpRepository.delete(otpLocal)
            true
        } else {
            false
        }
    }

}