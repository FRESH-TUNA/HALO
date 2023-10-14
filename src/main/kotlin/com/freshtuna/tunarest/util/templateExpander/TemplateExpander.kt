package com.freshtuna.tunarest.util.templateExpander

import com.freshtuna.tunarest.domain.Variables

interface TemplateExpander {

    fun expand(variables: Variables, template: String): String
}
