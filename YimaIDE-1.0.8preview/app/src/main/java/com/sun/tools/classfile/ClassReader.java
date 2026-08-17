package com.sun.tools.classfile;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassReader {
    private Attribute.Factory attributeFactory;
    private ClassFile classFile;
    private DataInputStream in;

    public ClassReader(ClassFile classFile, InputStream inputStream, Attribute.Factory factory) throws IOException {
        Objects.requireNonNull(classFile);
        this.classFile = classFile;
        Objects.requireNonNull(factory);
        this.attributeFactory = factory;
        this.in = new DataInputStream(new BufferedInputStream(inputStream));
    }

    public ClassFile getClassFile() {
        return this.classFile;
    }

    public ConstantPool getConstantPool() {
        return this.classFile.constant_pool;
    }

    public Attribute readAttribute() throws IOException {
        String uTF8Value;
        int unsignedShort = readUnsignedShort();
        int i = readInt();
        if (i < 0) {
            try {
                uTF8Value = getConstantPool().getUTF8Value(unsignedShort);
            } catch (ConstantPool.InvalidIndex | ConstantPool.UnexpectedEntry unused) {
                uTF8Value = "";
            }
            throw new FatalError(String.format("attribute %s too big to handle", uTF8Value));
        }
        byte[] bArr = new byte[i];
        readFully(bArr);
        DataInputStream dataInputStream = this.in;
        this.in = new DataInputStream(new ByteArrayInputStream(bArr));
        try {
            return this.attributeFactory.createAttribute(this, unsignedShort, bArr);
        } finally {
            this.in = dataInputStream;
        }
    }

    public double readDouble() throws IOException {
        return this.in.readDouble();
    }

    public float readFloat() throws IOException {
        return this.in.readFloat();
    }

    public void readFully(byte[] bArr) throws IOException {
        this.in.readFully(bArr);
    }

    public int readInt() throws IOException {
        return this.in.readInt();
    }

    public long readLong() throws IOException {
        return this.in.readLong();
    }

    public String readUTF() throws IOException {
        return this.in.readUTF();
    }

    public int readUnsignedByte() throws IOException {
        return this.in.readUnsignedByte();
    }

    public int readUnsignedShort() throws IOException {
        return this.in.readUnsignedShort();
    }
}
