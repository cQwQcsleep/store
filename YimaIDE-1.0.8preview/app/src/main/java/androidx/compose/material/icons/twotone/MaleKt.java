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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_male", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Male", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMale", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MaleKt {
    private static ImageVector _male;

    public static final ImageVector getMale(Icons.TwoTone twoTone) {
        ImageVector imageVector = _male;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.Male", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.5f, 11.0f);
        pathBuilder.curveToRelative(1.93f, 0.0f, 3.5f, 1.57f, 3.5f, 3.5f);
        pathBuilder.reflectiveCurveTo(11.43f, 18.0f, 9.5f, 18.0f);
        pathBuilder.reflectiveCurveTo(6.0f, 16.43f, 6.0f, 14.5f);
        pathBuilder.reflectiveCurveTo(7.57f, 11.0f, 9.5f, 11.0f);
        pathBuilder.close();
        pathBuilder.moveTo(9.5f, 9.0f);
        pathBuilder.curveTo(6.46f, 9.0f, 4.0f, 11.46f, 4.0f, 14.5f);
        pathBuilder.reflectiveCurveTo(6.46f, 20.0f, 9.5f, 20.0f);
        pathBuilder.reflectiveCurveToRelative(5.5f, -2.46f, 5.5f, -5.5f);
        pathBuilder.curveToRelative(0.0f, -1.16f, -0.36f, -2.23f, -0.97f, -3.12f);
        pathBuilder.lineTo(18.0f, 7.42f);
        pathBuilder.verticalLineTo(10.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineTo(4.0f);
        pathBuilder.horizontalLineToRelative(-6.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(2.58f);
        pathBuilder.lineToRelative(-3.97f, 3.97f);
        pathBuilder.curveTo(11.73f, 9.36f, 10.66f, 9.0f, 9.5f, 9.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _male = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
