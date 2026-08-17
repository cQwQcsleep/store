package com.reandroid.dex.dexopt;

import com.reandroid.arsc.container.FixedBlockContainer;
import com.reandroid.arsc.io.BlockReader;
import com.reandroid.arsc.item.BooleanReference;
import com.reandroid.arsc.item.IntegerReference;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class DeflatedBlockContainer extends FixedBlockContainer {
    private final BooleanReference is_deflated;
    private final IntegerReference sizeCompressed;
    private final IntegerReference sizeUncompressed;

    public DeflatedBlockContainer(int i, BooleanReference booleanReference, IntegerReference integerReference, IntegerReference integerReference2) {
        super(i);
        this.is_deflated = booleanReference;
        this.sizeUncompressed = integerReference;
        this.sizeCompressed = integerReference2;
    }

    private byte[] deflate(IntegerReference integerReference) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, new Deflater(1));
        int iOnWriteBytes = super/*com.reandroid.arsc.base.BlockContainer*/.onWriteBytes(deflaterOutputStream);
        if (integerReference != null) {
            integerReference.set(iOnWriteBytes);
        }
        deflaterOutputStream.close();
        byteArrayOutputStream.close();
        return byteArrayOutputStream.toByteArray();
    }

    private static byte[] readCompressed(InputStream inputStream, int i, int i2) throws IOException {
        Inflater inflater = new Inflater();
        try {
            byte[] bArr = new byte[i2];
            byte[] bArr2 = new byte[2048];
            int i3 = 0;
            int iInflate = 0;
            while (!inflater.finished() && !inflater.needsDictionary() && i3 < i) {
                int i4 = inputStream.read(bArr2);
                if (i4 < 0) {
                    throw new IOException("Invalid zip data. Stream ended after $totalBytesRead bytes. Expected " + i + " bytes");
                }
                inflater.setInput(bArr2, 0, i4);
                try {
                    iInflate += inflater.inflate(bArr, iInflate, i2 - iInflate);
                    i3 += i4;
                } catch (DataFormatException e) {
                    throw new IOException(e.getMessage());
                }
            }
            if (i3 == i) {
                if (!inflater.finished()) {
                    throw new IOException("Inflater did not finish");
                }
                inflater.end();
                return bArr;
            }
            throw new IOException("Didn't read enough bytes during decompression. expected=" + i + " actual=" + i3);
        } catch (Throwable th) {
            inflater.end();
            throw th;
        }
    }

    private void updateDeflatedBytesCount() {
        if (isDeflated()) {
            try {
                this.sizeCompressed.set(deflate(this.sizeUncompressed).length);
            } catch (IOException e) {
                rc6.a(e);
            }
        }
    }

    public int countBytes() {
        return isDeflated() ? this.sizeCompressed.get() : super/*com.reandroid.arsc.base.BlockContainer*/.countBytes();
    }

    public boolean isDeflated() {
        return this.is_deflated.get();
    }

    public void onReadBytes(BlockReader blockReader) throws IOException {
        if (isDeflated()) {
            blockReader = new BlockReader(readCompressed(blockReader, this.sizeCompressed.get(), this.sizeUncompressed.get()));
        }
        super/*com.reandroid.arsc.base.BlockContainer*/.onReadBytes(blockReader);
    }

    public void onRefreshed() {
        super.onRefreshed();
        updateDeflatedBytesCount();
    }

    public int onWriteBytes(OutputStream outputStream) throws IOException {
        if (!isDeflated()) {
            return super/*com.reandroid.arsc.base.BlockContainer*/.onWriteBytes(outputStream);
        }
        byte[] bArrDeflate = deflate(null);
        int length = bArrDeflate.length;
        outputStream.write(bArrDeflate, 0, length);
        return length;
    }
}
