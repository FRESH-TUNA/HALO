# HALO
![Maven Central](https://img.shields.io/maven-central/v/io.github.fresh-tuna/halo?style=flat-square&color=green)

## Description
This project provides a variety of tools to help you develop restful APIs and parse HAL templates in kotlin/java environments.

We are currently working on creating a tool to parse templates in compliance with ([RFC 6570](https://datatracker.ietf.org/doc/html/rfc6570#section-1.4)) standards.


## How to install?
```shell
# gradle
implementation("io.github.fresh-tuna:halo:1.0.2-beta")

# maven
<dependency>
    <groupId>io.github.fresh-tuna</groupId>
    <artifactId>halo</artifactId>
    <version>1.0.1-beta</version>
</dependency>

```

## features
### LinkBuilder
Tools to create links to access the resources needed to create a restful API.

#### SimpleLinkBuilder
- help to create link(url).
- supports simple string expand feature of hal `ex) /users/{userId} -> /users/1`
- Adding query feature `ex) ?a=1&b=2&c=3)`
- [usecases](src/test/kotlin/io/github/freshtuna/halo/util/linkBuilder/SimpleLinkBuilderTest.kt) 

#### HalLinkBuilder
- help to expand hal ([RFC 6570](https://datatracker.ietf.org/doc/html/rfc6570#section-1.4)) based template to create url.
- Currently supporting simple string expand, form query expand of hal
- [usecases](src/test/kotlin/io/github/freshtuna/halo/util/linkBuilder/HalLinkBuilderTest.kt)

### HAL Templete Parser
Tools to parse hal template

#### [TemplateParser](src/main/kotlin/io/github/freshtuna/halo/util/templateParser/TemplateParser.kt)
- interface to all Parsers
- All implemented parsers has TemplateParser variable.
- So parsers can be chained to parse templates with multiple rules. (Decorator pattern)

#### [HAL Simple String Parser](src/main/kotlin/io/github/freshtuna/halo/util/templateParser/HalSimpleStringParser.kt)
- Supports to parse simple string in HAL Template ([RFC 6570](https://datatracker.ietf.org/doc/html/rfc6570#section-1.4))
- `ex) /users/{userId} -> /users/1`
- Used in SimpleLinkBuilder

#### [HAL Form Style Query Parser](src/main/kotlin/io/github/freshtuna/halo/util/templateParser/HalFormStyleQueryParser.kt)
- Supports to parse Form Style Query in HAL Template ([RFC 6570](https://datatracker.ietf.org/doc/html/rfc6570#section-1.4))
- `ex) {?name}, {?page,sort,size} -> ?name=tuna, ?page=1&sort=2&size=3`
- Used in HalLinkBuilder
