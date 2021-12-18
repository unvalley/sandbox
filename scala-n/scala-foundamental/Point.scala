class Point(val x: Int, val y: Int) {
    override def toString = s"Point($x, $y)"
}

object Point {
    def apply(x: Int, y: Int): Point = new Point(x, y)
}

case class Point(val x: Double, val y: Double)