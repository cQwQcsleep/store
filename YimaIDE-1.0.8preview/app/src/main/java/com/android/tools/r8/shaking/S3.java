package com.android.tools.r8.shaking;

import com.android.tools.r8.internal.Kk0;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class S3 {

    public static class b extends S3 {
        public final String a;
        public String b = null;

        public b(String str) {
            this.a = str;
        }

        @Override // com.android.tools.r8.shaking.S3
        public final synchronized void a(String str) {
            this.b = str;
        }

        @Override // com.android.tools.r8.shaking.S3
        public final b b() {
            return this;
        }

        @Override // com.android.tools.r8.shaking.S3
        public final synchronized void c() {
            this.b = null;
        }

        @Override // com.android.tools.r8.shaking.S3
        public final boolean e() {
            return true;
        }

        @Override // com.android.tools.r8.shaking.S3
        /* JADX INFO: renamed from: g, reason: merged with bridge method [inline-methods] */
        public final b f() {
            if (this.b == null) {
                return this;
            }
            b bVar = new b(this.a);
            String str = this.b;
            synchronized (bVar) {
                bVar.b = str;
            }
            return bVar;
        }

        public final String toString() {
            return this.a;
        }
    }

    public a a() {
        return null;
    }

    public abstract void a(String str);

    public b b() {
        return null;
    }

    public abstract void c();

    public boolean d() {
        return false;
    }

    public boolean e() {
        return false;
    }

    public abstract S3 f();

    public static class a extends S3 {
        b a;
        final int b;

        public a(int i) {
            this.b = i;
        }

        @Override // com.android.tools.r8.shaking.S3
        public final void a(String str) {
            throw new Kk0("A back reference refers back to a previously matched wildcard.");
        }

        @Override // com.android.tools.r8.shaking.S3
        public final void c() {
        }

        @Override // com.android.tools.r8.shaking.S3
        public final boolean d() {
            return true;
        }

        @Override // com.android.tools.r8.shaking.S3
        public final S3 f() {
            String str;
            b bVar = this.a;
            if (bVar != null) {
                synchronized (bVar) {
                    str = bVar.b;
                }
                if (str != null) {
                    a aVar = new a(this.b);
                    aVar.a(this.a.f());
                    return aVar;
                }
            }
            return this;
        }

        public final String g() {
            String str;
            b bVar = this.a;
            if (bVar == null) {
                return null;
            }
            synchronized (bVar) {
                str = bVar.b;
            }
            return str;
        }

        public final String toString() {
            return "<" + this.b + ">";
        }

        public void a(b bVar) {
            this.a = bVar;
        }

        @Override // com.android.tools.r8.shaking.S3
        public final a a() {
            return this;
        }
    }
}
