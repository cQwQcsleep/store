package com.android.tools.r8.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/* JADX INFO: renamed from: com.android.tools.r8.internal.r30, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C2481r30 extends AbstractC2647t1 implements Cloneable {
    public transient Object[] c;
    public transient int[] d;
    public transient int e;
    public transient boolean f;
    public transient int g;
    public transient int h;
    public int i;
    public transient C2225o30 j;
    public transient C2054m30 k;
    public transient C1797j30 l;

    public C2481r30() {
        int iA = AbstractC0938Ws.a(16, 0.75f);
        this.g = iA;
        this.e = iA - 1;
        this.h = AbstractC0938Ws.b(iA, 0.75f);
        int i = this.g + 1;
        this.c = new Object[i];
        this.d = new int[i];
    }

    /* JADX WARN: Code duplicated, block: B:19:0x0042  */
    @Override // com.android.tools.r8.internal.AbstractC2647t1
    public final int b(int i, Object obj) {
        int i2;
        int i3;
        int i4;
        if (obj != null) {
            Object[] objArr = this.c;
            int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.e;
            Object obj2 = objArr[iA];
            if (obj2 != null) {
                if (obj2 != obj) {
                    while (true) {
                        iA = (iA + 1) & this.e;
                        Object obj3 = objArr[iA];
                        if (obj3 != null) {
                            if (obj3 == obj) {
                            }
                        }
                    }
                }
                i3 = iA;
            }
            i2 = iA;
            this.c[i2] = obj;
            this.d[i2] = i;
            i4 = this.i;
            this.i = i4 + 1;
            if (i4 >= this.h) {
                e(AbstractC0938Ws.a(i4 + 2, 0.75f));
            }
            i3 = -1;
        } else if (this.f) {
            i3 = this.g;
        } else {
            this.f = true;
            i2 = this.g;
            this.c[i2] = obj;
            this.d[i2] = i;
            i4 = this.i;
            this.i = i4 + 1;
            if (i4 >= this.h) {
                e(AbstractC0938Ws.a(i4 + 2, 0.75f));
            }
            i3 = -1;
        }
        if (i3 < 0) {
            return this.b;
        }
        int[] iArr = this.d;
        int i5 = iArr[i3];
        iArr[i3] = i;
        return i5;
    }

    @Override // com.android.tools.r8.internal.AbstractC2647t1
    public final int c(Object obj) {
        Object obj2;
        if (obj == null) {
            if (!this.f) {
                return this.b;
            }
            this.f = false;
            Object[] objArr = this.c;
            int i = this.g;
            objArr[i] = null;
            int i2 = this.d[i];
            int i3 = this.i - 1;
            this.i = i3;
            if (i3 < this.h / 4 && i > 16) {
                e(i / 2);
            }
            return i2;
        }
        Object[] objArr2 = this.c;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.e;
        Object obj3 = objArr2[iA];
        if (obj3 == null) {
            return this.b;
        }
        if (obj == obj3) {
            return f(iA);
        }
        do {
            iA = (iA + 1) & this.e;
            obj2 = objArr2[iA];
            if (obj2 == null) {
                return this.b;
            }
        } while (obj != obj2);
        return f(iA);
    }

    @Override // java.util.Map
    public final void clear() {
        if (this.i == 0) {
            return;
        }
        this.i = 0;
        this.f = false;
        Arrays.fill(this.c, (Object) null);
    }

    public final Object clone() {
        try {
            C2481r30 c2481r30 = (C2481r30) super.clone();
            c2481r30.k = null;
            c2481r30.l = null;
            c2481r30.j = null;
            c2481r30.f = this.f;
            c2481r30.c = (Object[]) this.c.clone();
            c2481r30.d = (int[]) this.d.clone();
            return c2481r30;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final boolean containsKey(Object obj) {
        Object obj2;
        if (obj == null) {
            return this.f;
        }
        Object[] objArr = this.c;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.e;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return false;
        }
        if (obj == obj3) {
            return true;
        }
        do {
            iA = (iA + 1) & this.e;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return false;
            }
        } while (obj != obj2);
        return true;
    }

    @Override // com.android.tools.r8.internal.AbstractC2647t1
    public final boolean d(int i) {
        int[] iArr = this.d;
        Object[] objArr = this.c;
        if (this.f && iArr[this.g] == i) {
            return true;
        }
        int i2 = this.g;
        while (true) {
            int i3 = i2 - 1;
            if (i2 == 0) {
                return false;
            }
            if (objArr[i3] != null && iArr[i3] == i) {
                return true;
            }
            i2 = i3;
        }
    }

    public final void e(int i) {
        Object obj;
        Object[] objArr = this.c;
        int[] iArr = this.d;
        int i2 = i - 1;
        int i3 = i + 1;
        Object[] objArr2 = new Object[i3];
        int[] iArr2 = new int[i3];
        int i4 = this.g;
        boolean z = this.f;
        int i5 = this.i;
        if (z) {
            i5--;
        }
        while (true) {
            int i6 = i5 - 1;
            if (i5 == 0) {
                iArr2[i] = iArr[this.g];
                this.g = i;
                this.e = i2;
                this.h = AbstractC0938Ws.b(i, 0.75f);
                this.c = objArr2;
                this.d = iArr2;
                return;
            }
            do {
                i4--;
                obj = objArr[i4];
            } while (obj == null);
            int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & i2;
            if (objArr2[iA] != null) {
                do {
                    iA = (iA + 1) & i2;
                } while (objArr2[iA] != null);
            }
            objArr2[iA] = objArr[i4];
            iArr2[iA] = iArr[i4];
            i5 = i6;
        }
    }

    public final int f(int i) {
        Object obj;
        int i2;
        int i3 = this.d[i];
        this.i--;
        Object[] objArr = this.c;
        loop0: while (true) {
            int i4 = (i + 1) & this.e;
            while (true) {
                obj = objArr[i4];
                if (obj != null) {
                    int iA = AbstractC0938Ws.a(System.identityHashCode(obj));
                    int i5 = this.e;
                    int i6 = iA & i5;
                    if (i > i4) {
                        if (i >= i6 && i6 > i4) {
                            break;
                        }
                        i4 = (i4 + 1) & i5;
                    } else {
                        if (i >= i6 || i6 > i4) {
                            break;
                        }
                        i4 = (i4 + 1) & i5;
                    }
                } else {
                    break loop0;
                }
            }
            objArr[i] = obj;
            int[] iArr = this.d;
            iArr[i] = iArr[i4];
            i = i4;
        }
        objArr[i] = null;
        if (this.i < this.h / 4 && (i2 = this.g) > 16) {
            e(i2 / 2);
        }
        return i3;
    }

    @Override // com.android.tools.r8.internal.AbstractC2647t1, java.util.Map
    public final int hashCode() {
        Object obj;
        boolean z = this.f;
        int i = this.i;
        if (z) {
            i--;
        }
        int i2 = 0;
        int iIdentityHashCode = 0;
        int i3 = i;
        int i4 = 0;
        while (true) {
            int i5 = i3 - 1;
            if (i3 == 0) {
                break;
            }
            while (true) {
                obj = this.c[i2];
                if (obj != null) {
                    break;
                }
                i2++;
            }
            if (this != obj) {
                iIdentityHashCode = System.identityHashCode(obj);
            }
            iIdentityHashCode ^= this.d[i2];
            i4 += iIdentityHashCode;
            i2++;
            i3 = i5;
        }
        return this.f ? i4 + this.d[this.g] : i4;
    }

    @Override // com.android.tools.r8.internal.AbstractC2647t1
    public final JU i() {
        if (this.j == null) {
            this.j = new C2225o30(this);
        }
        return this.j;
    }

    @Override // java.util.Map
    public final boolean isEmpty() {
        return this.i == 0;
    }

    @Override // java.util.Map
    public final Set keySet() {
        if (this.k == null) {
            this.k = new C2054m30(this);
        }
        return this.k;
    }

    @Override // com.android.tools.r8.internal.AbstractC2647t1, java.util.Map
    public final void putAll(Map map) {
        int iMin = (int) Math.min(1073741824L, Math.max(2L, AbstractC0938Ws.b((long) Math.ceil((map.size() + this.i) / 0.75f))));
        if (iMin > this.g) {
            e(iMin);
        }
        super.putAll(map);
    }

    @Override // java.util.Map, com.android.tools.r8.internal.InterfaceC2294or
    public final int size() {
        return this.i;
    }

    @Override // java.util.Map
    public final Collection values() {
        if (this.l == null) {
            this.l = new C1797j30(this);
        }
        return this.l;
    }

    @Override // com.android.tools.r8.internal.AbstractC2647t1
    public final int b(Object obj) {
        Object obj2;
        if (obj == null) {
            return this.f ? this.d[this.g] : this.b;
        }
        Object[] objArr = this.c;
        int iA = AbstractC0938Ws.a(System.identityHashCode(obj)) & this.e;
        Object obj3 = objArr[iA];
        if (obj3 == null) {
            return this.b;
        }
        if (obj == obj3) {
            return this.d[iA];
        }
        do {
            iA = (iA + 1) & this.e;
            obj2 = objArr[iA];
            if (obj2 == null) {
                return this.b;
            }
        } while (obj != obj2);
        return this.d[iA];
    }
}
