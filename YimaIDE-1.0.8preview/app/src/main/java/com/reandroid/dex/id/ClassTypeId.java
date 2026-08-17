package com.reandroid.dex.id;

import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.reference.IdItemIndirectReference;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClassTypeId extends IdItemIndirectReference<TypeId> implements SmaliRegion {
    public ClassTypeId(ClassId classId, int i) {
        super(SectionType.TYPE_ID, classId, i, UsageMarker.USAGE_DEFINITION);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.onWriteClass(getKey());
        smaliWriter.newLine();
        getSmaliDirective().append(smaliWriter);
        smaliWriter.appendModifiers(getBlockItem().getAccessFlags());
        getItem().append(smaliWriter);
    }

    public ClassId getBlockItem() {
        return (ClassId) super.getBlockItem();
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference, com.reandroid.dex.key.KeyItem
    public TypeKey getKey() {
        return (TypeKey) super.getKey();
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.CLASS;
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference
    public String toString() {
        return SmaliWriter.toStringSafe(this).trim();
    }
}
