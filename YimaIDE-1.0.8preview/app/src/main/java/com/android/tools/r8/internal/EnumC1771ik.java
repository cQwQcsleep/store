package com.android.tools.r8.internal;

/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX WARN: Unknown enum class pattern. Please report as an issue! */
/* JADX INFO: renamed from: com.android.tools.r8.internal.ik, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC1771ik {
    public static final EnumC1771ik c;
    public static final EnumC1771ik d;
    public static final EnumC1771ik e;
    public static final EnumC1771ik f;
    public static final EnumC1771ik g;
    public static final EnumC1771ik[] h;
    public final EnumC1686hk b;

    static {
        EnumC1771ik enumC1771ik = new EnumC1771ik("DOUBLE", 0, EnumC1686hk.f);
        EnumC1771ik enumC1771ik2 = new EnumC1771ik("FLOAT", 1, EnumC1686hk.e);
        EnumC1686hk enumC1686hk = EnumC1686hk.d;
        EnumC1771ik enumC1771ik3 = new EnumC1771ik("INT64", 2, enumC1686hk);
        EnumC1771ik enumC1771ik4 = new EnumC1771ik("UINT64", 3, enumC1686hk);
        EnumC1686hk enumC1686hk2 = EnumC1686hk.c;
        EnumC1771ik enumC1771ik5 = new EnumC1771ik("INT32", 4, enumC1686hk2);
        EnumC1771ik enumC1771ik6 = new EnumC1771ik("FIXED64", 5, enumC1686hk);
        EnumC1771ik enumC1771ik7 = new EnumC1771ik("FIXED32", 6, enumC1686hk2);
        EnumC1771ik enumC1771ik8 = new EnumC1771ik("BOOL", 7, EnumC1686hk.g);
        EnumC1771ik enumC1771ik9 = new EnumC1771ik("STRING", 8, EnumC1686hk.h);
        c = enumC1771ik9;
        EnumC1686hk enumC1686hk3 = EnumC1686hk.k;
        EnumC1771ik enumC1771ik10 = new EnumC1771ik("GROUP", 9, enumC1686hk3);
        d = enumC1771ik10;
        EnumC1771ik enumC1771ik11 = new EnumC1771ik("MESSAGE", 10, enumC1686hk3);
        e = enumC1771ik11;
        EnumC1771ik enumC1771ik12 = new EnumC1771ik("BYTES", 11, EnumC1686hk.i);
        f = enumC1771ik12;
        EnumC1771ik enumC1771ik13 = new EnumC1771ik("UINT32", 12, enumC1686hk2);
        EnumC1771ik enumC1771ik14 = new EnumC1771ik("ENUM", 13, EnumC1686hk.j);
        g = enumC1771ik14;
        h = (EnumC1771ik[]) new EnumC1771ik[]{enumC1771ik, enumC1771ik2, enumC1771ik3, enumC1771ik4, enumC1771ik5, enumC1771ik6, enumC1771ik7, enumC1771ik8, enumC1771ik9, enumC1771ik10, enumC1771ik11, enumC1771ik12, enumC1771ik13, enumC1771ik14, new EnumC1771ik("SFIXED32", 14, enumC1686hk2), new EnumC1771ik("SFIXED64", 15, enumC1686hk), new EnumC1771ik("SINT32", 16, enumC1686hk2), new EnumC1771ik("SINT64", 17, enumC1686hk)}.clone();
    }

    public EnumC1771ik(String str, int i, EnumC1686hk enumC1686hk) {
        super(str, i);
        this.b = enumC1686hk;
    }
}
