package androidx.compose.ui.autofill;

import androidx.collection.MutableScatterMap;
import androidx.compose.ui.semantics.SemanticsActions;
import androidx.compose.ui.semantics.SemanticsConfiguration;
import androidx.compose.ui.semantics.SemanticsProperties;
import androidx.compose.ui.semantics.SemanticsPropertyKey;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\f\u0010\u0002\u001a\u00020\u0003*\u00020\u0004H\u0002\u001a\f\u0010\u0005\u001a\u00020\u0003*\u00020\u0004H\u0002\u001a\f\u0010\u0006\u001a\u00020\u0003*\u00020\u0004H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"logTag", "", "isAutofillable", "", "Landroidx/compose/ui/semantics/SemanticsConfiguration;", "isRelatedToAutoCommit", "isRelatedToAutofill", "ui"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidAutofillManager_androidKt {
    private static final String logTag = "ComposeAutofillManager";

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isAutofillable(SemanticsConfiguration semanticsConfiguration) {
        MutableScatterMap<SemanticsPropertyKey<?>, Object> props$ui = semanticsConfiguration.getProps$ui();
        SemanticsActions semanticsActions = SemanticsActions.INSTANCE;
        return props$ui.contains(semanticsActions.getOnAutofillText()) || semanticsConfiguration.getProps$ui().contains(semanticsActions.getOnFillData());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isRelatedToAutoCommit(SemanticsConfiguration semanticsConfiguration) {
        return semanticsConfiguration.getProps$ui().contains(SemanticsProperties.INSTANCE.getContentType());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isRelatedToAutofill(SemanticsConfiguration semanticsConfiguration) {
        MutableScatterMap<SemanticsPropertyKey<?>, Object> props$ui = semanticsConfiguration.getProps$ui();
        SemanticsActions semanticsActions = SemanticsActions.INSTANCE;
        if (props$ui.contains(semanticsActions.getOnAutofillText()) || semanticsConfiguration.getProps$ui().contains(semanticsActions.getOnFillData())) {
            return true;
        }
        MutableScatterMap<SemanticsPropertyKey<?>, Object> props$ui2 = semanticsConfiguration.getProps$ui();
        SemanticsProperties semanticsProperties = SemanticsProperties.INSTANCE;
        return props$ui2.contains(semanticsProperties.getContentType()) || semanticsConfiguration.getProps$ui().contains(semanticsProperties.getContentDataType());
    }
}
