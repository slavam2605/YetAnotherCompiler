package compiler

import parser.MainParser
import parser.MainParserBaseVisitor

/**
 * @author Moklev Vyacheslav
 */
abstract class AbstractVisitor<T> : MainParserBaseVisitor<T>() {
    fun visitStmt(ctx: MainParser.StmtContext) {
        when (ctx) {
            is MainParser.WriteContext -> visitWrite(ctx)
            is MainParser.ReturnContext -> visitReturn(ctx)
        }
    }
    
    fun visitExpr(ctx: MainParser.ExprContext) {
        when (ctx) {
            is MainParser.ConstContext -> visitConst(ctx)
            is MainParser.ParensContext -> visitParens(ctx)
            is MainParser.MulDivContext -> visitMulDiv(ctx)
            is MainParser.PlusMinusContext -> visitPlusMinus(ctx)
            is MainParser.CallContext -> visitCall(ctx)
        }
    }
}