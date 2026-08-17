package androidx.compose.material.icons.filled;

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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_howToReg", "Landroidx/compose/ui/graphics/vector/ImageVector;", "HowToReg", "Landroidx/compose/material/icons/Icons$Filled;", "getHowToReg", "(Landroidx/compose/material/icons/Icons$Filled;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class HowToRegKt {
    private static ImageVector _howToReg;

    public static final ImageVector getHowToReg(Icons.Filled filled) {
        ImageVector imageVector = _howToReg;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Filled.HowToReg", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        PathFillType.Companion companion = PathFillType.Companion;
        int i = companion.getEvenOdd-Rg-k1Os();
        Color.Companion companion2 = Color.Companion;
        SolidColor solidColor = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion3 = StrokeCap.Companion;
        int i2 = companion3.getButt-KaPHkGw();
        StrokeJoin.Companion companion4 = StrokeJoin.Companion;
        int i3 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.0f, 17.0f);
        pathBuilder.lineToRelative(3.0f, -2.94f);
        pathBuilder.curveToRelative(-0.39f, -0.04f, -0.68f, -0.06f, -1.0f, -0.06f);
        pathBuilder.curveToRelative(-2.67f, 0.0f, -8.0f, 1.34f, -8.0f, 4.0f);
        pathBuilder.verticalLineToRelative(2.0f);
        pathBuilder.horizontalLineToRelative(9.0f);
        pathBuilder.lineToRelative(-3.0f, -3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(11.0f, 12.0f);
        pathBuilder.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilder.reflectiveCurveToRelative(-1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilder.reflectiveCurveToRelative(-4.0f, 1.79f, -4.0f, 4.0f);
        pathBuilder.reflectiveCurveToRelative(1.79f, 4.0f, 4.0f, 4.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), i, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i2, i3, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i4 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor2 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion3.getButt-KaPHkGw();
        int i6 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(15.47f, 20.5f);
        pathBuilder2.lineTo(12.0f, 17.0f);
        pathBuilder2.lineToRelative(1.4f, -1.41f);
        pathBuilder2.lineToRelative(2.07f, 2.08f);
        pathBuilder2.lineToRelative(5.13f, -5.17f);
        pathBuilder2.lineToRelative(1.4f, 1.41f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), i4, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _howToReg = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
