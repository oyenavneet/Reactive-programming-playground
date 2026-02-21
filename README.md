# Learning Reactive Programming Using Project Reactor


## Flux - create/generate
| Create                                                                                 | Generate                                                           |
|----------------------------------------------------------------------------------------|--------------------------------------------------------------------|
| Accepts a Consumer<FluxSink<T>>                                                        | Accepts Consumer<SynchronousSink<T>>                               | 
| Consumer is invoked only once                                                          | COnsumer is invoked again and again                                |
| Consumer can emit 0 to N elements immediately without worrying about downstream demand | Consumer can emit only one element. Downstream demand is respected | 
| Thread-safe                                                                            | N/A                                                                |


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
- Example: Movie Streaming on TV each user have same timeline, event no user is watching still the TV channel stream data movie

| Method                         | Usage                                                                                                                            |
|--------------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| share(), publish().refCount(1) | At least 1 subscriber. It will start when there is at least 1 subscriber and stop when there is 0 subscriber.we can re-subscribe |
| publish().autoConnect(1)       | Same as above. It does NOT stop when subscribers leave.                                                                          |
| publish().autoConenct(0)       | Real hot publisher - no subscriber required                                                                                      |
| replay(n).autoConenct(0)       | Cache the emitted item for late subscriber                                                                                       |


# Thread & Schedulers

## Reactive Pipeline Thread

By Default everything is executed by same thread,We can create separate thread in java to do the work but that could be a hectic task to manage thread, 
or we might not want to go that way.

So to solve this problem reactor provide some set of thread pools optimization for certain purposes which is called Scheduler.

## Schedulers - Thread Pool

| Scheduler      | # of threads       | Usage                                       |
|----------------|--------------------|---------------------------------------------|
| boundedElastic | 10 * Number of CPU | Network/time-consuming/blocking operations  |
| parallel       | Number of CPU      | CPU intensive tasks                         |
| single         | 1                  | A single dedicated thread for one-off tasks |
| immediate      | N/A                | Current thread                              |

So we have scheduler, now how we can schedule for that reactor provide 2 different operators.

## Operators for Scheduling

| Operator    | Usage                 |
|-------------|-----------------------|
| subscribeOn | for upstream          |
| publishOn   | for downstreamThread  |

## In Reactive programing : Schedulers != Parallel-execution
- All the operations are always executed in sequential
- data is process one by one - by a thread from ThreadPool for a Subscriber
- Schedulers.parallel() - is a thread pool for CPU tasks. Does not mean parallel execution.

## Parallel-Execution
In Reactive programing by default execution is done one by one, If we do not want this behavior and want to process item in parallel
then it can be done easily by using operators below:
- parallel
- runIn

parallel(), runOn() can be used in case of CPU intensive task or if you have multiple core of CPU and want to utilize it.


## Backpressure / Overflow Strategy

In simple word, When producer produce a lot of data which can't be consumed by subscriber to handle the flow-control that allows
a slow consumer (subscriber) to single the fast producer that it is being overwhelmed by the rate of data, thereby using backpressure
flow-control mechanism we can prevent system by overload, memory issues (like OutOfMemoryError) or data loss.

## Backpressure Strategy's

| Stratgy | Behavior                                                                      |
|---------|-------------------------------------------------------------------------------|
| buffer  | keep in memory                                                                |
| error   | throw error to the downstream                                                 |
| drop    | Once the queue is full, new items will be dropped                             |
| latest  | Once the queue is full, keep 1 latest items as and when it arrived. drop old. |


## Overflow Strategy 
- It is used on Producer/upstream 
- It is used  when you want one Strategy for all of your subscriber
- Overflow strategy is second parameter in Flux.create()
- Overflow Strategies are: BUFFER, ERROR, DROP, LATEST, IGNORE

## Combining Publishers in Reactor

## Why is this needed?

In real-world **microservice architectures**, we often make multiple network calls to different services.

Depending on business requirements:

* Sometimes calls must be executed **in sequence**
* Sometimes they should run **in parallel**
* Sometimes we need to **aggregate results**
* Sometimes we only care about **completion**, not intermediate data

In **Reactive Programming**, publishers can be combined using operators to build complex reactive flows that satisfy these business needs.

---

## Use Case Example

