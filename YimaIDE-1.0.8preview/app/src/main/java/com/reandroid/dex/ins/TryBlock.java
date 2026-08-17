package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.ByteArray;
import com.reandroid.dex.base.DexPositionAlign;
import com.reandroid.dex.base.Ule128Item;
import com.reandroid.dex.common.IdUsageIterator;
import com.reandroid.dex.data.CodeItem;
import com.reandroid.dex.data.FixedDexContainerWithTool;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.ins.TryItem;
import com.reandroid.dex.smali.model.SmaliCodeTryItem;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.ExpandIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.Objects;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TryBlock extends FixedDexContainerWithTool implements Creator<TryItem>, Iterable<TryItem>, LabelsSet, IdUsageIterator {
    private static final int INDEX_itemArray = 3;
    private static final int INDEX_itemsCount = 1;
    private static final int INDEX_offsetArray = 0;
    private static final int INDEX_positionAlign = 4;
    private static final int INDEX_unknownBytes = 2;
    private final CodeItem codeItem;
    private HandlerOffsetArray handlerOffsetArray;
    private DexPositionAlign positionAlign;
    private BlockList<TryItem> tryItemArray;
    private Ule128Item tryItemsCount;
    private ByteArray unknownBytes;

    public TryBlock(CodeItem codeItem) {
        super(5);
        this.codeItem = codeItem;
    }

    private void add(TryItem tryItem) {
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList != null) {
            blockList.add(tryItem);
            this.handlerOffsetArray.ensureSize(this.tryItemArray.size());
        }
    }

    private void clear() {
        HandlerOffsetArray handlerOffsetArray = this.handlerOffsetArray;
        if (handlerOffsetArray != null) {
            handlerOffsetArray.setParent((Block) null);
            this.handlerOffsetArray.setIndex(-1);
            this.handlerOffsetArray = null;
        }
        Ule128Item ule128Item = this.tryItemsCount;
        if (ule128Item != null) {
            ule128Item.setParent((Block) null);
            this.tryItemsCount.setIndex(-1);
            this.tryItemArray = null;
        }
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList != null) {
            blockList.clearChildes();
            this.tryItemArray.setParent((Block) null);
            this.tryItemArray.setIndex(-1);
            this.tryItemArray = null;
        }
        DexPositionAlign dexPositionAlign = this.positionAlign;
        if (dexPositionAlign != null) {
            dexPositionAlign.setParent((Block) null);
            this.positionAlign.setIndex(-1);
            this.positionAlign = null;
        }
        addChild(0, (Block) null);
        addChild(1, (Block) null);
        addChild(2, (Block) null);
        addChild(3, (Block) null);
        addChild(4, (Block) null);
    }

    private CodeItem getCodeItem() {
        return this.codeItem;
    }

    private HandlerOffsetArray initHandlersOffset() {
        if (this.handlerOffsetArray == null) {
            HandlerOffsetArray handlerOffsetArray = new HandlerOffsetArray(getCodeItem().getTryCountReference());
            this.handlerOffsetArray = handlerOffsetArray;
            addChild(0, handlerOffsetArray);
        }
        return this.handlerOffsetArray;
    }

    private void initTryItemArray() {
        if (this.tryItemArray != null) {
            return;
        }
        Ule128Item ule128Item = new Ule128Item();
        this.tryItemsCount = ule128Item;
        addChild(1, ule128Item);
        BlockList<TryItem> blockList = new BlockList<>(this);
        this.tryItemArray = blockList;
        addChild(3, blockList);
    }

    private void initialize() {
        initHandlersOffset();
        initTryItemArray();
        if (this.positionAlign == null) {
            DexPositionAlign dexPositionAlign = new DexPositionAlign();
            this.positionAlign = dexPositionAlign;
            addChild(4, dexPositionAlign);
        }
    }

    private void readUnknownBytes(BlockReader blockReader) throws IOException {
        setUnknownBytes(this.handlerOffsetArray.getMinStart() - this.tryItemsCount.countBytes());
        ByteArray byteArray = this.unknownBytes;
        if (byteArray != null) {
            byteArray.readBytes(blockReader);
        }
    }

    private void setUnknownBytes(int i) {
        if (i <= 0 || isNull()) {
            ByteArray byteArray = this.unknownBytes;
            if (byteArray != null) {
                byteArray.setParent((Block) null);
                byteArray.setIndex(-1);
                byteArray.setSize(0);
                this.unknownBytes = null;
                return;
            }
            return;
        }
        ByteArray byteArray2 = this.unknownBytes;
        if (byteArray2 != null) {
            byteArray2.setSize(i);
            return;
        }
        ByteArray byteArray3 = new ByteArray(i);
        this.unknownBytes = byteArray3;
        byteArray3.setParent(this);
        addChild(2, byteArray3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void updateHandlerOffsets() {
        Ule128Item ule128Item = this.tryItemsCount;
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList == null || ule128Item == null) {
            return;
        }
        int size = blockList.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            if (!blockList.get(i2).isCompact()) {
                i++;
            }
        }
        ule128Item.set(i);
        int iCountBytes = ule128Item.countBytes();
        HandlerOffsetArray handlerOffsetArray = this.handlerOffsetArray;
        handlerOffsetArray.setSize(size);
        for (int i3 = 0; i3 < size; i3++) {
            ((HandlerOffset) handlerOffsetArray.get(i3)).setOffset(blockList.countUpTo(blockList.get(i3)) + iCountBytes);
        }
    }

    public boolean combineTries() {
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList == null) {
            return false;
        }
        boolean z = false;
        for (int i = 0; i < blockList.size(); i++) {
            TryItem tryItem = blockList.get(i);
            if (!tryItem.isCompact()) {
                for (int i2 = i + 1; i2 < blockList.size(); i2++) {
                    TryItem tryItem2 = (TryItem) blockList.get(i2);
                    if (tryItem.combineWith(tryItem2)) {
                        tryItem2.removeSelf();
                        z = true;
                    }
                }
            }
        }
        if (z) {
            refresh();
        }
        return z;
    }

    public boolean compactSimilarCatches() {
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList == null) {
            return false;
        }
        int size = blockList.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            TryItem tryItem = blockList.get(i);
            if (!tryItem.isCompact()) {
                for (int i2 = i + 1; i2 < size; i2++) {
                    if (tryItem.compactWith((TryItem) blockList.get(i2))) {
                        z = true;
                    }
                }
            }
        }
        if (z) {
            refresh();
        }
        return z;
    }

    public TryItem createNext() {
        initialize();
        TryItem tryItemNewInstance = newInstance();
        add(tryItemNewInstance);
        return tryItemNewInstance;
    }

    public TryItem createNextCopy(TryItem tryItem) {
        TryItem tryItemNewCompact = tryItem.newCompact();
        add(tryItemNewCompact);
        return tryItemNewCompact;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TryBlock tryBlock = (TryBlock) obj;
            if (isNull()) {
                return tryBlock.isNull();
            }
            if (Objects.equals(this.handlerOffsetArray, tryBlock.handlerOffsetArray) && Objects.equals(this.tryItemArray, tryBlock.tryItemArray)) {
                return true;
            }
        }
        return false;
    }

    public boolean flattenCompactCatches() {
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList == null) {
            return false;
        }
        int size = blockList.size();
        boolean z = false;
        for (int i = 0; i < size; i++) {
            if (blockList.get(i).flatten()) {
                z = true;
            }
        }
        if (z) {
            refresh();
        }
        return z;
    }

    public void fromSmali(SmaliCodeTryItem smaliCodeTryItem) {
        createNext().fromSmali(smaliCodeTryItem);
        updateHandlerOffsets();
    }

    public TryItem get(int i) {
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList != null) {
            return blockList.get(i);
        }
        return null;
    }

    public InstructionList getInstructionList() {
        return getCodeItem().getInstructionList();
    }

    @Override // com.reandroid.dex.ins.LabelsSet
    public Iterator<Label> getLabels() {
        return new ExpandIterator(iterator());
    }

    public DexPositionAlign getPositionAlign() {
        return this.positionAlign;
    }

    public Iterator<TryItem> getTriesForAddress(final int i) {
        return FilterIterator.of(iterator(), new Predicate() { // from class: kqe
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((TryItem) obj).hasExceptionHandlersForAddress(i);
            }
        });
    }

    public int getTryItemCount() {
        if (isNull()) {
            return 0;
        }
        return this.tryItemArray.getCount();
    }

    public int hashCode() {
        HandlerOffsetArray handlerOffsetArray = this.handlerOffsetArray;
        int iHashCode = handlerOffsetArray != null ? handlerOffsetArray.hashCode() + 31 : 31;
        BlockList<TryItem> blockList = this.tryItemArray;
        int i = iHashCode * 31;
        return blockList != null ? i + blockList.hashCode() : i;
    }

    public boolean isEmpty() {
        BlockList<TryItem> blockList = this.tryItemArray;
        return blockList == null || blockList.size() == 0;
    }

    public boolean isNull() {
        return this.tryItemArray == null;
    }

    @Override // java.lang.Iterable
    public Iterator<TryItem> iterator() {
        return isNull() ? EmptyIterator.of() : this.tryItemArray.iterator();
    }

    public void merge(TryBlock tryBlock) {
        boolean zIsNull = tryBlock.isNull();
        setNull(zIsNull);
        if (zIsNull) {
            return;
        }
        int tryItemCount = tryBlock.getTryItemCount();
        for (int i = 0; i < tryItemCount; i++) {
            TryItem tryItem = tryBlock.get(i);
            TryItem tryItem2 = tryItem.getTryItem();
            TryItem tryItemNewCompact = tryItem != tryItem2 ? get(tryItem2.getIndex()).newCompact() : newInstance();
            add(tryItemNewCompact);
            tryItemNewCompact.merge(tryItem);
        }
        updateHandlerOffsets();
    }

    public void moveTo(TryItem tryItem, int i) {
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList == null || tryItem.getIndex() == i) {
            return;
        }
        blockList.moveTo(tryItem, i);
        this.handlerOffsetArray.moveTo(tryItem.getHandlerOffset(), i);
    }

    @Override // com.reandroid.arsc.base.Creator
    public TryItem newInstance() {
        return new TryItem(initHandlersOffset());
    }

    @Override // com.reandroid.arsc.base.Creator
    public TryItem newInstanceAt(int i) {
        TryItem tryItemNewCompact;
        BlockList<TryItem> blockList = this.tryItemArray;
        HandlerOffsetArray handlerOffsetArrayInitHandlersOffset = initHandlersOffset();
        if (blockList.size() < 2) {
            return new TryItem(handlerOffsetArrayInitHandlersOffset);
        }
        int iIndexOf = handlerOffsetArrayInitHandlersOffset.indexOf(handlerOffsetArrayInitHandlersOffset.getOffset(i));
        if (iIndexOf < 0 || iIndexOf >= i) {
            tryItemNewCompact = null;
        } else {
            tryItemNewCompact = (TryItem) blockList.get(iIndexOf);
            if (tryItemNewCompact != null) {
                tryItemNewCompact = tryItemNewCompact.newCompact();
            }
        }
        return tryItemNewCompact == null ? new TryItem(handlerOffsetArrayInitHandlersOffset) : tryItemNewCompact;
    }

    @Override // com.reandroid.dex.base.FixedDexContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        boolean z = getCodeItem().getTryCountReference().get() == 0;
        setNull(z);
        if (z) {
            return;
        }
        this.handlerOffsetArray.onReadBytes(blockReader);
        this.tryItemsCount.onReadBytes(blockReader);
        readUnknownBytes(blockReader);
        this.tryItemArray.setSize(this.handlerOffsetArray.size());
        this.tryItemArray.readChildes(blockReader);
        this.positionAlign.onReadBytes(blockReader);
    }

    public void onRefreshed() {
        super.onRefreshed();
        if (isNull()) {
            return;
        }
        this.tryItemArray.removeIf(new Predicate() { // from class: lqe
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((TryItem) obj).isEmpty();
            }
        });
        updateHandlerOffsets();
        if (isEmpty()) {
            setNull(true);
        } else {
            this.positionAlign.align(this);
        }
    }

    public void onRemove() {
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList != null) {
            this.tryItemArray = null;
            int count = blockList.getCount();
            for (int i = 0; i < count; i++) {
                blockList.getLast().onRemove();
            }
            blockList.clearChildes();
        }
        HandlerOffsetArray handlerOffsetArray = this.handlerOffsetArray;
        if (handlerOffsetArray != null) {
            handlerOffsetArray.setSize(0);
            this.handlerOffsetArray = null;
        }
        if (this.positionAlign != null) {
            this.positionAlign = null;
        }
    }

    public void remove(TryItem tryItem) {
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList == null || !blockList.remove(tryItem)) {
            return;
        }
        tryItem.onRemove();
    }

    public void setNull(boolean z) {
        if (z == isNull()) {
            return;
        }
        if (z) {
            clear();
        } else {
            initialize();
        }
    }

    public boolean splitTryHandlers() {
        BlockList<TryItem> blockList = this.tryItemArray;
        if (blockList == null) {
            return false;
        }
        flattenCompactCatches();
        boolean z = false;
        for (int i = 0; i < blockList.size(); i++) {
            if (blockList.get(i).splitHandlers()) {
                z = true;
            }
        }
        if (z) {
            refresh();
        }
        return z;
    }

    public String toString() {
        if (isNull()) {
            return "NULL";
        }
        return "tryItems = " + this.tryItemArray.toString() + ", bytes=" + countBytes();
    }

    @Override // com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return new IterableIterator<TryItem, IdItem>(iterator()) { // from class: com.reandroid.dex.ins.TryBlock.1
            public Iterator<IdItem> iterator(TryItem tryItem) {
                return tryItem.usedIds();
            }
        };
    }
}
