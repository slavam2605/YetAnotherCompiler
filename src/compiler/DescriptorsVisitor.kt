package compiler

import parser.MainParser
import parser.MainParserBaseVisitor

/**
 * @author Moklev Vyacheslav
 */
class DescriptorsVisitor(val trace: DataTrace) : MainParserBaseVisitor<Any?>() {
    override fun visitFunction(ctx: MainParser.FunctionContext) {
        val name = ctx.name.text
        val list = trace.functionDescriptors.get(name) ?: arrayListOf()
        list.add(FunctionDescriptor(name))
        trace.functionDescriptors[name] = list
    }
}