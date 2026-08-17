package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueClassRepresentationKt;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.ValueClassesUtilsKt;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.util.PrivateForInline;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000è\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\f\u0010\u001d\u001a\u00020\u001e*\u00020\u001fH$J\f\u0010 \u001a\u00020\u001e*\u00020\u001fH$J\u001a\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\"\u0010(\u001a\u00020\u001f2\u0006\u0010)\u001a\u00020\u000f2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001f0+H\u0086\bø\u0001\u0000J\u001a\u0010,\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u00101\u001a\u0002022\u0006\u00103\u001a\u0002042\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u00105\u001a\u0002022\u0006\u00106\u001a\u0002072\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u00108\u001a\u0002022\u0006\u00109\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010\u0002H&J\u001a\u0010:\u001a\u0002022\u0006\u0010;\u001a\u00020<2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010=\u001a\u00020\u001f2\u0006\u0010-\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010>\u001a\u0002022\u0006\u0010)\u001a\u00020\u000f2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u000e\u0010?\u001a\u00020@2\u0006\u00109\u001a\u00020\u000fJ\u000e\u0010A\u001a\u00020@2\u0006\u00109\u001a\u00020\u000fJ\u001a\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020C2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J(\u0010E\u001a\u00020@2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020I2\u000e\b\u0002\u0010J\u001a\b\u0012\u0004\u0012\u00020\u00020KH\u0002J\u001a\u0010L\u001a\u0002022\u0006\u0010M\u001a\u00020N2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010O\u001a\u0002022\u0006\u0010P\u001a\u00020Q2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010R\u001a\u0002022\u0006\u0010S\u001a\u00020T2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J(\u0010R\u001a\u00020@2\u0006\u0010S\u001a\u00020T2\f\u0010U\u001a\b\u0012\u0004\u0012\u00020T0K2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0002J\u001a\u0010V\u001a\u0002022\u0006\u0010W\u001a\u00020I2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001c\u0010V\u001a\u00020@2\u0006\u0010W\u001a\u00020I2\f\u0010X\u001a\b\u0012\u0004\u0012\u00020I0KJ\u001a\u0010Y\u001a\u0002022\u0006\u0010Z\u001a\u00020[2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010E\u001a\u0002022\u0006\u0010F\u001a\u00020G2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010\\\u001a\u0002022\u0006\u0010]\u001a\u00020^2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010_\u001a\u0002022\u0006\u0010`\u001a\u00020a2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010b\u001a\u00020c2\u0006\u0010d\u001a\u00020e2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016J\u001a\u0010f\u001a\u0002022\u0006\u0010g\u001a\u00020h2\b\u0010$\u001a\u0004\u0018\u00010\u0002H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR&\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006X\u0087\u0004r\u0002\b\u0014¢\u0006\u000e\n\u0000\u0012\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0015\u001a\u00020\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u000f8F¢\u0006\f\u0012\u0004\b\u001a\u0010\u0011\u001a\u0004\b\u001b\u0010\u001c\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006i"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/AbstractFirStatusResolveTransformer;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirAbstractTreeTransformer;", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "statusComputationSession", "Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession;)V", "getStatusComputationSession", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/StatusComputationSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "classes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getClasses$annotations", "()V", "getClasses", "()Ljava/util/List;", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "statusResolver", "Lorg/jetbrains/kotlin/fir/resolve/transformers/FirStatusResolver;", "getStatusResolver", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/FirStatusResolver;", "containingClass", "getContainingClass$annotations", "getContainingClass", "()Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "needResolveMembers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "needResolveNestedClassifiers", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "data", "transformDeclarationStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "declarationStatus", "storeClass", "klass", "computeResult", "Lkotlin/Function0;", "transformDeclaration", "declaration", "transformDanglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "danglingModifierList", "transformTypeAlias", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "transformRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "transformClassContent", "firClass", "transformAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "transformDeclarationContent", "transformClass", "transformValueClassRepresentation", Argument.Delimiters.none, "transformClassStatus", "transformReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "replSnippet", "transformPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "containingProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "overriddenStatuses", Argument.Delimiters.none, "transformConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "transformErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "transformNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "overriddenFunctions", "transformProperty", "property", "overriddenProperties", "transformField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "transformEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "transformValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "transformTypeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "transformBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractFirStatusResolveTransformer extends FirAbstractTreeTransformer<FirResolvedDeclarationStatus> {
    private final List<FirClass> classes;
    private final StatusComputationSession statusComputationSession;
    private final FirStatusResolver statusResolver;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractFirStatusResolveTransformer(StatusComputationSession statusComputationSession) {
        super(FirResolvePhase.STATUS);
        statusComputationSession.getClass();
        this.statusComputationSession = statusComputationSession;
        this.classes = new ArrayList();
        this.statusResolver = new FirStatusResolver(getSession(), statusComputationSession.getUseSiteScopeSession());
    }

    @PrivateForInline
    public static /* synthetic */ void getClasses$annotations() {
    }

    public static /* synthetic */ void getContainingClass$annotations() {
    }

    public static /* synthetic */ void transformNamedFunction$default(AbstractFirStatusResolveTransformer abstractFirStatusResolveTransformer, FirNamedFunction firNamedFunction, List list, FirResolvedDeclarationStatus firResolvedDeclarationStatus, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: transformNamedFunction");
            return;
        }
        if ((i & 4) != 0) {
            firResolvedDeclarationStatus = null;
        }
        abstractFirStatusResolveTransformer.transformNamedFunction(firNamedFunction, list, firResolvedDeclarationStatus);
    }

    private final void transformPropertyAccessor(FirPropertyAccessor propertyAccessor, FirProperty containingProperty, List<? extends FirResolvedDeclarationStatus> overriddenStatuses) {
        FirPropertyAccessor firPropertyAccessor;
        FirSession session = getSession();
        try {
            firPropertyAccessor = propertyAccessor;
            try {
                firPropertyAccessor.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, this.statusResolver.resolveStatus(firPropertyAccessor, getContainingClass(), containingProperty, false, overriddenStatuses));
                firPropertyAccessor.transformValueParameters((FirTransformer<? super Object>) this, (Object) null);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                th = th;
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firPropertyAccessor, th);
                wq6.a();
            }
        } catch (Throwable th2) {
            th = th2;
            firPropertyAccessor = propertyAccessor;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ void transformPropertyAccessor$default(AbstractFirStatusResolveTransformer abstractFirStatusResolveTransformer, FirPropertyAccessor firPropertyAccessor, FirProperty firProperty, List list, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: transformPropertyAccessor");
            return;
        }
        if ((i & 4) != 0) {
            list = CollectionsKt.emptyList();
        }
        abstractFirStatusResolveTransformer.transformPropertyAccessor(firPropertyAccessor, firProperty, list);
    }

    public final List<FirClass> getClasses() {
        return this.classes;
    }

    public final FirClass getContainingClass() {
        return (FirClass) CollectionsKt.lastOrNull(this.classes);
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.statusComputationSession.getUseSiteSession();
    }

    public final StatusComputationSession getStatusComputationSession() {
        return this.statusComputationSession;
    }

    public final FirStatusResolver getStatusResolver() {
        return this.statusResolver;
    }

    public abstract boolean needResolveMembers(FirDeclaration firDeclaration);

    public abstract boolean needResolveNestedClassifiers(FirDeclaration firDeclaration);

    public final FirDeclaration storeClass(FirClass klass, Function0<? extends FirDeclaration> computeResult) {
        klass.getClass();
        computeResult.getClass();
        getClasses().add(klass);
        FirDeclaration firDeclaration = (FirDeclaration) computeResult.invoke();
        getClasses().remove(CollectionsKt.getLastIndex(getClasses()));
        return firDeclaration;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousObject(FirAnonymousObject anonymousObject, FirResolvedDeclarationStatus data) {
        anonymousObject.getClass();
        FirSession session = getSession();
        try {
            return transformClassContent(anonymousObject, data);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousObject, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformClass(FirClass klass, FirResolvedDeclarationStatus data) {
        klass.getClass();
        FirSession session = getSession();
        try {
            getClasses().add(klass);
            Iterator<T> it = klass.getTypeParameters().iterator();
            while (it.hasNext()) {
                FirTransformerUtilKt.transformSingle((FirTypeParameterRef) it.next(), this, data);
            }
            FirAnnotationContainer firAnnotationContainerTransformDeclarationContent = transformDeclarationContent(klass, data);
            getClasses().remove(CollectionsKt.getLastIndex(getClasses()));
            firAnnotationContainerTransformDeclarationContent.getClass();
            return (FirStatement) firAnnotationContainerTransformDeclarationContent;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(klass, th);
            wq6.a();
            return null;
        }
    }

    public abstract FirStatement transformClassContent(FirClass firClass, FirResolvedDeclarationStatus data);

    public final void transformClassStatus(FirClass firClass) {
        firClass.getClass();
        firClass.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, this.statusResolver.resolveStatus(firClass, getContainingClass(), false));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformConstructor(FirConstructor constructor, FirResolvedDeclarationStatus data) {
        constructor.getClass();
        FirSession session = getSession();
        try {
            constructor.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, this.statusResolver.resolveStatus(constructor, getContainingClass(), false));
            FirAnnotationContainer firAnnotationContainerTransformDeclaration = transformDeclaration((FirDeclaration) constructor, data);
            firAnnotationContainerTransformDeclaration.getClass();
            return (FirStatement) firAnnotationContainerTransformDeclaration;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(constructor, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDeclaration transformDeclaration(FirDeclaration declaration, FirResolvedDeclarationStatus data) {
        declaration.getClass();
        FirSession session = getSession();
        try {
            if (!(declaration instanceof FirCallableDeclaration)) {
                return (FirDeclaration) transformElement(declaration, data);
            }
            if (declaration instanceof FirFunction) {
                Iterator<FirValueParameter> it = ((FirFunction) declaration).getValueParameters().iterator();
                while (it.hasNext()) {
                    transformValueParameter(it.next(), data);
                }
            }
            return declaration;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(declaration, th);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public FirDeclaration transformDeclarationContent(FirDeclaration declaration, FirResolvedDeclarationStatus data) throws KotlinIllegalArgumentExceptionWithAttachments {
        List<FirDeclaration> declarations;
        declaration.getClass();
        if (declaration instanceof FirRegularClass) {
            declarations = ((FirRegularClass) declaration).getDeclarations();
        } else if (declaration instanceof FirAnonymousObject) {
            declarations = ((FirAnonymousObject) declaration).getDeclarations();
        } else {
            if (!(declaration instanceof FirFile)) {
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Unsupported declaration: " + declaration.getClass(), (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "declaration", declaration);
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
            declarations = ((FirFile) declaration).getDeclarations();
        }
        if (needResolveMembers(declaration)) {
            for (FirDeclaration firDeclaration : declarations) {
                if (!(firDeclaration instanceof FirClassLikeDeclaration)) {
                    FirTransformerUtilKt.transformSingle(firDeclaration, this, data);
                }
            }
        }
        if (needResolveNestedClassifiers(declaration)) {
            for (FirDeclaration firDeclaration2 : declarations) {
                if (firDeclaration2 instanceof FirClassLikeDeclaration) {
                    FirTransformerUtilKt.transformSingle(firDeclaration2, this, data);
                }
            }
        }
        return declaration;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDeclarationStatus transformDeclarationStatus(FirDeclarationStatus declarationStatus, FirResolvedDeclarationStatus data) {
        declarationStatus.getClass();
        return data != null ? data : declarationStatus;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformEnumEntry(FirEnumEntry enumEntry, FirResolvedDeclarationStatus data) {
        enumEntry.getClass();
        FirSession session = getSession();
        try {
            enumEntry.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, this.statusResolver.resolveStatus(enumEntry, getContainingClass(), false));
            FirDeclaration firDeclarationTransformDeclaration = transformDeclaration((FirDeclaration) enumEntry, data);
            firDeclarationTransformDeclaration.getClass();
            return (FirEnumEntry) firDeclarationTransformDeclaration;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(enumEntry, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, FirResolvedDeclarationStatus data) {
        errorPrimaryConstructor.getClass();
        return transformConstructor((FirConstructor) errorPrimaryConstructor, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformField(FirField field, FirResolvedDeclarationStatus data) {
        field.getClass();
        FirSession session = getSession();
        try {
            field.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, this.statusResolver.resolveStatus(field, getContainingClass(), false));
            FirDeclaration firDeclarationTransformDeclaration = transformDeclaration((FirDeclaration) field, data);
            firDeclarationTransformDeclaration.getClass();
            return (FirField) firDeclarationTransformDeclaration;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(field, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractPhaseTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, FirResolvedDeclarationStatus data) {
        file.getClass();
        try {
            transformDeclarationContent(file, data);
            return file;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(file.getModuleData().getSession()).handleExceptionOnFileAnalysis(file, th);
            wq6.a();
            return null;
        }
    }

    public final void transformNamedFunction(FirNamedFunction namedFunction, List<? extends FirNamedFunction> overriddenFunctions, FirResolvedDeclarationStatus data) {
        namedFunction.getClass();
        overriddenFunctions.getClass();
        FirStatusResolver firStatusResolver = this.statusResolver;
        FirClass containingClass = getContainingClass();
        List<? extends FirNamedFunction> list = overriddenFunctions;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            FirDeclarationStatus status = ((FirNamedFunction) it.next()).getStatus();
            status.getClass();
            arrayList.add((FirResolvedDeclarationStatus) status);
        }
        namedFunction.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, firStatusResolver.resolveStatus(namedFunction, containingClass, false, (List<? extends FirResolvedDeclarationStatus>) arrayList));
        FirAnnotationContainer firAnnotationContainerTransformDeclaration = transformDeclaration((FirDeclaration) namedFunction, data);
        firAnnotationContainerTransformDeclaration.getClass();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void transformProperty(FirProperty property, List<? extends FirProperty> overriddenProperties) {
        FirResolvedDeclarationStatus firResolvedDeclarationStatus;
        property.getClass();
        overriddenProperties.getClass();
        List<? extends FirProperty> list = overriddenProperties;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            FirDeclarationStatus status = ((FirProperty) it.next()).getStatus();
            status.getClass();
            arrayList.add((FirResolvedDeclarationStatus) status);
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            FirPropertyAccessor setter = ((FirProperty) it2.next()).getSetter();
            if (setter == null) {
                firResolvedDeclarationStatus = null;
            } else {
                FirDeclarationStatus status2 = setter.getStatus();
                status2.getClass();
                firResolvedDeclarationStatus = (FirResolvedDeclarationStatus) status2;
            }
            if (firResolvedDeclarationStatus != null) {
                arrayList2.add(firResolvedDeclarationStatus);
            }
        }
        property.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, this.statusResolver.resolveStatus(property, getContainingClass(), false, (List<? extends FirResolvedDeclarationStatus>) arrayList));
        FirPropertyAccessor getter = property.getGetter();
        if (getter != null) {
            transformPropertyAccessor$default(this, getter, property, null, 4, null);
        }
        FirPropertyAccessor setter2 = property.getSetter();
        if (setter2 != null) {
            transformPropertyAccessor(setter2, property, arrayList2);
        }
        FirBackingField backingField = property.getBackingField();
        if (backingField != null) {
            backingField.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, this.statusResolver.resolveStatus((FirDeclaration) backingField, getContainingClass(), property, false));
        }
        FirNamedFunctionSymbol componentFunctionSymbol = DeclarationAttributesKt.getComponentFunctionSymbol(property);
        if (componentFunctionSymbol == null || !Intrinsics.areEqual(((FirNamedFunction) componentFunctionSymbol.getFir()).getStatus().getVisibility(), Visibilities.Unknown.INSTANCE)) {
            return;
        }
        FirNamedFunction firNamedFunction = (FirNamedFunction) componentFunctionSymbol.getFir();
        FirDeclarationStatus status3 = ((FirNamedFunction) componentFunctionSymbol.getFir()).getStatus();
        firNamedFunction.replaceStatus(UtilsKt.copy(status3, (8388575 & 1) != 0 ? status3.getVisibility() : property.getStatus().getVisibility(), (8388575 & 2) != 0 ? status3.getModality() : null, (8388575 & 4) != 0 ? status3.isExpect() : false, (8388575 & 8) != 0 ? status3.isActual() : false, (8388575 & 16) != 0 ? status3.isOverride() : false, (8388575 & 32) != 0 ? status3.isOperator() : false, (8388575 & 64) != 0 ? status3.isInfix() : false, (8388575 & 128) != 0 ? status3.isInline() : false, (8388575 & 256) != 0 ? status3.isValue() : false, (8388575 & 512) != 0 ? status3.isTailRec() : false, (8388575 & 1024) != 0 ? status3.isExternal() : false, (8388575 & 2048) != 0 ? status3.isConst() : false, (8388575 & 4096) != 0 ? status3.isLateInit() : false, (8388575 & 8192) != 0 ? status3.isInner() : false, (8388575 & 16384) != 0 ? status3.isCompanion() : false, (8388575 & 32768) != 0 ? status3.isData() : false, (8388575 & 65536) != 0 ? status3.isSuspend() : false, (8388575 & 131072) != 0 ? status3.isStatic() : false, (8388575 & 262144) != 0 ? status3.isFromSealedClass() : false, (8388575 & 524288) != 0 ? status3.isFromEnumClass() : false, (8388575 & 1048576) != 0 ? status3.isFun() : false, (8388575 & 2097152) != 0 ? status3.getHasStableParameterNames() : false, (8388575 & 4194304) != 0 ? status3.getReturnValueStatus() : null));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformRegularClass(FirRegularClass regularClass, FirResolvedDeclarationStatus data) {
        regularClass.getClass();
        FirSession session = getSession();
        try {
            return transformClassContent(regularClass, data);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(regularClass, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReplSnippet transformReplSnippet(FirReplSnippet replSnippet, FirResolvedDeclarationStatus data) {
        replSnippet.getClass();
        FirSession session = getSession();
        try {
            transformRegularClass(replSnippet.getSnippetClass(), data);
            return replSnippet;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(replSnippet, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformTypeAlias(FirTypeAlias typeAlias, FirResolvedDeclarationStatus data) {
        typeAlias.getClass();
        FirSession session = getSession();
        try {
            Iterator<T> it = typeAlias.getTypeParameters().iterator();
            while (it.hasNext()) {
                FirTransformerUtilKt.transformSingle((FirTypeParameterRef) it.next(), this, data);
            }
            typeAlias.transformStatus((FirTransformer<? super FirResolvedDeclarationStatus>) this, this.statusResolver.resolveStatus(typeAlias, getContainingClass(), false));
            FirDeclaration firDeclarationTransformDeclaration = transformDeclaration((FirDeclaration) typeAlias, data);
            firDeclarationTransformDeclaration.getClass();
            return (FirTypeAlias) firDeclarationTransformDeclaration;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(typeAlias, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeParameterRef transformTypeParameter(FirTypeParameter typeParameter, FirResolvedDeclarationStatus data) {
        typeParameter.getClass();
        FirDeclaration firDeclarationTransformDeclaration = transformDeclaration((FirDeclaration) typeParameter, data);
        firDeclarationTransformDeclaration.getClass();
        return (FirTypeParameter) firDeclarationTransformDeclaration;
    }

    public final void transformValueClassRepresentation(FirClass firClass) {
        firClass.getClass();
        if (firClass instanceof FirRegularClass) {
            if (firClass.getStatus().isInline() || firClass.getStatus().isValue()) {
                FirRegularClass firRegularClass = (FirRegularClass) firClass;
                FirValueClassRepresentationKt.setValueClassRepresentation(firRegularClass, ValueClassesUtilsKt.computeValueClassRepresentation(firRegularClass, getSession()));
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformValueParameter(FirValueParameter valueParameter, FirResolvedDeclarationStatus data) {
        valueParameter.getClass();
        FirAnnotationContainer firAnnotationContainerTransformDeclaration = transformDeclaration((FirDeclaration) valueParameter, data);
        firAnnotationContainerTransformDeclaration.getClass();
        return (FirStatement) firAnnotationContainerTransformDeclaration;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBlock(FirBlock block, FirResolvedDeclarationStatus data) {
        block.getClass();
        return block;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDanglingModifierList transformDanglingModifierList(FirDanglingModifierList danglingModifierList, FirResolvedDeclarationStatus data) {
        danglingModifierList.getClass();
        return danglingModifierList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformPropertyAccessor(FirPropertyAccessor propertyAccessor, FirResolvedDeclarationStatus data) {
        propertyAccessor.getClass();
        transformProperty((FirProperty) propertyAccessor.getPropertySymbol().getFir(), data);
        return propertyAccessor;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformNamedFunction(FirNamedFunction namedFunction, FirResolvedDeclarationStatus data) {
        namedFunction.getClass();
        FirSession session = getSession();
        try {
            transformNamedFunction(namedFunction, this.statusResolver.getOverriddenFunctions(namedFunction, getContainingClass()), data);
            return namedFunction;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(namedFunction, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformProperty(FirProperty property, FirResolvedDeclarationStatus data) {
        property.getClass();
        FirSession session = getSession();
        try {
            transformProperty(property, (List<? extends FirProperty>) this.statusResolver.getOverriddenProperties(property, getContainingClass()));
            return property;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(property, th);
            wq6.a();
            return null;
        }
    }
}
