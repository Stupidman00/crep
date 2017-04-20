package core

import java.io.*

class Filter(private val request: Request) {
    fun filter(reader: Reader, writer: Writer) {
        reader.readLines()
                .filter { it != "" && request.isSatisfy(it) }
                .forEach { writer.write("$it\n") }
                writer.flush()
    }
}