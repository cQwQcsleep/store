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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_squareFoot", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SquareFoot", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSquareFoot", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SquareFootKt {
    private static ImageVector _squareFoot;

    public static final ImageVector getSquareFoot(Icons.TwoTone twoTone) {
        ImageVector imageVector = _squareFoot;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SquareFoot", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(7.0f, 17.0f);
        pathBuilder.lineToRelative(5.76f, 0.0f);
        pathBuilder.lineToRelative(-5.76f, -5.76f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(17.66f, 17.66f);
        pathBuilder2.lineToRelative(-1.06f, 1.06f);
        pathBuilder2.lineToRelative(-0.71f, -0.71f);
        pathBuilder2.lineToRelative(1.06f, -1.06f);
        pathBuilder2.lineToRelative(-1.94f, -1.94f);
        pathBuilder2.lineToRelative(-1.06f, 1.06f);
        pathBuilder2.lineToRelative(-0.71f, -0.71f);
        pathBuilder2.lineToRelative(1.06f, -1.06f);
        pathBuilder2.lineToRelative(-1.94f, -1.94f);
        pathBuilder2.lineToRelative(-1.06f, 1.06f);
        pathBuilder2.lineToRelative(-0.71f, -0.71f);
        pathBuilder2.lineToRelative(1.06f, -1.06f);
        pathBuilder2.lineTo(9.7f, 9.7f);
        pathBuilder2.lineToRelative(-1.06f, 1.06f);
        pathBuilder2.lineToRelative(-0.71f, -0.71f);
        pathBuilder2.lineToRelative(1.06f, -1.06f);
        pathBuilder2.lineTo(7.05f, 7.05f);
        pathBuilder2.lineTo(5.99f, 8.11f);
        pathBuilder2.lineTo(5.28f, 7.4f);
        pathBuilder2.lineToRelative(1.06f, -1.06f);
        pathBuilder2.lineTo(4.0f, 4.0f);
        pathBuilder2.verticalLineToRelative(14.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(14.0f);
        pathBuilder2.lineTo(17.66f, 17.66f);
        pathBuilder2.close();
        pathBuilder2.moveTo(7.0f, 17.0f);
        pathBuilder2.verticalLineToRelative(-5.76f);
        pathBuilder2.lineTo(12.76f, 17.0f);
        pathBuilder2.horizontalLineTo(7.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _squareFoot = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
