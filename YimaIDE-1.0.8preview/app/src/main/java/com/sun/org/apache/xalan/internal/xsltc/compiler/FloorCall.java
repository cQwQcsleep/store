package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xpath.internal.compiler.Keywords;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class FloorCall extends FunctionCall {
    public FloorCall(QName qName, List<Expression> list) {
        super(qName, list);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        argument().translate(classGenerator, methodGenerator);
        methodGenerator.getInstructionList().append(new INVOKESTATIC(classGenerator.getConstantPool().addMethodref(Constants.MATH_CLASS, Keywords.FUNC_FLOOR_STRING, "(D)D")));
    }
}
