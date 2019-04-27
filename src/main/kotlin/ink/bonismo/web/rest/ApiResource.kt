package ink.bonismo.web.rest

import ink.bonismo.domain.Dictionary
import ink.bonismo.repository.DictionaryRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * Created by bonismo@hotmail.com on 2019/4/19 1:39 PM
 *
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api")
class ApiResource {

    @Autowired
    lateinit var dictionaryService: DictionaryRepository

//    @GetMapping("/dict/save")
//    fun saveDict():List<Dictionary>{
//        var dictList = ArrayList<Dictionary>()
//        for (i in 0..10) {
//            var dict = Dictionary(i + 1L,
//                    "root",
//                    "about-us",
//                    "zh-CN",
//                    UIStatus.PUBLISHED,
//                    null,
//                    null,
//                    null,
//                    10,
//                    null,
//                    null,
//                    "bonismo",
//                    Instant.now(),
//                    "bonismo",
//                    Instant.now())
//            dictList.add(dict)
//        }
//        return dictionaryService.saveDictList(dictList)
//    }


//    @GetMapping("/dict/del")
//    fun test() {
//        var ids =  ArrayList<Long>()
//        ids.add(1)
//        ids.add(2)
//        ids.add(3)
//        ids.add(4)
//        dictionaryService.batchDeleteByIds(ids)
//    }

    @GetMapping("/dict/parent")
    fun test(): List<Dictionary> {
        return dictionaryService.findByParent("root")
    }
}
