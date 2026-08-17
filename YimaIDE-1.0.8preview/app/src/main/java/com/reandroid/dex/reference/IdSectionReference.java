package com.reandroid.dex.reference;

import com.reandroid.dex.base.DexException;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.key.Key;
import com.reandroid.dex.sections.SectionType;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import com.reandroid.utils.HexUtil;
import com.reandroid.utils.ObjectsUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class IdSectionReference<T extends IdItem> implements IdReference<T>, SmaliFormat {
    private T item;
    private final SectionTool sectionTool;
    private final int usage;

    public IdSectionReference(SectionTool sectionTool, int i) {
        this.sectionTool = sectionTool;
        this.usage = i;
    }

    public static String toDebugString(IdItem idItem) {
        Key key;
        String string;
        if (idItem == null || (key = idItem.getKey()) == null || (string = key.toString()) == null) {
            return null;
        }
        if (string.length() > 100) {
            string = string.substring(0, 100).concat("...");
        }
        if (string.startsWith("\"")) {
            string = string.substring(1);
        }
        return string.endsWith("\"") ? string.substring(0, string.length() - 1) : string;
    }

    private void updateUsage() {
        IdItem item = getItem();
        if (item != null) {
            item.addUsageType(this.usage);
        }
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        IdItem item = getItem();
        if (item != null) {
            item.append(smaliWriter);
            return;
        }
        smaliWriter.appendComment("error reference = " + HexUtil.toHex(get(), 1));
    }

    public String buildTrace(T t) {
        StringBuilder sb = new StringBuilder(", key = '");
        String debugString = toDebugString(t);
        if (debugString == null) {
            debugString = HexUtil.toHex(get(), 1);
        }
        sb.append(debugString);
        sb.append('\'');
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            IdSectionReference idSectionReference = (IdSectionReference) obj;
            IdItem item = getItem();
            IdItem item2 = idSectionReference.getItem();
            if (item != null && item2 != null) {
                return ObjectsUtil.equals(item.getKey(), item2.getKey());
            }
            if (getSectionTool() == idSectionReference.getSectionTool() && ObjectsUtil.equals(Integer.valueOf(get()), Integer.valueOf(idSectionReference.get()))) {
                return true;
            }
        }
        return false;
    }

    public abstract int get();

    @Override // com.reandroid.dex.key.KeyItem
    public Key getKey() {
        IdItem item = getItem();
        if (item != null) {
            return item.getKey();
        }
        return null;
    }

    public SectionTool getSectionTool() {
        return this.sectionTool;
    }

    @Override // com.reandroid.dex.reference.DexReference
    public abstract SectionType<T> getSectionType();

    public int hashCode() {
        Key key = getKey();
        if (key == null) {
            return 0;
        }
        return ObjectsUtil.hash(key);
    }

    public int onSetKey(int i) {
        return i;
    }

    @Override // com.reandroid.dex.reference.DexReference
    public void pullItem() {
        this.item = (T) getSectionTool().getSectionItem(getSectionType(), get());
        updateUsage();
    }

    public void refresh() {
        T t = (T) validateReplace(this.item);
        this.item = t;
        set(t.getIdx());
        updateUsage();
    }

    public abstract void set(int i);

    @Override // com.reandroid.dex.reference.DexReference
    public void setItem(T t) {
        T t2 = (T) validateReplace(t);
        this.item = t2;
        set(t2.getIdx());
        updateUsage();
    }

    @Override // com.reandroid.dex.key.KeyReference
    public void setKey(Key key) {
        T t = (T) getSectionTool().getOrCreateSection(getSectionType()).getOrCreate(key);
        this.item = t;
        set(onSetKey(t.getIdx()));
        updateUsage();
    }

    public String toString() {
        IdItem item = getItem();
        if (item != null) {
            return item.getKey().toString();
        }
        return getSectionType().getName() + ": " + get();
    }

    public void validate() {
        validateReplace(getItem());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.reandroid.dex.base.DexException */
    public T validateReplace(T t) throws DexException {
        if (t == null) {
            throw new DexException("null id item: " + buildTrace(getItem()));
        }
        T t2 = (T) t.getReplace();
        if (t2 != null) {
            return t2;
        }
        throw new DexException("Invalid id item: " + buildTrace(getItem()));
    }

    @Override // com.reandroid.dex.reference.DexReference
    public T getItem() {
        return this.item;
    }
}
