package net.thetechstack

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

fun main() {
    Database.connect("jdbc:h2:mem:test", driver = "org.h2.Driver")
    transaction{
        addLogger(StdOutSqlLogger)
        SchemaUtils.create(Companies, Employees)
        //Insert
        Companies.insert {
            it[id]=1
            it[name] = "Apple"
            it[city] = "San Francisco"
        }
        Companies.insert {
            it[id]=2
            it[name] = "Amazon"
            it[city] = "Seattle"
        }

        Employees.insert { it[id]=1; it[name]="Bob"; it[age]=30; it[company]=1 }
        Employees.insert { it[id]=2; it[name]="Andrew"; it[age]=32; it[company]=2 }
        Employees.insert { it[id]=3; it[name]="Sam"; it[age]=35; it[company]=1 }
        Employees.insert { it[id]=4; it[name]="Alice"; it[age]=32; it[company]=2 }
        Employees.insert { it[id]=5; it[name]="David"; it[age]=29; it[company]=2 }

        //Select
        Employees.selectAll().forEach { println("$it") }
        Employees.select {Employees.id greater 3}
            .forEach { println(it[Employees.name]) }
        Employees.slice(Employees.name).select {Employees.name like "S%"}
            .forEach { println(it) }

        //Update
        Employees.update({Employees.id eq 5}){
            it[name] = "Dave"
        }

        //Inner join
        (Employees innerJoin Companies).select {
            Companies.id eq 2
        }.forEach { println("$it") }


        //Delete
        Employees.deleteWhere { Employees.name eq "Sam" }

        //Join tables
        SchemaUtils.drop (Companies, Employees)

    }
}

object Companies: Table(){
    val id = integer("id").primaryKey()
    val name = varchar("name", 50)
    val city = varchar("city", 25)
}
object Employees: Table(){
    val id = integer("id").primaryKey()
    val name = varchar("name", 50)
    val age = integer("age")
    val company = (integer("company") references Companies.id)
}


