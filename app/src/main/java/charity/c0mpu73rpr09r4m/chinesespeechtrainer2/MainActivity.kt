package charity.c0mpu73rpr09r4m.chinesespeechtrainer2

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import charity.c0mpu73rpr09r4m.chinesespeechtrainer2.ui.theme.ChineseSpeechTrainerTheme

class MainActivity : ComponentActivity() {
    private var displayText by mutableStateOf("")
    private  var dictionaryEntry : DictionaryEntry? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ChineseSpeechTrainerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val logic = DataLogic()
                    dictionaryEntry = logic.getTranslation(this, 0)

                    if(dictionaryEntry == null) {
                        val intent = Intent(this, WinActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        updateText("${dictionaryEntry?.englishWord}\n\n${dictionaryEntry?.chineseWord}\n\n${dictionaryEntry?.pinyin}\n")
                    }

                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = displayText,
                            fontSize = 32.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }

    private fun updateText(text: String) {
        displayText = text
    }
}