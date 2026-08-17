package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class LogicalExpr extends Expression {
    public static final int AND = 1;
    public static final int OR = 0;
    private static final String[] Ops = {"or", "and"};
    private Expression _left;
    private final int _op;
    private Expression _right;

    public LogicalExpr(int i, Expression expression, Expression expression2) {
        this._op = i;
        this._left = expression;
        expression.setParent(this);
        this._right = expression2;
        expression2.setParent(this);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public Object evaluateAtCompileTime() {
        Object objEvaluateAtCompileTime = this._left.evaluateAtCompileTime();
        Object objEvaluateAtCompileTime2 = this._right.evaluateAtCompileTime();
        if (objEvaluateAtCompileTime == null || objEvaluateAtCompileTime2 == null) {
            return null;
        }
        if (this._op == 1) {
            Boolean bool = Boolean.TRUE;
            return (objEvaluateAtCompileTime == bool && objEvaluateAtCompileTime2 == bool) ? bool : Boolean.FALSE;
        }
        Boolean bool2 = Boolean.TRUE;
        return (objEvaluateAtCompileTime == bool2 || objEvaluateAtCompileTime2 == bool2) ? bool2 : Boolean.FALSE;
    }

    public int getOp() {
        return this._op;
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
        translateDesynthesized(classGenerator, methodGenerator);
        synthesize(classGenerator, methodGenerator);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public void translateDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        getParent();
        int i = this._op;
        Expression expression = this._left;
        if (i != 1) {
            expression.translateDesynthesized(classGenerator, methodGenerator);
            BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
            this._right.translateDesynthesized(classGenerator, methodGenerator);
            this._left._trueList.backPatch(branchHandleAppend);
            this._left._falseList.backPatch(branchHandleAppend.getNext());
            this._falseList.append(this._right._falseList);
            this._trueList.add(branchHandleAppend).append(this._right._trueList);
            return;
        }
        expression.translateDesynthesized(classGenerator, methodGenerator);
        com.sun.org.apache.bcel.internal.generic.Instruction instruction = Constants.NOP;
        InstructionHandle instructionHandleAppend = instructionList.append(instruction);
        this._right.translateDesynthesized(classGenerator, methodGenerator);
        InstructionHandle instructionHandleAppend2 = instructionList.append(instruction);
        this._falseList.append(this._right._falseList.append(this._left._falseList));
        Expression expression2 = this._left;
        if ((expression2 instanceof LogicalExpr) && ((LogicalExpr) expression2).getOp() == 0) {
            this._left.backPatchTrueList(instructionHandleAppend);
        } else {
            Expression expression3 = this._left;
            if (expression3 instanceof NotCall) {
                expression3.backPatchTrueList(instructionHandleAppend);
            } else {
                this._trueList.append(expression3._trueList);
            }
        }
        Expression expression4 = this._right;
        if ((expression4 instanceof LogicalExpr) && ((LogicalExpr) expression4).getOp() == 0) {
            this._right.backPatchTrueList(instructionHandleAppend2);
            return;
        }
        Expression expression5 = this._right;
        if (expression5 instanceof NotCall) {
            expression5.backPatchTrueList(instructionHandleAppend2);
        } else {
            this._trueList.append(expression5._trueList);
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
