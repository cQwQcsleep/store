package Ark;

/* loaded from: /workspace/unpacked/classes.dex */
public class VMProtect {
    static {
        System.loadLibrary("ArkSafe");
    }

    public static native void ArkSafeVM(int i);
}
