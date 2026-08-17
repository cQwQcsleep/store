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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u0015\u0010\u0002\u001a\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"_personAddAlt1", "Landroidx/compose/ui/graphics/vector/ImageVector;", "PersonAddAlt1", "Landroidx/compose/material/icons/Icons$TwoTone;", "getPersonAddAlt1", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class PersonAddAlt1Kt {
    private static ImageVector _personAddAlt1;

    public static final ImageVector getPersonAddAlt1(Icons.TwoTone twoTone) {
        ImageVector imageVector = _personAddAlt1;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.PersonAddAlt1", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(9.0f, 8.0f);
        pathBuilder.moveToRelative(-2.0f, 0.0f);
        pathBuilder.arcToRelative(2.0f, 2.0f, 0.0f, true, true, 4.0f, 0.0f);
        pathBuilder.arcToRelative(2.0f, 2.0f, 0.0f, true, true, -4.0f, 0.0f);
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(9.0f, 16.0f);
        pathBuilder2.curveToRelative(-2.7f, 0.0f, -5.8f, 1.29f, -6.0f, 2.0f);
        pathBuilder2.horizontalLineToRelative(12.0f);
        pathBuilder2.curveTo(14.78f, 17.28f, 11.69f, 16.0f, 9.0f, 16.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 0.3f, (Brush) null, 0.3f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(9.0f, 14.0f);
        pathBuilder3.curveToRelative(-2.67f, 0.0f, -8.0f, 1.34f, -8.0f, 4.0f);
        pathBuilder3.verticalLineToRelative(2.0f);
        pathBuilder3.horizontalLineToRelative(16.0f);
        pathBuilder3.verticalLineToRelative(-2.0f);
        pathBuilder3.curveTo(17.0f, 15.34f, 11.67f, 14.0f, 9.0f, 14.0f);
        pathBuilder3.close();
        pathBuilder3.moveTo(3.0f, 18.0f);
        pathBuilder3.curveToRelative(0.2f, -0.71f, 3.3f, -2.0f, 6.0f, -2.0f);
        pathBuilder3.curveToRelative(2.69f, 0.0f, 5.78f, 1.28f, 6.0f, 2.0f);
        pathBuilder3.horizontalLineTo(3.0f);
        pathBuilder3.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType4 = VectorKt.getDefaultFillType();
        SolidColor solidColor4 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i7 = companion2.getButt-KaPHkGw();
        int i8 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder4 = new PathBuilder();
        pathBuilder4.moveTo(20.0f, 10.0f);
        pathBuilder4.lineToRelative(0.0f, -3.0f);
        pathBuilder4.lineToRelative(-2.0f, 0.0f);
        pathBuilder4.lineToRelative(0.0f, 3.0f);
        pathBuilder4.lineToRelative(-3.0f, 0.0f);
        pathBuilder4.lineToRelative(0.0f, 2.0f);
        pathBuilder4.lineToRelative(3.0f, 0.0f);
        pathBuilder4.lineToRelative(0.0f, 3.0f);
        pathBuilder4.lineToRelative(2.0f, 0.0f);
        pathBuilder4.lineToRelative(0.0f, -3.0f);
        pathBuilder4.lineToRelative(3.0f, 0.0f);
        pathBuilder4.lineToRelative(0.0f, -2.0f);
        pathBuilder4.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder4.getNodes(), defaultFillType4, "", solidColor4, 1.0f, (Brush) null, 1.0f, 1.0f, i7, i8, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType5 = VectorKt.getDefaultFillType();
        SolidColor solidColor5 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i9 = companion2.getButt-KaPHkGw();
        int i10 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder5 = new PathBuilder();
        pathBuilder5.moveTo(9.0f, 12.0f);
        pathBuilder5.curveToRelative(2.21f, 0.0f, 4.0f, -1.79f, 4.0f, -4.0f);
        pathBuilder5.curveToRelative(0.0f, -2.21f, -1.79f, -4.0f, -4.0f, -4.0f);
        pathBuilder5.reflectiveCurveTo(5.0f, 5.79f, 5.0f, 8.0f);
        pathBuilder5.curveTo(5.0f, 10.21f, 6.79f, 12.0f, 9.0f, 12.0f);
        pathBuilder5.close();
        pathBuilder5.moveTo(9.0f, 6.0f);
        pathBuilder5.curveToRelative(1.1f, 0.0f, 2.0f, 0.9f, 2.0f, 2.0f);
        pathBuilder5.curveToRelative(0.0f, 1.1f, -0.9f, 2.0f, -2.0f, 2.0f);
        pathBuilder5.reflectiveCurveTo(7.0f, 9.1f, 7.0f, 8.0f);
        pathBuilder5.curveTo(7.0f, 6.9f, 7.9f, 6.0f, 9.0f, 6.0f);
        pathBuilder5.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder5.getNodes(), defaultFillType5, "", solidColor5, 1.0f, (Brush) null, 1.0f, 1.0f, i9, i10, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _personAddAlt1 = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }
}
