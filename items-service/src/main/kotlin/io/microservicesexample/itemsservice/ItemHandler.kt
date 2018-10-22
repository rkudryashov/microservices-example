package io.microservicesexample.itemsservice

import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class ItemHandler(private val itemRepository: ItemRepository) {

    fun getAllItems(request: ServerRequest) = ServerResponse.ok()
            .contentType(APPLICATION_JSON_UTF8)
            .body(fromObject(itemRepository.findAll()))

    fun getItem(request: ServerRequest) = ServerResponse.ok()
            .contentType(APPLICATION_JSON_UTF8)
            .body(fromObject(itemRepository.findById(request.pathVariable("id").toLong())
                    ?: ItemsServiceException("Request doesn't contain id")))

    fun updateItem(request: ServerRequest) = request.bodyToMono(Item::class.java).flatMap {
        if (it.id != request.pathVariable("id").toLong()) {
            throw ItemsServiceException("Item.id not equals path variable id")
        }
        ServerResponse.ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(fromObject(itemRepository.save(it)))
    }

    fun addItem(request: ServerRequest) = request.bodyToMono(Item::class.java).flatMap {
        ServerResponse.ok()
                .contentType(APPLICATION_JSON_UTF8)
                .body(fromObject(itemRepository.save(it)))
    }
}