Richard Bolkey's Guice Tutorial
==============

[![Build Status](https://travis-ci.org/rbolkey/guice-tutorials.png)](https://travis-ci.org/rbolkey/guice-tutorials)

## Part 1: Building a simple object graph.

Here we have a simple object graph containing a Restaurant that depends upon a kitchen. There is no Guice module or
custom bindings in this section.

### Demonstrations

+ An injector can create instances of a class without a module defining the binding for the class.
+ By default, Guice creates a new instance for each time a request for a class is performed (singletons require
  separate configuration)
+ By default, you cannot fetch an instance of a class by its interface (you need a linked binding as seen in part 2).
+ JUL Logger is a built in binding.

# Part 2: Creating simple bindings

Here we have a different object graph that requires a little configuration in order to distinguish implementations and
to provide different binding scopes (like singletons).

### Demonstrations

+ Introduce Guice Modules.
+ Linked Bindings let you inject instances of an interface.
+ Bindings can be scoped (e.g. singleton, session, request).
+ Constant can be bound.
  + They must be annotated.
  + Strings can be coerced into enums and other types.
+ Bindings can be distinguished by annotations.
+ Complex bindings can be created via providers.

## Part 3: More advanced bindings

Here we have more advanced bindings that require things bind-time information or knowledge of the generic type.

### Demonstrations

+ Supports distinguishing bindings by generics (via TypeLiteral)
+ Supports AOP Alliance's MethodInterceptor
+ Providers can be injected for use as simple no-arg factories.
  + Easy AOP entry point.
  + Lazy creation of expensive objects.
  + Mixing scope (using a request scope instance within a session scope instance).
+ Supports circular proxying by interfaces.

## Part 4: Some Guice extensions

### Demonstrations

+