package com.android.tools.r8;

import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.references.MethodReference;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class AssertionsConfiguration {
    static final /* synthetic */ boolean e = true;
    private final int a;
    private final MethodReference b;
    private final a c;
    private final String d;

    public static class Builder {
        final C2742u50 a;
        private int b;
        private MethodReference c;
        private a d;
        private String e;

        private Builder(C2742u50 c2742u50) {
            this.a = c2742u50;
        }

        public static AssertionsConfiguration compileTimeDisableAllAssertions(Builder builder) {
            return builder.setCompileTimeDisable().setScopeAll().build();
        }

        public static AssertionsConfiguration compileTimeEnableAllAssertions(Builder builder) {
            return builder.setCompileTimeEnable().setScopeAll().build();
        }

        public static AssertionsConfiguration enableAllAssertions(Builder builder) {
            return compileTimeEnableAllAssertions(builder);
        }

        public static AssertionsConfiguration passthroughAllAssertions(Builder builder) {
            return builder.setPassthrough().setScopeAll().build();
        }

        public AssertionsConfiguration build() {
            if (this.b == 0 && this.c == null) {
                this.a.a("No transformation or assertion handler specified for building AssertionConfiguration");
            }
            if (this.d == null) {
                this.a.a("No scope specified for building AssertionConfiguration");
            }
            if (this.d == a.c && this.e == null) {
                this.a.a("No package name specified for building AssertionConfiguration");
            }
            if (this.d == a.d && this.e == null) {
                this.a.a("No class name specified for building AssertionConfiguration");
            }
            return new AssertionsConfiguration(this.b, this.c, this.d, this.e);
        }

        public Builder setAssertionHandler(MethodReference methodReference) {
            this.b = 0;
            this.c = methodReference;
            return this;
        }

        public Builder setCompileTimeDisable() {
            this.b = 2;
            this.c = null;
            return this;
        }

        public Builder setCompileTimeEnable() {
            this.b = 1;
            this.c = null;
            return this;
        }

        public Builder setPassthrough() {
            this.b = 3;
            this.c = null;
            return this;
        }

        public Builder setScopeAll() {
            this.d = a.b;
            this.e = null;
            return this;
        }

        public Builder setScopeClass(String str) {
            this.d = a.d;
            this.e = str;
            return this;
        }

        public Builder setScopePackage(String str) {
            this.d = a.c;
            this.e = str;
            return this;
        }
    }

    public enum a {
        b,
        c,
        d;

        a() {
        }
    }

    public AssertionsConfiguration(int i, MethodReference methodReference, a aVar, String str) {
        this.a = i;
        this.b = methodReference;
        this.c = aVar;
        this.d = str;
        if (e) {
            return;
        }
        boolean z = i != 0;
        boolean z2 = methodReference != null;
        if (!z || z2) {
            if (z || !z2) {
                x1f.a();
                throw null;
            }
        }
    }

    public static Builder a(C2742u50 c2742u50) {
        return new Builder(c2742u50);
    }

    public MethodReference getAssertionHandler() {
        return this.b;
    }

    public a getScope() {
        return this.c;
    }

    public String getValue() {
        return this.d;
    }

    public boolean isAssertionHandler() {
        return this.b != null;
    }

    public boolean isCompileTimeDisabled() {
        return this.a == 2;
    }

    public boolean isCompileTimeEnabled() {
        return this.a == 1;
    }

    public boolean isPassthrough() {
        return this.a == 3;
    }
}
