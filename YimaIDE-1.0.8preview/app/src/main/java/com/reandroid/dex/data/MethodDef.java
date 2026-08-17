package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.common.ArraySupplier;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.common.AccessFlag;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.MethodId;
import com.reandroid.dex.id.ProtoId;
import com.reandroid.dex.ins.Ins;
import com.reandroid.dex.ins.TryBlock;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.MethodKey;
import com.reandroid.dex.key.ProtoKey;
import com.reandroid.dex.program.MethodProgram;
import com.reandroid.dex.reference.DataItemUle128Reference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.smali.model.SmaliMethod;
import com.reandroid.dex.smali.model.SmaliMethodParameter;
import com.reandroid.utils.collection.ArraySupplierIterator;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.EmptyIterator;
import java.io.IOException;
import java.lang.annotation.ElementType;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class MethodDef extends Def<MethodId> implements MethodProgram {
    private final DataItemUle128Reference<CodeItem> codeOffset;

    public MethodDef() {
        super(1, SectionType.METHOD_ID);
        DataItemUle128Reference<CodeItem> dataItemUle128Reference = new DataItemUle128Reference<>(SectionType.CODE, UsageMarker.USAGE_DEFINITION);
        this.codeOffset = dataItemUle128Reference;
        addChild(2, dataItemUle128Reference);
    }

    private void linkCodeItem() {
        CodeItem codeItem = (CodeItem) this.codeOffset.getItem();
        if (codeItem != null) {
            codeItem.addUniqueUser(this);
            codeItem.setMethodDef(this);
        }
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        MethodKey key = getKey();
        if (key == null) {
            a16.a("Null MethodKey");
            return;
        }
        smaliWriter.onWriteMethod(key);
        smaliWriter.newLine();
        getSmaliDirective().append(smaliWriter);
        smaliWriter.appendModifiers(getModifiers());
        key.appendDefinition(smaliWriter);
        smaliWriter.indentPlus();
        CodeItem codeItem = getCodeItem();
        if (codeItem != null) {
            codeItem.appendRegistersCount(smaliWriter);
        }
        smaliWriter.appendAll(getParameters(), false);
        getAnnotation().append(smaliWriter);
        if (codeItem != null) {
            codeItem.getInstructionList().append(smaliWriter);
        }
        smaliWriter.indentMinus();
        getSmaliDirective().appendEnd(smaliWriter);
        smaliWriter.setCurrentRegistersTable(null);
    }

    public void clearCode() {
        this.codeOffset.setItem((DataItem) null);
    }

    public void clearDebug() {
        CodeItem codeItem = getCodeItem();
        if (codeItem != null) {
            codeItem.removeDebugInfo();
        }
    }

    @Override // com.reandroid.dex.common.EditableItem
    public void edit() {
        CodeItem codeItem = (CodeItem) this.codeOffset.getItem();
        CodeItem codeItem2 = (CodeItem) this.codeOffset.getUniqueItem(this);
        if (codeItem2 != null) {
            codeItem2.setMethodDef(this);
            if (codeItem != codeItem2) {
                codeItem2.edit();
                codeItem.getInstructionList().onEditing(codeItem2.getInstructionList());
            }
            codeItem2.flattenTryItems();
        }
    }

    @Override // com.reandroid.dex.data.Def, com.reandroid.dex.common.EditableItem
    public void editInternal(Block block) {
        edit();
    }

    @Override // com.reandroid.dex.data.Def
    public void fromSmali(Smali smali) {
        SmaliMethod smaliMethod = (SmaliMethod) smali;
        setKey(smaliMethod.getKey());
        setAccessFlagsValue(smaliMethod.getAccessFlagsValue());
        addHiddenApiFlags(smaliMethod.getHiddenApiFlags());
        if (smaliMethod.hasInstructions()) {
            getOrCreateCodeItem().fromSmali(smaliMethod);
        }
        if (smaliMethod.hasAnnotation()) {
            setAnnotation(smaliMethod.getAnnotationSetKey());
        }
        Iterator<SmaliMethodParameter> parameters = smaliMethod.getParameters();
        while (parameters.hasNext()) {
            SmaliMethodParameter next = parameters.next();
            int definitionIndex = next.getDefinitionIndex();
            if (definitionIndex < 0) {
                MethodKey key = smaliMethod.getKey();
                StringBuilder sb = new StringBuilder("Parameter out of range, class = ");
                sb.append(key.getDeclaring());
                String name = key.getName();
                ProtoKey proto = key.getProto();
                sb.append(", method = ");
                sb.append(name);
                sb.append(proto);
                sb.append("\n");
                sb.append(next);
                throw new RuntimeException(sb.toString());
            }
            getParameter(definitionIndex).fromSmali(next);
        }
        linkCodeItem();
    }

    public CodeItem getCodeItem() {
        CodeItem codeItem = (CodeItem) this.codeOffset.getItem();
        if (codeItem != null) {
            codeItem.setMethodDef(this);
        }
        return codeItem;
    }

    public DebugInfo getDebugInfo() {
        CodeItem codeItem = getCodeItem();
        if (codeItem != null) {
            return codeItem.getDebugInfo();
        }
        return null;
    }

    @Override // com.reandroid.dex.program.ProgramElement, com.reandroid.dex.program.MethodProgram
    public ElementType getElementType() {
        return ElementType.METHOD;
    }

    public Ins getInstruction(int i) {
        InstructionList instructionList = getInstructionList();
        if (instructionList != null) {
            return instructionList.get(i);
        }
        return null;
    }

    public Ins getInstructionAt(int i) {
        InstructionList instructionList = getInstructionList();
        if (instructionList != null) {
            return instructionList.getAtAddress(i);
        }
        return null;
    }

    public InstructionList getInstructionList() {
        CodeItem codeItem = getCodeItem();
        if (codeItem != null) {
            return codeItem.getInstructionList();
        }
        return null;
    }

    public Iterator<Ins> getInstructions() {
        InstructionList instructionList = getInstructionList();
        return instructionList != null ? instructionList.iterator() : EmptyIterator.of();
    }

    public int getInstructionsCount() {
        InstructionList instructionList = getInstructionList();
        if (instructionList != null) {
            return instructionList.getCount();
        }
        return 0;
    }

    @Override // com.reandroid.dex.data.Def, com.reandroid.dex.program.ProgramElement, com.reandroid.dex.data.DefIndex
    public MethodKey getKey() {
        MethodId id = getId();
        if (id != null) {
            return id.getKey();
        }
        return null;
    }

    public String getName() {
        MethodId id = getId();
        if (id != null) {
            return id.getName();
        }
        return null;
    }

    public CodeItem getOrCreateCodeItem() {
        CodeItem codeItem = (CodeItem) this.codeOffset.getItem();
        CodeItem codeItem2 = (CodeItem) this.codeOffset.getOrCreateUniqueItem(this);
        if (codeItem == null) {
            codeItem2.setMethodDef(this);
            int parameterRegistersCount = getParameterRegistersCount();
            codeItem2.setRegistersCount(parameterRegistersCount);
            codeItem2.setParameterRegistersCount(parameterRegistersCount);
        }
        return codeItem2;
    }

    public DebugInfo getOrCreateDebugInfo() {
        return getOrCreateCodeItem().getOrCreateDebugInfo();
    }

    public InstructionList getOrCreateInstructionList() {
        return getOrCreateCodeItem().getInstructionList();
    }

    public TryBlock getOrCreateTryBlock() {
        return getOrCreateCodeItem().getOrCreateTryBlock();
    }

    public MethodParameter getParameter(int i) {
        if (i < 0 || i >= getParametersCount()) {
            return null;
        }
        return new MethodParameter(this, i);
    }

    public int getParameterRegistersCount() {
        MethodId id = getId();
        if (id == null) {
            return 0;
        }
        int parameterRegistersCount = id.getParameterRegistersCount();
        return !isStatic() ? parameterRegistersCount + 1 : parameterRegistersCount;
    }

    public Iterator<MethodParameter> getParameters() {
        return ArraySupplierIterator.of(new ArraySupplier<MethodParameter>() { // from class: com.reandroid.dex.data.MethodDef.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.reandroid.common.ArraySupplier
            public MethodParameter get(int i) {
                return MethodDef.this.getParameter(i);
            }

            @Override // com.reandroid.common.CountSupplier
            public int getCount() {
                return MethodDef.this.getParametersCount();
            }
        });
    }

    public int getParametersCount() {
        MethodId id = getId();
        if (id != null) {
            return id.getParametersCount();
        }
        return 0;
    }

    public ProtoId getProtoId() {
        MethodId id = getId();
        if (id != null) {
            return id.getProtoId();
        }
        return null;
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.METHOD;
    }

    public TryBlock getTryBlock() {
        CodeItem codeItem = getCodeItem();
        if (codeItem != null) {
            return codeItem.getTryBlock();
        }
        return null;
    }

    public boolean hasParameter(int i) {
        return i >= 0 && i < getParametersCount();
    }

    @Override // com.reandroid.dex.data.Def
    public void merge(Def<?> def) {
        super.merge(def);
        CodeItem codeItem = ((MethodDef) def).getCodeItem();
        if (codeItem != null) {
            this.codeOffset.setKey(codeItem.getKey());
        }
    }

    @Override // com.reandroid.dex.data.Def, com.reandroid.dex.base.FixedDexContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        super.onReadBytes(blockReader);
        linkCodeItem();
    }

    @Override // com.reandroid.dex.data.Def
    public void onRemove() {
        CodeItem codeItem = (CodeItem) this.codeOffset.getItem();
        if (codeItem != null) {
            codeItem.setMethodDef(null);
            this.codeOffset.setItem((DataItem) null);
        }
        super.onRemove();
    }

    public void removeParameter(int i) {
        MethodParameter parameter = getParameter(i);
        if (parameter == null) {
            return;
        }
        parameter.onRemoved();
        MethodKey key = getKey();
        if (key == null) {
            return;
        }
        setItem(key.removeParameter(i));
    }

    @Override // com.reandroid.dex.data.Def
    public void replaceKeys(Key key, Key key2) {
        super.replaceKeys(key, key2);
        CodeItem codeItem = getCodeItem();
        if (codeItem != null) {
            codeItem.replaceKeys(key, key2);
        }
    }

    public void setDebugInfo(DebugInfo debugInfo) {
        CodeItem codeItem = getCodeItem();
        if (codeItem != null) {
            codeItem.setDebugInfo(debugInfo);
        }
    }

    public void setName(String str) {
        if (Objects.equals(getName(), str)) {
            return;
        }
        getId().setName(str);
    }

    @Override // com.reandroid.dex.data.Def
    public SmaliMethod toSmali() {
        SmaliMethod smaliMethod = new SmaliMethod();
        smaliMethod.setKey(getKey());
        smaliMethod.setAccessFlags(AccessFlag.valuesOfField(getAccessFlagsValue()));
        smaliMethod.setHiddenApiFlags(getHiddenApiFlags());
        smaliMethod.setAnnotation(getAnnotationKeys());
        CodeItem codeItem = getCodeItem();
        if (codeItem != null) {
            codeItem.toSmali(smaliMethod);
        }
        return smaliMethod;
    }

    public String toString() {
        if (isReading()) {
            return getSmaliDirective() + " " + Modifier.toString(getModifiers()) + getKey();
        }
        MethodId id = getId();
        if (id != null) {
            return getSmaliDirective() + " " + Modifier.toString(getModifiers()) + id.toString();
        }
        return getSmaliDirective() + " " + Modifier.toString(getAccessFlags()) + " " + getRelativeIdValue();
    }

    @Override // com.reandroid.dex.data.Def, com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        CodeItem codeItem = getCodeItem();
        return CombiningIterator.singleOne(getId(), codeItem == null ? EmptyIterator.of() : codeItem.usedIds());
    }
}
