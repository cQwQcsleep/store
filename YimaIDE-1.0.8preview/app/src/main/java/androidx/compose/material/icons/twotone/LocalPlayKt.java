package androidx.compose.material.icons.twotone;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localPlay", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalPlay", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocalPlay", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalPlayKt {
    private static ImageVector _localPlay;

    public static final ImageVector getLocalPlay(Icons.TwoTone twoTone) {
        ImageVector imageVector = _localPlay;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LocalPlay", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(4.01f, 8.54f);
        pathBuilder.curveTo(5.2f, 9.23f, 6.0f, 10.52f, 6.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(-0.81f, 2.77f, -2.0f, 3.46f);
        pathBuilder.lineTo(4.0f, 18.0f);
        pathBuilder.horizontalLineToRelative(16.0f);
        pathBuilder.verticalLineToRelative(-2.54f);
        pathBuilder.curveToRelative(-1.19f, -0.69f, -2.0f, -1.99f, -2.0f, -3.46f);
        pathBuilder.reflectiveCurveToRelative(0.81f, -2.77f, 2.0f, -3.46f);
        pathBuilder.lineTo(20.0f, 6.0f);
        pathBuilder.lineTo(4.0f, 6.0f);
        pathBuilder.lineToRelative(0.01f, 2.54f);
        pathBuilder.close();
        pathBuilder.moveTo(10.73f, 10.22f);
        pathBuilder.lineTo(12.0f, 7.0f);
        pathBuilder.lineToRelative(1.26f, 3.23f);
        pathBuilder.lineToRelative(3.47f, 0.2f);
        pathBuilder.lineToRelative(-2.69f, 2.2f);
        pathBuilder.lineToRelative(0.89f, 3.37f);
        pathBuilder.lineTo(12.0f, 14.12f);
        pathBuilder.lineTo(9.07f, 16.0f);
        pathBuilder.lineToRelative(0.88f, -3.37f);
        pathBuilder.lineToRelative(-2.69f, -2.2f);
        pathBuilder.lineToRelative(3.47f, -0.21f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(20.0f, 4.0f);
        pathBuilder2.lineTo(4.0f, 4.0f);
        pathBuilder2.curveToRelative(-1.1f, 0.0f, -1.99f, 0.9f, -1.99f, 2.0f);
        pathBuilder2.verticalLineToRelative(4.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 1.99f, 0.9f, 1.99f, 2.0f);
        pathBuilder2.reflectiveCurveToRelative(-0.89f, 2.0f, -2.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(4.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(16.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder2.verticalLineToRelative(-4.0f);
        pathBuilder2.curveToRelative(-1.1f, 0.0f, -2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder2.reflectiveCurveToRelative(0.9f, -2.0f, 2.0f, -2.0f);
        pathBuilder2.lineTo(22.0f, 6.0f);
        pathBuilder2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.0f, 8.54f);
        pathBuilder2.curveToRelative(-1.19f, 0.69f, -2.0f, 1.99f, -2.0f, 3.46f);
        pathBuilder2.reflectiveCurveToRelative(0.81f, 2.77f, 2.0f, 3.46f);
        pathBuilder2.lineTo(20.0f, 18.0f);
        pathBuilder2.lineTo(4.0f, 18.0f);
        pathBuilder2.verticalLineToRelative(-2.54f);
        pathBuilder2.curveToRelative(1.19f, -0.69f, 2.0f, -1.99f, 2.0f, -3.46f);
        pathBuilder2.curveToRelative(0.0f, -1.48f, -0.8f, -2.77f, -1.99f, -3.46f);
        pathBuilder2.lineTo(4.0f, 6.0f);
        pathBuilder2.horizontalLineToRelative(16.0f);
        pathBuilder2.verticalLineToRelative(2.54f);
        pathBuilder2.close();
        pathBuilder2.moveTo(9.07f, 16.0f);
        pathBuilder2.lineTo(12.0f, 14.12f);
        pathBuilder2.lineTo(14.93f, 16.0f);
        pathBuilder2.lineToRelative(-0.89f, -3.36f);
        pathBuilder2.lineToRelative(2.69f, -2.2f);
        pathBuilder2.lineToRelative(-3.47f, -0.21f);
        pathBuilder2.lineTo(12.0f, 7.0f);
        pathBuilder2.lineToRelative(-1.27f, 3.22f);
        pathBuilder2.lineToRelative(-3.47f, 0.21f);
        pathBuilder2.lineToRelative(2.69f, 2.2f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _localPlay = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
