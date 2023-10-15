package io.github.freshtuna.halo.util.linkBuilder

import io.github.freshtuna.halo.util.templateParser.HalFormStyleQueryParser
import io.github.freshtuna.halo.util.templateParser.HalSimpleStringParser

class LinkBuilderFactory {
    companion object {

        /**
         * parser DI
         */
        private val halSimpleStringExpander = HalSimpleStringParser()

        private val expander = HalFormStyleQueryParser(halSimpleStringExpander)

        /**
         * factory method
         */
        fun createLinkBuilder(): HalLinkBuilder {
            return HalLinkBuilder(expander)
        }

        fun createSimpleLinkBuilder(): SimpleLinkBuilder {
            return SimpleLinkBuilder(halSimpleStringExpander)
        }
    }
}
