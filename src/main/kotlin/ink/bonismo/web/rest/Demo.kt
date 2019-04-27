package ink.bonismo.web.rest

import org.apache.commons.lang3.StringUtils
import java.util.*
import kotlin.random.Random

/**
 * Created by bonismo@hotmail.com on 2019/4/18 6:40 PM
 *
 * @Description:
 * @Version: 1.0
 */

fun main() {
    val language = " "
    println(StringUtils.isNoneBlank(language))
    println(StringUtils.isNotBlank(language))

}

fun createFile(filename: String = "",
               appendDate: Boolean = false,
               executable: Boolean = false) {
    println(filename + " " + appendDate + " " + executable)
}


fun ByteArray.toHex(aaa: String): String {
    return aaa
}
