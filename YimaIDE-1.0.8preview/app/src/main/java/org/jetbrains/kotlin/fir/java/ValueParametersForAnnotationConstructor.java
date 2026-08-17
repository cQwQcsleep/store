package org.jetbrains.kotlin.fir.java;

import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaValueParameter;
import org.jetbrains.kotlin.load.java.structure.JavaMethod;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J&\u0010\u0010\u001a\u00020\u00112\u0018\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00110\u0013H\u0086\bø\u0001\u0000R\u001d\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR(\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/ValueParametersForAnnotationConstructor;", Argument.Delimiters.none, "<init>", "()V", "valueParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaMethod;", "Lorg/jetbrains/kotlin/fir/java/declarations/FirJavaValueParameter;", "getValueParameters", "()Ljava/util/Map;", "valueParameterForValue", "Lkotlin/Pair;", "getValueParameterForValue", "()Lkotlin/Pair;", "setValueParameterForValue", "(Lkotlin/Pair;)V", "forEach", Argument.Delimiters.none, "block", "Lkotlin/Function2;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class ValueParametersForAnnotationConstructor {
    private Pair<? extends JavaMethod, FirJavaValueParameter> valueParameterForValue;
    private final Map<JavaMethod, FirJavaValueParameter> valueParameters = new LinkedHashMap();

    public final void forEach(Function2<? super JavaMethod, ? super FirJavaValueParameter, Unit> block) {
        block.getClass();
        Pair<JavaMethod, FirJavaValueParameter> valueParameterForValue = getValueParameterForValue();
        if (valueParameterForValue != null) {
            block.invoke((JavaMethod) valueParameterForValue.component1(), (FirJavaValueParameter) valueParameterForValue.component2());
        }
        for (Map.Entry<JavaMethod, FirJavaValueParameter> entry : getValueParameters().entrySet()) {
            block.invoke(entry.getKey(), entry.getValue());
        }
    }

    public final Pair<JavaMethod, FirJavaValueParameter> getValueParameterForValue() {
        return this.valueParameterForValue;
    }

    public final Map<JavaMethod, FirJavaValueParameter> getValueParameters() {
        return this.valueParameters;
    }

    public final void setValueParameterForValue(Pair<? extends JavaMethod, FirJavaValueParameter> pair) {
        this.valueParameterForValue = pair;
    }
}
