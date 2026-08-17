package com.sun.org.apache.xalan.internal.xsltc.compiler;

import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ClassGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.MethodGenerator;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.NodeSetType;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class VariableRef extends VariableRefBase {
    public VariableRef(Variable variable) {
        super(variable);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.Expression, com.sun.org.apache.xalan.internal.xsltc.compiler.SyntaxTreeNode
    public void translate(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (this._type.implementedAsMethod()) {
            return;
        }
        String escapedName = this._variable.getEscapedName();
        String signature = this._type.toSignature();
        if (!this._variable.isLocal()) {
            String className = classGenerator.getClassName();
            instructionList.append(classGenerator.loadTranslet());
            if (classGenerator.isExternal()) {
                instructionList.append(new CHECKCAST(constantPool.addClass(className)));
            }
            instructionList.append(new GETFIELD(constantPool.addFieldref(className, escapedName, signature)));
        } else if (classGenerator.isExternal()) {
            Closure parentClosure = this._closure;
            while (parentClosure != null && !parentClosure.inInnerClass()) {
                parentClosure = parentClosure.getParentClosure();
            }
            if (parentClosure != null) {
                instructionList.append(Constants.ALOAD_0);
                instructionList.append(new GETFIELD(constantPool.addFieldref(parentClosure.getInnerClassName(), escapedName, signature)));
            } else {
                instructionList.append(this._variable.loadInstruction());
            }
        } else {
            instructionList.append(this._variable.loadInstruction());
        }
        if (this._variable.getType() instanceof NodeSetType) {
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.NODE_ITERATOR, "cloneIterator", "()Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), 1));
        }
    }
}
