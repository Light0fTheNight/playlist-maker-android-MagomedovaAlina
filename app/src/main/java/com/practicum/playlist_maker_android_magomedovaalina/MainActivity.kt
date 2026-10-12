package com.practicum.playlist_maker_android_magomedovaalina

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.PlaylistmakerandroidMagomedovaAlinaTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlaylistmakerandroidMagomedovaAlinaTheme {
                val navController = rememberNavController()
                PlaylistHost(navController = navController)
            }
        }
    }
}