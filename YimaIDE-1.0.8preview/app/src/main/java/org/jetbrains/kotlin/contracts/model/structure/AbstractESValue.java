package org.jetbrains.kotlin.contracts.model.structure;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\b&\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001a\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/structure/AbstractESValue;", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "<init>", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESType;)V", "getType", "()Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "effects", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "getEffects", "()Ljava/util/List;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractESValue implements ESValue {
    private final List<ESEffect> effects = CollectionsKt.emptyList();
    private final ESType type;

    public AbstractESValue(ESType eSType) {
        this.type = eSType;
    }

    @Override // org.jetbrains.kotlin.contracts.model.Computation
    public List<ESEffect> getEffects() {
        return this.effects;
    }

    @Override // org.jetbrains.kotlin.contracts.model.Computation
    public ESType getType() {
        return this.type;
    }
}
