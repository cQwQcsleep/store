package com.intellij.util.io.pagecache.impl;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.IntRef;
import it.unimi.dsi.fastutil.HashCommon;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PagesTable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final float loadFactor;
    private volatile AtomicReferenceArray<PageImpl> pages;
    private int pagesCount;
    private final transient ReentrantLock pagesLock;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3) ? 2 : 3];
        switch (i) {
            case 1:
            case 2:
            case 3:
                objArr[0] = "com/intellij/util/io/pagecache/impl/PagesTable";
                break;
            case 4:
                objArr[0] = "sourcePages";
                break;
            case 5:
                objArr[0] = "targetPages";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "pages";
                break;
            default:
                objArr[0] = "uninitializedPageFactory";
                break;
        }
        if (i == 1) {
            objArr[1] = "lookupOrCreate";
        } else if (i == 2 || i == 3) {
            objArr[1] = "insertNewPage";
        } else {
            objArr[1] = "com/intellij/util/io/pagecache/impl/PagesTable";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                objArr[2] = "rehashWithoutTombstones";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "findPageOrInsertionIndex";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "probingSequenceLengthFor";
                break;
            default:
                objArr[2] = "lookupOrCreate";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private static PageImpl findPageOrInsertionIndex(AtomicReferenceArray<PageImpl> atomicReferenceArray, int i, IntRef intRef) {
        if (atomicReferenceArray == null) {
            $$$reportNull$$$0(6);
        }
        int length = atomicReferenceArray.length();
        int iHash = hash(i) % length;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            PageImpl pageImpl = atomicReferenceArray.get(iHash);
            if (pageImpl == null) {
                if (intRef != null) {
                    if (i2 >= 0) {
                        iHash = i2;
                    }
                    intRef.set(iHash);
                }
                return null;
            }
            if (!pageImpl.isTombstone()) {
                if (pageImpl.pageIndex() == i) {
                    if (intRef != null) {
                        intRef.set(iHash);
                    }
                    return pageImpl;
                }
            } else if (i2 < 0) {
                i2 = iHash;
            }
            iHash = (iHash + 1) % length;
        }
        if (intRef != null) {
            intRef.set(-1);
        }
        return null;
    }

    private static int hash(int i) {
        return Math.abs(HashCommon.mix(i));
    }

    private void rehashToSize(int i) throws NoFreeSpaceException {
        AtomicReferenceArray<PageImpl> atomicReferenceArray = new AtomicReferenceArray<>(i);
        this.pagesCount = rehashWithoutTombstones(this.pages, atomicReferenceArray);
        this.pages = atomicReferenceArray;
    }

    private static int rehashWithoutTombstones(AtomicReferenceArray<PageImpl> atomicReferenceArray, AtomicReferenceArray<PageImpl> atomicReferenceArray2) throws NoFreeSpaceException {
        if (atomicReferenceArray == null) {
            $$$reportNull$$$0(4);
        }
        if (atomicReferenceArray2 == null) {
            $$$reportNull$$$0(5);
        }
        IntRef intRef = new IntRef();
        int i = 0;
        for (int i2 = 0; i2 < atomicReferenceArray.length(); i2++) {
            PageImpl pageImpl = atomicReferenceArray.get(i2);
            if (pageImpl != null && !pageImpl.isTombstone()) {
                int iPageIndex = pageImpl.pageIndex();
                PageImpl pageImplFindPageOrInsertionIndex = findPageOrInsertionIndex(atomicReferenceArray2, iPageIndex, intRef);
                int i3 = intRef.get();
                if (pageImplFindPageOrInsertionIndex != null) {
                    throw new AssertionError("Page[#" + iPageIndex + "] is copying now -- can't be already in .newPages! \nsource: " + atomicReferenceArray + "\ntarget: " + atomicReferenceArray2);
                }
                if (i3 < 0) {
                    if (i == atomicReferenceArray2.length()) {
                        throw new NoFreeSpaceException("Not enough space in targetPages(length: " + atomicReferenceArray2.length() + "): sourcePages(length: " + atomicReferenceArray.length() + ") contains > " + i + " !tombstone pages. \nsource: " + atomicReferenceArray + "\ntarget: " + atomicReferenceArray2);
                    }
                    throw new AssertionError("Bug: insertion index must be found for Page[#" + iPageIndex + "] during rehash.  source.length: " + atomicReferenceArray.length() + " target.length: " + atomicReferenceArray2.length() + "\nsource: " + atomicReferenceArray + "\ntarget: " + atomicReferenceArray2);
                }
                atomicReferenceArray2.set(i3, pageImpl);
                i++;
            }
        }
        return i;
    }

    public void flushAll() throws IOException {
        AtomicReferenceArray<PageImpl> atomicReferenceArray = this.pages;
        for (int i = 0; i < atomicReferenceArray.length(); i++) {
            PageImpl pageImpl = atomicReferenceArray.get(i);
            if (pageImpl != null && pageImpl.isDirty()) {
                pageImpl.flush();
            }
        }
    }

    public AtomicReferenceArray<PageImpl> pages() {
        return this.pages;
    }

    public ReentrantLock pagesLock() {
        return this.pagesLock;
    }

    public boolean shrinkIfNeeded(int i) {
        int iCeil = (int) Math.ceil(i / this.loadFactor);
        if (iCeil >= 16 && ((double) iCeil) * 2.0d < this.pages.length()) {
            this.pagesLock.lock();
            try {
                rehashToSize(iCeil);
                return true;
            } catch (NoFreeSpaceException unused) {
            } finally {
                this.pagesLock.unlock();
            }
        }
        return false;
    }

    public static final class NoFreeSpaceException extends IllegalStateException {
        private NoFreeSpaceException(String str) {
            super(str);
        }
    }
}
