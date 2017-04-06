import java.io.*

class Filter(private val reader: Reader,
             private val writer: Writer,
             private val request: Request) {
    fun filter() {
        reader.readLines()
                .filter { request.isSatisfy(it) }
                .forEach { writer.write("$it\n") }
        reader.close()
        writer.close()
    }
}