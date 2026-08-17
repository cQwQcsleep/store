package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\bf\u0018\u00002\u00020\u0001J\"\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\nÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContextFactory;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/FirExpectActualMatchingContext;", "actualSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "actualScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "allowedWritingMemberExpectForActualMapping", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface FirExpectActualMatchingContextFactory extends FirSessionComponent {
    static /* synthetic */ FirExpectActualMatchingContext create$default(FirExpectActualMatchingContextFactory firExpectActualMatchingContextFactory, FirSession firSession, ScopeSession scopeSession, boolean z, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: create");
            return null;
        }
        if ((i & 4) != 0) {
            z = false;
        }
        return firExpectActualMatchingContextFactory.create(firSession, scopeSession, z);
    }

    FirExpectActualMatchingContext create(FirSession actualSession, ScopeSession actualScopeSession, boolean allowedWritingMemberExpectForActualMapping);
}
