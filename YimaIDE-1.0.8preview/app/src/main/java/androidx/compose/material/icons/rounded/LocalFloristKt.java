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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localFlorist", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalFlorist", "Landroidx/compose/material/icons/Icons$Rounded;", "getLocalFlorist", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalFloristKt {
    private static ImageVector _localFlorist;

    public static final ImageVector getLocalFlorist(Icons.Rounded rounded) {
        ImageVector imageVector = _localFlorist;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LocalFlorist", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 22.0f);
        pathBuilder.curveToRelative(4.56f, 0.0f, 8.33f, -3.4f, 8.92f, -7.8f);
        pathBuilder.curveToRelative(0.09f, -0.64f, -0.48f, -1.21f, -1.12f, -1.12f);
        pathBuilder.curveToRelative(-4.4f, 0.59f, -7.8f, 4.36f, -7.8f, 8.92f);
        pathBuilder.close();
        pathBuilder.moveTo(5.6f, 10.25f);
        pathBuilder.curveToRelative(0.0f, 1.38f, 1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilder.curveToRelative(0.53f, 0.0f, 1.01f, -0.16f, 1.42f, -0.44f);
        pathBuilder.lineToRelative(-0.02f, 0.19f);
        pathBuilder.curveToRelative(0.0f, 1.38f, 1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilder.reflectiveCurveToRelative(2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilder.lineToRelative(-0.02f, -0.19f);
        pathBuilder.curveToRelative(0.4f, 0.28f, 0.89f, 0.44f, 1.42f, 0.44f);
        pathBuilder.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilder.curveToRelative(0.0f, -1.0f, -0.59f, -1.85f, -1.43f, -2.25f);
        pathBuilder.curveToRelative(0.84f, -0.4f, 1.43f, -1.25f, 1.43f, -2.25f);
        pathBuilder.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilder.curveToRelative(-0.53f, 0.0f, -1.01f, 0.16f, -1.42f, 0.44f);
        pathBuilder.lineToRelative(0.02f, -0.19f);
        pathBuilder.curveTo(14.5f, 2.12f, 13.38f, 1.0f, 12.0f, 1.0f);
        pathBuilder.reflectiveCurveTo(9.5f, 2.12f, 9.5f, 3.5f);
        pathBuilder.lineToRelative(0.02f, 0.19f);
        pathBuilder.curveToRelative(-0.4f, -0.28f, -0.89f, -0.44f, -1.42f, -0.44f);
        pathBuilder.curveToRelative(-1.38f, 0.0f, -2.5f, 1.12f, -2.5f, 2.5f);
        pathBuilder.curveToRelative(0.0f, 1.0f, 0.59f, 1.85f, 1.43f, 2.25f);
        pathBuilder.curveToRelative(-0.84f, 0.4f, -1.43f, 1.25f, -1.43f, 2.25f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 5.5f);
        pathBuilder.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilder.reflectiveCurveToRelative(-1.12f, 2.5f, -2.5f, 2.5f);
        pathBuilder.reflectiveCurveTo(9.5f, 9.38f, 9.5f, 8.0f);
        pathBuilder.reflectiveCurveToRelative(1.12f, -2.5f, 2.5f, -2.5f);
        pathBuilder.close();
        pathBuilder.moveTo(3.08f, 14.2f);
        pathBuilder.curveTo(3.67f, 18.6f, 7.44f, 22.0f, 12.0f, 22.0f);
        pathBuilder.curveToRelative(0.0f, -4.56f, -3.4f, -8.33f, -7.8f, -8.92f);
        pathBuilder.curveToRelative(-0.64f, -0.09f, -1.21f, 0.48f, -1.12f, 1.12f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _localFlorist = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
