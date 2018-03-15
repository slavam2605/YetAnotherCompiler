package compiler

import compiler.assembly.AssemblyBuilder
import compiler.ir.IrBuilder
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import parser.MainLexer
import parser.MainParser
import java.io.PrintStream

/**
 * @author Moklev Vyacheslav
 */
fun main(args: Array<String>) {
    val input = CharStreams.fromString("""
        fun _main() {
            write 3 * foo();
            return 0;
        }

        fun foo() {
            write (1 - 10) * 8;
            return 10;
        }
    """)
    val lexer = MainLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = MainParser(tokens)

    val ctx = parser.file()
    val trace = DataTrace()
    val assemblyBuilder = AssemblyBuilder()
    val irBuilder = IrBuilder(trace)
    val writer = PrintStream("out.s")
    DescriptorsVisitor(trace).visitFile(ctx)

    FrontendCompilerVisitor(trace, irBuilder).visitFile(ctx)
    irBuilder.print(System.out)

    CompilerVisitor(trace, assemblyBuilder).visitFile(ctx)
    assemblyBuilder.print(writer)
}