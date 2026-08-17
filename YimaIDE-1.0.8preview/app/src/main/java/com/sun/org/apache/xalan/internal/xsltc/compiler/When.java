package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.BooleanType;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class When extends Instruction {
    private boolean _ignore = false;
    private Expression _test;

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void display(int i) {
        indent(i);
        Util.println("When");
        int i2 = i + 4;
        indent(i2);
        System.out.print("test ");
        Util.println(this._test.toString());
        displayContents(i2);
    }

    public Expression getTest() {
        return this._test;
    }

    public boolean ignore() {
        return this._ignore;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void parseContents(Parser parser) {
        Expression expression = parser.parseExpression(this, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_TEST, null);
        this._test = expression;
        Object objEvaluateAtCompileTime = expression.evaluateAtCompileTime();
        if (objEvaluateAtCompileTime != null && (objEvaluateAtCompileTime instanceof Boolean)) {
            this._ignore = !((Boolean) objEvaluateAtCompileTime).booleanValue();
        }
        parseChildren(parser);
        if (this._test.isDummy()) {
            reportError(this, parser, ErrorMsg.REQUIRED_ATTR_ERR, com.sun.org.apache.xalan.internal.templates.Constants.ATTRNAME_TEST);
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Instruction, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        getParser().reportError(3, new ErrorMsg(ErrorMsg.STRAY_WHEN_ERR, (SyntaxTreeNode) this));
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
