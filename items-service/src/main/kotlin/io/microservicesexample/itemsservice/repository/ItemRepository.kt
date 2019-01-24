package io.microservicesexample.itemsservice.repository

import io.microservicesexample.itemsservice.model.Item
import org.springframework.stereotype.Repository
import javax.annotation.PostConstruct

@Repository
class ItemRepository {

    @PostConstruct
    private fun init() {
        save(Item(null, "first"))
        save(Item(null, "second"))
    }

    private val store = mutableListOf<Item>()

    fun findById(id: Long) = store.singleOrNull { item -> item.id == id }

    fun findAll() = store

    fun save(item: Item): Item {
        if (item.id == null) {
            item.id = (store.size + 1).toLong()
        }
        store.add(item)
        return item
    }
}
