#Learning Reactive Programming Using Project Reactor


##Flux - create/generate
|     Create                      | Generate                   |
|---------------------------------|----------------------------|
| Accepts a Consumer<FluxSink<T>> | Accepts Consumer<SynchronousSink<T>>| 
| Consumer is invoked only once   | COnsumer is invoked again and again|
| Consumer can emit 0 to N elements immediately without worrying about downstream demand | Consumer can emit only one element. Downstream demand is respected | 
| Thread-safe                      | N/A                  |


## Operators

Operators are similar to Decorator (similar to masala we put in food to prepare different kind of dish).
Some Example of Operators

    - filter
    - map
    - log
    - take
    - takeWhile
    - takeUntil
