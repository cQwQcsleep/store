package org.jetbrains.kotlin.fir.analysis.js.checkers.declaration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.js.FirJsErrors;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsStableName;
import org.jetbrains.kotlin.fir.analysis.js.checkers.FirJsStableNameKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertyAccessorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J;\u0010\n\u001a\u00020\u000b*\u0014\u0012\u0004\u0012\u00020\u000f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000e2\n\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u0013H\u0002R\u00020\fj\u0006\u0010\r\u001a\u00020\f¢\u0006\u0002\u0010\u0014J-\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0018\u001a\u00020\u0002H\u0016R\u00020\fR\u00020\u0016j\u0006\u0010\r\u001a\u00020\fj\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0002\u0010\u0019R\u0014\u0010\u0006\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\t¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/js/checkers/declaration/FirJsNameClashFileTopLevelDeclarationsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFileChecker;", "<init>", "()V", "platformSpecificCheckerEnabledInMetadataCompilation", Argument.Delimiters.none, "getPlatformSpecificCheckerEnabledInMetadataCompilation", "()Z", "addStableName", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/Map;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)V", "check", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFile;)V", "org.jetbrains.kotlin:checkers.js"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsNameClashFileTopLevelDeclarationsChecker extends FirDeclarationChecker<FirFile> {
    public static final FirJsNameClashFileTopLevelDeclarationsChecker INSTANCE = new FirJsNameClashFileTopLevelDeclarationsChecker();

    private FirJsNameClashFileTopLevelDeclarationsChecker() {
        super(MppCheckerKind.Common);
    }

    private final void addStableName(CheckerContext checkerContext, Map<String, List<FirJsStableName>> map, FirBasedSymbol<?> firBasedSymbol) {
        FirJsStableName firJsStableNameCreateStableNameOrNull = FirJsStableName.INSTANCE.createStableNameOrNull(checkerContext, firBasedSymbol);
        if (firJsStableNameCreateStableNameOrNull != null) {
            String name = firJsStableNameCreateStableNameOrNull.getName();
            List<FirJsStableName> arrayList = map.get(name);
            if (arrayList == null) {
                arrayList = new ArrayList<>();
                map.put(name, arrayList);
            }
            arrayList.add(firJsStableNameCreateStableNameOrNull);
        }
        if (firBasedSymbol instanceof FirPropertySymbol) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firBasedSymbol;
            FirPropertyAccessorSymbol getterSymbol = firPropertySymbol.getGetterSymbol();
            if (getterSymbol != null) {
                INSTANCE.addStableName(checkerContext, map, getterSymbol);
            }
            FirPropertyAccessorSymbol setterSymbol = firPropertySymbol.getSetterSymbol();
            if (setterSymbol != null) {
                INSTANCE.addStableName(checkerContext, map, setterSymbol);
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFile firFile) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFile.getClass();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        Iterator<FirDeclaration> it = firFile.getDeclarations().iterator();
        while (it.hasNext()) {
            addStableName(checkerContext, linkedHashMap, it.next().getSymbol());
        }
        for (Map.Entry<String, List<FirJsStableName>> entry : linkedHashMap.entrySet()) {
            String key = entry.getKey();
            List<FirJsStableName> value = entry.getValue();
            for (FirJsStableName firJsStableName : value) {
                List<FirJsStableName> listCollectNameClashesWith = FirJsStableNameKt.collectNameClashesWith(checkerContext, value, firJsStableName);
                if (listCollectNameClashesWith.isEmpty()) {
                    listCollectNameClashesWith = null;
                }
                if (listCollectNameClashesWith != null) {
                    KtSourceElement source = firJsStableName.getSymbol().getSource();
                    if (source == null) {
                        source = firFile.getSource();
                    }
                    KtSourceElement ktSourceElement = source;
                    KtDiagnosticFactory2<String, Collection<FirBasedSymbol<?>>> js_name_clash = FirJsErrors.INSTANCE.getJS_NAME_CLASH();
                    List<FirJsStableName> list = listCollectNameClashesWith;
                    ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator<T> it2 = list.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(((FirJsStableName) it2.next()).getSymbol());
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory2) js_name_clash, (Object) key, (Object) arrayList, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind
    public boolean getPlatformSpecificCheckerEnabledInMetadataCompilation() {
        return true;
    }
}
