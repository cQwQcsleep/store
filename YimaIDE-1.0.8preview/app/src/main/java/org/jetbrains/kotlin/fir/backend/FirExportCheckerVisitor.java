package org.jetbrains.kotlin.fir.backend;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import org.jetbrains.kotlin.backend.common.serialization.mangle.KotlinExportChecker;
import org.jetbrains.kotlin.backend.common.serialization.mangle.MangleUtilsKt;
import org.jetbrains.kotlin.backend.common.serialization.mangle.SpecialDeclarationType;
import org.jetbrains.kotlin.backend.common.serialization.mangle.ir.IrExportCheckerVisitorKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirAnnotationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.visitors.FirVisitor;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\b\u0012\u0004\u0012\u00020\u00050\u0004B\u0007¢\u0006\u0004\b\u0006\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0003H\u0016J\u001d\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u000fJ\u001b\u0010\u0010\u001a\u00020\u0002\"\b\b\u0000\u0010\u0011*\u00020\u0012*\u0002H\u0011H\u0002¢\u0006\u0002\u0010\u0013J\u001b\u0010\u0014\u001a\u00020\u0002\"\b\b\u0000\u0010\u0011*\u00020\u0015*\u0002H\u0011H\u0002¢\u0006\u0002\u0010\u0016J\u001b\u0010\u0014\u001a\u00020\u0002\"\b\b\u0000\u0010\u0011*\u00020\u0017*\u0002H\u0011H\u0002¢\u0006\u0002\u0010\u0018J\u001d\u0010\u0019\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010 J\u001d\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010$J\u001d\u0010%\u001a\u00020\u00022\u0006\u0010&\u001a\u00020'2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010(J\u001d\u0010)\u001a\u00020\u00022\u0006\u0010*\u001a\u00020+2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0002\u0010,J\u001d\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020/2\u0006\u0010\u000e\u001a\u00020\u0003H\u0016¢\u0006\u0002\u00100¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/FirExportCheckerVisitor;", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;", "Lorg/jetbrains/kotlin/backend/common/serialization/mangle/KotlinExportChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "<init>", "()V", "check", "declaration", ModuleXmlParser.TYPE, "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;)Ljava/lang/Boolean;", "globalMemberIsExported", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;)Z", "isExported", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)Z", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;)Z", "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;)Ljava/lang/Boolean;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;)Ljava/lang/Boolean;", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "(Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;)Ljava/lang/Boolean;", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;)Ljava/lang/Boolean;", "visitAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;)Ljava/lang/Boolean;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;Lorg/jetbrains/kotlin/backend/common/serialization/mangle/SpecialDeclarationType;)Ljava/lang/Boolean;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirExportCheckerVisitor extends FirVisitor<Boolean, SpecialDeclarationType> implements KotlinExportChecker<FirDeclaration> {
    private final <D extends FirMemberDeclaration> boolean globalMemberIsExported(D d) {
        Visibility visibility = d.getStatus().getVisibility();
        if (!visibility.getIsPublicAPI() && visibility != Visibilities.Internal.INSTANCE) {
            if (visibility == Visibilities.Local.INSTANCE) {
                return false;
            }
            if (!FirAnnotationUtilsKt.hasAnnotation(d.getAnnotations(), ClassId.Companion.topLevel(MangleUtilsKt.getPublishedApiAnnotation()), d.getModuleData().getSession()) && !isPlatformSpecificExported(d)) {
                return false;
            }
        }
        return true;
    }

    /* JADX WARN: Type inference failed for: r4v4, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    private final <D extends FirCallableDeclaration> boolean isExported(D d) {
        ClassId classId;
        CallableId callableId = d.getSymbol().getCallableId();
        if (callableId == null || (classId = callableId.getClassId()) == null) {
            return globalMemberIsExported(d);
        }
        if (d.getStatus().getVisibility() == Visibilities.Local.INSTANCE) {
            return false;
        }
        FirClassifierSymbol<?> symbol = ToSymbolUtilsKt.toSymbol(classId, d.getModuleData().getSession());
        symbol.getClass();
        return ((Boolean) symbol.getFir().accept(this, SpecialDeclarationType.REGULAR)).booleanValue();
    }

    public boolean check(FirDeclaration declaration, SpecialDeclarationType type) {
        declaration.getClass();
        type.getClass();
        return ((Boolean) declaration.accept(this, type)).booleanValue();
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitAnonymousFunction(FirAnonymousFunction anonymousFunction, SpecialDeclarationType data) {
        anonymousFunction.getClass();
        data.getClass();
        return Boolean.FALSE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitAnonymousObject(FirAnonymousObject anonymousObject, SpecialDeclarationType data) {
        anonymousObject.getClass();
        data.getClass();
        return Boolean.FALSE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitConstructor(FirConstructor constructor, SpecialDeclarationType data) {
        constructor.getClass();
        data.getClass();
        FirClassLikeDeclaration firClassLikeDeclarationFirClassLike = DeclarationUtilsKt.firClassLike(constructor.getReturnTypeRef(), constructor.getModuleData().getSession());
        firClassLikeDeclarationFirClassLike.getClass();
        return Boolean.valueOf(isExported(firClassLikeDeclarationFirClassLike));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitElement(FirElement element, SpecialDeclarationType data) throws NotImplementedError {
        element.getClass();
        data.getClass();
        throw new NotImplementedError("An operation is not implemented: Should have not been reached");
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitNamedFunction(FirNamedFunction namedFunction, SpecialDeclarationType data) {
        namedFunction.getClass();
        data.getClass();
        return Boolean.valueOf(!IrExportCheckerVisitorKt.isAnonymous(namedFunction.getName()) && isExported(namedFunction));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitProperty(FirProperty property, SpecialDeclarationType data) {
        property.getClass();
        data.getClass();
        return Boolean.valueOf(isExported(property));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public Boolean visitRegularClass(FirRegularClass regularClass, SpecialDeclarationType data) {
        regularClass.getClass();
        data.getClass();
        if (data != SpecialDeclarationType.ANON_INIT && !IrExportCheckerVisitorKt.isAnonymous(regularClass.getName())) {
            return Boolean.valueOf(isExported(regularClass));
        }
        return Boolean.FALSE;
    }

    private final <D extends FirClassLikeDeclaration> boolean isExported(D d) {
        FirClassLikeDeclaration containingDeclaration = DeclarationUtilsKt.getContainingDeclaration(d, d.getModuleData().getSession());
        if (containingDeclaration == null) {
            return globalMemberIsExported(d);
        }
        return d.getStatus().getVisibility() != Visibilities.Local.INSTANCE && ((Boolean) containingDeclaration.accept(this, SpecialDeclarationType.REGULAR)).booleanValue();
    }
}
