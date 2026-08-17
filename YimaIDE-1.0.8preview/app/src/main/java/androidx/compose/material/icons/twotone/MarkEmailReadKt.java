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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_markEmailRead", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MarkEmailRead", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMarkEmailRead", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MarkEmailReadKt {
    private static ImageVector _markEmailRead;

    public static final ImageVector getMarkEmailRead(Icons.TwoTone twoTone) {
        ImageVector imageVector = _markEmailRead;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.MarkEmailRead", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(20.0f, 8.0f);
        pathBuilder.verticalLineToRelative(4.08f);
        pathBuilder.curveTo(19.67f, 12.03f, 19.34f, 12.0f, 19.0f, 12.0f);
        pathBuilder.curveToRelative(-3.53f, 0.0f, -6.43f, 2.61f, -6.92f, 6.0f);
        pathBuilder.horizontalLineTo(4.0f);
        pathBuilder.verticalLineTo(8.0f);
        pathBuilder.lineToRelative(8.0f, 5.0f);
        pathBuilder.lineTo(20.0f, 8.0f);
        pathBuilder.close();
        pathBuilder.moveTo(20.0f, 6.0f);
        pathBuilder.horizontalLineTo(4.0f);
        pathBuilder.lineToRelative(8.0f, 5.0f);
        pathBuilder.lineTo(20.0f, 6.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(12.08f, 18.0f);
        pathBuilder2.horizontalLineTo(4.0f);
        pathBuilder2.verticalLineTo(8.0f);
        pathBuilder2.lineToRelative(8.0f, 5.0f);
        pathBuilder2.lineToRelative(8.0f, -5.0f);
        pathBuilder2.verticalLineToRelative(4.08f);
        pathBuilder2.curveToRelative(0.71f, 0.1f, 1.38f, 0.31f, 2.0f, 0.6f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.horizontalLineTo(4.0f);
        pathBuilder2.curveTo(2.9f, 4.0f, 2.01f, 4.9f, 2.01f, 6.0f);
        pathBuilder2.lineTo(2.0f, 18.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(8.08f);
        pathBuilder2.curveTo(12.03f, 19.67f, 12.0f, 19.34f, 12.0f, 19.0f);
        pathBuilder2.reflectiveCurveTo(12.03f, 18.33f, 12.08f, 18.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.0f, 6.0f);
        pathBuilder2.lineToRelative(-8.0f, 5.0f);
        pathBuilder2.lineTo(4.0f, 6.0f);
        pathBuilder2.horizontalLineTo(20.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(17.34f, 22.0f);
        pathBuilder2.lineToRelative(-3.54f, -3.54f);
        pathBuilder2.lineToRelative(1.41f, -1.41f);
        pathBuilder2.lineToRelative(2.12f, 2.12f);
        pathBuilder2.lineToRelative(4.24f, -4.24f);
        pathBuilder2.lineTo(23.0f, 16.34f);
        pathBuilder2.lineTo(17.34f, 22.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _markEmailRead = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
