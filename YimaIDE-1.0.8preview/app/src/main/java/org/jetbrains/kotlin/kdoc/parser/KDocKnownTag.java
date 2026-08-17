package org.jetbrains.kotlin.kdoc.parser;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.util.capitalizeDecapitalize.CapitalizeDecapitalizeKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0012\b\u0086\u0081\u0002\u0018\u0000 \u00142\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0001\u0014B\u0019\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/kdoc/parser/KDocKnownTag;", "", "isReferenceRequired", "", "isSectionStart", "<init>", "(Ljava/lang/String;IZZ)V", "()Z", "AUTHOR", "THROWS", "EXCEPTION", "PARAM", "RECEIVER", "RETURN", "SEE", "SINCE", "CONSTRUCTOR", "PROPERTY", "SAMPLE", "SUPPRESS", "Companion", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
public enum KDocKnownTag {
    AUTHOR(false, false),
    THROWS(true, false),
    EXCEPTION(true, false),
    PARAM(true, false),
    RECEIVER(false, false),
    RETURN(false, false),
    SEE(true, false),
    SINCE(false, false),
    CONSTRUCTOR(false, true),
    PROPERTY(true, true),
    SAMPLE(true, false),
    SUPPRESS(false, false);

    private final boolean isReferenceRequired;
    private final boolean isSectionStart;
    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    KDocKnownTag(boolean z, boolean z2) {
        this.isReferenceRequired = z;
        this.isSectionStart = z2;
    }

    public static EnumEntries<KDocKnownTag> getEntries() {
        return $ENTRIES;
    }

    /* JADX INFO: renamed from: isReferenceRequired, reason: from getter */
    public final boolean getIsReferenceRequired() {
        return this.isReferenceRequired;
    }

    /* JADX INFO: renamed from: isSectionStart, reason: from getter */
    public final boolean getIsSectionStart() {
        return this.isSectionStart;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/kdoc/parser/KDocKnownTag$Companion;", "", "<init>", "()V", "findByTagName", "Lorg/jetbrains/kotlin/kdoc/parser/KDocKnownTag;", "tagName", "", "org.jetbrains.kotlin:psi-api"}, k = 1, mv = {2, 4, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final KDocKnownTag findByTagName(CharSequence tagName) {
            tagName.getClass();
            if (StringsKt.startsWith$default(tagName, '@', false, 2, (Object) null)) {
                tagName = tagName.subSequence(1, tagName.length());
            }
            try {
                return KDocKnownTag.valueOf(CapitalizeDecapitalizeKt.toUpperCaseAsciiOnly(tagName.toString()));
            } catch (IllegalArgumentException unused) {
                return null;
            }
        }

        private Companion() {
        }
    }
}
