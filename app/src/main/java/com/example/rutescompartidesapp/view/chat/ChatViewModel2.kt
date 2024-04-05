package com.example.rutescompartidesapp.view.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.rutescompartidesapp.data.domain.chat.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener

class ChatViewModel2: ViewModel()  {
    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val messages = _messages.asStateFlow()
    private lateinit var client: OkHttpClient
    private lateinit var webSocket: WebSocket

    private val listener = object : WebSocketListener() {
        override fun onOpen(webSocket: WebSocket, response: Response) {
            super.onOpen(webSocket, response)
            println("Connected to chat")
        }

        override fun onMessage(webSocket: WebSocket, text: String) {
            super.onMessage(webSocket, text)
            //onMessageReceived(text)
            println("New message received")
            println(webSocket)
            println(text)
            //val jsonToString = Json.decodeFromString<Message>(text)
            _messages.update { it + Message("asdasd", text) }
        }

        override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
            super.onFailure(webSocket, t, response)
            println("Failed socket | Reason: $t Response: $response")
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

    suspend fun connectToWebSocket(){
        withContext(Dispatchers.IO) {
            client = OkHttpClient.Builder()
                .build()

            val request = Request.Builder()
                .url("ws://89.47.29.153:27040/chat")
                .build()

            webSocket = client.newWebSocket(request, listener)
        }
    }

    fun sendMessage (message: Message){
        //val stringToJson = Json.encodeToString(message)
        webSocket.send(message.text)
    }
    fun shutdown (){
        webSocket.close(100,"Closed Manually")
        client.dispatcher.executorService.shutdown()
    }
}