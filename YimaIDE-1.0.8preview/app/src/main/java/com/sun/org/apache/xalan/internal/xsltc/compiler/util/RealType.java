package com.sun.org.apache.xalan.internal.xsltc.compiler.util;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.generic.BranchHandle;
import com.sun.org.apache.bcel.internal.generic.BranchInstruction;
import com.sun.org.apache.bcel.internal.generic.CHECKCAST;
import com.sun.org.apache.bcel.internal.generic.ConstantPoolGen;
import com.sun.org.apache.bcel.internal.generic.DLOAD;
import com.sun.org.apache.bcel.internal.generic.DSTORE;
import com.sun.org.apache.bcel.internal.generic.GOTO;
import com.sun.org.apache.bcel.internal.generic.IFEQ;
import com.sun.org.apache.bcel.internal.generic.IFNE;
import com.sun.org.apache.bcel.internal.generic.INVOKESPECIAL;
import com.sun.org.apache.bcel.internal.generic.INVOKESTATIC;
import com.sun.org.apache.bcel.internal.generic.INVOKEVIRTUAL;
import com.sun.org.apache.bcel.internal.generic.Instruction;
import com.sun.org.apache.bcel.internal.generic.InstructionConst;
import com.sun.org.apache.bcel.internal.generic.InstructionList;
import com.sun.org.apache.bcel.internal.generic.LocalVariableGen;
import com.sun.org.apache.bcel.internal.generic.NEW;
import com.sun.org.apache.bcel.internal.generic.StackInstruction;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.FlowList;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class RealType extends NumberType {
    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction ADD() {
        return InstructionConst.DADD;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction CMP(boolean z) {
        return z ? InstructionConst.DCMPG : InstructionConst.DCMPL;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction DIV() {
        return InstructionConst.DDIV;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction DUP() {
        return Constants.DUP2;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction LOAD(int i) {
        return new DLOAD(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction MUL() {
        return InstructionConst.DMUL;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction NEG() {
        return InstructionConst.DNEG;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction POP() {
        return Constants.POP2;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction REM() {
        return InstructionConst.DREM;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction STORE(int i) {
        return new DSTORE(i);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public Instruction SUB() {
        return InstructionConst.DSUB;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public int distanceTo(Type type) {
        if (type == this) {
            return 0;
        }
        return type == Type.Int ? 1 : Integer.MAX_VALUE;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public boolean identicalTo(Type type) {
        return this == type;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public com.sun.org.apache.bcel.internal.generic.Type toJCType() {
        return com.sun.org.apache.bcel.internal.generic.Type.DOUBLE;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toSignature() {
        return "D";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public String toString() {
        return "real";
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        translateTo(classGenerator, methodGenerator, Type.Reference);
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateFrom(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (cls == Character.TYPE || cls == Byte.TYPE || cls == Short.TYPE || cls == Integer.TYPE) {
            instructionList.append(Constants.I2D);
            return;
        }
        if (cls == Long.TYPE) {
            instructionList.append(Constants.L2D);
            return;
        }
        if (cls == Float.TYPE) {
            instructionList.append(Constants.F2D);
        } else if (cls == Double.TYPE) {
            instructionList.append(Constants.NOP);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), cls.getName()));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, Class<?> cls) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        if (cls == Character.TYPE) {
            instructionList.append(Constants.D2I);
            instructionList.append(Constants.I2C);
            return;
        }
        if (cls == Byte.TYPE) {
            instructionList.append(Constants.D2I);
            instructionList.append(Constants.I2B);
            return;
        }
        if (cls == Short.TYPE) {
            instructionList.append(Constants.D2I);
            instructionList.append(Constants.I2S);
            return;
        }
        if (cls == Integer.TYPE) {
            instructionList.append(Constants.D2I);
            return;
        }
        if (cls == Long.TYPE) {
            instructionList.append(Constants.D2L);
            return;
        }
        if (cls == Float.TYPE) {
            instructionList.append(Constants.D2F);
            return;
        }
        if (cls == Double.TYPE) {
            instructionList.append(Constants.NOP);
        } else if (cls.isAssignableFrom(Double.class)) {
            translateTo(classGenerator, methodGenerator, Type.Reference);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), cls.getName()));
        }
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public FlowList translateToDesynthesized(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        FlowList flowList = new FlowList();
        classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(Constants.DUP2);
        LocalVariableGen localVariableGenAddLocalVariable = methodGenerator.addLocalVariable("real_to_boolean_tmp", com.sun.org.apache.bcel.internal.generic.Type.DOUBLE, null, null);
        localVariableGenAddLocalVariable.setStart(instructionList.append(new DSTORE(localVariableGenAddLocalVariable.getIndex())));
        instructionList.append(Constants.DCONST_0);
        Instruction instruction = Constants.DCMPG;
        instructionList.append(instruction);
        flowList.add(instructionList.append((BranchInstruction) new IFEQ(null)));
        instructionList.append(new DLOAD(localVariableGenAddLocalVariable.getIndex()));
        localVariableGenAddLocalVariable.setEnd(instructionList.append(new DLOAD(localVariableGenAddLocalVariable.getIndex())));
        instructionList.append(instruction);
        flowList.add(instructionList.append((BranchInstruction) new IFNE(null)));
        return flowList;
    }

    @Override // com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type
    public void translateUnBox(ClassGenerator classGenerator, MethodGenerator methodGenerator) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(new CHECKCAST(constantPool.addClass(Constants.DOUBLE_CLASS)));
        instructionList.append(new INVOKEVIRTUAL(constantPool.addMethodref(Constants.DOUBLE_CLASS, Constants.DOUBLE_VALUE, Constants.DOUBLE_VALUE_SIG)));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, StringType stringType) {
        methodGenerator.getInstructionList().append(new INVOKESTATIC(classGenerator.getConstantPool().addMethodref(Constants.BASIS_LIBRARY_CLASS, "realToString", "(D)Ljava/lang/String;")));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, BooleanType booleanType) {
        InstructionList instructionList = methodGenerator.getInstructionList();
        FlowList flowListTranslateToDesynthesized = translateToDesynthesized(classGenerator, methodGenerator, booleanType);
        instructionList.append(Constants.ICONST_1);
        BranchHandle branchHandleAppend = instructionList.append((BranchInstruction) new GOTO(null));
        flowListTranslateToDesynthesized.backPatch(instructionList.append(Constants.ICONST_0));
        branchHandleAppend.setTarget(instructionList.append(Constants.NOP));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, IntType intType) {
        methodGenerator.getInstructionList().append(new INVOKESTATIC(classGenerator.getConstantPool().addMethodref(Constants.BASIS_LIBRARY_CLASS, "realToInt", "(D)I")));
    }

    public void translateTo(ClassGenerator classGenerator, MethodGenerator methodGenerator, ReferenceType referenceType) {
        ConstantPoolGen constantPool = classGenerator.getConstantPool();
        InstructionList instructionList = methodGenerator.getInstructionList();
        instructionList.append(new NEW(constantPool.addClass(Constants.DOUBLE_CLASS)));
        StackInstruction stackInstruction = Constants.DUP_X2;
        instructionList.append(stackInstruction);
        instructionList.append(stackInstruction);
        instructionList.append(Constants.POP);
        instructionList.append(new INVOKESPECIAL(constantPool.addMethodref(Constants.DOUBLE_CLASS, Const.CONSTRUCTOR_NAME, "(D)V")));
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
        if (type == Type.Reference) {
            translateTo(classGenerator, methodGenerator, (ReferenceType) type);
        } else if (type == Type.Int) {
            translateTo(classGenerator, methodGenerator, (IntType) type);
        } else {
            classGenerator.getParser().reportError(2, new ErrorMsg("DATA_CONVERSION_ERR", toString(), type.toString()));
        }
    }
}
