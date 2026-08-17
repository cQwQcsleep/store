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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_militaryTech", "Landroidx/compose/ui/graphics/vector/ImageVector;", "MilitaryTech", "Landroidx/compose/material/icons/Icons$TwoTone;", "getMilitaryTech", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class MilitaryTechKt {
    private static ImageVector _militaryTech;

    public static final ImageVector getMilitaryTech(Icons.TwoTone twoTone) {
        ImageVector imageVector = _militaryTech;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.MilitaryTech", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(13.0f, 11.07f);
        pathBuilder.lineToRelative(2.0f, -1.2f);
        pathBuilder.lineToRelative(0.0f, -5.87f);
        pathBuilder.lineToRelative(-2.0f, 0.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(9.0f, 4.0f);
        pathBuilder2.lineToRelative(0.0f, 5.87f);
        pathBuilder2.lineToRelative(2.0f, 1.2f);
        pathBuilder2.lineToRelative(0.0f, -7.07f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, (Brush) null, 0.3f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(17.0f, 10.43f);
        pathBuilder3.verticalLineTo(2.0f);
        pathBuilder3.horizontalLineTo(7.0f);
        pathBuilder3.verticalLineToRelative(8.43f);
        pathBuilder3.curveToRelative(0.0f, 0.35f, 0.18f, 0.68f, 0.49f, 0.86f);
        pathBuilder3.lineToRelative(4.18f, 2.51f);
        pathBuilder3.lineToRelative(-0.99f, 2.34f);
        pathBuilder3.lineToRelative(-3.41f, 0.29f);
        pathBuilder3.lineToRelative(2.59f, 2.24f);
        pathBuilder3.lineTo(9.07f, 22.0f);
        pathBuilder3.lineTo(12.0f, 20.23f);
        pathBuilder3.lineTo(14.93f, 22.0f);
        pathBuilder3.lineToRelative(-0.78f, -3.33f);
        pathBuilder3.lineToRelative(2.59f, -2.24f);
        pathBuilder3.lineToRelative(-3.41f, -0.29f);
        pathBuilder3.lineToRelative(-0.99f, -2.34f);
        pathBuilder3.lineToRelative(4.18f, -2.51f);
        pathBuilder3.curveTo(16.82f, 11.11f, 17.0f, 10.79f, 17.0f, 10.43f);
        pathBuilder3.close();
        pathBuilder3.moveTo(11.0f, 11.07f);
        pathBuilder3.lineToRelative(-2.0f, -1.2f);
        pathBuilder3.verticalLineTo(4.0f);
        pathBuilder3.horizontalLineToRelative(2.0f);
        pathBuilder3.verticalLineTo(11.07f);
        pathBuilder3.close();
        pathBuilder3.moveTo(15.0f, 9.87f);
        pathBuilder3.lineToRelative(-2.0f, 1.2f);
        pathBuilder3.verticalLineTo(4.0f);
        pathBuilder3.horizontalLineToRelative(2.0f);
        pathBuilder3.verticalLineTo(9.87f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _militaryTech = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
