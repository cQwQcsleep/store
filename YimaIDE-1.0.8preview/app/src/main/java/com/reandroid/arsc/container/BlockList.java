package com.reandroid.arsc.container;

import com.reandroid.arsc.base.Block;
import com.reandroid.arsc.base.BlockCounter;
import com.reandroid.arsc.base.BlockLocator;
import com.reandroid.arsc.base.BlockRefresh;
import com.reandroid.arsc.base.Creator;
import com.reandroid.arsc.container.BlockList;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.json.JSONArray;
import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONItem;
import com.reandroid.utils.ObjectsUtil;
import com.reandroid.utils.collection.ArrayCollection;
import com.reandroid.utils.collection.SwapListener;
import com.reandroid.utils.collection.Swappable;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BlockList<T extends Block> extends Block implements BlockRefresh, Swappable {
    private static final BlockList<?> empty_list = new 2();
    private Creator<? extends T> mCreator;
    private ArrayCollection<T> mItems;

    public BlockList(Creator<? extends T> creator) {
        this.mItems = ArrayCollection.empty();
        this.mCreator = creator;
    }

    public static /* synthetic */ void b(BlockList blockList, int i, int i2) {
        Block block = blockList.get(i);
        Block block2 = blockList.get(i2);
        if (block != null) {
            block.setIndex(i);
        }
        if (block2 != null) {
            block2.setIndex(i2);
        }
    }

    public static <T1 extends Block> BlockList<T1> empty() {
        return (BlockList<T1>) empty_list;
    }

    public static void fromJsonArray(BlockList<? extends JSONConvert<?>> blockList, JSONArray jSONArray) {
        int length = jSONArray == null ? 0 : jSONArray.length();
        blockList.setSize(length);
        for (int i = 0; i < length; i++) {
            blockList.get(i).fromJson((JSONItem) ObjectsUtil.cast(jSONArray.get(i)));
        }
    }

    public static boolean isImmutableEmpty(Object obj) {
        return empty_list == obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Block k(BlockList blockList, Creator creator, int i) {
        blockList.getClass();
        Block blockNewInstanceAt = creator.newInstanceAt(i);
        blockList.onItemCreated(i, blockNewInstanceAt);
        return blockNewInstanceAt;
    }

    private void lockList() {
        if (this.mItems.isImmutableEmpty()) {
            return;
        }
        this.mItems = ArrayCollection.empty();
    }

    private boolean skipIndividualCounting(BlockCounter blockCounter) {
        if (size() == 0) {
            return true;
        }
        if (blockCounter instanceof BlockLocator) {
            return false;
        }
        Block block = blockCounter.END;
        if (block == null) {
            return true;
        }
        return hasSimilarEntries() && get(0).getClass() != block.getClass();
    }

    private int[] toIndexArray(Collection<?> collection) {
        Block block;
        int index;
        int size = collection.size();
        int[] iArr = new int[size];
        ArrayCollection<T> arrayCollection = this.mItems;
        int size2 = arrayCollection.size();
        int i = 0;
        for (Object obj : collection) {
            if (obj != null && (index = (block = (Block) obj).getIndex()) >= 0 && index < size2 && block == arrayCollection.get(index)) {
                iArr[i] = index;
                i++;
            }
        }
        while (i < size) {
            iArr[i] = -1;
            i++;
        }
        return iArr;
    }

    public static JSONArray toJsonArray(BlockList<? extends JSONConvert<?>> blockList) {
        int size = blockList.size();
        if (size == 0) {
            return null;
        }
        JSONArray jSONArray = new JSONArray(size);
        for (int i = 0; i < size; i++) {
            jSONArray.put(i, blockList.get(i).toJson());
        }
        return jSONArray;
    }

    private void unlockList() {
        if (this.mItems.isImmutableEmpty()) {
            this.mItems = new ArrayCollection<>();
            updateCreator();
            this.mItems.setMonitor(getMonitor());
        }
    }

    private void updateCreator() {
        final Creator<? extends T> creator = getCreator();
        if (creator == null) {
            this.mItems.setInitializer(null);
        } else {
            this.mItems.setInitializer(new ArrayCollection.Initializer() { // from class: kx0
                @Override // com.reandroid.utils.collection.ArrayCollection.Initializer
                public final Object createNewItem(int i) {
                    return BlockList.k(this.a, creator, i);
                }
            });
        }
    }

    private boolean updateIndex(int i, int i2) {
        boolean z = false;
        if (i < 0) {
            i = 0;
        }
        if (i > i2) {
            i2 = i;
            i = i2;
        }
        int i3 = i2 + 1;
        int size = size();
        if (i3 > size) {
            i3 = size;
        }
        List<T> childes = getChildes();
        while (i < i3) {
            T t = childes.get(i);
            if (t.getIndex() != i) {
                t.setIndex(i);
                z = true;
            }
            i++;
        }
        return z;
    }

    public boolean add(T t) {
        if (t == null) {
            return false;
        }
        unlockList();
        t.setIndex(size());
        t.setParent(this);
        boolean zAdd = this.mItems.add(t);
        onChanged();
        return zAdd;
    }

    public void addAll(int i, T[] tArr) {
        if (tArr == null || (tArr.length) == 0) {
            return;
        }
        unlockList();
        this.mItems.addAll(i, tArr);
        for (T t : tArr) {
            if (t != null) {
                t.setIndex(i);
                t.setParent(this);
                i++;
            }
        }
        updateIndex(i);
        onChanged();
    }

    public Iterator<T> arrayIterator() {
        return this.mItems.arrayIterator();
    }

    public void clearChildes() {
        if (this.mItems.isEmpty()) {
            return;
        }
        Object objOnRemoveRequestStarted = onRemoveRequestStarted();
        int size = size();
        for (int i = 0; i < size; i++) {
            remove(size() - 1, false);
        }
        lockList();
        onChanged();
        onRemoveRequestCompleted(objOnRemoveRequestStarted);
    }

    public Iterator<T> clonedIterator(int i) {
        return clonedIterator(i, size() - i);
    }

    public boolean contains(Object obj) {
        return this.mItems.contains(obj);
    }

    public boolean containsExact(Object obj) {
        return this.mItems.containsExact(obj);
    }

    public int countBytes() {
        int size = size();
        if (size == 0) {
            return 0;
        }
        if (hasSimilarEntries()) {
            return size * get(0).countBytes();
        }
        int iCountBytes = 0;
        for (int i = 0; i < size; i++) {
            iCountBytes += get(i).countBytes();
        }
        return iCountBytes;
    }

    public int countFromLast(Predicate<? super T> predicate) {
        return this.mItems.countFromLast(predicate);
    }

    public int countIf(Predicate<? super T> predicate) {
        return this.mItems.count(predicate);
    }

    public T createAt(int i) {
        Creator<? extends T> creator = getCreator();
        ensureSize(i);
        T t = (T) creator.newInstanceAt(i);
        add(i, t);
        return t;
    }

    public T createNext() {
        T t = (T) getCreator().newInstanceAt(size());
        add(t);
        return t;
    }

    public void destroy() {
        this.mItems.clear();
        lockList();
        onChanged();
    }

    public void ensureCapacity(int i) {
        unlockList();
        this.mItems.ensureCapacity(i);
    }

    public void ensureSize(int i) {
        if (i > size()) {
            setSize(i);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.mItems.equals(((BlockList) obj).mItems);
    }

    public T get(int i) {
        if (i >= this.mItems.size() || i < 0) {
            return null;
        }
        return this.mItems.get(i);
    }

    public byte[] getBytes() {
        Iterator<T> it = iterator();
        byte[] bArrAddBytes = null;
        while (it.hasNext()) {
            bArrAddBytes = Block.addBytes(bArrAddBytes, it.next().getBytes());
        }
        return bArrAddBytes;
    }

    public List<T> getChildes() {
        return this.mItems;
    }

    public int getCount() {
        return size();
    }

    public Creator<? extends T> getCreator() {
        return this.mCreator;
    }

    public T getFirst() {
        int size = size();
        for (int i = 0; i < size; i++) {
            T t = (T) get(i);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    public T getLast() {
        for (int size = size() - 1; size >= 0; size--) {
            T t = (T) get(size);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    public ArrayCollection.Monitor<T> getMonitor() {
        return new 1(this);
    }

    public boolean hasSimilarEntries() {
        return false;
    }

    public int hashCode() {
        return this.mItems.hashCode();
    }

    public int indexOf(T t) {
        if (t == null) {
            return -1;
        }
        int iIndexOfExact = this.mItems.indexOfExact(t, t.getIndex());
        return iIndexOfExact < 0 ? this.mItems.indexOfExact(t) : iIndexOfExact;
    }

    public Iterator<T> iterator() {
        return this.mItems.iterator();
    }

    public int lastIndexOf(T t) {
        return this.mItems.lastIndexOf(t);
    }

    public void moveTo(T t, int i) {
        if (i < 0) {
            i = 0;
        }
        int iIndexOfExact = this.mItems.indexOfExact(t, t.getIndex());
        Object objOnRemoveRequestStarted = onRemoveRequestStarted();
        this.mItems.move(t, i);
        updateIndex(iIndexOfExact, i);
        onRemoveRequestCompleted(objOnRemoveRequestStarted);
    }

    public boolean needsSort(Comparator<? super T> comparator) {
        int size;
        if (comparator == null || (size = size()) < 2) {
            return false;
        }
        Block block = get(0);
        int i = 1;
        while (i < size) {
            Block block2 = get(i);
            if (comparator.compare(block, block2) > 0) {
                return true;
            }
            i++;
            block = block2;
        }
        return false;
    }

    public void notifyPreRemove(T t) {
        if (t == null || t.getParent() != this) {
            return;
        }
        onPreRemove(t);
        t.setIndex(-1);
        t.setParent((Block) null);
    }

    public void onChanged() {
        this.mItems.onChanged();
    }

    public void onCountUpTo(BlockCounter blockCounter) {
        if (blockCounter.FOUND) {
            return;
        }
        blockCounter.setCurrent(this);
        if (blockCounter.END == this) {
            blockCounter.FOUND = true;
            return;
        }
        if (skipIndividualCounting(blockCounter)) {
            blockCounter.addCount(countBytes());
            return;
        }
        int size = size();
        for (int i = 0; i < size && !blockCounter.FOUND; i++) {
            Block block = get(i);
            if (block != null) {
                block.onCountUpTo(blockCounter);
            }
        }
    }

    public void onItemCreated(int i, T t) {
        if (t == null) {
            return;
        }
        t.setIndex(i);
        t.setParent(this);
    }

    public void onPreRefresh() {
    }

    public void onPreRemove(T t) {
    }

    public void onRefreshed() {
        onChanged();
    }

    public void onRemoveRequestCompleted(Object obj) {
    }

    public Object onRemoveRequestStarted() {
        return null;
    }

    public int onWriteBytes(OutputStream outputStream) throws IOException {
        int size = size();
        int iWriteBytes = 0;
        for (int i = 0; i < size; i++) {
            Block block = get(i);
            if (block != null) {
                iWriteBytes += block.writeBytes(outputStream);
            }
        }
        return iWriteBytes;
    }

    public void readChildes(BlockReader blockReader) throws IOException {
        int size = size();
        for (int i = 0; i < size; i++) {
            get(i).readBytes(blockReader);
        }
        onChanged();
    }

    public final void refresh() {
        if (isNull()) {
            return;
        }
        trimToSize();
        onPreRefresh();
        refreshChildes();
        onRefreshed();
        onChanged();
    }

    public void refreshChildes() {
        for (BlockRefresh blockRefresh : this) {
            if (blockRefresh instanceof BlockRefresh) {
                blockRefresh.refresh();
            }
        }
    }

    public boolean remove(T t) {
        if (t == null) {
            return false;
        }
        int iIndexOfExact = this.mItems.indexOfExact(t, t.getIndex());
        if (iIndexOfExact < 0) {
            iIndexOfExact = this.mItems.indexOfExact(t);
        }
        if (iIndexOfExact < 0) {
            return false;
        }
        Object objOnRemoveRequestStarted = onRemoveRequestStarted();
        boolean z = this.mItems.remove(iIndexOfExact) != null;
        if (z) {
            updateIndex(iIndexOfExact);
            t.setIndex(-1);
            t.setParent((Block) null);
        }
        onChanged();
        onRemoveRequestCompleted(objOnRemoveRequestStarted);
        return z;
    }

    public boolean removeAll(Collection<?> collection) {
        return removeAllIndexes(toIndexArray(collection));
    }

    public boolean removeAllIndexes(int[] iArr) {
        Object objOnRemoveRequestStarted = onRemoveRequestStarted();
        this.mItems.removeAllIndexes(iArr);
        updateIndex();
        onRemoveRequestCompleted(objOnRemoveRequestStarted);
        return true;
    }

    public boolean removeIf(Predicate<? super T> predicate) {
        Object objOnRemoveRequestStarted = onRemoveRequestStarted();
        boolean zRemoveIf = this.mItems.removeIf(predicate);
        if (zRemoveIf) {
            updateIndex();
        }
        onRemoveRequestCompleted(objOnRemoveRequestStarted);
        return zRemoveIf;
    }

    public Iterator<T> reversedIterator() {
        return this.mItems.reversedIterator();
    }

    public void set(int i, T t) {
        if (t == null) {
            return;
        }
        unlockList();
        t.setIndex(i);
        t.setParent(this);
        this.mItems.set(i, t);
        onChanged();
    }

    public void setCreator(Creator<? extends T> creator) {
        this.mCreator = creator;
        if (this.mItems.isImmutableEmpty()) {
            return;
        }
        updateCreator();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void setElements(T[] tArr) {
        if (tArr == 0 || tArr.length == 0) {
            lockList();
            return;
        }
        unlockList();
        Creator<? extends T> creator = getCreator();
        int length = tArr.length;
        for (int i = 0; i < length; i++) {
            Object objNewInstanceAt = tArr[i];
            if (objNewInstanceAt == 0 && creator != null) {
                objNewInstanceAt = creator.newInstanceAt(i);
                tArr[i] = objNewInstanceAt;
            }
            onItemCreated(i, objNewInstanceAt);
        }
        this.mItems.setElements(tArr);
        onChanged();
    }

    public void setSize(int i, boolean z) {
        if (i == 0 && !z) {
            lockList();
            return;
        }
        if (this.mCreator != null || i < size()) {
            unlockList();
            this.mItems.setSize(i, z);
            if (i == 0) {
                lockList();
            }
        }
    }

    public int size() {
        return this.mItems.size();
    }

    public boolean sort(Comparator<? super T> comparator) {
        if (size() < 2) {
            return false;
        }
        boolean zSort = this.mItems.sort(comparator, new SwapListener() { // from class: jx0
            @Override // com.reandroid.utils.collection.SwapListener
            public final void onSwap(int i, int i2) {
                BlockList.b(this.a, i, i2);
            }
        });
        if (zSort) {
            updateIndex();
        }
        return zSort;
    }

    public ArrayCollection<T> subListIf(Predicate<? super T> predicate) {
        return this.mItems.subListIf(predicate);
    }

    public boolean swap(T t, T t2) {
        if (t == t2 || t == null || t2 == null) {
            return false;
        }
        int index = t.getIndex();
        int index2 = t2.getIndex();
        this.mItems.swap(index, index2);
        t.setIndex(index2);
        t2.setIndex(index);
        return true;
    }

    public Object[] toArray() {
        return this.mItems.toArray();
    }

    public String toString() {
        return "size=" + size();
    }

    public boolean transferTo(T t, BlockList<? super T> blockList) {
        int iIndexOfExact;
        if (t == null || blockList == null || blockList == this || (iIndexOfExact = this.mItems.indexOfExact(t, t.getIndex())) < 0) {
            return false;
        }
        Object objOnRemoveRequestStarted = onRemoveRequestStarted();
        this.mItems.removeSilent(iIndexOfExact);
        boolean zAdd = blockList.add(t);
        onRemoveRequestCompleted(objOnRemoveRequestStarted);
        return zAdd;
    }

    public void trimLastIf(int i, Predicate<? super T> predicate) {
        int size = size() - 1;
        int iCountFromLast = size - this.mItems.countFromLast(i, predicate);
        while (size > iCountFromLast) {
            remove(size);
            size--;
        }
    }

    public void trimToSize() {
        this.mItems.trimToSize();
        if (this.mItems.size() == 0) {
            lockList();
        }
    }

    public Iterator<T> iterator(int i, int i2) {
        return this.mItems.iterator(i, i2);
    }

    public <T1> T1[] toArray(T1[] t1Arr) {
        return (T1[]) this.mItems.toArray(t1Arr);
    }

    public Iterator<T> iterator(Predicate<? super T> predicate) {
        return this.mItems.iterator(predicate);
    }

    public <T1> Iterator<T1> iterator(Class<T1> cls) {
        return this.mItems.iterator(cls);
    }

    public Iterator<T> clonedIterator() {
        return this.mItems.clonedIterator();
    }

    public Iterator<T> clonedIterator(int i, int i2) {
        return this.mItems.clonedIterator(i, i2);
    }

    public BlockList() {
        this(null);
    }

    public void trimLastIf(Predicate<? super T> predicate) {
        trimLastIf(0, predicate);
    }

    public boolean sort(Comparator<? super T> comparator, Swappable swappable) {
        if (size() >= 2 && this.mItems.sort(comparator, swappable)) {
            return updateIndex();
        }
        return false;
    }

    public void add(int i, T t) {
        if (t == null) {
            return;
        }
        unlockList();
        t.setIndex(i);
        t.setParent(this);
        this.mItems.add(i, t);
        updateIndex(i);
        onChanged();
    }

    @Override // com.reandroid.utils.collection.Swappable
    public boolean swap(int i, int i2) {
        if (i == i2) {
            return false;
        }
        return swap(get(i), get(i2));
    }

    public void setSize(int i) {
        setSize(i, false);
    }

    public boolean transferTo(int i, BlockList<? super T> blockList) {
        if (i < 0 || blockList == null || blockList == this) {
            return false;
        }
        Object objOnRemoveRequestStarted = onRemoveRequestStarted();
        boolean zAdd = blockList.add(this.mItems.removeSilent(i));
        onRemoveRequestCompleted(objOnRemoveRequestStarted);
        return zAdd;
    }

    private boolean updateIndex(int i) {
        return updateIndex(i, size());
    }

    private boolean updateIndex() {
        return updateIndex(0);
    }

    private T remove(int i, boolean z) {
        T tRemove = this.mItems.remove(i);
        if (tRemove == null) {
            return null;
        }
        tRemove.setParent((Block) null);
        tRemove.setIndex(-1);
        if (z) {
            updateIndex(i);
        }
        onChanged();
        return tRemove;
    }

    public T remove(int i) {
        Object objOnRemoveRequestStarted = onRemoveRequestStarted();
        T t = (T) remove(i, true);
        onRemoveRequestCompleted(objOnRemoveRequestStarted);
        return t;
    }
}
