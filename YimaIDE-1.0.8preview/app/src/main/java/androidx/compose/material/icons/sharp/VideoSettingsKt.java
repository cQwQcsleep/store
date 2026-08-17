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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_videoSettings", "Landroidx/compose/ui/graphics/vector/ImageVector;", "VideoSettings", "Landroidx/compose/material/icons/Icons$Sharp;", "getVideoSettings", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class VideoSettingsKt {
    private static ImageVector _videoSettings;

    public static final ImageVector getVideoSettings(Icons.Sharp sharp) {
        ImageVector imageVector = _videoSettings;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.VideoSettings", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(3.0f, 6.0f);
        pathBuilder.lineToRelative(18.0f, 0.0f);
        pathBuilder.lineToRelative(0.0f, 5.0f);
        pathBuilder.lineToRelative(2.0f, 0.0f);
        pathBuilder.lineToRelative(0.0f, -7.0f);
        pathBuilder.lineToRelative(-22.0f, 0.0f);
        pathBuilder.lineToRelative(0.0f, 16.0f);
        pathBuilder.lineToRelative(11.0f, 0.0f);
        pathBuilder.lineToRelative(0.0f, -2.0f);
        pathBuilder.lineToRelative(-9.0f, 0.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(15.0f, 12.0f);
        pathBuilder2.lineToRelative(-6.0f, -4.0f);
        pathBuilder2.lineToRelative(0.0f, 8.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(22.72f, 17.57f);
        pathBuilder3.lineToRelative(1.23f, -0.98f);
        pathBuilder3.lineToRelative(-1.25f, -2.17f);
        pathBuilder3.lineTo(21.23f, 15.0f);
        pathBuilder3.curveToRelative(-0.23f, -0.17f, -0.48f, -0.31f, -0.75f, -0.42f);
        pathBuilder3.lineTo(20.25f, 13.0f);
        pathBuilder3.horizontalLineToRelative(-2.5f);
        pathBuilder3.lineToRelative(-0.24f, 1.58f);
        pathBuilder3.curveToRelative(-0.26f, 0.11f, -0.51f, 0.26f, -0.74f, 0.42f);
        pathBuilder3.lineToRelative(-1.48f, -0.58f);
        pathBuilder3.lineToRelative(-1.25f, 2.17f);
        pathBuilder3.lineToRelative(1.24f, 0.99f);
        pathBuilder3.curveToRelative(-0.03f, 0.29f, -0.04f, 0.58f, -0.01f, 0.86f);
        pathBuilder3.lineToRelative(-1.23f, 0.98f);
        pathBuilder3.lineToRelative(1.25f, 2.17f);
        pathBuilder3.lineTo(16.77f, 21.0f);
        pathBuilder3.curveToRelative(0.23f, 0.17f, 0.48f, 0.31f, 0.75f, 0.42f);
        pathBuilder3.lineTo(17.75f, 23.0f);
        pathBuilder3.horizontalLineToRelative(2.5f);
        pathBuilder3.lineToRelative(0.24f, -1.58f);
        pathBuilder3.curveToRelative(0.26f, -0.11f, 0.51f, -0.26f, 0.74f, -0.42f);
        pathBuilder3.lineToRelative(1.48f, 0.58f);
        pathBuilder3.lineToRelative(1.25f, -2.17f);
        pathBuilder3.lineToRelative(-1.24f, -0.99f);
        pathBuilder3.curveTo(22.75f, 18.14f, 22.75f, 17.85f, 22.72f, 17.57f);
        pathBuilder3.close();
        pathBuilder3.moveTo(19.0f, 19.5f);
        pathBuilder3.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilder3.reflectiveCurveToRelative(0.67f, -1.5f, 1.5f, -1.5f);
        pathBuilder3.reflectiveCurveToRelative(1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilder3.reflectiveCurveTo(19.83f, 19.5f, 19.0f, 19.5f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _videoSettings = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
