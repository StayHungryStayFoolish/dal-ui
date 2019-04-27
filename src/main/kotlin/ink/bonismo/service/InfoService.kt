package ink.bonismo.service

import com.google.protobuf.Empty
import ink.bonismo.Info
import ink.bonismo.Info.DictList
import ink.bonismo.InfoServiceGrpc
import ink.bonismo.domain.Article
import ink.bonismo.domain.Dictionary
import ink.bonismo.domain.enumeration.InfoStatus
import ink.bonismo.repository.ArticleRepository
import ink.bonismo.repository.DictionaryRepository
import ink.bonismo.util.InfoStatusUtil
import ink.bonismo.util.PageableUtil
import io.grpc.Status
import io.grpc.StatusRuntimeException
import io.grpc.stub.StreamObserver
import org.apache.commons.lang3.StringUtils
import org.lognet.springboot.grpc.GRpcService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.transaction.annotation.Transactional
import java.time.Instant
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

/**
 * Created by bonismo@hotmail.com on 2019/4/17 11:27 AM
 *
 * @Description:
 * @Version: 1.0
 */
@GRpcService
@Transactional
class InfoService(@Autowired private val dictionaryRepository: DictionaryRepository,
                  @Autowired private val articleRepository: ArticleRepository
) : InfoServiceGrpc.InfoServiceImplBase() {

    var logger = LoggerFactory.getLogger(this.javaClass)!!

    override fun saveDict(request: DictList?, responseObserver: StreamObserver<DictList>?) {
        try {
            val protoList = request!!.dictList
            val dictList = ArrayList<Dictionary>()
            for (proto in protoList) {
                if (0L == proto.id) {
                    dictUniqueChecked(proto)
                    var dict = Dictionary()
                    dict.toEntity(proto)
                    dict = dictionaryRepository.save(dict)
                    dictList.add(dict)
                } else {
                    dictUniqueChecked(proto)
                    val dict = dictionaryRepository.findByIdOrNull(proto.id)
                    if (null != dict) {
                        if (StringUtils.isNotBlank(proto.parent)) dict.parent = proto.parent
                        if (StringUtils.isNotBlank(proto.name)) dict.name = proto.name
                        if (StringUtils.isNotBlank(proto.language)) dict.language = proto.language
                        when (proto.statusValue) {
                            0 -> dict.status = InfoStatus.NULL
                            1 -> dict.status = InfoStatus.PUBLISHED
                            2 -> dict.status = InfoStatus.REVIEWED
                            3 -> dict.status = InfoStatus.DRAFTED
                            4 -> dict.status = InfoStatus.CANCELED
                        }
                        if (StringUtils.isNotBlank(proto.icon)) dict.icon = proto.icon
                        if (StringUtils.isNotBlank(proto.url)) dict.url = proto.url
                        if (StringUtils.isNotBlank(proto.links)) dict.links = proto.links
                        if (0 != proto.ordinal) dict.ordinal = proto.ordinal
                        if (StringUtils.isNotBlank(proto.extension)) dict.extension = proto.extension
                        if (StringUtils.isNotBlank(proto.description)) dict.description = proto.description
                        if (StringUtils.isNotBlank(proto.createdBy)) dict.createdBy = proto.createdBy
                        if (0L != proto.createdDate) dict.createdDate = Instant.ofEpochMilli(proto.createdDate)
                        if (StringUtils.isNotBlank(proto.lastModifiedBy)) dict.lastModifiedBy = proto.lastModifiedBy
                        dict.lastModifiedDate = Instant.now()
                        dictList.add(dict)
                    }
                }
            }
            val entity = Dictionary()
            val result = entity.toProtoList(dictList)
            responseObserver?.onNext(result)
            responseObserver?.onCompleted()
        } catch (e: Throwable) {
            logger.error(e.toString())
            responseObserver?.onError(StatusRuntimeException(Status.UNAVAILABLE.withDescription("Dictionary Does Not Unique")))
            responseObserver?.onCompleted()
        }/**/
    }

    override fun saveArticle(request: Info.ArticleList?, responseObserver: StreamObserver<Info.ArticleList>?) {
        try {
            val protoList = request!!.articleList
            val artList = ArrayList<Article>()
            for (proto in protoList) {
                if (0L == proto.id) {
                    artExistChecked(proto)
                    var art = Article()
                    art.toEntity(proto)
                    art = articleRepository.save(art)
                    artList.add(art)
                } else {
                    artExistChecked(proto)
                    val art = articleRepository.findById(proto.id).orElse(null)
                    if (null != art) {
                        if (StringUtils.isNotBlank(proto.title)) art.title = proto.title
                        if (StringUtils.isNotBlank(proto.text)) art.text = proto.text
                        if (StringUtils.isNotBlank(proto.language)) art.title = proto.language
                        if (StringUtils.isNotBlank(proto.icon)) art.title = proto.icon
                        if (StringUtils.isNotBlank(proto.thumbnail)) art.title = proto.thumbnail
                        if (StringUtils.isNotBlank(proto.tag)) art.title = proto.tag
                        if (0 != proto.ordinal) art.ordinal = proto.ordinal
                        if (StringUtils.isNotBlank(proto.extension)) art.extension = proto.extension
                        if (StringUtils.isNotBlank(proto.description)) art.description = proto.description
                        if (StringUtils.isNotBlank(proto.createdBy)) art.createdBy = proto.createdBy
                        if (0L != proto.createdDate) art.createdDate = Instant.ofEpochMilli(proto.createdDate)
                        if (StringUtils.isNotBlank(proto.lastModifiedBy)) art.lastModifiedBy = proto.lastModifiedBy
                        art.lastModifiedDate = Instant.now()
                        artList.add(art)
                    }
                }
            }
            val entity = Article()
            val result = entity.toProtoList(artList)
            responseObserver?.onNext(result)
            responseObserver?.onCompleted()
        } catch (e: Throwable) {
            logger.error(e.toString())
            responseObserver?.onError(StatusRuntimeException(Status.INTERNAL.withDescription("Article Does Not Exist")))
            responseObserver?.onCompleted()
        }
    }

    override fun updateDictStatusByDictName(request: Info.PublishReq?, responseObserver: StreamObserver<DictList>?) {
        try {
            val protoStatus = request!!.status
            val dictName = request.dictName
            val lastModifiedBy = request.lastModifiedBy
            val entityStatus = InfoStatusUtil.toEntityStatus(protoStatus)
            val dictList = dictionaryRepository.findByName(dictName)
            if (dictList.isNotEmpty()) {
                dictList.forEach {
                    it.status = entityStatus
                    it.lastModifiedBy = lastModifiedBy
                    it.lastModifiedDate = Instant.now()
                }
            }
            val entity = Dictionary()
            val result = entity.toProtoList(dictList)
            responseObserver?.onNext(result)
            responseObserver?.onCompleted()
        } catch (e: Throwable) {
            logger.error(e.toString())
            responseObserver?.onError(StatusRuntimeException(Status.INTERNAL.withDescription("Update Exception")))
            responseObserver?.onCompleted()
        }
    }

    override fun batchDeleteDictById(request: Info.BatchDictId?, responseObserver: StreamObserver<Empty>?) {
        try {
            val ids = request!!.idList
            ids.forEach {
                dictionaryRepository.deleteById(it)
            }
            val result = Empty.newBuilder().build();
            responseObserver?.onNext(result)
            responseObserver?.onCompleted()
        } catch (e: Throwable) {
            logger.error(e.toString())
            responseObserver?.onError(StatusRuntimeException(Status.INTERNAL.withDescription("Delete Exception")))
            responseObserver?.onCompleted()
        }
    }

    override fun batchDeleteArticleById(request: Info.BatchArtId?, responseObserver: StreamObserver<Empty>?) {
        try {
            val ids = request!!.idList
            for (id in ids) {
                articleRepository.deleteById(id)
            }
            val result = Empty.newBuilder().build();
            responseObserver?.onNext(result)
            responseObserver?.onCompleted()
        } catch (e: Throwable) {
            logger.error(e.toString())
            responseObserver?.onError(StatusRuntimeException(Status.INTERNAL.withDescription("Delete Exception")))
            responseObserver?.onCompleted()
        }
    }

    override fun queryDictPage(request: Info.DictPageReq?, responseObserver: StreamObserver<Info.DictPageResp>?) {
        try {
            val parent = request!!.parent
            val protoStatus = request.status
            val status = InfoStatusUtil.toEntityStatus(protoStatus)
            val language = request.language
            val pageable = PageableUtil.toPageable(request.page)
            val dictPage = dictionaryRepository.findByParentAndLanguageAndStatus(parent, language, status, pageable)
            val entityList = dictPage.content

            val pageBuilder = Info.PageResp.newBuilder()
            pageBuilder.first = dictPage.isFirst
            pageBuilder.last = dictPage.isLast
            pageBuilder.totalElements = dictPage.totalElements
            pageBuilder.totalPages = dictPage.totalPages
            val pageResp = pageBuilder.buildPartial()

            val entity = Dictionary()
            val protoList = entity.toProtoList(entityList)

            val pageRespBuilder = Info.DictPageResp.newBuilder()
            pageRespBuilder.dictList = protoList
            pageRespBuilder.page = pageResp
            val result = pageRespBuilder.buildPartial()

            responseObserver?.onNext(result)
            responseObserver?.onCompleted()
        } catch (e: Throwable) {
            logger.error(e.toString())
            responseObserver?.onError(StatusRuntimeException(Status.INTERNAL.withDescription("DictPage Exception")))
            responseObserver?.onCompleted()
        }
    }

    override fun queryArticle(request: Info.ArticleReq?, responseObserver: StreamObserver<Info.Article>?) {
        try {
            val dictName = request!!.dictName
            val protoStatus = request.status
            val status = InfoStatusUtil.toEntityStatus(protoStatus)
            val language = request.language
            val entity = Article()
            var result = Info.Article.newBuilder().buildPartial()
            val dictionary = dictionaryRepository.findByNameAndLanguageAndStatus(dictName, language, status).orElse(null)
            if (null != dictionary) {
                val article = articleRepository.findByDictId(dictionary.id!!).orElse(null)
                if (null != article) {
                    result = entity.toProto(article)
                }
            }
            responseObserver?.onNext(result)
            responseObserver?.onCompleted()
        } catch (e: Throwable) {
            logger.error(e.toString())
            responseObserver?.onError(StatusRuntimeException(Status.INTERNAL.withDescription("DictPage Exception")))
            responseObserver?.onCompleted()
        }
    }

    override fun queryMultiDictByParent(request: Info.MultiDictReq?, responseObserver: StreamObserver<Info.MultiDictResp>?) {
        try {
            val resultBuilder = Info.MultiDictResp.newBuilder()
            val parent = request!!.parent
            val protoStatus = request.status
            val status = InfoStatusUtil.toEntityStatus(protoStatus)
            val language = request.language
            val mutableList: MutableList<Dictionary> = mutableListOf()
            var dictList: List<Dictionary> = mutableList
            if (InfoStatus.NULL == status && StringUtils.isBlank(language)) {
                dictList = dictionaryRepository.findByParent(parent)
            } else if (InfoStatus.NULL != status && StringUtils.isBlank(language)) {
                dictList = dictionaryRepository.findByParentAndStatus(parent, status)
            } else if (InfoStatus.NULL == status && StringUtils.isNotBlank(language)) {
                dictList = dictionaryRepository.findByParentAndLanguage(parent, language)
            } else if (InfoStatus.NULL != status && StringUtils.isNotBlank(language)) {
                dictList = dictionaryRepository.findByParentAndLanguageAndStatus(parent, language, status)
            }
            val mutableSet: MutableSet<String> = HashSet()
            dictList.forEach {
                mutableSet.add(it.name!!)
            }
            val entity = Dictionary()
            mutableSet.forEach {
                val name = it
                val sameNameList: MutableList<Dictionary> = mutableListOf()
                dictList.forEach {
                    if (name == it.name) {
                        sameNameList.add(it)
                    }
                }
                val protoList = entity.toProtoList(sameNameList)
                resultBuilder.addDictList(protoList)
            }
            val result = resultBuilder.buildPartial()
            responseObserver?.onNext(result)
            responseObserver?.onCompleted()
        } catch (e: Throwable) {
            logger.error(e.toString())
            responseObserver?.onError(StatusRuntimeException(Status.INTERNAL.withDescription("MultiDict Exception")))
            responseObserver?.onCompleted()
        }
    }

    override fun queryMultiArticleByDictName(request: Info.MultiArticleReq?, responseObserver: StreamObserver<Info.MultiArticleResp>?) {
        try {
            val dictName = request!!.dictName
            val protoStatus = request.status
            val status = InfoStatusUtil.toEntityStatus(protoStatus)
            val language = request.language

            val mutableList: MutableList<Dictionary> = mutableListOf()
            var dictList: List<Dictionary> = mutableList
            var dictionary = Optional.of(Dictionary())
            if (InfoStatus.NULL == status && StringUtils.isBlank(language)) {
                dictList = dictionaryRepository.findByName(dictName)
            } else if (InfoStatus.NULL != status && StringUtils.isBlank(language)) {
                dictList = dictionaryRepository.findByNameAndStatus(dictName, status)
            } else if (InfoStatus.NULL == status && StringUtils.isNotBlank(language)) {
                dictionary = dictionaryRepository.findByNameAndLanguage(dictName, language)
            } else if (InfoStatus.NULL != status && StringUtils.isNotBlank(language)) {
                dictionary = dictionaryRepository.findByNameAndLanguageAndStatus(dictName, language, status)
            }
            mutableList.addAll(dictList)
            if (dictionary.isPresent) {
                mutableList.add(dictionary.get())
            }
            val artMutableList: MutableList<Article> = mutableListOf()
            dictList.forEach {
                val art = articleRepository.findByDictId(it.id!!).orElse(null)
                if (null != art) {
                    artMutableList.add(art)
                }
            }
            val entity = Article()
            val artProtoList = entity.toProtoList(artMutableList)

            val resultBuilder = Info.MultiArticleResp.newBuilder()
            resultBuilder.articleList = artProtoList
            val result = resultBuilder.buildPartial()
            responseObserver?.onNext(result)
            responseObserver?.onCompleted()
        } catch (e: Throwable) {
            logger.error(e.toString())
            responseObserver?.onError(StatusRuntimeException(Status.INTERNAL.withDescription("MultiArticle Exception")))
            responseObserver?.onCompleted()
        }
    }

    fun dictUniqueChecked(proto: Info.Dictionary) {
        val uniqueChecked = dictionaryRepository.findByNameAndLanguage(proto.name, proto.language).orElse(null)
        if (null != uniqueChecked) {
            logger.debug("UniqueChecked Dictionary: {}", uniqueChecked)
            throw RuntimeException()
        }
    }

    fun artExistChecked(proto: Info.Article) {
        if (0L == proto.id && 0L != proto.dictId) {
            val existsChecked = articleRepository.findByDictId(proto.dictId).orElse(null)
            if (null != existsChecked) {
                logger.debug("CheckExist Article: {}", existsChecked)
                throw RuntimeException()
            }
        }
    }
}
