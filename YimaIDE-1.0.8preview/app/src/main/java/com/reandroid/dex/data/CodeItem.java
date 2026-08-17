package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IndirectInteger;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.base.DexPositionAlign;
import com.reandroid.dex.base.IndirectShort;
import com.reandroid.dex.base.PositionAlignedItem;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.RegistersTable;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.data.CodeItem;
import com.reandroid.dex.debug.DebugElement;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.ins.ExtraLine;
import com.reandroid.dex.ins.Label;
import com.reandroid.dex.ins.TryBlock;
import com.reandroid.dex.key.DataKey;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.reference.DataItemIndirectReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliCodeTryItem;
import com.reandroid.dex.smali.model.SmaliMethod;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CodeItem extends DataItem implements RegistersTable, PositionAlignedItem, KeyReference {
    private final DataKey<CodeItem> codeItemKey;
    private final Header header;
    private final InstructionList instructionList;
    private MethodDef methodDef;
    private TryBlock tryBlock;

    public static class Header extends SectionItem implements BlockRefresh {
        private final CodeItem codeItem;
        final DataItemIndirectReference<DebugInfo> debugInfoOffset;
        final IntegerReference instructionCodeUnits;
        final IntegerReference outs;
        final IntegerReference parameterRegisters;
        final IntegerReference registersCount;
        final IntegerReference tryBlockCount;

        public Header(CodeItem codeItem) {
            super(16);
            this.codeItem = codeItem;
            this.registersCount = new IndirectShort(this, 0);
            this.parameterRegisters = new IndirectShort(this, 2);
            this.outs = new IndirectShort(this, 4);
            this.tryBlockCount = new IndirectShort(this, 6);
            this.debugInfoOffset = new DataItemIndirectReference<>(SectionType.DEBUG_INFO, this, 8, UsageMarker.USAGE_DEBUG);
            this.instructionCodeUnits = new IndirectInteger(this, 12);
        }

        @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.common.EditableItem
        public void editInternal(Block block) {
            this.debugInfoOffset.editInternal(block);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Header header = (Header) obj;
                if (this.registersCount.get() == header.registersCount.get() && this.parameterRegisters.get() == header.parameterRegisters.get() && this.outs.get() == header.outs.get() && this.tryBlockCount.get() == header.tryBlockCount.get() && this.instructionCodeUnits.get() == header.instructionCodeUnits.get() && ObjectsUtil.equals(this.debugInfoOffset.getItem(), header.debugInfoOffset.getItem())) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            int i = (((((((((this.registersCount.get() + 31) * 31) + this.parameterRegisters.get()) * 31) + this.outs.get()) * 31) + this.tryBlockCount.get()) * 31) + this.instructionCodeUnits.get()) * 31;
            DebugInfo debugInfo = (DebugInfo) this.debugInfoOffset.getItem();
            return debugInfo != null ? i + debugInfo.hashCode() : i;
        }

        public void merge(Header header) {
            this.registersCount.set(header.registersCount.get());
            this.parameterRegisters.set(header.parameterRegisters.get());
            this.outs.set(header.outs.get());
            this.tryBlockCount.set(header.tryBlockCount.get());
            DebugInfo debugInfo = (DebugInfo) header.debugInfoOffset.getItem();
            if (debugInfo != null) {
                this.debugInfoOffset.setKey(debugInfo.getKey());
                this.debugInfoOffset.addUniqueUser(this.codeItem);
            }
            this.instructionCodeUnits.set(header.instructionCodeUnits.get());
        }

        public void onReadBytes(BlockReader blockReader) throws IOException {
            super.onReadBytes(blockReader);
            this.debugInfoOffset.pullItem();
            this.debugInfoOffset.addUniqueUser(this.codeItem);
            if (this.tryBlockCount.get() != 0) {
                this.codeItem.initTryBlock();
            }
        }

        public void onRemove() {
            this.debugInfoOffset.setItem((DataItem) null);
        }

        public void refresh() {
            this.debugInfoOffset.addUniqueUser(this.codeItem);
            this.debugInfoOffset.refresh();
            if (this.codeItem.getTryBlock() == null) {
                this.tryBlockCount.set(0);
            }
        }

        public String toString() {
            return "registers=" + this.registersCount + ", parameters=" + this.parameterRegisters + ", outs=" + this.outs + ", tries=" + this.tryBlockCount + ", debugInfo=" + this.debugInfoOffset + ", codeUnits=" + this.instructionCodeUnits;
        }
    }

    public CodeItem() {
        super(3);
        Header header = new Header(this);
        this.header = header;
        InstructionList instructionList = new InstructionList(this);
        this.instructionList = instructionList;
        this.tryBlock = null;
        this.codeItemKey = new DataKey<>(this);
        addChildBlock(0, header);
        addChildBlock(1, instructionList);
    }

    public void appendRegistersCount(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.setCurrentRegistersTable(this);
        smaliWriter.newLine();
        if (smaliWriter.isLocalRegistersCount()) {
            SmaliDirective.LOCALS.append(smaliWriter);
            smaliWriter.appendInteger(getLocalRegistersCount());
        } else {
            SmaliDirective.REGISTERS.append(smaliWriter);
            smaliWriter.appendInteger(getRegistersCount());
        }
    }

    public boolean combineTries() {
        TryBlock tryBlock = getTryBlock();
        if (tryBlock != null) {
            return tryBlock.combineTries();
        }
        return false;
    }

    public boolean compactSimilarCatches() {
        TryBlock tryBlock = getTryBlock();
        if (tryBlock != null) {
            return tryBlock.compactSimilarCatches();
        }
        return false;
    }

    @Override // com.reandroid.dex.common.EditableItem
    public void edit() {
        editInternal(this);
    }

    @Override // com.reandroid.dex.common.SectionItem, com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
        this.header.editInternal(block);
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public boolean ensureLocalRegistersCount(int i) {
        if (i == 0 || i <= getLocalRegistersCount()) {
            return true;
        }
        int parameterRegistersCount = getParameterRegistersCount();
        int localRegistersCount = i - getLocalRegistersCount();
        InstructionList instructionList = getInstructionList();
        if (!instructionList.canAddLocalRegisters(localRegistersCount)) {
            return false;
        }
        if (localRegistersCount > 0) {
            instructionList.addLocalRegisters(localRegistersCount);
        }
        setRegistersCount(i + parameterRegistersCount);
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            CodeItem codeItem = (CodeItem) obj;
            if (this.header.equals(codeItem.header) && this.instructionList.equals(codeItem.instructionList) && ObjectsUtil.equals(this.tryBlock, codeItem.tryBlock)) {
                return true;
            }
        }
        return false;
    }

    public boolean flattenTryItems() {
        TryBlock tryBlock = getTryBlock();
        if (tryBlock != null) {
            return tryBlock.flattenCompactCatches();
        }
        return false;
    }

    public void fromSmali(SmaliMethod smaliMethod) {
        setRegistersCount(smaliMethod.getRegistersCount());
        setParameterRegistersCount(smaliMethod.getParameterRegistersCount());
        getInstructionList().fromSmali(smaliMethod.getCodeSet());
        Iterator<SmaliCodeTryItem> tryItems = smaliMethod.getTryItems();
        TryBlock orCreateTryBlock = tryItems.hasNext() ? getOrCreateTryBlock() : null;
        while (tryItems.hasNext()) {
            orCreateTryBlock.fromSmali(tryItems.next());
        }
        if (smaliMethod.hasDebugElements()) {
            getOrCreateDebugInfo().getDebugSequence().fromSmali(smaliMethod.getCodeSet());
        }
        if (orCreateTryBlock != null) {
            orCreateTryBlock.combineTries();
            orCreateTryBlock.compactSimilarCatches();
            orCreateTryBlock.refresh();
        }
    }

    public DebugInfo getDebugInfo() {
        return (DebugInfo) this.header.debugInfoOffset.getItem();
    }

    public Iterator<DebugElement> getDebugLabels() {
        DebugInfo debugInfo = getDebugInfo();
        return debugInfo != null ? debugInfo.getExtraLines() : EmptyIterator.of();
    }

    public Iterable<ExtraLine> getExtraLines() {
        return new Iterable() { // from class: n32
            @Override // java.lang.Iterable
            public final Iterator iterator() {
                CodeItem codeItem = this.b;
                return CombiningIterator.two(codeItem.getTryBlockLabels(), codeItem.getDebugLabels());
            }
        };
    }

    public IntegerReference getInstructionCodeUnitsReference() {
        return this.header.instructionCodeUnits;
    }

    public InstructionList getInstructionList() {
        return this.instructionList;
    }

    public IntegerReference getInstructionOutsReference() {
        return this.header.outs;
    }

    public MethodDef getMethodDef() {
        return this.methodDef;
    }

    public DebugInfo getOrCreateDebugInfo() {
        return (DebugInfo) this.header.debugInfoOffset.getOrCreateUniqueItem(this);
    }

    public TryBlock getOrCreateTryBlock() {
        initTryBlock();
        return this.tryBlock;
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public int getParameterRegistersCount() {
        return this.header.parameterRegisters.get();
    }

    @Override // com.reandroid.dex.base.PositionAlignedItem
    public DexPositionAlign getPositionAlign() {
        TryBlock tryBlock = this.tryBlock;
        if (tryBlock != null) {
            return tryBlock.getPositionAlign();
        }
        InstructionList instructionList = this.instructionList;
        return instructionList != null ? instructionList.getBlockAlign() : new DexPositionAlign();
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public int getRegistersCount() {
        return this.header.registersCount.get();
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<CodeItem> getSectionType() {
        return SectionType.CODE;
    }

    public TryBlock getTryBlock() {
        return this.tryBlock;
    }

    public Iterator<Label> getTryBlockLabels() {
        TryBlock tryBlock = getTryBlock();
        return (tryBlock == null || tryBlock.isNull()) ? EmptyIterator.of() : tryBlock.getLabels();
    }

    public IntegerReference getTryCountReference() {
        return this.header.tryBlockCount;
    }

    public int hashCode() {
        int iHashCode = ((this.header.hashCode() * 31) + this.instructionList.hashCode()) * 31;
        TryBlock tryBlock = this.tryBlock;
        return tryBlock != null ? iHashCode + tryBlock.hashCode() : iHashCode;
    }

    public void initTryBlock() {
        if (this.tryBlock == null) {
            TryBlock tryBlock = new TryBlock(this);
            this.tryBlock = tryBlock;
            addChildBlock(2, tryBlock);
        }
    }

    public void merge(CodeItem codeItem) {
        if (codeItem == this) {
            return;
        }
        this.header.merge(codeItem.header);
        getInstructionList().merge(codeItem.getInstructionList());
        TryBlock tryBlock = codeItem.getTryBlock();
        if (tryBlock != null) {
            getOrCreateTryBlock().merge(tryBlock);
        }
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItemContainer
    public void onRefreshed() {
        super.onRefreshed();
        TryBlock tryBlock = getTryBlock();
        if (tryBlock == null || !tryBlock.isEmpty()) {
            return;
        }
        removeTryBlock();
    }

    public void removeDebugInfo() {
        if (getDebugInfo() == null) {
            return;
        }
        setDebugInfo(null);
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItemContainer, com.reandroid.dex.base.PositionedItem
    public void removeLastAlign() {
        TryBlock tryBlock = this.tryBlock;
        if (tryBlock != null) {
            tryBlock.getPositionAlign().setSize(0);
            return;
        }
        InstructionList instructionList = this.instructionList;
        if (instructionList != null) {
            instructionList.getBlockAlign().setSize(0);
        }
    }

    public void removeTryBlock() {
        TryBlock tryBlock = this.tryBlock;
        if (tryBlock == null) {
            return;
        }
        this.tryBlock = null;
        this.header.tryBlockCount.set(0);
        tryBlock.setParent((Block) null);
    }

    public void replaceKeys(Key key, Key key2) {
        getInstructionList().replaceKeys(key, key2);
    }

    public void setDebugInfo(DebugInfo debugInfo) {
        this.header.debugInfoOffset.setItem(debugInfo);
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        merge((CodeItem) ((DataKey) key).getItem());
    }

    public void setMethodDef(MethodDef methodDef) {
        this.methodDef = methodDef;
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public void setParameterRegistersCount(int i) {
        this.header.parameterRegisters.set(i);
    }

    @Override // com.reandroid.dex.common.RegistersTable
    public void setRegistersCount(int i) {
        this.header.registersCount.set(i);
    }

    public boolean splitTryHandlers() {
        TryBlock tryBlock = getTryBlock();
        if (tryBlock != null) {
            return tryBlock.splitTryHandlers();
        }
        return false;
    }

    public void toSmali(SmaliMethod smaliMethod) {
        SmaliMethod.SmaliRegistersCount smaliRegistersCount = smaliMethod.getSmaliRegistersCount();
        smaliRegistersCount.setLocalsMode(true);
        smaliRegistersCount.setValue(getLocalRegistersCount());
        getInstructionList().toSmali(smaliMethod);
    }

    public String toString() {
        if (isNull()) {
            return "NULL";
        }
        return this.header.toString() + "\n instructionList=" + this.instructionList + "\n tryBlock=" + this.tryBlock + "\n debug=" + getDebugInfo();
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        DebugInfo debugInfo = getDebugInfo();
        Iterator<IdItem> itOf = debugInfo == null ? EmptyIterator.of() : debugInfo.usedIds();
        TryBlock tryBlock = getTryBlock();
        return CombiningIterator.three(getInstructionList().usedIds(), itOf, tryBlock == null ? EmptyIterator.of() : tryBlock.usedIds());
    }

    @Override // com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public DataKey<CodeItem> getKey() {
        return this.codeItemKey;
    }
}
