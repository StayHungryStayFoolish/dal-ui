package ink.bonismo.repository

import ink.bonismo.domain.Dictionary
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by bonismo@hotmail.com on 2019/4/16 11:02 AM
 *
 * @Description:
 * @Version: 1.0
 */
interface DictionaryRepository : JpaRepository<Dictionary, Long> {

}
