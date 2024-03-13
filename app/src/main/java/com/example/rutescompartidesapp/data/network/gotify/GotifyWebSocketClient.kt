package com.example.rutescompartidesapp.data.network.gotify

import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener


class GotifyWebSocketClient {

    private var webSocket: WebSocket? = null

    fun connect(url: String, appToken: String) {
        val request = Request.Builder().url(url).build()
        val listener = EchoWebSocketListener()
        val client = OkHttpClient()
        webSocket = client.newWebSocket(request, listener)
        
        client.dispatcher.executorService.shutdown()
    }

    fun disconnect() {
        webSocket?.cancel()
    }

    private inner class EchoWebSocketListener : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            // Conexión establecida
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            // Manejar el mensaje recibido
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            // Conexión cerrada
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            // Error de conexión
        }
    }
}