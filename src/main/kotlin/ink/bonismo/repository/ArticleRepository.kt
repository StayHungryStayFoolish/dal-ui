package ink.bonismo.repository

import ink.bonismo.domain.Article
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by bonismo@hotmail.com on 2019/4/16 11:17 AM
 *
 * @Description:
 * @Version: 1.0
 */
interface ArticleRepository : JpaRepository<Article, Long> {

}
