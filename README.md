# Yet Another Compiler
Just a simple compiler from some simple language to the x86-64 assembly.

## Build and run
For now the only way to build the project is to open it in IntelliJ IDEA with Kotlin plugin and ANTLR plugin (compatible with ANTLR 4.7.1).

### Steps to build
1. Open project in IntelliJ IDEA (or import it from existing sources)
2. Go to `src/parser` directory and generate lexer and parser with ANTLR plugin (with tree visitor)
3. Add run configuration for `compiler/Main.kt` and build / run

## Language features
* Expressions with `+`, `-`, `*`, `/`, `%` and integer constants
* Operator `write` to print an expression to screen
* Functions without arguments that can return integer values
* Functions can be called before defined

Note: entry-point function should have name `_main` to compile on Windows and `main` to compile on Linux (and Mac probably).

## Compiler features
* Intermediate representation of code (package `compiler.ir`)

## Examples
Just an example of language syntax:
```
fun _main() {
   write 3 * foo();
   return 0;
}

fun foo() {
    write (1 - 10) * 8;
    return 2 + 8;
}
```