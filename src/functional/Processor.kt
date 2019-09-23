package functional

import data.*
import java.lang.Math.pow
import kotlin.math.abs
import kotlin.math.sqrt

class Processor {

    fun distantion(first: Dot, second: Dot):Double {
        val result: Double = sqrt(pow(second.x - first.x,2.0) + pow(second.y - first.y,2.0))
        return result
    }

    fun perimeter(shape: Shape): Double {
        val perimeterShape: Double
        when {
            shape is Triangle -> {
                val firstLine: Double = distantion(shape.first, shape.second)
                val secondLine: Double = distantion(shape.second, shape.third)
                val thirdLine: Double = distantion(shape.third, shape.first)
                perimeterShape = firstLine + secondLine + thirdLine
            }

            shape is Squard -> {
                val firstLine: Double = distantion(shape.first, shape.second)
                val secondLine: Double = distantion(shape.second, shape.third)
                val thirdLine: Double = distantion(shape.third, shape.fourth)
                val fourthLine: Double = distantion(shape.fourth, shape.first)
                perimeterShape = firstLine + secondLine + thirdLine + fourthLine
            }

            shape is Trapeze -> {
                val firstLine: Double = distantion(shape.first, shape.second)
                val secondLine: Double = distantion(shape.second, shape.third)
                val thirdLine: Double = distantion(shape.third, shape.fourth)
                val fourthLine: Double = distantion(shape.fourth, shape.first)
                perimeterShape = firstLine + secondLine + thirdLine + fourthLine
            }

            else -> {
                val firstLine: Double = distantion((shape as Rhomb).first, shape.second)
                val secondLine: Double = distantion(shape.second, shape.third)
                val thirdLine: Double = distantion(shape.third, shape.fourth)
                val fourthLine: Double = distantion(shape.fourth, shape.first)
                perimeterShape = firstLine + secondLine + thirdLine + fourthLine
            }
        }
        return perimeterShape
    }


    fun cordinatOfVector(first: Dot, second: Dot) = Dot(abs(first.x - second.x), abs(first.y - second.y))

    fun multiOfVector(first: Dot, second: Dot) = (first.x * second.x) + (first.y * second.y)

    fun sizeOfVector(vector: Dot) = distantion(vector, Dot(0.0, 0.0) )

    fun cosOfAngle(vectorA: Dot, vectorB: Dot) =
        multiOfVector(vectorA, vectorB) / (sizeOfVector(vectorA) * sizeOfVector(vectorB))

    fun area(shape: Shape):Double {
        val areaShape: Double
        when (shape){
            is Triangle -> {
                val a: Double = distantion(shape.first, shape.second)
                val b: Double = distantion(shape.second, shape.third)
                val c: Double = distantion(shape.third, shape.first)

                val p: Double = perimeter(shape) / 2
                areaShape = sqrt(p * (p - a) * (p - b) * (p - c))
            }

            is Squard -> {
                val a: Double = distantion(shape.first, shape.second)

                areaShape = a * a
            }

            is Trapeze -> {
                val list = listOf(
                    cordinatOfVector(shape.first, shape.second),
                    cordinatOfVector(shape.second, shape.third),
                    cordinatOfVector(shape.third, shape.fourth),
                    cordinatOfVector(shape.fourth, shape.first)

                    )
                if (cosOfAngle(list[0], list[2]).toInt() == 1) {
                    val a = sizeOfVector(list[0])
                    val b = sizeOfVector(list[2])
                    val c = sizeOfVector(list[1])
                    val d = sizeOfVector(list[3])

                    val result: Double = (a + b) / 2 * sqrt( pow(c, 2.0) -
                            pow((pow(b - a,2.0) + pow(c, 2.0) - pow(d, 2.0)) / (2 * (b - a)), 2.0))
                    areaShape = result
                } else {
                    val a = sizeOfVector(list[1])
                    val b = sizeOfVector(list[3])
                    val c = sizeOfVector(list[2])
                    val d = sizeOfVector(list[0])

                    val result: Double = (a + b) / 2 * sqrt( pow(c, 2.0) -
                            pow((pow(b - a,2.0) + pow(c, 2.0) - pow(d, 2.0)) / (2 * (b - a)), 2.0))
                    areaShape = result
                }
            }

            else -> {
                shape as Rhomb
                areaShape = (distantion(shape.first, shape.third) * distantion(shape.second, shape.fourth)) / 2
            }
        }

        return areaShape
    }

}