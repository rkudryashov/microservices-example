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

    private val store = mutableMapOf<Long, Item>()
    private var nextId: Long = 0

    fun findById(id: Long) = store[id]

    fun findAll() = store.values

    fun save(item: Item): Item {
        if (item.id == null) {
            nextId = nextId.inc()
            item.id = nextId
        }
        item.id?.let { store[it] = item } ?: RuntimeException("Item must have id")
        return item
    }
}
