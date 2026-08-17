package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKEINTERFACE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.FlowList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class NodeType extends Type {
    private final int _type;

    public NodeType(int i) {
        this._type = i;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction LOAD(int i) {
        return new ILOAD(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction STORE(int i) {
        return new ISTORE(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String getClassName() {
        return Constants.RUNTIME_NODE_CLASS;
    }

    public int getType() {
        return this._type;
    }

    public int hashCode() {
        return this._type;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean identicalTo(Type type) {
        return type instanceof NodeType;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return com.sun.org.apache.bcel.internal.generic.Type.INT;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toSignature() {
        return "I";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toString() {
        return "node-type";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        translateTo(classGenerator, methodGenerator, Type.Reference);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        String name = cls.getName();
        if (name.equals("java.lang.String")) {
            translateTo(classGenerator, methodGenerator, Type.String);
            return;
        }
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(Constants.SWAP);
        if (name.equals("org.w3c.dom.Node") || name.equals(Constants.OBJECT_CLASS)) {
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.MAKE_NODE, Constants.MAKE_NODE_SIG), 2));
        } else if (name.equals("org.w3c.dom.NodeList")) {
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.MAKE_NODE_LIST, Constants.MAKE_NODE_LIST_SIG), 2));
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), name));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public FlowList translateToDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        return new FlowList(methodGenerator.getInstructionList().append((BranchInstruction) new IFEQ(null)));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateUnBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(new CHECKCAST(constantPool.addClass(Constants.RUNTIME_NODE_CLASS)));
        instructionList.append(new GETFIELD(constantPool.addFieldref(Constants.RUNTIME_NODE_CLASS, "node", "I")));
    }

    public NodeType() {
        this(-1);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, StringType stringType) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        int i = this._type;
        if (i != -1) {
            if (i != 1) {
                if (i != 2 && i != 7 && i != 8) {
                    if (i != 9) {
                        classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), stringType.toString()));
                        return;
                    }
                }
            }
            instructionList.append(methodGenerator.loadDOM());
            instructionList.append(Constants.SWAP);
            instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_ELEMENT_VALUE, "(I)Ljava/lang/String;"), 2));
            return;
        }
        instructionList.append(methodGenerator.loadDOM());
        instructionList.append(Constants.SWAP);
        instructionList.append(new INVOKEINTERFACE(constantPool.addInterfaceMethodref(Constants.DOM_INTF, Constants.GET_NODE_VALUE, "(I)Ljava/lang/String;"), 2));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        FlowList flowListTranslateToDesynthesized = translateToDesynthesized(classGenerator, methodGenerator, booleanType);
        instructionList.append(Constants.ICONST_1);
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        flowListTranslateToDesynthesized.backPatch(instructionList.append(Constants.ICONST_0));
        branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, RealType realType) {
        Type type = Type.String;
        translateTo(classGenerator, methodGenerator, type);
        type.translateTo(classGenerator, methodGenerator, Type.Real);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, NodeSetType nodeSetType) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(new NEW(constantPool.addClass(Constants.SINGLETON_ITERATOR)));
        instructionList.append(Constants.DUP_X1);
        instructionList.append(Constants.SWAP);
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.SINGLETON_ITERATOR, Const.CONSTRUCTOR_NAME, "(I)V")));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, ObjectType objectType) {
        methodGenerator.getInstructionList().append(Constants.NOP);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, ReferenceType referenceType) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(new NEW(constantPool.addClass(Constants.RUNTIME_NODE_CLASS)));
        instructionList.append(Constants.DUP_X1);
        instructionList.append(Constants.SWAP);
        instructionList.append(new PUSH(constantPool, this._type));
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.RUNTIME_NODE_CLASS, Const.CONSTRUCTOR_NAME, "(II)V")));
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
        if (type == Type.NodeSet) {
            translateTo(classGenerator, methodGenerator, (NodeSetType) type);
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
