package compiler.ir

/**
 * @author Moklev Vyacheslav
 */
enum class OperandType(val debugRepresentation: String) {
    Unknown("<unknown>"),
    Void("void"),
    Label("label"),
    Int64("i64")
}