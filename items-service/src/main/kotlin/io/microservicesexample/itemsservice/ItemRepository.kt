package io.microservicesexample.itemsservice

import org.springframework.stereotype.Repository

@Repository
class ItemRepository {

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
