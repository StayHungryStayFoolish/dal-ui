package ink.bonismo

import org.springframework.boot.WebApplicationType
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder

@SpringBootApplication
class KotlinUiApplication

fun main(args: Array<String>) {
    SpringApplicationBuilder(KotlinUiApplication::class.java).web(WebApplicationType.NONE).run(*args)
}
