package com.intellij.util.io;

import java.io.DataOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface AppendablePersistentMap extends PersistentMap {

    public interface ValueDataAppender {
        void append(DataOutput dataOutput) throws IOException;
    }
}
