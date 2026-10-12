package com.practicum.playlist_maker_android_magomedovaalina

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.PlaylistmakerandroidMagomedovaAlinaTheme
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.YpBlack
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.YpBlue
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.YpTextGray
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.YpWhite

private data class MenuEntry(
    val title: String,
    val icon: ImageVector,
    val onClick: () -> Unit
)

@Composable
fun MainScreen(
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val menuEntries = listOf(
        MenuEntry(stringResource(R.string.main_search), Icons.Default.Search, onSearchClick),
        MenuEntry(stringResource(R.string.main_playlists), Icons.Default.LibraryMusic) {
            //потом
        },
        MenuEntry(stringResource(R.string.main_favorites), Icons.Default.FavoriteBorder) {
            //потом
        },
        MenuEntry(stringResource(R.string.main_settings), Icons.Default.Settings, onSettingsClick)
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = YpBlue
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            PanelHeader(
                title = stringResource(R.string.main_title),
                modifier = Modifier.statusBarsPadding(),
                titleColor = YpWhite
            )

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
private fun MenuItem(
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
private fun MainScreenPreview() {
    PlaylistmakerandroidMagomedovaAlinaTheme {
        MainScreen(onSearchClick = {}, onSettingsClick = {})
    }
}