package org.jetbrains.kotlin.fir.expressions;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007j\u0012\b\b\u0012\u000e\b\t\u0012\n\b\n\u0012\u0006\b\n0\u000b8\fj\u0002\b\rj\u0002\b\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/expressions/InaccessibleReceiverKind;", Argument.Delimiters.none, "producesApplicableCandidate", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;IZ)V", "getProducesApplicableCandidate", "()Z", "SecondaryConstructor", "Lorg/jetbrains/kotlin/fir/OnlyForDefaultLanguageFeatureDisabled;", "languageFeature", "Lorg/jetbrains/kotlin/config/LanguageFeature;", "ImprovedResolutionInSecondaryConstructors", "OuterClassOfNonInner", "ClassHeader", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public enum InaccessibleReceiverKind {
    SecondaryConstructor(true),
    OuterClassOfNonInner(false),
    ClassHeader(false);

    private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());
    private final boolean producesApplicableCandidate;

    InaccessibleReceiverKind(boolean z) {
        this.producesApplicableCandidate = z;
    }

    public static EnumEntries<InaccessibleReceiverKind> getEntries() {
        return $ENTRIES;
    }

    public final boolean getProducesApplicableCandidate() {
        return this.producesApplicableCandidate;
    }
}
