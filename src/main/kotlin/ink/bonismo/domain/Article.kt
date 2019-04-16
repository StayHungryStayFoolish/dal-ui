package ink.bonismo.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.envers.Audited
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
        var id: Long,

        @NotNull
        @Column(name = "dict_id", nullable = false)
        var dictId: Long,

        @NotNull
        @Size(max = 32)
        @Column(name = "unique_id", length = 32, nullable = false)
        var uniqueId: String,

        @NotNull
        @Size(max = 800)
        @Column(name = "title", length = 800, nullable = false)
        var title: String,

        @NotNull
        @Lob
        @Column(name = "text", nullable = false)
        var text: String,

        @NotNull
        @Size(max = 5)
        @Column(name = "language", length = 5, nullable = false)
        var language: String,

        @Size(max = 800)
        @Column(name = "icon", length = 800)
        var icon: String,

        @Size(max = 800)
        @Column(name = "thumbnail", length = 800)
        var thumbnail: String,

        @Size(max = 800)
        @Column(name = "tag", length = 800)
        var tag: String,

        @NotNull
        @Column(name = "ordinal", nullable = false)
        var ordinal: Int,

        @Lob
        @Column(name = "extension")
        var extension: String,

        @Lob
        @Column(name = "description")
        var description: String,

        @CreatedBy
        @Column(name = "created_by", nullable = false, length = 50, updatable = false)
        @JsonIgnore
        var createdBy: String,

        @CreatedDate
        @Column(name = "created_date", updatable = false)
        @JsonIgnore
        var createdDate: Instant = Instant.now(),

        @LastModifiedBy
        @Lob
        @Column(name = "last_modified_by")
        @JsonIgnore
        var lastModifiedBy: String,

        @LastModifiedDate
        @Column(name = "last_modified_date")
        @JsonIgnore
        var lastModifiedDate: Instant = Instant.now()
)
