package com.sun.tools.classfile;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Attributes implements Iterable<Attribute> {
    public final Attribute[] attrs;
    public final Map<String, Attribute> map;

    public Attributes(ClassReader classReader) throws IOException {
        this.map = new HashMap();
        int unsignedShort = classReader.readUnsignedShort();
        this.attrs = new Attribute[unsignedShort];
        for (int i = 0; i < unsignedShort; i++) {
            Attribute attribute = Attribute.read(classReader);
            this.attrs[i] = attribute;
            try {
                this.map.put(attribute.getName(classReader.getConstantPool()), attribute);
            } catch (ConstantPoolException unused) {
            }
        }
    }

    public int byteLength() {
        int iByteLength = 2;
        for (Attribute attribute : this.attrs) {
            iByteLength += attribute.byteLength();
        }
        return iByteLength;
    }

    public Attribute get(String str) {
        return this.map.get(str);
    }

    public int getIndex(ConstantPool constantPool, String str) {
        int i = 0;
        while (true) {
            Attribute[] attributeArr = this.attrs;
            if (i >= attributeArr.length) {
                return -1;
            }
            Attribute attribute = attributeArr[i];
            if (attribute != null) {
                try {
                    if (attribute.getName(constantPool).equals(str)) {
                        return i;
                    }
                } catch (ConstantPoolException unused) {
                    continue;
                }
            }
            i++;
        }
    }

    @Override // java.lang.Iterable
    public Iterator<Attribute> iterator() {
        return Arrays.asList(this.attrs).iterator();
    }

    public int size() {
        return this.attrs.length;
    }

    public Attribute get(int i) {
        return this.attrs[i];
    }

    public Attributes(ConstantPool constantPool, Attribute[] attributeArr) {
        this.attrs = attributeArr;
        this.map = new HashMap();
        for (Attribute attribute : attributeArr) {
            try {
                this.map.put(attribute.getName(constantPool), attribute);
            } catch (ConstantPoolException unused) {
            }
        }
    }

    public Attributes(Map<String, Attribute> map) {
        this.attrs = (Attribute[]) map.values().toArray(new Attribute[map.size()]);
        this.map = map;
    }
}
