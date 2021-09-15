# PatGen

## Use cases
2d array data generation with predefined patterns mixed with random data. 

_wider description coming soon_

### See the sample code below

```java
PatternGenerator generator = PatternGenerator.newBuilder(5) //set size for every new integer vector
                .addPattern(Pattern.fromFile("predefinedFromFile", Path.of(this.getClass().getResource("/patterns/pat_1.csv").toURI()), ";"),
                        30, //share in total sum of such shares
                        0.1) //part of data of this pattern replaced with random data
                .addPattern(new Pattern(new int[][]{
                                {21, 2, 54, 100},
                                {0, 1, 2},
                                {0, 0, 1}
                        }, "predefined"),
                        10,
                        0.1)
                .addPattern(Pattern.randomized("randPattern", 1, 5), 100, 0)
                .withProbability(0.8) //set the probability of pattern generation after last one has finished
                .withLimit(100) //set generation length limit. Switch off for infinite generation.
                .build();
        
        for (int[] ints : generator) {
            System.out.println(Arrays.toString(ints));
        }
