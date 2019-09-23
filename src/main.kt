import data.*
import functional.Processor

fun main() {
    val rhomb: Rhomb = Rhomb(Dot(1.0,1.0), Dot(1.0, 3.0), Dot(3.0, 3.0),  Dot(3.0, 1.0))
    val processor: Processor = Processor()
    println(processor.area(rhomb))
}