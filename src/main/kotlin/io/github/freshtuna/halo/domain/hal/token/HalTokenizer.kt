package io.github.freshtuna.halo.domain.hal.token

/**
 * This interfaces tokenize hal template String
 *
 * ex) {?page,size:5,sort*,member:34*} -> page, size:5, sort*, member:34* (One template is parsed into four tokens)
 */
interface HalTokenizer {

    fun tokenize(base: String): List<HalToken>
}
