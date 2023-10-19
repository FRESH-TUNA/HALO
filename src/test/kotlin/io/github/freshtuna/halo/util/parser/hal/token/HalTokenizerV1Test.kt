package io.github.freshtuna.halo.util.parser.hal.token

import io.github.freshtuna.halo.domain.hal.token.HalToken
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class HalTokenizerV1Test {

    @Test
    @DisplayName("name, limit length, multi allow parsing text")
    fun test() {

        /**
         * given
         */
        val baseString = "page,repository*,size:2,friend:52*"

        /**
         * then
         */
        val tokenizerV1 = HalTokenizerV1()
        val tokens = tokenizerV1.tokenize(baseString)

        assertEquals(4, tokens.size)

        assertEquals("page", tokens[0].name)
        assertEquals(false, tokens[0].allowMulti)
        assertEquals(HalToken.UNLIMITED, tokens[0].allowedCount)

        assertEquals("repository", tokens[1].name)
        assertEquals(true, tokens[1].allowMulti)
        assertEquals(HalToken.UNLIMITED, tokens[1].allowedCount)

        assertEquals("size", tokens[2].name)
        assertEquals(false, tokens[2].allowMulti)
        assertEquals(2, tokens[2].allowedCount)

        assertEquals("friend", tokens[3].name)
        assertEquals(true, tokens[3].allowMulti)
        assertEquals(52, tokens[3].allowedCount)
    }
}
