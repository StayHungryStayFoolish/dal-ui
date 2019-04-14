package ink.bonismo.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import ink.bonismo.domain.enumeration.UIStatus
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
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

@Entity
@Table(name = "dictionary")
data class Dictionary(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long,

        @NotNull
        @Column(name = "parent", nullable = false)
        var parent: String,

        @NotNull
        @Column(name = "name", nullable = false)
        var name: String,

        @NotNull
        @Size(max = 5)
        @Column(name = "language", length = 5, nullable = false)
        var language: String,

        @NotNull
        @Enumerated(EnumType.STRING)
        @Column(name = "status", length = 10, nullable = false)
        var status: UIStatus,

        @Size(max = 800)
        @Column(name = "icon", length = 800)
        var icon: String,

        @Size(max = 800)
        @Column(name = "url", length = 800)
        var url: String,

        @Size(max = 800)
        @Column(name = "links", length = 800)
        var links: String,

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
