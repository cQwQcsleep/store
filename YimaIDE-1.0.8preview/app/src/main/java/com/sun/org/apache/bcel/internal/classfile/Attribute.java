package com.sun.org.apache.bcel.internal.classfile;

import com.sun.org.apache.bcel.internal.Const;
import com.sun.org.apache.bcel.internal.util.Args;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class Attribute implements Cloneable, Node {
    public static final Attribute[] EMPTY_ARRAY = new Attribute[0];
    private static final Map<String, Object> READERS = new HashMap();
    private static final boolean debug = false;

    @java.lang.Deprecated
    protected ConstantPool constant_pool;

    @java.lang.Deprecated
    protected int length;

    @java.lang.Deprecated
    protected int name_index;

    @java.lang.Deprecated
    protected byte tag;

    public Attribute(byte b, int i, int i2, ConstantPool constantPool) {
        this.tag = b;
        this.name_index = Args.requireU2(i, 0, constantPool.getLength(), getClass().getSimpleName().concat(" name index"));
        this.length = Args.requireU4(i2, getClass().getSimpleName().concat(" attribute length"));
        this.constant_pool = constantPool;
    }

    public static void addAttributeReader(String str, UnknownAttributeReader unknownAttributeReader) {
        READERS.put(str, unknownAttributeReader);
    }

    public static void println(String str) {
    }

    public static Attribute readAttribute(DataInput dataInput, ConstantPool constantPool) throws IOException {
        int unsignedShort = dataInput.readUnsignedShort();
        String bytes = constantPool.getConstantUtf8(unsignedShort).getBytes();
        int i = dataInput.readInt();
        byte b = 0;
        while (true) {
            if (b >= 27) {
                b = -1;
                break;
            }
            if (bytes.equals(Const.getAttributeName(b))) {
                break;
            }
            b = (byte) (b + 1);
        }
        switch (b) {
            case -1:
                Object obj = READERS.get(bytes);
                return obj instanceof UnknownAttributeReader ? ((UnknownAttributeReader) obj).createAttribute(unsignedShort, i, dataInput, constantPool) : new Unknown(unsignedShort, i, dataInput, constantPool);
            case 0:
                return new SourceFile(unsignedShort, i, dataInput, constantPool);
            case 1:
                return new ConstantValue(unsignedShort, i, dataInput, constantPool);
            case 2:
                return new Code(unsignedShort, i, dataInput, constantPool);
            case 3:
                return new ExceptionTable(unsignedShort, i, dataInput, constantPool);
            case 4:
                return new LineNumberTable(unsignedShort, i, dataInput, constantPool);
            case 5:
                return new LocalVariableTable(unsignedShort, i, dataInput, constantPool);
            case 6:
                return new InnerClasses(unsignedShort, i, dataInput, constantPool);
            case 7:
                return new Synthetic(unsignedShort, i, dataInput, constantPool);
            case 8:
                return new Deprecated(unsignedShort, i, dataInput, constantPool);
            case 9:
                return new PMGClass(unsignedShort, i, dataInput, constantPool);
            case 10:
                return new Signature(unsignedShort, i, dataInput, constantPool);
            case 11:
                println("Warning: Obsolete StackMap attribute ignored.");
                return new Unknown(unsignedShort, i, dataInput, constantPool);
            case 12:
                return new RuntimeVisibleAnnotations(unsignedShort, i, dataInput, constantPool);
            case 13:
                return new RuntimeInvisibleAnnotations(unsignedShort, i, dataInput, constantPool);
            case 14:
                return new RuntimeVisibleParameterAnnotations(unsignedShort, i, dataInput, constantPool);
            case 15:
                return new RuntimeInvisibleParameterAnnotations(unsignedShort, i, dataInput, constantPool);
            case 16:
                return new AnnotationDefault(unsignedShort, i, dataInput, constantPool);
            case 17:
                return new LocalVariableTypeTable(unsignedShort, i, dataInput, constantPool);
            case 18:
                return new EnclosingMethod(unsignedShort, i, dataInput, constantPool);
            case 19:
                return new StackMap(unsignedShort, i, dataInput, constantPool);
            case 20:
                return new BootstrapMethods(unsignedShort, i, dataInput, constantPool);
            case 21:
                return new MethodParameters(unsignedShort, i, dataInput, constantPool);
            case 22:
                return new Module(unsignedShort, i, dataInput, constantPool);
            case 23:
                return new ModulePackages(unsignedShort, i, dataInput, constantPool);
            case 24:
                return new ModuleMainClass(unsignedShort, i, dataInput, constantPool);
            case 25:
                return new NestHost(unsignedShort, i, dataInput, constantPool);
            case 26:
                return new NestMembers(unsignedShort, i, dataInput, constantPool);
            default:
                pu7.a("Unrecognized attribute type tag parsed: ", b);
                return null;
        }
    }

    public static void removeAttributeReader(String str) {
        READERS.remove(str);
    }

    public abstract void accept(Visitor visitor);

    public Object clone() {
        try {
            return (Attribute) super.clone();
        } catch (CloneNotSupportedException unused) {
            throw new Error("Clone Not Supported");
        }
    }

    public abstract Attribute copy(ConstantPool constantPool);

    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeShort(this.name_index);
        dataOutputStream.writeInt(this.length);
    }

    public final ConstantPool getConstantPool() {
        return this.constant_pool;
    }

    public final int getLength() {
        return this.length;
    }

    public String getName() {
        return this.constant_pool.getConstantUtf8(this.name_index).getBytes();
    }

    public final int getNameIndex() {
        return this.name_index;
    }

    public final byte getTag() {
        return this.tag;
    }

    public final void setConstantPool(ConstantPool constantPool) {
        this.constant_pool = constantPool;
    }

    public final void setLength(int i) {
        this.length = i;
    }

    public final void setNameIndex(int i) {
        this.name_index = i;
    }

    public String toString() {
        return Const.getAttributeName(this.tag);
    }

    public static Attribute readAttribute(DataInputStream dataInputStream, ConstantPool constantPool) throws IOException {
        return readAttribute((DataInput) dataInputStream, constantPool);
    }
}
