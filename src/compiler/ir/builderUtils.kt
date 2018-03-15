package compiler.ir

import compiler.ir.IrBuilder.IrElement.Instruction

/**
 * @author Moklev Vyacheslav
 */
fun IrBuilder.ret() {
    list.add(Instruction(RetInstruction))
}

fun IrBuilder.retval(operand: IrOperand) {
    val type = OperandType.Unknown
    list.add(Instruction(RetValInstruction(type, operand)))
}

fun IrBuilder.add(result: IrOperand, operand1: IrOperand, operand2: IrOperand) {
    val type = OperandType.Unknown
    list.add(Instruction(AddInstruction(type, result, operand1, operand2)))
}

fun IrBuilder.sub(result: IrOperand, operand1: IrOperand, operand2: IrOperand) {
    val type = OperandType.Unknown
    list.add(Instruction(SubInstruction(type, result, operand1, operand2)))
}

fun IrBuilder.mul(result: IrOperand, operand1: IrOperand, operand2: IrOperand) {
    val type = OperandType.Unknown
    list.add(Instruction(MulInstruction(type, result, operand1, operand2)))
}

fun IrBuilder.div(result: IrOperand, operand1: IrOperand, operand2: IrOperand) {
    val type = OperandType.Unknown
    list.add(Instruction(DivInstruction(type, result, operand1, operand2)))
}

fun IrBuilder.mod(result: IrOperand, operand1: IrOperand, operand2: IrOperand) {
    val type = OperandType.Unknown
    list.add(Instruction(ModInstruction(type, result, operand1, operand2)))
}

fun IrBuilder.call(name: String, vararg operands: IrOperand) {
    val returnType = OperandType.Unknown
    list.add(Instruction(CallInstruction(LabelOperand(name), returnType, *operands)))
}

fun IrBuilder.callval(name: String, result: IrOperand, vararg operands: IrOperand) {
    val returnType = OperandType.Unknown
    list.add(Instruction(CallValInstruction(LabelOperand(name), returnType, result, *operands)))
}