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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sports", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sports", "Landroidx/compose/material/icons/Icons$Filled;", "getSports", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsKt {
    private static ImageVector _sports;

    public static final ImageVector getSports(Icons.Filled filled) {
        ImageVector imageVector = _sports;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Sports", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(11.23f, 6.0f);
        pathBuilder.curveTo(9.57f, 6.0f, 8.01f, 6.66f, 6.87f, 7.73f);
        pathBuilder.curveTo(6.54f, 6.73f, 5.61f, 6.0f, 4.5f, 6.0f);
        pathBuilder.curveTo(3.12f, 6.0f, 2.0f, 7.12f, 2.0f, 8.5f);
        pathBuilder.curveTo(2.0f, 9.88f, 3.12f, 11.0f, 4.5f, 11.0f);
        pathBuilder.curveToRelative(0.21f, 0.0f, 0.41f, -0.03f, 0.61f, -0.08f);
        pathBuilder.curveToRelative(-0.05f, 0.25f, -0.09f, 0.51f, -0.1f, 0.78f);
        pathBuilder.curveToRelative(-0.18f, 3.68f, 2.95f, 6.68f, 6.68f, 6.27f);
        pathBuilder.curveToRelative(2.55f, -0.28f, 4.68f, -2.26f, 5.19f, -4.77f);
        pathBuilder.curveToRelative(0.15f, -0.71f, 0.15f, -1.4f, 0.06f, -2.06f);
        pathBuilder.curveToRelative(-0.09f, -0.6f, 0.38f, -1.13f, 0.99f, -1.13f);
        pathBuilder.horizontalLineTo(22.0f);
        pathBuilder.verticalLineTo(6.0f);
        pathBuilder.horizontalLineTo(11.23f);
        pathBuilder.close();
        pathBuilder.moveTo(4.5f, 9.0f);
        pathBuilder.curveTo(4.22f, 9.0f, 4.0f, 8.78f, 4.0f, 8.5f);
        pathBuilder.curveTo(4.0f, 8.22f, 4.22f, 8.0f, 4.5f, 8.0f);
        pathBuilder.reflectiveCurveTo(5.0f, 8.22f, 5.0f, 8.5f);
        pathBuilder.curveTo(5.0f, 8.78f, 4.78f, 9.0f, 4.5f, 9.0f);
        pathBuilder.close();
        pathBuilder.moveTo(11.0f, 15.0f);
        pathBuilder.curveToRelative(-1.66f, 0.0f, -3.0f, -1.34f, -3.0f, -3.0f);
        pathBuilder.reflectiveCurveToRelative(1.34f, -3.0f, 3.0f, -3.0f);
        pathBuilder.reflectiveCurveToRelative(3.0f, 1.34f, 3.0f, 3.0f);
        pathBuilder.reflectiveCurveTo(12.66f, 15.0f, 11.0f, 15.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(11.0f, 12.0f);
        pathBuilder2.moveToRelative(-2.0f, 0.0f);
        pathBuilder2.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilder2.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _sports = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
