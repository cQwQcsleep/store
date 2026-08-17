package defpackage;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.CanvasKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.material.icons.Icons;
import androidx.compose.material.icons.filled.FolderKt;
import androidx.compose.material.icons.filled.FolderOpenKt;
import androidx.compose.material.icons.filled.SettingsKt;
import androidx.compose.material3.IconKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Size;
import androidx.compose.ui.graphics.AndroidPath_androidKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorFilter;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Path;
import androidx.compose.ui.graphics.PathEffect;
import androidx.compose.ui.graphics.StrokeCap;
import androidx.compose.ui.graphics.drawscope.DrawContext;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.graphics.drawscope.Stroke;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.util.Locale;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.bouncycastle.pqc.crypto.crystals.kyber.KyberEngine;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class kr4 {
    public static Unit a(boolean z, Modifier modifier, int i, int i2, Composer composer, int i3) {
        k(z, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit b(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        m(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit c(String str, long j, Modifier modifier, int i, int i2, Composer composer, int i3) {
        i(str, j, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit d(String str, Modifier modifier, int i, int i2, Composer composer, int i3) {
        n(str, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit e(Modifier modifier, int i, int i2, Composer composer, int i3) {
        j(modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit f(DrawScope drawScope) {
        drawScope.getClass();
        float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L));
        Path Path = AndroidPath_androidKt.Path();
        Path.moveTo(0.0f, 0.0f);
        float f = fIntBitsToFloat * 0.6f;
        Path.lineTo(f, 0.0f);
        float f2 = fIntBitsToFloat2 * 0.3f;
        Path.lineTo(fIntBitsToFloat, f2);
        Path.lineTo(fIntBitsToFloat, fIntBitsToFloat2);
        Path.lineTo(0.0f, fIntBitsToFloat2);
        Path.close();
        DrawScope.drawPath-LG529CI$default(drawScope, Path, ColorKt.Color(4294047225L), 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 60, (Object) null);
        Path Path2 = AndroidPath_androidKt.Path();
        Path2.moveTo(f, 0.0f);
        Path2.lineTo(f, f2);
        Path2.lineTo(fIntBitsToFloat, f2);
        Path2.close();
        DrawScope.drawPath-LG529CI$default(drawScope, Path2, ColorKt.Color(4293060848L), 0.0f, (DrawStyle) null, (ColorFilter) null, 0, 60, (Object) null);
        Path Path3 = AndroidPath_androidKt.Path();
        Path3.moveTo(0.0f, 0.0f);
        Path3.lineTo(f, 0.0f);
        Path3.lineTo(fIntBitsToFloat, f2);
        Path3.lineTo(fIntBitsToFloat, fIntBitsToFloat2);
        Path3.lineTo(0.0f, fIntBitsToFloat2);
        Path3.close();
        DrawScope.drawPath-LG529CI$default(drawScope, Path3, ColorKt.Color(4291548641L), 0.0f, new Stroke(1.0f, 0.0f, 0, 0, (PathEffect) null, 30, (DefaultConstructorMarker) null), (ColorFilter) null, 0, 52, (Object) null);
        return Unit.INSTANCE;
    }

    public static Unit g(boolean z, Modifier modifier, long j, int i, int i2, Composer composer, int i3) {
        l(z, modifier, j, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit h(boolean z, DrawScope drawScope) throws Throwable {
        DrawContext drawContext;
        long j;
        drawScope.getClass();
        float f = Size.getMinDimension-impl(drawScope.getSize-NH-jbRc()) * 0.16f;
        float fIntBitsToFloat = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) * 0.28f;
        long j2 = Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) * 0.2f)) & 4294967295L) | (((long) Float.floatToRawIntBits(fIntBitsToFloat)) << 32));
        float fIntBitsToFloat2 = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) * 0.68f;
        long j3 = Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) * 0.5f)) & 4294967295L) | (((long) Float.floatToRawIntBits(fIntBitsToFloat2)) << 32));
        float fIntBitsToFloat3 = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) * 0.28f;
        long j4 = Offset.constructor-impl((((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) * 0.8f)) & 4294967295L) | (((long) Float.floatToRawIntBits(fIntBitsToFloat3)) << 32));
        float f2 = z ? 90.0f : 0.0f;
        float fIntBitsToFloat4 = Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() >> 32)) / 2.0f;
        long j5 = Offset.constructor-impl((4294967295L & ((long) Float.floatToRawIntBits(Float.intBitsToFloat((int) (drawScope.getSize-NH-jbRc() & 4294967295L)) / 2.0f))) | (((long) Float.floatToRawIntBits(fIntBitsToFloat4)) << 32));
        DrawContext drawContext2 = drawScope.getDrawContext();
        long j6 = drawContext2.getSize-NH-jbRc();
        drawContext2.getCanvas().save();
        try {
            drawContext2.getTransform().rotate-Uv8p0NA(f2, j5);
            long jColor = ColorKt.Color(4287931320L);
            StrokeCap.Companion companion = StrokeCap.Companion;
            try {
                DrawScope.drawLine-NGM6Ib0$default(drawScope, jColor, j2, j3, f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
                DrawScope.drawLine-NGM6Ib0$default(drawScope, ColorKt.Color(4287931320L), j3, j4, f, companion.getRound-KaPHkGw(), (PathEffect) null, 0.0f, (ColorFilter) null, 0, 480, (Object) null);
                drawContext2.getCanvas().restore();
                drawContext2.setSize-uvyYCjk(j6);
                return Unit.INSTANCE;
            } catch (Throwable th) {
                th = th;
                drawContext = drawContext2;
                j = j6;
                drawContext.getCanvas().restore();
                drawContext.setSize-uvyYCjk(j);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            drawContext = drawContext2;
            j = j6;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0054  */
    /* JADX WARN: Code duplicated, block: B:31:0x0056  */
    /* JADX WARN: Code duplicated, block: B:34:0x005f A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:35:0x0061  */
    /* JADX WARN: Code duplicated, block: B:36:0x0064  */
    /* JADX WARN: Code duplicated, block: B:39:0x006b  */
    /* JADX WARN: Code duplicated, block: B:42:0x00af  */
    /* JADX WARN: Code duplicated, block: B:45:0x00bb  */
    /* JADX WARN: Code duplicated, block: B:46:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:49:0x0137  */
    /* JADX WARN: Code duplicated, block: B:51:0x013c  */
    /* JADX WARN: Code duplicated, block: B:54:0x0148  */
    /* JADX WARN: Code duplicated, block: B:56:? A[RETURN, SYNTHETIC] */
    public static final void i(final String str, final long j, Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Modifier modifier2;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Function0 constructor;
        str.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(-184518394);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(j) ? 32 : 16;
        }
        int i4 = i2 & 4;
        if (i4 == 0) {
            if ((i & KyberEngine.KyberPolyBytes) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 256 : CodeEditor.FLAG_DRAW_SOFT_WRAP;
            }
            if ((i3 & 147) != 146) {
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
                    ComposerKt.traceEventStart(-184518394, i3, -1, "com.yimaide.app.ui.workbench.CodeFileIcon (FileTreeIcons.kt:81)");
                }
                Modifier modifier5 = BackgroundKt.background-bw27NRU(SizeKt.size-3ABfNKs(modifier4, Dp.constructor-impl(16.0f)), j, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(3.0f)));
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getCenter(), false);
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier5);
                ComposeUiNode.Companion companion = ComposeUiNode.Companion;
                constructor = companion.getConstructor();
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
                Updater.set-impl(composer3, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                Updater.set-impl(composer3, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer3, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer3, companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer3, modifierMaterializeModifier, companion.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                Modifier modifier6 = modifier4;
                composer2 = composerStartRestartGroup;
                TextKt.Text-Nvy7gAk(str, (Modifier) null, Color.Companion.getWhite-0d7_KjU(), (TextAutoSize) null, TextUnitKt.getSp(7), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(8), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer2, (i3 & 14) | 1597824, 48, 260010);
                composer2.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier6;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fr4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return kr4.c(str, j, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= KyberEngine.KyberPolyBytes;
        modifier2 = modifier;
        if ((i3 & 147) != 146) {
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
                ComposerKt.traceEventStart(-184518394, i3, -1, "com.yimaide.app.ui.workbench.CodeFileIcon (FileTreeIcons.kt:81)");
            }
            Modifier modifier7 = BackgroundKt.background-bw27NRU(SizeKt.size-3ABfNKs(modifier4, Dp.constructor-impl(16.0f)), j, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(3.0f)));
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getCenter(), false);
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier7);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            constructor = companion2.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer4 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer4, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer4, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer4, Integer.valueOf(iHashCode2), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer4, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer4, modifierMaterializeModifier2, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            Modifier modifier8 = modifier4;
            composer2 = composerStartRestartGroup;
            TextKt.Text-Nvy7gAk(str, (Modifier) null, Color.Companion.getWhite-0d7_KjU(), (TextAutoSize) null, TextUnitKt.getSp(7), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(8), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer2, (i3 & 14) | 1597824, 48, 260010);
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier8;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: fr4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return kr4.c(str, j, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void j(final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1078992931);
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
                ComposerKt.traceEventStart(-1078992931, i3, -1, "com.yimaide.app.ui.workbench.ConfigFileIcon (FileTreeIcons.kt:100)");
            }
            Modifier modifier2 = BackgroundKt.background-bw27NRU(SizeKt.size-3ABfNKs(modifier, Dp.constructor-impl(16.0f)), ColorKt.Color(4285231744L), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(3.0f)));
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getCenter(), false);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier2);
            ComposeUiNode.Companion companion = ComposeUiNode.Companion;
            Function0 constructor = companion.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
            Updater.set-impl(composer2, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, companion.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, modifierMaterializeModifier, companion.getSetModifier());
            BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
            IconKt.Icon-ww6aTOc(SettingsKt.getSettings(Icons.Filled.INSTANCE), "配置", SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(11.0f)), Color.Companion.getWhite-0d7_KjU(), composerStartRestartGroup, 3504, 0);
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: er4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return kr4.e(modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void k(final boolean z, final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-988627558);
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
                ComposerKt.traceEventStart(-988627558, i3, -1, "com.yimaide.app.ui.workbench.ExpandChevronIcon (FileTreeIcons.kt:39)");
            }
            Modifier modifier2 = SizeKt.size-3ABfNKs(modifier, Dp.constructor-impl(14.0f));
            boolean z2 = (i3 & 14) == 4;
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (z2 || objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: hr4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return kr4.h(z, (DrawScope) obj);
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ir4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return kr4.a(z, modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void l(final boolean z, Modifier modifier, long j, Composer composer, final int i, final int i2) {
        int i3;
        long j2;
        final long j3;
        Composer composerStartRestartGroup = composer.startRestartGroup(-1990356756);
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
        int i5 = i2 & 4;
        if (i5 != 0) {
            i3 |= KyberEngine.KyberPolyBytes;
            j2 = j;
        } else {
            j2 = j;
            if ((i & KyberEngine.KyberPolyBytes) == 0) {
                i3 |= composerStartRestartGroup.changed(j2) ? 256 : CodeEditor.FLAG_DRAW_SOFT_WRAP;
            }
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            if (i4 != 0) {
                modifier = Modifier.Companion;
            }
            long jColor = i5 != 0 ? ColorKt.Color(4292121902L) : j2;
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1990356756, i3, -1, "com.yimaide.app.ui.workbench.FileFolderIcon (FileTreeIcons.kt:29)");
            }
            Icons.Filled filled = Icons.Filled.INSTANCE;
            IconKt.Icon-ww6aTOc(z ? FolderOpenKt.getFolderOpen(filled) : FolderKt.getFolder(filled), z ? "展开" : "收起", SizeKt.size-3ABfNKs(modifier, Dp.constructor-impl(16.0f)), jColor, composerStartRestartGroup, (i3 << 3) & 7168, 0);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            j3 = jColor;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            j3 = j2;
        }
        final Modifier modifier2 = modifier;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: gr4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return kr4.g(z, modifier2, j3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:38:0x008c  */
    /* JADX WARN: Code duplicated, block: B:45:0x00d5  */
    /* JADX WARN: Code duplicated, block: B:46:0x00e2  */
    public static final void m(final String str, final Modifier modifier, Composer composer, final int i, final int i2) {
        int i3;
        str.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(-678483984);
        if ((i & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i;
        } else {
            i3 = i;
        }
        if (composerStartRestartGroup.shouldExecute((i3 & 3) != 2, i3 & 1)) {
            if ((i2 & 2) != 0) {
                modifier = Modifier.Companion;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-678483984, i3, -1, "com.yimaide.app.ui.workbench.FileIcon (FileTreeIcons.kt:68)");
            }
            String lowerCase = StringsKt.substringAfterLast(str, '.', "").toLowerCase(Locale.ROOT);
            lowerCase.getClass();
            switch (lowerCase) {
                case "kt":
                    composerStartRestartGroup.startReplaceGroup(811931240);
                    i("Kt", ColorKt.Color(4286534399L), null, composerStartRestartGroup, 54, 4);
                    composerStartRestartGroup.endReplaceGroup();
                    break;
                case "css":
                    composerStartRestartGroup.startReplaceGroup(811928649);
                    i("{ }", ColorKt.Color(4280700388L), null, composerStartRestartGroup, 54, 4);
                    composerStartRestartGroup.endReplaceGroup();
                    break;
                case "htm":
                    composerStartRestartGroup.startReplaceGroup(811926249);
                    i("</>", ColorKt.Color(4293152038L), null, composerStartRestartGroup, 54, 4);
                    composerStartRestartGroup.endReplaceGroup();
                    break;
                case "kts":
                    composerStartRestartGroup.startReplaceGroup(811931240);
                    i("Kt", ColorKt.Color(4286534399L), null, composerStartRestartGroup, 54, 4);
                    composerStartRestartGroup.endReplaceGroup();
                    break;
                case "html":
                    composerStartRestartGroup.startReplaceGroup(811926249);
                    i("</>", ColorKt.Color(4293152038L), null, composerStartRestartGroup, 54, 4);
                    composerStartRestartGroup.endReplaceGroup();
                    break;
                case "json":
                    composerStartRestartGroup.startReplaceGroup(811933600);
                    j(null, composerStartRestartGroup, 0, 1);
                    composerStartRestartGroup.endReplaceGroup();
                    break;
                default:
                    composerStartRestartGroup.startReplaceGroup(811934666);
                    n(lowerCase, null, composerStartRestartGroup, 0, 2);
                    composerStartRestartGroup.endReplaceGroup();
                    break;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composerStartRestartGroup.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: jr4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return kr4.b(str, modifier, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code duplicated, block: B:23:0x0045  */
    /* JADX WARN: Code duplicated, block: B:24:0x0047  */
    /* JADX WARN: Code duplicated, block: B:27:0x0050 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:28:0x0052  */
    /* JADX WARN: Code duplicated, block: B:29:0x0055  */
    /* JADX WARN: Code duplicated, block: B:32:0x005c  */
    /* JADX WARN: Code duplicated, block: B:36:0x006b  */
    /* JADX WARN: Code duplicated, block: B:39:0x0075  */
    /* JADX WARN: Code duplicated, block: B:42:0x007f  */
    /* JADX WARN: Code duplicated, block: B:43:0x009f  */
    /* JADX WARN: Code duplicated, block: B:46:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:49:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:52:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:55:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:58:0x00cc  */
    /* JADX WARN: Code duplicated, block: B:59:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:61:0x00f3  */
    /* JADX WARN: Code duplicated, block: B:62:0x011d  */
    /* JADX WARN: Code duplicated, block: B:65:0x018c  */
    /* JADX WARN: Code duplicated, block: B:68:0x0198  */
    /* JADX WARN: Code duplicated, block: B:69:0x019c  */
    /* JADX WARN: Code duplicated, block: B:72:0x01e3  */
    /* JADX WARN: Code duplicated, block: B:75:0x023b  */
    /* JADX WARN: Code duplicated, block: B:78:0x0247  */
    /* JADX WARN: Code duplicated, block: B:79:0x024b  */
    /* JADX WARN: Code duplicated, block: B:82:0x02bc  */
    /* JADX WARN: Code duplicated, block: B:83:0x02c0  */
    /* JADX WARN: Code duplicated, block: B:86:0x02cc  */
    /* JADX WARN: Code duplicated, block: B:88:? A[RETURN, SYNTHETIC] */
    public static final void n(final String str, Modifier modifier, Composer composer, final int i, final int i2) {
        Modifier modifier2;
        boolean z;
        Composer composer2;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        Triple triple;
        Function0 constructor;
        Object objRememberedValue;
        Function0 constructor2;
        str.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(1254020427);
        int i3 = (i & 6) == 0 ? (composerStartRestartGroup.changed(str) ? 4 : 2) | i : i;
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
                    ComposerKt.traceEventStart(1254020427, i3, -1, "com.yimaide.app.ui.workbench.GenericFileIcon (FileTreeIcons.kt:118)");
                }
                switch (str) {
                    case "js":
                        triple = new Triple("JS", Color.box-impl(ColorKt.Color(4293974863L)), Color.box-impl(ColorKt.Color(4281479984L)));
                        break;
                    case "gif":
                    case "jpg":
                    case "png":
                    case "svg":
                    case "jpeg":
                        triple = new Triple("IMG", Color.box-impl(ColorKt.Color(4279286145L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                        break;
                    case "json":
                        triple = new Triple("JSON", Color.box-impl(ColorKt.Color(4285231744L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                        break;
                    case "webp":
                        triple = new Triple("IMG", Color.box-impl(ColorKt.Color(4279286145L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                        break;
                    default:
                        String upperCase = str.toUpperCase(Locale.ROOT);
                        upperCase.getClass();
                        triple = new Triple(StringsKt.take(upperCase, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                        break;
                }
                String str2 = (String) triple.component1();
                long j = ((Color) triple.component2()).unbox-impl();
                long j2 = ((Color) triple.component3()).unbox-impl();
                Modifier modifier5 = SizeKt.size-VpY3zN4(modifier4, Dp.constructor-impl(16.0f), Dp.constructor-impl(16.0f));
                Alignment.Companion companion = Alignment.Companion;
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion.getTopStart(), false);
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier5);
                ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
                constructor = companion2.getConstructor();
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
                Updater.set-impl(composer3, measurePolicyMaybeCachedBoxMeasurePolicy, companion2.getSetMeasurePolicy());
                Updater.set-impl(composer3, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
                Updater.init-impl(composer3, Integer.valueOf(iHashCode), companion2.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer3, companion2.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer3, modifierMaterializeModifier, companion2.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                Modifier.Companion companion3 = Modifier.Companion;
                Modifier modifierFillMaxSize$default = SizeKt.fillMaxSize$default(companion3, 0.0f, 1, (Object) null);
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue == Composer.Companion.getEmpty()) {
                    objRememberedValue = new Function1() { // from class: cr4
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return kr4.f((DrawScope) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                CanvasKt.Canvas(modifierFillMaxSize$default, (Function1) objRememberedValue, composerStartRestartGroup, 54);
                Modifier modifier6 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU(boxScopeInstance.align(companion3, companion.getBottomCenter()), j, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(1.0f))), Dp.constructor-impl(1.5f), Dp.constructor-impl(0.5f));
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion.getCenter(), false);
                int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier6);
                constructor2 = companion2.getConstructor();
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
                Updater.set-impl(composer4, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
                Updater.set-impl(composer4, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
                Updater.init-impl(composer4, Integer.valueOf(iHashCode2), companion2.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer4, companion2.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer4, modifierMaterializeModifier2, companion2.getSetModifier());
                modifier3 = modifier4;
                composer2 = composerStartRestartGroup;
                TextKt.Text-Nvy7gAk(str2, (Modifier) null, j2, (TextAutoSize) null, TextUnitKt.getSp(5), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(6), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer2, 1597440, 48, 260010);
                composer2.endNode();
                composer2.endNode();
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
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dr4
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return kr4.d(str, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
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
                ComposerKt.traceEventStart(1254020427, i3, -1, "com.yimaide.app.ui.workbench.GenericFileIcon (FileTreeIcons.kt:118)");
            }
            switch (str) {
                case 3401:
                    if (str.equals("js")) {
                        triple = new Triple("JS", Color.box-impl(ColorKt.Color(4293974863L)), Color.box-impl(ColorKt.Color(4281479984L)));
                    } else {
                        String upperCase2 = str.toUpperCase(Locale.ROOT);
                        upperCase2.getClass();
                        triple = new Triple(StringsKt.take(upperCase2, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    }
                    break;
                case 102340:
                    if (str.equals("gif")) {
                        triple = new Triple("IMG", Color.box-impl(ColorKt.Color(4279286145L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    } else {
                        String upperCase3 = str.toUpperCase(Locale.ROOT);
                        upperCase3.getClass();
                        triple = new Triple(StringsKt.take(upperCase3, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    }
                    break;
                case 105441:
                    if (str.equals("jpg")) {
                        triple = new Triple("IMG", Color.box-impl(ColorKt.Color(4279286145L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    } else {
                        String upperCase4 = str.toUpperCase(Locale.ROOT);
                        upperCase4.getClass();
                        triple = new Triple(StringsKt.take(upperCase4, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    }
                    break;
                case 111145:
                    if (str.equals("png")) {
                        triple = new Triple("IMG", Color.box-impl(ColorKt.Color(4279286145L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    } else {
                        String upperCase5 = str.toUpperCase(Locale.ROOT);
                        upperCase5.getClass();
                        triple = new Triple(StringsKt.take(upperCase5, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    }
                    break;
                case 114276:
                    if (str.equals("svg")) {
                        triple = new Triple("IMG", Color.box-impl(ColorKt.Color(4279286145L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    } else {
                        String upperCase6 = str.toUpperCase(Locale.ROOT);
                        upperCase6.getClass();
                        triple = new Triple(StringsKt.take(upperCase6, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    }
                    break;
                case 3268712:
                    if (str.equals("jpeg")) {
                        triple = new Triple("IMG", Color.box-impl(ColorKt.Color(4279286145L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    } else {
                        String upperCase7 = str.toUpperCase(Locale.ROOT);
                        upperCase7.getClass();
                        triple = new Triple(StringsKt.take(upperCase7, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    }
                    break;
                case 3271912:
                    if (str.equals("json")) {
                        triple = new Triple("JSON", Color.box-impl(ColorKt.Color(4285231744L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    } else {
                        String upperCase8 = str.toUpperCase(Locale.ROOT);
                        upperCase8.getClass();
                        triple = new Triple(StringsKt.take(upperCase8, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    }
                    break;
                case 3645340:
                    if (str.equals("webp")) {
                        triple = new Triple("IMG", Color.box-impl(ColorKt.Color(4279286145L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    } else {
                        String upperCase9 = str.toUpperCase(Locale.ROOT);
                        upperCase9.getClass();
                        triple = new Triple(StringsKt.take(upperCase9, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    }
                    break;
                default:
                    String upperCase10 = str.toUpperCase(Locale.ROOT);
                    upperCase10.getClass();
                    triple = new Triple(StringsKt.take(upperCase10, 4), Color.box-impl(ColorKt.Color(4287931320L)), Color.box-impl(Color.Companion.getWhite-0d7_KjU()));
                    break;
            }
            String str3 = (String) triple.component1();
            long j3 = ((Color) triple.component2()).unbox-impl();
            long j4 = ((Color) triple.component3()).unbox-impl();
            Modifier modifier7 = SizeKt.size-VpY3zN4(modifier4, Dp.constructor-impl(16.0f), Dp.constructor-impl(16.0f));
            Alignment.Companion companion4 = Alignment.Companion;
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(companion4.getTopStart(), false);
            int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap3 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier7);
            ComposeUiNode.Companion companion5 = ComposeUiNode.Companion;
            constructor = companion5.getConstructor();
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
            Updater.set-impl(composer5, measurePolicyMaybeCachedBoxMeasurePolicy3, companion5.getSetMeasurePolicy());
            Updater.set-impl(composer5, currentCompositionLocalMap3, companion5.getSetResolvedCompositionLocals());
            Updater.init-impl(composer5, Integer.valueOf(iHashCode3), companion5.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer5, companion5.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer5, modifierMaterializeModifier3, companion5.getSetModifier());
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            Modifier.Companion companion6 = Modifier.Companion;
            Modifier modifierFillMaxSize$default2 = SizeKt.fillMaxSize$default(companion6, 0.0f, 1, (Object) null);
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (objRememberedValue == Composer.Companion.getEmpty()) {
                objRememberedValue = new Function1() { // from class: cr4
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return kr4.f((DrawScope) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            CanvasKt.Canvas(modifierFillMaxSize$default2, (Function1) objRememberedValue, composerStartRestartGroup, 54);
            Modifier modifier8 = PaddingKt.padding-VpY3zN4(BackgroundKt.background-bw27NRU(boxScopeInstance2.align(companion6, companion4.getBottomCenter()), j3, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(1.0f))), Dp.constructor-impl(1.5f), Dp.constructor-impl(0.5f));
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(companion4.getCenter(), false);
            int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap4 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifier8);
            constructor2 = companion5.getConstructor();
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
            Updater.set-impl(composer6, measurePolicyMaybeCachedBoxMeasurePolicy4, companion5.getSetMeasurePolicy());
            Updater.set-impl(composer6, currentCompositionLocalMap4, companion5.getSetResolvedCompositionLocals());
            Updater.init-impl(composer6, Integer.valueOf(iHashCode4), companion5.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer6, companion5.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer6, modifierMaterializeModifier4, companion5.getSetModifier());
            modifier3 = modifier4;
            composer2 = composerStartRestartGroup;
            TextKt.Text-Nvy7gAk(str3, (Modifier) null, j4, (TextAutoSize) null, TextUnitKt.getSp(5), (FontStyle) null, FontWeight.Companion.getBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(6), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer2, 1597440, 48, 260010);
            composer2.endNode();
            composer2.endNode();
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
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: dr4
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return kr4.d(str, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }
}
