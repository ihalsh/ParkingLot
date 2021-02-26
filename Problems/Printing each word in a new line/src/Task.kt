fun main() {
    generateSequence(::readLine)
            .joinToString(separator = " ")
            .split(' ')
            .forEach { println(it) }
    }