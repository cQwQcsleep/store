package org.jetbrains.kotlin.fir.session;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.platform.TargetPlatform;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J.\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0014R\u0014\u0010\u0011\u001a\u00020\u00128TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/FirMetadataSessionFactoryForHmppCompilation;", "Lorg/jetbrains/kotlin/fir/session/AbstractFirMetadataSessionFactory;", "targetPlatform", "Lorg/jetbrains/kotlin/platform/TargetPlatform;", "<init>", "(Lorg/jetbrains/kotlin/platform/TargetPlatform;)V", "createPlatformSpecificSharedProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "scopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "context", "Lorg/jetbrains/kotlin/fir/session/AbstractFirMetadataSessionFactory$Context;", "createSeparateSharedProvidersInHmppCompilation", Argument.Delimiters.none, "getCreateSeparateSharedProvidersInHmppCompilation", "()Z", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirMetadataSessionFactoryForHmppCompilation extends AbstractFirMetadataSessionFactory {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirMetadataSessionFactoryForHmppCompilation(TargetPlatform targetPlatform) {
        super(targetPlatform);
        targetPlatform.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.session.FirAbstractSessionFactory
    public List<FirSymbolProvider> createPlatformSpecificSharedProviders(FirSession session, FirModuleData moduleData, FirKotlinScopeProvider scopeProvider, AbstractFirMetadataSessionFactory.Context context) {
        session.getClass();
        moduleData.getClass();
        scopeProvider.getClass();
        context.getClass();
        return CollectionsKt.emptyList();
    }

    @Override // org.jetbrains.kotlin.fir.session.AbstractFirMetadataSessionFactory
    public boolean getCreateSeparateSharedProvidersInHmppCompilation() {
        return true;
    }
}
