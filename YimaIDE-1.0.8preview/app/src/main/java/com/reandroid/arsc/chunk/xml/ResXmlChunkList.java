package com.reandroid.arsc.chunk.xml;

import com.reandroid.arsc.chunk.xml.BaseXmlChunk;
import com.reandroid.arsc.container.BlockList;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
class ResXmlChunkList<T extends BaseXmlChunk> extends BlockList<T> {
    public void clear() {
        clearChildes();
    }

    @Override // 
    public void onPreRemove(T t) {
        super.onPreRemove(t);
        t.onPreRemove();
    }
}
