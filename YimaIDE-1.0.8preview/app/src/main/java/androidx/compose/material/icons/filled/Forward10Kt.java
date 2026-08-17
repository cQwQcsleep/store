package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_forward10", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Forward10", "Landroidx/compose/material/icons/Icons$Filled;", "getForward10", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Forward10Kt {
    private static ImageVector _forward10;

    public static final ImageVector getForward10(Icons.Filled filled) {
        ImageVector imageVector = _forward10;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Forward10", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.0f, 13.0f);
        pathBuilder.curveToRelative(0.0f, 3.31f, -2.69f, 6.0f, -6.0f, 6.0f);
        pathBuilder.reflectiveCurveToRelative(-6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilder.reflectiveCurveToRelative(2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.lineToRelative(5.0f, -5.0f);
        pathBuilder.lineToRelative(-5.0f, -5.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilder.curveToRelative(0.0f, 4.42f, 3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilder.reflectiveCurveToRelative(8.0f, -3.58f, 8.0f, -8.0f);
        pathBuilder.horizontalLineTo(18.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(10.86f, 15.94f);
        pathBuilder2.lineToRelative(0.0f, -4.27f);
        pathBuilder2.lineToRelative(-0.09f, 0.0f);
        pathBuilder2.lineToRelative(-1.77f, 0.63f);
        pathBuilder2.lineToRelative(0.0f, 0.69f);
        pathBuilder2.lineToRelative(1.01f, -0.31f);
        pathBuilder2.lineToRelative(0.0f, 3.26f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(12.25f, 13.44f);
        pathBuilder3.verticalLineToRelative(0.74f);
        pathBuilder3.curveToRelative(0.0f, 1.9f, 1.31f, 1.82f, 1.44f, 1.82f);
        pathBuilder3.curveToRelative(0.14f, 0.0f, 1.44f, 0.09f, 1.44f, -1.82f);
        pathBuilder3.verticalLineToRelative(-0.74f);
        pathBuilder3.curveToRelative(0.0f, -1.9f, -1.31f, -1.82f, -1.44f, -1.82f);
        pathBuilder3.curveTo(13.55f, 11.62f, 12.25f, 11.53f, 12.25f, 13.44f);
        pathBuilder3.close();
        pathBuilder3.moveTo(14.29f, 13.32f);
        pathBuilder3.verticalLineToRelative(0.97f);
        pathBuilder3.curveToRelative(0.0f, 0.77f, -0.21f, 1.03f, -0.59f, 1.03f);
        pathBuilder3.curveToRelative(-0.38f, 0.0f, -0.6f, -0.26f, -0.6f, -1.03f);
        pathBuilder3.verticalLineToRelative(-0.97f);
        pathBuilder3.curveToRelative(0.0f, -0.75f, 0.22f, -1.01f, 0.59f, -1.01f);
        pathBuilder3.curveTo(14.07f, 12.3f, 14.29f, 12.57f, 14.29f, 13.32f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _forward10 = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
