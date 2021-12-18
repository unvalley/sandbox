import scala.math.sqrt

object Factorization extends App {
    val target = 24
    val maxDivisor = sqrt(target).toInt

    def factorizationRec(num: Int, divisor: Int, acc: Map[Int, Int]): Map[Int, Int] = {
        if (divisor > maxDivisor) {
            // 「割る数」が「割る数の最大値」よりも大きい場合、現在の結果に現在の「対象」を加えたものを答えとする
            if(num == 1) acc else acc + (num -> 1)
        } else if (num%divisor == 0) {
            // 「対象」が「割る数」で割り切れる場合、「結果」に「割る数」で割り切れた回数を 1 足して再度関数を実行する
            val nextAcc = acc + (divisor -> (acc.getOrElse(divisor, 0) + 1))
            factorizationRec(num/divisor,divisor, nextAcc)
        } else {
            // 対象」が割り切れない場合、「割る数」を 1 足して再度関数を実行する
            factorizationRec(num, divisor+1, acc)
        }
    }

    println(factorizationRec(target, 2, Map()))
}