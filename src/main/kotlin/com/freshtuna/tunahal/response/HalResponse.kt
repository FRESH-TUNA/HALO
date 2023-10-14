package com.freshtuna.tunahal.response

class HalResponse : HalBasicResponse() {

    private val _embedded: MutableMap<String, Any> = HashMap()


    fun addCollection(key: String, collection: Collection<Any>) {
        _embedded[key] = collection;
    }

    fun addOne(key: String, entity: Any) {
        _embedded[key] = entity
    }
}
