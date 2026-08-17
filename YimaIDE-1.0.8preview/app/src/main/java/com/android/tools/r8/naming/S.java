package com.android.tools.r8.naming;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class S {
    public static final /* synthetic */ boolean a = true;

    public static int a(List list, int i, ArrayList arrayList) {
        int i2;
        if (!a && i >= list.size()) {
            x1f.a();
            return 0;
        }
        while (true) {
            i2 = i + 1;
            if (i2 < list.size()) {
                C3331k.b bVar = (C3331k.b) list.get(i);
                C3331k.b bVar2 = (C3331k.b) list.get(i2);
                if (bVar.b == null || bVar2.f() == null || !bVar.b.equals(bVar2.b)) {
                    break;
                }
                arrayList.add((C3331k.b) list.get(i));
                i = i2;
            } else {
                break;
            }
        }
        arrayList.add((C3331k.b) list.get(i));
        return i2;
    }

    public static boolean a(C3331k.b bVar, C3331k.b bVar2) {
        return bVar.b != null && bVar2.f() != null && bVar2.f().c && bVar.b.equals(bVar2.b);
    }
}
