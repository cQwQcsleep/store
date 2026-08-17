package org.jetbrains.kotlin.fir.declarations;

import kotlin.Metadata;
import org.jetbrains.kotlin.GeneratedDeclarationKey;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0004\"\u0015\u0010\u0005\u001a\u00020\u0006*\u00020\u00018F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007¨\u0006\b"}, d2 = {"origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "Lorg/jetbrains/kotlin/GeneratedDeclarationKey;", "getOrigin", "(Lorg/jetbrains/kotlin/GeneratedDeclarationKey;)Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "isLazyResolvable", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)Z", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclarationOriginKt {
    public static final FirDeclarationOrigin getOrigin(GeneratedDeclarationKey generatedDeclarationKey) {
        generatedDeclarationKey.getClass();
        return new FirDeclarationOrigin.Plugin(generatedDeclarationKey);
    }

    public static final boolean isLazyResolvable(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        return (firDeclarationOrigin instanceof FirDeclarationOrigin.Source) || (firDeclarationOrigin instanceof FirDeclarationOrigin.ImportedFromObjectOrStatic) || (firDeclarationOrigin instanceof FirDeclarationOrigin.Delegated) || (firDeclarationOrigin instanceof FirDeclarationOrigin.Synthetic) || (firDeclarationOrigin instanceof FirDeclarationOrigin.SubstitutionOverride) || (firDeclarationOrigin instanceof FirDeclarationOrigin.SamConstructor) || (firDeclarationOrigin instanceof FirDeclarationOrigin.WrappedIntegerOperator) || (firDeclarationOrigin instanceof FirDeclarationOrigin.IntersectionOverride) || (firDeclarationOrigin instanceof FirDeclarationOrigin.ScriptCustomization) || (firDeclarationOrigin instanceof FirDeclarationOrigin.Enhancement);
    }
}
