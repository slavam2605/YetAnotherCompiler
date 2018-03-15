package compiler.ir

import compiler.ir.IrBuilder.IrElement.Instruction

/**
 * @author Moklev Vyacheslav
 */
fun IrBuilder.ret() {
    list.add(Instruction(RetInstruction))
}

fun IrBuilder.retval(type: OperandType, operand: IrOperand) {
    list.add(Instruction(RetValInstruction(type, operand)))
}

fun IrBuilder.add(type: OperandType, result: IrOperand, operand1: IrOperand, operand2: IrOperand) {
    list.add(Instruction(AddInstruction(type, result, operand1, operand2)))
}

fun IrBuilder.sub(type: OperandType, result: IrOperand, operand1: IrOperand, operand2: IrOperand) {
    list.add(Instruction(SubInstruction(type, result, operand1, operand2)))
}

fun IrBuilder.mul(type: OperandType, result: IrOperand, operand1: IrOperand, operand2: IrOperand) {
    list.add(Instruction(MulInstruction(type, result, operand1, operand2)))
}

fun IrBuilder.div(type: OperandType, result: IrOperand, operand1: IrOperand, operand2: IrOperand) {
    list.add(Instruction(DivInstruction(type, result, operand1, operand2)))
}

fun IrBuilder.call(returnType: OperandType, vararg operands: IrOperand) {
    list.add(Instruction(CallInstruction(returnType, *operands)))
}

fun IrBuilder.callval(returnType: OperandType, result: IrOperand, vararg operands: IrOperand) {
    list.add(Instruction(CallValInstruction(returnType, result, *operands)))
}