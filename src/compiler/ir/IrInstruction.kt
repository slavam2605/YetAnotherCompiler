package compiler.ir

/**
 * @author Moklev Vyacheslav
 */
sealed class IrInstruction(val name: String, val type: OperandType = OperandType.Void, vararg val operands: IrOperand) {
    open fun debugRepresentation(): String {
        return "$name ${type.debugRepresentation} ${operands.joinToString { it.debugRepresentation() }}"
    }
}

object RetInstruction : IrInstruction("ret")

class RetValInstruction(type: OperandType, operand: IrOperand)
    : IrInstruction("retval", type, operand)

class AddInstruction(type: OperandType, result: IrOperand, operand1: IrOperand, operand2: IrOperand)
    : IrInstruction("add", type, result, operand1, operand2)

class SubInstruction(type: OperandType, result: IrOperand, operand1: IrOperand, operand2: IrOperand)
    : IrInstruction("add", type, result, operand1, operand2)

class MulInstruction(type: OperandType, result: IrOperand, operand1: IrOperand, operand2: IrOperand)
    : IrInstruction("add", type, result, operand1, operand2)

class DivInstruction(type: OperandType, result: IrOperand, operand1: IrOperand, operand2: IrOperand)
    : IrInstruction("add", type, result, operand1, operand2)

class CallInstruction(returnType: OperandType, vararg operands: IrOperand)
    : IrInstruction("call", returnType, *operands)

class CallValInstruction(returnType: OperandType, result: IrOperand, vararg operands: IrOperand)
    : IrInstruction("callval", returnType, result, *operands)