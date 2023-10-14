package com.freshtuna.tunarest.util.linkBuilder

import com.freshtuna.tunarest.util.templateExpander.HalFormStyleQueryExpander
import com.freshtuna.tunarest.util.templateExpander.HalSimpleStringExpander

class LinkBuilderFactory {
    companion object {

        /**
         * expander DI
         */
        private val halSimpleStringExpander = HalSimpleStringExpander()

        private val expander = HalFormStyleQueryExpander(halSimpleStringExpander)

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
