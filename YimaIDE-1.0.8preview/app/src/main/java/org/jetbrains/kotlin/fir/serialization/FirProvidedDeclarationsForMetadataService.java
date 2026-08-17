package org.jetbrains.kotlin.fir.serialization;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b&\u0018\u0000 \u00142\u00020\u0001:\u0001\u0014B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&J\"\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\t\u001a\u00020\nH&J\"\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00100\u00052\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\t\u001a\u00020\nH&J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0010H&¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirProvidedDeclarationsForMetadataService;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "getProvidedTopLevelDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getProvidedConstructors", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getProvidedCallables", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "registerDeclaration", Argument.Delimiters.none, "declaration", "Companion", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirProvidedDeclarationsForMetadataService implements FirSessionComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    public abstract List<FirCallableDeclaration> getProvidedCallables(FirClassSymbol<?> owner, ScopeSession scopeSession);

    public abstract List<FirConstructor> getProvidedConstructors(FirClassSymbol<?> owner, ScopeSession scopeSession);

    public abstract List<FirDeclaration> getProvidedTopLevelDeclarations(FqName packageFqName, ScopeSession scopeSession);

    public abstract void registerDeclaration(FirCallableDeclaration declaration);

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/serialization/FirProvidedDeclarationsForMetadataService$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/serialization/FirProvidedDeclarationsForMetadataService;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:fir-serialization"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirProvidedDeclarationsForMetadataService create(FirSession session) {
            session.getClass();
            return new FirProvidedDeclarationsForMetadataServiceImpl(session);
        }

        private Companion() {
        }
    }
}
