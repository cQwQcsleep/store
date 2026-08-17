package com.android.tools.r8.internal;

import java.util.AbstractCollection;

/* JADX INFO: renamed from: com.android.tools.r8.internal.u1, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2733u1 extends AbstractCollection implements P30 {
    @Override // java.util.AbstractCollection
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        BU it = iterator();
        int size = size();
        boolean z = true;
        while (true) {
            int i = size - 1;
            if (size == 0) {
                sb.append("}");
                return sb.toString();
            }
            if (z) {
                z = false;
            } else {
                sb.append(", ");
            }
            Object next = it.next();
            if (this == next) {
                sb.append("(this collection)");
            } else {
                sb.append(String.valueOf(next));
            }
            size = i;
        }
    }
}
