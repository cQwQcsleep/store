package com.android.tools.r8.internal;

import java.util.Objects;

/* JADX INFO: renamed from: com.android.tools.r8.internal.x5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2998x5 extends AbstractC3083y5 {
    public final C2741u5 b;
    public final Character c;

    /* JADX WARN: Code duplicated, block: B:9:0x001a  */
    public C2998x5(C2741u5 c2741u5, Character ch) {
        boolean z;
        c2741u5.getClass();
        this.b = c2741u5;
        if (ch != null) {
            char cCharValue = ch.charValue();
            byte[] bArr = c2741u5.g;
            if (cCharValue >= bArr.length || bArr[cCharValue] == -1) {
                z = true;
            } else {
                z = false;
            }
        } else {
            z = true;
        }
        DX.a(z, "Padding character %s was already in alphabet", ch);
        this.c = ch;
    }

    public final void a(StringBuilder sb, byte[] bArr, int i, int i2) {
        DX.a(i, i + i2, bArr.length);
        if (i2 > this.b.f) {
            j2d.a();
            return;
        }
        int i3 = 0;
        long j = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            j = (j | ((long) (bArr[i + i4] & 255))) << 8;
        }
        int i5 = ((i2 + 1) * 8) - this.b.d;
        while (i3 < i2 * 8) {
            C2741u5 c2741u5 = this.b;
            sb.append(c2741u5.b[((int) (j >>> (i5 - i3))) & c2741u5.c]);
            i3 += this.b.d;
        }
        if (this.c != null) {
            while (i3 < this.b.f * 8) {
                sb.append(this.c.charValue());
                i3 += this.b.d;
            }
        }
    }

    public final boolean equals(Object obj) {
        if (obj instanceof C2998x5) {
            C2998x5 c2998x5 = (C2998x5) obj;
            if (this.b.equals(c2998x5.b) && Objects.equals(this.c, c2998x5.c)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(this.c) ^ this.b.hashCode();
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("BaseEncoding.");
        sb.append(this.b);
        if (8 % this.b.d != 0) {
            if (this.c == null) {
                sb.append(".omitPadding()");
            } else {
                sb.append(".withPadChar('");
                sb.append(this.c);
                sb.append("')");
            }
        }
        return sb.toString();
    }

    @Override // com.android.tools.r8.internal.AbstractC3083y5
    public void a(StringBuilder sb, byte[] bArr, int i) {
        int i2 = 0;
        DX.a(0, i, bArr.length);
        while (i2 < i) {
            a(sb, bArr, i2, Math.min(this.b.f, i - i2));
            i2 += this.b.f;
        }
    }

    public AbstractC3083y5 a(C2741u5 c2741u5) {
        return new C2998x5(c2741u5, null);
    }
}
