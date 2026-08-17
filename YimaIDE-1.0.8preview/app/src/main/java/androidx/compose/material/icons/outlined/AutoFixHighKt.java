package androidx.compose.material.icons.outlined;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autoFixHigh", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AutoFixHigh", "Landroidx/compose/material/icons/Icons$Outlined;", "getAutoFixHigh", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoFixHighKt {
    private static ImageVector _autoFixHigh;

    public static final ImageVector getAutoFixHigh(Icons.Outlined outlined) {
        ImageVector imageVector = _autoFixHigh;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AutoFixHigh", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(20.0f, 7.0f);
        pathBuilder.lineToRelative(0.94f, -2.06f);
        pathBuilder.lineToRelative(2.06f, -0.94f);
        pathBuilder.lineToRelative(-2.06f, -0.94f);
        pathBuilder.lineToRelative(-0.94f, -2.06f);
        pathBuilder.lineToRelative(-0.94f, 2.06f);
        pathBuilder.lineToRelative(-2.06f, 0.94f);
        pathBuilder.lineToRelative(2.06f, 0.94f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(8.5f, 7.0f);
        pathBuilder2.lineToRelative(0.94f, -2.06f);
        pathBuilder2.lineToRelative(2.06f, -0.94f);
        pathBuilder2.lineToRelative(-2.06f, -0.94f);
        pathBuilder2.lineToRelative(-0.94f, -2.06f);
        pathBuilder2.lineToRelative(-0.94f, 2.06f);
        pathBuilder2.lineToRelative(-2.06f, 0.94f);
        pathBuilder2.lineToRelative(2.06f, 0.94f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(20.0f, 12.5f);
        pathBuilder3.lineToRelative(-0.94f, 2.06f);
        pathBuilder3.lineToRelative(-2.06f, 0.94f);
        pathBuilder3.lineToRelative(2.06f, 0.94f);
        pathBuilder3.lineToRelative(0.94f, 2.06f);
        pathBuilder3.lineToRelative(0.94f, -2.06f);
        pathBuilder3.lineToRelative(2.06f, -0.94f);
        pathBuilder3.lineToRelative(-2.06f, -0.94f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(17.71f, 9.12f);
        pathBuilder4.lineToRelative(-2.83f, -2.83f);
        pathBuilder4.curveTo(14.68f, 6.1f, 14.43f, 6.0f, 14.17f, 6.0f);
        pathBuilder4.curveToRelative(-0.26f, 0.0f, -0.51f, 0.1f, -0.71f, 0.29f);
        pathBuilder4.lineTo(2.29f, 17.46f);
        pathBuilder4.curveToRelative(-0.39f, 0.39f, -0.39f, 1.02f, 0.0f, 1.41f);
        pathBuilder4.lineToRelative(2.83f, 2.83f);
        pathBuilder4.curveTo(5.32f, 21.9f, 5.57f, 22.0f, 5.83f, 22.0f);
        pathBuilder4.reflectiveCurveToRelative(0.51f, -0.1f, 0.71f, -0.29f);
        pathBuilder4.lineToRelative(11.17f, -11.17f);
        pathBuilder4.curveTo(18.1f, 10.15f, 18.1f, 9.51f, 17.71f, 9.12f);
        pathBuilder4.close();
        pathBuilder4.moveTo(14.17f, 8.42f);
        pathBuilder4.lineToRelative(1.41f, 1.41f);
        pathBuilder4.lineTo(14.41f, 11.0f);
        pathBuilder4.lineTo(13.0f, 9.59f);
        pathBuilder4.lineTo(14.17f, 8.42f);
        pathBuilder4.close();
        pathBuilder4.moveTo(5.83f, 19.59f);
        pathBuilder4.lineToRelative(-1.41f, -1.41f);
        pathBuilder4.lineTo(11.59f, 11.0f);
        pathBuilder4.lineTo(13.0f, 12.41f);
        pathBuilder4.lineTo(5.83f, 19.59f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _autoFixHigh = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
