package com.practicum.playlist_maker_android_magomedovaalina

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.PlaylistmakerandroidMagomedovaAlinaTheme
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.YpBlack
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.YpBlue
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.YpLightGray
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.YpTextGray
import com.practicum.playlist_maker_android_magomedovaalina.ui.theme.YpWhite

@Composable
fun SearchScreen(onBackClick: () -> Unit) {
    var query by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = YpWhite
    ) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            PanelHeader(
                title = stringResource(R.string.search_title),
                onBackClick = onBackClick
            )

            SearchField(
                query = query,
                onQueryChange = { query = it },
                onClearClick = { query = "" },
                onSearchClick = {
                    //потом
                    focusManager.clearFocus()
                }
            )
        }
    }
}

@Composable
private fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onClearClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    BasicTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .height(36.dp),
        singleLine = true,
        textStyle = TextStyle(color = YpBlack, fontSize = 16.sp),
        cursorBrush = SolidColor(YpBlue),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearchClick() }),
        decorationBox = { innerTextField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(8.dp))
                    .background(YpLightGray)
                    .padding(start = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    modifier = Modifier
                        .size(16.dp)
                        .clickable { /* потом */ },
                    tint = YpTextGray
                )

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (query.isEmpty()) {
                        Text(
                            text = stringResource(R.string.search_placeholder),
                            color = YpTextGray,
                            fontSize = 16.sp
                        )
                    }
                    innerTextField()
                }

                if (query.isNotEmpty()) {
                    Box(
                        modifier = Modifier
                            .size(width = 40.dp, height = 36.dp)
                            .clickable(onClick = onClearClick),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = stringResource(R.string.search_clear),
                            modifier = Modifier.size(24.dp),
                            tint = YpTextGray
                        )
                    }
                }
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun SearchScreenPreview() {
    PlaylistmakerandroidMagomedovaAlinaTheme {
        SearchScreen(onBackClick = {})
    }
}