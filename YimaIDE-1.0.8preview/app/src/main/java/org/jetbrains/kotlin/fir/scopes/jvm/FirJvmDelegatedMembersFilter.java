package org.jetbrains.kotlin.fir.scopes.jvm;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMembersFilter;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.JvmStandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \r2\u00020\u0001:\u0001\rB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0016J\u0010\u0010\n\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\tH\u0002J\u0010\u0010\u000b\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\tH\u0002J\u0010\u0010\f\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/jvm/FirJvmDelegatedMembersFilter;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDelegatedMembersFilter;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "shouldNotGenerateDelegatedMember", Argument.Delimiters.none, "memberSymbolFromSuperInterface", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "isNonAbstractJavaMethod", "hasJvmDefaultAnnotation", "isBuiltInMemberMappedToJavaDefault", "Companion", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJvmDelegatedMembersFilter extends FirDelegatedMembersFilter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ClassId PLATFORM_DEPENDENT_ANNOTATION_CLASS_ID = ClassId.Companion.topLevel(new FqName("kotlin.internal.PlatformDependent"));
    private final FirSession session;

    public FirJvmDelegatedMembersFilter(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
    }

    private final boolean hasJvmDefaultAnnotation(FirCallableSymbol<?> firCallableSymbol) {
        return FirAnnotationUtilsKt.hasAnnotation(firCallableSymbol.getAnnotations(), JvmStandardClassIds.INSTANCE.getJVM_DEFAULT_CLASS_ID(), this.session);
    }

    private final boolean isBuiltInMemberMappedToJavaDefault(FirCallableSymbol<?> firCallableSymbol) {
        return ((FirMemberDeclaration) firCallableSymbol.getFir()).getStatus().getModality() != Modality.ABSTRACT && FirAnnotationUtilsKt.hasAnnotation(firCallableSymbol.getAnnotations(), PLATFORM_DEPENDENT_ANNOTATION_CLASS_ID, this.session);
    }

    private final boolean isNonAbstractJavaMethod(FirCallableSymbol<?> firCallableSymbol) {
        return Intrinsics.areEqual(firCallableSymbol.getOrigin(), FirDeclarationOrigin.Enhancement.INSTANCE) && ((FirMemberDeclaration) firCallableSymbol.getFir()).getStatus().getModality() != Modality.ABSTRACT;
    }

    @Override // org.jetbrains.kotlin.fir.scopes.impl.FirDelegatedMembersFilter
    public boolean shouldNotGenerateDelegatedMember(FirCallableSymbol<?> memberSymbolFromSuperInterface) {
        memberSymbolFromSuperInterface.getClass();
        FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) memberSymbolFromSuperInterface.getFir();
        while (true) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            firCallableDeclaration = originalForSubstitutionOverrideAttr;
        }
        FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
        if (symbol != null) {
            return isNonAbstractJavaMethod(symbol) || hasJvmDefaultAnnotation(symbol) || isBuiltInMemberMappedToJavaDefault(symbol) || Intrinsics.areEqual(symbol.getOrigin(), FirDeclarationOrigin.Synthetic.FakeHiddenInPreparationForNewJdk.INSTANCE);
        }
        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol<*>");
        return false;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/jvm/FirJvmDelegatedMembersFilter$Companion;", Argument.Delimiters.none, "<init>", "()V", "PLATFORM_DEPENDENT_ANNOTATION_CLASS_ID", "Lorg/jetbrains/kotlin/name/ClassId;", "getPLATFORM_DEPENDENT_ANNOTATION_CLASS_ID", "()Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ClassId getPLATFORM_DEPENDENT_ANNOTATION_CLASS_ID() {
            return FirJvmDelegatedMembersFilter.PLATFORM_DEPENDENT_ANNOTATION_CLASS_ID;
        }

        private Companion() {
        }
    }
}
