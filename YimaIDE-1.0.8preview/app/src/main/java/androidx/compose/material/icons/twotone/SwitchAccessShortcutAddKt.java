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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_switchAccessShortcutAdd", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SwitchAccessShortcutAdd", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSwitchAccessShortcutAdd", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SwitchAccessShortcutAddKt {
    private static ImageVector _switchAccessShortcutAdd;

    public static final ImageVector getSwitchAccessShortcutAdd(Icons.TwoTone twoTone) {
        ImageVector imageVector = _switchAccessShortcutAdd;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SwitchAccessShortcutAdd", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        SolidColor solidColor = new SolidColor(Color.Companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i = StrokeCap.Companion.getButt-KaPHkGw();
        int i2 = StrokeJoin.Companion.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(24.0f, 14.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(-2.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.horizontalLineToRelative(2.0f);
        pathBuilder.verticalLineTo(14.0f);
        pathBuilder.close();
        pathBuilder.moveTo(7.06f, 8.94f);
        pathBuilder.lineTo(5.0f, 8.0f);
        pathBuilder.lineToRelative(2.06f, -0.94f);
        pathBuilder.lineTo(8.0f, 5.0f);
        pathBuilder.lineToRelative(0.94f, 2.06f);
        pathBuilder.lineTo(11.0f, 8.0f);
        pathBuilder.lineTo(8.94f, 8.94f);
        pathBuilder.lineTo(8.0f, 11.0f);
        pathBuilder.lineTo(7.06f, 8.94f);
        pathBuilder.close();
        pathBuilder.moveTo(8.0f, 21.0f);
        pathBuilder.lineToRelative(0.94f, -2.06f);
        pathBuilder.lineTo(11.0f, 18.0f);
        pathBuilder.lineToRelative(-2.06f, -0.94f);
        pathBuilder.lineTo(8.0f, 15.0f);
        pathBuilder.lineToRelative(-0.94f, 2.06f);
        pathBuilder.lineTo(5.0f, 18.0f);
        pathBuilder.lineToRelative(2.06f, 0.94f);
        pathBuilder.lineTo(8.0f, 21.0f);
        pathBuilder.close();
        pathBuilder.moveTo(4.37f, 12.37f);
        pathBuilder.lineTo(3.0f, 13.0f);
        pathBuilder.lineToRelative(1.37f, 0.63f);
        pathBuilder.lineTo(5.0f, 15.0f);
        pathBuilder.lineToRelative(0.63f, -1.37f);
        pathBuilder.lineTo(7.0f, 13.0f);
        pathBuilder.lineToRelative(-1.37f, -0.63f);
        pathBuilder.lineTo(5.0f, 11.0f);
        pathBuilder.lineTo(4.37f, 12.37f);
        pathBuilder.close();
        pathBuilder.moveTo(12.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, -2.73f, 1.08f, -5.27f, 2.75f, -7.25f);
        pathBuilder.lineTo(12.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(7.0f);
        pathBuilder.verticalLineToRelative(7.0f);
        pathBuilder.lineToRelative(-2.82f, -2.82f);
        pathBuilder.curveTo(14.84f, 7.82f, 14.0f, 9.88f, 14.0f, 12.0f);
        pathBuilder.curveToRelative(0.0f, 3.32f, 2.1f, 6.36f, 5.0f, 7.82f);
        pathBuilder.lineTo(19.0f, 22.0f);
        pathBuilder.curveTo(14.91f, 20.41f, 12.0f, 16.35f, 12.0f, 12.0f);
        pathBuilder.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _switchAccessShortcutAdd = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
