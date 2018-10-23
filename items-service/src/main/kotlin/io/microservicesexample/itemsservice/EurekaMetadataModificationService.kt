package io.microservicesexample.itemsservice

import com.netflix.appinfo.ApplicationInfoManager
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class EurekaMetadataModificationService(private val aim: ApplicationInfoManager) {

    @PostConstruct
    private fun addMetadata() {
        aim.registerAppMetadata(mapOf("description" to "Some description"))
    }
}