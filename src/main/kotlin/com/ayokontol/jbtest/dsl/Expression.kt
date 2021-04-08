package com.ayokontol.jbtest.dsl

import java.util.*

enum class Types {
    OP, NUM, T
}

class Expression {
    private var left: Expression? = null
    private var right: Expression? = null
    private var type: Types = Types.T
    var data: String = ""

    constructor(data_: String, type_: Types, left_: Expression? = null, right_: Expression? = null) {
        data = data_
        type = type_
        left = left_
        right = right_
    }

    constructor(data_: Int) {
        data = data_.toString()
        type = Types.NUM
        left = null
        right = null
    }

    constructor(data_: Double) {
        data = data_.toString()
        type = Types.NUM
        left = null
        right = null
    }

    operator fun times(other: Expression): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other.data, other.type, other.left, other.right)
        val type_ = Types.OP
        val data_ = "*"
        return Expression(data_, type_, left_, right_)
    }

    operator fun times(other: Int): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other)
        val type_ = Types.OP
        val data_ = "*"
        return Expression(data_, type_, left_, right_)
    }

    operator fun times(other: Double): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other)
        val type_ = Types.OP
        val data_ = "*"
        return Expression(data_, type_, left_, right_)
    }

    operator fun div(other: Expression): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other.data, other.type, other.left, other.right)
        val type_ = Types.OP
        val data_ = "/"
        return Expression(data_, type_, left_, right_)
    }

    operator fun div(other: Int): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other)
        val type_ = Types.OP
        val data_ = "/"
        return Expression(data_, type_, left_, right_)
    }

    operator fun div(other: Double): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other)
        val type_ = Types.OP
        val data_ = "/"
        return Expression(data_, type_, left_, right_)
    }

    operator fun plus(other: Expression): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other.data, other.type, other.left, other.right)
        val type_ = Types.OP
        val data_ = "+"
        return Expression(data_, type_, left_, right_)
    }

    operator fun plus(other: Int): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other)
        val type_ = Types.OP
        val data_ = "+"
        return Expression(data_, type_, left_, right_)
    }

    operator fun plus(other: Double): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other)
        val type_ = Types.OP
        val data_ = "+"
        return Expression(data_, type_, left_, right_)
    }

    operator fun minus(other: Expression): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other.data, other.type, other.left, other.right)
        val type_ = Types.OP
        val data_ = "-"
        return Expression(data_, type_, left_, right_)
    }

    operator fun minus(other: Int): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other)
        val type_ = Types.OP
        val data_ = "-"
        return Expression(data_, type_, left_, right_)
    }

    operator fun minus(other: Double): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(other)
        val type_ = Types.OP
        val data_ = "-"
        return Expression(data_, type_, left_, right_)
    }

    operator fun unaryMinus(): Expression {
        val left_ = Expression(this.data, this.type, this.left, this.right)
        val right_ = Expression(-1)
        val type_ = Types.OP
        val data_ = "*"
        return Expression(data_, type_, left_, right_)
    }

    fun inverse() : Expression {
        val left_ = Expression(1)
        val right_ = Expression(this.data, this.type, this.left, this.right)
        val type_ = Types.OP
        val data_ = "/"
        return Expression(data_, type_, left_, right_)
    }

    fun calculate(t: Double): Double {
        if (left == null && right == null) {
            return when (type) {
                Types.T -> t as Double
                Types.NUM -> data.toDouble()
                else -> throw InputMismatchException("Incorrect expression")
            }
        } else if (left != null && right != null) {
            if (type != Types.OP)
                throw InputMismatchException("Incorrect expression")
            return when(data) {
                "+" -> left!!.calculate(t) + right!!.calculate(t)
                "*" -> left!!.calculate(t) * right!!.calculate(t)
                "/" -> left!!.calculate(t) / right!!.calculate(t)
                "-" -> left!!.calculate(t) - right!!.calculate(t)
                else -> throw InputMismatchException("Incorrect expression")
            }
        }
        else
            throw InputMismatchException("Incorrect expression")
    }
}

operator fun Int.plus(other: Expression) = other + this
operator fun Int.minus(other: Expression) = -other + this
operator fun Int.times(other: Expression) = other * this
operator fun Int.div(other: Expression) = other.inverse() * this

operator fun Double.plus(other: Expression) = other + this
operator fun Double.minus(other: Expression) = -other + this
operator fun Double.times(other: Expression) = other * this
operator fun Double.div(other: Expression) = other.inverse() * this
