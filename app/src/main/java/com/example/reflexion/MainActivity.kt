package com.example.reflexion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.reflect.*
import kotlin.reflect.full.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

///////////////////////////////////// REFLECTION ON CLASSES //////////////////////////////////////
// Accès aux méta-données d'une classe

     //Accès à partir du nom de la classe
        val classeDef: KClass<PremiumCoffeeMachine> = PremiumCoffeeMachine::class

     //Accès à partir d'une instance de la classe
        val coffeeMachineC = PremiumCoffeeMachine(10000.0, "Brown", "option1")
        val classeDef2: KClass<out PremiumCoffeeMachine> = coffeeMachineC::class

//Accès aux informations des méta-données d'une classe

      // Lecture des différents attributs d'une classe
        /*
        Log.i("alain", "PremiumCoffeeMachine is sealed: " + classeDef.isSealed)
        Log.i("alain", "PremiumCoffeeMachine is abstract: " + classeDef.isAbstract)
        Log.i("alain", "PremiumCoffeeMachine is final: " + classeDef.isFinal)
        Log.i("alain", "PremiumCoffeeMachine is a companion object: " + classeDef.isCompanion)
        Log.i("alain", "PremiumCoffeeMachine is open: " + classeDef.isOpen)
        */


      // Accès aux propriétés de la classe

            // Liste des propriétés déclarées dans la classe uniquement
        val declaredMemberProperties = classeDef.declaredMemberProperties
        /* declaredMemberProperties.forEach{ Log.i("alain", it.name) } */

            // Liste des propriétés déclarées dans la classe et ses classes parentes
         val memberProperties = classeDef.memberProperties
        /* memberProperties.forEach{ Log.i("alain", it.name) } */

            //Recherche et lecture d'une propriété particulière
         val option = memberProperties.find{it.name == "option"}
        /*Log.i("alain", "property is " + option?.name)*/

         val valueOption = option?.get(coffeeMachineC)
        /*Log.i("alain", "value property option = " + valueOption.toString())*/


        //// des essais

        val memberPropertiesbis = classeDef2.memberProperties
        val optionbis = memberPropertiesbis.find{it.name == "option"}
        val valueOptionbis = optionbis?.getter?.call(coffeeMachineC)

        Log.i("alain", "value property option = " + valueOptionbis.toString())


        //// fin des essais



        //Accès aux fonctions de la classe?.

            // Liste des fonctions déclarées dans la classe uniquement
        val declaredMemberFunctions = classeDef.declaredMemberFunctions
        /* declaredMemberFunctions.forEach{ Log.i("alain", it.name) }*/

            //Liste des fonctions déclarées dans la classe et ses classes parentes
        val memberFunctions = classeDef.memberFunctions
        //memberFunctions.forEach{ Log.i("alain", it.name) }

        //Recherche et invocation d'une fonction particulière
        //val makeChocolate = memberFunctions.find{it.name == "makeChocolate"}
        val makeChocolate = memberFunctions.single{it.name == "makeChocolate"}
        /*Log.i("alain", "function is " + makeChocolate?.name)*/

        makeChocolate.call(coffeeMachineC, "Alicia")


        //Accès aux annotations de la classe

            // Liste des annotations attachées à la classe
        val annotations = classeDef.annotations
        /*annotations.forEach { Log.i("alain", it.toString()) }*/


//////////////////////// REFLECTION ON PROPERTIES ///////////////////////////////

//Accès aux méta-données d'une propriété

        val CoffeeMachineP = PremiumCoffeeMachine(500.0, "black", "myOption")

     //A partir du nom de la propriété et celui de sa classe d'appartenance
         val option1 = PremiumCoffeeMachine::option

     //A partir du nom de la propriété et celui d'une instance de sa classe
        val option2 = CoffeeMachineP::option


