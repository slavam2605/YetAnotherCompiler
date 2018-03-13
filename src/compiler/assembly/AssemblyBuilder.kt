package compiler.assembly

import util.Either
import util.Either.*
import java.io.PrintStream

/**
 * @author Moklev Vyacheslav
 */
class AssemblyBuilder {
    val list = arrayListOf<Either<AssemblyInstruction, Either<String, String>>>()
    
    fun label(name: String) {
        list.add(Right(Left(name)))
    }
    
    fun metainfo(name: String) {
        list.add(Right(Right(name)))
    }
    
    fun print(stream: PrintStream) {
        list.forEach {
            when (it) {
                is Left -> stream.println("\t" + it.value)
                is Right -> when (it.value) {
                    is Left -> stream.println(it.value.value + ":")
                    is Right -> stream.println(it.value.value)
                }
            }
        }
    }
}