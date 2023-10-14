package com.freshtuna.tunahal.util.linkBuilder

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class LinkBuilderTest {

    /**
     * parsing test
     */
    @Test
    @DisplayName("LinkBuilder의 모든 기능 테스트")
    fun test() {

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

        val link = LinkBuilder.builder()
            .setTemplate(testTemplate)
            .setBaseUrl(baseUrl)
            .addArgument(userIdKey, userId)
            .addArgument(postIdKey, postId)
            .addQuery(pageKey, page)
            .addQuery(sortKey, sort)
            .addQuery(sizeKey, size)
            .build()

        /**
         * then
         */
        val expected = "${baseUrl}/user/${userId}/posts/${postId}/comments?${pageKey}=${page}&${sortKey}=${sort}&${sizeKey}=${size}"
        assertEquals(link, expected)
    }
}
