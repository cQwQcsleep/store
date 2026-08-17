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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_localPolice", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LocalPolice", "Landroidx/compose/material/icons/Icons$TwoTone;", "getLocalPolice", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LocalPoliceKt {
    private static ImageVector _localPolice;

    public static final ImageVector getLocalPolice(Icons.TwoTone twoTone) {
        ImageVector imageVector = _localPolice;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.LocalPolice", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 3.19f);
        pathBuilder.lineTo(5.0f, 6.3f);
        pathBuilder.verticalLineTo(11.0f);
        pathBuilder.curveToRelative(0.0f, 4.52f, 2.98f, 8.69f, 7.0f, 9.93f);
        pathBuilder.curveToRelative(4.02f, -1.23f, 7.0f, -5.41f, 7.0f, -9.93f);
        pathBuilder.verticalLineTo(6.3f);
        pathBuilder.lineTo(12.0f, 3.19f);
        pathBuilder.close();
        pathBuilder.moveTo(14.5f, 12.59f);
        pathBuilder.lineToRelative(0.9f, 3.88f);
        pathBuilder.lineTo(12.0f, 14.42f);
        pathBuilder.lineToRelative(-3.4f, 2.05f);
        pathBuilder.lineToRelative(0.9f, -3.87f);
        pathBuilder.lineToRelative(-3.0f, -2.59f);
        pathBuilder.lineToRelative(3.96f, -0.34f);
        pathBuilder.lineTo(12.0f, 6.02f);
        pathBuilder.lineToRelative(1.54f, 3.64f);
        pathBuilder.lineTo(17.5f, 10.0f);
        pathBuilder.lineTo(14.5f, 12.59f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(14.5f, 12.59f);
        pathBuilder2.lineToRelative(0.9f, 3.88f);
        pathBuilder2.lineTo(12.0f, 14.42f);
        pathBuilder2.lineToRelative(-3.4f, 2.05f);
        pathBuilder2.lineToRelative(0.9f, -3.87f);
        pathBuilder2.lineToRelative(-3.0f, -2.59f);
        pathBuilder2.lineToRelative(3.96f, -0.34f);
        pathBuilder2.lineTo(12.0f, 6.02f);
        pathBuilder2.lineToRelative(1.54f, 3.64f);
        pathBuilder2.lineTo(17.5f, 10.0f);
        pathBuilder2.lineTo(14.5f, 12.59f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.0f, 3.19f);
        pathBuilder2.lineToRelative(7.0f, 3.11f);
        pathBuilder2.verticalLineTo(11.0f);
        pathBuilder2.curveToRelative(0.0f, 4.52f, -2.98f, 8.69f, -7.0f, 9.93f);
        pathBuilder2.curveTo(7.98f, 19.69f, 5.0f, 15.52f, 5.0f, 11.0f);
        pathBuilder2.verticalLineTo(6.3f);
        pathBuilder2.lineTo(12.0f, 3.19f);
        pathBuilder2.moveTo(12.0f, 1.0f);
        pathBuilder2.lineTo(3.0f, 5.0f);
        pathBuilder2.verticalLineToRelative(6.0f);
        pathBuilder2.curveToRelative(0.0f, 5.55f, 3.84f, 10.74f, 9.0f, 12.0f);
        pathBuilder2.curveToRelative(5.16f, -1.26f, 9.0f, -6.45f, 9.0f, -12.0f);
        pathBuilder2.verticalLineTo(5.0f);
        pathBuilder2.lineTo(12.0f, 1.0f);
        pathBuilder2.lineTo(12.0f, 1.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _localPolice = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
