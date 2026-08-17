package org.jetbrains.kotlin.fir.analysis.checkers.type;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent;
import org.jetbrains.kotlin.fir.types.FirDynamicTypeRef;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J:\u0010.\u001a\u00020\u000e\"\n\b\u0000\u0010/\u0018\u0001*\u00020\u0015*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H/01002\u0006\u0010\u000f\u001a\u0002H/2\u0006\u00102\u001a\u00020\u0012H\u0082\b¢\u0006\u0002\u00103R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckersDiagnosticComponent;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "checkers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/type/TypeCheckers;)V", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "visitTypeRef", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "visitResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "visitFunctionTypeRef", "functionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "visitIntersectionTypeRef", "intersectionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "visitImplicitTypeRef", "implicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "visitUnresolvedTypeRef", "unresolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;", "visitUserTypeRef", "userTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "visitDynamicTypeRef", "dynamicTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;", "visitErrorTypeRef", "errorTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "check", "E", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;", "context", "([Lorg/jetbrains/kotlin/fir/analysis/checkers/type/FirTypeChecker;Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TypeCheckersDiagnosticComponent extends AbstractDiagnosticCollectorComponent {
    private final TypeCheckers checkers;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MppCheckerKind.values().length];
            try {
                iArr[MppCheckerKind.Common.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MppCheckerKind.Platform.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public TypeCheckersDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, MppCheckerKind mppCheckerKind) {
        TypeCheckers commonTypeCheckers;
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        mppCheckerKind.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[mppCheckerKind.ordinal()];
        if (i == 1) {
            commonTypeCheckers = CheckersComponentKt.getCheckersComponent(firSession).getCommonTypeCheckers();
        } else {
            if (i != 2) {
                bu8.a();
                throw null;
            }
            commonTypeCheckers = CheckersComponentKt.getCheckersComponent(firSession).getPlatformTypeCheckers();
        }
        this(firSession, pendingDiagnosticReporter, commonTypeCheckers);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: visitDynamicTypeRef, reason: avoid collision after fix types in other method */
    public void visitDynamicTypeRef2(FirDynamicTypeRef dynamicTypeRef, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        dynamicTypeRef.getClass();
        data.getClass();
        for (FirTypeChecker<FirTypeRef> firTypeChecker : this.checkers.getAllTypeRefCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firTypeChecker.check(data, getReporter(), dynamicTypeRef);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in type checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", dynamicTypeRef);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent
    /* JADX INFO: renamed from: visitElement, reason: avoid collision after fix types in other method */
    public void visitElement2(FirElement element, CheckerContext data) {
        element.getClass();
        data.getClass();
        if (element instanceof FirTypeRef) {
            i37.a(Reflection.getOrCreateKotlinClass(element.getClass()).getSimpleName(), " should call parent checkers inside ", Reflection.getOrCreateKotlinClass(TypeCheckersDiagnosticComponent.class).getSimpleName());
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitErrorTypeRef, reason: avoid collision after fix types in other method */
    public void visitErrorTypeRef2(FirErrorTypeRef errorTypeRef, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        errorTypeRef.getClass();
        data.getClass();
        for (FirTypeChecker<FirResolvedTypeRef> firTypeChecker : this.checkers.getAllResolvedTypeRefCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firTypeChecker.check(data, getReporter(), errorTypeRef);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in type checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", errorTypeRef);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitFunctionTypeRef, reason: avoid collision after fix types in other method */
    public void visitFunctionTypeRef2(FirFunctionTypeRef functionTypeRef, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        functionTypeRef.getClass();
        data.getClass();
        for (FirTypeChecker<FirFunctionTypeRef> firTypeChecker : this.checkers.getAllFunctionTypeRefCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firTypeChecker.check(data, getReporter(), functionTypeRef);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in type checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", functionTypeRef);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitImplicitTypeRef, reason: avoid collision after fix types in other method */
    public void visitImplicitTypeRef2(FirImplicitTypeRef implicitTypeRef, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        implicitTypeRef.getClass();
        data.getClass();
        for (FirTypeChecker<FirTypeRef> firTypeChecker : this.checkers.getAllTypeRefCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firTypeChecker.check(data, getReporter(), implicitTypeRef);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in type checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", implicitTypeRef);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitIntersectionTypeRef, reason: avoid collision after fix types in other method */
    public void visitIntersectionTypeRef2(FirIntersectionTypeRef intersectionTypeRef, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        intersectionTypeRef.getClass();
        data.getClass();
        for (FirTypeChecker<FirIntersectionTypeRef> firTypeChecker : this.checkers.getAllIntersectionTypeRefCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firTypeChecker.check(data, getReporter(), intersectionTypeRef);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in type checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", intersectionTypeRef);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitResolvedTypeRef, reason: avoid collision after fix types in other method */
    public void visitResolvedTypeRef2(FirResolvedTypeRef resolvedTypeRef, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        resolvedTypeRef.getClass();
        data.getClass();
        for (FirTypeChecker<FirResolvedTypeRef> firTypeChecker : this.checkers.getAllResolvedTypeRefCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firTypeChecker.check(data, getReporter(), resolvedTypeRef);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in type checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", resolvedTypeRef);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitTypeRef, reason: avoid collision after fix types in other method */
    public void visitTypeRef2(FirTypeRef typeRef, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        typeRef.getClass();
        data.getClass();
        for (FirTypeChecker<FirTypeRef> firTypeChecker : this.checkers.getAllTypeRefCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firTypeChecker.check(data, getReporter(), typeRef);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in type checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", typeRef);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitUnresolvedTypeRef, reason: avoid collision after fix types in other method */
    public void visitUnresolvedTypeRef2(FirUnresolvedTypeRef unresolvedTypeRef, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        unresolvedTypeRef.getClass();
        data.getClass();
        for (FirTypeChecker<FirTypeRef> firTypeChecker : this.checkers.getAllTypeRefCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firTypeChecker.check(data, getReporter(), unresolvedTypeRef);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in type checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", unresolvedTypeRef);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitUserTypeRef, reason: avoid collision after fix types in other method */
    public void visitUserTypeRef2(FirUserTypeRef userTypeRef, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        userTypeRef.getClass();
        data.getClass();
        for (FirTypeChecker<FirTypeRef> firTypeChecker : this.checkers.getAllTypeRefCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firTypeChecker.check(data, getReporter(), userTypeRef);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in type checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", userTypeRef);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, CheckerContext checkerContext) {
        visitElement2(firElement, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public TypeCheckersDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, TypeCheckers typeCheckers) {
        super(firSession, pendingDiagnosticReporter);
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        typeCheckers.getClass();
        this.checkers = typeCheckers;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitDynamicTypeRef(FirDynamicTypeRef firDynamicTypeRef, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitDynamicTypeRef2(firDynamicTypeRef, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorTypeRef(FirErrorTypeRef firErrorTypeRef, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitErrorTypeRef2(firErrorTypeRef, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitFunctionTypeRef(FirFunctionTypeRef firFunctionTypeRef, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitFunctionTypeRef2(firFunctionTypeRef, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitImplicitTypeRef(FirImplicitTypeRef firImplicitTypeRef, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitImplicitTypeRef2(firImplicitTypeRef, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitIntersectionTypeRef(FirIntersectionTypeRef firIntersectionTypeRef, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitIntersectionTypeRef2(firIntersectionTypeRef, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitResolvedTypeRef(FirResolvedTypeRef firResolvedTypeRef, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitResolvedTypeRef2(firResolvedTypeRef, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitTypeRef(FirTypeRef firTypeRef, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitTypeRef2(firTypeRef, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitUnresolvedTypeRef(FirUnresolvedTypeRef firUnresolvedTypeRef, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitUnresolvedTypeRef2(firUnresolvedTypeRef, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitUserTypeRef(FirUserTypeRef firUserTypeRef, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitUserTypeRef2(firUserTypeRef, checkerContext);
        return Unit.INSTANCE;
    }
}
