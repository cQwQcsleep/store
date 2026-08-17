package com.reandroid.dex.smali.model;

import com.reandroid.dex.ins.InsArrayData;
import com.reandroid.dex.ins.Opcode;
import com.reandroid.dex.key.PrimitiveKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliParseException;
import com.reandroid.dex.smali.SmaliReader;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SmaliPayloadArray extends SmaliInstructionPayload<SmaliValueX> {
    public SmaliPayloadArray() {
        super(new SmaliInstructionOperand.SmaliDecimalOperand());
    }

    public void addEntry(PrimitiveKey primitiveKey) {
        newEntry().setKey(primitiveKey);
    }

    public void addEntryKeys(Iterator<PrimitiveKey> it) {
        while (it.hasNext()) {
            addEntry(it.next());
        }
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload
    public SmaliValueX createEntry() {
        SmaliValueX smaliValueX = new SmaliValueX();
        smaliValueX.setWidth(getWidth());
        return smaliValueX;
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload, com.reandroid.dex.smali.model.SmaliInstruction
    public int getCodeUnits() {
        int width = (getWidth() * getCount()) + 8;
        return (width + ((2 - (width % 2)) % 2)) / 2;
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload, com.reandroid.dex.smali.model.SmaliInstruction
    public Opcode<InsArrayData> getOpcode() {
        return Opcode.ARRAY_PAYLOAD;
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload, com.reandroid.dex.smali.model.SmaliInstruction
    public SmaliInstructionOperand.SmaliDecimalOperand getOperand() {
        return (SmaliInstructionOperand.SmaliDecimalOperand) super.getOperand();
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.ARRAY_DATA;
    }

    public long[] getValuesAsLong() {
        SmaliSet<SmaliValueX> entries = getEntries();
        int size = entries.size();
        long[] jArr = new long[size];
        for (int i = 0; i < size; i++) {
            jArr[i] = ((SmaliValueX) entries.get(i)).getValueAsLong();
        }
        return jArr;
    }

    public int getWidth() {
        return getOperand().getNumber();
    }

    @Override // com.reandroid.dex.smali.model.SmaliInstructionPayload
    public void parseOperand(Opcode<?> opcode, SmaliReader smaliReader) throws IOException {
        smaliReader.skipWhitespacesOrComment();
        int iPosition = smaliReader.position();
        SmaliInstructionOperand.SmaliDecimalOperand operand = getOperand();
        operand.parse(opcode, smaliReader);
        int number = operand.getNumber();
        if (number < 0 || number > 8) {
            smaliReader.position(iPosition);
            throw new SmaliParseException("Array width out of range (0 .. 8) : '" + number + "'", smaliReader);
        }
    }

    public void setWidth(int i) {
        getOperand().setNumber(i);
        SmaliSet<SmaliValueX> entries = getEntries();
        int size = entries.size();
        for (int i2 = 0; i2 < size; i2++) {
            ((SmaliValueX) entries.get(i2)).setWidth(i);
        }
    }
}
