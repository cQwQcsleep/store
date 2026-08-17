package com.android.tools.r8.internal;

import java.io.Serializable;

/* JADX INFO: renamed from: com.android.tools.r8.internal.mh, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2106mh implements Comparable, Serializable {
    public final Comparable b;

    @Override // java.lang.Comparable
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public abstract int compareTo(AbstractC2106mh abstractC2106mh);

    public abstract void a(StringBuilder sb);

    public abstract boolean a();

    public abstract void b(StringBuilder sb);

    public final boolean equals(Object obj) {
        if (obj instanceof AbstractC2106mh) {
            try {
                if (compareTo((AbstractC2106mh) obj) == 0) {
                    return true;
                }
            } catch (ClassCastException unused) {
            }
        }
        return false;
    }

    public abstract int hashCode();
}
