package com.practicum.playlist_maker_android_magomedovaalina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.PlaylistmakerandroidMagomedovaAlinaTheme

class SettingsActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlaylistmakerandroidMagomedovaAlinaTheme {
                Text(
                    text = "Настройки"
                )
            }
        }
    }
}