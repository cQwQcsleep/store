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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_thunderstorm", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Thunderstorm", "Landroidx/compose/material/icons/Icons$Rounded;", "getThunderstorm", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ThunderstormKt {
    private static ImageVector _thunderstorm;

    public static final ImageVector getThunderstorm(Icons.Rounded rounded) {
        ImageVector imageVector = _thunderstorm;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Thunderstorm", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(17.92f, 7.02f);
        pathBuilder.curveTo(17.45f, 4.18f, 14.97f, 2.0f, 12.0f, 2.0f);
        pathBuilder.curveTo(9.82f, 2.0f, 7.83f, 3.18f, 6.78f, 5.06f);
        pathBuilder.curveTo(4.09f, 5.41f, 2.0f, 7.74f, 2.0f, 10.5f);
        pathBuilder.curveTo(2.0f, 13.53f, 4.47f, 16.0f, 7.5f, 16.0f);
        pathBuilder.horizontalLineToRelative(10.0f);
        pathBuilder.curveToRelative(2.48f, 0.0f, 4.5f, -2.02f, 4.5f, -4.5f);
        pathBuilder.curveTo(22.0f, 9.16f, 20.21f, 7.23f, 17.92f, 7.02f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(15.95f, 20.11f);
        pathBuilder2.lineToRelative(-0.84f, -0.42f);
        pathBuilder2.lineToRelative(0.9f, -1.03f);
        pathBuilder2.curveToRelative(0.36f, -0.42f, 0.32f, -1.05f, -0.09f, -1.41f);
        pathBuilder2.curveToRelative(-0.42f, -0.36f, -1.05f, -0.32f, -1.41f, 0.09f);
        pathBuilder2.lineToRelative(-1.75f, 2.0f);
        pathBuilder2.curveToRelative(-0.2f, 0.23f, -0.29f, 0.55f, -0.23f, 0.85f);
        pathBuilder2.curveToRelative(0.06f, 0.3f, 0.26f, 0.56f, 0.53f, 0.7f);
        pathBuilder2.lineToRelative(0.84f, 0.42f);
        pathBuilder2.lineTo(13.0f, 22.34f);
        pathBuilder2.curveToRelative(-0.36f, 0.42f, -0.32f, 1.05f, 0.09f, 1.41f);
        pathBuilder2.curveToRelative(0.19f, 0.17f, 0.42f, 0.25f, 0.66f, 0.25f);
        pathBuilder2.curveToRelative(0.28f, 0.0f, 0.55f, -0.12f, 0.75f, -0.34f);
        pathBuilder2.lineToRelative(1.75f, -2.0f);
        pathBuilder2.curveToRelative(0.2f, -0.23f, 0.29f, -0.55f, 0.23f, -0.85f);
        pathBuilder2.curveTo(16.42f, 20.5f, 16.22f, 20.24f, 15.95f, 20.11f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(9.95f, 20.11f);
        pathBuilder3.lineTo(9.1f, 19.68f);
        pathBuilder3.lineToRelative(0.9f, -1.03f);
        pathBuilder3.curveToRelative(0.36f, -0.42f, 0.32f, -1.05f, -0.09f, -1.41f);
        pathBuilder3.curveToRelative(-0.42f, -0.36f, -1.05f, -0.32f, -1.41f, 0.09f);
        pathBuilder3.lineToRelative(-1.75f, 2.0f);
        pathBuilder3.curveToRelative(-0.2f, 0.23f, -0.29f, 0.55f, -0.23f, 0.85f);
        pathBuilder3.curveToRelative(0.06f, 0.3f, 0.26f, 0.56f, 0.53f, 0.7f);
        pathBuilder3.lineToRelative(0.84f, 0.42f);
        pathBuilder3.lineTo(7.0f, 22.34f);
        pathBuilder3.curveToRelative(-0.36f, 0.42f, -0.32f, 1.05f, 0.09f, 1.41f);
        pathBuilder3.curveTo(7.28f, 23.92f, 7.52f, 24.0f, 7.75f, 24.0f);
        pathBuilder3.curveToRelative(0.28f, 0.0f, 0.55f, -0.12f, 0.75f, -0.34f);
        pathBuilder3.lineToRelative(1.75f, -2.0f);
        pathBuilder3.curveToRelative(0.2f, -0.23f, 0.29f, -0.55f, 0.23f, -0.85f);
        pathBuilder3.curveTo(10.42f, 20.5f, 10.22f, 20.24f, 9.95f, 20.11f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _thunderstorm = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
