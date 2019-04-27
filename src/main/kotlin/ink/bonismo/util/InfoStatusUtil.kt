package ink.bonismo.util

import ink.bonismo.Info
import ink.bonismo.domain.enumeration.InfoStatus

/**
 * Created by bonismo@hotmail.com on 2019/4/26 5:50 PM
 *
 * @Description:
 * @Version: 1.0
 */
object InfoStatusUtil {

     fun toEntityStatus(protoStatus: Info.InfoStatus): InfoStatus {
         val entityStatus: InfoStatus? = when (protoStatus) {
             Info.InfoStatus.NULL -> InfoStatus.NULL
             Info.InfoStatus.PUBLISHED -> InfoStatus.PUBLISHED
             Info.InfoStatus.REVIEWED -> InfoStatus.REVIEWED
             Info.InfoStatus.DRAFTED -> InfoStatus.DRAFTED
             Info.InfoStatus.CANCELED -> InfoStatus.CANCELED
             else -> InfoStatus.NULL
         }
         return entityStatus!!
    }

    fun toProtoStatus(entityStatus: InfoStatus): Info.InfoStatus? {
        return when (entityStatus) {
            InfoStatus.NULL -> Info.InfoStatus.NULL
            InfoStatus.PUBLISHED -> Info.InfoStatus.PUBLISHED
            InfoStatus.REVIEWED -> Info.InfoStatus.REVIEWED
            InfoStatus.DRAFTED -> Info.InfoStatus.DRAFTED
            InfoStatus.CANCELED -> Info.InfoStatus.CANCELED
        }
    }
}
