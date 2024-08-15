package creations.maa.devraj.probe

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.BuildConfig
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.launch

class ChatViewModel: ViewModel() {


    val messageList by lazy {
        mutableStateListOf<MessageModel>()
    }
    val generativeModel = GenerativeModel(
        modelName = "gemini-pro",
        apiKey = apiKey1
    )

    fun sendMessage(question: String) {
        try {
            viewModelScope.launch {
                val chat = generativeModel.startChat(
                    history = messageList.map {
                        content(it.role){text(it.message)}
                    }.toList()
                )
                messageList.add(MessageModel(question, "user"))
                messageList.add(MessageModel("Typing...", "model"))

                val response = chat.sendMessage(question)
                if (messageList.isNotEmpty()) { messageList.removeLast() }
                messageList.add(MessageModel(response.text.toString(), "model"))
            }
        } catch (e: Exception) {
            messageList.removeLast()
            messageList.add(MessageModel("Error: "+e.message.toString(), "model"))
        }
    }
}