package io.github.rosemoe.sora.lang.completion;

import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EnumMember' uses external variables
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
/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b#\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001b\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010*\u001a\u00020)R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010(\u001a\u00020)X\u0082\u0004¢\u0006\u0002\n\u0000j\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'¨\u0006+"}, d2 = {"Lio/github/rosemoe/sora/lang/completion/CompletionItemKind;", "", "value", "", "defaultDisplayBackgroundColor", "", "<init>", "(Ljava/lang/String;IIJ)V", "getValue", "()I", "getDefaultDisplayBackgroundColor", "()J", "Identifier", "Text", "Method", "Function", "Constructor", "Field", "Variable", "Class", "Interface", "Module", "Property", "Unit", "Value", "Enum", "Keyword", "Snippet", "Color", "Reference", "File", "Folder", "EnumMember", "Constant", "Struct", "Event", "Operator", "TypeParameter", "User", "Issue", "displayString", "", "getDisplayChar", "editor_release"}, k = 1, mv = {2, 2, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class CompletionItemKind {
    private static final /* synthetic */ EnumEntries $ENTRIES;
    private static final /* synthetic */ CompletionItemKind[] $VALUES;
    public static final CompletionItemKind EnumMember;
    public static final CompletionItemKind Issue;
    private final long defaultDisplayBackgroundColor;
    private final String displayString;
    private final int value;
    public static final CompletionItemKind Identifier = new CompletionItemKind("Identifier", 0, 0, 4289443517L);
    public static final CompletionItemKind Text = new CompletionItemKind("Text", 1, 0, 4289443517L);
    public static final CompletionItemKind Method = new CompletionItemKind("Method", 2, 1, 4294226622L);
    public static final CompletionItemKind Function = new CompletionItemKind("Function", 3, 2, 4294226622L);
    public static final CompletionItemKind Constructor = new CompletionItemKind("Constructor", 4, 3, 4294226622L);
    public static final CompletionItemKind Field = new CompletionItemKind("Field", 5, 4, 4294035587L);
    public static final CompletionItemKind Variable = new CompletionItemKind("Variable", 6, 5, 4294035587L);
    public static final CompletionItemKind Class = new CompletionItemKind("Class", 7, 6, 4286958821L);
    public static final CompletionItemKind Interface = new CompletionItemKind("Interface", 8, 7, 4288269191L);
    public static final CompletionItemKind Module = new CompletionItemKind("Module", 9, 8, 4286958821L);
    public static final CompletionItemKind Property = new CompletionItemKind("Property", 10, 9, 4291738868L);
    public static final CompletionItemKind Unit = new CompletionItemKind("Unit", 11, 10, 0, 2, null);
    public static final CompletionItemKind Value = new CompletionItemKind("Value", 12, 11, 4294035587L);
    public static final CompletionItemKind Enum = new CompletionItemKind("Enum", 13, 12, 4286958821L);
    public static final CompletionItemKind Keyword = new CompletionItemKind("Keyword", 14, 13, 4291590194L);
    public static final CompletionItemKind Snippet = new CompletionItemKind("Snippet", 15, 14, 0, 2, null);
    public static final CompletionItemKind Color = new CompletionItemKind("Color", 16, 15, 4294226622L);
    public static final CompletionItemKind Reference = new CompletionItemKind("Reference", 17, 17, 0, 2, null);
    public static final CompletionItemKind File = new CompletionItemKind("File", 18, 16, 0, 2, null);
    public static final CompletionItemKind Folder = new CompletionItemKind("Folder", 19, 18, 0, 2, null);
    public static final CompletionItemKind Constant = new CompletionItemKind("Constant", 21, 20, 4294035587L);
    public static final CompletionItemKind Struct = new CompletionItemKind("Struct", 22, 21, 4291738868L);
    public static final CompletionItemKind Event = new CompletionItemKind("Event", 23, 22, 0, 2, null);
    public static final CompletionItemKind Operator = new CompletionItemKind("Operator", 24, 23, 4293569462L);
    public static final CompletionItemKind TypeParameter = new CompletionItemKind("TypeParameter", 25, 24, 4294035587L);
    public static final CompletionItemKind User = new CompletionItemKind("User", 26, 25, 0, 2, null);

    private static final /* synthetic */ CompletionItemKind[] $values() {
        return new CompletionItemKind[]{Identifier, Text, Method, Function, Constructor, Field, Variable, Class, Interface, Module, Property, Unit, Value, Enum, Keyword, Snippet, Color, Reference, File, Folder, EnumMember, Constant, Struct, Event, Operator, TypeParameter, User, Issue};
    }

    static {
        DefaultConstructorMarker defaultConstructorMarker = null;
        EnumMember = new CompletionItemKind("EnumMember", 20, 19, 0L, 2, defaultConstructorMarker);
        Issue = new CompletionItemKind("Issue", 27, 26, 0L, 2, defaultConstructorMarker);
        CompletionItemKind[] completionItemKindArr$values = $values();
        $VALUES = completionItemKindArr$values;
        $ENTRIES = EnumEntriesKt.enumEntries(completionItemKindArr$values);
    }

    private CompletionItemKind(String str, int i, int i2, long j) {
        super(str, i);
        this.value = i2;
        this.defaultDisplayBackgroundColor = j;
        this.displayString = String.valueOf(name().charAt(0));
    }

    public static EnumEntries<CompletionItemKind> getEntries() {
        return $ENTRIES;
    }

    public static CompletionItemKind valueOf(String str) {
        return (CompletionItemKind) Enum.valueOf(CompletionItemKind.class, str);
    }

    public static CompletionItemKind[] values() {
        return (CompletionItemKind[]) $VALUES.clone();
    }

    public final long getDefaultDisplayBackgroundColor() {
        return this.defaultDisplayBackgroundColor;
    }

    /* JADX INFO: renamed from: getDisplayChar, reason: from getter */
    public final String getDisplayString() {
        return this.displayString;
    }

    public final int getValue() {
        return this.value;
    }

    public /* synthetic */ CompletionItemKind(String str, int i, int i2, long j, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, i, i2, (i3 & 2) != 0 ? 0L : j);
    }
}
