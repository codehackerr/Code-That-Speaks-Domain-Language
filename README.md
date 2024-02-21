### Status
[![Build Status](https://api.travis-ci.org/codehackerr/JGoL.png)](https://api.travis-ci.org/codehackerr/JGoL.png)

### Code that speaks domain language

#### Disclaimer: Example follows a more puristic approach for demonstration.

Show me an example.

```java
// Domain Rule: Any live cell with fewer than two live neighbours dies, as if by underpopulation. 
assertThat( live_cell_with_fewer_than_two_live_neighbours, dies_of_underpopulation(next_generation));

/** System Behavior */
a(BEEHIVE).remains_unchanged().after(1).generations();
```

Code and domain often speak different languages making it difficult to understand and map domain behaviors to code.
This repo shows examples and techniques to make code stay close to domain language.



The repo solves [Game of Life](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)

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
