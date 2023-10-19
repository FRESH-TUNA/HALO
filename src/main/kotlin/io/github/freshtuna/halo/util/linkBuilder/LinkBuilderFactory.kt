package io.github.freshtuna.halo.util.linkBuilder

import io.github.freshtuna.halo.util.parser.TemplateParserDecorator
import io.github.freshtuna.halo.util.parser.hal.context.HalTemplateParserContext

/**
 * This class provides creating new LinkBuilders using dependent singleton objects
 */
class LinkBuilderFactory {
    companion object {

        /**
         * parser DI
         */
        private val halSimpleStringParser = HalTemplateParserContext.SIMPLE_STRING_PARSER

        private val halFormStyleQueryParser = HalTemplateParserContext.FORM_QUERY_PARSER

        private val expander = TemplateParserDecorator(
            halFormStyleQueryParser, halSimpleStringParser
        )

        /**
         * factory method
         */
        fun createLinkBuilder(): HalLinkBuilder {
            return HalLinkBuilder(expander)
        }

        fun createSimpleLinkBuilder(): SimpleLinkBuilder {
            return SimpleLinkBuilder(halSimpleStringParser)
        }
    }
}
