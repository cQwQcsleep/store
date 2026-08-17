package com.sun.org.apache.bcel.internal.generic;

import defpackage.c5c;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ArrayType extends ReferenceType {
    private final Type basicType;
    private final int dimensions;

    public ArrayType(Type type, int i) {
        super((byte) 13, "<dummy>");
        if (i < 1 || i > 255) {
            c5c.a("Invalid number of dimensions: ", i);
            throw null;
        }
        byte type2 = type.getType();
        if (type2 == 12) {
            throw new ClassGenException("Invalid type: void[]");
        }
        if (type2 != 13) {
            this.dimensions = i;
            this.basicType = type;
        } else {
            ArrayType arrayType = (ArrayType) type;
            this.dimensions = i + arrayType.dimensions;
            this.basicType = arrayType.basicType;
        }
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < this.dimensions; i2++) {
            sb.append('[');
        }
        sb.append(this.basicType.getSignature());
        super.setSignature(sb.toString());
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    public boolean equals(Object obj) {
        if (obj instanceof ArrayType) {
            ArrayType arrayType = (ArrayType) obj;
            if (arrayType.dimensions == this.dimensions && arrayType.basicType.equals(this.basicType)) {
                return true;
            }
        }
        return false;
    }

    public Type getBasicType() {
        return this.basicType;
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    @Deprecated
    public String getClassName() {
        return this.signature;
    }

    public int getDimensions() {
        return this.dimensions;
    }

    public Type getElementType() {
        int i = this.dimensions;
        Type type = this.basicType;
        return i == 1 ? type : new ArrayType(type, i - 1);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    public int hashCode() {
        return this.dimensions ^ this.basicType.hashCode();
    }

    public ArrayType(String str, int i) {
        this(ObjectType.getInstance(str), i);
    }

    public ArrayType(byte b, int i) {
        this(BasicType.getType(b), i);
    }
}
