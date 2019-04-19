package ink.bonismo.repository

import ink.bonismo.domain.Dictionary
import ink.bonismo.domain.enumeration.UIStatus
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by bonismo@hotmail.com on 2019/4/16 11:02 AM
 *
 * @Description:
 * @Version: 1.0
 */
interface DictionaryRepository : JpaRepository<Dictionary, Long> {

    fun findByParent(parent: String): List<Dictionary>

//    fun findByParentAndUIStatus(parent: String, status: UIStatus): List<Dictionary>

//    fun findByParentAndLanguageAndUIStatus(parent: String, language: String, status: UIStatus): List<Dictionary>

//    fun findNameAndLanguageAndUIStatus(name: String, language: String, status: UIStatus): Dictionary

}
