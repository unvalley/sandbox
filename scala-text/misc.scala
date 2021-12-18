
class Point3D(val x: Int, val y: Int, val z: Int)

abstract class Shape {
	def area: Double
}

class Rectangle(val w: Double, val h: Double) extends Shape {
	override def area: Double = {
		w*h
	}
}

class Circle(val r: Double) extends Shape {
	override def area: Double = r * r * math.Pi
}


class Main {
	var shape: Shape = new Rectangle (10.0, 20.0)
	println(shape.area)
	shape = new Circle(2.0)
	println(shape.area)


	val age: Int = 5
	val isSchoolStarted: Boolean = false
	if (1 <= age && age <= 6 && !isSchoolStarted) {
		println("幼児だお")
	} else {
		println("幼児じゃないお")
	}

	val pitagorath = 
	for (a <- 1 to 1000; b <- 1 to 1000; c <- 1 to 1000 if a*a == b*b + c*c) {
		println((a,b,c))
	}

	val lst = List("A", "B", "C")
	lst match {
		case List("A", b, c) =>
			println("b=" + b)
			println("c=" + c)
		case _ =>
			println("nothing")
	}

	for(i <- 1 to 1000) {
		val rnd = new scala.util.Random(new java.security.SecureRandom()).alphanumeric.take(5).toList
		val res = rnd match {
			case List(a,b,c,d,_) =>  List(a,b,c,d,a).mkString
		}
		println(res)
	}

}


