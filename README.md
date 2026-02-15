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

#Hot & Cold Publishers
    ##Cold Publisher: 
        :In cold publisher each new subscriber get its own dedicated data producer and received the full sequence of data from beginning
        :Example: Netflix - if two subscribe watching same movie they can have different timeline 
    ##Hot Publisher:
        :In Hot publisher we have only one producer for all the subscribers
        :Didn't need any subscriber to emit data
        :Example: Movie Streaming on TV each user have same timeline, event no user is watching still the tv channel stream data movie
