package ink.bonismo.web.rest;

import ink.bonismo.domain.Dictionary;
import ink.bonismo.domain.enumeration.UIStatus;
import ink.bonismo.repository.DictionaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

/**
 * Created by bonismo@hotmail.com on 2019/4/11 10:49 PM
 *
 * @Description:
 * @Version: 1.0
 */
@RestController
@RequestMapping("/api")
public class HelloWorld {

    @Autowired
    DictionaryRepository dictionaryRepository;

    @GetMapping("/dict")
    public Dictionary getDict() {
        Dictionary dictionary = new Dictionary(
                1L,
                "root",
                "about",
                "zh-CN",
                UIStatus.PUBLISHED,
                "",
                "",
                "",
                1,
                "",
                "",
                "bonismo",
                Instant.now(),
                "bonismo",
                Instant.now());
        Dictionary dict = dictionaryRepository.save(dictionary);
        return dict;
    }
}
