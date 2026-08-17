package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActionScope;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.tokens.TimeInputTokens;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.Updater;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.input.key.KeyEvent;
import androidx.compose.ui.input.key.KeyEvent_androidKt;
import androidx.compose.ui.input.key.KeyInputModifierKt;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.node.Ref;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class TimePickerKt$TimeInputImpl$2$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ TimePickerColors $colors;
    final /* synthetic */ MutableState<TextFieldValue> $hourValue$delegate;
    final /* synthetic */ MutableState<TextFieldValue> $minuteValue$delegate;
    final /* synthetic */ TimePickerState $state;
    final /* synthetic */ Ref<Boolean> $userOverride;

    public TimePickerKt$TimeInputImpl$2$1(MutableState<TextFieldValue> mutableState, TimePickerState timePickerState, Ref<Boolean> ref, TimePickerColors timePickerColors, MutableState<TextFieldValue> mutableState2) {
        this.$hourValue$delegate = mutableState;
        this.$state = timePickerState;
        this.$userOverride = ref;
        this.$colors = timePickerColors;
        this.$minuteValue$delegate = mutableState2;
    }

    public static Unit a(MutableState mutableState, TextFieldValue textFieldValue) {
        mutableState.setValue(textFieldValue);
        return Unit.INSTANCE;
    }

    public static Unit b(TimePickerState timePickerState, KeyboardActionScope keyboardActionScope) {
        timePickerState.mo76setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI());
        return Unit.INSTANCE;
    }

    public static Unit c(TimePickerState timePickerState, Ref ref, final MutableState mutableState, TextFieldValue textFieldValue) {
        TimePickerKt.m1148timeInputOnChange_K77t0(TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI(), timePickerState, textFieldValue, TimePickerKt.TimeInputImpl$lambda$18(mutableState), timePickerState.getIs24hour() ? 23 : 12, ref, new Function1() { // from class: androidx.compose.material3.d5
            public final Object invoke(Object obj) {
                return TimePickerKt$TimeInputImpl$2$1.f(mutableState, (TextFieldValue) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static Unit d(TimePickerState timePickerState, KeyboardActionScope keyboardActionScope) {
        timePickerState.mo76setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI());
        return Unit.INSTANCE;
    }

    public static Unit e(TimePickerState timePickerState, Ref ref, final MutableState mutableState, TextFieldValue textFieldValue) {
        TimePickerKt.m1148timeInputOnChange_K77t0(TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI(), timePickerState, textFieldValue, TimePickerKt.TimeInputImpl$lambda$22(mutableState), 59, ref, new Function1() { // from class: androidx.compose.material3.e5
            public final Object invoke(Object obj) {
                return TimePickerKt$TimeInputImpl$2$1.a(mutableState, (TextFieldValue) obj);
            }
        });
        return Unit.INSTANCE;
    }

    public static Unit f(MutableState mutableState, TextFieldValue textFieldValue) {
        mutableState.setValue(textFieldValue);
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1306700887, i, -1, "androidx.compose.material3.TimeInputImpl.<anonymous>.<anonymous> (TimePicker.kt:1032)");
        }
        final MutableState<TextFieldValue> mutableState = this.$hourValue$delegate;
        final TimePickerState timePickerState = this.$state;
        final Ref<Boolean> ref = this.$userOverride;
        TimePickerColors timePickerColors = this.$colors;
        final MutableState<TextFieldValue> mutableState2 = this.$minuteValue$delegate;
        Modifier.Companion companion = Modifier.INSTANCE;
        MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.getStart(), Alignment.INSTANCE.getTop(), composer, 0);
        int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composer, 0);
        CompositionLocalMap currentCompositionLocalMap = composer.getCurrentCompositionLocalMap();
        Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composer, companion);
        ComposeUiNode.Companion companion2 = ComposeUiNode.INSTANCE;
        Function0<ComposeUiNode> constructor = companion2.getConstructor();
        if (composer.getApplier() == null) {
            ComposablesKt.invalidApplier();
        }
        composer.startReusableNode();
        if (composer.getInserting()) {
            composer.createNode(constructor);
        } else {
            composer.useNode();
        }
        Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composer);
        Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion2.getSetMeasurePolicy());
        Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion2.getSetResolvedCompositionLocals());
        Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion2.getSetCompositeKeyHash();
        if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
            composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
            composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
        }
        Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion2.getSetModifier());
        RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
        boolean zChanged = composer.changed(mutableState) | composer.changedInstance(timePickerState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$1$1$1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m1149invokeZmokQxo(((KeyEvent) obj).m4284unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m1149invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    int iM4297getUtf16CodePointZmokQxo = KeyEvent_androidKt.m4297getUtf16CodePointZmokQxo(keyEvent);
                    if (48 <= iM4297getUtf16CodePointZmokQxo && iM4297getUtf16CodePointZmokQxo < 58 && TextRange.m5479getStartimpl(TimePickerKt.TimeInputImpl$lambda$18(mutableState).getSelection()) == 2 && TimePickerKt.TimeInputImpl$lambda$18(mutableState).getText().length() == 2) {
                        timePickerState.mo76setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m1168getMinuteyecRtBI());
                    }
                    return Boolean.FALSE;
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierOnKeyEvent = KeyInputModifierKt.onKeyEvent(companion, (Function1) objRememberedValue);
        TextFieldValue textFieldValueTimeInputImpl$lambda$18 = TimePickerKt.TimeInputImpl$lambda$18(mutableState);
        boolean zChangedInstance = composer.changedInstance(timePickerState) | composer.changed(mutableState) | composer.changedInstance(ref);
        Object objRememberedValue2 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue2 = new Function1() { // from class: androidx.compose.material3.f5
                public final Object invoke(Object obj) {
                    return TimePickerKt$TimeInputImpl$2$1.c(timePickerState, ref, mutableState, (TextFieldValue) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue2);
        }
        Function1 function1 = (Function1) objRememberedValue2;
        TimePickerSelectionMode.Companion companion3 = TimePickerSelectionMode.INSTANCE;
        int iM1167getHouryecRtBI = companion3.m1167getHouryecRtBI();
        ImeAction.Companion companion4 = ImeAction.INSTANCE;
        int iM5663getNexteUduSuo = companion4.m5663getNexteUduSuo();
        KeyboardType.Companion companion5 = KeyboardType.INSTANCE;
        KeyboardOptions keyboardOptions = new KeyboardOptions(0, (Boolean) null, companion5.m5707getNumberPjHm6EE(), iM5663getNexteUduSuo, (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 115, (DefaultConstructorMarker) null);
        boolean zChangedInstance2 = composer.changedInstance(timePickerState);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue3 = new Function1() { // from class: androidx.compose.material3.g5
                public final Object invoke(Object obj) {
                    return TimePickerKt$TimeInputImpl$2$1.b(timePickerState, (KeyboardActionScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        TimePickerKt.m1138TimePickerTextField1vLObsk(modifierOnKeyEvent, textFieldValueTimeInputImpl$lambda$18, function1, timePickerState, iM1167getHouryecRtBI, keyboardOptions, new KeyboardActions((Function1) null, (Function1) null, (Function1) objRememberedValue3, (Function1) null, (Function1) null, (Function1) null, 59, (DefaultConstructorMarker) null), timePickerColors, composer, 24576, 0);
        TimePickerKt.DisplaySeparator(SizeKt.size-VpY3zN4(companion, TimePickerKt.DisplaySeparatorWidth, TimeInputTokens.INSTANCE.m2189getPeriodSelectorContainerHeightD9Ej5fM()), composer, 6);
        boolean zChanged2 = composer.changed(mutableState2) | composer.changedInstance(timePickerState);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChanged2 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue4 = new Function1<KeyEvent, Boolean>() { // from class: androidx.compose.material3.TimePickerKt$TimeInputImpl$2$1$1$4$1
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    return m1150invokeZmokQxo(((KeyEvent) obj).m4284unboximpl());
                }

                /* JADX INFO: renamed from: invoke-ZmokQxo, reason: not valid java name */
                public final Boolean m1150invokeZmokQxo(android.view.KeyEvent keyEvent) {
                    boolean z = KeyEvent_androidKt.m4297getUtf16CodePointZmokQxo(keyEvent) == 0 && TextRange.m5479getStartimpl(TimePickerKt.TimeInputImpl$lambda$22(mutableState2).getSelection()) == 0;
                    if (z) {
                        timePickerState.mo76setSelection6_8s6DQ(TimePickerSelectionMode.INSTANCE.m1167getHouryecRtBI());
                    }
                    return Boolean.valueOf(z);
                }
            };
            composer.updateRememberedValue(objRememberedValue4);
        }
        Modifier modifierOnPreviewKeyEvent = KeyInputModifierKt.onPreviewKeyEvent(companion, (Function1) objRememberedValue4);
        TextFieldValue textFieldValueTimeInputImpl$lambda$22 = TimePickerKt.TimeInputImpl$lambda$22(mutableState2);
        boolean zChangedInstance3 = composer.changedInstance(timePickerState) | composer.changed(mutableState2) | composer.changedInstance(ref);
        Object objRememberedValue5 = composer.rememberedValue();
        if (zChangedInstance3 || objRememberedValue5 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue5 = new Function1() { // from class: androidx.compose.material3.h5
                public final Object invoke(Object obj) {
                    return TimePickerKt$TimeInputImpl$2$1.e(timePickerState, ref, mutableState2, (TextFieldValue) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue5);
        }
        Function1 function2 = (Function1) objRememberedValue5;
        int iM1168getMinuteyecRtBI = companion3.m1168getMinuteyecRtBI();
        KeyboardOptions keyboardOptions2 = new KeyboardOptions(0, (Boolean) null, companion5.m5707getNumberPjHm6EE(), companion4.m5661getDoneeUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 115, (DefaultConstructorMarker) null);
        boolean zChangedInstance4 = composer.changedInstance(timePickerState);
        Object objRememberedValue6 = composer.rememberedValue();
        if (zChangedInstance4 || objRememberedValue6 == Composer.INSTANCE.getEmpty()) {
            objRememberedValue6 = new Function1() { // from class: androidx.compose.material3.i5
                public final Object invoke(Object obj) {
                    return TimePickerKt$TimeInputImpl$2$1.d(timePickerState, (KeyboardActionScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue6);
        }
        TimePickerKt.m1138TimePickerTextField1vLObsk(modifierOnPreviewKeyEvent, textFieldValueTimeInputImpl$lambda$22, function2, timePickerState, iM1168getMinuteyecRtBI, keyboardOptions2, new KeyboardActions((Function1) null, (Function1) null, (Function1) objRememberedValue6, (Function1) null, (Function1) null, (Function1) null, 59, (DefaultConstructorMarker) null), timePickerColors, composer, 24576, 0);
        composer.endNode();
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
