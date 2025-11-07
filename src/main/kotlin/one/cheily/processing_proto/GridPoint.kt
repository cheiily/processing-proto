package one.cheily.processing_proto


class GridPoint(val xi: Int, val yi: Int) {
    companion object {
        val cellSize = (Sketch.Config.size - Pair(
            Sketch.Config.margin * 2,
            Sketch.Config.margin * 2
        ) - (Sketch.Config.gridSize * Sketch.Config.gap)).toFloats() / Sketch.Config.gridSize
        init {
            Sketch.Config.properties["cell size"] = cellSize
        }
    }
    var node: Node? = null


    val pos = Point(
        xi * (cellSize.first + Sketch.Config.gap) + Sketch.Config.margin,
        yi * (cellSize.second + Sketch.Config.gap) + Sketch.Config.margin
    )
    val center = Point(
        pos.x + cellSize.first / 2,
        pos.y + cellSize.second / 2
    )

    fun draw() = with(Sketch) {
        fill(if (mouseWithin()) 0.75f else 0.5f)
        rect(pos.x, pos.y, cellSize.first, cellSize.second)
        if (Sketch.Debug.showPivots) {
            fill(1.0f)
            circle(center.x, center.y, 5.0f)
        }
    }

    fun mouseWithin(): Boolean = with(Sketch) {
        val mousePoint = Point(mouseX.toFloat(), mouseY.toFloat())
        return (mousePoint.x >= pos.x &&
                mousePoint.x <= pos.x + cellSize.first &&
                mousePoint.y >= pos.y &&
                mousePoint.y <= pos.y + cellSize.second)
    }
}

