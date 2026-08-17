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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_taxiAlert", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TaxiAlert", "Landroidx/compose/material/icons/Icons$Rounded;", "getTaxiAlert", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TaxiAlertKt {
    private static ImageVector _taxiAlert;

    public static final ImageVector getTaxiAlert(Icons.Rounded rounded) {
        ImageVector imageVector = _taxiAlert;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TaxiAlert", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.0f, 13.0f);
        pathBuilder.curveToRelative(-1.91f, 0.0f, -3.63f, -0.76f, -4.89f, -2.0f);
        pathBuilder.horizontalLineTo(4.81f);
        pathBuilder.lineToRelative(1.04f, -3.0f);
        pathBuilder.horizontalLineToRelative(5.44f);
        pathBuilder.curveTo(11.1f, 7.37f, 11.0f, 6.7f, 11.0f, 6.0f);
        pathBuilder.reflectiveCurveToRelative(0.1f, -1.37f, 0.29f, -2.0f);
        pathBuilder.horizontalLineTo(9.0f);
        pathBuilder.curveTo(8.45f, 4.0f, 8.0f, 4.45f, 8.0f, 5.0f);
        pathBuilder.verticalLineToRelative(1.0f);
        pathBuilder.horizontalLineTo(5.5f);
        pathBuilder.curveTo(4.84f, 6.0f, 4.29f, 6.42f, 4.08f, 7.01f);
        pathBuilder.lineTo(2.0f, 13.0f);
        pathBuilder.verticalLineToRelative(7.5f);
        pathBuilder.curveTo(2.0f, 21.32f, 2.67f, 22.0f, 3.5f, 22.0f);
        pathBuilder.reflectiveCurveTo(5.0f, 21.32f, 5.0f, 20.5f);
        pathBuilder.verticalLineTo(20.0f);
        pathBuilder.horizontalLineToRelative(12.0f);
        pathBuilder.verticalLineToRelative(0.5f);
        pathBuilder.curveToRelative(0.0f, 0.82f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilder.reflectiveCurveToRelative(1.5f, -0.68f, 1.5f, -1.5f);
        pathBuilder.verticalLineTo(13.0f);
        pathBuilder.lineToRelative(-0.09f, -0.27f);
        pathBuilder.curveTo(19.3f, 12.9f, 18.66f, 13.0f, 18.0f, 13.0f);
        pathBuilder.close();
        pathBuilder.moveTo(6.5f, 17.0f);
        pathBuilder.curveTo(5.67f, 17.0f, 5.0f, 16.33f, 5.0f, 15.5f);
        pathBuilder.reflectiveCurveTo(5.67f, 14.0f, 6.5f, 14.0f);
        pathBuilder.reflectiveCurveTo(8.0f, 14.67f, 8.0f, 15.5f);
        pathBuilder.reflectiveCurveTo(7.33f, 17.0f, 6.5f, 17.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.5f, 17.0f);
        pathBuilder.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilder.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilder.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilder.reflectiveCurveTo(16.33f, 17.0f, 15.5f, 17.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(18.0f, 1.0f);
        pathBuilder2.curveToRelative(-2.76f, 0.0f, -5.0f, 2.24f, -5.0f, 5.0f);
        pathBuilder2.reflectiveCurveToRelative(2.24f, 5.0f, 5.0f, 5.0f);
        pathBuilder2.reflectiveCurveToRelative(5.0f, -2.24f, 5.0f, -5.0f);
        pathBuilder2.reflectiveCurveTo(20.76f, 1.0f, 18.0f, 1.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(18.0f, 9.0f);
        pathBuilder2.curveToRelative(-0.28f, 0.0f, -0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder2.reflectiveCurveTo(17.72f, 8.0f, 18.0f, 8.0f);
        pathBuilder2.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilder2.reflectiveCurveTo(18.28f, 9.0f, 18.0f, 9.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(18.5f, 6.5f);
        pathBuilder2.curveTo(18.5f, 6.78f, 18.28f, 7.0f, 18.0f, 7.0f);
        pathBuilder2.reflectiveCurveToRelative(-0.5f, -0.22f, -0.5f, -0.5f);
        pathBuilder2.verticalLineToRelative(-3.0f);
        pathBuilder2.curveTo(17.5f, 3.22f, 17.72f, 3.0f, 18.0f, 3.0f);
        pathBuilder2.reflectiveCurveToRelative(0.5f, 0.22f, 0.5f, 0.5f);
        pathBuilder2.verticalLineTo(6.5f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _taxiAlert = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
