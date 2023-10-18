package io.github.freshtuna.halo.util.parser.hal.strategy

import io.github.freshtuna.halo.domain.hal.strategy.HalParsingStretegy

class HalValueParsingStrategy : HalParsingStretegy {

    override fun parse(name: String, value: String?): String {
        return value.orEmpty()
    }
}
