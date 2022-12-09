package chucknorris
fun CharArray.toIntArray(): IntArray = this.map{ it.code }.toIntArray()
object ChukNorisEncrypter {
	fun readLineToCharArray(): CharArray = readln().map{ it.toChar() }.toCharArray()
	fun encrypt(cA: CharArray): String {
		var binaryString: String = ""
		for (c in cA) {
			binaryString += String.format("%7s",c.code.toString(2)).replace(' ', '0')
		}
		var ret: String = "";
		var idx: Int = 0		
		val countChar: (String, Char, Int) -> Int = {
			s, char, offset ->
			var counter: Int = 1
			while (offset + counter < s.length && s[offset + counter] == char) counter++
			counter
		}
		while (idx < binaryString.length) {
			if (binaryString[idx] == '0')
			{
				ret += "00 "
				val countZeros = countChar(binaryString, '0', idx)
				for (i in 0 until countZeros) ret += "0"
				ret += " "
				idx += countZeros
				continue
				
			}
			else if (binaryString[idx] == '1')
			{
				ret += "0 "
				val countOnes = countChar(binaryString, '1', idx)
				for (i in 0 until countOnes) ret += "0"
				ret += " "
				idx += countOnes
				continue
			}
			idx ++
		}
		return ret
	}
}
fun main() {
    println("Input string:")
    val input = ChukNorisEncrypter.readLineToCharArray()
    println()
    println("The result:")
    println(ChukNorisEncrypter.encrypt(input))
}

