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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_starRate", "Landroidx/compose/ui/graphics/vector/ImageVector;", "StarRate", "Landroidx/compose/material/icons/Icons$TwoTone;", "getStarRate", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class StarRateKt {
    private static ImageVector _starRate;

    public static final ImageVector getStarRate(Icons.TwoTone twoTone) {
        ImageVector imageVector = _starRate;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.StarRate", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.94f, 12.0f);
        pathBuilder.lineToRelative(-0.94f, -3.11f);
        pathBuilder.lineToRelative(-0.94f, 3.11f);
        pathBuilder.lineToRelative(-2.82f, 0.0f);
        pathBuilder.lineToRelative(2.27f, 1.62f);
        pathBuilder.lineToRelative(-0.93f, 3.01f);
        pathBuilder.lineToRelative(2.42f, -1.84f);
        pathBuilder.lineToRelative(2.42f, 1.84f);
        pathBuilder.lineToRelative(-0.93f, -3.01f);
        pathBuilder.lineToRelative(2.27f, -1.62f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(22.0f, 10.0f);
        pathBuilder2.horizontalLineToRelative(-7.58f);
        pathBuilder2.lineTo(12.0f, 2.0f);
        pathBuilder2.lineToRelative(-2.42f, 8.0f);
        pathBuilder2.horizontalLineTo(2.0f);
        pathBuilder2.lineToRelative(6.17f, 4.41f);
        pathBuilder2.lineTo(5.83f, 22.0f);
        pathBuilder2.lineTo(12.0f, 17.31f);
        pathBuilder2.lineTo(18.17f, 22.0f);
        pathBuilder2.lineToRelative(-2.35f, -7.59f);
        pathBuilder2.lineTo(22.0f, 10.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(14.42f, 16.63f);
        pathBuilder2.lineTo(12.0f, 14.79f);
        pathBuilder2.lineToRelative(-2.42f, 1.84f);
        pathBuilder2.lineToRelative(0.93f, -3.01f);
        pathBuilder2.lineTo(8.24f, 12.0f);
        pathBuilder2.horizontalLineToRelative(2.82f);
        pathBuilder2.lineTo(12.0f, 8.89f);
        pathBuilder2.lineTo(12.94f, 12.0f);
        pathBuilder2.horizontalLineToRelative(2.82f);
        pathBuilder2.lineToRelative(-2.27f, 1.62f);
        pathBuilder2.lineTo(14.42f, 16.63f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _starRate = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
