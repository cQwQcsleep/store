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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_macroOff", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MacroOff", "Landroidx/compose/material/icons/Icons$Sharp;", "getMacroOff", "(Landroidx/compose/material/icons/Icons$Sharp;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MacroOffKt {
    private static ImageVector _macroOff;

    public static final ImageVector getMacroOff(Icons.Sharp sharp) {
        ImageVector imageVector = _macroOff;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Sharp.MacroOff", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(19.98f, 17.15f);
        pathBuilder.curveTo(20.63f, 15.91f, 21.0f, 14.5f, 21.0f, 13.0f);
        pathBuilder.curveToRelative(-1.5f, 0.0f, -2.91f, 0.37f, -4.15f, 1.02f);
        pathBuilder.lineTo(19.98f, 17.15f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(3.0f, 13.0f);
        pathBuilder2.curveToRelative(0.0f, 4.97f, 4.03f, 9.0f, 9.0f, 9.0f);
        pathBuilder2.curveTo(12.0f, 17.03f, 7.97f, 13.0f, 3.0f, 13.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(12.0f, 5.5f);
        pathBuilder3.curveToRelative(1.38f, 0.0f, 2.5f, 1.12f, 2.5f, 2.5f);
        pathBuilder3.curveToRelative(0.0f, 0.99f, -0.58f, 1.84f, -1.42f, 2.25f);
        pathBuilder3.lineToRelative(2.48f, 2.48f);
        pathBuilder3.curveToRelative(0.11f, 0.02f, 0.23f, 0.03f, 0.35f, 0.03f);
        pathBuilder3.curveToRelative(1.38f, 0.0f, 2.5f, -1.12f, 2.5f, -2.5f);
        pathBuilder3.curveToRelative(0.0f, -1.0f, -0.59f, -1.85f, -1.43f, -2.25f);
        pathBuilder3.curveToRelative(0.84f, -0.4f, 1.43f, -1.25f, 1.43f, -2.25f);
        pathBuilder3.curveToRelative(0.0f, -1.38f, -1.12f, -2.5f, -2.5f, -2.5f);
        pathBuilder3.curveToRelative(-0.53f, 0.0f, -1.01f, 0.16f, -1.42f, 0.44f);
        pathBuilder3.lineTo(14.5f, 3.5f);
        pathBuilder3.curveTo(14.5f, 2.12f, 13.38f, 1.0f, 12.0f, 1.0f);
        pathBuilder3.reflectiveCurveTo(9.5f, 2.12f, 9.5f, 3.5f);
        pathBuilder3.lineToRelative(0.02f, 0.19f);
        pathBuilder3.curveTo(9.12f, 3.41f, 8.63f, 3.25f, 8.1f, 3.25f);
        pathBuilder3.curveToRelative(-0.57f, 0.0f, -1.09f, 0.2f, -1.51f, 0.52f);
        pathBuilder3.lineToRelative(3.16f, 3.16f);
        pathBuilder3.curveTo(10.16f, 6.08f, 11.01f, 5.5f, 12.0f, 5.5f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(2.81f, 2.81f);
        pathBuilder4.lineTo(1.39f, 4.22f);
        pathBuilder4.lineToRelative(4.64f, 4.64f);
        pathBuilder4.curveTo(5.76f, 9.26f, 5.6f, 9.73f, 5.6f, 10.25f);
        pathBuilder4.curveToRelative(0.0f, 1.38f, 1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilder4.curveToRelative(0.52f, 0.0f, 0.99f, -0.16f, 1.4f, -0.43f);
        pathBuilder4.lineToRelative(0.02f, 0.02f);
        pathBuilder4.lineTo(9.5f, 12.5f);
        pathBuilder4.curveToRelative(0.0f, 1.38f, 1.12f, 2.5f, 2.5f, 2.5f);
        pathBuilder4.curveToRelative(0.05f, 0.0f, 0.1f, -0.01f, 0.16f, -0.02f);
        pathBuilder4.lineToRelative(1.64f, 1.64f);
        pathBuilder4.curveTo(12.67f, 18.12f, 12.0f, 19.98f, 12.0f, 22.0f);
        pathBuilder4.curveToRelative(2.02f, 0.0f, 3.88f, -0.67f, 5.38f, -1.8f);
        pathBuilder4.lineToRelative(2.4f, 2.4f);
        pathBuilder4.lineToRelative(1.41f, -1.41f);
        pathBuilder4.lineTo(2.81f, 2.81f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _macroOff = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
