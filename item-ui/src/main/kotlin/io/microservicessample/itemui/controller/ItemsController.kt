package io.microservicessample.itemui.controller

import io.microservicessample.itemui.service.ItemServiceClient
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

// todo move to router
@Controller
@RequestMapping("/items")
internal class ItemsController(
        private val itemServiceClient: ItemServiceClient
) {

    // todo more appropriate name
    @GetMapping("/example")
    fun example(model: Model): String {
        model.addAttribute("requestWithRestTemplate", itemServiceClient.requestWithRestTemplate())
//        model.addAttribute("requestWithWebClient", itemServiceClient.requestWithWebClient().map { it })
        return "example"
    }
}
