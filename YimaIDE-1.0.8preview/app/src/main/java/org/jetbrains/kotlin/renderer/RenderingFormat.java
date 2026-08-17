package org.jetbrains.kotlin.renderer;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&j\u0002\b\u0004j\u0002\b\u0005¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/renderer/RenderingFormat;", "", "<init>", "(Ljava/lang/String;I)V", "PLAIN", "HTML", "escape", "", "string", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
public enum RenderingFormat {
    PLAIN { // from class: org.jetbrains.kotlin.renderer.RenderingFormat.PLAIN
        @Override // org.jetbrains.kotlin.renderer.RenderingFormat
        public String escape(String string) {
            string.getClass();
            return string;
        }
    },
    HTML { // from class: org.jetbrains.kotlin.renderer.RenderingFormat.HTML
        @Override // org.jetbrains.kotlin.renderer.RenderingFormat
        public String escape(String string) {
            string.getClass();
            return StringsKt.replace$default(StringsKt.replace$default(string, "<", "&lt;", false, 4, (Object) null), ">", "&gt;", false, 4, (Object) null);
        }
    };

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* synthetic */ RenderingFormat(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static EnumEntries<RenderingFormat> getEntries() {
        return $ENTRIES;
    }

    public abstract String escape(String string);
}
