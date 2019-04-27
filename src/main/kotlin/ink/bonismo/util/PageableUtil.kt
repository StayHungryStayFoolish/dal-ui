package ink.bonismo.util

import ink.bonismo.Info
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort

/**
 * Created by bonismo@hotmail.com on 2019/4/27 12:57 AM
 *
 * @Description:
 * @Version: 1.0
 */
object PageableUtil {

    fun toPageable(pageReq: Info.PageReq): Pageable {
        val number = pageReq.number
        val size = pageReq.size
        val sort = pageReq.sort
        if (sort.contains(",")) {
            val sortArray = sort.split(",")
            val sortBy = sortArray[0]
            val direction = sortArray[1]
            var sortReq = Any()
            if ("asc".equals(direction, false)) {
                sortReq = Sort.by(sortBy).ascending()
                val pageRequest = PageRequest.of(number, size, sortReq)
                return pageRequest
            } else {
                sortReq = Sort.by(sortBy).descending()
                val pageRequest = PageRequest.of(number, size, sortReq)
                return pageRequest

            }
        }
        return PageRequest.of(number, size, Sort.by(sort).ascending())
    }
}
