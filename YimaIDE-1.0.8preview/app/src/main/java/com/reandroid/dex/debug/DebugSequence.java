package com.reandroid.dex.debug;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.IntegerReference;
import com.reandroid.dex.base.FixedDexContainer;
import com.reandroid.dex.data.DebugInfo;
import com.reandroid.dex.data.InstructionList;
import com.reandroid.dex.debug.DebugElement;
import com.reandroid.dex.debug.DebugSequence;
import com.reandroid.dex.id.IdItem;
import com.reandroid.dex.smali.model.SmaliCodeSet;
import com.reandroid.dex.smali.model.SmaliDebugElement;
import com.reandroid.utils.collection.ComputeIterator;
import com.reandroid.utils.collection.FilterIterator;
import com.reandroid.utils.collection.IterableIterator;
import java.io.IOException;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DebugSequence extends FixedDexContainer implements Iterable<DebugElement> {
    private BlockList<DebugElement> elementList;
    private final IntegerReference lineStart;

    public DebugSequence(IntegerReference integerReference) {
        super(2);
        this.lineStart = integerReference;
        addChild(1, DebugEndSequence.INSTANCE);
    }

    public static /* synthetic */ boolean b(int i, DebugElement debugElement) {
        return i == debugElement.getTargetAddress();
    }

    private void cacheValues() {
        DebugElement debugElement = null;
        for (DebugElement debugElement2 : this) {
            debugElement2.cacheValues(this, debugElement);
            debugElement = debugElement2;
        }
    }

    private BlockList<DebugElement> getElementList() {
        BlockList<DebugElement> blockList = this.elementList;
        return (blockList == null || isRemoved()) ? BlockList.empty() : blockList;
    }

    public static /* synthetic */ boolean k(DebugElement debugElement) {
        return !(debugElement instanceof DebugAdvance);
    }

    public static /* synthetic */ DebugElement o(DebugElementType debugElementType, DebugElement debugElement) {
        if (debugElement.getElementType() == debugElementType) {
            return debugElement;
        }
        return null;
    }

    public static /* synthetic */ boolean p(InstructionList instructionList, DebugElement debugElement) {
        return debugElement.getElementType() == DebugElementType.LINE_NUMBER && instructionList.getAtAddress(debugElement.getTargetAddress()) == null;
    }

    private DebugElementType<?> readNext(BlockReader blockReader) throws IOException {
        Block block;
        DebugElementType<?> flag = DebugElementType.readFlag(blockReader);
        if (flag == DebugElementType.END_SEQUENCE) {
            block = DebugEndSequence.INSTANCE;
        } else {
            Block blockNewInstance = flag.newInstance();
            unlockElementList().add(blockNewInstance);
            block = blockNewInstance;
        }
        block.readBytes(blockReader);
        return flag;
    }

    private boolean removeInternal(DebugElement debugElement) {
        debugElement.onPreRemove(this);
        boolean zRemove = getElementList().remove(debugElement);
        if (zRemove) {
            debugElement.setParent((Block) null);
            debugElement.setIndex(-1);
        }
        return zRemove;
    }

    private BlockList<DebugElement> unlockElementList() {
        BlockList<DebugElement> blockList = this.elementList;
        if (blockList != null && !BlockList.isImmutableEmpty(blockList)) {
            return blockList;
        }
        BlockList<DebugElement> blockList2 = new BlockList<>();
        this.elementList = blockList2;
        addChild(0, blockList2);
        return blockList2;
    }

    private void updateValues() {
        Iterator<DebugElement> itClonedIterator = clonedIterator();
        DebugElement debugElement = null;
        while (itClonedIterator.hasNext()) {
            DebugElement next = itClonedIterator.next();
            next.updateValues(this, debugElement);
            debugElement = next;
        }
    }

    public boolean add(DebugElement debugElement) {
        if (debugElement == null || debugElement.getClass() == DebugEndSequence.class) {
            return false;
        }
        return unlockElementList().add(debugElement);
    }

    public void clear() {
        getElementList().clearChildes();
    }

    public Iterator<DebugElement> clonedIterator() {
        return getElementList().clonedIterator();
    }

    public <T1 extends DebugElement> T1 createAtPosition(DebugElementType<T1> debugElementType, int i) {
        T1 t1 = (T1) debugElementType.newInstance();
        add(i, t1);
        return t1;
    }

    public <T1 extends DebugElement> T1 createNext(DebugElementType<T1> debugElementType) {
        if (debugElementType == DebugElementType.END_SEQUENCE) {
            return DebugEndSequence.INSTANCE;
        }
        T1 t1 = (T1) debugElementType.newInstance();
        add(t1);
        return t1;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return getElementList().equals(((DebugSequence) obj).getElementList());
    }

    public void fixDebugLineNumbers() {
        removeAll(new Predicate<DebugElement>() { // from class: com.reandroid.dex.debug.DebugSequence.1
            DebugElement previous = null;

            @Override // java.util.function.Predicate
            public boolean test(DebugElement debugElement) {
                if (debugElement.getElementType() != DebugElementType.LINE_NUMBER) {
                    return false;
                }
                DebugElement debugElement2 = this.previous;
                if (debugElement2 == null || debugElement2.getTargetAddress() != debugElement.getTargetAddress()) {
                    this.previous = debugElement;
                    return false;
                }
                this.previous = debugElement;
                return true;
            }
        });
    }

    public void fromSmali(SmaliCodeSet smaliCodeSet) {
        Iterator<SmaliDebugElement> debugElements = smaliCodeSet.getDebugElements();
        while (debugElements.hasNext()) {
            SmaliDebugElement next = debugElements.next();
            createNext(next.getDebugElementType()).fromSmali(next);
        }
    }

    public DebugElement get(int i) {
        return getElementList().get(i);
    }

    public Iterator<DebugElement> getAtAddress(final int i) {
        return FilterIterator.of(iterator(), new Predicate() { // from class: kc3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DebugSequence.b(i, (DebugElement) obj);
            }
        });
    }

    public Iterator<DebugElement> getExtraLines() {
        return new FilterIterator(iterator(), new Predicate() { // from class: lc3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DebugSequence.k((DebugElement) obj);
            }
        });
    }

    public int getLineStart() {
        return this.lineStart.get();
    }

    public <T1 extends DebugElement> T1 getOrCreateAtAddress(DebugElementType<T1> debugElementType, int i) {
        Iterator<T1> it = iterator(debugElementType);
        Block block = null;
        while (it.hasNext()) {
            Block block2 = (T1) it.next();
            int targetAddress = block2.getTargetAddress();
            if (targetAddress == i) {
                return block2;
            }
            if (targetAddress > i) {
                break;
            }
            block = block2;
        }
        T1 t1 = (T1) createAtPosition(debugElementType, block != null ? block.getIndex() + 1 : 0);
        t1.setTargetAddress(i);
        return t1;
    }

    public int hashCode() {
        return getElementList().hashCode();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean isRemoved() {
        DebugInfo debugInfo = (DebugInfo) getParentInstance(DebugInfo.class);
        if (debugInfo != null) {
            return debugInfo.isRemoved();
        }
        return true;
    }

    public <T1 extends DebugElement> Iterator<T1> iterator(final DebugElementType<T1> debugElementType) {
        return ComputeIterator.of(iterator(), new Function() { // from class: mc3
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return DebugSequence.o(debugElementType, (DebugElement) obj);
            }
        });
    }

    public void merge(DebugSequence debugSequence) {
        this.lineStart.set(debugSequence.lineStart.get());
        int size = debugSequence.size();
        if (size == 0) {
            return;
        }
        unlockElementList().ensureCapacity(size);
        for (int i = 0; i < size; i++) {
            DebugElement debugElement = debugSequence.get(i);
            createNext(debugElement.getElementType()).merge(debugElement);
        }
        cacheValues();
        getElementList().trimToSize();
    }

    @Override // com.reandroid.dex.base.FixedDexContainer
    public void onReadBytes(BlockReader blockReader) throws IOException {
        int position = blockReader.getPosition();
        int i = 0;
        while (blockReader.read() != 0) {
            i++;
        }
        if (i == 0) {
            return;
        }
        blockReader.seek(position);
        BlockList<DebugElement> blockListUnlockElementList = unlockElementList();
        unlockElementList().ensureCapacity(i);
        DebugElementType<?> next = readNext(blockReader);
        while (!next.is(DebugElementType.END_SEQUENCE)) {
            next = readNext(blockReader);
        }
        blockListUnlockElementList.trimToSize();
        cacheValues();
    }

    public boolean remove(DebugElement debugElement) {
        if (debugElement == null || debugElement.getParent(getClass()) != this) {
            return false;
        }
        boolean zRemoveInternal = removeInternal(debugElement);
        if (zRemoveInternal) {
            updateValues();
        }
        return zRemoveInternal;
    }

    public boolean removeAll(Predicate<? super DebugElement> predicate) {
        Iterator itOf = FilterIterator.of(clonedIterator(), predicate);
        boolean z = false;
        while (itOf.hasNext()) {
            if (removeInternal((DebugElement) itOf.next())) {
                z = true;
            }
        }
        if (z) {
            updateValues();
        }
        return z;
    }

    public void removeInvalid() {
        for (int size = size() - 1; size >= 0; size--) {
            DebugElement debugElement = get(size);
            if (!debugElement.isValid()) {
                remove(debugElement);
            }
        }
    }

    public void setLineStart(int i) {
        if (i == getLineStart()) {
            return;
        }
        setLineStartInternal(i);
        cacheValues();
    }

    public void setLineStartInternal(int i) {
        this.lineStart.set(i);
    }

    public int size() {
        return getElementList().getCount();
    }

    public String toString() {
        return "start=" + this.lineStart + ", elements=" + getElementList();
    }

    public Iterator<IdItem> usedIds() {
        return new IterableIterator<DebugElement, IdItem>(iterator()) { // from class: com.reandroid.dex.debug.DebugSequence.2
            public Iterator<IdItem> iterator(DebugElement debugElement) {
                return debugElement.usedIds();
            }
        };
    }

    @Override // java.lang.Iterable
    public Iterator<DebugElement> iterator() {
        return getElementList().iterator();
    }

    public void add(int i, DebugElement debugElement) {
        unlockElementList().add(i, debugElement);
    }

    public boolean removeInvalid(final InstructionList instructionList) {
        return removeAll(new Predicate() { // from class: nc3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return DebugSequence.p(instructionList, (DebugElement) obj);
            }
        });
    }
}
