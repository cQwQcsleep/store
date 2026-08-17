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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_autoMode", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AutoMode", "Landroidx/compose/material/icons/Icons$Outlined;", "getAutoMode", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AutoModeKt {
    private static ImageVector _autoMode;

    public static final ImageVector getAutoMode(Icons.Outlined outlined) {
        ImageVector imageVector = _autoMode;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.AutoMode", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(19.03f, 3.56f);
        pathBuilder.curveToRelative(-1.67f, -1.39f, -3.74f, -2.3f, -6.03f, -2.51f);
        pathBuilder.verticalLineToRelative(2.01f);
        pathBuilder.curveToRelative(1.73f, 0.19f, 3.31f, 0.88f, 4.61f, 1.92f);
        pathBuilder.lineTo(19.03f, 3.56f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(11.0f, 3.06f);
        pathBuilder2.verticalLineTo(1.05f);
        pathBuilder2.curveTo(8.71f, 1.25f, 6.64f, 2.17f, 4.97f, 3.56f);
        pathBuilder2.lineToRelative(1.42f, 1.42f);
        pathBuilder2.curveTo(7.69f, 3.94f, 9.27f, 3.25f, 11.0f, 3.06f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(4.98f, 6.39f);
        pathBuilder3.lineTo(3.56f, 4.97f);
        pathBuilder3.curveTo(2.17f, 6.64f, 1.26f, 8.71f, 1.05f, 11.0f);
        pathBuilder3.horizontalLineToRelative(2.01f);
        pathBuilder3.curveTo(3.25f, 9.27f, 3.94f, 7.69f, 4.98f, 6.39f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(20.94f, 11.0f);
        pathBuilder4.horizontalLineToRelative(2.01f);
        pathBuilder4.curveToRelative(-0.21f, -2.29f, -1.12f, -4.36f, -2.51f, -6.03f);
        pathBuilder4.lineToRelative(-1.42f, 1.42f);
        pathBuilder4.curveTo(20.06f, 7.69f, 20.75f, 9.27f, 20.94f, 11.0f);
        pathBuilder4.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i9 = companion2.getButt-KaPHkGw();
        int i10 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder5 = new PathBuilder();
        pathBuilder5.moveTo(7.0f, 12.0f);
        pathBuilder5.lineToRelative(3.44f, 1.56f);
        pathBuilder5.lineToRelative(1.56f, 3.44f);
        pathBuilder5.lineToRelative(1.56f, -3.44f);
        pathBuilder5.lineToRelative(3.44f, -1.56f);
        pathBuilder5.lineToRelative(-3.44f, -1.56f);
        pathBuilder5.lineToRelative(-1.56f, -3.44f);
        pathBuilder5.lineToRelative(-1.56f, 3.44f);
        pathBuilder5.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder5.getNodes(), defaultFillType5, "", solidColor5, 1.0f, (Brush) null, 1.0f, 1.0f, i9, i10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType6 = VectorKt.getDefaultFillType();
        SolidColor solidColor6 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i11 = companion2.getButt-KaPHkGw();
        int i12 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder6 = new PathBuilder();
        pathBuilder6.moveTo(12.0f, 21.0f);
        pathBuilder6.curveToRelative(-3.11f, 0.0f, -5.85f, -1.59f, -7.46f, -4.0f);
        pathBuilder6.horizontalLineTo(7.0f);
        pathBuilder6.verticalLineToRelative(-2.0f);
        pathBuilder6.horizontalLineTo(1.0f);
        pathBuilder6.verticalLineToRelative(6.0f);
        pathBuilder6.horizontalLineToRelative(2.0f);
        pathBuilder6.verticalLineToRelative(-2.7f);
        pathBuilder6.curveToRelative(1.99f, 2.84f, 5.27f, 4.7f, 9.0f, 4.7f);
        pathBuilder6.curveToRelative(4.87f, 0.0f, 9.0f, -3.17f, 10.44f, -7.56f);
        pathBuilder6.lineToRelative(-1.96f, -0.45f);
        pathBuilder6.curveTo(19.25f, 18.48f, 15.92f, 21.0f, 12.0f, 21.0f);
        pathBuilder6.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder6.getNodes(), defaultFillType6, "", solidColor6, 1.0f, (Brush) null, 1.0f, 1.0f, i11, i12, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _autoMode = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
