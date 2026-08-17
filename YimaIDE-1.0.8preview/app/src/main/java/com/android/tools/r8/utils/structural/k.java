package com.android.tools.r8.utils.structural;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface k<T> {
    static {
        boolean z = j.a;
    }

    /* JADX WARN: Incorrect types in method signature: <T::Lcom/android/tools/r8/utils/structural/k<TT;>;>(TT;Ljava/lang/Object;)Z */
    static boolean a(k kVar, Object obj) {
        if (!j.a && kVar == null) {
            x1f.a();
            return false;
        }
        if (kVar == obj) {
            return true;
        }
        if (obj == null || kVar.getClass() != obj.getClass()) {
            return false;
        }
        return kVar.isEqualTo((k) obj);
    }

    boolean isEqualTo(Object obj);
}
