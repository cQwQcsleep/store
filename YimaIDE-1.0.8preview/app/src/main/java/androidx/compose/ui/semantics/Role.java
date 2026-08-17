package androidx.compose.ui.semantics;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\u0006\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0013\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\r\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/semantics/Role;", "", "value", "", "constructor-impl", "(I)I", "toString", "", "toString-impl", "(I)Ljava/lang/String;", "equals", "", "other", "hashCode", "Companion", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
@JvmInline
public final class Role {
    private final int value;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final int Button = m5239constructorimpl(0);
    private static final int Checkbox = m5239constructorimpl(1);
    private static final int Switch = m5239constructorimpl(2);
    private static final int RadioButton = m5239constructorimpl(3);
    private static final int Tab = m5239constructorimpl(4);
    private static final int Image = m5239constructorimpl(5);
    private static final int DropdownList = m5239constructorimpl(6);
    private static final int ValuePicker = m5239constructorimpl(7);
    private static final int Carousel = m5239constructorimpl(8);

    private /* synthetic */ Role(int i) {
        this.value = i;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Role m5238boximpl(int i) {
        return new Role(i);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    private static int m5239constructorimpl(int i) {
        return i;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m5240equalsimpl(int i, Object obj) {
        return (obj instanceof Role) && i == ((Role) obj).getValue();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m5241equalsimpl0(int i, int i2) {
        return i == i2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m5242hashCodeimpl(int i) {
        return Integer.hashCode(i);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m5243toStringimpl(int i) {
        if (m5241equalsimpl0(i, Button)) {
            return "Button";
        }
        if (m5241equalsimpl0(i, Checkbox)) {
            return "Checkbox";
        }
        if (m5241equalsimpl0(i, Switch)) {
            return "Switch";
        }
        if (m5241equalsimpl0(i, RadioButton)) {
            return "RadioButton";
        }
        if (m5241equalsimpl0(i, Tab)) {
            return "Tab";
        }
        if (m5241equalsimpl0(i, Image)) {
            return "Image";
        }
        if (m5241equalsimpl0(i, DropdownList)) {
            return "DropdownList";
        }
        if (m5241equalsimpl0(i, ValuePicker)) {
            return "Picker";
        }
        return m5241equalsimpl0(i, Carousel) ? "Carousel" : "Unknown";
    }

    public boolean equals(Object other) {
        return m5240equalsimpl(this.value, other);
    }

    public int hashCode() {
        return m5242hashCodeimpl(this.value);
    }

    public String toString() {
        return m5243toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ int getValue() {
        return this.value;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0014\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\t\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\n\u0010\u0007R\u0013\u0010\u000b\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\f\u0010\u0007R\u0013\u0010\r\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u000e\u0010\u0007R\u0013\u0010\u000f\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0010\u0010\u0007R\u0013\u0010\u0011\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0012\u0010\u0007R\u0013\u0010\u0013\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0014\u0010\u0007R\u0013\u0010\u0015\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0016\u0010\u0007R\u0013\u0010\u0017\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0018\u0010\u0007¨\u0006\u0019"}, d2 = {"Landroidx/compose/ui/semantics/Role$Companion;", "", "<init>", "()V", "Button", "Landroidx/compose/ui/semantics/Role;", "getButton-o7Vup1c", "()I", "I", "Checkbox", "getCheckbox-o7Vup1c", "Switch", "getSwitch-o7Vup1c", "RadioButton", "getRadioButton-o7Vup1c", "Tab", "getTab-o7Vup1c", "Image", "getImage-o7Vup1c", "DropdownList", "getDropdownList-o7Vup1c", "ValuePicker", "getValuePicker-o7Vup1c", "Carousel", "getCarousel-o7Vup1c", "ui"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: getButton-o7Vup1c, reason: not valid java name */
        public final int m5245getButtono7Vup1c() {
            return Role.Button;
        }

        /* JADX INFO: renamed from: getCarousel-o7Vup1c, reason: not valid java name */
        public final int m5246getCarouselo7Vup1c() {
            return Role.Carousel;
        }

        /* JADX INFO: renamed from: getCheckbox-o7Vup1c, reason: not valid java name */
        public final int m5247getCheckboxo7Vup1c() {
            return Role.Checkbox;
        }

        /* JADX INFO: renamed from: getDropdownList-o7Vup1c, reason: not valid java name */
        public final int m5248getDropdownListo7Vup1c() {
            return Role.DropdownList;
        }

        /* JADX INFO: renamed from: getImage-o7Vup1c, reason: not valid java name */
        public final int m5249getImageo7Vup1c() {
            return Role.Image;
        }

        /* JADX INFO: renamed from: getRadioButton-o7Vup1c, reason: not valid java name */
        public final int m5250getRadioButtono7Vup1c() {
            return Role.RadioButton;
        }

        /* JADX INFO: renamed from: getSwitch-o7Vup1c, reason: not valid java name */
        public final int m5251getSwitcho7Vup1c() {
            return Role.Switch;
        }

        /* JADX INFO: renamed from: getTab-o7Vup1c, reason: not valid java name */
        public final int m5252getTabo7Vup1c() {
            return Role.Tab;
        }

        /* JADX INFO: renamed from: getValuePicker-o7Vup1c, reason: not valid java name */
        public final int m5253getValuePickero7Vup1c() {
            return Role.ValuePicker;
        }

        private Companion() {
        }
    }
}
