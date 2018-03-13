package compiler

import compiler.assembly.*
import parser.MainParser

/**
 * @author Moklev Vyacheslav
 */
class CompilerVisitor(val trace: DataTrace, val builder: AssemblyBuilder) : AbstractVisitor<Any?>() {
    override fun visitFile(ctx: MainParser.FileContext) {
        with (builder) {
            metainfo(".data")
            metainfo("fmt: .string \"result: %d\\n\"")
            metainfo(".globl _main")
            metainfo(".text")
        }
        ctx.functions.forEach { 
            visitFunction(it)
        }
    }

    override fun visitFunction(ctx: MainParser.FunctionContext) {
        with (builder) {
            label(ctx.name.text)
            visitStmtList(ctx.stmtList())
            ret()
        }
    }
    
    override fun visitPlusMinus(ctx: MainParser.PlusMinusContext) {
        visitExpr(ctx.e1)
        visitExpr(ctx.e2)
        with (builder) {
            pop(ECX)
            pop(EAX)
            when (ctx.op.text) {
                "+" -> add(EAX, ECX)
                "-" -> sub(EAX, ECX)
            }
            push(EAX)
        }
    }

    override fun visitMulDiv(ctx: MainParser.MulDivContext) {
        visitExpr(ctx.e1)
        visitExpr(ctx.e2)
        with (builder) {
            pop(ECX)
            pop(EAX)
            when (ctx.op.text) {
                "*" -> {
                    imul(EAX, ECX)
                    push(EAX)
                }
                "/" -> {
                    cdq()
                    idiv(ECX)
                    push(EAX)
                }
                "%" -> {
                    cdq()
                    idiv(ECX)
                    push(EDX)
                }
            }
        } 
    }

    override fun visitParens(ctx: MainParser.ParensContext) {
        visitExpr(ctx.expr())
    }

    override fun visitConst(ctx: MainParser.ConstContext) {
        with (builder) {
            push(IntegerOperand(ctx.INT().text), OperandSize.Long)
        }
    }

    override fun visitCall(ctx: MainParser.CallContext) {
        val descriptors = trace.functionDescriptors.get(ctx.IDENT().text) 
                ?: throw Exception("Unknown function")
        val singleDescriptor = descriptors.single()
        with (builder) {
            call(LabelOperand(singleDescriptor.name))
            push(EAX)
        }
    }

    override fun visitStmtList(ctx: MainParser.StmtListContext) {
        ctx.statements.forEach { 
            visitStmt(it)
        }
    }
    
    override fun visitWrite(ctx: MainParser.WriteContext) {
        visitExpr(ctx.expr())
        with (builder) {
            push(LabelOperand("fmt", true), OperandSize.Long)
            call(LabelOperand("_printf"))
            add(ESP, IntegerOperand("8"))
        }
    }

    override fun visitReturn(ctx: MainParser.ReturnContext) {
        visitExpr(ctx.expr())
        with (builder) {
            pop(EAX)
            ret()
        }
    }
}