package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.CompareGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.TestGenerator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class LastCall extends FunctionCall {
    public LastCall(QName qName) {
        super(qName);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasLastCall() {
        return true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression
    public boolean hasPositionCall() {
        return true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.FunctionCall, com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (methodGenerator instanceof CompareGenerator) {
            instructionList.append(((CompareGenerator) methodGenerator).loadLastNode());
        } else {
            if (methodGenerator instanceof TestGenerator) {
                instructionList.append(new ILOAD(3));
                return;
            }
            int iAddInterfaceMethodref = classGenerator.getConstantPool().addInterfaceMethodref(Constants.NODE_ITERATOR, "getLast", "()I");
            instructionList.append(methodGenerator.loadIterator());
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 1));
        }
    }
}
