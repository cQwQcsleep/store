package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.generic.ALOAD;
import com.sun.org.apache.bcel.internal.generic.ASTORE;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.FlowList;
import com.sun.org.apache.xpath.internal.compiler.Keywords;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ReferenceType extends Type {
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction LOAD(int i) {
        return new ALOAD(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction STORE(int i) {
        return new ASTORE(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean identicalTo(Type type) {
        return this == type;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return com.sun.org.apache.bcel.internal.generic.Type.OBJECT;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toSignature() {
        return Constants.OBJECT_SIG;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toString() {
        return "reference";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateFrom(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        if (cls.getName().equals(Constants.OBJECT_CLASS)) {
            methodGenerator.getInstructionList().append(Constants.NOP);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), cls.getName()));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int iAddMethodref = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "referenceToLong", "(Ljava/lang/Object;)J");
        int iAddMethodref2 = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "referenceToDouble", "(Ljava/lang/Object;)D");
        int iAddMethodref3 = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "referenceToBoolean", "(Ljava/lang/Object;)Z");
        if (cls.getName().equals(Constants.OBJECT_CLASS)) {
            instructionList.append(Constants.NOP);
            return;
        }
        if (cls == Double.TYPE) {
            instructionList.append(new INVOKESTATIC(iAddMethodref2));
            return;
        }
        if (cls.getName().equals(Constants.DOUBLE_CLASS)) {
            instructionList.append(new INVOKESTATIC(iAddMethodref2));
            Type.Real.translateTo(classGenerator, methodGenerator, Type.Reference);
            return;
        }
        if (cls == Float.TYPE) {
            instructionList.append(new INVOKESTATIC(iAddMethodref2));
            instructionList.append(Constants.D2F);
            return;
        }
        if (cls.getName().equals("java.lang.String")) {
            int iAddMethodref4 = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "referenceToString", "(Ljava/lang/Object;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)Ljava/lang/String;");
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new INVOKESTATIC(iAddMethodref4));
            return;
        }
        if (cls.getName().equals("org.w3c.dom.Node")) {
            int iAddMethodref5 = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "referenceToNode", "(Ljava/lang/Object;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)Lorg/w3c/dom/Node;");
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new INVOKESTATIC(iAddMethodref5));
            return;
        }
        if (cls.getName().equals("org.w3c.dom.NodeList")) {
            int iAddMethodref6 = constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "referenceToNodeList", "(Ljava/lang/Object;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)Lorg/w3c/dom/NodeList;");
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(new INVOKESTATIC(iAddMethodref6));
            return;
        }
        if (cls.getName().equals(Constants.DOM_INTF)) {
            translateTo(classGenerator, methodGenerator, Type.ResultTree);
            return;
        }
        if (cls == Long.TYPE) {
            instructionList.append(new INVOKESTATIC(iAddMethodref));
            return;
        }
        if (cls == Integer.TYPE) {
            instructionList.append(new INVOKESTATIC(iAddMethodref));
            instructionList.append(Constants.L2I);
            return;
        }
        if (cls == Short.TYPE) {
            instructionList.append(new INVOKESTATIC(iAddMethodref));
            instructionList.append(Constants.L2I);
            instructionList.append(Constants.I2S);
            return;
        }
        if (cls == Byte.TYPE) {
            instructionList.append(new INVOKESTATIC(iAddMethodref));
            instructionList.append(Constants.L2I);
            instructionList.append(Constants.I2B);
        } else if (cls == Character.TYPE) {
            instructionList.append(new INVOKESTATIC(iAddMethodref));
            instructionList.append(Constants.L2I);
            instructionList.append(Constants.I2C);
        } else if (cls == Boolean.TYPE) {
            instructionList.append(new INVOKESTATIC(iAddMethodref3));
        } else if (cls.getName().equals(Constants.BOOLEAN_CLASS)) {
            instructionList.append(new INVOKESTATIC(iAddMethodref3));
            Type.Boolean.translateTo(classGenerator, methodGenerator, Type.Reference);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), cls.getName()));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public FlowList translateToDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        translateTo(classGenerator, methodGenerator, booleanType);
        return new FlowList(instructionList.append((BranchInstruction) new IFEQ(null)));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateUnBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, StringType stringType) {
        int localIndex = methodGenerator.getLocalIndex(Keywords.FUNC_CURRENT_STRING);
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (localIndex < 0) {
            instructionList.append(new PUSH(constantPool, 0));
        } else {
            instructionList.append(new ILOAD(localIndex));
        }
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "stringF", "(Ljava/lang/Object;ILcom/sun/org/apache/xalan/internal/xsltc/DOM;)Ljava/lang/String;")));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, RealType realType) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "numberF", "(Ljava/lang/Object;Lcom/sun/org/apache/xalan/internal/xsltc/DOM;)D")));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        methodGenerator.getInstructionList().append(new INVOKESTATIC(classGenerator.getConstantPool().addMethodref(Constants.BASIS_LIBRARY_CLASS, "booleanF", "(Ljava/lang/Object;)Z")));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, NodeSetType nodeSetType) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(new INVOKESTATIC(constantPool.addMethodref(Constants.BASIS_LIBRARY_CLASS, "referenceToNodeSet", "(Ljava/lang/Object;)Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;")));
        instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.NODE_ITERATOR, Constants.RESET, "()Lcom/sun/org/apache/xml/internal/dtm/DTMAxisIterator;"), 1));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, NodeType nodeType) {
        Type type = Type.NodeSet;
        translateTo(classGenerator, methodGenerator, type);
        type.translateTo(classGenerator, methodGenerator, nodeType);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, ResultTreeType resultTreeType) {
        methodGenerator.getInstructionList().append(new INVOKESTATIC(classGenerator.getConstantPool().addMethodref(Constants.BASIS_LIBRARY_CLASS, "referenceToResultTree", "(Ljava/lang/Object;)Lcom/sun/org/apache/xalan/internal/xsltc/DOM;")));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, ObjectType objectType) {
        methodGenerator.getInstructionList().append(Constants.NOP);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Type type) {
        if (type == Type.String) {
            translateTo(classGenerator, methodGenerator, (StringType) type);
            return;
        }
        if (type == Type.Real) {
            translateTo(classGenerator, methodGenerator, (RealType) type);
            return;
        }
        if (type == Type.Boolean) {
            translateTo(classGenerator, methodGenerator, (BooleanType) type);
            return;
        }
        if (type == Type.NodeSet) {
            translateTo(classGenerator, methodGenerator, (NodeSetType) type);
            return;
        }
        if (type == Type.Node) {
            translateTo(classGenerator, methodGenerator, (NodeType) type);
            return;
        }
        if (type == Type.ResultTree) {
            translateTo(classGenerator, methodGenerator, (ResultTreeType) type);
            return;
        }
        if (type == Type.Object) {
            translateTo(classGenerator, methodGenerator, (ObjectType) type);
        } else {
            if (type == Type.Reference) {
                return;
            }
            classGenerator.getParser().reportError(2, new ErrorMsg(ErrorMsg.INTERNAL_ERR, type.toString()));
        }
    }
}
