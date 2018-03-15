package compiler.ir

/**
 * @author Moklev Vyacheslav
 */
object IrTypeUtils {
    fun add(type1: OperandType, type2: OperandType): OperandType {
        if (type1 == type2) {
            return when (type1) {
                OperandType.Int64 -> OperandType.Int64
                else -> TODO()
            }
        }
        TODO()
    }

    fun sub(type1: OperandType, type2: OperandType): OperandType {
        if (type1 == type2) {
            return when (type1) {
                OperandType.Int64 -> OperandType.Int64
                else -> TODO()
            }
        }
        TODO()
    }

    fun mul(type1: OperandType, type2: OperandType): OperandType {
        if (type1 == type2) {
            return when (type1) {
                OperandType.Int64 -> OperandType.Int64
                else -> TODO()
            }
        }
        TODO()
    }

    fun div(type1: OperandType, type2: OperandType): OperandType {
        if (type1 == type2) {
            return when (type1) {
                OperandType.Int64 -> OperandType.Int64
                else -> TODO()
            }
        }
        TODO()
    }

    fun mod(type1: OperandType, type2: OperandType): OperandType {
        if (type1 == type2) {
            return when (type1) {
                OperandType.Int64 -> OperandType.Int64
                else -> TODO()
            }
        }
        TODO()
    }
}