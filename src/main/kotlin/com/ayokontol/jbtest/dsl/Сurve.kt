package com.ayokontol.jbtest.dsl

class Curve {
    lateinit var x: Expression
    lateinit var y: Expression
    var t = Expression("t", Types.T)
    var range = 0..0
}