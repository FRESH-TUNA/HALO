package com.freshtuna.tunarest.util.linkBuilder

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class HalLinkBuilderTest {

    @Test
    @DisplayName("LinkBuilder의 모든 파서 테스트")
    fun test1() {

        /**
         * given
         */
        val userIdKey = "user"
        val postIdKey = "post"
        val pageKey = "page"
        val sortKey = "sort"
        val sizeKey = "size"

        val testTemplate = "/user/{${userIdKey}}/posts/{${postIdKey}}/comments{?${pageKey},${sortKey},${sizeKey}}"


        /**
         * when
         */
        val userId = "freshtuna"
        val postId = "29381"

        val page = "1"
        val sort = "asc"
        val size = "5"

        val baseUrl = "https://stackoverflow.com"

        val link = HalLinkBuilder.builder()
            .setTemplate(testTemplate)
            .setRoot(baseUrl)

            .addVariable(userIdKey, userId)
            .addVariable(postIdKey, postId)

            .addVariable(pageKey, page)
            .addVariable(sortKey, sort)
            .addVariable(sizeKey, size)

            .build()

        /**
         * then
         */
        val expected = "${baseUrl}/user/${userId}/posts/${postId}/comments?${pageKey}=${page}&${sortKey}=${sort}&${sizeKey}=${size}"
        assertEquals(link, expected)
    }

    @Test
    @DisplayName("파싱을 하지 않는 테스트케이스")
    fun test2() {

        /**
         * given
         */
        val testTemplate = "/users/me"


        /**
         * when
         */
        val root = "https://stackoverflow.com"

        val link = HalLinkBuilder.builder()
            .setTemplate(testTemplate)
            .setRoot(root)
            .build()

        /**
         * then
         */
        val expected = "${root}${testTemplate}"
        assertEquals(link, expected)
    }
}
