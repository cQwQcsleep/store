package com.android.tools.r8.internal;

import java.util.Iterator;
import java.util.LinkedList;

/* JADX INFO: renamed from: com.android.tools.r8.internal.e10, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1366e10 {
    public final com.android.tools.r8.graph.B5 a;
    public final int b;
    public final LinkedList c;
    public final LinkedList d;
    public final LinkedList e;

    public C1366e10(com.android.tools.r8.graph.B5 b5, int i, LinkedList linkedList, LinkedList linkedList2, LinkedList linkedList3) {
        this.a = b5;
        this.b = i;
        this.c = linkedList;
        this.d = linkedList2;
        this.e = linkedList3;
    }

    public final boolean a() {
        LinkedList linkedList = this.c;
        return (linkedList == null || linkedList.isEmpty()) ? false : true;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ProtoMessageInfo(fields=[");
        if (a()) {
            Iterator it = this.c.iterator();
            sb.append(it.next());
            while (it.hasNext()) {
                sb.append(", ");
                sb.append(it.next());
            }
        }
        sb.append("])");
        return sb.toString();
    }

    public static c10 a(com.android.tools.r8.graph.B5 b5) {
        return new c10(b5);
    }
}
