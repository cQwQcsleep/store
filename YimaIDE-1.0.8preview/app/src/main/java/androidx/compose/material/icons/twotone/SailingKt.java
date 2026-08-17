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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sailing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Sailing", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSailing", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SailingKt {
    private static ImageVector _sailing;

    public static final ImageVector getSailing(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sailing;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Sailing", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.0f, 11.5f);
        pathBuilder.horizontalLineTo(6.83f);
        pathBuilder.lineTo(9.0f, 8.38f);
        pathBuilder.verticalLineTo(11.5f);
        pathBuilder.close();
        pathBuilder.moveTo(15.38f, 5.24f);
        pathBuilder.curveToRelative(1.42f, 1.52f, 2.88f, 3.72f, 3.41f, 6.26f);
        pathBuilder.horizontalLineToRelative(-3.68f);
        pathBuilder.curveToRelative(0.21f, -1.1f, 0.39f, -2.46f, 0.39f, -4.0f);
        pathBuilder.curveTo(15.5f, 6.71f, 15.45f, 5.95f, 15.38f, 5.24f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(11.0f, 13.5f);
        pathBuilder2.verticalLineTo(2.0f);
        pathBuilder2.lineTo(3.0f, 13.5f);
        pathBuilder2.horizontalLineTo(11.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(9.0f, 11.5f);
        pathBuilder2.horizontalLineTo(6.83f);
        pathBuilder2.lineTo(9.0f, 8.38f);
        pathBuilder2.verticalLineTo(11.5f);
        pathBuilder2.close();
        pathBuilder2.moveTo(21.0f, 13.5f);
        pathBuilder2.curveTo(21.0f, 6.5f, 14.5f, 1.0f, 12.5f, 1.0f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, 1.0f, 3.0f, 1.0f, 6.5f);
        pathBuilder2.reflectiveCurveToRelative(-1.0f, 6.0f, -1.0f, 6.0f);
        pathBuilder2.horizontalLineTo(21.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.38f, 5.24f);
        pathBuilder2.curveToRelative(1.42f, 1.52f, 2.88f, 3.72f, 3.41f, 6.26f);
        pathBuilder2.horizontalLineToRelative(-3.68f);
        pathBuilder2.curveToRelative(0.21f, -1.1f, 0.39f, -2.46f, 0.39f, -4.0f);
        pathBuilder2.curveTo(15.5f, 6.71f, 15.45f, 5.95f, 15.38f, 5.24f);
        pathBuilder2.close();
        pathBuilder2.moveTo(22.0f, 15.0f);
        pathBuilder2.horizontalLineTo(2.0f);
        pathBuilder2.curveToRelative(0.31f, 1.53f, 1.16f, 2.84f, 2.33f, 3.73f);
        pathBuilder2.curveTo(4.98f, 18.46f, 5.55f, 18.01f, 6.0f, 17.5f);
        pathBuilder2.curveTo(6.73f, 18.34f, 7.8f, 19.0f, 9.0f, 19.0f);
        pathBuilder2.reflectiveCurveToRelative(2.27f, -0.66f, 3.0f, -1.5f);
        pathBuilder2.curveToRelative(0.73f, 0.84f, 1.8f, 1.5f, 3.0f, 1.5f);
        pathBuilder2.reflectiveCurveToRelative(2.26f, -0.66f, 3.0f, -1.5f);
        pathBuilder2.curveToRelative(0.45f, 0.51f, 1.02f, 0.96f, 1.67f, 1.23f);
        pathBuilder2.curveTo(20.84f, 17.84f, 21.69f, 16.53f, 22.0f, 15.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(22.0f, 23.0f);
        pathBuilder2.verticalLineToRelative(-2.0f);
        pathBuilder2.horizontalLineToRelative(-1.0f);
        pathBuilder2.curveToRelative(-1.04f, 0.0f, -2.08f, -0.35f, -3.0f, -1.0f);
        pathBuilder2.curveToRelative(-1.83f, 1.3f, -4.17f, 1.3f, -6.0f, 0.0f);
        pathBuilder2.curveToRelative(-1.83f, 1.3f, -4.17f, 1.3f, -6.0f, 0.0f);
        pathBuilder2.curveToRelative(-0.91f, 0.65f, -1.96f, 1.0f, -3.0f, 1.0f);
        pathBuilder2.horizontalLineTo(2.0f);
        pathBuilder2.lineToRelative(0.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(1.0f);
        pathBuilder2.curveToRelative(1.03f, 0.0f, 2.05f, -0.25f, 3.0f, -0.75f);
        pathBuilder2.curveToRelative(1.89f, 1.0f, 4.11f, 1.0f, 6.0f, 0.0f);
        pathBuilder2.curveToRelative(1.89f, 1.0f, 4.11f, 1.0f, 6.0f, 0.0f);
        pathBuilder2.horizontalLineToRelative(0.0f);
        pathBuilder2.curveToRelative(0.95f, 0.5f, 1.97f, 0.75f, 3.0f, 0.75f);
        pathBuilder2.horizontalLineTo(22.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _sailing = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
