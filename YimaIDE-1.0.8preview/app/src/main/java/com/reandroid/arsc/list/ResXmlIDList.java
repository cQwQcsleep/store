package com.reandroid.arsc.list;

import com.reandroid.arsc.chunk.xml.ResXmlDocumentChunk;
import com.reandroid.arsc.container.CountedBlockList;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.ResXmlID;
import com.reandroid.arsc.item.ResXmlString;
import com.reandroid.utils.CompareUtil;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResXmlIDList extends CountedBlockList<ResXmlID> {
    public ResXmlIDList(IntegerReference integerReference) {
        super(ResXmlID.CREATOR, integerReference);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private boolean adjustIndexes() {
        int index;
        Iterator itClonedIterator = clonedIterator();
        boolean z = false;
        while (itClonedIterator.hasNext()) {
            ResXmlID resXmlID = (ResXmlID) itClonedIterator.next();
            ResXmlString resXmlString = resXmlID.getResXmlString();
            if (resXmlString != null && (index = resXmlString.getIndex()) != resXmlID.getIndex()) {
                moveTo(resXmlID, index);
                z = true;
            }
        }
        if (z) {
            ((ResXmlDocumentChunk) getParentInstance(ResXmlDocumentChunk.class)).getStringPool().linkResXmlIDMapInternal();
        }
        return z;
    }

    public void clear() {
        super/*com.reandroid.arsc.container.BlockList*/.clearChildes();
    }

    public int countBytes() {
        int size = size();
        return size != 0 ? size * ((ResXmlID) get(0)).countBytes() : size;
    }

    public ResXmlID getId(int i) {
        int size = size();
        for (int i2 = 0; i2 < size; i2++) {
            ResXmlID resXmlID = (ResXmlID) get(i2);
            if (i == resXmlID.get()) {
                return resXmlID;
            }
        }
        return null;
    }

    public ResXmlID getOrCreate(int i) {
        ResXmlID resXmlID = (ResXmlID) get(i);
        if (resXmlID != null) {
            return resXmlID;
        }
        ResXmlID resXmlID2 = (ResXmlID) createNext();
        resXmlID2.set(i);
        return resXmlID2;
    }

    public boolean hasSimilarEntries() {
        return true;
    }

    public void setSize(int i, boolean z) {
        super/*com.reandroid.arsc.container.BlockList*/.setSize(i, true);
    }

    public boolean sort(Comparator<? super ResXmlID> comparator) {
        boolean zSort = super/*com.reandroid.arsc.container.BlockList*/.sort(comparator);
        if (adjustIndexes() && super/*com.reandroid.arsc.container.BlockList*/.sort(comparator)) {
            zSort = true;
        }
        trimLastIf(new Predicate() { // from class: kfc
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ResXmlID) obj).isEmpty();
            }
        });
        return zSort;
    }

    public boolean sort() {
        return sort(CompareUtil.getComparableComparator());
    }
}
