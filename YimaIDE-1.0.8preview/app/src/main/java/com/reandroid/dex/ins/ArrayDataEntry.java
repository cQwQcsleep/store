package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.NumberBlock;
import com.reandroid.dex.ins.ArrayDataEntry;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliValueX;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ArrayDataEntry extends NumberBlock implements PayloadEntry {
    public ArrayDataEntry(int i) {
        super(i);
    }

    private void appendFloatOrDoubleComment(TypeKey typeKey, SmaliWriter smaliWriter) {
        if (typeKey == null || !smaliWriter.isEnableComments()) {
            return;
        }
        long j = getLong();
        if (j == 0) {
            return;
        }
        TypeKey arrayDimension = typeKey.setArrayDimension(0);
        if (TypeKey.TYPE_F.equals(arrayDimension)) {
            smaliWriter.appendComment(Float.intBitsToFloat((int) j) + "f");
            return;
        }
        if (TypeKey.TYPE_D.equals(arrayDimension)) {
            smaliWriter.appendComment(Double.toString(Double.longBitsToDouble(j)));
        } else if (TypeKey.TYPE_C.equals(arrayDimension)) {
            smaliWriter.appendComment("'" + ((char) j) + "'");
        }
    }

    public static /* synthetic */ ArrayDataEntry b(IntegerReference integerReference) {
        return new ArrayDataEntry(integerReference.get());
    }

    public static Creator<ArrayDataEntry> newCreator(final IntegerReference integerReference) {
        return new Creator() { // from class: ig0
            @Override // com.reandroid.arsc.base.Creator
            public final Block newInstance() {
                return ArrayDataEntry.b(integerReference);
            }
        };
    }

    public void append(TypeKey typeKey, SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendHex(width(), getLong());
        appendFloatOrDoubleComment(typeKey, smaliWriter);
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        return Block.areEqual(getBytesInternal(), ((ArrayDataEntry) obj).getBytesInternal());
    }

    public void fromSmali(SmaliValueX smaliValueX) {
        set(smaliValueX.getValueAsLong());
    }

    @Override // com.reandroid.dex.ins.PayloadEntry
    public InsArrayData getPayload() {
        return (InsArrayData) getParentInstance(InsArrayData.class);
    }

    public int hashCode() {
        return Block.hashCodeOf(getBytesInternal());
    }

    public void merge(ArrayDataEntry arrayDataEntry) {
        set(arrayDataEntry.getLong());
    }

    public SmaliValueX toSmali() {
        return new SmaliValueX(width(), getLong());
    }

    @Override // com.reandroid.arsc.item.NumberBlock
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(toHexString());
        int iWidth = width();
        if (iWidth == 1) {
            sb.append('t');
        } else if (iWidth == 2) {
            sb.append('S');
        } else if (iWidth == 8 && (getLong() & (-2147483648L)) != 0) {
            sb.append('L');
        }
        return sb.toString();
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        append(null, smaliWriter);
    }
}
