package io.microservicesexample.itemsservice.misc

import com.netflix.appinfo.ApplicationInfoManager
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class EurekaAdditionalMetadataReporter(private val aim: ApplicationInfoManager) {

    @PostConstruct
    private fun addMetadata() = aim.registerAppMetadata(mapOf("description" to "Some description"))
}