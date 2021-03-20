# jlox

My Java implementation of the Lox interpreter from [Crafting Interpreters].

## Deviations

So far, I've stayed mostly true to the source code written in the book.

One notable exception is [my use of Java 16's records for the AST definition][expr.java]
(rather than [the book's code gen solution][ci ast code gen]), coupled with the use of a
sealed interface which is in its second preview in 16.

[crafting interpreters]: http://www.craftinginterpreters.com/
[ci ast code gen]: http://www.craftinginterpreters.com/representing-code.html#implementing-syntax-trees
[expr.java]: src/main/java/com/tylerkindy/lox/Expr.java
