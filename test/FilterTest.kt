import org.junit.Test

import org.junit.Assert.*
import java.io.File

class FilterTest {
    @Test
    fun filter() {
        val results = arrayOf(
                """

0123456789 _+-.,!@#$%^&*();\/|<>"'
12345 -98.7 3.141 .6180 9,000 +42
555.123.4567	+1-(800)-555-2468
\\+[0-9]-\\([0-9]+\\)
""",
                """Welcome to RegExr v2.1 by gskinner.com, proudly hosted by Media Temple!
Edit the Expression & Text to see matches. Roll over matches or the expression for
details. Undo mistakes with ctrl-z. Save Favorites & Share expressions with friends or
the Community. Explore your results with Tools. A full Reference & Help is available
Sample text for testing:
abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ
foo@demo.net	bar.ba@test.co.uk
""",
                """

0123456789 _+-.,!@#$%^&*();\/|<>"'
12345 -98.7 3.141 .6180 9,000 +42
555.123.4567	+1-(800)-555-2468
\\+[0-9]-\\([0-9]+\\)
""",
                """

0123456789 _+-.,!@#$%^&*();\/|<>"'
12345 -98.7 3.141 .6180 9,000 +42
555.123.4567	+1-(800)-555-2468
www.demo.com	http://foo.co.uk/
https://mediatemple.net
\\+[0-9]-\\([0-9]+\\)
""",
                """555.123.4567	+1-(800)-555-2468
""",
                """Edit the Expression & Text to see matches. Roll over matches or the expression for
Sample text for testing:
""",
                """Welcome to RegExr v2.1 by gskinner.com, proudly hosted by Media Temple!

Edit the Expression & Text to see matches. Roll over matches or the expression for
details. Undo mistakes with ctrl-z. Save Favorites & Share expressions with friends or
the Community. Explore your results with Tools. A full Reference & Help is available
in the Library, or watch the video Tutorial.

Sample text for testing:
abcdefghijklmnopqrstuvwxyz ABCDEFGHIJKLMNOPQRSTUVWXYZ
0123456789 _+-.,!@#$%^&*();\/|<>"'
12345 -98.7 3.141 .6180 9,000 +42
555.123.4567	+1-(800)-555-2468
foo@demo.net	bar.ba@test.co.uk
www.demo.com	http://foo.co.uk/
http://regexr.com/foo.html?q=bar
https://mediatemple.net
""",
                """Welcome to RegExr v2.1 by gskinner.com, proudly hosted by Media Temple!
"""
        )
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
                Request.Builder("""\\+[0-9]-\\([0-9]+\\)""")
                        .setInverted(true)
                        .build(),
                Request.Builder("Welcome to RegExr")
                        .build()
        )
        val testFile = File("out/production/crep/input")
        val resultFile = File("testTemp.txt")
        for(i in 0..7) {
            Filter(testFile.bufferedReader(), resultFile.writer(), requests[i]).filter()
            assertEquals(results[i], resultFile.readText())
            resultFile.delete()
        }
    }

}