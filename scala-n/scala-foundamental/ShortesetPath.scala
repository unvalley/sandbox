// newしなくて良いやつだっけ？
case class Edge(from: Char, to: Char, distance: Int)

object ShortestPath {
    val vertexes = 'A' to 'G'
    val edges = Seq(
        Edge('A', 'B', 1),
        Edge('A', 'C', 8),
        Edge('B', 'A', 1),
        Edge('B', 'C', 6),
        Edge('B', 'D', 6),
        Edge('B', 'E', 6),
        Edge('C', 'A', 8),
        Edge('C', 'B', 6),
        Edge('C', 'D', 7),
        Edge('D', 'B', 6),
        Edge('D', 'C', 7),
        Edge('D', 'F', 2),
        Edge('E', 'B', 6),
        Edge('E', 'F', 6),
        Edge('E', 'G', 8),
        Edge('F', 'D', 2),
        Edge('F', 'E', 6),
        Edge('F', 'G', 5),
        Edge('G', 'E', 8),
        Edge('G', 'F', 5)
    )
    def solveByBellmanFord(start: Char, goal: Char): Unit = {
        // 各頂点までの距離の初期化
        var distances = vertexes.map(v => (v -> Int.MaxValue)).toMap
        distances = distances + (start -> 0)

        var isUpdated = true
        while (isUpdated) {
            isUpdated = false
            edges.foeach { e =>
                // 「辺の始点までの最短距離」が無限大ではない場合
                val notMaxValue = distances(e.from) != Int.MaxValue
                //「辺の終点までの最短距離」が「辺の始点までの最短距離」+ 「辺の距離」より大きい場合
                val untillLastPoint =  distances(e.to) > distances(e.from) + e.distance

                if(notMaxValue && untillLastPoint) {
                    distances = distances + (e.to -> (distances(e.from) + e.distance))
                    isUpdated = true
                }
            }
        }
        println(distances)
        println(distances(goal))
    }
}