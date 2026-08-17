package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.InstructionHandle;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class If extends Instruction {
    private boolean _ignore = false;
    private Expression _test;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("If");
        int i2 = i + 4;
        indent(i2);
        System.out.print("test ");
        Util.println(this._test.toString());
        displayContents(i2);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        Expression expression = parser.parseExpression(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_TEST, null);
        this._test = expression;
        if (expression.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_TEST);
            return;
        }
        Object objEvaluateAtCompileTime = this._test.evaluateAtCompileTime();
        if (objEvaluateAtCompileTime != null && (objEvaluateAtCompileTime instanceof Boolean)) {
            this._ignore = !((Boolean) objEvaluateAtCompileTime).booleanValue();
        }
        parseChildren(parser);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        this._test.translateDesynthesized(classGenerator, methodGenerator);
        InstructionHandle end = instructionList.getEnd();
        if (!this._ignore) {
            translateContents(classGenerator, methodGenerator);
        }
        this._test.backPatchFalseList(instructionList.append(Constants.NOP));
        this._test.backPatchTrueList(end.getNext());
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        if (!(this._test.typeCheck(symbolTable) instanceof BooleanType)) {
            this._test = new CastExpr(this._test, Type.Boolean);
        }
        if (!this._ignore) {
            typeCheckContents(symbolTable);
        }
        return Type.Void;
    }
}
