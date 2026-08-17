package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ShortItem;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.ins.PayloadEntry;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.model.SmaliInstruction;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class PayloadData<T extends PayloadEntry> extends Ins implements SmaliRegion, Iterable<T> {
    public PayloadData(int i, Opcode<?> opcode) {
        super(i + 1, opcode);
        ShortItem shortItem = new ShortItem();
        shortItem.set(opcode.getValue());
        addChild(0, shortItem);
    }

    private InsNop getNopAlignment() {
        InstructionList instructionList = getInstructionList();
        if (instructionList == null) {
            return null;
        }
        Ins ins = instructionList.get(getIndex() - 1);
        if (ins instanceof InsNop) {
            return (InsNop) ins;
        }
        return null;
    }

    @Override // com.reandroid.dex.ins.Ins
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PayloadData payloadData = (PayloadData) obj;
        if (getIndex() != payloadData.getIndex()) {
            return false;
        }
        return Block.areEqual(getChildes(), payloadData.getChildes());
    }

    @Override // com.reandroid.dex.ins.Ins
    public abstract void fromSmali(SmaliInstruction smaliInstruction);

    public abstract T get(int i);

    public abstract Iterator<IntegerReference> getReferences();

    public int hashCode() {
        return Block.hashCodeOf(getChildes()) + getIndex();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public abstract Iterator<T> iterator();

    public void onPreRefresh() {
        updateNopAlignment();
        super/*com.reandroid.arsc.base.BlockContainer*/.onPreRefresh();
    }

    public void releaseLock(Object obj) {
        if (obj != null) {
            getInsBlockList().unlinkLocked(obj);
        }
    }

    public Object requestLock() {
        InsBlockList insBlockList = getInsBlockList();
        if (insBlockList != null) {
            return insBlockList.linkLocked();
        }
        return null;
    }

    public abstract void setSize(int i);

    public abstract int size();

    public void toSmaliEntries(SmaliInstruction smaliInstruction) {
    }

    @Override // com.reandroid.dex.ins.Ins
    public void toSmaliOperand(SmaliInstruction smaliInstruction) {
        super.toSmaliOperand(smaliInstruction);
    }

    @Override // com.reandroid.dex.ins.Ins
    public void toSmaliOthers(SmaliInstruction smaliInstruction) {
        super.toSmaliOthers(smaliInstruction);
        toSmaliEntries(smaliInstruction);
    }

    public void updateNopAlignment() {
        InstructionList instructionList = getInstructionList();
        if (instructionList == null || instructionList.countUpTo(this) % 4 == 0) {
            return;
        }
        InsNop nopAlignment = getNopAlignment();
        if (nopAlignment != null) {
            instructionList.remove(nopAlignment);
        } else {
            instructionList.add(false, getIndex(), Opcode.NOP.newInstance());
        }
    }
}
