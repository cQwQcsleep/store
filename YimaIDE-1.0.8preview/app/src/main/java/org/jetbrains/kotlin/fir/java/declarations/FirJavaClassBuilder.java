package org.jetbrains.kotlin.fir.java.declarations;

import java.util.ArrayList;
import java.util.List;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.builder.FirAnnotationContainerBuilder;
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.java.MutableJavaTypeParameterStack;
import org.jetbrains.kotlin.fir.java.enhancement.FirEmptyJavaAnnotationList;
import org.jetbrains.kotlin.fir.java.enhancement.FirEmptyJavaDeclarationList;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaAnnotationList;
import org.jetbrains.kotlin.fir.java.enhancement.FirJavaDeclarationList;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.load.java.structure.JavaClass;
import org.jetbrains.kotlin.load.java.structure.JavaPackage;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@FirBuilderDsl
@Metadata(d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\b\u0010K\u001a\u00020LH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR+\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\f8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u001a\u0010\u0019\u001a\u00020\u001aX\u0086.¢\u0006\u000e\n\u0000\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u0017\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 ¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001a\u0010*\u001a\u00020+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u001a\u00100\u001a\b\u0012\u0004\u0012\u0002010 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010#R\u001a\u00103\u001a\b\u0012\u0004\u0012\u0002040 8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b5\u0010#R\u001a\u00106\u001a\b\u0012\u0004\u0012\u0002070 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b8\u0010#R \u00109\u001a\b\u0012\u0002\b\u0003\u0018\u00010:X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010<\"\u0004\b=\u0010>R\u001a\u0010?\u001a\u00020@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\u001c\u0010E\u001a\u0004\u0018\u00010FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JRD\u0010O\u001a\u00020N2\u0006\u0010M\u001a\u00020N8V@VX\u0097\u000er\u0018\bU\u0012\b\bV\u0012\u0004\b\b(W\u0012\n\bX\u0012\u0006\b\n0Y8Z¢\u0006\u0012\u0012\u0004\bP\u0010\u0004\u001a\u0004\bQ\u0010R\"\u0004\bS\u0010TÊ\u0001\u0002\b\\¨\u0006["}, d2 = {"Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClassBuilder;", "Lorg/jetbrains/kotlin/fir/declarations/builder/FirRegularClassBuilder;", "Lorg/jetbrains/kotlin/fir/builder/FirAnnotationContainerBuilder;", "<init>", "()V", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "getVisibility", "()Lorg/jetbrains/kotlin/descriptors/Visibility;", "setVisibility", "(Lorg/jetbrains/kotlin/descriptors/Visibility;)V", "<set-?>", Argument.Delimiters.none, "isFromSource", "()Z", "setFromSource", "(Z)V", "isFromSource$delegate", "Lkotlin/properties/ReadWriteProperty;", "javaPackage", "Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;", "getJavaPackage", "()Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;", "setJavaPackage", "(Lorg/jetbrains/kotlin/load/java/structure/JavaPackage;)V", "javaTypeParameterStack", "Lorg/jetbrains/kotlin/fir/java/MutableJavaTypeParameterStack;", "getJavaTypeParameterStack", "()Lorg/jetbrains/kotlin/fir/java/MutableJavaTypeParameterStack;", "setJavaTypeParameterStack", "(Lorg/jetbrains/kotlin/fir/java/MutableJavaTypeParameterStack;)V", "existingNestedClassifierNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "getExistingNestedClassifierNames", "()Ljava/util/List;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "getSource", "()Lorg/jetbrains/kotlin/KtSourceElement;", "setSource", "(Lorg/jetbrains/kotlin/KtSourceElement;)V", "annotationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "getAnnotationList", "()Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;", "setAnnotationList", "(Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaAnnotationList;)V", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "getTypeParameters", "declarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "getDeclarations", "superTypeRefs", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getSuperTypeRefs", "containingClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getContainingClassSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "setContainingClassSymbol", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;)V", "declarationList", "Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaDeclarationList;", "getDeclarationList", "()Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaDeclarationList;", "setDeclarationList", "(Lorg/jetbrains/kotlin/fir/java/enhancement/FirJavaDeclarationList;)V", "javaClass", "Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "getJavaClass", "()Lorg/jetbrains/kotlin/load/java/structure/JavaClass;", "setJavaClass", "(Lorg/jetbrains/kotlin/load/java/structure/JavaClass;)V", "build", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaClass;", "value", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "origin", "getOrigin$annotations", "getOrigin", "()Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "setOrigin", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "Lkotlin/Deprecated;", "message", "Modification of 'origin' has no impact for FirJavaClassBuilder", "level", "Lkotlin/DeprecationLevel;", "HIDDEN", "org.jetbrains.kotlin:fir-jvm", "Lorg/jetbrains/kotlin/fir/builder/FirBuilderDsl;"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaClassBuilder extends FirRegularClassBuilder implements FirAnnotationContainerBuilder {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new MutablePropertyReference1Impl<>(FirJavaClassBuilder.class, "isFromSource", "isFromSource()Z", 0)};
    private FirClassSymbol<?> containingClassSymbol;
    private JavaClass javaClass;
    private JavaPackage javaPackage;
    public MutableJavaTypeParameterStack javaTypeParameterStack;
    private KtSourceElement source;
    public Visibility visibility;

    /* JADX INFO: renamed from: isFromSource$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty isFromSource = Delegates.INSTANCE.notNull();
    private final List<Name> existingNestedClassifierNames = new ArrayList();
    private FirJavaAnnotationList annotationList = FirEmptyJavaAnnotationList.INSTANCE;
    private final List<FirTypeParameterRef> typeParameters = new ArrayList();
    private final List<FirTypeRef> superTypeRefs = new ArrayList();
    private FirJavaDeclarationList declarationList = FirEmptyJavaDeclarationList.INSTANCE;

    @Deprecated(level = DeprecationLevel.HIDDEN, message = "Modification of 'origin' has no impact for FirJavaClassBuilder")
    public static /* synthetic */ void getOrigin$annotations() {
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirTypeParameterRefsOwnerBuilder
    /* JADX INFO: renamed from: build, reason: merged with bridge method [inline-methods] */
    public FirJavaClass mo289build() {
        JavaClass javaClass = this.javaClass;
        KtSourceElement source = getSource();
        FirModuleData moduleData = getModuleData();
        Name name = getName();
        FirDeclarationOrigin.Java javaJavaOrigin = UtilsKt.javaOrigin(isFromSource());
        FirJavaAnnotationList firJavaAnnotationList = this.annotationList;
        FirDeclarationStatus status = getStatus();
        status.getClass();
        return new FirJavaClass(javaClass, source, moduleData, name, javaJavaOrigin, firJavaAnnotationList, (FirResolvedDeclarationStatusImpl) status, getClassKind(), this.declarationList, getScopeProvider(), getSymbol(), getSuperTypeRefs(), getTypeParameters(), this.javaPackage, getJavaTypeParameterStack().copy(), this.existingNestedClassifierNames, this.containingClassSymbol);
    }

    public final FirJavaAnnotationList getAnnotationList() {
        return this.annotationList;
    }

    public final FirClassSymbol<?> getContainingClassSymbol() {
        return this.containingClassSymbol;
    }

    public final FirJavaDeclarationList getDeclarationList() {
        return this.declarationList;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public List<FirDeclaration> getDeclarations() throws KotlinNothingValueException {
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    public final List<Name> getExistingNestedClassifierNames() {
        return this.existingNestedClassifierNames;
    }

    public final JavaClass getJavaClass() {
        return this.javaClass;
    }

    public final JavaPackage getJavaPackage() {
        return this.javaPackage;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final MutableJavaTypeParameterStack getJavaTypeParameterStack() throws UninitializedPropertyAccessException {
        MutableJavaTypeParameterStack mutableJavaTypeParameterStack = this.javaTypeParameterStack;
        if (mutableJavaTypeParameterStack != null) {
            return mutableJavaTypeParameterStack;
        }
        Intrinsics.throwUninitializedPropertyAccessException("javaTypeParameterStack");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ FirDeclarationOrigin getOrigin() {
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public KtSourceElement getSource() {
        return this.source;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public List<FirTypeRef> getSuperTypeRefs() {
        return this.superTypeRefs;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder
    public List<FirTypeParameterRef> getTypeParameters() {
        return this.typeParameters;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final Visibility getVisibility() throws UninitializedPropertyAccessException {
        Visibility visibility = this.visibility;
        if (visibility != null) {
            return visibility;
        }
        Intrinsics.throwUninitializedPropertyAccessException("visibility");
        return null;
    }

    public final boolean isFromSource() {
        return ((Boolean) this.isFromSource.getValue(this, $$delegatedProperties[0])).booleanValue();
    }

    public final void setAnnotationList(FirJavaAnnotationList firJavaAnnotationList) {
        firJavaAnnotationList.getClass();
        this.annotationList = firJavaAnnotationList;
    }

    public final void setContainingClassSymbol(FirClassSymbol<?> firClassSymbol) {
        this.containingClassSymbol = firClassSymbol;
    }

    public final void setDeclarationList(FirJavaDeclarationList firJavaDeclarationList) {
        firJavaDeclarationList.getClass();
        this.declarationList = firJavaDeclarationList;
    }

    public final void setFromSource(boolean z) {
        this.isFromSource.setValue(this, $$delegatedProperties[0], Boolean.valueOf(z));
    }

    public final void setJavaClass(JavaClass javaClass) {
        this.javaClass = javaClass;
    }

    public final void setJavaPackage(JavaPackage javaPackage) {
        this.javaPackage = javaPackage;
    }

    public final void setJavaTypeParameterStack(MutableJavaTypeParameterStack mutableJavaTypeParameterStack) {
        mutableJavaTypeParameterStack.getClass();
        this.javaTypeParameterStack = mutableJavaTypeParameterStack;
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public /* synthetic */ void setOrigin(FirDeclarationOrigin firDeclarationOrigin) {
        firDeclarationOrigin.getClass();
        throw new IllegalStateException();
    }

    @Override // org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirClassBuilder, org.jetbrains.kotlin.fir.declarations.builder.FirDeclarationBuilder
    public void setSource(KtSourceElement ktSourceElement) {
        this.source = ktSourceElement;
    }

    public final void setVisibility(Visibility visibility) {
        visibility.getClass();
        this.visibility = visibility;
    }
}
