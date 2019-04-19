package ink.bonismo.service

import ink.bonismo.domain.Dictionary
import ink.bonismo.domain.enumeration.UIStatus

/**
 * Created by bonismo@hotmail.com on 2019/4/17 11:27 AM
 *
 * @Description:
 * @Version: 1.0
 */
interface DictionaryService {

    fun saveDictList(dictList: List<Dictionary>): List<Dictionary>

    fun findByParent(parent: String): List<Dictionary>

    fun findByParentAndStatus(parent: String, status: UIStatus): List<Dictionary>

    fun findByParentAndLanguageAndStatus(parent: String, language: String, status: UIStatus): List<Dictionary>

    fun findNameAndLanguageAndStatus(name: String, language: String, status: UIStatus): Dictionary

    fun batchDeleteByIds(ids: List<Long>)
}
