package com.shadow.kotlin;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class Unit {
    public final /* synthetic */ int a;
    public static final Unit INSTANCE$1 = new Unit(1);
    public static final Unit INSTANCE = new Unit(0);

    public /* synthetic */ Unit(int i) {
        this.a = i;
    }

    public String toString() {
        switch (this.a) {
            case 0:
                return "kotlin.Unit";
            default:
                return super.toString();
        }
    }
}
