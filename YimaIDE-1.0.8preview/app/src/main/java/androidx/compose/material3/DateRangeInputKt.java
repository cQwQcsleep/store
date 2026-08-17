package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.PaddingKt;
import androidx.compose.foundation.layout.RowKt;
import androidx.compose.foundation.layout.RowScope;
import androidx.compose.foundation.layout.RowScopeInstance;
import androidx.compose.material3.DateRangeInputKt;
import androidx.compose.material3.internal.CalendarModel;
import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.material3.internal.Strings;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.runtime.ComposablesKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.CompositionLocalMap;
import androidx.compose.runtime.RecomposeScopeImplKt;
import androidx.compose.runtime.ScopeUpdateScope;
import androidx.compose.runtime.Updater;
import androidx.compose.runtime.internal.ComposableLambda;
import androidx.compose.runtime.internal.ComposableLambdaKt;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.focus.FocusRequester;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.node.ComposeUiNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Dp;
import androidx.core.view.accessibility.AccessibilityEventCompat;
import com.android.apksig.internal.apk.AndroidBinXmlParser;
import com.android.apksig.internal.apk.v4.V4Signature;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u008f\u0001\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032:\u0010\u0005\u001a6\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\t\u0012\u0015\u0012\u0013\u0018\u00010\u0003¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\b(\n\u0012\u0004\u0012\u00020\u00010\u00062\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0001¢\u0006\u0002\u0010\u0017\"\u0010\u0010\u0018\u001a\u00020\u0019X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001a¨\u0006\u001b"}, d2 = {"DateRangeInputContent", "", "selectedStartDateMillis", "", "selectedEndDateMillis", "onDatesSelectionChange", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "startDateMillis", "endDateMillis", "calendarModel", "Landroidx/compose/material3/internal/CalendarModel;", "yearRange", "Lkotlin/ranges/IntRange;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "colors", "Landroidx/compose/material3/DatePickerColors;", "focusRequester", "Landroidx/compose/ui/focus/FocusRequester;", "(Ljava/lang/Long;Ljava/lang/Long;Lkotlin/jvm/functions/Function2;Landroidx/compose/material3/internal/CalendarModel;Lkotlin/ranges/IntRange;Landroidx/compose/material3/DatePickerFormatter;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/DatePickerColors;Landroidx/compose/ui/focus/FocusRequester;Landroidx/compose/runtime/Composer;I)V", "TextFieldSpacing", "Landroidx/compose/ui/unit/Dp;", "F", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DateRangeInputKt {
    private static final float TextFieldSpacing = Dp.m6022constructorimpl(8.0f);

    public static final void DateRangeInputContent(final Long l, final Long l2, final Function2<? super Long, ? super Long, Unit> function2, final CalendarModel calendarModel, final IntRange intRange, final DatePickerFormatter datePickerFormatter, final SelectableDates selectableDates, final DatePickerColors datePickerColors, final FocusRequester focusRequester, Composer composer, final int i) {
        int i2;
        IntRange intRange2;
        final Function2<? super Long, ? super Long, Unit> function3;
        Composer composer2;
        Object dateInputValidator;
        Composer composerStartRestartGroup = composer.startRestartGroup(1372713366);
        if ((i & 6) == 0) {
            i2 = (composerStartRestartGroup.changed(l) ? 4 : 2) | i;
        } else {
            i2 = i;
        }
        if ((i & 48) == 0) {
            i2 |= composerStartRestartGroup.changed(l2) ? 32 : 16;
        }
        if ((i & AndroidBinXmlParser.Chunk.RES_XML_TYPE_RESOURCE_MAP) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(function2) ? 256 : 128;
        }
        if ((i & 3072) == 0) {
            i2 |= composerStartRestartGroup.changedInstance(calendarModel) ? 2048 : 1024;
        }
        if ((i & 24576) == 0) {
            intRange2 = intRange;
            i2 |= composerStartRestartGroup.changedInstance(intRange2) ? 16384 : 8192;
        } else {
            intRange2 = intRange;
        }
        if ((196608 & i) == 0) {
            i2 |= (i & 262144) == 0 ? composerStartRestartGroup.changed(datePickerFormatter) : composerStartRestartGroup.changedInstance(datePickerFormatter) ? 131072 : 65536;
        }
        if ((1572864 & i) == 0) {
            i2 |= composerStartRestartGroup.changed(selectableDates) ? 1048576 : 524288;
        }
        if ((i & 12582912) == 0) {
            i2 |= composerStartRestartGroup.changed(datePickerColors) ? 8388608 : 4194304;
        }
        if ((i & 100663296) == 0) {
            i2 |= composerStartRestartGroup.changed(focusRequester) ? AccessibilityEventCompat.TYPE_VIEW_TARGETED_BY_SCROLL : 33554432;
        }
        if (composerStartRestartGroup.shouldExecute((i2 & 38347923) != 38347922, i2 & 1)) {
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventStart(1372713366, i2, -1, "androidx.compose.material3.DateRangeInputContent (DateRangeInput.kt:44)");
            }
            boolean zChanged = composerStartRestartGroup.changed(calendarModel.getLocale());
            Object objRememberedValue = composerStartRestartGroup.rememberedValue();
            if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
                objRememberedValue = calendarModel.getDateInputFormat(calendarModel.getLocale());
                composerStartRestartGroup.updateRememberedValue(objRememberedValue);
            }
            DateInputFormat dateInputFormat = (DateInputFormat) objRememberedValue;
            Strings.Companion companion = Strings.INSTANCE;
            String strM1471getString2EP1pXo = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_invalid_for_pattern), composerStartRestartGroup, 0);
            String strM1471getString2EP1pXo2 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_invalid_year_range), composerStartRestartGroup, 0);
            String strM1471getString2EP1pXo3 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_input_invalid_not_allowed), composerStartRestartGroup, 0);
            String strM1471getString2EP1pXo4 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_input_invalid_range_input), composerStartRestartGroup, 0);
            boolean zChanged2 = composerStartRestartGroup.changed(dateInputFormat) | ((i2 & 458752) == 131072 || ((i2 & 262144) != 0 && composerStartRestartGroup.changed(datePickerFormatter)));
            Object objRememberedValue2 = composerStartRestartGroup.rememberedValue();
            if (zChanged2 || objRememberedValue2 == Composer.INSTANCE.getEmpty()) {
                dateInputValidator = new DateInputValidator(intRange2, selectableDates, dateInputFormat, datePickerFormatter, strM1471getString2EP1pXo, strM1471getString2EP1pXo2, strM1471getString2EP1pXo3, strM1471getString2EP1pXo4);
                dateInputFormat = dateInputFormat;
                composerStartRestartGroup.updateRememberedValue(dateInputValidator);
            } else {
                dateInputValidator = objRememberedValue2;
            }
            DateInputValidator dateInputValidator2 = (DateInputValidator) dateInputValidator;
            dateInputValidator2.setCurrentStartDateMillis(l);
            dateInputValidator2.setCurrentEndDateMillis(l2);
            Modifier.Companion companion2 = Modifier.INSTANCE;
            Modifier modifierPadding = PaddingKt.padding(companion2, DateInputKt.getInputTextFieldPadding());
            MeasurePolicy measurePolicyRowMeasurePolicy = RowKt.rowMeasurePolicy(Arrangement.INSTANCE.spacedBy-0680j_4(TextFieldSpacing), Alignment.INSTANCE.getTop(), composerStartRestartGroup, 6);
            int currentCompositeKeyHash = ComposablesKt.getCurrentCompositeKeyHash(composerStartRestartGroup, 0);
            CompositionLocalMap currentCompositionLocalMap = composerStartRestartGroup.getCurrentCompositionLocalMap();
            Modifier modifierMaterializeModifier = ComposedModifierKt.materializeModifier(composerStartRestartGroup, modifierPadding);
            ComposeUiNode.Companion companion3 = ComposeUiNode.INSTANCE;
            Function0<ComposeUiNode> constructor = companion3.getConstructor();
            if (composerStartRestartGroup.getApplier() == null) {
                ComposablesKt.invalidApplier();
            }
            composerStartRestartGroup.startReusableNode();
            if (composerStartRestartGroup.getInserting()) {
                composerStartRestartGroup.createNode(constructor);
            } else {
                composerStartRestartGroup.useNode();
            }
            Composer composerM2388constructorimpl = Updater.m2388constructorimpl(composerStartRestartGroup);
            Updater.m2396setimpl(composerM2388constructorimpl, measurePolicyRowMeasurePolicy, companion3.getSetMeasurePolicy());
            Updater.m2396setimpl(composerM2388constructorimpl, currentCompositionLocalMap, companion3.getSetResolvedCompositionLocals());
            Function2<ComposeUiNode, Integer, Unit> setCompositeKeyHash = companion3.getSetCompositeKeyHash();
            if (composerM2388constructorimpl.getInserting() || !Intrinsics.areEqual(composerM2388constructorimpl.rememberedValue(), Integer.valueOf(currentCompositeKeyHash))) {
                composerM2388constructorimpl.updateRememberedValue(Integer.valueOf(currentCompositeKeyHash));
                composerM2388constructorimpl.apply(Integer.valueOf(currentCompositeKeyHash), setCompositeKeyHash);
            }
            Updater.m2396setimpl(composerM2388constructorimpl, modifierMaterializeModifier, companion3.getSetModifier());
            RowScopeInstance rowScopeInstance = RowScopeInstance.INSTANCE;
            String upperCase = dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            upperCase.getClass();
            String strM1471getString2EP1pXo5 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_start_headline), composerStartRestartGroup, 0);
            Modifier modifierWeight$default = RowScope.weight$default(rowScopeInstance, companion2, 0.5f, false, 2, (Object) null);
            InputIdentifier.Companion companion4 = InputIdentifier.INSTANCE;
            int iM571getStartDateInputJ2x2o4M = companion4.m571getStartDateInputJ2x2o4M();
            Locale locale = calendarModel.getLocale();
            int i3 = i2 & 896;
            int i4 = i2 & 112;
            boolean z = (i4 == 32) | (i3 == 256);
            Object objRememberedValue3 = composerStartRestartGroup.rememberedValue();
            if (z || objRememberedValue3 == Composer.INSTANCE.getEmpty()) {
                objRememberedValue3 = new Function1() { // from class: la3
                    public final Object invoke(Object obj) {
                        return DateRangeInputKt.c(function2, l2, (Long) obj);
                    }
                };
                composerStartRestartGroup.updateRememberedValue(objRememberedValue3);
            }
            ComposableLambda composableLambdaRememberComposableLambda = ComposableLambdaKt.rememberComposableLambda(1740538748, true, new DateRangeInputKt$DateRangeInputContent$2$2(strM1471getString2EP1pXo5, upperCase), composerStartRestartGroup, 54);
            ComposableLambda composableLambdaRememberComposableLambda2 = ComposableLambdaKt.rememberComposableLambda(1229526589, true, new DateRangeInputKt$DateRangeInputContent$2$3(upperCase), composerStartRestartGroup, 54);
            int i5 = i2 & V4Signature.MAX_SIGNING_INFOS_SIZE;
            int i6 = i2 >> 21;
            int i7 = i6 & 14;
            int i8 = i2;
            composer2 = composerStartRestartGroup;
            boolean z2 = false;
            DateInputKt.m330DateInputTextFieldxJ3Ic0Y(modifierWeight$default, l, (Function1) objRememberedValue3, calendarModel, composableLambdaRememberComposableLambda, composableLambdaRememberComposableLambda2, iM571getStartDateInputJ2x2o4M, dateInputValidator2, dateInputFormat, locale, datePickerColors, focusRequester, composer2, ((i2 << 3) & 112) | 1794048 | i5, i6 & 126);
            String strM1471getString2EP1pXo6 = Strings_androidKt.m1471getString2EP1pXo(Strings.m1392constructorimpl(R.string.m3c_date_range_picker_end_headline), composer2, 0);
            Modifier modifierWeight$default2 = RowScope.weight$default(rowScopeInstance, companion2, 0.5f, false, 2, (Object) null);
            int iM569getEndDateInputJ2x2o4M = companion4.m569getEndDateInputJ2x2o4M();
            Locale locale2 = calendarModel.getLocale();
            boolean z3 = i3 == 256;
            if ((i8 & 14) == 4) {
                z2 = true;
            }
            boolean z4 = z3 | z2;
            Object objRememberedValue4 = composer2.rememberedValue();
            if (z4 || objRememberedValue4 == Composer.INSTANCE.getEmpty()) {
                function3 = function2;
                objRememberedValue4 = new Function1() { // from class: ma3
                    public final Object invoke(Object obj) {
                        return DateRangeInputKt.a(function3, l, (Long) obj);
                    }
                };
                composer2.updateRememberedValue(objRememberedValue4);
            } else {
                function3 = function2;
            }
            DateInputKt.m330DateInputTextFieldxJ3Ic0Y(modifierWeight$default2, l2, (Function1) objRememberedValue4, calendarModel, ComposableLambdaKt.rememberComposableLambda(-882370893, true, new DateRangeInputKt$DateRangeInputContent$2$5(strM1471getString2EP1pXo6, upperCase), composer2, 54), ComposableLambdaKt.rememberComposableLambda(1956183348, true, new DateRangeInputKt$DateRangeInputContent$2$6(upperCase), composer2, 54), iM569getEndDateInputJ2x2o4M, dateInputValidator2, dateInputFormat, locale2, datePickerColors, null, composer2, i4 | 1794048 | i5, i7 | 48);
            composer2.endNode();
            if (ComposerKt.isTraceInProgress()) {
                ComposerKt.traceEventEnd();
            }
        } else {
            function3 = function2;
            composer2 = composerStartRestartGroup;
            composer2.skipToGroupEnd();
        }
        ScopeUpdateScope scopeUpdateScopeEndRestartGroup = composer2.endRestartGroup();
        if (scopeUpdateScopeEndRestartGroup != null) {
            final Function2<? super Long, ? super Long, Unit> function4 = function3;
            scopeUpdateScopeEndRestartGroup.updateScope(new Function2() { // from class: na3
                public final Object invoke(Object obj, Object obj2) {
                    return DateRangeInputKt.b(l, l2, function4, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, i, (Composer) obj, ((Integer) obj2).intValue());
                }
            });
        }
    }

    public static Unit a(Function2 function2, Long l, Long l2) {
        function2.invoke(l, l2);
        return Unit.INSTANCE;
    }

    public static Unit b(Long l, Long l2, Function2 function2, CalendarModel calendarModel, IntRange intRange, DatePickerFormatter datePickerFormatter, SelectableDates selectableDates, DatePickerColors datePickerColors, FocusRequester focusRequester, int i, Composer composer, int i2) {
        DateRangeInputContent(l, l2, function2, calendarModel, intRange, datePickerFormatter, selectableDates, datePickerColors, focusRequester, composer, RecomposeScopeImplKt.updateChangedFlags(i | 1));
        return Unit.INSTANCE;
    }

    public static Unit c(Function2 function2, Long l, Long l2) {
        function2.invoke(l2, l);
        return Unit.INSTANCE;
    }
}
