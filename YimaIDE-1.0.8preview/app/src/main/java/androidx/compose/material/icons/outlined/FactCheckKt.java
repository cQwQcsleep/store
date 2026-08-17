package androidx.compose.material.icons.outlined;

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
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_factCheck", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FactCheck", "Landroidx/compose/material/icons/Icons$Outlined;", "getFactCheck$annotations", "(Landroidx/compose/material/icons/Icons$Outlined;)V", "getFactCheck", "(Landroidx/compose/material/icons/Icons$Outlined;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FactCheckKt {
    private static ImageVector _factCheck;

    public static final ImageVector getFactCheck(Icons.Outlined outlined) {
        ImageVector imageVector = _factCheck;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("Outlined.FactCheck", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        PathFillType.Companion companion = PathFillType.Companion;
        int i = companion.getEvenOdd-Rg-k1Os();
        Color.Companion companion2 = Color.Companion;
        SolidColor solidColor = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion3 = StrokeCap.Companion;
        int i2 = companion3.getButt-KaPHkGw();
        StrokeJoin.Companion companion4 = StrokeJoin.Companion;
        int i3 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(20.0f, 3.0f);
        pathBuilder.horizontalLineTo(4.0f);
        pathBuilder.curveTo(2.9f, 3.0f, 2.0f, 3.9f, 2.0f, 5.0f);
        pathBuilder.verticalLineToRelative(14.0f);
        pathBuilder.curveToRelative(0.0f, 1.1f, 0.9f, 2.0f, 2.0f, 2.0f);
        pathBuilder.horizontalLineToRelative(16.0f);
        pathBuilder.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder.verticalLineTo(5.0f);
        pathBuilder.curveTo(22.0f, 3.9f, 21.1f, 3.0f, 20.0f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(20.0f, 19.0f);
        pathBuilder.horizontalLineTo(4.0f);
        pathBuilder.verticalLineTo(5.0f);
        pathBuilder.horizontalLineToRelative(16.0f);
        pathBuilder.verticalLineTo(19.0f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), i, "", solidColor, 1.0f, (Brush) null, 1.0f, 1.0f, i2, i3, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i4 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor2 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion3.getButt-KaPHkGw();
        int i6 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(19.41f, 10.42f);
        pathBuilder2.lineToRelative(-1.42f, -1.42f);
        pathBuilder2.lineToRelative(-3.17f, 3.17f);
        pathBuilder2.lineToRelative(-1.41f, -1.42f);
        pathBuilder2.lineToRelative(-1.41f, 1.41f);
        pathBuilder2.lineToRelative(2.82f, 2.84f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), i4, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i7 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor3 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i8 = companion3.getButt-KaPHkGw();
        int i9 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(5.0f, 7.0f);
        pathBuilder3.horizontalLineToRelative(5.0f);
        pathBuilder3.verticalLineToRelative(2.0f);
        pathBuilder3.horizontalLineToRelative(-5.0f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), i7, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i8, i9, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i10 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor4 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i11 = companion3.getButt-KaPHkGw();
        int i12 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(5.0f, 11.0f);
        pathBuilder4.horizontalLineToRelative(5.0f);
        pathBuilder4.verticalLineToRelative(2.0f);
        pathBuilder4.horizontalLineToRelative(-5.0f);
        pathBuilder4.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), i10, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i11, i12, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int i13 = companion.getEvenOdd-Rg-k1Os();
        SolidColor solidColor5 = new SolidColor(companion2.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i14 = companion3.getButt-KaPHkGw();
        int i15 = companion4.getBevel-LxFBmk8();
        PathBuilder pathBuilder5 = new PathBuilder();
        pathBuilder5.moveTo(5.0f, 15.0f);
        pathBuilder5.horizontalLineToRelative(5.0f);
        pathBuilder5.verticalLineToRelative(2.0f);
        pathBuilder5.horizontalLineToRelative(-5.0f);
        pathBuilder5.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder5.getNodes(), i13, "", solidColor5, 1.0f, (Brush) null, 1.0f, 1.0f, i14, i15, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _factCheck = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }

    @Deprecated(message = "Use the AutoMirrored version at Icons.AutoMirrored.Outlined.FactCheck", replaceWith = @ReplaceWith(expression = "Icons.AutoMirrored.Outlined.FactCheck", imports = {"androidx.compose.material.icons.automirrored.outlined.FactCheck"}))
    public static /* synthetic */ void getFactCheck$annotations(Icons.Outlined outlined) {
    }
}
