package com.sun.org.apache.bcel.internal.util;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ByteSequence extends DataInputStream {
    private final ByteArrayStream byteStream;

    public static final class ByteArrayStream extends ByteArrayInputStream {
        public ByteArrayStream(byte[] bArr) {
            super(bArr);
        }

        public int getPosition() {
            return ((ByteArrayInputStream) this).pos;
        }

        public void unreadByte() {
            int i = ((ByteArrayInputStream) this).pos;
            if (i > 0) {
                ((ByteArrayInputStream) this).pos = i - 1;
            }
        }
    }

    public ByteSequence(byte[] bArr) {
        super(new ByteArrayStream(bArr));
        this.byteStream = (ByteArrayStream) ((DataInputStream) this).in;
    }

    public int getIndex() {
        return this.byteStream.getPosition();
    }

    public void unreadByte() {
        this.byteStream.unreadByte();
    }
}
