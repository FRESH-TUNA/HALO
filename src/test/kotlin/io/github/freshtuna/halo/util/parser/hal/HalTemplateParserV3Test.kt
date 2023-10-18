package io.github.freshtuna.halo.util.parser.hal

import io.github.freshtuna.halo.domain.Variables
import io.github.freshtuna.halo.util.parser.hal.context.HalTemplateParserContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HalTemplateParserV3Test() {


    @Test
    @DisplayName("multi variable test")
    fun multiTest() {

        /**
         * given
         */
        val listKey = "list"
        val template = "{$listKey*}"
        val variables = Variables()

        /**
         * when
         */
        variables.add(listKey, "blue")
        variables.add(listKey, "red")
        variables.add(listKey, "orange")

        val parser = HalTemplateParserContext.SIMPLE_STRING_PARSER
        val parsed = parser.parse(variables, template)

        /**
         * then
         */
        assertEquals("blue,red,orange", parsed)
    }
}