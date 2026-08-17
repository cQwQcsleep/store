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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_settingsOverscan", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SettingsOverscan", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSettingsOverscan", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SettingsOverscanKt {
    private static ImageVector _settingsOverscan;

    public static final ImageVector getSettingsOverscan(Icons.TwoTone twoTone) {
        ImageVector imageVector = _settingsOverscan;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SettingsOverscan", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(3.0f, 19.01f);
        pathBuilder.horizontalLineToRelative(18.0f);
        pathBuilder.lineTo(21.0f, 4.99f);
        pathBuilder.lineTo(3.0f, 4.99f);
        pathBuilder.verticalLineToRelative(14.02f);
        pathBuilder.close();
        pathBuilder.moveTo(18.0f, 10.0f);
        pathBuilder.lineToRelative(2.5f, 2.01f);
        pathBuilder.lineTo(18.0f, 14.0f);
        pathBuilder.verticalLineToRelative(-4.0f);
        pathBuilder.close();
        pathBuilder.moveTo(12.01f, 5.5f);
        pathBuilder.lineTo(14.0f, 8.0f);
        pathBuilder.horizontalLineToRelative(-4.0f);
        pathBuilder.lineToRelative(2.01f, -2.5f);
        pathBuilder.close();
        pathBuilder.moveTo(14.0f, 16.0f);
        pathBuilder.lineToRelative(-1.99f, 2.5f);
        pathBuilder.lineTo(10.0f, 16.0f);
        pathBuilder.horizontalLineToRelative(4.0f);
        pathBuilder.close();
        pathBuilder.moveTo(6.0f, 10.0f);
        pathBuilder.verticalLineToRelative(4.0f);
        pathBuilder.lineToRelative(-2.5f, -1.99f);
        pathBuilder.lineTo(6.0f, 10.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(14.0f, 16.0f);
        pathBuilder2.horizontalLineToRelative(-4.0f);
        pathBuilder2.lineToRelative(2.01f, 2.5f);
        pathBuilder2.close();
        pathBuilder2.moveTo(18.0f, 10.0f);
        pathBuilder2.verticalLineToRelative(4.0f);
        pathBuilder2.lineToRelative(2.5f, -1.99f);
        pathBuilder2.close();
        pathBuilder2.moveTo(21.0f, 3.0f);
        pathBuilder2.lineTo(3.0f, 3.0f);
        pathBuilder2.curveToRelative(-1.1f, 0.0f, -2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder2.verticalLineToRelative(14.0f);
        pathBuilder2.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(18.0f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder2.lineTo(23.0f, 5.0f);
        pathBuilder2.curveToRelative(0.0f, -1.1f, -0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(21.0f, 19.01f);
        pathBuilder2.lineTo(3.0f, 19.01f);
        pathBuilder2.lineTo(3.0f, 4.99f);
        pathBuilder2.horizontalLineToRelative(18.0f);
        pathBuilder2.verticalLineToRelative(14.02f);
        pathBuilder2.close();
        pathBuilder2.moveTo(6.0f, 10.0f);
        pathBuilder2.lineToRelative(-2.5f, 2.01f);
        pathBuilder2.lineTo(6.0f, 14.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.01f, 5.5f);
        pathBuilder2.lineTo(10.0f, 8.0f);
        pathBuilder2.horizontalLineToRelative(4.0f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _settingsOverscan = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
