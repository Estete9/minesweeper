import java.util.Scanner

enum class Rainbow(val color: String, val position: Int) {
    RED("red", 1),
    ORANGE("orange", 2),
    YELLOW("yellow", 3),
    GREEN("green", 4),
    BLUE("blue", 5),
    INDIGO("indigo", 6),
    VIOLET("violet", 7),
    NULL("null", 0)
}

fun main(args: Array<String>) {
    val input = Scanner(System.`in`)
    println(Rainbow.valueOf(input.next().toUpperCase()).position)
}
