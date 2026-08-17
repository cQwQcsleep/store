package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ConstantPool implements Cloneable, Node, Iterable<Constant> {
    private Constant[] constantPool;

    public ConstantPool(DataInput dataInput) throws IOException {
        int unsignedShort = dataInput.readUnsignedShort();
        this.constantPool = new Constant[unsignedShort];
        int i = 1;
        while (i < unsignedShort) {
            this.constantPool[i] = Constant.readConstant(dataInput);
            byte tag = this.constantPool[i].getTag();
            if (tag == 6 || tag == 5) {
                i++;
            }
            i++;
        }
    }

    private static String escape(String str) {
        int length = str.length();
        StringBuilder sb = new StringBuilder(length + 5);
        char[] charArray = str.toCharArray();
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (c == '\r') {
                sb.append("\\r");
            } else if (c != '\"') {
                switch (c) {
                    case '\b':
                        sb.append("\\b");
                        break;
                    case '\t':
                        sb.append("\\t");
                        break;
                    case '\n':
                        sb.append("\\n");
                        break;
                    default:
                        sb.append(c);
                        break;
                }
            } else {
                sb.append("\\\"");
            }
        }
        return sb.toString();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitConstantPool(this);
    }

    public String constantToString(Constant constant) throws IllegalArgumentException {
        byte tag = constant.getTag();
        switch (tag) {
            case 1:
                return ((ConstantUtf8) constant).getBytes();
            case 2:
            case 13:
            case 14:
            default:
                qf1.a("Unknown constant type ", tag);
                return null;
            case 3:
                return String.valueOf(((ConstantInteger) constant).getBytes());
            case 4:
                return String.valueOf(((ConstantFloat) constant).getBytes());
            case 5:
                return String.valueOf(((ConstantLong) constant).getBytes());
            case 6:
                return String.valueOf(((ConstantDouble) constant).getBytes());
            case 7:
                return Utility.compactClassName(getConstantUtf8(((ConstantClass) constant).getNameIndex()).getBytes(), false);
            case 8:
                return "\"" + escape(getConstantUtf8(((ConstantString) constant).getStringIndex()).getBytes()) + "\"";
            case 9:
            case 10:
            case 11:
                StringBuilder sb = new StringBuilder();
                ConstantCP constantCP = (ConstantCP) constant;
                sb.append(constantToString(constantCP.getClassIndex(), (byte) 7));
                sb.append(Constants.ATTRVAL_THIS);
                sb.append(constantToString(constantCP.getNameAndTypeIndex(), (byte) 12));
                return sb.toString();
            case 12:
                StringBuilder sb2 = new StringBuilder();
                ConstantNameAndType constantNameAndType = (ConstantNameAndType) constant;
                sb2.append(constantToString(constantNameAndType.getNameIndex(), (byte) 1));
                sb2.append(" ");
                sb2.append(constantToString(constantNameAndType.getSignatureIndex(), (byte) 1));
                return sb2.toString();
            case 15:
                ConstantMethodHandle constantMethodHandle = (ConstantMethodHandle) constant;
                return Const.getMethodHandleName(constantMethodHandle.getReferenceKind()) + " " + constantToString(constantMethodHandle.getReferenceIndex(), getConstant(constantMethodHandle.getReferenceIndex()).getTag());
            case 16:
                return constantToString(((ConstantMethodType) constant).getDescriptorIndex(), (byte) 1);
            case 17:
                ConstantDynamic constantDynamic = (ConstantDynamic) constant;
                return constantDynamic.getBootstrapMethodAttrIndex() + ":" + constantToString(constantDynamic.getNameAndTypeIndex(), (byte) 12);
            case 18:
                ConstantInvokeDynamic constantInvokeDynamic = (ConstantInvokeDynamic) constant;
                return constantInvokeDynamic.getBootstrapMethodAttrIndex() + ":" + constantToString(constantInvokeDynamic.getNameAndTypeIndex(), (byte) 12);
            case 19:
                return Utility.compactClassName(getConstantUtf8(((ConstantModule) constant).getNameIndex()).getBytes(), false);
            case 20:
                return Utility.compactClassName(getConstantUtf8(((ConstantPackage) constant).getNameIndex()).getBytes(), false);
        }
    }

    public ConstantPool copy() {
        try {
            ConstantPool constantPool = (ConstantPool) clone();
            try {
                constantPool.constantPool = new Constant[this.constantPool.length];
                int i = 1;
                while (true) {
                    Constant[] constantArr = this.constantPool;
                    if (i >= constantArr.length) {
                        return constantPool;
                    }
                    Constant constant = constantArr[i];
                    if (constant != null) {
                        constantPool.constantPool[i] = constant.copy();
                    }
                    i++;
                }
            } catch (CloneNotSupportedException unused) {
                return constantPool;
            }
        } catch (CloneNotSupportedException unused2) {
            return null;
        }
    }

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        int iMin = Math.min(this.constantPool.length, 65535);
        dataOutputStream.writeShort(iMin);
        for (int i = 1; i < iMin; i++) {
            Constant constant = this.constantPool[i];
            if (constant != null) {
                constant.dump(dataOutputStream);
            }
        }
    }

    public <T extends Constant> T getConstant(int i, Class<T> cls) throws ClassFormatException {
        Constant constant;
        Constant[] constantArr = this.constantPool;
        if (i >= constantArr.length || i < 0) {
            throw new ClassFormatException("Invalid constant pool reference using index: " + i + ". Constant pool size is: " + this.constantPool.length);
        }
        Constant constant2 = constantArr[i];
        if (constant2 != null && !cls.isAssignableFrom(constant2.getClass())) {
            StringBuilder sb = new StringBuilder("Invalid constant pool reference at index: ");
            sb.append(i);
            sb.append(". Expected ");
            sb.append(cls);
            Class<?> cls2 = this.constantPool[i].getClass();
            sb.append(" but was ");
            sb.append(cls2);
            throw new ClassFormatException(sb.toString());
        }
        T tCast = cls.cast(this.constantPool[i]);
        if (tCast != null || i == 0 || ((constant = this.constantPool[i - 1]) != null && (constant.getTag() == 6 || constant.getTag() == 5))) {
            return tCast;
        }
        throw new ClassFormatException("Constant pool at index " + i + " is null.");
    }

    public ConstantInteger getConstantInteger(int i) {
        return (ConstantInteger) getConstant(i, (byte) 3, ConstantInteger.class);
    }

    public Constant[] getConstantPool() {
        return this.constantPool;
    }

    public String getConstantString(int i, byte b) throws IllegalArgumentException {
        int nameIndex;
        if (b == 1) {
            return getConstantUtf8(i).getBytes();
        }
        if (b == 7) {
            nameIndex = ((ConstantClass) getConstant(i, ConstantClass.class)).getNameIndex();
        } else if (b == 8) {
            nameIndex = ((ConstantString) getConstant(i, ConstantString.class)).getStringIndex();
        } else if (b == 19) {
            nameIndex = ((ConstantModule) getConstant(i, ConstantModule.class)).getNameIndex();
        } else {
            if (b != 20) {
                qf1.a("getConstantString called with illegal tag ", b);
                return null;
            }
            nameIndex = ((ConstantPackage) getConstant(i, ConstantPackage.class)).getNameIndex();
        }
        return getConstantUtf8(nameIndex).getBytes();
    }

    public ConstantUtf8 getConstantUtf8(int i) throws ClassFormatException {
        return (ConstantUtf8) getConstant(i, (byte) 1, ConstantUtf8.class);
    }

    public int getLength() {
        Constant[] constantArr = this.constantPool;
        if (constantArr == null) {
            return 0;
        }
        return constantArr.length;
    }

    @Override // java.lang.Iterable
    public Iterator<Constant> iterator() {
        return Arrays.stream(this.constantPool).iterator();
    }

    public void setConstant(int i, Constant constant) {
        this.constantPool[i] = constant;
    }

    public void setConstantPool(Constant[] constantArr) {
        this.constantPool = constantArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i < this.constantPool.length; i++) {
            sb.append(i);
            sb.append(")");
            sb.append(this.constantPool[i]);
            sb.append("\n");
        }
        return sb.toString();
    }

    public ConstantPool(Constant[] constantArr) {
        this.constantPool = constantArr;
    }

    public <T extends Constant> T getConstant(int i, byte b) throws ClassFormatException {
        return (T) getConstant(i, b, Constant.class);
    }

    public <T extends Constant> T getConstant(int i, byte b, Class<T> cls) throws ClassFormatException {
        T t = (T) getConstant(i);
        if (t.getTag() == b) {
            return t;
        }
        throw new ClassFormatException("Expected class '" + Const.getConstantName(b) + "' at index " + i + " and got " + t);
    }

    public <T extends Constant> T getConstant(int i) throws ClassFormatException {
        return (T) getConstant(i, Constant.class);
    }

    public String constantToString(int i, byte b) {
        return constantToString(getConstant(i, b));
    }
}
