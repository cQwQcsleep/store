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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_girl", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Girl", "Landroidx/compose/material/icons/Icons$Rounded;", "getGirl", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GirlKt {
    private static ImageVector _girl;

    public static final ImageVector getGirl(Icons.Rounded rounded) {
        ImageVector imageVector = _girl;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.Girl", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 7.5f);
        pathBuilder.curveToRelative(0.97f, 0.0f, 1.75f, -0.78f, 1.75f, -1.75f);
        pathBuilder.reflectiveCurveTo(12.97f, 4.0f, 12.0f, 4.0f);
        pathBuilder.reflectiveCurveToRelative(-1.75f, 0.78f, -1.75f, 1.75f);
        pathBuilder.reflectiveCurveTo(11.03f, 7.5f, 12.0f, 7.5f);
        pathBuilder.close();
        pathBuilder.moveTo(14.0f, 16.0f);
        pathBuilder.verticalLineToRelative(3.0f);
        pathBuilder.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.curveToRelative(-0.55f, 0.0f, -1.0f, -0.45f, -1.0f, -1.0f);
        pathBuilder.verticalLineToRelative(-3.0f);
        pathBuilder.horizontalLineTo(9.44f);
        pathBuilder.curveToRelative(-0.7f, 0.0f, -1.18f, -0.7f, -0.94f, -1.35f);
        pathBuilder.lineToRelative(1.88f, -5.03f);
        pathBuilder.curveTo(10.63f, 8.95f, 11.28f, 8.5f, 12.0f, 8.5f);
        pathBuilder.reflectiveCurveToRelative(1.37f, 0.45f, 1.62f, 1.12f);
        pathBuilder.lineToRelative(1.88f, 5.03f);
        pathBuilder.curveTo(15.74f, 15.3f, 15.26f, 16.0f, 14.56f, 16.0f);
        pathBuilder.horizontalLineTo(14.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _girl = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
