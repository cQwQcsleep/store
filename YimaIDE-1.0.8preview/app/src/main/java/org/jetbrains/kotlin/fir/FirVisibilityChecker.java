package org.jetbrains.kotlin.fir;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.SupertypeSupplier;
import org.jetbrains.kotlin.fir.resolve.SupertypeUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.name.CallableIdKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001:\u0002CDB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001a\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00000\u0007H\u0017b\u0002\b\bJ,\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u0007J`\u0010\u0013\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\n2\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00192\b\b\u0002\u0010\u001a\u001a\u00020\n2\b\b\u0002\u0010\u001b\u001a\u00020\u001cJ\u0016\u0010\u001d\u001a\u00020\n2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001fJ\"\u0010\u001d\u001a\u00020\n2\u0006\u0010!\u001a\u00020\"2\n\u0010#\u001a\u0006\u0012\u0002\b\u00030$2\u0006\u0010 \u001a\u00020\u001fJ \u0010\u001d\u001a\u00020\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010%\u001a\u00020&2\u0006\u0010 \u001a\u00020\u001fH\u0002J \u0010'\u001a\u00020\n2\u0006\u0010!\u001a\u00020\"2\u0006\u0010%\u001a\u00020&2\u0006\u0010 \u001a\u00020\u001fH\u0002JJ\u0010(\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00142\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\b\b\u0002\u0010\u0017\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002JT\u0010)\u001a\u00020\n2\u0006\u0010*\u001a\u00020+2\n\u0010,\u001a\u0006\u0012\u0002\b\u00030-2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cH$J$\u0010.\u001a\u00020\n2\u0006\u0010%\u001a\u00020&2\n\u0010/\u001a\u0006\u0012\u0002\b\u00030-2\u0006\u00100\u001a\u00020+H$JD\u00101\u001a\u00020\n2\n\u0010,\u001a\u0006\u0012\u0002\b\u00030-2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\u0006\u00103\u001a\u0002042\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u00105\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0016\u00106\u001a\u0004\u0018\u000104*\u0002042\u0006\u0010\r\u001a\u00020\u000eH\u0002JB\u00107\u001a\u00020\n2\u0006\u00108\u001a\u0002092\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u00103\u001a\u0002042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u00105\u001a\u00020\n2\u0006\u0010:\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002J2\u0010;\u001a\u00020\n2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u00108\u001a\u0002092\u0006\u00103\u001a\u0002042\u0006\u0010:\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u00106\u001a\u0004\u0018\u000104*\u0004\u0018\u00010\u00162\u0006\u0010\r\u001a\u00020\u000eH\u0002J\f\u0010<\u001a\u00020\n*\u00020=H\u0002JT\u00107\u001a\u00020\n2\n\u0010>\u001a\u0006\u0012\u0002\b\u00030-2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\u00120\u00072\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u00103\u001a\u0002042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u00105\u001a\u00020\n2\u0006\u0010:\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001cH\u0004J\u0018\u0010?\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020\"H\u0002J$\u0010A\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010B\u001a\u0006\u0012\u0002\b\u00030-H\u0002¨\u0006E"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirVisibilityChecker;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent;", "<init>", "()V", "createComposed", "Lorg/jetbrains/kotlin/fir/FirVisibilityChecker$Composed;", "components", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/SessionConfiguration;", "isClassLikeVisible", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "isVisible", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "isCallToPropertySetter", "staticQualifierClassForCallable", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "skipCheckForContainingClassVisibility", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "isVisibleForOverriding", "candidateInDerivedClass", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "candidateInBaseClass", "derivedClassModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "symbolFromDerivedClass", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "packageNameOfDerivedClass", "Lorg/jetbrains/kotlin/name/FqName;", "isSpecificDeclarationVisibleForOverriding", "isSpecificDeclarationVisible", "platformVisibilityCheck", "declarationVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "platformOverrideVisibilityCheck", "symbolInBaseClass", "visibilityInBaseClass", "canSeePrivateMemberOf", "containingDeclarationOfUseSite", "ownerLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "isVariableOrNamedFunction", "ownerIfCompanion", "canSeeProtectedMemberOf", "containingUseSiteClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "isSyntheticProperty", "doesReceiverFitForProtectedVisibility", "isAllowedToBeAccessedFromOutside", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "usedSymbol", "canSeePrivateDeclarationsOfModule", "otherModuleData", "canSeePrivateTopLevelDeclarationFromFile", "declarationSymbol", "Default", "Composed", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirVisibilityChecker implements FirComposableSessionComponent<FirVisibilityChecker> {

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00010\u0002B\u0015\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0004\b\u0005\u0010\u0006JT\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00042\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\u0019H\u0014J$\u0010\u001a\u001a\u00020\n2\u0006\u0010\u001b\u001a\u00020\u001c2\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\u000e2\u0006\u0010\u001e\u001a\u00020\fH\u0014R\u001a\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirVisibilityChecker$Composed;", "Lorg/jetbrains/kotlin/fir/FirVisibilityChecker;", "Lorg/jetbrains/kotlin/fir/FirComposableSessionComponent$Composed;", "components", Argument.Delimiters.none, "<init>", "(Ljava/util/List;)V", "getComponents", "()Ljava/util/List;", "platformVisibilityCheck", Argument.Delimiters.none, "declarationVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isCallToPropertySetter", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "platformOverrideVisibilityCheck", "packageNameOfDerivedClass", "Lorg/jetbrains/kotlin/name/FqName;", "symbolInBaseClass", "visibilityInBaseClass", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Composed extends FirVisibilityChecker implements FirComposableSessionComponent.Composed<FirVisibilityChecker> {
        private final List<FirVisibilityChecker> components;

        /* JADX WARN: Multi-variable type inference failed */
        public Composed(List<? extends FirVisibilityChecker> list) {
            list.getClass();
            this.components = list;
        }

        @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
        public List<FirVisibilityChecker> getComponents() {
            return this.components;
        }

        @Override // org.jetbrains.kotlin.fir.FirVisibilityChecker
        public boolean platformOverrideVisibilityCheck(FqName packageNameOfDerivedClass, FirBasedSymbol<?> symbolInBaseClass, Visibility visibilityInBaseClass) {
            packageNameOfDerivedClass.getClass();
            symbolInBaseClass.getClass();
            visibilityInBaseClass.getClass();
            List<FirVisibilityChecker> components = getComponents();
            if ((components instanceof Collection) && components.isEmpty()) {
                return true;
            }
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                if (!((FirVisibilityChecker) it.next()).platformOverrideVisibilityCheck(packageNameOfDerivedClass, symbolInBaseClass, visibilityInBaseClass)) {
                    return false;
                }
            }
            return true;
        }

        @Override // org.jetbrains.kotlin.fir.FirVisibilityChecker
        public boolean platformVisibilityCheck(Visibility declarationVisibility, FirBasedSymbol<?> symbol, FirFile useSiteFile, List<? extends FirDeclaration> containingDeclarations, FirExpression dispatchReceiver, FirSession session, boolean isCallToPropertySetter, SupertypeSupplier supertypeSupplier) {
            declarationVisibility.getClass();
            symbol.getClass();
            useSiteFile.getClass();
            containingDeclarations.getClass();
            session.getClass();
            supertypeSupplier.getClass();
            List<FirVisibilityChecker> components = getComponents();
            if ((components instanceof Collection) && components.isEmpty()) {
                return true;
            }
            Iterator<T> it = components.iterator();
            while (it.hasNext()) {
                if (!((FirVisibilityChecker) it.next()).platformVisibilityCheck(declarationVisibility, symbol, useSiteFile, containingDeclarations, dispatchReceiver, session, isCallToPropertySetter, supertypeSupplier)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JT\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0014\u001a\u00020\u0015H\u0014J$\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u00182\n\u0010\u0019\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u001a\u001a\u00020\u0007H\u0014¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/FirVisibilityChecker$Default;", "Lorg/jetbrains/kotlin/fir/FirVisibilityChecker;", "<init>", "()V", "platformVisibilityCheck", Argument.Delimiters.none, "declarationVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "useSiteFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "containingDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "isCallToPropertySetter", "supertypeSupplier", "Lorg/jetbrains/kotlin/fir/resolve/SupertypeSupplier;", "platformOverrideVisibilityCheck", "packageNameOfDerivedClass", "Lorg/jetbrains/kotlin/name/FqName;", "symbolInBaseClass", "visibilityInBaseClass", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Default extends FirVisibilityChecker {
        public static final Default INSTANCE = new Default();

        private Default() {
        }

        @Override // org.jetbrains.kotlin.fir.FirVisibilityChecker
        public boolean platformOverrideVisibilityCheck(FqName packageNameOfDerivedClass, FirBasedSymbol<?> symbolInBaseClass, Visibility visibilityInBaseClass) {
            packageNameOfDerivedClass.getClass();
            symbolInBaseClass.getClass();
            visibilityInBaseClass.getClass();
            return true;
        }

        @Override // org.jetbrains.kotlin.fir.FirVisibilityChecker
        public boolean platformVisibilityCheck(Visibility declarationVisibility, FirBasedSymbol<?> symbol, FirFile useSiteFile, List<? extends FirDeclaration> containingDeclarations, FirExpression dispatchReceiver, FirSession session, boolean isCallToPropertySetter, SupertypeSupplier supertypeSupplier) {
            declarationVisibility.getClass();
            symbol.getClass();
            useSiteFile.getClass();
            containingDeclarations.getClass();
            session.getClass();
            supertypeSupplier.getClass();
            return true;
        }
    }

    private final boolean canSeePrivateDeclarationsOfModule(FirSession session, FirModuleData otherModuleData) {
        FirPrivateVisibleFromDifferentModuleExtension privateVisibleFromDifferentModulesExtension;
        return Intrinsics.areEqual(FirModuleDataKt.getModuleData(session), otherModuleData) || ((privateVisibleFromDifferentModulesExtension = FirVisibilityCheckerKt.getPrivateVisibleFromDifferentModulesExtension(session)) != null && privateVisibleFromDifferentModulesExtension.canSeePrivateDeclarationsOfModule(otherModuleData));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final boolean canSeePrivateMemberOf(FirBasedSymbol<?> symbol, List<? extends FirDeclaration> containingDeclarationOfUseSite, ConeClassLikeLookupTag ownerLookupTag, FirExpression dispatchReceiver, boolean isVariableOrNamedFunction, FirSession session) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirCallableDeclaration propertyIfAccessor;
        FirCallableDeclaration propertyIfBackingField;
        ConeClassLikeLookupTag coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull;
        FirClassLikeSymbol<?> symbol2;
        ConeClassLikeLookupTag coneClassLikeLookupTagOwnerIfCompanion = ownerIfCompanion(ownerLookupTag, session);
        if (coneClassLikeLookupTagOwnerIfCompanion != null) {
            return canSeePrivateMemberOf(symbol, containingDeclarationOfUseSite, coneClassLikeLookupTagOwnerIfCompanion, dispatchReceiver, isVariableOrNamedFunction, session);
        }
        if (dispatchReceiver != null && (!(symbol instanceof FirCallableSymbol) || !((FirCallableSymbol) symbol).getRawStatus().isStatic())) {
            FirDeclaration fir = symbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (propertyIfAccessor = ClassMembersKt.getPropertyIfAccessor(firCallableDeclaration)) == null || (propertyIfBackingField = ClassMembersKt.getPropertyIfBackingField(propertyIfAccessor)) == null || (coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull = ClassMembersKt.dispatchReceiverClassLookupTagOrNull(propertyIfBackingField)) == null || (symbol2 = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTagDispatchReceiverClassLookupTagOrNull, session)) == null) {
                return true;
            }
            ConeClassLikeLookupTag lookupTag = symbol2.getLookupTag();
            ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver);
            int size = ((FirClassLikeDeclaration) symbol2.getFir()).getTypeParameters().size();
            ConeStarProjection[] coneStarProjectionArr = new ConeStarProjection[size];
            for (int i = 0; i < size; i++) {
                coneStarProjectionArr[i] = ConeStarProjection.INSTANCE;
            }
            if (!Intrinsics.areEqual(lookupTag, LookupTagUtilsKt.findClassRepresentation(resolvedType, TypeConstructionUtilsKt.constructClassType$default(lookupTag, coneStarProjectionArr, true, null, 4, null), session))) {
                return false;
            }
            if (Intrinsics.areEqual(((FirMemberDeclaration) fir).getStatus().getVisibility(), Visibilities.PrivateToThis.INSTANCE) && (!(dispatchReceiver instanceof FirThisReceiverExpression) || !Intrinsics.areEqual(((FirThisReceiverExpression) dispatchReceiver).getCalleeReference().getBoundSymbol(), symbol2))) {
                return false;
            }
        }
        for (FirDeclaration firDeclaration : containingDeclarationOfUseSite) {
            if ((firDeclaration instanceof FirClass) && Intrinsics.areEqual(((FirClass) firDeclaration).getSymbol().getLookupTag(), ownerLookupTag)) {
                return true;
            }
        }
        return false;
    }

    private final boolean canSeePrivateTopLevelDeclarationFromFile(FirSession session, FirFile useSiteFile, FirBasedSymbol<?> declarationSymbol) {
        FirPrivateVisibleFromDifferentModuleExtension privateVisibleFromDifferentModulesExtension;
        FirFile containingFile = FirProviderUtilsKt.getContainingFile(FirProviderKt.getFirProvider(declarationSymbol.getModuleData().getSession()), declarationSymbol);
        if (containingFile == null) {
            return false;
        }
        return Intrinsics.areEqual(useSiteFile, containingFile) || ((privateVisibleFromDifferentModulesExtension = FirVisibilityCheckerKt.getPrivateVisibleFromDifferentModulesExtension(session)) != null && privateVisibleFromDifferentModulesExtension.canSeePrivateTopLevelDeclarationsFromFile(useSiteFile, containingFile));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final boolean doesReceiverFitForProtectedVisibility(FirExpression dispatchReceiver, FirClass containingUseSiteClass, ConeClassLikeLookupTag ownerLookupTag, boolean isSyntheticProperty, FirSession session) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (dispatchReceiver == null) {
            return true;
        }
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver);
        if (dispatchReceiver instanceof FirSuperReceiverExpression) {
            FirExpression dispatchReceiver2 = ((FirSuperReceiverExpression) dispatchReceiver).getDispatchReceiver();
            dispatchReceiver2.getClass();
            resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver2);
        }
        if (AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeCheckerProviderContext.newTypeCheckerState$default(TypeComponentsKt.getTypeContext(session), false, false, false, 4, (Object) null), TypeExpansionUtilsKt.fullyExpandedType$default(resolvedType, session, (Function1) null, 2, (Object) null), TypeConstructionUtilsKt.constructStarProjectedType$default(containingUseSiteClass.getSymbol(), 0, false, 3, null), false, 8, (Object) null)) {
            return true;
        }
        if (isSyntheticProperty) {
            return Intrinsics.areEqual(FirDeclarationUtilKt.getClassId(containingUseSiteClass).getPackageFqName(), ownerLookupTag.getClassId().getPackageFqName());
        }
        return false;
    }

    private final boolean isAllowedToBeAccessedFromOutside(FirNamedFunction firNamedFunction) {
        if (!Intrinsics.areEqual(firNamedFunction.getOrigin(), FirDeclarationOrigin.Library.INSTANCE) && !Intrinsics.areEqual(firNamedFunction.getOrigin(), FirDeclarationOrigin.Java.Library.INSTANCE)) {
            return false;
        }
        String strAsString = firNamedFunction.getSymbol().getCallableId().getPackageName().asString();
        String strAsString2 = firNamedFunction.getName().asString();
        strAsString2.getClass();
        return Intrinsics.areEqual(strAsString, "kotlin.jvm.internal.unsafe") && (Intrinsics.areEqual(strAsString2, "monitorEnter") || Intrinsics.areEqual(strAsString2, "monitorExit"));
    }

    private final boolean isSpecificDeclarationVisible(FirMemberDeclaration declaration, FirSession session, FirFile useSiteFile, List<? extends FirDeclaration> containingDeclarations, FirExpression dispatchReceiver, boolean isCallToPropertySetter, SupertypeSupplier supertypeSupplier) {
        FirModuleVisibilityChecker moduleVisibilityChecker;
        FirBasedSymbol<FirDeclaration> symbol = declaration.getSymbol();
        Visibility visibility = declaration.getStatus().getVisibility();
        if (Intrinsics.areEqual(visibility, Visibilities.Internal.INSTANCE)) {
            return Intrinsics.areEqual(declaration.getModuleData(), FirModuleDataKt.getModuleData(session)) || ((moduleVisibilityChecker = FirVisibilityCheckerKt.getModuleVisibilityChecker(session)) != null && moduleVisibilityChecker.isInFriendModule(declaration));
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Private.INSTANCE) || Intrinsics.areEqual(visibility, Visibilities.PrivateToThis.INSTANCE)) {
            ConeClassLikeLookupTag ownerLookupTag = FirVisibilityCheckerKt.getOwnerLookupTag(symbol);
            if (canSeePrivateDeclarationsOfModule(session, declaration.getModuleData())) {
                return ownerLookupTag == null ? canSeePrivateTopLevelDeclarationFromFile(session, useSiteFile, symbol) : canSeePrivateMemberOf(symbol, containingDeclarations, ownerLookupTag, dispatchReceiver, FirVisibilityCheckerKt.isVariableOrNamedFunction(symbol), session);
            }
            return (declaration instanceof FirNamedFunction) && isAllowedToBeAccessedFromOutside((FirNamedFunction) declaration);
        }
        if (!Intrinsics.areEqual(visibility, Visibilities.Protected.INSTANCE)) {
            return platformVisibilityCheck(declaration.getStatus().getVisibility(), symbol, useSiteFile, containingDeclarations, dispatchReceiver, session, isCallToPropertySetter, supertypeSupplier);
        }
        ConeClassLikeLookupTag ownerLookupTag2 = FirVisibilityCheckerKt.getOwnerLookupTag(symbol);
        return ownerLookupTag2 != null && canSeeProtectedMemberOf(symbol, containingDeclarations, dispatchReceiver, ownerLookupTag2, session, FirVisibilityCheckerKt.isVariableOrNamedFunction(symbol), symbol.getFir() instanceof FirSyntheticPropertyAccessor, supertypeSupplier);
    }

    private final boolean isSpecificDeclarationVisibleForOverriding(FirModuleData derivedClassModuleData, FqName packageNameOfDerivedClass, FirCallableDeclaration candidateInBaseClass) {
        FirModuleVisibilityChecker moduleVisibilityChecker;
        Visibility visibility = candidateInBaseClass.getStatus().getVisibility();
        if (Intrinsics.areEqual(visibility, Visibilities.Internal.INSTANCE)) {
            return Intrinsics.areEqual(candidateInBaseClass.getModuleData(), derivedClassModuleData) || ((moduleVisibilityChecker = FirVisibilityCheckerKt.getModuleVisibilityChecker(derivedClassModuleData.getSession())) != null && moduleVisibilityChecker.isInFriendModule(candidateInBaseClass));
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Private.INSTANCE) || Intrinsics.areEqual(visibility, Visibilities.PrivateToThis.INSTANCE)) {
            return false;
        }
        if (Intrinsics.areEqual(visibility, Visibilities.Protected.INSTANCE)) {
            return true;
        }
        return platformOverrideVisibilityCheck(packageNameOfDerivedClass, candidateInBaseClass.getSymbol(), candidateInBaseClass.getStatus().getVisibility());
    }

    public static /* synthetic */ boolean isVisible$default(FirVisibilityChecker firVisibilityChecker, FirMemberDeclaration firMemberDeclaration, FirSession firSession, FirFile firFile, List list, FirExpression firExpression, boolean z, FirRegularClass firRegularClass, boolean z2, SupertypeSupplier supertypeSupplier, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: isVisible");
            return false;
        }
        if ((i & 32) != 0) {
            z = false;
        }
        if ((i & 64) != 0) {
            firRegularClass = null;
        }
        if ((i & 128) != 0) {
            z2 = false;
        }
        if ((i & 256) != 0) {
            supertypeSupplier = SupertypeSupplier.Default.INSTANCE;
        }
        return firVisibilityChecker.isVisible(firMemberDeclaration, firSession, firFile, list, firExpression, z, firRegularClass, z2, supertypeSupplier);
    }

    private final boolean isVisibleForOverriding(FirModuleData derivedClassModuleData, FqName packageNameOfDerivedClass, FirCallableDeclaration candidateInBaseClass) {
        while (ClassMembersKt.isSubstitutionOrIntersectionOverride(candidateInBaseClass)) {
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(candidateInBaseClass) || (candidateInBaseClass.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(candidateInBaseClass) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(candidateInBaseClass) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(candidateInBaseClass) : null;
            }
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            candidateInBaseClass = originalForSubstitutionOverrideAttr;
        }
        return isSpecificDeclarationVisibleForOverriding(derivedClassModuleData, packageNameOfDerivedClass, candidateInBaseClass);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final ConeClassLikeLookupTag ownerIfCompanion(ConeClassLikeLookupTag coneClassLikeLookupTag, FirSession firSession) {
        FirRegularClass firRegularClass;
        ClassId outerClassId = coneClassLikeLookupTag.getClassId().getOuterClassId();
        if (outerClassId == null) {
            return null;
        }
        FirClassLikeSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(coneClassLikeLookupTag, firSession);
        if (symbol != null && ((FirClassLikeDeclaration) symbol.getFir()).getIsLocal()) {
            return null;
        }
        FirRegularClassSymbol firRegularClassSymbol = symbol instanceof FirRegularClassSymbol ? (FirRegularClassSymbol) symbol : null;
        if (firRegularClassSymbol == null || (firRegularClass = (FirRegularClass) firRegularClassSymbol.getFir()) == null || !firRegularClass.getStatus().isCompanion()) {
            return null;
        }
        return TypeConstructionUtilsKt.toLookupTag(outerClassId);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final boolean canSeeProtectedMemberOf(FirBasedSymbol<?> usedSymbol, List<? extends FirDeclaration> containingDeclarationOfUseSite, FirExpression dispatchReceiver, ConeClassLikeLookupTag ownerLookupTag, FirSession session, boolean isVariableOrNamedFunction, boolean isSyntheticProperty, SupertypeSupplier supertypeSupplier) {
        boolean z;
        SupertypeSupplier supertypeSupplier2;
        usedSymbol.getClass();
        containingDeclarationOfUseSite.getClass();
        ownerLookupTag.getClass();
        session.getClass();
        supertypeSupplier.getClass();
        FirExpression firExpression = dispatchReceiver;
        FirSession firSession = session;
        if (canSeePrivateMemberOf(usedSymbol, containingDeclarationOfUseSite, ownerLookupTag, firExpression, isVariableOrNamedFunction, firSession)) {
            return true;
        }
        for (FirDeclaration firDeclaration : containingDeclarationOfUseSite) {
            if (firDeclaration instanceof FirClass) {
                FirExpression firExpression2 = firExpression;
                FirSession firSession2 = firSession;
                boolean z2 = isSyntheticProperty;
                supertypeSupplier2 = supertypeSupplier;
                boolean zCanSeeProtectedMemberOf = canSeeProtectedMemberOf((FirClass) ((FirClass) firDeclaration).getSymbol().getFir(), firExpression2, ownerLookupTag, firSession2, isVariableOrNamedFunction, z2, supertypeSupplier2);
                firExpression = firExpression2;
                z = z2;
                firSession = firSession2;
                if (zCanSeeProtectedMemberOf) {
                    return true;
                }
            } else {
                z = isSyntheticProperty;
                supertypeSupplier2 = supertypeSupplier;
                if ((firDeclaration instanceof FirFile) && z && Intrinsics.areEqual(UtilsKt.getPackageFqName((FirFile) firDeclaration), ownerLookupTag.getClassId().getPackageFqName())) {
                    return true;
                }
            }
            supertypeSupplier = supertypeSupplier2;
            isSyntheticProperty = z;
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public Composed createComposed(List<? extends FirVisibilityChecker> components) {
        components.getClass();
        return new Composed(components);
    }

    public final boolean isClassLikeVisible(FirClassLikeDeclaration declaration, FirSession session, FirFile useSiteFile, List<? extends FirDeclaration> containingDeclarations) {
        declaration.getClass();
        session.getClass();
        useSiteFile.getClass();
        containingDeclarations.getClass();
        return isVisible(declaration, session, useSiteFile, containingDeclarations, null, false, null, false, SupertypeSupplier.Default.INSTANCE);
    }

    public final boolean isVisible(FirMemberDeclaration declaration, FirSession session, FirFile useSiteFile, List<? extends FirDeclaration> containingDeclarations, FirExpression dispatchReceiver, boolean isCallToPropertySetter, FirRegularClass staticQualifierClassForCallable, boolean skipCheckForContainingClassVisibility, SupertypeSupplier supertypeSupplier) {
        FirMemberDeclaration firMemberDeclaration;
        declaration.getClass();
        session.getClass();
        useSiteFile.getClass();
        containingDeclarations.getClass();
        supertypeSupplier.getClass();
        if (declaration instanceof FirCallableDeclaration) {
            FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) declaration;
            while (ClassMembersKt.isSubstitutionOrIntersectionOverride(firCallableDeclaration)) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            firMemberDeclaration = firCallableDeclaration;
        } else {
            firMemberDeclaration = declaration;
        }
        if (!isSpecificDeclarationVisible(firMemberDeclaration, session, useSiteFile, containingDeclarations, dispatchReceiver, isCallToPropertySetter, supertypeSupplier)) {
            return false;
        }
        if (skipCheckForContainingClassVisibility) {
            return true;
        }
        if (staticQualifierClassForCallable != null) {
            return isSpecificDeclarationVisible(staticQualifierClassForCallable, session, useSiteFile, containingDeclarations, null, isCallToPropertySetter, supertypeSupplier);
        }
        SupertypeSupplier supertypeSupplier2 = supertypeSupplier;
        Sequence<FirClassLikeDeclaration> sequenceParentDeclarationSequence = FirVisibilityCheckerKt.parentDeclarationSequence(declaration, session, dispatchReceiver, containingDeclarations, supertypeSupplier2);
        if (sequenceParentDeclarationSequence != null) {
            Iterator it = sequenceParentDeclarationSequence.iterator();
            while (it.hasNext()) {
                if (!isSpecificDeclarationVisible((FirClassLikeDeclaration) it.next(), session, useSiteFile, containingDeclarations, null, isCallToPropertySetter, supertypeSupplier2)) {
                    return false;
                }
                supertypeSupplier2 = supertypeSupplier;
            }
        }
        return true;
    }

    public abstract boolean platformOverrideVisibilityCheck(FqName packageNameOfDerivedClass, FirBasedSymbol<?> symbolInBaseClass, Visibility visibilityInBaseClass);

    public abstract boolean platformVisibilityCheck(Visibility declarationVisibility, FirBasedSymbol<?> symbol, FirFile useSiteFile, List<? extends FirDeclaration> containingDeclarations, FirExpression dispatchReceiver, FirSession session, boolean isCallToPropertySetter, SupertypeSupplier supertypeSupplier);

    @Override // org.jetbrains.kotlin.fir.FirComposableSessionComponent
    @SessionConfiguration
    public /* bridge */ /* synthetic */ FirComposableSessionComponent.Composed createComposed(List list) {
        return createComposed((List<? extends FirVisibilityChecker>) list);
    }

    public final boolean isVisibleForOverriding(FirModuleData derivedClassModuleData, FirClassSymbol<?> symbolFromDerivedClass, FirCallableDeclaration candidateInBaseClass) {
        derivedClassModuleData.getClass();
        symbolFromDerivedClass.getClass();
        candidateInBaseClass.getClass();
        return isVisibleForOverriding(derivedClassModuleData, symbolFromDerivedClass.getClassId().getPackageFqName(), candidateInBaseClass);
    }

    public final boolean isVisibleForOverriding(FirCallableDeclaration candidateInDerivedClass, FirCallableDeclaration candidateInBaseClass) {
        candidateInDerivedClass.getClass();
        candidateInBaseClass.getClass();
        return isVisibleForOverriding(candidateInDerivedClass.getModuleData(), CallableIdKt.getPackageName(candidateInDerivedClass.getSymbol().getCallableId()), candidateInBaseClass);
    }

    private final ConeClassLikeLookupTag ownerIfCompanion(FirExpression firExpression, FirSession firSession) {
        ConeClassLikeLookupTag lookupTag;
        ConeKotlinType resolvedType;
        ConeRigidType coneRigidTypeLowerBoundIfFlexible = (firExpression == null || (resolvedType = FirTypeUtilsKt.getResolvedType(firExpression)) == null) ? null : ConeTypeUtilsKt.lowerBoundIfFlexible(resolvedType);
        ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
        if (coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null) {
            return null;
        }
        return ownerIfCompanion(lookupTag, firSession);
    }

    private final boolean canSeeProtectedMemberOf(FirClass containingUseSiteClass, FirExpression dispatchReceiver, ConeClassLikeLookupTag ownerLookupTag, FirSession session, boolean isVariableOrNamedFunction, boolean isSyntheticProperty, SupertypeSupplier supertypeSupplier) {
        ConeClassLikeLookupTag coneClassLikeLookupTagOwnerIfCompanion;
        if (dispatchReceiver != null && (coneClassLikeLookupTagOwnerIfCompanion = ownerIfCompanion(dispatchReceiver, session)) != null && SupertypeUtilsKt.isSubclassOf$default(containingUseSiteClass, coneClassLikeLookupTagOwnerIfCompanion, session, false, supertypeSupplier, false, 16, null)) {
            return true;
        }
        if (!SupertypeUtilsKt.isSubclassOf$default(containingUseSiteClass, ownerLookupTag, session, false, supertypeSupplier, false, 16, null)) {
            return false;
        }
        if (isVariableOrNamedFunction) {
            return doesReceiverFitForProtectedVisibility(dispatchReceiver, containingUseSiteClass, ownerLookupTag, isSyntheticProperty, session);
        }
        return true;
    }
}
