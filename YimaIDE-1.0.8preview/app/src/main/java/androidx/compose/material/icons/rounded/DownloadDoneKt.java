package androidx.compose.material.icons.rounded;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.graphics.vector.VectorKt;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_downloadDone", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DownloadDone", "Landroidx/compose/material/icons/Icons$Rounded;", "getDownloadDone", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DownloadDoneKt {
    private static ImageVector _downloadDone;

    public static final ImageVector getDownloadDone(Icons.Rounded rounded) {
        ImageVector imageVector = _downloadDone;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.DownloadDone", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(6.0f, 18.0f);
        pathBuilder.horizontalLineToRelative(12.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, 0.45f, 1.0f, 1.0f);
        pathBuilder.reflectiveCurveToRelative(-0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilder.lineTo(6.0f, 20.0f);
        pathBuilder.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilder.reflectiveCurveToRelative(0.45f, -1.0f, 1.0f, -1.0f);
        pathBuilder.close();
        pathBuilder.moveTo(11.01f, 13.9f);
        pathBuilder.curveToRelative(-0.78f, 0.77f, -2.04f, 0.77f, -2.82f, -0.01f);
        pathBuilder.lineTo(6.0f, 11.7f);
        pathBuilder.curveToRelative(-0.55f, -0.55f, -0.54f, -1.44f, 0.03f, -1.97f);
        pathBuilder.curveToRelative(0.54f, -0.52f, 1.4f, -0.5f, 1.92f, 0.02f);
        pathBuilder.lineTo(9.6f, 11.4f);
        pathBuilder.lineToRelative(6.43f, -6.43f);
        pathBuilder.curveToRelative(0.54f, -0.54f, 1.41f, -0.54f, 1.95f, 0.0f);
        pathBuilder.lineToRelative(0.04f, 0.04f);
        pathBuilder.curveToRelative(0.54f, 0.54f, 0.54f, 1.42f, -0.01f, 1.96f);
        pathBuilder.lineToRelative(-7.0f, 6.93f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _downloadDone = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
