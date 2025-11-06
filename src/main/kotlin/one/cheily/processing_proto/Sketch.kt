package one.cheily.processing_proto

import processing.core.PApplet


object Sketch : PApplet() {
    val processingArgs = arrayOf<String?>(javaClass.simpleName)

    private val balls = ArrayList<Ball>()

    override fun settings() {
        size(500, 500)
        balls.add(Ball(this, width / 2f, height / 2f))
    }

    override fun draw() {
        background(64)
        for (b in balls) {
            b.step()
            b.render()
        }
    }

    override fun mouseDragged() {
        balls.add(Ball(this, mouseX * 1f, mouseY * 1f))
    }
}