package com.reandroid.dex.id;

import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.key.StringKey;
import com.reandroid.dex.reference.IndirectStringReference;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliRegion;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.ObjectsUtil;
import com.sun.tools.classfile.Attribute;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class SourceFile extends IndirectStringReference implements SmaliRegion {
    public static final String SourceFile = ObjectsUtil.of(Attribute.SourceFile);

    public SourceFile(ClassId classId, int i) {
        super(classId, i, UsageMarker.USAGE_SOURCE);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        StringId item = getItem();
        if (item == null) {
            return;
        }
        smaliWriter.newLine();
        getSmaliDirective().append(smaliWriter);
        item.append(smaliWriter);
    }

    public ClassId getBlockItem() {
        return (ClassId) super.getBlockItem();
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference
    public int getItemIndex(StringId stringId) {
        if (stringId == null) {
            return -1;
        }
        return stringId.getIdx();
    }

    @Override // com.reandroid.dex.smali.SmaliRegion
    public SmaliDirective getSmaliDirective() {
        return SmaliDirective.SOURCE;
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference
    public StringId pullItem(int i) {
        if (i == -1) {
            return null;
        }
        return (StringId) super.pullItem(i);
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference, com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        if (key == null) {
            setItem((IdItem) null);
        } else {
            super.setKey(key);
        }
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference
    public String toString() {
        return SmaliWriter.toStringSafe(this);
    }

    @Override // com.reandroid.dex.reference.IndirectStringReference, com.reandroid.dex.reference.IdItemIndirectReference, com.reandroid.dex.key.KeyItem
    public StringKey getKey() {
        return super.getKey();
    }

    @Override // com.reandroid.dex.reference.IdReference
    public void checkNonNullItem(StringId stringId, int i) {
    }

    @Override // com.reandroid.dex.reference.IdItemIndirectReference, com.reandroid.dex.reference.IdReference
    public void checkNonNullItem(StringId stringId) {
    }
}
