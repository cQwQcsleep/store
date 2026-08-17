package kotlin.jvm.internal;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\t\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\u0017\u001a\u00020\u0005H\u0086\u0080\u0004J\n\u0010\u0018\u001a\u00020\u0005H\u0086\u0080\u0004J\n\u0010\u0019\u001a\u00020\u0005H\u0086\u0080\u0004J\n\u0010\u001a\u001a\u00020\u0005H\u0086\u0080\u0004J\n\u0010\u001b\u001a\u00020\u0005H\u0086\u0080\u0004R%\u0010\u0004\u001a\u00020\u00058\u0006X\u0087Ô\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0003R%\u0010\n\u001a\u00020\u00058\u0006X\u0087Ô\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\b\n\u0000\u0012\u0004\b\u000b\u0010\u0003R%\u0010\f\u001a\u00020\u00058\u0006X\u0087Ô\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\b\n\u0000\u0012\u0004\b\r\u0010\u0003R%\u0010\u000e\u001a\u00020\u00058\u0006X\u0087Ô\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\b\n\u0000\u0012\u0004\b\u000f\u0010\u0003R%\u0010\u0010\u001a\u00020\u00058\u0006X\u0087Ô\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\b\n\u0000\u0012\u0004\b\u0011\u0010\u0003R%\u0010\u0012\u001a\u00020\u00138\u0006X\u0087Ô\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\b\n\u0000\u0012\u0004\b\u0014\u0010\u0003R%\u0010\u0015\u001a\u00020\u00138\u0006X\u0087Ô\br\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t¢\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0003¨\u0006\u001c"}, d2 = {"Lkotlin/jvm/internal/DoubleCompanionObject;", "", "<init>", "()V", "MIN_VALUE", "", "getMIN_VALUE$annotations", "Lkotlin/SinceKotlin;", "version", "1.4", "MAX_VALUE", "getMAX_VALUE$annotations", "POSITIVE_INFINITY", "getPOSITIVE_INFINITY$annotations", "NEGATIVE_INFINITY", "getNEGATIVE_INFINITY$annotations", "NaN", "getNaN$annotations", "SIZE_BYTES", "", "getSIZE_BYTES$annotations", "SIZE_BITS", "getSIZE_BITS$annotations", "getMIN_VALUE", "getMAX_VALUE", "getPOSITIVE_INFINITY", "getNEGATIVE_INFINITY", "getNaN", "kotlin-stdlib"}, k = 1, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class DoubleCompanionObject {
    public static final DoubleCompanionObject INSTANCE = new DoubleCompanionObject();
    public static final double MAX_VALUE = Double.MAX_VALUE;
    public static final double MIN_VALUE = Double.MIN_VALUE;
    public static final double NEGATIVE_INFINITY = Double.NEGATIVE_INFINITY;
    public static final double NaN = Double.NaN;
    public static final double POSITIVE_INFINITY = Double.POSITIVE_INFINITY;
    public static final int SIZE_BITS = 64;
    public static final int SIZE_BYTES = 8;

    private DoubleCompanionObject() {
    }

    public static /* synthetic */ void getMAX_VALUE$annotations() {
    }

    public static /* synthetic */ void getMIN_VALUE$annotations() {
    }

    public static /* synthetic */ void getNEGATIVE_INFINITY$annotations() {
    }

    public static /* synthetic */ void getNaN$annotations() {
    }

    public static /* synthetic */ void getPOSITIVE_INFINITY$annotations() {
    }

    public static /* synthetic */ void getSIZE_BITS$annotations() {
    }

    public static /* synthetic */ void getSIZE_BYTES$annotations() {
    }

    public final double getMAX_VALUE() {
        return Double.MAX_VALUE;
    }

    public final double getMIN_VALUE() {
        return Double.MIN_VALUE;
    }

    public final double getNEGATIVE_INFINITY() {
        return Double.NEGATIVE_INFINITY;
    }

    public final double getNaN() {
        return Double.NaN;
    }

    public final double getPOSITIVE_INFINITY() {
        return Double.POSITIVE_INFINITY;
    }
}
