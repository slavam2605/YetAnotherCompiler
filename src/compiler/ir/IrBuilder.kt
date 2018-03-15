package compiler.ir

import compiler.DataTrace
import java.io.PrintStream

/**
 * @author Moklev Vyacheslav
 */
class IrBuilder(val trace: DataTrace) {
    val list = arrayListOf<IrElement>()
    private var varCounter: Int = 0

    fun tempVar(type: OperandType): VariableOperand {
        val operand = VariableOperand("%$varCounter", type)
        varCounter++
        return operand
    }

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