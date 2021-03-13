import androidx.compose.desktop.Window
import androidx.compose.material.Text
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import me.zohaib.ComposeNotesDesktop.model.Database
import me.zohaib.ComposeNotesDesktop.model.NoteQueries

fun main() = Window {

    val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
    Database.Schema.create(driver)

    val database = Database(driver)

    val playerQueries: NoteQueries = database.noteQueries

    println(playerQueries.selectAll().executeAsList())

    playerQueries.insert(note_number = 10, title = "Corey Perry", body = "Hello !")
    println(playerQueries.selectAll().executeAsList())

    var text by remember { mutableStateOf("Hello, World!") }


    MaterialTheme {
        Button(onClick = {
            text = "Hello, Desktop!"
        }) {
            Text(text)
        }
    }
}
