import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File

class Filter(private val inputFile: File, private val request: Request) {
    fun filter() {
        val outputFile: File = File("temp.txt")
        val reader: BufferedReader = inputFile.bufferedReader()
        val writer: BufferedWriter = outputFile.bufferedWriter()
        var str: String? = reader.readLine()
        while (str != null) {
            if (request.isSatisfy(str)) writer.appendln(str)
            str = reader.readLine()
        }
        writer.close()
        reader.close()
    }
}