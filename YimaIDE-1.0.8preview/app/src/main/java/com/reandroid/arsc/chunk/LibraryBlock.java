package com.reandroid.arsc.chunk;

import com.reandroid.arsc.array.LibraryInfoArray;
import com.reandroid.arsc.header.LibraryHeader;
import com.reandroid.arsc.value.LibraryInfo;
import java.util.Iterator;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class LibraryBlock extends Chunk<LibraryHeader> implements Iterable<LibraryInfo> {
    private final LibraryInfoArray mLibraryInfoArray;

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [com.reandroid.arsc.array.LibraryInfoArray, com.reandroid.arsc.base.Block] */
    public LibraryBlock() {
        super(new LibraryHeader(), 1);
        ?? libraryInfoArray = new LibraryInfoArray(((LibraryHeader) getHeaderBlock()).getCountItem());
        this.mLibraryInfoArray = libraryInfoArray;
        addChild(libraryInfoArray);
    }

    public void addLibraryInfo(LibraryBlock libraryBlock) {
        if (libraryBlock == null) {
            return;
        }
        Iterator<LibraryInfo> it = libraryBlock.getLibraryInfoArray().listItems().iterator();
        while (it.hasNext()) {
            addLibraryInfo(it.next());
        }
    }

    public boolean containsLibraryInfo(String str) {
        if (isEmpty()) {
            return false;
        }
        return getLibraryInfoArray().containsLibraryInfo(str);
    }

    public LibraryInfoArray getLibraryInfoArray() {
        return this.mLibraryInfoArray;
    }

    public boolean isEmpty() {
        return !iterator().hasNext();
    }

    @Override // com.reandroid.arsc.base.Block
    public boolean isNull() {
        return isEmpty();
    }

    @Override // java.lang.Iterable
    public Iterator<LibraryInfo> iterator() {
        return getLibraryInfoArray().iterator(true);
    }

    public void merge(LibraryBlock libraryBlock) {
        if (libraryBlock == null || libraryBlock == this) {
            return;
        }
        getLibraryInfoArray().merge(libraryBlock.getLibraryInfoArray());
    }

    public LibraryInfo newLibraryInfo() {
        return (LibraryInfo) this.mLibraryInfoArray.createNext();
    }

    @Override // com.reandroid.arsc.chunk.Chunk
    public void onChunkRefreshed() {
        getHeaderBlock().getCountItem().set(this.mLibraryInfoArray.size());
    }

    public void setLibraryCount(int i) {
        getHeaderBlock().getCountItem().set(i);
        this.mLibraryInfoArray.setSize(i);
    }

    public int size() {
        return this.mLibraryInfoArray.size();
    }

    public void addLibraryInfo(LibraryInfo libraryInfo) {
        if (libraryInfo == null) {
            return;
        }
        getLibraryInfoArray().add(libraryInfo);
        getHeaderBlock().getCountItem().set(this.mLibraryInfoArray.size());
    }
}
