package io.romankudryashov.bootcloudkotlin

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
open class BootCloudKotlinApplication

fun main(args: Array<String>) {
    runApplication<BootCloudKotlinApplication>(*args)
}
