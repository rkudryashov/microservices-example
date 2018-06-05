package io.microservicessample.uigateway.misc

import com.netflix.appinfo.ApplicationInfoManager
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class EurekaMetadataModificationService(private val aim: ApplicationInfoManager) {

    @PostConstruct
    private fun addMetadata() {
        aim.info.metadata["description"] = "Some description"
    }
}