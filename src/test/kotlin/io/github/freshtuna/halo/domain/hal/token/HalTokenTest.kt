package io.github.freshtuna.halo.domain.hal.token

import io.github.freshtuna.halo.domain.variable.Variable
import io.github.freshtuna.halo.domain.variable.VariableRepresentationStrategy
import io.github.freshtuna.halo.domain.variable.Variables
import io.github.freshtuna.halo.util.variable.strategy.DictRepresentationStrategy
import io.github.freshtuna.halo.util.variable.strategy.VariableStrategyContext
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HalTokenTest {

    @Test
    @DisplayName("multi variable test")
    fun multiTest() {

        /**
         * given
         */
        val name = "name"
        val seperator = "x"
        val stretegy = VariableStrategyContext.DICT_STYLE


        /**
         * when
         */
        val variables = Variables()

        val blue = Variable(name, "blue")
        val red = Variable(name, "red")
        val orange = Variable(name, "orange")

        variables.add(blue)
        variables.add(red)
        variables.add(orange)


        val halToken = HalToken(name, allowMulti = true)
        val result = halToken.parse(variables, stretegy, seperator)

        /**
         * then
         */
        val expected = blue.toString(stretegy)+seperator+red.toString(stretegy)+seperator+orange.toString(stretegy)+seperator

        assertEquals(expected, result)
    }

    @Test
    @DisplayName("value length limit test when multi value disallowed")
    fun valueLengthLimitTest() {

        /**
         * given
         */
        val name = "name"
        val limit = 3
        val seperator = "x"
        val stretegy = VariableStrategyContext.DICT_STYLE


        /**
         * when
         */
        val variables = Variables()
        variables.add(Variable(name, "blue"))


        val halToken = HalToken(name, limit, allowMulti = false)
        val result = halToken.parse(variables, stretegy, seperator)

        /**
         * then
         */
        assertEquals("", result)
    }

    @Test
    @DisplayName("if variable not Passed when multi disallowed")
    fun ifVariableNotPassedWhenMultiDisallowed() {

        /**
         * given
         */
        val name = "name"
        val seperator = "x"
        val stretegy = VariableStrategyContext.DICT_STYLE


        /**
         * when
         */
        val variables = Variables()

        val halToken = HalToken(name, allowMulti = false)
        val result = halToken.parse(variables, stretegy, seperator)

        /**
         * then
         */
        assertEquals("name=$seperator", result)
    }

    @Test
    @DisplayName("value length limit test when multi value allowed")
    fun valueLengthLimitTestWhenMultiValueAllowed() {

        /**
         * given
         */
        val name = "name"
        val limit = 4
        val seperator = "x"
        val stretegy = VariableStrategyContext.DICT_STYLE


        /**
         * when
         */
        val variables = Variables()

        variables.add(Variable(name, "green"))
        variables.add(Variable(name, "blue"))
        variables.add(Variable(name, "orange"))
        variables.add(Variable(name, "red"))

        val halToken = HalToken(name, limit,true)
        val result = halToken.parse(variables, stretegy, seperator)

        /**
         * then
         */
        val expected = stretegy.parse(Variable(name, "blue"))+seperator+stretegy.parse(Variable(name, "red"))+seperator
        assertEquals(expected, result)
    }
}
