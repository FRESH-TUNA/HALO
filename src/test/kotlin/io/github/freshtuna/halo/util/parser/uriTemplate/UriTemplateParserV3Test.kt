package io.github.freshtuna.halo.util.parser.uriTemplate

import io.github.freshtuna.halo.domain.variable.Variable
import io.github.freshtuna.halo.domain.variable.Variables
import io.github.freshtuna.halo.util.parser.uriTemplate.context.UriTemplateParserContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UriTemplateParserV3Test {


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
        variables.add(Variable(listKey, "blue"))
        variables.add(Variable(listKey, "red"))
        variables.add(Variable(listKey, "orange"))

        val parser = UriTemplateParserContext.SIMPLE_STRING_PARSER
        val parsed = parser.parse(variables, template)

        /**
         * then
         */
        assertEquals("blue,red,orange", parsed)
    }
}