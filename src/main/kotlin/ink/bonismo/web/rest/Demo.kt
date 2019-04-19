package ink.bonismo.web.rest

/**
 * Created by bonismo@hotmail.com on 2019/4/18 6:40 PM
 *
 * @Description:
 * @Version: 1.0
 */

fun main() {


}

fun createFile(filename: String = "",
               appendDate: Boolean = false,
               executable: Boolean = false) {
    println(filename + " " + appendDate + " " + executable)
}


fun ByteArray.toHex(aaa: String): String {
    return aaa
}
