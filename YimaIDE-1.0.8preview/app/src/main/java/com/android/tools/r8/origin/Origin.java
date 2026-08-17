package com.android.tools.r8.origin;

import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Origin implements Comparable<Origin> {
    private static final d c = new d();
    private static final e d = new e();
    static final /* synthetic */ boolean e = true;
    private final Origin b;

    public Origin(Origin origin) {
        if (e || origin != null) {
            this.b = origin;
        } else {
            x1f.a();
            throw null;
        }
    }

    public static Origin root() {
        return c;
    }

    public static Origin unknown() {
        return d;
    }

    public List b(int i) {
        List listB = parent().b(i + 1);
        listB.add(part());
        return listB;
    }

    @Override // java.lang.Comparable
    public int compareTo(Origin origin) {
        List<String> listParts = parts();
        List<String> listParts2 = origin.parts();
        int iMin = Math.min(listParts.size(), listParts2.size());
        for (int i = 0; i < iMin; i++) {
            int iCompareTo = listParts.get(i).compareTo(listParts2.get(i));
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return Integer.compare(listParts.size(), listParts2.size());
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Origin)) {
            return false;
        }
        Origin originParent = (Origin) obj;
        while (this != null && originParent != null && this.part().equals(originParent.part())) {
            this = this.parent();
            originParent = originParent.parent();
        }
        return this == originParent;
    }

    public int hashCode() {
        Iterator<String> it = parts().iterator();
        int iHashCode = 1;
        while (it.hasNext()) {
            iHashCode = (iHashCode * 31) + it.next().hashCode();
        }
        return iHashCode;
    }

    public Origin parent() {
        return this.b;
    }

    public abstract String part();

    public List<String> parts() {
        return b(0);
    }

    public String toString() {
        return String.join(":", parts());
    }

    private Origin() {
        this.b = null;
    }
}
