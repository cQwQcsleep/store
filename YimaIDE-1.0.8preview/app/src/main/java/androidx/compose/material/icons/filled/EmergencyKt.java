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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_emergency", "Landroidx/compose/ui/graphics/vector/ImageVector;", "Emergency", "Landroidx/compose/material/icons/Icons$Filled;", "getEmergency", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class EmergencyKt {
    private static ImageVector _emergency;

    public static final ImageVector getEmergency(Icons.Filled filled) {
        ImageVector imageVector = _emergency;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.Emergency", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(20.79f, 9.23f);
        pathBuilder.lineToRelative(-2.0f, -3.46f);
        pathBuilder.lineToRelative(-4.79f, 2.77f);
        pathBuilder.lineToRelative(0.0f, -5.54f);
        pathBuilder.lineToRelative(-4.0f, 0.0f);
        pathBuilder.lineToRelative(0.0f, 5.54f);
        pathBuilder.lineToRelative(-4.79f, -2.77f);
        pathBuilder.lineToRelative(-2.0f, 3.46f);
        pathBuilder.lineToRelative(4.79f, 2.77f);
        pathBuilder.lineToRelative(-4.79f, 2.77f);
        pathBuilder.lineToRelative(2.0f, 3.46f);
        pathBuilder.lineToRelative(4.79f, -2.77f);
        pathBuilder.lineToRelative(0.0f, 5.54f);
        pathBuilder.lineToRelative(4.0f, 0.0f);
        pathBuilder.lineToRelative(0.0f, -5.54f);
        pathBuilder.lineToRelative(4.79f, 2.77f);
        pathBuilder.lineToRelative(2.0f, -3.46f);
        pathBuilder.lineToRelative(-4.79f, -2.77f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _emergency = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
