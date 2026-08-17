package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.classfile.ConstantCP;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import java.util.StringTokenizer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class InvokeInstruction extends FieldOrMethod implements ExceptionThrower, StackConsumer, StackProducer {
    public InvokeInstruction() {
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction, com.sun.org.apache.bcel.internal.generic.StackConsumer
    public int consumeStack(ConstantPoolGen constantPoolGen) {
        return ((super.getOpcode() == 184 || super.getOpcode() == 186) ? 0 : 1) + Type.getArgumentTypesSize(getSignature(constantPoolGen));
    }

    public Type[] getArgumentTypes(ConstantPoolGen constantPoolGen) {
        return Type.getArgumentTypes(getSignature(constantPoolGen));
    }

    @Override // com.sun.org.apache.bcel.internal.generic.FieldOrMethod
    @Deprecated
    public String getClassName(ConstantPoolGen constantPoolGen) {
        ConstantPool constantPool = constantPoolGen.getConstantPool();
        return Utility.pathToPackage(constantPool.getConstantString(((ConstantCP) constantPool.getConstant(super.getIndex())).getClassIndex(), (byte) 7));
    }

    public String getMethodName(ConstantPoolGen constantPoolGen) {
        return getName(constantPoolGen);
    }

    public Type getReturnType(ConstantPoolGen constantPoolGen) {
        return Type.getReturnType(getSignature(constantPoolGen));
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.TypedInstruction
    public Type getType(ConstantPoolGen constantPoolGen) {
        return getReturnType(constantPoolGen);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Instruction, com.sun.org.apache.bcel.internal.generic.StackProducer
    public int produceStack(ConstantPoolGen constantPoolGen) {
        return Type.getReturnTypeSize(getSignature(constantPoolGen));
    }

    @Override // com.sun.org.apache.bcel.internal.generic.CPInstruction, com.sun.org.apache.bcel.internal.generic.Instruction
    public String toString(ConstantPool constantPool) {
        StringTokenizer stringTokenizer = new StringTokenizer(constantPool.constantToString(constantPool.getConstant(super.getIndex())));
        StringBuilder sb = new StringBuilder(Const.getOpcodeName(super.getOpcode()));
        if (stringTokenizer.hasMoreTokens()) {
            sb.append(" ");
            sb.append(Utility.packageToPath(stringTokenizer.nextToken()));
            if (stringTokenizer.hasMoreTokens()) {
                sb.append(stringTokenizer.nextToken());
            }
        }
        return sb.toString();
    }

    public InvokeInstruction(short s, int i) {
        super(s, i);
    }
}
