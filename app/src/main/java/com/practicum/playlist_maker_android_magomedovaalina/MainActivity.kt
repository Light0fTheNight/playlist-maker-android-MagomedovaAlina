package com.practicum.playlist_maker_android_magomedovaalina

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LibraryMusic
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.PlaylistmakerandroidMagomedovaAlinaTheme

private val YpBlue = Color(0xFF3772E7)
private val YpWhite = Color(0xFFFFFFFF)
private val YpBlack = Color(0xFF1A1B22)
private val YpTextGray = Color(0xFFAEAFB4)

private data class MenuEntry(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            PlaylistmakerandroidMagomedovaAlinaTheme {
                PlaylistMakerScreen()
            }
        }
    }
}

@Composable
fun PlaylistMakerScreen() {
    val context = LocalContext.current

    val menuEntries = listOf(
        MenuEntry("Поиск", Icons.Default.Search) {
            context.startActivity(Intent(context, SearchActivity::class.java))
        },
        MenuEntry("Плейлисты", Icons.Default.LibraryMusic) {

        },
        MenuEntry("Избранное", Icons.Default.FavoriteBorder) {

        },
        MenuEntry("Настройки", Icons.Default.Settings) {
            context.startActivity(Intent(context, SettingsActivity::class.java))
        }
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = YpBlue
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .height(56.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                Text(
                    text = "Playlist Maker",
                    modifier = Modifier.padding(start = 16.dp),
                    color = YpWhite,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium
                )
            }

            Spacer(modifier = Modifier.height(14.dp))

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                color = YpWhite,
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(16.dp))
                            .background(YpWhite)
                    ) {
                        menuEntries.forEach { entry ->
                            MenuItem(
                                text = entry.title,
                                icon = entry.icon,
                                onClick = entry.onClick
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun MenuItem(
    text: String,
    icon: ImageVector,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(66.dp)
            .clickable(onClick = onClick)
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = YpBlack
            )

            Text(
                text = text,
                modifier = Modifier.padding(start = 8.dp),
                fontSize = 22.sp,
                color = YpBlack,
                fontWeight = FontWeight.Medium
            )
        }

        Icon(
            imageVector = Icons.Default.ChevronRight,
            contentDescription = null,
            modifier = Modifier.size(24.dp),
            tint = YpTextGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlaylistMakerPreview() {
    PlaylistmakerandroidMagomedovaAlinaTheme {
        PlaylistMakerScreen()
    }
}