package chucknorris
fun readLineToCharArray(): CharArray = readln().map{ it.toChar() }.toCharArray()
fun CharArray.toIntArray(): IntArray = this.map{ it.code }.toIntArray()
fun main() {
    println("Input string:")
    val input = readLineToCharArray()
    val inputAsNumber = input.toIntArray()
    println()
    println("The result:")
    for (idx in 0 until input.size)
    {
        if ( input[idx] == '\n') continue
        // inputAsNumber[idx].toString(2)
        val binaryString = String.format("%7s",Integer.toBinaryString(inputAsNumber[idx])).replace(' ', '0')
        println("${ input[idx] } = ${ binaryString }")
    }
}

