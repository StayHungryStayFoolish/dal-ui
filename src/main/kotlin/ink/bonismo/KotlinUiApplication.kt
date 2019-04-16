package ink.bonismo

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.EnableConfigurationProperties

@SpringBootApplication
@EnableConfigurationProperties(LiquibaseProperties::class)
@EnableAutoConfiguration(exclude = [org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration::class])
class KotlinUiApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(KotlinUiApplication::class.java).web(WebApplicationType.SERVLET).run(*args)
}
