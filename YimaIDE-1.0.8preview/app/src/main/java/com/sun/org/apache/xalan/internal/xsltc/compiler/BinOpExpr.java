package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class BinOpExpr extends Expression {
    public static final int DIV = 3;
    public static final int MINUS = 1;
    public static final int MOD = 4;
    private static final String[] Ops = {"+", "-", "*", PsuedoNames.PSEUDONAME_ROOT, "%"};
    public static final int PLUS = 0;
    public static final int TIMES = 2;
    private Expression _left;
    private int _op;
    private Expression _right;

    public BinOpExpr(int i, Expression expression, Expression expression2) {
        this._op = i;
        this._left = expression;
        expression.setParent(this);
        this._right = expression2;
        expression2.setParent(this);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasLastCall() {
        return this._left.hasLastCall() || this._right.hasLastCall();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasPositionCall() {
        return this._left.hasPositionCall() || this._right.hasPositionCall();
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void setParser(Parser parser) {
        super.setParser(parser);
        this._left.setParser(parser);
        this._right.setParser(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public String toString() {
        return Ops[this._op] + '(' + this._left + ", " + this._right + ')';
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        this._left.translate(classGenerator, methodGenerator);
        this._right.translate(classGenerator, methodGenerator);
        int i = this._op;
        if (i == 0) {
            instructionList.append(this._type.ADD());
            return;
        }
        if (i == 1) {
            instructionList.append(this._type.SUB());
            return;
        }
        if (i == 2) {
            instructionList.append(this._type.MUL());
            return;
        }
        if (i == 3) {
            instructionList.append(this._type.DIV());
        } else if (i == 4) {
            instructionList.append(this._type.REM());
        } else {
            getParser().reportError(3, new ErrorMsg(ErrorMsg.ILLEGAL_BINARY_OP_ERR, (SyntaxTreeNode) this));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        Type typeTypeCheck = this._left.typeCheck(symbolTable);
        Type typeTypeCheck2 = this._right.typeCheck(symbolTable);
        MethodType methodTypeLookupPrimop = lookupPrimop(symbolTable, Ops[this._op], new MethodType(Type.Void, typeTypeCheck, typeTypeCheck2));
        if (methodTypeLookupPrimop == null) {
            throw new TypeCheckError(this);
        }
        Type type = methodTypeLookupPrimop.argsType().get(0);
        if (!type.identicalTo(typeTypeCheck)) {
            this._left = new CastExpr(this._left, type);
        }
        if (!methodTypeLookupPrimop.argsType().get(1).identicalTo(typeTypeCheck2)) {
            this._right = new CastExpr(this._right, type);
        }
        Type typeResultType = methodTypeLookupPrimop.resultType();
        this._type = typeResultType;
        return typeResultType;
    }
}
