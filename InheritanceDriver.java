/* Nicholas Vadivelu
 * 5 November 2015
 * Inheritance Assignment
 */

/* Criteria:
 * Inheritance of data fields, constructors and methods (implicit and using the super keyword). <- found in all sub classes
 * Valid assignments of object variables and class casting. <- found in main method 
 * Visibility of data fields (public, package, private, protected) <- found in RCToys class
 * Overriding of class methods <- toString() was overwritten in RCToy subclasses
 * Polymorphism <- overwritten methods in Cars
 * Abstract classes <- abstract class RCToy with abstract methods
*/

import java.util.Scanner; //import the scanner class to create Scanner objects

public class InheritanceDriver //this class will be used to test all of the other classes
{
  private static Scanner scan; //declare scanner object for user input

  public static void main (String []  args) //need a main method to run the program
  {
    scan = new Scanner(System.in); //initialize scanner
    System.out.println("What do you want to call the plane?"); //prompting user for name of the airplane
    RCToy plane = new Plane(scan.next(), 5); //declaring a new plane object that has a battery capacity of 5 
    System.out.println("What do you want to call the truck?"); //prompting uesr for the nae of the trick
    Car truck = new PickupTruck (scan.next(), 7); //declaring new truck that has a battery capacity of 7
    PickupTruck newtruck = (PickupTruck)truck; //casting the car into the truck
    Plane airplane = new Plane((Plane)plane); //using copy costructor to make new plane
    
    System.out.println("\nThere are " + RCToy.getNumToys() + " toys in total.");//getNumToys() is a static method, and therefore must be accessed by the class
    //NOTICE HOW there are only 3 RCToys because when you make newtruck = truck,you are copying the reference, not making a new object
    
    newtruck.isOn = true; //turning on the truck using the public boolean int he class
    newtruck.charge();
    newtruck.charge(); //charging the truck twice
    newtruck.headlights(true); //turning the headlights of the newtruck on
    System.out.println("\nHow many items do you want to load onto " + newtruck + " initially?"); //prompts the user to ask how many items they want to enter
    newtruck.load(scan.nextInt());  //loads that many items
    System.out.println("How many items do you want to load onto " + newtruck + " this time??"); //prompts the user to ask how many items they want to enter the second time
    newtruck.load(scan.nextInt());//loads that many items onto the truck
    newtruck.driveOffroad(); //drive the truck offroad which uses 2 battery life
    newtruck.reverse();//try to reverse truck, but it will fail because not enough battery
    scan.close(); //closing scanner to prevent resource leak
    
    
    airplane.up(); //try to fly the airplane up, won't work because isn't on
    airplane.charge();
    airplane.charge();
    airplane.charge(); //charging the airplane for 3 uses
    airplane.isOn = true; //turn on airplane
    airplane.forward(); //fly the plane forward
    airplane.up(); //fly the airplane up
    System.out.println(airplane + " has " + airplane.getBatteryLeft() + " out of " + airplane.getBatteryLife() + " battery left"); //displays how much battery the plane has left
    airplane.isOn = false; //turning off the airplane
    
    System.out.println("\nThank you for playing! Have a nice day!"); //thanks the user for playing
  }
}

abstract class RCToy //this is an abstract class, meaning you cannot create instances of it. this is useful because it allows you to create subclasses with similar properties
{
  protected int batteryLife, batteryLeft; //all RC toys (in this situation) are battery powered, and so these two variables store information about that. 
//the protected keyword indicates that this variable can be accessed within this class and all subclasses, but not outside of the class
  protected String name; //each RC toy must have a name so they can be referred to in output
  private static int numToys = 0; // the private keyword means this data field can only be accessed in this class (NOT subclasses)
  //the static keyword means that this int is shared between all instances of the RCToy class (class variable)
  //this variable is used to keep track of how many rc toys there are.
  public boolean isOn; //public meaning can be accessed from any class
  //this data field says whether the toy is on or off
  
  
  
  public RCToy(String name, int battery) //this is a constructor. although instances of the RCToy class cannot be created, sub-classes can use the super() keyword to access it, which leads to less code in all
  { //the parameters are the name n as a String and the batteryLife battery as an integer
    this.name = name; //name becomes the name that is accepted in the parameters
    // the this keyword is used to tell the compiler that I am referring to the instance variable, not the variable in this scope
    batteryLife = battery; //batteryLife becomes battery
    batteryLeft = 0; //the toy will always start out with no charge, and therefore the battery left would be 0
    numToys++; //this is used to increment the static integer numToys to keep count of how many RCToys there are
    isOn = false; //toys are off by default
  }
  
