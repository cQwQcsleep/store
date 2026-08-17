package com.reandroid.dex.ins;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.ProtoId;
import com.reandroid.dex.key.DualKeyReference;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.reference.IdSectionReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.SmaliInstruction;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Ins4rcc extends Size8Ins implements RegistersSet, DualKeyReference {
    private final IdSectionReference<ProtoId> reference2;

    public Ins4rcc(Opcode<?> opcode) {
        super(opcode);
        this.reference2 = new IdSectionReference<ProtoId>(this, UsageMarker.USAGE_INSTRUCTION) { // from class: com.reandroid.dex.ins.Ins4rcc.1
            @Override // com.reandroid.dex.reference.IdSectionReference
            public String buildTrace(ProtoId protoId) {
                return SizeXIns.buildTrace(this, protoId, get());
            }

            @Override // com.reandroid.dex.reference.IdSectionReference
            public int get() {
                return this.getData2();
            }

            @Override // com.reandroid.dex.reference.IdSectionReference, com.reandroid.dex.reference.DexReference
            public SectionType<ProtoId> getSectionType() {
                return this.getSectionType2();
            }

            @Override // com.reandroid.dex.reference.IdSectionReference
            public void set(int i) {
                this.setData2(i);
            }
        };
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void appendOperand(SmaliWriter smaliWriter) throws IOException {
        super.appendOperand(smaliWriter);
        smaliWriter.append(", ");
        getSectionId2().append(smaliWriter);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    @Override // com.reandroid.dex.ins.SizeXIns, com.reandroid.dex.ins.Ins
    public void fromSmali(SmaliInstruction smaliInstruction) throws DexException {
        super.fromSmali(smaliInstruction);
        setKey2(smaliInstruction.getKey2());
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public int getData() {
        return getShortUnsigned(2);
    }

    public int getData2() {
        return getShortUnsigned(6);
    }

    @Override // com.reandroid.dex.key.DualKeyReference
    public Key getKey2() {
        return this.reference2.getKey();
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegister(int i) {
        return getShortUnsigned(4) + i;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegisterLimit(int i) {
        return 65535;
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public int getRegistersCount() {
        return getByteUnsigned(1);
    }

    public ProtoId getSectionId2() {
        return (ProtoId) this.reference2.getItem();
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public SectionType<ProtoId> getSectionType2() {
        return (SectionType) ObjectsUtil.cast(super.getSectionType2());
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void onRefreshed() {
        super.onRefreshed();
        this.reference2.refresh();
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void pullSectionItem() {
        super.pullSectionItem();
        this.reference2.pullItem();
    }

    @Override // com.reandroid.dex.ins.SizeXIns, com.reandroid.dex.ins.Ins
    public void replaceKeys(Key key, Key key2) {
        Key keyReplaceKey;
        super.replaceKeys(key, key2);
        Key key3 = getKey2();
        if (key3 == null || key3 == (keyReplaceKey = key3.replaceKey(key, key2))) {
            return;
        }
        setKey2(keyReplaceKey);
    }

    @Override // com.reandroid.dex.ins.SizeXIns
    public void setData(int i) {
        setShort(2, i);
    }

    public void setData2(int i) {
        setShort(6, i);
    }

    @Override // com.reandroid.dex.key.DualKeyReference
    public void setKey2(Key key) {
        this.reference2.setKey(key);
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegister(int i, int i2) {
        if (i != 0) {
            setShort(1, i2 + 1);
        } else {
            setShort(4, i2);
        }
    }

    @Override // com.reandroid.dex.ins.RegistersSet
    public void setRegistersCount(int i) {
        setByte(1, i);
    }

    public void setSectionId2(IdItem idItem) {
        this.reference2.setItem((ProtoId) idItem);
    }

    @Override // com.reandroid.dex.ins.SizeXIns, com.reandroid.dex.ins.Ins
    public Iterator<IdItem> usedIds() {
        return CombiningIterator.two(super.usedIds(), SingleIterator.of(getSectionId2()));
    }
}
