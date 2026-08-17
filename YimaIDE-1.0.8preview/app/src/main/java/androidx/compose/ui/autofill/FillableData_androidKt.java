package androidx.compose.ui.autofill;

import android.view.autofill.AutofillValue;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007\u001a\u0014\u0010\b\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n\u001a\u0014\u0010\u000b\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\r\u001a\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0010\u001a\f\u0010\u0011\u001a\u0004\u0018\u00010\u0010*\u00020\u0001¨\u0006\u0012"}, d2 = {"createFromText", "Landroidx/compose/ui/autofill/FillableData;", "Landroidx/compose/ui/autofill/FillableData$Companion;", "textValue", "", "createFromBoolean", "booleanValue", "", "createFromListIndex", "listIndexValue", "", "createFromDateMillis", "dateMillisValue", "", "createFromAutofillValue", "autofillValue", "Landroid/view/autofill/AutofillValue;", "toAutofillValue", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class FillableData_androidKt {
    public static final FillableData createFromAutofillValue(FillableData.Companion companion, AutofillValue autofillValue) {
        return new AndroidFillableData(autofillValue);
    }

    public static final FillableData createFromBoolean(FillableData.Companion companion, boolean z) {
        return new AndroidFillableData(AutofillValue.forToggle(z));
    }

    public static final FillableData createFromDateMillis(FillableData.Companion companion, long j) {
        return new AndroidFillableData(AutofillValue.forDate(j));
    }

    public static final FillableData createFromListIndex(FillableData.Companion companion, int i) {
        return new AndroidFillableData(AutofillValue.forList(i));
    }

    public static final FillableData createFromText(FillableData.Companion companion, CharSequence charSequence) {
        return new AndroidFillableData(AutofillValue.forText(charSequence));
    }

    public static final AutofillValue toAutofillValue(FillableData fillableData) {
        AndroidFillableData androidFillableData = fillableData instanceof AndroidFillableData ? (AndroidFillableData) fillableData : null;
        if (androidFillableData != null) {
            return androidFillableData.getAutofillValue();
        }
        return null;
    }
}
