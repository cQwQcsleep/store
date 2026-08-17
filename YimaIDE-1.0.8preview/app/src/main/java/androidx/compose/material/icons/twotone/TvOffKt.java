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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tvOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TvOff", "Landroidx/compose/material/icons/Icons$TwoTone;", "getTvOff", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TvOffKt {
    private static ImageVector _tvOff;

    public static final ImageVector getTvOff(Icons.TwoTone twoTone) {
        ImageVector imageVector = _tvOff;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.TvOff", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(3.0f, 19.0f);
        pathBuilder.horizontalLineToRelative(13.46f);
        pathBuilder.lineToRelative(-12.0f, -12.0f);
        pathBuilder.lineTo(3.0f, 7.0f);
        pathBuilder.close();
        pathBuilder.moveTo(10.12f, 7.0f);
        pathBuilder.lineTo(21.0f, 17.88f);
        pathBuilder.lineTo(21.0f, 7.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(21.0f, 7.0f);
        pathBuilder2.verticalLineToRelative(10.88f);
        pathBuilder2.lineToRelative(1.85f, 1.85f);
        pathBuilder2.curveToRelative(0.09f, -0.23f, 0.15f, -0.47f, 0.15f, -0.73f);
        pathBuilder2.lineTo(23.0f, 7.0f);
        pathBuilder2.curveToRelative(0.0f, -1.11f, -0.89f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.horizontalLineToRelative(-7.58f);
        pathBuilder2.lineToRelative(3.29f, -3.3f);
        pathBuilder2.lineTo(16.0f, 1.0f);
        pathBuilder2.lineToRelative(-4.0f, 4.0f);
        pathBuilder2.lineToRelative(-4.0f, -4.0f);
        pathBuilder2.lineToRelative(-0.7f, 0.7f);
        pathBuilder2.lineTo(10.58f, 5.0f);
        pathBuilder2.lineTo(8.12f, 5.0f);
        pathBuilder2.lineToRelative(2.0f, 2.0f);
        pathBuilder2.lineTo(21.0f, 7.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.46f, 23.0f);
        pathBuilder2.lineToRelative(1.26f, -1.27f);
        pathBuilder2.lineToRelative(-1.26f, 1.26f);
        pathBuilder2.close();
        pathBuilder2.moveTo(2.41f, 2.13f);
        pathBuilder2.lineToRelative(-0.14f, 0.14f);
        pathBuilder2.lineTo(1.0f, 3.54f);
        pathBuilder2.lineToRelative(1.53f, 1.53f);
        pathBuilder2.curveTo(1.65f, 5.28f, 1.0f, 6.06f, 1.0f, 7.0f);
        pathBuilder2.verticalLineToRelative(12.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(15.46f);
        pathBuilder2.lineToRelative(1.99f, 1.99f);
        pathBuilder2.lineToRelative(1.26f, -1.26f);
        pathBuilder2.lineToRelative(0.15f, -0.15f);
        pathBuilder2.lineTo(2.41f, 2.13f);
        pathBuilder2.close();
        pathBuilder2.moveTo(3.0f, 19.0f);
        pathBuilder2.lineTo(3.0f, 7.0f);
        pathBuilder2.horizontalLineToRelative(1.46f);
        pathBuilder2.lineToRelative(12.0f, 12.0f);
        pathBuilder2.lineTo(3.0f, 19.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _tvOff = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
