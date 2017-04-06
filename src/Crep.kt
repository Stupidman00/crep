import com.beust.jcommander.*
import java.io.File

fun main(args: Array<String>) {
    val crep = Crep()
    JCommander(crep, *args)
    crep.run()
}

class Crep {
    @Parameter(names = arrayOf("-v"), description = "Inverted mode")
    private var inverted = false

    @Parameter(names = arrayOf("-i"), description = "Ignore case mode")
    private var ignoreCase = false

    @Parameter(names = arrayOf("-r"), description = "Regex mode")
    private var regex = false

    @Parameter(description = "Word for searching")
    private var word = mutableListOf<String>()

    @Parameter(names = arrayOf("-files"), description = "Files", variableArity = true)
    private var fileNames = listOf<String>()

    fun run() {
        val request = Request.Builder(word.joinToString(" "))
                .setRegex(regex)
                .setIgnoreCase(ignoreCase)
                .setInverted(inverted)
                .build()
        for (filename in fileNames) {
            try {
                Filter(File(filename).bufferedReader(),System.out.bufferedWriter(), request).filter()
            }
            catch (e: Exception) {
                println("This file not found: $filename")
            }
        }
    }

    override fun toString(): String {
        return "Crep(inverted=$inverted, ignoreCase=$ignoreCase, regex=$regex, word=$word, fileNames=$fileNames)"
    }
}
