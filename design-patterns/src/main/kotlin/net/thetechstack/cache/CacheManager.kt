package net.thetechstack.cache

class CacheManager<E>(private val capacity:Int){
    private val cache:LRUCache<E> = LRUCache(capacity)

    fun put(id:Int, data:E) {
        cache.put(id, data)
    }
}