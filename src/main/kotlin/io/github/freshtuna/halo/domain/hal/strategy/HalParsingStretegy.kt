package io.github.freshtuna.halo.domain.hal.strategy


interface HalParsingStretegy {

    fun parse(name: String, value: String?): String
}
