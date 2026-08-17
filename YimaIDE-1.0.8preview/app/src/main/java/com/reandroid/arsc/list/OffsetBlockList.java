package com.reandroid.arsc.list;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.AlignItem;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.arsc.item.OffsetItem;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class OffsetBlockList<T extends Block> extends BlockList<T> {
    private final OffsetReferenceList<?> offsetReferenceList;
    private final IntegerReference start;

    public OffsetBlockList(IntegerReference integerReference, OffsetReferenceList<?> offsetReferenceList, Creator<? extends T> creator) {
        super(creator);
        this.start = integerReference;
        this.offsetReferenceList = offsetReferenceList;
    }

    private void updateCountReference() {
        getOffsetReferenceList().setSize(size());
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateStartReference() {
        updateCountReference();
        Block parent = getParent();
        getStart().set((parent == null || isEmpty()) ? 0 : parent.countUpTo(this));
    }

    public int buildOffsetList() {
        updateStartReference();
        OffsetReferenceList<?> offsetReferenceList = getOffsetReferenceList();
        int size = size();
        offsetReferenceList.setSize(size);
        int iUpdateOffset = 0;
        for (int i = 0; i < size; i++) {
            iUpdateOffset = ((OffsetItem) offsetReferenceList.get(i)).updateOffset(get(i), iUpdateOffset);
        }
        AlignItem alignment = getAlignment();
        return alignment != null ? iUpdateOffset + alignment.align(iUpdateOffset) : iUpdateOffset;
    }

    public void clear() {
        clearChildes();
    }

    public AlignItem getAlignment() {
        return null;
    }

    public IntegerReference getCountReference() {
        return getOffsetReferenceList().getCountReference();
    }

    public OffsetReferenceList<?> getOffsetReferenceList() {
        return this.offsetReferenceList;
    }

    public IntegerReference getStart() {
        return this.start;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public void onPreRefresh() {
        super.onPreRefresh();
        updateCountReference();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (getOffsetReferenceList().size() != 0) {
            int i = getStart().get();
            blockReader.seek(i);
            BlockReader blockReaderCreate = blockReader.create(blockReader.available());
            readChildes(blockReaderCreate);
            int position = i + blockReaderCreate.getPosition();
            blockReaderCreate.close();
            blockReader.seek(position);
        }
    }

    public void onRefreshed() {
        super.onRefreshed();
        buildOffsetList();
    }

    public void readChildes(BlockReader blockReader) throws IOException {
        OffsetReferenceList<?> offsetReferenceList = getOffsetReferenceList();
        int size = offsetReferenceList.size();
        setSize(size);
        for (int i = 0; i < size; i++) {
            ((OffsetItem) offsetReferenceList.get(i)).readTarget(blockReader, get(i));
        }
    }

    public void refreshChildes() {
        int size = size();
        if (size != 0) {
            if (get(0) instanceof BlockRefresh) {
                for (int i = 0; i < size; i++) {
                    ((BlockRefresh) get(i)).refresh();
                }
            }
        }
    }

    public void setSize(int i, boolean z) {
        super.setSize(i, true);
    }

    public OffsetBlockList(IntegerReference integerReference, OffsetReferenceList<?> offsetReferenceList) {
        this.start = integerReference;
        this.offsetReferenceList = offsetReferenceList;
    }
}
