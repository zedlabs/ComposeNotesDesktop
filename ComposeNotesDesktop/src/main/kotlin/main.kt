import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.desktop.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import me.zohaib.ComposeNotesDesktop.model.NoteQueries
import theme.purpleD0
import theme.purpleD1
import ui.NoteDetails.NoteDetails
import ui.NoteViewModel
import ui.notesList.NotesList

@ExperimentalFoundationApi
fun main() = Window {

    val playerQueries: NoteQueries = DatabaseHelper.queries
    playerQueries.insert(note_number = 1, title = "Corey Perry", body = "Hello !")
    playerQueries.insert(
        note_number = 2,
        title = "Jetpack Compose",
        body = "This is a declarative UI ramework developed by google and jetbrains"
    )
    playerQueries.insert(note_number = 4, title = "Perry the platypus", body = "Hello there perry !")
    playerQueries.insert(
        note_number = 5,
        title = "Tyler Blevins NINJA",
        body = "Professional fortnite player and streamer on twitch.tv/ninja "
    )
    playerQueries.insert(note_number = 6, title = "Corey Perry", body = "Hello !")
    playerQueries.insert(
        note_number = 7,
        title = "Jetpack Compose",
        body = "This is a declarative UI ramework developed by google and jetbrains"
    )
    playerQueries.insert(note_number = 8, title = "Perry the platypus", body = "Hello there perry !")

    //val viewModel = NoteViewModel()
    var screenState by remember { mutableStateOf<Configuration>(Configuration.Screen1) }

    MaterialTheme {
        Crossfade(
            modifier = Modifier.background(purpleD0),
            animationSpec = tween(600),
            targetState = screenState) { screen ->
            when (screen) {
                is Configuration.Screen1 -> NotesList(onItemClick = {
                    screenState = (Configuration.Screen2(id = it.note_number.toInt()))
                })
                is Configuration.Screen2 -> NoteDetails(
                    noteId = screen.id,
                    onBack = { screenState = (Configuration.Screen1) })
            }
        }

    }
}
