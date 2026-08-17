package com.android.tools.r8.internal;

import com.reandroid.arsc.chunk.TypeBlock;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class VH {

    public static final class a extends VH {
        public final String a;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public a(String str) {
            super(0);
            KB.c(str, TypeBlock.NAME_name);
            this.a = str;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof a) && KB.a((Object) this.a, (Object) ((a) obj).a);
        }

        public final int hashCode() {
            return this.a.hashCode();
        }

        public final String toString() {
            return "TypeAlias(name=" + this.a + ')';
        }
    }

    public static final class b extends VH {
        public final int a;

        public b(int i) {
            super(0);
            this.a = i;
        }

        public final int a() {
            return this.a;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof b) && this.a == ((b) obj).a;
        }

        public final int hashCode() {
            return Integer.hashCode(this.a);
        }

        public final String toString() {
            return "TypeParameter(id=" + this.a + ')';
        }
    }

    public /* synthetic */ VH(int i) {
        this();
    }

    private VH() {
    }
}
