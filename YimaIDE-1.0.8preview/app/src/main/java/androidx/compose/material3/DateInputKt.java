package androidx.compose.material3;

import androidx.compose.foundation.interaction.MutableInteractionSource;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.PaddingValues;
import androidx.compose.foundation.layout.SizeKt;
import androidx.compose.foundation.text.KeyboardActions;
import androidx.compose.foundation.text.KeyboardOptions;
import androidx.compose.material3.DateInputKt;
import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.focus.FocusRequesterModifierKt;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.semantics.SemanticsModifierKt;
import androidx.compose.ui.semantics.SemanticsPropertiesKt;
import androidx.compose.ui.semantics.SemanticsPropertyReceiver;
import androidx.compose.ui.text.TextRange;
import androidx.compose.ui.text.TextStyle;
import androidx.compose.ui.text.input.ImeAction;
import androidx.compose.ui.text.input.KeyboardType;
import androidx.compose.ui.text.input.PlatformImeOptions;
import androidx.compose.ui.text.input.TextFieldValue;
import androidx.compose.ui.text.input.VisualTransformation;
import androidx.compose.ui.text.intl.LocaleList;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import androidx.profileinstaller.ProfileVerifier;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.IntRange;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u001an\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032#\u0010\u0004\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0001¢\u0006\u0002\u0010\u0015\u001a\u009f\u0001\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u00032\u0014\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0003\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\t\u001a\u00020\n2\u0013\u0010\u001a\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001b¢\u0006\u0002\b\u001c2\u0013\u0010\u001d\u001a\u000f\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001b¢\u0006\u0002\b\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#2\n\u0010$\u001a\u00060%j\u0002`&2\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0001¢\u0006\u0004\b'\u0010(\"\u0014\u0010)\u001a\u00020*X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,\"\u0010\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0004\n\u0002\u0010/¨\u00060²\u0006\n\u00101\u001a\u000202X\u008a\u008e\u0002"}, d2 = {"DateInputContent", "", "selectedDateMillis", "", "onDateSelectionChange", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "dateInMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "colors", "Landroidx/compose/material3/DatePickerColors;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "(Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;I)V", "DateInputTextField", "modifier", "Landroidx/compose/ui/Modifier;", "initialDateMillis", "label", "Lkotlin/Function0;", "Landroidx/compose/runtime/Composable;", "placeholder", "inputIdentifier", "Landroidx/compose/material3/InputIdentifier;", "dateInputValidator", "Landroidx/compose/material3/DateInputValidator;", "dateInputFormat", "Landroidx/compose/material3/internal/DateInputFormat;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "DateInputTextField-xJ3Ic0Y", "(Landroidx/compose/ui/Modifier;Ljava/lang/Long;Lkotlin/jvm/functions/Function1;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function2;ILandroidx/compose/material3/DateInputValidator;Landroidx/compose/material3/internal/DateInputFormat;Ljava/util/Locale;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;II)V", "InputTextFieldPadding", "Landroidx/compose/foundation/layout/PaddingValues;", "getInputTextFieldPadding", "()Landroidx/compose/foundation/layout/PaddingValues;", "InputTextNonErroneousBottomPadding", "Landroidx/compose/ui/unit/Dp;", "F", "material3", "text", "Landroidx/compose/ui/text/input/TextFieldValue;"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DateInputKt {
    private static final PaddingValues InputTextFieldPadding = PaddingKt.PaddingValues-a9UjIt4$default(Dp.m6022constructorimpl(24.0f), Dp.m6022constructorimpl(10.0f), Dp.m6022constructorimpl(24.0f), 0.0f, 8, (Object) null);
    private static final float InputTextNonErroneousBottomPadding = Dp.m6022constructorimpl(16.0f);

    public static final void DateInputContent(final Long l, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, final FocusRequester focusRequester, Composer composer, final int i) {
        int i2;
        IntRange intRange2;
        SelectableDates selectableDates2;
        Composer composer2;
        int i3;
        DateInputFormat dateInputFormat;
        int i4;
        Composer composerStartRestartGroup = composer.startRestartGroup(-432341251);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function1) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            intRange2 = intRange;
            i2 |= composerStartRestartGroup.changedInstance(intRange2) ? 2048 : 1024;
        } else {
            intRange2 = intRange;
        }
        if ((i & 24576) == 0) {
            i2 |= (i & 32768) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 16384 : 8192;
        }
        if ((196608 & i) == 0) {
            selectableDates2 = selectableDates;
            i2 |= composerStartRestartGroup.changed(selectableDates2) ? 131072 : 65536;
        } else {
            selectableDates2 = selectableDates;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? 1048576 : 524288;
        }
        if ((12582912 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(focusRequester) ? 8388608 : 4194304;
        }
        if (composerStartRestartGroup.shouldExecute((4793491 & i2) != 4793490, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-432341251, i2, -1, "androidx.compose.material3.DateInputContent (DateInput.kt:67)");
            }
            boolean zChanged = composerStartRestartGroup.changed(calendarModel.getLocale());
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = calendarModel.getDateInputFormat(calendarModel.getLocale());
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            DateInputFormat dateInputFormat2 = (DateInputFormat) objRememberedValue;
            Strings.Companion companion = Strings.INSTANCE;
            String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_invalid_for_pattern), composerStartRestartGroup, 0);
            String strM1471getString2EP1pXo2 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_invalid_year_range), composerStartRestartGroup, 0);
            String strM1471getString2EP1pXo3 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_invalid_not_allowed), composerStartRestartGroup, 0);
            boolean zChanged2 = composerStartRestartGroup.changed(dateInputFormat2) | ((i2 & 57344) == 16384 || ((i2 & 32768) != 0 && composerStartRestartGroup.changed(datePickerFormatter)));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                i3 = i2;
                dateInputFormat = dateInputFormat2;
                i4 = 0;
                DateInputValidator dateInputValidator = new DateInputValidator(intRange2, selectableDates2, dateInputFormat, datePickerFormatter, strM1471getString2EP1pXo, strM1471getString2EP1pXo2, strM1471getString2EP1pXo3, "");
                composerStartRestartGroup.updateRememberedValue(dateInputValidator);
                objRememberedValue2 = dateInputValidator;
            } else {
                i3 = i2;
                dateInputFormat = dateInputFormat2;
                i4 = 0;
            }
            DateInputValidator dateInputValidator2 = (DateInputValidator) objRememberedValue2;
            String upperCase = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            upperCase.getClass();
            String strM1471getString2EP1pXo4 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_label), composerStartRestartGroup, i4);
            Modifier modifierPadding = PaddingKt.padding(SizeKt.fillMaxWidth$default(Modifier.INSTANCE, 0.0f, 1, (Object) null), InputTextFieldPadding);
            int iM570getSingleDateInputJ2x2o4M = InputIdentifier.INSTANCE.m570getSingleDateInputJ2x2o4M();
            dateInputValidator2.setCurrentStartDateMillis(l);
            int i5 = i3 << 3;
            composer2 = composerStartRestartGroup;
            DateInputFormat dateInputFormat3 = dateInputFormat;
            m330DateInputTextFieldxJ3Ic0Y(modifierPadding, l, function1, calendarModel, ComposableLambdaKt.rememberComposableLambda(-752164549, true, new AnonymousClass2(strM1471getString2EP1pXo4, upperCase), composerStartRestartGroup, 54), ComposableLambdaKt.rememberComposableLambda(-1179434278, true, new AnonymousClass3(upperCase), composerStartRestartGroup, 54), iM570getSingleDateInputJ2x2o4M, dateInputValidator2, dateInputFormat3, calendarModel.getLocale(), datePickerColors, focusRequester, composer2, (i5 & V4Signature.MAX_SIGNING_INFOS_SIZE) | (i5 & 112) | 1794054 | (i5 & 896), (i3 >> 18) & 126);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: r83
                public final Object invoke(Object obj, Object obj2) {
                    return DateInputKt.b(l, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    /* JADX INFO: renamed from: DateInputTextField-xJ3Ic0Y, reason: not valid java name */
    public static final void m330DateInputTextFieldxJ3Ic0Y(final Modifier modifier, Long l, final Function1<? super Long, Unit> function1, final CalendarModel calendarModel, final Function2<? super Composer, ? super Integer, Unit> function2, final Function2<? super Composer, ? super Integer, Unit> function3, final int i, final DateInputValidator dateInputValidator, final DateInputFormat dateInputFormat, final Locale locale, final DatePickerColors datePickerColors, final FocusRequester focusRequester, Composer composer, final int i2, final int i3) {
        int i4;
        int i5;
        Long l2;
        Composer composer2;
        MutableState mutableState;
        float fM6022constructorimpl;
        Object obj;
        final DateInputFormat dateInputFormat2;
        Object dateInputKt$DateInputTextField$5$1;
        final CalendarModel calendarModel2 = calendarModel;
        final Locale locale2 = locale;
        Composer composerStartRestartGroup = composer.startRestartGroup(1456309913);
        if ((i2 & 6) == 0) {
            i4 = (composerStartRestartGroup.changed(modifier) ? 4 : 2) | i2;
        } else {
            i4 = i2;
        }
        if ((i2 & 48) == 0) {
            i4 |= composerStartRestartGroup.changed(l) ? 32 : 16;
        }
        if ((i2 & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function1) ? 256 : 128;
        }
        if ((i2 & 3072) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(calendarModel2) ? 2048 : 1024;
        }
        if ((i2 & 24576) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function2) ? 16384 : 8192;
        }
        if ((i2 & ProfileVerifier.CompilationStatus.RESULT_CODE_ERROR_CANT_WRITE_PROFILE_VERIFICATION_RESULT_CACHE_FILE) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(function3) ? 131072 : 65536;
        }
        if ((i2 & 1572864) == 0) {
            i4 |= composerStartRestartGroup.changed(i) ? 1048576 : 524288;
        }
        if ((i2 & 12582912) == 0) {
            i4 |= composerStartRestartGroup.changed(dateInputValidator) ? 8388608 : 4194304;
        }
        if ((i2 & 100663296) == 0) {
            i4 |= composerStartRestartGroup.changed(dateInputFormat) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if ((i2 & 805306368) == 0) {
            i4 |= composerStartRestartGroup.changedInstance(locale2) ? 536870912 : 268435456;
        }
        if ((i3 & 6) == 0) {
            i5 = i3 | (composerStartRestartGroup.changed(datePickerColors) ? 4 : 2);
        } else {
            i5 = i3;
        }
        if ((i3 & 48) == 0) {
            i5 |= composerStartRestartGroup.changed(focusRequester) ? 32 : 16;
        }
        int i6 = i5;
        if (composerStartRestartGroup.shouldExecute(((i4 & 306783379) == 306783378 && (i6 & 19) == 18) ? false : true, i4 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1456309913, i4, i6, "androidx.compose.material3.DateInputTextField (DateInput.kt:128)");
            }
            Object[] objArr = new Object[0];
            Saver<TextFieldValue, Object> saver = TextFieldValue.INSTANCE.getSaver();
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            Composer.Companion companion = Composer.INSTANCE;
            int i7 = i4;
            if (objRememberedValue == companion.getEmpty()) {
                objRememberedValue = new Function0() { // from class: s83
                    public final Object invoke() {
                        return DateInputKt.a();
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            final MutableState mutableStateRememberSaveable = RememberSaveableKt.rememberSaveable(objArr, (Saver) saver, (Function0) objRememberedValue, composerStartRestartGroup, AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP);
            Object[] objArr2 = {DateInputTextField_xJ3Ic0Y$lambda$6(mutableStateRememberSaveable)};
            int i8 = i7 & 29360128;
            int i9 = i7 & 234881024;
            int i10 = i7 & 3670016;
            boolean zChanged = composerStartRestartGroup.changed(mutableStateRememberSaveable) | (i8 == 8388608) | composerStartRestartGroup.changedInstance(calendarModel2) | (i9 == 67108864) | composerStartRestartGroup.changedInstance(locale2) | (i10 == 1048576);
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue2 == companion.getEmpty()) {
                objRememberedValue2 = new Function0() { // from class: t83
                    public final Object invoke() {
                        return DateInputKt.d(dateInputValidator, calendarModel2, dateInputFormat, locale2, i, mutableStateRememberSaveable);
                    }
                };
                mutableState = mutableStateRememberSaveable;
                composerStartRestartGroup.updateRememberedValue(objRememberedValue2);
            } else {
                mutableState = mutableStateRememberSaveable;
            }
            final MutableState mutableState2 = (MutableState) RememberSaveableKt.rememberSaveable(objArr2, (Function0) objRememberedValue2, composerStartRestartGroup, 0);
            if (StringsKt.isBlank((CharSequence) mutableState2.getValue())) {
                fM6022constructorimpl = InputTextNonErroneousBottomPadding;
            } else {
                PaddingValues paddingValuesM1073supportingTextPaddinga9UjIt4$material3$default = TextFieldDefaults.m1073supportingTextPaddinga9UjIt4$material3$default(TextFieldDefaults.INSTANCE, 0.0f, 0.0f, 0.0f, 0.0f, 15, null);
                fM6022constructorimpl = Dp.m6022constructorimpl(InputTextNonErroneousBottomPadding - Dp.m6022constructorimpl(paddingValuesM1073supportingTextPaddinga9UjIt4$material3$default.calculateBottomPadding-D9Ej5fM() + paddingValuesM1073supportingTextPaddinga9UjIt4$material3$default.calculateTopPadding-D9Ej5fM()));
            }
            float f = fM6022constructorimpl;
            TextFieldValue textFieldValueDateInputTextField_xJ3Ic0Y$lambda$6 = DateInputTextField_xJ3Ic0Y$lambda$6(mutableState);
            boolean zChanged2 = (i10 == 1048576) | (i9 == 67108864) | composerStartRestartGroup.changed(mutableState) | composerStartRestartGroup.changed(mutableState2) | ((i7 & 896) == 256) | composerStartRestartGroup.changedInstance(calendarModel2) | composerStartRestartGroup.changedInstance(locale2) | (i8 == 8388608);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue3 == companion.getEmpty()) {
                dateInputFormat2 = dateInputFormat;
                final MutableState mutableState3 = mutableState;
                obj = new Function1() { // from class: u83
                    public final Object invoke(Object obj2) {
                        return DateInputKt.f(dateInputFormat2, mutableState2, function1, calendarModel2, locale2, dateInputValidator, i, mutableState3, (TextFieldValue) obj2);
                    }
                };
                calendarModel2 = calendarModel2;
                locale2 = locale2;
                mutableState = mutableState3;
                composerStartRestartGroup.updateRememberedValue(obj);
            } else {
                obj = objRememberedValue3;
                dateInputFormat2 = dateInputFormat;
            }
            Function1 function4 = (Function1) obj;
            Modifier modifier2 = PaddingKt.padding-qDBjuR0$default(modifier, 0.0f, 0.0f, 0.0f, f, 7, (Object) null);
            boolean zChanged3 = composerStartRestartGroup.changed(mutableState2);
            Object objRememberedValue4 = composerStartRestartGroup.rememberedValue();
            if (zChanged3 || objRememberedValue4 == companion.getEmpty()) {
                objRememberedValue4 = new Function1() { // from class: v83
                    public final Object invoke(Object obj2) {
                        return DateInputKt.c(mutableState2, (SemanticsPropertyReceiver) obj2);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue4);
            }
            OutlinedTextFieldKt.OutlinedTextField(textFieldValueDateInputTextField_xJ3Ic0Y$lambda$6, (Function1<? super TextFieldValue, Unit>) function4, SemanticsModifierKt.semantics$default(modifier2, false, (Function1) objRememberedValue4, 1, null).then(focusRequester != null ? FocusRequesterModifierKt.focusRequester(Modifier.INSTANCE, focusRequester) : Modifier.INSTANCE), false, false, (TextStyle) null, function2, function3, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) null, (Function2<? super Composer, ? super Integer, Unit>) ComposableLambdaKt.rememberComposableLambda(-357881838, true, new Function2<Composer, Integer, Unit>() { // from class: androidx.compose.material3.DateInputKt$DateInputTextField$3
                public final void invoke(Composer composer3, int i11) {
                    Composer composer4 = composer3;
                    if (!composer4.shouldExecute((i11 & 3) != 2, i11 & 1)) {
                        composer4.skipToGroupEnd();
                        return;
                    }
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventStart(-357881838, i11, -1, "androidx.compose.material3.DateInputTextField.<anonymous> (DateInput.kt:215)");
                    }
                    if (StringsKt.isBlank(mutableState2.getValue())) {
                        composer4.startReplaceGroup(-1548950640);
                    } else {
                        composer4.startReplaceGroup(-327061465);
                        TextKt.m1097TextNvy7gAk(mutableState2.getValue(), null, 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer3, 0, 0, 262142);
                        composer4 = composer3;
                    }
                    composer4.endReplaceGroup();
                    if (ComposerKt.isTraceInProgress()) {
                        ComposerKt.traceEventEnd();
                    }
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj2, Object obj3) {
                    invoke((Composer) obj2, ((Number) obj3).intValue());
                    return Unit.INSTANCE;
                }
            }, composerStartRestartGroup, 54), !StringsKt.isBlank((CharSequence) mutableState2.getValue()), (VisualTransformation) new DateVisualTransformation(dateInputFormat2), new KeyboardOptions(0, Boolean.FALSE, KeyboardType.INSTANCE.m5707getNumberPjHm6EE(), ImeAction.INSTANCE.m5661getDoneeUduSuo(), (PlatformImeOptions) null, (Boolean) null, (LocaleList) null, 113, (DefaultConstructorMarker) null), (KeyboardActions) null, true, 0, 0, (MutableInteractionSource) null, (Shape) null, datePickerColors.getDateTextFieldColors(), composerStartRestartGroup, (i7 << 6) & 33030144, 12779904, 0, 4001592);
            composer2 = composerStartRestartGroup;
            Unit unit = Unit.INSTANCE;
            boolean z = (i6 & 112) == 32;
            Object objRememberedValue5 = composer2.rememberedValue();
            if (z || objRememberedValue5 == companion.getEmpty()) {
                objRememberedValue5 = new DateInputKt$DateInputTextField$4$1(focusRequester, null);
                composer2.updateRememberedValue(objRememberedValue5);
            }
            EffectsKt.LaunchedEffect(unit, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) objRememberedValue5, composer2, 6);
            boolean zChangedInstance = ((i7 & 112) == 32) | composer2.changedInstance(calendarModel2) | (i9 == 67108864) | composer2.changedInstance(locale2) | composer2.changed(mutableState);
            Object objRememberedValue6 = composer2.rememberedValue();
            if (zChangedInstance || objRememberedValue6 == companion.getEmpty()) {
                l2 = l;
                dateInputKt$DateInputTextField$5$1 = new DateInputKt$DateInputTextField$5$1(l2, calendarModel2, dateInputFormat2, locale, mutableState, null);
                composer2.updateRememberedValue(dateInputKt$DateInputTextField$5$1);
            } else {
                dateInputKt$DateInputTextField$5$1 = objRememberedValue6;
                l2 = l;
            }
            EffectsKt.LaunchedEffect(l2, (Function2<? super CoroutineScope, ? super Continuation<? super Unit>, ? extends Object>) dateInputKt$DateInputTextField$5$1, composer2, (i7 >> 3) & 14);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            l2 = l;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Long l3 = l2;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: w83
                public final Object invoke(Object obj2, Object obj3) {
                    return DateInputKt.e(modifier, l3, function1, calendarModel, function2, function3, i, dateInputValidator, dateInputFormat, locale, datePickerColors, focusRequester, i2, i3, (Composer) obj2, ((Integer) obj3).intValue());
                }
            });
        }
    }

    private static final TextFieldValue DateInputTextField_xJ3Ic0Y$lambda$6(MutableState<TextFieldValue> mutableState) {
        return mutableState.getValue();
    }

    public static MutableState a() {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(new TextFieldValue((String) null, 0L, (TextRange) null, 7, (DefaultConstructorMarker) null), null, 2, null);
    }

    public static Unit b(Long l, Function1 function1, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, FocusRequester focusRequester, int i, Composer composer, int i2) {
        DateInputContent(l, function1, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit c(MutableState mutableState, SemanticsPropertyReceiver semanticsPropertyReceiver) {
        if (!StringsKt.isBlank((CharSequence) mutableState.getValue())) {
            SemanticsPropertiesKt.error(semanticsPropertyReceiver, (String) mutableState.getValue());
        }
        return Unit.INSTANCE;
    }

    public static MutableState d(DateInputValidator dateInputValidator, CalendarModel calendarModel, DateInputFormat dateInputFormat, Locale locale, int i, MutableState mutableState) {
        return SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(DateInputTextField_xJ3Ic0Y$lambda$6(mutableState).getText().length() > 0 ? dateInputValidator.m331validateXivgLIo(calendarModel.parse(DateInputTextField_xJ3Ic0Y$lambda$6(mutableState).getText(), dateInputFormat.getPatternWithoutDelimiters(), locale), i, locale) : "", null, 2, null);
    }

    public static Unit e(Modifier modifier, Long l, Function1 function1, CalendarModel calendarModel, Function2 function2, Function2 function3, int i, DateInputValidator dateInputValidator, DateInputFormat dateInputFormat, Locale locale, DatePickerColors datePickerColors, FocusRequester focusRequester, int i2, int i3, Composer composer, int i4) {
        m330DateInputTextFieldxJ3Ic0Y(modifier, l, function1, calendarModel, function2, function3, i, dateInputValidator, dateInputFormat, locale, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i2 | 1), RecomposeScopeImplKt.updateChangedFlags(i3));
        return Unit.INSTANCE;
    }

    public static Unit f(DateInputFormat dateInputFormat, MutableState mutableState, Function1 function1, CalendarModel calendarModel, Locale locale, DateInputValidator dateInputValidator, int i, MutableState mutableState2, TextFieldValue textFieldValue) {
        if (textFieldValue.getText().length() <= dateInputFormat.getPatternWithoutDelimiters().length()) {
            String text = textFieldValue.getText();
            for (int i2 = 0; i2 < text.length(); i2++) {
                if (Character.isDigit(text.charAt(i2))) {
                }
            }
            mutableState2.setValue(textFieldValue);
            String string = StringsKt.trim(textFieldValue.getText()).toString();
            Long lValueOf = null;
            if (string.length() != 0 && string.length() >= dateInputFormat.getPatternWithoutDelimiters().length()) {
                CalendarDate calendarDate = calendarModel.parse(string, dateInputFormat.getPatternWithoutDelimiters(), locale);
                mutableState.setValue(dateInputValidator.m331validateXivgLIo(calendarDate, i, locale));
                if (((CharSequence) mutableState.getValue()).length() == 0 && calendarDate != null) {
                    lValueOf = Long.valueOf(calendarDate.getUtcTimeMillis());
                }
                function1.invoke(lValueOf);
            } else {
                mutableState.setValue("");
                function1.invoke((Object) null);
            }
        }
        return Unit.INSTANCE;
    }

    public static final PaddingValues getInputTextFieldPadding() {
        return InputTextFieldPadding;
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$3, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass3 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ String $pattern;

        public AnonymousClass3(String str) {
            this.$pattern = str;
        }

        public static Unit a(SemanticsPropertyReceiver semanticsPropertyReceiver) {
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-1179434278, i, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:98)");
            }
            String str = this.$pattern;
            Modifier.Companion companion = Modifier.INSTANCE;
            Object objRememberedValue = composer.rememberedValue();
            if (objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.x
                    public final Object invoke(Object obj) {
                        return DateInputKt.AnonymousClass3.a((SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            TextKt.m1097TextNvy7gAk(str, SemanticsModifierKt.clearAndSetSemantics(companion, (Function1) objRememberedValue), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262140);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: androidx.compose.material3.DateInputKt$DateInputContent$2, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class AnonymousClass2 implements Function2<Composer, Integer, Unit> {
        final /* synthetic */ String $labelText;
        final /* synthetic */ String $pattern;

        public AnonymousClass2(String str, String str2) {
            this.$labelText = str;
            this.$pattern = str2;
        }

        public static Unit a(String str, String str2, SemanticsPropertyReceiver semanticsPropertyReceiver) {
            SemanticsPropertiesKt.setContentDescription(semanticsPropertyReceiver, str + ", " + str2);
            return Unit.INSTANCE;
        }

        public final void invoke(Composer composer, int i) {
            if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
                composer.skipToGroupEnd();
                return;
            }
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(-752164549, i, -1, "androidx.compose.material3.DateInputContent.<anonymous> (DateInput.kt:93)");
            }
            String str = this.$labelText;
            Modifier.Companion companion = Modifier.INSTANCE;
            boolean zChanged = composer.changed(str) | composer.changed(this.$pattern);
            final String str2 = this.$labelText;
            final String str3 = this.$pattern;
            Object objRememberedValue = composer.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = new Function1() { // from class: androidx.compose.material3.w
                    public final Object invoke(Object obj) {
                        return DateInputKt.AnonymousClass2.a(str2, str3, (SemanticsPropertyReceiver) obj);
                    }
                };
                composer.updateRememberedValue(objRememberedValue);
            }
            TextKt.m1097TextNvy7gAk(str, SemanticsModifierKt.semantics$default(companion, false, (Function1) objRememberedValue, 1, null), 0L, null, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, null, composer, 0, 0, 262140);
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            invoke((Composer) obj, ((Number) obj2).intValue());
            return Unit.INSTANCE;
        }
    }
}
