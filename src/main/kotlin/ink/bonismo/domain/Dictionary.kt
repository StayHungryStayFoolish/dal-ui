package ink.bonismo.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import ink.bonismo.Info
import ink.bonismo.config.Constants
import ink.bonismo.domain.enumeration.InfoStatus
import ink.bonismo.util.InfoStatusUtil
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
 * Created by bonismo@hotmail.com on 2019/4/12 12:16 PM
 *
 * @Description:
 * @Version: 1.0
 */
@EntityListeners(AuditingEntityListener::class)
//@MappedSuperclass
//@Audited
@Entity
@Table(name = "dictionary")
data class Dictionary(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @NotNull
        @Column(name = "parent", nullable = false)
        var parent: String? = null,

        @NotNull
        @Column(name = "name", nullable = false)
        var name: String? = null,

        @NotNull
        @Size(max = 5)
        @Column(name = "language", length = 5, nullable = false)
        var language: String? = null,

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(name = "status", nullable = false)
        var status: InfoStatus? = null,

        @Size(max = 800)
        @Column(name = "icon", length = 800)
        var icon: String? = null,

        @Size(max = 800)
        @Column(name = "url", length = 800)
        var url: String? = null,

        @Size(max = 800)
        @Column(name = "links", length = 800)
        var links: String? = null,

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
        var createdDate: Instant ?= null,

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
    fun toProto(dict: Dictionary): Info.Dictionary {
        val proto = Info.Dictionary.newBuilder()
        proto.id = dict.id ?: 0
        proto.parent = dict.parent ?: ""
        proto.name = dict.name ?: ""
        proto.language = dict.language ?: ""
        if (null != dict.status) proto.status = InfoStatusUtil.toProtoStatus(dict.status!!)
        proto.icon = dict.icon ?: ""
        proto.url = dict.url ?: ""
        proto.links = dict.links ?: ""
        proto.ordinal = dict.ordinal ?: 0
        proto.extension = dict.extension ?: ""
        proto.description = dict.description ?: ""
        if (StringUtils.isNotBlank(dict.createdBy)) proto.createdBy = dict.createdBy else proto.createdBy = Constants.ANONYMOUS_USER
        proto.createdDate = Instant.now().toEpochMilli()
        if (StringUtils.isNotBlank(dict.lastModifiedBy)) proto.lastModifiedBy = dict.lastModifiedBy else proto.lastModifiedBy = Constants.ANONYMOUS_USER
        proto.lastModifiedDate = Instant.now().toEpochMilli()
        return proto.buildPartial()
    }

    fun toProtoList(dictList: List<Dictionary>): Info.DictList {
        val protoList = Info.DictList.newBuilder()
        for (dictionary in dictList) {
            val proto = Info.Dictionary.newBuilder()
            if (null != dictionary.id) proto.id = dictionary.id!! else proto.id = 0
            if (StringUtils.isNotBlank(dictionary.parent)) proto.parent = dictionary.parent else proto.parent = ""
            if (StringUtils.isNotBlank(dictionary.name)) proto.name = dictionary.name else proto.name = ""
            if (StringUtils.isNotBlank(dictionary.language)) proto.language = dictionary.language else proto.language = ""
            if (null != dictionary.status) proto.status = InfoStatusUtil.toProtoStatus(dictionary.status!!)
            if (StringUtils.isNotBlank(dictionary.icon)) proto.icon = dictionary.icon else proto.icon = ""
            if (StringUtils.isNotBlank(dictionary.url)) proto.url = dictionary.url else proto.url = ""
            if (StringUtils.isNotBlank(dictionary.links)) proto.links = dictionary.links else proto.links = ""
            if (null != dictionary.ordinal) proto.ordinal = dictionary.ordinal!! else proto.ordinal = 0
            if (StringUtils.isNotBlank(dictionary.extension)) proto.extension = dictionary.extension else proto.extension = ""
            if (StringUtils.isNotBlank(dictionary.description)) proto.description = dictionary.description else proto.description = ""
            if (StringUtils.isNotBlank(dictionary.description)) proto.description = dictionary.description else proto.description = ""
            if (StringUtils.isNotBlank(dictionary.createdBy)) proto.createdBy = dictionary.createdBy else proto.createdBy = Constants.ANONYMOUS_USER
            proto.createdDate = Instant.now().toEpochMilli()
            if (StringUtils.isNotBlank(dictionary.lastModifiedBy)) proto.lastModifiedBy = dictionary.lastModifiedBy else proto.lastModifiedBy = Constants.ANONYMOUS_USER
            proto.lastModifiedDate = Instant.now().toEpochMilli()
            protoList.addDict(proto)
        }
        return protoList.buildPartial()
    }

    fun toEntity(proto: Info.Dictionary) {
        if (proto.id != 0L) id = proto.id
        if (proto.parent != "") parent = proto.parent
        if (proto.name != "") name = proto.name
        if (proto.language != "") language = proto.language
        status = InfoStatusUtil.toEntityStatus(proto.status)
        if (proto.icon != "") icon = proto.icon
        if (proto.url != "") url = proto.url
        if (proto.links != "") links = proto.links
        if (proto.ordinal != 0) ordinal = proto.ordinal
        if (proto.extension != "") extension = proto.extension
        if (proto.description != "") description = proto.description
        if (StringUtils.isNotBlank(proto.createdBy)) createdBy = proto.createdBy else createdBy = Constants.ANONYMOUS_USER
        if (proto.createdDate != 0L) createdDate = Instant.ofEpochMilli(proto.createdDate) else createdDate = Instant.now()
        if (StringUtils.isNotBlank(proto.lastModifiedBy)) lastModifiedBy = proto.lastModifiedBy else lastModifiedBy = Constants.ANONYMOUS_USER
        if (proto.lastModifiedDate != 0L) lastModifiedDate = Instant.ofEpochMilli(proto.lastModifiedDate) else lastModifiedDate = Instant.now()
    }

    fun toEntityList(dictProtos: Info.DictList): List<Dictionary> {
        val dictList = ArrayList<Dictionary>()
        val protoList = dictProtos.dictList
        for (proto in protoList) {
            val dict = Dictionary(
                    proto.id,
                    proto.parent,
                    proto.name,
                    proto.language,
                    InfoStatusUtil.toEntityStatus(proto.status),
                    proto.icon,
                    proto.url,
                    proto.links,
                    proto.ordinal,
                    proto.extension,
                    proto.description,
                    if (StringUtils.isNotBlank(proto.createdBy)) proto.createdBy else Constants.ANONYMOUS_USER,
                    if (0L != proto.createdDate) Instant.ofEpochMilli(proto.createdDate) else Instant.now(),
                    if (StringUtils.isNotBlank(proto.lastModifiedBy)) proto.lastModifiedBy else Constants.ANONYMOUS_USER,
                    if (0L != proto.lastModifiedDate) Instant.ofEpochMilli(proto.lastModifiedDate) else Instant.now()
            )
            dictList.add(dict)
        }
        return dictList
    }
}
