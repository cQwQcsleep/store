package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.IFGE;
import com.sun.org.apache.bcel.internal.generic.IFGT;
import com.sun.org.apache.bcel.internal.generic.IFLE;
import com.sun.org.apache.bcel.internal.generic.IFLT;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPGE;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPGT;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPLE;
import com.sun.org.apache.bcel.internal.generic.IF_ICMPLT;
import com.sun.org.apache.bcel.internal.generic.ILOAD;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.PUSH;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class BooleanType extends Type {
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public BranchInstruction GE(boolean z) {
        return z ? new IFGE(null) : new IF_ICMPGE(null);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public BranchInstruction GT(boolean z) {
        return z ? new IFGT(null) : new IF_ICMPGT(null);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public BranchInstruction LE(boolean z) {
        return z ? new IFLE(null) : new IF_ICMPLE(null);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction LOAD(int i) {
        return new ILOAD(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public BranchInstruction LT(boolean z) {
        return z ? new IFLT(null) : new IF_ICMPLT(null);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction STORE(int i) {
        return new ISTORE(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean identicalTo(Type type) {
        return this == type;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean isSimple() {
        return true;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return com.sun.org.apache.bcel.internal.generic.Type.BOOLEAN;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toSignature() {
        return Constants.HASIDCALL_INDEX_SIG;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toString() {
        return "boolean";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        translateTo(classGenerator, methodGenerator, Type.Reference);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateFrom(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        translateTo(classGenerator, methodGenerator, cls);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, StringType stringType) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new IFEQ(null));
        instructionList.append(new PUSH(constantPool, "true"));
        BranchHandle branchHandleAppend2 = instructionList.append((BranchInstruction) new GOTO(null));
        branchHandleAppend.setTarget(instructionList.append(new PUSH(constantPool, "false")));
        branchHandleAppend2.setTarget(instructionList.append(Constants.NOP));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateUnBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(new CHECKCAST(constantPool.addClass(Constants.BOOLEAN_CLASS)));
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.BOOLEAN_CLASS, Constants.BOOLEAN_VALUE, Constants.BOOLEAN_VALUE_SIG)));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Type type) {
        if (type == Type.String) {
            translateTo(classGenerator, methodGenerator, (StringType) type);
            return;
        }
        if (type == Type.Real) {
            translateTo(classGenerator, methodGenerator, (RealType) type);
        } else if (type == Type.Reference) {
            translateTo(classGenerator, methodGenerator, (ReferenceType) type);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString()));
        }
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, RealType realType) {
        methodGenerator.getInstructionList().append(Constants.I2D);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, ReferenceType referenceType) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(new NEW(constantPool.addClass(Constants.BOOLEAN_CLASS)));
        instructionList.append(Constants.DUP_X1);
        instructionList.append(Constants.SWAP);
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.BOOLEAN_CLASS, Const.CONSTRUCTOR_NAME, "(Z)V")));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        if (cls == Boolean.TYPE) {
            methodGenerator.getInstructionList().append(Constants.NOP);
        } else if (cls.isAssignableFrom(Boolean.class)) {
            translateTo(classGenerator, methodGenerator, Type.Reference);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), cls.getName()));
        }
    }
}
