package com.reandroid.dex.io;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class StreamUtil {

    public static class ByteArrayReader implements ByteReader {
        private final byte[] bytes;
        private int index;
        private final int offset;

        public ByteArrayReader(byte[] bArr, int i) {
            this.bytes = bArr;
            this.offset = i;
        }

        @Override // com.reandroid.dex.io.ByteReader
        public int count() {
            return this.index;
        }

        @Override // com.reandroid.dex.io.ByteReader
        public int read() throws IOException {
            byte[] bArr = this.bytes;
            int i = this.offset;
            int i2 = this.index;
            int i3 = bArr[i + i2] & 255;
            this.index = i2 + 1;
            return i3;
        }
    }

    public static class ByteInputStreamReader implements ByteReader {
        private int count;
        private final InputStream inputStream;

        public ByteInputStreamReader(InputStream inputStream) {
            this.inputStream = inputStream;
        }

        @Override // com.reandroid.dex.io.ByteReader
        public int count() {
            return this.count;
        }

        @Override // com.reandroid.dex.io.ByteReader
        public int read() throws IOException {
            int i = this.inputStream.read();
            if (i != -1) {
                this.count++;
                return i;
            }
            u8g.a("Finished reading: ", this.inputStream);
            return 0;
        }
    }

    public static ByteReader createByteReader(byte[] bArr) {
        return new ByteArrayReader(bArr, 0);
    }

    public static ByteReader createByteReader(byte[] bArr, int i) {
        return new ByteArrayReader(bArr, i);
    }

    public static ByteReader createByteReader(InputStream inputStream) {
        return new ByteInputStreamReader(inputStream);
    }
}
