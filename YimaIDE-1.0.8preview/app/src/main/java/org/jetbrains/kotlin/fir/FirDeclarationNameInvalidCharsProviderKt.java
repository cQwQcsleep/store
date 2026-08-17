package org.jetbrains.kotlin.fir;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.util.NullableArrayMapAccessor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\f\n\u0002\b\u0003\"!\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00028BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u001b\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"declarationNameInvalidCharsProvider", "Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider;", "Lorg/jetbrains/kotlin/fir/FirSession;", "getDeclarationNameInvalidCharsProvider", "(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider;", "declarationNameInvalidCharsProvider$delegate", "Lorg/jetbrains/kotlin/util/NullableArrayMapAccessor;", "declarationNameInvalidChars", Argument.Delimiters.none, Argument.Delimiters.none, "getDeclarationNameInvalidChars", "(Lorg/jetbrains/kotlin/fir/FirSession;)Ljava/util/Set;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationNameInvalidCharsProviderKt {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirDeclarationNameInvalidCharsProviderKt.class, "declarationNameInvalidCharsProvider", "getDeclarationNameInvalidCharsProvider(Lorg/jetbrains/kotlin/fir/FirSession;)Lorg/jetbrains/kotlin/fir/FirDeclarationNameInvalidCharsProvider;", 1)};
    private static final NullableArrayMapAccessor declarationNameInvalidCharsProvider$delegate = FirSession.INSTANCE.generateNullableAccessor(Reflection.getOrCreateKotlinClass(FirDeclarationNameInvalidCharsProvider.class));

    public static final Set<Character> getDeclarationNameInvalidChars(FirSession firSession) {
        Set<Character> invalidChars;
        firSession.getClass();
        FirDeclarationNameInvalidCharsProvider declarationNameInvalidCharsProvider = getDeclarationNameInvalidCharsProvider(firSession);
        return (declarationNameInvalidCharsProvider == null || (invalidChars = declarationNameInvalidCharsProvider.getInvalidChars()) == null) ? SetsKt.emptySet() : invalidChars;
    }

    private static final FirDeclarationNameInvalidCharsProvider getDeclarationNameInvalidCharsProvider(FirSession firSession) {
        return (FirDeclarationNameInvalidCharsProvider) declarationNameInvalidCharsProvider$delegate.getValue(firSession, $$delegatedProperties[0]);
    }
}
