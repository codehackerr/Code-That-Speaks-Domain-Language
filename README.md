### Status
[![Build Status](https://api.travis-ci.org/codehackerr/JGoL.png)](https://api.travis-ci.org/codehackerr/JGoL.png)

[Game of Life in Java](https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life)


In this project, I tried to write the test cases in a declarative fashion using plain java for my love of human readable code.

Here is an example. Hope you enjoy!

```
public class StillLife {.....

    @Test
    public void beehive() {
        a(BEEHIVE).remains_unchanged().after(1).generations();
    }
}
``` 
