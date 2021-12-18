object QuadNumberPrinter extends App {
    private var counter = 0

    def next(): Int = synchronized {
        counter = counter + 1
        counter
    }

    for (i <- 1 to 4) {
        new Thread(() => for(j <- 1 to 100000)  println(s"thread ${i}: ${j}")).start()
    }
}

object TenThousandNamePrinter extends App {
    for (i <- 1 to 10000) {
        new Thread(() => {
            Thread.sleep(1000)
            println(Thread.currentThread().getName)
        }).start()
    }
}

object DeadLock extends App {
    var now: Long = 0L

    val threadA = new Thread(() => synchronized {
        Thread.sleep(1000)
        now = System.currentTimeMillis()
    })
    val threadB = new Thread(() => synchronized {
        while (now == 0L) {
            Thread.sleep(1000)
        }
        print(now)
    })

    threadA.start()
    threadB.start()
}