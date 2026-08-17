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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_snowmobile", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Snowmobile", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSnowmobile", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SnowmobileKt {
    private static ImageVector _snowmobile;

    public static final ImageVector getSnowmobile(Icons.TwoTone twoTone) {
        ImageVector imageVector = _snowmobile;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Snowmobile", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(17.0f, 14.0f);
        pathBuilder.horizontalLineToRelative(-6.7f);
        pathBuilder.lineToRelative(-7.45f, -2.23f);
        pathBuilder.lineToRelative(0.31f, -0.62f);
        pathBuilder.lineTo(11.6f, 12.0f);
        pathBuilder.lineToRelative(3.93f, -2.94f);
        pathBuilder.curveToRelative(0.0f, 0.0f, 3.77f, 3.44f, 4.27f, 4.14f);
        pathBuilder.curveTo(19.8f, 13.2f, 18.7f, 14.0f, 17.0f, 14.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(22.0f, 17.0f);
        pathBuilder2.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilder2.horizontalLineToRelative(-0.17f);
        pathBuilder2.lineToRelative(-2.2f, -2.2f);
        pathBuilder2.curveTo(20.58f, 15.37f, 22.0f, 14.4f, 22.0f, 13.0f);
        pathBuilder2.curveToRelative(0.0f, -1.0f, -8.0f, -8.0f, -8.0f, -8.0f);
        pathBuilder2.horizontalLineToRelative(-3.0f);
        pathBuilder2.verticalLineToRelative(2.0f);
        pathBuilder2.horizontalLineToRelative(2.25f);
        pathBuilder2.lineToRelative(0.8f, 0.72f);
        pathBuilder2.lineTo(11.0f, 10.0f);
        pathBuilder2.lineTo(2.0f, 9.0f);
        pathBuilder2.lineToRelative(-2.0f, 4.0f);
        pathBuilder2.lineToRelative(4.54f, 1.36f);
        pathBuilder2.lineToRelative(-3.49f, 1.88f);
        pathBuilder2.curveTo(-0.77f, 17.22f, -0.07f, 20.0f, 2.0f, 20.0f);
        pathBuilder2.horizontalLineToRelative(6.0f);
        pathBuilder2.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilder2.horizontalLineToRelative(4.0f);
        pathBuilder2.lineToRelative(2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(-3.0f);
        pathBuilder2.verticalLineToRelative(2.0f);
        pathBuilder2.horizontalLineToRelative(6.0f);
        pathBuilder2.curveToRelative(1.66f, 0.0f, 3.0f, -1.34f, 3.0f, -3.0f);
        pathBuilder2.horizontalLineTo(22.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(8.0f, 18.0f);
        pathBuilder2.horizontalLineTo(2.0f);
        pathBuilder2.lineToRelative(5.25f, -2.83f);
        pathBuilder2.lineTo(10.0f, 16.0f);
        pathBuilder2.curveTo(10.0f, 17.1f, 9.11f, 18.0f, 8.0f, 18.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(17.0f, 14.0f);
        pathBuilder2.horizontalLineToRelative(-6.7f);
        pathBuilder2.lineToRelative(-7.45f, -2.23f);
        pathBuilder2.lineToRelative(0.31f, -0.62f);
        pathBuilder2.lineTo(11.6f, 12.0f);
        pathBuilder2.lineToRelative(3.93f, -2.94f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, 3.77f, 3.44f, 4.27f, 4.14f);
        pathBuilder2.curveTo(19.8f, 13.2f, 18.7f, 14.0f, 17.0f, 14.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _snowmobile = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
