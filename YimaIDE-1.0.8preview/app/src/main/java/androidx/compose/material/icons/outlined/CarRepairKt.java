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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_carRepair", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CarRepair", "Landroidx/compose/material/icons/Icons$Outlined;", "getCarRepair", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CarRepairKt {
    private static ImageVector _carRepair;

    public static final ImageVector getCarRepair(Icons.Outlined outlined) {
        ImageVector imageVector = _carRepair;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.CarRepair", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.0f, 10.5f);
        pathBuilder.moveToRelative(-1.0f, 0.0f);
        pathBuilder.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilder.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(15.0f, 10.5f);
        pathBuilder2.moveToRelative(-1.0f, 0.0f);
        pathBuilder2.arcToRelative(1.0f, 1.0f, 0.0f, true, true, 2.0f, 0.0f);
        pathBuilder2.arcToRelative(1.0f, 1.0f, 0.0f, true, true, -2.0f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(5.78f, 16.0f);
        pathBuilder3.horizontalLineToRelative(0.44f);
        pathBuilder3.curveTo(6.65f, 16.0f, 7.0f, 15.64f, 7.0f, 15.19f);
        pathBuilder3.verticalLineTo(14.0f);
        pathBuilder3.horizontalLineToRelative(10.0f);
        pathBuilder3.verticalLineToRelative(1.19f);
        pathBuilder3.curveToRelative(0.0f, 0.45f, 0.34f, 0.81f, 0.78f, 0.81f);
        pathBuilder3.horizontalLineToRelative(0.44f);
        pathBuilder3.curveToRelative(0.43f, 0.0f, 0.78f, -0.36f, 0.78f, -0.81f);
        pathBuilder3.verticalLineToRelative(-6.5f);
        pathBuilder3.curveToRelative(0.0f, 0.0f, -1.34f, -4.03f, -1.56f, -4.69f);
        pathBuilder3.curveToRelative(-0.05f, -0.16f, -0.12f, -0.29f, -0.19f, -0.4f);
        pathBuilder3.curveToRelative(-0.02f, -0.02f, -0.03f, -0.04f, -0.05f, -0.07f);
        pathBuilder3.curveTo(16.82f, 3.01f, 16.28f, 3.0f, 16.28f, 3.0f);
        pathBuilder3.horizontalLineTo(7.72f);
        pathBuilder3.curveToRelative(0.0f, 0.0f, -0.54f, 0.01f, -0.92f, 0.54f);
        pathBuilder3.curveTo(6.78f, 3.56f, 6.77f, 3.58f, 6.75f, 3.6f);
        pathBuilder3.curveTo(6.68f, 3.71f, 6.61f, 3.84f, 6.56f, 4.0f);
        pathBuilder3.curveTo(6.34f, 4.66f, 5.0f, 8.69f, 5.0f, 8.69f);
        pathBuilder3.verticalLineToRelative(6.5f);
        pathBuilder3.curveTo(5.0f, 15.64f, 5.35f, 16.0f, 5.78f, 16.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(8.33f, 5.0f);
        pathBuilder3.horizontalLineToRelative(7.34f);
        pathBuilder3.lineToRelative(0.23f, 0.69f);
        pathBuilder3.lineTo(16.33f, 7.0f);
        pathBuilder3.horizontalLineTo(7.67f);
        pathBuilder3.lineTo(8.33f, 5.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(7.0f, 9.01f);
        pathBuilder3.verticalLineTo(9.0f);
        pathBuilder3.horizontalLineToRelative(10.0f);
        pathBuilder3.verticalLineToRelative(0.01f);
        pathBuilder3.verticalLineTo(12.0f);
        pathBuilder3.horizontalLineTo(7.0f);
        pathBuilder3.verticalLineTo(9.01f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(4.0f, 17.01f);
        pathBuilder4.lineToRelative(0.0f, 1.99f);
        pathBuilder4.lineToRelative(7.0f, 0.0f);
        pathBuilder4.lineToRelative(0.0f, 3.0f);
        pathBuilder4.lineToRelative(2.0f, 0.0f);
        pathBuilder4.lineToRelative(0.0f, -3.0f);
        pathBuilder4.lineToRelative(7.0f, 0.0f);
        pathBuilder4.lineToRelative(0.0f, -1.99f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _carRepair = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
