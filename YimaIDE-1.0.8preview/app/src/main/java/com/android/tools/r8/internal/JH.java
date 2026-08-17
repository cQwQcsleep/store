package com.android.tools.r8.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class JH extends AH {
    public abstract Object a();

    @Override // com.android.tools.r8.internal.AH
    public final String toString() {
        String string;
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append('(');
        if (this instanceof MH) {
            string = "\"" + ((MH) this).a + '\"';
        } else {
            string = a().toString();
        }
        sb.append(string);
        sb.append(')');
        return sb.toString();
    }
}
