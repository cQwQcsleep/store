package org.jetbrains.kotlin.fir.resolve.calls.jvm;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtensionKt;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolver;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolverFactory;
import org.jetbrains.kotlin.fir.resolve.calls.overloads.ReplOverloadCallConflictResolver;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/calls/jvm/JvmCallConflictResolverFactory;", "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolverFactory;", "<init>", "()V", "createAdditionalResolvers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/overloads/ConeCallConflictResolver;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmCallConflictResolverFactory extends ConeCallConflictResolverFactory {
    public static final JvmCallConflictResolverFactory INSTANCE = new JvmCallConflictResolverFactory();

    private JvmCallConflictResolverFactory() {
    }

    @Override // org.jetbrains.kotlin.fir.resolve.calls.overloads.ConeCallConflictResolverFactory
    public List<ConeCallConflictResolver> createAdditionalResolvers(FirSession session) {
        session.getClass();
        boolean z = FirReplSnippetResolveExtensionKt.getReplSnippetResolveExtension(session) != null;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        if (z) {
            listCreateListBuilder.add(ReplOverloadCallConflictResolver.INSTANCE);
        }
        listCreateListBuilder.add(new JvmPlatformOverloadsConflictResolver(session));
        return CollectionsKt.build(listCreateListBuilder);
    }
}
