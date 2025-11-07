package one.cheily.processing_proto

import processing.core.PApplet


object Sketch : PApplet() {
    val processingArgs = arrayOf<String?>(javaClass.simpleName)
    object Config {
        val properties get() = Properties[Config::class]

        val size by Property("size", Pair(800, 800)) {}
        val gridSize by Property("grid size", 32) {}
        val margin by Property("margin", 8) {}
        val gap by Property("gap", 2) {}

    }
    object Debug {
        val toggles get() = Properties[Debug::class]
        val showPivots by Property("Show Pivots", false) {}

        var active = false
        fun draw() {
            val toggleH = (toggles.size + 1f) * 20
            fill(0.1f);
            rect(15f, 10f, 250f, toggleH)
            fill(1.0f);
            textAlign(LEFT, TOP)
            text("Click NUM_<INDEX> to toggle property.", 25f, 15f)
            var i = 0
            for ((key, value) in toggles) {
                text("[$i] $key? $value", 25f, (20 * (i + 1) + 15).toFloat())
                i++
            }
            fill(0.1f);
            rect(15f, toggleH + 15f, 250f, (Config.properties.size * 20).toFloat())
            fill(color(1.0f, 1.0f, 0.0f))
            textAlign(LEFT, TOP)
            i = 0
            for ((key, value) in Config.properties) {
                val delimiter = if (value is Boolean) "?" else ":"
                text("$key$delimiter $value", 25f, toggleH + (20 * (i + 1)))
                i++
            }
        }

        fun handleKeyPress(keyCode: Int) {
            if (keyCode < java.awt.event.KeyEvent.VK_NUMPAD0 ||
                keyCode > java.awt.event.KeyEvent.VK_NUMPAD9) return

            val index = keyCode - java.awt.event.KeyEvent.VK_NUMPAD0
            toggles.keys.elementAtOrNull(index)?.let {
                val currentValue = toggles[it]
                if (currentValue is Boolean) {
                    toggles[it] = !currentValue
                }
            }
        }
    }

    val grid = mutableListOf<GridPoint>()
    init {
        for (yi in 0 until Config.gridSize) {
            for (xi in 0 until Config.gridSize) {
                grid.add(GridPoint(xi, yi))
            }
        }
    }

    override fun settings() {
        size(Config.size.first, Config.size.second)
    }

    override fun setup() {
        colorMode(RGB, 1.0f)
    }

    override fun draw() {
        background(64)
        grid.forEach(GridPoint::draw)

        if (Debug.active) {
            Debug.draw()
        }
    }

    override fun keyPressed() {
        if (keyCode == java.awt.event.KeyEvent.VK_F1) {
            Debug.active = !Debug.active
        }
        if (Debug.active) {
            Debug.handleKeyPress(keyCode)
        }
    }
}