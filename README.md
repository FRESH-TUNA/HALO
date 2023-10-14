# TUNA REST

## ?
The project includes objects and tools to help develop Java (Kotlin)-based restful api.

## features
### SimpleLinkBuilder
- help to create link(url).
- supports simple string expand feature of hal `ex) /users/{userId} -> /users/1`
- query add feature `ex) ?a=1&b=2&c=3)`
- [usecases](src/test/kotlin/com/freshtuna/tunarest/util/linkBuilder/SimpleLinkBuilderTest.kt) 

### HalLinkBuilder
- help to expand hal ([RFC 6570](https://datatracker.ietf.org/doc/html/rfc6570#section-1.4)) based template to create url.
- [usecases](src/test/kotlin/com/freshtuna/tunarest/util/linkBuilder/HalLinkBuilderTest.kt)

## developing...
- hal based response dto
