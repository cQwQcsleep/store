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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_templeBuddhist", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TempleBuddhist", "Landroidx/compose/material/icons/Icons$Rounded;", "getTempleBuddhist", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TempleBuddhistKt {
    private static ImageVector _templeBuddhist;

    public static final ImageVector getTempleBuddhist(Icons.Rounded rounded) {
        ImageVector imageVector = _templeBuddhist;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.TempleBuddhist", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(21.85f, 9.01f);
        pathBuilder.curveToRelative(-0.41f, 0.0f, -0.82f, 0.24f, -0.95f, 0.63f);
        pathBuilder.curveTo(20.64f, 10.43f, 19.89f, 11.0f, 19.02f, 11.0f);
        pathBuilder.horizontalLineTo(4.98f);
        pathBuilder.curveToRelative(-0.87f, 0.0f, -1.62f, -0.57f, -1.88f, -1.36f);
        pathBuilder.curveTo(2.97f, 9.25f, 2.57f, 9.02f, 2.16f, 9.02f);
        pathBuilder.horizontalLineToRelative(0.0f);
        pathBuilder.curveTo(1.5f, 9.02f, 1.0f, 9.66f, 1.21f, 10.28f);
        pathBuilder.curveToRelative(0.43f, 1.27f, 1.48f, 2.24f, 2.79f, 2.58f);
        pathBuilder.verticalLineTo(20.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.lineToRelative(0.0f, -2.89f);
        pathBuilder.curveToRelative(0.0f, -1.0f, 0.68f, -1.92f, 1.66f, -2.08f);
        pathBuilder.curveTo(12.92f, 16.82f, 14.0f, 17.79f, 14.0f, 19.0f);
        pathBuilder.verticalLineToRelative(3.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.verticalLineToRelative(-7.14f);
        pathBuilder.curveToRelative(0.46f, -0.12f, 2.22f, -0.76f, 2.81f, -2.58f);
        pathBuilder.curveTo(23.01f, 9.65f, 22.51f, 9.0f, 21.85f, 9.01f);
        pathBuilder.lineTo(21.85f, 9.01f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(6.0f, 8.86f);
        pathBuilder2.verticalLineTo(10.0f);
        pathBuilder2.horizontalLineToRelative(12.0f);
        pathBuilder2.verticalLineTo(8.86f);
        pathBuilder2.curveToRelative(0.46f, -0.12f, 2.22f, -0.76f, 2.81f, -2.58f);
        pathBuilder2.curveToRelative(0.2f, -0.63f, -0.3f, -1.27f, -0.96f, -1.27f);
        pathBuilder2.lineToRelative(0.0f, 0.0f);
        pathBuilder2.curveToRelative(-0.41f, 0.0f, -0.82f, 0.24f, -0.95f, 0.63f);
        pathBuilder2.curveTo(18.64f, 6.43f, 17.89f, 7.0f, 17.02f, 7.0f);
        pathBuilder2.horizontalLineTo(6.98f);
        pathBuilder2.curveTo(6.11f, 7.0f, 5.36f, 6.43f, 5.1f, 5.64f);
        pathBuilder2.curveTo(4.97f, 5.25f, 4.57f, 5.02f, 4.16f, 5.02f);
        pathBuilder2.horizontalLineToRelative(0.0f);
        pathBuilder2.curveTo(3.5f, 5.02f, 3.0f, 5.66f, 3.21f, 6.28f);
        pathBuilder2.curveTo(3.64f, 7.55f, 4.69f, 8.53f, 6.0f, 8.86f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(11.2f, 2.07f);
        pathBuilder3.lineTo(8.25f, 6.0f);
        pathBuilder3.horizontalLineToRelative(7.5f);
        pathBuilder3.lineTo(12.8f, 2.07f);
        pathBuilder3.curveTo(12.4f, 1.53f, 11.6f, 1.53f, 11.2f, 2.07f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _templeBuddhist = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
