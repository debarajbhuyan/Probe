package creations.maa.devraj.probe

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import com.google.ai.client.generativeai.BuildConfig
import creations.maa.devraj.probe.ui.theme.ProbeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiKey = BuildConfig.API_KEY
        enableEdgeToEdge()
        val chatViewModel = ViewModelProvider(this)[ChatViewModel::class.java]
        setContent {
            ProbeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ChatPage(modifier = Modifier.padding(innerPadding), chatViewModel)
                }
            }
        }
    }
}