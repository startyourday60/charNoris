package chucknorris
fun readLineToCharArray(): CharArray = readln().map{ it.toChar() }.toCharArray()
fun main() {
    println("Input string:")
    println(readLineToCharArray().joinToString(" "))
}

