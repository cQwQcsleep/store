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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_motionPhotosPaused", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MotionPhotosPaused", "Landroidx/compose/material/icons/Icons$Outlined;", "getMotionPhotosPaused", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MotionPhotosPausedKt {
    private static ImageVector _motionPhotosPaused;

    public static final ImageVector getMotionPhotosPaused(Icons.Outlined outlined) {
        ImageVector imageVector = _motionPhotosPaused;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.MotionPhotosPaused", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(22.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 5.52f, -4.48f, 10.0f, -10.0f, 10.0f);
        pathBuilder.reflectiveCurveTo(2.0f, 17.52f, 2.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -1.19f, 0.22f, -2.32f, 0.6f, -3.38f);
        pathBuilder.lineTo(4.48f, 9.3f);
        pathBuilder.curveTo(4.17f, 10.14f, 4.0f, 11.05f, 4.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 4.41f, 3.59f, 8.0f, 8.0f, 8.0f);
        pathBuilder.reflectiveCurveToRelative(8.0f, -3.59f, 8.0f, -8.0f);
        pathBuilder.reflectiveCurveToRelative(-3.59f, -8.0f, -8.0f, -8.0f);
        pathBuilder.curveToRelative(-0.95f, 0.0f, -1.85f, 0.17f, -2.69f, 0.48f);
        pathBuilder.lineTo(8.63f, 2.59f);
        pathBuilder.curveTo(9.69f, 2.22f, 10.82f, 2.0f, 12.0f, 2.0f);
        pathBuilder.curveTo(17.52f, 2.0f, 22.0f, 6.48f, 22.0f, 12.0f);
        pathBuilder.close();
        pathBuilder.moveTo(5.5f, 4.0f);
        pathBuilder.curveTo(4.67f, 4.0f, 4.0f, 4.67f, 4.0f, 5.5f);
        pathBuilder.reflectiveCurveTo(4.67f, 7.0f, 5.5f, 7.0f);
        pathBuilder.reflectiveCurveTo(7.0f, 6.33f, 7.0f, 5.5f);
        pathBuilder.reflectiveCurveTo(6.33f, 4.0f, 5.5f, 4.0f);
        pathBuilder.close();
        pathBuilder.moveTo(11.0f, 16.0f);
        pathBuilder.verticalLineTo(8.0f);
        pathBuilder.horizontalLineTo(9.0f);
        pathBuilder.verticalLineToRelative(8.0f);
        pathBuilder.horizontalLineTo(11.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.0f, 16.0f);
        pathBuilder.verticalLineTo(8.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineToRelative(8.0f);
        pathBuilder.horizontalLineTo(15.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _motionPhotosPaused = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
