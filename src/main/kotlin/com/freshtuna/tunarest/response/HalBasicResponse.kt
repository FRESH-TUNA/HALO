package com.freshtuna.tunarest.response

import java.util.HashMap;

open class HalBasicResponse {

    private val _links = HashMap<String, String>()

    companion object {
        private const val SELF_RELATION = "self";
    }

    fun addLink(relation: String, url: String) {
        this._links[relation] = url;
    }

    fun addSelfLink(url: String) {
        addLink(SELF_RELATION, url);
    }
}
