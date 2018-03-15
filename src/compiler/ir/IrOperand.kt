package compiler.ir

/**
 * @author Moklev Vyacheslav
 */
sealed class IrOperand {
    open fun debugRepresentation(): String = "<ir operand: $javaClass>"
}

class IntegerOperand(val stringValue: String) : IrOperand() {
    override fun debugRepresentation(): String = stringValue
}

class VariableOperand(val name: String) : IrOperand() {
    override fun debugRepresentation(): String = name
}

class LabelOperand(val name: String) : IrOperand() {
    override fun debugRepresentation(): String = name
}