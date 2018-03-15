package compiler

import parser.MainParser
import parser.MainParserBaseVisitor

/**
 * @author Moklev Vyacheslav
 */
abstract class AbstractVisitor<T> : MainParserBaseVisitor<T>() {
    open fun visitStmt(ctx: MainParser.StmtContext): T {
        return when (ctx) {
            is MainParser.WriteContext -> visitWrite(ctx)
            is MainParser.ReturnContext -> visitReturn(ctx)
            else -> throw IllegalStateException("Unknown context: $ctx")
        }
    }
    
    open fun visitExpr(ctx: MainParser.ExprContext): T {
        return when (ctx) {
            is MainParser.ConstContext -> visitConst(ctx)
            is MainParser.ParensContext -> visitParens(ctx)
            is MainParser.MulDivContext -> visitMulDiv(ctx)
            is MainParser.PlusMinusContext -> visitPlusMinus(ctx)
            is MainParser.CallContext -> visitCall(ctx)
            else -> throw IllegalStateException("Unknown context: $ctx")
        }
    }
}