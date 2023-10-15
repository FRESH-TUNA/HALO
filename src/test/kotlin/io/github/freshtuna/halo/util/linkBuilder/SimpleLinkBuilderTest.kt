package io.github.freshtuna.halo.util.linkBuilder

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class SimpleLinkBuilderTest {

    @Test
    @DisplayName("tests all features of SimpleLinkBuilder")
    fun test1() {

        /**
         * given
         */
        val userIdKey = "user"
        val postIdKey = "post"
        val pageKey = "page"
        val sortKey = "sort"
        val sizeKey = "size"

        val testTemplate = "/user/{${userIdKey}}/posts/{${postIdKey}}/comments"


        /**
         * when
         */
        val userId = "freshtuna"
        val postId = "29381"

        val page = "1"
        val sort = "asc"
        val size = "5"

        val baseUrl = "https://stackoverflow.com"

        val link = SimpleLinkBuilder.builder()
            .setTemplate(testTemplate)
            .setRoot(baseUrl)

            .addArg(userIdKey, userId)
            .addArg(postIdKey, postId)

            .addQuery(pageKey, page)
            .addQuery(sortKey, sort)
            .addQuery(sizeKey, size)

            .build()

        /**
         * then
         */
        val expected = "${baseUrl}/user/${userId}/posts/${postId}/comments?${pageKey}=${page}&${sizeKey}=${size}&${sortKey}=${sort}"
        assertEquals(expected, link)
    }

    @Test
    @DisplayName("setPath test")
    fun test2() {

        /**
         * given
         */
        val root = "https://stackoverflow.com"
        val testTemplate = "/users/me"

        /**
         * when
         */
        val link = SimpleLinkBuilder.builder()
            .setPath(testTemplate)
            .setRoot(root)
            .build()

        /**
         * then
         */
        val expected = "${root}${testTemplate}"
        assertEquals(expected, link)
    }

    @Test
    @DisplayName("setTemplate test")
    fun test3() {

        /**
         * given
         */
        val root = "https://stackoverflow.com"
        val testTemplate = "/users/me"

        /**
         * when
         */
        val link = SimpleLinkBuilder.builder()
            .setTemplate(testTemplate)
            .setRoot(root)
            .build()

        /**
         * then
         */
        val expected = "${root}${testTemplate}"
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
        val link = SimpleLinkBuilder.builder()
            .setTemplate(testTemplate)
            .setRoot(root)
            .build()

        assertEquals(link, "iamroot/hahahaha")
    }
}