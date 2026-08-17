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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_followTheSigns", "Landroidx/compose/ui/graphics/vector/ImageVector;", "FollowTheSigns", "Landroidx/compose/material/icons/Icons$TwoTone;", "getFollowTheSigns$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getFollowTheSigns", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class FollowTheSignsKt {
    private static ImageVector _followTheSigns;

    public static final ImageVector getFollowTheSigns(Icons.TwoTone twoTone) {
        ImageVector imageVector = _followTheSigns;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.FollowTheSigns", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(17.64f, 7.75f);
        pathBuilder.verticalLineTo(6.0f);
        pathBuilder.horizontalLineToRelative(-3.51f);
        pathBuilder.verticalLineTo(4.5f);
        pathBuilder.horizontalLineToRelative(3.51f);
        pathBuilder.verticalLineTo(2.75f);
        pathBuilder.lineToRelative(2.49f, 2.5f);
        pathBuilder.lineTo(17.64f, 7.75f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(9.12f, 5.25f);
        pathBuilder2.curveToRelative(1.1f, 0.0f, 2.0f, -0.9f, 2.0f, -2.0f);
        pathBuilder2.reflectiveCurveToRelative(-0.9f, -2.0f, -2.0f, -2.0f);
        pathBuilder2.reflectiveCurveToRelative(-2.0f, 0.9f, -2.0f, 2.0f);
        pathBuilder2.reflectiveCurveTo(8.02f, 5.25f, 9.12f, 5.25f);
        pathBuilder2.close();
        pathBuilder2.moveTo(5.38f, 8.65f);
        pathBuilder2.lineToRelative(-2.75f, 14.1f);
        pathBuilder2.horizontalLineToRelative(2.1f);
        pathBuilder2.lineToRelative(1.75f, -8.0f);
        pathBuilder2.lineToRelative(2.15f, 2.0f);
        pathBuilder2.verticalLineToRelative(6.0f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.verticalLineTo(15.2f);
        pathBuilder2.lineToRelative(-2.05f, -2.05f);
        pathBuilder2.lineToRelative(0.6f, -3.0f);
        pathBuilder2.curveToRelative(1.3f, 1.6f, 3.25f, 2.6f, 5.45f, 2.6f);
        pathBuilder2.verticalLineToRelative(-2.0f);
        pathBuilder2.curveToRelative(-1.85f, 0.0f, -3.45f, -1.0f, -4.35f, -2.45f);
        pathBuilder2.lineTo(9.32f, 6.7f);
        pathBuilder2.curveToRelative(-0.35f, -0.6f, -1.0f, -0.95f, -1.7f, -0.95f);
        pathBuilder2.curveToRelative(-0.25f, 0.0f, -0.5f, 0.05f, -0.75f, 0.15f);
        pathBuilder2.lineTo(1.62f, 8.05f);
        pathBuilder2.verticalLineToRelative(4.7f);
        pathBuilder2.horizontalLineToRelative(2.0f);
        pathBuilder2.verticalLineTo(9.4f);
        pathBuilder2.lineTo(5.38f, 8.65f);
        pathBuilder2.moveTo(12.62f, 1.75f);
        pathBuilder2.verticalLineToRelative(7.0f);
        pathBuilder2.horizontalLineToRelative(3.75f);
        pathBuilder2.verticalLineToRelative(14.0f);
        pathBuilder2.horizontalLineToRelative(1.5f);
        pathBuilder2.verticalLineToRelative(-14.0f);
        pathBuilder2.horizontalLineToRelative(3.75f);
        pathBuilder2.verticalLineToRelative(-7.0f);
        pathBuilder2.horizontalLineTo(12.62f);
        pathBuilder2.close();
        pathBuilder2.moveTo(17.64f, 7.75f);
        pathBuilder2.verticalLineTo(6.0f);
        pathBuilder2.horizontalLineToRelative(-3.51f);
        pathBuilder2.verticalLineTo(4.5f);
        pathBuilder2.horizontalLineToRelative(3.51f);
        pathBuilder2.verticalLineTo(2.75f);
        pathBuilder2.lineToRelative(2.49f, 2.5f);
        pathBuilder2.lineTo(17.64f, 7.75f);
        pathBuilder2.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _followTheSigns = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }

    @Deprecated(message = "Use the AutoMirrored version at Icons.AutoMirrored.TwoTone.FollowTheSigns", replaceWith = @ReplaceWith(expression = "Icons.AutoMirrored.TwoTone.FollowTheSigns", imports = {"androidx.compose.material.icons.automirrored.twotone.FollowTheSigns"}))
    public static /* synthetic */ void getFollowTheSigns$annotations(Icons.TwoTone twoTone) {
    }
}
