package com.reandroid.dex.data;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.DexException;
import com.reandroid.dex.data.HiddenApiFlagValue;
import com.reandroid.utils.CompareUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class HiddenApiFlagValueList extends BlockList<HiddenApiFlagValue> implements Iterable<HiddenApiFlagValue> {
    private static final Creator<HiddenApiFlagValue> CREATOR = new Creator() { // from class: y76
        @Override // com.reandroid.arsc.base.Creator
        public final Block newInstance() {
            return new HiddenApiFlagValue();
        }
    };
    private DefArray<?> defArray;

    public static class Compact extends HiddenApiFlagValueList {
        private final HiddenApiFlagValueList source;

        public Compact(HiddenApiFlagValueList hiddenApiFlagValueList) {
            super(new CompactCreator(hiddenApiFlagValueList));
            this.source = hiddenApiFlagValueList;
        }

        @Override // com.reandroid.arsc.container.BlockList
        public int countBytes() {
            return 0;
        }

        @Override // com.reandroid.dex.data.HiddenApiFlagValueList
        public HiddenApiFlagValueList newCompact() {
            return new Compact(this.source);
        }

        @Override // com.reandroid.dex.data.HiddenApiFlagValueList, com.reandroid.arsc.container.BlockList
        public void onPreRefresh() {
        }

        @Override // com.reandroid.dex.data.HiddenApiFlagValueList
        public void onReadBytes(BlockReader blockReader) throws IOException {
        }

        @Override // com.reandroid.arsc.container.BlockList
        public int onWriteBytes(OutputStream outputStream) throws IOException {
            return 0;
        }

        @Override // com.reandroid.arsc.container.BlockList
        public void readChildes(BlockReader blockReader) throws IOException {
        }
    }

    public static class CompactCreator implements Creator<HiddenApiFlagValue> {
        private final HiddenApiFlagValueList source;

        public CompactCreator(HiddenApiFlagValueList hiddenApiFlagValueList) {
            this.source = hiddenApiFlagValueList;
        }

        @Override // com.reandroid.arsc.base.Creator
        public HiddenApiFlagValue newInstance() {
            throw new RuntimeException("Call newInstanceAt()");
        }

        @Override // com.reandroid.arsc.base.Creator
        public HiddenApiFlagValue newInstanceAt(int i) {
            return this.source.get(i).newCompact();
        }
    }

    public HiddenApiFlagValueList() {
        this(CREATOR);
    }

    private void ensureDefLinked() {
        DefArray<?> defArray = this.defArray;
        if (defArray == null || defArray.size() == 0) {
            setSize(0);
            return;
        }
        removeIf(new Predicate() { // from class: z76
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((HiddenApiFlagValue) obj).isEmpty();
            }
        });
        int size = defArray.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            Def<?> def = defArray.get(i);
            HiddenApiFlagValue hiddenApiFlagValue = get(def);
            if (!z) {
                z = def.getIndex() != hiddenApiFlagValue.getIndex();
            }
        }
        if (z) {
            sort(CompareUtil.getComparableComparator());
        }
    }

    public HiddenApiFlagValue get(Def<?> def) {
        if (def == null) {
            return null;
        }
        DefArray<?> defArray = this.defArray;
        if (defArray == null) {
            defArray = (DefArray) def.getParentInstance(DefArray.class);
            linkDefArray(defArray);
        }
        HiddenApiFlagValue hiddenApiFlagValue = get(def.getIndex());
        if (defArray == null) {
            return hiddenApiFlagValue;
        }
        if (hiddenApiFlagValue != null && hiddenApiFlagValue.getDef() == def) {
            return hiddenApiFlagValue;
        }
        int size = size();
        for (int i = 0; i < size; i++) {
            HiddenApiFlagValue hiddenApiFlagValue2 = get(i);
            if (hiddenApiFlagValue2.getDef() == def) {
                return hiddenApiFlagValue2;
            }
        }
        HiddenApiFlagValue hiddenApiFlagValueCreateNext = createNext();
        hiddenApiFlagValueCreateNext.linkDef(def);
        return hiddenApiFlagValueCreateNext;
    }

    public boolean isAllNoRestrictions() {
        Iterator<HiddenApiFlagValue> it = iterator();
        while (it.hasNext()) {
            if (!it.next().isNoRestriction()) {
                return false;
            }
        }
        return true;
    }

    public void linkDefArray(DefArray<?> defArray) {
        DefArray<?> defArray2 = this.defArray;
        if (defArray2 != null) {
            if (defArray2 != defArray) {
                throw new DexException("Invalid link state");
            }
            return;
        }
        this.defArray = defArray;
        if (defArray == null) {
            setSize(0);
            return;
        }
        int size = defArray.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            get(i).linkDef((Def) defArray.get(i));
        }
    }

    public HiddenApiFlagValueList newCompact() {
        return new Compact(this);
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onPreRefresh() {
        super.onPreRefresh();
        ensureDefLinked();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        readChildes(blockReader);
    }

    public HiddenApiFlagValueList(Creator<? extends HiddenApiFlagValue> creator) {
        super(creator);
    }
}
