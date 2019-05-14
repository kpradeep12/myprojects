package net.thetechstack.cache

class LRUCache<E>(private val capacity:Int){
    private val map:MutableMap<Int, Node<E>> = mutableMapOf()
    private var queue:Deque<E> = Deque()
    private var count:Int = 0

    fun put(id:Int, data:E){
        if(map.containsKey(id)){
            queue.remove(map[id])
            queue.addFirst(map[id])
        }else {
            if (count < capacity) {
                val node = Node(id, data)
                queue.addFirst(node)
                map[id] = node
                count++
            }else if(capacity in 1..count){
                map.remove(queue.removeLast()?.id)
                val node = Node(id, data)
                queue.addFirst(node)
                map[id] = node
            }
        }
    }

    fun get(id:Int):E? {
        if(map.containsKey(id)) {
            queue.remove(map[id])
            queue.addFirst(map[id])
        }
        return map[id]?.value
    }

    fun allKeys() = queue.allKeys()

    data class Node<E>(val id:Int){
        constructor(id:Int, value:E):this(id){
            this.value = value
        }
        var value:E? = null
        var prev:Node<E>? = null
        var next:Node<E>? = null
    }

    class Deque<E>{
        private var head:Node<E>? = null
        private var tail:Node<E>? = null

        fun addFirst(node:Node<E>?){
            node?.prev = null
            node?.next = head
            head?.prev = node
            head = node
            if(tail == null) tail = node
        }

        fun remove(node:Node<E>?){
            node?.prev?.next = node?.next
            node?.next?.prev = node?.prev
            if(tail == node)
                tail = node?.prev
            if(head == node)
                head = null
        }

        fun removeLast():Node<E>?{
            return when {
                tail == null -> null
                head == tail -> {
                    val node = tail
                    head = null
                    tail = null
                    node
                }
                else -> {
                    val node = tail
                    tail = node?.prev
                    tail?.next = null
                    node
                }
            }
        }

        fun allKeys(): List<Int>{
            var temp = head
            val list = mutableListOf<Int>()
            while(temp != null){
                list.add(temp.id)
                temp = temp.next
            }
            return list
        }
    }
}