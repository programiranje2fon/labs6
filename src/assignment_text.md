# Lab exercise 6

## Task 1
(to be done by the tutor together with students)

Create class **HomeAppliance** in package **task1** with:

* private attribute **turnedOn** with value TRUE if the home appliance is turned on, and FALSE otherwise.

* private attribute **brandAndModel** of type String.

* public constructor that receives two input parameters - a boolean and a String - and assigns their values to the attributes **turnedOn** and **brandAndModel**.

* public method **turnOnOrOff** that turns the appliance on if it is off, and vice versa (if it is on, turns it off).

* redefined public method **toString** of the Object class. The method returns a String with all the data about the home appliance in the following format: "BRAND AND MODEL: #### TURNED ON: ####".

* redefined public method **equals** of the Object class. The method first checks if the input value is an object of type **HomeAppliance**, and if it is not, the method returns FALSE. The method returns TRUE if brand and model of the appliance are the same as brand and model of the input appliance; otherwise, it returns FALSE.  

Create class **TestHomeAppliance** in package **task1.test** that creates two objects of the **HomeAppliance** class. Both appliances are initially tuned on and their brands and models are "Laptop Acer A315" and "Canon DSLR EOS 5D", respectively. On the first object, call the **turnOnOrOff** method two times, and then call the **toString** and **equals** methods; pass the 2nd object to the **equals**  method as as its input parameter.  

In package **task1**, create class **TV** that extends the **HomeAppliance** class and has:

* private attribute **program** that stands for the number of the current program on the TV.

* public constructor that receives three input parameters: a boolean, a String, and an integer. The first two input parameters are used to initialize the attributes **turnedOn** and **brandAndModel** of the HomeAppliance. The 3rd parameter is used to initialize the **program** attribute; if the value of this parameter is less than 1 or greater than 40, "ERROR" is printed on the console and **program** is set to 1; otherwise, **program** is set to the input value.  

* public method **changeProgram** that receives as its input parameter a new value for the **program** attribute. Program can only take values between 1 and 40. 

* redefined public method **toString** of the Object class. The method returns a String with all the data about the TV in the following format: "BRAND AND MODEL: #### TURNED ON: #### PROGRAM: ####".

In the existing **TestHomeAppliance** class, create two objects of the **TV** class that are initially turned on, have the same brand and model ("Samsung UE40") and are showing the same channel (1). On the 1st TV object, call the **turnOnOrOff** method, and print the values returned by the **toString** and **equals** methods; the **equals** methods receives the 2nd object as the input parameter. 



## Task 2
(students work on their own)

In package **task2**, create class **Radio** that extends the **HomeAppliance** class and has:

* private attribute **frequency** that represents the current frequency on the radio (e.g. 87.5)

* public constructor that receives three input parameters: a boolean, a String, and a double. The first two input parameters are used to initialize the attributes **turnedOn** and **brandAndModel** of the HomeAppliance. The 3rd parameter is used to initialize the **frequency** attribute; if the value of this parameter is not in the [87.5 - 107.9] range, "ERROR" is printed on the console and **frequency** is set to 87.5; otherwise, **frequency** is set to the input value.

* public method **getFrequency** that returns the current value of the **frequency** attribute.

* public method **setFrequency** that receives as its input parameter a new value for the **frequency** attribute. If the parameter value is not in the [87.5 - 107.9] range, "ERROR" is printed on the console; otherwise, **frequency** is set to the input value.

* redefined public method **toString** of the Object class. The method returns a String with the data about the radio in the following format: "RADIO FREQUENCY: #### Mhz".

Create class **TestRadio** in package **task2** that creates one object of the **Radio** class and calls all its methods.