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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_face2", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Face2", "Landroidx/compose/material/icons/Icons$Rounded;", "getFace2", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Face2Kt {
    private static ImageVector _face2;

    public static final ImageVector getFace2(Icons.Rounded rounded) {
        ImageVector imageVector = _face2;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Face2", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(21.97f, 13.52f);
        pathBuilder.curveToRelative(0.0f, -0.01f, 0.0f, -0.02f, 0.0f, -0.04f);
        pathBuilder.curveTo(23.21f, 12.38f, 24.0f, 10.78f, 24.0f, 9.0f);
        pathBuilder.curveToRelative(0.0f, -3.31f, -2.69f, -6.0f, -6.0f, -6.0f);
        pathBuilder.curveToRelative(-0.26f, 0.0f, -0.52f, 0.02f, -0.78f, 0.06f);
        pathBuilder.curveTo(16.19f, 1.23f, 14.24f, 0.0f, 12.0f, 0.0f);
        pathBuilder.reflectiveCurveTo(7.81f, 1.23f, 6.78f, 3.06f);
        pathBuilder.curveTo(6.52f, 3.02f, 6.26f, 3.0f, 6.0f, 3.0f);
        pathBuilder.curveTo(2.69f, 3.0f, 0.0f, 5.69f, 0.0f, 9.0f);
        pathBuilder.curveToRelative(0.0f, 1.78f, 0.79f, 3.38f, 2.02f, 4.48f);
        pathBuilder.curveToRelative(0.0f, 0.01f, 0.0f, 0.02f, 0.0f, 0.04f);
        pathBuilder.curveTo(0.79f, 14.62f, 0.0f, 16.22f, 0.0f, 18.0f);
        pathBuilder.curveToRelative(0.0f, 3.31f, 2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilder.curveToRelative(1.39f, 0.0f, 2.67f, -0.48f, 3.69f, -1.28f);
        pathBuilder.curveTo(10.43f, 22.9f, 11.2f, 23.0f, 12.0f, 23.0f);
        pathBuilder.reflectiveCurveToRelative(1.57f, -0.1f, 2.31f, -0.28f);
        pathBuilder.curveTo(15.33f, 23.52f, 16.61f, 24.0f, 18.0f, 24.0f);
        pathBuilder.curveToRelative(3.31f, 0.0f, 6.0f, -2.69f, 6.0f, -6.0f);
        pathBuilder.curveTo(24.0f, 16.22f, 23.21f, 14.62f, 21.97f, 13.52f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 21.0f);
        pathBuilder.curveToRelative(-4.41f, 0.0f, -8.0f, -3.59f, -8.0f, -8.0f);
        pathBuilder.curveToRelative(0.0f, -3.72f, 2.56f, -6.85f, 6.0f, -7.74f);
        pathBuilder.curveToRelative(0.0f, 0.02f, 0.0f, 0.03f, 0.0f, 0.05f);
        pathBuilder.curveToRelative(0.0f, 3.34f, 2.72f, 6.06f, 6.06f, 6.06f);
        pathBuilder.curveToRelative(1.26f, 0.0f, 2.45f, -0.39f, 3.45f, -1.09f);
        pathBuilder.curveTo(19.82f, 11.14f, 20.0f, 12.05f, 20.0f, 13.0f);
        pathBuilder.curveTo(20.0f, 17.41f, 16.41f, 21.0f, 12.0f, 21.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(9.0f, 14.0f);
        pathBuilder2.moveToRelative(-1.25f, 0.0f);
        pathBuilder2.arcToRelative(1.25f, 1.25f, 0.0f, true, true, 2.5f, 0.0f);
        pathBuilder2.arcToRelative(1.25f, 1.25f, 0.0f, true, true, -2.5f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(15.0f, 14.0f);
        pathBuilder3.moveToRelative(-1.25f, 0.0f);
        pathBuilder3.arcToRelative(1.25f, 1.25f, 0.0f, true, true, 2.5f, 0.0f);
        pathBuilder3.arcToRelative(1.25f, 1.25f, 0.0f, true, true, -2.5f, 0.0f);
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _face2 = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
