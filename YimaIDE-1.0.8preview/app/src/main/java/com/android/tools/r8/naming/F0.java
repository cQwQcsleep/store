package com.android.tools.r8.naming;

import com.android.tools.r8.position.Position;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class F0 implements Position {
    public final int a;

    public F0(int i) {
        this.a = i;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        return (obj instanceof F0) && this.a == ((F0) obj).a;
    }

    @Override // com.android.tools.r8.position.Position
    public final String getDescription() {
        return "line " + this.a;
    }

    public final int hashCode() {
        return this.a;
    }
}
