package compiler

import compiler.ir.OperandType
import parser.MainParser
import parser.MainParserBaseVisitor

/**
 * @author Moklev Vyacheslav
 */
class DescriptorsVisitor(val trace: DataTrace) : MainParserBaseVisitor<Any?>() {
    override fun visitFunction(ctx: MainParser.FunctionContext) {
        val name = ctx.name.text
        val list = trace.functionDescriptors.get(name) ?: arrayListOf()
        list.add(FunctionDescriptor(name, OperandType.Int64)) // TODO: instead of Int64 should infer a type from declaration
        trace.functionDescriptors[name] = list
    }
}