package protect.card_locker.compose.theme

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import protect.card_locker.R
import protect.card_locker.preferences.Settings

@Composable
fun CatimaTheme(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val settings = Settings(context)

    val lightTheme = lightColorScheme(
        primary = colorResource(id = R.color.md_theme_light_primary),
        onPrimary = colorResource(id = R.color.md_theme_light_onPrimary),
        primaryContainer = colorResource(id = R.color.md_theme_light_primaryContainer),
        onPrimaryContainer = colorResource(id = R.color.md_theme_light_onPrimaryContainer),
        secondary = colorResource(id = R.color.md_theme_light_secondary),
        onSecondary = colorResource(id = R.color.md_theme_light_onSecondary),
        secondaryContainer = colorResource(id = R.color.md_theme_light_secondaryContainer),
        onSecondaryContainer = colorResource(id = R.color.md_theme_light_onSecondaryContainer),
        tertiary = colorResource(id = R.color.md_theme_light_tertiary),
        onTertiary = colorResource(id = R.color.md_theme_light_onTertiary),
        tertiaryContainer = colorResource(id = R.color.md_theme_light_tertiaryContainer),
        onTertiaryContainer = colorResource(id = R.color.md_theme_light_onTertiaryContainer),
        error = colorResource(id = R.color.md_theme_light_error),
        onError = colorResource(id = R.color.md_theme_light_onError),
        errorContainer = colorResource(id = R.color.md_theme_light_errorContainer),
        onErrorContainer = colorResource(id = R.color.md_theme_light_onErrorContainer),
        background = colorResource(id = R.color.md_theme_light_background),
        onBackground = colorResource(id = R.color.md_theme_light_onBackground),
        surface = colorResource(id = R.color.md_theme_light_surface),
        onSurface = colorResource(id = R.color.md_theme_light_onSurface),
        surfaceVariant = colorResource(id = R.color.md_theme_light_surfaceVariant),
        onSurfaceVariant = colorResource(id = R.color.md_theme_light_onSurfaceVariant),
        outline = colorResource(id = R.color.md_theme_light_outline)
    )

    var darkTheme = darkColorScheme(
        primary = colorResource(id = R.color.md_theme_dark_primary),
        onPrimary = colorResource(id = R.color.md_theme_dark_onPrimary),
        primaryContainer = colorResource(id = R.color.md_theme_dark_primaryContainer),
        onPrimaryContainer = colorResource(id = R.color.md_theme_dark_onPrimaryContainer),
        secondary = colorResource(id = R.color.md_theme_dark_secondary),
        onSecondary = colorResource(id = R.color.md_theme_dark_onSecondary),
        secondaryContainer = colorResource(id = R.color.md_theme_dark_secondaryContainer),
        onSecondaryContainer = colorResource(id = R.color.md_theme_dark_onSecondaryContainer),
        tertiary = colorResource(id = R.color.md_theme_dark_tertiary),
        onTertiary = colorResource(id = R.color.md_theme_dark_onTertiary),
        tertiaryContainer = colorResource(id = R.color.md_theme_dark_tertiaryContainer),
        onTertiaryContainer = colorResource(id = R.color.md_theme_dark_onTertiaryContainer),
        error = colorResource(id = R.color.md_theme_dark_error),
        onError = colorResource(id = R.color.md_theme_dark_onError),
        errorContainer = colorResource(id = R.color.md_theme_dark_errorContainer),
        onErrorContainer = colorResource(id = R.color.md_theme_dark_onErrorContainer),
        background = colorResource(id = R.color.md_theme_dark_background),
        onBackground = colorResource(id = R.color.md_theme_dark_onBackground),
        surface = colorResource(id = R.color.md_theme_dark_surface),
        onSurface = colorResource(id = R.color.md_theme_dark_onSurface),
        surfaceVariant = colorResource(id = R.color.md_theme_dark_surfaceVariant),
        onSurfaceVariant = colorResource(id = R.color.md_theme_dark_onSurfaceVariant),
        outline = colorResource(id = R.color.md_theme_dark_outline)
    )

    if (settings.oledDark) {
        darkTheme = darkTheme.copy(background = Color.Black)
    }

    val colorScheme = when (settings.theme) {
        AppCompatDelegate.MODE_NIGHT_NO -> lightTheme
        AppCompatDelegate.MODE_NIGHT_YES -> darkTheme
        else -> if (isSystemInDarkTheme()) darkTheme else lightTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
