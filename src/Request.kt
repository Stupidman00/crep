
data class Request private constructor(private val word: String,
                                       private val regex: Boolean = false,
                                       private val ignoreCase: Boolean = false,
                                       private val inverted: Boolean = false) {
    class Builder(private val word: String) {
        private var regex = false
        private var ignoreCase = false
        private var inverted = false

        fun setRegex(value: Boolean) = this.apply { regex = value }

        fun setIgnoreCase(value: Boolean) = this.apply { ignoreCase = value }

        fun setInverted(value: Boolean) = this.apply { inverted = value }

        fun build() = Request(word, regex, ignoreCase, inverted)
    }

    fun isSatisfy(str: String): Boolean {
        val temp = when {
            regex && ignoreCase -> str.contains(word.toRegex(RegexOption.IGNORE_CASE))
            ignoreCase          -> str.contains(word, true)
            regex               -> str.contains(word.toRegex())
            else                -> str.contains(word)
        }
        return if (inverted) !temp else temp
    }
}