package com.reandroid.dex.sections;

import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.common.BytesOutputStream;
import com.reandroid.dex.common.FullRefresh;
import com.reandroid.dex.common.SectionItem;
import com.reandroid.dex.header.DexHeader;
import com.reandroid.dex.sections.DexLayoutBlock;
import com.reandroid.dex.sections.SectionList;
import com.reandroid.utils.collection.ComputeIterator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DexContainerBlock extends BlockList<DexLayoutBlock> implements FullRefresh, Iterable<DexLayoutBlock> {
    private LayoutBlockChangedListener layoutBlockChangedListener;
    private String mSimpleName;
    private Object mTag;

    public interface LayoutBlockChangedListener {
        void onLayoutAdded(DexLayoutBlock dexLayoutBlock);

        void onLayoutRemoved(DexLayoutBlock dexLayoutBlock);

        void onLayoutsCleared();
    }

    public static DexContainerBlock createDefault() {
        DexContainerBlock dexContainerBlock = new DexContainerBlock();
        dexContainerBlock.createNextDefault();
        return dexContainerBlock;
    }

    private void fixMinimumVersion() {
        if (isMultiLayout()) {
            for (DexLayoutBlock dexLayoutBlock : this) {
                if (dexLayoutBlock.getVersion() < 41) {
                    dexLayoutBlock.setVersion(41);
                }
            }
        }
    }

    private static boolean isDexFile(DexHeader dexHeader) {
        int version;
        return dexHeader != null && dexHeader.magic.isValid() && (version = dexHeader.getVersion()) > 0 && version < 1000;
    }

    private void notifyAdded(DexLayoutBlock dexLayoutBlock) {
        LayoutBlockChangedListener layoutBlockChangedListener = this.layoutBlockChangedListener;
        if (layoutBlockChangedListener != null) {
            layoutBlockChangedListener.onLayoutAdded(dexLayoutBlock);
        }
    }

    private void notifyLayoutsCleared() {
        LayoutBlockChangedListener layoutBlockChangedListener = this.layoutBlockChangedListener;
        if (layoutBlockChangedListener != null) {
            layoutBlockChangedListener.onLayoutsCleared();
        }
    }

    private void notifyRemoved(DexLayoutBlock dexLayoutBlock) {
        LayoutBlockChangedListener layoutBlockChangedListener = this.layoutBlockChangedListener;
        if (layoutBlockChangedListener != null) {
            layoutBlockChangedListener.onLayoutRemoved(dexLayoutBlock);
        }
    }

    private void onCreatedNew(DexLayoutBlock dexLayoutBlock) {
        if (dexLayoutBlock.getIndex() == 0) {
            return;
        }
        fixMinimumVersion();
        dexLayoutBlock.setVersion(get(0).getVersion());
    }

    @Override // com.reandroid.arsc.container.BlockList
    public boolean add(DexLayoutBlock dexLayoutBlock) {
        boolean zAdd = super.add(dexLayoutBlock);
        notifyAdded(dexLayoutBlock);
        return zAdd;
    }

    public void clear() {
        clearChildes();
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void clearChildes() {
        super.clearChildes();
        notifyLayoutsCleared();
    }

    public void clearEmptyLayouts() {
        removeIf(new Predicate() { // from class: zp3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((DexLayoutBlock) obj).isEmpty();
            }
        });
    }

    public int clearEmptySections() {
        Iterator<DexLayoutBlock> it = iterator();
        int iClearEmptySections = 0;
        while (it.hasNext()) {
            iClearEmptySections += it.next().clearEmptySections();
        }
        return iClearEmptySections;
    }

    public void combineFrom(MergeOptions mergeOptions, DexLayoutBlock dexLayoutBlock) {
        if (containsExact(dexLayoutBlock) || dexLayoutBlock.isEmpty()) {
            return;
        }
        DexLayoutBlock last = getLast();
        if (last == null || !last.isEmpty()) {
            last = createNextDefault();
        }
        last.merge(mergeOptions, dexLayoutBlock);
        fixMinimumVersion();
    }

    @Override // com.reandroid.arsc.container.BlockList
    public DexLayoutBlock createNext() {
        DexLayoutBlock dexLayoutBlock = new DexLayoutBlock();
        add(dexLayoutBlock);
        onCreatedNew(dexLayoutBlock);
        return dexLayoutBlock;
    }

    public DexLayoutBlock createNextDefault() {
        DexLayoutBlock dexLayoutBlockCreateDefault = DexLayoutBlock.createDefault();
        add(dexLayoutBlockCreateDefault);
        onCreatedNew(dexLayoutBlockCreateDefault);
        return dexLayoutBlockCreateDefault;
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void ensureSize(int i) {
        while (size() < i) {
            createNextDefault();
        }
    }

    @Override // com.reandroid.arsc.container.BlockList
    public byte[] getBytes() {
        BytesOutputStream bytesOutputStream = new BytesOutputStream(getFileSize());
        try {
            writeBytes(bytesOutputStream);
            bytesOutputStream.close();
        } catch (IOException unused) {
        }
        return bytesOutputStream.toByteArray();
    }

    public int getFileSize() {
        Iterator<DexLayoutBlock> it = iterator();
        int fileSize = 0;
        while (it.hasNext()) {
            fileSize += it.next().getFileSize();
        }
        return fileSize;
    }

    public Iterator<SectionList> getSectionLists() {
        return ComputeIterator.of(iterator(), new Function() { // from class: yp3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DexLayoutBlock) obj).getSectionList();
            }
        });
    }

    public <T1 extends SectionItem> Iterator<Section<T1>> getSections(final SectionType<T1> sectionType) {
        return ComputeIterator.of(getSectionLists(), new Function() { // from class: aq3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((SectionList) obj).getSection(sectionType);
            }
        });
    }

    public String getSimpleName() {
        return this.mSimpleName;
    }

    public Object getTag() {
        return this.mTag;
    }

    public int getVersion() {
        DexLayoutBlock first = getFirst();
        if (first != null) {
            return first.getVersion();
        }
        return 0;
    }

    public boolean isEmpty() {
        Iterator<DexLayoutBlock> it = iterator();
        while (it.hasNext()) {
            if (!it.next().isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public boolean isMultiLayout() {
        return size() > 1;
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onPreRefresh() {
        super.onPreRefresh();
        fixMinimumVersion();
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onPreRemove(DexLayoutBlock dexLayoutBlock) {
        super.onPreRemove(dexLayoutBlock);
        notifyRemoved(dexLayoutBlock);
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        readChildes(blockReader);
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void onRefreshed() {
        super.onRefreshed();
    }

    public void readBytes(BlockReader blockReader, Predicate<SectionType<?>> predicate) throws IOException {
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).readBytes(blockReader, predicate);
        }
        while (blockReader.isAvailable()) {
            createNext().readBytes(blockReader, predicate);
        }
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void readChildes(BlockReader blockReader) throws IOException {
        readBytes(blockReader, null);
    }

    @Override // com.reandroid.dex.common.FullRefresh
    public void refreshFull() {
        clearEmptyLayouts();
        Iterator<DexLayoutBlock> it = iterator();
        while (it.hasNext()) {
            it.next().refreshFull();
        }
    }

    public void setLayoutBlockChangedListener(LayoutBlockChangedListener layoutBlockChangedListener) {
        this.layoutBlockChangedListener = layoutBlockChangedListener;
    }

    public void setSimpleName(String str) {
        this.mSimpleName = str;
    }

    public void setTag(Object obj) {
        this.mTag = obj;
    }

    public void setVersion(int i) {
        Iterator<DexLayoutBlock> it = iterator();
        while (it.hasNext()) {
            it.next().setVersion(i);
        }
    }

    @Override // com.reandroid.arsc.container.BlockList
    public void add(int i, DexLayoutBlock dexLayoutBlock) {
        super.add(i, dexLayoutBlock);
        notifyAdded(dexLayoutBlock);
    }

    public static boolean isDexFile(File file) {
        if (file != null && file.isFile()) {
            try {
                return isDexFile(DexHeader.readHeader(file));
            } catch (IOException unused) {
            }
        }
        return false;
    }

    public static boolean isDexFile(InputStream inputStream) {
        try {
            return isDexFile(DexHeader.readHeader(inputStream));
        } catch (IOException unused) {
            return false;
        }
    }

    public void combineFrom(DexLayoutBlock dexLayoutBlock) {
        combineFrom(MergeOptions.DEFAULT, dexLayoutBlock);
    }

    public void combineFrom(DexContainerBlock dexContainerBlock) {
        Iterator<DexLayoutBlock> it = dexContainerBlock.iterator();
        while (it.hasNext()) {
            combineFrom(it.next());
        }
    }

    public void combineFrom(MergeOptions mergeOptions, DexContainerBlock dexContainerBlock) {
        Iterator<DexLayoutBlock> it = dexContainerBlock.iterator();
        while (it.hasNext()) {
            combineFrom(mergeOptions, it.next());
        }
    }
}
