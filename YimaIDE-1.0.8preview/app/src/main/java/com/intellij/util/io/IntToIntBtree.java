package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import com.intellij.util.BitUtil;
import com.intellij.util.ObjectUtils;
import com.intellij.util.SystemProperties;
import com.intellij.util.io.stats.BTreeStatistics;
import it.unimi.dsi.fastutil.ints.Int2IntMap;
import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
import java.io.Closeable;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.function.IntUnaryOperator;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class IntToIntBtree extends AbstractIntToIntBtree {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final boolean CACHE_ROOT_NODE_BUFFER = SystemProperties.getBooleanProperty("idea.btree.cache.root.node.buffer", true);
    private int count;
    private boolean hasZeroKey;
    private final int hashPageCapacity;
    private int hashSearchRequests;
    private int hashedPagesCount;
    private int height;
    private final short maxInteriorNodes;
    private final short maxLeafNodes;
    private final short maxLeafNodesInHash;
    private int maxStepsSearchedInHash;
    private final int metaDataLeafPageLength;
    private int movedMembersCount;
    private BtreeIndexNodeView myAccessNodeView;
    private boolean myCanUseLastKey;
    private int myLastGetKey;
    private int myOptimizedInserts;
    final int pageSize;
    private int pagesCount;
    final BtreeRootNode root;
    private final ResizeableMappedFile storage;
    private int totalHashStepsSearched;
    private int zeroKeyValue;

    public final class BtreeRootNode {
        int address;
        boolean initialized;
        final BtreeIndexNodeView nodeView;

        private BtreeRootNode() {
            this.nodeView = IntToIntBtree.this.new BtreeIndexNodeView(false);
        }

        public BtreeIndexNodeView getNodeView() throws IOException {
            if (!this.initialized) {
                syncWithStore();
            }
            return this.nodeView;
        }

        public void setAddress(int i) {
            this.address = i;
            this.initialized = false;
        }

        public void syncWithStore() throws IOException {
            this.nodeView.setAddress(this.address);
            this.initialized = true;
        }
    }

    @FunctionalInterface
    public interface NodeOp<T> {
        T operate(DirectBufferWrapper directBufferWrapper) throws IOException;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        switch (i) {
            case 1:
                objArr[0] = "storageLockContext";
                break;
            case 2:
                objArr[0] = "storage";
                break;
            case 3:
                objArr[0] = "result";
                break;
            case 4:
            case 5:
                objArr[0] = "processor";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "node";
                break;
            default:
                objArr[0] = "file";
                break;
        }
        objArr[1] = "com/intellij/util/io/IntToIntBtree";
        if (i == 2) {
            objArr[2] = "persistVars";
        } else if (i == 3) {
            objArr[2] = "get";
        } else if (i == 4) {
            objArr[2] = "processMappings";
        } else if (i == 5 || i == 6) {
            objArr[2] = "processLeafPages";
        } else {
            objArr[2] = "<init>";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public IntToIntBtree(int i, Path path, StorageLockContext storageLockContext, boolean z) throws IOException {
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        if (storageLockContext == null) {
            $$$reportNull$$$0(1);
        }
        this.pageSize = i;
        if (z) {
            Files.deleteIfExists(path);
        }
        ResizeableMappedFile resizeableMappedFile = new ResizeableMappedFile(path, i, storageLockContext, SystemProperties.getIntProperty("idea.IntToIntBtree.page.size", IOUtil.MiB), true, IOUtil.useNativeByteOrderForByteBuffers());
        this.storage = resizeableMappedFile;
        resizeableMappedFile.setRoundFactor(i);
        BtreeRootNode btreeRootNode = new BtreeRootNode();
        this.root = btreeRootNode;
        if (z) {
            btreeRootNode.setAddress(-1);
        }
        int i2 = (i - 8) / 8;
        short s = (short) (i2 - 1);
        this.maxInteriorNodes = s;
        this.maxLeafNodes = s;
        while (!isPrime(i2)) {
            i2 -= 2;
        }
        this.hashPageCapacity = i2;
        int i3 = (int) (((double) i2) * 0.9d);
        i3 = (i3 & 1) == 1 ? i3 + 1 : i3;
        this.metaDataLeafPageLength = 8;
        this.maxLeafNodesInHash = (short) i3;
    }

    public static /* synthetic */ int access$2104(IntToIntBtree intToIntBtree) {
        int i = intToIntBtree.hashedPagesCount + 1;
        intToIntBtree.hashedPagesCount = i;
        return i;
    }

    public static /* synthetic */ int access$2106(IntToIntBtree intToIntBtree) {
        int i = intToIntBtree.hashedPagesCount - 1;
        intToIntBtree.hashedPagesCount = i;
        return i;
    }

    public static /* synthetic */ int access$2312(IntToIntBtree intToIntBtree, int i) {
        int i2 = intToIntBtree.movedMembersCount + i;
        intToIntBtree.movedMembersCount = i2;
        return i2;
    }

    public static /* synthetic */ int access$2408(IntToIntBtree intToIntBtree) {
        int i = intToIntBtree.hashSearchRequests;
        intToIntBtree.hashSearchRequests = i + 1;
        return i;
    }

    public static /* synthetic */ int access$2612(IntToIntBtree intToIntBtree, int i) {
        int i2 = intToIntBtree.totalHashStepsSearched + i;
        intToIntBtree.totalHashStepsSearched = i2;
        return i2;
    }

    private void doAllocateRoot() throws IOException {
        nextPage();
        this.root.setAddress(0);
        this.root.getNodeView().setIndexLeaf(true);
    }

    private void doPut(int i, int i2) throws IOException {
        if (this.root.address == -1) {
            doAllocateRoot();
        }
        DirectBufferWrapper directBufferWrapperInitAccessNodeView = initAccessNodeView();
        try {
            int iLocate = this.myAccessNodeView.locate(i, true);
            if (iLocate < 0) {
                this.count++;
                this.myAccessNodeView.insert(i, i2);
            } else {
                this.myAccessNodeView.setAddressAt(iLocate, i2);
            }
        } finally {
            this.myAccessNodeView.disposeBuffer();
            if (directBufferWrapperInitAccessNodeView != null) {
                directBufferWrapperInitAccessNodeView.unlock();
            }
        }
    }

    private DirectBufferWrapper initAccessNodeView() throws IOException {
        DirectBufferWrapper directBufferWrapper;
        BtreeRootNode btreeRootNode = this.root;
        int i = btreeRootNode.address;
        if (CACHE_ROOT_NODE_BUFFER) {
            BtreeIndexNodeView nodeView = btreeRootNode.getNodeView();
            nodeView.lockBuffer();
            directBufferWrapper = nodeView.bufferWrapper;
        } else {
            directBufferWrapper = null;
        }
        BtreeIndexNodeView btreeIndexNodeView = this.myAccessNodeView;
        if (btreeIndexNodeView == null) {
            this.myAccessNodeView = new BtreeIndexNodeView(i, true, directBufferWrapper);
            return directBufferWrapper;
        }
        btreeIndexNodeView.initTraversal(i, directBufferWrapper);
        return directBufferWrapper;
    }

    private static boolean isPrime(int i) {
        if (i % 2 == 0) {
            return false;
        }
        int iSqrt = (int) Math.sqrt(i);
        for (int i2 = 3; i2 <= iSqrt; i2 += 2) {
            if (i % i2 == 0) {
                return false;
            }
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int nextPage() throws IOException {
        int length = (int) this.storage.length();
        this.storage.putInt((this.pageSize + length) - 4, 0);
        this.pagesCount++;
        return length;
    }

    private boolean processLeafPages(BtreeIndexNodeView btreeIndexNodeView, AbstractIntToIntBtree.KeyValueProcessor keyValueProcessor) throws IOException {
        if (keyValueProcessor == null) {
            $$$reportNull$$$0(5);
        }
        if (btreeIndexNodeView == null) {
            $$$reportNull$$$0(6);
        }
        if (btreeIndexNodeView.isIndexLeaf()) {
            return btreeIndexNodeView.processMappings(keyValueProcessor);
        }
        int childrenCount = btreeIndexNodeView.getChildrenCount() + 1;
        int[] iArr = new int[childrenCount];
        for (int i = 0; i < childrenCount; i++) {
            iArr[i] = -btreeIndexNodeView.addressAt(i);
        }
        if (childrenCount > 0) {
            BtreeIndexNodeView btreeIndexNodeView2 = new BtreeIndexNodeView(true);
            for (int i2 = 0; i2 < childrenCount; i2++) {
                try {
                    btreeIndexNodeView2.setAddress(iArr[i2]);
                    if (!processLeafPages(btreeIndexNodeView2, keyValueProcessor)) {
                        btreeIndexNodeView2.close();
                        return false;
                    }
                } catch (Throwable th) {
                    try {
                        btreeIndexNodeView2.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            btreeIndexNodeView2.close();
        }
        return true;
    }

    public void doClose() throws Exception {
        this.storage.close();
    }

    public void doFlush() throws IOException {
        this.storage.force();
    }

    public boolean get(int i, int[] iArr) throws IOException {
        if (iArr == null) {
            $$$reportNull$$$0(3);
        }
        if (i == 0) {
            if (!this.hasZeroKey) {
                return false;
            }
            iArr[0] = this.zeroKeyValue;
            return true;
        }
        if (this.root.address == -1) {
            return false;
        }
        DirectBufferWrapper directBufferWrapperInitAccessNodeView = initAccessNodeView();
        try {
            int iLocate = this.myAccessNodeView.locate(i, false);
            if (iLocate < 0) {
                this.myCanUseLastKey = true;
                this.myLastGetKey = i;
                return false;
            }
            this.myCanUseLastKey = false;
            iArr[0] = this.myAccessNodeView.addressAt(iLocate);
            return true;
        } finally {
            this.myAccessNodeView.disposeBuffer();
            if (directBufferWrapperInitAccessNodeView != null) {
                directBufferWrapperInitAccessNodeView.unlock();
            }
        }
    }

    public BTreeStatistics getStatistics() throws IOException {
        int i;
        int childrenCount;
        int i2 = this.height;
        if (i2 != 3) {
            if (i2 == 2) {
                childrenCount = this.pagesCount - 1;
            } else {
                i = 1;
            }
            return new BTreeStatistics(this.pagesCount, this.count, this.height, this.movedMembersCount, i, this.maxStepsSearchedInHash, this.hashSearchRequests, this.totalHashStepsSearched, this.pageSize, this.storage.length());
        }
        childrenCount = this.pagesCount - (this.root.getNodeView().getChildrenCount() + 2);
        i = childrenCount;
        return new BTreeStatistics(this.pagesCount, this.count, this.height, this.movedMembersCount, i, this.maxStepsSearchedInHash, this.hashSearchRequests, this.totalHashStepsSearched, this.pageSize, this.storage.length());
    }

    public void persistVars(AbstractIntToIntBtree.BtreeDataStorage btreeDataStorage, boolean z) throws IOException {
        if (btreeDataStorage == null) {
            $$$reportNull$$$0(2);
        }
        int iPersistInt = btreeDataStorage.persistInt(0, this.height | (this.hasZeroKey ? -16777216 : 0), z);
        this.hasZeroKey = (iPersistInt & (-16777216)) != 0;
        this.height = iPersistInt & 16777215;
        this.pagesCount = btreeDataStorage.persistInt(4, this.pagesCount, z);
        this.movedMembersCount = btreeDataStorage.persistInt(8, this.movedMembersCount, z);
        this.maxStepsSearchedInHash = btreeDataStorage.persistInt(12, this.maxStepsSearchedInHash, z);
        this.count = btreeDataStorage.persistInt(16, this.count, z);
        this.hashSearchRequests = btreeDataStorage.persistInt(20, this.hashSearchRequests, z);
        this.totalHashStepsSearched = btreeDataStorage.persistInt(24, this.totalHashStepsSearched, z);
        this.hashedPagesCount = btreeDataStorage.persistInt(28, this.hashedPagesCount, z);
        BtreeRootNode btreeRootNode = this.root;
        btreeRootNode.setAddress(btreeDataStorage.persistInt(32, btreeRootNode.address, z));
        this.zeroKeyValue = btreeDataStorage.persistInt(36, this.zeroKeyValue, z);
    }

    public boolean processMappings(AbstractIntToIntBtree.KeyValueProcessor keyValueProcessor) throws IOException {
        if (keyValueProcessor == null) {
            $$$reportNull$$$0(4);
        }
        doFlush();
        if (this.hasZeroKey && !keyValueProcessor.process(0, this.zeroKeyValue)) {
            return false;
        }
        BtreeRootNode btreeRootNode = this.root;
        if (btreeRootNode.address == -1) {
            return true;
        }
        btreeRootNode.syncWithStore();
        return processLeafPages(this.root.getNodeView(), keyValueProcessor);
    }

    public void put(int i, int i2) throws IOException {
        if (i == 0) {
            this.hasZeroKey = true;
            this.zeroKeyValue = i2;
            return;
        }
        if (this.myCanUseLastKey) {
            this.myCanUseLastKey = false;
            if (i == this.myLastGetKey && !this.myAccessNodeView.myHasFullPagesAlongPath) {
                this.myOptimizedInserts++;
                this.count++;
                try {
                    this.myAccessNodeView.insert(i, i2);
                    return;
                } finally {
                    this.myAccessNodeView.disposeBuffer();
                }
            }
        }
        doPut(i, i2);
    }

    public final class BtreeIndexNodeView implements Closeable {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private int addressInBuffer;
        private DirectBufferWrapper bufferWrapper;
        private final boolean cacheBuffer;
        private boolean isHashedLeaf;
        private boolean isIndexLeaf;
        private boolean isSharedBuffer;
        private boolean myHasFullPagesAlongPath;
        private int address = -1;
        private short myChildrenCount = -1;

        public final class HashLeafData {
            final int[] keys;
            final BtreeIndexNodeView nodeView;
            final Int2IntMap values;

            public HashLeafData(BtreeIndexNodeView btreeIndexNodeView, int i) throws IOException {
                this.nodeView = btreeIndexNodeView;
                int iIndexToOffset = btreeIndexNodeView.addressInBuffer + btreeIndexNodeView.indexToOffset(0);
                this.keys = new int[i];
                this.values = new Int2IntOpenHashMap(i);
                int[] iArr = {0};
                for (int i2 = 0; i2 < IntToIntBtree.this.hashPageCapacity; i2++) {
                    this.nodeView.lockBuffer();
                    try {
                        int i3 = this.nodeView.bufferWrapper.getInt(iIndexToOffset + 4);
                        if (i3 != 0) {
                            int i4 = this.nodeView.bufferWrapper.getInt(iIndexToOffset);
                            int i5 = iArr[0];
                            int[] iArr2 = this.keys;
                            if (i5 == iArr2.length) {
                                throw new CorruptedException(IntToIntBtree.this.storage.getPagedFileStorage().getFile());
                            }
                            iArr[0] = i5 + 1;
                            iArr2[i5] = i3;
                            this.values.put(i3, i4);
                        }
                        this.nodeView.unlockBuffer();
                        iIndexToOffset += 8;
                    } catch (Throwable th) {
                        this.nodeView.unlockBuffer();
                        throw th;
                    }
                }
                Arrays.sort(this.keys);
            }

            /* JADX INFO: Access modifiers changed from: private */
            public void clean() throws IOException {
                for (int i = 0; i < IntToIntBtree.this.hashPageCapacity; i++) {
                    this.nodeView.setKeyAt(i, 0);
                }
            }
        }

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/io/IntToIntBtree$BtreeIndexNodeView", "getStorage"));
        }

        public BtreeIndexNodeView(int i, boolean z, DirectBufferWrapper directBufferWrapper) throws IOException {
            this.cacheBuffer = z;
            initTraversal(i, directBufferWrapper);
        }

        public static /* synthetic */ Boolean a(int i, AbstractIntToIntBtree.KeyValueProcessor keyValueProcessor, DirectBufferWrapper directBufferWrapper) {
            int i2 = directBufferWrapper.getInt(i + 4);
            return (i2 == 0 || keyValueProcessor.process(i2, directBufferWrapper.getInt(i))) ? Boolean.TRUE : Boolean.FALSE;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void disposeBuffer() {
            DirectBufferWrapper directBufferWrapper = this.bufferWrapper;
            if (directBufferWrapper != null && this.cacheBuffer) {
                if (this.isSharedBuffer) {
                    this.isSharedBuffer = false;
                } else {
                    directBufferWrapper.unlock();
                }
            }
            this.bufferWrapper = null;
        }

        private void doInitFlags(int i) {
            this.myChildrenCount = (short) ((i >>> 8) & 65535);
            int i2 = (i >> 24) & PartialGapBuffer.BUF_SIZE;
            this.isHashedLeaf = BitUtil.isSet(i2, 2);
            this.isIndexLeaf = BitUtil.isSet(i2, 1);
        }

        private ByteBuffer getBytes(int i, int i2) throws IOException {
            lockBuffer();
            try {
                ByteBuffer byteBufferCopy = this.bufferWrapper.copy();
                int i3 = i + this.addressInBuffer;
                byteBufferCopy.position(i3);
                byteBufferCopy.limit(i3 + i2);
                return byteBufferCopy;
            } finally {
                unlockBuffer();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public short getChildrenCount() {
            return this.myChildrenCount;
        }

        private int getInt(int i) throws IOException {
            lockBuffer();
            try {
                return this.bufferWrapper.getInt(this.addressInBuffer + i);
            } finally {
                unlockBuffer();
            }
        }

        private int getNextPage() throws IOException {
            lockBuffer();
            try {
                return getInt(4);
            } finally {
                unlockBuffer();
            }
        }

        private PagedFileStorage getStorage() {
            PagedFileStorage pagedFileStorage = IntToIntBtree.this.storage.getPagedFileStorage();
            if (pagedFileStorage == null) {
                $$$reportNull$$$0(0);
            }
            return pagedFileStorage;
        }

        private int hashIndex(int i) throws IOException {
            int i2 = IntToIntBtree.this.hashPageCapacity;
            int i3 = Integer.MAX_VALUE & i;
            int i4 = i3 % i2;
            int iKeyAt = keyAt(i4);
            IntToIntBtree.access$2408(IntToIntBtree.this);
            int i5 = 0;
            if (iKeyAt != i && iKeyAt != 0) {
                int i6 = (i3 % (i2 - 2)) + 1;
                do {
                    i4 -= i6;
                    if (i4 < 0) {
                        i4 += i2;
                    }
                    iKeyAt = keyAt(i4);
                    i5++;
                    if (i5 > i2) {
                        throw new CorruptedException(IntToIntBtree.this.storage.getPagedFileStorage().getFile());
                    }
                    if (iKeyAt == i) {
                        break;
                    }
                } while (iKeyAt != 0);
            }
            IntToIntBtree intToIntBtree = IntToIntBtree.this;
            intToIntBtree.maxStepsSearchedInHash = Math.max(intToIntBtree.maxStepsSearchedInHash, i5);
            IntToIntBtree.access$2612(IntToIntBtree.this, i5);
            return iKeyAt == 0 ? (-i4) - 1 : i4;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int indexToOffset(int i) {
            return (i * 8) + (isHashedLeaf() ? IntToIntBtree.this.metaDataLeafPageLength : 8);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void insert(int i, int i2) throws IOException {
            short childrenCount = getChildrenCount();
            boolean zIsIndexLeaf = isIndexLeaf();
            if (zIsIndexLeaf) {
                if (childrenCount == 0) {
                    setHashedLeaf();
                    IntToIntBtree.access$2104(IntToIntBtree.this);
                }
                if (isHashedLeaf()) {
                    int iHashIndex = hashIndex(i);
                    if (iHashIndex < 0) {
                        iHashIndex = (-iHashIndex) - 1;
                    }
                    setKeyAt(iHashIndex, i);
                    setAddressAt(iHashIndex, i2);
                    setChildrenCount((short) (childrenCount + 1));
                    return;
                }
            }
            int i3 = -search(i);
            int i4 = i3 - 1;
            int i5 = childrenCount + 1;
            setChildrenCount((short) i5);
            int i6 = childrenCount - i4;
            IntToIntBtree.access$2312(IntToIntBtree.this, i6);
            if (zIsIndexLeaf) {
                if (i6 > 5) {
                    putBytes(indexToOffset(i3), getBytes(indexToOffset(i4), i6 * 8));
                } else {
                    for (int i7 = childrenCount - 1; i7 >= i4; i7--) {
                        int i8 = i7 + 1;
                        setKeyAt(i8, keyAt(i7));
                        setAddressAt(i8, addressAt(i7));
                    }
                }
                setKeyAt(i4, i);
                setAddressAt(i4, i2);
                return;
            }
            setAddressAt(i5, addressAt(childrenCount));
            if (i6 > 5) {
                int i9 = i6 - 1;
                if (i9 > 0) {
                    putBytes(indexToOffset(i3 + 1), getBytes(indexToOffset(i3), i9 * 8));
                }
            } else {
                for (int i10 = childrenCount - 1; i10 > i4; i10--) {
                    int i11 = i10 + 1;
                    setKeyAt(i11, keyAt(i10));
                    setAddressAt(i11, addressAt(i10));
                }
            }
            if (i4 < childrenCount) {
                setKeyAt(i3, keyAt(i4));
            }
            setKeyAt(i4, i);
            setAddressAt(i3, i2);
        }

        private boolean isHashedLeaf() {
            return this.isHashedLeaf;
        }

        private int keyAt(int i) {
            try {
                return getInt(indexToOffset(i) + 4);
            } catch (IOException e) {
                rc6.a(e);
                return 0;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public int locate(int i, boolean z) throws Throwable {
            int i2 = IntToIntBtree.this.height + 1;
            int i3 = 0;
            int i4 = 0;
            while (true) {
                if (isFull()) {
                    if (z) {
                        int iSplitNode = splitNode(i3);
                        if (iSplitNode != 0) {
                            setAddress(iSplitNode);
                        }
                        i4--;
                    } else {
                        this.myHasFullPagesAlongPath = true;
                    }
                }
                int iSearch = search(i);
                i4++;
                if (i4 > i2) {
                    throw new CorruptedException(IntToIntBtree.this.storage.getPagedFileStorage().getFile());
                }
                if (isIndexLeaf()) {
                    IntToIntBtree intToIntBtree = IntToIntBtree.this;
                    intToIntBtree.height = Math.max(intToIntBtree.height, i4);
                    return iSearch;
                }
                int iAddressAt = addressAt(iSearch < 0 ? (-iSearch) - 1 : iSearch + 1);
                int i5 = this.address;
                setAddress(-iAddressAt);
                i3 = i5;
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void lockBuffer() throws IOException {
            if (this.isSharedBuffer) {
                return;
            }
            DirectBufferWrapper directBufferWrapper = this.bufferWrapper;
            if (directBufferWrapper == null) {
                this.bufferWrapper = getStorage().getByteBuffer(this.address, false);
            } else {
                if (this.cacheBuffer || directBufferWrapper.tryLock()) {
                    return;
                }
                this.bufferWrapper = getStorage().getByteBuffer(this.address, false);
            }
        }

        private void putBytes(int i, ByteBuffer byteBuffer) throws IOException {
            lockBuffer();
            try {
                this.bufferWrapper.position(i + this.addressInBuffer);
                this.bufferWrapper.put(byteBuffer);
            } finally {
                unlockBuffer();
            }
        }

        private void putInt(int i, int i2) throws IOException {
            lockBuffer();
            try {
                this.bufferWrapper.putInt(this.addressInBuffer + i, i2);
            } finally {
                unlockBuffer();
            }
        }

        private int search(final int i) throws IOException {
            return (isIndexLeaf() && isHashedLeaf()) ? hashIndex(i) : ObjectUtils.binarySearch(0, getChildrenCount(), new IntUnaryOperator() { // from class: com.intellij.util.io.e
                @Override // java.util.function.IntUnaryOperator
                public final int applyAsInt(int i2) {
                    return Integer.compare(this.b.keyAt(i2), i);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setAddressAt(int i, int i2) throws IOException {
            putInt(indexToOffset(i), i2);
        }

        private void setChildrenCount(short s) throws IOException {
            this.myChildrenCount = s;
            lockBuffer();
            try {
                this.bufferWrapper.putInt(this.addressInBuffer, (s << 8) | (this.bufferWrapper.getInt(this.addressInBuffer) & (-16777216)));
            } finally {
                unlockBuffer();
            }
        }

        private void setFlag(int i, boolean z) throws IOException {
            int i2 = i << 24;
            lockBuffer();
            try {
                int i3 = this.bufferWrapper.getInt(this.addressInBuffer);
                this.bufferWrapper.putInt(this.addressInBuffer, z ? i2 | i3 : (~i2) & i3);
            } finally {
                unlockBuffer();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setKeyAt(int i, int i2) throws IOException {
            putInt(indexToOffset(i) + 4, i2);
        }

        private void setNextPage(int i) throws IOException {
            putInt(4, i);
        }

        /* JADX WARN: Code duplicated, block: B:41:0x0108  */
        private int splitNode(int i) throws Throwable {
            BtreeIndexNodeView btreeIndexNodeView;
            int iKeyAt;
            int i2;
            boolean zIsIndexLeaf = isIndexLeaf();
            boolean zIsHashedLeaf = isHashedLeaf();
            short childrenCount = getChildrenCount();
            BtreeIndexNodeView btreeIndexNodeView2 = null;
            if (i != 0) {
                try {
                    btreeIndexNodeView = IntToIntBtree.this.new BtreeIndexNodeView(i, true, null);
                } catch (Throwable th) {
                    th = th;
                    if (btreeIndexNodeView2 != null) {
                        btreeIndexNodeView2.close();
                    }
                    throw th;
                }
            } else {
                btreeIndexNodeView = null;
            }
            try {
                short maxChildrenCount = (short) (getMaxChildrenCount() / 2);
                IntToIntBtree intToIntBtree = IntToIntBtree.this;
                BtreeIndexNodeView btreeIndexNodeView3 = intToIntBtree.new BtreeIndexNodeView(intToIntBtree.nextPage(), true, null);
                try {
                    syncWithStore();
                    if (btreeIndexNodeView != null) {
                        btreeIndexNodeView.syncWithStore();
                    }
                    IntToIntBtree.this.root.syncWithStore();
                    btreeIndexNodeView3.setIndexLeaf(zIsIndexLeaf);
                    int nextPage = getNextPage();
                    setNextPage(btreeIndexNodeView3.address);
                    btreeIndexNodeView3.setNextPage(nextPage);
                    if (zIsIndexLeaf && zIsHashedLeaf) {
                        HashLeafData hashLeafData = new HashLeafData(this, childrenCount);
                        int[] iArr = hashLeafData.keys;
                        hashLeafData.clean();
                        Int2IntMap int2IntMap = hashLeafData.values;
                        int length = iArr.length / 2;
                        i2 = iArr[length];
                        IntToIntBtree.access$2106(IntToIntBtree.this);
                        setChildrenCount((short) 0);
                        btreeIndexNodeView3.setChildrenCount((short) 0);
                        for (int i3 = 0; i3 < length; i3++) {
                            int i4 = iArr[i3];
                            insert(i4, int2IntMap.get(i4));
                            int i5 = iArr[length + i3];
                            btreeIndexNodeView3.insert(i5, int2IntMap.get(i5));
                        }
                    } else {
                        short s = (short) (childrenCount - maxChildrenCount);
                        btreeIndexNodeView3.setChildrenCount(s);
                        btreeIndexNodeView3.putBytes(btreeIndexNodeView3.indexToOffset(0), getBytes(indexToOffset(maxChildrenCount), s * 8));
                        if (zIsIndexLeaf) {
                            iKeyAt = btreeIndexNodeView3.keyAt(0);
                        } else {
                            btreeIndexNodeView3.setAddressAt(s, addressAt(childrenCount));
                            maxChildrenCount = (short) (maxChildrenCount - 1);
                            iKeyAt = keyAt(maxChildrenCount);
                        }
                        setChildrenCount(maxChildrenCount);
                        i2 = iKeyAt;
                    }
                    if (btreeIndexNodeView != null) {
                        btreeIndexNodeView.insert(i2, -btreeIndexNodeView3.address);
                    } else {
                        i = IntToIntBtree.this.nextPage();
                        btreeIndexNodeView3.syncWithStore();
                        syncWithStore();
                        IntToIntBtree.this.root.setAddress(i);
                        BtreeIndexNodeView nodeView = IntToIntBtree.this.root.getNodeView();
                        nodeView.setChildrenCount((short) 1);
                        nodeView.setKeyAt(0, i2);
                        nodeView.setAddressAt(0, -this.address);
                        nodeView.setAddressAt(1, -btreeIndexNodeView3.address);
                    }
                    btreeIndexNodeView3.close();
                    if (btreeIndexNodeView != null) {
                        btreeIndexNodeView.close();
                    }
                    return i;
                } catch (Throwable th2) {
                    try {
                        btreeIndexNodeView3.close();
                    } catch (Throwable th3) {
                        th2.addSuppressed(th3);
                    }
                    throw th2;
                }
            } catch (Throwable th4) {
                th = th4;
                btreeIndexNodeView2 = btreeIndexNodeView;
                if (btreeIndexNodeView2 != null) {
                    btreeIndexNodeView2.close();
                }
                throw th;
            }
        }

        private void syncWithStore() throws IOException {
            lockBuffer();
            try {
                doInitFlags(this.bufferWrapper.getInt(this.addressInBuffer));
            } finally {
                unlockBuffer();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void unlockBuffer() {
            if (this.isSharedBuffer || this.cacheBuffer) {
                return;
            }
            this.bufferWrapper.unlock();
        }

        public int addressAt(int i) throws IOException {
            return getInt(indexToOffset(i));
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            disposeBuffer();
        }

        public short getMaxChildrenCount() {
            if (!isIndexLeaf()) {
                return IntToIntBtree.this.maxInteriorNodes;
            }
            boolean zIsHashedLeaf = isHashedLeaf();
            IntToIntBtree intToIntBtree = IntToIntBtree.this;
            return zIsHashedLeaf ? intToIntBtree.maxLeafNodesInHash : intToIntBtree.maxLeafNodes;
        }

        public void initTraversal(int i, DirectBufferWrapper directBufferWrapper) throws IOException {
            this.myHasFullPagesAlongPath = false;
            setAddress(i, directBufferWrapper);
        }

        public boolean isFull() {
            short childrenCount = getChildrenCount();
            if (!isIndexLeaf()) {
                childrenCount = (short) (childrenCount + 1);
            }
            return childrenCount == getMaxChildrenCount();
        }

        public boolean isIndexLeaf() {
            return this.isIndexLeaf;
        }

        public boolean processMappings(final AbstractIntToIntBtree.KeyValueProcessor keyValueProcessor) throws IOException {
            if (!isHashedLeaf()) {
                short childrenCount = getChildrenCount();
                for (int i = 0; i < childrenCount; i++) {
                    if (!keyValueProcessor.process(keyAt(i), addressAt(i))) {
                        return false;
                    }
                }
                return true;
            }
            final int iIndexToOffset = this.addressInBuffer + indexToOffset(0);
            for (int i2 = 0; i2 < IntToIntBtree.this.hashPageCapacity; i2++) {
                lockBuffer();
                try {
                    Boolean bool = (Boolean) new NodeOp() { // from class: com.intellij.util.io.d
                        @Override // com.intellij.util.io.IntToIntBtree.NodeOp
                        public final Object operate(DirectBufferWrapper directBufferWrapper) {
                            return IntToIntBtree.BtreeIndexNodeView.a(iIndexToOffset, keyValueProcessor, directBufferWrapper);
                        }
                    }.operate(this.bufferWrapper);
                    unlockBuffer();
                    if (!bool.booleanValue()) {
                        return false;
                    }
                    iIndexToOffset += 8;
                } catch (Throwable th) {
                    unlockBuffer();
                    throw th;
                }
            }
            return true;
        }

        public void setAddress(int i, DirectBufferWrapper directBufferWrapper) throws IOException {
            this.address = i;
            this.addressInBuffer = getStorage().getOffsetInPage(this.address);
            disposeBuffer();
            if (directBufferWrapper != null) {
                this.bufferWrapper = directBufferWrapper;
                this.isSharedBuffer = true;
            }
            syncWithStore();
        }

        public void setHashedLeaf() throws IOException {
            this.isHashedLeaf = true;
            setFlag(2, true);
        }

        public void setIndexLeaf(boolean z) throws IOException {
            this.isIndexLeaf = z;
            setFlag(1, z);
        }

        public BtreeIndexNodeView(boolean z) {
            this.cacheBuffer = z;
        }

        public void setAddress(int i) throws IOException {
            setAddress(i, null);
        }
    }
}
