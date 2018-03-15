package compiler.ir

import compiler.ir.IrBuilder.IrElement.Instruction

/**
 * @author Moklev Vyacheslav
 */
fun IrBuilder.ret() {
    list.add(Instruction(RetInstruction))
}

fun IrBuilder.retval(operand: IrOperand) {
    list.add(Instruction(RetValInstruction(operand.type, operand)))
}

fun IrBuilder.add(operand1: IrOperand, operand2: IrOperand): VariableOperand {
    val type = IrTypeUtils.add(operand1.type, operand2.type)
    val result = tempVar(type)
    list.add(Instruction(AddInstruction(type, result, operand1, operand2)))
    return result
}

fun IrBuilder.sub(operand1: IrOperand, operand2: IrOperand): VariableOperand {
    val type = IrTypeUtils.sub(operand1.type, operand2.type)
    val result = tempVar(type)
    list.add(Instruction(SubInstruction(type, result, operand1, operand2)))
    return result
}

fun IrBuilder.mul(operand1: IrOperand, operand2: IrOperand): VariableOperand {
    val type = IrTypeUtils.mul(operand1.type, operand2.type)
    val result = tempVar(type)
    list.add(Instruction(MulInstruction(type, result, operand1, operand2)))
    return result
}

fun IrBuilder.div(operand1: IrOperand, operand2: IrOperand): VariableOperand {
    val type = IrTypeUtils.div(operand1.type, operand2.type)
    val result = tempVar(type)
    list.add(Instruction(DivInstruction(type, result, operand1, operand2)))
    return result
}

fun IrBuilder.mod(operand1: IrOperand, operand2: IrOperand): VariableOperand {
    val type = IrTypeUtils.mod(operand1.type, operand2.type)
    val result = tempVar(type)
    list.add(Instruction(ModInstruction(type, result, operand1, operand2)))
    return result
}

fun IrBuilder.call(name: String, vararg operands: IrOperand) {
    val returnType = OperandType.Unknown
    list.add(Instruction(CallInstruction(LabelOperand(name), returnType, *operands)))
}

fun IrBuilder.callval(name: String, vararg operands: IrOperand): VariableOperand {
    val returnType = trace.functionDescriptors.get(name)?.singleOrNull()?.irType
            ?: OperandType.Unknown
    val result = tempVar(returnType)
    list.add(Instruction(CallValInstruction(LabelOperand(name), returnType, result, *operands)))
    return result
}