//Accès aux informations des méta-données d'une propriété

    //Accès aux différents attributs d'une propriété

        val myVar = PremiumCoffeeMachine::myVar

        Log.i("alain", "myVar property is abstract: " + myVar.isAbstract)
        Log.i("alain", "myVar property is final: " + myVar.isFinal)
        Log.i("alain", "myVar property is open: " + myVar.isOpen)
        Log.i("alain", "myVar property is const: " + myVar.isConst)
        Log.i("alain", "myVar property is lateinit: " + myVar.isLateinit)
        Log.i("alain", "myVar property has a A_option annotation: " + myVar.hasAnnotation<A_myVar>())
        Log.i("alain", "myVar property has a A_makeChocolate annotation: " + myVar.hasAnnotation<A_makeChocolate>())


    //Accès à la liste des annotations attachées à une propriété

        val annotationsP = myVar.annotations
        annotationsP.forEach { Log.i("alain", it.toString()) }


//Lecture et modification de la valeur d'une propriété

    //Lecture de la valeur d'une propriété

        val myOption1 = option1.get(CoffeeMachineP)
        Log.i("alain", "valeur de la propriété option :" + myOption1)

        val myOption2 = option2.get()
        Log.i("alain", "valeur de la propriété option :" + myOption2)


    //Modification de la valeur d'une propriété

        option1.set(CoffeeMachineP, "newOptionValue1")
        Log.i("alain", "nouvelle valeur de la propriété option :" + option1.get(CoffeeMachineP))

        option2.set("newOptionValue2")
        Log.i("alain", "nouvelle valeur de la propriété option :" + option2.get())



//////////////////////// REFLECTION ON FUNCTIONS ///////////////////////////////

//Accès aux méta-données d'une fonction

        val coffeeMachineF = PremiumCoffeeMachine(10000.0, "Red", "myOption")

     //A partir du nom de la fonction et celui de sa classe d'appartenance
        val makeCapuccino1 = PremiumCoffeeMachine::makeCapuccino
        val makeChocolate1  = PremiumCoffeeMachine::makeChocolate

      //A partir du nom de la fonction et d'une instance de sa classe
        val makeCapuccino2 = coffeeMachineF::makeCapuccino
        val makeChocolate2= coffeeMachineF::makeChocolate


//Accès aux informations des méta-données d'une fonction

     //Accès aux différents attributs d'une fonction
        /*
        Log.i("alain", "function makeChocolate is abstract: " + makeChocolate1.isAbstract)
        Log.i("alain", "function makeChocolate is external: " + makeChocolate1.isExternal)
        Log.i("alain", "function makeChocolate is final: " + makeChocolate1.isFinal)
        Log.i("alain", "function makeChocolate is open: " + makeChocolate1.isOpen)
        Log.i("alain", "function makeChocolate is suspend: " + makeChocolate1.isSuspend)
        Log.i("alain", "function makeChocolate has A_PremiumCoffeeMachine annotation: " + makeChocolate1.hasAnnotation<A_PremiumCoffeeMachine>())
        Log.i("alain", "function makeChocolate has A_makeChocolate annotation: " + makeChocolate1.hasAnnotation<A_makeChocolate>())
        */


     //Accès à la liste des annotations attachées à une fonction

        val annotationsF = makeChocolate1.annotations
        annotationsF.forEach { Log.i("alain", it.toString()) }


     //Accès à la liste des paramètres d'une fonction

        val parameters = makeChocolate1.parameters
        parameters.forEach { Log.i("alain", it.toString()) }

//Invocation des fonctions

        makeCapuccino1(coffeeMachineF)
        makeChocolate1(coffeeMachineF, "alain")

        makeCapuccino2()
        makeChocolate2("alain")


//////////////////////// TOP LEVEL FUNCTIONS AND PROPERTIES ///////////////////////////////

 //Accès aux méta-données d'une fonction "top level"

        val myFunc1_metaData = ::myFunc1

        val myFunc2_metaData = ::myFunc2

        val myFunc3_metaData = ::myFunc3

  //Accès aux méta-données d'une propriété "top level"

        val myProp1_metaData = ::myProp1

        val myProp2_metaData = ::myProp2


/////////////////////////JAVA REFLECTION///////////////////////////////////////////////////////

        val classeDef_java = PremiumCoffeeMachine::class.java
        val myFields = classeDef_java.fields
        myFields.forEach { Log.i("alain", it.toString()) }

        val myMethods = classeDef_java.declaredMethods
        myMethods.forEach { Log.i("alain", it.toString()) }

        val coffeeMachineJ = PremiumCoffeeMachine(10000.0, "Brown", "option1")
        val classeDef_Java2 = coffeeMachineJ.javaClass




    }






}