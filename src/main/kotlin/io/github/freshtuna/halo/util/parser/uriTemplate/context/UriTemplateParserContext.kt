package io.github.freshtuna.halo.util.parser.uriTemplate.context

import io.github.freshtuna.halo.domain.uriTemplate.UriTemplateMode
import io.github.freshtuna.halo.util.parser.uriTemplate.UriTemplateParserV3

/**
 * This class manages singleton UriTemplateParserV3
 */
class UriTemplateParserContext {

    companion object {
        val SIMPLE_STRING_PARSER = UriTemplateParserV3(UriTemplateMode.SIMPLE_STRING, UriTemplateTokenizerContext.DEFAULT_TOKENIZER)
        val FORM_QUERY_PARSER = UriTemplateParserV3(UriTemplateMode.FORM_STYLE_QUERY, UriTemplateTokenizerContext.DEFAULT_TOKENIZER)
    }
}
