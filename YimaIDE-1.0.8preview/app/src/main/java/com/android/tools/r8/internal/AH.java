package com.android.tools.r8.internal;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AH {

    public static final class a extends AH {
        public final List a;

        public a(ArrayList arrayList) {
            this.a = arrayList;
        }

        public final List<AH> a() {
            return this.a;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof a) && KB.a(this.a, ((a) obj).a);
        }

        public final int hashCode() {
            return this.a.hashCode();
        }

        @Override // com.android.tools.r8.internal.AH
        public final String toString() {
            return "ArrayValue(" + this.a + ')';
        }
    }

    public abstract String toString();
}
