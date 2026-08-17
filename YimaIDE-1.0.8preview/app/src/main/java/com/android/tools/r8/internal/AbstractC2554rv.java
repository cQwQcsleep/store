package com.android.tools.r8.internal;

import defpackage.pv9;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;
import java.util.SortedSet;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rv, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC2554rv extends AbstractC3066xu implements Set {
    public static final /* synthetic */ int c = 0;

    public static AbstractC2554rv a(Collection collection) {
        int i;
        if ((collection instanceof AbstractC2554rv) && !(collection instanceof SortedSet)) {
            AbstractC2554rv abstractC2554rv = (AbstractC2554rv) collection;
            if (!abstractC2554rv.e()) {
                return abstractC2554rv;
            }
        } else if (collection instanceof EnumSet) {
            EnumSet enumSetCopyOf = EnumSet.copyOf((EnumSet) collection);
            int size = enumSetCopyOf.size();
            if (size != 0) {
                return size != 1 ? new C0395Bu(enumSetCopyOf) : new Cc0((Enum) AbstractC3179zC.a(enumSetCopyOf));
            }
            return W40.j;
        }
        Object[] array = collection.toArray();
        if (collection instanceof Set) {
            return a(array.length, array.length, array);
        }
        int length = array.length;
        RoundingMode roundingMode = RoundingMode.CEILING;
        if (length < 0) {
            w01.a(AbstractC1784iv.a(length, "x (", ") must be >= 0"));
            return null;
        }
        int iSqrt = (int) Math.sqrt(length);
        switch (AbstractC2324pA.a[roundingMode.ordinal()]) {
            case 1:
                if (iSqrt * iSqrt != length) {
                    pv9.a("mode was UNNECESSARY, but rounding was necessary");
                    return null;
                }
            case 2:
            case XmlPullParser.END_TAG /* 3 */:
                return a(length, Math.max(4, iSqrt), array);
            case 4:
            case XmlPullParser.CDSECT /* 5 */:
                i = iSqrt * iSqrt;
                iSqrt += (~(~(i - length))) >>> 31;
                return a(length, Math.max(4, iSqrt), array);
            case XmlPullParser.ENTITY_REF /* 6 */:
            case 7:
            case 8:
                i = (iSqrt * iSqrt) + iSqrt;
                iSqrt += (~(~(i - length))) >>> 31;
                return a(length, Math.max(4, iSqrt), array);
            default:
                x1f.a();
                return null;
        }
    }

    public static C1870jv g() {
        return new C1870jv();
    }

    public static int j(int i) {
        int iMax = Math.max(i, 2);
        if (iMax < 751619276) {
            int iHighestOneBit = Integer.highestOneBit(iMax - 1) << 1;
            while (((double) iHighestOneBit) * 0.7d < iMax) {
                iHighestOneBit <<= 1;
            }
            return iHighestOneBit;
        }
        if (iMax < 1073741824) {
            return 1073741824;
        }
        w01.a("collection too large");
        return 0;
    }

    public static W40 k() {
        return W40.j;
    }

    @Override // java.util.Collection, java.util.Set
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if ((obj instanceof AbstractC2554rv) && i() && ((AbstractC2554rv) obj).i() && hashCode() != obj.hashCode()) {
            return false;
        }
        if (this != obj) {
            if (obj instanceof Set) {
                Set set = (Set) obj;
                try {
                    if (size() != set.size() || !containsAll(set)) {
                    }
                } catch (ClassCastException | NullPointerException unused) {
                }
            }
            return false;
        }
        return true;
    }

    @Override // java.util.Collection, java.util.Set
    public int hashCode() {
        return AbstractC2780ub0.a((Set) this);
    }

    public boolean i() {
        return this instanceof C0395Bu;
    }

    public static AbstractC2554rv j() {
        return a(4, 4, "--output", "--min-api", "--desugared-lib", "--lib");
    }

    public static AbstractC2554rv a(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object... objArr) {
        if (objArr.length <= 2147483641) {
            int length = objArr.length + 6;
            Object[] objArr2 = new Object[length];
            objArr2[0] = obj;
            objArr2[1] = obj2;
            objArr2[2] = obj3;
            objArr2[3] = obj4;
            objArr2[4] = obj5;
            objArr2[5] = obj6;
            System.arraycopy(objArr, 0, objArr2, 6, objArr.length);
            return a(length, length, objArr2);
        }
        w01.a("the total number of elements must fit in an int");
        return null;
    }

    public static AbstractC2554rv a(int i, int i2, Object... objArr) {
        if (i == 0) {
            return W40.j;
        }
        if (i != 1) {
            AbstractC2469qv c2384pv = new C2384pv(i2);
            for (int i3 = 0; i3 < i; i3++) {
                Object obj = objArr[i3];
                obj.getClass();
                c2384pv = c2384pv.a(obj);
            }
            return c2384pv.c().a();
        }
        return new Cc0(objArr[0]);
    }

    public static AbstractC2554rv a(Object obj, Object obj2, Object obj3) {
        return a(3, 3, obj, obj2, obj3);
    }
}
