package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

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
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirErrorProperty;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u0002002\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u0002032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u00104\u001a\u00020\u000e2\u0006\u00105\u001a\u0002062\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u0002092\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020<2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010=\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020?2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010@\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020B2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020E2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010F\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020H2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010I\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020K2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020N2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010O\u001a\u00020\u000e2\u0006\u0010P\u001a\u00020Q2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020T2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010U\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020W2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010X\u001a\u00020\u000e2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J:\u0010[\u001a\u00020\u000e\"\n\b\u0000\u0010\\\u0018\u0001*\u00020\u0015*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\\0^0]2\u0006\u0010\u000f\u001a\u0002H\\2\u0006\u0010_\u001a\u00020\u0012H\u0082\b¢\u0006\u0002\u0010`R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckersDiagnosticComponent;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "checkers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/DeclarationCheckers;)V", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "visitDeclaration", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "visitFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "visitScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "visitReplSnippet", "replSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "visitTypeParameter", "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "visitAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "visitPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "visitBackingField", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "visitValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "visitEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visitAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "visitReceiverParameter", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "visitDanglingModifierList", "danglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "visitCodeFragment", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "visitField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "visitErrorProperty", "errorProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;", "visitErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "check", "E", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "context", "([Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeclarationCheckersDiagnosticComponent extends AbstractDiagnosticCollectorComponent {
    private final DeclarationCheckers checkers;

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
    public DeclarationCheckersDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, MppCheckerKind mppCheckerKind) {
        DeclarationCheckers commonDeclarationCheckers;
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        mppCheckerKind.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[mppCheckerKind.ordinal()];
        if (i == 1) {
            commonDeclarationCheckers = CheckersComponentKt.getCheckersComponent(firSession).getCommonDeclarationCheckers();
        } else {
            if (i != 2) {
                bu8.a();
                throw null;
            }
            commonDeclarationCheckers = CheckersComponentKt.getCheckersComponent(firSession).getPlatformDeclarationCheckers();
        }
        this(firSession, pendingDiagnosticReporter, commonDeclarationCheckers);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitAnonymousFunction, reason: avoid collision after fix types in other method */
    public void visitAnonymousFunction2(FirAnonymousFunction anonymousFunction, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        anonymousFunction.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirAnonymousFunction> firDeclarationChecker : this.checkers.getAllAnonymousFunctionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), anonymousFunction);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", anonymousFunction);
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
    /* JADX INFO: renamed from: visitAnonymousInitializer, reason: avoid collision after fix types in other method */
    public void visitAnonymousInitializer2(FirAnonymousInitializer anonymousInitializer, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        anonymousInitializer.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirAnonymousInitializer> firDeclarationChecker : this.checkers.getAllAnonymousInitializerCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), anonymousInitializer);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", anonymousInitializer);
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
    /* JADX INFO: renamed from: visitAnonymousObject, reason: avoid collision after fix types in other method */
    public void visitAnonymousObject2(FirAnonymousObject anonymousObject, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        anonymousObject.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirAnonymousObject> firDeclarationChecker : this.checkers.getAllAnonymousObjectCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), anonymousObject);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", anonymousObject);
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
    /* JADX INFO: renamed from: visitBackingField, reason: avoid collision after fix types in other method */
    public void visitBackingField2(FirBackingField backingField, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        backingField.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirBackingField> firDeclarationChecker : this.checkers.getAllBackingFieldCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), backingField);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", backingField);
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
    /* JADX INFO: renamed from: visitClass, reason: avoid collision after fix types in other method */
    public void visitClass2(FirClass klass, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        klass.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirClass> firDeclarationChecker : this.checkers.getAllClassCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), klass);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", klass);
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
    /* JADX INFO: renamed from: visitCodeFragment, reason: avoid collision after fix types in other method */
    public void visitCodeFragment2(FirCodeFragment codeFragment, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        codeFragment.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirDeclaration> firDeclarationChecker : this.checkers.getAllBasicDeclarationCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), codeFragment);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", codeFragment);
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
    /* JADX INFO: renamed from: visitConstructor, reason: avoid collision after fix types in other method */
    public void visitConstructor2(FirConstructor constructor, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        constructor.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirConstructor> firDeclarationChecker : this.checkers.getAllConstructorCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), constructor);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", constructor);
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
    /* JADX INFO: renamed from: visitDanglingModifierList, reason: avoid collision after fix types in other method */
    public void visitDanglingModifierList2(FirDanglingModifierList danglingModifierList, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        danglingModifierList.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirDeclaration> firDeclarationChecker : this.checkers.getAllBasicDeclarationCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), danglingModifierList);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", danglingModifierList);
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
    /* JADX INFO: renamed from: visitDeclaration, reason: avoid collision after fix types in other method */
    public void visitDeclaration2(FirDeclaration declaration, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        declaration.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirDeclaration> firDeclarationChecker : this.checkers.getAllBasicDeclarationCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), declaration);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", declaration);
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
        if (element instanceof FirDeclaration) {
            i37.a(Reflection.getOrCreateKotlinClass(element.getClass()).getSimpleName(), " should call parent checkers inside ", Reflection.getOrCreateKotlinClass(DeclarationCheckersDiagnosticComponent.class).getSimpleName());
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitEnumEntry, reason: avoid collision after fix types in other method */
    public void visitEnumEntry2(FirEnumEntry enumEntry, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        enumEntry.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirEnumEntry> firDeclarationChecker : this.checkers.getAllEnumEntryCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), enumEntry);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", enumEntry);
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
    /* JADX INFO: renamed from: visitErrorPrimaryConstructor, reason: avoid collision after fix types in other method */
    public void visitErrorPrimaryConstructor2(FirErrorPrimaryConstructor errorPrimaryConstructor, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        errorPrimaryConstructor.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirConstructor> firDeclarationChecker : this.checkers.getAllConstructorCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), errorPrimaryConstructor);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", errorPrimaryConstructor);
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
    /* JADX INFO: renamed from: visitErrorProperty, reason: avoid collision after fix types in other method */
    public void visitErrorProperty2(FirErrorProperty errorProperty, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        errorProperty.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirCallableDeclaration> firDeclarationChecker : this.checkers.getAllCallableDeclarationCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), errorProperty);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", errorProperty);
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
    /* JADX INFO: renamed from: visitField, reason: avoid collision after fix types in other method */
    public void visitField2(FirField field, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        field.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirCallableDeclaration> firDeclarationChecker : this.checkers.getAllCallableDeclarationCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), field);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", field);
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
    /* JADX INFO: renamed from: visitFile, reason: avoid collision after fix types in other method */
    public void visitFile2(FirFile file, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        file.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirFile> firDeclarationChecker : this.checkers.getAllFileCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), file);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", file);
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
    /* JADX INFO: renamed from: visitNamedFunction, reason: avoid collision after fix types in other method */
    public void visitNamedFunction2(FirNamedFunction namedFunction, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        namedFunction.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirNamedFunction> firDeclarationChecker : this.checkers.getAllSimpleFunctionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), namedFunction);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", namedFunction);
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
    /* JADX INFO: renamed from: visitProperty, reason: avoid collision after fix types in other method */
    public void visitProperty2(FirProperty property, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        property.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirProperty> firDeclarationChecker : this.checkers.getAllPropertyCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), property);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", property);
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
    /* JADX INFO: renamed from: visitPropertyAccessor, reason: avoid collision after fix types in other method */
    public void visitPropertyAccessor2(FirPropertyAccessor propertyAccessor, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        propertyAccessor.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirPropertyAccessor> firDeclarationChecker : this.checkers.getAllPropertyAccessorCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), propertyAccessor);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", propertyAccessor);
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
    /* JADX INFO: renamed from: visitReceiverParameter, reason: avoid collision after fix types in other method */
    public void visitReceiverParameter2(FirReceiverParameter receiverParameter, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        receiverParameter.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirReceiverParameter> firDeclarationChecker : this.checkers.getAllReceiverParameterCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), receiverParameter);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", receiverParameter);
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
    /* JADX INFO: renamed from: visitRegularClass, reason: avoid collision after fix types in other method */
    public void visitRegularClass2(FirRegularClass regularClass, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        regularClass.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirRegularClass> firDeclarationChecker : this.checkers.getAllRegularClassCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), regularClass);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", regularClass);
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
    /* JADX INFO: renamed from: visitReplSnippet, reason: avoid collision after fix types in other method */
    public void visitReplSnippet2(FirReplSnippet replSnippet, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        replSnippet.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirReplSnippet> firDeclarationChecker : this.checkers.getAllReplSnippetCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), replSnippet);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", replSnippet);
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
    /* JADX INFO: renamed from: visitScript, reason: avoid collision after fix types in other method */
    public void visitScript2(FirScript script, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        script.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirScript> firDeclarationChecker : this.checkers.getAllScriptCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), script);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", script);
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
    /* JADX INFO: renamed from: visitTypeAlias, reason: avoid collision after fix types in other method */
    public void visitTypeAlias2(FirTypeAlias typeAlias, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        typeAlias.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirTypeAlias> firDeclarationChecker : this.checkers.getAllTypeAliasCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), typeAlias);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", typeAlias);
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
    /* JADX INFO: renamed from: visitTypeParameter, reason: avoid collision after fix types in other method */
    public void visitTypeParameter2(FirTypeParameter typeParameter, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        typeParameter.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirTypeParameter> firDeclarationChecker : this.checkers.getAllTypeParameterCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), typeParameter);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", typeParameter);
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
    /* JADX INFO: renamed from: visitValueParameter, reason: avoid collision after fix types in other method */
    public void visitValueParameter2(FirValueParameter valueParameter, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        valueParameter.getClass();
        data.getClass();
        for (FirDeclarationChecker<FirValueParameter> firDeclarationChecker : this.checkers.getAllValueParameterCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firDeclarationChecker.check(data, getReporter(), valueParameter);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in declaration checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", valueParameter);
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
    public DeclarationCheckersDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, DeclarationCheckers declarationCheckers) {
        super(firSession, pendingDiagnosticReporter);
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        declarationCheckers.getClass();
        this.checkers = declarationCheckers;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitAnonymousFunction(FirAnonymousFunction firAnonymousFunction, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitAnonymousFunction2(firAnonymousFunction, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitAnonymousInitializer(FirAnonymousInitializer firAnonymousInitializer, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitAnonymousInitializer2(firAnonymousInitializer, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitAnonymousObject(FirAnonymousObject firAnonymousObject, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitAnonymousObject2(firAnonymousObject, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitBackingField(FirBackingField firBackingField, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitBackingField2(firBackingField, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitClass(FirClass firClass, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitClass2(firClass, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitCodeFragment(FirCodeFragment firCodeFragment, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitCodeFragment2(firCodeFragment, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitConstructor(FirConstructor firConstructor, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitConstructor2(firConstructor, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitDanglingModifierList(FirDanglingModifierList firDanglingModifierList, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitDanglingModifierList2(firDanglingModifierList, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitDeclaration(FirDeclaration firDeclaration, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitDeclaration2(firDeclaration, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitEnumEntry(FirEnumEntry firEnumEntry, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitEnumEntry2(firEnumEntry, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorPrimaryConstructor(FirErrorPrimaryConstructor firErrorPrimaryConstructor, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitErrorPrimaryConstructor2(firErrorPrimaryConstructor, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorProperty(FirErrorProperty firErrorProperty, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitErrorProperty2(firErrorProperty, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitField(FirField firField, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitField2(firField, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitFile(FirFile firFile, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitFile2(firFile, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitNamedFunction(FirNamedFunction firNamedFunction, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitNamedFunction2(firNamedFunction, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitProperty(FirProperty firProperty, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitProperty2(firProperty, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitPropertyAccessor(FirPropertyAccessor firPropertyAccessor, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitPropertyAccessor2(firPropertyAccessor, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitReceiverParameter(FirReceiverParameter firReceiverParameter, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitReceiverParameter2(firReceiverParameter, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitRegularClass(FirRegularClass firRegularClass, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitRegularClass2(firRegularClass, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitReplSnippet(FirReplSnippet firReplSnippet, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitReplSnippet2(firReplSnippet, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitScript(FirScript firScript, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitScript2(firScript, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitTypeAlias(FirTypeAlias firTypeAlias, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitTypeAlias2(firTypeAlias, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitTypeParameter(FirTypeParameter firTypeParameter, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitTypeParameter2(firTypeParameter, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitValueParameter(FirValueParameter firValueParameter, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitValueParameter2(firValueParameter, checkerContext);
        return Unit.INSTANCE;
    }
}
