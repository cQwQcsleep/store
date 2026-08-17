package org.jetbrains.kotlin.fir.resolve;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bBG\b\u0002\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fB;\b\u0016\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u000e\u0010\u0010R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0013\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001a¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration;", Argument.Delimiters.none, "scopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "containingClassDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "topContainer", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "sealedClassForContextSensitiveResolution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "<init>", "(Ljava/lang/Iterable;Ljava/util/List;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;)V", "(Ljava/lang/Iterable;Ljava/util/List;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)V", "getScopes", "()Ljava/lang/Iterable;", "getContainingClassDeclarations", "()Ljava/util/List;", "getUseSiteFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getTopContainer", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getSealedClassForContextSensitiveResolution", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeResolutionConfiguration {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final List<FirClass> containingClassDeclarations;
    private final Iterable<FirScope> scopes;
    private final FirRegularClassSymbol sealedClassForContextSensitiveResolution;
    private final FirDeclaration topContainer;
    private final FirFile useSiteFile;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public TypeResolutionConfiguration(Iterable<? extends FirScope> iterable, List<? extends FirClass> list, FirFile firFile, FirDeclaration firDeclaration) {
        this(iterable, list, firFile, firDeclaration, null);
        iterable.getClass();
        list.getClass();
    }

    public final List<FirClass> getContainingClassDeclarations() {
        return this.containingClassDeclarations;
    }

    public final Iterable<FirScope> getScopes() {
        return this.scopes;
    }

    public final FirRegularClassSymbol getSealedClassForContextSensitiveResolution() {
        return this.sealedClassForContextSensitiveResolution;
    }

    public final FirDeclaration getTopContainer() {
        return this.topContainer;
    }

    public final FirFile getUseSiteFile() {
        return this.useSiteFile;
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration$Companion;", Argument.Delimiters.none, "<init>", "()V", "createForContextSensitiveResolution", "Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration;", "containingClassDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "topContainer", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "sealedClassForContextSensitiveResolution", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final TypeResolutionConfiguration createForContextSensitiveResolution(List<? extends FirClass> containingClassDeclarations, FirFile useSiteFile, FirDeclaration topContainer, FirRegularClassSymbol sealedClassForContextSensitiveResolution) {
            containingClassDeclarations.getClass();
            useSiteFile.getClass();
            sealedClassForContextSensitiveResolution.getClass();
            if (sealedClassForContextSensitiveResolution.getResolvedStatus().getModality() == Modality.SEALED) {
                return new TypeResolutionConfiguration(CollectionsKt.emptyList(), containingClassDeclarations, useSiteFile, topContainer, sealedClassForContextSensitiveResolution, (DefaultConstructorMarker) null);
            }
            w01.a("Failed requirement.");
            return null;
        }

        private Companion() {
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private TypeResolutionConfiguration(Iterable<? extends FirScope> iterable, List<? extends FirClass> list, FirFile firFile, FirDeclaration firDeclaration, FirRegularClassSymbol firRegularClassSymbol) {
        this.scopes = iterable;
        this.containingClassDeclarations = list;
        this.useSiteFile = firFile;
        this.topContainer = firDeclaration;
        this.sealedClassForContextSensitiveResolution = firRegularClassSymbol;
    }

    public /* synthetic */ TypeResolutionConfiguration(Iterable iterable, List list, FirFile firFile, FirDeclaration firDeclaration, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iterable, list, firFile, (i & 8) != 0 ? null : firDeclaration);
    }

    public /* synthetic */ TypeResolutionConfiguration(Iterable iterable, List list, FirFile firFile, FirDeclaration firDeclaration, FirRegularClassSymbol firRegularClassSymbol, DefaultConstructorMarker defaultConstructorMarker) {
        this(iterable, list, firFile, firDeclaration, firRegularClassSymbol);
    }
}
