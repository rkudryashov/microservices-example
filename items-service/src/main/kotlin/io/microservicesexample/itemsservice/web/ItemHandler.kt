package io.microservicesexample.itemsservice.web

import io.microservicesexample.itemsservice.exception.ItemsServiceException
import io.microservicesexample.itemsservice.model.Item
import io.microservicesexample.itemsservice.repository.ItemRepository
import org.springframework.http.MediaType.APPLICATION_JSON_UTF8
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters.fromObject
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse

@Component
class ItemHandler(private val itemRepository: ItemRepository) {

    fun getAll(request: ServerRequest) = ServerResponse.ok()
        .contentType(APPLICATION_JSON_UTF8)
        .body(fromObject(itemRepository.findAll()))

    fun getOne(request: ServerRequest) = ServerResponse.ok()
        .contentType(APPLICATION_JSON_UTF8)
        .body(
            fromObject(
                itemRepository.findById(request.pathVariable("id").toLong())
                    ?: ItemsServiceException("Request doesn't contain id")
            )
        )

    fun update(request: ServerRequest) = request.bodyToMono(Item::class.java).flatMap {
        if (it.id != request.pathVariable("id").toLong()) {
            throw ItemsServiceException("Item.id not equals path variable id")
        }
        ServerResponse.ok()
            .contentType(APPLICATION_JSON_UTF8)
            .body(fromObject(itemRepository.save(it)))
    }

    fun add(request: ServerRequest) = request.bodyToMono(Item::class.java).flatMap {
        ServerResponse.ok()
            .contentType(APPLICATION_JSON_UTF8)
            .body(fromObject(itemRepository.save(it)))
    }
}