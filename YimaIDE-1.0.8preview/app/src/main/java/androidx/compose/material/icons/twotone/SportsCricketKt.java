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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_sportsCricket", "Landroidx/compose/ui/graphics/vector/ImageVector;", "SportsCricket", "Landroidx/compose/material/icons/Icons$TwoTone;", "getSportsCricket", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class SportsCricketKt {
    private static ImageVector _sportsCricket;

    public static final ImageVector getSportsCricket(Icons.TwoTone twoTone) {
        ImageVector imageVector = _sportsCricket;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.SportsCricket", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(4.414f, 7.839f);
        pathBuilder.lineToRelative(1.421f, -1.421f);
        pathBuilder.lineToRelative(7.085f, 7.085f);
        pathBuilder.lineToRelative(-1.421f, 1.421f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(18.5f, 5.5f);
        pathBuilder2.moveToRelative(-1.5f, 0.0f);
        pathBuilder2.arcToRelative(1.5f, 1.5f, 0.0f, true, true, 3.0f, 0.0f);
        pathBuilder2.arcToRelative(1.5f, 1.5f, 0.0f, true, true, -3.0f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, (Brush) null, 0.3f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(15.04f, 12.79f);
        pathBuilder3.lineToRelative(-8.5f, -8.5f);
        pathBuilder3.curveTo(6.35f, 4.1f, 6.09f, 4.0f, 5.83f, 4.0f);
        pathBuilder3.reflectiveCurveTo(5.32f, 4.1f, 5.13f, 4.29f);
        pathBuilder3.lineTo(2.29f, 7.13f);
        pathBuilder3.curveToRelative(-0.39f, 0.39f, -0.39f, 1.03f, 0.0f, 1.42f);
        pathBuilder3.lineToRelative(8.5f, 8.5f);
        pathBuilder3.curveToRelative(0.2f, 0.2f, 0.45f, 0.29f, 0.71f, 0.29f);
        pathBuilder3.curveToRelative(0.26f, 0.0f, 0.51f, -0.1f, 0.71f, -0.29f);
        pathBuilder3.lineToRelative(2.83f, -2.83f);
        pathBuilder3.curveTo(15.43f, 13.82f, 15.43f, 13.18f, 15.04f, 12.79f);
        pathBuilder3.close();
        pathBuilder3.moveTo(11.5f, 14.92f);
        pathBuilder3.lineTo(4.41f, 7.83f);
        pathBuilder3.lineToRelative(1.42f, -1.42f);
        pathBuilder3.lineToRelative(7.09f, 7.09f);
        pathBuilder3.lineTo(11.5f, 14.92f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(14.341f, 17.756f);
        pathBuilder4.lineToRelative(1.414f, -1.414f);
        pathBuilder4.lineToRelative(4.243f, 4.243f);
        pathBuilder4.lineToRelative(-1.414f, 1.414f);
        pathBuilder4.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i9 = companion2.getButt-KaPHkGw();
        int i10 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder5 = new PathBuilder();
        pathBuilder5.moveTo(18.5f, 2.0f);
        pathBuilder5.curveTo(16.57f, 2.0f, 15.0f, 3.57f, 15.0f, 5.5f);
        pathBuilder5.curveTo(15.0f, 7.43f, 16.57f, 9.0f, 18.5f, 9.0f);
        pathBuilder5.reflectiveCurveTo(22.0f, 7.43f, 22.0f, 5.5f);
        pathBuilder5.curveTo(22.0f, 3.57f, 20.43f, 2.0f, 18.5f, 2.0f);
        pathBuilder5.close();
        pathBuilder5.moveTo(18.5f, 7.0f);
        pathBuilder5.curveTo(17.67f, 7.0f, 17.0f, 6.33f, 17.0f, 5.5f);
        pathBuilder5.reflectiveCurveTo(17.67f, 4.0f, 18.5f, 4.0f);
        pathBuilder5.reflectiveCurveTo(20.0f, 4.67f, 20.0f, 5.5f);
        pathBuilder5.reflectiveCurveTo(19.33f, 7.0f, 18.5f, 7.0f);
        pathBuilder5.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder5.getNodes(), defaultFillType5, "", solidColor5, 1.0f, (Brush) null, 1.0f, 1.0f, i9, i10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _sportsCricket = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
