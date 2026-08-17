package com.reandroid.dex.ins;

import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.base.DexBlockAlign;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliPayloadArray;
import com.reandroid.dex.smali.model.SmaliSet;
import com.reandroid.dex.smali.model.SmaliValueX;
import com.reandroid.utils.StringsUtil;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class InsArrayDataList extends CountedBlockList<ArrayDataEntry> {
    private final DexBlockAlign alignment;
    private final IntegerReference widthReference;

    public InsArrayDataList(IntegerReference integerReference, IntegerReference integerReference2) {
        super(ArrayDataEntry.newCreator(integerReference), widthCheckingReference(integerReference, integerReference2));
        this.widthReference = integerReference;
        DexBlockAlign dexBlockAlign = new DexBlockAlign(this);
        this.alignment = dexBlockAlign;
        dexBlockAlign.setAlignment(2);
    }

    private static IntegerReference widthCheckingReference(final IntegerReference integerReference, final IntegerReference integerReference2) {
        return new IntegerReference() { // from class: com.reandroid.dex.ins.InsArrayDataList.1
            public int get() {
                if (integerReference.get() == 0) {
                    return 0;
                }
                return integerReference2.get();
            }

            public void set(int i) {
                integerReference2.set(i);
            }

            public String toString() {
                return Integer.toString(get());
            }
        };
    }

    public void append(TypeKey typeKey, SmaliWriter smaliWriter) throws IOException {
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            ArrayDataEntry arrayDataEntry = (ArrayDataEntry) it.next();
            smaliWriter.newLine();
            arrayDataEntry.append(typeKey, smaliWriter);
        }
    }

    public void clear() {
        setSize(0);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void fromSmali(SmaliPayloadArray smaliPayloadArray) {
        setWidth(smaliPayloadArray.getWidth());
        SmaliSet<SmaliValueX> entries = smaliPayloadArray.getEntries();
        int size = entries.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            ((ArrayDataEntry) get(i)).fromSmali((SmaliValueX) entries.get(i));
        }
    }

    public DexBlockAlign getAlignment() {
        return this.alignment;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public long[] getValues() {
        int size = size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = ((ArrayDataEntry) get(i)).getLong();
        }
        return jArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public byte[] getValuesAsByte() {
        int size = size();
        byte[] bArr = new byte[size];
        for (int i = 0; i < size; i++) {
            bArr[i] = (byte) ((ArrayDataEntry) get(i)).getLong();
        }
        return bArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public char[] getValuesAsChar() {
        int size = size();
        char[] cArr = new char[size];
        for (int i = 0; i < size; i++) {
            cArr[i] = (char) ((ArrayDataEntry) get(i)).getLong();
        }
        return cArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public double[] getValuesAsDouble() {
        int size = size();
        double[] dArr = new double[size];
        for (int i = 0; i < size; i++) {
            dArr[i] = Double.longBitsToDouble(((ArrayDataEntry) get(i)).getLong());
        }
        return dArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public float[] getValuesAsFloat() {
        int size = size();
        float[] fArr = new float[size];
        for (int i = 0; i < size; i++) {
            fArr[i] = Float.intBitsToFloat(((ArrayDataEntry) get(i)).get());
        }
        return fArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public int[] getValuesAsInt() {
        int size = size();
        int[] iArr = new int[size];
        for (int i = 0; i < size; i++) {
            iArr[i] = (int) ((ArrayDataEntry) get(i)).getLong();
        }
        return iArr;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public short[] getValuesAsShort() {
        int size = size();
        short[] sArr = new short[size];
        for (int i = 0; i < size; i++) {
            sArr[i] = (short) ((ArrayDataEntry) get(i)).getLong();
        }
        return sArr;
    }

    public int getWidth() {
        return this.widthReference.get();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void merge(InsArrayDataList insArrayDataList) {
        setWidth(insArrayDataList.getWidth());
        int size = insArrayDataList.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            ((ArrayDataEntry) get(i)).merge((ArrayDataEntry) insArrayDataList.get(i));
        }
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void setSize(int i) {
        super.setSize(i);
        IntegerReference countReference = getCountReference();
        if (i != countReference.get()) {
            countReference.set(i);
        }
        getAlignment().align();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setWidth(int i) {
        this.widthReference.set(i);
        if (i == 0) {
            clear();
            return;
        }
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            ((ArrayDataEntry) get(i2)).width(i);
        }
        getAlignment().align();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void toSmali(SmaliPayloadArray smaliPayloadArray) {
        int size = size();
        smaliPayloadArray.setWidth(getWidth());
        for (int i = 0; i < size; i++) {
            smaliPayloadArray.addEntry(((ArrayDataEntry) get(i)).toSmali());
        }
    }

    @Override // com.reandroid.arsc.container.BlockList
    public String toString() {
        return "width = " + getWidth() + ", size = " + size() + "\n  " + StringsUtil.join((Iterator<?>) iterator(), (Object) "\n  ");
    }
}
