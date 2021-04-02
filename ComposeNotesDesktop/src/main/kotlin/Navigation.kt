
sealed class Configuration {
    object Screen1 : Configuration()
    data class Screen2(val id: Int) : Configuration()
}
