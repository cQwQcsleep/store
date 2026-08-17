package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.BCELComparator;
import defpackage.yr2;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Constant implements Cloneable, Node {
    private static BCELComparator bcelComparator = new BCELComparator() { // from class: com.sun.org.apache.bcel.internal.classfile.Constant.1
        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public boolean equals(Object obj, Object obj2) {
            return Objects.equals(((Constant) obj).toString(), ((Constant) obj2).toString());
        }

        @Override // com.sun.org.apache.bcel.internal.util.BCELComparator
        public int hashCode(Object obj) {
            return ((Constant) obj).toString().hashCode();
        }
    };

    @java.lang.Deprecated
    protected byte tag;

    public Constant(byte b) {
        this.tag = b;
    }

    public static BCELComparator getComparator() {
        return bcelComparator;
    }

    public static Constant readConstant(DataInput dataInput) throws IOException, ClassFormatException {
        byte b = dataInput.readByte();
        switch (b) {
            case 1:
                return ConstantUtf8.getInstance(dataInput);
            case 2:
            case 13:
            case 14:
            default:
                yr2.a("Invalid byte tag in constant pool: ", b);
                return null;
            case 3:
                return new ConstantInteger(dataInput);
            case 4:
                return new ConstantFloat(dataInput);
            case 5:
                return new ConstantLong(dataInput);
            case 6:
                return new ConstantDouble(dataInput);
            case 7:
                return new ConstantClass(dataInput);
            case 8:
                return new ConstantString(dataInput);
            case 9:
                return new ConstantFieldref(dataInput);
            case 10:
                return new ConstantMethodref(dataInput);
            case 11:
                return new ConstantInterfaceMethodref(dataInput);
            case 12:
                return new ConstantNameAndType(dataInput);
            case 15:
                return new ConstantMethodHandle(dataInput);
            case 16:
                return new ConstantMethodType(dataInput);
            case 17:
                return new ConstantDynamic(dataInput);
            case 18:
                return new ConstantInvokeDynamic(dataInput);
            case 19:
                return new ConstantModule(dataInput);
            case 20:
                return new ConstantPackage(dataInput);
        }
    }

    public static void setComparator(BCELComparator bCELComparator) {
        bcelComparator = bCELComparator;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public abstract void accept(Visitor visitor);

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new Error("Clone Not Supported");
        }
    }

    public Constant copy() {
        try {
            return (Constant) super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public abstract void dump(DataOutputStream dataOutputStream) throws IOException;

    public boolean equals(Object obj) {
        return bcelComparator.equals(this, obj);
    }

    public final byte getTag() {
        return this.tag;
    }

    public int hashCode() {
        return bcelComparator.hashCode(this);
    }

    public String toString() {
        return Const.getConstantName(this.tag) + "[" + ((int) this.tag) + "]";
    }
}
