package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hdrPlus", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HdrPlus", "Landroidx/compose/material/icons/Icons$Sharp;", "getHdrPlus", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HdrPlusKt {
    private static ImageVector _hdrPlus;

    public static final ImageVector getHdrPlus(Icons.Sharp sharp) {
        ImageVector imageVector = _hdrPlus;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.HdrPlus", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(8.5f, 14.5f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineToRelative(1.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(14.5f, 7.5f);
        pathBuilder2.horizontalLineToRelative(1.5f);
        pathBuilder2.verticalLineToRelative(3.0f);
        pathBuilder2.horizontalLineToRelative(-1.5f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(12.0f, 2.0f);
        pathBuilder3.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilder3.reflectiveCurveToRelative(4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilder3.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilder3.reflectiveCurveTo(17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(12.0f, 17.0f);
        pathBuilder3.lineToRelative(-0.86f, -0.01f);
        pathBuilder3.lineTo(12.0f, 19.0f);
        pathBuilder3.horizontalLineToRelative(-1.5f);
        pathBuilder3.lineToRelative(-0.9f, -2.0f);
        pathBuilder3.horizontalLineTo(8.5f);
        pathBuilder3.verticalLineToRelative(2.0f);
        pathBuilder3.horizontalLineTo(7.0f);
        pathBuilder3.verticalLineToRelative(-6.0f);
        pathBuilder3.horizontalLineToRelative(5.0f);
        pathBuilder3.curveToRelative(0.0f, 0.0f, 0.0f, 0.7f, 0.0f, 1.5f);
        pathBuilder3.verticalLineTo(17.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(12.0f, 12.0f);
        pathBuilder3.horizontalLineToRelative(-1.5f);
        pathBuilder3.verticalLineTo(9.5f);
        pathBuilder3.horizontalLineToRelative(-2.0f);
        pathBuilder3.verticalLineTo(12.0f);
        pathBuilder3.horizontalLineTo(7.0f);
        pathBuilder3.verticalLineTo(6.0f);
        pathBuilder3.horizontalLineToRelative(1.5f);
        pathBuilder3.verticalLineToRelative(2.0f);
        pathBuilder3.horizontalLineToRelative(2.0f);
        pathBuilder3.verticalLineTo(6.0f);
        pathBuilder3.horizontalLineTo(12.0f);
        pathBuilder3.verticalLineTo(12.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(17.5f, 16.0f);
        pathBuilder3.horizontalLineTo(16.0f);
        pathBuilder3.verticalLineToRelative(1.5f);
        pathBuilder3.horizontalLineToRelative(-1.5f);
        pathBuilder3.verticalLineTo(16.0f);
        pathBuilder3.horizontalLineTo(13.0f);
        pathBuilder3.verticalLineToRelative(-1.5f);
        pathBuilder3.horizontalLineToRelative(1.5f);
        pathBuilder3.verticalLineTo(13.0f);
        pathBuilder3.horizontalLineTo(16.0f);
        pathBuilder3.verticalLineToRelative(1.49f);
        pathBuilder3.horizontalLineToRelative(1.5f);
        pathBuilder3.verticalLineTo(16.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(17.5f, 10.5f);
        pathBuilder3.curveToRelative(0.0f, 0.8f, -0.7f, 1.5f, -1.5f, 1.5f);
        pathBuilder3.horizontalLineToRelative(-3.0f);
        pathBuilder3.verticalLineTo(6.0f);
        pathBuilder3.horizontalLineToRelative(3.0f);
        pathBuilder3.curveToRelative(0.8f, 0.0f, 1.5f, 0.7f, 1.5f, 1.5f);
        pathBuilder3.verticalLineTo(10.5f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _hdrPlus = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
