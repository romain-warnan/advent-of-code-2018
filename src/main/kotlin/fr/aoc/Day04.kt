package fr.aoc

import java.io.File
import java.time.LocalDateTime
import kotlin.streams.toList

class Day04 {

    private val regexBeginsShift = Regex("\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)\\] Guard #(\\d+) begins shift")
    private val regexFallsAsleep = Regex("\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)\\] falls asleep")
    private val regexWakesUp = Regex("\\[(\\d+)-(\\d+)-(\\d+) (\\d+):(\\d+)\\] wakes up")

    fun part1(path: String): Int {
        val records = records(path)
        val guards =  guards(records)
        val guard = guards.maxBy { it.totalTimeAsleep() }
        val a = guard!!.id
        val b = guard.minuteMostAsleep()
        return a * b
    }

    private fun guards(records: List<Record>): List<Guard> {
        val guards = mutableMapOf<Int, Guard>()
        var guard = Guard()
        val times = mutableListOf<Int>()
        for (record in records) {
            if(record.type == Record.Type.BEGINS_SHIFT) {
                guard.shifts.add(shift(times))
                val id = record.guardId
                guard = guards.getOrPut(id) { Guard(id) }
                times.removeIf {true}
            }
            else{
                times.add(record.date.minute)
            }
        }
        return guards.values.toList()
    }

    private fun records(path: String) = File(path).bufferedReader()
        .lines()
        .map { record(it) }
        .sorted()
        .toList()

    fun part2(path: String): Int {
        return -2
    }

    private fun record(line: String): Record {
        if(regexBeginsShift.matches(line)) {
            val (year, month, day, hour, minute, id) = regexBeginsShift.matchEntire(line)!!.destructured
            val date = date(year, month, day, hour, minute)
            return Record(date, Record.Type.BEGINS_SHIFT, id.toInt())
        }
        if(regexFallsAsleep.matches(line)) {
            val (year, month, day, hour, minute) = regexFallsAsleep.matchEntire(line)!!.destructured
            val date = date(year, month, day, hour, minute)
            return Record(date, Record.Type.FALLS_ASLEEP)
        }
        val (year, month, day, hour, minute) = regexWakesUp.matchEntire(line)!!.destructured
        val date = date(year, month, day, hour, minute)
        return Record(date, Record.Type.WAKES_UP)
    }

    private fun date(year: String, month: String, day: String, hour: String, minute: String) = LocalDateTime.of(year.toInt(), month.toInt(), day.toInt(), hour.toInt(), minute.toInt())

    private fun shift(times: List<Int>): Shift {
        val shift = Shift()
        var sleeping = false
        for (time in 0..59) {
            if(time in times) sleeping = !sleeping
            shift.minutes += Minute(time, sleeping)
        }
        return shift
    }
}

class Record(val date: LocalDateTime, val type: Type, val guardId: Int = 0): Comparable<Record> {

    override fun compareTo(other: Record) = this.date.compareTo(other.date)

    enum class Type {
        BEGINS_SHIFT, WAKES_UP, FALLS_ASLEEP
    }
}

class Minute(val time: Int, val sleeping: Boolean)

class Shift {
    val minutes = mutableListOf<Minute>()
}

class Guard(val id: Int = 0) {

    val shifts = mutableListOf<Shift>()

    fun totalTimeAsleep() = shifts
        .flatMap { it.minutes }
        .count { it.sleeping }

    fun minuteMostAsleep() = shifts.flatMap { it.minutes }
        .filter { it.sleeping }
        .groupingBy { it.time }
        .eachCount()
        .maxBy { it.value }!!.key
}