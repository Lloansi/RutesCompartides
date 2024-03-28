package com.example.rutescompartidesapp.view.prueba

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatViewModel: ViewModel() {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()

    private val client: OkHttpClient = OkHttpClient()
    var webSocket: WebSocket? = null

    fun createRequest(): Request {
        return Request
            .Builder()
            .url("ws://localhost:6969/chatbueno")
            .build()
    }

    // En vez de crear una clase, creamos un objecto y lo linkeamos a una variable, a efectos prácticos, lo mismo
    // El WebSocketListener() nos da metodos básicos de websocket, como en este caso, enviar un mensaje
    private val listener = object : WebSocketListener () {
        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            val jsonToString = Json.decodeFromString<Message>(text)
            println("New message received")
            _messages.update { it + jsonToString }
        }
    }

    fun connect(){
        webSocket = client.newWebSocket(createRequest() ,listener)
    }
    fun sendMessage (message: Message){
        val stringToJson = Json.encodeToString(message)
        webSocket?.send(stringToJson)
    }
    fun shutdown (){
        webSocket?.close(100,"Closed Manually")
        client.dispatcher.executorService.shutdown()
    }
}