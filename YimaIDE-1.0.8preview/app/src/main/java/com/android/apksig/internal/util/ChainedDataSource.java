package com.android.apksig.internal.util;

import com.android.apksig.util.DataSink;
import com.android.apksig.util.DataSource;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ChainedDataSource implements DataSource {
    private final DataSource[] mSources;
    private final long mTotalSize;

    public ChainedDataSource(DataSource... dataSourceArr) {
        this.mSources = dataSourceArr;
        this.mTotalSize = Arrays.stream(dataSourceArr).mapToLong(new ToLongFunction() { // from class: qe1
            @Override // java.util.function.ToLongFunction
            public final long applyAsLong(Object obj) {
                return ((DataSource) obj).size();
            }
        }).sum();
    }

    private Pair<Integer, Long> locateDataSource(long j) {
        int i = 0;
        long size = j;
        while (true) {
            DataSource[] dataSourceArr = this.mSources;
            if (i >= dataSourceArr.length) {
                throw new IndexOutOfBoundsException("Access is out of bound, offset: " + j + ", totalSize: " + this.mTotalSize);
            }
            if (size < dataSourceArr[i].size()) {
                return Pair.of(Integer.valueOf(i), Long.valueOf(size));
            }
            size -= this.mSources[i].size();
            i++;
        }
    }

    @Override // com.android.apksig.util.DataSource
    public void copyTo(long j, int i, ByteBuffer byteBuffer) throws IOException {
        feed(j, i, new ByteBufferSink(byteBuffer));
    }

    @Override // com.android.apksig.util.DataSource
    public void feed(long j, long j2, DataSink dataSink) throws IOException {
        DataSink dataSink2;
        if (j + j2 > this.mTotalSize) {
            jb9.a("Requested more than available");
            return;
        }
        DataSource[] dataSourceArr = this.mSources;
        int length = dataSourceArr.length;
        int i = 0;
        long size = j;
        long j3 = j2;
        while (i < length) {
            DataSource dataSource = dataSourceArr[i];
            if (size >= dataSource.size()) {
                size -= dataSource.size();
                dataSink2 = dataSink;
            } else {
                long size2 = dataSource.size() - size;
                if (size2 >= j3) {
                    dataSource.feed(size, j3, dataSink);
                    return;
                }
                dataSink2 = dataSink;
                dataSource.feed(size, size2, dataSink2);
                j3 -= size2;
                size = 0;
            }
            i++;
            dataSink = dataSink2;
        }
    }

    @Override // com.android.apksig.util.DataSource
    public ByteBuffer getByteBuffer(long j, int i) throws IOException {
        long j2 = i;
        if (j + j2 > this.mTotalSize) {
            jb9.a("Requested more than available");
            return null;
        }
        Pair<Integer, Long> pairLocateDataSource = locateDataSource(j);
        int iIntValue = pairLocateDataSource.getFirst().intValue();
        long jLongValue = pairLocateDataSource.getSecond().longValue();
        if (j2 + jLongValue <= this.mSources[iIntValue].size()) {
            return this.mSources[iIntValue].getByteBuffer(jLongValue, i);
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
        while (iIntValue < this.mSources.length && byteBufferAllocate.hasRemaining()) {
            this.mSources[iIntValue].copyTo(jLongValue, Math.toIntExact(Math.min(this.mSources[iIntValue].size() - jLongValue, byteBufferAllocate.remaining())), byteBufferAllocate);
            iIntValue++;
            jLongValue = 0;
        }
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }

    @Override // com.android.apksig.util.DataSource
    public long size() {
        return this.mTotalSize;
    }

    @Override // com.android.apksig.util.DataSource
    public DataSource slice(long j, long j2) {
        Pair<Integer, Long> pairLocateDataSource = locateDataSource(j);
        int iIntValue = pairLocateDataSource.getFirst().intValue();
        long jLongValue = pairLocateDataSource.getSecond().longValue();
        DataSource dataSource = this.mSources[iIntValue];
        if (jLongValue + j2 <= dataSource.size()) {
            return dataSource.slice(jLongValue, j2);
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(dataSource.slice(jLongValue, dataSource.size() - jLongValue));
        Pair<Integer, Long> pairLocateDataSource2 = locateDataSource((j + j2) - 1);
        int iIntValue2 = pairLocateDataSource2.getFirst().intValue();
        long jLongValue2 = pairLocateDataSource2.getSecond().longValue();
        while (true) {
            iIntValue++;
            DataSource[] dataSourceArr = this.mSources;
            if (iIntValue >= iIntValue2) {
                arrayList.add(dataSourceArr[iIntValue2].slice(0L, jLongValue2 + 1));
                return new ChainedDataSource((DataSource[]) arrayList.toArray(new DataSource[0]));
            }
            arrayList.add(dataSourceArr[iIntValue]);
        }
    }
}
