package com.reandroid.dex.data;

import com.reandroid.dex.base.PositionAlignedItem;
import com.reandroid.dex.base.UsageMarker;
import com.reandroid.dex.id.TypeId;
import com.reandroid.dex.key.KeyReference;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.key.TypeListKey;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliDirective;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.collection.ComputeIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeList extends ShortIdList<TypeId> implements KeyReference, PositionAlignedItem, SmaliFormat, Iterable<TypeId> {
    public TypeList() {
        super(SectionType.TYPE_ID, UsageMarker.USAGE_DEFINITION);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        Iterator<TypeId> it = iterator();
        while (it.hasNext()) {
            it.next().append(smaliWriter);
        }
    }

    public void appendInterfaces(SmaliWriter smaliWriter) throws IOException {
        SmaliDirective smaliDirective = null;
        for (TypeId typeId : this) {
            if (smaliDirective == null) {
                smaliWriter.appendCommentNewLine("interfaces");
                smaliDirective = SmaliDirective.IMPLEMENTS;
            }
            smaliWriter.newLine();
            smaliDirective.append(smaliWriter);
            typeId.append(smaliWriter);
        }
        if (smaliDirective != null) {
            smaliWriter.newLine();
        }
    }

    public TypeId get(TypeKey typeKey) {
        if (typeKey == null) {
            return null;
        }
        for (TypeId typeId : this) {
            if (typeKey.equals(typeId.getKey())) {
                return typeId;
            }
        }
        return null;
    }

    @Override // com.reandroid.dex.data.ShortIdList, com.reandroid.dex.data.DataItem, com.reandroid.dex.common.SectionItem, com.reandroid.dex.key.KeyItem
    public TypeListKey getKey() {
        TypeKey[] typeKeyArr = new TypeKey[size()];
        getItemKeys(typeKeyArr);
        return (TypeListKey) checkKey(TypeListKey.create(typeKeyArr));
    }

    public int getParameterRegistersCount() {
        int size = size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i = getItem(i2).isWide() ? i + 2 : i + 1;
        }
        return i;
    }

    @Override // com.reandroid.dex.common.SectionItem
    public SectionType<TypeList> getSectionType() {
        return SectionType.TYPE_LIST;
    }

    public TypeId getTypeIdForRegister(int i) {
        int size = size();
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            TypeId item = getItem(i3);
            if (i2 == i) {
                return item;
            }
            i2 = item.isWide() ? i2 + 2 : i2 + 1;
        }
        return null;
    }

    public Iterator<TypeKey> getTypeKeys() {
        return ComputeIterator.of(iterator(), new Function() { // from class: cve
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((TypeId) obj).getKey();
            }
        });
    }

    @Override // com.reandroid.dex.common.SectionItem
    public boolean isBlank() {
        return isRemoved() || isEmpty();
    }

    @Override // com.reandroid.dex.data.ShortIdList
    public int size() {
        return super.size();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<TypeId> it = iterator();
        while (it.hasNext()) {
            sb.append(it.next());
        }
        return sb.toString();
    }
}
