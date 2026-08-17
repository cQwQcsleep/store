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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_dinnerDining", "Landroidx/compose/ui/graphics/vector/ImageVector;", "DinnerDining", "Landroidx/compose/material/icons/Icons$Sharp;", "getDinnerDining", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class DinnerDiningKt {
    private static ImageVector _dinnerDining;

    public static final ImageVector getDinnerDining(Icons.Sharp sharp) {
        ImageVector imageVector = _dinnerDining;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.DinnerDining", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(2.0f, 19.0f);
        pathBuilder.lineToRelative(2.0f, 2.0f);
        pathBuilder.lineToRelative(16.0f, 0.0f);
        pathBuilder.lineToRelative(2.0f, -2.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(3.0f, 18.0f);
        pathBuilder2.lineToRelative(16.97f, 0.0f);
        pathBuilder2.curveToRelative(0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f);
        pathBuilder2.curveToRelative(0.29f, -3.26f, -2.28f, -6.0f, -5.48f, -6.0f);
        pathBuilder2.curveToRelative(-2.35f, 0.0f, -4.35f, 1.48f, -5.14f, 3.55f);
        pathBuilder2.curveTo(8.94f, 15.32f, 8.48f, 15.17f, 8.0f, 15.08f);
        pathBuilder2.verticalLineTo(9.0f);
        pathBuilder2.horizontalLineToRelative(4.0f);
        pathBuilder2.verticalLineTo(6.75f);
        pathBuilder2.horizontalLineToRelative(9.0f);
        pathBuilder2.verticalLineToRelative(-1.5f);
        pathBuilder2.horizontalLineToRelative(-9.0f);
        pathBuilder2.verticalLineTo(3.0f);
        pathBuilder2.horizontalLineTo(3.0f);
        pathBuilder2.verticalLineToRelative(1.5f);
        pathBuilder2.horizontalLineToRelative(1.0f);
        pathBuilder2.verticalLineToRelative(0.75f);
        pathBuilder2.horizontalLineTo(3.0f);
        pathBuilder2.verticalLineToRelative(1.5f);
        pathBuilder2.horizontalLineToRelative(1.0f);
        pathBuilder2.verticalLineTo(7.5f);
        pathBuilder2.horizontalLineTo(3.0f);
        pathBuilder2.verticalLineTo(9.0f);
        pathBuilder2.horizontalLineToRelative(1.0f);
        pathBuilder2.verticalLineToRelative(7.39f);
        pathBuilder2.curveTo(3.56f, 16.85f, 3.22f, 17.39f, 3.0f, 18.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(8.0f, 7.5f);
        pathBuilder2.verticalLineTo(6.75f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.verticalLineTo(7.5f);
        pathBuilder2.horizontalLineTo(8.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(10.0f, 5.25f);
        pathBuilder2.horizontalLineTo(8.0f);
        pathBuilder2.verticalLineTo(4.5f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.verticalLineTo(5.25f);
        pathBuilder2.close();
        pathBuilder2.moveTo(5.5f, 4.5f);
        pathBuilder2.horizontalLineToRelative(1.0f);
        pathBuilder2.verticalLineToRelative(0.75f);
        pathBuilder2.horizontalLineToRelative(-1.0f);
        pathBuilder2.verticalLineTo(4.5f);
        pathBuilder2.close();
        pathBuilder2.moveTo(5.5f, 6.75f);
        pathBuilder2.horizontalLineToRelative(1.0f);
        pathBuilder2.verticalLineTo(7.5f);
        pathBuilder2.horizontalLineToRelative(-1.0f);
        pathBuilder2.verticalLineTo(6.75f);
        pathBuilder2.close();
        pathBuilder2.moveTo(6.5f, 9.0f);
        pathBuilder2.verticalLineToRelative(6.06f);
        pathBuilder2.curveToRelative(-0.35f, 0.06f, -0.68f, 0.17f, -1.0f, 0.3f);
        pathBuilder2.verticalLineTo(9.0f);
        pathBuilder2.horizontalLineTo(6.5f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _dinnerDining = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
