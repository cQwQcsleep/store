package androidx.compose.material3;

import androidx.compose.material3.internal.CalendarDate;
import androidx.compose.material3.internal.DateInputFormat;
import androidx.compose.material3.internal.Strings_androidKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ranges.IntRange;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001BG\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\u0006\u0010\r\u001a\u00020\u000b\u0012\u0006\u0010\u000e\u001a\u00020\u000b¢\u0006\u0004\b\u000f\u0010\u0010J+\u0010\u001b\u001a\u00020\u000b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\n\u0010 \u001a\u00060!j\u0002`\"¢\u0006\u0004\b#\u0010$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001e\u0010\u0018\u001a\u0004\u0018\u00010\u0012X\u0086\u000e¢\u0006\u0010\n\u0002\u0010\u0017\u001a\u0004\b\u0019\u0010\u0014\"\u0004\b\u001a\u0010\u0016¨\u0006%"}, d2 = {"Landroidx/compose/material3/DateInputValidator;", "", "yearRange", "Lkotlin/ranges/IntRange;", "selectableDates", "Landroidx/compose/material3/SelectableDates;", "dateInputFormat", "Landroidx/compose/material3/internal/DateInputFormat;", "dateFormatter", "Landroidx/compose/material3/DatePickerFormatter;", "errorDatePattern", "", "errorDateOutOfYearRange", "errorInvalidNotAllowed", "errorInvalidRangeInput", "<init>", "(Lkotlin/ranges/IntRange;Landroidx/compose/material3/SelectableDates;Landroidx/compose/material3/internal/DateInputFormat;Landroidx/compose/material3/DatePickerFormatter;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "currentStartDateMillis", "", "getCurrentStartDateMillis", "()Ljava/lang/Long;", "setCurrentStartDateMillis", "(Ljava/lang/Long;)V", "Ljava/lang/Long;", "currentEndDateMillis", "getCurrentEndDateMillis", "setCurrentEndDateMillis", "validate", "dateToValidate", "Landroidx/compose/material3/internal/CalendarDate;", "inputIdentifier", "Landroidx/compose/material3/InputIdentifier;", "locale", "Ljava/util/Locale;", "Landroidx/compose/material3/CalendarLocale;", "validate-XivgLIo", "(Landroidx/compose/material3/internal/CalendarDate;ILjava/util/Locale;)Ljava/lang/String;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DateInputValidator {
    public static final int $stable = 0;
    private Long currentEndDateMillis;
    private Long currentStartDateMillis;
    private final DatePickerFormatter dateFormatter;
    private final DateInputFormat dateInputFormat;
    private final String errorDateOutOfYearRange;
    private final String errorDatePattern;
    private final String errorInvalidNotAllowed;
    private final String errorInvalidRangeInput;
    private final SelectableDates selectableDates;
    private final IntRange yearRange;

    public DateInputValidator(IntRange intRange, SelectableDates selectableDates, DateInputFormat dateInputFormat, DatePickerFormatter datePickerFormatter, String str, String str2, String str3, String str4) {
        this.yearRange = intRange;
        this.selectableDates = selectableDates;
        this.dateInputFormat = dateInputFormat;
        this.dateFormatter = datePickerFormatter;
        this.errorDatePattern = str;
        this.errorDateOutOfYearRange = str2;
        this.errorInvalidNotAllowed = str3;
        this.errorInvalidRangeInput = str4;
    }

    public final Long getCurrentEndDateMillis() {
        return this.currentEndDateMillis;
    }

    public final Long getCurrentStartDateMillis() {
        return this.currentStartDateMillis;
    }

    public final void setCurrentEndDateMillis(Long l) {
        this.currentEndDateMillis = l;
    }

    public final void setCurrentStartDateMillis(Long l) {
        this.currentStartDateMillis = l;
    }

    /* JADX WARN: Code duplicated, block: B:22:0x0087  */
    /* JADX WARN: Code duplicated, block: B:24:0x0091  */
    /* JADX WARN: Code duplicated, block: B:26:0x0099  */
    /* JADX WARN: Code duplicated, block: B:27:0x009e  */
    /* JADX WARN: Code duplicated, block: B:32:0x00a7 A[ORIG_RETURN, RETURN] */
    /* JADX WARN: Code duplicated, block: B:36:? A[RETURN, SYNTHETIC] */
    /* JADX INFO: renamed from: validate-XivgLIo, reason: not valid java name */
    public final String m331validateXivgLIo(CalendarDate dateToValidate, int inputIdentifier, Locale locale) {
        long utcTimeMillis;
        Long l;
        long jLongValue;
        if (dateToValidate == null) {
            String str = this.errorDatePattern;
            String upperCase = this.dateInputFormat.getPatternWithDelimiters().toUpperCase(Locale.ROOT);
            upperCase.getClass();
            return Strings_androidKt.formatString(str, upperCase);
        }
        if (!this.yearRange.contains(dateToValidate.getYear())) {
            return Strings_androidKt.formatString(this.errorDateOutOfYearRange, CalendarLocale_jvmKt.toLocalString$default(this.yearRange.getFirst(), 0, 0, false, locale, 7, null), CalendarLocale_jvmKt.toLocalString$default(this.yearRange.getLast(), 0, 0, false, locale, 7, null));
        }
        SelectableDates selectableDates = this.selectableDates;
        if (!selectableDates.isSelectableYear(dateToValidate.getYear()) || !selectableDates.isSelectableDate(dateToValidate.getUtcTimeMillis())) {
            return Strings_androidKt.formatString(this.errorInvalidNotAllowed, DatePickerFormatter.formatDate$default(this.dateFormatter, Long.valueOf(dateToValidate.getUtcTimeMillis()), locale, false, 4, null));
        }
        InputIdentifier.Companion companion = InputIdentifier.INSTANCE;
        if (InputIdentifier.m565equalsimpl0(inputIdentifier, companion.m571getStartDateInputJ2x2o4M())) {
            long utcTimeMillis2 = dateToValidate.getUtcTimeMillis();
            Long l2 = this.currentEndDateMillis;
            if (utcTimeMillis2 <= (l2 != null ? l2.longValue() : Long.MAX_VALUE)) {
                if (InputIdentifier.m565equalsimpl0(inputIdentifier, companion.m569getEndDateInputJ2x2o4M())) {
                    return "";
                }
                utcTimeMillis = dateToValidate.getUtcTimeMillis();
                l = this.currentStartDateMillis;
                if (l != null) {
                    jLongValue = l.longValue();
                } else {
                    jLongValue = Long.MIN_VALUE;
                }
                if (utcTimeMillis >= jLongValue) {
                    return "";
                }
            }
        } else {
            if (InputIdentifier.m565equalsimpl0(inputIdentifier, companion.m569getEndDateInputJ2x2o4M())) {
                return "";
            }
            utcTimeMillis = dateToValidate.getUtcTimeMillis();
            l = this.currentStartDateMillis;
            if (l != null) {
                jLongValue = l.longValue();
            } else {
                jLongValue = Long.MIN_VALUE;
            }
            if (utcTimeMillis >= jLongValue) {
                return "";
            }
        }
        return this.errorInvalidRangeInput;
    }
}
