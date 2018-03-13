package compiler.assembly

/**
 * @author Moklev Vyacheslav
 */
sealed class AssemblyInstruction(val name: String, val size: OperandSize? = null, vararg val operands: AssemblyOperand) {
    override fun toString(): String {
        return "$name${size?.suffix ?: ""} ${operands.reversed().joinToString { it.asmRepresentation() }}"
    }
}

object RetInstruction : AssemblyInstruction("ret")

class PopInstruction(size: OperandSize?, operand: AssemblyOperand) 
    : AssemblyInstruction("pop", size, operand)

class PushInstruction(size: OperandSize?, operand: AssemblyOperand)
    : AssemblyInstruction("push", size, operand)

class AddInstruction(size: OperandSize?, vararg operands: AssemblyOperand)
    : AssemblyInstruction("add", size, *operands)

class SubInstruction(size: OperandSize?, vararg operands: AssemblyOperand)
    : AssemblyInstruction("sub", size, *operands)

class IMulInstruction(size: OperandSize?, vararg operands: AssemblyOperand)
    : AssemblyInstruction("imul", size, *operands)

class IDivInstruction(size: OperandSize?, vararg operands: AssemblyOperand)
    : AssemblyInstruction("idiv", size, *operands)

object CdqInstruction : AssemblyInstruction("cdq")

class CallInstruction(label: LabelOperand) : AssemblyInstruction("call", null, label)