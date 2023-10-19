package io.github.freshtuna.halo.util.parser.hal.context

import io.github.freshtuna.halo.domain.hal.HalMode
import io.github.freshtuna.halo.util.parser.hal.HalTemplateParserV3

/**
 * This class manages singleton HalTemplateParserV3
 */
class HalTemplateParserContext {

    companion object {
        val SIMPLE_STRING_PARSER = HalTemplateParserV3(HalMode.SIMPLE_STRING, HalTokenizerContext.DEFAULT_TOKENIZER)
        val FORM_QUERY_PARSER = HalTemplateParserV3(HalMode.FORM_STYLE_QUERY, HalTokenizerContext.DEFAULT_TOKENIZER)
    }
}
