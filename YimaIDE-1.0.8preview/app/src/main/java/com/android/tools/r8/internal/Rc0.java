package com.android.tools.r8.internal;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class Rc0 extends AbstractMap {
    public static final /* synthetic */ int h = 0;
    public final int b;
    public List c = Collections.EMPTY_LIST;
    public Map d;
    public boolean e;
    public volatile Qc0 f;
    public Map g;

    public Rc0(int i) {
        this.b = i;
        Map map = Collections.EMPTY_MAP;
        this.d = map;
        this.g = map;
    }

    public final Object a(Comparable comparable, Object obj) {
        e();
        int iA = a(comparable);
        if (iA >= 0) {
            return ((Mc0) this.c.get(iA)).setValue(obj);
        }
        e();
        if (this.c.isEmpty() && !(this.c instanceof ArrayList)) {
            this.c = new ArrayList(this.b);
        }
        int i = -(iA + 1);
        if (i >= this.b) {
            return j().put(comparable, obj);
        }
        int size = this.c.size();
        int i2 = this.b;
        if (size == i2) {
            Mc0 mc0 = (Mc0) this.c.remove(i2 - 1);
            j().put(mc0.b, mc0.c);
        }
        this.c.add(i, new Mc0(this, comparable, obj));
        return null;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final void clear() {
        e();
        if (!this.c.isEmpty()) {
            this.c.clear();
        }
        if (this.d.isEmpty()) {
            return;
        }
        this.d.clear();
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean containsKey(Object obj) {
        Comparable comparable = (Comparable) obj;
        return a(comparable) >= 0 || this.d.containsKey(comparable);
    }

    public final void e() {
        if (this.e) {
            a9g.a();
        }
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Set entrySet() {
        if (this.f == null) {
            this.f = new Qc0(this);
        }
        return this.f;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Rc0)) {
            return super.equals(obj);
        }
        Rc0 rc0 = (Rc0) obj;
        int size = size();
        if (size != rc0.size()) {
            return false;
        }
        int size2 = this.c.size();
        if (size2 != rc0.c.size()) {
            return ((AbstractSet) entrySet()).equals(rc0.entrySet());
        }
        for (int i = 0; i < size2; i++) {
            if (!((Map.Entry) this.c.get(i)).equals((Map.Entry) rc0.c.get(i))) {
                return false;
            }
        }
        if (size2 != size) {
            return this.d.equals(rc0.d);
        }
        return true;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final Object get(Object obj) {
        Comparable comparable = (Comparable) obj;
        int iA = a(comparable);
        return iA >= 0 ? ((Mc0) this.c.get(iA)).c : this.d.get(comparable);
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int hashCode() {
        int size = this.c.size();
        int iHashCode = 0;
        for (int i = 0; i < size; i++) {
            iHashCode += ((Mc0) this.c.get(i)).hashCode();
        }
        return this.d.size() > 0 ? this.d.hashCode() + iHashCode : iHashCode;
    }

    public final Iterable i() {
        return this.d.isEmpty() ? Kc0.b : this.d.entrySet();
    }

    public final SortedMap j() {
        e();
        if (this.d.isEmpty() && !(this.d instanceof TreeMap)) {
            TreeMap treeMap = new TreeMap();
            this.d = treeMap;
            this.g = treeMap.descendingMap();
        }
        return (SortedMap) this.d;
    }

    public abstract void k();

    @Override // java.util.AbstractMap, java.util.Map
    public final Object remove(Object obj) {
        e();
        Comparable comparable = (Comparable) obj;
        int iA = a(comparable);
        if (iA < 0) {
            if (this.d.isEmpty()) {
                return null;
            }
            return this.d.remove(comparable);
        }
        e();
        Object obj2 = ((Mc0) this.c.remove(iA)).c;
        if (!this.d.isEmpty()) {
            Iterator it = j().entrySet().iterator();
            this.c.add(new Mc0(this, (Map.Entry) it.next()));
            it.remove();
        }
        return obj2;
    }

    @Override // java.util.AbstractMap, java.util.Map
    public final int size() {
        return this.d.size() + this.c.size();
    }

    /* JADX WARN: Code duplicated, block: B:13:0x0024  */
    /* JADX WARN: Code duplicated, block: B:17:0x003d  */
    /* JADX WARN: Code duplicated, block: B:21:0x003b A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:22:0x0040 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0038 A[SYNTHETIC] */
    public final int a(Comparable comparable) {
        int i;
        int i2;
        int i3;
        int iCompareTo;
        int size = this.c.size();
        int i4 = size - 1;
        if (i4 < 0) {
            i = 0;
            while (i <= i4) {
                i3 = (i + i4) / 2;
                iCompareTo = comparable.compareTo(((Mc0) this.c.get(i3)).b);
                if (iCompareTo < 0) {
                    i4 = i3 - 1;
                } else {
                    if (iCompareTo > 0) {
                        return i3;
                    }
                    i = i3 + 1;
                }
            }
            i2 = i + 1;
        } else {
            int iCompareTo2 = comparable.compareTo(((Mc0) this.c.get(i4)).b);
            if (iCompareTo2 > 0) {
                i2 = size + 1;
            } else {
                if (iCompareTo2 == 0) {
                    return i4;
                }
                i = 0;
                while (i <= i4) {
                    i3 = (i + i4) / 2;
                    iCompareTo = comparable.compareTo(((Mc0) this.c.get(i3)).b);
                    if (iCompareTo < 0) {
                        i4 = i3 - 1;
                    } else {
                        if (iCompareTo > 0) {
                            return i3;
                        }
                        i = i3 + 1;
                    }
                }
                i2 = i + 1;
            }
        }
        return -i2;
    }
}
