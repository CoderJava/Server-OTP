package com.ysn.serverotp.domain

import org.hibernate.annotations.GenericGenerator
import org.hibernate.annotations.Parameter
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "Otp")
class Otp (
    @GenericGenerator(
            name = "otpSequenceGenerator",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = [
                Parameter(name = "sequence_name", value = "otpSequence"),
                Parameter(name = "initial_value", value = "1"),
                Parameter(name = "increment_value", value = "1")
            ]
    )

    @GeneratedValue(generator = "otpSequenceGenerator")
    @Id
    var id: Long = 0L,
    var code: String = "",
    var isActive: Boolean = false
)
