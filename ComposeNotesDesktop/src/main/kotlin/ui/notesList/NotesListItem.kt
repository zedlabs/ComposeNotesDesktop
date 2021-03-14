package ui.notesList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.zohaib.ComposeNotesDesktop.model.Note
import theme.purpleD3
import theme.robotoCus

@Composable
fun NotesListItem(note: Note) {
    //  val vm: MainViewModel = viewModel()


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(purpleD3)
            .clickable(true) {
                //  editNote(note)
            }
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row {
                Text(
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxWidth(.92f),
                    text = note.title,
                    style = robotoCus.body1,
                    fontSize = 20.sp,
                    color = Color.White
                )

/** weird bug when the window is resized **/
//                    Icon(
//                        imageVector = Icons.Sharp.Delete,
//                        contentDescription = "delete-button",
//                        tint = purpleD1,
//                        modifier = Modifier.height(30.dp)
//                            .width(30.dp)
//                    )

            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.body,
                style = robotoCus.subtitle1,
                fontSize = 16.sp,
                modifier = Modifier.padding(start = 8.dp, bottom = 8.dp)
            )
        }

    }

}