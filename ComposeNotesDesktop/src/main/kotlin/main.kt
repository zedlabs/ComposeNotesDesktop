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
