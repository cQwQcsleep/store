package org.jetbrains.kotlin.fir.extensions;

import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.caches.FirLazyValue;
import org.jetbrains.kotlin.fir.extensions.FirDeclarationGenerationExtension;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 92\u00020\u0001:\u00029:B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001a\u0010\u000e\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0017b\u0002\b\u0012J.\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u000f2\n\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u00152\u0006\u0010\u0006\u001a\u00020\u00162\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0016J&\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u0017\u001a\n\u0018\u00010\u001fj\u0004\u0018\u0001` H\u0016J&\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001b2\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u0017\u001a\n\u0018\u00010\u001fj\u0004\u0018\u0001` H\u0016J\u001a\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001b2\n\u0010\u0017\u001a\u00060\u001fj\u0002` H\u0016J\u0010\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(H\u0016J&\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00160*2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030\u00152\n\u0010\u0017\u001a\u00060\u001fj\u0002` H\u0016J&\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00160*2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030\u00152\n\u0010\u0017\u001a\u00060\u0018j\u0002`\u0019H\u0016J\u0012\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001e0*H\u0017b\u0002\b\u0012J\u0012\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00110*H\u0017b\u0002\b\u0012R\u0011\u0010\u0006\u001a\u00020\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0019\u0010\n\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR,\u0010/\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110*008\u0006X\u0087\u0004r\u0002\b5¢\u0006\u000e\n\u0000\u0012\u0004\b1\u00102\u001a\u0004\b3\u00104R,\u00106\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0*008\u0006X\u0087\u0004r\u0002\b5¢\u0006\u000e\n\u0000\u0012\u0004\b7\u00102\u001a\u0004\b8\u00104¨\u0006;"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "getName", "()Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "extensionType", "Lkotlin/reflect/KClass;", "getExtensionType", "()Lkotlin/reflect/KClass;", "generateTopLevelClassLikeDeclaration", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "Lorg/jetbrains/kotlin/fir/extensions/ExperimentalTopLevelDeclarationsGenerationApi;", "generateNestedClassLikeDeclaration", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "Lorg/jetbrains/kotlin/name/Name;", "context", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Nested;", "Lorg/jetbrains/kotlin/fir/extensions/NestedClassGenerationContext;", "generateFunctions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "callableId", "Lorg/jetbrains/kotlin/name/CallableId;", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Member;", "Lorg/jetbrains/kotlin/fir/extensions/MemberGenerationContext;", "generateProperties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "generateConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "hasPackage", Argument.Delimiters.none, "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getCallableNamesForClass", Argument.Delimiters.none, "classSymbol", "getNestedClassifiersNames", "getTopLevelCallableIds", "getTopLevelClassIds", "topLevelClassIdsCache", "Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", "getTopLevelClassIdsCache$annotations", "()V", "getTopLevelClassIdsCache", "()Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionApiInternals;", "topLevelCallableIdsCache", "getTopLevelCallableIdsCache$annotations", "getTopLevelCallableIdsCache", "Companion", "Factory", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDeclarationGenerationExtension extends FirExtension {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final FirExtensionPointName NAME = new FirExtensionPointName("ExistingClassModification");
    private final KClass<? extends FirExtension> extensionType;
    private final FirLazyValue<Set<CallableId>> topLevelCallableIdsCache;
    private final FirLazyValue<Set<ClassId>> topLevelClassIdsCache;

    @Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bæ\u0080\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0003À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension$Factory;", "Lorg/jetbrains/kotlin/fir/extensions/FirExtension$Factory;", "Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public interface Factory extends FirExtension.Factory<FirDeclarationGenerationExtension> {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirDeclarationGenerationExtension(FirSession firSession) {
        super(firSession);
        firSession.getClass();
        this.extensionType = Reflection.getOrCreateKotlinClass(FirDeclarationGenerationExtension.class);
        this.topLevelClassIdsCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createLazyValue(new Function0() { // from class: p15
            public final Object invoke() {
                return FirDeclarationGenerationExtension.b(this.b);
            }
        });
        this.topLevelCallableIdsCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createLazyValue(new Function0() { // from class: q15
            public final Object invoke() {
                return FirDeclarationGenerationExtension.a(this.b);
            }
        });
    }

    public static Set a(FirDeclarationGenerationExtension firDeclarationGenerationExtension) {
        return firDeclarationGenerationExtension.getTopLevelCallableIds();
    }

    public static Set b(FirDeclarationGenerationExtension firDeclarationGenerationExtension) {
        return firDeclarationGenerationExtension.getTopLevelClassIds();
    }

    @FirExtensionApiInternals
    public static /* synthetic */ void getTopLevelCallableIdsCache$annotations() {
    }

    @FirExtensionApiInternals
    public static /* synthetic */ void getTopLevelClassIdsCache$annotations() {
    }

    public List<FirConstructorSymbol> generateConstructors(DeclarationGenerationContext.Member context) {
        context.getClass();
        return CollectionsKt.emptyList();
    }

    public List<FirNamedFunctionSymbol> generateFunctions(CallableId callableId, DeclarationGenerationContext.Member context) {
        callableId.getClass();
        return CollectionsKt.emptyList();
    }

    public FirClassLikeSymbol<?> generateNestedClassLikeDeclaration(FirClassSymbol<?> owner, Name name, DeclarationGenerationContext.Nested context) {
        owner.getClass();
        name.getClass();
        context.getClass();
        return null;
    }

    public List<FirPropertySymbol> generateProperties(CallableId callableId, DeclarationGenerationContext.Member context) {
        callableId.getClass();
        return CollectionsKt.emptyList();
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    public FirClassLikeSymbol<?> generateTopLevelClassLikeDeclaration(ClassId classId) {
        classId.getClass();
        return null;
    }

    public Set<Name> getCallableNamesForClass(FirClassSymbol<?> classSymbol, DeclarationGenerationContext.Member context) {
        classSymbol.getClass();
        context.getClass();
        return SetsKt.emptySet();
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirExtension
    public final KClass<? extends FirExtension> getExtensionType() {
        return this.extensionType;
    }

    @Override // org.jetbrains.kotlin.fir.extensions.FirExtension
    public final FirExtensionPointName getName() {
        return NAME;
    }

    public Set<Name> getNestedClassifiersNames(FirClassSymbol<?> classSymbol, DeclarationGenerationContext.Nested context) {
        classSymbol.getClass();
        context.getClass();
        return SetsKt.emptySet();
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    public Set<CallableId> getTopLevelCallableIds() {
        return SetsKt.emptySet();
    }

    public final FirLazyValue<Set<CallableId>> getTopLevelCallableIdsCache() {
        return this.topLevelCallableIdsCache;
    }

    @ExperimentalTopLevelDeclarationsGenerationApi
    public Set<ClassId> getTopLevelClassIds() {
        return SetsKt.emptySet();
    }

    public final FirLazyValue<Set<ClassId>> getTopLevelClassIdsCache() {
        return this.topLevelClassIdsCache;
    }

    public boolean hasPackage(FqName packageFqName) {
        packageFqName.getClass();
        return false;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/FirDeclarationGenerationExtension$Companion;", Argument.Delimiters.none, "<init>", "()V", "NAME", "Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "getNAME", "()Lorg/jetbrains/kotlin/fir/extensions/FirExtensionPointName;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirExtensionPointName getNAME() {
            return FirDeclarationGenerationExtension.NAME;
        }

        private Companion() {
        }
    }
}
