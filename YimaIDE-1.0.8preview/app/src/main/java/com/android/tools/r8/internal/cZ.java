package com.android.tools.r8.internal;

import androidx.collection.SieveCacheKt;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class cZ extends dZ {
    public final FileChannel i;
    public MappedByteBuffer j;
    public final long k;
    public final byte[] l;
    public int m;
    public int n;

    public cZ(Path path, Predicate predicate, boolean z) throws IOException {
        super(predicate, z);
        this.l = new byte[8192];
        this.m = 0;
        this.n = 0;
        FileChannel fileChannelOpen = FileChannel.open(path, StandardOpenOption.READ);
        this.i = fileChannelOpen;
        long size = fileChannelOpen.size();
        this.k = size;
        FileChannel.MapMode mapMode = FileChannel.MapMode.READ_ONLY;
        long j = this.m;
        this.j = fileChannelOpen.map(mapMode, j, Math.min(size - j, SieveCacheKt.NodeLinkMask));
    }

    public final boolean b() {
        return this.n == 8192;
    }

    public final int c() {
        int i = this.n;
        if (i > 0) {
            int i2 = i - 1;
            if (this.l[i2] == 13) {
                return i2;
            }
        }
        return i;
    }

    public final void close() throws IOException {
        this.i.close();
    }

    public final int d() {
        return 0;
    }

    public final byte[] e() {
        if (this.m >= this.k) {
            return null;
        }
        this.n = 0;
        while (this.m < this.k) {
            if (!this.j.hasRemaining()) {
                FileChannel fileChannel = this.i;
                FileChannel.MapMode mapMode = FileChannel.MapMode.READ_ONLY;
                long j = this.m;
                this.j = fileChannel.map(mapMode, j, Math.min(this.k - j, SieveCacheKt.NodeLinkMask));
            }
            this.m++;
            byte b = this.j.get();
            if (b == 10) {
                break;
            }
            byte[] bArr = this.l;
            int i = this.n;
            int i2 = i + 1;
            this.n = i2;
            bArr[i] = b;
            if (i2 == 8192) {
                break;
            }
        }
        return this.l;
    }
}
