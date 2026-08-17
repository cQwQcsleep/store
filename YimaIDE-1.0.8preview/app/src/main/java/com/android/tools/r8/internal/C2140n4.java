package com.android.tools.r8.internal;

import com.android.tools.r8.internal.C2140n4;
import com.android.tools.r8.profile.art.ArtProfileMethodRuleInfo;
import com.android.tools.r8.profile.art.ArtProfileMethodRuleInfoBuilder;
import java.util.function.IntFunction;

/* JADX INFO: renamed from: com.android.tools.r8.internal.n4, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C2140n4 implements ArtProfileMethodRuleInfo {
    public static final C2140n4[] b = (C2140n4[]) R3.a((Object[]) new C2140n4[8], new IntFunction() { // from class: xph
        @Override // java.util.function.IntFunction
        public final Object apply(int i) {
            return new C2140n4(i);
        }
    });
    public final int a;

    public C2140n4(int i) {
        this.a = i;
    }

    public static a a() {
        return new a();
    }

    public static C2140n4 b() {
        return b[0];
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        return obj != null && getClass() == obj.getClass() && this.a == ((C2140n4) obj).a;
    }

    public final int hashCode() {
        return this.a;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleInfo
    public boolean isHot() {
        return (this.a & 1) != 0;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleInfo
    public boolean isPostStartup() {
        return (this.a & 4) != 0;
    }

    @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleInfo
    public boolean isStartup() {
        return (this.a & 2) != 0;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        if (isHot()) {
            sb.append('H');
        }
        if (isStartup()) {
            sb.append('S');
        }
        if (isPostStartup()) {
            sb.append('P');
        }
        return sb.toString();
    }

    /* JADX INFO: renamed from: com.android.tools.r8.internal.n4$a */
    public static class a implements ArtProfileMethodRuleInfoBuilder {
        public static final /* synthetic */ boolean b = true;
        public int a;

        public C2140n4 a() {
            boolean z = b;
            if (!z && this.a < 0) {
                x1f.a();
                return null;
            }
            if (z || this.a < C2140n4.b.length) {
                return C2140n4.b[this.a];
            }
            x1f.a();
            return null;
        }

        public a b() {
            this.a |= 1;
            return this;
        }

        public a c() {
            this.a |= 4;
            return this;
        }

        public a d() {
            this.a |= 2;
            return this;
        }

        @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleInfoBuilder
        public final ArtProfileMethodRuleInfoBuilder setIsHot(boolean z) {
            int i = this.a;
            this.a = z ? i | 1 : i & (-2);
            return this;
        }

        @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleInfoBuilder
        public final ArtProfileMethodRuleInfoBuilder setIsPostStartup(boolean z) {
            int i = this.a;
            this.a = z ? i | 4 : i & (-5);
            return this;
        }

        @Override // com.android.tools.r8.profile.art.ArtProfileMethodRuleInfoBuilder
        public final ArtProfileMethodRuleInfoBuilder setIsStartup(boolean z) {
            int i = this.a;
            this.a = z ? i | 2 : i & (-3);
            return this;
        }

        public final void a(C2140n4 c2140n4) {
            if (c2140n4.isHot()) {
                b();
            }
            if (c2140n4.isStartup()) {
                d();
            }
            if (c2140n4.isPostStartup()) {
                c();
            }
        }
    }
}
