package ink.bonismo.service.impl

import ink.bonismo.domain.Dictionary
import ink.bonismo.domain.enumeration.UIStatus
import ink.bonismo.repository.DictionaryRepository
import ink.bonismo.service.DictionaryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Created by bonismo@hotmail.com on 2019/4/19 11:47 AM
 *
 * @Description:
 * @Version: 1.0
 */
@Component
class DictionaryServiceImpl(@Autowired private val dictRepository: DictionaryRepository) : DictionaryService {

    override fun saveDictList(dictList: List<Dictionary>): List<Dictionary> {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return dictRepository.saveAll(dictList)
    }

    override fun batchDeleteByIds(ids: List<Long>) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        var dictList = ArrayList<Dictionary>()
        for (id in ids) {
            var dict = dictRepository.findById(id).orElse(null)
            dictList.add(dict!!)
        }
        dictRepository.deleteAll(dictList)
    }


    override fun findByParentAndStatus(parent: String, status: UIStatus): List<Dictionary> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findByParentAndLanguageAndStatus(parent: String, language: String, status: UIStatus): List<Dictionary> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findNameAndLanguageAndStatus(name: String, language: String, status: UIStatus): Dictionary {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun findByParent(parent: String): List<Dictionary> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
