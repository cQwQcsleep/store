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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsBaseball", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsBaseball", "Landroidx/compose/material/icons/Icons$Filled;", "getSportsBaseball", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsBaseballKt {
    private static ImageVector _sportsBaseball;

    public static final ImageVector getSportsBaseball(Icons.Filled filled) {
        ImageVector imageVector = _sportsBaseball;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.SportsBaseball", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(3.81f, 6.28f);
        pathBuilder.curveTo(2.67f, 7.9f, 2.0f, 9.87f, 2.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(0.67f, 4.1f, 1.81f, 5.72f);
        pathBuilder.curveTo(6.23f, 16.95f, 8.0f, 14.68f, 8.0f, 12.0f);
        pathBuilder.reflectiveCurveTo(6.23f, 7.05f, 3.81f, 6.28f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(20.19f, 6.28f);
        pathBuilder2.curveTo(17.77f, 7.05f, 16.0f, 9.32f, 16.0f, 12.0f);
        pathBuilder2.reflectiveCurveToRelative(1.77f, 4.95f, 4.19f, 5.72f);
        pathBuilder2.curveTo(21.33f, 16.1f, 22.0f, 14.13f, 22.0f, 12.0f);
        pathBuilder2.reflectiveCurveTo(21.33f, 7.9f, 20.19f, 6.28f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(14.0f, 12.0f);
        pathBuilder3.curveToRelative(0.0f, -3.28f, 1.97f, -6.09f, 4.79f, -7.33f);
        pathBuilder3.curveTo(17.01f, 3.02f, 14.63f, 2.0f, 12.0f, 2.0f);
        pathBuilder3.reflectiveCurveTo(6.99f, 3.02f, 5.21f, 4.67f);
        pathBuilder3.curveTo(8.03f, 5.91f, 10.0f, 8.72f, 10.0f, 12.0f);
        pathBuilder3.reflectiveCurveToRelative(-1.97f, 6.09f, -4.79f, 7.33f);
        pathBuilder3.curveTo(6.99f, 20.98f, 9.37f, 22.0f, 12.0f, 22.0f);
        pathBuilder3.reflectiveCurveToRelative(5.01f, -1.02f, 6.79f, -2.67f);
        pathBuilder3.curveTo(15.97f, 18.09f, 14.0f, 15.28f, 14.0f, 12.0f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _sportsBaseball = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
