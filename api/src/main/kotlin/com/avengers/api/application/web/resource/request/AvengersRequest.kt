package com.avengers.api.application.web.resource.request

import com.avengers.api.application.domain.avenger.Avenger
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty
import org.jetbrains.annotations.NotNull

data class AvengersRequest(
    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val nick: String,
    @field:NotNull
    @field:NotBlank
    @field:NotEmpty
    val person: String,
    val description: String?,
    val history: String?,
){
    fun toAvenger()=Avenger(nick, person, description, history)
}
