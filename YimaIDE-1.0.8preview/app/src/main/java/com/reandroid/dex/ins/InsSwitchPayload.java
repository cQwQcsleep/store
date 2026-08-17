package com.reandroid.dex.ins;

import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.common.Register;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.ins.SwitchEntry;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.utils.ObjectsUtil;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class InsSwitchPayload<T extends SwitchEntry> extends PayloadData<T> implements LabelsSet, SmaliRegion, SmaliFormat {
    private InsSwitch insSwitch;

    public InsSwitchPayload(int i, Opcode<?> opcode) {
        super(i, opcode);
    }

    private InsSwitch findByAddress() {
        InstructionList instructionList = getInstructionList();
        if (instructionList == null) {
            return null;
        }
        Iterator it = instructionList.iterator(getSwitchOpcode());
        int address = getAddress();
        while (it.hasNext()) {
            InsSwitch insSwitch = (InsSwitch) it.next();
            if (insSwitch.getTargetAddress() == address) {
                return insSwitch;
            }
        }
        return null;
    }

    private InsSwitch findOnExtraLines() {
        Iterator<ExtraLine> extraLines = getExtraLines();
        while (extraLines.hasNext()) {
            ExtraLine next = extraLines.next();
            if (next instanceof InsSwitch) {
                InsSwitch insSwitch = (InsSwitch) next;
                if (insSwitch.getOpcode() == getSwitchOpcode()) {
                    return insSwitch;
                }
            }
        }
        return null;
    }

    @Override // com.reandroid.dex.ins.PayloadData
    public Iterator<IntegerReference> getReferences() {
        return (Iterator) ObjectsUtil.cast(iterator());
    }

    public InsSwitch getSwitch() {
        InsSwitch insSwitchFindOnExtraLines = this.insSwitch;
        if (insSwitchFindOnExtraLines == null) {
            insSwitchFindOnExtraLines = findOnExtraLines();
            if (insSwitchFindOnExtraLines == null) {
                insSwitchFindOnExtraLines = findByAddress();
            }
            this.insSwitch = insSwitchFindOnExtraLines;
            if (insSwitchFindOnExtraLines != null) {
                insSwitchFindOnExtraLines.setPayload(this);
            }
        }
        return insSwitchFindOnExtraLines;
    }

    public abstract Opcode<? extends InsSwitch> getSwitchOpcode();

    @Override // com.reandroid.dex.ins.Ins
    public void linkTargetIns() {
        super.linkTargetIns();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().getTargetIns();
        }
    }

    public boolean replaceByIfEq() {
        InstructionList instructionList = getInstructionList();
        RegistersTable registersTable = instructionList.getRegistersTable();
        int localRegistersCount = registersTable.getLocalRegistersCount();
        InsSwitch insSwitch = getSwitch();
        if (!registersTable.ensureLocalRegistersCount(localRegistersCount + 1)) {
            List<Register> localFreeRegisters = instructionList.getLocalFreeRegisters(insSwitch.getIndex());
            if (localFreeRegisters.isEmpty()) {
                return false;
            }
            localRegistersCount = localFreeRegisters.get(0).getValue();
        }
        if (localRegistersCount > 15) {
            return false;
        }
        replaceByIfEq(localRegistersCount);
        return true;
    }

    public void setSwitch(InsSwitch insSwitch) {
        if (insSwitch == null) {
            this.insSwitch = null;
            return;
        }
        if (getSwitchOpcode() == insSwitch.getOpcode()) {
            this.insSwitch = insSwitch;
            addExtraLine(insSwitch);
            insSwitch.setPayload(this);
        } else {
            StringBuilder sb = new StringBuilder("Incompatible switch opcode: '");
            sb.append(getSwitchOpcode());
            Opcode<?> opcode = insSwitch.getOpcode();
            sb.append("' vs '");
            sb.append(opcode);
            sb.append("'");
            throw new ClassCastException(sb.toString());
        }
    }

    @Override // com.reandroid.dex.ins.Ins
    public void unLinkTargetIns() {
        super.unLinkTargetIns();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().setTargetIns(null);
        }
    }

    @Override // com.reandroid.dex.ins.Ins
    public void updateTargetAddress() {
        super.updateTargetAddress();
        getSwitch().setTargetIns(this);
        getSwitch().updateTargetAddress();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().updateTargetAddress();
        }
    }

    public void replaceByIfEq(int i) {
        InsSwitch insSwitch = getSwitch();
        InsBlockList insBlockList = getInsBlockList();
        Object objLink = insBlockList.link(new Object());
        linkTargetIns();
        Iterator<T> it = iterator();
        while (it.hasNext()) {
            it.next().addEquivalentIfEq(i);
        }
        InstructionList instructionList = getInstructionList();
        instructionList.remove(insSwitch);
        instructionList.remove(this);
        insBlockList.unlinkLocked(objLink);
    }
}
