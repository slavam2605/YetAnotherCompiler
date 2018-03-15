package compiler.assembly

import util.Either
import util.Either.*
import java.io.PrintStream

/**
 * @author Moklev Vyacheslav
 */
class AssemblyBuilder {
    val list = arrayListOf<AssemblyElement>()
    
    fun label(name: String) {
        list.add(AssemblyElement.Label(name))
    }
    
    fun metainfo(name: String) {
        list.add(AssemblyElement.Metainfo(name))
    }
    
    fun print(stream: PrintStream) {
        list.forEach {
            when (it) {
                is AssemblyElement.Instruction -> stream.println("\t" + it.value)
                is AssemblyElement.Label -> stream.println(it.value + ":")
                is AssemblyElement.Metainfo -> stream.println(it.value)
            }
        }
    }

    sealed class AssemblyElement {
        class Instruction(val value: AssemblyInstruction) : AssemblyElement()
        class Label(val value: String) : AssemblyElement()
        class Metainfo(val value: String) : AssemblyElement()
    }
}