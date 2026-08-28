package j$.util.concurrent;

/* loaded from: /workspace/unpacked/classes3.dex */
class p {
    l[] a;
    l b = null;
    o c;
    o d;
    int e;
    int f;
    int g;
    final int h;

    p(l[] lVarArr, int i, int i2, int i3) {
        this.a = lVarArr;
        this.h = i;
        this.e = i2;
        this.f = i2;
        this.g = i3;
    }

    /* JADX WARN: Removed duplicated region for block: B:57:0x0097 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x005f A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    final l a() {
        l[] lVarArr;
        int length;
        int i;
        o oVar;
        l lVar = this.b;
        if (lVar != null) {
            lVar = lVar.d;
        }
        while (lVar == null) {
            if (this.f >= this.g || (lVarArr = this.a) == null || (length = lVarArr.length) <= (i = this.e) || i < 0) {
                this.b = null;
                return null;
            }
            l lVarK = ConcurrentHashMap.k(lVarArr, i);
            if (lVarK != null && lVarK.a < 0) {
                if (lVarK instanceof g) {
                    this.a = ((g) lVarK).e;
                    o oVar2 = this.d;
                    if (oVar2 == null) {
                        oVar2 = new o();
                    } else {
                        this.d = oVar2.d;
                    }
                    oVar2.c = lVarArr;
                    oVar2.a = length;
                    oVar2.b = i;
                    oVar2.d = this.c;
                    this.c = oVar2;
                    lVar = null;
                } else {
                    lVar = lVarK instanceof q ? ((q) lVarK).f : null;
                    if (this.c == null) {
                    }
                }
            } else {
                lVar = lVarK;
                if (this.c == null) {
                    while (true) {
                        oVar = this.c;
                        if (oVar == null) {
                            break;
                        }
                        int i2 = this.e;
                        int i3 = oVar.a;
                        int i4 = i2 + i3;
                        this.e = i4;
                        if (i4 < length) {
                            break;
                        }
                        this.e = oVar.b;
                        this.a = oVar.c;
                        oVar.c = null;
                        o oVar3 = oVar.d;
                        oVar.d = this.d;
                        this.c = oVar3;
                        this.d = oVar;
                        length = i3;
                    }
                    if (oVar == null) {
                        int i5 = this.e + this.h;
                        this.e = i5;
                        if (i5 >= length) {
                            int i6 = this.f + 1;
                            this.f = i6;
                            this.e = i6;
                        }
                    }
                } else {
                    int i7 = i + this.h;
                    this.e = i7;
                    if (i7 >= length) {
                        int i8 = this.f + 1;
                        this.f = i8;
                        this.e = i8;
                    }
                }
            }
        }
        this.b = lVar;
        return lVar;
    }
}
