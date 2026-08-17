package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFLT;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.FlowList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class NodeSetType extends Type {
    private void getFirstNode(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        methodGenerator.getInstructionList().append(new INVOKEINTERFACE(classGenerator.getConstantPool().addInterfaceMethodref(Constants.NODE_ITERATOR, Constants.NEXT, "()I"), 1));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction LOAD(int i) {
        return new ALOAD(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction STORE(int i) {
        return new ASTORE(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String getClassName() {
        return Constants.NODE_ITERATOR;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean identicalTo(Type type) {
        return this == type;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return new com.sun.org.apache.bcel.internal.generic.ObjectType(Constants.NODE_ITERATOR);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toSignature() {
        return "Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toString() {
        return "node-set";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        translateTo(classGenerator, methodGenerator, Type.Reference);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateFrom(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        if (cls.getName().equals("org.w3c.dom.NodeList")) {
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "nodeList2Iterator", "(Lorg/w3c/dom/NodeList;Lcom/sun/org/apache/xalan/internal/xsltc/Translet;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;")));
        } else if (!cls.getName().equals("org.w3c.dom.Node")) {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), cls.getName()));
        } else {
            instructionList.append(classGenerator.loadTranslet());
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "node2Iterator", "(Lorg/w3c/dom/Node;Lcom/sun/org/apache/xalan/internal/xsltc/Translet;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;")));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        String name = cls.getName();
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(Constants.SWAP);
        if (name.equals("org.w3c.dom.Node")) {
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.MAKE_NODE, Constants.MAKE_NODE_SIG2), 2));
            return;
        }
        if (name.equals("org.w3c.dom.NodeList") || name.equals(Constants.OBJECT_CLASS)) {
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.MAKE_NODE_LIST, Constants.MAKE_NODE_LIST_SIG2), 2));
            return;
        }
        if (!name.equals("java.lang.String")) {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), name));
        } else {
            int iAddInterfaceMethodref = constantPool.addInterfaceMethodref(Constants.NODE_ITERATOR, Constants.NEXT, "()I");
            int iAddInterfaceMethodref2 = constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_NODE_VALUE, "(I)Ljava/lang/String;");
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref, 1));
            instructionList.append(new INVOKEINTERFACE(iAddInterfaceMethodref2, 2));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public FlowList translateToDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        getFirstNode(classGenerator, methodGenerator);
        return new FlowList(instructionList.append((BranchInstruction) new IFLT(null)));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateUnBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        methodGenerator.getInstructionList().append(Constants.NOP);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        FlowList flowListTranslateToDesynthesized = translateToDesynthesized(classGenerator, methodGenerator, booleanType);
        instructionList.append(Constants.ICONST_1);
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        flowListTranslateToDesynthesized.backPatch(instructionList.append(Constants.ICONST_0));
        branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, StringType stringType) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        getFirstNode(classGenerator, methodGenerator);
        instructionList.append(Constants.DUP);
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new IFLT(null));
        Type.Node.translateTo(classGenerator, methodGenerator, stringType);
        BranchHandle branchHandleAppend2 = instructionList.append((BranchInstruction) new GOTO(null));
        branchHandleAppend.setTarget(instructionList.append(Constants.POP));
        instructionList.append(new PUSH(classGenerator.getConstantPool(), ""));
        branchHandleAppend2.setTarget(instructionList.append(Constants.NOP));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, RealType realType) {
        Type type = Type.String;
        translateTo(classGenerator, methodGenerator, type);
        type.translateTo(classGenerator, methodGenerator, Type.Real);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, NodeType nodeType) {
        getFirstNode(classGenerator, methodGenerator);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, ObjectType objectType) {
        methodGenerator.getInstructionList().append(Constants.NOP);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, ReferenceType referenceType) {
        methodGenerator.getInstructionList().append(Constants.NOP);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Type type) {
        if (type == Type.String) {
            translateTo(classGenerator, methodGenerator, (StringType) type);
            return;
        }
        if (type == Type.Boolean) {
            translateTo(classGenerator, methodGenerator, (BooleanType) type);
            return;
        }
        if (type == Type.Real) {
            translateTo(classGenerator, methodGenerator, (RealType) type);
            return;
        }
        if (type == Type.Node) {
            translateTo(classGenerator, methodGenerator, (NodeType) type);
            return;
        }
        if (type == Type.Reference) {
            translateTo(classGenerator, methodGenerator, (ReferenceType) type);
        } else if (type == Type.Object) {
            translateTo(classGenerator, methodGenerator, (ObjectType) type);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString()));
        }
    }
}
