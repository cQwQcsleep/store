package com.reandroid.dex.ins;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.common.OperandType;
import com.reandroid.dex.common.RegisterFormat;
import com.reandroid.dex.data.FixedDexContainerWithTool;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.data.MethodDef;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.ins.Ins;
import com.reandroid.dex.ins.Label;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliCodeSet;
import com.reandroid.dex.smali.model.SmaliInstruction;
import com.reandroid.dex.smali.model.SmaliLabel;
import com.reandroid.utils.ObjectsStore;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.InstanceIterator;
import defpackage.jq6;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins extends FixedDexContainerWithTool implements SmaliFormat {
    private Object extraLineList;
    private final Opcode<?> opcode;
    private Ins targetIns;

    public Ins(int i, Opcode<?> opcode) {
        super(i);
        this.opcode = opcode;
    }

    private void appendExtraLines(SmaliWriter smaliWriter) throws IOException {
        Iterator<ExtraLine> extraLines = getExtraLines();
        Object obj = null;
        ExtraLine next = null;
        boolean z = false;
        while (extraLines.hasNext()) {
            next = extraLines.next();
            if (!next.isEqualExtraLine(obj)) {
                smaliWriter.newLine();
                next.appendExtra(smaliWriter);
                if (!z) {
                    z = next.getSortOrder() == 3;
                }
                obj = next;
            }
        }
        if (!z || next.getSortOrder() < 3) {
            return;
        }
        smaliWriter.newLine();
    }

    public static /* synthetic */ boolean b(Label label) {
        return !(label instanceof ExceptionLabel);
    }

    private Ins ensureTargetNotRemoved() {
        Ins ins = this.targetIns;
        if (ins == null || !ins.isRemoved()) {
            return ins;
        }
        this.targetIns = null;
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private Ins findTargetIns() {
        InsBlockList insBlockList;
        if (!(this instanceof Label) || (insBlockList = getInsBlockList()) == null) {
            return null;
        }
        int targetAddress = ((Label) this).getTargetAddress();
        Ins atAddress = insBlockList.getAtAddress(targetAddress);
        if (targetAddress == 0 && atAddress == this) {
            return null;
        }
        return atAddress;
    }

    public static int toSigned(int i, int i2) {
        return i <= i2 / 2 ? i : (i - i2) - 1;
    }

    private void toSmali(SmaliInstruction smaliInstruction) {
        if (smaliInstruction.getOperandType() != OperandType.NONE) {
            toSmaliOperand(smaliInstruction);
        }
        if (smaliInstruction.getRegisterFormat() != RegisterFormat.NONE) {
            toSmaliRegisters(smaliInstruction);
        }
        if (hasExtraLines() && smaliInstruction.getCodeSet() != null) {
            toSmaliExtraLines(smaliInstruction);
        }
        toSmaliOthers(smaliInstruction);
    }

    public void addExtraLine(ExtraLine extraLine) {
        if (extraLine != this) {
            this.extraLineList = ObjectsStore.add(this.extraLineList, extraLine);
        }
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public final void append(SmaliWriter smaliWriter) throws IOException {
        appendExtraLines(smaliWriter);
        smaliWriter.newLine();
        appendCode(smaliWriter);
    }

    public void appendCode(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.append((CharSequence) getOpcode().getName());
        smaliWriter.append(' ');
    }

    public void clearExtraLines() {
        this.extraLineList = ObjectsStore.clear(this.extraLineList);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public <T1 extends Ins> T1 createNext(Opcode<T1> opcode) throws DexException {
        InstructionList instructionList = getInstructionList();
        if (instructionList != null) {
            return (T1) instructionList.createAt(getIndex() + 1, opcode);
        }
        jq6.a("Parent ", getClass().getSimpleName(), " == null");
        return null;
    }

    public Ins edit() {
        MethodDef methodDef = getMethodDef();
        InstructionList instructionList = methodDef.getInstructionList();
        methodDef.edit();
        InstructionList instructionList2 = methodDef.getInstructionList();
        return instructionList != instructionList2 ? instructionList2.get(getIndex()) : this;
    }

    public boolean equals(Object obj) {
        return obj == this;
    }

    public void fromSmali(SmaliInstruction smaliInstruction) {
        throw new RuntimeException("fromSmali method not implemented, opcode = " + getOpcode());
    }

    public int getAddress() {
        InsBlockList insBlockList = getInsBlockList();
        if (insBlockList != null) {
            return insBlockList.addressOf(this);
        }
        return -1;
    }

    public int getCodeUnits() {
        return countBytes() / 2;
    }

    public Iterator<ExtraLine> getExtraLines() {
        ObjectsStore.sort(this.extraLineList, ExtraLine.COMPARATOR);
        return ObjectsStore.iterator(this.extraLineList);
    }

    public InsBlockList getInsBlockList() {
        return (InsBlockList) getParentInstance(InsBlockList.class);
    }

    public InstructionList getInstructionList() {
        return (InstructionList) getParentInstance(InstructionList.class);
    }

    public MethodDef getMethodDef() {
        InstructionList instructionList = getInstructionList();
        if (instructionList != null) {
            return instructionList.getMethodDef();
        }
        return null;
    }

    public MethodKey getMethodKey() {
        MethodDef methodDef = getMethodDef();
        if (methodDef != null) {
            return methodDef.getKey();
        }
        return null;
    }

    public Opcode<?> getOpcode() {
        return this.opcode;
    }

    public int getOutSize() {
        return 0;
    }

    public RegisterFormat getRegisterFormat() {
        return getOpcode().getRegisterFormat();
    }

    public Ins getTargetIns() {
        Ins insEnsureTargetNotRemoved = ensureTargetNotRemoved();
        if (insEnsureTargetNotRemoved != null) {
            return insEnsureTargetNotRemoved;
        }
        setTargetIns(findTargetIns());
        return this.targetIns;
    }

    public boolean hasExtraLines() {
        return !ObjectsStore.isEmpty(this.extraLineList);
    }

    public boolean is(Opcode<?> opcode) {
        return opcode == getOpcode();
    }

    public boolean isRemoved() {
        return getParent() == null;
    }

    public void linkTargetIns() {
        if (this.targetIns == null) {
            setTargetIns(findTargetIns());
        }
        if ((this instanceof Label) && this.targetIns == null) {
            StringBuilder sb = new StringBuilder("Missing target: ");
            sb.append(this);
            MethodKey methodKey = getMethodKey();
            sb.append(", ");
            sb.append(methodKey);
            throw new NullPointerException(sb.toString());
        }
    }

    public void merge(Ins ins) {
        throw new RuntimeException("merge method not implemented, opcode = " + getOpcode());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void moveTo(int i) throws DexException {
        InstructionList instructionList = getInstructionList();
        if (instructionList != null) {
            instructionList.moveTo(this, i);
        } else {
            jq6.a("Parent ", getClass().getSimpleName(), " == null");
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public <T1 extends Ins> T1 replace(Opcode<T1> opcode) throws DexException {
        InstructionList instructionList = getInstructionList();
        if (instructionList != null) {
            return (T1) instructionList.replace(this, opcode);
        }
        throw new DexException("Missing parent ".concat(InstructionList.class.getSimpleName()));
    }

    public void replaceKeys(Key key, Key key2) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setTargetIns(Ins ins) {
        if (ins == this || ins == this.targetIns) {
            return;
        }
        this.targetIns = ins;
        if (ins != null) {
            Label label = (Label) this;
            label.setTargetAddress(ins.getAddress());
            ins.addExtraLine(label);
        }
    }

    public void toSmaliExtraLines(SmaliInstruction smaliInstruction) {
        SmaliCodeSet codeSet = smaliInstruction.getCodeSet();
        Iterator itOf = InstanceIterator.of(getExtraLines(), Label.class, new Predicate() { // from class: kq6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Ins.b((Label) obj);
            }
        });
        int iIndexOf = codeSet.indexOf(smaliInstruction);
        Object obj = null;
        while (itOf.hasNext()) {
            Label label = (Label) itOf.next();
            if (!label.isEqualExtraLine(obj)) {
                SmaliLabel smaliLabel = new SmaliLabel();
                codeSet.add(iIndexOf, smaliLabel);
                smaliLabel.setLabelName(label.getLabelName());
                iIndexOf++;
                obj = label;
            }
        }
    }

    public void toSmaliOperand(SmaliInstruction smaliInstruction) {
    }

    public void toSmaliOthers(SmaliInstruction smaliInstruction) {
    }

    public void toSmaliRegisters(SmaliInstruction smaliInstruction) {
    }

    public String toString() {
        StringWriter stringWriter = new StringWriter();
        SmaliWriter smaliWriter = new SmaliWriter(stringWriter);
        try {
            appendCode(smaliWriter);
            smaliWriter.close();
            return stringWriter.toString().trim();
        } catch (Throwable th) {
            return th.toString();
        }
    }

    public void transferExtraLinesTo(Ins ins) {
        ins.extraLineList = ObjectsStore.addAll(ins.extraLineList, getExtraLines());
        Iterator<ExtraLine> extraLines = ins.getExtraLines();
        while (extraLines.hasNext()) {
            ExtraLine next = extraLines.next();
            next.setTargetIns(null);
            next.setTargetIns(ins);
        }
        Ins ins2 = this.targetIns;
        if (ins2 != null && (ins instanceof Label)) {
            ins.setTargetIns(ins2);
        }
        clearExtraLines();
    }

    public void unLinkTargetIns() {
        if (this.targetIns != null) {
            setTargetIns(null);
        }
        clearExtraLines();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void updateTargetAddress() {
        if (this instanceof Label) {
            Ins targetIns = getTargetIns();
            if (targetIns != null) {
                ((Label) this).setTargetAddress(targetIns.getAddress());
                return;
            }
            StringBuilder sb = new StringBuilder("Null target: ");
            sb.append(this);
            MethodKey methodKey = getMethodKey();
            sb.append(", ");
            sb.append(methodKey);
            throw new NullPointerException(sb.toString());
        }
    }

    public Iterator<IdItem> usedIds() {
        return EmptyIterator.of();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public void validateOpcode(SmaliInstruction smaliInstruction) throws DexException {
        if (getOpcode() == smaliInstruction.getOpcode()) {
            return;
        }
        StringBuilder sb = new StringBuilder("Mismatch opcode ");
        sb.append(getOpcode());
        Opcode<?> opcode = smaliInstruction.getOpcode();
        sb.append(" vs ");
        sb.append(opcode);
        throw new DexException(sb.toString());
    }

    public Ins(Opcode<?> opcode) {
        this(1, opcode);
    }

    public <T1> Iterator<T1> getExtraLines(Class<T1> cls) {
        ObjectsStore.sort(this.extraLineList, ExtraLine.COMPARATOR);
        return ObjectsStore.iterator(this.extraLineList, cls);
    }

    public void replace(Ins ins) {
        InstructionList instructionList;
        if (ins == null || ins == this || (instructionList = getInstructionList()) == null) {
            return;
        }
        instructionList.replace(this, ins);
    }

    public SmaliInstruction toSmali(SmaliCodeSet smaliCodeSet) {
        SmaliInstruction smaliInstructionNewInstruction = smaliCodeSet.newInstruction(getOpcode());
        toSmali(smaliInstructionNewInstruction);
        return smaliInstructionNewInstruction;
    }

    public SmaliInstruction toSmali() {
        return toSmali(new SmaliCodeSet());
    }
}
