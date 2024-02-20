### Status
[![Build Status](https://api.travis-ci.org/codehackerr/JGoL.png)](https://api.travis-ci.org/codehackerr/JGoL.png)

### Code that speaks domain language

#### Disclaimer: Example follows a more puristic approach for demonstration. In big and complex projects, practical take away is to create reusable code constructs closely mirroring domain terminology.

Show me an example.

```java
// Domain Rule: Any live cell with fewer than two live neighbours dies, as if by underpopulation. 
        assertThat( live_cell_with_fewer_than_two_live_neighbours, dies_of_underpopulation(next_generation));

/** System Behavior */
  a(BEEHIVE).remains_unchanged().after(1).generations();
``` 

Lets spent some time on the whys and whats before we get back to the above code implementations.

Ever felt like understanding code is like trying to translate between two different languages? You're not alone! The code world and the real-world problem it's supposed to solve often seem worlds apart. It's like trying to fit a square peg into a round hole.

To make this connection work, we rely on some key tricks. First, there's the art of naming things right—variables, objects, methods—they all need names that speak the language of the problem we're solving.
Then, there's the structure—classes, modules, and packages—these help us organize our code neatly resembling domain concepts.

But here's the catch: all these techniques only get us so far, especially when it comes to capturing the intricate behaviors and rules of the real-world problem we're tackling.

That's where the magic happens!
We can add a layer of what we call [Domain Specific Language](https://martinfowler.com/dsl.html#:~:text=A%20Domain%2DSpecific%20Language%20\(DSL,as%20computing%20has%20been%20done.\)) (DSL) on top of our code. Think of it as a special dialect that bridges the gap between our code and the real-world problem.
Another technique is clever encapsulation of domain behavior(intent) in language structure.

In this sample implementation, I used Internal DSLs with Fluent Interfaces 
and [Hamcrest matchers](https://hamcrest.org/JavaHamcrest/) to create domain vocabulary and glue them together.

Let's explore this a bit together!

We will solve this problem to demonstrate the techniques. [Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

Here are some Domain Rules copied from the above link and corresponding code snippets.

`Any live cell with fewer than two live neighbours dies, as if by underpopulation.`
```java 
assertThat( live_cell_with_fewer_than_two_live_neighbours, dies_of_underpopulation(next_generation));
```
`Any live cell with two or three live neighbours lives on to the next generation.`
```java
assertThat(live_cell_with_two_live_neighbours, lives_on_to(nex_generation));
assertThat(live_cell_with_three_live_neighbours, lives_on_to(next_generation));
```

More examples of domain specific matchers see `GameOfLifeRulesTest.java`


```Java
/** A system behavior specification using Fluent Interfaces*/
a(BLOCK).remains_unchanged().after(1).generations();
```

More examples of Internal DSL implementation `src/functional-test/java/com/gol/functional/dsl`

While both these examples are implemented in tests, the concepts are equally applicable in production code too.

### How do I adopt this ?
#### Naming
- Name variables, classes, methods using domain vocabulary.
- Biggest ROI and applicable irrespective of language context.
- Need to be conscious to use the domain terminology. It's easy to use interpretations than the actual domain. Eg: I used overcrowding for overpopulation
#### Create Intents
- Wrap domain behaviors and rules as intents.
- Hamcrest Matcher is an excellent example of this. See `CellMatchers.java`
- Simple enough, with a little bit of practice.
- More effort than `Naming` and `Aliasing`. High ROI because of reuse and code clarity.
#### Fluent Interfaces
- Use fluent interfaces to build a sentence structure by gluing together the domain concepts.
- `Deferred` implementations are quite useful in flexible composition of fluent interfaces. Eg: `DeferredAssertion.java`
- Hardest of all the techniques listed here, but easy enough with some practice.
#### Aliasing
- Use aliases to create rich connector and utility vocabulary for non domain concepts. Eg: `a` and `an`, one() for count(1) or two for count(2)
- There are language nuances. `Ruby` has method aliasing, while `Java` needs a wrapper method. 
- See `CellMatchers.java`.
- High ROI and applicable across languages.
- Don't use aliases for core domain concepts. This creates Ambiguity.
