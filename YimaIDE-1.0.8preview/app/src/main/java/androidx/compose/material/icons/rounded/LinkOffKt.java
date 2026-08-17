package androidx.compose.material.icons.rounded;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_linkOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "LinkOff", "Landroidx/compose/material/icons/Icons$Rounded;", "getLinkOff", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class LinkOffKt {
    private static ImageVector _linkOff;

    public static final ImageVector getLinkOff(Icons.Rounded rounded) {
        ImageVector imageVector = _linkOff;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.LinkOff", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(14.0f, 9.0f);
        pathBuilder.horizontalLineToRelative(2.87f);
        pathBuilder.curveToRelative(1.46f, 0.0f, 2.8f, 0.98f, 3.08f, 2.42f);
        pathBuilder.curveToRelative(0.31f, 1.64f, -0.74f, 3.11f, -2.22f, 3.48f);
        pathBuilder.lineToRelative(1.53f, 1.53f);
        pathBuilder.curveToRelative(1.77f, -0.91f, 2.95f, -2.82f, 2.7f, -5.01f);
        pathBuilder.curveTo(21.68f, 8.86f, 19.37f, 7.0f, 16.79f, 7.0f);
        pathBuilder.horizontalLineTo(14.0f);
        pathBuilder.curveToRelative(-0.55f, 0.0f, -1.0f, 0.45f, -1.0f, 1.0f);
        pathBuilder.curveTo(13.0f, 8.55f, 13.45f, 9.0f, 14.0f, 9.0f);
        pathBuilder.close();
        pathBuilder.moveTo(3.51f, 3.51f);
        pathBuilder.curveToRelative(-0.39f, -0.39f, -1.02f, -0.39f, -1.41f, 0.0f);
        pathBuilder.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilder.lineToRelative(2.64f, 2.64f);
        pathBuilder.curveToRelative(-1.77f, 0.91f, -2.95f, 2.82f, -2.7f, 5.01f);
        pathBuilder.curveTo(2.32f, 15.14f, 4.63f, 17.0f, 7.21f, 17.0f);
        pathBuilder.horizontalLineTo(10.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilder.horizontalLineTo(7.13f);
        pathBuilder.curveToRelative(-1.46f, 0.0f, -2.8f, -0.98f, -3.08f, -2.42f);
        pathBuilder.curveTo(3.74f, 10.94f, 4.8f, 9.47f, 6.27f, 9.1f);
        pathBuilder.lineToRelative(2.12f, 2.12f);
        pathBuilder.curveTo(8.16f, 11.41f, 8.0f, 11.68f, 8.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder.horizontalLineToRelative(1.17f);
        pathBuilder.lineToRelative(8.9f, 8.9f);
        pathBuilder.curveToRelative(0.39f, 0.39f, 1.02f, 0.39f, 1.41f, 0.0f);
        pathBuilder.curveToRelative(0.39f, -0.39f, 0.39f, -1.02f, 0.0f, -1.41f);
        pathBuilder.lineTo(3.51f, 3.51f);
        pathBuilder.close();
        pathBuilder.moveTo(14.0f, 11.0f);
        pathBuilder.lineToRelative(1.71f, 1.71f);
        pathBuilder.curveTo(15.89f, 12.53f, 16.0f, 12.28f, 16.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -0.55f, -0.45f, -1.0f, -1.0f, -1.0f);
        pathBuilder.horizontalLineTo(14.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _linkOff = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
