package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_person4", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Person4", "Landroidx/compose/material/icons/Icons$Outlined;", "getPerson4", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Person4Kt {
    private static ImageVector _person4;

    public static final ImageVector getPerson4(Icons.Outlined outlined) {
        ImageVector imageVector = _person4;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.Person4", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.39f, 14.56f);
        pathBuilder.curveTo(16.71f, 13.7f, 14.53f, 13.0f, 12.0f, 13.0f);
        pathBuilder.curveToRelative(-2.53f, 0.0f, -4.71f, 0.7f, -6.39f, 1.56f);
        pathBuilder.curveTo(4.61f, 15.07f, 4.0f, 16.1f, 4.0f, 17.22f);
        pathBuilder.verticalLineTo(20.0f);
        pathBuilder.horizontalLineToRelative(16.0f);
        pathBuilder.verticalLineToRelative(-2.78f);
        pathBuilder.curveTo(20.0f, 16.1f, 19.39f, 15.07f, 18.39f, 14.56f);
        pathBuilder.close();
        pathBuilder.moveTo(18.0f, 18.0f);
        pathBuilder.horizontalLineTo(6.0f);
        pathBuilder.verticalLineToRelative(-0.78f);
        pathBuilder.curveToRelative(0.0f, -0.38f, 0.2f, -0.72f, 0.52f, -0.88f);
        pathBuilder.curveTo(7.71f, 15.73f, 9.63f, 15.0f, 12.0f, 15.0f);
        pathBuilder.curveToRelative(2.37f, 0.0f, 4.29f, 0.73f, 5.48f, 1.34f);
        pathBuilder.curveTo(17.8f, 16.5f, 18.0f, 16.84f, 18.0f, 17.22f);
        pathBuilder.verticalLineTo(18.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(12.0f, 12.0f);
        pathBuilder2.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilder2.curveToRelative(0.0f, -1.37f, 0.0f, -3.5f, 0.0f, -3.5f);
        pathBuilder2.curveTo(16.0f, 3.67f, 15.33f, 3.0f, 14.5f, 3.0f);
        pathBuilder2.curveToRelative(-0.52f, 0.0f, -0.98f, 0.27f, -1.25f, 0.67f);
        pathBuilder2.curveTo(12.98f, 3.27f, 12.52f, 3.0f, 12.0f, 3.0f);
        pathBuilder2.reflectiveCurveToRelative(-0.98f, 0.27f, -1.25f, 0.67f);
        pathBuilder2.curveTo(10.48f, 3.27f, 10.02f, 3.0f, 9.5f, 3.0f);
        pathBuilder2.curveTo(8.67f, 3.0f, 8.0f, 3.67f, 8.0f, 4.5f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, 0.0f, 2.12f, 0.0f, 3.5f);
        pathBuilder2.curveTo(8.0f, 10.21f, 9.79f, 12.0f, 12.0f, 12.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(10.0f, 5.5f);
        pathBuilder2.horizontalLineToRelative(4.0f);
        pathBuilder2.verticalLineTo(8.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder2.reflectiveCurveToRelative(-2.0f, -0.9f, -2.0f, -2.0f);
        pathBuilder2.verticalLineTo(5.5f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _person4 = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
