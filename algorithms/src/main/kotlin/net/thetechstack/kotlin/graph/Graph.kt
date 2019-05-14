package net.thetechstack.kotlin.graph

data class Node(val data:Int){
    var next:Node? = null
}
class Graph(size:Int, private val isDirected:Boolean){
    private var adjNodes = Array(size) { Node(it) }

    fun addEdge(source:Int, destination:Int) = addEdge(source, destination, isDirected)

    private fun addEdge(source:Int, destination: Int, isDirected: Boolean){
        val dest = Node(destination)
        dest.next = adjNodes[source].next
        adjNodes[source].next = dest
        if (isDirected)
            addEdge(destination, source, false)
    }

    fun print(){
        adjNodes.forEach {
            var node:Node? = it
            while(node != null){
                print("${node.data} -> ")
                node = node.next
            }
            println()
        }
    }
}

fun main(){
    val graph = Graph(4,false)
    graph.addEdge(0,1)
    graph.addEdge(0,2)
    graph.addEdge(2,1)
    graph.print()
}