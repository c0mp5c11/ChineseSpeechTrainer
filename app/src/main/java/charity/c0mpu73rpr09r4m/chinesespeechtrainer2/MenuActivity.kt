package charity.c0mpu73rpr09r4m.chinesespeechtrainer2

import android.Manifest
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat
import charity.c0mpu73rpr09r4m.chinesespeechtrainer2.ui.theme.ChineseSpeechTrainerTheme

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            ChineseSpeechTrainerTheme {
                MenuScreen()
            }
        }
    }
}

@Composable
fun MenuScreen() {
    var clickCount by remember { mutableStateOf(0) }
    val context = LocalContext.current

    val requestPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            context.startActivity(Intent(context, MenuActivity::class.java))
        }
    }

    val hasPermission = ContextCompat.checkSelfPermission(
        context,
        Manifest.permission.RECORD_AUDIO
    ) == android.content.pm.PackageManager.PERMISSION_GRANTED

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (hasPermission) {
            Text(
                text = "Permission already granted",
                fontSize = 20.sp,
                color = Color.Green
            )
        } else {
            Button(
                onClick = {
                    clickCount++
                    requestPermissionLauncher.launch(Manifest.permission.RECORD_AUDIO)
                }
            ) {
                Text(text = "Request Permission")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Tap the button to grant permission.",
                fontSize = 20.sp,
                color = Color.Blue
            )
        }
    }
}