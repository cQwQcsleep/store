package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class LocalNameCall extends NameBase {
    public LocalNameCall(QName qName) {
        super(qName);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.NameBase, com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.DOM_INTF, "getNodeName", "(I)Ljava/lang/String;");
        int iAddMethodref = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "getLocalName", Constants.GET_UNPARSED_ENTITY_URI_SIG);
        super.translate(classGenerator, methodGenerator);
        instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 2));
        instructionList.append(new INVOKESTATIC(iAddMethodref));
    }

    public LocalNameCall(QName qName, List<Expression> list) {
        super(qName, list);
    }
}