  public RCToy () //this is a default constructor, used to create an object with default values. it is useful when you want to create an object whose values you will add later
  {
    batteryLife = 0;
    batteryLeft = 0;
    name = ""; //these 3 variables are being assigned their default value
    numToys++; //numToys must still be incremented because another RCToy object is being created
    isOn = false; //toys are off by default
  }
  
  public RCToy(RCToy r) //this is a copy constructor, used to create an object that is the same as another
  { //this is useful because if you just made two objects equal, e.g. RCToy a = b, you would be copying the references to the same object instead of making a new object, which can cause problems when modifying the objects
    batteryLife = r.batteryLife;
    batteryLeft = r.batteryLeft;
    name = r.name; //these three data fields are being made equal to the object passed as a parameter.
    numToys++; //again, numToys must be incremented since another object is being created
    isOn = false; //even if this is the exact same as r, it still starts out as off
  }
  
  public int getBatteryLeft() { return batteryLeft; } //this is a accessor/ getter method that returns batteryLeft
  //the public ketword means this method can be accessed outside and inside of this class (anywhere)
  
  public int getChargeTime() { return (batteryLife - batteryLeft); } //this returns how much charge is needed.
  
  public void charge() 
  { 
    if (batteryLeft < batteryLife)
      batteryLeft++; 
  } //charges battery so you can use it, ensures that the battery left doesn't surpass battery life
  
  public int getBatteryLife() { return (batteryLife); }//this is a accessor/ getter method that returns batteryLife
  
  protected boolean canMove () //checks if the vehicle can move.  
  {
    //this if/ else if ensures that the toy has battery. used when the user wants to play and calls a movement function
    if (!isOn)
      System.out.println("Please turn " + toString() + "on before playing!");
    else if (batteryLeft <= 0)
    {
      System.out.println(toString() + "'s battery is empty! Please charge your toy.");
      isOn = false;
    }
    
    return (batteryLeft > 0 && isOn); //returns if it can move
  }
  
  public void setName (String name) { this.name = name; } //setter/ mutator method to change the name of the RC Toy
  
  public static int getNumToys() {return numToys; }// getter/ accessor to get number of toys that exist
// must be static because it is global, meaning for the whole class not for one isntance
 
  public String toString() { return name; } //used to represent a given instance of this class as a strng (can be called implicitly)
  
  public abstract void forward(); //this is an abstract method which is used to move forward, meaning that it is declared but not defined. all subclasses MUST redefine this method

  public abstract void left(); //this is an abstract method which is used to turn left, meaning that it is declared but not defined. all subclasses MUST redefine this method

  public abstract void right(); //this is an abstract method which is used to turn right, meaning that it is declared but not defined. all subclasses MUST redefine this method
}

class Car extends RCToy //this is a subclass of RCToy, meaning it inherits all of the methods and instance variables, and can add additional funcitionality
{
  protected boolean headlightsOn; //this data field determines if the headlights are on
  
  public Car (String n, int battery) //this is a constructor to create a car instance
  {
    super(n, battery); //uses constructor in RCToys class 
    headlightsOn = false; //sets headlights on to false
  }
  
  public Car () //this is a default constructor, which is useful when yyou want to set the values in the datafields later
  {
    super(); //uses constructor in RCToys class 
    headlightsOn = false; //sets headlights on to false
  }
  
  public Car (Car c) //this is a copy constructor to create a car instance that is the same as another (if you use =, you are copying reference not values)
  {
    super(c); //uses constructor in RCToys class 
    headlightsOn = false; //sets headlights on to false
  }
  
  public void headlights(boolean change){ headlightsOn = change; } //setter/ mutator to turn headlights on and off
  
  public String toString() //overriding toString method to make it specifically for this class. this is a form of polymorphism
  {    return super.toString() + " (Car)"; }//adding the ending "Car" makes it clear what kind of toy this is
  
  public void forward() // redefining abstract method in RCToys class 
  { 
    if (canMove()) //checks if vehicle can move
    {
      System.out.println(toString() + "is driving forward!");// indicates that the car is moving foward
      batteryLeft--; //decreases charge left in battery
    }
  }
  
