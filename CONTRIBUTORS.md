# Before Name Change

*The `Contrubutors` section below will only include the contributors to the 1.0.0 version.*

*The inclusion of myself is needed since the professor at my university demanded every contributor, including myself.* 

## Contributions

*Sorted by an alphabetical order of their surnames, after which follows the timeline of their contributions, from the first commit to the latest.*

*It is recommended to read DOCUMENTATION.md first in order to fully understand every method that will be listed below.* 

### Ana Narmania

#### Commit 1

- Provided a minimal interpreter including basic functionalities of the following methods:
  - HashMap `Map<String, Integer> variables` for integer storing
  - `eval()` (evaluation of an `int` assignment and `print()`)
  - `handleAssignment()` of primitive integers
  - `evaluateExpression()` (addition, substraction, multiplication, division, modulo)
  - `handlePrint()` (print of a singular `int` variable)

#### Commit 2

- Refactor of HashMap:
  - Moving from `Integer` to `Double` for easier Python-like division (3 / 2 = 1.5 instead of 3 / 2 = 1)
  - Added `String` and `Boolean`
- Improved handling of variable assignment and added comments in `eval()`
- Updated `handleAssignment()` to also handle `''` Strings, direct boolean assignment (`x ="True"`, `x = "False"`)
- Error catching for `evaluateExpression()`, improved operator handling
- Addition of `evalString()`, which evaluates Strings

#### Commit 3

- `handlePrint()` now prints strings of `''`

#### Commit 4

- Fixed `while` loop and `if-else` block, ensuring it works correctly

#### Commit 5

- Fixed `evalBool()` catching `!` as a comparison operator instead of intended `!=`

#### Commit 6

- Added an implementation of task 3 and 4 (`gcd()` and `reverseNumber()`)

#### Commit 7

- Refactored number type handling from `Double` to `Integer`,  ensuring better handling of numbers, useful for many tasks. Though it's `3 / 2 = 1` now
- Added "division by zero" error handling
- Changed some comments in various methods for better readability

<br>

<br>

### Nini Phkhakadze

#### Commit 1

- Added a method `containsStringVariable()`, which detects if the expression contains a string variable. Used for printing string concatenation inside `print()`
- Improved boolean comparison handling in `handlePrint()`

#### Commit 2

- Added an implementation of task 5, 6, 7 (`isPrime()`, `isPalindrome()`, `largestDigit()`)

#### Commit 3

- Removed a redundant part of the `Main.java`

<br>

<br>

### Giorgi Siradze (team leader)

#### Commit 1

- Updated `.gitignore`

#### Commit 2

- Fixed comment grammar and improved code readability (applied common Java practices and conventions), replaced regular switch statement with enhanced switch statement
  
#### Commit 3

- Removed `main()` from `MinimalInterpreter.java`, added it to `Main.java` instead

#### Commit 4

- Added a while loop method `handleWhileLoop()`
- Improved or added some of the comments

#### Commit 5

- Changes in `eval()`:
  - Removed accidental comment of a while loop handling
  - Replaced for-each approach of line-by-line interpreting with a traditional for loop
 
#### Commit 6

- Added variable assignment using other variables such as `x = y + 3`
- Enhance print ability, example being `print(x + y + 1)`
- Improved some comments
- Adjusted some parts of the code to better match the program flow (e.g. mainstreaming the use of double instead of writing something with int and then casting it to double)
- Improved exception handling in `evaluateExpression()`, now catching an undefined variable

#### Commit 7

- Changes in `handleIfElse()`:
  - Added comments
  - Applied consistent coding standards
- Fixed a switch case, which was incorrectly catching `=` instead of `==` in `handleWhileLoop()`

#### Commits 8, 9, 10, 11

- Improved `.gitignore` and project directory

#### Commit 12

- Changed `+=` with a standard `total = total + digit` to better match the interpreter subset

#### Commit 13

- Changes in `Main.java`:
  - Added "Contributor: Name Surname" on top of all algori
  - Tested all algorithms and wrote `// Works as intended`

#### Commit 14

- Added Task 1, 2 (`sumOfNNumbers()`, `factorial()`)

#### Commit 15

- Added boolean assignment such as `x = 10 == 3`
- if-else chain readjustment in `eval()` to allow booleans in assignment
- better boolean expression handling in `handleAssignment()`
- improved `handlePrint()`
- Added an ability to comment using `#`, though inline comments are not supported (e.g. `x = 3 # comment`)

#### Commit 16

- Better assignment handling in `handleAssignment()` with changed RegEx;
- `nextOperand` in `evaluateExpression()` is now `int` instead of `double` since we now work on ints only

#### Commit 17

- if-else inside while loop now works as intended, though one extra \n is necessary, otherwise it goes into an infinity loop
- Tested `isPrime()`, now it works as indended after the abovementioned change.

#### Commit 17

- Task 7 `largestDigit()` now works as intended

#### Commit 18

- Changes in `evalBool()`:
  - Added error handling in `evalBool()`
  - Improved splitting and evaluation
  - Enhanced code readability
- Changes in `Main()`, algorithms part:
  - Fixed `isPalindrome()`
  - Improved readability in many tasks and added comments, resulting in more intuitive implementations
  - Added descriptions of tasks
- Added `CONTRIBUTORS.md` (this file) (this was split into 4 commmits, but I won't separate them)
 
<br>

<br>

### Tatia Tkeshelashvili

#### Commit 1

- Added `evalBool()`, method used for evaluating a boolean

#### Commit 2

- Added `handleIfElse()`, method used for handling if-else block

#### Commit 3

- Added task 8, 9, 10 (`sumOfDigits()`, `multiplicationTable()`, `NthFibonacci()`)

<br>

<br>

# After Name Change

*The `Contrubutors` section below will include the contributors **after** the 1.0.0 version.*

*I won't include myself as a contributor since I don't think that would make sense.*

## Contributors

*To be added, hopefully*
