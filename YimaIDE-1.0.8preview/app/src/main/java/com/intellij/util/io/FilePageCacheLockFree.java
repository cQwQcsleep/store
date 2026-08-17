package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.MathUtil;
import com.intellij.util.ThrowableRunnable;
import com.intellij.util.containers.CollectionFactory;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.io.FilePageCacheLockFree;
import com.intellij.util.io.pagecache.FilePageCacheStatistics;
import com.intellij.util.io.pagecache.impl.ConfinedIntValue;
import com.intellij.util.io.pagecache.impl.DefaultMemoryManager;
import com.intellij.util.io.pagecache.impl.FrugalQuantileEstimator;
import com.intellij.util.io.pagecache.impl.IMemoryManager;
import com.intellij.util.io.pagecache.impl.PageImpl;
import com.intellij.util.io.pagecache.impl.PagesTable;
import com.intellij.util.io.pagecache.impl.Throttler;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.ByteBuffer;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReferenceArray;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class FilePageCacheLockFree implements AutoCloseable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = Logger.getInstance(FilePageCacheLockFree.class);
    private final ConcurrentLinkedQueue<Command> commandsQueue;
    private final Object housekeeperSleepLock;
    private final Thread housekeeperThread;
    private final IMemoryManager memoryManager;
    private final CyclicIterator<PagesTable> pageTableCyclicIterator;
    private final PagesForReclaimCollector pagesForReclaimCollector;
    private final Map<Path, PagesTable> pagesPerFile;
    private final PagesToReclaim pagesToProbablyReclaim;
    private final RateController rateController;
    private final Throttler releaseMemoryOverflowThrottler;
    private volatile int state;
    private final FilePageCacheStatistics statistics;

    public static abstract class Command {
    }

    public static class CyclicIterator<T> implements Iterator<T> {
        private Collection<T> currentItems;
        private ArrayDeque<T> processedItems;
        private ArrayDeque<T> unprocessedItems;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "items", "com/intellij/util/io/FilePageCacheLockFree$CyclicIterator", "update"));
        }

        private CyclicIterator() {
            this.processedItems = new ArrayDeque<>();
            this.unprocessedItems = new ArrayDeque<>();
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            if (this.currentItems != null) {
                return (this.unprocessedItems.isEmpty() && this.processedItems.isEmpty()) ? false : true;
            }
            k2d.a(".update() must be called first");
            return false;
        }

        @Override // java.util.Iterator
        public T next() {
            boolean zHasNext = hasNext();
            ArrayDeque<T> arrayDeque = this.unprocessedItems;
            if (zHasNext) {
                if (arrayDeque.isEmpty()) {
                    this.unprocessedItems = this.processedItems;
                    this.processedItems = new ArrayDeque<>();
                }
                T tPoll = this.unprocessedItems.poll();
                this.processedItems.add(tPoll);
                return tPoll;
            }
            StringBuilder sb = new StringBuilder("Nothing to offer: unprocessed=");
            sb.append(arrayDeque);
            sb.append(", processed=");
            sb.append(this.processedItems);
            Collection<T> collection = this.currentItems;
            sb.append(", current=");
            sb.append(collection);
            throw new NoSuchElementException(sb.toString());
        }

        public int size() {
            return this.currentItems.size();
        }

        public void update(Collection<T> collection) {
            if (collection == null) {
                $$$reportNull$$$0(0);
            }
            Collection<T> collection2 = this.currentItems;
            if (collection2 != null && collection2.containsAll(collection) && this.currentItems.size() == collection.size()) {
                return;
            }
            this.currentItems = CollectionFactory.createSmallMemoryFootprintSet(collection);
            this.processedItems = new ArrayDeque<>(ContainerUtil.intersection(this.processedItems, this.currentItems));
            this.unprocessedItems = new ArrayDeque<>(ContainerUtil.subtract(this.currentItems, this.processedItems));
        }
    }

    public static class PagesToReclaim {
        private long lastRefillAtNs;
        private final AtomicInteger pagesInQueue;
        private volatile ConcurrentLinkedQueue<PageImpl> pagesToProbablyReclaimQueue;
        private final Object refillSignalLock;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "pagesCollector";
            } else {
                objArr[0] = "page";
            }
            objArr[1] = "com/intellij/util/io/FilePageCacheLockFree$PagesToReclaim";
            if (i != 1) {
                objArr[2] = "refill";
            } else {
                objArr[2] = "pushBack";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private PagesToReclaim() {
            this.pagesToProbablyReclaimQueue = new ConcurrentLinkedQueue<>();
            this.pagesInQueue = new AtomicInteger(0);
            this.refillSignalLock = new Object();
            this.lastRefillAtNs = 0L;
        }

        private void notifyAboutRefill() {
            synchronized (this.refillSignalLock) {
                this.refillSignalLock.notifyAll();
            }
        }

        public boolean isOlderThen(long j, long j2) {
            return j - j2 > this.lastRefillAtNs;
        }

        public Iterator<PageImpl> iterator() {
            final Iterator<PageImpl> it = this.pagesToProbablyReclaimQueue.iterator();
            return new Iterator<PageImpl>() { // from class: com.intellij.util.io.FilePageCacheLockFree.PagesToReclaim.1
                @Override // java.util.Iterator
                public boolean hasNext() {
                    return it.hasNext();
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // java.util.Iterator
                public PageImpl next() {
                    return (PageImpl) it.next();
                }

                @Override // java.util.Iterator
                public void remove() {
                    it.remove();
                    PagesToReclaim.this.pagesInQueue.decrementAndGet();
                }
            };
        }

        public void refill(PagesForReclaimCollector pagesForReclaimCollector) {
            if (pagesForReclaimCollector == null) {
                $$$reportNull$$$0(0);
            }
            ConcurrentLinkedQueue<PageImpl> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();
            List<PageImpl> listPagesForReclaimDirty = pagesForReclaimCollector.pagesForReclaimDirty();
            List<PageImpl> listPagesForReclaimNonDirty = pagesForReclaimCollector.pagesForReclaimNonDirty();
            concurrentLinkedQueue.addAll(listPagesForReclaimDirty);
            concurrentLinkedQueue.addAll(listPagesForReclaimNonDirty);
            this.pagesToProbablyReclaimQueue = concurrentLinkedQueue;
            this.pagesInQueue.addAndGet(listPagesForReclaimDirty.size() + listPagesForReclaimNonDirty.size());
            this.lastRefillAtNs = System.nanoTime();
            notifyAboutRefill();
        }

        public String toString() {
            return "PagesToReclaim[" + this.pagesInQueue + " in queue]";
        }
    }

    public static class PostCloseStorageCleanupCommand extends Command {
        private final CompletableFuture<?> onFinish;
        private final PagedFileStorageWithRWLockedPageContent storageToClose;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "storageToClose";
            } else {
                objArr[0] = "onFinish";
            }
            objArr[1] = "com/intellij/util/io/FilePageCacheLockFree$PostCloseStorageCleanupCommand";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public PostCloseStorageCleanupCommand(PagedFileStorageWithRWLockedPageContent pagedFileStorageWithRWLockedPageContent, CompletableFuture<Object> completableFuture) {
            if (pagedFileStorageWithRWLockedPageContent == null) {
                $$$reportNull$$$0(0);
            }
            if (completableFuture == null) {
                $$$reportNull$$$0(1);
            }
            this.storageToClose = pagedFileStorageWithRWLockedPageContent;
            this.onFinish = completableFuture;
        }
    }

    public class RateController {
        private final ConfinedIntValue safetyMarginFactor;
        private long totalPagesAllocatedTurnBefore;
        private long totalPagesWaitedTurnBefore;

        private RateController() {
            this.totalPagesAllocatedTurnBefore = 0L;
            this.totalPagesWaitedTurnBefore = 0L;
            this.safetyMarginFactor = new ConfinedIntValue(12, 12, 36);
        }

        public int predictPagesDemandForNextTurn() {
            long j = FilePageCacheLockFree.this.statistics.totalPagesAllocated();
            int i = FilePageCacheLockFree.this.statistics.totalPageAllocationsWaited();
            int intExact = Math.toIntExact(j - this.totalPagesAllocatedTurnBefore);
            long j2 = i;
            int intExact2 = Math.toIntExact(j2 - this.totalPagesWaitedTurnBefore);
            this.totalPagesAllocatedTurnBefore = j;
            this.totalPagesWaitedTurnBefore = j2;
            ConfinedIntValue confinedIntValue = this.safetyMarginFactor;
            if (intExact2 > 0) {
                confinedIntValue.update(confinedIntValue.value() * 2);
            } else {
                confinedIntValue.dec();
            }
            return Math.max(((intExact * this.safetyMarginFactor.value()) / 10) + 1, 1);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 8:
            case 9:
            case 10:
            case 11:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 8:
            case 9:
            case 10:
            case 11:
                i2 = 2;
                break;
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 2:
                objArr[0] = "finish";
                break;
            case 3:
                objArr[0] = "page";
                break;
            case 4:
                objArr[0] = "pagesTable";
                break;
            case 5:
                objArr[0] = "pageToReclaim";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "pageBuffer";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "pageToUnmap";
                break;
            case 8:
            case 9:
            case 10:
            case 11:
                objArr[0] = "com/intellij/util/io/FilePageCacheLockFree";
                break;
            default:
                objArr[0] = "storage";
                break;
        }
        switch (i) {
            case 8:
                objArr[1] = "entombPageAndGetPageBuffer";
                break;
            case 9:
            case 10:
            case 11:
                objArr[1] = "allocatePageBuffer";
                break;
            default:
                objArr[1] = "com/intellij/util/io/FilePageCacheLockFree";
                break;
        }
        switch (i) {
            case 1:
            case 2:
                objArr[2] = "enqueueStoragePagesClosing";
                break;
            case 3:
                objArr[2] = "adjustPageUsefulness";
                break;
            case 4:
                objArr[2] = "tryToReclaimAll";
                break;
            case 5:
                objArr[2] = "unmapPageAndReclaimBuffer";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "reclaimPageBuffer";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "entombPageAndGetPageBuffer";
                break;
            case 8:
            case 9:
            case 10:
            case 11:
                break;
            default:
                objArr[2] = "registerStorage";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 8:
            case 9:
            case 10:
            case 11:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public FilePageCacheLockFree(long j, long j2, ThreadFactory threadFactory) {
        this.pagesPerFile = CollectionFactory.createSmallMemoryFootprintMap();
        this.pageTableCyclicIterator = new CyclicIterator<>();
        this.pagesToProbablyReclaim = new PagesToReclaim();
        this.pagesForReclaimCollector = new PagesForReclaimCollector(10, 20);
        this.rateController = new RateController();
        this.commandsQueue = new ConcurrentLinkedQueue<>();
        this.housekeeperSleepLock = new Object();
        this.state = 0;
        FilePageCacheStatistics filePageCacheStatistics = new FilePageCacheStatistics();
        this.statistics = filePageCacheStatistics;
        this.releaseMemoryOverflowThrottler = new Throttler(100L, TimeUnit.MILLISECONDS);
        this.memoryManager = new DefaultMemoryManager(j, j2, filePageCacheStatistics);
        Thread threadNewThread = threadFactory.newThread(new Runnable() { // from class: mq4
            @Override // java.lang.Runnable
            public final void run() {
                this.b.cacheMaintenanceLoop();
            }
        });
        this.housekeeperThread = threadNewThread;
        threadNewThread.setDaemon(true);
        this.state = 1;
    }

    public static /* synthetic */ Thread a(Runnable runnable) {
        return new Thread(runnable, "FilePageCache housekeeper");
    }

    private static int adjustPageUsefulness(PageImpl pageImpl) {
        if (pageImpl == null) {
            $$$reportNull$$$0(3);
        }
        int iUsageCount = pageImpl.usageCount();
        return iUsageCount > 0 ? pageImpl.addTokensOfUsefulness(iUsageCount * 8) : pageImpl.decayTokensOfUsefulness(7, 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cacheMaintenanceLoop() {
        boolean z;
        while (!Thread.interrupted()) {
            long jNanoTime = System.nanoTime();
            try {
                int i = 0;
                boolean zRunThrottled = true;
                if (this.commandsQueue.isEmpty()) {
                    z = false;
                } else {
                    this.statistics.closedStoragesReclaimed(cleanClosedStoragesAndReclaimPages(1));
                    z = true;
                }
                Iterator it = this.pagesToProbablyReclaim.pagesToProbablyReclaimQueue.iterator();
                while (it.hasNext()) {
                    if (this.pagesForReclaimCollector.isGoodForReclaim((PageImpl) it.next())) {
                        i++;
                    } else {
                        it.remove();
                    }
                }
                int iPredictPagesDemandForNextTurn = this.rateController.predictPagesDemandForNextTurn();
                boolean zIsPageDeficitLikely = isPageDeficitLikely(i, iPredictPagesDemandForNextTurn);
                if (zIsPageDeficitLikely || this.pagesToProbablyReclaim.isOlderThen(jNanoTime, 500000000L)) {
                    refillPagesForReclaim(iPredictPagesDemandForNextTurn);
                    this.pagesForReclaimCollector.ensureEnoughCleanPagesToReclaim(0.5d);
                    i = this.pagesForReclaimCollector.totalPagesPreparedToReclaim();
                    zIsPageDeficitLikely = isPageDeficitLikely(i, iPredictPagesDemandForNextTurn);
                } else {
                    zRunThrottled = z;
                }
                if (this.memoryManager.hasOverflow()) {
                    zRunThrottled |= this.releaseMemoryOverflowThrottler.runThrottled(jNanoTime, new ThrowableRunnable() { // from class: oq4
                        @Override // com.intellij.util.ThrowableRunnable
                        public final void run() {
                            this.a.releasePagesAllocatedAboveCapacity(10);
                        }
                    });
                }
                long jNanoTime2 = System.nanoTime() - jNanoTime;
                FilePageCacheStatistics filePageCacheStatistics = this.statistics;
                if (zRunThrottled) {
                    filePageCacheStatistics.cacheMaintenanceTurnDone(jNanoTime2);
                } else {
                    filePageCacheStatistics.cacheMaintenanceTurnSkipped(jNanoTime2);
                }
                if (!zIsPageDeficitLikely) {
                    this.pagesForReclaimCollector.collectLessAggressively();
                    synchronized (this.housekeeperSleepLock) {
                        try {
                            this.housekeeperSleepLock.wait(1L);
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } else if (i > 0) {
                    Thread.yield();
                } else {
                    this.pagesForReclaimCollector.collectMoreAggressively();
                }
            } catch (InterruptedException unused) {
            } catch (Throwable th2) {
                LOG.error("Exception in FilePageCache housekeeper thread (thread continue to run)", th2);
            }
        }
        LOG.info("maintenance loop interrupted -> exiting");
    }

    private void checkNotClosed() throws IllegalStateException {
        if (this.state != 3) {
            return;
        }
        k2d.a("Cache is already closed");
    }

    private int cleanClosedStoragesAndReclaimPages(int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            Command commandPoll = this.commandsQueue.poll();
            if (commandPoll == null) {
                break;
            }
            if (commandPoll instanceof PostCloseStorageCleanupCommand) {
                PostCloseStorageCleanupCommand postCloseStorageCleanupCommand = (PostCloseStorageCleanupCommand) commandPoll;
                PagedFileStorageWithRWLockedPageContent pagedFileStorageWithRWLockedPageContent = postCloseStorageCleanupCommand.storageToClose;
                CompletableFuture completableFuture = postCloseStorageCleanupCommand.onFinish;
                if (!pagedFileStorageWithRWLockedPageContent.isClosed()) {
                    AssertionError assertionError = new AssertionError("Code bug: storage " + pagedFileStorageWithRWLockedPageContent + " must be closed before PostCloseStorageCleanupCommand is queued");
                    completableFuture.completeExceptionally(assertionError);
                    throw assertionError;
                }
                if (tryToReclaimAll(pagedFileStorageWithRWLockedPageContent.pages())) {
                    this.commandsQueue.offer(commandPoll);
                } else {
                    Path file = pagedFileStorageWithRWLockedPageContent.getFile();
                    try {
                        PageCacheUtils.CHANNELS_CACHE.closeChannel(file);
                    } catch (Throwable th) {
                        LOG.error("Can't close channel for " + file, th);
                        completableFuture.completeExceptionally(th);
                    }
                    i2++;
                    synchronized (this.pagesPerFile) {
                        this.pagesPerFile.remove(pagedFileStorageWithRWLockedPageContent.getFile().toAbsolutePath());
                    }
                    completableFuture.complete(null);
                }
            }
        }
        return i2;
    }

    private static ByteBuffer entombPageAndGetPageBuffer(PageImpl pageImpl) {
        if (pageImpl == null) {
            $$$reportNull$$$0(7);
        }
        if (!pageImpl.isPreTombstone()) {
            s22.a("Bug: page must be PRE_TOMBSTONE: ", pageImpl);
            return null;
        }
        if (pageImpl.isDirty()) {
            try {
                pageImpl.flush();
            } catch (IOException e) {
                throw new UncheckedIOException("Can't flush page: " + pageImpl, e);
            }
        }
        ByteBuffer byteBufferDetachTombstoneBuffer = pageImpl.detachTombstoneBuffer();
        pageImpl.entomb();
        if (byteBufferDetachTombstoneBuffer == null) {
            $$$reportNull$$$0(8);
        }
        return byteBufferDetachTombstoneBuffer;
    }

    private boolean isPageDeficitLikely(int i, int i2) {
        return i2 > i && ((this.memoryManager.nativeBytesUsed() > ((this.memoryManager.nativeCapacityBytes() * 4) / 5) ? 1 : (this.memoryManager.nativeBytesUsed() == ((this.memoryManager.nativeCapacityBytes() * 4) / 5) ? 0 : -1)) > 0);
    }

    private void refillPagesForReclaim(int i) {
        CyclicIterator<PagesTable> cyclicIteratorThreadSafeCopyOfPagesTables = threadSafeCopyOfPagesTables();
        this.pagesForReclaimCollector.startCollectingTurn(i, Math.max(this.statistics.totalPagesAllocated() - this.statistics.totalPagesReclaimed(), 0));
        try {
            int size = cyclicIteratorThreadSafeCopyOfPagesTables.size();
            for (int i2 = 0; i2 < size; i2++) {
                PagesTable next = cyclicIteratorThreadSafeCopyOfPagesTables.next();
                AtomicReferenceArray<PageImpl> atomicReferenceArrayPages = next.pages();
                int length = atomicReferenceArrayPages.length();
                int i3 = 0;
                for (int i4 = 0; i4 < length; i4++) {
                    PageImpl pageImpl = atomicReferenceArrayPages.get(i4);
                    if (pageImpl != null && !pageImpl.isTombstone()) {
                        if (pageImpl.isAboutToUnmap() && pageImpl.usageCount() == 0 && pageImpl.tryMoveTowardsPreTombstone(false)) {
                            unmapPageAndReclaimBuffer(pageImpl);
                        } else {
                            i3++;
                            pageImpl.updateLocalTokensOfUsefulness(adjustPageUsefulness(pageImpl));
                            this.pagesForReclaimCollector.takePageIfGoodForReclaim(pageImpl);
                        }
                    }
                }
                next.shrinkIfNeeded(i3);
            }
            this.pagesForReclaimCollector.finishCollectingTurn();
            this.pagesToProbablyReclaim.refill(this.pagesForReclaimCollector);
        } catch (Throwable th) {
            this.pagesForReclaimCollector.finishCollectingTurn();
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void releasePagesAllocatedAboveCapacity(int i) {
        PageImpl next;
        Iterator<PageImpl> it = this.pagesToProbablyReclaim.iterator();
        while (it.hasNext() && this.memoryManager.hasOverflow() && (next = it.next()) != null) {
            ByteBuffer byteBufferPageBufferUnchecked = next.pageBufferUnchecked();
            if (byteBufferPageBufferUnchecked != null && !byteBufferPageBufferUnchecked.isDirect() && (next.isUsable() || next.isAboutToUnmap())) {
                if (next.usageCount() == 0 && next.tryMoveTowardsPreTombstone(false)) {
                    it.remove();
                    i--;
                    unmapPageAndReclaimBuffer(next);
                    if (i == 0) {
                        return;
                    }
                }
            }
        }
    }

    private CyclicIterator<PagesTable> threadSafeCopyOfPagesTables() {
        CyclicIterator<PagesTable> cyclicIterator;
        synchronized (this.pagesPerFile) {
            this.pageTableCyclicIterator.update(this.pagesPerFile.values());
            cyclicIterator = this.pageTableCyclicIterator;
        }
        return cyclicIterator;
    }

    @Override // java.lang.AutoCloseable
    public void close() throws InterruptedException {
        synchronized (this) {
            try {
                if (this.state != 3) {
                    this.housekeeperThread.interrupt();
                    this.housekeeperThread.join();
                    this.state = 3;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public Future<?> enqueueStoragePagesClosing(PagedFileStorageWithRWLockedPageContent pagedFileStorageWithRWLockedPageContent, CompletableFuture<Object> completableFuture) {
        if (pagedFileStorageWithRWLockedPageContent == null) {
            $$$reportNull$$$0(1);
        }
        if (completableFuture == null) {
            $$$reportNull$$$0(2);
        }
        checkNotClosed();
        PostCloseStorageCleanupCommand postCloseStorageCleanupCommand = new PostCloseStorageCleanupCommand(pagedFileStorageWithRWLockedPageContent, completableFuture);
        this.commandsQueue.add(postCloseStorageCleanupCommand);
        return postCloseStorageCleanupCommand.onFinish;
    }

    public void reclaimPageBuffer(int i, ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            $$$reportNull$$$0(6);
        }
        this.memoryManager.releaseBuffer(i, byteBuffer);
    }

    public boolean tryToReclaimAll(PagesTable pagesTable) {
        if (pagesTable == null) {
            $$$reportNull$$$0(4);
        }
        pagesTable.pagesLock().lock();
        try {
            AtomicReferenceArray<PageImpl> atomicReferenceArrayPages = pagesTable.pages();
            boolean z = false;
            for (int i = 0; i < atomicReferenceArrayPages.length(); i++) {
                PageImpl pageImpl = atomicReferenceArrayPages.get(i);
                if (pageImpl != null && !pageImpl.isTombstone()) {
                    if (pageImpl.tryMoveTowardsPreTombstone(true)) {
                        if (pageImpl.pageBufferUnchecked() != null) {
                            unmapPageAndReclaimBuffer(pageImpl);
                        } else {
                            pageImpl.entomb();
                        }
                    }
                    z |= !pageImpl.isTombstone();
                }
            }
            return z;
        } finally {
            pagesTable.pagesLock().unlock();
        }
    }

    public void unmapPageAndReclaimBuffer(PageImpl pageImpl) {
        if (pageImpl == null) {
            $$$reportNull$$$0(5);
        }
        reclaimPageBuffer(pageImpl.pageSize(), entombPageAndGetPageBuffer(pageImpl));
    }

    public static class PagesForReclaimCollector {
        private static final Comparator<PageImpl> BY_USEFULNESS = Comparator.comparing(new Function() { // from class: com.intellij.util.io.c
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(((PageImpl) obj).localTokensOfUsefulness());
            }
        });
        private final FrugalQuantileEstimator lowUsefulnessThresholdEstimator;
        private int maxPagesToCollect;
        private final int maxPercentOfPagesToPrepareForReclaim;
        private final int minPercentOfPagesToPrepareForReclaim;
        private List<PageImpl> pagesForReclaimDirty;
        private List<PageImpl> pagesForReclaimNonDirty;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            objArr[0] = "page";
            objArr[1] = "com/intellij/util/io/FilePageCacheLockFree$PagesForReclaimCollector";
            if (i == 1 || i == 2) {
                objArr[2] = "isGoodForReclaim";
            } else if (i != 3) {
                objArr[2] = "takePageIfGoodForReclaim";
            } else {
                objArr[2] = "addCandidateForReclaim";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public PagesForReclaimCollector(int i, int i2) {
            List<PageImpl> list = Collections.EMPTY_LIST;
            this.pagesForReclaimNonDirty = list;
            this.pagesForReclaimDirty = list;
            if (i > i2) {
                pnd.a("minPercent(=", i, ") must be <= maxPercent(=", i2, ")");
                throw null;
            }
            this.minPercentOfPagesToPrepareForReclaim = i;
            this.maxPercentOfPagesToPrepareForReclaim = i2;
            this.lowUsefulnessThresholdEstimator = new FrugalQuantileEstimator(i, 0.5d, 0.0d);
        }

        private void addCandidateForReclaim(PageImpl pageImpl) {
            if (pageImpl == null) {
                $$$reportNull$$$0(3);
            }
            if (pageImpl.isDirty()) {
                this.pagesForReclaimDirty.add(pageImpl);
            } else {
                this.pagesForReclaimNonDirty.add(pageImpl);
            }
        }

        public void collectLessAggressively() {
            this.lowUsefulnessThresholdEstimator.updateTargetPercentile(MathUtil.clamp(this.lowUsefulnessThresholdEstimator.percentileToEstimate() - 1, this.minPercentOfPagesToPrepareForReclaim, this.maxPercentOfPagesToPrepareForReclaim));
        }

        public void collectMoreAggressively() {
            this.lowUsefulnessThresholdEstimator.updateTargetPercentile(MathUtil.clamp(this.lowUsefulnessThresholdEstimator.percentileToEstimate() + 1, this.minPercentOfPagesToPrepareForReclaim, this.maxPercentOfPagesToPrepareForReclaim));
        }

        public int ensureEnoughCleanPagesToReclaim(double d) {
            int size = this.pagesForReclaimDirty.size() - ((int) ((1.0d - d) * ((double) (this.pagesForReclaimDirty.size() + this.pagesForReclaimNonDirty.size()))));
            int i = 0;
            for (int i2 = 0; i2 < size; i2++) {
                PageImpl pageImpl = this.pagesForReclaimDirty.get(i2);
                if (!pageImpl.isTombstone()) {
                    try {
                        if (pageImpl.tryFlush()) {
                            i++;
                        }
                    } catch (IOException e) {
                        FilePageCacheLockFree.LOG.warn("Can't flush page " + pageImpl, e);
                    }
                }
            }
            return i;
        }

        public void finishCollectingTurn() {
            List<PageImpl> list = this.pagesForReclaimDirty;
            Comparator<PageImpl> comparator = BY_USEFULNESS;
            list.sort(comparator);
            this.pagesForReclaimNonDirty.sort(comparator);
        }

        public boolean hasCollectedEnough() {
            return totalPagesPreparedToReclaim() >= this.maxPagesToCollect;
        }

        public boolean isGoodForReclaim(PageImpl pageImpl, double d) {
            if (pageImpl == null) {
                $$$reportNull$$$0(2);
            }
            return pageImpl.isUsable() && pageImpl.usageCount() == 0 && ((double) pageImpl.tokensOfUsefulness()) <= d;
        }

        public List<PageImpl> pagesForReclaimDirty() {
            return this.pagesForReclaimDirty;
        }

        public List<PageImpl> pagesForReclaimNonDirty() {
            return this.pagesForReclaimNonDirty;
        }

        public void startCollectingTurn(int i, int i2) {
            if (i <= 0) {
                ty8.a("pagesToCollect(=", i, ") must be > 0");
                return;
            }
            if (i2 < 0) {
                ty8.a("totalPagesCount(=", i2, ") must be >= 0");
                return;
            }
            this.maxPagesToCollect = Math.max(i, ((i2 * this.lowUsefulnessThresholdEstimator.percentileToEstimate()) / 100) + 1);
            int iMax = Math.max(i / 2, 32);
            this.pagesForReclaimDirty = new ArrayList(iMax);
            this.pagesForReclaimNonDirty = new ArrayList(iMax);
        }

        public boolean takePageIfGoodForReclaim(PageImpl pageImpl) {
            if (pageImpl == null) {
                $$$reportNull$$$0(0);
            }
            double dUpdateEstimation = this.lowUsefulnessThresholdEstimator.updateEstimation(pageImpl.tokensOfUsefulness());
            if (hasCollectedEnough() || !isGoodForReclaim(pageImpl, dUpdateEstimation)) {
                return false;
            }
            addCandidateForReclaim(pageImpl);
            return true;
        }

        public String toString() {
            return "PagesForReclaimCollector[" + this.pagesForReclaimDirty.size() + " dirty/" + this.pagesForReclaimNonDirty.size() + " non-dirty, low usefulness <= " + this.lowUsefulnessThresholdEstimator.currentEstimation() + " (" + this.lowUsefulnessThresholdEstimator.percentileToEstimate() + "%)]";
        }

        public int totalPagesPreparedToReclaim() {
            return this.pagesForReclaimDirty.size() + this.pagesForReclaimNonDirty.size();
        }

        public boolean isGoodForReclaim(PageImpl pageImpl) {
            if (pageImpl == null) {
                $$$reportNull$$$0(1);
            }
            return isGoodForReclaim(pageImpl, this.lowUsefulnessThresholdEstimator.currentEstimation());
        }
    }

    public FilePageCacheLockFree(long j, long j2) {
        this(j, j2, new ThreadFactory() { // from class: nq4
            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return FilePageCacheLockFree.a(runnable);
            }
        });
    }
}
