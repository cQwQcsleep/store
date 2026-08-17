package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.function.IntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Code extends Attribute {
    private Attribute[] attributes;
    private byte[] code;
    private CodeException[] exceptionTable;
    private int maxLocals;
    private int maxStack;

    public Code(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, dataInput.readUnsignedShort(), dataInput.readUnsignedShort(), null, null, null, constantPool);
        byte[] bArr = new byte[Args.requireU4(dataInput.readInt(), 1, "Code length attribute")];
        this.code = bArr;
        dataInput.readFully(bArr);
        int unsignedShort = dataInput.readUnsignedShort();
        this.exceptionTable = new CodeException[unsignedShort];
        for (int i3 = 0; i3 < unsignedShort; i3++) {
            this.exceptionTable[i3] = new CodeException(dataInput);
        }
        int unsignedShort2 = dataInput.readUnsignedShort();
        this.attributes = new Attribute[unsignedShort2];
        for (int i4 = 0; i4 < unsignedShort2; i4++) {
            this.attributes[i4] = Attribute.readAttribute(dataInput, constantPool);
        }
        super.setLength(i2);
    }

    private int calculateLength() {
        Attribute[] attributeArr = this.attributes;
        int i = 0;
        if (attributeArr != null) {
            int length = attributeArr.length;
            int length2 = 0;
            while (i < length) {
                length2 += attributeArr[i].getLength() + 6;
                i++;
            }
            i = length2;
        }
        return i + getInternalLength();
    }

    private int getInternalLength() {
        int length = this.code.length + 10;
        CodeException[] codeExceptionArr = this.exceptionTable;
        return length + ((codeExceptionArr == null ? 0 : codeExceptionArr.length) * 8) + 2;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitCode(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(final ConstantPool constantPool) {
        Code code = (Code) clone();
        byte[] bArr = this.code;
        if (bArr != null) {
            code.code = (byte[]) bArr.clone();
        }
        code.setConstantPool(constantPool);
        CodeException[] codeExceptionArr = new CodeException[this.exceptionTable.length];
        code.exceptionTable = codeExceptionArr;
        Arrays.setAll(codeExceptionArr, new IntFunction() { // from class: t22
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.exceptionTable[i].copy();
            }
        });
        Attribute[] attributeArr = new Attribute[this.attributes.length];
        code.attributes = attributeArr;
        Arrays.setAll(attributeArr, new IntFunction() { // from class: u22
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.b.attributes[i].copy(constantPool);
            }
        });
        return code;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.maxStack);
        dataOutputStream.writeShort(this.maxLocals);
        dataOutputStream.writeInt(this.code.length);
        byte[] bArr = this.code;
        dataOutputStream.write(bArr, 0, bArr.length);
        dataOutputStream.writeShort(this.exceptionTable.length);
        for (CodeException codeException : this.exceptionTable) {
            codeException.dump(dataOutputStream);
        }
        dataOutputStream.writeShort(this.attributes.length);
        for (Attribute attribute : this.attributes) {
            attribute.dump(dataOutputStream);
        }
    }

    public Attribute[] getAttributes() {
        return this.attributes;
    }

    public byte[] getCode() {
        return this.code;
    }

    public CodeException[] getExceptionTable() {
        return this.exceptionTable;
    }

    public LineNumberTable getLineNumberTable() {
        for (Attribute attribute : this.attributes) {
            if (attribute instanceof LineNumberTable) {
                return (LineNumberTable) attribute;
            }
        }
        return null;
    }

    public LocalVariableTable getLocalVariableTable() {
        for (Attribute attribute : this.attributes) {
            if (attribute instanceof LocalVariableTable) {
                return (LocalVariableTable) attribute;
            }
        }
        return null;
    }

    public int getMaxLocals() {
        return this.maxLocals;
    }

    public int getMaxStack() {
        return this.maxStack;
    }

    public void setAttributes(Attribute[] attributeArr) {
        if (attributeArr == null) {
            attributeArr = Attribute.EMPTY_ARRAY;
        }
        this.attributes = attributeArr;
        super.setLength(calculateLength());
    }

    public void setCode(byte[] bArr) {
        if (bArr == null) {
            bArr = Const.EMPTY_BYTE_ARRAY;
        }
        this.code = bArr;
        super.setLength(calculateLength());
    }

    public void setExceptionTable(CodeException[] codeExceptionArr) {
        if (codeExceptionArr == null) {
            codeExceptionArr = CodeException.EMPTY_CODE_EXCEPTION_ARRAY;
        }
        this.exceptionTable = codeExceptionArr;
        super.setLength(calculateLength());
    }

    public void setMaxLocals(int i) {
        this.maxLocals = i;
    }

    public void setMaxStack(int i) {
        this.maxStack = i;
    }

    public String toString(boolean z) {
        StringBuilder sb = new StringBuilder(100);
        sb.append("Code(maxStack = ");
        sb.append(this.maxStack);
        sb.append(", maxLocals = ");
        sb.append(this.maxLocals);
        sb.append(", code_length = ");
        sb.append(this.code.length);
        sb.append(")\n");
        sb.append(Utility.codeToString(this.code, super.getConstantPool(), 0, -1, z));
        if (this.exceptionTable.length > 0) {
            sb.append("\nException handler(s) = \nFrom\tTo\tHandler\tType\n");
            for (CodeException codeException : this.exceptionTable) {
                sb.append(codeException.toString(super.getConstantPool(), z));
                sb.append("\n");
            }
        }
        if (this.attributes.length > 0) {
            sb.append("\nAttribute(s) = ");
            for (Attribute attribute : this.attributes) {
                sb.append("\n");
                sb.append(attribute.getName());
                sb.append(":\n");
                sb.append(attribute);
            }
        }
        return sb.toString();
    }

    public Code(Code code) {
        this(code.getNameIndex(), code.getLength(), code.getMaxStack(), code.getMaxLocals(), code.getCode(), code.getExceptionTable(), code.getAttributes(), code.getConstantPool());
    }

    public Code(int i, int i2, int i3, int i4, byte[] bArr, CodeException[] codeExceptionArr, Attribute[] attributeArr, ConstantPool constantPool) {
        super((byte) 2, i, i2, constantPool);
        this.maxStack = Args.requireU2(i3, "maxStack");
        this.maxLocals = Args.requireU2(i4, "maxLocals");
        this.code = bArr == null ? Const.EMPTY_BYTE_ARRAY : bArr;
        codeExceptionArr = codeExceptionArr == null ? CodeException.EMPTY_CODE_EXCEPTION_ARRAY : codeExceptionArr;
        this.exceptionTable = codeExceptionArr;
        Args.requireU2(codeExceptionArr.length, "exceptionTable.length");
        this.attributes = attributeArr == null ? Attribute.EMPTY_ARRAY : attributeArr;
        super.setLength(calculateLength());
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        return toString(true);
    }
}
