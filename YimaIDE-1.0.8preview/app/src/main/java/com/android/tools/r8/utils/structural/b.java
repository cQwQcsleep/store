package com.android.tools.r8.utils.structural;

import com.android.tools.r8.graph.F2;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class b extends A {
    public static final /* synthetic */ boolean e = true;
    public final c a;
    public final Object b;
    public final Object c;
    public int d = 0;

    public b(Object obj, Object obj2, c cVar) {
        this.b = obj;
        this.c = obj2;
        this.a = cVar;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(Function function) {
        if (this.d == 0) {
            byte[] bArr = (byte[]) function.apply(this.b);
            byte[] bArr2 = (byte[]) function.apply(this.c);
            int iMin = Math.min(bArr.length, bArr2.length);
            for (int i = 0; i < iMin && this.d == 0; i++) {
                c cVar = this.a;
                byte b = bArr[i];
                byte b2 = bArr2[i];
                cVar.getClass();
                this.d = Integer.compare(b, b2);
            }
            if (this.d == 0) {
                c cVar2 = this.a;
                int length = bArr.length;
                int length2 = bArr2.length;
                cVar2.getClass();
                this.d = Integer.compare(length, length2);
            }
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A b(Function function, v vVar, w wVar) {
        if (this.d == 0) {
            this.d = this.a.a((Iterator) function.apply(this.b), (Iterator) function.apply(this.c), vVar);
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A c(Function function) {
        if (this.d == 0) {
            this.d = this.a.a((F2) function.apply(this.b), (F2) function.apply(this.c));
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A d(Function function) {
        if (this.d == 0) {
            int[] iArr = (int[]) function.apply(this.b);
            int[] iArr2 = (int[]) function.apply(this.c);
            int iMin = Math.min(iArr.length, iArr2.length);
            for (int i = 0; i < iMin && this.d == 0; i++) {
                c cVar = this.a;
                int i2 = iArr[i];
                int i3 = iArr2[i];
                cVar.getClass();
                this.d = Integer.compare(i2, i3);
            }
            if (this.d == 0) {
                c cVar2 = this.a;
                int length = iArr.length;
                int length2 = iArr2.length;
                cVar2.getClass();
                this.d = Integer.compare(length, length2);
            }
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A k(Function function) {
        if (this.d == 0) {
            short[] sArr = (short[]) function.apply(this.b);
            short[] sArr2 = (short[]) function.apply(this.c);
            int iMin = Math.min(sArr.length, sArr2.length);
            for (int i = 0; i < iMin && this.d == 0; i++) {
                c cVar = this.a;
                short s = sArr[i];
                short s2 = sArr2[i];
                cVar.getClass();
                this.d = Integer.compare(s, s2);
            }
            if (this.d == 0) {
                c cVar2 = this.a;
                int length = sArr.length;
                int length2 = sArr2.length;
                cVar2.getClass();
                this.d = Integer.compare(length, length2);
            }
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A b(Predicate predicate) {
        if (this.d == 0) {
            c cVar = this.a;
            boolean zTest = predicate.test(this.b);
            boolean zTest2 = predicate.test(this.c);
            cVar.getClass();
            this.d = Boolean.compare(zTest, zTest2);
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(Predicate predicate) {
        boolean z = e;
        if (!z && !predicate.test(this.b)) {
            x1f.a();
            return null;
        }
        if (z || predicate.test(this.c)) {
            return this;
        }
        x1f.a();
        return null;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(ToIntFunction toIntFunction) {
        if (this.d == 0) {
            c cVar = this.a;
            int iApplyAsInt = toIntFunction.applyAsInt(this.b);
            int iApplyAsInt2 = toIntFunction.applyAsInt(this.c);
            cVar.getClass();
            this.d = Integer.compare(iApplyAsInt, iApplyAsInt2);
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(ToLongFunction toLongFunction) {
        if (this.d == 0) {
            c cVar = this.a;
            long jApplyAsLong = toLongFunction.applyAsLong(this.b);
            long jApplyAsLong2 = toLongFunction.applyAsLong(this.c);
            cVar.getClass();
            this.d = Long.compare(jApplyAsLong, jApplyAsLong2);
        }
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a() {
        return this;
    }

    @Override // com.android.tools.r8.utils.structural.A
    public final A a(Predicate predicate, Function function, v vVar, w wVar) {
        if (this.d == 0) {
            boolean zTest = predicate.test(this.b);
            boolean zTest2 = predicate.test(this.c);
            if (zTest && zTest2) {
                this.d = vVar.a(function.apply(this.b), function.apply(this.c), this.a);
                return this;
            }
            this.a.getClass();
            this.d = Boolean.compare(zTest, zTest2);
        }
        return this;
    }
}
