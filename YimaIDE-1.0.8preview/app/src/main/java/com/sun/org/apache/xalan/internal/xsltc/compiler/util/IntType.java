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
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.ISTORE;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionConst;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.FlowList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class IntType extends NumberType {
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction ADD() {
        return InstructionConst.IADD;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction DIV() {
        return InstructionConst.IDIV;
    }

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
    public Instruction MUL() {
        return InstructionConst.IMUL;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction NEG() {
        return InstructionConst.INEG;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction REM() {
        return InstructionConst.IREM;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction STORE(int i) {
        return new ISTORE(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction SUB() {
        return InstructionConst.ISUB;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public int distanceTo(Type type) {
        if (type == this) {
            return 0;
        }
        return type == Type.Real ? 1 : Integer.MAX_VALUE;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean identicalTo(Type type) {
        return this == type;
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
        return "int";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        translateTo(classGenerator, methodGenerator, Type.Reference);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (cls == Character.TYPE) {
            instructionList.append(Constants.I2C);
            return;
        }
        if (cls == Byte.TYPE) {
            instructionList.append(Constants.I2B);
            return;
        }
        if (cls == Short.TYPE) {
            instructionList.append(Constants.I2S);
            return;
        }
        if (cls == Integer.TYPE) {
            instructionList.append(Constants.NOP);
            return;
        }
        if (cls == Long.TYPE) {
            instructionList.append(Constants.I2L);
            return;
        }
        if (cls == Float.TYPE) {
            instructionList.append(Constants.I2F);
            return;
        }
        if (cls == Double.TYPE) {
            instructionList.append(Constants.I2D);
        } else if (cls.isAssignableFrom(Double.class)) {
            instructionList.append(Constants.I2D);
            Type.Real.translateTo(classGenerator, methodGenerator, Type.Reference);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), cls.getName()));
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
        instructionList.append(new CHECKCAST(constantPool.addClass(Constants.INTEGER_CLASS)));
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.INTEGER_CLASS, Constants.INT_VALUE, "()I")));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, RealType realType) {
        methodGenerator.getInstructionList().append(Constants.I2D);
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, StringType stringType) {
        methodGenerator.getInstructionList().append(new INVOKESTATIC(classGenerator.getConstantPool().addMethodref(Constants.INTEGER_CLASS, "toString", "(I)Ljava/lang/String;")));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new IFEQ(null));
        instructionList.append(Constants.ICONST_1);
        BranchHandle branchHandleAppend2 = instructionList.append((BranchInstruction) new GOTO(null));
        branchHandleAppend.setTarget(instructionList.append(Constants.ICONST_0));
        branchHandleAppend2.setTarget(instructionList.append(Constants.NOP));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, ReferenceType referenceType) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(new NEW(constantPool.addClass(Constants.INTEGER_CLASS)));
        instructionList.append(Constants.DUP_X1);
        instructionList.append(Constants.SWAP);
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.INTEGER_CLASS, Const.CONSTRUCTOR_NAME, "(I)V")));
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Type type) {
        if (type == Type.Real) {
            translateTo(classGenerator, methodGenerator, (RealType) type);
            return;
        }
        if (type == Type.String) {
            translateTo(classGenerator, methodGenerator, (StringType) type);
            return;
        }
        if (type == Type.Boolean) {
            translateTo(classGenerator, methodGenerator, (BooleanType) type);
        } else if (type == Type.Reference) {
            translateTo(classGenerator, methodGenerator, (ReferenceType) type);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString()));
        }
    }
}
