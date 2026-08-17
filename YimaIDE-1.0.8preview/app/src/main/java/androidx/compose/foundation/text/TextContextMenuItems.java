package androidx.compose.foundation.text;

import androidx.compose.foundation.text.contextmenu.data.TextContextMenuKeys;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'Cut' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:485)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByField(EnumVisitor.java:399)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByWrappedInsn(EnumVisitor.java:364)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:349)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:284)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInvoke(EnumVisitor.java:315)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:288)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:160)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0080\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B!\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\r\u0010\u0015\u001a\u00020\u0016H\u0007¢\u0006\u0002\u0010\u0017R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0013\u0010\u0004\u001a\u00020\u0005¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0013\u0010\u0006\u001a\u00020\u0007¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\u000f\u0010\rj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014¨\u0006\u0018"}, d2 = {"Landroidx/compose/foundation/text/TextContextMenuItems;", "", "key", "", "stringId", "Landroidx/compose/foundation/text/ContextMenuStrings;", "drawableId", "Landroidx/compose/foundation/text/ContextMenuIcons;", "<init>", "(Ljava/lang/String;ILjava/lang/Object;II)V", "getKey", "()Ljava/lang/Object;", "getStringId-9Hzcbyc", "()I", "I", "getDrawableId-3I4p1mQ", "Cut", "Copy", "Paste", "SelectAll", "Autofill", "resolvedString", "", "(Landroidx/compose/runtime/Composer;I)Ljava/lang/String;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class TextContextMenuItems {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ TextContextMenuItems[] $VALUES;
    public static final TextContextMenuItems Autofill;
    public static final TextContextMenuItems Copy;
    public static final TextContextMenuItems Cut;
    public static final TextContextMenuItems Paste;
    public static final TextContextMenuItems SelectAll;
    private final int drawableId;
    private final Object key;
    private final int stringId;

    private static final /* synthetic */ TextContextMenuItems[] $values() {
        return new TextContextMenuItems[]{Cut, Copy, Paste, SelectAll, Autofill};
    }

    static {
        TextContextMenuKeys textContextMenuKeys = TextContextMenuKeys.INSTANCE;
        Object cutKey = textContextMenuKeys.getCutKey();
        ContextMenuStrings.Companion companion = ContextMenuStrings.INSTANCE;
        int iM1344getCut9Hzcbyc = companion.m1344getCut9Hzcbyc();
        ContextMenuIcons.Companion companion2 = ContextMenuIcons.INSTANCE;
        Cut = new TextContextMenuItems("Cut", 0, cutKey, iM1344getCut9Hzcbyc, companion2.m1331getActionModeCutDrawable3I4p1mQ());
        Copy = new TextContextMenuItems("Copy", 1, textContextMenuKeys.getCopyKey(), companion.m1343getCopy9Hzcbyc(), companion2.m1330getActionModeCopyDrawable3I4p1mQ());
        Paste = new TextContextMenuItems("Paste", 2, textContextMenuKeys.getPasteKey(), companion.m1345getPaste9Hzcbyc(), companion2.m1332getActionModePasteDrawable3I4p1mQ());
        SelectAll = new TextContextMenuItems("SelectAll", 3, textContextMenuKeys.getSelectAllKey(), companion.m1346getSelectAll9Hzcbyc(), companion2.m1333getActionModeSelectAllDrawable3I4p1mQ());
        Autofill = new TextContextMenuItems("Autofill", 4, textContextMenuKeys.getAutofillKey(), companion.m1342getAutofill9Hzcbyc(), companion2.m1334getID_NULL3I4p1mQ());
        TextContextMenuItems[] textContextMenuItemsArr$values = $values();
        $VALUES = textContextMenuItemsArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(textContextMenuItemsArr$values);
    }

    private TextContextMenuItems(String str, int i, Object obj, int i2, int i3) {
        super(str, i);
        this.key = obj;
        this.stringId = i2;
        this.drawableId = i3;
    }

    public static EnumEntries<TextContextMenuItems> getEntries() {
        return $ENTRIES;
    }

    public static TextContextMenuItems valueOf(String str) {
        return (TextContextMenuItems) Enum.valueOf(TextContextMenuItems.class, str);
    }

    public static TextContextMenuItems[] values() {
        return (TextContextMenuItems[]) $VALUES.clone();
    }

    /* JADX INFO: renamed from: getDrawableId-3I4p1mQ, reason: not valid java name and from getter */
    public final int getDrawableId() {
        return this.drawableId;
    }

    public final Object getKey() {
        return this.key;
    }

    /* JADX INFO: renamed from: getStringId-9Hzcbyc, reason: not valid java name and from getter */
    public final int getStringId() {
        return this.stringId;
    }

    public final String resolvedString(Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(479426150, i, -1, "androidx.compose.foundation.text.TextContextMenuItems.resolvedString (CommonContextMenuArea.kt:178)");
        }
        String strM1347getStringtk4Tqcs = ContextMenuStrings_androidKt.m1347getStringtk4Tqcs(this.stringId, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return strM1347getStringtk4Tqcs;
    }
}
