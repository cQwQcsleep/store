package j$.util.stream;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* renamed from: j$.util.stream.i, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes3.dex */
public final class EnumC0135i {
    public static final EnumC0135i CONCURRENT;
    public static final EnumC0135i IDENTITY_FINISH;
    public static final EnumC0135i UNORDERED;
    private static final /* synthetic */ EnumC0135i[] a;

    public static EnumC0135i valueOf(String str) {
        return (EnumC0135i) Enum.valueOf(EnumC0135i.class, str);
    }

    public static EnumC0135i[] values() {
        return (EnumC0135i[]) a.clone();
    }

    static {
        EnumC0135i enumC0135i = new EnumC0135i("CONCURRENT", 0);
        CONCURRENT = enumC0135i;
        EnumC0135i enumC0135i2 = new EnumC0135i("UNORDERED", 1);
        UNORDERED = enumC0135i2;
        EnumC0135i enumC0135i3 = new EnumC0135i("IDENTITY_FINISH", 2);
        IDENTITY_FINISH = enumC0135i3;
        a = new EnumC0135i[]{enumC0135i, enumC0135i2, enumC0135i3};
    }
}
