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
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_viewSidebar", "Landroidx/compose/ui/graphics/vector/ImageVector;", "ViewSidebar", "Landroidx/compose/material/icons/Icons$TwoTone;", "getViewSidebar$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getViewSidebar", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class ViewSidebarKt {
    private static ImageVector _viewSidebar;

    public static final ImageVector getViewSidebar(Icons.TwoTone twoTone) {
        ImageVector imageVector = _viewSidebar;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.ViewSidebar", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(20.0f, 8.67f);
        pathBuilder.horizontalLineToRelative(-2.5f);
        pathBuilder.verticalLineTo(6.0f);
        pathBuilder.horizontalLineTo(20.0f);
        pathBuilder.verticalLineTo(8.67f);
        pathBuilder.close();
        pathBuilder.moveTo(17.5f, 10.67f);
        pathBuilder.horizontalLineTo(20.0f);
        pathBuilder.verticalLineToRelative(2.67f);
        pathBuilder.horizontalLineToRelative(-2.5f);
        pathBuilder.verticalLineTo(10.67f);
        pathBuilder.close();
        pathBuilder.moveTo(4.0f, 6.0f);
        pathBuilder.horizontalLineToRelative(11.5f);
        pathBuilder.verticalLineToRelative(12.0f);
        pathBuilder.horizontalLineTo(4.0f);
        pathBuilder.verticalLineTo(6.0f);
        pathBuilder.close();
        pathBuilder.moveTo(17.5f, 18.0f);
        pathBuilder.verticalLineToRelative(-2.67f);
        pathBuilder.horizontalLineTo(20.0f);
        pathBuilder.verticalLineTo(18.0f);
        pathBuilder.horizontalLineTo(17.5f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(2.0f, 4.0f);
        pathBuilder2.verticalLineToRelative(16.0f);
        pathBuilder2.horizontalLineToRelative(20.0f);
        pathBuilder2.verticalLineTo(4.0f);
        pathBuilder2.horizontalLineTo(2.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(20.0f, 8.67f);
        pathBuilder2.horizontalLineToRelative(-2.5f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.horizontalLineTo(20.0f);
        pathBuilder2.verticalLineTo(8.67f);
        pathBuilder2.close();
        pathBuilder2.moveTo(17.5f, 10.67f);
        pathBuilder2.horizontalLineTo(20.0f);
        pathBuilder2.verticalLineToRelative(2.67f);
        pathBuilder2.horizontalLineToRelative(-2.5f);
        pathBuilder2.verticalLineTo(10.67f);
        pathBuilder2.close();
        pathBuilder2.moveTo(4.0f, 6.0f);
        pathBuilder2.horizontalLineToRelative(11.5f);
        pathBuilder2.verticalLineToRelative(12.0f);
        pathBuilder2.horizontalLineTo(4.0f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(17.5f, 18.0f);
        pathBuilder2.verticalLineToRelative(-2.67f);
        pathBuilder2.horizontalLineTo(20.0f);
        pathBuilder2.verticalLineTo(18.0f);
        pathBuilder2.horizontalLineTo(17.5f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _viewSidebar = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }

    @Deprecated(message = "Use the AutoMirrored version at Icons.AutoMirrored.TwoTone.ViewSidebar", replaceWith = @ReplaceWith(expression = "Icons.AutoMirrored.TwoTone.ViewSidebar", imports = {"androidx.compose.material.icons.automirrored.twotone.ViewSidebar"}))
    public static /* synthetic */ void getViewSidebar$annotations(Icons.TwoTone twoTone) {
    }
}
