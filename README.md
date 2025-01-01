# FOP Midterm Project

<br>

## About the Repository

This repository is a university project for a subject called Fundamentals of Programming (FOP in short).

Our objective was to design and implement a simple interpreter for a programming language, in our case, Python, focusing on creating and supporting a minimal subset of the language, with an emphasis on basic constructs like variables, arithmetic, conditionals, and iterative control flow. The interpreter will be capable of executing simple algorithms, serving as a proof of its functionality.

<br>

## Team Members

The team consists of Giorgi Siradze (me), Anna Narmania, Nini Phkhakadze and Tatia Tkeshelashvili.

<br>

## Team Leader

I (Giorgi Siradze) am a team leader, with only added responsibilities being that I own and manage this repo and that I have to "remind mates that they need to do their duties".

<br>

## Instructions on how to run and test the program

All the running and testing is happening in `Main.java`.

In this file, we can see `main()`, where we can run our code in `String code = "`, in full, in this:

```java
String code = """
  # Code to run
  """
```

Here we can write our code in place of `# Code to run`.

*NOTE: make sure that the first indentation of code is **DIRECTLY ABOVE** the second `"""` as not following this guideline would make the program to work incorrectly.*

Example of a correct code:

```java
String code = """
  i = 1
  while i <= 3:
    print(i)
    i = i + 1
  """
```

As you can see, zero indentation is `i = 1` and `while i <= 3:` part, after which follows a singular indentation of `print(i)` and `i = i + 1`.

This would raise an exception:

```java
String code = """
  i = 1
  while i <= 3:
    print(i)
    i = i + 1
"""
```

While this doesn't raise an exception in this case, other codes might cause an error. We would recommend the abovementioned rule. 

```java
String code = """
  i = 1
  while i <= 3:
    print(i)
    i = i + 1
    """
```
