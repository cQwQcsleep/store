package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.data.FixedDexContainerWithTool;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliCodeExceptionHandler;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayIterator;
import com.reandroid.utils.collection.CollectionUtil;
import com.reandroid.utils.collection.EmptyIterator;
import com.sun.org.apache.xalan.internal.templates.Constants;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class ExceptionHandler extends FixedDexContainerWithTool implements SmaliRegion, Iterable<Label>, LabelsSet {
    private final Ule128Item catchAddress;
    private final ExceptionLabel catchLabel;
    private final ExceptionLabel endLabel;
    private final ExceptionLabel handlerLabel;
    private Label[] mLabels;
    private final ExceptionLabel startLabel;

    public static abstract class AbstractExceptionLabel implements ExceptionLabel {
        private final ExceptionHandler handler;
        private Ins targetIns;

        public AbstractExceptionLabel(ExceptionHandler exceptionHandler) {
            this.handler = exceptionHandler;
        }

        @Override // com.reandroid.dex.ins.ExceptionLabel
        public ExceptionHandler getHandler() {
            return this.handler;
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public Ins getTargetIns() {
            Ins ins = this.targetIns;
            if (ins == null || !ins.isRemoved()) {
                return ins;
            }
            this.targetIns = null;
            return null;
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public void setTargetIns(Ins ins) {
            if (ins != this.targetIns) {
                this.targetIns = ins;
                if (ins != null) {
                    ins.addExtraLine(this);
                }
            }
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public void updateTarget() {
        }
    }

    public static class CatchLabel extends AbstractExceptionLabel {
        public CatchLabel(ExceptionHandler exceptionHandler) {
            super(exceptionHandler);
        }

        @Override // com.reandroid.dex.ins.ExceptionLabel, com.reandroid.dex.ins.Label
        public int compareLabelName(Label label) {
            if (label == this) {
                return 0;
            }
            if (!(label instanceof CatchLabel)) {
                return getLabelName().compareTo(label.getLabelName());
            }
            CatchLabel catchLabel = (CatchLabel) label;
            int iCompare = CompareUtil.compare(getHandler().isCatchAll(), catchLabel.getHandler().isCatchAll());
            return iCompare == 0 ? CompareUtil.compare(getTargetAddress(), catchLabel.getTargetAddress()) : iCompare;
        }

        @Override // com.reandroid.dex.ins.Label
        public int getAddress() {
            return getHandler().getStartLabel().getAddress();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExceptionLabel
        public /* bridge */ /* synthetic */ ExceptionHandler getHandler() {
            return super.getHandler();
        }

        @Override // com.reandroid.dex.ins.Label
        public String getLabelName() {
            return HexUtil.toHex(":" + getHandler().getSmaliDirective().getName() + "_", getTargetAddress(), 1);
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public int getSortOrder() {
            return 4;
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public int getTargetAddress() {
            return getHandler().getCatchAddress();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ Ins getTargetIns() {
            return super.getTargetIns();
        }

        @Override // com.reandroid.dex.ins.Label, com.reandroid.dex.ins.ExtraLine
        public boolean isEqualExtraLine(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                CatchLabel catchLabel = (CatchLabel) obj;
                if (getHandler() == catchLabel.getHandler()) {
                    return true;
                }
                if (getTargetAddress() == catchLabel.getTargetAddress() && getHandler().getSmaliDirective().equals(catchLabel.getHandler().getSmaliDirective())) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public void setTargetAddress(int i) {
            getHandler().setCatchAddress(i);
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ void setTargetIns(Ins ins) {
            super.setTargetIns(ins);
        }

        public String toString() {
            return getLabelName();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ void updateTarget() {
            super.updateTarget();
        }
    }

    public static class HandlerLabel extends AbstractExceptionLabel {
        public HandlerLabel(ExceptionHandler exceptionHandler) {
            super(exceptionHandler);
        }

        @Override // com.reandroid.dex.ins.Label, com.reandroid.dex.ins.ExtraLine
        public void appendExtra(SmaliWriter smaliWriter) throws IOException {
            ExceptionHandler handler = getHandler();
            handler.getSmaliDirective().append(smaliWriter);
            TypeId typeId = handler.getTypeId();
            if (typeId != null) {
                typeId.append(smaliWriter);
                smaliWriter.append(' ');
            }
            smaliWriter.append("{");
            smaliWriter.appendLabelName(handler.getStartLabel().getLabelName());
            smaliWriter.append(" .. ");
            smaliWriter.appendLabelName(handler.getEndLabel().getLabelName());
            smaliWriter.append("} ");
            smaliWriter.appendLabelName(handler.getCatchLabel().getLabelName());
        }

        @Override // com.reandroid.dex.ins.ExceptionLabel, com.reandroid.dex.ins.Label
        public int compareLabelName(Label label) {
            return getHandler().compareHandler(((HandlerLabel) label).getHandler());
        }

        @Override // com.reandroid.dex.ins.Label
        public int getAddress() {
            return getHandler().getAddress();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExceptionLabel
        public /* bridge */ /* synthetic */ ExceptionHandler getHandler() {
            return super.getHandler();
        }

        @Override // com.reandroid.dex.ins.Label
        public String getLabelName() {
            ExceptionHandler handler = getHandler();
            StringBuilder sb = new StringBuilder(Constants.ATTRVAL_THIS);
            sb.append(handler.getSmaliDirective().getName());
            sb.append(' ');
            TypeId typeId = handler.getTypeId();
            if (typeId != null) {
                sb.append(typeId.getName());
                sb.append(' ');
            }
            sb.append("{");
            sb.append(handler.getStartLabel().getLabelName());
            sb.append(" .. ");
            sb.append(handler.getEndLabel().getLabelName());
            sb.append("} ");
            sb.append(handler.getCatchLabel().getLabelName());
            return sb.toString();
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public int getSortOrder() {
            return 3;
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public int getTargetAddress() {
            return getHandler().getAddress();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ Ins getTargetIns() {
            return super.getTargetIns();
        }

        @Override // com.reandroid.dex.ins.Label, com.reandroid.dex.ins.ExtraLine
        public boolean isEqualExtraLine(Object obj) {
            if (obj == this) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && getHandler() == ((HandlerLabel) obj).getHandler();
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public void setTargetAddress(int i) {
            getHandler().setAddress(i);
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ void setTargetIns(Ins ins) {
            super.setTargetIns(ins);
        }

        public String toString() {
            return getLabelName();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public void updateTarget() {
            getHandler().refreshAddresses();
        }
    }

    public static class TryEndLabel extends AbstractExceptionLabel {
        public TryEndLabel(ExceptionHandler exceptionHandler) {
            super(exceptionHandler);
        }

        @Override // com.reandroid.dex.ins.Label
        public int getAddress() {
            return getHandler().getAddress();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExceptionLabel
        public /* bridge */ /* synthetic */ ExceptionHandler getHandler() {
            return super.getHandler();
        }

        @Override // com.reandroid.dex.ins.Label
        public String getLabelName() {
            return HexUtil.toHex(":try_end_", getHandler().getStartLabel().getTargetAddress(), 1);
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public int getSortOrder() {
            return 2;
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public int getTargetAddress() {
            return getHandler().getAddress();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ Ins getTargetIns() {
            return super.getTargetIns();
        }

        @Override // com.reandroid.dex.ins.Label, com.reandroid.dex.ins.ExtraLine
        public boolean isEqualExtraLine(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                TryEndLabel tryEndLabel = (TryEndLabel) obj;
                if (getHandler() == tryEndLabel.getHandler() || getTargetAddress() == tryEndLabel.getTargetAddress()) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public void setTargetAddress(int i) {
            getHandler().setAddress(i);
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ void setTargetIns(Ins ins) {
            super.setTargetIns(ins);
        }

        public String toString() {
            return getLabelName();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ void updateTarget() {
            super.updateTarget();
        }
    }

    public static class TryStartLabel extends AbstractExceptionLabel {
        public TryStartLabel(ExceptionHandler exceptionHandler) {
            super(exceptionHandler);
        }

        @Override // com.reandroid.dex.ins.Label
        public int getAddress() {
            return getHandler().getAddress();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExceptionLabel
        public /* bridge */ /* synthetic */ ExceptionHandler getHandler() {
            return super.getHandler();
        }

        @Override // com.reandroid.dex.ins.Label
        public String getLabelName() {
            return HexUtil.toHex(":try_start_", getTargetAddress(), 1);
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public int getSortOrder() {
            return 6;
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public int getTargetAddress() {
            return getHandler().getStartAddress();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ Ins getTargetIns() {
            return super.getTargetIns();
        }

        @Override // com.reandroid.dex.ins.Label, com.reandroid.dex.ins.ExtraLine
        public boolean isEqualExtraLine(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                TryStartLabel tryStartLabel = (TryStartLabel) obj;
                if (getHandler() == tryStartLabel.getHandler() || getTargetAddress() == tryStartLabel.getTargetAddress()) {
                    return true;
                }
            }
            return false;
        }

        @Override // com.reandroid.dex.ins.ExtraLine
        public void setTargetAddress(int i) {
            getHandler().setStartAddress(i);
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ void setTargetIns(Ins ins) {
            super.setTargetIns(ins);
        }

        public String toString() {
            return getLabelName();
        }

        @Override // com.reandroid.dex.ins.ExceptionHandler.AbstractExceptionLabel, com.reandroid.dex.ins.ExtraLine
        public /* bridge */ /* synthetic */ void updateTarget() {
            super.updateTarget();
        }
    }

    private ExceptionHandler(int i, Ule128Item ule128Item, int i2) {
        super(i);
        this.catchAddress = ule128Item;
        if (ule128Item != null) {
            addChild(i2, ule128Item);
        }
        TryStartLabel tryStartLabel = new TryStartLabel(this);
        this.startLabel = tryStartLabel;
        TryEndLabel tryEndLabel = new TryEndLabel(this);
        this.endLabel = tryEndLabel;
        HandlerLabel handlerLabel = new HandlerLabel(this);
        this.handlerLabel = handlerLabel;
        CatchLabel catchLabel = new CatchLabel(this);
        this.catchLabel = catchLabel;
        this.mLabels = new Label[]{tryStartLabel, tryEndLabel, handlerLabel, catchLabel};
    }

    public static boolean areSimilar(ExceptionHandler exceptionHandler, ExceptionHandler exceptionHandler2) {
        if (exceptionHandler == null) {
            return exceptionHandler2 == null;
        }
        if (exceptionHandler2 != null && exceptionHandler.getCatchAddress() == exceptionHandler2.getCatchAddress()) {
            return ObjectsUtil.equals(exceptionHandler.getKey(), exceptionHandler2.getKey());
        }
        return false;
    }

    private InstructionList getInstructionList() {
        TryItem tryItem = getTryItem();
        if (tryItem != null) {
            return tryItem.getInstructionList();
        }
        return null;
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
    }

    public int compareHandler(ExceptionHandler exceptionHandler) {
        if (exceptionHandler == this) {
            return 0;
        }
        int iCompare = CompareUtil.compare(getAddress(), exceptionHandler.getAddress());
        if (iCompare != 0) {
            return iCompare;
        }
        int iCompare2 = CompareUtil.compare(getTryItem().getIndex(), exceptionHandler.getTryItem().getIndex());
        if (iCompare2 != 0) {
            return iCompare2;
        }
        int iCompare3 = CompareUtil.compare(isCatchAll(), exceptionHandler.isCatchAll());
        return iCompare3 != 0 ? iCompare3 : CompareUtil.compare(getIndex(), exceptionHandler.getIndex());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ExceptionHandler exceptionHandler = (ExceptionHandler) obj;
            if (getStartAddress() == exceptionHandler.getStartAddress() && getAddress() == exceptionHandler.getAddress() && getCatchAddress() == exceptionHandler.getCatchAddress() && ObjectsUtil.equals(getKey(), exceptionHandler.getKey())) {
                return true;
            }
        }
        return false;
    }

    public void fromSmali(SmaliCodeExceptionHandler smaliCodeExceptionHandler) {
        getHandlerLabel().setTargetAddress(smaliCodeExceptionHandler.getAddress());
        getStartLabel().setTargetAddress(smaliCodeExceptionHandler.getStart().getAddress());
        getEndLabel().setTargetAddress(smaliCodeExceptionHandler.getEnd().getAddress());
        getCatchLabel().setTargetAddress(smaliCodeExceptionHandler.getCatchLabel().getAddress());
    }

    public int getAddress() {
        return getStartAddress() + getCodeUnit();
    }

    public int getCatchAddress() {
        return getCatchAddressUle128().get();
    }

    public Ule128Item getCatchAddressUle128() {
        return this.catchAddress;
    }

    public ExceptionLabel getCatchLabel() {
        return this.catchLabel;
    }

    public int getCodeUnit() {
        TryItem tryItem = getTryItem();
        if (tryItem != null) {
            return tryItem.getCatchCodeUnit();
        }
        return 0;
    }

    public ExceptionLabel getEndLabel() {
        return this.endLabel;
    }

    public ExceptionLabel getHandlerLabel() {
        return this.handlerLabel;
    }

    public int getInstructionCount() {
        return CollectionUtil.count(getTryInstructions());
    }

    public TypeKey getKey() {
        return null;
    }

    @Override // com.reandroid.dex.ins.LabelsSet
    public Iterator<Label> getLabels() {
        return iterator();
    }

    public abstract SmaliDirective getSmaliDirective();

    public int getStartAddress() {
        TryItem tryItem = getTryItem();
        if (tryItem != null) {
            return tryItem.getStartAddress();
        }
        return 0;
    }

    public ExceptionLabel getStartLabel() {
        return this.startLabel;
    }

    public Iterator<Ins> getTryInstructions() {
        InstructionList instructionList = getInstructionList();
        return instructionList == null ? EmptyIterator.of() : instructionList.iteratorByAddress(getStartLabel().getTargetAddress(), getCodeUnit());
    }

    public TryItem getTryItem() {
        return (TryItem) getParentInstance(TryItem.class);
    }

    public abstract TypeId getTypeId();

    public int hashCode() {
        int startAddress = (((((getStartAddress() + 31) * 31) + getAddress()) * 31) + getCatchAddress()) * 31;
        TypeKey key = getKey();
        return key != null ? startAddress + key.hashCode() : startAddress;
    }

    public boolean isAddressBounded(int i) {
        if (i == -1) {
            return true;
        }
        return i >= getStartAddress() && i <= getAddress();
    }

    public abstract boolean isCatchAll();

    public boolean isRemoved() {
        return getParent() == null;
    }

    @Override // java.lang.Iterable
    public Iterator<Label> iterator() {
        return ArrayIterator.of(this.mLabels);
    }

    public void merge(ExceptionHandler exceptionHandler) {
        this.catchAddress.set(exceptionHandler.getCatchAddress());
    }

    public void onRemove() {
        this.mLabels = null;
        setParent((Block) null);
    }

    public void refreshAddresses() {
        Ins targetIns = getHandlerLabel().getTargetIns();
        Ins targetIns2 = getStartLabel().getTargetIns();
        Ins targetIns3 = getEndLabel().getTargetIns();
        Ins targetIns4 = getCatchLabel().getTargetIns();
        if (targetIns == null || targetIns2 == null || targetIns3 == null || targetIns4 == null) {
            return;
        }
        int address = targetIns.getAddress();
        int address2 = targetIns2.getAddress();
        int address3 = targetIns4.getAddress();
        setStartAddress(targetIns2.getAddress());
        setCatchAddress(address3);
        setCodeUnit(address - address2);
    }

    public void removeSelf() {
        TryItem tryItem = getTryItem();
        if (tryItem != null) {
            tryItem.remove(this);
        }
    }

    public void setAddress(int i) {
        setCodeUnit(i - getStartAddress());
    }

    public void setCatchAddress(int i) {
        getCatchAddressUle128().set(i);
    }

    public void setCodeUnit(int i) {
        TryItem tryItem = getTryItem();
        if (tryItem != null) {
            tryItem.getHandlerOffset().setCatchCodeUnit(i);
        }
    }

    public void setKey(TypeKey typeKey) {
    }

    public void setStartAddress(int i) {
        TryItem tryItem = getTryItem();
        if (tryItem != null) {
            tryItem.setStartAddress(i);
        }
    }

    public String toString() {
        return getHandlerLabel().toString();
    }

    public abstract boolean traps(TypeKey typeKey);

    public ExceptionHandler(int i) {
        this(i + 1, new Ule128Item(), i);
    }

    public ExceptionHandler() {
        this(0, null, 0);
    }
}
