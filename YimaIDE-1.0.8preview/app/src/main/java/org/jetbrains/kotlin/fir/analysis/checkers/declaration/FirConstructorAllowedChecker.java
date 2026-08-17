package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import com.intellij.psi.tree.IElementType;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirKeywordUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifier;
import org.jetbrains.kotlin.fir.analysis.checkers.FirModifierList;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.lexer.KtModifierKeywordToken;
import org.jetbrains.kotlin.lexer.KtTokens;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirConstructorAllowedChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirConstructorChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirConstructorAllowedChecker extends FirDeclarationChecker<FirConstructor> {
    public static final FirConstructorAllowedChecker INSTANCE = new FirConstructorAllowedChecker();

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.OBJECT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ClassKind.INTERFACE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[ClassKind.ENUM_ENTRY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[ClassKind.ENUM_CLASS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[ClassKind.CLASS.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[ClassKind.ANNOTATION_CLASS.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private FirConstructorAllowedChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirConstructor firConstructor) {
        FirModifierList modifierList;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firConstructor.getClass();
        Object objLastOrNull = CollectionsKt.lastOrNull(checkerContext.getContainingDeclarations());
        FirClassSymbol firClassSymbol = objLastOrNull instanceof FirClassSymbol ? (FirClassSymbol) objLastOrNull : null;
        if (firClassSymbol == null) {
            return;
        }
        KtSourceElement source = firConstructor.getSource();
        IElementType elementType = source != null ? source.getElementType() : null;
        if (Intrinsics.areEqual(elementType, KtNodeTypes.PRIMARY_CONSTRUCTOR) || Intrinsics.areEqual(elementType, KtNodeTypes.SECONDARY_CONSTRUCTOR)) {
            switch (WhenMappings.$EnumSwitchMapping$0[firClassSymbol.getClassKind().ordinal()]) {
                case 1:
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getCONSTRUCTOR_IN_OBJECT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    break;
                case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getCONSTRUCTOR_IN_INTERFACE(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    break;
                case 3:
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getCONSTRUCTOR_IN_OBJECT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    break;
                case 4:
                    if (!Intrinsics.areEqual(firConstructor.getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getNON_PRIVATE_CONSTRUCTOR_IN_ENUM(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                    break;
                case 5:
                    if (firClassSymbol instanceof FirAnonymousObjectSymbol) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getCONSTRUCTOR_IN_OBJECT(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        break;
                    } else if (!(firClassSymbol instanceof FirRegularClassSymbol)) {
                        bu8.a();
                        break;
                    } else if (firClassSymbol.getResolvedStatus().getModality() == Modality.SEALED && (modifierList = FirKeywordUtilsKt.getModifierList(source)) != null) {
                        List<FirModifier<?>> modifiers = modifierList.getModifiers();
                        if (!(modifiers instanceof Collection) || !modifiers.isEmpty()) {
                            Iterator<T> it = modifiers.iterator();
                            while (it.hasNext()) {
                                KtModifierKeywordToken token = ((FirModifier) it.next()).getToken();
                                if (KtTokens.VISIBILITY_MODIFIERS.contains(token) && !Intrinsics.areEqual(token, KtTokens.PROTECTED_KEYWORD) && !Intrinsics.areEqual(token, KtTokens.PRIVATE_KEYWORD)) {
                                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getNON_PRIVATE_OR_PROTECTED_CONSTRUCTOR_IN_SEALED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                    break;
                case 6:
                    break;
                default:
                    bu8.a();
                    break;
            }
        }
    }
}
