
val v1: Option[Int] = Some(2)
val v2: Option[Int] = Some(3)
val v3: Option[Int] = Some(5)
val v4: Option[Int] = Some(7)
val v5: Option[Int] = Some(11)

for {
    i1 <- v1
    i2 <- v2
    i3 <- v3
    i4 <- v4
    i5 <- v5
} yield i1 * i2 * i3 * i4 * i5



val f1: Option[Int => Int] = Some((x) => x * 2)
val f2: Option[Int => Int] = Some((x) => x + 10)
val f3: Option[Int => Int] = Some((x) => x / 3)

val result = 
    for {   i1 <- f1
            i2 <- f2
            i3 <- f3 } yield i1.andThen(i2).andThen(i3)

result.map(_.apply(15))






