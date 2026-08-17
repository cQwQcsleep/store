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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_supervisorAccount", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SupervisorAccount", "Landroidx/compose/material/icons/Icons$Sharp;", "getSupervisorAccount", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SupervisorAccountKt {
    private static ImageVector _supervisorAccount;

    public static final ImageVector getSupervisorAccount(Icons.Sharp sharp) {
        ImageVector imageVector = _supervisorAccount;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.SupervisorAccount", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(16.5f, 12.0f);
        pathBuilder.curveToRelative(1.38f, 0.0f, 2.49f, -1.12f, 2.49f, -2.5f);
        pathBuilder.reflectiveCurveTo(17.88f, 7.0f, 16.5f, 7.0f);
        pathBuilder.reflectiveCurveTo(14.0f, 8.12f, 14.0f, 9.5f);
        pathBuilder.reflectiveCurveToRelative(1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilder.close();
        pathBuilder.moveTo(9.0f, 11.0f);
        pathBuilder.curveToRelative(1.66f, 0.0f, 2.99f, -1.34f, 2.99f, -3.0f);
        pathBuilder.reflectiveCurveTo(10.66f, 5.0f, 9.0f, 5.0f);
        pathBuilder.reflectiveCurveTo(6.0f, 6.34f, 6.0f, 8.0f);
        pathBuilder.reflectiveCurveToRelative(1.34f, 3.0f, 3.0f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(16.5f, 14.0f);
        pathBuilder.curveToRelative(-1.83f, 0.0f, -5.5f, 0.92f, -5.5f, 2.75f);
        pathBuilder.lineTo(11.0f, 19.0f);
        pathBuilder.horizontalLineToRelative(11.0f);
        pathBuilder.verticalLineToRelative(-2.25f);
        pathBuilder.curveToRelative(0.0f, -1.83f, -3.67f, -2.75f, -5.5f, -2.75f);
        pathBuilder.close();
        pathBuilder.moveTo(9.0f, 13.0f);
        pathBuilder.curveToRelative(-2.33f, 0.0f, -7.0f, 1.17f, -7.0f, 3.5f);
        pathBuilder.lineTo(2.0f, 19.0f);
        pathBuilder.horizontalLineToRelative(7.0f);
        pathBuilder.verticalLineToRelative(-2.25f);
        pathBuilder.curveToRelative(0.0f, -0.85f, 0.33f, -2.34f, 2.37f, -3.47f);
        pathBuilder.curveTo(10.5f, 13.1f, 9.66f, 13.0f, 9.0f, 13.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _supervisorAccount = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
