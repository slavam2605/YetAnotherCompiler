package compiler.assembly

import compiler.assembly.AssemblyBuilder.AssemblyElement.Instruction

/**
 * @author Moklev Vyacheslav
 */
fun AssemblyBuilder.ret() {
    list.add(Instruction(RetInstruction))
}

fun AssemblyBuilder.push(operand: AssemblyOperand, size: OperandSize? = null) {
    list.add(Instruction(PushInstruction(size, operand)))
}

fun AssemblyBuilder.pop(operand: AssemblyOperand, size: OperandSize? = null) {
    list.add(Instruction(PopInstruction(size, operand)))
}

fun AssemblyBuilder.add(operand1: AssemblyOperand, operand2: AssemblyOperand, size: OperandSize? = null) {
    list.add(Instruction(AddInstruction(size, operand1, operand2)))
}

fun AssemblyBuilder.sub(operand1: AssemblyOperand, operand2: AssemblyOperand, size: OperandSize? = null) {
    list.add(Instruction(SubInstruction(size, operand1, operand2)))
}

fun AssemblyBuilder.imul(operand1: AssemblyOperand, operand2: AssemblyOperand, size: OperandSize? = null) {
    list.add(Instruction(IMulInstruction(size, operand1, operand2)))
}

fun AssemblyBuilder.idiv(operand: AssemblyOperand, size: OperandSize? = null) {
    list.add(Instruction(IDivInstruction(size, operand)))
}

fun AssemblyBuilder.cdq() {
    list.add(Instruction(CdqInstruction))
}

fun AssemblyBuilder.call(label: LabelOperand) {
    list.add(Instruction(CallInstruction(label)))
}