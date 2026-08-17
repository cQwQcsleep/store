package androidx.compose.material.icons.sharp;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_lowPriority", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LowPriority", "Landroidx/compose/material/icons/Icons$Sharp;", "getLowPriority", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LowPriorityKt {
    private static ImageVector _lowPriority;

    public static final ImageVector getLowPriority(Icons.Sharp sharp) {
        ImageVector imageVector = _lowPriority;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.LowPriority", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.0f, 5.0f);
        pathBuilder.horizontalLineToRelative(8.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(-8.0f);
        pathBuilder.lineTo(14.0f, 5.0f);
        pathBuilder.close();
        pathBuilder.moveTo(14.0f, 10.5f);
        pathBuilder.horizontalLineToRelative(8.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(-8.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.close();
        pathBuilder.moveTo(14.0f, 16.0f);
        pathBuilder.horizontalLineToRelative(8.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(-8.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.close();
        pathBuilder.moveTo(2.0f, 11.5f);
        pathBuilder.curveTo(2.0f, 15.08f, 4.92f, 18.0f, 8.5f, 18.0f);
        pathBuilder.lineTo(9.0f, 18.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.lineToRelative(3.0f, -3.0f);
        pathBuilder.lineToRelative(-3.0f, -3.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(-0.5f);
        pathBuilder.curveTo(6.02f, 16.0f, 4.0f, 13.98f, 4.0f, 11.5f);
        pathBuilder.reflectiveCurveTo(6.02f, 7.0f, 8.5f, 7.0f);
        pathBuilder.lineTo(12.0f, 7.0f);
        pathBuilder.lineTo(12.0f, 5.0f);
        pathBuilder.lineTo(8.5f, 5.0f);
        pathBuilder.curveTo(4.92f, 5.0f, 2.0f, 7.92f, 2.0f, 11.5f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _lowPriority = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
