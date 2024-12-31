N P S T

# Contributions

*Sorted by an alphabetical order of their surnames, after which follows the timeline of their contributions, from the first commit to the latest.*

*It is recommended to read DOCUMENTATION.md first in order to fully understand every method that will be listed below.* 

## Ana Narmania

### Commit 1

- Provided a minimal interpreter including basic functionalities of the following methods:
  - HashMap `Map<String, Integer> variables` for integer storing;
  - `eval()` (evaluation of an `int` assignment and `print()`);
  - `handleAssignment()` of primitive integers;
  -  `evaluateExpression()` (addition, substraction, multiplication, division, modulo);
  - `handlePrint()` (print of a singular `int` variable)

### Commit 2

- Refactor of HashMap:
  - Moving from `Integer` to `Double` for easier Python-like division (3 / 2 = 1.5 instead of 3 / 2 = 1);
  - Added `String` and `Boolean`.
- Improved handling of variable assignment and added comments in `eval()`
- Updated `handleAssignment()` to also handle `''` Strings, direct boolean assignment (`x ="True"`, `x = "False"`)
- Error catching for `evaluateExpression()`, improved operator handling
- Addition of `evalString()`, which evaluates Strings.

## Commit 3

- `handlePrint()` now prints strings of `''`

## Commit 4

- Fixed `while` loop and `if-else` block, ensuring it works correctly

## Commit 5

- Fixed `evalBool()` catching `!` as a comparison operator instead of intended `!=`

## Commit 6

- Added an implementation of task 3 and 4 (`gcd()` and `reverseNumber()`)

## Commit 7

- Refactored number type handling from `Double` to `Integer`,  ensuring better handling of numbers, useful for many tasks. Though `3 / 2 = 1` now
- Added "division by zero" error handling
- Changed some comments in various methods for better readability
