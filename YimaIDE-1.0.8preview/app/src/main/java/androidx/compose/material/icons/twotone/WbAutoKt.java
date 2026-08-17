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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_wbAuto", "Landroidx/compose/ui/graphics/vector/ImageVector;", "WbAuto", "Landroidx/compose/material/icons/Icons$TwoTone;", "getWbAuto", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class WbAutoKt {
    private static ImageVector _wbAuto;

    public static final ImageVector getWbAuto(Icons.TwoTone twoTone) {
        ImageVector imageVector = _wbAuto;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.WbAuto", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(8.0f, 6.0f);
        pathBuilder.curveToRelative(-3.31f, 0.0f, -6.0f, 2.69f, -6.0f, 6.0f);
        pathBuilder.reflectiveCurveToRelative(2.69f, 6.0f, 6.0f, 6.0f);
        pathBuilder.curveToRelative(2.35f, 0.0f, 4.38f, -1.36f, 5.36f, -3.32f);
        pathBuilder.lineToRelative(0.01f, -0.01f);
        pathBuilder.curveToRelative(0.4f, -0.81f, 0.63f, -1.71f, 0.63f, -2.67f);
        pathBuilder.curveToRelative(0.0f, -3.31f, -2.69f, -6.0f, -6.0f, -6.0f);
        pathBuilder.close();
        pathBuilder.moveTo(10.3f, 16.0f);
        pathBuilder.lineToRelative(-0.7f, -2.0f);
        pathBuilder.lineTo(6.4f, 14.0f);
        pathBuilder.lineToRelative(-0.7f, 2.0f);
        pathBuilder.lineTo(3.8f, 16.0f);
        pathBuilder.lineTo(7.0f, 7.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.lineToRelative(3.2f, 9.0f);
        pathBuilder.horizontalLineToRelative(-1.9f);
        pathBuilder.close();
        pathBuilder.moveTo(6.85f, 12.65f);
        pathBuilder.horizontalLineToRelative(2.3f);
        pathBuilder.lineTo(8.0f, 9.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(7.0f, 7.0f);
        pathBuilder2.lineToRelative(-3.2f, 9.0f);
        pathBuilder2.horizontalLineToRelative(1.9f);
        pathBuilder2.lineToRelative(0.7f, -2.0f);
        pathBuilder2.horizontalLineToRelative(3.2f);
        pathBuilder2.lineToRelative(0.7f, 2.0f);
        pathBuilder2.horizontalLineToRelative(1.9f);
        pathBuilder2.lineTo(9.0f, 7.0f);
        pathBuilder2.lineTo(7.0f, 7.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(6.85f, 12.65f);
        pathBuilder2.lineTo(8.0f, 9.0f);
        pathBuilder2.lineToRelative(1.15f, 3.65f);
        pathBuilder2.horizontalLineToRelative(-2.3f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.8f, 13.29f);
        pathBuilder2.lineTo(19.3f, 7.0f);
        pathBuilder2.horizontalLineToRelative(-1.6f);
        pathBuilder2.lineToRelative(-1.49f, 6.29f);
        pathBuilder2.lineTo(15.0f, 7.0f);
        pathBuilder2.horizontalLineToRelative(-0.76f);
        pathBuilder2.lineToRelative(-0.01f, 0.01f);
        pathBuilder2.curveTo(12.76f, 5.18f, 10.53f, 4.0f, 8.0f, 4.0f);
        pathBuilder2.curveToRelative(-4.42f, 0.0f, -8.0f, 3.58f, -8.0f, 8.0f);
        pathBuilder2.reflectiveCurveToRelative(3.58f, 8.0f, 8.0f, 8.0f);
        pathBuilder2.curveToRelative(2.96f, 0.0f, 5.55f, -1.61f, 6.93f, -4.0f);
        pathBuilder2.curveToRelative(0.03f, -0.06f, 0.05f, -0.12f, 0.08f, -0.18f);
        pathBuilder2.curveToRelative(0.05f, -0.08f, 0.09f, -0.17f, 0.14f, -0.25f);
        pathBuilder2.lineToRelative(0.1f, 0.43f);
        pathBuilder2.lineTo(17.0f, 16.0f);
        pathBuilder2.lineToRelative(1.5f, -6.1f);
        pathBuilder2.lineTo(20.0f, 16.0f);
        pathBuilder2.horizontalLineToRelative(1.75f);
        pathBuilder2.lineToRelative(2.05f, -9.0f);
        pathBuilder2.lineTo(22.0f, 7.0f);
        pathBuilder2.lineToRelative(-1.2f, 6.29f);
        pathBuilder2.close();
        pathBuilder2.moveTo(13.37f, 14.67f);
        pathBuilder2.curveTo(12.38f, 16.64f, 10.35f, 18.0f, 8.0f, 18.0f);
        pathBuilder2.curveToRelative(-3.31f, 0.0f, -6.0f, -2.69f, -6.0f, -6.0f);
        pathBuilder2.reflectiveCurveToRelative(2.69f, -6.0f, 6.0f, -6.0f);
        pathBuilder2.reflectiveCurveToRelative(6.0f, 2.69f, 6.0f, 6.0f);
        pathBuilder2.curveToRelative(0.0f, 0.96f, -0.23f, 1.86f, -0.63f, 2.67f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _wbAuto = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
