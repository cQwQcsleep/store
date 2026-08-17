package com.reandroid.dex.ins;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.dex.base.Sle128Item;
import com.reandroid.dex.common.IdUsageIterator;
import com.reandroid.dex.data.FixedDexContainerWithTool;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.ins.CatchTypedHandler;
import com.reandroid.dex.ins.ExceptionHandler;
import com.reandroid.dex.key.TypeKey;
import com.reandroid.dex.smali.model.SmaliCodeCatch;
import com.reandroid.dex.smali.model.SmaliCodeCatchAll;
import com.reandroid.dex.smali.model.SmaliCodeTryItem;
import com.reandroid.dex.smali.model.SmaliSet;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.CombiningIterator;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.EmptyIterator;
import com.reandroid.utils.collection.ExpandIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.SingleIterator;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TryItem extends FixedDexContainerWithTool implements Iterable<Label>, IdUsageIterator {
    private CatchAllHandler catchAllHandler;
    private final BlockList<CatchTypedHandler> catchTypedHandlerList;
    private final HandlerOffsetArray handlerOffsetArray;
    final Sle128Item handlersCount;
    private HandlerOffset mHandlerOffset;

    public static class Compact extends TryItem {
        private final TryItem tryItem;

        public Compact(TryItem tryItem) {
            super();
            this.tryItem = tryItem;
        }

        @Override // com.reandroid.dex.ins.TryItem
        public boolean compactWith(TryItem tryItem) {
            return false;
        }

        public int countBytes() {
            return 0;
        }

        @Override // com.reandroid.dex.ins.TryItem
        public boolean flatten() {
            TryBlock tryBlock = getTryBlock();
            if (tryBlock == null) {
                return false;
            }
            int index = getIndex();
            TryItem tryItemCreateNext = tryBlock.createNext();
            tryItemCreateNext.merge(this);
            tryBlock.remove(this);
            tryBlock.moveTo(tryItemCreateNext, index);
            tryItemCreateNext.refresh();
            return true;
        }

        public byte[] getBytes() {
            return null;
        }

        @Override // com.reandroid.dex.ins.TryItem
        public CatchAllHandler getCatchAllHandler() {
            CatchAllHandler catchAllHandler = this.tryItem.getCatchAllHandler();
            return catchAllHandler != null ? catchAllHandler.newCompact(this) : catchAllHandler;
        }

        @Override // com.reandroid.dex.ins.TryItem
        public CatchTypedHandler getCatchTypedHandler(int i) {
            return super.getCatchTypedHandler(i).newCompact(this);
        }

        @Override // com.reandroid.dex.ins.TryItem
        public BlockList<CatchTypedHandler> getCatchTypedHandlerBlockList() {
            return this.tryItem.getCatchTypedHandlerBlockList();
        }

        @Override // com.reandroid.dex.ins.TryItem
        public Iterator<CatchTypedHandler> getCatchTypedHandlers() {
            return ComputeIterator.of(getCatchTypedHandlerBlockList().iterator(), new Function() { // from class: com.reandroid.dex.ins.a
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((CatchTypedHandler) obj).newCompact(this);
                }
            });
        }

        @Override // com.reandroid.dex.ins.TryItem
        public HandlerOffsetArray getHandlerOffsetArray() {
            return this.tryItem.getHandlerOffsetArray();
        }

        @Override // com.reandroid.dex.ins.TryItem
        public CatchAllHandler getOrCreateCatchAll() {
            this.tryItem.getOrCreateCatchAll();
            return getCatchAllHandler();
        }

        @Override // com.reandroid.dex.ins.TryItem
        public TryBlock getTryBlock() {
            return this.tryItem.getTryBlock();
        }

        @Override // com.reandroid.dex.ins.TryItem
        public TryItem getTryItem() {
            return this.tryItem.getTryItem();
        }

        @Override // com.reandroid.dex.ins.TryItem
        public int hashCode() {
            return ObjectsUtil.hash(getClass(), Integer.valueOf(super.hashCode()));
        }

        @Override // com.reandroid.dex.ins.TryItem
        public boolean isCompact() {
            return true;
        }

        @Override // com.reandroid.dex.ins.TryItem
        public void mergeHandler(ExceptionHandler exceptionHandler) {
        }

        @Override // com.reandroid.dex.ins.TryItem
        public void mergeHandlers(TryItem tryItem) {
        }

        @Override // com.reandroid.dex.ins.TryItem
        public TryItem newCompact() {
            return this.tryItem.newCompact();
        }

        public void onPreRefresh() {
        }

        @Override // com.reandroid.dex.ins.TryItem, com.reandroid.dex.base.FixedDexContainer
        public void onReadBytes(BlockReader blockReader) throws IOException {
        }

        @Override // com.reandroid.dex.ins.TryItem
        public void onRefreshed() {
        }

        public int onWriteBytes(OutputStream outputStream) throws IOException {
            return 0;
        }

        @Override // com.reandroid.dex.ins.TryItem
        public String toString() {
            return getParent() == null ? "NULL" : super.toString();
        }

        @Override // com.reandroid.dex.ins.TryItem
        public void updateCount() {
        }
    }

    public TryItem(HandlerOffsetArray handlerOffsetArray) {
        super(3);
        this.handlerOffsetArray = handlerOffsetArray;
        Sle128Item sle128Item = new Sle128Item();
        this.handlersCount = sle128Item;
        BlockList<CatchTypedHandler> blockList = new BlockList<>();
        this.catchTypedHandlerList = blockList;
        addChild(0, sle128Item);
        addChild(1, blockList);
    }

    private boolean equalsOffsetAndCodeUnit(TryItem tryItem) {
        if (tryItem == this || isCompact() || tryItem.isCompact() || getParent() != tryItem.getParent()) {
            return false;
        }
        HandlerOffset handlerOffset = getHandlerOffset();
        HandlerOffset handlerOffset2 = tryItem.getHandlerOffset();
        return handlerOffset.getStartAddress() == handlerOffset2.getStartAddress() && handlerOffset.getCatchCodeUnit() == handlerOffset2.getCatchCodeUnit();
    }

    private CatchAllHandler initCatchAllHandler() {
        CatchAllHandler catchAllHandler = getCatchAllHandler();
        if (catchAllHandler != null) {
            return catchAllHandler;
        }
        CatchAllHandler catchAllHandler2 = new CatchAllHandler();
        addChild(2, catchAllHandler2);
        this.catchAllHandler = catchAllHandler2;
        return catchAllHandler2;
    }

    private boolean isSimilarTo(TryItem tryItem) {
        int catchTypedHandlersCount;
        if (tryItem == this || isCompact() || tryItem.isCompact() || getParent() != tryItem.getParent() || !ExceptionHandler.areSimilar(getCatchAllHandler(), tryItem.getCatchAllHandler()) || (catchTypedHandlersCount = getCatchTypedHandlersCount()) != tryItem.getCatchTypedHandlersCount()) {
            return false;
        }
        for (int i = 0; i < catchTypedHandlersCount; i++) {
            if (!ExceptionHandler.areSimilar(getCatchTypedHandler(i), tryItem.getCatchTypedHandler(i))) {
                return false;
            }
        }
        return true;
    }

    private int transferHandlerToNewTryItem(ExceptionHandler exceptionHandler, int i) {
        if (exceptionHandler == null) {
            return i;
        }
        TryBlock tryBlock = getTryBlock();
        TryItem tryItemCreateNext = tryBlock.createNext();
        tryItemCreateNext.mergeOffset(this);
        tryItemCreateNext.mergeHandler(exceptionHandler);
        remove(exceptionHandler);
        tryBlock.moveTo(tryItemCreateNext, i);
        tryItemCreateNext.refresh();
        return i + 1;
    }

    public boolean combineWith(TryItem tryItem) {
        if (!equalsOffsetAndCodeUnit(tryItem)) {
            return false;
        }
        Iterator<ExceptionHandler> exceptionHandlers = tryItem.getExceptionHandlers();
        while (exceptionHandlers.hasNext()) {
            mergeHandler(exceptionHandlers.next());
        }
        return true;
    }

    public boolean compactWith(TryItem tryItem) {
        if (!isSimilarTo(tryItem)) {
            return false;
        }
        int index = tryItem.getIndex();
        TryBlock tryBlock = getTryBlock();
        TryItem tryItemCreateNextCopy = tryBlock.createNextCopy(this);
        tryItemCreateNextCopy.merge(tryItem);
        tryBlock.remove(tryItem);
        tryBlock.moveTo(tryItemCreateNextCopy, index);
        return true;
    }

    public CatchTypedHandler createNext() {
        BlockList<CatchTypedHandler> catchTypedHandlerBlockList = getCatchTypedHandlerBlockList();
        CatchTypedHandler catchTypedHandler = new CatchTypedHandler();
        catchTypedHandlerBlockList.add(catchTypedHandler);
        updateCount();
        return catchTypedHandler;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            TryItem tryItem = (TryItem) obj;
            if (ObjectsUtil.equals(getCatchTypedHandlerBlockList(), tryItem.getCatchTypedHandlerBlockList()) && ObjectsUtil.equals(getCatchAllHandler(), tryItem.getCatchAllHandler())) {
                return true;
            }
        }
        return false;
    }

    public boolean flatten() {
        return false;
    }

    public void fromSmali(SmaliCodeTryItem smaliCodeTryItem) {
        setStartAddress(smaliCodeTryItem.getStartAddress());
        SmaliSet<SmaliCodeCatch> catchSet = smaliCodeTryItem.getCatchSet();
        BlockList<CatchTypedHandler> catchTypedHandlerBlockList = getCatchTypedHandlerBlockList();
        int size = catchSet.size();
        for (int i = 0; i < size; i++) {
            SmaliCodeCatch smaliCodeCatch = (SmaliCodeCatch) catchSet.get(i);
            CatchTypedHandler catchTypedHandler = new CatchTypedHandler();
            catchTypedHandlerBlockList.add(catchTypedHandler);
            catchTypedHandler.fromSmali(smaliCodeCatch);
        }
        SmaliCodeCatchAll catchAll = smaliCodeTryItem.getCatchAll();
        if (catchAll != null) {
            initCatchAllHandler().fromSmali(catchAll);
        }
        updateCount();
    }

    public CatchAllHandler getCatchAllHandler() {
        return this.catchAllHandler;
    }

    public int getCatchCodeUnit() {
        return getHandlerOffset().getCatchCodeUnit();
    }

    public CatchTypedHandler getCatchTypedHandler(int i) {
        return getCatchTypedHandlerBlockList().get(i);
    }

    public BlockList<CatchTypedHandler> getCatchTypedHandlerBlockList() {
        return this.catchTypedHandlerList;
    }

    public Iterator<CatchTypedHandler> getCatchTypedHandlers() {
        return this.catchTypedHandlerList.iterator();
    }

    public int getCatchTypedHandlersCount() {
        return getCatchTypedHandlerBlockList().size();
    }

    public ExceptionHandler getExceptionHandler(TypeKey typeKey, int i) {
        Iterator<ExceptionHandler> exceptionHandlers = getExceptionHandlers();
        while (exceptionHandlers.hasNext()) {
            ExceptionHandler next = exceptionHandlers.next();
            if (next.traps(typeKey) && next.isAddressBounded(i)) {
                return next;
            }
        }
        return null;
    }

    public Iterator<ExceptionHandler> getExceptionHandlers() {
        Iterator itOf = EmptyIterator.of();
        CatchAllHandler catchAllHandler = getCatchAllHandler();
        if (catchAllHandler != null) {
            itOf = SingleIterator.of(catchAllHandler);
        }
        return new CombiningIterator(getCatchTypedHandlers(), itOf);
    }

    public Iterator<ExceptionHandler> getExceptionHandlersForAddress(final int i) {
        return FilterIterator.of(getExceptionHandlers(), new Predicate() { // from class: mqe
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((ExceptionHandler) obj).isAddressBounded(i);
            }
        });
    }

    public HandlerOffset getHandlerOffset() {
        HandlerOffset handlerOffset = this.mHandlerOffset;
        if (handlerOffset != null) {
            return handlerOffset;
        }
        HandlerOffset orCreate = getHandlerOffsetArray().getOrCreate(getIndex());
        this.mHandlerOffset = orCreate;
        orCreate.setTryItem(this);
        return orCreate;
    }

    public HandlerOffsetArray getHandlerOffsetArray() {
        return this.handlerOffsetArray;
    }

    public InstructionList getInstructionList() {
        return getTryBlock().getInstructionList();
    }

    public CatchAllHandler getOrCreateCatchAll() {
        CatchAllHandler catchAllHandler = getCatchAllHandler();
        if (catchAllHandler != null) {
            return catchAllHandler;
        }
        initCatchAllHandler();
        return getCatchAllHandler();
    }

    public int getStartAddress() {
        return getHandlerOffset().getStartAddress();
    }

    public TryBlock getTryBlock() {
        return (TryBlock) getParent(TryBlock.class);
    }

    public TryItem getTryItem() {
        return this;
    }

    public boolean hasCatchAllHandler() {
        return getCatchAllHandler() != null;
    }

    public boolean hasExceptionHandlersForAddress(int i) {
        return getExceptionHandlersForAddress(i).hasNext();
    }

    public boolean hasMultipleHandlers() {
        int catchTypedHandlersCount = getCatchTypedHandlersCount();
        if (catchTypedHandlersCount == 1) {
            return getCatchAllHandler() != null;
        }
        return catchTypedHandlersCount != 0;
    }

    public int hashCode() {
        return ObjectsUtil.hash(getCatchTypedHandlerBlockList(), getCatchAllHandler());
    }

    public boolean isCompact() {
        return false;
    }

    public boolean isEmpty() {
        return getCatchAllHandler() == null && getCatchTypedHandlersCount() == 0;
    }

    public boolean isRemoved() {
        TryBlock tryBlock;
        return getParent() == null || (tryBlock = getTryBlock()) == null || tryBlock.getParent() == null;
    }

    @Override // java.lang.Iterable
    public Iterator<Label> iterator() {
        return new ExpandIterator(getExceptionHandlers());
    }

    public void merge(TryItem tryItem) {
        mergeOffset(tryItem);
        mergeHandlers(tryItem);
    }

    public void mergeHandler(ExceptionHandler exceptionHandler) {
        (exceptionHandler instanceof CatchTypedHandler ? createNext() : getOrCreateCatchAll()).merge(exceptionHandler);
    }

    public void mergeHandlers(TryItem tryItem) {
        BlockList<CatchTypedHandler> catchTypedHandlerBlockList = tryItem.getCatchTypedHandlerBlockList();
        int size = catchTypedHandlerBlockList.size();
        BlockList<CatchTypedHandler> catchTypedHandlerBlockList2 = getCatchTypedHandlerBlockList();
        catchTypedHandlerBlockList2.ensureCapacity(size);
        for (int i = 0; i < size; i++) {
            CatchTypedHandler catchTypedHandler = catchTypedHandlerBlockList.get(i);
            CatchTypedHandler catchTypedHandler2 = new CatchTypedHandler();
            catchTypedHandlerBlockList2.add(catchTypedHandler2);
            catchTypedHandler2.merge(catchTypedHandler);
        }
        if (tryItem.hasCatchAllHandler()) {
            initCatchAllHandler().merge(tryItem.getCatchAllHandler());
        }
        updateCount();
    }

    public void mergeOffset(TryItem tryItem) {
        HandlerOffset handlerOffset = tryItem.getHandlerOffset();
        HandlerOffset handlerOffset2 = getHandlerOffset();
        handlerOffset2.setCatchCodeUnit(handlerOffset.getCatchCodeUnit());
        handlerOffset2.setStartAddress(handlerOffset.getStartAddress());
    }

    public TryItem newCompact() {
        return new Compact(this);
    }

    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        Compact compact = blockCounter.END;
        if ((compact instanceof Compact) && compact.getTryItem() == this) {
            blockCounter.FOUND = true;
        } else {
            super/*com.reandroid.arsc.base.BlockContainer*/.onCountUpTo(blockCounter);
        }
    }

    @Override // com.reandroid.dex.base.FixedDexContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        boolean z;
        int position = blockReader.getPosition();
        blockReader.seek(getHandlerOffsetArray().getItemsStart() + getHandlerOffset().getOffset());
        this.handlersCount.readBytes(blockReader);
        int i = this.handlersCount.get();
        if (i <= 0) {
            i = -i;
            z = true;
        } else {
            z = false;
        }
        BlockList<CatchTypedHandler> catchTypedHandlerBlockList = getCatchTypedHandlerBlockList();
        catchTypedHandlerBlockList.ensureCapacity(i);
        for (int i2 = 0; i2 < i; i2++) {
            CatchTypedHandler catchTypedHandler = new CatchTypedHandler();
            catchTypedHandlerBlockList.add(catchTypedHandler);
            catchTypedHandler.readBytes(blockReader);
        }
        if (z) {
            initCatchAllHandler().readBytes(blockReader);
        }
        if (position > blockReader.getPosition()) {
            blockReader.seek(position);
        }
    }

    public void onRefreshed() {
        super.onRefreshed();
        updateCount();
    }

    public void onRemove() {
        HandlerOffset handlerOffset = this.mHandlerOffset;
        BlockList<CatchTypedHandler> blockList = this.catchTypedHandlerList;
        if (blockList != null) {
            int size = blockList.size();
            for (int i = 0; i < size; i++) {
                CatchTypedHandler catchTypedHandler = blockList.get(i);
                catchTypedHandler.onRemove();
                catchTypedHandler.setParent((Block) null);
            }
            blockList.destroy();
        }
        remove(this.catchAllHandler);
        if (handlerOffset != null) {
            this.mHandlerOffset = null;
            handlerOffset.removeSelf();
        }
        setParent((Block) null);
    }

    public void remove(ExceptionHandler exceptionHandler) {
        BlockList<CatchTypedHandler> blockList;
        if (exceptionHandler == null) {
            return;
        }
        if (exceptionHandler == this.catchAllHandler) {
            exceptionHandler.onRemove();
            this.catchAllHandler = null;
        } else if ((exceptionHandler instanceof CatchTypedHandler) && (blockList = this.catchTypedHandlerList) != null && blockList.contains(exceptionHandler)) {
            this.catchTypedHandlerList.remove((CatchTypedHandler) exceptionHandler);
            exceptionHandler.onRemove();
        }
    }

    public void removeSelf() {
        TryBlock tryBlock = getTryBlock();
        if (tryBlock != null) {
            tryBlock.remove(this);
        }
    }

    public void setCatchCodeUnit(int i) {
        getHandlerOffset().setCatchCodeUnit(i);
    }

    public void setStartAddress(int i) {
        getHandlerOffset().setStartAddress(i);
    }

    public boolean splitHandlers() {
        if (!hasMultipleHandlers()) {
            return false;
        }
        int index = getIndex() + 1;
        int catchTypedHandlersCount = getCatchTypedHandlersCount();
        for (int i = 1; i < catchTypedHandlersCount; i++) {
            index = transferHandlerToNewTryItem(getCatchTypedHandler(1), index);
        }
        transferHandlerToNewTryItem(getCatchAllHandler(), index);
        refresh();
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        Iterator<ExceptionHandler> exceptionHandlers = getExceptionHandlers();
        while (exceptionHandlers.hasNext()) {
            if (sb.length() != 0) {
                sb.append('\n');
            }
            sb.append(exceptionHandlers.next());
        }
        return sb.toString();
    }

    public boolean traps(TypeKey typeKey) {
        return getExceptionHandler(typeKey) != null;
    }

    public void updateCount() {
        Sle128Item sle128Item = this.handlersCount;
        if (sle128Item == null) {
            return;
        }
        int size = this.catchTypedHandlerList.size();
        if (hasCatchAllHandler()) {
            size = -size;
        }
        sle128Item.set(size);
    }

    @Override // com.reandroid.dex.common.IdUsageIterator
    public Iterator<IdItem> usedIds() {
        return ComputeIterator.of(getCatchTypedHandlers(), new Function() { // from class: nqe
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((CatchTypedHandler) obj).getTypeId();
            }
        });
    }

    public boolean traps(TypeKey typeKey, int i) {
        return getExceptionHandler(typeKey, i) != null;
    }

    private TryItem() {
        super(0);
        this.handlerOffsetArray = null;
        this.handlersCount = null;
        this.catchTypedHandlerList = null;
    }

    public ExceptionHandler getExceptionHandler(TypeKey typeKey) {
        Iterator<ExceptionHandler> exceptionHandlers = getExceptionHandlers();
        while (exceptionHandlers.hasNext()) {
            ExceptionHandler next = exceptionHandlers.next();
            if (next.traps(typeKey)) {
                return next;
            }
        }
        return null;
    }
}
