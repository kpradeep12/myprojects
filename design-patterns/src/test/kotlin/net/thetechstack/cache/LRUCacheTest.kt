package net.thetechstack.cache

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class LRUCacheTest {

    @Test
    fun addElements() {
        val cache = LRUCache<User>(3)
        cache.put(1, User(1))
        cache.put(2, User(2))
        assertEquals(listOf(2,1), cache.allKeys())
    }

    @Test fun `add more than capacity`(){
        val cache = LRUCache<User>(3)
        cache.put(1, User(1))
        cache.put(2, User(2))
        cache.put(3, User(3))
        cache.put(4, User(4))
        assertEquals(listOf(4,3,2), cache.allKeys())
    }

    @Test fun `capacity overflow`(){
        val cache = LRUCache<User>(3)
        cache.put(1, User(1))
        assertEquals(User(1), cache.get(1))

        cache.put(2, User(2))
        cache.put(3, User(3))
        cache.put(4, User(4))
        assertEquals(null, cache.get(1))
        cache.get(3)
        assertEquals(listOf(3,4,2), cache.allKeys())
    }

    @Test fun `capacity with 0 size`(){
        val cache = LRUCache<User>(0)
        cache.put(1, User(1))
        cache.put(2, User(2))
        assertEquals(emptyList<Int>(), cache.allKeys())
    }

    @Test fun `capacity with 1 size`(){
        val cache = LRUCache<User>(1)
        cache.put(1, User(1))
        cache.put(2, User(2))
        assertEquals(listOf(2), cache.allKeys())
    }

    @Test fun `get elements`(){
        val cache = LRUCache<User>(2)
        cache.put(1, User(1))
        cache.put(2, User(2))
        assertEquals(listOf(2,1), cache.allKeys())
        cache.put(3, User(3))
        assertEquals(listOf(3,2), cache.allKeys())
        assertEquals(User(2), cache.get(2))
        assertEquals(null, cache.get(1))
        assertEquals(null, cache.get(4))
    }
}