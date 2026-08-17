package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_fastfood", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Fastfood", "Landroidx/compose/material/icons/Icons$Filled;", "getFastfood", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FastfoodKt {
    private static ImageVector _fastfood;

    public static final ImageVector getFastfood(Icons.Filled filled) {
        ImageVector imageVector = _fastfood;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Fastfood", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(18.06f, 22.99f);
        pathBuilder.horizontalLineToRelative(1.66f);
        pathBuilder.curveToRelative(0.84f, 0.0f, 1.53f, -0.64f, 1.63f, -1.46f);
        pathBuilder.lineTo(23.0f, 5.05f);
        pathBuilder.horizontalLineToRelative(-5.0f);
        pathBuilder.lineTo(18.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(-1.97f);
        pathBuilder.verticalLineToRelative(4.05f);
        pathBuilder.horizontalLineToRelative(-4.97f);
        pathBuilder.lineToRelative(0.3f, 2.34f);
        pathBuilder.curveToRelative(1.71f, 0.47f, 3.31f, 1.32f, 4.27f, 2.26f);
        pathBuilder.curveToRelative(1.44f, 1.42f, 2.43f, 2.89f, 2.43f, 5.29f);
        pathBuilder.verticalLineToRelative(8.05f);
        pathBuilder.close();
        pathBuilder.moveTo(1.0f, 21.99f);
        pathBuilder.lineTo(1.0f, 21.0f);
        pathBuilder.horizontalLineToRelative(15.03f);
        pathBuilder.verticalLineToRelative(0.99f);
        pathBuilder.curveToRelative(0.0f, 0.55f, -0.45f, 1.0f, -1.01f, 1.0f);
        pathBuilder.lineTo(2.01f, 22.99f);
        pathBuilder.curveToRelative(-0.56f, 0.0f, -1.01f, -0.45f, -1.01f, -1.0f);
        pathBuilder.close();
        pathBuilder.moveTo(16.03f, 14.99f);
        pathBuilder.curveToRelative(0.0f, -8.0f, -15.03f, -8.0f, -15.03f, 0.0f);
        pathBuilder.horizontalLineToRelative(15.03f);
        pathBuilder.close();
        pathBuilder.moveTo(1.02f, 17.0f);
        pathBuilder.horizontalLineToRelative(15.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(-15.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _fastfood = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
