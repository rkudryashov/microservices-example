package io.microservicessample.itemservice

class ItemRepository {

    private val store = mutableMapOf<Long, Item>()
    // todo check work
    private val nextId: Long = 1

    fun findById(id: Long) = store[id]

    fun findAll() = store.values

    fun save(item: Item): Item {
        if (item.id == null) {
            item.id = nextId.inc()
        }
        item.id?.let { store[it] = item } ?: RuntimeException("Item must have id")
        return item
    }
}
