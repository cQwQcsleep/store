package com.sun.org.apache.bcel.internal.generic;

import com.sun.org.apache.bcel.internal.classfile.ArrayElementValue;
import com.sun.org.apache.bcel.internal.classfile.ElementValue;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayElementValueGen extends ElementValueGen {
    private final List<ElementValueGen> evalues;

    public ArrayElementValueGen(int i, ElementValue[] elementValueArr, ConstantPoolGen constantPoolGen) {
        super(i, constantPoolGen);
        if (i != 91) {
            qf1.a("Only element values of type array can be built with this ctor - type specified: ", i);
            throw null;
        }
        this.evalues = new ArrayList();
        for (ElementValue elementValue : elementValueArr) {
            this.evalues.add(ElementValueGen.copy(elementValue, constantPoolGen, true));
        }
    }

    public void addElement(ElementValueGen elementValueGen) {
        this.evalues.add(elementValueGen);
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public void dump(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(super.getElementValueType());
        dataOutputStream.writeShort(this.evalues.size());
        Iterator<ElementValueGen> it = this.evalues.iterator();
        while (it.hasNext()) {
            it.next().dump(dataOutputStream);
        }
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public ElementValue getElementValue() {
        ElementValue[] elementValueArr = new ElementValue[this.evalues.size()];
        Iterator<ElementValueGen> it = this.evalues.iterator();
        int i = 0;
        while (it.hasNext()) {
            elementValueArr[i] = it.next().getElementValue();
            i++;
        }
        return new ArrayElementValue(super.getElementValueType(), elementValueArr, getConstantPool().getConstantPool());
    }

    public List<ElementValueGen> getElementValues() {
        return this.evalues;
    }

    public int getElementValuesSize() {
        return this.evalues.size();
    }

    @Override // com.sun.org.apache.bcel.internal.generic.ElementValueGen
    public String stringifyValue() {
        StringBuilder sb = new StringBuilder("[");
        String str = "";
        for (ElementValueGen elementValueGen : this.evalues) {
            sb.append(str);
            sb.append(elementValueGen.stringifyValue());
            str = ",";
        }
        sb.append("]");
        return sb.toString();
    }

    public ArrayElementValueGen(ConstantPoolGen constantPoolGen) {
        super(91, constantPoolGen);
        this.evalues = new ArrayList();
    }

    public ArrayElementValueGen(ArrayElementValue arrayElementValue, ConstantPoolGen constantPoolGen, boolean z) {
        super(91, constantPoolGen);
        this.evalues = new ArrayList();
        for (ElementValue elementValue : arrayElementValue.getElementValuesArray()) {
            this.evalues.add(ElementValueGen.copy(elementValue, constantPoolGen, z));
        }
    }
}
