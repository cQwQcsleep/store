package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.util.Context;
import java.util.Collection;
import java.util.HashMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TypeEnvs {
    private static final long serialVersionUID = 571524752489954631L;
    protected static final Context.Key<TypeEnvs> typeEnvsKey = new Context.Key<>();
    private HashMap<Symbol.TypeSymbol, Env<AttrContext>> map = new HashMap<>();

    public TypeEnvs(Context context) {
        context.put(typeEnvsKey, this);
    }

    public static TypeEnvs instance(Context context) {
        TypeEnvs typeEnvs = (TypeEnvs) context.get(typeEnvsKey);
        return typeEnvs == null ? new TypeEnvs(context) : typeEnvs;
    }

    public void clear() {
        this.map.clear();
    }

    public Env<AttrContext> get(Symbol.TypeSymbol typeSymbol) {
        return this.map.get(typeSymbol);
    }

    public Env<AttrContext> put(Symbol.TypeSymbol typeSymbol, Env<AttrContext> env) {
        return this.map.put(typeSymbol, env);
    }

    public Env<AttrContext> remove(Symbol.TypeSymbol typeSymbol) {
        return this.map.remove(typeSymbol);
    }

    public Collection<Env<AttrContext>> values() {
        return this.map.values();
    }
}
