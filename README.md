# Caffeinated Python

<br>

## About the Repository

This repository contains Caffeinated Python, a Python interpreter written in Java, originally created as a university project for the midterm assignment in a subject called Fundamentals of Programming.

The goal of this project was to design and implement a simple interpreter for a programming language — Python in this case — with a focus on supporting a minimal subset of the language. Caffeinated Python emphasizes core features like variables, arithmetic operations, conditionals, and iterative control flow. The interpreter is capable of executing basic algorithms, showcasing its functionality as a foundational Python interpreter.

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
