package com.ysn.serverotp.repository

import com.ysn.serverotp.domain.Otp
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface OtpRepository : CrudRepository<Otp, Long> {

    fun findByCode(code: String): Otp?

}