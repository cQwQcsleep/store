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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tableRestaurant", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TableRestaurant", "Landroidx/compose/material/icons/Icons$Outlined;", "getTableRestaurant", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TableRestaurantKt {
    private static ImageVector _tableRestaurant;

    public static final ImageVector getTableRestaurant(Icons.Outlined outlined) {
        ImageVector imageVector = _tableRestaurant;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.TableRestaurant", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(21.96f, 9.73f);
        pathBuilder.lineToRelative(-1.43f, -5.0f);
        pathBuilder.curveTo(20.41f, 4.3f, 20.02f, 4.0f, 19.57f, 4.0f);
        pathBuilder.horizontalLineTo(4.43f);
        pathBuilder.curveTo(3.98f, 4.0f, 3.59f, 4.3f, 3.47f, 4.73f);
        pathBuilder.lineToRelative(-1.43f, 5.0f);
        pathBuilder.curveTo(1.86f, 10.36f, 2.34f, 11.0f, 3.0f, 11.0f);
        pathBuilder.horizontalLineToRelative(2.2f);
        pathBuilder.lineTo(4.0f, 20.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.lineToRelative(0.67f, -5.0f);
        pathBuilder.horizontalLineToRelative(10.67f);
        pathBuilder.lineTo(18.0f, 20.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.lineToRelative(-1.2f, -9.0f);
        pathBuilder.horizontalLineTo(21.0f);
        pathBuilder.curveTo(21.66f, 11.0f, 22.14f, 10.36f, 21.96f, 9.73f);
        pathBuilder.close();
        pathBuilder.moveTo(6.93f, 13.0f);
        pathBuilder.lineToRelative(0.27f, -2.0f);
        pathBuilder.horizontalLineToRelative(9.6f);
        pathBuilder.lineToRelative(0.27f, 2.0f);
        pathBuilder.horizontalLineTo(6.93f);
        pathBuilder.close();
        pathBuilder.moveTo(4.33f, 9.0f);
        pathBuilder.lineToRelative(0.86f, -3.0f);
        pathBuilder.horizontalLineToRelative(13.63f);
        pathBuilder.lineToRelative(0.86f, 3.0f);
        pathBuilder.horizontalLineTo(4.33f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _tableRestaurant = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
