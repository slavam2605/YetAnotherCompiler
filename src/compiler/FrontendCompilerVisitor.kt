package compiler

import compiler.ir.*
import parser.MainParser

/**
 * @author Moklev Vyacheslav
 */
class FrontendCompilerVisitor(val trace: DataTrace, val builder: IrBuilder) : AbstractVisitor<Any?>() {
    override fun visitFile(ctx: MainParser.FileContext) {
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

    override fun visitPlusMinus(ctx: MainParser.PlusMinusContext): IrOperand {
        val a = visitExpr(ctx.e1)
        val b = visitExpr(ctx.e2)
        with (builder) {
            val temp = tempVar()
            when (ctx.op.text) {
                "+" -> add(temp, a, b)
                "-" -> sub(temp, a, b)
            }
            return temp
        }
    }

    override fun visitMulDiv(ctx: MainParser.MulDivContext): IrOperand {
        val a = visitExpr(ctx.e1)
        val b = visitExpr(ctx.e2)
        with (builder) {
            val temp = tempVar()
            when (ctx.op.text) {
                "*" -> mul(temp, a, b)
                "/" -> div(temp, a, b)
                "%" -> mod(temp, a, b)
            }
            return temp
        }
    }

    override fun visitParens(ctx: MainParser.ParensContext): IrOperand {
        return visitExpr(ctx.expr())
    }

    override fun visitConst(ctx: MainParser.ConstContext): IrOperand {
        return IntegerOperand(ctx.INT().text)
    }

    override fun visitCall(ctx: MainParser.CallContext): IrOperand {
        val descriptors = trace.functionDescriptors.get(ctx.IDENT().text)
                ?: throw Exception("Unknown function")
        val singleDescriptor = descriptors.single()
        with (builder) {
            val temp = tempVar()
            callval(singleDescriptor.name, temp)
            return temp
        }
    }

    override fun visitStmtList(ctx: MainParser.StmtListContext) {
        ctx.statements.forEach {
            visitStmt(it)
        }
    }

    override fun visitWrite(ctx: MainParser.WriteContext) {
        val a = visitExpr(ctx.expr())
        with (builder) {
            call("_printf", LabelOperand("fmt"), a)
        }
    }

    override fun visitReturn(ctx: MainParser.ReturnContext) {
        val a = visitExpr(ctx.expr())
        with (builder) {
            retval(a)
        }
    }

    override fun visitExpr(ctx: MainParser.ExprContext): IrOperand {
        return super.visitExpr(ctx) as IrOperand
    }
}