package org.jetbrains.kotlin.fir.pipeline;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u001a\u0014\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0005"}, d2 = {"referenceAllCommonDependencies", Argument.Delimiters.none, "outputs", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", "org.jetbrains.kotlin:entrypoint"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReferenceAllCommonDependenciesKt {
    public static final void referenceAllCommonDependencies(List<SingleModuleFrontendOutput> list) {
        list.getClass();
        FirSession session = ((SingleModuleFrontendOutput) CollectionsKt.last(list)).getSession();
        if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(session).getFlag(AnalysisFlags.INSTANCE.getHierarchicalMultiplatformCompilation())).booleanValue()) {
            FirVisitorVoid visitor = new Visitor(session);
            Iterator it = CollectionsKt.dropLast(list, 1).iterator();
            while (it.hasNext()) {
                Iterator<FirFile> it2 = ((SingleModuleFrontendOutput) it.next()).component3().iterator();
                while (it2.hasNext()) {
                    it2.next().accept(visitor);
                }
            }
        }
    }
}
