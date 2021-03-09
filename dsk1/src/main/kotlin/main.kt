import androidx.compose.desktop.Window
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import pages.BasePage
import pages.browse.BrowsePage
import pages.browse.browsePage
import pages.detail.DetailPage
import pages.detail.detailPage


object Paginator {
    var currentPage by mutableStateOf<BasePage>(BrowsePage())
}

object AppSettings {
    var darkMode by mutableStateOf<Boolean>(false)
}

fun main() {

    val DarkColors = darkColors(primary = Color(245, 127, 127), secondary = Color.White)
    val LightColors = lightColors(primary = Color(222, 78, 78), secondary = Color.White)

    Window {
        MaterialTheme(colors = if (!AppSettings.darkMode) LightColors else DarkColors) {

            Scaffold(bodyContent = {
                when (Paginator.currentPage) {
                    is BrowsePage ->
                        browsePage()
                    is DetailPage -> detailPage((Paginator.currentPage as DetailPage).anime)
                }
            })
        }
    }
}

