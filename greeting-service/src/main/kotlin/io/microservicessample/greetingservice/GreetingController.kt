package io.microservicessample.greetingservice

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestHeader

@Controller
class GreetingController {

    @GetMapping("/")
    fun greetingView(@PathVariable salutation: String?,
                     @RequestHeader("logged-in-user") loggedInUser: String,
                     model: Model): String {
        model.addAttribute("greeting", "${salutation ?: "Hi"}, $loggedInUser")
        return "greeting"
    }
}