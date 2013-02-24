Richard Bolkey's Guice Tutorial
==============

[![Build Status](https://travis-ci.org/rbolkey/guice-tutorials.png)](https://travis-ci.org/rbolkey/guice-tutorials)

## Part 1: Building a simple object graph.

In part 1, we have a simple object graph that requires no binding configuration.

### Key classes

+ *Dish* - Immutable value object of a dish served by the restaurant.
+ *Kitchen* - A service where the restaurant can place an order a dish.
+ *Pantry* - Immutable value object. Used to demonstrate a Guice configuration failure.
+ *Restaurant* - Runnable service that tries to place an order for a dish.

### Demonstrations

+ An injector can create instances of a class without a module defining the binding for the class.
+ By default, Guice creates a new instance each time a request for a class is performed (singletons require
  separate configuration as we will see in part 2).
+ By default, you cannot fetch an instance of a class by its interface (you need a linked binding as seen in part 2).
+ JUL Logger is a built in binding.

## Part 2: Creating simple bindings

In part 2, we have a different object graph that requires additional configuration in order to distinguish implementations and
to provide binding scopes (e.g. singletons).

### Key classes

+ *Apartment* - Interface for an apartment entity. Provides an address, and holds person.
+ *Person* - Immutable value object of a person.

### Demonstrations

+ Introduce Guice Modules.
+ Linked bindings let you customize your injection points (e.g. inject instances of an interface).
  + Can identify implementations of interfaces.
  + Can be differentiated by annotations.
  + Can be scoped (e.g. singleton, session, request).
+ Constants can be bound.
  + They must be annotated.
  + Strings can be coerced into enums and other types.
+ Complex bindings can be created via providers (either by using @Provides or linked binding's #toProvider).

## Part 3: More bindings concepts

In part 3, we demonstrate more bindings concepts like type literals, aspects, and circular proxies.

### Key classes

+ *Mechanic* - Parameterized interface that defines a service for repairing a vehicle.
+ *Vehicle* - Interface with two concrete implementations: Bicycle and Truck.
+ *Wheel* - Interface with two concrete implementations: BicycleWheel and BicycleTruck

### Demonstrations

+ Bindings recognize generics (via TypeLiteral). There is no need to use an annotation if the parameterized type is unique.
+ Supports AOP Alliance's MethodInterceptor
+ Providers can be injected for use as simple no-arg factories.
  + Easy AOP entry point.
  + Lazy creation of expensive objects.
  + Mixing scope (using a request scope instance within a session scope instance).
+ Supports circular proxying by interfaces.

## Part 4: A few Guice extensions

In part 4, we look at a few Guice extensions that can simplify and modularize your object graph. PrivateModule's can
isolate subgraphs from other part of the object graph. This can provide a more well defined API that a module exposes
and simpler binding definitions. Multibinders allow collections to be aggregated across modules, and when combined with
PrivateModules, can behave like a simpler version of OSGi. Assisted injection removes boiler plate code necessary when
creating factories.

### Key classes

+ *BookOrderService* - A service for ordering a book title from a publisher.
+ *Publisher* - A value object that exposes book titles from the publisher along with it's ordering service.
+ *Title* - A value object for a book title. Provides a name, author, and optional topic.
+ *Topic* - An entity that collects different publisher's titles in the bookstore under a topic.

### Demonstrations

+ Bindings can be distributed across modules.
  + It is good practice to *require* bindings that are expected from other modules.
+ Guice adds extension points like TypeListeners to respond to injection events.
+ Private modules can isolate and hide binding details.
  + They should expose their public services.
+ Multibinders can aggregate bindings across modules.
+ Assisted inject can simplify the creation of Guice factories.