package app

import java.util.*

fun <T> randomItem(iterable: Iterable<T>): T {
    val random = Random()
    val randomIndex = random.nextInt((iterable.count() - 1) - 0 + 1) + 0
    return iterable.elementAt(randomIndex)
}

fun <T> getACountOfItems(iterable: Iterable<T>, count:Int): Iterable<T>{
    val result = ArrayList<T>()
    while (result.count() != count) {
        val question = randomItem(iterable)
        if (result.indexOf(question) == -1) {
            result.add(question)
        }
    }

    return result
}

fun isValidDepartement(value: Int): Boolean {
    return value in 1..101
}

fun isValidRegion(value: Int): Boolean {
    return value in 1..27
}

fun isValidYear(value: Int): Boolean {
    return value in 1950..2020
}

var isDbInitialisationMode: Boolean? = true

fun isDbInitialisationMode(): Boolean {
    if (isDbInitialisationMode == null) {
        val loader = Thread.currentThread().contextClassLoader
        val props = Properties()
        val stream = loader.getResourceAsStream("application.properties")
        props.load(stream)
        isDbInitialisationMode = props.getProperty("spring.jpa.hibernate.ddl-auto") == "create"
        stream.close()
    }
    return isDbInitialisationMode!!
}