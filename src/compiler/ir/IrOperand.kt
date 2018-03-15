package compiler.ir

/**
 * @author Moklev Vyacheslav
 */
sealed class IrOperand {
    open fun debugRepresentation(): String = "<ir operand[$type]: $javaClass>"

    abstract val type: OperandType
}

class IntegerOperand(val stringValue: String, override val type: OperandType = OperandType.Int64) : IrOperand() {
    override fun debugRepresentation(): String = stringValue
}

class VariableOperand(val name: String, override val type: OperandType) : IrOperand() {
    override fun debugRepresentation(): String = name
}

class LabelOperand(val name: String) : IrOperand() {
    override fun debugRepresentation(): String = name

    override val type: OperandType
        get() = OperandType.Label
}