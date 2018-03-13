package compiler.assembly

/**
 * @author Moklev Vyacheslav
 */
sealed class AssemblyOperand {
    abstract fun asmRepresentation(): String
}

class IntegerOperand(val stringValue: String) : AssemblyOperand() {
    override fun asmRepresentation(): String = "$" + stringValue
}

class RegisterOperand(val name: String) : AssemblyOperand() {
    override fun asmRepresentation(): String = "%" + name
}

/**
 * Addres of form `[baseReg + offsetReg * factor + offset]` or `offset(baseReg, offsetReg, factor)` 
 */
class MemoryAddressOperand(val baseReg: RegisterOperand? = null, 
                           val offsetReg: RegisterOperand? = null,
                           val factor: Int = 0,
                           val offset: Int = 0) : AssemblyOperand() {
    override fun asmRepresentation(): String {
        return when {
            baseReg == null && offsetReg == null -> "$offset"
            baseReg != null && offsetReg == null -> "$offset($baseReg)"
            baseReg != null && offsetReg != null -> "$offset($baseReg, $offsetReg, $factor)"
            else -> throw Exception(toString())
        } 
    }
}

class LabelOperand(val name: String, val asImm: Boolean = false) : AssemblyOperand() {
    override fun asmRepresentation(): String = (if (asImm) "$" else "") + name
}

val EAX = RegisterOperand("eax")
val EBX = RegisterOperand("ebx")
val ECX = RegisterOperand("ecx")
val EDX = RegisterOperand("edx")
val ESI = RegisterOperand("esi")
val EDI = RegisterOperand("edi")
val ESP = RegisterOperand("esp")
val EBP = RegisterOperand("ebp")