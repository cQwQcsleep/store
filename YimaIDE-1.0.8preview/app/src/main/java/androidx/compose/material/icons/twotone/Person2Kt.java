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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_person2", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Person2", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPerson2", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class Person2Kt {
    private static ImageVector _person2;

    public static final ImageVector getPerson2(Icons.TwoTone twoTone) {
        ImageVector imageVector = _person2;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Person2", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(13.9f, 7.59f);
        pathBuilder.curveTo(13.74f, 6.67f, 12.94f, 6.0f, 12.0f, 6.0f);
        pathBuilder.reflectiveCurveToRelative(-1.74f, 0.67f, -1.9f, 1.59f);
        pathBuilder.lineTo(9.78f, 10.0f);
        pathBuilder.horizontalLineToRelative(4.44f);
        pathBuilder.lineTo(13.9f, 7.59f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(17.48f, 16.34f);
        pathBuilder2.curveTo(16.29f, 15.73f, 14.37f, 15.0f, 12.0f, 15.0f);
        pathBuilder2.curveToRelative(-2.37f, 0.0f, -4.29f, 0.73f, -5.48f, 1.34f);
        pathBuilder2.curveTo(6.2f, 16.5f, 6.0f, 16.84f, 6.0f, 17.22f);
        pathBuilder2.verticalLineTo(18.0f);
        pathBuilder2.horizontalLineToRelative(12.0f);
        pathBuilder2.verticalLineToRelative(-0.78f);
        pathBuilder2.curveTo(18.0f, 16.84f, 17.8f, 16.5f, 17.48f, 16.34f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, (Brush) null, 0.3f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(18.39f, 14.56f);
        pathBuilder3.curveTo(16.71f, 13.7f, 14.53f, 13.0f, 12.0f, 13.0f);
        pathBuilder3.curveToRelative(-2.53f, 0.0f, -4.71f, 0.7f, -6.39f, 1.56f);
        pathBuilder3.curveTo(4.61f, 15.07f, 4.0f, 16.1f, 4.0f, 17.22f);
        pathBuilder3.verticalLineTo(20.0f);
        pathBuilder3.horizontalLineToRelative(16.0f);
        pathBuilder3.verticalLineToRelative(-2.78f);
        pathBuilder3.curveTo(20.0f, 16.1f, 19.39f, 15.07f, 18.39f, 14.56f);
        pathBuilder3.close();
        pathBuilder3.moveTo(18.0f, 18.0f);
        pathBuilder3.horizontalLineTo(6.0f);
        pathBuilder3.verticalLineToRelative(-0.78f);
        pathBuilder3.curveToRelative(0.0f, -0.38f, 0.2f, -0.72f, 0.52f, -0.88f);
        pathBuilder3.curveTo(7.71f, 15.73f, 9.63f, 15.0f, 12.0f, 15.0f);
        pathBuilder3.curveToRelative(2.37f, 0.0f, 4.29f, 0.73f, 5.48f, 1.34f);
        pathBuilder3.curveTo(17.8f, 16.5f, 18.0f, 16.84f, 18.0f, 17.22f);
        pathBuilder3.verticalLineTo(18.0f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(9.78f, 12.0f);
        pathBuilder4.horizontalLineToRelative(4.44f);
        pathBuilder4.curveToRelative(1.21f, 0.0f, 2.14f, -1.06f, 1.98f, -2.26f);
        pathBuilder4.lineToRelative(-0.32f, -2.45f);
        pathBuilder4.curveTo(15.57f, 5.39f, 13.92f, 4.0f, 12.0f, 4.0f);
        pathBuilder4.reflectiveCurveTo(8.43f, 5.39f, 8.12f, 7.29f);
        pathBuilder4.lineTo(7.8f, 9.74f);
        pathBuilder4.curveTo(7.64f, 10.94f, 8.57f, 12.0f, 9.78f, 12.0f);
        pathBuilder4.close();
        pathBuilder4.moveTo(10.1f, 7.59f);
        pathBuilder4.curveTo(10.26f, 6.67f, 11.06f, 6.0f, 12.0f, 6.0f);
        pathBuilder4.reflectiveCurveToRelative(1.74f, 0.67f, 1.9f, 1.59f);
        pathBuilder4.lineTo(14.22f, 10.0f);
        pathBuilder4.horizontalLineTo(9.78f);
        pathBuilder4.lineTo(10.1f, 7.59f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _person2 = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
