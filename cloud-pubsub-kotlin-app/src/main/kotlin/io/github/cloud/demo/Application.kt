package io.github.cloud.demo

import io.github.cloud.demo.listener.PubSubListener
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication


@SpringBootApplication
@EnableAutoConfiguration
open class Application

fun main(args: Array<String>) {
    val ctx = SpringApplication.run(Application::class.java)
    ctx.getBean(PubSubListener::class.java).listen()
}