package io.github.freshtuna.halo.util.parser.hal.context

import io.github.freshtuna.halo.domain.hal.token.HalTokenizer
import io.github.freshtuna.halo.util.parser.hal.token.HalTokenizerV1

/**
 * This class manages singleton HalTokenizer
 */
class HalTokenizerContext {
    companion object {
        val DEFAULT_TOKENIZER: HalTokenizer = HalTokenizerV1()
    }
}
