package io.github.rosemoe.sora.lang.styling;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class BlocksUpdater {
    public static void update(List<CodeBlock> list, int i, int i2) {
        if (i2 == 0) {
            return;
        }
        Iterator<CodeBlock> it2 = list.iterator();
        while (it2.hasNext()) {
            CodeBlock next = it2.next();
            int i3 = next.startLine;
            if (i3 >= i) {
                next.startLine = i3 + i2;
            }
            int i4 = next.endLine;
            if (i4 >= i) {
                next.endLine = i4 + i2;
            }
            if (next.startLine >= next.endLine) {
                it2.remove();
            }
        }
    }
}
