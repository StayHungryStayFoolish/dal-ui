import ink.bonismo.web.rest.createFile
import ink.bonismo.web.rest.toHex

fun main() {

//    var myList: MutableList<Int>? = null
    val myList = ArrayList<Int>()
    myList.add(0)
    myList.add(1)
    myList.add(10)
//    myList!!.remove(10)

    myList.add(20)

    val (evens, odds) = myList.partition { it == 0 }
    println("EVENS " + evens)
    println("ODDS " + odds)


    val dummyData = byteArrayOf('a'.toByte())

    println(dummyData.size)


    createFile()
    val array =  ByteArray(10)
    println(array.toHex("bbb"))

}

//fun createFile(filename: String = "",
//                       appendDate: Boolean = false,
//                       executable: Boolean = false) {
//    println(filename + " " + appendDate + " " + executable)
//}
//
//
//fun ByteArray.sssss(): String {
//    return this.joinToString("") {
//        java.lang.String.format("%02x", it)
//    }
//}
