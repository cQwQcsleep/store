package defpackage;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.BorderKt;
import androidx.compose.foundation.ClickableKt;
import androidx.compose.foundation.interaction.FocusInteractionKt;
import androidx.compose.foundation.interaction.InteractionSourceKt;
import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.foundation.layout.BoxScopeInstance;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.layout.SpacerKt;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.foundation.text.BasicTextFieldKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.foundation.text.TextAutoSize;
import androidx.compose.material3.ProgressIndicatorKt;
import androidx.compose.material3.TextKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.State;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.draw.ClipKt;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Shadow;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.SolidColor;
import androidx.compose.ui.graphics.drawscope.DrawStyle;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.semantics.Role;
import androidx.compose.ui.text.PlatformTextStyle;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.font.FontFamily;
import androidx.compose.ui.text.font.FontStyle;
import androidx.compose.ui.text.font.FontSynthesis;
import androidx.compose.ui.text.font.FontWeight;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.text.style.BaselineShift;
import androidx.compose.ui.text.style.LineHeightStyle;
import androidx.compose.ui.text.style.TextAlign;
import androidx.compose.ui.text.style.TextDecoration;
import androidx.compose.ui.text.style.TextGeometricTransform;
import androidx.compose.ui.text.style.TextIndent;
import androidx.compose.ui.text.style.TextMotion;
import androidx.compose.ui.unit.Dp;
import androidx.compose.ui.unit.TextUnitKt;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.io.IOException;
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
public abstract class wg9 {
    public static final long a = ColorKt.Color(4280640491L);

