
data class Request private constructor(private val word: String,
                                       private val regex: Boolean = false,
                                       private val ignoreCase: Boolean = false,
                                       private val inverted: Boolean = false) {
    class Builder(private val word: String) {
        private var regex = false
        private var ignoreCase = false
        private var inverted = false

        fun setRegex(value: Boolean): Builder {
            regex = value
            return this
        }

        fun setIgnoreCase(value: Boolean): Builder {
            ignoreCase = value
            return this
        }

        fun setInverted(value: Boolean): Builder {
            inverted = value
            return this
        }

        fun build(): Request {
            return Request(word, regex, ignoreCase, inverted)
        }
    }

    fun isSatisfy(str: String): Boolean {
        return inverted xor when {
            regex && ignoreCase -> str.contains(word.toRegex(RegexOption.IGNORE_CASE))
            ignoreCase          -> str.contains(word, true)
            regex               -> str.contains(word.toRegex())
            else                -> str.contains(word)
        }
    }
}