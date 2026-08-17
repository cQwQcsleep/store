package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TypeCheckError;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ElementAvailableCall extends FunctionCall {
    public ElementAvailableCall(QName qName, List<Expression> list) {
        super(qName, list);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public Object evaluateAtCompileTime() {
        return getResult() ? Boolean.TRUE : Boolean.FALSE;
    }

    public boolean getResult() {
        try {
            LiteralExpr literalExpr = (LiteralExpr) argument();
            String value = literalExpr.getValue();
            int iIndexOf = value.indexOf(58);
            if (iIndexOf > 0) {
                value = value.substring(iIndexOf + 1);
            }
            return getParser().elementSupported(literalExpr.getNamespace(), value);
        } catch (ClassCastException unused) {
            return false;
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        methodGenerator.getInstructionList().append(new PUSH(classGenerator.getConstantPool(), getResult()));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public Type typeCheck(SymbolTable symbolTable) throws TypeCheckError {
        if (!(argument() instanceof LiteralExpr)) {
            throw new TypeCheckError(new ErrorMsg(ErrorMsg.NEED_LITERAL_ERR, (Object) Keywords.FUNC_EXT_ELEM_AVAILABLE_STRING, (SyntaxTreeNode) this));
        }
        Type type = Type.Boolean;
        this._type = type;
        return type;
    }
}
