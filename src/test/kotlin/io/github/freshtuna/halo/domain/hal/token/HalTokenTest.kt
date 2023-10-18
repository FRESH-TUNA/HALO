package io.github.freshtuna.halo.domain.hal.token

import io.github.freshtuna.halo.domain.Variables
import io.github.freshtuna.halo.util.parser.hal.strategy.HalDictParingStrategy
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
        val stretegy = HalDictParingStrategy()


        /**
         * when
         */
        val variables = Variables()
        variables.add(name, "blue")
        variables.add(name, "red")
        variables.add(name, "orange")


        val halToken = HalToken(name, allowMulti = true)
        val result = halToken.parse(variables, stretegy, seperator)

        /**
         * then
         */
        val expected = stretegy.parse(name, "blue")+seperator+stretegy.parse(name, "red")+seperator+stretegy.parse(name, "orange")+seperator

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
        val stretegy = HalDictParingStrategy()


        /**
         * when
         */
        val variables = Variables()
        variables.add(name, "blue")


        val halToken = HalToken(name, limit, allowMulti = false)
        val result = halToken.parse(variables, stretegy, seperator)

        /**
         * then
         */
        assertEquals("", result)
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
        val stretegy = HalDictParingStrategy()


        /**
         * when
         */
        val variables = Variables()

        variables.add(name, "green")
        variables.add(name, "blue")
        variables.add(name, "orange")
        variables.add(name, "red")

        val halToken = HalToken(name, limit,true)
        val result = halToken.parse(variables, stretegy, seperator)

        /**
         * then
         */
        val expected = stretegy.parse(name, "blue")+seperator+stretegy.parse(name, "red")+seperator
        assertEquals(expected, result)
    }
}
