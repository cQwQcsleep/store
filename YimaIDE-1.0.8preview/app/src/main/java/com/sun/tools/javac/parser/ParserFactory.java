package com.sun.tools.javac.parser;

import com.sun.tools.javac.api.JavacTrees;
import com.sun.tools.javac.code.Preview;
import com.sun.tools.javac.code.Source;
import com.sun.tools.javac.tree.DocTreeMaker;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Names;
import com.sun.tools.javac.util.Options;
import java.util.Locale;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ParserFactory {
    protected static final Context.Key<ParserFactory> parserFactoryKey = new Context.Key<>();
    final TreeMaker F;
    final DocTreeMaker docTreeMaker;
    final Locale locale;
    final Log log;
    final Names names;
    final Options options;
    final Preview preview;
    final ScannerFactory scannerFactory;
    final Source source;
    final Tokens tokens;
    private final JavacTrees trees;

    public ParserFactory(Context context) {
        context.put(parserFactoryKey, this);
        this.F = TreeMaker.instance(context);
        this.docTreeMaker = DocTreeMaker.instance(context);
        this.log = Log.instance(context);
        this.names = Names.instance(context);
        this.tokens = Tokens.instance(context);
        this.source = Source.instance(context);
        this.preview = Preview.instance(context);
        this.options = Options.instance(context);
        this.scannerFactory = ScannerFactory.instance(context);
        this.locale = (Locale) context.get(Locale.class);
        this.trees = JavacTrees.instance(context);
    }

    public static ParserFactory instance(Context context) {
        ParserFactory parserFactory = (ParserFactory) context.get(parserFactoryKey);
        return parserFactory == null ? new ParserFactory(context) : parserFactory;
    }

    public JavacTrees getTrees() {
        return this.trees;
    }

    public JavacParser newParser(CharSequence charSequence, boolean z, boolean z2, boolean z3, boolean z4) {
        return new JavacParser(this, this.scannerFactory.newScanner(charSequence, z), z, z3, z2, z4);
    }

    public JavacParser newParser(CharSequence charSequence, boolean z, boolean z2, boolean z3) {
        return newParser(charSequence, z, z2, z3, false);
    }
}
