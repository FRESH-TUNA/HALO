package io.github.freshtuna.halo.util.parser.uriTemplate.token

import io.github.freshtuna.halo.domain.uriTemplate.token.UriTemplateToken
import io.github.freshtuna.halo.domain.uriTemplate.token.UriTemplateTokenizer

class UriTemplateTokenizerV1 : UriTemplateTokenizer{

    private val TOKEN_REGEX = Regex("([-,\\w]+):?(\\d+)?(\\*)?")

    override fun tokenize(base: String): List<UriTemplateToken> {

        return base.split(",").map { v ->

            val match = TOKEN_REGEX.matchEntire(v)

            val name = match!!.groups[1]?.value!!
            val allowedCount = match!!.groups[2]?.value?.toIntOrNull() ?: -1
            val allowMulti = match!!.groups[3]?.value == "*"

            UriTemplateToken(name, allowedCount, allowMulti)
        }.toList()
    }
}
