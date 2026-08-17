package com.intellij.util.io;

import com.intellij.openapi.util.io.FileUtilRt;
import java.io.Closeable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class OpenChannelsCache {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final Map<Path, ChannelDescriptor> myCache;
    private final transient Object myCacheLock = new Object();
    private final int myCapacity;
    private int myHitCount;
    private int myLoadCount;
    private int myMissCount;

    public static final class ChannelDescriptor implements Closeable {
        private static final OpenOption[] MODIFIABLE_OPTS;
        private static final OpenOption[] READ_ONLY_OPTS;
        private final ResilientFileChannel channel;
        private int lockCount;
        private final boolean readOnly;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "file";
            } else {
                objArr[0] = "com/intellij/util/io/OpenChannelsCache$ChannelDescriptor";
            }
            if (i != 1) {
                objArr[1] = "com/intellij/util/io/OpenChannelsCache$ChannelDescriptor";
            } else {
                objArr[1] = "channel";
            }
            if (i != 1) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        static {
            StandardOpenOption standardOpenOption = StandardOpenOption.READ;
            MODIFIABLE_OPTS = new OpenOption[]{standardOpenOption, StandardOpenOption.WRITE, StandardOpenOption.CREATE};
            READ_ONLY_OPTS = new OpenOption[]{standardOpenOption};
        }

        public ChannelDescriptor(final Path path, final boolean z) throws IOException {
            if (path == null) {
                $$$reportNull$$$0(0);
            }
            this.lockCount = 0;
            this.readOnly = z;
            ResilientFileChannel resilientFileChannel = (ResilientFileChannel) FileUtilRt.doIOOperation(new FileUtilRt.RepeatableIOOperation() { // from class: com.intellij.util.io.f
                public final Object execute(boolean z2) {
                    return OpenChannelsCache.ChannelDescriptor.a(path, z, z2);
                }
            });
            Objects.requireNonNull(resilientFileChannel);
            this.channel = resilientFileChannel;
        }

        public static /* synthetic */ ResilientFileChannel a(Path path, boolean z, boolean z2) throws IOException {
            try {
                return new ResilientFileChannel(path, z ? READ_ONLY_OPTS : MODIFIABLE_OPTS);
            } catch (NoSuchFileException e) {
                Path parent = path.getParent();
                if (!z) {
                    if (!Files.exists(parent, new LinkOption[0])) {
                        Files.createDirectories(parent, new FileAttribute[0]);
                    }
                    if (!z2) {
                        return null;
                    }
                }
                throw e;
            }
        }

        public ResilientFileChannel channel() {
            ResilientFileChannel resilientFileChannel = this.channel;
            if (resilientFileChannel == null) {
                $$$reportNull$$$0(1);
            }
            return resilientFileChannel;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            this.channel.close();
        }

        public boolean isLocked() {
            return this.lockCount != 0;
        }

        public boolean isReadOnly() {
            return this.readOnly;
        }

        public void lock() {
            this.lockCount++;
        }

        public String toString() {
            return "ChannelDescriptor{locks=" + this.lockCount + ", channel=" + this.channel + ", readOnly=" + this.readOnly + '}';
        }

        public void unlock() {
            this.lockCount--;
        }
    }

    @FunctionalInterface
    public interface FileChannelOperation<T> {
        T execute(ResilientFileChannel resilientFileChannel) throws IOException;
    }

    /* JADX WARN: Code duplicated, block: B:21:0x002c  */
    /* JADX WARN: Code duplicated, block: B:22:0x0032  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3 || i == 4) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3 || i == 4) ? 3 : 2];
        if (i == 1) {
            objArr[0] = "path";
        } else if (i == 2) {
            objArr[0] = "operation";
        } else if (i == 3) {
            objArr[0] = "path";
        } else if (i != 4) {
            objArr[0] = "com/intellij/util/io/OpenChannelsCache";
        } else {
            objArr[0] = "operation";
        }
        if (i == 1 || i == 2 || i == 3 || i == 4) {
            objArr[1] = "com/intellij/util/io/OpenChannelsCache";
        } else {
            objArr[1] = "getStatistics";
        }
        if (i == 1 || i == 2) {
            objArr[2] = "executeOp";
        } else if (i == 3 || i == 4) {
            objArr[2] = "executeIdempotentOp";
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3 && i != 4) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    public OpenChannelsCache(int i) {
        this.myCapacity = i;
        this.myCache = new LinkedHashMap(i, 0.5f, true);
    }

    private boolean releaseOverCachedChannels() throws IOException {
        int size = this.myCache.size() - this.myCapacity;
        if (size < 0) {
            return false;
        }
        ArrayList arrayList = new ArrayList();
        for (Map.Entry<Path, ChannelDescriptor> entry : this.myCache.entrySet()) {
            if (size < 0) {
                break;
            }
            if (!entry.getValue().isLocked()) {
                size--;
                arrayList.add(entry.getKey());
            }
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            closeChannel((Path) it.next());
        }
        return true;
    }

    public void closeChannel(Path path) throws IOException {
        synchronized (this.myCacheLock) {
            try {
                ChannelDescriptor channelDescriptorRemove = this.myCache.remove(path);
                if (channelDescriptorRemove != null) {
                    channelDescriptorRemove.close();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public <T> T executeIdempotentOp(Path path, FileChannelInterruptsRetryer.FileChannelIdempotentOperation<T> fileChannelIdempotentOperation, boolean z) throws IOException {
        ChannelDescriptor channelDescriptor;
        ChannelDescriptor channelDescriptor2;
        if (path == null) {
            $$$reportNull$$$0(3);
        }
        if (fileChannelIdempotentOperation == null) {
            $$$reportNull$$$0(4);
        }
        synchronized (this.myCacheLock) {
            try {
                channelDescriptor = this.myCache.get(path);
                if (channelDescriptor == null) {
                    boolean zReleaseOverCachedChannels = releaseOverCachedChannels();
                    ChannelDescriptor channelDescriptor3 = new ChannelDescriptor(path, z);
                    this.myCache.put(path, channelDescriptor3);
                    if (zReleaseOverCachedChannels) {
                        this.myMissCount++;
                    } else {
                        this.myLoadCount++;
                    }
                    channelDescriptor = channelDescriptor3;
                } else if (z || !channelDescriptor.isReadOnly()) {
                    this.myHitCount++;
                } else {
                    if (channelDescriptor.isLocked()) {
                        channelDescriptor2 = new ChannelDescriptor(path, false);
                    } else {
                        closeChannel(path);
                        channelDescriptor2 = new ChannelDescriptor(path, false);
                        this.myCache.put(path, channelDescriptor2);
                    }
                    channelDescriptor = channelDescriptor2;
                    this.myMissCount++;
                }
                channelDescriptor.lock();
            } catch (Throwable th) {
                throw th;
            }
        }
        try {
            T t = (T) channelDescriptor.channel().executeOperation(fileChannelIdempotentOperation);
            synchronized (this.myCacheLock) {
                channelDescriptor.unlock();
            }
            return t;
        } catch (Throwable th2) {
            synchronized (this.myCacheLock) {
                channelDescriptor.unlock();
                throw th2;
            }
        }
    }

    public <T> T executeOp(Path path, FileChannelOperation<T> fileChannelOperation, boolean z) throws IOException {
        ChannelDescriptor channelDescriptor;
        ChannelDescriptor channelDescriptor2;
        if (path == null) {
            $$$reportNull$$$0(1);
        }
        if (fileChannelOperation == null) {
            $$$reportNull$$$0(2);
        }
        synchronized (this.myCacheLock) {
            try {
                channelDescriptor = this.myCache.get(path);
                if (channelDescriptor == null) {
                    boolean zReleaseOverCachedChannels = releaseOverCachedChannels();
                    ChannelDescriptor channelDescriptor3 = new ChannelDescriptor(path, z);
                    this.myCache.put(path, channelDescriptor3);
                    if (zReleaseOverCachedChannels) {
                        this.myMissCount++;
                    } else {
                        this.myLoadCount++;
                    }
                    channelDescriptor = channelDescriptor3;
                } else if (z || !channelDescriptor.isReadOnly()) {
                    this.myHitCount++;
                } else {
                    if (channelDescriptor.isLocked()) {
                        channelDescriptor2 = new ChannelDescriptor(path, false);
                    } else {
                        closeChannel(path);
                        channelDescriptor2 = new ChannelDescriptor(path, false);
                        this.myCache.put(path, channelDescriptor2);
                    }
                    channelDescriptor = channelDescriptor2;
                    this.myMissCount++;
                }
                channelDescriptor.lock();
            } catch (Throwable th) {
                throw th;
            }
        }
        try {
            T tExecute = fileChannelOperation.execute(channelDescriptor.channel());
            synchronized (this.myCacheLock) {
                channelDescriptor.unlock();
            }
            return tExecute;
        } catch (Throwable th2) {
            synchronized (this.myCacheLock) {
                channelDescriptor.unlock();
                throw th2;
            }
        }
    }
}
