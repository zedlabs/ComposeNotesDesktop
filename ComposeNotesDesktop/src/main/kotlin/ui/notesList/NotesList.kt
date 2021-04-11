package ui.notesList

import DatabaseHelper
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.material.icons.sharp.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import me.zohaib.ComposeNotesDesktop.model.Note
import me.zohaib.ComposeNotesDesktop.model.NoteQueries
import theme.*


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
                    .height(70.dp)
                    .background(purpleD1)
            ) {
                TextField(
                    modifier = Modifier
                        .padding(start = 20.dp, end = 20.dp, top = 10.dp, bottom = 5.dp)
                        .fillMaxWidth()
                        .height(60.dp)
                        .clip(RoundedCornerShape(5.dp))
                        .background(purpleD3),
                    textStyle = TextStyle(
                        color = Color.White,
                        fontSize = 15.sp,
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
                    onClick = { onItemClick.invoke(Note(-1, "", "")) },
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
                items = noteList
            ) { _, note ->
                NotesListItem(note, onItemClick)
            }
        }
    }

}

//@Composable
//fun NotesListTopBar(vm: MainViewModel, searchParam: String) {
//
//    val isSearchBarVisible by vm.searchViewVisible.observeAsState(false)
//
//    Crossfade(targetState = isSearchBarVisible) { isVisible ->
//        when (isVisible) {
//            true -> {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(70.dp)
//                ) {
//                    TextField(
//                        placeholder = { Text(text = "Enter Search Query") },
//                        singleLine = true,
//                        textStyle = TextStyle(fontSize = 14.sp),
//                        modifier = Modifier
//                            .fillMaxWidth(.9f)
//                            .padding(start = 8.dp),
//                        value = searchParam, onValueChange = {
//                            vm.updateSearchParam(it)
//                        },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            backgroundColor = purpleD5
//                        )
//                    )
//                    Icon(
//                        imageVector = Icons.Sharp.Clear,
//                        tint = purpleD1,
//                        modifier = Modifier
//                            .weight(1f)
//                            .wrapContentWidth(Alignment.End)
//                            .padding(end = 6.dp, top = 16.dp)
//                            .size(30.dp)
//                            .clickable {
//                                vm.searchViewVisibility(false)
//                                vm.updateSearchParam("")
//                            }
//                    )
//                }
//
//            }
//            false -> {
//                Row(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(70.dp)
//                ) {
//                    Spacer(modifier = Modifier.width(4.dp))
//                    Text(
//                        modifier = Modifier.padding(8.dp),
//                        text = "📝 Notes",
//                        style = robotoCus.subtitle2,
//                        fontSize = 24.sp,
//                    )
//                    Icon(
//                        imageVector = Icons.Sharp.Search,
//                        tint = purpleD1,
//                        modifier = Modifier
//                            .weight(1f)
//                            .wrapContentWidth(Alignment.End)
//                            .padding(top = 16.dp, end = 8.dp)
//                            .size(30.dp)
//                            .clickable {
//                                vm.searchViewVisibility(true)
//                            }
//                    )
//                }
//            }
//        }
//
//    }
//}