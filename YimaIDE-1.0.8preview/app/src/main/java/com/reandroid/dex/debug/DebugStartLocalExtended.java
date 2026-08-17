package com.reandroid.dex.debug;

import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.reference.Base1Ule128IdItemReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.smali.model.SmaliDebugLocal;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugStartLocalExtended extends DebugStartLocal {
    private final Base1Ule128IdItemReference<StringId> mSignature;

    public DebugStartLocalExtended() {
        super(1, DebugElementType.START_LOCAL_EXTENDED);
        Base1Ule128IdItemReference<StringId> base1Ule128IdItemReference = new Base1Ule128IdItemReference<>(SectionType.STRING_ID);
        this.mSignature = base1Ule128IdItemReference;
        addChild(4, base1Ule128IdItemReference);
    }

    @Override // com.reandroid.dex.debug.DebugStartLocal, com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement, com.reandroid.dex.ins.ExtraLine
    public void appendExtra(SmaliWriter smaliWriter) throws IOException {
        if (isValid()) {
            super.appendExtra(smaliWriter);
            smaliWriter.append(", ");
            this.mSignature.append(smaliWriter);
        }
    }

    @Override // com.reandroid.dex.debug.DebugStartLocal, com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            DebugStartLocalExtended debugStartLocalExtended = (DebugStartLocalExtended) obj;
            if (getFlag() == debugStartLocalExtended.getFlag() && getRegisterNumber() == debugStartLocalExtended.getRegisterNumber() && ObjectsUtil.equals(getName(), debugStartLocalExtended.getName()) && ObjectsUtil.equals(getType(), debugStartLocalExtended.getType()) && ObjectsUtil.equals(getSignature(), debugStartLocalExtended.getSignature())) {
                return true;
            }
        }
        return false;
    }

    @Override // com.reandroid.dex.debug.DebugStartLocal, com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public void fromSmali(Smali smali) {
        super.fromSmali(smali);
        setSignature(((SmaliDebugLocal) smali).getSignature());
    }

    @Override // com.reandroid.dex.debug.DebugStartLocal, com.reandroid.dex.debug.DebugElement
    public DebugElementType<DebugStartLocalExtended> getElementType() {
        return DebugElementType.START_LOCAL_EXTENDED;
    }

    public String getSignature() {
        StringId stringId = (StringId) this.mSignature.getItem();
        if (stringId != null) {
            return stringId.getString();
        }
        return null;
    }

    public StringKey getSignatureKey() {
        return (StringKey) this.mSignature.getKey();
    }

    @Override // com.reandroid.dex.debug.DebugStartLocal, com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public int hashCode() {
        return ((((getFlag() + 31) * 31) + getRegisterNumber()) * 31) + ObjectsUtil.hash(getName(), getType(), getSignature());
    }

    @Override // com.reandroid.dex.debug.DebugStartLocal, com.reandroid.dex.debug.DebugElement
    public boolean isValid() {
        return (isRemoved() || this.mSignature.getItem() == null) ? false : true;
    }

    @Override // com.reandroid.dex.debug.DebugStartLocal, com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public void merge(DebugElement debugElement) {
        super.merge(debugElement);
        this.mSignature.setKey(((DebugStartLocalExtended) debugElement).mSignature.getKey());
    }

    public void setSignature(String str) {
        this.mSignature.setKey(StringKey.create(str));
    }

    @Override // com.reandroid.dex.debug.DebugStartLocal, com.reandroid.dex.debug.DebugRegisterNumber, com.reandroid.dex.debug.DebugElement
    public String toString() {
        return super.toString() + ", " + this.mSignature;
    }

    @Override // com.reandroid.dex.debug.DebugStartLocal, com.reandroid.dex.debug.DebugElement
    public Iterator<IdItem> usedIds() {
        return CombiningIterator.two(super.usedIds(), SingleIterator.of((StringId) this.mSignature.getItem()));
    }

    public void setSignature(StringKey stringKey) {
        this.mSignature.setKey(stringKey);
    }
}
