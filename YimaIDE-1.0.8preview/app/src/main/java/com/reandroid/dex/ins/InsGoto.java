package com.reandroid.dex.ins;

import com.reandroid.arsc.item.ByteArray;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.HexUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsGoto extends SizeXIns implements Label {
    private Opcode<?> opcode;

    public InsGoto(Opcode<?> opcode) {
        super(opcode);
        this.opcode = opcode;
    }

    private boolean canFitOpcode(int i) {
        int size = getOpcode().size();
        if (size == 2) {
            return i == ((byte) i);
        }
        return size != 4 || i == ((short) i);
    }

    private void ensureFittingOpcode(int i) {
        Opcode<InsGoto> opcode;
        int size = getOpcode().size();
        if (size != 2 || i == ((byte) i)) {
            opcode = (size != 4 || i == ((short) i)) ? null : Opcode.GOTO_32;
        } else {
            opcode = i == ((short) i) ? Opcode.GOTO_16 : Opcode.GOTO_32;
        }
        if (opcode != null) {
            setOpcode(opcode);
        }
    }

    @Override // com.reandroid.dex.ins.SizeXIns, com.reandroid.dex.ins.Ins
    public void appendCode(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getOpcode().getName());
        smaliWriter.append(' ');
        smaliWriter.appendLabelName(getLabelName());
    }

    @Override // com.reandroid.dex.ins.Ins
    public int getCodeUnits() {
        return getOpcode().size() / 2;
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public int getData() {
        int size = getOpcode().size();
        if (size == 2) {
            return getByteSigned();
        }
        return size == 4 ? getShortSigned() : getInteger();
    }

    @Override // com.reandroid.dex.ins.Label
    public String getLabelName() {
        return HexUtil.toHex(":goto_", getTargetAddress(), 1);
    }

    @Override // com.reandroid.dex.ins.Ins
    public Opcode<?> getOpcode() {
        return this.opcode;
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public int getSortOrder() {
        return 5;
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public int getTargetAddress() {
        return getAddress() + getData();
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        int size = getOpcode().size();
        if (size == 2) {
            setByte(1, i);
        } else if (size == 4) {
            setShort(2, i);
        } else {
            setInteger(i);
        }
    }

    public void setOpcode(Opcode<InsGoto> opcode) {
        if (opcode == this.opcode) {
            return;
        }
        int data = getData();
        InsBlockList insBlockList = getInsBlockList();
        if (insBlockList != null) {
            insBlockList.link();
        }
        this.opcode = opcode;
        ByteArray valueBytes = getValueBytes();
        valueBytes.setSize(0);
        valueBytes.setSize(opcode.size());
        valueBytes.putShort(0, opcode.getValue());
        setData(data);
        if (insBlockList != null) {
            insBlockList.unlink();
        }
    }

    @Override // com.reandroid.dex.ins.ExtraLine
    public void setTargetAddress(int i) {
        int address = i - getAddress();
        if (!canFitOpcode(address)) {
            InsBlockList insBlockList = getInsBlockList();
            Ins atAddress = insBlockList != null ? insBlockList.getAtAddress(i) : null;
            ensureFittingOpcode(address);
            if (atAddress != null) {
                i = atAddress.getAddress();
            }
            address = i - getAddress();
        }
        setData(address);
    }
}
