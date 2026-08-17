package com.android.tools.r8.internal;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: com.android.tools.r8.internal.vc, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class EnumC2866vc {
    public static final /* synthetic */ C3059xn c = new C3059xn(new EnumC2866vc[]{new EnumC2866vc(0, 0, "CLASS"), new EnumC2866vc(1, 1, "INTERFACE"), new EnumC2866vc(2, 2, "ENUM_CLASS"), new EnumC2866vc(3, 3, "ENUM_ENTRY"), new EnumC2866vc(4, 4, "ANNOTATION_CLASS"), new EnumC2866vc(5, 5, "OBJECT"), new EnumC2866vc(6, 6, "COMPANION_OBJECT")});
    public final C2464qq b;

    public EnumC2866vc(int i, int i2, String str) {
        super(str, i);
        C2634sq c2634sq = AbstractC2805uq.e;
        KB.b(c2634sq, "CLASS_KIND");
        this.b = new C2464qq(c2634sq, i2);
    }
}
