package j$.util.stream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: j$.util.stream.f3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
final class EnumC0124f3 {
    public static final EnumC0124f3 OP;
    public static final EnumC0124f3 SPLITERATOR;
    public static final EnumC0124f3 STREAM;
    public static final EnumC0124f3 TERMINAL_OP;
    public static final EnumC0124f3 UPSTREAM_TERMINAL_OP;
    private static final /* synthetic */ EnumC0124f3[] a;

    public static EnumC0124f3 valueOf(String str) {
        return (EnumC0124f3) Enum.valueOf(EnumC0124f3.class, str);
    }

    public static EnumC0124f3[] values() {
        return (EnumC0124f3[]) a.clone();
    }

    static {
        EnumC0124f3 enumC0124f3 = new EnumC0124f3("SPLITERATOR", 0);
        SPLITERATOR = enumC0124f3;
        EnumC0124f3 enumC0124f32 = new EnumC0124f3("STREAM", 1);
        STREAM = enumC0124f32;
        EnumC0124f3 enumC0124f33 = new EnumC0124f3("OP", 2);
        OP = enumC0124f33;
        EnumC0124f3 enumC0124f34 = new EnumC0124f3("TERMINAL_OP", 3);
        TERMINAL_OP = enumC0124f34;
        EnumC0124f3 enumC0124f35 = new EnumC0124f3("UPSTREAM_TERMINAL_OP", 4);
        UPSTREAM_TERMINAL_OP = enumC0124f35;
        a = new EnumC0124f3[]{enumC0124f3, enumC0124f32, enumC0124f33, enumC0124f34, enumC0124f35};
    }
}
