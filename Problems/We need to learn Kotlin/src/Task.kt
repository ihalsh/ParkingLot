fun main() {
    listOf("WE NEED", "TO LEARN KOTLIN", "AS QUICKLY AS POSSIBLE").forEachIndexed { index, s ->
        println(s)
        if (index < 2) println()
    }
}