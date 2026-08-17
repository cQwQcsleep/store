package org.jetbrains.kotlin.fir.declarations;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.ReturnValueCheckerMode;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFileSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.name.StandardClassIds$Annotations;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000 '2\u00020\u0001:\u0003'()B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JJ\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H&J<\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\f\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u0010\b\u0002\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0011H&J2\u0010\u0016\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\t2\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\t2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0016\u0010!\u001a\u00020\u000b2\u000e\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0011J\u000e\u0010$\u001a\u00020\u000b2\u0006\u0010%\u001a\u00020&R\u0014\u0010\u001b\u001a\u00020\u001cX\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00150 X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "<init>", "()V", "computeMustUseReturnValueForCallable", "Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "declaration", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "isLocal", Argument.Delimiters.none, "containingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "containingProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "overriddenStatuses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "computeMustUseReturnValueForJavaCallable", "javaPackageAnnotations", "Lorg/jetbrains/kotlin/name/ClassId;", "isExpectActualIgnorabilityCompatible", "expect", "actual", "containingExpectClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "errorPronePackageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "getErrorPronePackageFqName$org_jetbrains_kotlin_semantics", "()Lorg/jetbrains/kotlin/name/FqName;", "ignorableReturnValueLikeAnnotations", Argument.Delimiters.none, "hasIgnorableLikeAnnotation", "list", "JAVA_LANG_VOID", "isIgnorableType", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Companion", "Disabled", "Default", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirMustUseReturnValueStatusComponent implements FirSessionComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ClassId JAVA_LANG_VOID;
    private final FqName errorPronePackageFqName;
    private final Set<ClassId> ignorableReturnValueLikeAnnotations;

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0007\u001a\u00020\b*\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tH\u0002J:\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\f\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00112\u000e\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tH\u0016JJ\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\u0006\u0010\u0014\u001a\u00020\b2\f\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\tH\u0016J2\u0010\u0019\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u000f2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J \u0010\u001e\u001a\u00020\b*\u0006\u0012\u0002\b\u00030\u000f2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\f\u001a\u00020\rJD\u0010\u001f\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\r2\n\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f2\f\u0010\u0010\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u000e\u0010 \u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\tH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent$Default;", "Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent;", "<init>", "()V", "mustUseReturnValueLikeAnnotations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "hasMustUseReturnValueLikeAnnotation", Argument.Delimiters.none, Argument.Delimiters.none, "computeMustUseReturnValueForJavaCallable", "Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "declaration", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "containingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "javaPackageAnnotations", "computeMustUseReturnValueForCallable", "isLocal", "containingProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "overriddenStatuses", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "isExpectActualIgnorabilityCompatible", "expect", "actual", "containingExpectClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "isNotDirectMember", "findMustUseAmongContainers", "additionalAnnotations", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends FirMustUseReturnValueStatusComponent {
        private final Set<ClassId> mustUseReturnValueLikeAnnotations;

        @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0;

            static {
                int[] iArr = new int[ReturnValueStatus.values().length];
                try {
                    iArr[ReturnValueStatus.MustUse.ordinal()] = 1;
                } catch (NoSuchFieldError unused) {
                }
                try {
                    iArr[ReturnValueStatus.ExplicitlyIgnorable.ordinal()] = 2;
                } catch (NoSuchFieldError unused2) {
                }
                try {
                    iArr[ReturnValueStatus.Unspecified.ordinal()] = 3;
                } catch (NoSuchFieldError unused3) {
                }
                $EnumSwitchMapping$0 = iArr;
            }
        }

        public Default() {
            ClassId mustUseReturnValues = StandardClassIds$Annotations.INSTANCE.getMustUseReturnValues();
            FqName base_kotlin_package = StandardClassIds.INSTANCE.getBASE_KOTLIN_PACKAGE();
            Name nameIdentifier = Name.identifier("MustUseReturnValue");
            nameIdentifier.getClass();
            ClassId classId = new ClassId(base_kotlin_package, nameIdentifier);
            FqName errorPronePackageFqName = getErrorPronePackageFqName();
            Name nameIdentifier2 = Name.identifier("CheckReturnValue");
            nameIdentifier2.getClass();
            ClassId classId2 = new ClassId(errorPronePackageFqName, nameIdentifier2);
            FqName fqName = new FqName("org.jetbrains.annotations");
            Name nameIdentifier3 = Name.identifier("CheckReturnValue");
            nameIdentifier3.getClass();
            ClassId classId3 = new ClassId(fqName, nameIdentifier3);
            FqName fqName2 = new FqName("org.springframework.lang");
            Name nameIdentifier4 = Name.identifier("CheckReturnValue");
            nameIdentifier4.getClass();
            ClassId classId4 = new ClassId(fqName2, nameIdentifier4);
            FqName fqName3 = new FqName("org.jooq");
            Name nameIdentifier5 = Name.identifier("CheckReturnValue");
            nameIdentifier5.getClass();
            ClassId classId5 = new ClassId(fqName3, nameIdentifier5);
            FqName fqName4 = new FqName("edu.umd.cs.findbugs.annotations");
            Name nameIdentifier6 = Name.identifier("CheckReturnValue");
            nameIdentifier6.getClass();
            this.mustUseReturnValueLikeAnnotations = SetsKt.setOf(new ClassId[]{mustUseReturnValues, classId, classId2, classId3, classId4, classId5, new ClassId(fqName4, nameIdentifier6)});
        }

        private final boolean findMustUseAmongContainers(FirSession session, FirCallableSymbol<?> declaration, FirClassLikeSymbol<?> containingClass, FirPropertySymbol containingProperty, List<ClassId> additionalAnnotations) {
            FirClassLikeSymbol<FirClassLikeDeclaration> containingDeclaration;
            FirFileSymbol symbol;
            if (hasMustUseReturnValueLikeAnnotation(declaration.getResolvedAnnotationClassIds())) {
                return true;
            }
            if (hasMustUseReturnValueLikeAnnotation(containingClass != null ? containingClass.getResolvedAnnotationClassIds() : null)) {
                return true;
            }
            FirFile firCallableContainerFile = FirProviderKt.getFirProvider(session).getFirCallableContainerFile(declaration);
            if (hasMustUseReturnValueLikeAnnotation((firCallableContainerFile == null || (symbol = firCallableContainerFile.getSymbol()) == null) ? null : symbol.getResolvedAnnotationClassIds())) {
                return true;
            }
            if (hasMustUseReturnValueLikeAnnotation(containingProperty != null ? containingProperty.getResolvedAnnotationClassIds() : null) || hasMustUseReturnValueLikeAnnotation(additionalAnnotations)) {
                return true;
            }
            if (containingClass == null || (containingDeclaration = org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt.getContainingDeclaration((FirClassLikeSymbol<? extends FirClassLikeDeclaration>) containingClass, session)) == null) {
                return false;
            }
            return findMustUseAmongContainers$hasMurvOrOuter(containingDeclaration, this, session);
        }

        private static final boolean findMustUseAmongContainers$hasMurvOrOuter(FirClassLikeSymbol<?> firClassLikeSymbol, Default r2, FirSession firSession) {
            while (!r2.hasMustUseReturnValueLikeAnnotation(firClassLikeSymbol.getResolvedAnnotationClassIds())) {
                firClassLikeSymbol = org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt.getContainingDeclaration((FirClassLikeSymbol<? extends FirClassLikeDeclaration>) firClassLikeSymbol, firSession);
                if (firClassLikeSymbol == null) {
                    return false;
                }
            }
            return true;
        }

        private final boolean hasMustUseReturnValueLikeAnnotation(List<ClassId> list) {
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            List<ClassId> list2 = list;
            if ((list2 instanceof Collection) && list2.isEmpty()) {
                return false;
            }
            Iterator<T> it = list2.iterator();
            while (it.hasNext()) {
                if (this.mustUseReturnValueLikeAnnotations.contains((ClassId) it.next())) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponent
        public ReturnValueStatus computeMustUseReturnValueForCallable(FirSession session, FirCallableSymbol<?> declaration, boolean isLocal, FirClassLikeSymbol<?> containingClass, FirPropertySymbol containingProperty, List<? extends FirResolvedDeclarationStatus> overriddenStatuses) {
            session.getClass();
            declaration.getClass();
            overriddenStatuses.getClass();
            ReturnValueCheckerMode returnValueCheckerMode = (ReturnValueCheckerMode) FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).getFlag(AnalysisFlags.getReturnValueCheckerMode());
            if (isLocal) {
                return ((declaration instanceof FirFunctionSymbol) && returnValueCheckerMode == ReturnValueCheckerMode.FULL) ? ReturnValueStatus.MustUse : ReturnValueStatus.Unspecified;
            }
            FirResolvedDeclarationStatus firResolvedDeclarationStatus = (FirResolvedDeclarationStatus) CollectionsKt.firstOrNull(overriddenStatuses);
            ReturnValueStatus returnValueStatus = firResolvedDeclarationStatus != null ? firResolvedDeclarationStatus.getReturnValueStatus() : null;
            if (hasIgnorableLikeAnnotation(declaration.getResolvedAnnotationClassIds())) {
                return ReturnValueStatus.ExplicitlyIgnorable;
            }
            ReturnValueStatus returnValueStatus2 = ReturnValueStatus.MustUse;
            if (returnValueStatus != returnValueStatus2) {
                boolean z = returnValueStatus == ReturnValueStatus.ExplicitlyIgnorable || returnValueStatus == ReturnValueStatus.Unspecified;
                if ((returnValueCheckerMode != ReturnValueCheckerMode.FULL || z) && !findMustUseAmongContainers(session, declaration, containingClass, containingProperty, null)) {
                    return returnValueStatus == null ? ReturnValueStatus.Unspecified : returnValueStatus;
                }
            }
            return returnValueStatus2;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponent
        public ReturnValueStatus computeMustUseReturnValueForJavaCallable(FirSession session, FirCallableSymbol<?> declaration, FirClassLikeSymbol<?> containingClass, List<ClassId> javaPackageAnnotations) {
            session.getClass();
            declaration.getClass();
            if (hasIgnorableLikeAnnotation(declaration.getResolvedAnnotationClassIds())) {
                return ReturnValueStatus.ExplicitlyIgnorable;
            }
            return findMustUseAmongContainers(session, declaration, containingClass, null, javaPackageAnnotations) ? ReturnValueStatus.MustUse : ReturnValueStatus.Unspecified;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponent
        public boolean isExpectActualIgnorabilityCompatible(FirSession session, FirCallableSymbol<?> expect, FirCallableSymbol<?> actual, FirRegularClassSymbol containingExpectClass) {
            ReturnValueStatus returnValueStatus;
            session.getClass();
            expect.getClass();
            actual.getClass();
            if (isIgnorableType(expect.getResolvedReturnType()) || isIgnorableType(actual.getResolvedReturnType())) {
                return true;
            }
            boolean z = isNotDirectMember(actual, containingExpectClass, session) || isNotDirectMember(expect, containingExpectClass, session);
            ReturnValueStatus returnValueStatus2 = expect.getResolvedStatus().getReturnValueStatus();
            ReturnValueStatus returnValueStatus3 = actual.getResolvedStatus().getReturnValueStatus();
            if (z && (returnValueStatus2 == (returnValueStatus = ReturnValueStatus.Unspecified) || returnValueStatus3 == returnValueStatus)) {
                return true;
            }
            int i = WhenMappings.$EnumSwitchMapping$0[returnValueStatus2.ordinal()];
            if (i == 1) {
                return returnValueStatus3 == ReturnValueStatus.MustUse;
            }
            if (i == 2 || i == 3) {
                return returnValueStatus3 != ReturnValueStatus.MustUse;
            }
            bu8.a();
            return false;
        }

        public final boolean isNotDirectMember(FirCallableSymbol<?> firCallableSymbol, FirRegularClassSymbol firRegularClassSymbol, FirSession firSession) {
            firCallableSymbol.getClass();
            firSession.getClass();
            if (firRegularClassSymbol == null) {
                return false;
            }
            if (firCallableSymbol instanceof FirConstructorSymbol) {
                FirRegularClassSymbol constructedClass = DeclarationUtilsKt.getConstructedClass((FirConstructorSymbol) firCallableSymbol, firSession);
                return !Intrinsics.areEqual(constructedClass != null ? constructedClass.getClassId() : null, firRegularClassSymbol.getClassId());
            }
            ConeSimpleKotlinType dispatchReceiverType = firCallableSymbol.getDispatchReceiverType();
            if (Intrinsics.areEqual(dispatchReceiverType != null ? ConeTypeUtilsKt.getClassId(dispatchReceiverType) : null, firRegularClassSymbol.getClassId())) {
                return ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableSymbol);
            }
            return true;
        }
    }

    @Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JJ\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0016J:\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\f\u0010\f\u001a\b\u0012\u0002\b\u0003\u0018\u00010\r2\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0015\u0018\u00010\u0011H\u0016¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent$Disabled;", "Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent;", "<init>", "()V", "computeMustUseReturnValueForCallable", "Lorg/jetbrains/kotlin/resolve/ReturnValueStatus;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "declaration", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "isLocal", Argument.Delimiters.none, "containingClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "containingProperty", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "overriddenStatuses", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "computeMustUseReturnValueForJavaCallable", "javaPackageAnnotations", "Lorg/jetbrains/kotlin/name/ClassId;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Disabled extends FirMustUseReturnValueStatusComponent {
        public static final Disabled INSTANCE = new Disabled();

        private Disabled() {
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponent
        public ReturnValueStatus computeMustUseReturnValueForCallable(FirSession session, FirCallableSymbol<?> declaration, boolean isLocal, FirClassLikeSymbol<?> containingClass, FirPropertySymbol containingProperty, List<? extends FirResolvedDeclarationStatus> overriddenStatuses) {
            ReturnValueStatus returnValueStatus;
            session.getClass();
            declaration.getClass();
            overriddenStatuses.getClass();
            FirResolvedDeclarationStatus firResolvedDeclarationStatus = (FirResolvedDeclarationStatus) CollectionsKt.firstOrNull(overriddenStatuses);
            return (firResolvedDeclarationStatus == null || (returnValueStatus = firResolvedDeclarationStatus.getReturnValueStatus()) == null) ? ReturnValueStatus.Unspecified : returnValueStatus;
        }

        @Override // org.jetbrains.kotlin.fir.declarations.FirMustUseReturnValueStatusComponent
        public ReturnValueStatus computeMustUseReturnValueForJavaCallable(FirSession session, FirCallableSymbol<?> declaration, FirClassLikeSymbol<?> containingClass, List<ClassId> javaPackageAnnotations) {
            session.getClass();
            declaration.getClass();
            return ReturnValueStatus.Unspecified;
        }
    }

    public FirMustUseReturnValueStatusComponent() {
        FqName fqNameFromSegments = FqName.Companion.fromSegments(CollectionsKt.listOf(new String[]{"com", "google", "errorprone", "annotations"}));
        this.errorPronePackageFqName = fqNameFromSegments;
        ClassId ignorableReturnValue = StandardClassIds$Annotations.INSTANCE.getIgnorableReturnValue();
        Name nameIdentifier = Name.identifier("CanIgnoreReturnValue");
        nameIdentifier.getClass();
        this.ignorableReturnValueLikeAnnotations = SetsKt.setOf(new ClassId[]{ignorableReturnValue, new ClassId(fqNameFromSegments, nameIdentifier)});
        this.JAVA_LANG_VOID = ClassId.Companion.topLevel(new FqName("java.lang.Void"));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ReturnValueStatus computeMustUseReturnValueForJavaCallable$default(FirMustUseReturnValueStatusComponent firMustUseReturnValueStatusComponent, FirSession firSession, FirCallableSymbol firCallableSymbol, FirClassLikeSymbol firClassLikeSymbol, List list, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: computeMustUseReturnValueForJavaCallable");
            return null;
        }
        if ((i & 8) != 0) {
            list = null;
        }
        return firMustUseReturnValueStatusComponent.computeMustUseReturnValueForJavaCallable(firSession, firCallableSymbol, firClassLikeSymbol, list);
    }

    public abstract ReturnValueStatus computeMustUseReturnValueForCallable(FirSession session, FirCallableSymbol<?> declaration, boolean isLocal, FirClassLikeSymbol<?> containingClass, FirPropertySymbol containingProperty, List<? extends FirResolvedDeclarationStatus> overriddenStatuses);

    public abstract ReturnValueStatus computeMustUseReturnValueForJavaCallable(FirSession session, FirCallableSymbol<?> declaration, FirClassLikeSymbol<?> containingClass, List<ClassId> javaPackageAnnotations);

    /* JADX INFO: renamed from: getErrorPronePackageFqName$org_jetbrains_kotlin_semantics, reason: from getter */
    public final FqName getErrorPronePackageFqName() {
        return this.errorPronePackageFqName;
    }

    public final boolean hasIgnorableLikeAnnotation(List<ClassId> list) {
        if (list == null) {
            list = CollectionsKt.emptyList();
        }
        List<ClassId> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            if (this.ignorableReturnValueLikeAnnotations.contains((ClassId) it.next())) {
                return true;
            }
        }
        return false;
    }

    public boolean isExpectActualIgnorabilityCompatible(FirSession session, FirCallableSymbol<?> expect, FirCallableSymbol<?> actual, FirRegularClassSymbol containingExpectClass) {
        session.getClass();
        expect.getClass();
        actual.getClass();
        return true;
    }

    public final boolean isIgnorableType(ConeKotlinType type) {
        type.getClass();
        if ((type instanceof ConeErrorType) || ConeTypeUtilsKt.hasError(type)) {
            return true;
        }
        ClassId classId = ConeTypeUtilsKt.getClassId(type);
        if (classId == null) {
            return false;
        }
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        if (Intrinsics.areEqual(classId, standardClassIds.getNothing())) {
            return true;
        }
        if (!Intrinsics.areEqual(classId, standardClassIds.getUnit()) || ConeTypeUtilsKt.isMarkedNullable(type)) {
            return Intrinsics.areEqual(classId, this.JAVA_LANG_VOID) && !ConeTypeUtilsKt.isMarkedNullable(type);
        }
        return true;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent$Companion;", Argument.Delimiters.none, "<init>", "()V", CoroutineCodegenUtilKt.SUSPEND_FUNCTION_CREATE_METHOD_NAME, "Lorg/jetbrains/kotlin/fir/declarations/FirMustUseReturnValueStatusComponent;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final FirMustUseReturnValueStatusComponent create(LanguageVersionSettings languageVersionSettings) {
            languageVersionSettings.getClass();
            return languageVersionSettings.getFlag(AnalysisFlags.getReturnValueCheckerMode()) == ReturnValueCheckerMode.DISABLED ? Disabled.INSTANCE : new Default();
        }

        private Companion() {
        }
    }
}
