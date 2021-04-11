package ui.NoteDetails

import DatabaseHelper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.purpleD1
import theme.purpleD3
import theme.robotoCus

@Composable
fun NoteDetails(noteId: Int, onBack: () -> Unit) {

    val noteQueries = DatabaseHelper.queries
    val note = noteQueries.selectNote(note_number = noteId.toLong()).executeAsOneOrNull()

    val title = remember { mutableStateOf(note?.title ?: "") }
    val body = remember { mutableStateOf(note?.body ?: "") }

    Column(
        modifier = Modifier.background(purpleD1)
            .fillMaxSize()
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                tint = Color.White,
                contentDescription = "back-button",
                modifier = Modifier
                    .padding(start = 20.dp, top = 15.dp)
                    .clickable {
                        if (noteId == -1) noteQueries.insert(0, title.value, body.value)
                        else noteQueries.updateNote(title.value, body.value, noteId.toLong())
                        onBack.invoke()
                    })
            Text(
                modifier = Modifier.padding(top = 10.dp),
                text = "Edit Note",
                style = robotoCus.caption,
                fontSize = 25.sp,
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Icon(
                imageVector = Icons.Filled.Delete,
                tint = Color.White,
                contentDescription = "delete-button",
                modifier = Modifier
                    .padding(end = 20.dp, top = 15.dp)
                    .clickable {
                        noteQueries.deleteNote(noteId.toLong())
                        onBack.invoke()
                    })
        }

        TextField(
            modifier = Modifier.fillMaxWidth()
                .height(100.dp)
                .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(purpleD3),
            onValueChange = { title.value = it },
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 20.sp,
            ),
            label = {  if(title.value.isEmpty()) Text("Title", color = Color.Gray)},
            maxLines = 1,
            value = title.value
        )
        TextField(
            modifier = Modifier.fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(purpleD3),
            textStyle = TextStyle(
                color = Color.Gray,
                fontSize = 16.sp,
            ),
            label = {
                if(body.value.isEmpty()) Text("Body", color = Color.Gray)
            },
            onValueChange = { body.value = it },
            value = body.value
        )

    }
}