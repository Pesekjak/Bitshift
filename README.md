# Bitshift

[**Skript**](https://github.com/SkriptLang/Skript) addon for more operators.

---

#### Syntax:
##### Conditions
- `<condition> && <condition>` - Conditional-AND operator
- `<condition> || <condition>` - Conditional-OR operator
- `!<condition>` - Inverts the result of a condition
- `%booleans%?` - Checks if all booleans are true
##### Bitwise Operators
- `%number% & %number%` - Bitwise AND
- `%number% | %number%` - Bitwise inclusive OR
- `%number% ^^ %number%` - Bitwise exclusive OR
- `~%number%` - Unary bitwise complement
##### Shift Operators
- `%number% << %number%` - Signed left shift
- `%number% >> %number%` - Signed right shift
- `%number% >>> %number%` - Unsigned right shift
##### Unary Operators
- `!%boolean%` - Inverts the value of a boolean

Bitshift also supports hexadecimal and binary number format.
```js
set {_result} to 0xFF - 0xAA
set {_result} to 0b1100 | 0b0011
```
