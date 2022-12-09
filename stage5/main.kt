package chucknorris
fun CharArray.toIntArray(): IntArray = this.map{ it.code }.toIntArray()
object ChukNorisEncrypter {
	class invalidEncryptedString : Exception("Encoded string is not valid.") {}
	fun readLineToCharArray(): CharArray = readln().map{ it.toChar() }.toCharArray()
	val countChar: (String, Char, Int) -> Int = {
			s, char, offset ->
			var counter: Int = 1
			while (offset + counter < s.length && s[offset + counter] == char) counter++
			counter
		}
		
	fun encrypt(cA: CharArray): String {
		var binaryString: String = ""
		for (c in cA) {
			binaryString += String.format("%7s",c.code.toString(2)).replace(' ', '0')
		}
		var ret: String = "";
		var idx: Int = 0		
		
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
	fun decrypt(s: String): List<Char> {
		s.forEach{ if (it != '0' && it != ' ') throw invalidEncryptedString() }
		val blocks = s.split(" ")
		if (blocks.size % 2 != 0) throw invalidEncryptedString()
		var ret: String = ""
		var idx: Int = 0
		// we can just blocks.size / 2
		//println("RAW: $s")
		// val lamdaForForBlocks = TODO()
		
		while(idx + 1 < blocks.size) { 
			val block = blocks[idx]
			val nextBlock = blocks[idx + 1]
			//println("IDX: $idx")
			//println("Block: $block; NextBlock: $nextBlock")
			idx += 2;
			val tmp = ret
			if (block == "0") {
				//ret += "1"
				val countZeros = countChar(nextBlock, '0', 0)
				//println("CountZeros for 1... $countZeros")
				for (i in 0 until countZeros) {
				 if (ret.length % 8 == 0) ret += " "
				 ret += "1"
				}
			} else if (block == "00") {
				//ret += "0"
				val countZeros = countChar(nextBlock, '0', 0)
				//println("CountZeros for 0... $countZeros")
				for (i in 0 until countZeros) {
					if (ret.length % 8 == 0) ret += " "
					ret += "0"
				}
			} else throw invalidEncryptedString()
			if (ret == tmp) throw invalidEncryptedString()
		}
		
		//println(ret)
		val binaryData = ret.trim().split(" ")
		binaryData.forEach {
			if (it.length != 7) throw invalidEncryptedString()
		}
		val dataArray = binaryData.map ( { it.toInt(2).toChar() } )
		//dataArray.forEach { println("$it = ${it.code}") }
		//if (dataArray.joinToString().trim() == "") throw invalidEncryptedString()
		return dataArray
	}
}

fun doOperation() {
    while(true) {
	    println("Please input operation (encode/decode/exit):")
	    val operation_raw = readln()
	    try {
		    when(operation_raw) {
		    	"encode" -> {
		    		println("Input string:")
		    		val encoded = ChukNorisEncrypter.encrypt( ChukNorisEncrypter.readLineToCharArray() )
		    		println("Encoded string:\n$encoded")
		    	}
		    	"decode" -> {
		    		println("Input encoded string:")
		    		val input = readln()
		    		val decoded = ChukNorisEncrypter.decrypt(input).joinToString("")
		    		println("Decoded string:")
		    		println(decoded)
		    	}
		    	"exit" -> { break }
		    	else -> { println("There is no '${operation_raw}' operation") }
		    }
	    } catch(exc: Exception) {
	    	println(exc.toString().split(": ")[1])
	    	//println()
	    }
	    println()

    }
}

fun main() {
    doOperation()
    println("Bye!")
    //println("Input string:")
   // val input = ChukNorisEncrypter.readLineToCharArray()
   // val input = readln()
   // println()
   // println("The result:")
  //  println(ChukNorisEncrypter.decrypt(input).joinToString(""))
}

