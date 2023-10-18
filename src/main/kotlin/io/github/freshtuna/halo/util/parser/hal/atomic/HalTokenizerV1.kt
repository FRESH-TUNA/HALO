package io.github.freshtuna.halo.util.parser.hal.atomic

import io.github.freshtuna.halo.domain.hal.token.HalToken
import io.github.freshtuna.halo.domain.hal.token.HalTokenizer

class HalTokenizerV1 : HalTokenizer{

    private val ATOMIC_REGEX = Regex("([-,\\w]+)(:\\d)?(\\*)?")

    override fun tokenize(base: String): List<HalToken> {

        return base.split(",").map { v ->
            val match = ATOMIC_REGEX.matchEntire(v)

            val name = match!!.groups[1]?.value!!
            val allowedCount = match!!.groups[2]?.value?.toIntOrNull() ?: -1
            val allowMulti = match!!.groups[3]?.value == "*"

            HalToken(name, allowedCount, allowMulti)
        }.toList()
    }
}
