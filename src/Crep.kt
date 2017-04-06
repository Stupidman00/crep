import com.beust.jcommander.*

fun main(args: Array<String>) {

}

class Crep {
    @Parameter(names = arrayOf("-v"), description = "Inverted mode")
    var inverted = false

    @Parameter(names = arrayOf("-i"), description = "Ignore case mode")
    var ignoreCase = false

    @Parameter(names = arrayOf("-r"), description = "Regex mode")
    var regex = false

    @Parameter(description = "Word for searching")
    var word = mutableListOf<String>()

    @Parameter(names = arrayOf("-files"), description = "Files", variableArity = true)
    var files = listOf<String>()

    fun run() {
        println(inverted)
        println(ignoreCase)
        println(regex)
        println(word)
        println(files)
    }
}
