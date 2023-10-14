package com.freshtuna.tunahal.util.linkBuilder

import com.freshtuna.tunahal.util.templateExpander.HalFormStyleQueryExpander
import com.freshtuna.tunahal.util.templateExpander.HalSimpleStringExpander

class LinkBuilderFactory {
    companion object {

        /**
         * expander DI
         */
        private val halFormStyleQueryExpander = HalFormStyleQueryExpander()

        private val expander = HalSimpleStringExpander(halFormStyleQueryExpander)

        /**
         * factory method
         */
        fun createLinkBuilder(): LinkBuilder {
            return LinkBuilder(expander)
        }
    }
}
