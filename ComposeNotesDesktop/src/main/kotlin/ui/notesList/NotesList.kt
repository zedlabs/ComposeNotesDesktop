package ui.notesList

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import me.zohaib.ComposeNotesDesktop.model.Database
import me.zohaib.ComposeNotesDesktop.model.NoteQueries
import theme.purpleD0
import theme.purpleD1
import theme.robotoCus

@Composable
fun NotesList() {


    val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    Database.Schema.create(driver)

    val database = Database(driver)

    val playerQueries: NoteQueries = database.noteQueries

    val noteList = playerQueries.selectAll().executeAsList()

        Scaffold(
            topBar = {
                //NotesListTopBar()
            },
            floatingActionButton = {
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
                    onClick = {/* editNote(Note(title = "", body = "")) */ },
                    icon = { Icon(imageVector = Icons.Sharp.Add) },
                    backgroundColor = purpleD0
                )
            }
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize().background(purpleD1)
            ) {
                itemsIndexed(
                    items = noteList
                ) { _, note ->
                    NotesListItem(note)
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