package com.reandroid.dex.data;

import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.common.HiddenApiFlag;
import com.reandroid.dex.common.Modifier;
import com.reandroid.dex.common.SectionTool;
import com.reandroid.dex.smali.SmaliFormat;
import com.reandroid.dex.smali.SmaliWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class HiddenApiFlagValue extends Ule128Item implements BlockRefresh, Iterable<HiddenApiFlag>, SmaliFormat, Comparable<HiddenApiFlagValue> {
    private Def<?> def;

    public static class Compact extends HiddenApiFlagValue {
        private final HiddenApiFlagValue source;

        public Compact(HiddenApiFlagValue hiddenApiFlagValue) {
            this.source = hiddenApiFlagValue;
        }

        @Override // com.reandroid.dex.data.HiddenApiFlagValue, java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(HiddenApiFlagValue hiddenApiFlagValue) {
            return super.compareTo(hiddenApiFlagValue);
        }

        public int countBytes() {
            return 0;
        }

        @Override // com.reandroid.dex.base.Le128
        public int get() {
            return this.source.get();
        }

        public byte[] getBytes() {
            return null;
        }

        public boolean isNull() {
            return true;
        }

        @Override // com.reandroid.dex.base.Le128
        public void onReadBytes(BlockReader blockReader) throws IOException {
        }

        public int onWriteBytes(OutputStream outputStream) throws IOException {
            return 0;
        }

        @Override // com.reandroid.dex.base.Le128
        public void set(int i) {
            this.source.set(i);
        }

        @Override // com.reandroid.dex.base.Ule128Item, com.reandroid.dex.base.Le128
        public void writeValue(int i) {
        }
    }

    public HiddenApiFlagValue() {
        set(HiddenApiFlag.NO_RESTRICTION);
    }

    public void add(HiddenApiFlag hiddenApiFlag) {
        int value;
        int value2 = HiddenApiFlag.NO_RESTRICTION;
        if (hiddenApiFlag.isDomainFlag()) {
            HiddenApiFlag restriction = getRestriction();
            if (restriction != null) {
                value2 = restriction.getValue();
            }
            value = hiddenApiFlag.getValue();
        } else {
            HiddenApiFlag domain = getDomain();
            int value3 = domain != null ? domain.getValue() : 0;
            value2 = hiddenApiFlag.getValue();
            value = value3;
        }
        set(value | value2);
    }

    @Override // com.reandroid.dex.smali.SmaliFormat
    public void append(SmaliWriter smaliWriter) throws IOException {
        smaliWriter.appendModifiers(iterator());
    }

    public void clear() {
        set(HiddenApiFlag.NO_RESTRICTION);
    }

    @Override // java.lang.Comparable
    public int compareTo(HiddenApiFlagValue hiddenApiFlagValue) {
        if (hiddenApiFlagValue == null) {
            return -1;
        }
        if (hiddenApiFlagValue == this) {
            return 0;
        }
        return SectionTool.compareIndex(this.def, hiddenApiFlagValue.def);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return (obj instanceof HiddenApiFlagValue) && get() == ((HiddenApiFlagValue) obj).get();
    }

    public Def<?> getDef() {
        return this.def;
    }

    public HiddenApiFlag getDomain() {
        return HiddenApiFlag.domainOf(get());
    }

    public HiddenApiFlag getRestriction() {
        return HiddenApiFlag.restrictionOf(get());
    }

    public int hashCode() {
        return get();
    }

    public boolean isEmpty() {
        Def<?> def = getDef();
        return def == null || def.isRemoved();
    }

    public boolean isNoRestriction() {
        return isEmpty() || get() == HiddenApiFlag.NO_RESTRICTION;
    }

    public boolean isRemoved() {
        return getParentInstance(HiddenApiRestrictions.class) == null;
    }

    @Override // java.lang.Iterable
    public Iterator<HiddenApiFlag> iterator() {
        return HiddenApiFlag.valuesOf(get());
    }

    public void linkDef(Def<?> def) {
        this.def = def;
        def.linkHiddenApiFlagValueInternal(this);
    }

    public HiddenApiFlagValue newCompact() {
        return new Compact(this);
    }

    public void refresh() {
        if (this.def == null) {
            set(HiddenApiFlag.NO_RESTRICTION);
        }
    }

    public void remove(HiddenApiFlag hiddenApiFlag) {
        int value = HiddenApiFlag.NO_RESTRICTION;
        int value2 = 0;
        if (hiddenApiFlag.isDomainFlag()) {
            HiddenApiFlag restriction = getRestriction();
            if (restriction != null) {
                value = restriction.getValue();
            }
        } else {
            HiddenApiFlag domain = getDomain();
            if (domain != null) {
                value2 = domain.getValue();
            }
        }
        set(value2 | value);
    }

    public void removeSelf() {
        HiddenApiFlagValueList hiddenApiFlagValueList = (HiddenApiFlagValueList) getParentInstance(HiddenApiFlagValueList.class);
        if (hiddenApiFlagValueList != null) {
            hiddenApiFlagValueList.remove(this);
        }
    }

    @Override // com.reandroid.dex.base.Le128
    public String toString() {
        return Modifier.toString(iterator());
    }
}
