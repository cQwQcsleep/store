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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rowing", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rowing", "Landroidx/compose/material/icons/Icons$TwoTone;", "getRowing", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RowingKt {
    private static ImageVector _rowing;

    public static final ImageVector getRowing(Icons.TwoTone twoTone) {
        ImageVector imageVector = _rowing;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Rowing", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(8.5f, 14.5f);
        pathBuilder.lineTo(4.0f, 19.0f);
        pathBuilder.lineToRelative(1.5f, 1.5f);
        pathBuilder.lineTo(9.0f, 17.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.lineToRelative(-2.5f, -2.5f);
        pathBuilder.close();
        pathBuilder.moveTo(15.0f, 1.0f);
        pathBuilder.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.reflectiveCurveToRelative(2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder.close();
        pathBuilder.moveTo(21.0f, 21.01f);
        pathBuilder.lineTo(18.0f, 24.0f);
        pathBuilder.lineToRelative(-2.99f, -3.01f);
        pathBuilder.lineTo(15.01f, 19.5f);
        pathBuilder.lineToRelative(-7.1f, -7.09f);
        pathBuilder.curveToRelative(-0.31f, 0.05f, -0.61f, 0.07f, -0.91f, 0.07f);
        pathBuilder.verticalLineToRelative(-2.16f);
        pathBuilder.curveToRelative(1.66f, 0.03f, 3.61f, -0.87f, 4.67f, -2.04f);
        pathBuilder.lineToRelative(1.4f, -1.55f);
        pathBuilder.curveToRelative(0.19f, -0.21f, 0.43f, -0.38f, 0.69f, -0.5f);
        pathBuilder.curveToRelative(0.29f, -0.14f, 0.62f, -0.23f, 0.96f, -0.23f);
        pathBuilder.horizontalLineToRelative(0.03f);
        pathBuilder.curveTo(15.99f, 6.01f, 17.0f, 7.02f, 17.0f, 8.26f);
        pathBuilder.verticalLineToRelative(5.75f);
        pathBuilder.curveToRelative(0.0f, 0.84f, -0.35f, 1.61f, -0.92f, 2.16f);
        pathBuilder.lineToRelative(-3.58f, -3.58f);
        pathBuilder.verticalLineToRelative(-2.27f);
        pathBuilder.curveToRelative(-0.63f, 0.52f, -1.43f, 1.02f, -2.29f, 1.39f);
        pathBuilder.lineTo(16.5f, 18.0f);
        pathBuilder.lineTo(18.0f, 18.0f);
        pathBuilder.lineToRelative(3.0f, 3.01f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _rowing = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
