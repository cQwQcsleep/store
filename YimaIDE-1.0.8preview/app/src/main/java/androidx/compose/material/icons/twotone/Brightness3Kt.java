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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_brightness3", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Brightness3", "Landroidx/compose/material/icons/Icons$TwoTone;", "getBrightness3", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Brightness3Kt {
    private static ImageVector _brightness3;

    public static final ImageVector getBrightness3(Icons.TwoTone twoTone) {
        ImageVector imageVector = _brightness3;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Brightness3", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.7f, 4.91f);
        pathBuilder.curveToRelative(1.46f, 2.0f, 2.3f, 4.46f, 2.3f, 7.09f);
        pathBuilder.reflectiveCurveToRelative(-0.84f, 5.09f, -2.3f, 7.09f);
        pathBuilder.curveTo(15.25f, 17.76f, 17.0f, 15.08f, 17.0f, 12.0f);
        pathBuilder.reflectiveCurveToRelative(-1.75f, -5.76f, -4.3f, -7.09f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(9.0f, 2.0f);
        pathBuilder2.curveToRelative(-1.05f, 0.0f, -2.05f, 0.16f, -3.0f, 0.46f);
        pathBuilder2.curveToRelative(4.06f, 1.27f, 7.0f, 5.06f, 7.0f, 9.54f);
        pathBuilder2.curveToRelative(0.0f, 4.48f, -2.94f, 8.27f, -7.0f, 9.54f);
        pathBuilder2.curveToRelative(0.95f, 0.3f, 1.95f, 0.46f, 3.0f, 0.46f);
        pathBuilder2.curveToRelative(5.52f, 0.0f, 10.0f, -4.48f, 10.0f, -10.0f);
        pathBuilder2.reflectiveCurveTo(14.52f, 2.0f, 9.0f, 2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.7f, 19.09f);
        pathBuilder2.curveToRelative(1.46f, -2.0f, 2.3f, -4.46f, 2.3f, -7.09f);
        pathBuilder2.reflectiveCurveToRelative(-0.84f, -5.09f, -2.3f, -7.09f);
        pathBuilder2.curveTo(15.25f, 6.24f, 17.0f, 8.92f, 17.0f, 12.0f);
        pathBuilder2.reflectiveCurveToRelative(-1.75f, 5.76f, -4.3f, 7.09f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _brightness3 = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
