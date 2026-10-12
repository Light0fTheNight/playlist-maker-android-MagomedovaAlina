package com.practicum.playlist_maker_android_magomedovaalina

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
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
fun SettingsScreen() {
    val context = LocalContext.current

    var darkTheme by rememberSaveable { mutableStateOf(false) }

    val shareMessage = stringResource(R.string.share_message)
    val supportEmail = stringResource(R.string.support_email)
    val supportSubject = stringResource(R.string.support_subject)
    val supportBody = stringResource(R.string.support_body)
    val agreementUrl = stringResource(R.string.agreement_url)
    val noAppFound = stringResource(R.string.no_app_found)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = YpWhite
    ) {
        Column(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
            PanelHeader(title = stringResource(R.string.settings_title))

            Spacer(modifier = Modifier.height(24.dp))

            SettingsItem(
                text = stringResource(R.string.settings_dark_theme),
                trailing = {
                    Switch(
                        checked = darkTheme,
                        onCheckedChange = { darkTheme = it },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = YpWhite,
                            checkedTrackColor = YpBlue,
                            checkedBorderColor = Color.Transparent,
                            uncheckedThumbColor = YpTextGray,
                            uncheckedTrackColor = YpLightGray,
                            uncheckedBorderColor = Color.Transparent
                        )
                    )
                }
            )

            SettingsItem(
                text = stringResource(R.string.settings_share),
                trailing = { SettingsIcon(Icons.Default.Share) },
                onClick = {
                    val sendIntent = Intent(Intent.ACTION_SEND).apply {
                        type = "text/plain"
                        putExtra(Intent.EXTRA_TEXT, shareMessage)
                    }
                    context.startActivitySafely(
                        Intent.createChooser(sendIntent, null),
                        noAppFound
                    )
                }
            )

            SettingsItem(
                text = stringResource(R.string.settings_support),
                trailing = { SettingsIcon(Icons.Default.SupportAgent) },
                onClick = {
                    val emailIntent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:")
                        putExtra(Intent.EXTRA_EMAIL, arrayOf(supportEmail))
                        putExtra(Intent.EXTRA_SUBJECT, supportSubject)
                        putExtra(Intent.EXTRA_TEXT, supportBody)
                    }
                    context.startActivitySafely(emailIntent, noAppFound)
                }
            )

            SettingsItem(
                text = stringResource(R.string.settings_agreement),
                trailing = { SettingsIcon(Icons.Default.ChevronRight) },
                onClick = {
                    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(agreementUrl))
                    context.startActivitySafely(browserIntent, noAppFound)
                }
            )
        }
    }
}

@Composable
private fun SettingsItem(
    text: String,
    trailing: @Composable () -> Unit,
    onClick: (() -> Unit)? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(61.dp)
            .then(if (onClick != null) Modifier.clickable(onClick = onClick) else Modifier)
            .padding(start = 16.dp, end = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            modifier = Modifier.weight(1f),
            fontSize = 16.sp,
            color = YpBlack
        )
        trailing()
    }
}

@Composable
private fun SettingsIcon(imageVector: ImageVector) {
    Icon(
        imageVector = imageVector,
        contentDescription = null,
        modifier = Modifier.size(24.dp),
        tint = YpTextGray
    )
}

private fun Context.startActivitySafely(intent: Intent, errorMessage: String) {
    try {
        startActivity(intent)
    } catch (e: ActivityNotFoundException) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenPreview() {
    PlaylistmakerandroidMagomedovaAlinaTheme {
        SettingsScreen()
    }
}