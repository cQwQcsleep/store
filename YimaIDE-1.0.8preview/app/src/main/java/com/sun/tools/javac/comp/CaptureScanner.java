package com.sun.tools.javac.comp;

import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeScanner;
import com.sun.tools.javac.util.List;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class CaptureScanner extends TreeScanner {
    private final JCTree tree;
    private final Set<Symbol.VarSymbol> seenVars = new HashSet();
    private final LinkedHashSet<Symbol.VarSymbol> fvs = new LinkedHashSet<>();

    public CaptureScanner(JCTree jCTree) {
        this.tree = jCTree;
    }

    public void addFreeVar(Symbol.VarSymbol varSymbol) {
        this.fvs.add(varSymbol);
    }

    public List<Symbol.VarSymbol> analyzeCaptures() {
        scan(this.tree);
        return List.from(this.fvs);
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitIdent(JCTree.JCIdent jCIdent) {
        Symbol symbol = jCIdent.sym;
        if (symbol.kind == Kinds.Kind.VAR && symbol.owner.kind == Kinds.Kind.MTH) {
            Symbol.VarSymbol varSymbol = (Symbol.VarSymbol) symbol;
            if (varSymbol.getConstValue() != null || this.seenVars.contains(varSymbol)) {
                return;
            }
            addFreeVar(varSymbol);
        }
    }

    @Override // com.sun.tools.javac.tree.TreeScanner, com.sun.tools.javac.tree.JCTree.Visitor
    public void visitVarDef(JCTree.JCVariableDecl jCVariableDecl) {
        Symbol.VarSymbol varSymbol = jCVariableDecl.sym;
        if (varSymbol.owner.kind == Kinds.Kind.MTH) {
            this.seenVars.add(varSymbol);
        }
        super.visitVarDef(jCVariableDecl);
    }
}
