package defpackage;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.ColumnKt;
import androidx.compose.foundation.layout.ColumnScopeInstance;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.ColorKt;
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
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class yi2 {
    public static final yi2 a = new yi2();
    public static Function3 b = ComposableLambdaKt.composableLambdaInstance(-942171712, false, new Function3() { // from class: ii2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return yi2.o((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 c = ComposableLambdaKt.composableLambdaInstance(1800020433, false, new Function3() { // from class: vi2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return yi2.n((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 d = ComposableLambdaKt.composableLambdaInstance(13034796, false, new Function2() { // from class: wi2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return yi2.f((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function3 e = ComposableLambdaKt.composableLambdaInstance(-1661563127, false, new Function3() { // from class: xi2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return yi2.d((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 f = ComposableLambdaKt.composableLambdaInstance(1866775291, false, new Function2() { // from class: ji2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return yi2.b((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 g = ComposableLambdaKt.composableLambdaInstance(-1918220636, false, new Function2() { // from class: ki2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return yi2.m((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 h = ComposableLambdaKt.composableLambdaInstance(-1243011776, false, new Function2() { // from class: li2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return yi2.e((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 i = ComposableLambdaKt.composableLambdaInstance(-665816063, false, new Function2() { // from class: mi2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return yi2.i((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 j = ComposableLambdaKt.composableLambdaInstance(-20751639, false, new Function2() { // from class: ni2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return yi2.j((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 k = ComposableLambdaKt.composableLambdaInstance(613547370, false, new Function2() { // from class: oi2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return yi2.l((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function3 l = ComposableLambdaKt.composableLambdaInstance(1383705415, false, new Function3() { // from class: pi2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return yi2.a((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 m = ComposableLambdaKt.composableLambdaInstance(1563864987, false, new Function3() { // from class: qi2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return yi2.k((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 n = ComposableLambdaKt.composableLambdaInstance(767343688, false, new Function3() { // from class: ri2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return yi2.h((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function3 o = ComposableLambdaKt.composableLambdaInstance(-2081804239, false, new Function3() { // from class: si2
        public final Object invoke(Object obj, Object obj2, Object obj3) {
            return yi2.c((RowScope) obj, (Composer) obj2, ((Integer) obj3).intValue());
        }
    });
    public static Function2 p = ComposableLambdaKt.composableLambdaInstance(1212130311, false, new Function2() { // from class: ti2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return yi2.p((Composer) obj, ((Integer) obj2).intValue());
        }
    });
    public static Function2 q = ComposableLambdaKt.composableLambdaInstance(-2032967770, false, new Function2() { // from class: ui2
        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(Object obj, Object obj2) {
            return yi2.g((Composer) obj, ((Integer) obj2).intValue());
        }
    });

    public static Unit a(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1383705415, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$1383705415.<anonymous> (ReleaseDialog.kt:256)");
            }
            TextKt.Text-Nvy7gAk("签名设置", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit b(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1866775291, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$1866775291.<anonymous> (ReleaseDialog.kt:173)");
            }
            TextKt.Text-Nvy7gAk("应用名", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit c(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2081804239, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$-2081804239.<anonymous> (ReleaseDialog.kt:320)");
            }
            TextKt.Text-Nvy7gAk("仍用调试签名导出", (Modifier) null, ColorKt.Color(4288455599L), (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 390, 0, 262138);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit d(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1661563127, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$-1661563127.<anonymous> (ReleaseDialog.kt:156)");
            }
            TextKt.Text-Nvy7gAk("更换图标", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit e(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1243011776, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$-1243011776.<anonymous> (ReleaseDialog.kt:211)");
            }
            TextKt.Text-Nvy7gAk("版本名 (versionName)", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit f(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(13034796, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$13034796.<anonymous> (ReleaseDialog.kt:128)");
            }
            TextKt.Text-Nvy7gAk("正式打包", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit g(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-2032967770, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$-2032967770.<anonymous> (ReleaseDialog.kt:282)");
            }
            Arrangement.HorizontalOrVertical horizontalOrVertical = Arrangement.INSTANCE.spacedBy-0680j_4(Dp.constructor-impl(10.0f));
            Modifier.Companion companion = Modifier.Companion;
            MeasurePolicy measurePolicyColumnMeasurePolicy = ColumnKt.columnMeasurePolicy(horizontalOrVertical, Alignment.Companion.getStart(), composer, 6);
            int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer, 0));
            CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
            ComposeUiNode.Companion companion2 = ComposeUiNode.Companion;
            Function0 constructor = companion2.getConstructor();
            if (composer.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer.startReusableNode();
            if (composer.getInserting()) {
                composer.createNode(constructor);
            } else {
                composer.useNode();
            }
            Composer composer2 = Updater.constructor-impl(composer);
            Updater.set-impl(composer2, measurePolicyColumnMeasurePolicy, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer2, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer2, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer2, modifierMaterializeModifier, companion2.getSetModifier());
            ColumnScopeInstance columnScopeInstance = ColumnScopeInstance.INSTANCE;
            TextKt.Text-Nvy7gAk("当前将使用 IDE 调试签名导出，存在以下风险：", (Modifier) null, 0L, (TextAutoSize) null, TextUnitKt.getSp(14), (FontStyle) null, FontWeight.Companion.getMedium(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 1597446, 0, 262062);
            TextKt.Text-Nvy7gAk("· 调试签名的 APK 不能上架应用商店，也不能用于正式备案；\n· 导出 ZIP 不含正式密钥库，后续无法凭此包发布更新；\n· 若以后才配置正式签名，已安装用户通常无法直接覆盖更新。", (Modifier) null, ColorKt.Color(4283127139L), (TextAutoSize) null, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(20), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24960, 48, 260074);
            TextKt.Text-Nvy7gAk("上架前请先在「签名设置」中生成或导入自有密钥并启用。", (Modifier) null, ColorKt.Color(4290007817L), (TextAutoSize) null, TextUnitKt.getSp(13), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 24966, 0, 262122);
            composer.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit h(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(767343688, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$767343688.<anonymous> (ReleaseDialog.kt:314)");
            }
            TextKt.Text-Nvy7gAk("取消", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit i(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-665816063, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$-665816063.<anonymous> (ReleaseDialog.kt:212)");
            }
            TextKt.Text-Nvy7gAk("1.0", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit j(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-20751639, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$-20751639.<anonymous> (ReleaseDialog.kt:224)");
            }
            TextKt.Text-Nvy7gAk("版本号 (versionCode)", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit k(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1563864987, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$1563864987.<anonymous> (ReleaseDialog.kt:308)");
            }
            TextKt.Text-Nvy7gAk("去配置签名", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit l(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(613547370, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$613547370.<anonymous> (ReleaseDialog.kt:225)");
            }
            TextKt.Text-Nvy7gAk("1", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit m(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1918220636, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$-1918220636.<anonymous> (ReleaseDialog.kt:183)");
            }
            TextKt.Text-Nvy7gAk("包名 (applicationId)", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit n(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1800020433, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$1800020433.<anonymous> (ReleaseDialog.kt:273)");
            }
            TextKt.Text-Nvy7gAk("取消", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit o(RowScope rowScope, Composer composer, int i2) {
        rowScope.getClass();
        if (composer.shouldExecute((i2 & 17) != 16, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-942171712, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$-942171712.<anonymous> (ReleaseDialog.kt:270)");
            }
            TextKt.Text-Nvy7gAk("开始打包", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public static Unit p(Composer composer, int i2) {
        if (composer.shouldExecute((i2 & 3) != 2, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1212130311, i2, -1, "com.yimaide.app.ui.workbench.ComposableSingletons$ReleaseDialogKt.lambda$1212130311.<anonymous> (ReleaseDialog.kt:280)");
            }
            TextKt.Text-Nvy7gAk("尚未配置正式签名", (Modifier) null, 0L, (TextAutoSize) null, 0L, (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer, 6, 0, 262142);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer.skipToGroupEnd();
        }
        return Unit.INSTANCE;
    }

    public final Function3 A() {
        return l;
    }

    public final Function3 B() {
        return m;
    }

    public final Function3 C() {
        return c;
    }

    public final Function2 D() {
        return f;
    }

    public final Function2 E() {
        return k;
    }

    public final Function3 F() {
        return n;
    }

    public final Function2 q() {
        return h;
    }

    public final Function3 r() {
        return e;
    }

    public final Function2 s() {
        return g;
    }

    public final Function2 t() {
        return q;
    }

    public final Function2 u() {
        return j;
    }

    public final Function3 v() {
        return o;
    }

    public final Function2 w() {
        return i;
    }

    public final Function3 x() {
        return b;
    }

    public final Function2 y() {
        return p;
    }

    public final Function2 z() {
        return d;
    }
}
