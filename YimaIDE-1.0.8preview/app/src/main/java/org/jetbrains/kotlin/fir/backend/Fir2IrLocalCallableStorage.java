package org.jetbrains.kotlin.fir.backend;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrValueParameterSymbol;
import org.jetbrains.kotlin.ir.symbols.IrVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u0011\u001a\u00020\nJ\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u0019J\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u0010\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\u0006\u0010 \u001a\u00020!J1\u0010\"\u001a\u0004\u0018\u0001H#\"\u0004\b\u0000\u0010#2\u0019\u0010$\u001a\u0015\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u0001H#0%¢\u0006\u0002\b&H\u0082\b¢\u0006\u0002\u0010'J\u0016\u0010(\u001a\u00020\n2\u0006\u0010)\u001a\u00020\u00152\u0006\u0010*\u001a\u00020\u0013J\u0016\u0010+\u001a\u00020\n2\u0006\u0010,\u001a\u00020\u00192\u0006\u0010-\u001a\u00020\u0017J\u0016\u0010.\u001a\u00020\n2\u0006\u0010/\u001a\u00020\u001d2\u0006\u00100\u001a\u00020\u001bJ\u0016\u00101\u001a\u00020\n2\u0006\u00102\u001a\u00020!2\u0006\u00103\u001a\u00020\u001fR\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u000b\u001a\u00020\u00048FX\u0087\u0004r\u0002\b\u0010¢\u0006\f\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrLocalCallableStorage;", Argument.Delimiters.none, "initialStack", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/backend/Fir2IrScopeCache;", "<init>", "(Ljava/util/List;)V", "cacheStack", Argument.Delimiters.none, "enterCallable", Argument.Delimiters.none, "lastCache", "getLastCache$annotations", "()V", "getLastCache", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrScopeCache;", "Lorg/jetbrains/kotlin/fir/backend/LeakedDeclarationCaches;", "leaveCallable", "getParameter", "Lorg/jetbrains/kotlin/ir/symbols/IrValueParameterSymbol;", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getVariable", "Lorg/jetbrains/kotlin/ir/symbols/IrVariableSymbol;", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "getLocalFunctionSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "localFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getDelegatedProperty", "Lorg/jetbrains/kotlin/ir/symbols/IrLocalDelegatedPropertySymbol;", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "last", "T", "getter", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "putParameter", "firParameter", "irParameterSymbol", "putVariable", "firVariable", "irVariableSymbol", "putLocalFunction", "firFunction", "irFunctionSymbol", "putDelegatedProperty", "firProperty", "irPropertySymbol", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLocalCallableStorage {
    private final List<Fir2IrScopeCache> cacheStack;

    public Fir2IrLocalCallableStorage(List<Fir2IrScopeCache> list) {
        list.getClass();
        ArrayList arrayList = new ArrayList();
        this.cacheStack = arrayList;
        arrayList.addAll(list);
    }

    @LeakedDeclarationCaches
    public static /* synthetic */ void getLastCache$annotations() {
    }

    public final void enterCallable() {
        this.cacheStack.add(new Fir2IrScopeCache());
    }

    public final IrLocalDelegatedPropertySymbol getDelegatedProperty(FirProperty property) {
        property.getClass();
        Iterator it = CollectionsKt.asReversedMutable(this.cacheStack).iterator();
        while (it.hasNext()) {
            IrLocalDelegatedPropertySymbol delegatedProperty = ((Fir2IrScopeCache) it.next()).getDelegatedProperty(property);
            if (delegatedProperty != null) {
                return delegatedProperty;
            }
        }
        return null;
    }

    public final Fir2IrScopeCache getLastCache() {
        return (Fir2IrScopeCache) CollectionsKt.last(this.cacheStack);
    }

    public final IrSimpleFunctionSymbol getLocalFunctionSymbol(FirFunction localFunction) {
        localFunction.getClass();
        Iterator it = CollectionsKt.asReversedMutable(this.cacheStack).iterator();
        while (it.hasNext()) {
            IrSimpleFunctionSymbol localFunction2 = ((Fir2IrScopeCache) it.next()).getLocalFunction(localFunction);
            if (localFunction2 != null) {
                return localFunction2;
            }
        }
        return null;
    }

    public final IrValueParameterSymbol getParameter(FirValueParameter parameter) {
        parameter.getClass();
        Iterator it = CollectionsKt.asReversedMutable(this.cacheStack).iterator();
        while (it.hasNext()) {
            IrValueParameterSymbol parameter2 = ((Fir2IrScopeCache) it.next()).getParameter(parameter);
            if (parameter2 != null) {
                return parameter2;
            }
        }
        return null;
    }

    public final IrVariableSymbol getVariable(FirVariable variable) {
        variable.getClass();
        Iterator it = CollectionsKt.asReversedMutable(this.cacheStack).iterator();
        while (it.hasNext()) {
            IrVariableSymbol variable2 = ((Fir2IrScopeCache) it.next()).getVariable(variable);
            if (variable2 != null) {
                return variable2;
            }
        }
        return null;
    }

    public final void leaveCallable() {
        ((Fir2IrScopeCache) CollectionsKt.last(this.cacheStack)).clear();
        List<Fir2IrScopeCache> list = this.cacheStack;
        list.remove(list.size() - 1);
    }

    public final void putDelegatedProperty(FirProperty firProperty, IrLocalDelegatedPropertySymbol irPropertySymbol) {
        firProperty.getClass();
        irPropertySymbol.getClass();
        ((Fir2IrScopeCache) CollectionsKt.last(this.cacheStack)).putDelegatedProperty(firProperty, irPropertySymbol);
    }

    public final void putLocalFunction(FirFunction firFunction, IrSimpleFunctionSymbol irFunctionSymbol) {
        firFunction.getClass();
        irFunctionSymbol.getClass();
        ((Fir2IrScopeCache) CollectionsKt.last(this.cacheStack)).putLocalFunction(firFunction, irFunctionSymbol);
    }

    public final void putParameter(FirValueParameter firParameter, IrValueParameterSymbol irParameterSymbol) {
        firParameter.getClass();
        irParameterSymbol.getClass();
        ((Fir2IrScopeCache) CollectionsKt.last(this.cacheStack)).putParameter(firParameter, irParameterSymbol);
    }

    public final void putVariable(FirVariable firVariable, IrVariableSymbol irVariableSymbol) {
        firVariable.getClass();
        irVariableSymbol.getClass();
        ((Fir2IrScopeCache) CollectionsKt.last(this.cacheStack)).putVariable(firVariable, irVariableSymbol);
    }
}
