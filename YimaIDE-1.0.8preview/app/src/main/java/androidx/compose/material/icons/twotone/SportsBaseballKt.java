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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsBaseball", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsBaseball", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSportsBaseball", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsBaseballKt {
    private static ImageVector _sportsBaseball;

    public static final ImageVector getSportsBaseball(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sportsBaseball;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SportsBaseball", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(5.61f, 7.22f);
        pathBuilder.curveTo(4.6f, 8.55f, 4.0f, 10.2f, 4.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(0.6f, 3.45f, 1.61f, 4.78f);
        pathBuilder.curveTo(7.06f, 15.69f, 8.0f, 13.95f, 8.0f, 12.0f);
        pathBuilder.reflectiveCurveTo(7.06f, 8.31f, 5.61f, 7.22f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(14.0f, 12.0f);
        pathBuilder2.curveToRelative(0.0f, -2.52f, 1.17f, -4.77f, 3.0f, -6.24f);
        pathBuilder2.curveTo(15.63f, 4.66f, 13.89f, 4.0f, 12.0f, 4.0f);
        pathBuilder2.reflectiveCurveTo(8.37f, 4.66f, 7.0f, 5.76f);
        pathBuilder2.curveToRelative(1.83f, 1.47f, 3.0f, 3.71f, 3.0f, 6.24f);
        pathBuilder2.reflectiveCurveToRelative(-1.17f, 4.77f, -3.0f, 6.24f);
        pathBuilder2.curveToRelative(1.37f, 1.1f, 3.11f, 1.76f, 5.0f, 1.76f);
        pathBuilder2.reflectiveCurveToRelative(3.63f, -0.66f, 5.0f, -1.76f);
        pathBuilder2.curveTo(15.17f, 16.77f, 14.0f, 14.52f, 14.0f, 12.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, (Brush) null, 0.3f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(18.39f, 7.22f);
        pathBuilder3.curveTo(16.94f, 8.31f, 16.0f, 10.05f, 16.0f, 12.0f);
        pathBuilder3.reflectiveCurveToRelative(0.94f, 3.69f, 2.39f, 4.78f);
        pathBuilder3.curveTo(19.4f, 15.45f, 20.0f, 13.8f, 20.0f, 12.0f);
        pathBuilder3.reflectiveCurveTo(19.4f, 8.55f, 18.39f, 7.22f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 0.3f, (Brush) null, 0.3f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(12.0f, 2.0f);
        pathBuilder4.curveTo(6.48f, 2.0f, 2.0f, 6.48f, 2.0f, 12.0f);
        pathBuilder4.curveToRelative(0.0f, 5.52f, 4.48f, 10.0f, 10.0f, 10.0f);
        pathBuilder4.reflectiveCurveToRelative(10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilder4.curveTo(22.0f, 6.48f, 17.52f, 2.0f, 12.0f, 2.0f);
        pathBuilder4.close();
        pathBuilder4.moveTo(5.61f, 16.78f);
        pathBuilder4.curveTo(4.6f, 15.45f, 4.0f, 13.8f, 4.0f, 12.0f);
        pathBuilder4.reflectiveCurveToRelative(0.6f, -3.45f, 1.61f, -4.78f);
        pathBuilder4.curveTo(7.06f, 8.31f, 8.0f, 10.05f, 8.0f, 12.0f);
        pathBuilder4.reflectiveCurveTo(7.06f, 15.69f, 5.61f, 16.78f);
        pathBuilder4.close();
        pathBuilder4.moveTo(12.0f, 20.0f);
        pathBuilder4.curveToRelative(-1.89f, 0.0f, -3.63f, -0.66f, -5.0f, -1.76f);
        pathBuilder4.curveToRelative(1.83f, -1.47f, 3.0f, -3.71f, 3.0f, -6.24f);
        pathBuilder4.reflectiveCurveTo(8.83f, 7.23f, 7.0f, 5.76f);
        pathBuilder4.curveTo(8.37f, 4.66f, 10.11f, 4.0f, 12.0f, 4.0f);
        pathBuilder4.reflectiveCurveToRelative(3.63f, 0.66f, 5.0f, 1.76f);
        pathBuilder4.curveToRelative(-1.83f, 1.47f, -3.0f, 3.71f, -3.0f, 6.24f);
        pathBuilder4.reflectiveCurveToRelative(1.17f, 4.77f, 3.0f, 6.24f);
        pathBuilder4.curveTo(15.63f, 19.34f, 13.89f, 20.0f, 12.0f, 20.0f);
        pathBuilder4.close();
        pathBuilder4.moveTo(18.39f, 16.78f);
        pathBuilder4.curveTo(16.94f, 15.69f, 16.0f, 13.95f, 16.0f, 12.0f);
        pathBuilder4.reflectiveCurveToRelative(0.94f, -3.69f, 2.39f, -4.78f);
        pathBuilder4.curveTo(19.4f, 8.55f, 20.0f, 10.2f, 20.0f, 12.0f);
        pathBuilder4.reflectiveCurveTo(19.4f, 15.45f, 18.39f, 16.78f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _sportsBaseball = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
