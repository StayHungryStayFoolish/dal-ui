package ink.bonismo.repository

import ink.bonismo.domain.Dictionary
import ink.bonismo.domain.enumeration.InfoStatus
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

/**
 * Created by bonismo@hotmail.com on 2019/4/16 11:02 AM
 *
 * @Description:
 * @Version: 1.0
 */
interface DictionaryRepository : JpaRepository<Dictionary, Long> {

    fun findByParent(parent: String): List<Dictionary>

    fun findByParentAndStatus(parent: String, status: InfoStatus): List<Dictionary>

    fun findByParentAndLanguage(parent: String, language: String): List<Dictionary>

    fun findByParentAndLanguageAndStatus(parent: String, language: String, status: InfoStatus): List<Dictionary>

    fun findByParentAndLanguageAndStatus(parent: String, language: String, status: InfoStatus, pageable: Pageable): Page<Dictionary>

    fun findByName(name: String): List<Dictionary>

    fun findByNameAndStatus(name: String, status: InfoStatus): List<Dictionary>

    fun findByNameAndLanguage(name: String, language: String): Optional<Dictionary>

    fun findByNameAndLanguageAndStatus(name: String, language: String, status: InfoStatus): Optional<Dictionary>

}
