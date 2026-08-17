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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_gppBad", "Landroidx/compose/ui/graphics/vector/ImageVector;", "GppBad", "Landroidx/compose/material/icons/Icons$TwoTone;", "getGppBad", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class GppBadKt {
    private static ImageVector _gppBad;

    public static final ImageVector getGppBad(Icons.TwoTone twoTone) {
        ImageVector imageVector = _gppBad;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.GppBad", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 2.0f);
        pathBuilder.lineTo(4.0f, 5.0f);
        pathBuilder.verticalLineToRelative(6.09f);
        pathBuilder.curveToRelative(0.0f, 5.05f, 3.41f, 9.76f, 8.0f, 10.91f);
        pathBuilder.curveToRelative(4.59f, -1.15f, 8.0f, -5.86f, 8.0f, -10.91f);
        pathBuilder.verticalLineTo(5.0f);
        pathBuilder.lineTo(12.0f, 2.0f);
        pathBuilder.close();
        pathBuilder.moveTo(18.0f, 11.09f);
        pathBuilder.curveToRelative(0.0f, 4.0f, -2.55f, 7.7f, -6.0f, 8.83f);
        pathBuilder.curveToRelative(-3.45f, -1.13f, -6.0f, -4.82f, -6.0f, -8.83f);
        pathBuilder.verticalLineToRelative(-4.7f);
        pathBuilder.lineToRelative(6.0f, -2.25f);
        pathBuilder.lineToRelative(6.0f, 2.25f);
        pathBuilder.verticalLineTo(11.09f);
        pathBuilder.close();
        pathBuilder.moveTo(9.91f, 8.5f);
        pathBuilder.lineTo(8.5f, 9.91f);
        pathBuilder.lineTo(10.59f, 12.0f);
        pathBuilder.lineTo(8.5f, 14.09f);
        pathBuilder.lineToRelative(1.41f, 1.41f);
        pathBuilder.lineTo(12.0f, 13.42f);
        pathBuilder.lineToRelative(2.09f, 2.08f);
        pathBuilder.lineToRelative(1.41f, -1.41f);
        pathBuilder.lineTo(13.42f, 12.0f);
        pathBuilder.lineToRelative(2.08f, -2.09f);
        pathBuilder.lineTo(14.09f, 8.5f);
        pathBuilder.lineTo(12.0f, 10.59f);
        pathBuilder.lineTo(9.91f, 8.5f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(6.0f, 6.39f);
        pathBuilder2.verticalLineToRelative(4.7f);
        pathBuilder2.curveToRelative(0.0f, 4.0f, 2.55f, 7.7f, 6.0f, 8.83f);
        pathBuilder2.curveToRelative(3.45f, -1.13f, 6.0f, -4.82f, 6.0f, -8.83f);
        pathBuilder2.verticalLineToRelative(-4.7f);
        pathBuilder2.lineToRelative(-6.0f, -2.25f);
        pathBuilder2.lineTo(6.0f, 6.39f);
        pathBuilder2.close();
        pathBuilder2.moveTo(15.5f, 9.91f);
        pathBuilder2.lineTo(13.42f, 12.0f);
        pathBuilder2.lineToRelative(2.08f, 2.09f);
        pathBuilder2.lineToRelative(-1.41f, 1.41f);
        pathBuilder2.lineTo(12.0f, 13.42f);
        pathBuilder2.lineTo(9.91f, 15.5f);
        pathBuilder2.lineTo(8.5f, 14.09f);
        pathBuilder2.lineTo(10.59f, 12.0f);
        pathBuilder2.lineTo(8.5f, 9.91f);
        pathBuilder2.lineTo(9.91f, 8.5f);
        pathBuilder2.lineTo(12.0f, 10.59f);
        pathBuilder2.lineToRelative(2.09f, -2.09f);
        pathBuilder2.lineTo(15.5f, 9.91f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, (Brush) null, 0.3f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _gppBad = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
