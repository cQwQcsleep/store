package com.sun.org.apache.bcel.internal.classfile;

import defpackage.yr2;
import java.io.DataOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayElementValue extends ElementValue {
    private final ElementValue[] elementValues;

    public ArrayElementValue(int i, ElementValue[] elementValueArr, ConstantPool constantPool) {
        super(i, constantPool);
        if (i == 91) {
            this.elementValues = elementValueArr;
        } else {
            yr2.a("Only element values of type array can be built with this ctor - type specified: ", i);
            throw null;
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getType());
        dataOutputStream.writeShort(this.elementValues.length);
        for (ElementValue elementValue : this.elementValues) {
            elementValue.dump(dataOutputStream);
        }
    }

    public ElementValue[] getElementValuesArray() {
        return this.elementValues;
    }

    public int getElementValuesArraySize() {
        return this.elementValues.length;
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public String stringifyValue() {
        StringBuilder sb = new StringBuilder("[");
        int i = 0;
        while (true) {
            ElementValue[] elementValueArr = this.elementValues;
            if (i >= elementValueArr.length) {
                sb.append("]");
                return sb.toString();
            }
            sb.append(elementValueArr[i].stringifyValue());
            i++;
            if (i < this.elementValues.length) {
                sb.append(",");
            }
        }
    }

    @Override // com.sun.org.apache.bcel.internal.classfile.ElementValue
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        int i = 0;
        while (true) {
            ElementValue[] elementValueArr = this.elementValues;
            if (i >= elementValueArr.length) {
                sb.append("}");
                return sb.toString();
            }
            sb.append(elementValueArr[i]);
            i++;
            if (i < this.elementValues.length) {
                sb.append(",");
            }
        }
    }
}
