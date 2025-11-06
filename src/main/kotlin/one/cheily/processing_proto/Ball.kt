package one.cheily.processing_proto
import processing.core.PApplet

class Ball(private val sketch: PApplet, private var x: Float, private var y: Float) {
    private val size: Float = sketch.random(10f, 100f)
    private var xSpeed: Float = sketch.random(-10f, 10f)
    private var ySpeed: Float = sketch.random(-10f, 10f)

    fun step() {
        x += xSpeed
        if (x < 0 || x > sketch.width) {
            xSpeed *= -1f
        }

        y += ySpeed
        if (y < 0 || y > sketch.height) {
            ySpeed *= -1f
        }
    }

    fun render() {
        sketch.ellipse(x, y, size, size)
    }
}