**Movie Streaming Application**

When a user logs in and opens the homepage:

* Fetch **Recommendations**
* Fetch **Trending Movies**
* Fetch **User Watch History**

Using reactive programming, we combine these API publishers into a **single aggregated stream** and send the final result to the subscriber.

---

## Reactor Operators for Combining Publishers

Reactor provides multiple operators to control execution order, concurrency, and combination behavior.

---
### For Independent Producer
- startWith : will be useful when you have multiple producers, and you want to check one producer first before you go to another producer (e.g., check cache before calling DB).
- concatWith : sequentially subscribes to another producer only after the first one completes (order guaranteed, no interleaving).
- merge : subscribes to multiple producers at the same time and emits values as they arrive (order not guaranteed, can interleave).
- zip : combines multiple producers by pairing their emitted items one-by-one based on index and emits combined results.

### For Dependent Sequential Calls
- flatMap : transforms each emitted item into a new producer and merges them concurrently (order not guaranteed, faster but interleaved).
- concatMap : transforms each emitted item into a new producer and subscribes to them sequentially (order preserved, waits for one to complete before starting the next).

| Operator     | Order Preserved   | Parallel   | Use Case                     |
| ------------ | ----------------- | ---------- | ---------------------------- |
| `startWith`  | Yes               | No         | Fallback (cache → DB)        |
| `concatWith` | Yes               | No         | Sequential execution         |
| `merge`      | No                | Yes        | Parallel independent calls   |
| `zip`        | Yes (index-based) | Yes        | Combine synchronized results |
| `flatMap`    | No                | Yes        | Dependent parallel calls     |
| `concatMap`  | Yes               | No         | Dependent sequential calls   |
| `then`       | N/A               | Sequential | Only care about completion   |

# `then` Operator

### `then`

* Use when you're **not interested in intermediate results**.
* Executes publishers sequentially.
* Only signals **completion** (or error).

### Example Use Case

Inserting multiple records into a database:

* You don't care about each insert result.
* You only care whether the entire operation **succeeded or failed**.


## Batching / Windowing / Grouping

When consuming a continuous stream (`Flux<T>`) from systems like Kafka or RabbitMQ, you may need to process data in batches or logical groups.

### buffer
- Collects items into a `List`
- Emits when size/time condition is met
- `Flux<T> → Flux<List<T>>`
- Use for bulk DB inserts or batch API calls

### window
- Splits stream into smaller sub-streams
- Emits `Flux<T>` instead of `List`
- `Flux<T> → Flux<Flux<T>>`
- Use when you want reactive processing per batch

### groupBy
- Groups items based on a key
- `Flux<T> → Flux<GroupedFlux<K, T>>`
- Use for logical grouping (e.g., by userId, accountId)


## Repeat & Retry

In Reactive programing we know that we have publisher and subscriber 
no item can be emitted after:
- onComplete
- On Error

| Operator | Behavior                           |
|----------|------------------------------------|
| repeat   | Resubscribe after complete signal  |
| retry    | Resubscribe after error signal     |


## Sinks

In Reactive Programming, publishers (like `Flux` and `Mono`) start emitting data only when there is a subscriber.

However, in real-world applications, there are scenarios where:

- You need to emit data manually.
- You want to push events whenever something happens (e.g., user action, callback, message listener).
- You need to emit data from multiple threads.
- You may want to handle emission even before subscribers are attached.

For such use cases, Reactor provides **Sinks**.

### What are Sinks?

Sinks are programmatic event emitters that allow you to:

- Push data manually into a reactive stream.
- Control when and how data is emitted.
- Safely emit items from multiple threads.
- Connect imperative code with reactive pipelines.

In simple terms, a Sink acts as a bridge between non-reactive (imperative) code and reactive streams.

| Sink Type          | Publisher Type | # of Subscribers | Behavior                                                                   |
|--------------------|----------------|------------------|----------------------------------------------------------------------------|
| `one`              | Mono           | N                |                                                                            |
| `many - unicast`   | Flux           | 1                | subscriber cam join late if required. message will be stored in the memory |
| `many - multicast` | Flux           | N                | late subscribers can not see the messages                                  |
| `many - replay`    | Flux           | N                | with relay of all values to late susbscribers                              |

