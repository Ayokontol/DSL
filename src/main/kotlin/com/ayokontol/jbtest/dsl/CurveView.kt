package com.ayokontol.jbtest.dsl

import javafx.scene.canvas.GraphicsContext
import tornadofx.*

class CurveView : View() {
    private lateinit var gc: GraphicsContext
    private val zoom = 10
    private val canvasWidth = 500.0
    private val canvasHeight = 500.0

    override val root = stackpane {
        prefWidth = 600.0
        prefHeight = 600.0
        canvas(canvasWidth, canvasHeight) {
            gc = graphicsContext2D
        }

    }

    fun drawCurves(curves: ArrayList<Curve>) {
        for (curve in curves) {
            val epsilon = 0.01
            val arrayT = ArrayList<Double>()
            val arrayX = ArrayList<Double>()
            val arrayY = ArrayList<Double>()
            var cur = curve.range.first.toDouble()
            while (cur < curve.range.last) {
                arrayT.add(cur)
                cur += epsilon
            }
            arrayT.add(curve.range.last.toDouble())
            for (t in arrayT) {
                arrayX.add(curve.x.calculate(t))
                arrayY.add(curve.y.calculate(t))
            }
            drawCurve(arrayX, arrayY)
        }
    }

    private fun drawCurve(x: ArrayList<Double>, y: ArrayList<Double>) {
        for (i in 0 until x.lastIndex)
            gc.strokeLine(
                x[i] * zoom, canvasHeight - y[i] * zoom,
                x[i + 1] * zoom, canvasHeight - y[i + 1] * zoom
            )
    }
}