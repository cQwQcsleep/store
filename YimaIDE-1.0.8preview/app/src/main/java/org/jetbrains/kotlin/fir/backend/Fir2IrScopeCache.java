package org.jetbrains.kotlin.fir.backend;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrValueParameterSymbol;
import org.jetbrains.kotlin.ir.symbols.IrVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0011\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003BY\b\u0016\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0005\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u0005\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\u0004\b\u0002\u0010\u0011J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u001c\u001a\u00020\u0006J\u0016\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0007J\u0010\u0010!\u001a\u0004\u0018\u00010\n2\u0006\u0010\"\u001a\u00020\tJ\u0016\u0010#\u001a\u00020\u001e2\u0006\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\nJ\u0010\u0010&\u001a\u0004\u0018\u00010\r2\u0006\u0010'\u001a\u00020\fJ\u0016\u0010(\u001a\u00020\u001e2\u0006\u0010'\u001a\u00020\f2\u0006\u0010)\u001a\u00020\rJ\u0010\u0010*\u001a\u0004\u0018\u00010\u00102\u0006\u0010+\u001a\u00020\u000fJ\u0016\u0010,\u001a\u00020\u001e2\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010.\u001a\u00020\u0010J\u0006\u0010/\u001a\u000200J\u0018\u00101\u001a\u00020\u00002\u0010\u00102\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030403J\u0006\u00105\u001a\u00020\u001eR\u001d\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u001d\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u001d\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrScopeCache;", Argument.Delimiters.none, "<init>", "()V", "initParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "Lorg/jetbrains/kotlin/ir/symbols/IrValueParameterSymbol;", "initVariables", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "Lorg/jetbrains/kotlin/ir/symbols/IrVariableSymbol;", "initLocalFunctions", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "initDelegatedProperties", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "Lorg/jetbrains/kotlin/ir/symbols/IrLocalDelegatedPropertySymbol;", "(Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;Ljava/util/Map;)V", "parameters", "getParameters", "()Ljava/util/Map;", "variables", "getVariables", "localFunctions", "getLocalFunctions", "delegatedProperties", "getDelegatedProperties", "getParameter", "parameter", "putParameter", Argument.Delimiters.none, "firParameter", "irParameterSymbol", "getVariable", "variable", "putVariable", "firVariable", "irVariableSymbol", "getLocalFunction", "localFunction", "putLocalFunction", "irFunctionSymbol", "getDelegatedProperty", "property", "putDelegatedProperty", "firProperty", "irPropertySymbol", "isEmpty", Argument.Delimiters.none, "cloneFilteringSymbols", "filterOutSymbols", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "clear", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrScopeCache {
    private final Map<FirProperty, IrLocalDelegatedPropertySymbol> delegatedProperties;
    private final Map<FirFunction, IrSimpleFunctionSymbol> localFunctions;
    private final Map<FirValueParameter, IrValueParameterSymbol> parameters;
    private final Map<FirVariable, IrVariableSymbol> variables;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public Fir2IrScopeCache(Map<FirValueParameter, ? extends IrValueParameterSymbol> map, Map<FirVariable, ? extends IrVariableSymbol> map2, Map<FirFunction, ? extends IrSimpleFunctionSymbol> map3, Map<FirProperty, ? extends IrLocalDelegatedPropertySymbol> map4) {
        this();
        map.getClass();
        map2.getClass();
        map3.getClass();
        map4.getClass();
        this.parameters.putAll(map);
        this.variables.putAll(map2);
        this.localFunctions.putAll(map3);
        this.delegatedProperties.putAll(map4);
    }

    public final void clear() {
        this.parameters.clear();
        this.variables.clear();
        this.localFunctions.clear();
        this.delegatedProperties.clear();
    }

    public final Fir2IrScopeCache cloneFilteringSymbols(Set<? extends FirBasedSymbol<?>> filterOutSymbols) {
        filterOutSymbols.getClass();
        return new Fir2IrScopeCache(VariousUtilsKt.filterOutSymbolsFromCache(this.parameters, filterOutSymbols), VariousUtilsKt.filterOutSymbolsFromCache(this.variables, filterOutSymbols), VariousUtilsKt.filterOutSymbolsFromCache(this.localFunctions, filterOutSymbols), VariousUtilsKt.filterOutSymbolsFromCache(this.delegatedProperties, filterOutSymbols));
    }

    public final Map<FirProperty, IrLocalDelegatedPropertySymbol> getDelegatedProperties() {
        return this.delegatedProperties;
    }

    public final IrLocalDelegatedPropertySymbol getDelegatedProperty(FirProperty property) {
        property.getClass();
        return this.delegatedProperties.get(property);
    }

    public final IrSimpleFunctionSymbol getLocalFunction(FirFunction localFunction) {
        localFunction.getClass();
        return this.localFunctions.get(localFunction);
    }

    public final Map<FirFunction, IrSimpleFunctionSymbol> getLocalFunctions() {
        return this.localFunctions;
    }

    public final IrValueParameterSymbol getParameter(FirValueParameter parameter) {
        parameter.getClass();
        return this.parameters.get(parameter);
    }

    public final Map<FirValueParameter, IrValueParameterSymbol> getParameters() {
        return this.parameters;
    }

    public final IrVariableSymbol getVariable(FirVariable variable) {
        variable.getClass();
        return this.variables.get(variable);
    }

    public final Map<FirVariable, IrVariableSymbol> getVariables() {
        return this.variables;
    }

    public final boolean isEmpty() {
        return this.parameters.isEmpty() && this.variables.isEmpty() && this.localFunctions.isEmpty() && this.delegatedProperties.isEmpty();
    }

    public final void putDelegatedProperty(FirProperty firProperty, IrLocalDelegatedPropertySymbol irPropertySymbol) {
        firProperty.getClass();
        irPropertySymbol.getClass();
        this.delegatedProperties.put(firProperty, irPropertySymbol);
    }

    public final void putLocalFunction(FirFunction localFunction, IrSimpleFunctionSymbol irFunctionSymbol) {
        localFunction.getClass();
        irFunctionSymbol.getClass();
        if (!(localFunction instanceof FirNamedFunction) || Intrinsics.areEqual(localFunction.getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
            this.localFunctions.put(localFunction, irFunctionSymbol);
        } else {
            dt1.a("Function is not local: ", UtilsKt.render(localFunction));
        }
    }

    public final void putParameter(FirValueParameter firParameter, IrValueParameterSymbol irParameterSymbol) {
        firParameter.getClass();
        irParameterSymbol.getClass();
        this.parameters.put(firParameter, irParameterSymbol);
    }

    public final void putVariable(FirVariable firVariable, IrVariableSymbol irVariableSymbol) {
        firVariable.getClass();
        irVariableSymbol.getClass();
        this.variables.put(firVariable, irVariableSymbol);
    }

    public Fir2IrScopeCache() {
        this.parameters = new LinkedHashMap();
        this.variables = new LinkedHashMap();
        this.localFunctions = new LinkedHashMap();
        this.delegatedProperties = new LinkedHashMap();
    }
}
