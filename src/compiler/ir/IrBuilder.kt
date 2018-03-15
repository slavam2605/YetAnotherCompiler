package compiler.ir

import java.io.PrintStream

/**
 * @author Moklev Vyacheslav
 */
class IrBuilder {
    val list = arrayListOf<IrElement>()

    fun label(name: String) {
        list.add(IrElement.Label(name))
    }

    fun print(stream: PrintStream) {
        list.forEach {
            when (it) {
                is IrElement.Instruction -> stream.println("\t" + it.value.debugRepresentation())
                is IrElement.Label -> stream.println(it.value + ":")
            }
        }
    }

    sealed class IrElement {
        class Instruction(val value: IrInstruction) : IrElement()
        class Label(val value: String) : IrElement()
    }
}