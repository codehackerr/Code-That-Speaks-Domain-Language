### Status
[![Build Status](https://api.travis-ci.org/codehackerr/JGoL.png)](https://api.travis-ci.org/codehackerr/JGoL.png)

### Code that speaks domain language

```java
/** Domain Rule */
   
  assertThat(live_cell_with_less_than_two_two_live_neighbours, becomes_dead_in(next_generation));

/** System Behavior */
  a(BEEHIVE).remains_unchanged().after(1).generations();
``` 

Ever felt like understanding code is like trying to translate between two different languages? You're not alone! The code world and the real-world problem it's supposed to solve often seem worlds apart. It's like trying to fit a square peg into a round hole.

To make this connection work, we rely on some key tricks. First, there's the art of naming things right—variables, objects, methods—they all need names that speak the language of the problem we're solving.
Then, there's the structure—classes, modules, and packages—these help us organize our code neatly resembling domain concepts.

But here's the catch: all these techniques only get us so far, especially when it comes to capturing the intricate behaviors and rules of the real-world problem we're tackling.

That's where the magic happens!
We can add a layer of what we call "[Domain-Specific Language](https://martinfowler.com/dsl.html#:~:text=A%20Domain%2DSpecific%20Language%20(DSL,as%20computing%20has%20been%20done.)" (DSL) on top of our code. Think of it as a special dialect that bridges the gap between our code and the real-world problem.
Another technique is clever encapsulation of intent in language structure.

But wait, it gets even cooler! We're going to dive into a lightweight approach here.
We're talking about using Internal DSLs with Fluent Interfaces.
It's like speaking the language of your code in a way that's so smooth and intuitive, it feels like having a conversation with your computer.
Also I will be using custom [Hamcrest matchers](https://hamcrest.org/JavaHamcrest/) to create domain vocabulary .

Let's explore this exciting world of DSLs and Fluent Interfaces together!

We will solve this problem to demonstrate the techniques. [Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

Here is some Domain Rules Copied from the above link and corresponding code snippets.

`Any live cell with fewer than two live neighbours dies, as if by underpopulation.`
```java 
assertThat(live_cell_with_less_than_two_two_live_neighbours, becomes_dead_in(next_generation));
```
`Any live cell with two or three live neighbours lives on to the next generation.`
```java
assertThat(live_cell_with_two_live_neighbours, lives_on_to(new_generation));
```

More examples of domain specific matchers see `GameOfLifeRulesTest.java`

```Java
/** A functional test Using Fluent Interfaces*/
a(BLOCK).remains_unchanged().after(1).generations();
```

More examples of Internal DSL implementation `src/functional-test/java/com/gol/functional/dsl`

While both these examples are implemented in tests, the concepts are equally applicable in production code too.
