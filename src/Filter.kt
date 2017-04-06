import java.io.*

class Filter(private val reader: Reader,
             private val writer: Writer,
             private val request: Request) {
    fun filter() {
        var str = reader.buffered().readLine()
        while(str != null) {
            if (request.isSatisfy(str)) writer.write(str)
            str = reader.buffered().readLine()
        }
        reader.close()
    }
}