package com.freshtuna.tunahal.util.templateExpander

import com.freshtuna.tunahal.domain.Variables

interface TemplateExpander {

    fun expand(variables: Variables, template: String): String
}
