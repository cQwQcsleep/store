package com.android.tools.r8.internal;

import java.io.Reader;

/* JADX INFO: renamed from: com.android.tools.r8.internal.rb, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2522rb extends AbstractC2693tb {
    public final CharSequence a;

    static {
        C1928kd0.a("\r\n|\n|\r");
    }

    public C2522rb(CharSequence charSequence) {
        charSequence.getClass();
        this.a = charSequence;
    }

    public Reader a() {
        return new C2437qb(this.a);
    }

    /* JADX WARN: Code duplicated, block: B:7:0x001f A[PHI: r4
      0x001f: PHI (r4v2 java.lang.CharSequence) = (r4v11 java.lang.CharSequence), (r4v12 java.lang.CharSequence) binds: [B:3:0x0012, B:5:0x001c] A[DONT_GENERATE, DONT_INLINE]] */
    public final String toString() {
        String string;
        String string2;
        StringBuilder sb = new StringBuilder("CharSource.wrap(");
        CharSequence charSequence = this.a;
        charSequence.getClass();
        int length = charSequence.length();
        CharSequence charSequence2 = charSequence;
        if (length <= 30) {
            string2 = charSequence.toString();
            if (string2.length() > 30) {
                string = string2;
                charSequence2 = string2;
                StringBuilder sb2 = new StringBuilder(30);
                sb2.append(charSequence2, 0, 27);
                sb2.append("...");
                string = sb2.toString();
            }
        } else {
            string = string2;
            charSequence2 = string2;
            StringBuilder sb3 = new StringBuilder(30);
            sb3.append(charSequence2, 0, 27);
            sb3.append("...");
            string = sb3.toString();
        }
        string = string2;
        charSequence2 = string2;
        sb.append(string);
        sb.append(")");
        return sb.toString();
    }
}
