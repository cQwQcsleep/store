package com.sun.tools.classfile;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DefaultAttribute extends Attribute {
    public final byte[] info;
    public final String reason;

    public DefaultAttribute(ClassReader classReader, int i, byte[] bArr, String str) {
        super(i, bArr.length);
        this.info = bArr;
        this.reason = str;
    }

    @Override // com.sun.tools.classfile.Attribute
    public <R, P> R accept(Attribute.Visitor<R, P> visitor, P p) {
        return visitor.visitDefault(this, p);
    }

    public DefaultAttribute(ClassReader classReader, int i, byte[] bArr) {
        this(classReader, i, bArr, (String) null);
    }

    public DefaultAttribute(ConstantPool constantPool, int i, byte[] bArr) {
        this(constantPool, i, bArr, (String) null);
    }

    public DefaultAttribute(ConstantPool constantPool, int i, byte[] bArr, String str) {
        super(i, bArr.length);
        this.info = bArr;
        this.reason = str;
    }
}
