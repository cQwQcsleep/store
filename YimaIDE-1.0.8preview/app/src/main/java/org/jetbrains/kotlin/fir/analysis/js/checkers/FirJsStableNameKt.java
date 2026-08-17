package org.jetbrains.kotlin.fir.analysis.js.checkers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\u001a1\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0000R\u00020\u0003j\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0007¨\u0006\b"}, d2 = {"collectNameClashesWith", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", Argument.Delimiters.none, ModuleXmlParser.NAME, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/analysis/js/checkers/FirJsStableName;)Ljava/util/List;", "org.jetbrains.kotlin:checkers.js"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJsStableNameKt {
    public static final List<FirJsStableName> collectNameClashesWith(CheckerContext checkerContext, Collection<FirJsStableName> collection, FirJsStableName firJsStableName) {
        checkerContext.getClass();
        collection.getClass();
        firJsStableName.getClass();
        ArrayList arrayList = new ArrayList();
        for (FirJsStableName firJsStableName2 : collection) {
            if (!firJsStableName2.clashesWith(checkerContext, firJsStableName)) {
                firJsStableName2 = null;
            }
            if (firJsStableName2 != null) {
                arrayList.add(firJsStableName2);
            }
        }
        return arrayList;
    }
}
