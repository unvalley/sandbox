object PatternMatch extends App {
    def printRandomChars(): Unit = {
        for (i <- 1 to 1000) {
            val chars: Seq[Char] = new scala.util.Random(new java.security.SecureRandom()).alphanumeric.take(5).toList
            val result = chars match {
               case Seq(a, b, c, d, _) => Seq(a,b,c,d,a)
            }
            println(result)
        }
    }

    def last(seq: Seq[Int]): Int = {
        seq match {
            case Seq(x) => return x
            case x::xs => last(xs)
        }
    }

    def reverse(seq: Seq[Int]): Seq[Int] = {
        seq match {
            // シーケンスは内部的には List という型
            // List 型は `先頭の要素::先頭の要素を取り除いたList` というマッチングを行うことができる
            case Seq(x) => Seq(x)
            case x::xs => reverse(xs) :+ x
        }
    }
}