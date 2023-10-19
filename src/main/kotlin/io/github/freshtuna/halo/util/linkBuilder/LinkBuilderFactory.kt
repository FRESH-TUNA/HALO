package io.github.freshtuna.halo.util.linkBuilder

import io.github.freshtuna.halo.util.parser.TemplateParserDecorator
import io.github.freshtuna.halo.util.parser.uriTemplate.context.UriTemplateParserContext

/**
 * This class provides creating new LinkBuilders using dependent singleton objects
 */
class LinkBuilderFactory {
    companion object {

        /**
         * parser DI
         */
        private val halSimpleStringParser = UriTemplateParserContext.SIMPLE_STRING_PARSER

        private val halFormStyleQueryParser = UriTemplateParserContext.FORM_QUERY_PARSER

        private val expander = TemplateParserDecorator(
            halFormStyleQueryParser, halSimpleStringParser
        )

        /**
         * factory method
         */
        fun createLinkBuilder(): UriTemplateLinkBuilder {
            return UriTemplateLinkBuilder(expander)
        }

        fun createSimpleLinkBuilder(): SimpleLinkBuilder {
            return SimpleLinkBuilder(halSimpleStringParser)
        }
    }
}
