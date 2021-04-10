package ui.NoteDetails

import DatabaseHelper
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import theme.purpleD1
import theme.purpleD3

@Composable
fun NoteDetails(noteId: Int, onBack: () -> Unit) {

    val noteQueries = DatabaseHelper.queries
    val note = noteQueries.selectNote(note_number = noteId.toLong()).executeAsOneOrNull()

    var title = remember { mutableStateOf(note?.title) }
    var body = remember { mutableStateOf(note?.body) }

    Column(
        modifier = Modifier.background(purpleD1)
            .fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            tint = Color.White,
            contentDescription = "back-button",
            modifier = Modifier
                .padding(start = 20.dp, top = 10.dp)
                .clickable {
                    noteQueries.updateNote(title.value.toString(), body.value.toString(), noteId.toLong())
                    onBack.invoke()
                })

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
            maxLines = 1,
            value = title.value.toString()
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
            onValueChange = { body.value = it },
            value = body.value.toString()
        )

    }
}