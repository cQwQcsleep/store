package com.sun.org.apache.xerces.internal.xs;

import java.util.List;
import org.w3c.dom.ls.LSInput;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface LSInputList extends List<LSInput> {
    int getLength();

    LSInput item(int i);
}
