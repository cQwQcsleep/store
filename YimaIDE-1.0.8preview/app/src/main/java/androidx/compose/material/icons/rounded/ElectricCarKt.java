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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_electricCar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ElectricCar", "Landroidx/compose/material/icons/Icons$Rounded;", "getElectricCar", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ElectricCarKt {
    private static ImageVector _electricCar;

    public static final ImageVector getElectricCar(Icons.Rounded rounded) {
        ImageVector imageVector = _electricCar;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ElectricCar", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.92f, 2.01f);
        pathBuilder.curveTo(18.72f, 1.42f, 18.16f, 1.0f, 17.5f, 1.0f);
        pathBuilder.horizontalLineToRelative(-11.0f);
        pathBuilder.curveTo(5.84f, 1.0f, 5.29f, 1.42f, 5.08f, 2.01f);
        pathBuilder.lineTo(3.11f, 7.68f);
        pathBuilder.curveTo(3.04f, 7.89f, 3.0f, 8.11f, 3.0f, 8.34f);
        pathBuilder.verticalLineToRelative(7.16f);
        pathBuilder.curveTo(3.0f, 16.33f, 3.67f, 17.0f, 4.5f, 17.0f);
        pathBuilder.horizontalLineToRelative(0.0f);
        pathBuilder.curveTo(5.33f, 17.0f, 6.0f, 16.33f, 6.0f, 15.5f);
        pathBuilder.verticalLineTo(15.0f);
        pathBuilder.horizontalLineToRelative(12.0f);
        pathBuilder.verticalLineToRelative(0.5f);
        pathBuilder.curveToRelative(0.0f, 0.82f, 0.67f, 1.5f, 1.5f, 1.5f);
        pathBuilder.horizontalLineToRelative(0.0f);
        pathBuilder.curveToRelative(0.82f, 0.0f, 1.5f, -0.67f, 1.5f, -1.5f);
        pathBuilder.verticalLineTo(8.34f);
        pathBuilder.curveToRelative(0.0f, -0.22f, -0.04f, -0.45f, -0.11f, -0.66f);
        pathBuilder.lineTo(18.92f, 2.01f);
        pathBuilder.close();
        pathBuilder.moveTo(6.5f, 12.0f);
        pathBuilder.curveTo(5.67f, 12.0f, 5.0f, 11.33f, 5.0f, 10.5f);
        pathBuilder.reflectiveCurveTo(5.67f, 9.0f, 6.5f, 9.0f);
        pathBuilder.reflectiveCurveTo(8.0f, 9.67f, 8.0f, 10.5f);
        pathBuilder.reflectiveCurveTo(7.33f, 12.0f, 6.5f, 12.0f);
        pathBuilder.close();
        pathBuilder.moveTo(17.5f, 12.0f);
        pathBuilder.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilder.reflectiveCurveTo(16.67f, 9.0f, 17.5f, 9.0f);
        pathBuilder.reflectiveCurveTo(19.0f, 9.67f, 19.0f, 10.5f);
        pathBuilder.reflectiveCurveTo(18.33f, 12.0f, 17.5f, 12.0f);
        pathBuilder.close();
        pathBuilder.moveTo(5.0f, 7.0f);
        pathBuilder.lineToRelative(1.27f, -3.82f);
        pathBuilder.curveTo(6.41f, 2.78f, 6.79f, 2.5f, 7.22f, 2.5f);
        pathBuilder.horizontalLineToRelative(9.56f);
        pathBuilder.curveToRelative(0.43f, 0.0f, 0.81f, 0.28f, 0.95f, 0.68f);
        pathBuilder.lineTo(19.0f, 7.0f);
        pathBuilder.horizontalLineTo(5.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(7.0f, 20.0f);
        pathBuilder2.lineToRelative(4.0f, 0.0f);
        pathBuilder2.lineToRelative(0.0f, -2.0f);
        pathBuilder2.lineToRelative(6.0f, 3.0f);
        pathBuilder2.lineToRelative(-4.0f, 0.0f);
        pathBuilder2.lineToRelative(0.0f, 2.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _electricCar = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
