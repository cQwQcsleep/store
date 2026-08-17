package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.Const;
import defpackage.c5c;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class BasicType extends Type {
    public BasicType(byte b) {
        super(b, Const.getShortTypeName(b));
        if (b < 4 || b > 12) {
            c5c.a("Invalid type: ", b);
            throw null;
        }
    }

    public static BasicType getType(byte b) {
        switch (b) {
            case 4:
                return Type.BOOLEAN;
            case 5:
                return Type.CHAR;
            case 6:
                return Type.FLOAT;
            case 7:
                return Type.DOUBLE;
            case 8:
                return Type.BYTE;
            case 9:
                return Type.SHORT;
            case 10:
                return Type.INT;
            case 11:
                return Type.LONG;
            case 12:
                return Type.VOID;
            default:
                c5c.a("Invalid type: ", b);
                return null;
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    public boolean equals(Object obj) {
        return (obj instanceof BasicType) && ((BasicType) obj).getType() == getType();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.Type
    public int hashCode() {
        return super.getType();
    }
}
