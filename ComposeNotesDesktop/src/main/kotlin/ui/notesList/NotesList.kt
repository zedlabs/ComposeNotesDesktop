package ui.notesList

import DatabaseHelper
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.zohaib.ComposeNotesDesktop.model.Note
import me.zohaib.ComposeNotesDesktop.model.NoteQueries
import theme.purpleD0
import theme.purpleD1
import theme.purpleD3
import theme.robotoCus


@ExperimentalFoundationApi
@Composable
fun NotesList(onItemClick: (Note) -> Unit) {

    val playerQueries: NoteQueries = DatabaseHelper.queries
    val noteList = playerQueries.selectAll().executeAsList()
    val searchBarState = remember { false }
    val searchQuery = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(purpleD1)
            ) {
                TextField(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(5.dp))
                        .background(purpleD3),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
                    ),
                    colors = TextFieldDefaults.outlinedTextFieldColors(
                        cursorColor = Color.Gray,
                        focusedBorderColor = Color.Gray
                    ),
                    singleLine = true,
                    value = searchQuery.value,
                    onValueChange = { searchQuery.value = it },
                    placeholder = { Text("Search Here...", fontSize = 15.sp, color = Color.Gray) },
                )
            }

        },
        floatingActionButton = {
            Row {
                ExtendedFloatingActionButton(
                    text = {
                        Text(
                            text = "New Note",
                            style = robotoCus.subtitle2,
                            fontSize = 16.sp,
                            modifier = Modifier
                                .wrapContentHeight(Alignment.CenterVertically)
                                .padding(bottom = 2.dp)
                        )
                    },
                    onClick = { onItemClick.invoke(Note(0, "", "")) },
                    icon = { Icon(imageVector = Icons.Sharp.Add, contentDescription = "add-button") },
                    backgroundColor = purpleD0,
                )
            }

        }
    ) {
        LazyVerticalGrid(
            cells = GridCells.Adaptive(300.dp),
            modifier = Modifier.fillMaxSize().background(purpleD1)
        ) {
            itemsIndexed(
                items = noteList.filter { it.title.contains(searchQuery.value, ignoreCase = true) }
            ) { _ , note ->
                NotesListItem(note, onItemClick)
            }
        }
    }

}