  public void left() // redefining abstract method in RCToys class 
  { 
    if (canMove()) //checks if vehicle can move
    {
      System.out.println(toString() + "is turning left!");// indicates that the car is turning left
      batteryLeft--; //decreases charge left in battery
    }
  }
  
  public void right() // redefining abstract method in RCToys class 
  { 
    if (canMove()) //checks if vehicle can move
    {
      System.out.println(toString() + "is turning right!");// indicates that the car is turning right
      batteryLeft--; //decreases charge left in battery
    }
  }
  
  public void reverse () //car can go backward
  { 
    if (canMove()) //checks if vehicle can move
    {
      System.out.println(toString() + "is reversing turning right!");// indicates that the car is backing up
      batteryLeft--; //decreases charge left in battery
    }
  }
}

class PickupTruck extends Car  //this is a subclass of Car (and thus, RCToy as well) , meaning it inherits all of the methods and instance variables, and can add additional funcitionality
{
  private int items; //keeps track of how much the truck is carrying 
  
  public PickupTruck (String n, int battery) //this is a constructor to create a pickup truck instance
  {
    super(n, battery); //uses constructor in Car class 
    items = 0; //trucks start out with no items
  }
  
  public PickupTruck () //this is a default constructor, which is useful when yyou want to set the values in the datafields later
  {
    super(); //uses default constructor in Car class 
    items = 0; //trucks start out with no items
  }
  
  public PickupTruck (PickupTruck p) //this is a copy constructor to create a pickup truck instance that is the same as another (if you use =, you are copying reference not values)
  {
    super(p); //uses copy constructor in Car class 
    items = 0; //trucks start out with no items
  }
  
  public String toString() { return name + " (Pickup Truck)"; } //overriding car toString method, a form of polymorphism
  
  public void load (int items) //loading items onto truck
  {
    this.items += items; //adding items to load
    System.out.println("You have a total of " + this.items + " items after loading " + items + " items."); //tells user that items have been loaded
  }
  
  public void driveOffroad() //driving offroad
  {
    batteryLeft -= 2; //driving offroad consumesmore battery
    System.out.println(toString() + " is driving offroad!"); //shows what it's doing
  }
}

class Plane extends RCToy  //this is a subclass of RCToy, meaning it inherits all of the methods and instance variables, and can add additional funcitionality
{  
  public Plane (String n, int battery) //this is a constructor to create a Plane instance
  { super(n, battery); }//uses constructor in RCToys class 
  
  public Plane () //this is a default constructor, which is useful when yyou want to set the values in the datafields later
  { super(); } //uses constructor in RCToys class  
  
  public Plane (Plane p) //this is a copy constructor to create a Plane instance that is the same as another (if you use =, you are copying reference not values)
  { super(p); }//uses constructor in RCToys class 

  public String toString() //overriding toString method to make it specifically for this class. this is a form of polymorphism
  {    return super.toString() + " (Plane)"; }//adding the ending "Plane" makes it clear what kind of toy this is
  
  public void forward() // redefining abstract method in RCToys class 
  { 
    if (canMove()) //checks if vehicle can move
    {
      System.out.println(toString() + " is flying forward!");// indicates that the Plane is moving foward
      batteryLeft--; //decreases charge left in battery
    }
  }
  
  public void left() // redefining abstract method in RCToys class 
  { 
    if (canMove()) //checks if vehicle can move
    {
      System.out.println(toString() + " is banking left!");// indicates that the Plane is turning left
      batteryLeft--; //decreases charge left in battery
    }
  }
  
  public void right() // redefining abstract method in RCToys class 
  { 
    if (canMove()) //checks if vehicle can move
    {
      System.out.println(toString() + " is banking right!");// indicates that the Plane is turning right
      batteryLeft--; //decreases charge left in battery
    }
  }
  
  public void up() //planes can fly upwards
  { 
    if (canMove()) //checks if vehicle can move
    {
      System.out.println(toString() + " is tilting up!");// indicates that the Plane is tilting up
      batteryLeft--; //decreases charge left in battery
    }
  }
 
  public void down() //planes can fly downwards
  { 
    if (canMove()) //checks if vehicle can move
    {
      System.out.println(toString() + " is tilting down!");// indicates that the Plane is tilting down
      batteryLeft--; //decreases charge left in battery
    }
  }
}
