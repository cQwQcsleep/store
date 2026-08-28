package j$.util.stream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* loaded from: /workspace/unpacked/classes3.dex */
final class H3 {
    public static final H3 MAYBE_MORE;
    public static final H3 NO_MORE;
    public static final H3 UNLIMITED;
    private static final /* synthetic */ H3[] a;

    static {
        H3 h3 = new H3("NO_MORE", 0);
        NO_MORE = h3;
        H3 h32 = new H3("MAYBE_MORE", 1);
        MAYBE_MORE = h32;
        H3 h33 = new H3("UNLIMITED", 2);
        UNLIMITED = h33;
        a = new H3[]{h3, h32, h33};
    }

    public static H3 valueOf(String str) {
        return (H3) Enum.valueOf(H3.class, str);
    }

    public static H3[] values() {
        return (H3[]) a.clone();
    }
}
