package com.avengers.api.application.web.resource

import com.avengers.api.application.domain.avenger.AvengerRepository
import com.avengers.api.application.web.resource.request.AvengersRequest
import com.avengers.api.application.web.resource.request.response.AvengersResponse
import jakarta.validation.Valid
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI
private const val API_PATH = "v1/api/avenger"
@RestController
@RequestMapping(API_PATH)
class AvengerResource(@Autowired private  val avengerRepository: AvengerRepository){


    @GetMapping
    fun getAvengers()  = avengerRepository.getAvengers()
        .map { AvengersResponse.from(it) }
        .let { ResponseEntity.ok().body(it) }

    @GetMapping("{id}")
    fun getAvengerDetails(@PathVariable("id") id: Long) =
        avengerRepository.getDetail(id).let {
            ResponseEntity.ok().body(AvengersResponse.from(it))
        }

    @PostMapping("create")
    fun createAvenger(@Valid @RequestBody request: AvengersRequest) =
        request.toAvenger().run { avengerRepository.create(this)
            .let { ResponseEntity.created(URI("$API_PATH/${it.id}")) }}

}