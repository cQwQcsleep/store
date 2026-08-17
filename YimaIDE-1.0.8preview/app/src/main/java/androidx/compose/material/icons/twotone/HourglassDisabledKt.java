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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_hourglassDisabled", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HourglassDisabled", "Landroidx/compose/material/icons/Icons$TwoTone;", "getHourglassDisabled", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HourglassDisabledKt {
    private static ImageVector _hourglassDisabled;

    public static final ImageVector getHourglassDisabled(Icons.TwoTone twoTone) {
        ImageVector imageVector = _hourglassDisabled;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.HourglassDisabled", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(8.0f, 4.0f);
        pathBuilder.lineToRelative(8.0f, 0.0f);
        pathBuilder.lineToRelative(0.0f, 3.5f);
        pathBuilder.lineToRelative(-2.84f, 2.84f);
        pathBuilder.lineToRelative(1.25f, 1.25f);
        pathBuilder.lineToRelative(3.59f, -3.58f);
        pathBuilder.lineToRelative(-0.01f, -0.01f);
        pathBuilder.lineToRelative(0.01f, 0.0f);
        pathBuilder.lineToRelative(0.0f, -6.0f);
        pathBuilder.lineToRelative(-12.0f, 0.0f);
        pathBuilder.lineToRelative(0.0f, 1.17f);
        pathBuilder.lineToRelative(2.0f, 2.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(2.1f, 2.1f);
        pathBuilder2.lineTo(0.69f, 3.51f);
        pathBuilder2.lineToRelative(8.9f, 8.9f);
        pathBuilder2.lineTo(6.0f, 16.0f);
        pathBuilder2.lineToRelative(0.01f, 0.01f);
        pathBuilder2.horizontalLineTo(6.0f);
        pathBuilder2.verticalLineTo(22.0f);
        pathBuilder2.horizontalLineToRelative(12.0f);
        pathBuilder2.verticalLineToRelative(-1.17f);
        pathBuilder2.lineToRelative(2.49f, 2.49f);
        pathBuilder2.lineToRelative(1.41f, -1.41f);
        pathBuilder2.lineTo(2.1f, 2.1f);
        pathBuilder2.close();
        pathBuilder2.moveTo(16.0f, 20.0f);
        pathBuilder2.horizontalLineTo(8.0f);
        pathBuilder2.verticalLineToRelative(-3.5f);
        pathBuilder2.lineToRelative(2.84f, -2.84f);
        pathBuilder2.lineTo(16.0f, 18.83f);
        pathBuilder2.verticalLineTo(20.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _hourglassDisabled = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
