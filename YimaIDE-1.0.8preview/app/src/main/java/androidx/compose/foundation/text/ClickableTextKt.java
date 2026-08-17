package androidx.compose.foundation.text;

import androidx.compose.foundation.text.ClickableTextKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.pointer.PointerInputEventHandler;
import androidx.compose.ui.input.pointer.SuspendingPointerInputFilterKt;
import androidx.compose.ui.text.AnnotatedString;
import androidx.compose.ui.text.TextLayoutResult;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.style.TextOverflow;
import com.intellij.util.io.IOUtil;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001as\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\t2\b\b\u0002\u0010\n\u001a\u00020\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\u0014\b\u0002\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00010\u000f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00010\u000fH\u0007¢\u0006\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"ClickableText", "", "text", "Landroidx/compose/ui/text/AnnotatedString;", "modifier", "Landroidx/compose/ui/Modifier;", "style", "Landroidx/compose/ui/text/TextStyle;", "softWrap", "", "overflow", "Landroidx/compose/ui/text/style/TextOverflow;", "maxLines", "", "onTextLayout", "Lkotlin/Function1;", "Landroidx/compose/ui/text/TextLayoutResult;", "onClick", "ClickableText-4YKlhWE", "(Landroidx/compose/ui/text/AnnotatedString;Landroidx/compose/ui/Modifier;Landroidx/compose/ui/text/TextStyle;ZIILkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/runtime/Composer;II)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class ClickableTextKt {
    /* JADX WARN: Code duplicated, block: B:100:0x0122  */
    /* JADX WARN: Code duplicated, block: B:102:0x0128  */
    /* JADX WARN: Code duplicated, block: B:104:0x0134  */
    /* JADX WARN: Code duplicated, block: B:106:0x013f  */
    /* JADX WARN: Code duplicated, block: B:109:0x0147  */
    /* JADX WARN: Code duplicated, block: B:112:0x015c  */
    /* JADX WARN: Code duplicated, block: B:115:0x0170  */
    /* JADX WARN: Code duplicated, block: B:116:0x0173  */
    /* JADX WARN: Code duplicated, block: B:119:0x017b  */
    /* JADX WARN: Code duplicated, block: B:121:0x0181  */
    /* JADX WARN: Code duplicated, block: B:124:0x019b  */
    /* JADX WARN: Code duplicated, block: B:127:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:129:0x01a9  */
    /* JADX WARN: Code duplicated, block: B:132:0x01da  */
    /* JADX WARN: Code duplicated, block: B:135:0x01e5  */
    /* JADX WARN: Code duplicated, block: B:138:0x01f8  */
    /* JADX WARN: Code duplicated, block: B:140:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:23:0x003e  */
    /* JADX WARN: Code duplicated, block: B:25:0x0043  */
    /* JADX WARN: Code duplicated, block: B:27:0x0047  */
    /* JADX WARN: Code duplicated, block: B:29:0x004f  */
    /* JADX WARN: Code duplicated, block: B:30:0x0052  */
    /* JADX WARN: Code duplicated, block: B:34:0x0059  */
    /* JADX WARN: Code duplicated, block: B:36:0x005e  */
    /* JADX WARN: Code duplicated, block: B:38:0x0062  */
    /* JADX WARN: Code duplicated, block: B:40:0x006a  */
    /* JADX WARN: Code duplicated, block: B:41:0x006d  */
    /* JADX WARN: Code duplicated, block: B:45:0x0074  */
    /* JADX WARN: Code duplicated, block: B:47:0x0079  */
    /* JADX WARN: Code duplicated, block: B:49:0x007d  */
    /* JADX WARN: Code duplicated, block: B:51:0x0085  */
    /* JADX WARN: Code duplicated, block: B:52:0x0088  */
    /* JADX WARN: Code duplicated, block: B:56:0x0091  */
    /* JADX WARN: Code duplicated, block: B:57:0x0096  */
    /* JADX WARN: Code duplicated, block: B:59:0x009c  */
    /* JADX WARN: Code duplicated, block: B:61:0x00a2  */
    /* JADX WARN: Code duplicated, block: B:62:0x00a5  */
    /* JADX WARN: Code duplicated, block: B:66:0x00af  */
    /* JADX WARN: Code duplicated, block: B:67:0x00b4  */
    /* JADX WARN: Code duplicated, block: B:69:0x00ba  */
    /* JADX WARN: Code duplicated, block: B:71:0x00c0  */
    /* JADX WARN: Code duplicated, block: B:72:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:76:0x00cd  */
    /* JADX WARN: Code duplicated, block: B:78:0x00d3  */
    /* JADX WARN: Code duplicated, block: B:79:0x00d6  */
    /* JADX WARN: Code duplicated, block: B:83:0x00e8  */
    /* JADX WARN: Code duplicated, block: B:84:0x00eb  */
    /* JADX WARN: Code duplicated, block: B:87:0x00f5 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:88:0x00f7  */
    /* JADX WARN: Code duplicated, block: B:90:0x00fc  */
    /* JADX WARN: Code duplicated, block: B:91:0x0104  */
    /* JADX WARN: Code duplicated, block: B:94:0x0108  */
    /* JADX WARN: Code duplicated, block: B:95:0x010b  */
    /* JADX WARN: Code duplicated, block: B:97:0x010f  */
    /* JADX WARN: Code duplicated, block: B:99:0x0118  */
    @Deprecated(message = "Use Text or BasicText and pass an AnnotatedString that contains a LinkAnnotation. Check LinkAnnotation's documentation for more details and samples.")
    /* JADX INFO: renamed from: ClickableText-4YKlhWE, reason: not valid java name */
    public static final void m1317ClickableText4YKlhWE(final AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, boolean z, int i, int i2, Function1<? super TextLayoutResult, Unit> function1, final Function1<? super Integer, Unit> function2, Composer composer, final int i3, final int i4) {
        int i5;
        Modifier modifier2;
        int i6;
        TextStyle textStyle2;
        int i7;
        int i8;
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        int i14;
        int i15;
        int i16;
        boolean z2;
        Composer composer2;
        final boolean z3;
        final int i17;
        final TextStyle textStyle3;
        final Function1<? super TextLayoutResult, Unit> function3;
        final Modifier modifier3;
        final int i18;
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup;
        TextStyle textStyle4;
        boolean z4;
        int i19;
        int i20;
        final Function1<? super TextLayoutResult, Unit> function4;
        Object objRememberedValue;
        Composer.Companion companion;
        final MutableState mutableState;
        boolean z5;
        Object objRememberedValue2;
        boolean z6;
        Object objRememberedValue3;
        Object objRememberedValue4;
        int i21;
        Composer composerStartRestartGroup = composer.startRestartGroup(-246609449);
        if ((i3 & 6) == 0) {
            i5 = (composerStartRestartGroup.changed(annotatedString) ? 4 : 2) | i3;
        } else {
            i5 = i3;
        }
        int i22 = i4 & 2;
        if (i22 == 0) {
            if ((i3 & 48) == 0) {
                modifier2 = modifier;
                i5 |= composerStartRestartGroup.changed(modifier2) ? 32 : 16;
            }
            i6 = i4 & 4;
            if (i6 != 0) {
                if ((i3 & 384) == 0) {
                    textStyle2 = textStyle;
                    if (composerStartRestartGroup.changed(textStyle2)) {
                        i7 = 256;
                    } else {
                        i7 = 128;
                    }
                    i5 |= i7;
                }
                i8 = i4 & 8;
                if (i8 != 0) {
                    if ((i3 & 3072) == 0) {
                        if (composerStartRestartGroup.changed(z)) {
                            i9 = 2048;
                        } else {
                            i9 = 1024;
                        }
                        i5 |= i9;
                    }
                    i10 = i4 & 16;
                    if (i10 != 0) {
                        if ((i3 & 24576) == 0) {
                            i11 = i;
                            if (composerStartRestartGroup.changed(i11)) {
                                i12 = 16384;
                            } else {
                                i12 = 8192;
                            }
                            i5 |= i12;
                        }
                        i13 = i4 & 32;
                        if (i13 != 0) {
                            i5 |= 196608;
                        } else if ((i3 & 196608) == 0) {
                            if (composerStartRestartGroup.changed(i2)) {
                                i14 = 131072;
                            } else {
                                i14 = 65536;
                            }
                            i5 |= i14;
                        }
                        i15 = i4 & 64;
                        if (i15 != 0) {
                            i5 |= 1572864;
                        } else if ((i3 & 1572864) == 0) {
                            if (composerStartRestartGroup.changedInstance(function1)) {
                                i16 = IOUtil.MiB;
                            } else {
                                i16 = 524288;
                            }
                            i5 |= i16;
                        }
                        if ((i3 & 12582912) == 0) {
                            if (composerStartRestartGroup.changedInstance(function2)) {
                                i21 = 8388608;
                            } else {
                                i21 = 4194304;
                            }
                            i5 |= i21;
                        }
                        if ((i5 & 4793491) != 4793490) {
                            z2 = true;
                        } else {
                            z2 = false;
                        }
                        if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                            if (i22 != 0) {
                                modifier2 = Modifier.Companion;
                            }
                            if (i6 != 0) {
                                textStyle4 = TextStyle.Companion.getDefault();
                            } else {
                                textStyle4 = textStyle2;
                            }
                            if (i8 != 0) {
                                z4 = true;
                            } else {
                                z4 = z;
                            }
                            if (i10 != 0) {
                                i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                            }
                            if (i13 != 0) {
                                i20 = Integer.MAX_VALUE;
                                i19 = i15;
                            } else {
                                i19 = i15;
                                i20 = i2;
                            }
                            if (i19 != 0) {
                                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                                if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                                    objRememberedValue4 = new Function1() { // from class: q02
                                        public final Object invoke(Object obj) {
                                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                        }
                                    };
                                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                                }
                                function4 = (Function1) objRememberedValue4;
                            } else {
                                function4 = function1;
                            }
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                            }
                            objRememberedValue = composerStartRestartGroup.rememberedValue();
                            companion = Composer.Companion;
                            if (objRememberedValue == companion.getEmpty()) {
                                objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                            }
                            mutableState = (MutableState) objRememberedValue;
                            Modifier.Companion companion2 = Modifier.Companion;
                            if ((29360128 & i5) == 8388608) {
                                z5 = true;
                            } else {
                                z5 = false;
                            }
                            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                            if (z5 || objRememberedValue2 == companion.getEmpty()) {
                                objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                            }
                            Modifier modifierThen = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion2, function2, (PointerInputEventHandler) objRememberedValue2));
                            z6 = (i5 & 3670016) == 1048576;
                            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                            if (z6 || objRememberedValue3 == companion.getEmpty()) {
                                objRememberedValue3 = new Function1() { // from class: r02
                                    public final Object invoke(Object obj) {
                                        return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                            }
                            composer2 = composerStartRestartGroup;
                            BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                            if (ComposerKt.isTraceInProgress()) {
                                ComposerKt.traceEventEnd();
                            }
                            function3 = function4;
                            textStyle3 = textStyle4;
                            z3 = z4;
                            i17 = i20;
                        } else {
                            composer2 = composerStartRestartGroup;
                            composer2.skipToGroupEnd();
                            z3 = z;
                            i17 = i2;
                            textStyle3 = textStyle2;
                            function3 = function1;
                        }
                        modifier3 = modifier2;
                        i18 = i11;
                        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                        if (scopeUpdateScopeEndRestartGroup != null) {
                            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                                public final Object invoke(Object obj, Object obj2) {
                                    return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                                }
                            });
                        }
                    }
                    i5 |= 24576;
                    i11 = i;
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i21 = 8388608;
                        } else {
                            i21 = 4194304;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                        if (i22 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i6 != 0) {
                            textStyle4 = TextStyle.Companion.getDefault();
                        } else {
                            textStyle4 = textStyle2;
                        }
                        if (i8 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i10 != 0) {
                            i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                            i19 = i15;
                        } else {
                            i19 = i15;
                            i20 = i2;
                        }
                        if (i19 != 0) {
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                                objRememberedValue4 = new Function1() { // from class: q02
                                    public final Object invoke(Object obj) {
                                        return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            function4 = (Function1) objRememberedValue4;
                        } else {
                            function4 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.Companion;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        Modifier.Companion companion3 = Modifier.Companion;
                        if ((29360128 & i5) == 8388608) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierThen2 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion3, function2, (PointerInputEventHandler) objRememberedValue2));
                        if ((i5 & 3670016) == 1048576) {
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue3 = new Function1() { // from class: r02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1() { // from class: r02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        composer2 = composerStartRestartGroup;
                        BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen2, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = function4;
                        textStyle3 = textStyle4;
                        z3 = z4;
                        i17 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z3 = z;
                        i17 = i2;
                        textStyle3 = textStyle2;
                        function3 = function1;
                    }
                    modifier3 = modifier2;
                    i18 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                            public final Object invoke(Object obj, Object obj2) {
                                return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 3072;
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i21 = 8388608;
                        } else {
                            i21 = 4194304;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                        if (i22 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i6 != 0) {
                            textStyle4 = TextStyle.Companion.getDefault();
                        } else {
                            textStyle4 = textStyle2;
                        }
                        if (i8 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i10 != 0) {
                            i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                            i19 = i15;
                        } else {
                            i19 = i15;
                            i20 = i2;
                        }
                        if (i19 != 0) {
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                                objRememberedValue4 = new Function1() { // from class: q02
                                    public final Object invoke(Object obj) {
                                        return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            function4 = (Function1) objRememberedValue4;
                        } else {
                            function4 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.Companion;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        Modifier.Companion companion4 = Modifier.Companion;
                        if ((29360128 & i5) == 8388608) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierThen3 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion4, function2, (PointerInputEventHandler) objRememberedValue2));
                        if ((i5 & 3670016) == 1048576) {
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue3 = new Function1() { // from class: r02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1() { // from class: r02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        composer2 = composerStartRestartGroup;
                        BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen3, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = function4;
                        textStyle3 = textStyle4;
                        z3 = z4;
                        i17 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z3 = z;
                        i17 = i2;
                        textStyle3 = textStyle2;
                        function3 = function1;
                    }
                    modifier3 = modifier2;
                    i18 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                            public final Object invoke(Object obj, Object obj2) {
                                return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i21 = 8388608;
                    } else {
                        i21 = 4194304;
                    }
                    i5 |= i21;
                }
                if ((i5 & 4793491) != 4793490) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                    if (i22 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i6 != 0) {
                        textStyle4 = TextStyle.Companion.getDefault();
                    } else {
                        textStyle4 = textStyle2;
                    }
                    if (i8 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i10 != 0) {
                        i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                        i19 = i15;
                    } else {
                        i19 = i15;
                        i20 = i2;
                    }
                    if (i19 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function1() { // from class: q02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function4 = (Function1) objRememberedValue4;
                    } else {
                        function4 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    Modifier.Companion companion5 = Modifier.Companion;
                    if ((29360128 & i5) == 8388608) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierThen4 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion5, function2, (PointerInputEventHandler) objRememberedValue2));
                    if ((i5 & 3670016) == 1048576) {
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    composer2 = composerStartRestartGroup;
                    BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen4, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = function4;
                    textStyle3 = textStyle4;
                    z3 = z4;
                    i17 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z3 = z;
                    i17 = i2;
                    textStyle3 = textStyle2;
                    function3 = function1;
                }
                modifier3 = modifier2;
                i18 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                        public final Object invoke(Object obj, Object obj2) {
                            return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 384;
            textStyle2 = textStyle;
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(z)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i21 = 8388608;
                        } else {
                            i21 = 4194304;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                        if (i22 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i6 != 0) {
                            textStyle4 = TextStyle.Companion.getDefault();
                        } else {
                            textStyle4 = textStyle2;
                        }
                        if (i8 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i10 != 0) {
                            i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                            i19 = i15;
                        } else {
                            i19 = i15;
                            i20 = i2;
                        }
                        if (i19 != 0) {
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                                objRememberedValue4 = new Function1() { // from class: q02
                                    public final Object invoke(Object obj) {
                                        return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            function4 = (Function1) objRememberedValue4;
                        } else {
                            function4 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.Companion;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        Modifier.Companion companion6 = Modifier.Companion;
                        if ((29360128 & i5) == 8388608) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierThen5 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion6, function2, (PointerInputEventHandler) objRememberedValue2));
                        if ((i5 & 3670016) == 1048576) {
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue3 = new Function1() { // from class: r02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1() { // from class: r02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        composer2 = composerStartRestartGroup;
                        BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen5, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = function4;
                        textStyle3 = textStyle4;
                        z3 = z4;
                        i17 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z3 = z;
                        i17 = i2;
                        textStyle3 = textStyle2;
                        function3 = function1;
                    }
                    modifier3 = modifier2;
                    i18 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                            public final Object invoke(Object obj, Object obj2) {
                                return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i21 = 8388608;
                    } else {
                        i21 = 4194304;
                    }
                    i5 |= i21;
                }
                if ((i5 & 4793491) != 4793490) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                    if (i22 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i6 != 0) {
                        textStyle4 = TextStyle.Companion.getDefault();
                    } else {
                        textStyle4 = textStyle2;
                    }
                    if (i8 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i10 != 0) {
                        i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                        i19 = i15;
                    } else {
                        i19 = i15;
                        i20 = i2;
                    }
                    if (i19 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function1() { // from class: q02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function4 = (Function1) objRememberedValue4;
                    } else {
                        function4 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    Modifier.Companion companion7 = Modifier.Companion;
                    if ((29360128 & i5) == 8388608) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierThen6 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion7, function2, (PointerInputEventHandler) objRememberedValue2));
                    if ((i5 & 3670016) == 1048576) {
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    composer2 = composerStartRestartGroup;
                    BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen6, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = function4;
                    textStyle3 = textStyle4;
                    z3 = z4;
                    i17 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z3 = z;
                    i17 = i2;
                    textStyle3 = textStyle2;
                    function3 = function1;
                }
                modifier3 = modifier2;
                i18 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                        public final Object invoke(Object obj, Object obj2) {
                            return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i21 = 8388608;
                    } else {
                        i21 = 4194304;
                    }
                    i5 |= i21;
                }
                if ((i5 & 4793491) != 4793490) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                    if (i22 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i6 != 0) {
                        textStyle4 = TextStyle.Companion.getDefault();
                    } else {
                        textStyle4 = textStyle2;
                    }
                    if (i8 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i10 != 0) {
                        i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                        i19 = i15;
                    } else {
                        i19 = i15;
                        i20 = i2;
                    }
                    if (i19 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function1() { // from class: q02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function4 = (Function1) objRememberedValue4;
                    } else {
                        function4 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    Modifier.Companion companion8 = Modifier.Companion;
                    if ((29360128 & i5) == 8388608) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierThen7 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion8, function2, (PointerInputEventHandler) objRememberedValue2));
                    if ((i5 & 3670016) == 1048576) {
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    composer2 = composerStartRestartGroup;
                    BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen7, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = function4;
                    textStyle3 = textStyle4;
                    z3 = z4;
                    i17 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z3 = z;
                    i17 = i2;
                    textStyle3 = textStyle2;
                    function3 = function1;
                }
                modifier3 = modifier2;
                i18 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                        public final Object invoke(Object obj, Object obj2) {
                            return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i21 = 8388608;
                } else {
                    i21 = 4194304;
                }
                i5 |= i21;
            }
            if ((i5 & 4793491) != 4793490) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                if (i22 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i6 != 0) {
                    textStyle4 = TextStyle.Companion.getDefault();
                } else {
                    textStyle4 = textStyle2;
                }
                if (i8 != 0) {
                    z4 = true;
                } else {
                    z4 = z;
                }
                if (i10 != 0) {
                    i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                    i19 = i15;
                } else {
                    i19 = i15;
                    i20 = i2;
                }
                if (i19 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function1() { // from class: q02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function4 = (Function1) objRememberedValue4;
                } else {
                    function4 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                Modifier.Companion companion9 = Modifier.Companion;
                if ((29360128 & i5) == 8388608) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierThen8 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion9, function2, (PointerInputEventHandler) objRememberedValue2));
                if ((i5 & 3670016) == 1048576) {
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    objRememberedValue3 = new Function1() { // from class: r02
                        public final Object invoke(Object obj) {
                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: r02
                        public final Object invoke(Object obj) {
                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                composer2 = composerStartRestartGroup;
                BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen8, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function4;
                textStyle3 = textStyle4;
                z3 = z4;
                i17 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z3 = z;
                i17 = i2;
                textStyle3 = textStyle2;
                function3 = function1;
            }
            modifier3 = modifier2;
            i18 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                    public final Object invoke(Object obj, Object obj2) {
                        return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 48;
        modifier2 = modifier;
        i6 = i4 & 4;
        if (i6 != 0) {
            if ((i3 & 384) == 0) {
                textStyle2 = textStyle;
                if (composerStartRestartGroup.changed(textStyle2)) {
                    i7 = 256;
                } else {
                    i7 = 128;
                }
                i5 |= i7;
            }
            i8 = i4 & 8;
            if (i8 != 0) {
                if ((i3 & 3072) == 0) {
                    if (composerStartRestartGroup.changed(z)) {
                        i9 = 2048;
                    } else {
                        i9 = 1024;
                    }
                    i5 |= i9;
                }
                i10 = i4 & 16;
                if (i10 != 0) {
                    if ((i3 & 24576) == 0) {
                        i11 = i;
                        if (composerStartRestartGroup.changed(i11)) {
                            i12 = 16384;
                        } else {
                            i12 = 8192;
                        }
                        i5 |= i12;
                    }
                    i13 = i4 & 32;
                    if (i13 != 0) {
                        i5 |= 196608;
                    } else if ((i3 & 196608) == 0) {
                        if (composerStartRestartGroup.changed(i2)) {
                            i14 = 131072;
                        } else {
                            i14 = 65536;
                        }
                        i5 |= i14;
                    }
                    i15 = i4 & 64;
                    if (i15 != 0) {
                        i5 |= 1572864;
                    } else if ((i3 & 1572864) == 0) {
                        if (composerStartRestartGroup.changedInstance(function1)) {
                            i16 = IOUtil.MiB;
                        } else {
                            i16 = 524288;
                        }
                        i5 |= i16;
                    }
                    if ((i3 & 12582912) == 0) {
                        if (composerStartRestartGroup.changedInstance(function2)) {
                            i21 = 8388608;
                        } else {
                            i21 = 4194304;
                        }
                        i5 |= i21;
                    }
                    if ((i5 & 4793491) != 4793490) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                        if (i22 != 0) {
                            modifier2 = Modifier.Companion;
                        }
                        if (i6 != 0) {
                            textStyle4 = TextStyle.Companion.getDefault();
                        } else {
                            textStyle4 = textStyle2;
                        }
                        if (i8 != 0) {
                            z4 = true;
                        } else {
                            z4 = z;
                        }
                        if (i10 != 0) {
                            i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                        }
                        if (i13 != 0) {
                            i20 = Integer.MAX_VALUE;
                            i19 = i15;
                        } else {
                            i19 = i15;
                            i20 = i2;
                        }
                        if (i19 != 0) {
                            objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                            if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                                objRememberedValue4 = new Function1() { // from class: q02
                                    public final Object invoke(Object obj) {
                                        return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                    }
                                };
                                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                            }
                            function4 = (Function1) objRememberedValue4;
                        } else {
                            function4 = function1;
                        }
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                        }
                        objRememberedValue = composerStartRestartGroup.rememberedValue();
                        companion = Composer.Companion;
                        if (objRememberedValue == companion.getEmpty()) {
                            objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                        }
                        mutableState = (MutableState) objRememberedValue;
                        Modifier.Companion companion10 = Modifier.Companion;
                        if ((29360128 & i5) == 8388608) {
                            z5 = true;
                        } else {
                            z5 = false;
                        }
                        objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                        if (z5) {
                            objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        } else {
                            objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                        }
                        Modifier modifierThen9 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion10, function2, (PointerInputEventHandler) objRememberedValue2));
                        if ((i5 & 3670016) == 1048576) {
                        }
                        objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                        if (z6) {
                            objRememberedValue3 = new Function1() { // from class: r02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        } else {
                            objRememberedValue3 = new Function1() { // from class: r02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                        }
                        composer2 = composerStartRestartGroup;
                        BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen9, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                        if (ComposerKt.isTraceInProgress()) {
                            ComposerKt.traceEventEnd();
                        }
                        function3 = function4;
                        textStyle3 = textStyle4;
                        z3 = z4;
                        i17 = i20;
                    } else {
                        composer2 = composerStartRestartGroup;
                        composer2.skipToGroupEnd();
                        z3 = z;
                        i17 = i2;
                        textStyle3 = textStyle2;
                        function3 = function1;
                    }
                    modifier3 = modifier2;
                    i18 = i11;
                    scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                    if (scopeUpdateScopeEndRestartGroup != null) {
                        scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                            public final Object invoke(Object obj, Object obj2) {
                                return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                            }
                        });
                    }
                }
                i5 |= 24576;
                i11 = i;
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i21 = 8388608;
                    } else {
                        i21 = 4194304;
                    }
                    i5 |= i21;
                }
                if ((i5 & 4793491) != 4793490) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                    if (i22 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i6 != 0) {
                        textStyle4 = TextStyle.Companion.getDefault();
                    } else {
                        textStyle4 = textStyle2;
                    }
                    if (i8 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i10 != 0) {
                        i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                        i19 = i15;
                    } else {
                        i19 = i15;
                        i20 = i2;
                    }
                    if (i19 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function1() { // from class: q02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function4 = (Function1) objRememberedValue4;
                    } else {
                        function4 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    Modifier.Companion companion11 = Modifier.Companion;
                    if ((29360128 & i5) == 8388608) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierThen10 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion11, function2, (PointerInputEventHandler) objRememberedValue2));
                    if ((i5 & 3670016) == 1048576) {
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    composer2 = composerStartRestartGroup;
                    BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen10, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = function4;
                    textStyle3 = textStyle4;
                    z3 = z4;
                    i17 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z3 = z;
                    i17 = i2;
                    textStyle3 = textStyle2;
                    function3 = function1;
                }
                modifier3 = modifier2;
                i18 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                        public final Object invoke(Object obj, Object obj2) {
                            return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 3072;
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i21 = 8388608;
                    } else {
                        i21 = 4194304;
                    }
                    i5 |= i21;
                }
                if ((i5 & 4793491) != 4793490) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                    if (i22 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i6 != 0) {
                        textStyle4 = TextStyle.Companion.getDefault();
                    } else {
                        textStyle4 = textStyle2;
                    }
                    if (i8 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i10 != 0) {
                        i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                        i19 = i15;
                    } else {
                        i19 = i15;
                        i20 = i2;
                    }
                    if (i19 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function1() { // from class: q02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function4 = (Function1) objRememberedValue4;
                    } else {
                        function4 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    Modifier.Companion companion12 = Modifier.Companion;
                    if ((29360128 & i5) == 8388608) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierThen11 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion12, function2, (PointerInputEventHandler) objRememberedValue2));
                    if ((i5 & 3670016) == 1048576) {
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    composer2 = composerStartRestartGroup;
                    BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen11, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = function4;
                    textStyle3 = textStyle4;
                    z3 = z4;
                    i17 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z3 = z;
                    i17 = i2;
                    textStyle3 = textStyle2;
                    function3 = function1;
                }
                modifier3 = modifier2;
                i18 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                        public final Object invoke(Object obj, Object obj2) {
                            return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i21 = 8388608;
                } else {
                    i21 = 4194304;
                }
                i5 |= i21;
            }
            if ((i5 & 4793491) != 4793490) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                if (i22 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i6 != 0) {
                    textStyle4 = TextStyle.Companion.getDefault();
                } else {
                    textStyle4 = textStyle2;
                }
                if (i8 != 0) {
                    z4 = true;
                } else {
                    z4 = z;
                }
                if (i10 != 0) {
                    i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                    i19 = i15;
                } else {
                    i19 = i15;
                    i20 = i2;
                }
                if (i19 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function1() { // from class: q02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function4 = (Function1) objRememberedValue4;
                } else {
                    function4 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                Modifier.Companion companion13 = Modifier.Companion;
                if ((29360128 & i5) == 8388608) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierThen12 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion13, function2, (PointerInputEventHandler) objRememberedValue2));
                if ((i5 & 3670016) == 1048576) {
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    objRememberedValue3 = new Function1() { // from class: r02
                        public final Object invoke(Object obj) {
                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: r02
                        public final Object invoke(Object obj) {
                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                composer2 = composerStartRestartGroup;
                BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen12, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function4;
                textStyle3 = textStyle4;
                z3 = z4;
                i17 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z3 = z;
                i17 = i2;
                textStyle3 = textStyle2;
                function3 = function1;
            }
            modifier3 = modifier2;
            i18 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                    public final Object invoke(Object obj, Object obj2) {
                        return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 384;
        textStyle2 = textStyle;
        i8 = i4 & 8;
        if (i8 != 0) {
            if ((i3 & 3072) == 0) {
                if (composerStartRestartGroup.changed(z)) {
                    i9 = 2048;
                } else {
                    i9 = 1024;
                }
                i5 |= i9;
            }
            i10 = i4 & 16;
            if (i10 != 0) {
                if ((i3 & 24576) == 0) {
                    i11 = i;
                    if (composerStartRestartGroup.changed(i11)) {
                        i12 = 16384;
                    } else {
                        i12 = 8192;
                    }
                    i5 |= i12;
                }
                i13 = i4 & 32;
                if (i13 != 0) {
                    i5 |= 196608;
                } else if ((i3 & 196608) == 0) {
                    if (composerStartRestartGroup.changed(i2)) {
                        i14 = 131072;
                    } else {
                        i14 = 65536;
                    }
                    i5 |= i14;
                }
                i15 = i4 & 64;
                if (i15 != 0) {
                    i5 |= 1572864;
                } else if ((i3 & 1572864) == 0) {
                    if (composerStartRestartGroup.changedInstance(function1)) {
                        i16 = IOUtil.MiB;
                    } else {
                        i16 = 524288;
                    }
                    i5 |= i16;
                }
                if ((i3 & 12582912) == 0) {
                    if (composerStartRestartGroup.changedInstance(function2)) {
                        i21 = 8388608;
                    } else {
                        i21 = 4194304;
                    }
                    i5 |= i21;
                }
                if ((i5 & 4793491) != 4793490) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                    if (i22 != 0) {
                        modifier2 = Modifier.Companion;
                    }
                    if (i6 != 0) {
                        textStyle4 = TextStyle.Companion.getDefault();
                    } else {
                        textStyle4 = textStyle2;
                    }
                    if (i8 != 0) {
                        z4 = true;
                    } else {
                        z4 = z;
                    }
                    if (i10 != 0) {
                        i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                    }
                    if (i13 != 0) {
                        i20 = Integer.MAX_VALUE;
                        i19 = i15;
                    } else {
                        i19 = i15;
                        i20 = i2;
                    }
                    if (i19 != 0) {
                        objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                        if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                            objRememberedValue4 = new Function1() { // from class: q02
                                public final Object invoke(Object obj) {
                                    return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                                }
                            };
                            composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                        }
                        function4 = (Function1) objRememberedValue4;
                    } else {
                        function4 = function1;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                    }
                    objRememberedValue = composerStartRestartGroup.rememberedValue();
                    companion = Composer.Companion;
                    if (objRememberedValue == companion.getEmpty()) {
                        objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                    }
                    mutableState = (MutableState) objRememberedValue;
                    Modifier.Companion companion14 = Modifier.Companion;
                    if ((29360128 & i5) == 8388608) {
                        z5 = true;
                    } else {
                        z5 = false;
                    }
                    objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                    if (z5) {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    } else {
                        objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                    }
                    Modifier modifierThen13 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion14, function2, (PointerInputEventHandler) objRememberedValue2));
                    if ((i5 & 3670016) == 1048576) {
                    }
                    objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                    if (z6) {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    } else {
                        objRememberedValue3 = new Function1() { // from class: r02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                    }
                    composer2 = composerStartRestartGroup;
                    BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen13, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                    function3 = function4;
                    textStyle3 = textStyle4;
                    z3 = z4;
                    i17 = i20;
                } else {
                    composer2 = composerStartRestartGroup;
                    composer2.skipToGroupEnd();
                    z3 = z;
                    i17 = i2;
                    textStyle3 = textStyle2;
                    function3 = function1;
                }
                modifier3 = modifier2;
                i18 = i11;
                scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
                if (scopeUpdateScopeEndRestartGroup != null) {
                    scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                        public final Object invoke(Object obj, Object obj2) {
                            return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                        }
                    });
                }
            }
            i5 |= 24576;
            i11 = i;
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i21 = 8388608;
                } else {
                    i21 = 4194304;
                }
                i5 |= i21;
            }
            if ((i5 & 4793491) != 4793490) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                if (i22 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i6 != 0) {
                    textStyle4 = TextStyle.Companion.getDefault();
                } else {
                    textStyle4 = textStyle2;
                }
                if (i8 != 0) {
                    z4 = true;
                } else {
                    z4 = z;
                }
                if (i10 != 0) {
                    i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                    i19 = i15;
                } else {
                    i19 = i15;
                    i20 = i2;
                }
                if (i19 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function1() { // from class: q02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function4 = (Function1) objRememberedValue4;
                } else {
                    function4 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                Modifier.Companion companion15 = Modifier.Companion;
                if ((29360128 & i5) == 8388608) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierThen14 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion15, function2, (PointerInputEventHandler) objRememberedValue2));
                if ((i5 & 3670016) == 1048576) {
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    objRememberedValue3 = new Function1() { // from class: r02
                        public final Object invoke(Object obj) {
                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: r02
                        public final Object invoke(Object obj) {
                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                composer2 = composerStartRestartGroup;
                BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen14, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function4;
                textStyle3 = textStyle4;
                z3 = z4;
                i17 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z3 = z;
                i17 = i2;
                textStyle3 = textStyle2;
                function3 = function1;
            }
            modifier3 = modifier2;
            i18 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                    public final Object invoke(Object obj, Object obj2) {
                        return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 3072;
        i10 = i4 & 16;
        if (i10 != 0) {
            if ((i3 & 24576) == 0) {
                i11 = i;
                if (composerStartRestartGroup.changed(i11)) {
                    i12 = 16384;
                } else {
                    i12 = 8192;
                }
                i5 |= i12;
            }
            i13 = i4 & 32;
            if (i13 != 0) {
                i5 |= 196608;
            } else if ((i3 & 196608) == 0) {
                if (composerStartRestartGroup.changed(i2)) {
                    i14 = 131072;
                } else {
                    i14 = 65536;
                }
                i5 |= i14;
            }
            i15 = i4 & 64;
            if (i15 != 0) {
                i5 |= 1572864;
            } else if ((i3 & 1572864) == 0) {
                if (composerStartRestartGroup.changedInstance(function1)) {
                    i16 = IOUtil.MiB;
                } else {
                    i16 = 524288;
                }
                i5 |= i16;
            }
            if ((i3 & 12582912) == 0) {
                if (composerStartRestartGroup.changedInstance(function2)) {
                    i21 = 8388608;
                } else {
                    i21 = 4194304;
                }
                i5 |= i21;
            }
            if ((i5 & 4793491) != 4793490) {
                z2 = true;
            } else {
                z2 = false;
            }
            if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
                if (i22 != 0) {
                    modifier2 = Modifier.Companion;
                }
                if (i6 != 0) {
                    textStyle4 = TextStyle.Companion.getDefault();
                } else {
                    textStyle4 = textStyle2;
                }
                if (i8 != 0) {
                    z4 = true;
                } else {
                    z4 = z;
                }
                if (i10 != 0) {
                    i11 = TextOverflow.Companion.getClip-gIe3tQ8();
                }
                if (i13 != 0) {
                    i20 = Integer.MAX_VALUE;
                    i19 = i15;
                } else {
                    i19 = i15;
                    i20 = i2;
                }
                if (i19 != 0) {
                    objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                    if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                        objRememberedValue4 = new Function1() { // from class: q02
                            public final Object invoke(Object obj) {
                                return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                            }
                        };
                        composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                    }
                    function4 = (Function1) objRememberedValue4;
                } else {
                    function4 = function1;
                }
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
                }
                objRememberedValue = composerStartRestartGroup.rememberedValue();
                companion = Composer.Companion;
                if (objRememberedValue == companion.getEmpty()) {
                    objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue);
                }
                mutableState = (MutableState) objRememberedValue;
                Modifier.Companion companion16 = Modifier.Companion;
                if ((29360128 & i5) == 8388608) {
                    z5 = true;
                } else {
                    z5 = false;
                }
                objRememberedValue2 = composerStartRestartGroup.rememberedValue();
                if (z5) {
                    objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                } else {
                    objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
                }
                Modifier modifierThen15 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion16, function2, (PointerInputEventHandler) objRememberedValue2));
                if ((i5 & 3670016) == 1048576) {
                }
                objRememberedValue3 = composerStartRestartGroup.rememberedValue();
                if (z6) {
                    objRememberedValue3 = new Function1() { // from class: r02
                        public final Object invoke(Object obj) {
                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                } else {
                    objRememberedValue3 = new Function1() { // from class: r02
                        public final Object invoke(Object obj) {
                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
                }
                composer2 = composerStartRestartGroup;
                BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen15, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
                if (ComposerKt.isTraceInProgress()) {
                    ComposerKt.traceEventEnd();
                }
                function3 = function4;
                textStyle3 = textStyle4;
                z3 = z4;
                i17 = i20;
            } else {
                composer2 = composerStartRestartGroup;
                composer2.skipToGroupEnd();
                z3 = z;
                i17 = i2;
                textStyle3 = textStyle2;
                function3 = function1;
            }
            modifier3 = modifier2;
            i18 = i11;
            scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
            if (scopeUpdateScopeEndRestartGroup != null) {
                scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                    public final Object invoke(Object obj, Object obj2) {
                        return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                    }
                });
            }
        }
        i5 |= 24576;
        i11 = i;
        i13 = i4 & 32;
        if (i13 != 0) {
            i5 |= 196608;
        } else if ((i3 & 196608) == 0) {
            if (composerStartRestartGroup.changed(i2)) {
                i14 = 131072;
            } else {
                i14 = 65536;
            }
            i5 |= i14;
        }
        i15 = i4 & 64;
        if (i15 != 0) {
            i5 |= 1572864;
        } else if ((i3 & 1572864) == 0) {
            if (composerStartRestartGroup.changedInstance(function1)) {
                i16 = IOUtil.MiB;
            } else {
                i16 = 524288;
            }
            i5 |= i16;
        }
        if ((i3 & 12582912) == 0) {
            if (composerStartRestartGroup.changedInstance(function2)) {
                i21 = 8388608;
            } else {
                i21 = 4194304;
            }
            i5 |= i21;
        }
        if ((i5 & 4793491) != 4793490) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (composerStartRestartGroup.shouldExecute(z2, i5 & 1)) {
            if (i22 != 0) {
                modifier2 = Modifier.Companion;
            }
            if (i6 != 0) {
                textStyle4 = TextStyle.Companion.getDefault();
            } else {
                textStyle4 = textStyle2;
            }
            if (i8 != 0) {
                z4 = true;
            } else {
                z4 = z;
            }
            if (i10 != 0) {
                i11 = TextOverflow.Companion.getClip-gIe3tQ8();
            }
            if (i13 != 0) {
                i20 = Integer.MAX_VALUE;
                i19 = i15;
            } else {
                i19 = i15;
                i20 = i2;
            }
            if (i19 != 0) {
                objRememberedValue4 = composerStartRestartGroup.rememberedValue();
                if (objRememberedValue4 == Composer.Companion.getEmpty()) {
                    objRememberedValue4 = new Function1() { // from class: q02
                        public final Object invoke(Object obj) {
                            return ClickableTextKt.ClickableText_4YKlhWE$lambda$0$0((TextLayoutResult) obj);
                        }
                    };
                    composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
                }
                function4 = (Function1) objRememberedValue4;
            } else {
                function4 = function1;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-246609449, i5, -1, "androidx.compose.foundation.text.ClickableText (ClickableText.kt:79)");
            }
            objRememberedValue = composerStartRestartGroup.rememberedValue();
            companion = Composer.Companion;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = SnapshotStateKt.mutableStateOf$default((Object) null, (SnapshotMutationPolicy) null, 2, (Object) null);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            mutableState = (MutableState) objRememberedValue;
            Modifier.Companion companion17 = Modifier.Companion;
            if ((29360128 & i5) == 8388608) {
                z5 = true;
            } else {
                z5 = false;
            }
            objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (z5) {
                objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                objRememberedValue2 = new ClickableTextKt$ClickableText$pressIndicator$1$1(mutableState, function2);
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            }
            Modifier modifierThen16 = modifier2.then(SuspendingPointerInputFilterKt.pointerInput(companion17, function2, (PointerInputEventHandler) objRememberedValue2));
            if ((i5 & 3670016) == 1048576) {
            }
            objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z6) {
                objRememberedValue3 = new Function1() { // from class: r02
                    public final Object invoke(Object obj) {
                        return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            } else {
                objRememberedValue3 = new Function1() { // from class: r02
                    public final Object invoke(Object obj) {
                        return ClickableTextKt.ClickableText_4YKlhWE$lambda$3$0(mutableState, function4, (TextLayoutResult) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            composer2 = composerStartRestartGroup;
            BasicTextKt.m1310BasicTextCL7eQgs(annotatedString, modifierThen16, textStyle4, (Function1) objRememberedValue3, i11, z4, i20, 0, null, null, null, composer2, (58254 & i5) | (458752 & (i5 << 6)) | ((i5 << 3) & 3670016), 0, 1920);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
            function3 = function4;
            textStyle3 = textStyle4;
            z3 = z4;
            i17 = i20;
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
            z3 = z;
            i17 = i2;
            textStyle3 = textStyle2;
            function3 = function1;
        }
        modifier3 = modifier2;
        i18 = i11;
        scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: s02
                public final Object invoke(Object obj, Object obj2) {
                    return ClickableTextKt.b(annotatedString, modifier3, textStyle3, z3, i18, i17, function3, function2, i3, i4, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ClickableText_4YKlhWE$lambda$0$0(TextLayoutResult textLayoutResult) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit ClickableText_4YKlhWE$lambda$3$0(MutableState mutableState, Function1 function1, TextLayoutResult textLayoutResult) {
        mutableState.setValue(textLayoutResult);
        function1.invoke(textLayoutResult);
        return Unit.INSTANCE;
    }

    public static Unit b(AnnotatedString annotatedString, Modifier modifier, TextStyle textStyle, boolean z, int i, int i2, Function1 function1, Function1 function2, int i3, int i4, Composer composer, int i5) {
        m1317ClickableText4YKlhWE(annotatedString, modifier, textStyle, z, i, i2, function1, function2, composer, RecomposeScopeImplKt.updateChangedFlags(i3 | 1), i4);
        return Unit.INSTANCE;
    }
}
