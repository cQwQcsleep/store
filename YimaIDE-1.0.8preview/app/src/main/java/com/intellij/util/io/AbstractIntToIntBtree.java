package com.intellij.util.io;

import androidx.compose.foundation.text.input.internal.PartialGapBuffer;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class AbstractIntToIntBtree {

    public interface BtreeDataStorage {
        int persistInt(int i, int i2, boolean z) throws IOException;
    }

    public static abstract class KeyValueProcessor {
        public abstract boolean process(int i, int i2) throws IOException;
    }

    public static int version() {
        return (IOUtil.useNativeByteOrderForByteBuffers() ? PartialGapBuffer.BUF_SIZE : 0) + 4;
    }
}
