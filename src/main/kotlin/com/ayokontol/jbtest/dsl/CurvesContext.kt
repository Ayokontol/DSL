package com.ayokontol.jbtest.dsl

import tornadofx.find
import tornadofx.launch

class CurveContext {
    val curves = ArrayList<Curve>()

    fun curve(init: Curve.() -> Unit) = Curve().apply {
        this.init()
        curves.add(this)
    }
}

object curves {
    operator fun invoke(init: CurveContext.() -> Unit) {
        val context = CurveContext()
        context.init()
        find<CurveView>().drawCurves(context.curves)
        launch<DrawApp>()
    }
}