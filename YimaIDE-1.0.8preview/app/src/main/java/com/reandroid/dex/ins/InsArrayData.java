package com.reandroid.dex.ins;

import com.reandroid.arsc.item.IntegerItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliInstruction;
import com.reandroid.dex.smali.model.SmaliInstructionOperand;
import com.reandroid.dex.smali.model.SmaliPayloadArray;
import com.reandroid.utils.NumberX;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.InstanceIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsArrayData extends PayloadData<ArrayDataEntry> implements SmaliRegion {
    private final InsArrayDataList entryList;

    public InsArrayData() {
        super(4, Opcode.ARRAY_PAYLOAD);
        ShortItem shortItem = new ShortItem();
        IntegerItem integerItem = new IntegerItem();
        InsArrayDataList insArrayDataList = new InsArrayDataList(shortItem, integerItem);
        this.entryList = insArrayDataList;
        addChild(1, shortItem);
        addChild(2, integerItem);
        addChild(3, insArrayDataList);
        addChild(4, insArrayDataList.getAlignment());
    }

    private TypeKey findNewArrayType() {
        Iterator<InsFillArrayData> insFillArrayData = getInsFillArrayData();
        while (insFillArrayData.hasNext()) {
            Ins22c ins22cFindNewArrayLazy = insFillArrayData.next().findNewArrayLazy();
            if (ins22cFindNewArrayLazy != null) {
                return (TypeKey) ins22cFindNewArrayLazy.getKey();
            }
        }
        return null;
    }

    public void addValue(long j) {
        int size = size();
        setSize(size + 1);
        get(size).set(j);
    }

    public void addValues(double[] dArr) {
        int size = size();
        int length = dArr.length;
        if (length != 0) {
            ensureMinWidth(8);
        }
        setSize(size + length);
        for (int i = 0; i < length; i++) {
            get(size + i).set(Double.doubleToLongBits(dArr[i]));
        }
    }

    @Override // com.reandroid.dex.ins.Ins
    public void appendCode(SmaliWriter smaliWriter) throws IOException {
        getSmaliDirective().append(smaliWriter);
        smaliWriter.appendInteger(getWidth());
        smaliWriter.indentPlus();
        getEntryList().append(findNewArrayType(), smaliWriter);
        smaliWriter.indentMinus();
        getSmaliDirective().appendEnd(smaliWriter);
    }

    public void clear() {
        Object objRequestLock = requestLock();
        getEntryList().clear();
        releaseLock(objRequestLock);
    }

    public void ensureMinSize(int i) {
        if (i > size()) {
            setSize(i);
        }
    }

    public void ensureMinWidth(int i) {
        if (i > getWidth()) {
            setWidth(NumberX.toStandardWidth(i));
        }
    }

    @Override // com.reandroid.dex.ins.PayloadData, com.reandroid.dex.ins.Ins
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        InsArrayData insArrayData = (InsArrayData) obj;
        if (getIndex() == insArrayData.getIndex() && getWidth() == insArrayData.getWidth()) {
            return this.entryList.equals(insArrayData.entryList);
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.ins.PayloadData, com.reandroid.dex.ins.Ins
    public void fromSmali(SmaliInstruction smaliInstruction) throws DexException {
        validateOpcode(smaliInstruction);
        getEntryList().fromSmali((SmaliPayloadArray) smaliInstruction);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.reandroid.dex.ins.PayloadData
    public ArrayDataEntry get(int i) {
        return (ArrayDataEntry) getEntryList().get(i);
    }

    public InsArrayDataList getEntryList() {
        return this.entryList;
    }

    public Iterator<InsFillArrayData> getInsFillArrayData() {
        InsBlockList insBlockList = getInsBlockList();
        if (insBlockList == null) {
            return EmptyIterator.of();
        }
        insBlockList.link();
        return InstanceIterator.of(getExtraLines(), InsFillArrayData.class);
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public Iterator<IntegerReference> getReferences() {
        return (Iterator) ObjectsUtil.cast(getEntryList().iterator());
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.ARRAY_DATA;
    }

    public byte[] getValuesAsByte() {
        return getEntryList().getValuesAsByte();
    }

    public char[] getValuesAsChar() {
        return getEntryList().getValuesAsChar();
    }

    public double[] getValuesAsDouble() {
        return getEntryList().getValuesAsDouble();
    }

    public float[] getValuesAsFloat() {
        return getEntryList().getValuesAsFloat();
    }

    public int[] getValuesAsInt() {
        return getEntryList().getValuesAsInt();
    }

    public long[] getValuesAsLong() {
        return getEntryList().getValues();
    }

    public short[] getValuesAsShort() {
        return getEntryList().getValuesAsShort();
    }

    public int getWidth() {
        return getEntryList().getWidth();
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public int hashCode() {
        return (getIndex() * 31) + 31 + (getWidth() * 31) + (this.entryList.hashCode() * 31);
    }

    @Override // com.reandroid.dex.ins.PayloadData, java.lang.Iterable
    public Iterator<ArrayDataEntry> iterator() {
        return (Iterator) ObjectsUtil.cast(getEntryList().clonedIterator());
    }

    @Override // com.reandroid.dex.ins.Ins
    public void merge(Ins ins) {
        getEntryList().merge(((InsArrayData) ins).getEntryList());
    }

    public void put(int i, long j) {
        ensureMinSize(i + 1);
        get(i).set(j);
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public void setSize(int i) {
        if (getWidth() == 0) {
            i = 0;
        }
        Object objRequestLock = requestLock();
        getEntryList().setSize(i);
        releaseLock(objRequestLock);
    }

    public void setWidth(int i) {
        Object objRequestLock = requestLock();
        getEntryList().setWidth(i);
        releaseLock(objRequestLock);
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public int size() {
        return getEntryList().size();
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public void toSmaliEntries(SmaliInstruction smaliInstruction) {
        super.toSmaliEntries(smaliInstruction);
        getEntryList().toSmali((SmaliPayloadArray) smaliInstruction);
    }

    @Override // com.reandroid.dex.ins.PayloadData, com.reandroid.dex.ins.Ins
    public void toSmaliOperand(SmaliInstruction smaliInstruction) {
        super.toSmaliOperand(smaliInstruction);
        ((SmaliInstructionOperand.SmaliDecimalOperand) smaliInstruction.getOperand()).setNumber(getWidth());
    }

    public void addValues(int[] iArr) {
        int size = size();
        int length = iArr.length;
        setSize(size + length);
        for (int i = 0; i < length; i++) {
            get(size + i).set(iArr[i]);
        }
    }

    public void addValues(short[] sArr) {
        int size = size();
        int length = sArr.length;
        setSize(size + length);
        for (int i = 0; i < length; i++) {
            get(size + i).set((int) sArr[i]);
        }
    }

    public void addValues(byte[] bArr) {
        int size = size();
        int length = bArr.length;
        setSize(size + length);
        for (int i = 0; i < length; i++) {
            get(size + i).set((int) bArr[i]);
        }
    }

    public void addValues(char[] cArr) {
        int size = size();
        int length = cArr.length;
        setSize(size + length);
        for (int i = 0; i < length; i++) {
            get(size + i).set((int) cArr[i]);
        }
    }

    public void addValues(float[] fArr) {
        int size = size();
        int length = fArr.length;
        if (length != 0) {
            ensureMinWidth(4);
        }
        setSize(size + length);
        for (int i = 0; i < length; i++) {
            get(size + i).set(Float.floatToIntBits(fArr[i]));
        }
    }

    public void addValues(long[] jArr) {
        int size = size();
        int length = jArr.length;
        setSize(size + length);
        for (int i = 0; i < length; i++) {
            get(size + i).set(jArr[i]);
        }
    }
}
