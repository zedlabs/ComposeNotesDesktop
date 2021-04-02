import com.squareup.sqldelight.db.SqlDriver
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import me.zohaib.ComposeNotesDesktop.model.Database
import me.zohaib.ComposeNotesDesktop.model.NoteQueries

object DatabaseHelper {

    var  queries : NoteQueries

    init {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Database.Schema.create(driver)
        val database = Database(driver)

        queries = database.noteQueries
    }

}