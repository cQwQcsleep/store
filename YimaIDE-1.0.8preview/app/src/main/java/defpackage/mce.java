package defpackage;

import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public enum mce {
    OFF("不思考"),
    STANDARD("思考-高"),
    DEEP("思考-MAX");

    public static final /* synthetic */ EnumEntries g = EnumEntriesKt.enumEntries(b());
    public final String b;

    mce(String str) {
        this.b = str;
    }

    public final String d() {
        return this.b;
    }

    public final boolean g() {
        return this != OFF;
    }
}
