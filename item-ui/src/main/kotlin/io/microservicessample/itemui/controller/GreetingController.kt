package io.microservicessample.itemui.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader

@Controller
// todo move to router
// todo maybe refactor? do I need this example?
class GreetingController {

    @GetMapping("/")
    fun greetingView(@RequestHeader("logged-in-user") loggedInUser: String,
                     model: Model): String {
        model.addAttribute("greeting", "Hi, $loggedInUser")
        return "greeting"
    }
}