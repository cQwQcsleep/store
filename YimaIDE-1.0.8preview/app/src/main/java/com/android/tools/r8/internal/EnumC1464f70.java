package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.f70, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC1464f70 implements InterfaceC1046aB {
    public static final EnumC1464f70 c = new EnumC1464f70(0, 0, "NONE");
    public static final EnumC1464f70 d = new EnumC1464f70(1, 1, "PUBLIC");
    public static final EnumC1464f70 e = new EnumC1464f70(2, 2, "SYSTEM");
    public static final EnumC1464f70 f = new EnumC1464f70(3, 3, "VENDOR");
    public static final EnumC1464f70 g = new EnumC1464f70(4, 4, "PRODUCT");
    public static final EnumC1464f70 h = new EnumC1464f70(5, 5, "SIGNATURE");
    public static final EnumC1464f70 i = new EnumC1464f70(6, 6, "ODM");
    public static final EnumC1464f70 j = new EnumC1464f70(7, 7, "OEM");
    public static final EnumC1464f70 k = new EnumC1464f70(8, 8, "ACTOR");
    public static final EnumC1464f70 l = new EnumC1464f70(9, 9, "CONFIG_SIGNATURE");
    public static final EnumC1464f70 m = new EnumC1464f70(10, -1, "UNRECOGNIZED");
    public final int b;

    public EnumC1464f70(int i2, int i3, String str) {
        super(str, i2);
        this.b = i3;
    }

    @Override // com.android.tools.r8.internal.InterfaceC1046aB
    public final int a() {
        if (this != m) {
            return this.b;
        }
        w01.a("Can't get the number of an unknown enum value.");
        return 0;
    }
}
