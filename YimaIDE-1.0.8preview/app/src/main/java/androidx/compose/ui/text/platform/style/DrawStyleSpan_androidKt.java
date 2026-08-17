package androidx.compose.ui.text.platform.style;

import android.graphics.Paint;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0013\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0013\u0010\u0005\u001a\u00020\u0006*\u00020\u0007H\u0000¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"toAndroidJoin", "Landroid/graphics/Paint$Join;", "Landroidx/compose/ui/graphics/StrokeJoin;", "toAndroidJoin-Ww9F2mQ", "(I)Landroid/graphics/Paint$Join;", "toAndroidCap", "Landroid/graphics/Paint$Cap;", "Landroidx/compose/ui/graphics/StrokeCap;", "toAndroidCap-BeK7IIE", "(I)Landroid/graphics/Paint$Cap;", "ui-text"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DrawStyleSpan_androidKt {
    /* JADX INFO: renamed from: toAndroidCap-BeK7IIE, reason: not valid java name */
    public static final Paint.Cap m5758toAndroidCapBeK7IIE(int i) {
        StrokeCap.Companion companion = StrokeCap.INSTANCE;
        if (StrokeCap.m3504equalsimpl0(i, companion.m3508getButtKaPHkGw())) {
            return Paint.Cap.BUTT;
        }
        if (StrokeCap.m3504equalsimpl0(i, companion.m3509getRoundKaPHkGw())) {
            return Paint.Cap.ROUND;
        }
        return StrokeCap.m3504equalsimpl0(i, companion.m3510getSquareKaPHkGw()) ? Paint.Cap.SQUARE : Paint.Cap.BUTT;
    }

    /* JADX INFO: renamed from: toAndroidJoin-Ww9F2mQ, reason: not valid java name */
    public static final Paint.Join m5759toAndroidJoinWw9F2mQ(int i) {
        StrokeJoin.Companion companion = StrokeJoin.INSTANCE;
        if (StrokeJoin.m3514equalsimpl0(i, companion.m3519getMiterLxFBmk8())) {
            return Paint.Join.MITER;
        }
        if (StrokeJoin.m3514equalsimpl0(i, companion.m3520getRoundLxFBmk8())) {
            return Paint.Join.ROUND;
        }
        return StrokeJoin.m3514equalsimpl0(i, companion.m3518getBevelLxFBmk8()) ? Paint.Join.BEVEL : Paint.Join.MITER;
    }
}
