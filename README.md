# Learning Reactive Programming Using Project Reactor


## Flux - create/generate
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

# Hot & Cold Publishers

## Cold Publisher: 

- In cold publisher each new subscriber get its own dedicated data producer and received the full sequence of data from beginning
- Example: Netflix - if two subscribe watching same movie they can have different timeline 

## Hot Publisher:

- In Hot publisher we have only one producer for all the subscribers
- Didn't need any subscriber to emit data
- Example: Movie Streaming on TV each user have same timeline, event no user is watching still the tv channel stream data movie

|     Method                    | Usage                   |
|-------------------------------|-------------------------|
| share(), publish().refCount(1)| At least 1 subscriber. It will start when there is at least 1 subscriber and stop when there is 0 subscriber.we can re-subscribe |
| publish().autoConnect(1)      | Same as above. It does NOT stop when subscribers leave. |
| publish().autoConenct(0)      | Real hot publisher - no subscriber required |
| replay(n).autoConenct(0)      | Cache the emitted item for late subscriber |


# Thread & Schedulers

## Reactive Pipeline Thread

By Default everything is executed by same thread,We can create separate thread in java to do the work but that could be a hectic task to manage thread 
or we might not want to go that way.

So to solve this problem reactor provide some set of thread pools optimization for certain purposes which is called Scheduler.

## Schedulers - Thread Pool

| Scheduler      | Usage                                        |
|----------------|----------------------------------------------|
| boundedElastic | Network/time-consuming/blocking operations   |
| parallel       | CPU intensive tasks                          |
| single         | A single dedicated thread for one-off tasks  |
| immediate      | Current thread                               |

So we have scheduler, now how we can schedule for that reactor provide 2 different operators.

## Operators for Scheduling

| Operator    | Usage                 |
|-------------|-----------------------|
| subscribeOn | for upstream          |
| publishOn   | for downstreamThread  |