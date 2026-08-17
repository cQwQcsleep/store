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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_departureBoard", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DepartureBoard", "Landroidx/compose/material/icons/Icons$Outlined;", "getDepartureBoard", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DepartureBoardKt {
    private static ImageVector _departureBoard;

    public static final ImageVector getDepartureBoard(Icons.Outlined outlined) {
        ImageVector imageVector = _departureBoard;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.DepartureBoard", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(5.5f, 16.5f);
        pathBuilder.moveToRelative(-1.5f, 0.0f);
        pathBuilder.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilder.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(12.5f, 16.5f);
        pathBuilder2.moveToRelative(-1.5f, 0.0f);
        pathBuilder2.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilder2.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(16.0f, 1.0f);
        pathBuilder3.curveToRelative(-2.39f, 0.0f, -4.49f, 1.2f, -5.75f, 3.02f);
        pathBuilder3.curveTo(9.84f, 4.01f, 9.43f, 4.0f, 9.0f, 4.0f);
        pathBuilder3.curveToRelative(-4.42f, 0.0f, -8.0f, 0.5f, -8.0f, 4.0f);
        pathBuilder3.verticalLineToRelative(10.0f);
        pathBuilder3.curveToRelative(0.0f, 0.88f, 0.39f, 1.67f, 1.0f, 2.22f);
        pathBuilder3.lineTo(2.0f, 22.0f);
        pathBuilder3.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder3.horizontalLineToRelative(1.0f);
        pathBuilder3.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder3.verticalLineToRelative(-1.0f);
        pathBuilder3.horizontalLineToRelative(8.0f);
        pathBuilder3.verticalLineToRelative(1.0f);
        pathBuilder3.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder3.horizontalLineToRelative(1.0f);
        pathBuilder3.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder3.verticalLineToRelative(-1.78f);
        pathBuilder3.curveToRelative(0.61f, -0.55f, 1.0f, -1.34f, 1.0f, -2.22f);
        pathBuilder3.verticalLineToRelative(-3.08f);
        pathBuilder3.curveToRelative(3.39f, -0.49f, 6.0f, -3.39f, 6.0f, -6.92f);
        pathBuilder3.curveToRelative(0.0f, -3.87f, -3.13f, -7.0f, -7.0f, -7.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(9.0f, 6.0f);
        pathBuilder3.horizontalLineToRelative(0.29f);
        pathBuilder3.curveToRelative(-0.09f, 0.32f, -0.16f, 0.66f, -0.21f, 0.99f);
        pathBuilder3.lineTo(3.34f, 6.99f);
        pathBuilder3.curveTo(3.89f, 6.46f, 5.31f, 6.0f, 9.0f, 6.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(3.0f, 8.99f);
        pathBuilder3.horizontalLineToRelative(6.08f);
        pathBuilder3.curveToRelative(0.16f, 1.11f, 0.57f, 2.13f, 1.18f, 3.01f);
        pathBuilder3.lineTo(3.0f, 12.0f);
        pathBuilder3.lineTo(3.0f, 8.99f);
        pathBuilder3.close();
        pathBuilder3.moveTo(15.0f, 18.0f);
        pathBuilder3.curveToRelative(0.0f, 0.37f, -0.21f, 0.62f, -0.34f, 0.73f);
        pathBuilder3.lineToRelative(-0.29f, 0.27f);
        pathBuilder3.lineTo(3.63f, 19.0f);
        pathBuilder3.lineToRelative(-0.29f, -0.27f);
        pathBuilder3.curveTo(3.21f, 18.62f, 3.0f, 18.37f, 3.0f, 18.0f);
        pathBuilder3.verticalLineToRelative(-4.0f);
        pathBuilder3.horizontalLineToRelative(9.41f);
        pathBuilder3.curveToRelative(0.78f, 0.47f, 1.65f, 0.79f, 2.59f, 0.92f);
        pathBuilder3.lineTo(15.0f, 18.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(16.0f, 13.0f);
        pathBuilder3.curveToRelative(-2.76f, 0.0f, -5.0f, -2.24f, -5.0f, -5.0f);
        pathBuilder3.reflectiveCurveToRelative(2.24f, -5.0f, 5.0f, -5.0f);
        pathBuilder3.reflectiveCurveToRelative(5.0f, 2.24f, 5.0f, 5.0f);
        pathBuilder3.reflectiveCurveToRelative(-2.24f, 5.0f, -5.0f, 5.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(16.5f, 4.0f);
        pathBuilder3.lineTo(15.0f, 4.0f);
        pathBuilder3.verticalLineToRelative(5.0f);
        pathBuilder3.lineToRelative(3.62f, 2.16f);
        pathBuilder3.lineToRelative(0.75f, -1.23f);
        pathBuilder3.lineToRelative(-2.87f, -1.68f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _departureBoard = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
