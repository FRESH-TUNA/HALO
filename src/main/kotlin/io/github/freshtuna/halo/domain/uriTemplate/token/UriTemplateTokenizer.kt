package io.github.freshtuna.halo.domain.uriTemplate.token

/**
 * This interfaces tokenize uri template (RFC 6570)
 *
 * ex) {?page,size:5,sort*,member:34*} -> page, size:5, sort*, member:34* (One template is parsed into four tokens)
 */
interface UriTemplateTokenizer {

    fun tokenize(base: String): List<UriTemplateToken>
}
