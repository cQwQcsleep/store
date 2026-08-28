package j$.time.format;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: /workspace/unpacked/classes3.dex */
final class l implements g {
    public static final l INSENSITIVE;
    public static final l LENIENT;
    public static final l SENSITIVE;
    public static final l STRICT;
    private static final /* synthetic */ l[] a;

    @Override // j$.time.format.g
    public final boolean j(q qVar, StringBuilder sb) {
        return true;
    }

    public static l valueOf(String str) {
        return (l) Enum.valueOf(l.class, str);
    }

    public static l[] values() {
        return (l[]) a.clone();
    }

    static {
        l lVar = new l("SENSITIVE", 0);
        SENSITIVE = lVar;
        l lVar2 = new l("INSENSITIVE", 1);
        INSENSITIVE = lVar2;
        l lVar3 = new l("STRICT", 2);
        STRICT = lVar3;
        l lVar4 = new l("LENIENT", 3);
        LENIENT = lVar4;
        a = new l[]{lVar, lVar2, lVar3, lVar4};
    }

    @Override // java.lang.Enum
    public final String toString() {
        int iOrdinal = ordinal();
        if (iOrdinal == 0) {
            return "ParseCaseSensitive(true)";
        }
        if (iOrdinal == 1) {
            return "ParseCaseSensitive(false)";
        }
        if (iOrdinal == 2) {
            return "ParseStrict(true)";
        }
        if (iOrdinal == 3) {
            return "ParseStrict(false)";
        }
        throw new IllegalStateException("Unreachable");
    }
}
