package com.reandroid.dex.model;

import com.reandroid.common.ArraySupplier;
import com.reandroid.dex.sections.DexContainerBlock;
import com.reandroid.dex.sections.DexLayoutBlock;
import com.reandroid.utils.CompareUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.ArraySupplierIterator;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
class DexFileLayoutController implements DexContainerBlock.LayoutBlockChangedListener, ArraySupplier<DexLayout>, Iterable<DexLayout> {
    private final DexFile dexFile;
    private final List<DexLayout> dexLayoutList = new ArrayCollection();
    private boolean sortRequired;

    public DexFileLayoutController(DexFile dexFile) {
        this.dexFile = dexFile;
    }

    private DexLayout get(DexLayoutBlock dexLayoutBlock) {
        DexLayout dexLayout;
        if (dexLayoutBlock == null) {
            return null;
        }
        int index = dexLayoutBlock.getIndex();
        List<DexLayout> list = this.dexLayoutList;
        if (index > 0 && index < list.size() && (dexLayout = list.get(index)) != null && dexLayout.getDexLayoutBlock() == dexLayoutBlock) {
            return dexLayout;
        }
        for (DexLayout dexLayout2 : list) {
            if (dexLayoutBlock == dexLayout2.getDexLayoutBlock()) {
                this.sortRequired = true;
                return dexLayout2;
            }
        }
        DexLayout dexLayout3 = new DexLayout(this.dexFile, dexLayoutBlock);
        list.add(dexLayout3);
        return dexLayout3;
    }

    private DexContainerBlock getContainerBlock() {
        return this.dexFile.getContainerBlock();
    }

    @Override // com.reandroid.common.CountSupplier
    public int getCount() {
        return getContainerBlock().size();
    }

    @Override // java.lang.Iterable
    public Iterator<DexLayout> iterator() {
        return ArraySupplierIterator.of(this);
    }

    @Override // com.reandroid.dex.sections.DexContainerBlock.LayoutBlockChangedListener
    public void onLayoutAdded(DexLayoutBlock dexLayoutBlock) {
        get(dexLayoutBlock);
    }

    @Override // com.reandroid.dex.sections.DexContainerBlock.LayoutBlockChangedListener
    public void onLayoutRemoved(DexLayoutBlock dexLayoutBlock) {
        List<DexLayout> list = this.dexLayoutList;
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (list.get(i).getDexLayoutBlock() == dexLayoutBlock) {
                list.remove(i);
                return;
            }
        }
    }

    @Override // com.reandroid.dex.sections.DexContainerBlock.LayoutBlockChangedListener
    public void onLayoutsCleared() {
        this.dexLayoutList.clear();
    }

    public void refreshController() {
        if (this.sortRequired) {
            DexContainerBlock containerBlock = getContainerBlock();
            List<DexLayout> list = this.dexLayoutList;
            int i = 0;
            while (i < list.size()) {
                if (!containerBlock.containsExact(list.get(i).getDexLayoutBlock())) {
                    list.remove(i);
                    i--;
                }
                i++;
            }
            list.sort(new Comparator() { // from class: com.reandroid.dex.model.a
                @Override // java.util.Comparator
                public final int compare(Object obj, Object obj2) {
                    return CompareUtil.compare(((DexLayout) obj).getIndex(), ((DexLayout) obj2).getIndex());
                }
            });
        }
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.reandroid.common.ArraySupplier
    public DexLayout get(int i) {
        return get(getContainerBlock().get(i));
    }
}
