package console

import com.beust.jcommander.*
import core.Filter
import core.Request
import java.io.File
import java.io.FileNotFoundException

fun main(args: Array<String>) {
    try {
        val crep = Crep()
        JCommander(crep, *args)
        crep.run()
    }
    catch (e: ParameterException) {
        System.err.println(e.message)
    }
}

class Crep {
    @Parameter(names = arrayOf("-v"), description = "Inverted mode")
    private var inverted = false

    @Parameter(names = arrayOf("-i"), description = "Ignore case mode")
    private var ignoreCase = false

    @Parameter(names = arrayOf("-r"), description = "Regex mode")
    private var regex = false

    @Parameter(description = "Word for searching", required = true)
    private var word = mutableListOf<String>()

    @Parameter(names = arrayOf("-files", "-f"), description = "Files",
            variableArity = true, required = true)
    private var fileNames = listOf<String>()

    fun run() {
        if (word.size != 1) throw ParameterException(
                "Redundant arguments: ${word.filterIndexed { index, s -> index != 0 }}")
        val request = Request.Builder(word[0])
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
        return "console.Crep(inverted=$inverted, " +
                "ignoreCase=$ignoreCase, " +
                "regex=$regex, " +
                "word=$word, " +
                "fileNames=$fileNames)"
    }
}
