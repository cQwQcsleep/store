package org.jetbrains.kotlin.fir.java.declarations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CallableReference;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.PropertyReference0Impl;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.BuiltinTypes;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationAttributes;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirResolveStateKt;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.java.FirJavaTypeConversionMode;
import org.jetbrains.kotlin.fir.java.JavaTypeConversionKt;
import org.jetbrains.kotlin.fir.java.JavaTypeParameterStack;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaAnnotationList;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.builder.FirResolvedTypeRefBuilder;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.load.java.structure.JavaClassifierType;
import org.jetbrains.kotlin.load.java.structure.JavaTypeParameter;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0096\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001TBU\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0004\b\u0014\u0010\u0015J'\u00107\u001a\n\u0012\u0004\u0012\u000209\u0018\u0001082\u0006\u0010:\u001a\u00020;2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0002\b<J\u001b\u0010=\u001a\u0002022\f\u00104\u001a\b\u0012\u0004\u0012\u0002090)H\u0000¢\u0006\u0002\b>J'\u0010?\u001a\n\u0012\u0004\u0012\u000209\u0018\u0001082\u0006\u0010:\u001a\u00020;2\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0000¢\u0006\u0002\b@J\u001b\u0010A\u001a\u0002022\f\u00104\u001a\b\u0012\u0004\u0012\u0002090)H\u0000¢\u0006\u0002\bBJ5\u0010C\u001a\u00020D\"\u0004\b\u0000\u0010E\"\u0004\b\u0001\u0010F2\u0012\u0010G\u001a\u000e\u0012\u0004\u0012\u0002HE\u0012\u0004\u0012\u0002HF0H2\u0006\u0010I\u001a\u0002HFH\u0016¢\u0006\u0002\u0010JJ)\u0010K\u001a\u00020\u0000\"\u0004\b\u0000\u0010F2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002HF0M2\u0006\u0010I\u001a\u0002HFH\u0016¢\u0006\u0002\u0010NJ)\u0010O\u001a\u00020\u0000\"\u0004\b\u0000\u0010F2\f\u0010L\u001a\b\u0012\u0004\u0012\u0002HF0M2\u0006\u0010I\u001a\u0002HFH\u0016¢\u0006\u0002\u0010NJ\u0016\u0010P\u001a\u00020D2\f\u0010Q\u001a\b\u0012\u0004\u0012\u0002050)H\u0016J\u0016\u0010R\u001a\u00020D2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020*0)H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0014\u0010\n\u001a\u00020\u000bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0014\u0010\f\u001a\u00020\rX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R\u0014\u0010\u000e\u001a\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0018\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010(\u001a\b\u0012\u0004\u0012\u00020*0)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u00020.8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0014\u00101\u001a\u0002028VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b1\u00103R\u001a\u00104\u001a\b\u0012\u0004\u0012\u0002050)8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u0010,¨\u0006U"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter;", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "javaTypeParameter", "Lorg/jetbrains/kotlin/load/java/structure/JavaTypeParameter;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "origin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "attributes", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "containingDeclarationSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "annotationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "<init>", "(Lorg/jetbrains/kotlin/load/java/structure/JavaTypeParameter;Lorg/jetbrains/kotlin/KtSourceElement;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;Lorg/jetbrains/kotlin/name/Name;Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;)V", "getJavaTypeParameter", "()Lorg/jetbrains/kotlin/load/java/structure/JavaTypeParameter;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "getModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "getAttributes", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationAttributes;", "getName", "()Lorg/jetbrains/kotlin/name/Name;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "getContainingDeclarationSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "boundsEnhancementState", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState;", "annotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getAnnotations", "()Ljava/util/List;", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "getVariance", "()Lorg/jetbrains/kotlin/types/Variance;", "isReified", Argument.Delimiters.none, "()Z", "bounds", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getBounds", "performFirstRoundOfBoundsResolution", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "javaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "performFirstRoundOfBoundsResolution$org_jetbrains_kotlin_fir_jvm", "storeBoundsAfterFirstRound", "storeBoundsAfterFirstRound$org_jetbrains_kotlin_fir_jvm", "performSecondRoundOfBoundsResolution", "performSecondRoundOfBoundsResolution$org_jetbrains_kotlin_fir_jvm", "storeBoundsAfterSecondRound", "storeBoundsAfterSecondRound$org_jetbrains_kotlin_fir_jvm", "acceptChildren", Argument.Delimiters.none, "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;Ljava/lang/Object;)V", "transformChildren", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter;", "transformAnnotations", "replaceBounds", "newBounds", "replaceAnnotations", "newAnnotations", "BoundsEnhancementState", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaTypeParameter extends FirTypeParameter {
    private final FirJavaAnnotationList annotationList;
    private final FirDeclarationAttributes attributes;
    private volatile BoundsEnhancementState boundsEnhancementState;
    private final FirBasedSymbol<?> containingDeclarationSymbol;
    private final JavaTypeParameter javaTypeParameter;
    private final FirModuleData moduleData;
    private final Name name;
    private final FirDeclarationOrigin origin;
    private final KtSourceElement source;
    private final FirTypeParameterSymbol symbol;

    public FirJavaTypeParameter(JavaTypeParameter javaTypeParameter, KtSourceElement ktSourceElement, FirModuleData firModuleData, FirDeclarationOrigin firDeclarationOrigin, FirDeclarationAttributes firDeclarationAttributes, Name name, FirTypeParameterSymbol firTypeParameterSymbol, FirBasedSymbol<?> firBasedSymbol, FirJavaAnnotationList firJavaAnnotationList) {
        javaTypeParameter.getClass();
        firModuleData.getClass();
        firDeclarationOrigin.getClass();
        firDeclarationAttributes.getClass();
        name.getClass();
        firTypeParameterSymbol.getClass();
        firBasedSymbol.getClass();
        firJavaAnnotationList.getClass();
        this.javaTypeParameter = javaTypeParameter;
        this.source = ktSourceElement;
        this.moduleData = firModuleData;
        this.origin = firDeclarationOrigin;
        this.attributes = firDeclarationAttributes;
        this.name = name;
        this.symbol = firTypeParameterSymbol;
        this.containingDeclarationSymbol = firBasedSymbol;
        this.annotationList = firJavaAnnotationList;
        this.boundsEnhancementState = new BoundsEnhancementState.NotFinished.NotStarted(LazyKt.lazy(LazyThreadSafetyMode.PUBLICATION, new Function0() { // from class: l95
            public final Object invoke() {
                return FirJavaTypeParameter.a(this.b);
            }
        }));
        getSymbol().bind(this);
        setResolveState(FirResolveStateKt.asResolveState(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES()));
    }

    public static List a(FirJavaTypeParameter firJavaTypeParameter) {
        FirSession session = firJavaTypeParameter.getModuleData().getSession();
        KtSourceElement source = firJavaTypeParameter.getSource();
        KtSourceElement ktSourceElementFakeElement$default = source != null ? KtSourceElementKt.fakeElement$default(source, KtFakeSourceElementKind.Enhancement.INSTANCE, null, 2, null) : null;
        Collection upperBounds = firJavaTypeParameter.javaTypeParameter.getUpperBounds();
        List arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(upperBounds, 10));
        Iterator it = upperBounds.iterator();
        while (it.hasNext()) {
            arrayList.add(JavaTypeConversionKt.toFirJavaTypeRef((JavaClassifierType) it.next(), session, ktSourceElementFakeElement$default));
        }
        if (arrayList.isEmpty()) {
            BuiltinTypes builtinTypes = session.getBuiltinTypes();
            FirResolvedTypeRefBuilder firResolvedTypeRefBuilder = new FirResolvedTypeRefBuilder();
            firResolvedTypeRefBuilder.setConeType(new ConeFlexibleType(builtinTypes.getAnyType().getConeType(), builtinTypes.getNullableAnyType().getConeType(), true));
            arrayList = CollectionsKt.listOf(firResolvedTypeRefBuilder.build());
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <R, D> void acceptChildren(FirVisitor<? extends R, ? super D> visitor, D data) {
        visitor.getClass();
        Iterator<T> it = getBounds().iterator();
        while (it.hasNext()) {
            ((FirTypeRef) it.next()).accept(visitor, data);
        }
        Iterator<T> it2 = getAnnotations().iterator();
        while (it2.hasNext()) {
            ((FirAnnotation) it2.next()).accept(visitor, data);
        }
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public List<FirAnnotation> getAnnotations() {
        return this.annotationList;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationAttributes getAttributes() {
        return this.attributes;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalStateExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter
    public List<FirTypeRef> getBounds() throws KotlinIllegalStateExceptionWithAttachments, KotlinIllegalArgumentExceptionWithAttachments {
        BoundsEnhancementState boundsEnhancementState = this.boundsEnhancementState;
        List<FirResolvedTypeRef> enhancedBounds = boundsEnhancementState.getEnhancedBounds();
        if (enhancedBounds != null) {
            return enhancedBounds;
        }
        if (!(getContainingDeclarationSymbol() instanceof FirClassSymbol)) {
            return boundsEnhancementState.getBounds();
        }
        FirDeclaration fir = getContainingDeclarationSymbol().getFir();
        if (!(fir instanceof FirJavaClass)) {
            KotlinIllegalStateExceptionWithAttachments kotlinIllegalStateExceptionWithAttachments = new KotlinIllegalStateExceptionWithAttachments("Unexpected containing declaration: " + Reflection.getOrCreateKotlinClass(fir.getClass()).getSimpleName());
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "class", fir);
            kotlinIllegalStateExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalStateExceptionWithAttachments;
        }
        ((FirJavaClass) fir).getTypeParameters();
        BoundsEnhancementState boundsEnhancementState2 = this.boundsEnhancementState;
        List<FirResolvedTypeRef> enhancedBounds2 = boundsEnhancementState2.getEnhancedBounds();
        if (enhancedBounds2 != null) {
            return enhancedBounds2;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Attempt to access Java type parameter bounds before their enhancement! (state: " + Reflection.getOrCreateKotlinClass(boundsEnhancementState2.getClass()).getSimpleName() + ')', (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder2 = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder2, "class", fir);
        exceptionAttachmentBuilder2.withEntry(ModuleXmlParser.NAME, getName().asString());
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder2.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter
    public FirBasedSymbol<?> getContainingDeclarationSymbol() {
        return this.containingDeclarationSymbol;
    }

    public final JavaTypeParameter getJavaTypeParameter() {
        return this.javaTypeParameter;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState
    public FirModuleData getModuleData() {
        return this.moduleData;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter
    public Name getName() {
        return this.name;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirDeclarationOrigin getOrigin() {
        return this.origin;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirElementWithResolveState, org.jetbrains.kotlin.fir.FirElement
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter
    public Variance getVariance() {
        return Variance.INVARIANT;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter
    public boolean isReified() {
        return false;
    }

    public final List<FirResolvedTypeRef> performFirstRoundOfBoundsResolution$org_jetbrains_kotlin_fir_jvm(JavaTypeParameterStack javaTypeParameterStack, KtSourceElement source) {
        javaTypeParameterStack.getClass();
        BoundsEnhancementState boundsEnhancementState = this.boundsEnhancementState;
        if (!(boundsEnhancementState instanceof BoundsEnhancementState.NotFinished.NotStarted)) {
            return null;
        }
        List<FirTypeRef> bounds = ((BoundsEnhancementState.NotFinished.NotStarted) boundsEnhancementState).getBounds();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = bounds.iterator();
        while (it.hasNext()) {
            FirResolvedTypeRef firResolvedTypeRefResolveIfJavaType = JavaTypeConversionKt.resolveIfJavaType((FirTypeRef) it.next(), getModuleData().getSession(), javaTypeParameterStack, source, FirJavaTypeConversionMode.TYPE_PARAMETER_BOUND_FIRST_ROUND);
            firResolvedTypeRefResolveIfJavaType.getClass();
            arrayList.add(firResolvedTypeRefResolveIfJavaType);
        }
        return arrayList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final List<FirResolvedTypeRef> performSecondRoundOfBoundsResolution$org_jetbrains_kotlin_fir_jvm(JavaTypeParameterStack javaTypeParameterStack, KtSourceElement source) throws KotlinIllegalArgumentExceptionWithAttachments {
        javaTypeParameterStack.getClass();
        BoundsEnhancementState boundsEnhancementState = this.boundsEnhancementState;
        if (boundsEnhancementState instanceof BoundsEnhancementState.NotFinished.FirstRound) {
            List<FirTypeRef> bounds = ((BoundsEnhancementState.NotFinished.FirstRound) boundsEnhancementState).getBounds();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = bounds.iterator();
            while (it.hasNext()) {
                FirResolvedTypeRef firResolvedTypeRefResolveIfJavaType = JavaTypeConversionKt.resolveIfJavaType((FirTypeRef) it.next(), getModuleData().getSession(), javaTypeParameterStack, source, FirJavaTypeConversionMode.TYPE_PARAMETER_BOUND_AFTER_FIRST_ROUND);
                firResolvedTypeRefResolveIfJavaType.getClass();
                arrayList.add(firResolvedTypeRefResolveIfJavaType);
            }
            return arrayList;
        }
        if (boundsEnhancementState instanceof BoundsEnhancementState.Completed) {
            return null;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Attempt to miss the first round of Java class type parameter bounds enhancement!");
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "owner", getContainingDeclarationSymbol().getFir());
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "parameter", this);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public void replaceAnnotations(List<? extends FirAnnotation> newAnnotations) throws KotlinNothingValueException {
        newAnnotations.getClass();
        UtilsKt.shouldNotBeCalled(this, new AnonymousClass1(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter.replaceAnnotations.2
            public Object get() {
                return ((FirJavaTypeParameter) ((CallableReference) this).receiver).getAnnotations();
            }
        });
        throw new KotlinNothingValueException();
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter
    public void replaceBounds(List<? extends FirTypeRef> newBounds) throws KotlinNothingValueException {
        newBounds.getClass();
        UtilsKt.shouldNotBeCalled(this, new C00331(this), new PropertyReference0Impl(this) { // from class: org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter.replaceBounds.2
            public Object get() {
                return ((FirJavaTypeParameter) ((CallableReference) this).receiver).getBounds();
            }
        });
        throw new KotlinNothingValueException();
    }

    public final boolean storeBoundsAfterFirstRound$org_jetbrains_kotlin_fir_jvm(List<? extends FirResolvedTypeRef> bounds) {
        bounds.getClass();
        BoundsEnhancementState boundsEnhancementState = this.boundsEnhancementState;
        if (!(boundsEnhancementState instanceof BoundsEnhancementState.NotFinished.NotStarted)) {
            return false;
        }
        this.boundsEnhancementState = new BoundsEnhancementState.NotFinished.FirstRound(((BoundsEnhancementState.NotFinished.NotStarted) boundsEnhancementState).getInitialBounds(), bounds);
        return true;
    }

    public final boolean storeBoundsAfterSecondRound$org_jetbrains_kotlin_fir_jvm(List<? extends FirResolvedTypeRef> bounds) {
        bounds.getClass();
        if (!(this.boundsEnhancementState instanceof BoundsEnhancementState.NotFinished.FirstRound)) {
            return false;
        }
        this.boundsEnhancementState = new BoundsEnhancementState.Completed(bounds);
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirAnnotationContainer transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public /* bridge */ /* synthetic */ FirElement transformChildren(FirTransformer firTransformer, Object obj) {
        return transformChildren((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u0001:\u0002\f\rB\t\b\u0004¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b\u0082\u0001\u0002\u000e\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState;", Argument.Delimiters.none, "<init>", "()V", "bounds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getBounds", "()Ljava/util/List;", "enhancedBounds", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "getEnhancedBounds", "NotFinished", "Completed", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$Completed;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$NotFinished;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static abstract class BoundsEnhancementState {

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\b\u0012\u0004\u0012\u00020\n0\u00038VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$Completed;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState;", "enhancedBounds", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "<init>", "(Ljava/util/List;)V", "getEnhancedBounds", "()Ljava/util/List;", "bounds", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getBounds", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Completed extends BoundsEnhancementState {
            private final List<FirResolvedTypeRef> enhancedBounds;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public Completed(List<? extends FirResolvedTypeRef> list) {
                super(null);
                list.getClass();
                this.enhancedBounds = list;
            }

            @Override // org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter.BoundsEnhancementState
            public List<FirTypeRef> getBounds() {
                return getEnhancedBounds();
            }

            @Override // org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter.BoundsEnhancementState
            public List<FirResolvedTypeRef> getEnhancedBounds() {
                return this.enhancedBounds;
            }
        }

        public /* synthetic */ BoundsEnhancementState(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public abstract List<FirTypeRef> getBounds();

        public abstract List<FirResolvedTypeRef> getEnhancedBounds();

        private BoundsEnhancementState() {
        }

        @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\r\u000eB\u001d\b\u0004\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001d\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f\u0082\u0001\u0002\u000f\u0010¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$NotFinished;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState;", "initialBounds", "Lkotlin/Lazy;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "<init>", "(Lkotlin/Lazy;)V", "getInitialBounds", "()Lkotlin/Lazy;", "bounds", "getBounds", "()Ljava/util/List;", "NotStarted", "FirstRound", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$NotFinished$FirstRound;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$NotFinished$NotStarted;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static abstract class NotFinished extends BoundsEnhancementState {
            private final Lazy<List<FirTypeRef>> initialBounds;

            @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B)\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004¢\u0006\u0004\b\b\u0010\tR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00070\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$NotFinished$FirstRound;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$NotFinished;", "initialBounds", "Lkotlin/Lazy;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "enhancedBounds", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "<init>", "(Lkotlin/Lazy;Ljava/util/List;)V", "getEnhancedBounds", "()Ljava/util/List;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
            public static final class FirstRound extends NotFinished {
                private final List<FirResolvedTypeRef> enhancedBounds;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public FirstRound(Lazy<? extends List<? extends FirTypeRef>> lazy, List<? extends FirResolvedTypeRef> list) {
                    super(lazy, null);
                    lazy.getClass();
                    list.getClass();
                    this.enhancedBounds = list;
                }

                @Override // org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter.BoundsEnhancementState
                public List<FirResolvedTypeRef> getEnhancedBounds() {
                    return this.enhancedBounds;
                }
            }

            @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001c\u0010\b\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$NotFinished$NotStarted;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaTypeParameter$BoundsEnhancementState$NotFinished;", "initialBounds", "Lkotlin/Lazy;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "<init>", "(Lkotlin/Lazy;)V", "enhancedBounds", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "getEnhancedBounds", "()Ljava/util/List;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
            public static final class NotStarted extends NotFinished {
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                public NotStarted(Lazy<? extends List<? extends FirTypeRef>> lazy) {
                    super(lazy, null);
                    lazy.getClass();
                }

                @Override // org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter.BoundsEnhancementState
                public List<FirResolvedTypeRef> getEnhancedBounds() {
                    return null;
                }
            }

            /* JADX WARN: Multi-variable type inference failed */
            private NotFinished(Lazy<? extends List<? extends FirTypeRef>> lazy) {
                super(null);
                this.initialBounds = lazy;
            }

            @Override // org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter.BoundsEnhancementState
            public List<FirTypeRef> getBounds() {
                return (List) this.initialBounds.getValue();
            }

            public final Lazy<List<FirTypeRef>> getInitialBounds() {
                return this.initialBounds;
            }

            public /* synthetic */ NotFinished(Lazy lazy, DefaultConstructorMarker defaultConstructorMarker) {
                this(lazy);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration
    public FirTypeParameterSymbol getSymbol() {
        return this.symbol;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public <D> FirJavaTypeParameter transformAnnotations(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.FirPureAbstractElement, org.jetbrains.kotlin.fir.FirElement
    public <D> FirJavaTypeParameter transformChildren(FirTransformer<? super D> transformer, D data) {
        transformer.getClass();
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirDeclaration transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    @Override // org.jetbrains.kotlin.fir.declarations.FirTypeParameter, org.jetbrains.kotlin.fir.declarations.FirDeclaration, org.jetbrains.kotlin.fir.FirAnnotationContainer, org.jetbrains.kotlin.fir.expressions.FirStatement
    public /* bridge */ /* synthetic */ FirTypeParameter transformAnnotations(FirTransformer firTransformer, Object obj) {
        return transformAnnotations((FirTransformer<? super Object>) firTransformer, obj);
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter$replaceAnnotations$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<List<? extends FirAnnotation>, Unit> {
        public AnonymousClass1(Object obj) {
            super(1, obj, FirJavaTypeParameter.class, "replaceAnnotations", "replaceAnnotations(Ljava/util/List;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(List<? extends FirAnnotation> list) throws KotlinNothingValueException {
            list.getClass();
            ((FirJavaTypeParameter) ((CallableReference) this).receiver).replaceAnnotations(list);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((List<? extends FirAnnotation>) obj);
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.fir.java.declarations.FirJavaTypeParameter$replaceBounds$1, reason: invalid class name and case insensitive filesystem */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class C00331 extends FunctionReferenceImpl implements Function1<List<? extends FirTypeRef>, Unit> {
        public C00331(Object obj) {
            super(1, obj, FirJavaTypeParameter.class, "replaceBounds", "replaceBounds(Ljava/util/List;)V", 0);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public final void invoke(List<? extends FirTypeRef> list) throws KotlinNothingValueException {
            list.getClass();
            ((FirJavaTypeParameter) ((CallableReference) this).receiver).replaceBounds(list);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public /* bridge */ /* synthetic */ Object invoke(Object obj) throws KotlinNothingValueException {
            invoke((List<? extends FirTypeRef>) obj);
            return Unit.INSTANCE;
        }
    }
}
