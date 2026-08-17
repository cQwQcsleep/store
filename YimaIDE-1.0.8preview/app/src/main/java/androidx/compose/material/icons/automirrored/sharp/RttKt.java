package androidx.compose.material.icons.automirrored.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_rtt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Rtt", "Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;", "getRtt", "(Landroidx/compose/material/icons/Icons$AutoMirrored$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class RttKt {
    private static ImageVector _rtt;

    public static final ImageVector getRtt(Icons.AutoMirrored.Sharp sharp) {
        ImageVector imageVector = _rtt;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("AutoMirrored.Sharp.Rtt", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, true, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.03f, 3.0f);
        pathBuilder.lineToRelative(-1.11f, 7.07f);
        pathBuilder.horizontalLineToRelative(2.62f);
        pathBuilder.lineToRelative(0.7f, -4.5f);
        pathBuilder.horizontalLineToRelative(2.58f);
        pathBuilder.lineTo(11.8f, 18.43f);
        pathBuilder.horizontalLineTo(9.47f);
        pathBuilder.lineTo(9.06f, 21.0f);
        pathBuilder.horizontalLineToRelative(7.27f);
        pathBuilder.lineToRelative(0.4f, -2.57f);
        pathBuilder.horizontalLineToRelative(-2.35f);
        pathBuilder.lineToRelative(2.0f, -12.86f);
        pathBuilder.horizontalLineToRelative(2.58f);
        pathBuilder.lineToRelative(-0.71f, 4.5f);
        pathBuilder.horizontalLineToRelative(2.65f);
        pathBuilder.lineTo(22.0f, 3.0f);
        pathBuilder.horizontalLineTo(9.03f);
        pathBuilder.close();
        pathBuilder.moveTo(8.0f, 5.0f);
        pathBuilder.horizontalLineTo(4.0f);
        pathBuilder.lineTo(3.69f, 7.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.lineTo(8.0f, 5.0f);
        pathBuilder.close();
        pathBuilder.moveTo(7.39f, 9.0f);
        pathBuilder.horizontalLineToRelative(-4.0f);
        pathBuilder.lineToRelative(-0.31f, 2.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.lineTo(7.39f, 9.0f);
        pathBuilder.close();
        pathBuilder.moveTo(8.31f, 17.0f);
        pathBuilder.horizontalLineToRelative(-6.0f);
        pathBuilder.lineTo(2.0f, 19.0f);
        pathBuilder.horizontalLineToRelative(6.0f);
        pathBuilder.lineTo(8.31f, 17.0f);
        pathBuilder.close();
        pathBuilder.moveTo(8.93f, 13.0f);
        pathBuilder.horizontalLineToRelative(-6.0f);
        pathBuilder.lineToRelative(-0.31f, 2.0f);
        pathBuilder.horizontalLineToRelative(6.01f);
        pathBuilder.lineTo(8.93f, 13.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _rtt = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
