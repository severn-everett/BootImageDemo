package com.severett.bootimagedemo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class BootImageDemo {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(BootImageDemo::class.java, *args)
        }
    }
}
