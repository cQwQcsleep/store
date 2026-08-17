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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fitnessCenter", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FitnessCenter", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFitnessCenter", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FitnessCenterKt {
    private static ImageVector _fitnessCenter;

    public static final ImageVector getFitnessCenter(Icons.TwoTone twoTone) {
        ImageVector imageVector = _fitnessCenter;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FitnessCenter", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(20.57f, 14.86f);
        pathBuilder.lineTo(22.0f, 13.43f);
        pathBuilder.lineTo(20.57f, 12.0f);
        pathBuilder.lineTo(17.0f, 15.57f);
        pathBuilder.lineTo(8.43f, 7.0f);
        pathBuilder.lineTo(12.0f, 3.43f);
        pathBuilder.lineTo(10.57f, 2.0f);
        pathBuilder.lineTo(9.14f, 3.43f);
        pathBuilder.lineTo(7.71f, 2.0f);
        pathBuilder.lineTo(5.57f, 4.14f);
        pathBuilder.lineTo(4.14f, 2.71f);
        pathBuilder.lineTo(2.71f, 4.14f);
        pathBuilder.lineToRelative(1.43f, 1.43f);
        pathBuilder.lineTo(2.0f, 7.71f);
        pathBuilder.lineToRelative(1.43f, 1.43f);
        pathBuilder.lineTo(2.0f, 10.57f);
        pathBuilder.lineTo(3.43f, 12.0f);
        pathBuilder.lineTo(7.0f, 8.43f);
        pathBuilder.lineTo(15.57f, 17.0f);
        pathBuilder.lineTo(12.0f, 20.57f);
        pathBuilder.lineTo(13.43f, 22.0f);
        pathBuilder.lineToRelative(1.43f, -1.43f);
        pathBuilder.lineTo(16.29f, 22.0f);
        pathBuilder.lineToRelative(2.14f, -2.14f);
        pathBuilder.lineToRelative(1.43f, 1.43f);
        pathBuilder.lineToRelative(1.43f, -1.43f);
        pathBuilder.lineToRelative(-1.43f, -1.43f);
        pathBuilder.lineTo(22.0f, 16.29f);
        pathBuilder.lineToRelative(-1.43f, -1.43f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _fitnessCenter = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
