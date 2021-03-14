package ui.notesList

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.zohaib.ComposeNotesDesktop.model.Note
import theme.purpleD1
import theme.purpleD3
import theme.robotoCus

@Composable
fun NotesListItem(note: Note) {
    //  val vm: MainViewModel = viewModel()


    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
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
                        .fillMaxWidth(.95f),
                    text = note.title,
                    style = robotoCus.body1,
                    fontSize = 20.sp,
                    color = Color.White
                )

                Box(modifier = Modifier
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)
                    .padding(top = 10.dp)
                    .size(25.dp)
                    .clickable {
                        // vm.deleteNote(note)
                    }) {
                    Icon(
                        imageVector = Icons.Sharp.Delete,
                        contentDescription = "delete-button",
                        tint = purpleD1,
                        modifier = Modifier.size(40.dp)
                    )
                }

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