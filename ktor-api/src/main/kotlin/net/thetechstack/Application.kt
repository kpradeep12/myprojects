package net.thetechstack

import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.routing.routing
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main() {
    embeddedServer(Netty, port = 8080){
        install(CallLogging)
        routing {

        }
    }.start(wait = true)
}

