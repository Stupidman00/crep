import com.beust.jcommander.*
import java.io.File
import java.io.FileNotFoundException

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

    @Parameter(names = arrayOf("-files", "-f"), description = "Files",
            variableArity = true)
    private var fileNames = listOf<String>()

    fun run() {
        val request = Request.Builder(word.joinToString(" "))
                .setRegex(regex)
                .setIgnoreCase(ignoreCase)
                .setInverted(inverted)
                .build()
        val writer = System.out.bufferedWriter()
        for (filename in fileNames) {
            try {
                val reader = File(filename).bufferedReader()
                Filter(request).filter(reader, writer)
                reader.close()
            }
            catch (e: FileNotFoundException) {
                System.err.println("This file not found: $filename.")
            }
        }
        writer.close()
    }

    override fun toString(): String {
        return "Crep(inverted=$inverted, " +
                "ignoreCase=$ignoreCase, " +
                "regex=$regex, " +
                "word=$word, " +
                "fileNames=$fileNames)"
    }
}
