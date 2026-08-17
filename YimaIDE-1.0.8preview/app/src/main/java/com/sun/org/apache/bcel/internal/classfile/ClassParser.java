package com.sun.org.apache.bcel.internal.classfile;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ClassParser {
    private static final int BUFSIZE = 8192;
    private int accessFlags;
    private Attribute[] attributes;
    private int classNameIndex;
    private ConstantPool constantPool;
    private DataInputStream dataInputStream;
    private Field[] fields;
    private final String fileName;
    private final boolean fileOwned;
    private int[] interfaces;
    private final boolean isZip;
    private int major;
    private Method[] methods;
    private int minor;
    private int superclassNameIndex;
    private String zipFile;

    public ClassParser(InputStream inputStream, String str) {
        this.fileName = str;
        this.fileOwned = false;
        String name = inputStream.getClass().getName();
        this.isZip = name.startsWith("java.util.zip.") || name.startsWith("java.util.jar.");
        if (inputStream instanceof DataInputStream) {
            this.dataInputStream = (DataInputStream) inputStream;
        } else {
            this.dataInputStream = new DataInputStream(new BufferedInputStream(inputStream, 8192));
        }
    }

    private void readAttributes() throws IOException, ClassFormatException {
        int unsignedShort = this.dataInputStream.readUnsignedShort();
        this.attributes = new Attribute[unsignedShort];
        for (int i = 0; i < unsignedShort; i++) {
            this.attributes[i] = Attribute.readAttribute(this.dataInputStream, this.constantPool);
        }
    }

    private void readClassInfo() throws IOException, ClassFormatException {
        int unsignedShort = this.dataInputStream.readUnsignedShort();
        this.accessFlags = unsignedShort;
        if ((unsignedShort & 512) != 0) {
            this.accessFlags = unsignedShort | 1024;
        }
        int i = this.accessFlags;
        if ((i & 1024) == 0 || (i & 16) == 0) {
            this.classNameIndex = this.dataInputStream.readUnsignedShort();
            this.superclassNameIndex = this.dataInputStream.readUnsignedShort();
        } else {
            throw new ClassFormatException("Class " + this.fileName + " can't be both final and abstract");
        }
    }

    private void readConstantPool() throws IOException, ClassFormatException {
        this.constantPool = new ConstantPool(this.dataInputStream);
    }

    private void readFields() throws IOException, ClassFormatException {
        int unsignedShort = this.dataInputStream.readUnsignedShort();
        this.fields = new Field[unsignedShort];
        for (int i = 0; i < unsignedShort; i++) {
            this.fields[i] = new Field(this.dataInputStream, this.constantPool);
        }
    }

    private void readID() throws IOException, ClassFormatException {
        if (this.dataInputStream.readInt() == -889275714) {
            return;
        }
        throw new ClassFormatException(this.fileName + " is not a Java .class file");
    }

    private void readInterfaces() throws IOException, ClassFormatException {
        int unsignedShort = this.dataInputStream.readUnsignedShort();
        this.interfaces = new int[unsignedShort];
        for (int i = 0; i < unsignedShort; i++) {
            this.interfaces[i] = this.dataInputStream.readUnsignedShort();
        }
    }

    private void readMethods() throws IOException {
        int unsignedShort = this.dataInputStream.readUnsignedShort();
        this.methods = new Method[unsignedShort];
        for (int i = 0; i < unsignedShort; i++) {
            this.methods[i] = new Method(this.dataInputStream, this.constantPool);
        }
    }

    private void readVersion() throws IOException, ClassFormatException {
        this.minor = this.dataInputStream.readUnsignedShort();
        this.major = this.dataInputStream.readUnsignedShort();
    }

    public JavaClass parse() throws IOException, ClassFormatException {
        ZipFile zipFile = null;
        try {
            if (this.fileOwned) {
                if (this.isZip) {
                    ZipFile zipFile2 = new ZipFile(this.zipFile);
                    try {
                        ZipEntry entry = zipFile2.getEntry(this.fileName);
                        if (entry == null) {
                            throw new IOException("File " + this.fileName + " not found");
                        }
                        this.dataInputStream = new DataInputStream(new BufferedInputStream(zipFile2.getInputStream(entry), 8192));
                        zipFile = zipFile2;
                    } catch (Throwable th) {
                        th = th;
                        zipFile = zipFile2;
                        if (this.fileOwned) {
                            try {
                                DataInputStream dataInputStream = this.dataInputStream;
                                if (dataInputStream != null) {
                                    dataInputStream.close();
                                }
                            } catch (IOException unused) {
                            }
                        }
                        if (zipFile == null) {
                            throw th;
                        }
                        try {
                            zipFile.close();
                            throw th;
                        } catch (IOException unused2) {
                            throw th;
                        }
                    }
                } else {
                    this.dataInputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(this.fileName), 8192));
                }
            }
            readID();
            readVersion();
            readConstantPool();
            readClassInfo();
            readInterfaces();
            readFields();
            readMethods();
            readAttributes();
            if (this.fileOwned) {
                try {
                    DataInputStream dataInputStream2 = this.dataInputStream;
                    if (dataInputStream2 != null) {
                        dataInputStream2.close();
                    }
                } catch (IOException unused3) {
                }
            }
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException unused4) {
                }
            }
            return new JavaClass(this.classNameIndex, this.superclassNameIndex, this.fileName, this.major, this.minor, this.accessFlags, this.constantPool, this.interfaces, this.fields, this.methods, this.attributes, this.isZip ? (byte) 3 : (byte) 2);
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public ClassParser(String str) {
        this.isZip = false;
        this.fileName = str;
        this.fileOwned = true;
    }

    public ClassParser(String str, String str2) {
        this.isZip = true;
        this.fileOwned = true;
        this.zipFile = str;
        this.fileName = str2;
    }
}
