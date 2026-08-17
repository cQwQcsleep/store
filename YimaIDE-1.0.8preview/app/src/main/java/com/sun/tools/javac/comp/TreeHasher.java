package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.jvm.PoolConstant;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;
import com.sun.tools.javac.tree.TreeScanner;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class TreeHasher extends TreeScanner {
    private int result = 17;
    private final Map<Symbol, Integer> symbolHashes;
    private final Types types;

    public TreeHasher(Types types, Map<Symbol, Integer> map) {
        Objects.requireNonNull(map);
        this.symbolHashes = map;
        this.types = types;
    }

    public static int hash(Types types, JCTree jCTree, Collection<? extends Symbol> collection) {
        if (jCTree == null) {
            return 0;
        }
        final HashMap map = new HashMap();
        collection.forEach(new Consumer() { // from class: cne
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                Map map2 = map;
                map2.put((Symbol) obj, Integer.valueOf(map2.size()));
            }
        });
        TreeHasher treeHasher = new TreeHasher(types, map);
        jCTree.accept(treeHasher);
        return treeHasher.result;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void hashSymbol(Symbol symbol) {
        if (symbol instanceof PoolConstant.Dynamic) {
            hash(((PoolConstant.Dynamic) symbol).bsmKey(this.types));
        } else {
            hash(symbol);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner
    public void scan(JCTree jCTree) {
        Object objConstValue;
        if (jCTree == null) {
            return;
        }
        JCTree jCTreeSkipParens = TreeInfo.skipParens(jCTree);
        Type type = jCTreeSkipParens.type;
        if (type != null && (objConstValue = type.constValue()) != null) {
            hash(objConstValue);
        } else {
            hash(jCTreeSkipParens.getTag());
            jCTreeSkipParens.accept(this);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitClassDef(JCTree.JCClassDecl jCClassDecl) {
        hash(jCClassDecl.sym);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        Integer num;
        Symbol symbol = jCIdent.sym;
        if (symbol == null || (num = this.symbolHashes.get(symbol)) == null) {
            hashSymbol(symbol);
        } else {
            hash(num);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitLiteral(JCTree.JCLiteral jCLiteral) {
        hash(jCLiteral.value);
        super.visitLiteral(jCLiteral);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitSelect(JCTree.JCFieldAccess jCFieldAccess) {
        hashSymbol(jCFieldAccess.sym);
        super.visitSelect(jCFieldAccess);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        this.symbolHashes.computeIfAbsent(jCVariableDecl.sym, new Function() { // from class: dne
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return Integer.valueOf(this.b.symbolHashes.size());
            }
        });
        super.visitVarDef(jCVariableDecl);
    }

    private void hash(Object obj) {
        this.result = (this.result * 31) + Objects.hashCode(obj);
    }
}
