package com.example.rutescompartidesapp.view.chat

import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.chat.Message
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatViewModel: ViewModel() {
    //private val _messages = MutableStateFlow<List<Message>>(emptyList())
    //val messages = _messages.asStateFlow()

    private val client: OkHttpClient = OkHttpClient()
    private lateinit var webSocket: WebSocket
    //var webSocket: WebSocket? = null

    fun createRequest(): Request {
        return Request
            .Builder()
            .url("ws://89.47.29.153:27040/chat")
            .build()
    }

    // En vez de crear una clase, creamos un objecto y lo linkeamos a una variable, a efectos prácticos, lo mismo
    // El WebSocketListener() nos da metodos básicos de websocket, como en este caso, enviar un mensaje
    private val listener = object : WebSocketListener () {
        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            val jsonToString = Json.decodeFromString<Message>(text)
            println("New message received")
            //_messages.update { it + jsonToString }
        }

        override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosing(webSocket, code, reason)
            println("Closing socket | Reason: $reason Code: $code")
        }

        override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
            super.onClosed(webSocket, code, reason)
            println("Closed socket | Reason: $reason Code: $code")

        }
    }

     fun connect(){
        webSocket = client.newWebSocket(createRequest() ,listener)
    }
    fun sendMessage (message: Message){
        val stringToJson = Json.encodeToString(message)
        webSocket.send(stringToJson)
    }
    fun shutdown (){
        webSocket.close(100,"Closed Manually")
        client.dispatcher.executorService.shutdown()
    }
}