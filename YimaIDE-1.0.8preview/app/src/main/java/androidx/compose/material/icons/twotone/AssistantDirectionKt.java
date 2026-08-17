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
@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u0082\u000e¢\u0006\u0002\n\u0000\"\u001e\u0010\u0002\u001a\u00020\u0001*\u00020\u00038FX\u0087\u0004¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"_assistantDirection", "Landroidx/compose/ui/graphics/vector/ImageVector;", "AssistantDirection", "Landroidx/compose/material/icons/Icons$TwoTone;", "getAssistantDirection$annotations", "(Landroidx/compose/material/icons/Icons$TwoTone;)V", "getAssistantDirection", "(Landroidx/compose/material/icons/Icons$TwoTone;)Landroidx/compose/ui/graphics/vector/ImageVector;", "material-icons-extended_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
public final class AssistantDirectionKt {
    private static ImageVector _assistantDirection;

    public static final ImageVector getAssistantDirection(Icons.TwoTone twoTone) {
        ImageVector imageVector = _assistantDirection;
        if (imageVector != null) {
            imageVector.getClass();
            return imageVector;
        }
        ImageVector.Builder builder = new ImageVector.Builder("TwoTone.AssistantDirection", Dp.constructor-impl(24.0f), Dp.constructor-impl(24.0f), 24.0f, 24.0f, 0L, 0, false, 96, (DefaultConstructorMarker) null);
        int defaultFillType = VectorKt.getDefaultFillType();
        Color.Companion companion = Color.Companion;
        SolidColor solidColor = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        StrokeCap.Companion companion2 = StrokeCap.Companion;
        int i = companion2.getButt-KaPHkGw();
        StrokeJoin.Companion companion3 = StrokeJoin.Companion;
        int i2 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder = new PathBuilder();
        pathBuilder.moveTo(12.0f, 3.0f);
        pathBuilder.curveToRelative(-4.99f, 0.0f, -9.0f, 4.01f, -9.0f, 9.0f);
        pathBuilder.reflectiveCurveToRelative(4.01f, 9.0f, 9.0f, 9.0f);
        pathBuilder.reflectiveCurveToRelative(9.0f, -4.01f, 9.0f, -9.0f);
        pathBuilder.reflectiveCurveTo(16.99f, 3.0f, 12.0f, 3.0f);
        pathBuilder.close();
        pathBuilder.moveTo(12.54f, 19.8f);
        pathBuilder.curveToRelative(-0.35f, 0.27f, -0.79f, 0.27f, -1.15f, 0.0f);
        pathBuilder.lineTo(4.2f, 12.58f);
        pathBuilder.curveToRelative(-0.27f, -0.36f, -0.27f, -0.8f, 0.0f, -1.16f);
        pathBuilder.lineToRelative(7.19f, -7.22f);
        pathBuilder.curveToRelative(0.35f, -0.27f, 0.79f, -0.27f, 1.15f, 0.0f);
        pathBuilder.lineToRelative(7.19f, 7.22f);
        pathBuilder.curveToRelative(0.36f, 0.27f, 0.36f, 0.8f, 0.0f, 1.16f);
        pathBuilder.lineTo(12.54f, 19.8f);
        pathBuilder.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder.getNodes(), defaultFillType, "", solidColor, 0.3f, (Brush) null, 0.3f, 1.0f, i, i2, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType2 = VectorKt.getDefaultFillType();
        SolidColor solidColor2 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i3 = companion2.getButt-KaPHkGw();
        int i4 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder2 = new PathBuilder();
        pathBuilder2.moveTo(12.0f, 1.0f);
        pathBuilder2.curveTo(5.9f, 1.0f, 1.0f, 5.9f, 1.0f, 12.0f);
        pathBuilder2.reflectiveCurveToRelative(4.9f, 11.0f, 11.0f, 11.0f);
        pathBuilder2.reflectiveCurveToRelative(11.0f, -4.9f, 11.0f, -11.0f);
        pathBuilder2.reflectiveCurveTo(18.1f, 1.0f, 12.0f, 1.0f);
        pathBuilder2.close();
        pathBuilder2.moveTo(12.0f, 21.0f);
        pathBuilder2.curveToRelative(-4.99f, 0.0f, -9.0f, -4.01f, -9.0f, -9.0f);
        pathBuilder2.reflectiveCurveToRelative(4.01f, -9.0f, 9.0f, -9.0f);
        pathBuilder2.reflectiveCurveToRelative(9.0f, 4.01f, 9.0f, 9.0f);
        pathBuilder2.reflectiveCurveTo(16.99f, 21.0f, 12.0f, 21.0f);
        pathBuilder2.close();
        ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder2.getNodes(), defaultFillType2, "", solidColor2, 1.0f, (Brush) null, 1.0f, 1.0f, i3, i4, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null);
        int defaultFillType3 = VectorKt.getDefaultFillType();
        SolidColor solidColor3 = new SolidColor(companion.getBlack-0d7_KjU(), (DefaultConstructorMarker) null);
        int i5 = companion2.getButt-KaPHkGw();
        int i6 = companion3.getBevel-LxFBmk8();
        PathBuilder pathBuilder3 = new PathBuilder();
        pathBuilder3.moveTo(19.73f, 11.42f);
        pathBuilder3.lineTo(12.54f, 4.2f);
        pathBuilder3.curveToRelative(-0.36f, -0.27f, -0.8f, -0.27f, -1.15f, 0.0f);
        pathBuilder3.lineTo(4.2f, 11.42f);
        pathBuilder3.curveToRelative(-0.27f, 0.36f, -0.27f, 0.8f, 0.0f, 1.16f);
        pathBuilder3.lineToRelative(7.19f, 7.22f);
        pathBuilder3.curveToRelative(0.36f, 0.27f, 0.8f, 0.27f, 1.15f, 0.0f);
        pathBuilder3.lineToRelative(7.19f, -7.22f);
        pathBuilder3.curveTo(20.09f, 12.22f, 20.09f, 11.69f, 19.73f, 11.42f);
        pathBuilder3.close();
        pathBuilder3.moveTo(13.5f, 14.5f);
        pathBuilder3.verticalLineTo(12.0f);
        pathBuilder3.horizontalLineTo(10.0f);
        pathBuilder3.verticalLineToRelative(3.0f);
        pathBuilder3.horizontalLineTo(8.0f);
        pathBuilder3.verticalLineToRelative(-4.0f);
        pathBuilder3.curveToRelative(0.0f, -0.6f, 0.4f, -1.0f, 1.0f, -1.0f);
        pathBuilder3.horizontalLineToRelative(4.5f);
        pathBuilder3.verticalLineTo(7.5f);
        pathBuilder3.lineTo(17.0f, 11.0f);
        pathBuilder3.lineTo(13.5f, 14.5f);
        pathBuilder3.close();
        ImageVector imageVectorBuild = ImageVector.Builder.addPath-oIyEayM$default(builder, pathBuilder3.getNodes(), defaultFillType3, "", solidColor3, 1.0f, (Brush) null, 1.0f, 1.0f, i5, i6, 1.0f, 0.0f, 0.0f, 0.0f, 14336, (Object) null).build();
        _assistantDirection = imageVectorBuild;
        imageVectorBuild.getClass();
        return imageVectorBuild;
    }

    @Deprecated(message = "Use the AutoMirrored version at Icons.AutoMirrored.TwoTone.AssistantDirection", replaceWith = @ReplaceWith(expression = "Icons.AutoMirrored.TwoTone.AssistantDirection", imports = {"androidx.compose.material.icons.automirrored.twotone.AssistantDirection"}))
    public static /* synthetic */ void getAssistantDirection$annotations(Icons.TwoTone twoTone) {
    }
}
