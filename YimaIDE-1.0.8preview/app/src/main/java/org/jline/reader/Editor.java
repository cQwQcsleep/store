package org.jline.reader;

import java.io.IOException;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface Editor {
    void open(List<String> list) throws IOException;

    void run() throws IOException;

    void setRestricted(boolean z);
}
