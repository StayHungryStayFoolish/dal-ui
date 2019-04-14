package ink.bonismo.domain

import lombok.Data
import org.hibernate.envers.Audited
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant
import javax.persistence.Column
import javax.persistence.EntityListeners
import javax.persistence.MappedSuperclass

/**
 * Created by bonismo@hotmail.com on 2019/4/12 10:34 AM
 *
 * @Description:
 * @Version: 1.0
 */   
@EntityListeners(AuditingEntityListener::class)
@MappedSuperclass
@Audited
@Data
open class AuditingEntity {


}
