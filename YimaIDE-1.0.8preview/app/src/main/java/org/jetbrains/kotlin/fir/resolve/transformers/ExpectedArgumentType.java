package org.jetbrains.kotlin.fir.resolve.transformers;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.resolve.FirSamResolver;
import org.jetbrains.kotlin.fir.resolve.calls.candidate.Candidate;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\n\u000bB\u001f\b\u0004\u0012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007R\u001f\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u0082\u0001\u0002\f\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType;", Argument.Delimiters.none, "argumentReplacements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Ljava/util/Map;)V", "getArgumentReplacements", "()Ljava/util/Map;", "ArgumentsMap", "ExpectedType", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType$ArgumentsMap;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType$ExpectedType;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ExpectedArgumentType {
    private final Map<FirElement, FirExpression> argumentReplacements;

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000b\u0018\u00002\u00020\u0001Bu\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0003\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0003\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0014\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u000b\u0018\u00010\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R\u001d\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\t0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0013R\u0011\u0010\r\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType$ArgumentsMap;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType;", "map", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "lambdasReturnTypes", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "samConversions", "Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver$SamConversionInfo;", "argumentsWithFunctionKindConversion", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "Lorg/jetbrains/kotlin/fir/resolve/calls/candidate/Candidate$FunctionConversionDescription;", "forErrorReference", Argument.Delimiters.none, "argumentReplacements", "<init>", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;ZLjava/util/Map;)V", "getMap", "()Ljava/util/Map;", "getLambdasReturnTypes", "getSamConversions", "getArgumentsWithFunctionKindConversion", "getForErrorReference", "()Z", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ArgumentsMap extends ExpectedArgumentType {
        private final Map<FirExpression, Candidate.FunctionConversionDescription> argumentsWithFunctionKindConversion;
        private final boolean forErrorReference;
        private final Map<FirAnonymousFunction, ConeKotlinType> lambdasReturnTypes;
        private final Map<FirElement, ConeKotlinType> map;
        private final Map<FirElement, FirSamResolver.SamConversionInfo> samConversions;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ArgumentsMap(Map<FirElement, ? extends ConeKotlinType> map, Map<FirAnonymousFunction, ? extends ConeKotlinType> map2, Map<FirElement, FirSamResolver.SamConversionInfo> map3, Map<FirExpression, Candidate.FunctionConversionDescription> map4, boolean z, Map<FirElement, ? extends FirExpression> map5) {
            super(map5, null);
            map.getClass();
            map2.getClass();
            map3.getClass();
            map4.getClass();
            this.map = map;
            this.lambdasReturnTypes = map2;
            this.samConversions = map3;
            this.argumentsWithFunctionKindConversion = map4;
            this.forErrorReference = z;
        }

        public final Map<FirExpression, Candidate.FunctionConversionDescription> getArgumentsWithFunctionKindConversion() {
            return this.argumentsWithFunctionKindConversion;
        }

        public final boolean getForErrorReference() {
            return this.forErrorReference;
        }

        public final Map<FirAnonymousFunction, ConeKotlinType> getLambdasReturnTypes() {
            return this.lambdasReturnTypes;
        }

        public final Map<FirElement, ConeKotlinType> getMap() {
            return this.map;
        }

        public final Map<FirElement, FirSamResolver.SamConversionInfo> getSamConversions() {
            return this.samConversions;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0014\u0010\u0004\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType$ExpectedType;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ExpectedArgumentType;", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "argumentReplacements", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Ljava/util/Map;)V", "getType", "()Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ExpectedType extends ExpectedArgumentType {
        private final ConeKotlinType type;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ExpectedType(ConeKotlinType coneKotlinType, Map<FirElement, ? extends FirExpression> map) {
            super(map, null);
            coneKotlinType.getClass();
            this.type = coneKotlinType;
        }

        public final ConeKotlinType getType() {
            return this.type;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ExpectedArgumentType(Map<FirElement, ? extends FirExpression> map) {
        this.argumentReplacements = map;
    }

    public final Map<FirElement, FirExpression> getArgumentReplacements() {
        return this.argumentReplacements;
    }

    public /* synthetic */ ExpectedArgumentType(Map map, DefaultConstructorMarker defaultConstructorMarker) {
        this(map);
    }
}
