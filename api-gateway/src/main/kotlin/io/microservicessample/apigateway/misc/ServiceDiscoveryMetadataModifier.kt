package io.microservicessample.apigateway.misc

import com.netflix.appinfo.ApplicationInfoManager
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class ServiceDiscoveryMetadataModifier(val aim: ApplicationInfoManager) {

    @PostConstruct
    private fun addMetadata() {
        aim.info.metadata["description"] = "Some description"
    }
}