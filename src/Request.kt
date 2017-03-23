
class Request private constructor(private var word: String) {
    private var regex: Boolean = false
    private var ignoreCase: Boolean = false
    private var inverted: Boolean = false

    class RequestBuilder(private val word: String) {
        private var regex: Boolean = false
        private var ignoreCase: Boolean = false
        private var inverted: Boolean = false

        fun setRegex(): RequestBuilder {
            regex = !regex
            return this
        }

        fun setIgnoreCase(): RequestBuilder {
            ignoreCase = !ignoreCase
            return this
        }

        fun setInverted(): RequestBuilder {
            inverted = !inverted
            return this
        }

        fun build(): Request {
            val result = Request(word)
            result.regex = regex
            result.ignoreCase = ignoreCase
            result.inverted = inverted
            return result
        }
    }

    fun isSatisfy(str: String): Boolean {
        return when {
            regex && ignoreCase && inverted -> !str.contains(word.toRegex(RegexOption.IGNORE_CASE))
            regex && ignoreCase             -> str.contains(word.toRegex(RegexOption.IGNORE_CASE))
            regex && inverted               -> !str.contains(word.toRegex())
            inverted && ignoreCase          -> !str.contains(word, true)
            regex                           -> str.contains(word.toRegex())
            ignoreCase                      -> str.contains(word, true)
            inverted                        -> !str.contains(word)
            else                            -> str.contains(word)
        }
    }

    override fun toString(): String {
        return "Request(word='$word', regex=$regex, ignoreCase=$ignoreCase, inverted=$inverted)"
    }
}