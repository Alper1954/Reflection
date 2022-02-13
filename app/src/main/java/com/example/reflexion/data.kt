package com.example.reflexion

import android.util.Log

annotation class A_PremiumCoffeeMachine
annotation class A_myVar
annotation class A_makeChocolate


fun myFunc1 (){}    //0 parameter, no return value
fun myFunc2 (par1:Int){}  //1 parameter, no return value
fun myFunc3 (par1:Int, par2:Int):Number{   //2 parameters, return value type Number
    return 5.0
}

val myProp1:String ="Init Value"  //val top level property
var myProp2:Int = 100  //var top level property


open class BaseCoffeeMachine(
    val price: Double,
    val color: String
){
    fun makeCoffee(){
        Log.i("alain", "Here is your coffee")
    }
}

@A_PremiumCoffeeMachine
class PremiumCoffeeMachine(
    price: Double,
    color: String,
    var option: String
):BaseCoffeeMachine(price,color){
    @A_myVar
    lateinit final var myVar: String
    fun makeCapuccino(){
        Log.i("alain", "Here is your capuccino")
    }
    @A_makeChocolate
    final fun makeChocolate(other:String){
        Log.i("alain", "Hi $other, here is your chocolate")
    }
}


class Person(val name: String, var age: Int) {
    fun present() = "I'm $name, and I'm $age years old"
    fun greet(other: String) = "Hi, $other, I'm $name"
}






