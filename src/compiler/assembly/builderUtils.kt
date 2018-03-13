package compiler.assembly

import util.Either.Left

/**
 * @author Moklev Vyacheslav
 */
fun AssemblyBuilder.ret() {
    list.add(Left(RetInstruction))
}

fun AssemblyBuilder.push(operand: AssemblyOperand, size: OperandSize? = null) {
    list.add(Left(PushInstruction(size, operand)))
}

fun AssemblyBuilder.pop(operand: AssemblyOperand, size: OperandSize? = null) {
    list.add(Left(PopInstruction(size, operand)))
}

fun AssemblyBuilder.add(operand1: AssemblyOperand, operand2: AssemblyOperand, size: OperandSize? = null) {
    list.add(Left(AddInstruction(size, operand1, operand2)))
}

fun AssemblyBuilder.sub(operand1: AssemblyOperand, operand2: AssemblyOperand, size: OperandSize? = null) {
    list.add(Left(SubInstruction(size, operand1, operand2)))
}

fun AssemblyBuilder.imul(operand1: AssemblyOperand, operand2: AssemblyOperand, size: OperandSize? = null) {
    list.add(Left(IMulInstruction(size, operand1, operand2)))
}

fun AssemblyBuilder.idiv(operand: AssemblyOperand, size: OperandSize? = null) {
    list.add(Left(IDivInstruction(size, operand)))
}

fun AssemblyBuilder.cdq() {
    list.add(Left(CdqInstruction))
}

fun AssemblyBuilder.call(label: LabelOperand) {
    list.add(Left(CallInstruction(label)))
}