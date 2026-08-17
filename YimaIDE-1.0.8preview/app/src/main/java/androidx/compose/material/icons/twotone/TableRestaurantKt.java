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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_tableRestaurant", "Landroidx/compose/ui/graphics/vector/ImageVector;", "TableRestaurant", "Landroidx/compose/material/icons/Icons$TwoTone;", "getTableRestaurant", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class TableRestaurantKt {
    private static ImageVector _tableRestaurant;

    public static final ImageVector getTableRestaurant(Icons.TwoTone twoTone) {
        ImageVector imageVector = _tableRestaurant;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.TableRestaurant", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(5.18f, 6.0f);
        pathBuilder.lineToRelative(-0.85f, 3.0f);
        pathBuilder.lineToRelative(15.34f, 0.0f);
        pathBuilder.lineToRelative(-0.85f, -3.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(21.96f, 9.73f);
        pathBuilder2.lineToRelative(-1.43f, -5.0f);
        pathBuilder2.curveTo(20.41f, 4.3f, 20.02f, 4.0f, 19.57f, 4.0f);
        pathBuilder2.horizontalLineTo(4.43f);
        pathBuilder2.curveTo(3.98f, 4.0f, 3.59f, 4.3f, 3.47f, 4.73f);
        pathBuilder2.lineToRelative(-1.43f, 5.0f);
        pathBuilder2.curveTo(1.86f, 10.36f, 2.34f, 11.0f, 3.0f, 11.0f);
        pathBuilder2.horizontalLineToRelative(2.2f);
        pathBuilder2.lineTo(4.0f, 20.0f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.lineToRelative(0.67f, -5.0f);
        pathBuilder2.horizontalLineToRelative(10.67f);
        pathBuilder2.lineTo(18.0f, 20.0f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.lineToRelative(-1.2f, -9.0f);
        pathBuilder2.horizontalLineTo(21.0f);
        pathBuilder2.curveTo(21.66f, 11.0f, 22.14f, 10.36f, 21.96f, 9.73f);
        pathBuilder2.close();
        pathBuilder2.moveTo(6.93f, 13.0f);
        pathBuilder2.lineToRelative(0.27f, -2.0f);
        pathBuilder2.horizontalLineToRelative(9.6f);
        pathBuilder2.lineToRelative(0.27f, 2.0f);
        pathBuilder2.horizontalLineTo(6.93f);
        pathBuilder2.close();
        pathBuilder2.moveTo(4.33f, 9.0f);
        pathBuilder2.lineToRelative(0.86f, -3.0f);
        pathBuilder2.horizontalLineToRelative(13.63f);
        pathBuilder2.lineToRelative(0.86f, 3.0f);
        pathBuilder2.horizontalLineTo(4.33f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _tableRestaurant = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
