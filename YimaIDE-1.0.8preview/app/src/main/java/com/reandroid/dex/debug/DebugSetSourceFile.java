package com.reandroid.dex.debug;

import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.reference.Base1Ule128IdItemReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.dex.smali.model.Smali;
import com.reandroid.dex.smali.model.SmaliDebugSetSourceFile;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugSetSourceFile extends DebugElement {
    private final Base1Ule128IdItemReference<StringId> mName;

    public DebugSetSourceFile() {
        super(1, DebugElementType.SET_SOURCE_FILE);
        Base1Ule128IdItemReference<StringId> base1Ule128IdItemReference = new Base1Ule128IdItemReference<>(SectionType.STRING_ID);
        this.mName = base1Ule128IdItemReference;
        addChild(1, base1Ule128IdItemReference);
    }

    @Override // com.reandroid.dex.debug.DebugElement, com.reandroid.dex.ins.ExtraLine
    public void appendExtra(SmaliWriter smaliWriter) throws IOException {
        super.appendExtra(smaliWriter);
        this.mName.append(smaliWriter);
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public void fromSmali(Smali smali) {
        super.fromSmali(smali);
        setName(((SmaliDebugSetSourceFile) smali).getName());
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public DebugElementType<DebugSetSourceFile> getElementType() {
        return DebugElementType.SET_SOURCE_FILE;
    }

    public String getName() {
        StringId stringId = (StringId) this.mName.getItem();
        if (stringId != null) {
            return stringId.getString();
        }
        return null;
    }

    public StringKey getNameKey() {
        return (StringKey) this.mName.getKey();
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public boolean isValid() {
        return (isRemoved() || this.mName.getItem() == null) ? false : true;
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public void merge(DebugElement debugElement) {
        super.merge(debugElement);
        this.mName.setKey(((DebugSetSourceFile) debugElement).mName.getKey());
    }

    public void setName(String str) {
        this.mName.setKey(StringKey.create(str));
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public String toString() {
        return super.toString() + ", " + this.mName;
    }

    @Override // com.reandroid.dex.debug.DebugElement
    public Iterator<IdItem> usedIds() {
        return SingleIterator.of(this.mName.getItem());
    }

    public void setName(StringKey stringKey) {
        this.mName.setKey(stringKey);
    }
}
