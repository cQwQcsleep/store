package androidx.compose.material.icons.rounded;

import androidx.compose.material.icons.Icons;
import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.PathFillType;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.StrokeJoin;
import androidx.compose.ui.graphics.vector.ImageVector;
import androidx.compose.ui.graphics.vector.PathBuilder;
import androidx.compose.ui.unit.Dp;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_peopleAlt", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PeopleAlt", "Landroidx/compose/material/icons/Icons$Rounded;", "getPeopleAlt", "(Landroidx/compose/material/icons/Icons$Rounded;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PeopleAltKt {
    private static ImageVector _peopleAlt;

    public static final ImageVector getPeopleAlt(Icons.Rounded rounded) {
        ImageVector imageVector = _peopleAlt;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Rounded.PeopleAlt", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        PathFillType.Companion companion = PathFillType.Companion;
        int i = companion.getEvenOdd-Rg-k1Os();
        Color.Companion companion2 = Color.Companion;
        SolidColor solidColor = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion3 = StrokeCap.Companion;
        int i2 = companion3.getButt-KaPHkGw();
        StrokeJoin.Companion companion4 = StrokeJoin.Companion;
        int i3 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(16.67f, 13.13f);
        pathBuilder.curveTo(18.04f, 14.06f, 19.0f, 15.32f, 19.0f, 17.0f);
        pathBuilder.verticalLineToRelative(3.0f);
        pathBuilder.horizontalLineToRelative(3.0f);
        pathBuilder.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder.verticalLineToRelative(-2.0f);
        pathBuilder.curveTo(23.0f, 14.82f, 19.43f, 13.53f, 16.67f, 13.13f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), i, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i2, i3, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i4 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor2 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion3.getButt-KaPHkGw();
        int i6 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(9.0f, 8.0f);
        pathBuilder2.moveToRelative(-4.0f, 0.0f);
        pathBuilder2.arcToRelative(4.0f, 4.0f, 0.0f, true, true, 8.0f, 0.0f);
        pathBuilder2.arcToRelative(4.0f, 4.0f, 0.0f, true, true, -8.0f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), i4, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i7 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor3 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i8 = companion3.getButt-KaPHkGw();
        int i9 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(15.0f, 12.0f);
        pathBuilder3.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilder3.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilder3.curveToRelative(-0.47f, 0.0f, -0.91f, 0.1f, -1.33f, 0.24f);
        pathBuilder3.curveTo(14.5f, 5.27f, 15.0f, 6.58f, 15.0f, 8.0f);
        pathBuilder3.reflectiveCurveToRelative(-0.5f, 2.73f, -1.33f, 3.76f);
        pathBuilder3.curveTo(14.09f, 11.9f, 14.53f, 12.0f, 15.0f, 12.0f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), i7, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i8, i9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i10 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor4 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i11 = companion3.getButt-KaPHkGw();
        int i12 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(9.0f, 13.0f);
        pathBuilder4.curveToRelative(-2.67f, 0.0f, -8.0f, 1.34f, -8.0f, 4.0f);
        pathBuilder4.verticalLineToRelative(2.0f);
        pathBuilder4.curveToRelative(0.0f, 0.55f, 0.45f, 1.0f, 1.0f, 1.0f);
        pathBuilder4.horizontalLineToRelative(14.0f);
        pathBuilder4.curveToRelative(0.55f, 0.0f, 1.0f, -0.45f, 1.0f, -1.0f);
        pathBuilder4.verticalLineToRelative(-2.0f);
        pathBuilder4.curveTo(17.0f, 14.34f, 11.67f, 13.0f, 9.0f, 13.0f);
        pathBuilder4.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), i10, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i11, i12, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _peopleAlt = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
