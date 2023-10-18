package io.github.freshtuna.halo.domain.hal.token

interface HalTokenizer {

    fun tokenize(base: String): List<HalToken>
}
