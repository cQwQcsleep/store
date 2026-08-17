package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentList;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtension;
import org.jetbrains.kotlin.fir.extensions.FirReplSnippetResolveExtensionKt;
import org.jetbrains.kotlin.fir.extensions.FirSupertypeGenerationExtension;
import org.jetbrains.kotlin.fir.extensions.FirSupertypeGenerationExtensionKt;
import org.jetbrains.kotlin.fir.extensions.GeneratedDeclarationsUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeResolutionConfiguration;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeParameterSupertype;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSpecificTypeResolverTransformer;
import org.jetbrains.kotlin.fir.resolve.transformers.FirSupertypeResolverVisitor;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.LocalClassesNavigationInfo;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.scopes.ImportingScopesKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirErrorTypeRefBuilder;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.util.PrivateForInline;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ø\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0016\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001:\u0001jBY\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0010\b\u0002\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000b\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\u000e\b\u0002\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012¢\u0006\u0004\b\u0014\u0010\u0015J-\u0010)\u001a\u0002H*\"\u0004\b\u0000\u0010*2\u0006\u0010+\u001a\u00020\u00102\f\u0010,\u001a\b\u0012\u0004\u0012\u0002H*0-H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010.J\u0016\u0010/\u001a\u0004\u0018\u00010\u00102\n\u00100\u001a\u0006\u0012\u0002\b\u000301H\u0002J\u001a\u00102\u001a\u00020\u00022\u0006\u00103\u001a\u0002042\b\u00105\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00106\u001a\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`72\u0006\u0010+\u001a\u00020\u0010H\u0002J\u001a\u00108\u001a\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`72\u0006\u0010'\u001a\u00020(H\u0002J\"\u00109\u001a\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`72\u0006\u0010:\u001a\u00020#2\u0006\u0010;\u001a\u00020<H\u0002J\u001a\u0010=\u001a\f\u0012\u0004\u0012\u00020\f0\u000bj\u0002`72\u0006\u0010:\u001a\u00020#H\u0002J&\u0010>\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010?\u001a\u00020#2\u0006\u0010@\u001a\u00020<2\u0006\u0010;\u001a\u00020<H\u0002J\u0010\u0010A\u001a\u00020\u00022\u0006\u0010?\u001a\u00020#H\u0014J.\u0010B\u001a\u00020\u00022\u0006\u0010C\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u00122\u000e\b\u0002\u0010G\u001a\b\u0012\u0004\u0012\u00020D0HH\u0002J\u001e\u0010I\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010C\u001a\u00020D2\u0006\u0010;\u001a\u00020<H\u0002J6\u0010J\u001a\b\u0012\u0004\u0012\u00020K0\u00122\u0006\u0010C\u001a\u00020D2\u001e\u0010L\u001a\u001a\u0012\u0004\u0012\u00020N\u0012\u0004\u0012\u00020O\u0012\n\u0012\b\u0012\u0004\u0012\u00020K0\u00120MH\u0002J\u001a\u0010P\u001a\u00020\u00022\u0006\u0010Q\u001a\u00020\u00132\b\u00105\u001a\u0004\u0018\u00010\u0003H\u0002J-\u0010R\u001a\u0002HS\"\u0004\b\u0000\u0010S2\u0006\u0010T\u001a\u00020#2\f\u0010U\u001a\b\u0012\u0004\u0012\u0002HS0-H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010VJ\u001a\u0010W\u001a\u00020\u00022\u0006\u0010X\u001a\u00020Y2\b\u00105\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010Z\u001a\u00020\u00022\u0006\u0010[\u001a\u00020\\2\b\u00105\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010]\u001a\u00020\u00022\u0006\u0010'\u001a\u00020(2\b\u00105\u001a\u0004\u0018\u00010\u0003H\u0016J*\u0010J\u001a\b\u0012\u0004\u0012\u00020K0\u00122\u0006\u0010C\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020F0\u00122\u0006\u0010^\u001a\u00020<J.\u0010_\u001a\u00020\u00022\u0006\u0010:\u001a\u00020D2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020K0`2\u0006\u0010a\u001a\u00020N2\u0006\u0010b\u001a\u00020OH\u0002J \u0010c\u001a\u00020\u00022\u0006\u0010:\u001a\u00020Y2\u0006\u0010a\u001a\u00020N2\u0006\u0010b\u001a\u00020OH\u0002J\u001a\u0010d\u001a\u00020\u00022\u0006\u0010e\u001a\u00020f2\b\u00105\u001a\u0004\u0018\u00010\u0003H\u0016J$\u0010g\u001a\b\u0012\u0004\u0012\u00020K0\u00122\u0006\u0010e\u001a\u00020f2\u0006\u0010h\u001a\u00020F2\u0006\u0010^\u001a\u00020<J\u001a\u0010i\u001a\u00020\u00022\u0006\u0010+\u001a\u00020\u00102\b\u00105\u001a\u0004\u0018\u00010\u0003H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\n\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R*\u0010\u000f\u001a\u0004\u0018\u00010\u00108\u0006@\u0006X\u0087\u000er\u0002\b\u001e¢\u0006\u0014\n\u0000\u0012\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010!\u001a\b\u0012\u0004\u0012\u00020#0\"8\u0006X\u0087\u0004r\u0002\b\u001e¢\u0006\u000e\n\u0000\u0012\u0004\b$\u0010\u0019\u001a\u0004\b%\u0010&R\u0010\u0010'\u001a\u0004\u0018\u00010(X\u0082\u000e¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006k"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSupertypeResolverVisitor;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "supertypeComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "scopeForLocalClass", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "localClassesNavigationInfo", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/SupertypeComputationSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lkotlinx/collections/immutable/PersistentList;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;Lorg/jetbrains/kotlin/fir/declarations/FirFile;Ljava/util/List;)V", "getLocalClassesNavigationInfo", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/LocalClassesNavigationInfo;", "getUseSiteFile$annotations", "()V", "getUseSiteFile", "()Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "setUseSiteFile", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "supertypeGenerationExtensions", "Lorg/jetbrains/kotlin/fir/extensions/FirSupertypeGenerationExtension;", "classDeclarationsStack", "Lkotlin/collections/ArrayDeque;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getClassDeclarationsStack$annotations", "getClassDeclarationsStack", "()Lkotlin/collections/ArrayDeque;", "replSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "withFile", "R", "file", "block", "Lkotlin/Function0;", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "getFirClassifierContainerFileIfAny", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "prepareFileScopes", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ScopePersistentList;", "prepareReplScopes", "prepareScopeForNestedClasses", "klass", "forStaticNestedClass", Argument.Delimiters.none, "prepareScopeForCompanion", "calculateScopes", "outerClass", "withCompanionScopes", "resolveAllSupertypesForOuterClass", "resolveAllSupertypes", "classLikeDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "supertypeRefs", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "visited", Argument.Delimiters.none, "prepareScopes", "resolveSpecificClassLikeSupertypes", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "resolveSuperTypeRefs", "Lkotlin/Function2;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration;", "visitDeclarationContent", "declaration", "withClass", "T", "firClass", "body", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visitReplSnippet", "resolveRecursively", "addSupertypesFromExtensions", Argument.Delimiters.none, "typeResolveTransformer", "configuration", "addSupertypesToGeneratedNestedClasses", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "resolveTypeAliasSupertype", "expandedTypeRef", "visitFile", "TypeResolveServiceForPlugins", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class FirSupertypeResolverVisitor extends FirDefaultVisitor<Unit, Object> {
    private final ArrayDeque<FirClass> classDeclarationsStack;
    private final LocalClassesNavigationInfo localClassesNavigationInfo;
    private FirReplSnippet replSnippet;
    private final PersistentList<FirScope> scopeForLocalClass;
    private final ScopeSession scopeSession;
    private final FirSession session;
    private final SupertypeComputationSession supertypeComputationSession;
    private final List<FirSupertypeGenerationExtension> supertypeGenerationExtensions;
    private FirFile useSiteFile;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSupertypeResolverVisitor$TypeResolveServiceForPlugins;", "Lorg/jetbrains/kotlin/fir/extensions/FirSupertypeGenerationExtension$TypeResolveService;", "typeResolveTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "configuration", "Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration;)V", "getTypeResolveTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/FirSpecificTypeResolverTransformer;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/resolve/TypeResolutionConfiguration;", "resolveUserType", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TypeResolveServiceForPlugins extends FirSupertypeGenerationExtension.TypeResolveService {
        private final TypeResolutionConfiguration configuration;
        private final FirSpecificTypeResolverTransformer typeResolveTransformer;

        public TypeResolveServiceForPlugins(FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer, TypeResolutionConfiguration typeResolutionConfiguration) {
            firSpecificTypeResolverTransformer.getClass();
            typeResolutionConfiguration.getClass();
            this.typeResolveTransformer = firSpecificTypeResolverTransformer;
            this.configuration = typeResolutionConfiguration;
        }

        public final TypeResolutionConfiguration getConfiguration() {
            return this.configuration;
        }

        public final FirSpecificTypeResolverTransformer getTypeResolveTransformer() {
            return this.typeResolveTransformer;
        }

        @Override // org.jetbrains.kotlin.fir.extensions.FirSupertypeGenerationExtension.TypeResolveService
        public FirResolvedTypeRef resolveUserType(FirUserTypeRef type) {
            type.getClass();
            FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer = this.typeResolveTransformer;
            boolean areBareTypesAllowed = firSpecificTypeResolverTransformer.getAreBareTypesAllowed();
            firSpecificTypeResolverTransformer.setAreBareTypesAllowed(true);
            try {
                return type.transform(this.typeResolveTransformer, this.configuration);
            } finally {
                firSpecificTypeResolverTransformer.setAreBareTypesAllowed(areBareTypesAllowed);
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FirSupertypeResolverVisitor(FirSession firSession, SupertypeComputationSession supertypeComputationSession, ScopeSession scopeSession, PersistentList<? extends FirScope> persistentList, LocalClassesNavigationInfo localClassesNavigationInfo, FirFile firFile, List<? extends FirDeclaration> list) {
        firSession.getClass();
        supertypeComputationSession.getClass();
        scopeSession.getClass();
        list.getClass();
        this.session = firSession;
        this.supertypeComputationSession = supertypeComputationSession;
        this.scopeSession = scopeSession;
        this.scopeForLocalClass = persistentList;
        this.localClassesNavigationInfo = localClassesNavigationInfo;
        this.useSiteFile = firFile;
        this.supertypeGenerationExtensions = FirSupertypeGenerationExtensionKt.getSupertypeGenerators(FirExtensionServiceKt.getExtensionService(firSession));
        this.classDeclarationsStack = new ArrayDeque<>();
        for (FirDeclaration firDeclaration : list) {
            if (firDeclaration instanceof FirClass) {
                this.classDeclarationsStack.add(firDeclaration);
            }
        }
    }

    private final void addSupertypesFromExtensions(FirClassLikeDeclaration klass, List<FirResolvedTypeRef> supertypeRefs, FirSpecificTypeResolverTransformer typeResolveTransformer, TypeResolutionConfiguration configuration) {
        if (this.supertypeGenerationExtensions.isEmpty()) {
            return;
        }
        TypeResolveServiceForPlugins typeResolveServiceForPlugins = new TypeResolveServiceForPlugins(typeResolveTransformer, configuration);
        for (FirSupertypeGenerationExtension firSupertypeGenerationExtension : this.supertypeGenerationExtensions) {
            if (firSupertypeGenerationExtension.needTransformSupertypes(klass)) {
                List<FirResolvedTypeRef> list = supertypeRefs;
                for (ConeKotlinType coneKotlinType : firSupertypeGenerationExtension.computeAdditionalSupertypes(klass, supertypeRefs, typeResolveServiceForPlugins)) {
                    KtSourceElement source = klass.getSource();
                    list.add(UtilsKt.toFirResolvedTypeRef$default(coneKotlinType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.PluginGenerated.INSTANCE, null, 2, null) : null, null, 2, null));
                }
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void addSupertypesToGeneratedNestedClasses(FirRegularClass klass, FirSpecificTypeResolverTransformer typeResolveTransformer, TypeResolutionConfiguration configuration) throws KotlinIllegalArgumentExceptionWithAttachments {
        boolean z;
        if (this.supertypeGenerationExtensions.isEmpty()) {
            return;
        }
        TypeResolveServiceForPlugins typeResolveServiceForPlugins = new TypeResolveServiceForPlugins(typeResolveTransformer, configuration);
        for (FirClassLikeDeclaration firClassLikeDeclaration : GeneratedDeclarationsUtilsKt.generatedNestedClassifiers(klass, this.session)) {
            if (firClassLikeDeclaration instanceof FirRegularClass) {
                FirRegularClass firRegularClass = (FirRegularClass) firClassLikeDeclaration;
                List<FirTypeRef> superTypeRefs = firRegularClass.getSuperTypeRefs();
                boolean z2 = false;
                if (!(superTypeRefs instanceof Collection) || !superTypeRefs.isEmpty()) {
                    Iterator<T> it = superTypeRefs.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (!(((FirTypeRef) it.next()) instanceof FirResolvedTypeRef)) {
                                z = false;
                                break;
                            }
                        } else {
                            z = true;
                            break;
                        }
                    }
                } else {
                    z = true;
                    break;
                }
                if (!z) {
                    KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Supertypes of generated class should be resolved");
                    ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                    List<FirTypeRef> superTypeRefs2 = firRegularClass.getSuperTypeRefs();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : superTypeRefs2) {
                        if (!(((FirTypeRef) obj) instanceof FirResolvedTypeRef)) {
                            arrayList.add(obj);
                        }
                    }
                    exceptionAttachmentBuilder.withEntry("Unresolved types", CollectionsKt.joinToString$default(arrayList, ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: we5
                        public final Object invoke(Object obj2) {
                            return FirSupertypeResolverVisitor.addSupertypesToGeneratedNestedClasses$lambda$2$1((FirTypeRef) obj2);
                        }
                    }, 30, (Object) null));
                    kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                    throw kotlinIllegalArgumentExceptionWithAttachments;
                }
                List mutableList = CollectionsKt.toMutableList(firRegularClass.getSuperTypeRefs());
                mutableList.getClass();
                List<? extends FirTypeRef> listAsMutableList = TypeIntrinsics.asMutableList(mutableList);
                for (FirSupertypeGenerationExtension firSupertypeGenerationExtension : this.supertypeGenerationExtensions) {
                    if (firSupertypeGenerationExtension.needTransformSupertypes(firClassLikeDeclaration)) {
                        List<ConeKotlinType> listComputeAdditionalSupertypesForGeneratedNestedClass = firSupertypeGenerationExtension.computeAdditionalSupertypesForGeneratedNestedClass(firRegularClass, typeResolveServiceForPlugins);
                        if (!listComputeAdditionalSupertypesForGeneratedNestedClass.isEmpty()) {
                            List<? extends FirTypeRef> list = listAsMutableList;
                            List<ConeKotlinType> list2 = listComputeAdditionalSupertypesForGeneratedNestedClass;
                            ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
                            for (ConeKotlinType coneKotlinType : list2) {
                                KtSourceElement source = klass.getSource();
                                arrayList2.add(UtilsKt.toFirResolvedTypeRef$default(coneKotlinType, source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.PluginGenerated.INSTANCE, null, 2, null) : null, null, 2, null));
                            }
                            CollectionsKt.addAll(list, arrayList2);
                            z2 = true;
                        }
                    }
                }
                if (z2 && !listAsMutableList.isEmpty()) {
                    final Function1 function1 = new Function1() { // from class: xe5
                        public final Object invoke(Object obj2) {
                            return Boolean.valueOf(FirSupertypeResolverVisitor.i((FirResolvedTypeRef) obj2));
                        }
                    };
                    listAsMutableList.removeIf(new Predicate() { // from class: ye5
                        @Override // java.util.function.Predicate
                        public final boolean test(Object obj2) {
                            return FirSupertypeResolverVisitor.g(function1, obj2);
                        }
                    });
                }
                firRegularClass.replaceSuperTypeRefs(listAsMutableList);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CharSequence addSupertypesToGeneratedNestedClasses$lambda$2$1(FirTypeRef firTypeRef) {
        firTypeRef.getClass();
        return UtilsKt.render(firTypeRef);
    }

    public static List b(FirTypeRef firTypeRef, boolean z, FirSupertypeResolverVisitor firSupertypeResolverVisitor, FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer, TypeResolutionConfiguration typeResolutionConfiguration) {
        firSpecificTypeResolverTransformer.getClass();
        typeResolutionConfiguration.getClass();
        FirResolvedTypeRef firResolvedTypeRefTransformTypeRef = firSpecificTypeResolverTransformer.mo600transformTypeRef(firTypeRef, typeResolutionConfiguration);
        if (z) {
            resolveTypeAliasSupertype$lambda$0$visitNestedTypeAliases(firSupertypeResolverVisitor, firResolvedTypeRefTransformTypeRef.getConeType());
        }
        return CollectionsKt.listOf(firResolvedTypeRefTransformTypeRef);
    }

    public static PersistentList c(FirSupertypeResolverVisitor firSupertypeResolverVisitor, FirClass firClass) {
        return firSupertypeResolverVisitor.calculateScopes(firClass, false, true);
    }

    private final PersistentList<FirScope> calculateScopes(FirClass outerClass, boolean withCompanionScopes, boolean forStaticNestedClass) {
        resolveAllSupertypesForOuterClass(outerClass);
        return FirSupertypesResolutionKt.pushAll(prepareScopes(outerClass, forStaticNestedClass), FirSupertypesResolutionKt.createOtherScopesForNestedClassesOrCompanion(outerClass, this.session, this.scopeSession, this.supertypeComputationSession, withCompanionScopes));
    }

    public static PersistentList e(FirFile firFile, FirSupertypeResolverVisitor firSupertypeResolverVisitor) {
        return ExtensionsKt.toPersistentList(CollectionsKt.asReversed(ImportingScopesKt.createImportingScopes$default(firFile, firSupertypeResolverVisitor.session, firSupertypeResolverVisitor.scopeSession, false, 8, null)));
    }

    public static PersistentList f(FirSupertypeResolverVisitor firSupertypeResolverVisitor, FirClass firClass) {
        return firSupertypeResolverVisitor.calculateScopes(firClass, true, false);
    }

    public static boolean g(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    @PrivateForInline
    public static /* synthetic */ void getClassDeclarationsStack$annotations() {
    }

    private final FirFile getFirClassifierContainerFileIfAny(FirClassLikeSymbol<?> symbol) {
        return FirProviderKt.getFirProvider(symbol.getModuleData().getSession()).getFirClassifierContainerFileIfAny(symbol);
    }

    @PrivateForInline
    public static /* synthetic */ void getUseSiteFile$annotations() {
    }

    public static PersistentList h(FirSupertypeResolverVisitor firSupertypeResolverVisitor, FirClass firClass) {
        return firSupertypeResolverVisitor.calculateScopes(firClass, true, true);
    }

    public static boolean i(FirResolvedTypeRef firResolvedTypeRef) {
        firResolvedTypeRef.getClass();
        return ConeBuiltinTypeUtilsKt.isAny(firResolvedTypeRef.getConeType());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static List j(List list, FirSupertypeResolverVisitor firSupertypeResolverVisitor, boolean z, FirClassLikeDeclaration firClassLikeDeclaration, FirSpecificTypeResolverTransformer firSpecificTypeResolverTransformer, TypeResolutionConfiguration typeResolutionConfiguration) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirResolvedTypeRef firResolvedTypeRefCreateErrorTypeRef;
        firSpecificTypeResolverTransformer.getClass();
        typeResolutionConfiguration.getClass();
        ArrayList arrayList = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            FirResolvedTypeRef firResolvedTypeRef = (FirTypeRef) ((FirTypeRef) it.next()).transform(firSpecificTypeResolverTransformer, typeResolutionConfiguration);
            boolean z2 = firResolvedTypeRef instanceof FirResolvedTypeRef;
            FirResolvedTypeRef firResolvedTypeRef2 = z2 ? firResolvedTypeRef : null;
            ConeKotlinType coneType = firResolvedTypeRef2 != null ? firResolvedTypeRef2.getConeType() : null;
            if (!(coneType instanceof ConeTypeParameterType)) {
                coneType = null;
            }
            ConeTypeParameterType coneTypeParameterType = (ConeTypeParameterType) coneType;
            FirResolvedTypeRef firResolvedTypeRef3 = z2 ? firResolvedTypeRef : null;
            ConeKotlinType coneType2 = firResolvedTypeRef3 != null ? firResolvedTypeRef3.getConeType() : null;
            if (!(coneType2 instanceof ConeClassLikeType)) {
                coneType2 = null;
            }
            ConeClassLikeType coneClassLikeType = (ConeClassLikeType) coneType2;
            FirTypeAliasSymbol typeAliasSymbol = coneClassLikeType != null ? ToSymbolUtilsKt.toTypeAliasSymbol(coneClassLikeType, firSupertypeResolverVisitor.session) : null;
            if (z && typeAliasSymbol != null) {
                firSupertypeResolverVisitor.m599visitTypeAlias((FirTypeAlias) typeAliasSymbol.getFir(), (Object) null);
            }
            if (coneTypeParameterType != null) {
                FirErrorTypeRefBuilder firErrorTypeRefBuilder = new FirErrorTypeRefBuilder();
                firErrorTypeRefBuilder.setSource(firResolvedTypeRef.getSource());
                firErrorTypeRefBuilder.setDiagnostic(new ConeTypeParameterSupertype(coneTypeParameterType.getLookupTag().getTypeParameterSymbol()));
                firResolvedTypeRefCreateErrorTypeRef = firErrorTypeRefBuilder.build();
            } else if (z2) {
                firResolvedTypeRefCreateErrorTypeRef = firResolvedTypeRef;
            } else {
                firResolvedTypeRefCreateErrorTypeRef = FirSupertypesResolutionKt.createErrorTypeRef(firResolvedTypeRef.getSource(), "Unresolved super-type: " + UtilsKt.render(firResolvedTypeRef), DiagnosticKind.UnresolvedSupertype);
            }
            arrayList.add(firResolvedTypeRefCreateErrorTypeRef);
        }
        firSupertypeResolverVisitor.addSupertypesFromExtensions(firClassLikeDeclaration, arrayList, firSpecificTypeResolverTransformer, typeResolutionConfiguration);
        if (z && typeResolutionConfiguration.getUseSiteFile() != null && (firClassLikeDeclaration instanceof FirRegularClass)) {
            firSupertypeResolverVisitor.addSupertypesToGeneratedNestedClasses((FirRegularClass) firClassLikeDeclaration, firSpecificTypeResolverTransformer, typeResolutionConfiguration);
        }
        return arrayList;
    }

    private final PersistentList<FirScope> prepareFileScopes(final FirFile file) {
        return this.supertypeComputationSession.getOrPutFileScope(file, new Function0() { // from class: te5
            public final Object invoke() {
                return FirSupertypeResolverVisitor.e(file, this);
            }
        });
    }

    private final PersistentList<FirScope> prepareReplScopes(FirReplSnippet replSnippet) {
        FirScope snippetScope;
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        FirReplSnippetSymbol symbol = replSnippet.getSymbol();
        FirFile firReplSnippetContainerFile = FirProviderKt.getFirProvider(symbol.getModuleData().getSession()).getFirReplSnippetContainerFile(symbol);
        if (firReplSnippetContainerFile != null) {
            listCreateListBuilder.addAll(prepareFileScopes(firReplSnippetContainerFile));
        }
        FirReplSnippetResolveExtension replSnippetResolveExtension = FirReplSnippetResolveExtensionKt.getReplSnippetResolveExtension(this.session);
        if (replSnippetResolveExtension != null && (snippetScope = replSnippetResolveExtension.getSnippetScope(replSnippet, this.session)) != null) {
            listCreateListBuilder.add(snippetScope);
        }
        return ExtensionsKt.toPersistentList(CollectionsKt.build(listCreateListBuilder));
    }

    private final PersistentList<FirScope> prepareScopeForCompanion(final FirClass klass) {
        return this.supertypeComputationSession.getOrPutScopeForCompanion(klass, new Function0() { // from class: ze5
            public final Object invoke() {
                return FirSupertypeResolverVisitor.c(this.b, klass);
            }
        });
    }

    private final PersistentList<FirScope> prepareScopeForNestedClasses(final FirClass klass, boolean forStaticNestedClass) {
        SupertypeComputationSession supertypeComputationSession = this.supertypeComputationSession;
        return forStaticNestedClass ? supertypeComputationSession.getOrPutScopeForStaticNestedClasses(klass, new Function0() { // from class: re5
            public final Object invoke() {
                return FirSupertypeResolverVisitor.h(this.b, klass);
            }
        }) : supertypeComputationSession.getOrPutScopeForNestedClasses(klass, new Function0() { // from class: se5
            public final Object invoke() {
                return FirSupertypeResolverVisitor.f(this.b, klass);
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final PersistentList<FirScope> prepareScopes(FirClassLikeDeclaration classLikeDeclaration, boolean forStaticNestedClass) {
        PersistentList<FirScope> persistentListPersistentListOf;
        FirRegularClass firRegularClass;
        ClassId classId = classLikeDeclaration.getSymbol().getClassId();
        FirSession session = classLikeDeclaration.getModuleData().getSession();
        if (classLikeDeclaration.getIsLocal()) {
            LocalClassesNavigationInfo localClassesNavigationInfo = this.localClassesNavigationInfo;
            if (localClassesNavigationInfo == null) {
                return ExtensionsKt.persistentListOf();
            }
            FirClassLikeDeclaration firClassLikeDeclaration = localClassesNavigationInfo.getParentForClass().get(classLikeDeclaration);
            if (firClassLikeDeclaration == null || !(firClassLikeDeclaration instanceof FirClass)) {
                persistentListPersistentListOf = this.scopeForLocalClass;
                if (persistentListPersistentListOf == null) {
                    return ExtensionsKt.persistentListOf();
                }
            } else {
                persistentListPersistentListOf = prepareScopeForNestedClasses((FirClass) firClassLikeDeclaration, forStaticNestedClass);
            }
        } else {
            FirRegularClass firRegularClass2 = classLikeDeclaration instanceof FirRegularClass ? (FirRegularClass) classLikeDeclaration : null;
            boolean z = true;
            if (firRegularClass2 != null && firRegularClass2.getStatus().isCompanion()) {
                FirClassLikeSymbol<?> containingClass = FirProviderKt.getFirProvider(session).getContainingClass(((FirRegularClass) classLikeDeclaration).getSymbol());
                FirClassLikeDeclaration firClassLikeDeclaration2 = containingClass != null ? (FirClassLikeDeclaration) containingClass.getFir() : null;
                firRegularClass = firClassLikeDeclaration2 instanceof FirRegularClass ? (FirRegularClass) firClassLikeDeclaration2 : null;
                if (firRegularClass == null) {
                    return ExtensionsKt.persistentListOf();
                }
                persistentListPersistentListOf = prepareScopeForCompanion(firRegularClass);
            } else if (classId.isNestedClass()) {
                FirClassLikeSymbol<?> containingClass2 = FirProviderKt.getFirProvider(session).getContainingClass(classLikeDeclaration.getSymbol());
                FirClassLikeDeclaration firClassLikeDeclaration3 = containingClass2 != null ? (FirClassLikeDeclaration) containingClass2.getFir() : null;
                firRegularClass = firClassLikeDeclaration3 instanceof FirRegularClass ? (FirRegularClass) firClassLikeDeclaration3 : null;
                boolean zIsInner = classLikeDeclaration.getStatus().isInner();
                if (firRegularClass == null) {
                    return ExtensionsKt.persistentListOf();
                }
                if (zIsInner && !forStaticNestedClass) {
                    z = false;
                }
                persistentListPersistentListOf = prepareScopeForNestedClasses(firRegularClass, z);
            } else {
                FirReplSnippet firReplSnippet = this.replSnippet;
                if (firReplSnippet != null) {
                    firReplSnippet.getClass();
                    persistentListPersistentListOf = prepareReplScopes(firReplSnippet);
                } else {
                    FirFile firClassifierContainerFileIfAny = getFirClassifierContainerFileIfAny(classLikeDeclaration.getSymbol());
                    if (firClassifierContainerFileIfAny == null || (persistentListPersistentListOf = prepareFileScopes(firClassifierContainerFileIfAny)) == null) {
                        persistentListPersistentListOf = ExtensionsKt.persistentListOf();
                    }
                }
            }
        }
        return forStaticNestedClass ? persistentListPersistentListOf : FirSupertypesResolutionKt.pushIfNotNull(persistentListPersistentListOf, FirSupertypesResolutionKt.typeParametersScope(classLikeDeclaration));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final void resolveAllSupertypes(FirClassLikeDeclaration classLikeDeclaration, List<? extends FirTypeRef> supertypeRefs, Set<FirClassLikeDeclaration> visited) {
        ConeClassLikeType coneClassLikeType;
        FirClassLikeSymbol<?> symbol;
        FirModuleData moduleData;
        FirSession session;
        FirClassLikeSymbol<?> symbol2;
        FirClassLikeDeclaration firClassLikeDeclaration;
        if (visited.add(classLikeDeclaration)) {
            List<FirResolvedTypeRef> listResolveSpecificClassLikeSupertypes = resolveSpecificClassLikeSupertypes(classLikeDeclaration, supertypeRefs, true);
            ArrayList<ConeKotlinType> arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listResolveSpecificClassLikeSupertypes, 10));
            Iterator<T> it = listResolveSpecificClassLikeSupertypes.iterator();
            while (it.hasNext()) {
                arrayList.add(((FirResolvedTypeRef) it.next()).getConeType());
            }
            for (ConeKotlinType coneKotlinType : arrayList) {
                if ((coneKotlinType instanceof ConeClassLikeType) && (symbol = ToSymbolUtilsKt.toSymbol((coneClassLikeType = (ConeClassLikeType) coneKotlinType), this.session)) != null && (moduleData = symbol.getModuleData()) != null && (session = moduleData.getSession()) != null && (symbol2 = ToSymbolUtilsKt.toSymbol(coneClassLikeType.getLookupTag(), session)) != null && (firClassLikeDeclaration = (FirClassLikeDeclaration) symbol2.getFir()) != null) {
                    resolveAllSupertypes(firClassLikeDeclaration, this.supertypeComputationSession.supertypeRefs(firClassLikeDeclaration, this.session), visited);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void resolveAllSupertypes$default(FirSupertypeResolverVisitor firSupertypeResolverVisitor, FirClassLikeDeclaration firClassLikeDeclaration, List list, Set set, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: resolveAllSupertypes");
            return;
        }
        if ((i & 4) != 0) {
            set = new LinkedHashSet();
        }
        firSupertypeResolverVisitor.resolveAllSupertypes(firClassLikeDeclaration, list, set);
    }

    private final List<FirResolvedTypeRef> resolveSpecificClassLikeSupertypes(FirClassLikeDeclaration classLikeDeclaration, Function2<? super FirSpecificTypeResolverTransformer, ? super TypeResolutionConfiguration, ? extends List<? extends FirResolvedTypeRef>> resolveSuperTypeRefs) {
        SupertypeComputationStatus supertypesComputationStatus = this.supertypeComputationSession.getSupertypesComputationStatus(classLikeDeclaration);
        if (supertypesComputationStatus instanceof SupertypeComputationStatus.Computed) {
            return ((SupertypeComputationStatus.Computed) supertypesComputationStatus).getSupertypeRefs();
        }
        if (supertypesComputationStatus instanceof SupertypeComputationStatus.Computing) {
            return CollectionsKt.listOf(FirSupertypesResolutionKt.createErrorTypeRef(classLikeDeclaration.getSource(), "Loop in supertype definition for " + classLikeDeclaration.getSymbol().getClassId(), classLikeDeclaration instanceof FirTypeAlias ? DiagnosticKind.RecursiveTypealiasExpansion : DiagnosticKind.LoopInSupertype));
        }
        if (!Intrinsics.areEqual(supertypesComputationStatus, SupertypeComputationStatus.NotComputed.INSTANCE)) {
            bu8.a();
            return null;
        }
        this.supertypeComputationSession.startComputingSupertypes(classLikeDeclaration);
        List<? extends FirResolvedTypeRef> list = (List) resolveSuperTypeRefs.invoke(new FirSpecificTypeResolverTransformer(this.session, false, false, this.supertypeComputationSession.getSupertypesSupplier(), false, 6, null), new TypeResolutionConfiguration((Iterable) prepareScopes(classLikeDeclaration, false), (List) this.classDeclarationsStack, classLikeDeclaration.getIsLocal() ? this.useSiteFile : FirProviderKt.getFirProvider(this.session).getFirClassifierContainerFileIfAny(classLikeDeclaration.getSymbol()), (FirDeclaration) null, 8, (DefaultConstructorMarker) null));
        this.supertypeComputationSession.storeSupertypes(classLikeDeclaration, list);
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void resolveTypeAliasSupertype$lambda$0$visitNestedTypeAliases(FirSupertypeResolverVisitor firSupertypeResolverVisitor, ConeTypeProjection coneTypeProjection) {
        ConeKotlinType type = ConeTypeProjectionKt.getType(coneTypeProjection);
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = type != null ? ConeTypeUtilsKt.lowerBoundIfFlexible(type) : null;
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType == null) {
            return;
        }
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeType.getLookupTag(), firSupertypeResolverVisitor.session);
        if (symbol instanceof FirTypeAliasSymbol) {
            firSupertypeResolverVisitor.m599visitTypeAlias((FirTypeAlias) ((FirTypeAliasSymbol) symbol).getFir(), (Object) null);
            return;
        }
        if (symbol != null) {
            for (ConeTypeProjection coneTypeProjection2 : coneClassLikeType.getTypeArguments()) {
                resolveTypeAliasSupertype$lambda$0$visitNestedTypeAliases(firSupertypeResolverVisitor, coneTypeProjection2);
            }
        }
    }

    private final void visitDeclarationContent(FirDeclaration declaration, Object data) {
        declaration.acceptChildren(this, data);
    }

    public final ArrayDeque<FirClass> getClassDeclarationsStack() {
        return this.classDeclarationsStack;
    }

    public final LocalClassesNavigationInfo getLocalClassesNavigationInfo() {
        return this.localClassesNavigationInfo;
    }

    public final FirFile getUseSiteFile() {
        return this.useSiteFile;
    }

    public void resolveAllSupertypesForOuterClass(FirClass outerClass) {
        outerClass.getClass();
        resolveAllSupertypes$default(this, outerClass, outerClass.getSuperTypeRefs(), null, 4, null);
    }

    public final List<FirResolvedTypeRef> resolveTypeAliasSupertype(FirTypeAlias typeAlias, final FirTypeRef expandedTypeRef, final boolean resolveRecursively) {
        typeAlias.getClass();
        expandedTypeRef.getClass();
        return expandedTypeRef instanceof FirResolvedTypeRef ? CollectionsKt.listOf(expandedTypeRef) : resolveSpecificClassLikeSupertypes(typeAlias, new Function2() { // from class: ue5
            public final Object invoke(Object obj, Object obj2) {
                return FirSupertypeResolverVisitor.b(expandedTypeRef, resolveRecursively, this, (FirSpecificTypeResolverTransformer) obj, (TypeResolutionConfiguration) obj2);
            }
        });
    }

    public final void setUseSiteFile(FirFile firFile) {
        this.useSiteFile = firFile;
    }

    /* JADX INFO: renamed from: visitAnonymousObject, reason: collision with other method in class */
    public void m594visitAnonymousObject(FirAnonymousObject anonymousObject, Object data) {
        anonymousObject.getClass();
        ArrayDeque<FirClass> classDeclarationsStack = getClassDeclarationsStack();
        classDeclarationsStack.addLast(anonymousObject);
        try {
            resolveSpecificClassLikeSupertypes(anonymousObject, anonymousObject.getSuperTypeRefs(), true);
            visitDeclarationContent(anonymousObject, null);
            Unit unit = Unit.INSTANCE;
        } finally {
            classDeclarationsStack.removeLast();
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitElement(FirElement firElement, Object obj) {
        m595visitElement(firElement, obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: visitFile, reason: collision with other method in class */
    public void m596visitFile(FirFile file, Object data) {
        file.getClass();
        FirFile useSiteFile = getUseSiteFile();
        try {
            setUseSiteFile(file);
            visitDeclarationContent(file, null);
            Unit unit = Unit.INSTANCE;
        } finally {
            setUseSiteFile(useSiteFile);
        }
    }

    /* JADX INFO: renamed from: visitRegularClass, reason: collision with other method in class */
    public void m597visitRegularClass(FirRegularClass regularClass, Object data) {
        regularClass.getClass();
        ArrayDeque<FirClass> classDeclarationsStack = getClassDeclarationsStack();
        classDeclarationsStack.addLast(regularClass);
        try {
            resolveSpecificClassLikeSupertypes(regularClass, regularClass.getSuperTypeRefs(), true);
            visitDeclarationContent(regularClass, null);
            Unit unit = Unit.INSTANCE;
        } finally {
            classDeclarationsStack.removeLast();
        }
    }

    /* JADX INFO: renamed from: visitReplSnippet, reason: collision with other method in class */
    public void m598visitReplSnippet(FirReplSnippet replSnippet, Object data) {
        replSnippet.getClass();
        FirReplSnippet firReplSnippet = this.replSnippet;
        this.replSnippet = replSnippet;
        try {
            visitDeclarationContent(replSnippet, data);
        } finally {
            this.replSnippet = firReplSnippet;
        }
    }

    /* JADX INFO: renamed from: visitTypeAlias, reason: collision with other method in class */
    public void m599visitTypeAlias(FirTypeAlias typeAlias, Object data) {
        typeAlias.getClass();
        resolveTypeAliasSupertype(typeAlias, typeAlias.getExpandedTypeRef(), true);
    }

    public final <T> T withClass(FirClass firClass, Function0<? extends T> body) {
        firClass.getClass();
        body.getClass();
        ArrayDeque<FirClass> classDeclarationsStack = getClassDeclarationsStack();
        classDeclarationsStack.addLast(firClass);
        try {
            return (T) body.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            classDeclarationsStack.removeLast();
            InlineMarker.finallyEnd(1);
        }
    }

    public final <R> R withFile(FirFile file, Function0<? extends R> block) {
        file.getClass();
        block.getClass();
        FirFile useSiteFile = getUseSiteFile();
        try {
            setUseSiteFile(file);
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setUseSiteFile(useSiteFile);
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: renamed from: visitElement, reason: collision with other method in class */
    public void m595visitElement(FirElement element, Object data) {
        element.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitTypeAlias(FirTypeAlias firTypeAlias, Object obj) {
        m599visitTypeAlias(firTypeAlias, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitReplSnippet(FirReplSnippet firReplSnippet, Object obj) {
        m598visitReplSnippet(firReplSnippet, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitFile(FirFile firFile, Object obj) {
        m596visitFile(firFile, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnonymousObject(FirAnonymousObject firAnonymousObject, Object obj) {
        m594visitAnonymousObject(firAnonymousObject, obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitRegularClass(FirRegularClass firRegularClass, Object obj) {
        m597visitRegularClass(firRegularClass, obj);
        return Unit.INSTANCE;
    }

    public /* synthetic */ FirSupertypeResolverVisitor(FirSession firSession, SupertypeComputationSession supertypeComputationSession, ScopeSession scopeSession, PersistentList persistentList, LocalClassesNavigationInfo localClassesNavigationInfo, FirFile firFile, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, supertypeComputationSession, scopeSession, (i & 8) != 0 ? null : persistentList, (i & 16) != 0 ? null : localClassesNavigationInfo, (i & 32) != 0 ? null : firFile, (i & 64) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final List<FirResolvedTypeRef> resolveSpecificClassLikeSupertypes(final FirClassLikeDeclaration classLikeDeclaration, final List<? extends FirTypeRef> supertypeRefs, final boolean resolveRecursively) {
        classLikeDeclaration.getClass();
        supertypeRefs.getClass();
        return resolveSpecificClassLikeSupertypes(classLikeDeclaration, new Function2() { // from class: ve5
            public final Object invoke(Object obj, Object obj2) {
                return FirSupertypeResolverVisitor.j(supertypeRefs, this, resolveRecursively, classLikeDeclaration, (FirSpecificTypeResolverTransformer) obj, (TypeResolutionConfiguration) obj2);
            }
        });
    }
}
