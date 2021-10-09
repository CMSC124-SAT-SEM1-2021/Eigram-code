# Syntax Exercise

Create a grammar for a small language using BNF that accepts a program code that
* Starts with  special word **begin**
* Followed by **one or more assignment statements**, wherein each statement ends with a **semicolon** (use the BNF for assignment in the example at the parse tree section)
* After the last statement, the code should end with the special word **end**

Derive the statement below and create a parse tree for it:
* **begin B = C * A + B * B; C = (B * C + A); end**


## Grammars
* &lt;syntax&gt; -> begin &lt;block&gt; end
* &lt;block&gt; -> &lt;stmt&gt;; | &lt;stmt&gt;; &lt;block&gt;
* &lt;stmt&gt; -> &lt;exp&gt;
* &lt;assign&gt; -> &lt;var&gt; = &lt;exp&gt;
* &lt;var&gt; -> A | B | C
* &lt;op&gt; -> + | - | * | / | ( | )
* &lt;exp&gt; -> &lt;var&gt; | &lt;assign&gt; | &lt;exp&gt; &lt;op&gt; &lt;exp&gt; | (&lt;exp&gt;)


## Parse Tree
```
<syntax>
+-- begin
+-- <block>
    +-- <stmt>
        +-- <exp>
            +-- <assign>
                +-- <var>
                    +-- B
                +-- =
                +-- <exp>
                    +-- <exp>
                        +-- <var>
                            +-- C
                    +-- <op>
                        +-- *
                    +-- <exp>
                        +-- <exp>
                            +-- <var>
                                +-- A
                        +-- <op>
                            +-- +
                        +-- <exp>
                            +-- <exp>
                                +-- <var>
                                    +-- B
                            +-- <op>
                                +-- *
                            +-- <exp>
                                +-- <var>
                                    +-- B
    +-- ;
    +-- <block>
        +-- <stmt>
            +-- <exp>
                +-- <assign>
                    +-- <var>
                        +-- C
                    +-- =
                    +-- <exp>
                        +-- (
                        +-- <exp>
                            +-- <exp>
                                +-- <var>
                                    +-- B
                            +-- <op>
                                +-- *
                            +-- <exp>
                                +-- <exp>
                                    +-- <var>
                                        +-- C
                                +-- <op>
                                    +-- +
                                +-- <exp>
                                    +-- <var>
                                        +-- A
                        +-- )
        +-- ;
+-- end
```

![Parse Tree](https://github.com/CMSC124-SAT-SEM1-2021/Eigram-code/blob/ac761277839cb34cce6bc0b028b19df08d76085d/Parse%20Tree.png)
