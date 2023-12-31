package io.github.freshtuna.halo.util.linkBuilder

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertEquals

class UriTemplateLinkBuilderTest {

    @Test
    @DisplayName("test all extanders")
    fun test1() {

        /**
         * given
         */
        val userIdKey = "user"
        val postIdKey = "post"
        val pageKey = "page"
        val sortKey = "sort"
        val sizeKey = "size"
        val friendKey = "friend"

        val testTemplate = "/user/{${userIdKey}}/posts/{${postIdKey}}/comments{?${pageKey},${sortKey},${sizeKey},${friendKey}:5*}"


        /**
         * when
         */
        val userId = "freshtuna"
        val postId = "29381"

        val page = "1"
        val sort = "asc"
        val size = "5"

        val baseUrl = "https://stackoverflow.com"

        val link = UriTemplateLinkBuilder.builder()
            .setTemplate(testTemplate)
            .setRoot(baseUrl)

            .addVariable(userIdKey, userId)
            .addVariable(postIdKey, postId)

            .addVariable(pageKey, page)
            .addVariable(sortKey, sort)
            .addVariable(sizeKey, size)

            .addVariable(friendKey, "tom")
            .addVariable(friendKey, "mickey")
            .addVariable(friendKey, "peter")

            .build()

        /**
         * then
         */
        val expected = "${baseUrl}/user/${userId}/posts/${postId}/comments?${pageKey}=${page}&${sortKey}=${sort}&${sizeKey}=${size}&${friendKey}=tom&${friendKey}=peter"
        assertEquals(expected, link)
    }

    @Test
    @DisplayName("no parsing template test")
    fun test2() {

        /**
         * given
         */
        val testTemplate = "/users/me"


        /**
         * when
         */
        val root = "https://stackoverflow.com"

        val link = UriTemplateLinkBuilder.builder()
            .setTemplate(testTemplate)
            .setRoot(root)
            .build()

        /**
         * then
         */
        val expected = "${root}${testTemplate}"
        assertEquals(link, expected)
    }

    @Test
    @DisplayName("query parsing test")
    fun test3() {

        /**
         * given
         */
        val pageKey = "page"
        val sortKey = "sort"
        val sizeKey = "size"

        val testTemplate = "/user/1/comments{?${pageKey},${sortKey},${sizeKey}}"

        /**
         * when
         */
        val page = "1"
        val sort = "asc"

        val link = UriTemplateLinkBuilder.builder()
            .setTemplate(testTemplate)

            .addVariable(pageKey, page)
            .addVariable(sortKey, sort)
            .build()

        /**
         * then
         */
        val expected = "/user/1/comments?${pageKey}=${page}&${sortKey}=${sort}&${sizeKey}="
        assertEquals(expected, link)
    }

    @Test
    @DisplayName("ending or starting slash problem test")
    fun test4() {

        /**
         * given
         */
        val testTemplate = "/hahahaha"
        val root = "iamroot/"

        /**
         * when
         */
        val link = UriTemplateLinkBuilder.builder()
            .setTemplate(testTemplate)
            .setRoot(root)
            .build()

        assertEquals(link, "iamroot/hahahaha")
    }
}
