package io.github.freshtuna.halo.util.parser.uriTemplate.context

import io.github.freshtuna.halo.domain.uriTemplate.token.UriTemplateTokenizer
import io.github.freshtuna.halo.util.parser.uriTemplate.token.UriTemplateTokenizerV1

/**
 * This class manages singleton UriTemplateTokenizer
 */
class UriTemplateTokenizerContext {
    companion object {
        val DEFAULT_TOKENIZER: UriTemplateTokenizer = UriTemplateTokenizerV1()
    }
}
