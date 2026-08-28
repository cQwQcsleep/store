package np.protect.assets.p;

import android.os.Build;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;

/* renamed from: np.protect.assets.p.۟۟۟ۡۢ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public final class C0038 {

    /* renamed from: ۟۟۟ۢۥ, reason: not valid java name and contains not printable characters */
    public static boolean f59 = false;

    /* renamed from: ۟۟۟ۢۦ, reason: not valid java name and contains not printable characters */
    public static boolean f60 = true;

    /* renamed from: ۟۟۟ۢۧ, reason: not valid java name and contains not printable characters */
    public static boolean f61 = false;

    /* renamed from: ۟۟۟ۢۨ, reason: not valid java name and contains not printable characters */
    public static boolean f62 = true;

    /* renamed from: ۣ۟۟۟, reason: not valid java name and contains not printable characters */
    public static boolean f63 = true;

    /* renamed from: ۣ۟۟۟۟, reason: not valid java name and contains not printable characters */
    public static boolean f64;

    /* renamed from: ۣ۟۟۟۠, reason: not valid java name and contains not printable characters */
    public static Pine.InterfaceC0051 f65 = new Pine.InterfaceC0051() { // from class: np.protect.assets.p.۟۟۟ۡۢ.1
        @Override // top.canyie.pine.Pine.InterfaceC0051
        public void loadLib() {
            System.loadLibrary(NPStringFog.decode("1E190304"));
        }
    };

    /* renamed from: ۣ۟۟۟ۡ, reason: not valid java name and contains not printable characters */
    public static int f66;

    /* renamed from: ۣ۟۟۟ۢ, reason: not valid java name and contains not printable characters */
    public static boolean f67;

    static {
        int i = Build.VERSION.SDK_INT;
        f66 = i;
        if (i != 30 || Build.VERSION.PREVIEW_SDK_INT <= 0) {
            return;
        }
        f66 = 31;
    }
}
