package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.AlignItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.data.MethodDef;
import com.reandroid.dex.debug.DebugElement;
import com.reandroid.utils.NumbersUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.IterableIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InsBlockList extends BlockList<Ins> {
    private final AlignItem blockAlign;
    private final IntegerReference codeUnitsReference;
    private final Iterable<? extends ExtraLine> extraLines;
    private boolean mLinked;
    private boolean mLocked;
    private Object mLockedBy;
    private NullInstruction mNullInstruction;
    private boolean mSecondUpdateRequired;
    private final IntegerReference outSizReference;

    public InsBlockList(AlignItem alignItem, IntegerReference integerReference, IntegerReference integerReference2, Iterable<? extends ExtraLine> iterable) {
        this.blockAlign = alignItem;
        this.codeUnitsReference = integerReference;
        this.outSizReference = integerReference2;
        this.extraLines = iterable;
    }

    private Ins[] buildAddressMap() {
        int size = size();
        updateCodeUnits();
        Ins[] insArr = new Ins[getCodeUnits()];
        int codeUnits = 0;
        for (int i = 0; i < size; i++) {
            Ins ins = get(i);
            insArr[codeUnits] = ins;
            codeUnits += ins.getCodeUnits();
        }
        return insArr;
    }

    private String getCurrentMethodForDebug() {
        MethodDef methodDef;
        InstructionList instructionList = (InstructionList) getParentInstance(InstructionList.class);
        if (instructionList == null || (methodDef = instructionList.getCodeItem().getMethodDef()) == null) {
            return null;
        }
        return methodDef.getKey().toString();
    }

    private void linkExtraLines() {
        Iterator<? extends ExtraLine> it = this.extraLines.iterator();
        if (it.hasNext()) {
            Ins[] insArrBuildAddressMap = buildAddressMap();
            int length = insArrBuildAddressMap.length;
            while (it.hasNext()) {
                ExtraLine next = it.next();
                if (!next.isRemoved() && next.getTargetIns() == null) {
                    int targetAddress = next.getTargetAddress();
                    if (!(next instanceof DebugElement) || (targetAddress >= 0 && targetAddress < length && insArrBuildAddressMap[targetAddress] != null)) {
                        Ins orCreateNullInstruction = targetAddress == length ? getOrCreateNullInstruction() : insArrBuildAddressMap[targetAddress];
                        if (orCreateNullInstruction == null) {
                            throw new NullPointerException("Invalid address " + targetAddress + ": " + next);
                        }
                        next.setTargetIns(orCreateNullInstruction);
                    }
                }
            }
        }
    }

    private void linkTargetIns() {
        this.mSecondUpdateRequired = false;
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).linkTargetIns();
        }
    }

    private void unlinkTargets() {
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).unLinkTargetIns();
        }
        removeNullInstruction();
        Iterator<? extends ExtraLine> it = this.extraLines.iterator();
        while (it.hasNext()) {
            it.next().setTargetIns(null);
        }
        this.mLinked = false;
    }

    private void update() {
        this.mSecondUpdateRequired = false;
        updateInsTarget();
        updateExtraLines();
        if (this.mSecondUpdateRequired) {
            updateInsTarget();
            updateExtraLines();
            this.mSecondUpdateRequired = false;
        }
    }

    private void updateExtraLines() {
        Iterator<? extends ExtraLine> it = this.extraLines.iterator();
        while (it.hasNext()) {
            it.next().updateTarget();
        }
    }

    private void updateInsTarget() {
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).updateTargetAddress();
        }
    }

    public int addressOf(Ins ins) {
        int size = size();
        int codeUnits = 0;
        for (int i = 0; i < size; i++) {
            Ins ins2 = get(i);
            if (ins2 == ins) {
                return codeUnits;
            }
            codeUnits += ins2.getCodeUnits();
        }
        if (ins == getNullInstruction()) {
            return codeUnits;
        }
        return -1;
    }

    public Ins getAtAddress(int i) {
        int size = size();
        int codeUnits = 0;
        for (int i2 = 0; i2 < size; i2++) {
            Ins ins = get(i2);
            if (codeUnits == i) {
                return ins;
            }
            codeUnits += ins.getCodeUnits();
        }
        if (i == codeUnits) {
            return getOrCreateNullInstruction();
        }
        return null;
    }

    public int getCodeUnits() {
        return this.codeUnitsReference.get();
    }

    public Iterator<Label> getLabels() {
        return new IterableIterator<Ins, Label>(iterator()) { // from class: com.reandroid.dex.ins.InsBlockList.1
            /* JADX WARN: Multi-variable type inference failed */
            public Iterator<Label> iterator(Ins ins) {
                Iterator<Label> itOf = ins instanceof LabelsSet ? (Iterator) ObjectsUtil.cast(((LabelsSet) ins).getLabels()) : null;
                if (ins instanceof Label) {
                    Label label = (Label) ins;
                    itOf = itOf == null ? SingleIterator.of(label) : CombiningIterator.singleOne(label, itOf);
                }
                return itOf == null ? EmptyIterator.of() : itOf;
            }
        };
    }

    public Ins getNext(Ins ins) {
        int iIndexOf = indexOf(ins);
        if (iIndexOf >= 0) {
            return get(iIndexOf + 1);
        }
        return null;
    }

    public NullInstruction getNullInstruction() {
        NullInstruction nullInstruction = this.mNullInstruction;
        if (nullInstruction != null) {
            nullInstruction.setParent(this);
            nullInstruction.setIndex(getCount());
        }
        return nullInstruction;
    }

    public NullInstruction getOrCreateNullInstruction() {
        NullInstruction nullInstruction = this.mNullInstruction;
        if (nullInstruction == null) {
            nullInstruction = new NullInstruction();
            this.mNullInstruction = nullInstruction;
        }
        nullInstruction.setParent(this);
        nullInstruction.setIndex(getCount());
        return nullInstruction;
    }

    public int getOutSize() {
        return this.outSizReference.get();
    }

    public Ins getPrevious(Ins ins) {
        return get(indexOf(ins) - 1);
    }

    public boolean isLinked() {
        return this.mLinked;
    }

    public boolean isLocked() {
        return this.mLocked || this.mLockedBy != null;
    }

    public Object link(Object obj) {
        if (isLocked()) {
            return null;
        }
        if (this.mLinked) {
            if (obj == null) {
                return null;
            }
            unlink();
        }
        this.mLockedBy = obj;
        this.mLocked = true;
        linkTargetIns();
        linkExtraLines();
        this.mLinked = size() != 0;
        this.mLocked = false;
        return obj;
    }

    public Object linkLocked() {
        return link(new Object());
    }

    public void merge(InsBlockList insBlockList) {
        if (insBlockList == this) {
            return;
        }
        this.mLockedBy = new Object();
        this.mLocked = true;
        clearChildes();
        int size = insBlockList.size();
        ensureCapacity(size);
        for (int i = 0; i < size; i++) {
            Ins ins = insBlockList.get(i);
            Ins insM21newInstance = ins.getOpcode().newInstance();
            add(insM21newInstance);
            insM21newInstance.merge(ins);
        }
        this.mLocked = false;
        this.mLinked = false;
        this.mLockedBy = null;
        updateCodeUnits();
    }

    public void onEditingInternal(InsBlockList insBlockList) {
        if (!isLinked() || insBlockList.isLinked()) {
            return;
        }
        insBlockList.linkLocked();
        insBlockList.mLocked = false;
        insBlockList.mLockedBy = null;
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        this.mLockedBy = new Object();
        int i = this.codeUnitsReference.get();
        int position = blockReader.getPosition() + (i * 2);
        int position2 = blockReader.getPosition();
        ensureCapacity((i + 1) / 2);
        while (blockReader.getPosition() < position) {
            Ins insM21newInstance = Opcode.read(blockReader).newInstance();
            add(insM21newInstance);
            insM21newInstance.readBytes(blockReader);
        }
        trimToSize();
        if (position != blockReader.getPosition()) {
            blockReader.seek(position);
        }
        this.blockAlign.align(blockReader.getPosition() - position2);
        blockReader.offset(this.blockAlign.size());
        this.mLocked = false;
        this.mLinked = false;
        this.mLockedBy = null;
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onRefreshed() {
        super.onRefreshed();
        updateCodeUnits();
        this.mLocked = false;
        this.mSecondUpdateRequired = false;
        unlink();
        this.mLockedBy = null;
    }

    public void removeNullInstruction() {
        NullInstruction nullInstruction = this.mNullInstruction;
        if (nullInstruction != null) {
            this.mNullInstruction = null;
            nullInstruction.setParent((Block) null);
            nullInstruction.setIndex(-1);
        }
    }

    public void unlink(Object obj, boolean z) {
        if (z) {
            this.mSecondUpdateRequired = true;
        }
        if (this.mLocked || obj == null || obj != this.mLockedBy) {
            return;
        }
        this.mLocked = true;
        if (!this.mLinked) {
            if (!z) {
                this.mLocked = false;
                this.mLockedBy = null;
                return;
            } else {
                linkTargetIns();
                linkExtraLines();
            }
        }
        if (z || this.mSecondUpdateRequired) {
            update();
        }
        unlinkTargets();
        this.mLocked = false;
        this.mLockedBy = null;
    }

    public void unlinkLocked(Object obj) {
        unlink(obj, true);
    }

    public void updateCodeUnits() {
        int size = size();
        int codeUnits = 0;
        int iMax = 0;
        for (int i = 0; i < size; i++) {
            Ins ins = get(i);
            codeUnits += ins.getCodeUnits();
            iMax = NumbersUtil.max(ins.getOutSize(), iMax);
        }
        this.codeUnitsReference.set(codeUnits);
        this.outSizReference.set(iMax);
        this.blockAlign.align(codeUnits * 2);
    }

    public void link() {
        if (this.mLinked || isLocked()) {
            return;
        }
        this.mLocked = true;
        linkTargetIns();
        linkExtraLines();
        this.mLinked = true;
        this.mLocked = false;
    }

    public void unlink(Object obj) {
        unlink(obj, false);
    }

    public void unlink() {
        if (this.mLinked && !isLocked()) {
            this.mLocked = true;
            update();
            unlinkTargets();
            updateCodeUnits();
            this.mLocked = false;
            return;
        }
        this.mSecondUpdateRequired = true;
    }
}
