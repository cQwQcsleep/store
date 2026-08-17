package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ConstantCP;
import com.sun.org.apache.bcel.internal.classfile.ConstantNameAndType;
import com.sun.org.apache.bcel.internal.classfile.ConstantPool;
import com.sun.org.apache.bcel.internal.classfile.ConstantUtf8;
import com.sun.org.apache.bcel.internal.classfile.Utility;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class FieldOrMethod extends CPInstruction implements LoadClass {
    public FieldOrMethod() {
    }

    @Deprecated
    public String getClassName(ConstantPoolGen constantPoolGen) {
        ConstantPool constantPool = constantPoolGen.getConstantPool();
        String constantString = constantPool.getConstantString(((ConstantCP) constantPool.getConstant(super.getIndex())).getClassIndex(), (byte) 7);
        return constantString.startsWith("[") ? Constants.OBJECT_CLASS : Utility.pathToPackage(constantString);
    }

    @Deprecated
    public ObjectType getClassType(ConstantPoolGen constantPoolGen) {
        return ObjectType.getInstance(getClassName(constantPoolGen));
    }

    @Override // com.sun.org.apache.bcel.internal.generic.LoadClass
    public ObjectType getLoadClassType(ConstantPoolGen constantPoolGen) {
        ReferenceType referenceType = getReferenceType(constantPoolGen);
        if (referenceType instanceof ObjectType) {
            return (ObjectType) referenceType;
        }
        throw new ClassGenException(referenceType.getClass().getCanonicalName() + " " + referenceType.getSignature() + " does not represent an ObjectType");
    }

    public String getName(ConstantPoolGen constantPoolGen) {
        ConstantPool constantPool = constantPoolGen.getConstantPool();
        return ((ConstantUtf8) constantPool.getConstant(((ConstantNameAndType) constantPool.getConstant(((ConstantCP) constantPool.getConstant(super.getIndex())).getNameAndTypeIndex())).getNameIndex())).getBytes();
    }

    public ReferenceType getReferenceType(ConstantPoolGen constantPoolGen) {
        ConstantPool constantPool = constantPoolGen.getConstantPool();
        String constantString = constantPool.getConstantString(((ConstantCP) constantPool.getConstant(super.getIndex())).getClassIndex(), (byte) 7);
        return constantString.startsWith("[") ? (ArrayType) Type.getType(constantString) : ObjectType.getInstance(Utility.pathToPackage(constantString));
    }

    public String getSignature(ConstantPoolGen constantPoolGen) {
        ConstantPool constantPool = constantPoolGen.getConstantPool();
        return ((ConstantUtf8) constantPool.getConstant(((ConstantNameAndType) constantPool.getConstant(((ConstantCP) constantPool.getConstant(super.getIndex())).getNameAndTypeIndex())).getSignatureIndex())).getBytes();
    }

    public FieldOrMethod(short s, int i) {
        super(s, i);
    }
}
