package compiler.assembly

/**
 * @author Moklev Vyacheslav
 */
enum class OperandSize(val suffix: String) {
    /** 8 bit int */
    Byte("b"), 
    /** 16 bit int or 32 bit float */
    Short("s"),
    /** 16 bit */
    Word("w"),
    /** 32 bit int or 64 bit float */
    Long("l"),
    /** 64 bit */
    Quad("q"),
    /** 80 bit float */
    Ten("t")   
}