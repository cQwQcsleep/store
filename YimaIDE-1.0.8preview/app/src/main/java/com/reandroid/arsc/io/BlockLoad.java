package com.reandroid.arsc.io;

import com.reandroid.arsc.base.Block;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface BlockLoad {
    void onBlockLoaded(BlockReader blockReader, Block block) throws IOException;
}
