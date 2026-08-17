package org.jetbrains.kotlin.contracts.model.functors;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.AbstractFunctor;
import org.jetbrains.kotlin.contracts.model.Computation;
import org.jetbrains.kotlin.contracts.model.ConditionalEffect;
import org.jetbrains.kotlin.contracts.model.ESEffect;
import org.jetbrains.kotlin.contracts.model.ESTypeSubstitution;
import org.jetbrains.kotlin.contracts.model.ESValue;
import org.jetbrains.kotlin.contracts.model.structure.ESConstants;
import org.jetbrains.kotlin.contracts.model.structure.ESIs;
import org.jetbrains.kotlin.contracts.model.structure.ESKotlinType;
import org.jetbrains.kotlin.contracts.model.structure.ESReturns;
import org.jetbrains.kotlin.contracts.model.structure.ESType;
import org.jetbrains.kotlin.contracts.model.visitors.Reducer;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J,\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\f2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\r0\f2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u001e\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\f2\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0011H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0004\u0010\n¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/contracts/model/functors/IsFunctor;", "Lorg/jetbrains/kotlin/contracts/model/AbstractFunctor;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "isNegated", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/contracts/model/structure/ESType;Z)V", "getType", "()Lorg/jetbrains/kotlin/contracts/model/structure/ESType;", "()Z", "doInvocation", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/contracts/model/ESEffect;", "arguments", "Lorg/jetbrains/kotlin/contracts/model/Computation;", "typeSubstitution", "Lorg/jetbrains/kotlin/contracts/model/ESTypeSubstitution;", "reducer", "Lorg/jetbrains/kotlin/contracts/model/visitors/Reducer;", "invokeWithArguments", "arg", "invokeWithValue", "Lorg/jetbrains/kotlin/contracts/model/ConditionalEffect;", "value", "Lorg/jetbrains/kotlin/contracts/model/ESValue;", "org.jetbrains.kotlin:resolution"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class IsFunctor extends AbstractFunctor {
    private final boolean isNegated;
    private final ESType type;

    public IsFunctor(ESType eSType, boolean z) {
        eSType.getClass();
        this.type = eSType;
        this.isNegated = z;
    }

    private final List<ConditionalEffect> invokeWithValue(ESValue value, ESTypeSubstitution typeSubstitution) {
        ESKotlinType eSKotlinType = new ESKotlinType(typeSubstitution.getSubstitutor().safeSubstitute(this.type.toKotlinType(typeSubstitution.getBuiltIns()).unwrap()));
        ESIs eSIs = new ESIs(value, new IsFunctor(eSKotlinType, this.isNegated));
        ESIs eSIs2 = new ESIs(value, new IsFunctor(eSKotlinType, !this.isNegated));
        ESConstants eSConstants = ESConstants.INSTANCE;
        return CollectionsKt.listOf(new ConditionalEffect[]{new ConditionalEffect(eSIs, new ESReturns(eSConstants.getTrueValue())), new ConditionalEffect(eSIs2, new ESReturns(eSConstants.getFalseValue()))});
    }

    @Override // org.jetbrains.kotlin.contracts.model.AbstractFunctor
    public List<ESEffect> doInvocation(List<? extends Computation> arguments, ESTypeSubstitution typeSubstitution, Reducer reducer) {
        arguments.getClass();
        typeSubstitution.getClass();
        reducer.getClass();
        arguments.size();
        return invokeWithArguments(arguments.get(0), typeSubstitution);
    }

    public final ESType getType() {
        return this.type;
    }

    public final List<ESEffect> invokeWithArguments(Computation arg, ESTypeSubstitution typeSubstitution) {
        arg.getClass();
        typeSubstitution.getClass();
        return arg instanceof ESValue ? invokeWithValue((ESValue) arg, typeSubstitution) : CollectionsKt.emptyList();
    }

    /* JADX INFO: renamed from: isNegated, reason: from getter */
    public final boolean getIsNegated() {
        return this.isNegated;
    }
}
