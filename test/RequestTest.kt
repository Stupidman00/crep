import core.Request
import org.junit.Test

import org.junit.Assert.*

class RequestTest {
    @Test
    fun isSatisfy() {
        val requests = arrayOf(
                Request.Builder("[A-z]{4}")
                        .setRegex(true)
                        .setIgnoreCase(true)
                        .setInverted(true)
                        .build(),
                Request.Builder("S\\w+")
                        .setRegex(true)
                        .setIgnoreCase(true)
                        .build(),
                Request.Builder("e\\w+")
                        .setRegex(true)
                        .setInverted(true)
                        .build(),
                Request.Builder("r")
                        .setIgnoreCase(true)
                        .setInverted(true)
                        .build(),
                Request.Builder("\\+[0-9]-\\([0-9]+\\)")
                        .setRegex(true)
                        .build(),
                Request.Builder("teXt")
                        .setIgnoreCase(true)
                        .build(),
                Request.Builder("\\+[0-9]-\\([0-9]+\\)")
                        .setInverted(true)
                        .build(),
                Request.Builder("Welcome to RegExr")
                        .build()
        )
        val testStrs = arrayOf(
                """0123456789 _+-.,!@#$%^&*();|<>"'""",
                "sample text for testing:",
                "555.123.4567	+1-(800)-555-2468",
                "https://mediatemple.net",
                "555.123.4567	+1-(800)-555-2468",
                "Sample text for testing:",
                "555.123.4567	+1-(800)-555-2468",
                "Welcome to RegExr v2.1 by gskinner.com"
        )
        for ((index, str) in testStrs.withIndex()) {
            assertTrue(requests[index].isSatisfy(str))
        }
    }

}