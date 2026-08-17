package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.util.Args;
import defpackage.yba;
import java.io.ByteArrayInputStream;
import java.io.DataInput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class Signature extends Attribute {
    private int signatureIndex;

    public static final class MyByteArrayInputStream extends ByteArrayInputStream {
        public MyByteArrayInputStream(String str) {
            super(str.getBytes(StandardCharsets.UTF_8));
        }

        public String getData() {
            return new String(((ByteArrayInputStream) this).buf, StandardCharsets.UTF_8);
        }

        public void unread() {
            int i = ((ByteArrayInputStream) this).pos;
            if (i > 0) {
                ((ByteArrayInputStream) this).pos = i - 1;
            }
        }
    }

    public Signature(int i, int i2, int i3, ConstantPool constantPool) {
        super((byte) 10, i, Args.require(i2, 2, "Signature length attribute"), constantPool);
        this.signatureIndex = i3;
        Objects.requireNonNull(constantPool.getConstantUtf8(i3), "constantPool.getConstantUtf8(signatureIndex)");
    }

    private static boolean identStart(int i) {
        return i == 84 || i == 76;
    }

    public static boolean isActualParameterList(String str) {
        return str.startsWith("L") && str.endsWith(">;");
    }

    public static boolean isFormalParameterList(String str) {
        return str.startsWith("<") && str.indexOf(58) > 0;
    }

    private static void matchGJIdent(MyByteArrayInputStream myByteArrayInputStream, StringBuilder sb) throws IOException {
        int i;
        matchIdent(myByteArrayInputStream, sb);
        int i2 = myByteArrayInputStream.read();
        if (i2 == 60 || i2 == 40) {
            sb.append((char) i2);
            matchGJIdent(myByteArrayInputStream, sb);
            while (true) {
                i = myByteArrayInputStream.read();
                if (i == 62 || i == 41) {
                    break;
                }
                if (i == -1) {
                    yba.a("Illegal signature: ", myByteArrayInputStream.getData(), " reaching EOF");
                    return;
                } else {
                    sb.append(", ");
                    myByteArrayInputStream.unread();
                    matchGJIdent(myByteArrayInputStream, sb);
                }
            }
            sb.append((char) i);
        } else {
            myByteArrayInputStream.unread();
        }
        int i3 = myByteArrayInputStream.read();
        if (identStart(i3)) {
            myByteArrayInputStream.unread();
            matchGJIdent(myByteArrayInputStream, sb);
        } else {
            if (i3 == 41) {
                myByteArrayInputStream.unread();
                return;
            }
            if (i3 == 59) {
                return;
            }
            throw new IllegalArgumentException("Illegal signature: " + myByteArrayInputStream.getData() + " read " + ((char) i3));
        }
    }

    private static void matchIdent(MyByteArrayInputStream myByteArrayInputStream, StringBuilder sb) throws IOException {
        int i = myByteArrayInputStream.read();
        if (i == -1) {
            yba.a("Illegal signature: ", myByteArrayInputStream.getData(), " no ident, reaching EOF");
            return;
        }
        if (identStart(i)) {
            StringBuilder sb2 = new StringBuilder();
            int i2 = myByteArrayInputStream.read();
            while (true) {
                sb2.append((char) i2);
                i2 = myByteArrayInputStream.read();
                if (i2 == -1 || (!Character.isJavaIdentifierPart((char) i2) && i2 != 47)) {
                    break;
                }
            }
            sb.append(Utility.pathToPackage(sb2.toString()));
            if (i2 != -1) {
                myByteArrayInputStream.unread();
                return;
            }
            return;
        }
        StringBuilder sb3 = new StringBuilder();
        int i3 = 1;
        while (true) {
            char c = (char) i;
            if (!Character.isJavaIdentifierPart(c)) {
                break;
            }
            sb3.append(c);
            i3++;
            i = myByteArrayInputStream.read();
        }
        if (i != 58) {
            for (int i4 = 0; i4 < i3; i4++) {
                myByteArrayInputStream.unread();
            }
            return;
        }
        long jSkip = myByteArrayInputStream.skip(17L);
        if (jSkip != 17) {
            throw new IllegalStateException(String.format("Unexpected skip: expected=%,d, actual=%,d", 17, Long.valueOf(jSkip)));
        }
        sb.append((CharSequence) sb3);
        myByteArrayInputStream.read();
        myByteArrayInputStream.unread();
    }

    public static String translate(String str) throws IOException {
        StringBuilder sb = new StringBuilder();
        matchGJIdent(new MyByteArrayInputStream(str), sb);
        return sb.toString();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute, com.sun.org.apache.bcel.internal.classfile.Node
    public void accept(Visitor visitor) {
        visitor.visitSignature(this);
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public Attribute copy(ConstantPool constantPool) {
        return (Attribute) clone();
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        super.dump(dataOutputStream);
        dataOutputStream.writeShort(this.signatureIndex);
    }

    public String getSignature() {
        return super.getConstantPool().getConstantUtf8(this.signatureIndex).getBytes();
    }

    public int getSignatureIndex() {
        return this.signatureIndex;
    }

    public void setSignatureIndex(int i) {
        this.signatureIndex = i;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.Attribute
    public String toString() {
        return "Signature: " + getSignature();
    }

    public Signature(int i, int i2, DataInput dataInput, ConstantPool constantPool) throws IOException {
        this(i, i2, dataInput.readUnsignedShort(), constantPool);
    }

    public Signature(Signature signature) {
        this(signature.getNameIndex(), signature.getLength(), signature.getSignatureIndex(), signature.getConstantPool());
    }
}
