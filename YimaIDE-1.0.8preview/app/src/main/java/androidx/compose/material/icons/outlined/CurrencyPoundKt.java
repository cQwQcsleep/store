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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_currencyPound", "Landroidx/compose/ui/graphics/vector/ImageVector;", "CurrencyPound", "Landroidx/compose/material/icons/Icons$Outlined;", "getCurrencyPound", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class CurrencyPoundKt {
    private static ImageVector _currencyPound;

    public static final ImageVector getCurrencyPound(Icons.Outlined outlined) {
        ImageVector imageVector = _currencyPound;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.CurrencyPound", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.0f, 21.0f);
        pathBuilder.curveToRelative(1.93f, 0.0f, 3.62f, -1.17f, 4.0f, -3.0f);
        pathBuilder.lineToRelative(-1.75f, -0.88f);
        pathBuilder.curveTo(16.0f, 18.21f, 15.33f, 19.0f, 14.0f, 19.0f);
        pathBuilder.lineToRelative(-4.9f, 0.0f);
        pathBuilder.curveToRelative(0.83f, -1.0f, 1.5f, -2.34f, 1.5f, -4.0f);
        pathBuilder.curveToRelative(0.0f, -0.35f, -0.03f, -0.69f, -0.08f, -1.0f);
        pathBuilder.lineTo(14.0f, 14.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.lineToRelative(-4.18f, 0.0f);
        pathBuilder.curveTo(9.0f, 10.42f, 8.0f, 9.6f, 8.0f, 8.0f);
        pathBuilder.curveToRelative(0.0f, -1.93f, 1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilder.curveToRelative(1.5f, 0.0f, 2.79f, 0.95f, 3.28f, 2.28f);
        pathBuilder.lineTo(16.63f, 6.0f);
        pathBuilder.curveToRelative(-0.8f, -2.05f, -2.79f, -3.5f, -5.13f, -3.5f);
        pathBuilder.curveTo(8.46f, 2.5f, 6.0f, 4.96f, 6.0f, 8.0f);
        pathBuilder.curveToRelative(0.0f, 1.78f, 0.79f, 2.9f, 1.49f, 4.0f);
        pathBuilder.lineTo(6.0f, 12.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.lineToRelative(2.47f, 0.0f);
        pathBuilder.curveToRelative(0.08f, 0.31f, 0.13f, 0.64f, 0.13f, 1.0f);
        pathBuilder.curveToRelative(0.0f, 2.7f, -2.6f, 4.0f, -2.6f, 4.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineTo(14.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _currencyPound = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
