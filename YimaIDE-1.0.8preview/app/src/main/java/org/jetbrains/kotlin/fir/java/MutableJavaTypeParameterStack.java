package org.jetbrains.kotlin.fir.java;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.load.java.structure.JavaTypeParameter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010(\n\u0002\u0010&\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0007J\u000e\u0010\f\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u0000J\u0013\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\n\u001a\u00020\u0006H\u0096\u0002J\u001c\u0010\u000f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00110\u0010H\u0096\u0082\u0004J\u0006\u0010\u0012\u001a\u00020\u0000R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/MutableJavaTypeParameterStack;", "Lorg/jetbrains/kotlin/fir/java/JavaTypeParameterStack;", "<init>", "()V", "typeParameterMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/load/java/structure/JavaTypeParameter;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "addParameter", Argument.Delimiters.none, "javaTypeParameter", "symbol", "addStack", "javaTypeParameterStack", "get", "iterator", Argument.Delimiters.none, Argument.Delimiters.none, "copy", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MutableJavaTypeParameterStack extends JavaTypeParameterStack {
    private final Map<JavaTypeParameter, FirTypeParameterSymbol> typeParameterMap = new LinkedHashMap();

    public final void addParameter(JavaTypeParameter javaTypeParameter, FirTypeParameterSymbol symbol) {
        javaTypeParameter.getClass();
        symbol.getClass();
        this.typeParameterMap.put(javaTypeParameter, symbol);
    }

    public final void addStack(MutableJavaTypeParameterStack javaTypeParameterStack) {
        javaTypeParameterStack.getClass();
        this.typeParameterMap.putAll(javaTypeParameterStack.typeParameterMap);
    }

    public final MutableJavaTypeParameterStack copy() {
        MutableJavaTypeParameterStack mutableJavaTypeParameterStack = new MutableJavaTypeParameterStack();
        mutableJavaTypeParameterStack.typeParameterMap.putAll(this.typeParameterMap);
        return mutableJavaTypeParameterStack;
    }

    @Override // org.jetbrains.kotlin.fir.java.JavaTypeParameterStack
    public FirTypeParameterSymbol get(JavaTypeParameter javaTypeParameter) {
        javaTypeParameter.getClass();
        return this.typeParameterMap.get(javaTypeParameter);
    }

    @Override // org.jetbrains.kotlin.fir.java.JavaTypeParameterStack, java.lang.Iterable
    public Iterator<Map.Entry<? extends JavaTypeParameter, ? extends FirTypeParameterSymbol>> iterator() {
        return this.typeParameterMap.entrySet().iterator();
    }
}
