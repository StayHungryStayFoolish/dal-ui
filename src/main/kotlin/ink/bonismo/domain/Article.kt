package ink.bonismo.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import ink.bonismo.Info
import ink.bonismo.config.Constants
import org.apache.commons.lang3.StringUtils
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.*
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

/**
 * Created by bonismo@hotmail.com on 2019/4/15 3:26 PM
 *
 * @Description:
 * @Version: 1.0
 */
@EntityListeners(AuditingEntityListener::class)
//@MappedSuperclass
//@Audited
@Entity
@Table(name = "article")
data class Article(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @NotNull
        @Column(name = "dict_id", nullable = false)
        var dictId: Long? = null,

        @NotNull
        @Size(max = 800)
        @Column(name = "title", length = 800, nullable = false)
        var title: String? = null,

        @NotNull
        @Lob
        @Column(name = "text", nullable = false)
        var text: String? = null,

        @NotNull
        @Size(max = 5)
        @Column(name = "language", length = 5, nullable = false)
        var language: String? = null,

        @Size(max = 800)
        @Column(name = "icon", length = 800)
        var icon: String? = null,

        @Size(max = 800)
        @Column(name = "thumbnail", length = 800)
        var thumbnail: String? = null,

        @Size(max = 800)
        @Column(name = "tag", length = 800)
        var tag: String? = null,

        @NotNull
        @Column(name = "ordinal", nullable = false)
        var ordinal: Int? = null,

        @Lob
        @Column(name = "extension")
        var extension: String? = null,

        @Lob
        @Column(name = "description")
        var description: String? = null,

        @CreatedBy
        @Column(name = "created_by", nullable = false, length = 50, updatable = false)
        @JsonIgnore
        var createdBy: String? = null,

        @CreatedDate
        @Column(name = "created_date", updatable = false)
        @JsonIgnore
        var createdDate: Instant? = null,

        @LastModifiedBy
        @Lob
        @Column(name = "last_modified_by")
        @JsonIgnore
        var lastModifiedBy: String? = null,

        @LastModifiedDate
        @Column(name = "last_modified_date")
        @JsonIgnore
        var lastModifiedDate: Instant? = null
) {
    fun toProto(art: Article): Info.Article {
        val proto = Info.Article.newBuilder()
        proto.id = art.id ?: 0
        proto.dictId = art.dictId ?: 0
        proto.title = art.title ?: ""
        proto.text = art.text ?: ""
        proto.language = art.language ?: ""
        proto.icon = art.icon ?: ""
        proto.thumbnail = art.thumbnail ?: ""
        proto.tag = art.tag ?: ""
        proto.ordinal = art.ordinal ?: 0
        proto.extension = art.extension ?: ""
        proto.description = art.description ?: ""
        if (StringUtils.isNotBlank(art.createdBy)) proto.createdBy = art.createdBy else proto.createdBy = Constants.ANONYMOUS_USER
        proto.createdDate = Instant.now().toEpochMilli()
        if (StringUtils.isNotBlank(art.lastModifiedBy)) proto.lastModifiedBy = art.lastModifiedBy else proto.lastModifiedBy = Constants.ANONYMOUS_USER
        proto.lastModifiedDate = Instant.now().toEpochMilli()
        return proto.buildPartial()
    }

    fun toProtoList(artList: List<Article>): Info.ArticleList {
        val protoList = Info.ArticleList.newBuilder()
        for (art in artList) {
            val proto = Info.Article.newBuilder()
            if (null != art.id) proto.id = art.id!! else proto.id = 0
            if (null != art.dictId) proto.dictId = art.dictId!! else proto.dictId = 0
            if (StringUtils.isNotBlank(art.title)) proto.title = art.title else proto.title = ""
            if (StringUtils.isNotBlank(art.text)) proto.text = art.text else proto.text = ""
            if (StringUtils.isNotBlank(art.language)) proto.language = art.language else proto.language = ""
            if (StringUtils.isNotBlank(art.icon)) proto.icon = art.icon else proto.icon = ""
            if (StringUtils.isNotBlank(art.thumbnail)) proto.thumbnail = art.thumbnail else proto.thumbnail = ""
            if (StringUtils.isNotBlank(art.tag)) proto.tag = art.tag else proto.tag = ""
            if (null != art.ordinal) proto.ordinal = art.ordinal!! else proto.ordinal = 0
            if (StringUtils.isNotBlank(art.extension)) proto.extension = art.extension else proto.extension = ""
            if (StringUtils.isNotBlank(art.description)) proto.description = art.description else proto.description = ""
            if (StringUtils.isNotBlank(art.description)) proto.description = art.description else proto.description = ""
            if (StringUtils.isNotBlank(art.createdBy)) proto.createdBy = art.createdBy else art.createdBy = Constants.ANONYMOUS_USER
            proto.createdDate = Instant.now().toEpochMilli()
            if (StringUtils.isNotBlank(art.lastModifiedBy)) proto.lastModifiedBy = art.lastModifiedBy else proto.lastModifiedBy = Constants.ANONYMOUS_USER
            proto.lastModifiedDate = Instant.now().toEpochMilli()
            protoList.addArticle(proto)
        }
        return protoList.buildPartial()
    }

    fun toEntity(proto: Info.Article) {
        if (proto.id != 0L) id = proto.id
        if (proto.dictId != 0L) dictId = proto.dictId
        if (proto.title != "") title = proto.title
        if (proto.text != "") text = proto.text
        if (proto.language != "") language = proto.language
        if (proto.icon != "") icon = proto.icon
        if (proto.thumbnail != "") thumbnail = proto.thumbnail
        if (proto.tag != "") tag = proto.tag
        if (proto.ordinal != 0) ordinal = proto.ordinal
        if (proto.extension != "") extension = proto.extension
        if (proto.description != "") description = proto.description
        if (StringUtils.isNotBlank(proto.createdBy)) createdBy = proto.createdBy else createdBy = Constants.ANONYMOUS_USER
        if (proto.createdDate != 0L) createdDate = Instant.ofEpochMilli(proto.createdDate) else createdDate = Instant.now()
        if (StringUtils.isNotBlank(proto.lastModifiedBy)) lastModifiedBy = proto.lastModifiedBy else lastModifiedBy = Constants.ANONYMOUS_USER
        if (proto.lastModifiedDate != 0L) lastModifiedDate = Instant.ofEpochMilli(proto.lastModifiedDate) else lastModifiedDate = Instant.now()
    }

    fun toEntityList(artProtos: Info.ArticleList): List<Article> {
        val artList = ArrayList<Article>()
        val protoList = artProtos.articleList
        for (proto in protoList) {
            val art = Article(
                    proto.id,
                    proto.dictId,
                    proto.title,
                    proto.text,
                    proto.language,
                    proto.icon,
                    proto.thumbnail,
                    proto.tag,
                    proto.ordinal,
                    proto.extension,
                    proto.description,
                    if (StringUtils.isNotBlank(proto.createdBy)) proto.createdBy else Constants.ANONYMOUS_USER,
                    if (0L != proto.createdDate) Instant.ofEpochMilli(proto.createdDate) else Instant.now(),
                    if (StringUtils.isNotBlank(proto.lastModifiedBy)) proto.lastModifiedBy else Constants.ANONYMOUS_USER,
                    if (0L != proto.lastModifiedDate) Instant.ofEpochMilli(proto.lastModifiedDate) else Instant.now()
            )
            artList.add(art)
        }
        return artList
    }
}
