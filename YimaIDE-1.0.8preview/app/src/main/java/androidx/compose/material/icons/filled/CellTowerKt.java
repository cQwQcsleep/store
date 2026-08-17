package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_cellTower", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CellTower", "Landroidx/compose/material/icons/Icons$Filled;", "getCellTower", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CellTowerKt {
    private static ImageVector _cellTower;

    public static final ImageVector getCellTower(Icons.Filled filled) {
        ImageVector imageVector = _cellTower;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.CellTower", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(7.3f, 14.7f);
        pathBuilder.lineToRelative(1.2f, -1.2f);
        pathBuilder.curveToRelative(-1.0f, -1.0f, -1.5f, -2.3f, -1.5f, -3.5f);
        pathBuilder.curveToRelative(0.0f, -1.3f, 0.5f, -2.6f, 1.5f, -3.5f);
        pathBuilder.lineTo(7.3f, 5.3f);
        pathBuilder.curveToRelative(-1.3f, 1.3f, -2.0f, 3.0f, -2.0f, 4.7f);
        pathBuilder.reflectiveCurveTo(6.0f, 13.4f, 7.3f, 14.7f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(19.1f, 2.9f);
        pathBuilder2.lineToRelative(-1.2f, 1.2f);
        pathBuilder2.curveToRelative(1.6f, 1.6f, 2.4f, 3.8f, 2.4f, 5.9f);
        pathBuilder2.curveToRelative(0.0f, 2.1f, -0.8f, 4.3f, -2.4f, 5.9f);
        pathBuilder2.lineToRelative(1.2f, 1.2f);
        pathBuilder2.curveToRelative(2.0f, -2.0f, 2.9f, -4.5f, 2.9f, -7.1f);
        pathBuilder2.curveTo(22.0f, 7.4f, 21.0f, 4.9f, 19.1f, 2.9f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(6.1f, 4.1f);
        pathBuilder3.lineTo(4.9f, 2.9f);
        pathBuilder3.curveTo(3.0f, 4.9f, 2.0f, 7.4f, 2.0f, 10.0f);
        pathBuilder3.curveToRelative(0.0f, 2.6f, 1.0f, 5.1f, 2.9f, 7.1f);
        pathBuilder3.lineToRelative(1.2f, -1.2f);
        pathBuilder3.curveToRelative(-1.6f, -1.6f, -2.4f, -3.8f, -2.4f, -5.9f);
        pathBuilder3.curveTo(3.7f, 7.9f, 4.5f, 5.7f, 6.1f, 4.1f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(16.7f, 14.7f);
        pathBuilder4.curveToRelative(1.3f, -1.3f, 2.0f, -3.0f, 2.0f, -4.7f);
        pathBuilder4.curveToRelative(-0.1f, -1.7f, -0.7f, -3.4f, -2.0f, -4.7f);
        pathBuilder4.lineToRelative(-1.2f, 1.2f);
        pathBuilder4.curveToRelative(1.0f, 1.0f, 1.5f, 2.3f, 1.5f, 3.5f);
        pathBuilder4.curveToRelative(0.0f, 1.3f, -0.5f, 2.6f, -1.5f, 3.5f);
        pathBuilder4.lineTo(16.7f, 14.7f);
        pathBuilder4.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i9 = companion2.getButt-KaPHkGw();
        int i10 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder5 = new PathBuilder();
        pathBuilder5.moveTo(14.5f, 10.0f);
        pathBuilder5.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilder5.reflectiveCurveTo(9.5f, 8.62f, 9.5f, 10.0f);
        pathBuilder5.curveToRelative(0.0f, 0.76f, 0.34f, 1.42f, 0.87f, 1.88f);
        pathBuilder5.lineTo(7.0f, 22.0f);
        pathBuilder5.horizontalLineToRelative(2.0f);
        pathBuilder5.lineToRelative(0.67f, -2.0f);
        pathBuilder5.horizontalLineToRelative(4.67f);
        pathBuilder5.lineTo(15.0f, 22.0f);
        pathBuilder5.horizontalLineToRelative(2.0f);
        pathBuilder5.lineToRelative(-3.37f, -10.12f);
        pathBuilder5.curveTo(14.16f, 11.42f, 14.5f, 10.76f, 14.5f, 10.0f);
        pathBuilder5.close();
        pathBuilder5.moveTo(10.33f, 18.0f);
        pathBuilder5.lineTo(12.0f, 13.0f);
        pathBuilder5.lineToRelative(1.67f, 5.0f);
        pathBuilder5.horizontalLineTo(10.33f);
        pathBuilder5.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder5.getNodes(), defaultFillType5, "", solidColor5, 1.0f, (Brush) null, 1.0f, 1.0f, i9, i10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _cellTower = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