    public static Unit a(String str, Function1 function1, String str2, boolean z, int i, Function2 function2, Modifier modifier, int i2, int i3, Composer composer, int i4) {
        e(str, function1, str2, z, i, function2, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), i3);
        return Unit.INSTANCE;
    }

    public static Unit b(String str, boolean z, boolean z2, Function0 function0, Modifier modifier, int i, int i2, Composer composer, int i3) {
        g(str, z, z2, function0, modifier, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1), i2);
        return Unit.INSTANCE;
    }

    public static Unit c(boolean z, Function1 function1, int i, String str) throws IOException {
        str.getClass();
        if (z) {
            StringBuilder sb = new StringBuilder();
            int length = str.length();
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = str.charAt(i2);
                if (Character.isDigit(cCharAt)) {
                    sb.append(cCharAt);
                }
            }
            str = sb.toString();
        }
        function1.invoke(StringsKt.take(str, i));
        return Unit.INSTANCE;
    }

    public static Unit d(boolean z, int i, Function0 function0, int i2, Composer composer, int i3) {
        h(z, i, function0, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1));
        return Unit.INSTANCE;
    }

    /* JADX WARN: Code duplicated, block: B:102:0x01bb  */
    /* JADX WARN: Code duplicated, block: B:105:0x01c7  */
    /* JADX WARN: Code duplicated, block: B:106:0x01cb  */
    /* JADX WARN: Code duplicated, block: B:109:0x0230  */
    /* JADX WARN: Code duplicated, block: B:112:0x023c  */
    /* JADX WARN: Code duplicated, block: B:113:0x0240  */
    /* JADX WARN: Code duplicated, block: B:116:0x027a  */
    /* JADX WARN: Code duplicated, block: B:118:0x02ec  */
    /* JADX WARN: Code duplicated, block: B:121:0x0351  */
    /* JADX WARN: Code duplicated, block: B:123:0x0358  */
    /* JADX WARN: Code duplicated, block: B:126:0x037a  */
    /* JADX WARN: Code duplicated, block: B:127:0x037c  */
    /* JADX WARN: Code duplicated, block: B:130:0x0384  */
    /* JADX WARN: Code duplicated, block: B:131:0x0386  */
    /* JADX WARN: Code duplicated, block: B:134:0x0391  */
    /* JADX WARN: Code duplicated, block: B:135:0x0393  */
    /* JADX WARN: Code duplicated, block: B:138:0x039d  */
    /* JADX WARN: Code duplicated, block: B:140:0x03a3  */
    /* JADX WARN: Code duplicated, block: B:143:0x03e1  */
    /* JADX WARN: Code duplicated, block: B:145:0x0404  */
    /* JADX WARN: Code duplicated, block: B:148:0x0414  */
    /* JADX WARN: Code duplicated, block: B:150:0x041c  */
    /* JADX WARN: Code duplicated, block: B:153:0x042c  */
    /* JADX WARN: Code duplicated, block: B:155:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:37:0x006b  */
    /* JADX WARN: Code duplicated, block: B:39:0x0070  */
    /* JADX WARN: Code duplicated, block: B:41:0x0074  */
    /* JADX WARN: Code duplicated, block: B:43:0x007c  */
    /* JADX WARN: Code duplicated, block: B:44:0x007f  */
    /* JADX WARN: Code duplicated, block: B:48:0x0088  */
    /* JADX WARN: Code duplicated, block: B:49:0x008d  */
    /* JADX WARN: Code duplicated, block: B:51:0x0093  */
    /* JADX WARN: Code duplicated, block: B:53:0x0099  */
    /* JADX WARN: Code duplicated, block: B:54:0x009c  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a6  */
    /* JADX WARN: Code duplicated, block: B:59:0x00ab  */
    /* JADX WARN: Code duplicated, block: B:61:0x00b1  */
    /* JADX WARN: Code duplicated, block: B:63:0x00b7  */
    /* JADX WARN: Code duplicated, block: B:64:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:68:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:69:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:72:0x00d4 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:73:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:74:0x00d8  */
    /* JADX WARN: Code duplicated, block: B:76:0x00db  */
    /* JADX WARN: Code duplicated, block: B:77:0x00df  */
    /* JADX WARN: Code duplicated, block: B:80:0x00e3  */
    /* JADX WARN: Code duplicated, block: B:81:0x00e5  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e9  */
    /* JADX WARN: Code duplicated, block: B:84:0x00ec  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f4  */
    /* JADX WARN: Code duplicated, block: B:90:0x0106  */
    /* JADX WARN: Code duplicated, block: B:93:0x014d  */
    /* JADX WARN: Code duplicated, block: B:94:0x0154  */
    /* JADX WARN: Code duplicated, block: B:97:0x0160  */
    /* JADX WARN: Code duplicated, block: B:99:0x0169  */
    public static final void e(final String str, final Function1 function1, final String str2, boolean z, int i, Function2 function2, Modifier modifier, Composer composer, final int i2, final int i3) {
        int i4;
        boolean z2;
        int i5;
        int i6;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        boolean z3;
        Composer composer2;
        final Function2 function3;
        final Modifier modifier2;
        final boolean z4;
        final int i12;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        final int i13;
        Function2 function4;
        Modifier modifier3;
        Object objRememberedValue;
        Composer.Companion companion;
        State stateCollectIsFocusedAsState;
        float f;
        long jColor;
        Composer composer3;
        Function0 constructor;
        Modifier.Companion companion2;
        Function0 constructor2;
        Function2 function5;
        Modifier modifier4;
        int i14;
        float f2;
        int i15;
        KeyboardType.Companion companion3;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        Object objRememberedValue2;
        str.getClass();
        function1.getClass();
        str2.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(-830387859);
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(str) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i2 & KyberEngine.KyberPolyBytes) == 0) {
            i4 |= composerStartRestartGroup.changed(str2) ? 256 : CodeEditor.FLAG_DRAW_SOFT_WRAP;
        }
        int i21 = i3 & 8;
        if (i21 == 0) {
            if ((i2 & 3072) == 0) {
                z2 = z;
                i4 |= composerStartRestartGroup.changed(z2) ? NewHope.SENDB_BYTES : NewHope.POLY_SIZE;
            }
            i5 = i3 & 16;
            if (i5 != 0) {
                if ((i2 & 24576) == 0) {
                    i6 = i;
                    if (composerStartRestartGroup.changed(i6)) {
                        i7 = 16384;
                    } else {
                        i7 = 8192;
                    }
                    i4 |= i7;
                }
                i8 = i3 & 32;
                if (i8 != 0) {
                    i4 |= 196608;
                } else if ((i2 & 196608) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i9 = 131072;
                    } else {
                        i9 = 65536;
                    }
                    i4 |= i9;
                }
                i10 = i3 & 64;
                if (i10 != 0) {
                    i4 |= 1572864;
                } else if ((i2 & 1572864) == 0) {
                    if (composerStartRestartGroup.changed(modifier)) {
                        i11 = 1048576;
                    } else {
                        i11 = 524288;
                    }
                    i4 |= i11;
                }
                if ((i4 & 599187) != 599186) {
                    z3 = true;
                } else {
                    z3 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z3, i4 & 1)) {
                    if (i21 != 0) {
                        z4 = false;
                    } else {
                        z4 = z2;
                    }
                    if (i5 != 0) {
                        i13 = Integer.MAX_VALUE;
                    } else {
                        i13 = i6;
                    }
                    if (i8 != 0) {
                        function4 = null;
                    } else {
                        function4 = function2;
                    }
                    if (i10 != 0) {
                        modifier3 = Modifier.Companion;
                    } else {
                        modifier3 = modifier;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-830387859, i4, -1, "com.yimaide.app.ui.auth.AuthField (LoginComponents.kt:50)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    MutableInteractionSource mutableInteractionSource = (MutableInteractionSource) objRememberedValue;
                    stateCollectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource, composerStartRestartGroup, 6);
                    Modifier modifier5 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.heightIn-VpY3zN4$default(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), Dp.constructor-impl(52.0f), 0.0f, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14.0f))), ColorKt.Color(4294178038L), (Shape) null, 2, (Object) null);
                    if (f(stateCollectIsFocusedAsState)) {
                        f = Dp.constructor-impl(1.5f);
                    } else {
                        f = Dp.constructor-impl(1.0f);
                    }
                    if (f(stateCollectIsFocusedAsState)) {
                        jColor = a;
                    } else {
                        jColor = ColorKt.Color(4293257195L);
                    }
                    Modifier modifier6 = PaddingKt.padding-VpY3zN4$default(BorderKt.border-xT4_qwU(modifier5, f, jColor, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14.0f))), Dp.constructor-impl(16.0f), 0.0f, 2, (Object) null);
                    Alignment.Companion companion4 = Alignment.Companion;
                    composer3 = composerStartRestartGroup;
                    MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), companion4.getCenterVertically(), composer3, 48);
                    int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer3, modifier6);
                    ComposeUiNode.Companion companion5 = ComposeUiNode.Companion;
                    constructor = companion5.getConstructor();
                    if (composer3.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor);
                    } else {
                        composer3.useNode();
                    }
                    Composer composer4 = Updater.constructor-impl(composer3);
                    Updater.set-impl(composer4, measurePolicyRowMeasurePolicy, companion5.getSetMeasurePolicy());
                    Updater.set-impl(composer4, currentCompositionLocalMap, companion5.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer4, Integer.valueOf(iHashCode), companion5.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer4, companion5.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer4, modifierMaterializeModifier, companion5.getSetModifier());
                    RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
                    companion2 = Modifier.Companion;
                    Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, companion2, 1.0f, false, 2, (Object) null);
                    MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(companion4.getCenterStart(), false);
                    int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                    CompositionLocalMap currentCompositionLocalMap2 = composer3.getCurrentCompositionLocalMap();
                    Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composer3, modifierWeight$default);
                    constructor2 = companion5.getConstructor();
                    if (composer3.getApplier() == null) {
                        ComposablesKt.invalidApplier();
                    }
                    composer3.startReusableNode();
                    if (composer3.getInserting()) {
                        composer3.createNode(constructor2);
                    } else {
                        composer3.useNode();
                    }
                    Composer composer5 = Updater.constructor-impl(composer3);
                    Updater.set-impl(composer5, measurePolicyMaybeCachedBoxMeasurePolicy, companion5.getSetMeasurePolicy());
                    Updater.set-impl(composer5, currentCompositionLocalMap2, companion5.getSetResolvedCompositionLocals());
                    Updater.init-impl(composer5, Integer.valueOf(iHashCode2), companion5.getSetCompositeKeyHash());
                    Updater.reconcile-impl(composer5, companion5.getApplyOnDeactivatedNodeAssertion());
                    Updater.set-impl(composer5, modifierMaterializeModifier2, companion5.getSetModifier());
                    BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                    if (str.length() == 0) {
                        composer3.startReplaceGroup(-1507806410);
                        i15 = 0;
                        modifier4 = modifier3;
                        function5 = function4;
                        f2 = 0.0f;
                        i14 = 1;
                        TextKt.Text-Nvy7gAk(str2, (Modifier) null, ColorKt.Color(4288455599L), (TextAutoSize) null, TextUnitKt.getSp(15), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(20), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer3, ((i4 >> 6) & 14) | 24960, 48, 260074);
                        composer3 = composer3;
                    } else {
                        function5 = function4;
                        modifier4 = modifier3;
                        i14 = 1;
                        f2 = 0.0f;
                        i15 = 0;
                        composer3.startReplaceGroup(-1510522165);
                    }
                    composer3.endReplaceGroup();
                    TextStyle textStyle = new TextStyle(ColorKt.Color(4279310375L), TextUnitKt.getSp(15), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(20), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646140, (DefaultConstructorMarker) null);
                    SolidColor solidColor = new SolidColor(a, (DefaultConstructorMarker) null);
                    companion3 = KeyboardType.Companion;
                    if (z4) {
                        i16 = companion3.getNumber-PjHm6EE();
                    } else {
                        i16 = companion3.getText-PjHm6EE();
                    }
                    KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, i16, 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 123, (DefaultConstructorMarker) null);
                    Modifier modifierFillMaxWidth$default = SizeKt.fillMaxWidth$default(companion2, f2, i14, (Object) null);
                    if ((i4 & 7168) == 2048) {
                        i17 = i14;
                    } else {
                        i17 = i15;
                    }
                    if ((i4 & 112) == 32) {
                        i18 = i14;
                    } else {
                        i18 = i15;
                    }
                    int i22 = i18 | i17;
                    if ((57344 & i4) == 16384) {
                        i19 = i14;
                    } else {
                        i19 = i15;
                    }
                    i20 = i22 | i19;
                    objRememberedValue2 = composer3.rememberedValue();
                    if (i20 == 0 || objRememberedValue2 == companion.getEmpty()) {
                        objRememberedValue2 = new Function1() { // from class: sg9
                            @Override // kotlin.jvm.functions.Function1
                            public final Object invoke(Object obj) {
                                return wg9.c(z4, function1, i13, (String) obj);
                            }
                        };
                        composer3.updateRememberedValue(objRememberedValue2);
                    }
                    Composer composer6 = composer3;
                    BasicTextFieldKt.BasicTextField(str, (Function1) objRememberedValue2, modifierFillMaxWidth$default, false, false, textStyle, keyboardOptions, (KeyboardActions) null, true, 0, 0, (VisualTransformation) null, (Function1) null, mutableInteractionSource, solidColor, (Function3) null, composer6, (i4 & 14) | 100663680, 27648, 40600);
                    composer2 = composer6;
                    composer2.endNode();
                    if (function5 != null) {
                        composer2.startReplaceGroup(-1297210277);
                        SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion2, Dp.constructor-impl(8.0f)), composer2, 6);
                        function5.invoke(composer2, Integer.valueOf((i4 >> 15) & 14));
                    } else {
                        composer2.startReplaceGroup(-1300984527);
                    }
                    composer2.endReplaceGroup();
                    composer2.endNode();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    i12 = i13;
                    modifier2 = modifier4;
                    function3 = function5;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    function3 = function2;
                    modifier2 = modifier;
                    z4 = z2;
                    i12 = i6;
                }
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tg9
                        @Override // kotlin.jvm.functions.Function2
                        public final Object invoke(Object obj, Object obj2) {
                            return wg9.a(str, function1, str2, z4, i12, function3, modifier2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i4 |= 24576;
            i6 = i;
            i8 = i3 & 32;
            if (i8 != 0) {
                i4 |= 196608;
            } else if ((i2 & 196608) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            i10 = i3 & 64;
            if (i10 != 0) {
                i4 |= 1572864;
            } else if ((i2 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(modifier)) {
                    i11 = 1048576;
                } else {
                    i11 = 524288;
                }
                i4 |= i11;
            }
            if ((i4 & 599187) != 599186) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i4 & 1)) {
                if (i21 != 0) {
                    z4 = false;
                } else {
                    z4 = z2;
                }
                if (i5 != 0) {
                    i13 = Integer.MAX_VALUE;
                } else {
                    i13 = i6;
                }
                if (i8 != 0) {
                    function4 = null;
                } else {
                    function4 = function2;
                }
                if (i10 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-830387859, i4, -1, "com.yimaide.app.ui.auth.AuthField (LoginComponents.kt:50)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                MutableInteractionSource mutableInteractionSource2 = (MutableInteractionSource) objRememberedValue;
                stateCollectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource2, composerStartRestartGroup, 6);
                Modifier modifier7 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.heightIn-VpY3zN4$default(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), Dp.constructor-impl(52.0f), 0.0f, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14.0f))), ColorKt.Color(4294178038L), (Shape) null, 2, (Object) null);
                if (f(stateCollectIsFocusedAsState)) {
                    f = Dp.constructor-impl(1.5f);
                } else {
                    f = Dp.constructor-impl(1.0f);
                }
                if (f(stateCollectIsFocusedAsState)) {
                    jColor = a;
                } else {
                    jColor = ColorKt.Color(4293257195L);
                }
                Modifier modifier8 = PaddingKt.padding-VpY3zN4$default(BorderKt.border-xT4_qwU(modifier7, f, jColor, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14.0f))), Dp.constructor-impl(16.0f), 0.0f, 2, (Object) null);
                Alignment.Companion companion6 = Alignment.Companion;
                composer3 = composerStartRestartGroup;
                MeasurePolicy measurePolicyRowMeasurePolicy2 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), companion6.getCenterVertically(), composer3, 48);
                int iHashCode3 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap3 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier3 = ComposedModifierKt.materializeModifier(composer3, modifier8);
                ComposeUiNode.Companion companion7 = ComposeUiNode.Companion;
                constructor = companion7.getConstructor();
                if (composer3.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                    composer3.createNode(constructor);
                } else {
                    composer3.useNode();
                }
                Composer composer7 = Updater.constructor-impl(composer3);
                Updater.set-impl(composer7, measurePolicyRowMeasurePolicy2, companion7.getSetMeasurePolicy());
                Updater.set-impl(composer7, currentCompositionLocalMap3, companion7.getSetResolvedCompositionLocals());
                Updater.init-impl(composer7, Integer.valueOf(iHashCode3), companion7.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer7, companion7.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer7, modifierMaterializeModifier3, companion7.getSetModifier());
                RowScopeInstance rowScopeInstance2 = RowScopeInstance.INSTANCE;
                companion2 = Modifier.Companion;
                Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance2, companion2, 1.0f, false, 2, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(companion6.getCenterStart(), false);
                int iHashCode4 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap4 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier4 = ComposedModifierKt.materializeModifier(composer3, modifierWeight$default2);
                constructor2 = companion7.getConstructor();
                if (composer3.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                    composer3.createNode(constructor2);
                } else {
                    composer3.useNode();
                }
                Composer composer8 = Updater.constructor-impl(composer3);
                Updater.set-impl(composer8, measurePolicyMaybeCachedBoxMeasurePolicy2, companion7.getSetMeasurePolicy());
                Updater.set-impl(composer8, currentCompositionLocalMap4, companion7.getSetResolvedCompositionLocals());
                Updater.init-impl(composer8, Integer.valueOf(iHashCode4), companion7.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer8, companion7.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer8, modifierMaterializeModifier4, companion7.getSetModifier());
                BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
                if (str.length() == 0) {
                    composer3.startReplaceGroup(-1507806410);
                    i15 = 0;
                    modifier4 = modifier3;
                    function5 = function4;
                    f2 = 0.0f;
                    i14 = 1;
                    TextKt.Text-Nvy7gAk(str2, (Modifier) null, ColorKt.Color(4288455599L), (TextAutoSize) null, TextUnitKt.getSp(15), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(20), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer3, ((i4 >> 6) & 14) | 24960, 48, 260074);
                    composer3 = composer3;
                } else {
                    function5 = function4;
                    modifier4 = modifier3;
                    i14 = 1;
                    f2 = 0.0f;
                    i15 = 0;
                    composer3.startReplaceGroup(-1510522165);
                }
                composer3.endReplaceGroup();
                TextStyle textStyle2 = new TextStyle(ColorKt.Color(4279310375L), TextUnitKt.getSp(15), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(20), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646140, (DefaultConstructorMarker) null);
                SolidColor solidColor2 = new SolidColor(a, (DefaultConstructorMarker) null);
                companion3 = KeyboardType.Companion;
                if (z4) {
                    i16 = companion3.getNumber-PjHm6EE();
                } else {
                    i16 = companion3.getText-PjHm6EE();
                }
                KeyboardOptions keyboardOptions2 = new KeyboardOptions(0, (Boolean) null, i16, 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 123, (DefaultConstructorMarker) null);
                Modifier modifierFillMaxWidth$default2 = SizeKt.fillMaxWidth$default(companion2, f2, i14, (Object) null);
                if ((i4 & 7168) == 2048) {
                    i17 = i14;
                } else {
                    i17 = i15;
                }
                if ((i4 & 112) == 32) {
                    i18 = i14;
                } else {
                    i18 = i15;
                }
                int i23 = i18 | i17;
                if ((57344 & i4) == 16384) {
                    i19 = i14;
                } else {
                    i19 = i15;
                }
                i20 = i23 | i19;
                objRememberedValue2 = composer3.rememberedValue();
                if (i20 == 0) {
                    objRememberedValue2 = new Function1() { // from class: sg9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return wg9.c(z4, function1, i13, (String) obj);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: sg9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return wg9.c(z4, function1, i13, (String) obj);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue2);
                }
                Composer composer9 = composer3;
                BasicTextFieldKt.BasicTextField(str, (Function1) objRememberedValue2, modifierFillMaxWidth$default2, false, false, textStyle2, keyboardOptions2, (KeyboardActions) null, true, 0, 0, (VisualTransformation) null, (Function1) null, mutableInteractionSource2, solidColor2, (Function3) null, composer9, (i4 & 14) | 100663680, 27648, 40600);
                composer2 = composer9;
                composer2.endNode();
                if (function5 != null) {
                    composer2.startReplaceGroup(-1297210277);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion2, Dp.constructor-impl(8.0f)), composer2, 6);
                    function5.invoke(composer2, Integer.valueOf((i4 >> 15) & 14));
                } else {
                    composer2.startReplaceGroup(-1300984527);
                }
                composer2.endReplaceGroup();
                composer2.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i12 = i13;
                modifier2 = modifier4;
                function3 = function5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function3 = function2;
                modifier2 = modifier;
                z4 = z2;
                i12 = i6;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tg9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return wg9.a(str, function1, str2, z4, i12, function3, modifier2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 3072;
        z2 = z;
        i5 = i3 & 16;
        if (i5 != 0) {
            if ((i2 & 24576) == 0) {
                i6 = i;
                if (composerStartRestartGroup.changed(i6)) {
                    i7 = 16384;
                } else {
                    i7 = 8192;
                }
                i4 |= i7;
            }
            i8 = i3 & 32;
            if (i8 != 0) {
                i4 |= 196608;
            } else if ((i2 & 196608) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i9 = 131072;
                } else {
                    i9 = 65536;
                }
                i4 |= i9;
            }
            i10 = i3 & 64;
            if (i10 != 0) {
                i4 |= 1572864;
            } else if ((i2 & 1572864) == 0) {
                if (composerStartRestartGroup.changed(modifier)) {
                    i11 = 1048576;
                } else {
                    i11 = 524288;
                }
                i4 |= i11;
            }
            if ((i4 & 599187) != 599186) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i4 & 1)) {
                if (i21 != 0) {
                    z4 = false;
                } else {
                    z4 = z2;
                }
                if (i5 != 0) {
                    i13 = Integer.MAX_VALUE;
                } else {
                    i13 = i6;
                }
                if (i8 != 0) {
                    function4 = null;
                } else {
                    function4 = function2;
                }
                if (i10 != 0) {
                    modifier3 = Modifier.Companion;
                } else {
                    modifier3 = modifier;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-830387859, i4, -1, "com.yimaide.app.ui.auth.AuthField (LoginComponents.kt:50)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                MutableInteractionSource mutableInteractionSource3 = (MutableInteractionSource) objRememberedValue;
                stateCollectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource3, composerStartRestartGroup, 6);
                Modifier modifier9 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.heightIn-VpY3zN4$default(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), Dp.constructor-impl(52.0f), 0.0f, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14.0f))), ColorKt.Color(4294178038L), (Shape) null, 2, (Object) null);
                if (f(stateCollectIsFocusedAsState)) {
                    f = Dp.constructor-impl(1.5f);
                } else {
                    f = Dp.constructor-impl(1.0f);
                }
                if (f(stateCollectIsFocusedAsState)) {
                    jColor = a;
                } else {
                    jColor = ColorKt.Color(4293257195L);
                }
                Modifier modifier10 = PaddingKt.padding-VpY3zN4$default(BorderKt.border-xT4_qwU(modifier9, f, jColor, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14.0f))), Dp.constructor-impl(16.0f), 0.0f, 2, (Object) null);
                Alignment.Companion companion8 = Alignment.Companion;
                composer3 = composerStartRestartGroup;
                MeasurePolicy measurePolicyRowMeasurePolicy3 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), companion8.getCenterVertically(), composer3, 48);
                int iHashCode5 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap5 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier5 = ComposedModifierKt.materializeModifier(composer3, modifier10);
                ComposeUiNode.Companion companion9 = ComposeUiNode.Companion;
                constructor = companion9.getConstructor();
                if (composer3.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                    composer3.createNode(constructor);
                } else {
                    composer3.useNode();
                }
                Composer composer10 = Updater.constructor-impl(composer3);
                Updater.set-impl(composer10, measurePolicyRowMeasurePolicy3, companion9.getSetMeasurePolicy());
                Updater.set-impl(composer10, currentCompositionLocalMap5, companion9.getSetResolvedCompositionLocals());
                Updater.init-impl(composer10, Integer.valueOf(iHashCode5), companion9.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer10, companion9.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer10, modifierMaterializeModifier5, companion9.getSetModifier());
                RowScopeInstance rowScopeInstance3 = RowScopeInstance.INSTANCE;
                companion2 = Modifier.Companion;
                Modifier modifierWeight$default3 = RowScope.weight$default(rowScopeInstance3, companion2, 1.0f, false, 2, (Object) null);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy3 = BoxKt.maybeCachedBoxMeasurePolicy(companion8.getCenterStart(), false);
                int iHashCode6 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
                CompositionLocalMap currentCompositionLocalMap6 = composer3.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier6 = ComposedModifierKt.materializeModifier(composer3, modifierWeight$default3);
                constructor2 = companion9.getConstructor();
                if (composer3.getApplier() == null) {
                    ComposablesKt.invalidApplier();
                }
                composer3.startReusableNode();
                if (composer3.getInserting()) {
                    composer3.createNode(constructor2);
                } else {
                    composer3.useNode();
                }
                Composer composer11 = Updater.constructor-impl(composer3);
                Updater.set-impl(composer11, measurePolicyMaybeCachedBoxMeasurePolicy3, companion9.getSetMeasurePolicy());
                Updater.set-impl(composer11, currentCompositionLocalMap6, companion9.getSetResolvedCompositionLocals());
                Updater.init-impl(composer11, Integer.valueOf(iHashCode6), companion9.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer11, companion9.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer11, modifierMaterializeModifier6, companion9.getSetModifier());
                BoxScopeInstance boxScopeInstance3 = BoxScopeInstance.INSTANCE;
                if (str.length() == 0) {
                    composer3.startReplaceGroup(-1507806410);
                    i15 = 0;
                    modifier4 = modifier3;
                    function5 = function4;
                    f2 = 0.0f;
                    i14 = 1;
                    TextKt.Text-Nvy7gAk(str2, (Modifier) null, ColorKt.Color(4288455599L), (TextAutoSize) null, TextUnitKt.getSp(15), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(20), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer3, ((i4 >> 6) & 14) | 24960, 48, 260074);
                    composer3 = composer3;
                } else {
                    function5 = function4;
                    modifier4 = modifier3;
                    i14 = 1;
                    f2 = 0.0f;
                    i15 = 0;
                    composer3.startReplaceGroup(-1510522165);
                }
                composer3.endReplaceGroup();
                TextStyle textStyle3 = new TextStyle(ColorKt.Color(4279310375L), TextUnitKt.getSp(15), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(20), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646140, (DefaultConstructorMarker) null);
                SolidColor solidColor3 = new SolidColor(a, (DefaultConstructorMarker) null);
                companion3 = KeyboardType.Companion;
                if (z4) {
                    i16 = companion3.getNumber-PjHm6EE();
                } else {
                    i16 = companion3.getText-PjHm6EE();
                }
                KeyboardOptions keyboardOptions3 = new KeyboardOptions(0, (Boolean) null, i16, 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 123, (DefaultConstructorMarker) null);
                Modifier modifierFillMaxWidth$default3 = SizeKt.fillMaxWidth$default(companion2, f2, i14, (Object) null);
                if ((i4 & 7168) == 2048) {
                    i17 = i14;
                } else {
                    i17 = i15;
                }
                if ((i4 & 112) == 32) {
                    i18 = i14;
                } else {
                    i18 = i15;
                }
                int i24 = i18 | i17;
                if ((57344 & i4) == 16384) {
                    i19 = i14;
                } else {
                    i19 = i15;
                }
                i20 = i24 | i19;
                objRememberedValue2 = composer3.rememberedValue();
                if (i20 == 0) {
                    objRememberedValue2 = new Function1() { // from class: sg9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return wg9.c(z4, function1, i13, (String) obj);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new Function1() { // from class: sg9
                        @Override // kotlin.jvm.functions.Function1
                        public final Object invoke(Object obj) {
                            return wg9.c(z4, function1, i13, (String) obj);
                        }
                    };
                    composer3.updateRememberedValue(objRememberedValue2);
                }
                Composer composer12 = composer3;
                BasicTextFieldKt.BasicTextField(str, (Function1) objRememberedValue2, modifierFillMaxWidth$default3, false, false, textStyle3, keyboardOptions3, (KeyboardActions) null, true, 0, 0, (VisualTransformation) null, (Function1) null, mutableInteractionSource3, solidColor3, (Function3) null, composer12, (i4 & 14) | 100663680, 27648, 40600);
                composer2 = composer12;
                composer2.endNode();
                if (function5 != null) {
                    composer2.startReplaceGroup(-1297210277);
                    SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion2, Dp.constructor-impl(8.0f)), composer2, 6);
                    function5.invoke(composer2, Integer.valueOf((i4 >> 15) & 14));
                } else {
                    composer2.startReplaceGroup(-1300984527);
                }
                composer2.endReplaceGroup();
                composer2.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                i12 = i13;
                modifier2 = modifier4;
                function3 = function5;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                function3 = function2;
                modifier2 = modifier;
                z4 = z2;
                i12 = i6;
            }
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tg9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return wg9.a(str, function1, str2, z4, i12, function3, modifier2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i4 |= 24576;
        i6 = i;
        i8 = i3 & 32;
        if (i8 != 0) {
            i4 |= 196608;
        } else if ((i2 & 196608) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i9 = 131072;
            } else {
                i9 = 65536;
            }
            i4 |= i9;
        }
        i10 = i3 & 64;
        if (i10 != 0) {
            i4 |= 1572864;
        } else if ((i2 & 1572864) == 0) {
            if (composerStartRestartGroup.changed(modifier)) {
                i11 = 1048576;
            } else {
                i11 = 524288;
            }
            i4 |= i11;
        }
        if ((i4 & 599187) != 599186) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i4 & 1)) {
            if (i21 != 0) {
                z4 = false;
            } else {
                z4 = z2;
            }
            if (i5 != 0) {
                i13 = Integer.MAX_VALUE;
            } else {
                i13 = i6;
            }
            if (i8 != 0) {
                function4 = null;
            } else {
                function4 = function2;
            }
            if (i10 != 0) {
                modifier3 = Modifier.Companion;
            } else {
                modifier3 = modifier;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-830387859, i4, -1, "com.yimaide.app.ui.auth.AuthField (LoginComponents.kt:50)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = InteractionSourceKt.MutableInteractionSource();
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            MutableInteractionSource mutableInteractionSource4 = (MutableInteractionSource) objRememberedValue;
            stateCollectIsFocusedAsState = FocusInteractionKt.collectIsFocusedAsState(mutableInteractionSource4, composerStartRestartGroup, 6);
            Modifier modifier11 = BackgroundKt.background-bw27NRU$default(ClipKt.clip(SizeKt.heightIn-VpY3zN4$default(SizeKt.fillMaxWidth$default(modifier3, 0.0f, 1, (Object) null), Dp.constructor-impl(52.0f), 0.0f, 2, (Object) null), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14.0f))), ColorKt.Color(4294178038L), (Shape) null, 2, (Object) null);
            if (f(stateCollectIsFocusedAsState)) {
                f = Dp.constructor-impl(1.5f);
            } else {
                f = Dp.constructor-impl(1.0f);
            }
            if (f(stateCollectIsFocusedAsState)) {
                jColor = a;
            } else {
                jColor = ColorKt.Color(4293257195L);
            }
            Modifier modifier12 = PaddingKt.padding-VpY3zN4$default(BorderKt.border-xT4_qwU(modifier11, f, jColor, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(14.0f))), Dp.constructor-impl(16.0f), 0.0f, 2, (Object) null);
            Alignment.Companion companion10 = Alignment.Companion;
            composer3 = composerStartRestartGroup;
            MeasurePolicy measurePolicyRowMeasurePolicy4 = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), companion10.getCenterVertically(), composer3, 48);
            int iHashCode7 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
            CompositionLocalMap currentCompositionLocalMap7 = composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier7 = ComposedModifierKt.materializeModifier(composer3, modifier12);
            ComposeUiNode.Companion companion11 = ComposeUiNode.Companion;
            constructor = companion11.getConstructor();
            if (composer3.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
                composer3.createNode(constructor);
            } else {
                composer3.useNode();
            }
            Composer composer13 = Updater.constructor-impl(composer3);
            Updater.set-impl(composer13, measurePolicyRowMeasurePolicy4, companion11.getSetMeasurePolicy());
            Updater.set-impl(composer13, currentCompositionLocalMap7, companion11.getSetResolvedCompositionLocals());
            Updater.init-impl(composer13, Integer.valueOf(iHashCode7), companion11.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer13, companion11.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer13, modifierMaterializeModifier7, companion11.getSetModifier());
            RowScopeInstance rowScopeInstance4 = RowScopeInstance.INSTANCE;
            companion2 = Modifier.Companion;
            Modifier modifierWeight$default4 = RowScope.weight$default(rowScopeInstance4, companion2, 1.0f, false, 2, (Object) null);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy4 = BoxKt.maybeCachedBoxMeasurePolicy(companion10.getCenterStart(), false);
            int iHashCode8 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composer3, 0));
            CompositionLocalMap currentCompositionLocalMap8 = composer3.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier8 = ComposedModifierKt.materializeModifier(composer3, modifierWeight$default4);
            constructor2 = companion11.getConstructor();
            if (composer3.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composer3.startReusableNode();
            if (composer3.getInserting()) {
                composer3.createNode(constructor2);
            } else {
                composer3.useNode();
            }
            Composer composer14 = Updater.constructor-impl(composer3);
            Updater.set-impl(composer14, measurePolicyMaybeCachedBoxMeasurePolicy4, companion11.getSetMeasurePolicy());
            Updater.set-impl(composer14, currentCompositionLocalMap8, companion11.getSetResolvedCompositionLocals());
            Updater.init-impl(composer14, Integer.valueOf(iHashCode8), companion11.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer14, companion11.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer14, modifierMaterializeModifier8, companion11.getSetModifier());
            BoxScopeInstance boxScopeInstance4 = BoxScopeInstance.INSTANCE;
            if (str.length() == 0) {
                composer3.startReplaceGroup(-1507806410);
                i15 = 0;
                modifier4 = modifier3;
                function5 = function4;
                f2 = 0.0f;
                i14 = 1;
                TextKt.Text-Nvy7gAk(str2, (Modifier) null, ColorKt.Color(4288455599L), (TextAutoSize) null, TextUnitKt.getSp(15), (FontStyle) null, (FontWeight) null, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, TextUnitKt.getSp(20), 0, false, 0, 0, (Function1) null, (TextStyle) null, composer3, ((i4 >> 6) & 14) | 24960, 48, 260074);
                composer3 = composer3;
            } else {
                function5 = function4;
                modifier4 = modifier3;
                i14 = 1;
                f2 = 0.0f;
                i15 = 0;
                composer3.startReplaceGroup(-1510522165);
            }
            composer3.endReplaceGroup();
            TextStyle textStyle4 = new TextStyle(ColorKt.Color(4279310375L), TextUnitKt.getSp(15), (FontWeight) null, (FontStyle) null, (FontSynthesis) null, (FontFamily) null, (String) null, 0L, (BaselineShift) null, (TextGeometricTransform) null, (LocaleList) null, 0L, (TextDecoration) null, (Shadow) null, (DrawStyle) null, 0, 0, TextUnitKt.getSp(20), (TextIndent) null, (PlatformTextStyle) null, (LineHeightStyle) null, 0, 0, (TextMotion) null, 16646140, (DefaultConstructorMarker) null);
            SolidColor solidColor4 = new SolidColor(a, (DefaultConstructorMarker) null);
            companion3 = KeyboardType.Companion;
            if (z4) {
                i16 = companion3.getNumber-PjHm6EE();
            } else {
                i16 = companion3.getText-PjHm6EE();
            }
            KeyboardOptions keyboardOptions4 = new KeyboardOptions(0, (Boolean) null, i16, 0, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 123, (DefaultConstructorMarker) null);
            Modifier modifierFillMaxWidth$default4 = SizeKt.fillMaxWidth$default(companion2, f2, i14, (Object) null);
            if ((i4 & 7168) == 2048) {
                i17 = i14;
            } else {
                i17 = i15;
            }
            if ((i4 & 112) == 32) {
                i18 = i14;
            } else {
                i18 = i15;
            }
            int i25 = i18 | i17;
            if ((57344 & i4) == 16384) {
                i19 = i14;
            } else {
                i19 = i15;
            }
            i20 = i25 | i19;
            objRememberedValue2 = composer3.rememberedValue();
            if (i20 == 0) {
                objRememberedValue2 = new Function1() { // from class: sg9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return wg9.c(z4, function1, i13, (String) obj);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new Function1() { // from class: sg9
                    @Override // kotlin.jvm.functions.Function1
                    public final Object invoke(Object obj) {
                        return wg9.c(z4, function1, i13, (String) obj);
                    }
                };
                composer3.updateRememberedValue(objRememberedValue2);
            }
            Composer composer15 = composer3;
            BasicTextFieldKt.BasicTextField(str, (Function1) objRememberedValue2, modifierFillMaxWidth$default4, false, false, textStyle4, keyboardOptions4, (KeyboardActions) null, true, 0, 0, (VisualTransformation) null, (Function1) null, mutableInteractionSource4, solidColor4, (Function3) null, composer15, (i4 & 14) | 100663680, 27648, 40600);
            composer2 = composer15;
            composer2.endNode();
            if (function5 != null) {
                composer2.startReplaceGroup(-1297210277);
                SpacerKt.Spacer(SizeKt.width-3ABfNKs(companion2, Dp.constructor-impl(8.0f)), composer2, 6);
                function5.invoke(composer2, Integer.valueOf((i4 >> 15) & 14));
            } else {
                composer2.startReplaceGroup(-1300984527);
            }
            composer2.endReplaceGroup();
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            i12 = i13;
            modifier2 = modifier4;
            function3 = function5;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            function3 = function2;
            modifier2 = modifier;
            z4 = z2;
            i12 = i6;
        }
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: tg9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return wg9.a(str, function1, str2, z4, i12, function3, modifier2, i2, i3, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final boolean f(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    /* JADX WARN: Code duplicated, block: B:45:0x0082  */
    /* JADX WARN: Code duplicated, block: B:46:0x0084  */
    /* JADX WARN: Code duplicated, block: B:49:0x008d A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:50:0x008f  */
    /* JADX WARN: Code duplicated, block: B:51:0x0092  */
    /* JADX WARN: Code duplicated, block: B:54:0x0099  */
    /* JADX WARN: Code duplicated, block: B:58:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:61:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:63:0x00cb  */
    /* JADX WARN: Code duplicated, block: B:66:0x00e1  */
    /* JADX WARN: Code duplicated, block: B:67:0x00f6  */
    /* JADX WARN: Code duplicated, block: B:70:0x0122  */
    /* JADX WARN: Code duplicated, block: B:73:0x012e  */
    /* JADX WARN: Code duplicated, block: B:74:0x0132  */
    /* JADX WARN: Code duplicated, block: B:77:0x0164  */
    /* JADX WARN: Code duplicated, block: B:78:0x0193  */
    /* JADX WARN: Code duplicated, block: B:81:0x01e4  */
    /* JADX WARN: Code duplicated, block: B:83:0x01e9  */
    /* JADX WARN: Code duplicated, block: B:86:0x01f3  */
    /* JADX WARN: Code duplicated, block: B:88:? A[RETURN, SYNTHETIC] */
    public static final void g(final String str, final boolean z, final boolean z2, final Function0 function0, Modifier modifier, Composer composer, final int i, final int i2) {
        String str2;
        int i3;
        Function0 function1;
        Modifier modifier2;
        boolean z3;
        final Modifier modifier3;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        Modifier modifier4;
        boolean z4;
        long jColor;
        Modifier modifier5;
        Function0 constructor;
        str.getClass();
        function0.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(2107478963);
        if ((i & 6) == 0) {
            str2 = str;
            i3 = (composerStartRestartGroup.changed(str2) ? 4 : 2) | i;
        } else {
            str2 = str;
            i3 = i;
        }
        if ((i & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(z) ? 32 : 16;
        }
        if ((i & KyberEngine.KyberPolyBytes) == 0) {
            i3 |= composerStartRestartGroup.changed(z2) ? 256 : CodeEditor.FLAG_DRAW_SOFT_WRAP;
        }
        if ((i & 3072) == 0) {
            function1 = function0;
            i3 |= composerStartRestartGroup.changedInstance(function1) ? NewHope.SENDB_BYTES : NewHope.POLY_SIZE;
        } else {
            function1 = function0;
        }
        int i4 = i2 & 16;
        if (i4 == 0) {
            if ((i & 24576) == 0) {
                modifier2 = modifier;
                i3 |= composerStartRestartGroup.changed(modifier2) ? 16384 : 8192;
            }
            if ((i3 & 9363) != 9362) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
                if (i4 != 0) {
                    modifier4 = Modifier.Companion;
                } else {
                    modifier4 = modifier2;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(2107478963, i3, -1, "com.yimaide.app.ui.auth.AuthPrimaryButton (LoginComponents.kt:136)");
                }
                if (z || z2) {
                    z4 = false;
                } else {
                    z4 = true;
                }
                Modifier modifierClip = ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, (Object) null), Dp.constructor-impl(50.0f)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(25.0f)));
                if (z4) {
                    jColor = a;
                } else {
                    jColor = ColorKt.Color(4291285758L);
                }
                Modifier modifier6 = BackgroundKt.background-bw27NRU$default(modifierClip, jColor, (Shape) null, 2, (Object) null);
                if (z4) {
                    modifier5 = ClickableKt.clickable-oSLSa3U$default(Modifier.Companion, false, (String) null, (Role) null, (MutableInteractionSource) null, function1, 15, (Object) null);
                } else {
                    modifier5 = Modifier.Companion;
                }
                Modifier modifierThen = modifier6.then(modifier5);
                MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getCenter(), false);
                int iHashCode = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
                CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
                Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen);
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
                Composer composer2 = Updater.constructor-impl(composerStartRestartGroup);
                Updater.set-impl(composer2, measurePolicyMaybeCachedBoxMeasurePolicy, companion.getSetMeasurePolicy());
                Updater.set-impl(composer2, currentCompositionLocalMap, companion.getSetResolvedCompositionLocals());
                Updater.init-impl(composer2, Integer.valueOf(iHashCode), companion.getSetCompositeKeyHash());
                Updater.reconcile-impl(composer2, companion.getApplyOnDeactivatedNodeAssertion());
                Updater.set-impl(composer2, modifierMaterializeModifier, companion.getSetModifier());
                BoxScopeInstance boxScopeInstance = BoxScopeInstance.INSTANCE;
                if (z2) {
                    composerStartRestartGroup.startReplaceGroup(-33876356);
                    ProgressIndicatorKt.CircularProgressIndicator-4lLiAd8(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20.0f)), Color.Companion.getWhite-0d7_KjU(), Dp.constructor-impl(2.0f), 0L, 0, 0.0f, composerStartRestartGroup, 438, 56);
                    composerStartRestartGroup.endReplaceGroup();
                } else {
                    composerStartRestartGroup.startReplaceGroup(-33689085);
                    TextKt.Text-Nvy7gAk(str2, (Modifier) null, Color.Companion.getWhite-0d7_KjU(), (TextAutoSize) null, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, (i3 & 14) | 1597824, 0, 262058);
                    composerStartRestartGroup = composerStartRestartGroup;
                    composerStartRestartGroup.endReplaceGroup();
                }
                composerStartRestartGroup.endNode();
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                modifier3 = modifier4;
            } else {
                composerStartRestartGroup.skipToGroupEnd();
                modifier3 = modifier2;
            }
            scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ug9
                    @Override // kotlin.jvm.functions.Function2
                    public final Object invoke(Object obj, Object obj2) {
                        return wg9.b(str, z, z2, function0, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i3 |= 24576;
        modifier2 = modifier;
        if ((i3 & 9363) != 9362) {
            z3 = true;
        } else {
            z3 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z3, i3 & 1)) {
            if (i4 != 0) {
                modifier4 = Modifier.Companion;
            } else {
                modifier4 = modifier2;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2107478963, i3, -1, "com.yimaide.app.ui.auth.AuthPrimaryButton (LoginComponents.kt:136)");
            }
            if (z) {
                z4 = false;
            } else {
                z4 = false;
            }
            Modifier modifierClip2 = ClipKt.clip(SizeKt.height-3ABfNKs(SizeKt.fillMaxWidth$default(modifier4, 0.0f, 1, (Object) null), Dp.constructor-impl(50.0f)), RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(25.0f)));
            if (z4) {
                jColor = a;
            } else {
                jColor = ColorKt.Color(4291285758L);
            }
            Modifier modifier7 = BackgroundKt.background-bw27NRU$default(modifierClip2, jColor, (Shape) null, 2, (Object) null);
            if (z4) {
                modifier5 = ClickableKt.clickable-oSLSa3U$default(Modifier.Companion, false, (String) null, (Role) null, (MutableInteractionSource) null, function1, 15, (Object) null);
            } else {
                modifier5 = Modifier.Companion;
            }
            Modifier modifierThen2 = modifier7.then(modifier5);
            MeasurePolicy measurePolicyMaybeCachedBoxMeasurePolicy2 = BoxKt.maybeCachedBoxMeasurePolicy(Alignment.Companion.getCenter(), false);
            int iHashCode2 = Long.hashCode(ComposablesKt.getCurrentCompositeKeyHashCode(composerStartRestartGroup, 0));
            CompositionLocalMap currentCompositionLocalMap2 = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier2 = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierThen2);
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
            Updater.set-impl(composer3, measurePolicyMaybeCachedBoxMeasurePolicy2, companion2.getSetMeasurePolicy());
            Updater.set-impl(composer3, currentCompositionLocalMap2, companion2.getSetResolvedCompositionLocals());
            Updater.init-impl(composer3, Integer.valueOf(iHashCode2), companion2.getSetCompositeKeyHash());
            Updater.reconcile-impl(composer3, companion2.getApplyOnDeactivatedNodeAssertion());
            Updater.set-impl(composer3, modifierMaterializeModifier2, companion2.getSetModifier());
            BoxScopeInstance boxScopeInstance2 = BoxScopeInstance.INSTANCE;
            if (z2) {
                composerStartRestartGroup.startReplaceGroup(-33876356);
                ProgressIndicatorKt.CircularProgressIndicator-4lLiAd8(SizeKt.size-3ABfNKs(Modifier.Companion, Dp.constructor-impl(20.0f)), Color.Companion.getWhite-0d7_KjU(), Dp.constructor-impl(2.0f), 0L, 0, 0.0f, composerStartRestartGroup, 438, 56);
                composerStartRestartGroup.endReplaceGroup();
            } else {
                composerStartRestartGroup.startReplaceGroup(-33689085);
                TextKt.Text-Nvy7gAk(str2, (Modifier) null, Color.Companion.getWhite-0d7_KjU(), (TextAutoSize) null, TextUnitKt.getSp(16), (FontStyle) null, FontWeight.Companion.getSemiBold(), (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composerStartRestartGroup, (i3 & 14) | 1597824, 0, 262058);
                composerStartRestartGroup = composerStartRestartGroup;
                composerStartRestartGroup.endReplaceGroup();
            }
            composerStartRestartGroup.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            modifier3 = modifier4;
        } else {
            composerStartRestartGroup.skipToGroupEnd();
            modifier3 = modifier2;
        }
        scopeUpdateScopeEndRestartGroup = composerStartRestartGroup.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: ug9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return wg9.b(str, z, z2, function0, modifier3, i, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final void h(final boolean z, final int i, final Function0 function0, Composer composer, final int i2) {
        int i3;
        Composer composer2;
        String str;
        function0.getClass();
        Composer composerStartRestartGroup = composer.startRestartGroup(2145673811);
        if ((i2 & 6) == 0) {
            i3 = (composerStartRestartGroup.changed(z) ? 4 : 2) | i2;
        } else {
            i3 = i2;
        }
        if ((i2 & 48) == 0) {
            i3 |= composerStartRestartGroup.changed(i) ? 32 : 16;
        }
        if ((i2 & KyberEngine.KyberPolyBytes) == 0) {
            i3 |= composerStartRestartGroup.changedInstance(function0) ? 256 : CodeEditor.FLAG_DRAW_SOFT_WRAP;
        }
        boolean z2 = false;
        if (composerStartRestartGroup.shouldExecute((i3 & 147) != 146, i3 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(2145673811, i3, -1, "com.yimaide.app.ui.auth.SendCodeButton (LoginComponents.kt:112)");
            }
            boolean z3 = i > 0;
            if (z && !z3) {
                z2 = true;
            }
            if (z3) {
                str = i + "s";
            } else {
                str = "获取验证码";
            }
            String str2 = str;
            long sp = TextUnitKt.getSp(14);
            FontWeight medium = FontWeight.Companion.getMedium();
            long jColor = z2 ? a : ColorKt.Color(4288455599L);
            Modifier modifier = Modifier.Companion;
            Modifier modifierClip = ClipKt.clip(modifier, RoundedCornerShapeKt.RoundedCornerShape-0680j_4(Dp.constructor-impl(8.0f)));
            if (z2) {
                modifier = ClickableKt.clickable-oSLSa3U$default(modifier, false, (String) null, (Role) null, (MutableInteractionSource) null, function0, 15, (Object) null);
            }
            composer2 = composerStartRestartGroup;
            TextKt.Text-Nvy7gAk(str2, PaddingKt.padding-VpY3zN4(modifierClip.then(modifier), Dp.constructor-impl(4.0f), Dp.constructor-impl(4.0f)), jColor, (TextAutoSize) null, sp, (FontStyle) null, medium, (FontFamily) null, 0L, (TextDecoration) null, (TextAlign) null, 0L, 0, false, 0, 0, (Function1) null, (TextStyle) null, composer2, 1597440, 0, 262056);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: vg9
                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(Object obj, Object obj2) {
                    return wg9.d(z, i, function0, i2, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static final long i() {
        return a;
    }
}
