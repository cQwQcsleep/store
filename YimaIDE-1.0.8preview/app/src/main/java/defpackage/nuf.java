package defpackage;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderStrokeKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.layout.WindowInsetsPadding_androidKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.material3.ButtonDefaults;
import androidx.compose.material3.ButtonElevation;
import androidx.compose.material3.ButtonKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextOverflow;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import io.github.rosemoe.sora.widget.CodeEditor;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class nuf {
    public static Unit a(Function0 function0, Modifier modifier, int i, int i2, Composer composer, int i3) {
        v(function0, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(Function2 function2, RowScope rowScope, Composer composer, int i) {
        rowScope.getClass();
        if (composer.shouldExecute((i & 17) != 16, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(463673212, i, -1, "com.yimaide.app.ui.workbench.ToolbarActionButton.<anonymous> (WorkbenchTopBar.kt:162)");
            }
            function2.invoke(composer, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(DrawScope drawScope) {
        drawScope.getClass();
        float f = Size.getMinDimension-impl(drawScope.getSize-NH-jbRc()) * 0.12f;
        float f2 = Size.getMinDimension-impl(drawScope.getSize-NH-jbRc()) * 0.28f;
        long j = Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) * 0.42f)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) * 0.42f)) & 4294967295L));
        DrawScope.drawCircle-VaOC9Bg$default(drawScope, ColorKt.Color(4281549141L), f2, j, 0.0f, new Stroke(f, 0.0f, 0, 0, (PathEffect) null, 30, (DefaultConstructorMarker) null), (ColorFilter) null, 0, 104, (Object) null);
        long jColor = ColorKt.Color(4281549141L);
        float fIntBitsToFloat = Float.intBitsToFloat((int) (j >> 32));
        float f3 = 0.62f * f2;
        DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (j & 4294967295L)) + f3)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat + f3) << 32)), Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) * 0.84f)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) * 0.84f)) & 4294967295L)), f, StrokeCap.Companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit d(String str, String str2, String str3, boolean z, Function0 function0, Function0 function1, Function0 function2, Function0 function3, Function0 function4, int i, int i2, Composer composer, int i3) {
        z(str, str2, str3, z, function0, function1, function2, function3, function4, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit e(boolean z, DrawScope drawScope) {
        drawScope.getClass();
        float f = Size.getMinDimension-impl(drawScope.getSize-NH-jbRc()) * 0.12f;
        long jColor = ColorKt.Color(4281549141L);
        float f2 = Size.getMinDimension-impl(drawScope.getSize-NH-jbRc()) * 0.18f;
        float f3 = Size.getMinDimension-impl(drawScope.getSize-NH-jbRc()) * 0.22f;
        if (z) {
            float f4 = f2 + f3;
            long j = Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(f4)) & 4294967295L));
            long j2 = Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L));
            StrokeCap.Companion companion = StrokeCap.Companion;
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, j, j2, f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(f4)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits(f4)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits((Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2) - f3)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits((Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2) - f3)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(f4)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            long j3 = Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits((Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2) - f3)) & 4294967295L));
            float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2;
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, j3, Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat) << 32)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            long j4 = Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L));
            float fIntBitsToFloat2 = (Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2) - f3;
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, j4, Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat2) << 32)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        } else {
            float f5 = f2 + f3;
            long j5 = Offset.constructor-impl((((long) Float.floatToRawIntBits(f5)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L));
            long j6 = Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L));
            StrokeCap.Companion companion2 = StrokeCap.Companion;
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, j5, j6, f, companion2.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(f5)) & 4294967295L)), f, companion2.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits((Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2) - f3)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), f, companion2.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits(f2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits(f5)) & 4294967295L)), f, companion2.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(f5)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L)), f, companion2.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits((Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2) - f3)) & 4294967295L)), f, companion2.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            long j7 = Offset.constructor-impl((((long) Float.floatToRawIntBits((Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2) - f3)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L));
            float fIntBitsToFloat3 = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2;
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, j7, Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L) | (Float.floatToRawIntBits(fIntBitsToFloat3) << 32)), f, companion2.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
            DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2)) << 32) | (((long) Float.floatToRawIntBits((Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2) - f3)) & 4294967295L)), f, companion2.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        }
        return Unit.INSTANCE;
    }

    public static Unit f(Modifier modifier, int i, int i2, Composer composer, int i3) {
        t(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit g(DrawScope drawScope) {
        drawScope.getClass();
        long jColor = ColorKt.Color(4279673674L);
        float f = Size.getMinDimension-impl(drawScope.getSize-NH-jbRc()) * 0.22f;
        Path Path = AndroidPath_androidKt.Path();
        Path.moveTo(f, f);
        Path.lineTo(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f, Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) * 0.5f);
        Path.lineTo(f, Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f);
        Path.close();
        DrawScope.drawPath-LG529CI$default(drawScope, Path, jColor, 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 60, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit h(Function0 function0, Function2 function2, int i, Composer composer, int i2) {
        y(function0, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit i(Modifier modifier, int i, int i2, Composer composer, int i3) {
        x(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit j() {
        return Unit.INSTANCE;
    }

    public static Unit k() {
        return Unit.INSTANCE;
    }

    public static Unit l(Function0 function0, Modifier modifier, int i, int i2, Composer composer, int i3) {
        w(function0, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit m(boolean z, Composer composer, int i) {
        if (composer.shouldExecute((i & 3) != 2, i & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(102628205, i, -1, "com.yimaide.app.ui.workbench.WorkbenchTopBar.<anonymous>.<anonymous> (WorkbenchTopBar.kt:78)");
            }
            s(z, null, composer, 0, 2);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit n() {
        return Unit.INSTANCE;
    }

    public static Unit o(DrawScope drawScope) {
        drawScope.getClass();
        long jColor = ColorKt.Color(4280640491L);
        float f = Size.getMinDimension-impl(drawScope.getSize-NH-jbRc()) * 0.11f;
        float f2 = Size.getMinDimension-impl(drawScope.getSize-NH-jbRc()) * 0.16f;
        float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) - f2;
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) * 0.42f;
        float fIntBitsToFloat3 = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) - f2;
        long j = Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L));
        long j2 = Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L));
        StrokeCap.Companion companion = StrokeCap.Companion;
        DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, j, j2, f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat3)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat3)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(f2)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat3)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat3)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        float fIntBitsToFloat4 = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) * 0.5f;
        float fIntBitsToFloat5 = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) * 0.1f;
        float f3 = fIntBitsToFloat2 + ((fIntBitsToFloat3 - fIntBitsToFloat2) * 0.45f);
        DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat4)) << 32) | (((long) Float.floatToRawIntBits(fIntBitsToFloat5)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat4)) << 32) | (((long) Float.floatToRawIntBits(f3)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        float f4 = 0.16f * Size.getMinDimension-impl(drawScope.getSize-NH-jbRc());
        float f5 = f3 - f4;
        DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat4)) << 32) | (((long) Float.floatToRawIntBits(f3)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat4 - f4)) << 32) | (((long) Float.floatToRawIntBits(f5)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat4)) << 32) | (((long) Float.floatToRawIntBits(f3)) & 4294967295L)), Offset.constructor-impl((((long) Float.floatToRawIntBits(fIntBitsToFloat4 + f4)) << 32) | (((long) Float.floatToRawIntBits(f5)) & 4294967295L)), f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit p() {
        return Unit.INSTANCE;
    }

    public static Unit q(boolean z, Modifier modifier, int i, int i2, Composer composer, int i3) {
        s(z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit r(Modifier modifier, int i, int i2, Composer composer, int i3) {
        u(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static final void s(final boolean z, final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-75281346);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 != 0) {
            i3 |= 48;
        } else if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(modifier) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 19) != 18, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-75281346, i3, -1, "com.yimaide.app.ui.workbench.FullscreenToolbarIcon (WorkbenchTopBar.kt:200)");
            }
            Modifier modifier2 = SizeKt.size-3ABfNKs(modifier, Dp.constructor-impl(18.0f));
            boolean z2 = (i3 & 14) == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: luf
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return nuf.e(z, (DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifier2, (Function1) objRememberedValue, composerStartRestartGroup, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: muf
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return nuf.q(z, modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void t(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(1377956820);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1377956820, i3, -1, "com.yimaide.app.ui.workbench.PackageIcon (WorkbenchTopBar.kt:119)");
            }
            Modifier modifier2 = SizeKt.size-3ABfNKs(modifier, Dp.constructor-impl(18.0f));
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: ytf
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return nuf.o((DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifier2, (Function1) objRememberedValue, composerStartRestartGroup, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ztf
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return nuf.f(modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void u(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1428570998);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1428570998, i3, -1, "com.yimaide.app.ui.workbench.PlayIcon (WorkbenchTopBar.kt:260)");
            }
            Modifier modifier2 = SizeKt.size-3ABfNKs(modifier, Dp.constructor-impl(16.0f));
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: auf
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return nuf.g((DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifier2, (Function1) objRememberedValue, composerStartRestartGroup, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: buf
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return nuf.r(modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0040  */
    /* JADX WARN: Code duplicated, block: B:24:0x0042  */
    /* JADX WARN: Code duplicated, block: B:27:0x004b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:29:0x0050  */
    /* JADX WARN: Code duplicated, block: B:32:0x0057  */
    /* JADX WARN: Code duplicated, block: B:35:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:36:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:41:? A[RETURN, SYNTHETIC] */
    public static final void v(final Function0 function0, Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Composer composerStartRestartGroup = composer.startRestartGroup(429525902);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i3 & 19) != 18) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i4 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(429525902, i3, -1, "com.yimaide.app.ui.workbench.ReleaseButton (WorkbenchTopBar.kt:101)");
                }
                composer2 = composerStartRestartGroup;
                modifier3 = modifier4;
                ButtonKt.OutlinedButton(function0, SizeKt.size-3ABfNKs(modifier4, Dp.constructor-impl(40.0f)), false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10.0f)), ButtonDefaults.INSTANCE.outlinedButtonColors-ro_MJ88(ColorKt.Color(4293916415L), ColorKt.Color(4280640491L), 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 54, 12), (ButtonElevation) null, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1.0f), ColorKt.Color(4282090230L)), PaddingKt.PaddingValues-0680j_4(Dp.constructor-impl(0.0f)), (MutableInteractionSource) null, sl2.a.e(), composer2, (i3 & 14) | 819462144, 292);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kuf
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return nuf.a(function0, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i3 & 19) != 18) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i4 != 0) {
                modifier4 = Modifier.Companion;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(429525902, i3, -1, "com.yimaide.app.ui.workbench.ReleaseButton (WorkbenchTopBar.kt:101)");
            }
            composer2 = composerStartRestartGroup;
            modifier3 = modifier4;
            ButtonKt.OutlinedButton(function0, SizeKt.size-3ABfNKs(modifier4, Dp.constructor-impl(40.0f)), false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10.0f)), ButtonDefaults.INSTANCE.outlinedButtonColors-ro_MJ88(ColorKt.Color(4293916415L), ColorKt.Color(4280640491L), 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 54, 12), (ButtonElevation) null, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1.0f), ColorKt.Color(4282090230L)), PaddingKt.PaddingValues-0680j_4(Dp.constructor-impl(0.0f)), (MutableInteractionSource) null, sl2.a.e(), composer2, (i3 & 14) | 819462144, 292);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: kuf
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return nuf.a(function0, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0040  */
    /* JADX WARN: Code duplicated, block: B:24:0x0042  */
    /* JADX WARN: Code duplicated, block: B:27:0x004b A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:28:0x004d  */
    /* JADX WARN: Code duplicated, block: B:29:0x0050  */
    /* JADX WARN: Code duplicated, block: B:32:0x0057  */
    /* JADX WARN: Code duplicated, block: B:35:0x00d4  */
    /* JADX WARN: Code duplicated, block: B:36:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:39:0x00e4  */
    /* JADX WARN: Code duplicated, block: B:41:? A[RETURN, SYNTHETIC] */
    public static final void w(final Function0 function0, Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1664529526);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i4 = i2 & 2;
        if (i4 == 0) {
            if ((i & 48) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            if ((i3 & 19) != 18) {
                z = true;
            } else {
                z = false;
            }
            if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
                if (i4 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-1664529526, i3, -1, "com.yimaide.app.ui.workbench.RunButton (WorkbenchTopBar.kt:242)");
                }
                composer2 = composerStartRestartGroup;
                modifier3 = modifier4;
                ButtonKt.OutlinedButton(function0, SizeKt.size-3ABfNKs(modifier4, Dp.constructor-impl(40.0f)), false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10.0f)), ButtonDefaults.INSTANCE.outlinedButtonColors-ro_MJ88(ColorKt.Color(4293983732L), ColorKt.Color(4279673674L), 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 54, 12), (ButtonElevation) null, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1.0f), ColorKt.Color(4280468830L)), PaddingKt.PaddingValues-0680j_4(Dp.constructor-impl(0.0f)), (MutableInteractionSource) null, sl2.a.f(), composer2, (i3 & 14) | 819462144, 292);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: juf
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return nuf.l(function0, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        modifier2 = modifier;
        if ((i3 & 19) != 18) {
            z = true;
        } else {
            z = false;
        }
        if (composerStartRestartGroup.shouldExecute(z, i3 & 1)) {
            if (i4 != 0) {
                modifier4 = Modifier.Companion;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1664529526, i3, -1, "com.yimaide.app.ui.workbench.RunButton (WorkbenchTopBar.kt:242)");
            }
            composer2 = composerStartRestartGroup;
            modifier3 = modifier4;
            ButtonKt.OutlinedButton(function0, SizeKt.size-3ABfNKs(modifier4, Dp.constructor-impl(40.0f)), false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10.0f)), ButtonDefaults.INSTANCE.outlinedButtonColors-ro_MJ88(ColorKt.Color(4293983732L), ColorKt.Color(4279673674L), 0L, 0L, composer2, (ButtonDefaults.$stable << 12) | 54, 12), (ButtonElevation) null, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1.0f), ColorKt.Color(4280468830L)), PaddingKt.PaddingValues-0680j_4(Dp.constructor-impl(0.0f)), (MutableInteractionSource) null, sl2.a.f(), composer2, (i3 & 14) | 819462144, 292);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: juf
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return nuf.l(function0, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void x(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-392835385);
        int i4 = i2 & 1;
        if (i4 != 0) {
            i3 = i | 6;
        } else if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-392835385, i3, -1, "com.yimaide.app.ui.workbench.SearchToolbarIcon (WorkbenchTopBar.kt:167)");
            }
            Modifier modifier2 = SizeKt.size-3ABfNKs(modifier, Dp.constructor-impl(18.0f));
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: cuf
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return nuf.c((DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifier2, (Function1) objRememberedValue, composerStartRestartGroup, 48);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: duf
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return nuf.i(modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void y(final Function0 function0, final Function2 function2, Composer composer, final int i) {
        int i2;
        Composer composerStartRestartGroup = composer.startRestartGroup(-910112118);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changedInstance(function0) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 32 : 16;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 19) != 18, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-910112118, i2, -1, "com.yimaide.app.ui.workbench.ToolbarActionButton (WorkbenchTopBar.kt:150)");
            }
            ButtonKt.OutlinedButton(function0, SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(40.0f)), false, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(10.0f)), ButtonDefaults.INSTANCE.outlinedButtonColors-ro_MJ88(ColorKt.Color(4294967295L), ColorKt.Color(4281549141L), 0L, 0L, composerStartRestartGroup, (ButtonDefaults.$stable << 12) | 54, 12), (ButtonElevation) null, BorderStrokeKt.BorderStroke-cXLIe8U(Dp.constructor-impl(1.0f), ColorKt.Color(4293060848L)), PaddingKt.PaddingValues-0680j_4(Dp.constructor-impl(0.0f)), (MutableInteractionSource) null, ComposableLambdaKt.rememberComposableLambda(463673212, true, new Function3() { // from class: wtf
                public final Object invoke(Object obj, Object obj2, Object obj3) {
                    return nuf.b(function2, (RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, (i2 & 14) | 819462192, 292);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: xtf
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return nuf.h(function0, function2, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Code duplicated, block: B:101:0x013d  */
    /* JADX WARN: Code duplicated, block: B:103:0x0141  */
    /* JADX WARN: Code duplicated, block: B:105:0x014d  */
    /* JADX WARN: Code duplicated, block: B:107:0x015a  */
    /* JADX WARN: Code duplicated, block: B:110:0x0162  */
    /* JADX WARN: Code duplicated, block: B:113:0x01cf  */
    /* JADX WARN: Code duplicated, block: B:116:0x01db  */
    /* JADX WARN: Code duplicated, block: B:117:0x01df  */
    /* JADX WARN: Code duplicated, block: B:120:0x0229  */
    /* JADX WARN: Code duplicated, block: B:123:0x0276  */
    /* JADX WARN: Code duplicated, block: B:126:0x0282  */
    /* JADX WARN: Code duplicated, block: B:127:0x0286  */
    /* JADX WARN: Code duplicated, block: B:130:0x02bf  */
    /* JADX WARN: Code duplicated, block: B:131:0x02d5  */
    /* JADX WARN: Code duplicated, block: B:134:0x0321  */
    /* JADX WARN: Code duplicated, block: B:135:0x0336  */
    /* JADX WARN: Code duplicated, block: B:138:0x03d7  */
    /* JADX WARN: Code duplicated, block: B:140:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:143:0x03f3  */
    /* JADX WARN: Code duplicated, block: B:145:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x0052  */
    /* JADX WARN: Code duplicated, block: B:25:0x0058  */
    /* JADX WARN: Code duplicated, block: B:26:0x005b  */
    /* JADX WARN: Code duplicated, block: B:30:0x0062  */
    /* JADX WARN: Code duplicated, block: B:32:0x0068  */
    /* JADX WARN: Code duplicated, block: B:33:0x006b  */
    /* JADX WARN: Code duplicated, block: B:37:0x0072  */
    /* JADX WARN: Code duplicated, block: B:39:0x0078  */
    /* JADX WARN: Code duplicated, block: B:40:0x007b  */
    /* JADX WARN: Code duplicated, block: B:44:0x0083  */
    /* JADX WARN: Code duplicated, block: B:46:0x0089  */
    /* JADX WARN: Code duplicated, block: B:47:0x008c  */
    /* JADX WARN: Code duplicated, block: B:51:0x0095  */
    /* JADX WARN: Code duplicated, block: B:53:0x0099  */
    /* JADX WARN: Code duplicated, block: B:55:0x009c  */
    /* JADX WARN: Code duplicated, block: B:57:0x00a4  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a7  */
    /* JADX WARN: Code duplicated, block: B:62:0x00b0  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b5  */
    /* JADX WARN: Code duplicated, block: B:65:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:67:0x00c1  */
    /* JADX WARN: Code duplicated, block: B:68:0x00c4  */
    /* JADX WARN: Code duplicated, block: B:72:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:76:0x00d9  */
    /* JADX WARN: Code duplicated, block: B:78:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:79:0x00e6  */
    /* JADX WARN: Code duplicated, block: B:83:0x00f9  */
    /* JADX WARN: Code duplicated, block: B:84:0x00fb  */
    /* JADX WARN: Code duplicated, block: B:87:0x0104 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x0106  */
    /* JADX WARN: Code duplicated, block: B:89:0x0109  */
    /* JADX WARN: Code duplicated, block: B:91:0x010c  */
    /* JADX WARN: Code duplicated, block: B:93:0x0118  */
    /* JADX WARN: Code duplicated, block: B:95:0x0123  */
    /* JADX WARN: Code duplicated, block: B:97:0x0126  */
    /* JADX WARN: Code duplicated, block: B:99:0x0132  */
    /* JADX WARN: Instruction removed from duplicated block: B:130:0x02bf, please report this as an issue */
    /* JADX WARN: Instruction removed from duplicated block: B:134:0x0321, please report this as an issue */
    public static final void z(final String str, String str2, final String str3, final boolean z, final Function0 function0, final Function0 function1, Function0 function2, Function0 function3, Function0 function4, Composer composer, final int i, final int i2) {
        int i3;
        String str4;
        int i4;
        Function0 function5;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        boolean z2;
        final Function0 function6;
        final Function0 function7;
        Composer composer2;
        final String str5;
        final Function0 function8;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        String str6;
        Function0 function9;
        Function0 function10;
        Function0 function11;
        Function0 constructor;
        Object objRememberedValue;
        Function0 constructor2;
        String str7;
        String str8;
        Object objRememberedValue2;
        Object objRememberedValue3;
        Object objRememberedValue4;
        int i10;
        int i11;
        int i12;
        int i13;
        str.getClass();
        str3.getClass();
        function0.getClass();
        function1.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(1428524872);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        int i14 = i2 & 2;
        if (i14 == 0) {
            if ((i & 48) == 0) {
                str4 = str2;
                i3 |= composerStartRestartGroup.changed(str4) ? 32 : 16;
            }
            if ((i & KyberEngine.KyberPolyBytes) == 0) {
                if (composerStartRestartGroup.changed(str3)) {
                    i13 = 256;
                } else {
                    i13 = CodeEditor.FLAG_DRAW_SOFT_WRAP;
                }
                i3 |= i13;
            }
            if ((i & 3072) == 0) {
                if (composerStartRestartGroup.changed(z)) {
                    i12 = NewHope.SENDB_BYTES;
                } else {
                    i12 = NewHope.POLY_SIZE;
                }
                i3 |= i12;
            }
            if ((i & 24576) == 0) {
                if (composerStartRestartGroup.changedInstance(function0)) {
                    i11 = 16384;
                } else {
                    i11 = 8192;
                }
                i3 |= i11;
            }
            if ((196608 & i) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i10 = 131072;
                } else {
                    i10 = 65536;
                }
                i3 |= i10;
            }
            i4 = i2 & 64;
            if (i4 != 0) {
                if ((1572864 & i) == 0) {
                    function5 = function2;
                    if (composerStartRestartGroup.changedInstance(function5)) {
                        i5 = 1048576;
                    } else {
                        i5 = 524288;
                    }
                    i3 |= i5;
                }
                i6 = i2 & CodeEditor.FLAG_DRAW_SOFT_WRAP;
                if (i6 != 0) {
                    i3 |= 12582912;
                } else if ((i & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function3)) {
                        i7 = 8388608;
                    } else {
                        i7 = 4194304;
                    }
                    i3 |= i7;
                }
                i8 = i2 & 256;
                if (i8 != 0) {
                    if ((i & 100663296) == 0) {
                        if (composerStartRestartGroup.changedInstance(function4)) {
                            i9 = 67108864;
                        } else {
                            i9 = 33554432;
                        }
                        i3 |= i9;
                    }
                    if ((i3 & 38347923) != 38347922) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                        if (i14 != 0) {
                            str6 = "";
                        } else {
                            str6 = str4;
                        }
                        if (i4 != 0) {
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                                objRememberedValue4 = new Function0() { // from class: vtf
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return nuf.k();
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            function9 = (Function0) objRememberedValue4;
                        } else {
                            function9 = function5;
                        }
                        if (i6 != 0) {
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                                objRememberedValue3 = new Function0() { // from class: euf
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return nuf.j();
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            function10 = (Function0) objRememberedValue3;
                        } else {
                            function10 = function3;
                        }
                        if (i8 != 0) {
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                                objRememberedValue2 = new Function0() { // from class: fuf
                                    @Override // kotlin.jvm.functions.Function0
                                    public final Object invoke() {
                                        return nuf.p();
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            function11 = (Function0) objRememberedValue2;
                        } else {
                            function11 = function4;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(1428524872, i3, -1, "com.yimaide.app.ui.workbench.WorkbenchTopBar (WorkbenchTopBar.kt:42)");
                        }
                        Modifier.Companion companion = Modifier.Companion;
                        Modifier modifier = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(companion, 0.0f, 1, (Object) null)), ColorKt.Color(4294704382L), (Shape) null, 2, (Object) null), Dp.constructor-impl(18.0f), Dp.constructor-impl(12.0f));
                        Alignment.Companion companion2 = Alignment.Companion;
                        Alignment.Vertical centerVertically = companion2.getCenterVertically();
                        Arrangement arrangement = Arrangement.INSTANCE;
                        int i15 = i3;
                        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(arrangement.getStart(), centerVertically, composerStartRestartGroup, 48);
                        int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier);
                        ComposeUiNode.Companion companion3 = ComposeUiNode.Companion;
                        constructor = companion3.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composer3 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer3, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.set-impl(composer3, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer3, Integer.valueOf(iHashCode), companion3.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer3, companion3.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer3, modifierMaterializeModifier, companion3.getSetModifier());
                        Modifier modifierWeight$default = RowScope.weight$default(RowScopeInstance.INSTANCE, companion, 1.0f, false, 2, (Object) null);
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue == Composer.Companion.getEmpty()) {
                            objRememberedValue = new Function0() { // from class: guf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.n();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        Modifier modifier2 = ClickableKt.combinedClickable-hoGz1lA$default(modifierWeight$default, false, (String) null, (Role) null, (String) null, function11, (Function0) null, false, (MutableInteractionSource) null, (Function0) objRememberedValue, 239, (Object) null);
                        Function0 function12 = function11;
                        MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(arrangement.getTop(), companion2.getStart(), composerStartRestartGroup, 0);
                        int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                        CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                        Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
                        constructor2 = companion3.getConstructor();
                        if (composerStartRestartGroup.getApplier() == null) {
                            ComposablesKt.invalidApplier();
                        }
                        composerStartRestartGroup.startReusableNode();
                        if (composerStartRestartGroup.getInserting()) {
                            composerStartRestartGroup.createNode(constructor2);
                        } else {
                            composerStartRestartGroup.useNode();
                        }
                        Composer composer4 = Updater.constructor-impl(composerStartRestartGroup);
                        Updater.set-impl(composer4, measurePolicyColumnMeasurePolicy, companion3.getSetMeasurePolicy());
                        Updater.set-impl(composer4, currentCompositionLocalMap2, companion3.getSetResolvedCompositionLocals());
                        Updater.init-impl(composer4, Integer.valueOf(iHashCode2), companion3.getSetCompositeKeyHash());
                        Updater.reconcile-impl(composer4, companion3.getApplyOnDeactivatedNodeAssertion());
                        Updater.set-impl(composer4, modifierMaterializeModifier2, companion3.getSetModifier());
                        ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
                        if (str.length() > 5) {
                            str7 = StringsKt.take(str, 5) + "…";
                        } else {
                            str7 = str;
                        }
                        long sp = TextUnitKt.getSp(20);
                        FontWeight bold = FontWeight.Companion.getBold();
                        long jColor = ColorKt.Color(4279310375L);
                        TextOverflow.Companion companion4 = TextOverflow.Companion;
                        Function0 function13 = function10;
                        TextKt.Text-Nvy7gAk(str7, (Modifier) null, jColor, (TextAutoSize) null, sp, (FontStyle) null, bold, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion4.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 1597824, 24960, 241578);
                        if (StringsKt.isBlank(str6)) {
                            str8 = str3;
                        } else {
                            str8 = str6 + " · " + str3;
                        }
                        TextKt.Text-Nvy7gAk(str8, (Modifier) null, ColorKt.Color(4285231744L), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion4.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 24960, 24960, 241642);
                        composerStartRestartGroup.endNode();
                        y(function0, ComposableLambdaKt.rememberComposableLambda(102628205, true, new Function2() { // from class: huf
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return nuf.m(z, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i15 >> 12) & 14) | 48);
                        SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                        y(function1, sl2.a.d(), composerStartRestartGroup, ((i15 >> 15) & 14) | 48);
                        SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                        v(function13, null, composerStartRestartGroup, (i15 >> 21) & 14, 2);
                        SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                        w(function9, null, composerStartRestartGroup, (i15 >> 18) & 14, 2);
                        composerStartRestartGroup.endNode();
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        composer2 = composerStartRestartGroup;
                        function8 = function9;
                        function7 = function12;
                        function6 = function13;
                        str5 = str6;
                    } else {
                        composerStartRestartGroup.skipToGroupEnd();
                        function6 = function3;
                        function7 = function4;
                        composer2 = composerStartRestartGroup;
                        str5 = str4;
                        function8 = function5;
                    }
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iuf
                            @Override // kotlin.jvm.functions.Function2
                            public final Object invoke(Object obj, Object obj2) {
                                return nuf.d(str, str5, str3, z, function0, function1, function8, function6, function7, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i3 |= 100663296;
                if ((i3 & 38347923) != 38347922) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                    if (i14 != 0) {
                        str6 = "";
                    } else {
                        str6 = str4;
                    }
                    if (i4 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function0() { // from class: vtf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.k();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function9 = (Function0) objRememberedValue4;
                    } else {
                        function9 = function5;
                    }
                    if (i6 != 0) {
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: euf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.j();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        function10 = (Function0) objRememberedValue3;
                    } else {
                        function10 = function3;
                    }
                    if (i8 != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                            objRememberedValue2 = new Function0() { // from class: fuf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.p();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function11 = (Function0) objRememberedValue2;
                    } else {
                        function11 = function4;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1428524872, i3, -1, "com.yimaide.app.ui.workbench.WorkbenchTopBar (WorkbenchTopBar.kt:42)");
                    }
                    Modifier.Companion companion5 = Modifier.Companion;
                    Modifier modifier3 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(companion5, 0.0f, 1, (Object) null)), ColorKt.Color(4294704382L), (Shape) null, 2, (Object) null), Dp.constructor-impl(18.0f), Dp.constructor-impl(12.0f));
                    Alignment.Companion companion6 = Alignment.Companion;
                    Alignment.Vertical centerVertically2 = companion6.getCenterVertically();
                    Arrangement arrangement2 = Arrangement.INSTANCE;
                    int i16 = i3;
                    MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(arrangement2.getStart(), centerVertically2, composerStartRestartGroup, 48);
                    int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier3);
                    ComposeUiNode.Companion companion7 = ComposeUiNode.Companion;
                    constructor = companion7.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer5 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer5, measurePolicyRowMeasurePolicy2, companion7.getSetMeasurePolicy());
                    Updater.set-impl(composer5, currentCompositionLocalMap3, companion7.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer5, Integer.valueOf(iHashCode3), companion7.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer5, companion7.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer5, modifierMaterializeModifier3, companion7.getSetModifier());
                    Modifier modifierWeight$default2 = RowScope.weight$default(RowScopeInstance.INSTANCE, companion5, 1.0f, false, 2, (Object) null);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: guf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.n();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifier4 = ClickableKt.combinedClickable-hoGz1lA$default(modifierWeight$default2, false, (String) null, (Role) null, (String) null, function11, (Function0) null, false, (MutableInteractionSource) null, (Function0) objRememberedValue, 239, (Object) null);
                    Function0 function14 = function11;
                    MeasurePolicy measurePolicyColumnMeasurePolicy2 = ColumnKt.columnMeasurePolicy(arrangement2.getTop(), companion6.getStart(), composerStartRestartGroup, 0);
                    int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier4);
                    constructor2 = companion7.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer6 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer6, measurePolicyColumnMeasurePolicy2, companion7.getSetMeasurePolicy());
                    Updater.set-impl(composer6, currentCompositionLocalMap4, companion7.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer6, Integer.valueOf(iHashCode4), companion7.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer6, companion7.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer6, modifierMaterializeModifier4, companion7.getSetModifier());
                    ColumnScopeInstance columnScopeInstance2 = ColumnScopeInstance.INSTANCE;
                    if (str.length() > 5) {
                        str7 = StringsKt.take(str, 5) + "…";
                    } else {
                        str7 = str;
                    }
                    long sp2 = TextUnitKt.getSp(20);
                    FontWeight bold2 = FontWeight.Companion.getBold();
                    long jColor2 = ColorKt.Color(4279310375L);
                    TextOverflow.Companion companion8 = TextOverflow.Companion;
                    Function0 function15 = function10;
                    TextKt.Text-Nvy7gAk(str7, (Modifier) null, jColor2, (TextAutoSize) null, sp2, (FontStyle) null, bold2, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion8.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 1597824, 24960, 241578);
                    if (StringsKt.isBlank(str6)) {
                        str8 = str6 + " · " + str3;
                    } else {
                        str8 = str3;
                    }
                    TextKt.Text-Nvy7gAk(str8, (Modifier) null, ColorKt.Color(4285231744L), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion8.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 24960, 24960, 241642);
                    composerStartRestartGroup.endNode();
                    y(function0, ComposableLambdaKt.rememberComposableLambda(102628205, true, new Function2() { // from class: huf
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return nuf.m(z, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i16 >> 12) & 14) | 48);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion5, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                    y(function1, sl2.a.d(), composerStartRestartGroup, ((i16 >> 15) & 14) | 48);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion5, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                    v(function15, null, composerStartRestartGroup, (i16 >> 21) & 14, 2);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion5, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                    w(function9, null, composerStartRestartGroup, (i16 >> 18) & 14, 2);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2 = composerStartRestartGroup;
                    function8 = function9;
                    function7 = function14;
                    function6 = function15;
                    str5 = str6;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function6 = function3;
                    function7 = function4;
                    composer2 = composerStartRestartGroup;
                    str5 = str4;
                    function8 = function5;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iuf
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return nuf.d(str, str5, str3, z, function0, function1, function8, function6, function7, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 1572864;
            function5 = function2;
            i6 = i2 & CodeEditor.FLAG_DRAW_SOFT_WRAP;
            if (i6 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i7 = 8388608;
                } else {
                    i7 = 4194304;
                }
                i3 |= i7;
            }
            i8 = i2 & 256;
            if (i8 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i9 = 67108864;
                    } else {
                        i9 = 33554432;
                    }
                    i3 |= i9;
                }
                if ((i3 & 38347923) != 38347922) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                    if (i14 != 0) {
                        str6 = "";
                    } else {
                        str6 = str4;
                    }
                    if (i4 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function0() { // from class: vtf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.k();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function9 = (Function0) objRememberedValue4;
                    } else {
                        function9 = function5;
                    }
                    if (i6 != 0) {
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: euf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.j();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        function10 = (Function0) objRememberedValue3;
                    } else {
                        function10 = function3;
                    }
                    if (i8 != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                            objRememberedValue2 = new Function0() { // from class: fuf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.p();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function11 = (Function0) objRememberedValue2;
                    } else {
                        function11 = function4;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1428524872, i3, -1, "com.yimaide.app.ui.workbench.WorkbenchTopBar (WorkbenchTopBar.kt:42)");
                    }
                    Modifier.Companion companion9 = Modifier.Companion;
                    Modifier modifier5 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(companion9, 0.0f, 1, (Object) null)), ColorKt.Color(4294704382L), (Shape) null, 2, (Object) null), Dp.constructor-impl(18.0f), Dp.constructor-impl(12.0f));
                    Alignment.Companion companion10 = Alignment.Companion;
                    Alignment.Vertical centerVertically3 = companion10.getCenterVertically();
                    Arrangement arrangement3 = Arrangement.INSTANCE;
                    int i17 = i3;
                    MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(arrangement3.getStart(), centerVertically3, composerStartRestartGroup, 48);
                    int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap5 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier5);
                    ComposeUiNode.Companion companion11 = ComposeUiNode.Companion;
                    constructor = companion11.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer7 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer7, measurePolicyRowMeasurePolicy3, companion11.getSetMeasurePolicy());
                    Updater.set-impl(composer7, currentCompositionLocalMap5, companion11.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer7, Integer.valueOf(iHashCode5), companion11.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer7, companion11.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer7, modifierMaterializeModifier5, companion11.getSetModifier());
                    Modifier modifierWeight$default3 = RowScope.weight$default(RowScopeInstance.INSTANCE, companion9, 1.0f, false, 2, (Object) null);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: guf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.n();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifier6 = ClickableKt.combinedClickable-hoGz1lA$default(modifierWeight$default3, false, (String) null, (Role) null, (String) null, function11, (Function0) null, false, (MutableInteractionSource) null, (Function0) objRememberedValue, 239, (Object) null);
                    Function0 function16 = function11;
                    MeasurePolicy measurePolicyColumnMeasurePolicy3 = ColumnKt.columnMeasurePolicy(arrangement3.getTop(), companion10.getStart(), composerStartRestartGroup, 0);
                    int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap6 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier6);
                    constructor2 = companion11.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer8 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer8, measurePolicyColumnMeasurePolicy3, companion11.getSetMeasurePolicy());
                    Updater.set-impl(composer8, currentCompositionLocalMap6, companion11.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer8, Integer.valueOf(iHashCode6), companion11.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer8, companion11.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer8, modifierMaterializeModifier6, companion11.getSetModifier());
                    ColumnScopeInstance columnScopeInstance3 = ColumnScopeInstance.INSTANCE;
                    if (str.length() > 5) {
                        str7 = StringsKt.take(str, 5) + "…";
                    } else {
                        str7 = str;
                    }
                    long sp3 = TextUnitKt.getSp(20);
                    FontWeight bold3 = FontWeight.Companion.getBold();
                    long jColor3 = ColorKt.Color(4279310375L);
                    TextOverflow.Companion companion12 = TextOverflow.Companion;
                    Function0 function17 = function10;
                    TextKt.Text-Nvy7gAk(str7, (Modifier) null, jColor3, (TextAutoSize) null, sp3, (FontStyle) null, bold3, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion12.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 1597824, 24960, 241578);
                    if (StringsKt.isBlank(str6)) {
                        str8 = str6 + " · " + str3;
                    } else {
                        str8 = str3;
                    }
                    TextKt.Text-Nvy7gAk(str8, (Modifier) null, ColorKt.Color(4285231744L), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion12.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 24960, 24960, 241642);
                    composerStartRestartGroup.endNode();
                    y(function0, ComposableLambdaKt.rememberComposableLambda(102628205, true, new Function2() { // from class: huf
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return nuf.m(z, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i17 >> 12) & 14) | 48);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion9, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                    y(function1, sl2.a.d(), composerStartRestartGroup, ((i17 >> 15) & 14) | 48);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion9, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                    v(function17, null, composerStartRestartGroup, (i17 >> 21) & 14, 2);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion9, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                    w(function9, null, composerStartRestartGroup, (i17 >> 18) & 14, 2);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2 = composerStartRestartGroup;
                    function8 = function9;
                    function7 = function16;
                    function6 = function17;
                    str5 = str6;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function6 = function3;
                    function7 = function4;
                    composer2 = composerStartRestartGroup;
                    str5 = str4;
                    function8 = function5;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iuf
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return nuf.d(str, str5, str3, z, function0, function1, function8, function6, function7, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) != 38347922) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                if (i14 != 0) {
                    str6 = "";
                } else {
                    str6 = str4;
                }
                if (i4 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: vtf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.k();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function9 = (Function0) objRememberedValue4;
                } else {
                    function9 = function5;
                }
                if (i6 != 0) {
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: euf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.j();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    function10 = (Function0) objRememberedValue3;
                } else {
                    function10 = function3;
                }
                if (i8 != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: fuf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.p();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function11 = (Function0) objRememberedValue2;
                } else {
                    function11 = function4;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1428524872, i3, -1, "com.yimaide.app.ui.workbench.WorkbenchTopBar (WorkbenchTopBar.kt:42)");
                }
                Modifier.Companion companion13 = Modifier.Companion;
                Modifier modifier7 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(companion13, 0.0f, 1, (Object) null)), ColorKt.Color(4294704382L), (Shape) null, 2, (Object) null), Dp.constructor-impl(18.0f), Dp.constructor-impl(12.0f));
                Alignment.Companion companion14 = Alignment.Companion;
                Alignment.Vertical centerVertically4 = companion14.getCenterVertically();
                Arrangement arrangement4 = Arrangement.INSTANCE;
                int i18 = i3;
                MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(arrangement4.getStart(), centerVertically4, composerStartRestartGroup, 48);
                int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap7 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier7);
                ComposeUiNode.Companion companion15 = ComposeUiNode.Companion;
                constructor = companion15.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer9 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer9, measurePolicyRowMeasurePolicy4, companion15.getSetMeasurePolicy());
                Updater.set-impl(composer9, currentCompositionLocalMap7, companion15.getSetResolvedCompositionLocals());
                Updater.init-impl(composer9, Integer.valueOf(iHashCode7), companion15.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer9, companion15.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer9, modifierMaterializeModifier7, companion15.getSetModifier());
                Modifier modifierWeight$default4 = RowScope.weight$default(RowScopeInstance.INSTANCE, companion13, 1.0f, false, 2, (Object) null);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: guf
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return nuf.n();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifier8 = ClickableKt.combinedClickable-hoGz1lA$default(modifierWeight$default4, false, (String) null, (Role) null, (String) null, function11, (Function0) null, false, (MutableInteractionSource) null, (Function0) objRememberedValue, 239, (Object) null);
                Function0 function18 = function11;
                MeasurePolicy measurePolicyColumnMeasurePolicy4 = ColumnKt.columnMeasurePolicy(arrangement4.getTop(), companion14.getStart(), composerStartRestartGroup, 0);
                int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap8 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier8);
                constructor2 = companion15.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer10 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer10, measurePolicyColumnMeasurePolicy4, companion15.getSetMeasurePolicy());
                Updater.set-impl(composer10, currentCompositionLocalMap8, companion15.getSetResolvedCompositionLocals());
                Updater.init-impl(composer10, Integer.valueOf(iHashCode8), companion15.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer10, companion15.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer10, modifierMaterializeModifier8, companion15.getSetModifier());
                ColumnScopeInstance columnScopeInstance4 = ColumnScopeInstance.INSTANCE;
                if (str.length() > 5) {
                    str7 = StringsKt.take(str, 5) + "…";
                } else {
                    str7 = str;
                }
                long sp4 = TextUnitKt.getSp(20);
                FontWeight bold4 = FontWeight.Companion.getBold();
                long jColor4 = ColorKt.Color(4279310375L);
                TextOverflow.Companion companion16 = TextOverflow.Companion;
                Function0 function19 = function10;
                TextKt.Text-Nvy7gAk(str7, (Modifier) null, jColor4, (TextAutoSize) null, sp4, (FontStyle) null, bold4, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion16.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 1597824, 24960, 241578);
                if (StringsKt.isBlank(str6)) {
                    str8 = str6 + " · " + str3;
                } else {
                    str8 = str3;
                }
                TextKt.Text-Nvy7gAk(str8, (Modifier) null, ColorKt.Color(4285231744L), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion16.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 24960, 24960, 241642);
                composerStartRestartGroup.endNode();
                y(function0, ComposableLambdaKt.rememberComposableLambda(102628205, true, new Function2() { // from class: huf
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return nuf.m(z, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i18 >> 12) & 14) | 48);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion13, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                y(function1, sl2.a.d(), composerStartRestartGroup, ((i18 >> 15) & 14) | 48);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion13, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                v(function19, null, composerStartRestartGroup, (i18 >> 21) & 14, 2);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion13, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                w(function9, null, composerStartRestartGroup, (i18 >> 18) & 14, 2);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2 = composerStartRestartGroup;
                function8 = function9;
                function7 = function18;
                function6 = function19;
                str5 = str6;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function6 = function3;
                function7 = function4;
                composer2 = composerStartRestartGroup;
                str5 = str4;
                function8 = function5;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iuf
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return nuf.d(str, str5, str3, z, function0, function1, function8, function6, function7, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 48;
        str4 = str2;
        if ((i & KyberEngine.KyberPolyBytes) == 0) {
            if (composerStartRestartGroup.changed(str3)) {
                i13 = 256;
            } else {
                i13 = CodeEditor.FLAG_DRAW_SOFT_WRAP;
            }
            i3 |= i13;
        }
        if ((i & 3072) == 0) {
            if (composerStartRestartGroup.changed(z)) {
                i12 = NewHope.SENDB_BYTES;
            } else {
                i12 = NewHope.POLY_SIZE;
            }
            i3 |= i12;
        }
        if ((i & 24576) == 0) {
            if (composerStartRestartGroup.changedInstance(function0)) {
                i11 = 16384;
            } else {
                i11 = 8192;
            }
            i3 |= i11;
        }
        if ((196608 & i) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i10 = 131072;
            } else {
                i10 = 65536;
            }
            i3 |= i10;
        }
        i4 = i2 & 64;
        if (i4 != 0) {
            if ((1572864 & i) == 0) {
                function5 = function2;
                if (composerStartRestartGroup.changedInstance(function5)) {
                    i5 = 1048576;
                } else {
                    i5 = 524288;
                }
                i3 |= i5;
            }
            i6 = i2 & CodeEditor.FLAG_DRAW_SOFT_WRAP;
            if (i6 != 0) {
                i3 |= 12582912;
            } else if ((i & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function3)) {
                    i7 = 8388608;
                } else {
                    i7 = 4194304;
                }
                i3 |= i7;
            }
            i8 = i2 & 256;
            if (i8 != 0) {
                if ((i & 100663296) == 0) {
                    if (composerStartRestartGroup.changedInstance(function4)) {
                        i9 = 67108864;
                    } else {
                        i9 = 33554432;
                    }
                    i3 |= i9;
                }
                if ((i3 & 38347923) != 38347922) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                    if (i14 != 0) {
                        str6 = "";
                    } else {
                        str6 = str4;
                    }
                    if (i4 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function0() { // from class: vtf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.k();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function9 = (Function0) objRememberedValue4;
                    } else {
                        function9 = function5;
                    }
                    if (i6 != 0) {
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                            objRememberedValue3 = new Function0() { // from class: euf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.j();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        function10 = (Function0) objRememberedValue3;
                    } else {
                        function10 = function3;
                    }
                    if (i8 != 0) {
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                            objRememberedValue2 = new Function0() { // from class: fuf
                                @Override // kotlin.jvm.functions.Function0
                                public final Object invoke() {
                                    return nuf.p();
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        function11 = (Function0) objRememberedValue2;
                    } else {
                        function11 = function4;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(1428524872, i3, -1, "com.yimaide.app.ui.workbench.WorkbenchTopBar (WorkbenchTopBar.kt:42)");
                    }
                    Modifier.Companion companion17 = Modifier.Companion;
                    Modifier modifier9 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(companion17, 0.0f, 1, (Object) null)), ColorKt.Color(4294704382L), (Shape) null, 2, (Object) null), Dp.constructor-impl(18.0f), Dp.constructor-impl(12.0f));
                    Alignment.Companion companion18 = Alignment.Companion;
                    Alignment.Vertical centerVertically5 = companion18.getCenterVertically();
                    Arrangement arrangement5 = Arrangement.INSTANCE;
                    int i19 = i3;
                    MeasurePolicy measurePolicyRowMeasurePolicy5 = RowKt.rowMeasurePolicy(arrangement5.getStart(), centerVertically5, composerStartRestartGroup, 48);
                    int iHashCode9 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap9 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier9 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier9);
                    ComposeUiNode.Companion companion19 = ComposeUiNode.Companion;
                    constructor = companion19.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer11 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer11, measurePolicyRowMeasurePolicy5, companion19.getSetMeasurePolicy());
                    Updater.set-impl(composer11, currentCompositionLocalMap9, companion19.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer11, Integer.valueOf(iHashCode9), companion19.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer11, companion19.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer11, modifierMaterializeModifier9, companion19.getSetModifier());
                    Modifier modifierWeight$default5 = RowScope.weight$default(RowScopeInstance.INSTANCE, companion17, 1.0f, false, 2, (Object) null);
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue == Composer.Companion.getEmpty()) {
                        objRememberedValue = new Function0() { // from class: guf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.n();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    Modifier modifier10 = ClickableKt.combinedClickable-hoGz1lA$default(modifierWeight$default5, false, (String) null, (Role) null, (String) null, function11, (Function0) null, false, (MutableInteractionSource) null, (Function0) objRememberedValue, 239, (Object) null);
                    Function0 function110 = function11;
                    MeasurePolicy measurePolicyColumnMeasurePolicy5 = ColumnKt.columnMeasurePolicy(arrangement5.getTop(), companion18.getStart(), composerStartRestartGroup, 0);
                    int iHashCode10 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                    CompositionLocalMap currentCompositionLocalMap10 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier10 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier10);
                    constructor2 = companion19.getConstructor();
                    if (composerStartRestartGroup.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composerStartRestartGroup.startReusableNode();
                    if (composerStartRestartGroup.getInserting()) {
                        composerStartRestartGroup.createNode(constructor2);
                    } else {
                        composerStartRestartGroup.useNode();
                    }
                    Composer composer12 = Updater.constructor-impl(composerStartRestartGroup);
                    Updater.set-impl(composer12, measurePolicyColumnMeasurePolicy5, companion19.getSetMeasurePolicy());
                    Updater.set-impl(composer12, currentCompositionLocalMap10, companion19.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer12, Integer.valueOf(iHashCode10), companion19.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer12, companion19.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer12, modifierMaterializeModifier10, companion19.getSetModifier());
                    ColumnScopeInstance columnScopeInstance5 = ColumnScopeInstance.INSTANCE;
                    if (str.length() > 5) {
                        str7 = StringsKt.take(str, 5) + "…";
                    } else {
                        str7 = str;
                    }
                    long sp5 = TextUnitKt.getSp(20);
                    FontWeight bold5 = FontWeight.Companion.getBold();
                    long jColor5 = ColorKt.Color(4279310375L);
                    TextOverflow.Companion companion110 = TextOverflow.Companion;
                    Function0 function111 = function10;
                    TextKt.Text-Nvy7gAk(str7, (Modifier) null, jColor5, (TextAutoSize) null, sp5, (FontStyle) null, bold5, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion110.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 1597824, 24960, 241578);
                    if (StringsKt.isBlank(str6)) {
                        str8 = str6 + " · " + str3;
                    } else {
                        str8 = str3;
                    }
                    TextKt.Text-Nvy7gAk(str8, (Modifier) null, ColorKt.Color(4285231744L), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion110.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 24960, 24960, 241642);
                    composerStartRestartGroup.endNode();
                    y(function0, ComposableLambdaKt.rememberComposableLambda(102628205, true, new Function2() { // from class: huf
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return nuf.m(z, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i19 >> 12) & 14) | 48);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion17, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                    y(function1, sl2.a.d(), composerStartRestartGroup, ((i19 >> 15) & 14) | 48);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion17, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                    v(function111, null, composerStartRestartGroup, (i19 >> 21) & 14, 2);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion17, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                    w(function9, null, composerStartRestartGroup, (i19 >> 18) & 14, 2);
                    composerStartRestartGroup.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    composer2 = composerStartRestartGroup;
                    function8 = function9;
                    function7 = function110;
                    function6 = function111;
                    str5 = str6;
                } else {
                    composerStartRestartGroup.skipToGroupEnd();
                    function6 = function3;
                    function7 = function4;
                    composer2 = composerStartRestartGroup;
                    str5 = str4;
                    function8 = function5;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iuf
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return nuf.d(str, str5, str3, z, function0, function1, function8, function6, function7, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i3 |= 100663296;
            if ((i3 & 38347923) != 38347922) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                if (i14 != 0) {
                    str6 = "";
                } else {
                    str6 = str4;
                }
                if (i4 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: vtf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.k();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function9 = (Function0) objRememberedValue4;
                } else {
                    function9 = function5;
                }
                if (i6 != 0) {
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: euf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.j();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    function10 = (Function0) objRememberedValue3;
                } else {
                    function10 = function3;
                }
                if (i8 != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: fuf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.p();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function11 = (Function0) objRememberedValue2;
                } else {
                    function11 = function4;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1428524872, i3, -1, "com.yimaide.app.ui.workbench.WorkbenchTopBar (WorkbenchTopBar.kt:42)");
                }
                Modifier.Companion companion111 = Modifier.Companion;
                Modifier modifier11 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(companion111, 0.0f, 1, (Object) null)), ColorKt.Color(4294704382L), (Shape) null, 2, (Object) null), Dp.constructor-impl(18.0f), Dp.constructor-impl(12.0f));
                Alignment.Companion companion112 = Alignment.Companion;
                Alignment.Vertical centerVertically6 = companion112.getCenterVertically();
                Arrangement arrangement6 = Arrangement.INSTANCE;
                int i110 = i3;
                MeasurePolicy measurePolicyRowMeasurePolicy6 = RowKt.rowMeasurePolicy(arrangement6.getStart(), centerVertically6, composerStartRestartGroup, 48);
                int iHashCode11 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap11 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier11 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier11);
                ComposeUiNode.Companion companion113 = ComposeUiNode.Companion;
                constructor = companion113.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer13 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer13, measurePolicyRowMeasurePolicy6, companion113.getSetMeasurePolicy());
                Updater.set-impl(composer13, currentCompositionLocalMap11, companion113.getSetResolvedCompositionLocals());
                Updater.init-impl(composer13, Integer.valueOf(iHashCode11), companion113.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer13, companion113.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer13, modifierMaterializeModifier11, companion113.getSetModifier());
                Modifier modifierWeight$default6 = RowScope.weight$default(RowScopeInstance.INSTANCE, companion111, 1.0f, false, 2, (Object) null);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: guf
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return nuf.n();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifier12 = ClickableKt.combinedClickable-hoGz1lA$default(modifierWeight$default6, false, (String) null, (Role) null, (String) null, function11, (Function0) null, false, (MutableInteractionSource) null, (Function0) objRememberedValue, 239, (Object) null);
                Function0 function112 = function11;
                MeasurePolicy measurePolicyColumnMeasurePolicy6 = ColumnKt.columnMeasurePolicy(arrangement6.getTop(), companion112.getStart(), composerStartRestartGroup, 0);
                int iHashCode12 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap12 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier12 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier12);
                constructor2 = companion113.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer14 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer14, measurePolicyColumnMeasurePolicy6, companion113.getSetMeasurePolicy());
                Updater.set-impl(composer14, currentCompositionLocalMap12, companion113.getSetResolvedCompositionLocals());
                Updater.init-impl(composer14, Integer.valueOf(iHashCode12), companion113.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer14, companion113.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer14, modifierMaterializeModifier12, companion113.getSetModifier());
                ColumnScopeInstance columnScopeInstance6 = ColumnScopeInstance.INSTANCE;
                if (str.length() > 5) {
                    str7 = StringsKt.take(str, 5) + "…";
                } else {
                    str7 = str;
                }
                long sp6 = TextUnitKt.getSp(20);
                FontWeight bold6 = FontWeight.Companion.getBold();
                long jColor6 = ColorKt.Color(4279310375L);
                TextOverflow.Companion companion114 = TextOverflow.Companion;
                Function0 function113 = function10;
                TextKt.Text-Nvy7gAk(str7, (Modifier) null, jColor6, (TextAutoSize) null, sp6, (FontStyle) null, bold6, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion114.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 1597824, 24960, 241578);
                if (StringsKt.isBlank(str6)) {
                    str8 = str6 + " · " + str3;
                } else {
                    str8 = str3;
                }
                TextKt.Text-Nvy7gAk(str8, (Modifier) null, ColorKt.Color(4285231744L), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion114.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 24960, 24960, 241642);
                composerStartRestartGroup.endNode();
                y(function0, ComposableLambdaKt.rememberComposableLambda(102628205, true, new Function2() { // from class: huf
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return nuf.m(z, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i110 >> 12) & 14) | 48);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion111, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                y(function1, sl2.a.d(), composerStartRestartGroup, ((i110 >> 15) & 14) | 48);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion111, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                v(function113, null, composerStartRestartGroup, (i110 >> 21) & 14, 2);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion111, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                w(function9, null, composerStartRestartGroup, (i110 >> 18) & 14, 2);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2 = composerStartRestartGroup;
                function8 = function9;
                function7 = function112;
                function6 = function113;
                str5 = str6;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function6 = function3;
                function7 = function4;
                composer2 = composerStartRestartGroup;
                str5 = str4;
                function8 = function5;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iuf
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return nuf.d(str, str5, str3, z, function0, function1, function8, function6, function7, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 1572864;
        function5 = function2;
        i6 = i2 & CodeEditor.FLAG_DRAW_SOFT_WRAP;
        if (i6 != 0) {
            i3 |= 12582912;
        } else if ((i & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function3)) {
                i7 = 8388608;
            } else {
                i7 = 4194304;
            }
            i3 |= i7;
        }
        i8 = i2 & 256;
        if (i8 != 0) {
            if ((i & 100663296) == 0) {
                if (composerStartRestartGroup.changedInstance(function4)) {
                    i9 = 67108864;
                } else {
                    i9 = 33554432;
                }
                i3 |= i9;
            }
            if ((i3 & 38347923) != 38347922) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
                if (i14 != 0) {
                    str6 = "";
                } else {
                    str6 = str4;
                }
                if (i4 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function0() { // from class: vtf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.k();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function9 = (Function0) objRememberedValue4;
                } else {
                    function9 = function5;
                }
                if (i6 != 0) {
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                        objRememberedValue3 = new Function0() { // from class: euf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.j();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    function10 = (Function0) objRememberedValue3;
                } else {
                    function10 = function3;
                }
                if (i8 != 0) {
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                        objRememberedValue2 = new Function0() { // from class: fuf
                            @Override // kotlin.jvm.functions.Function0
                            public final Object invoke() {
                                return nuf.p();
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    function11 = (Function0) objRememberedValue2;
                } else {
                    function11 = function4;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(1428524872, i3, -1, "com.yimaide.app.ui.workbench.WorkbenchTopBar (WorkbenchTopBar.kt:42)");
                }
                Modifier.Companion companion115 = Modifier.Companion;
                Modifier modifier13 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(companion115, 0.0f, 1, (Object) null)), ColorKt.Color(4294704382L), (Shape) null, 2, (Object) null), Dp.constructor-impl(18.0f), Dp.constructor-impl(12.0f));
                Alignment.Companion companion116 = Alignment.Companion;
                Alignment.Vertical centerVertically7 = companion116.getCenterVertically();
                Arrangement arrangement7 = Arrangement.INSTANCE;
                int i111 = i3;
                MeasurePolicy measurePolicyRowMeasurePolicy7 = RowKt.rowMeasurePolicy(arrangement7.getStart(), centerVertically7, composerStartRestartGroup, 48);
                int iHashCode13 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap13 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier13 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier13);
                ComposeUiNode.Companion companion117 = ComposeUiNode.Companion;
                constructor = companion117.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer15 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer15, measurePolicyRowMeasurePolicy7, companion117.getSetMeasurePolicy());
                Updater.set-impl(composer15, currentCompositionLocalMap13, companion117.getSetResolvedCompositionLocals());
                Updater.init-impl(composer15, Integer.valueOf(iHashCode13), companion117.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer15, companion117.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer15, modifierMaterializeModifier13, companion117.getSetModifier());
                Modifier modifierWeight$default7 = RowScope.weight$default(RowScopeInstance.INSTANCE, companion115, 1.0f, false, 2, (Object) null);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function0() { // from class: guf
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return nuf.n();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                Modifier modifier14 = ClickableKt.combinedClickable-hoGz1lA$default(modifierWeight$default7, false, (String) null, (Role) null, (String) null, function11, (Function0) null, false, (MutableInteractionSource) null, (Function0) objRememberedValue, 239, (Object) null);
                Function0 function114 = function11;
                MeasurePolicy measurePolicyColumnMeasurePolicy7 = ColumnKt.columnMeasurePolicy(arrangement7.getTop(), companion116.getStart(), composerStartRestartGroup, 0);
                int iHashCode14 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap14 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier14 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier14);
                constructor2 = companion117.getConstructor();
                if (composerStartRestartGroup.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composerStartRestartGroup.startReusableNode();
                if (composerStartRestartGroup.getInserting()) {
                    composerStartRestartGroup.createNode(constructor2);
                } else {
                    composerStartRestartGroup.useNode();
                }
                Composer composer16 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer16, measurePolicyColumnMeasurePolicy7, companion117.getSetMeasurePolicy());
                Updater.set-impl(composer16, currentCompositionLocalMap14, companion117.getSetResolvedCompositionLocals());
                Updater.init-impl(composer16, Integer.valueOf(iHashCode14), companion117.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer16, companion117.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer16, modifierMaterializeModifier14, companion117.getSetModifier());
                ColumnScopeInstance columnScopeInstance7 = ColumnScopeInstance.INSTANCE;
                if (str.length() > 5) {
                    str7 = StringsKt.take(str, 5) + "…";
                } else {
                    str7 = str;
                }
                long sp7 = TextUnitKt.getSp(20);
                FontWeight bold7 = FontWeight.Companion.getBold();
                long jColor7 = ColorKt.Color(4279310375L);
                TextOverflow.Companion companion118 = TextOverflow.Companion;
                Function0 function115 = function10;
                TextKt.Text-Nvy7gAk(str7, (Modifier) null, jColor7, (TextAutoSize) null, sp7, (FontStyle) null, bold7, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion118.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 1597824, 24960, 241578);
                if (StringsKt.isBlank(str6)) {
                    str8 = str6 + " · " + str3;
                } else {
                    str8 = str3;
                }
                TextKt.Text-Nvy7gAk(str8, (Modifier) null, ColorKt.Color(4285231744L), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion118.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 24960, 24960, 241642);
                composerStartRestartGroup.endNode();
                y(function0, ComposableLambdaKt.rememberComposableLambda(102628205, true, new Function2() { // from class: huf
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return nuf.m(z, (Composer) obj, ((Integer) obj2).intValue());
                    }
                }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i111 >> 12) & 14) | 48);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion115, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                y(function1, sl2.a.d(), composerStartRestartGroup, ((i111 >> 15) & 14) | 48);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion115, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                v(function115, null, composerStartRestartGroup, (i111 >> 21) & 14, 2);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion115, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
                w(function9, null, composerStartRestartGroup, (i111 >> 18) & 14, 2);
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                composer2 = composerStartRestartGroup;
                function8 = function9;
                function7 = function114;
                function6 = function115;
                str5 = str6;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                function6 = function3;
                function7 = function4;
                composer2 = composerStartRestartGroup;
                str5 = str4;
                function8 = function5;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iuf
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return nuf.d(str, str5, str3, z, function0, function1, function8, function6, function7, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 100663296;
        if ((i3 & 38347923) != 38347922) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i3 & 1)) {
            if (i14 != 0) {
                str6 = "";
            } else {
                str6 = str4;
            }
            if (i4 != 0) {
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                    objRememberedValue4 = new Function0() { // from class: vtf
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return nuf.k();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                function9 = (Function0) objRememberedValue4;
            } else {
                function9 = function5;
            }
            if (i6 != 0) {
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue3 == Composer.Companion.getEmpty()) {
                    objRememberedValue3 = new Function0() { // from class: euf
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return nuf.j();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                function10 = (Function0) objRememberedValue3;
            } else {
                function10 = function3;
            }
            if (i8 != 0) {
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue2 == Composer.Companion.getEmpty()) {
                    objRememberedValue2 = new Function0() { // from class: fuf
                        @Override // kotlin.jvm.functions.Function0
                        public final Object invoke() {
                            return nuf.p();
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                function11 = (Function0) objRememberedValue2;
            } else {
                function11 = function4;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1428524872, i3, -1, "com.yimaide.app.ui.workbench.WorkbenchTopBar (WorkbenchTopBar.kt:42)");
            }
            Modifier.Companion companion119 = Modifier.Companion;
            Modifier modifier15 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU$default(WindowInsetsPadding_androidKt.statusBarsPadding(SizeKt.fillMaxWidth$default(companion119, 0.0f, 1, (Object) null)), ColorKt.Color(4294704382L), (Shape) null, 2, (Object) null), Dp.constructor-impl(18.0f), Dp.constructor-impl(12.0f));
            Alignment.Companion companion1110 = Alignment.Companion;
            Alignment.Vertical centerVertically8 = companion1110.getCenterVertically();
            Arrangement arrangement8 = Arrangement.INSTANCE;
            int i112 = i3;
            MeasurePolicy measurePolicyRowMeasurePolicy8 = RowKt.rowMeasurePolicy(arrangement8.getStart(), centerVertically8, composerStartRestartGroup, 48);
            int iHashCode15 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap15 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier15 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier15);
            ComposeUiNode.Companion companion1111 = ComposeUiNode.Companion;
            constructor = companion1111.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer17 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer17, measurePolicyRowMeasurePolicy8, companion1111.getSetMeasurePolicy());
            Updater.set-impl(composer17, currentCompositionLocalMap15, companion1111.getSetResolvedCompositionLocals());
            Updater.init-impl(composer17, Integer.valueOf(iHashCode15), companion1111.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer17, companion1111.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer17, modifierMaterializeModifier15, companion1111.getSetModifier());
            Modifier modifierWeight$default8 = RowScope.weight$default(RowScopeInstance.INSTANCE, companion119, 1.0f, false, 2, (Object) null);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: guf
                    @Override // kotlin.jvm.functions.Function0
                    public final Object invoke() {
                        return nuf.n();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            Modifier modifier16 = ClickableKt.combinedClickable-hoGz1lA$default(modifierWeight$default8, false, (String) null, (Role) null, (String) null, function11, (Function0) null, false, (MutableInteractionSource) null, (Function0) objRememberedValue, 239, (Object) null);
            Function0 function116 = function11;
            MeasurePolicy measurePolicyColumnMeasurePolicy8 = ColumnKt.columnMeasurePolicy(arrangement8.getTop(), companion1110.getStart(), composerStartRestartGroup, 0);
            int iHashCode16 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap16 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier16 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier16);
            constructor2 = companion1111.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor2);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer18 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer18, measurePolicyColumnMeasurePolicy8, companion1111.getSetMeasurePolicy());
            Updater.set-impl(composer18, currentCompositionLocalMap16, companion1111.getSetResolvedCompositionLocals());
            Updater.init-impl(composer18, Integer.valueOf(iHashCode16), companion1111.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer18, companion1111.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer18, modifierMaterializeModifier16, companion1111.getSetModifier());
            ColumnScopeInstance columnScopeInstance8 = ColumnScopeInstance.INSTANCE;
            if (str.length() > 5) {
                str7 = StringsKt.take(str, 5) + "…";
            } else {
                str7 = str;
            }
            long sp8 = TextUnitKt.getSp(20);
            FontWeight bold8 = FontWeight.Companion.getBold();
            long jColor8 = ColorKt.Color(4279310375L);
            TextOverflow.Companion companion1112 = TextOverflow.Companion;
            Function0 function117 = function10;
            TextKt.Text-Nvy7gAk(str7, (Modifier) null, jColor8, (TextAutoSize) null, sp8, (FontStyle) null, bold8, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion1112.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 1597824, 24960, 241578);
            if (StringsKt.isBlank(str6)) {
                str8 = str6 + " · " + str3;
            } else {
                str8 = str3;
            }
            TextKt.Text-Nvy7gAk(str8, (Modifier) null, ColorKt.Color(4285231744L), (TextAutoSize) null, TextUnitKt.getSp(12), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, companion1112.getEllipsis-gIe3tQ8(), false, 1, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, 24960, 24960, 241642);
            composerStartRestartGroup.endNode();
            y(function0, ComposableLambdaKt.rememberComposableLambda(102628205, true, new Function2() { // from class: huf
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return nuf.m(z, (Composer) obj, ((Integer) obj2).intValue());
                }
            }, composerStartRestartGroup, 54), composerStartRestartGroup, ((i112 >> 12) & 14) | 48);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion119, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
            y(function1, sl2.a.d(), composerStartRestartGroup, ((i112 >> 15) & 14) | 48);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion119, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
            v(function117, null, composerStartRestartGroup, (i112 >> 21) & 14, 2);
            SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion119, Dp.constructor-impl(8.0f)), composerStartRestartGroup, 6);
            w(function9, null, composerStartRestartGroup, (i112 >> 18) & 14, 2);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            composer2 = composerStartRestartGroup;
            function8 = function9;
            function7 = function116;
            function6 = function117;
            str5 = str6;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            function6 = function3;
            function7 = function4;
            composer2 = composerStartRestartGroup;
            str5 = str4;
            function8 = function5;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: iuf
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return nuf.d(str, str5, str3, z, function0, function1, function8, function6, function7, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
