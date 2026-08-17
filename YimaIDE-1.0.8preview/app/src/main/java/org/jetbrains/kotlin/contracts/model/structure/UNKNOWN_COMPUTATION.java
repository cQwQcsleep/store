package org.jetbrains.kotlin.contracts.model.structure;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.Computation;
import org.jetbrains.kotlin.contracts.model.ESEffect;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/UNKNOWN_COMPUTATION;", "Lorg/jetbrains/kotlin/contracts/model/Computation;", "<init>", "()V", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "getType", "()Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "effects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "getEffects", "()Ljava/util/List;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UNKNOWN_COMPUTATION implements Computation {
    public static final UNKNOWN_COMPUTATION INSTANCE = new UNKNOWN_COMPUTATION();
    private static final List<ESEffect> effects = CollectionsKt.emptyList();
    private static final ESType type = null;

    private UNKNOWN_COMPUTATION() {
    }

    @Override // org.jetbrains.kotlin.contracts.model.Computation
    public List<ESEffect> getEffects() {
        return effects;
    }

    @Override // org.jetbrains.kotlin.contracts.model.Computation
    public ESType getType() {
        return type;
    }
}
