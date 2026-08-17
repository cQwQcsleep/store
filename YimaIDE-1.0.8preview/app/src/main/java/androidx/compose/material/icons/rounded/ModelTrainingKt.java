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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_modelTraining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ModelTraining", "Landroidx/compose/material/icons/Icons$Rounded;", "getModelTraining", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ModelTrainingKt {
    private static ImageVector _modelTraining;

    public static final ImageVector getModelTraining(Icons.Rounded rounded) {
        ImageVector imageVector = _modelTraining;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.ModelTraining", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(15.5f, 13.5f);
        pathBuilder.curveToRelative(0.0f, 2.0f, -2.5f, 3.5f, -2.5f, 5.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.curveToRelative(0.0f, -1.5f, -2.5f, -3.0f, -2.5f, -5.0f);
        pathBuilder.curveToRelative(0.0f, -1.93f, 1.57f, -3.5f, 3.5f, -3.5f);
        pathBuilder.horizontalLineToRelative(0.0f);
        pathBuilder.curveTo(13.93f, 10.0f, 15.5f, 11.57f, 15.5f, 13.5f);
        pathBuilder.close();
        pathBuilder.moveTo(13.0f, 19.5f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineTo(20.0f);
        pathBuilder.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(0.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder.verticalLineTo(19.5f);
        pathBuilder.close();
        pathBuilder.moveTo(19.0f, 13.0f);
        pathBuilder.curveToRelative(0.0f, 1.39f, -0.41f, 2.69f, -1.12f, 3.78f);
        pathBuilder.curveToRelative(-0.25f, 0.39f, -0.19f, 0.91f, 0.14f, 1.24f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveToRelative(0.44f, 0.44f, 1.2f, 0.38f, 1.54f, -0.15f);
        pathBuilder.curveTo(20.47f, 16.47f, 21.0f, 14.8f, 21.0f, 13.0f);
        pathBuilder.curveToRelative(0.0f, -2.36f, -0.91f, -4.51f, -2.4f, -6.12f);
        pathBuilder.curveToRelative(-0.39f, -0.42f, -1.05f, -0.43f, -1.45f, -0.03f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveToRelative(-0.38f, 0.38f, -0.38f, 0.99f, -0.02f, 1.39f);
        pathBuilder.curveTo(18.29f, 9.49f, 19.0f, 11.16f, 19.0f, 13.0f);
        pathBuilder.close();
        pathBuilder.moveTo(15.65f, 4.65f);
        pathBuilder.lineToRelative(-2.79f, -2.79f);
        pathBuilder.curveTo(12.54f, 1.54f, 12.0f, 1.76f, 12.0f, 2.21f);
        pathBuilder.verticalLineTo(4.0f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveToRelative(-4.97f, 0.0f, -9.0f, 4.03f, -9.0f, 9.0f);
        pathBuilder.curveToRelative(0.0f, 1.8f, 0.53f, 3.47f, 1.44f, 4.88f);
        pathBuilder.curveToRelative(0.34f, 0.53f, 1.1f, 0.59f, 1.54f, 0.15f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.curveToRelative(0.33f, -0.33f, 0.39f, -0.84f, 0.14f, -1.23f);
        pathBuilder.curveTo(4.73f, 14.65f, 4.48f, 11.7f, 6.25f, 8.8f);
        pathBuilder.curveTo(7.45f, 6.85f, 9.71f, 5.81f, 12.0f, 6.0f);
        pathBuilder.lineToRelative(0.0f, 0.0f);
        pathBuilder.verticalLineToRelative(1.79f);
        pathBuilder.curveToRelative(0.0f, 0.45f, 0.54f, 0.67f, 0.85f, 0.35f);
        pathBuilder.lineToRelative(2.79f, -2.79f);
        pathBuilder.curveTo(15.84f, 5.16f, 15.84f, 4.84f, 15.65f, 4.65f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _modelTraining = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
