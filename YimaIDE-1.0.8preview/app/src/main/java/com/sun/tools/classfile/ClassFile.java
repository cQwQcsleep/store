package com.sun.tools.classfile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassFile {
    public final AccessFlags access_flags;
    public final Attributes attributes;
    public final ConstantPool constant_pool;
    public final Field[] fields;
    public final int[] interfaces;
    public final int magic;
    public final int major_version;
    public final Method[] methods;
    public final int minor_version;
    public final int super_class;
    public final int this_class;

    public ClassFile(InputStream inputStream, Attribute.Factory factory) throws IOException, ConstantPoolException {
        ClassReader classReader = new ClassReader(this, inputStream, factory);
        this.magic = classReader.readInt();
        this.minor_version = classReader.readUnsignedShort();
        this.major_version = classReader.readUnsignedShort();
        this.constant_pool = new ConstantPool(classReader);
        this.access_flags = new AccessFlags(classReader);
        this.this_class = classReader.readUnsignedShort();
        this.super_class = classReader.readUnsignedShort();
        int unsignedShort = classReader.readUnsignedShort();
        this.interfaces = new int[unsignedShort];
        for (int i = 0; i < unsignedShort; i++) {
            this.interfaces[i] = classReader.readUnsignedShort();
        }
        int unsignedShort2 = classReader.readUnsignedShort();
        this.fields = new Field[unsignedShort2];
        for (int i2 = 0; i2 < unsignedShort2; i2++) {
            this.fields[i2] = new Field(classReader);
        }
        int unsignedShort3 = classReader.readUnsignedShort();
        this.methods = new Method[unsignedShort3];
        for (int i3 = 0; i3 < unsignedShort3; i3++) {
            this.methods[i3] = new Method(classReader);
        }
        this.attributes = new Attributes(classReader);
    }

    public static ClassFile read(Path path, Attribute.Factory factory) throws IOException, ConstantPoolException {
        InputStream inputStreamNewInputStream = Files.newInputStream(path, new OpenOption[0]);
        try {
            ClassFile classFile = new ClassFile(inputStreamNewInputStream, factory);
            if (inputStreamNewInputStream != null) {
                inputStreamNewInputStream.close();
            }
            return classFile;
        } catch (Throwable th) {
            if (inputStreamNewInputStream != null) {
                try {
                    inputStreamNewInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    public int byteLength() {
        return this.constant_pool.byteLength() + 14 + byteLength(this.interfaces) + byteLength(this.fields) + byteLength(this.methods) + this.attributes.byteLength();
    }

    public Attribute getAttribute(String str) {
        return this.attributes.get(str);
    }

    public String getInterfaceName(int i) throws ConstantPoolException {
        return this.constant_pool.getClassInfo(this.interfaces[i]).getName();
    }

    public String getName() throws ConstantPoolException {
        return this.constant_pool.getClassInfo(this.this_class).getName();
    }

    public String getSuperclassName() throws ConstantPoolException {
        return this.constant_pool.getClassInfo(this.super_class).getName();
    }

    public boolean isClass() {
        return !isInterface();
    }

    public boolean isInterface() {
        return this.access_flags.is(512);
    }

    public static ClassFile read(Path path) throws IOException, ConstantPoolException {
        return read(path, new Attribute.Factory());
    }

    public static ClassFile read(File file) throws IOException, ConstantPoolException {
        return read(file.toPath(), new Attribute.Factory());
    }

    public static ClassFile read(File file, Attribute.Factory factory) throws IOException, ConstantPoolException {
        return read(file.toPath(), factory);
    }

    public static ClassFile read(InputStream inputStream) throws IOException, ConstantPoolException {
        return new ClassFile(inputStream, new Attribute.Factory());
    }

    public static ClassFile read(InputStream inputStream, Attribute.Factory factory) throws IOException, ConstantPoolException {
        return new ClassFile(inputStream, factory);
    }

    private int byteLength(int[] iArr) {
        return (iArr.length * 2) + 2;
    }

    private int byteLength(Field[] fieldArr) {
        int iByteLength = 2;
        for (Field field : fieldArr) {
            iByteLength += field.byteLength();
        }
        return iByteLength;
    }

    private int byteLength(Method[] methodArr) {
        int iByteLength = 2;
        for (Method method : methodArr) {
            iByteLength += method.byteLength();
        }
        return iByteLength;
    }

    public ClassFile(int i, int i2, int i3, ConstantPool constantPool, AccessFlags accessFlags, int i4, int i5, int[] iArr, Field[] fieldArr, Method[] methodArr, Attributes attributes) {
        this.magic = i;
        this.minor_version = i2;
        this.major_version = i3;
        this.constant_pool = constantPool;
        this.access_flags = accessFlags;
        this.this_class = i4;
        this.super_class = i5;
        this.interfaces = iArr;
        this.fields = fieldArr;
        this.methods = methodArr;
        this.attributes = attributes;
    }
}
