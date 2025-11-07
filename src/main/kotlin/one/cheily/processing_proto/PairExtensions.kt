package one.cheily.processing_proto

operator fun Pair<Int, Int>.minus(other: Pair<Int, Int>): Pair<Int, Int> {
    return Pair(this.first - other.first, this.second - other.second)
}

operator fun Pair<Int, Int>.minus(scalar: Int): Pair<Int, Int> {
    return Pair(this.first - scalar, this.second - scalar)
}

operator fun Pair<Int, Int>.times(scalar: Int): Pair<Int, Int> {
    return Pair(this.first * scalar, this.second * scalar)
}

fun Pair<Int, Int>.toFloats(): Pair<Float, Float> {
    return Pair(this.first.toFloat(), this.second.toFloat())
}

operator fun Pair<Float, Float>.div(scalar: Int): Pair<Float, Float> {
    return Pair(this.first / scalar, this.second / scalar)
}

typealias Point = Pair<Float, Float>
typealias PointI = Pair<Int, Int>

val Point.x get() = this.first
val Point.y get() = this.second