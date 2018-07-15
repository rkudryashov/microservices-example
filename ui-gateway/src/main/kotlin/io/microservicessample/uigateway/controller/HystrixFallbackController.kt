package io.microservicessample.uigateway.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
// todo change to router
class HystrixFallbackController {

    @GetMapping("/hystrix-fallback")
    fun hystrixFallback() = "Something went wrong"
}