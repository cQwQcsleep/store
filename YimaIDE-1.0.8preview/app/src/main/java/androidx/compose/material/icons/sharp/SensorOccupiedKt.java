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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sensorOccupied", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SensorOccupied", "Landroidx/compose/material/icons/Icons$Sharp;", "getSensorOccupied", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SensorOccupiedKt {
    private static ImageVector _sensorOccupied;

    public static final ImageVector getSensorOccupied(Icons.Sharp sharp) {
        ImageVector imageVector = _sensorOccupied;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SensorOccupied", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 11.0f);
        pathBuilder.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilder.reflectiveCurveToRelative(-1.34f, -3.0f, -3.0f, -3.0f);
        pathBuilder.reflectiveCurveTo(9.0f, 6.34f, 9.0f, 8.0f);
        pathBuilder.reflectiveCurveTo(10.34f, 11.0f, 12.0f, 11.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(12.0f, 12.0f);
        pathBuilder2.curveToRelative(-1.84f, 0.0f, -3.56f, 0.5f, -5.03f, 1.37f);
        pathBuilder2.curveTo(6.36f, 13.72f, 6.0f, 14.39f, 6.0f, 15.09f);
        pathBuilder2.verticalLineTo(17.0f);
        pathBuilder2.horizontalLineToRelative(12.0f);
        pathBuilder2.verticalLineToRelative(-1.91f);
        pathBuilder2.curveToRelative(0.0f, -0.7f, -0.36f, -1.36f, -0.97f, -1.72f);
        pathBuilder2.curveTo(15.56f, 12.5f, 13.84f, 12.0f, 12.0f, 12.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(21.23f, 8.15f);
        pathBuilder3.lineToRelative(1.85f, -0.77f);
        pathBuilder3.curveToRelative(-1.22f, -2.91f, -3.55f, -5.25f, -6.46f, -6.46f);
        pathBuilder3.lineToRelative(-0.77f, 1.85f);
        pathBuilder3.curveTo(18.27f, 3.79f, 20.21f, 5.73f, 21.23f, 8.15f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(8.15f, 2.77f);
        pathBuilder4.lineTo(7.38f, 0.92f);
        pathBuilder4.curveTo(4.47f, 2.14f, 2.14f, 4.47f, 0.92f, 7.38f);
        pathBuilder4.lineToRelative(1.85f, 0.77f);
        pathBuilder4.curveTo(3.79f, 5.73f, 5.73f, 3.79f, 8.15f, 2.77f);
        pathBuilder4.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i9 = companion2.getButt-KaPHkGw();
        int i10 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder5 = new PathBuilder();
        pathBuilder5.moveTo(2.77f, 15.85f);
        pathBuilder5.lineToRelative(-1.85f, 0.77f);
        pathBuilder5.curveToRelative(1.22f, 2.91f, 3.55f, 5.25f, 6.46f, 6.46f);
        pathBuilder5.lineToRelative(0.77f, -1.85f);
        pathBuilder5.curveTo(5.73f, 20.21f, 3.79f, 18.27f, 2.77f, 15.85f);
        pathBuilder5.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder5.getNodes(), defaultFillType5, "", solidColor5, 1.0f, (Brush) null, 1.0f, 1.0f, i9, i10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType6 = VectorKt.getDefaultFillType();
        SolidColor solidColor6 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i11 = companion2.getButt-KaPHkGw();
        int i12 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder6 = new PathBuilder();
        pathBuilder6.moveTo(15.85f, 21.23f);
        pathBuilder6.lineToRelative(0.77f, 1.85f);
        pathBuilder6.curveToRelative(2.91f, -1.22f, 5.25f, -3.55f, 6.46f, -6.46f);
        pathBuilder6.lineToRelative(-1.85f, -0.77f);
        pathBuilder6.curveTo(20.21f, 18.27f, 18.27f, 20.21f, 15.85f, 21.23f);
        pathBuilder6.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder6.getNodes(), defaultFillType6, "", solidColor6, 1.0f, (Brush) null, 1.0f, 1.0f, i11, i12, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _sensorOccupied = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
