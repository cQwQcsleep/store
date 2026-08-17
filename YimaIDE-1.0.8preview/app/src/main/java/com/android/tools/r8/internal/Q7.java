package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class Q7 extends P7 {
    public final byte[] e;

    public Q7(byte[] bArr) {
        bArr.getClass();
        this.e = bArr;
    }

    @Override // com.android.tools.r8.internal.U7
    public final boolean a() {
        int iD = d();
        return AbstractC2201nl0.a.b(this.e, iD, size() + iD) == 0;
    }

    @Override // com.android.tools.r8.internal.U7
    public final String b() {
        return new String(this.e, d(), size(), AbstractC1556gB.b);
    }

    public int d() {
        return 0;
    }

    @Override // com.android.tools.r8.internal.U7
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof U7) || size() != ((U7) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof Q7)) {
            return obj.equals(this);
        }
        Q7 q7 = (Q7) obj;
        int i = this.b;
        int i2 = q7.b;
        if (i != 0 && i2 != 0 && i != i2) {
            return false;
        }
        int size = size();
        if (size > q7.size()) {
            throw new IllegalArgumentException("Length too large: " + size + size());
        }
        if (size > q7.size()) {
            StringBuilder sbA = Ni0.a(size, "Ran off end of other: 0, ", ", ");
            sbA.append(q7.size());
            throw new IllegalArgumentException(sbA.toString());
        }
        byte[] bArr = this.e;
        byte[] bArr2 = q7.e;
        int iD = d() + size;
        int iD2 = d();
        int iD3 = q7.d();
        while (iD2 < iD) {
            if (bArr[iD2] != bArr2[iD3]) {
                return false;
            }
            iD2++;
            iD3++;
        }
        return true;
    }

    @Override // com.android.tools.r8.internal.U7
    public byte j(int i) {
        return this.e[i];
    }

    @Override // com.android.tools.r8.internal.U7
    public byte k(int i) {
        return this.e[i];
    }

    @Override // com.android.tools.r8.internal.U7
    public int size() {
        return this.e.length;
    }
}
