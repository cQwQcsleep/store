package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_keyboardCommandKey", "Landroidx/compose/ui/graphics/vector/ImageVector;", "KeyboardCommandKey", "Landroidx/compose/material/icons/Icons$Rounded;", "getKeyboardCommandKey", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class KeyboardCommandKeyKt {
    private static ImageVector _keyboardCommandKey;

    public static final ImageVector getKeyboardCommandKey(Icons.Rounded rounded) {
        ImageVector imageVector = _keyboardCommandKey;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.KeyboardCommandKey", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(17.5f, 3.0f);
        pathBuilder.curveTo(15.57f, 3.0f, 14.0f, 4.57f, 14.0f, 6.5f);
        pathBuilder.verticalLineTo(8.0f);
        pathBuilder.horizontalLineToRelative(-4.0f);
        pathBuilder.verticalLineTo(6.5f);
        pathBuilder.curveTo(10.0f, 4.57f, 8.43f, 3.0f, 6.5f, 3.0f);
        pathBuilder.reflectiveCurveTo(3.0f, 4.57f, 3.0f, 6.5f);
        pathBuilder.reflectiveCurveTo(4.57f, 10.0f, 6.5f, 10.0f);
        pathBuilder.horizontalLineTo(8.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.horizontalLineTo(6.5f);
        pathBuilder.curveTo(4.57f, 14.0f, 3.0f, 15.57f, 3.0f, 17.5f);
        pathBuilder.reflectiveCurveTo(4.57f, 21.0f, 6.5f, 21.0f);
        pathBuilder.reflectiveCurveToRelative(3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilder.verticalLineTo(16.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.verticalLineToRelative(1.5f);
        pathBuilder.curveToRelative(0.0f, 1.93f, 1.57f, 3.5f, 3.5f, 3.5f);
        pathBuilder.reflectiveCurveToRelative(3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilder.reflectiveCurveTo(19.43f, 14.0f, 17.5f, 14.0f);
        pathBuilder.horizontalLineTo(16.0f);
        pathBuilder.verticalLineToRelative(-4.0f);
        pathBuilder.horizontalLineToRelative(1.5f);
        pathBuilder.curveToRelative(1.93f, 0.0f, 3.5f, -1.57f, 3.5f, -3.5f);
        pathBuilder.reflectiveCurveTo(19.43f, 3.0f, 17.5f, 3.0f);
        pathBuilder.lineTo(17.5f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(16.0f, 8.0f);
        pathBuilder.verticalLineTo(6.5f);
        pathBuilder.curveTo(16.0f, 5.67f, 16.67f, 5.0f, 17.5f, 5.0f);
        pathBuilder.reflectiveCurveTo(19.0f, 5.67f, 19.0f, 6.5f);
        pathBuilder.reflectiveCurveTo(18.33f, 8.0f, 17.5f, 8.0f);
        pathBuilder.horizontalLineTo(16.0f);
        pathBuilder.lineTo(16.0f, 8.0f);
        pathBuilder.close();
        pathBuilder.moveTo(6.5f, 8.0f);
        pathBuilder.curveTo(5.67f, 8.0f, 5.0f, 7.33f, 5.0f, 6.5f);
        pathBuilder.reflectiveCurveTo(5.67f, 5.0f, 6.5f, 5.0f);
        pathBuilder.reflectiveCurveTo(8.0f, 5.67f, 8.0f, 6.5f);
        pathBuilder.verticalLineTo(8.0f);
        pathBuilder.horizontalLineTo(6.5f);
        pathBuilder.lineTo(6.5f, 8.0f);
        pathBuilder.close();
        pathBuilder.moveTo(10.0f, 14.0f);
        pathBuilder.verticalLineToRelative(-4.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.horizontalLineTo(10.0f);
        pathBuilder.lineTo(10.0f, 14.0f);
        pathBuilder.close();
        pathBuilder.moveTo(17.5f, 19.0f);
        pathBuilder.curveToRelative(-0.83f, 0.0f, -1.5f, -0.67f, -1.5f, -1.5f);
        pathBuilder.verticalLineTo(16.0f);
        pathBuilder.horizontalLineToRelative(1.5f);
        pathBuilder.curveToRelative(0.83f, 0.0f, 1.5f, 0.67f, 1.5f, 1.5f);
        pathBuilder.reflectiveCurveTo(18.33f, 19.0f, 17.5f, 19.0f);
        pathBuilder.lineTo(17.5f, 19.0f);
        pathBuilder.close();
        pathBuilder.moveTo(6.5f, 19.0f);
        pathBuilder.curveTo(5.67f, 19.0f, 5.0f, 18.33f, 5.0f, 17.5f);
        pathBuilder.reflectiveCurveTo(5.67f, 16.0f, 6.5f, 16.0f);
        pathBuilder.horizontalLineTo(8.0f);
        pathBuilder.verticalLineToRelative(1.5f);
        pathBuilder.curveTo(8.0f, 18.33f, 7.33f, 19.0f, 6.5f, 19.0f);
        pathBuilder.lineTo(6.5f, 19.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _keyboardCommandKey = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
