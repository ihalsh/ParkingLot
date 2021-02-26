package parking

const val CREATE = "create"
const val PARK = "park"
const val LEAVE = "leave"
const val STATUS = "status"
const val EXIT = "exit"
const val REG_BY_COLOR = "reg_by_color"
const val SPOT_BY_COLOR = "spot_by_color"
const val SPOT_BY_REG = "spot_by_reg"

class Parking(private val spots: MutableList<Spot> = mutableListOf()) {
    val isNotEmpty get() = spots.isNotEmpty()

    fun createSpots(qty: Int) {
        spots.clear()
        repeat(qty) { spots.add(Spot(it + 1)) }
        println("Created a parking lot with $qty spots.")
    }

    fun parkCar(command: List<String>) {
        val freeSpot: Spot? =
                spots.firstOrNull { it.isFree }.also { if (it == null) println("Sorry, parking lot is full.") }

        freeSpot?.let {
            it.car = Car(command[1], command[2])
            println("${it.car!!.color} car parked on the spot ${it.number}.")
        }
    }

    fun leaveCar(command: List<String>) {
        spots[command.intParam() - 1].apply {
            if (isFree) println("There is no car in the spot $number.")
            else {
                free()
                println("Spot $number is free.")
            }
        }
    }

    fun regByColor(command: List<String>) {
        spots.filter { !it.isFree && it.car!!.color.toUpperCase() == command.stringParam().toUpperCase() }.run {
            if (this.isEmpty()) println("No cars with color ${command.stringParam()} were found.") else
                println(this.joinToString(", ") { spot -> spot.car!!.number })
        }
    }

    fun spotByColor(command: List<String>) {
        spots.filter { !it.isFree && it.car!!.color.toUpperCase() == command.stringParam().toUpperCase() }.run {
            if (this.isEmpty()) println("No cars with color ${command.stringParam()} were found.") else
                println(this.joinToString(", ") { spot -> spot.number.toString() })
        }
    }

    fun spotByReg(command: List<String>) {
        spots.filter { !it.isFree && it.car!!.number == command.stringParam() }.run {
            if (this.isEmpty()) println("No cars with registration number ${command.stringParam()} were found.") else
                println(this.joinToString(", ") { spot -> spot.number.toString() })
        }
    }

    fun showStatus() = if (spots.all { it.isFree }) println("Parking lot is empty.")
    else spots.forEach {
        if (!it.isFree) println("${it.number} ${it.car!!.number} ${it.car!!.color}")
    }

    inner class Car(val number: String, val color: String)

    inner class Spot(val number: Int, var car: Car? = null) {
        val isFree get() = car == null
        fun free() { car = null }
    }
}

fun main() {
    Parking().apply {
        do {
            val command = readLine()!!.split(" ")
            if (command.first() == EXIT) continue
            if (isNotEmpty)
                when (command.first()) {
                    CREATE -> createSpots(command.intParam())
                    PARK -> parkCar(command)
                    LEAVE -> leaveCar(command)
                    REG_BY_COLOR -> regByColor(command)
                    SPOT_BY_COLOR -> spotByColor(command)
                    SPOT_BY_REG -> spotByReg(command)
                    STATUS -> showStatus()
                }
            else when (command.first()) {
                CREATE -> createSpots(command.intParam())
                else -> println("Sorry, parking lot is not created.")
            }
        } while (command.first() != EXIT)
    }
}

private fun List<String>.intParam(): Int = this[1].toInt()
private fun List<String>.stringParam(): String = this[1]

