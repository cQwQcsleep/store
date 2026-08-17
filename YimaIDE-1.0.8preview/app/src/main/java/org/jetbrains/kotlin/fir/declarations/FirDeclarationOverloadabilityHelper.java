package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001:\u0001\fJ \u0010\u0002\u001a\u00020\u00032\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005H&J \u0010\u0007\u001a\u00020\b2\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0005H&J \u0010\t\u001a\u00020\u00032\n\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u00052\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\rÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOverloadabilityHelper;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "isConflicting", Argument.Delimiters.none, "a", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "b", "getContextParameterShadowing", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOverloadabilityHelper$ContextParameterShadowing;", "isExtensionShadowedByMember", "extension", "member", "ContextParameterShadowing", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirDeclarationOverloadabilityHelper extends FirSessionComponent {

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOverloadabilityHelper$ContextParameterShadowing;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "None", "Shadowing", "BothWays", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum ContextParameterShadowing {
        None,
        Shadowing,
        BothWays;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<ContextParameterShadowing> getEntries() {
            return $ENTRIES;
        }
    }

    ContextParameterShadowing getContextParameterShadowing(FirCallableSymbol<?> a, FirCallableSymbol<?> b);

    boolean isConflicting(FirCallableSymbol<?> a, FirCallableSymbol<?> b);

    boolean isExtensionShadowedByMember(FirCallableSymbol<?> extension, FirCallableSymbol<?> member);
}
