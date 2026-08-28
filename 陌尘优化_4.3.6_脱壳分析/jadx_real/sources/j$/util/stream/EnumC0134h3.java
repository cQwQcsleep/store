package j$.util.stream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: j$.util.stream.h3, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class EnumC0134h3 {
    public static final EnumC0134h3 DOUBLE_VALUE;
    public static final EnumC0134h3 INT_VALUE;
    public static final EnumC0134h3 LONG_VALUE;
    public static final EnumC0134h3 REFERENCE;
    private static final /* synthetic */ EnumC0134h3[] a;

    public static EnumC0134h3 valueOf(String str) {
        return (EnumC0134h3) Enum.valueOf(EnumC0134h3.class, str);
    }

    public static EnumC0134h3[] values() {
        return (EnumC0134h3[]) a.clone();
    }

    static {
        EnumC0134h3 enumC0134h3 = new EnumC0134h3("REFERENCE", 0);
        REFERENCE = enumC0134h3;
        EnumC0134h3 enumC0134h32 = new EnumC0134h3("INT_VALUE", 1);
        INT_VALUE = enumC0134h32;
        EnumC0134h3 enumC0134h33 = new EnumC0134h3("LONG_VALUE", 2);
        LONG_VALUE = enumC0134h33;
        EnumC0134h3 enumC0134h34 = new EnumC0134h3("DOUBLE_VALUE", 3);
        DOUBLE_VALUE = enumC0134h34;
        a = new EnumC0134h3[]{enumC0134h3, enumC0134h32, enumC0134h33, enumC0134h34};
    }
}
