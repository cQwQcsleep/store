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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autoStories", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AutoStories", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAutoStories", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoStoriesKt {
    private static ImageVector _autoStories;

    public static final ImageVector getAutoStories(Icons.TwoTone twoTone) {
        ImageVector imageVector = _autoStories;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AutoStories", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(3.0f, 6.71f);
        pathBuilder.verticalLineToRelative(9.91f);
        pathBuilder.curveTo(4.14f, 16.21f, 5.31f, 16.0f, 6.5f, 16.0f);
        pathBuilder.curveToRelative(1.19f, 0.0f, 2.36f, 0.21f, 3.5f, 0.62f);
        pathBuilder.verticalLineTo(6.72f);
        pathBuilder.curveTo(8.89f, 6.25f, 7.7f, 6.0f, 6.5f, 6.0f);
        pathBuilder.curveTo(5.28f, 6.0f, 4.11f, 6.24f, 3.0f, 6.71f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(19.0f, 0.5f);
        pathBuilder2.lineToRelative(-5.0f, 5.0f);
        pathBuilder2.lineToRelative(0.0f, 9.5f);
        pathBuilder2.lineToRelative(5.0f, -4.5f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(22.47f, 5.2f);
        pathBuilder3.curveTo(22.0f, 4.96f, 21.51f, 4.76f, 21.0f, 4.59f);
        pathBuilder3.verticalLineToRelative(12.03f);
        pathBuilder3.curveTo(19.86f, 16.21f, 18.69f, 16.0f, 17.5f, 16.0f);
        pathBuilder3.curveToRelative(-1.9f, 0.0f, -3.78f, 0.54f, -5.5f, 1.58f);
        pathBuilder3.verticalLineTo(5.48f);
        pathBuilder3.curveTo(10.38f, 4.55f, 8.51f, 4.0f, 6.5f, 4.0f);
        pathBuilder3.curveTo(4.71f, 4.0f, 3.02f, 4.44f, 1.53f, 5.2f);
        pathBuilder3.curveTo(1.2f, 5.36f, 1.0f, 5.71f, 1.0f, 6.08f);
        pathBuilder3.verticalLineToRelative(12.08f);
        pathBuilder3.curveToRelative(0.0f, 0.58f, 0.47f, 0.99f, 1.0f, 0.99f);
        pathBuilder3.curveToRelative(0.16f, 0.0f, 0.32f, -0.04f, 0.48f, -0.12f);
        pathBuilder3.curveTo(3.69f, 18.4f, 5.05f, 18.0f, 6.5f, 18.0f);
        pathBuilder3.curveToRelative(2.07f, 0.0f, 3.98f, 0.82f, 5.5f, 2.0f);
        pathBuilder3.curveToRelative(1.52f, -1.18f, 3.43f, -2.0f, 5.5f, -2.0f);
        pathBuilder3.curveToRelative(1.45f, 0.0f, 2.81f, 0.4f, 4.02f, 1.04f);
        pathBuilder3.curveToRelative(0.16f, 0.08f, 0.32f, 0.12f, 0.48f, 0.12f);
        pathBuilder3.curveToRelative(0.52f, 0.0f, 1.0f, -0.41f, 1.0f, -0.99f);
        pathBuilder3.verticalLineTo(6.08f);
        pathBuilder3.curveTo(23.0f, 5.71f, 22.8f, 5.36f, 22.47f, 5.2f);
        pathBuilder3.close();
        pathBuilder3.moveTo(10.0f, 16.62f);
        pathBuilder3.curveTo(8.86f, 16.21f, 7.69f, 16.0f, 6.5f, 16.0f);
        pathBuilder3.curveToRelative(-1.19f, 0.0f, -2.36f, 0.21f, -3.5f, 0.62f);
        pathBuilder3.verticalLineTo(6.71f);
        pathBuilder3.curveTo(4.11f, 6.24f, 5.28f, 6.0f, 6.5f, 6.0f);
        pathBuilder3.curveTo(7.7f, 6.0f, 8.89f, 6.25f, 10.0f, 6.72f);
        pathBuilder3.verticalLineTo(16.62f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _autoStories = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
