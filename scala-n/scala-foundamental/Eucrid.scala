object Euclid extends App {
    def greatestCommonDivisor(a:Int, b:Int): Int = {
        if (a==0) return b
        if (b==0) return a
        return greatestCommonDivisor(b, a%b)
    }
    println(greatestCommonDivisor(36, 24))
}