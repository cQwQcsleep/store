package com.sun.tools.javac.code;

import com.sun.tools.javac.util.Context;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class DeferredCompletionFailureHandler {
    protected static final Context.Key<DeferredCompletionFailureHandler> deferredCompletionFailureHandlerKey = new Context.Key<>();
    private Handler handler;
    public final Handler javacCodeHandler;
    public final Handler userCodeHandler = new AnonymousClass1();
    public final Handler speculativeCodeHandler = new AnonymousClass2();

    /* JADX INFO: renamed from: com.sun.tools.javac.code.DeferredCompletionFailureHandler$1, reason: invalid class name */
    public class AnonymousClass1 implements Handler {
        private final Map<Symbol.ClassSymbol, FlipSymbolDescription> class2Flip = new HashMap();

        public AnonymousClass1() {
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void classSymbolCompleteFailed(Symbol.ClassSymbol classSymbol, Symbol.Completer completer) {
            this.class2Flip.put(classSymbol, new FlipSymbolDescription(classSymbol, new DeferredCompleter(completer) { // from class: com.sun.tools.javac.code.DeferredCompletionFailureHandler.1.1
                {
                    DeferredCompletionFailureHandler deferredCompletionFailureHandler = DeferredCompletionFailureHandler.this;
                }

                @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.DeferredCompleter, com.sun.tools.javac.code.Symbol.Completer
                public void complete(Symbol symbol) throws Symbol.CompletionFailure {
                    AnonymousClass1.this.class2Flip.remove(symbol);
                    super.complete(symbol);
                }
            }));
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void classSymbolRemoved(Symbol.ClassSymbol classSymbol) {
            this.class2Flip.remove(classSymbol);
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void handleAPICompletionFailure(Symbol.CompletionFailure completionFailure) {
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void install() {
            this.class2Flip.values().forEach(new Consumer() { // from class: com.sun.tools.javac.code.a
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((DeferredCompletionFailureHandler.FlipSymbolDescription) obj).flip();
                }
            });
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void uninstall() {
            this.class2Flip.values().forEach(new Consumer() { // from class: com.sun.tools.javac.code.b
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((DeferredCompletionFailureHandler.FlipSymbolDescription) obj).flip();
                }
            });
        }
    }

    /* JADX INFO: renamed from: com.sun.tools.javac.code.DeferredCompletionFailureHandler$2, reason: invalid class name */
    public class AnonymousClass2 implements Handler {
        private final Map<Symbol.ClassSymbol, FlipSymbolDescription> class2Flip = new HashMap();

        public AnonymousClass2() {
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void classSymbolCompleteFailed(Symbol.ClassSymbol classSymbol, Symbol.Completer completer) {
            this.class2Flip.put(classSymbol, new FlipSymbolDescription(classSymbol, DeferredCompletionFailureHandler.this.new DeferredCompleter(completer)));
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void classSymbolRemoved(Symbol.ClassSymbol classSymbol) {
            this.class2Flip.remove(classSymbol);
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void handleAPICompletionFailure(Symbol.CompletionFailure completionFailure) {
            throw completionFailure;
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void install() {
        }

        @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
        public void uninstall() {
            this.class2Flip.values().forEach(new Consumer() { // from class: com.sun.tools.javac.code.c
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    ((DeferredCompletionFailureHandler.FlipSymbolDescription) obj).flip();
                }
            });
            this.class2Flip.clear();
        }
    }

    public class DeferredCompleter implements Symbol.Completer {
        private final Symbol.Completer origCompleter;

        public DeferredCompleter(Symbol.Completer completer) {
            this.origCompleter = completer;
        }

        @Override // com.sun.tools.javac.code.Symbol.Completer
        public void complete(Symbol symbol) throws Symbol.CompletionFailure {
            this.origCompleter.complete(symbol);
        }
    }

    public static class FlipSymbolDescription {
        public Symbol.Completer completer;
        public Kinds.Kind kind;
        public Scope.WriteableScope members = null;
        public final Symbol.ClassSymbol sym;
        public Type type;

        public FlipSymbolDescription(Symbol.ClassSymbol classSymbol, Symbol.Completer completer) {
            this.sym = classSymbol;
            this.type = classSymbol.type;
            this.kind = classSymbol.kind;
            this.completer = completer;
        }

        public void flip() {
            Symbol.ClassSymbol classSymbol = this.sym;
            Type type = classSymbol.type;
            classSymbol.type = this.type;
            this.type = type;
            Kinds.Kind kind = classSymbol.kind;
            classSymbol.kind = this.kind;
            this.kind = kind;
            Symbol.Completer completer = classSymbol.completer;
            classSymbol.completer = this.completer;
            this.completer = completer;
            Scope.WriteableScope writeableScope = classSymbol.members_field;
            classSymbol.members_field = this.members;
            this.members = writeableScope;
        }
    }

    public interface Handler {
        void classSymbolCompleteFailed(Symbol.ClassSymbol classSymbol, Symbol.Completer completer);

        void classSymbolRemoved(Symbol.ClassSymbol classSymbol);

        void handleAPICompletionFailure(Symbol.CompletionFailure completionFailure);

        void install();

        void uninstall();
    }

    public DeferredCompletionFailureHandler(Context context) {
        Handler handler = new Handler() { // from class: com.sun.tools.javac.code.DeferredCompletionFailureHandler.3
            @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
            public void classSymbolCompleteFailed(Symbol.ClassSymbol classSymbol, Symbol.Completer completer) {
            }

            @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
            public void classSymbolRemoved(Symbol.ClassSymbol classSymbol) {
            }

            @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
            public void handleAPICompletionFailure(Symbol.CompletionFailure completionFailure) {
                throw completionFailure;
            }

            @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
            public void install() {
            }

            @Override // com.sun.tools.javac.code.DeferredCompletionFailureHandler.Handler
            public void uninstall() {
            }
        };
        this.javacCodeHandler = handler;
        this.handler = handler;
        context.put(deferredCompletionFailureHandlerKey, this);
    }

    public static DeferredCompletionFailureHandler instance(Context context) {
        DeferredCompletionFailureHandler deferredCompletionFailureHandler = (DeferredCompletionFailureHandler) context.get(deferredCompletionFailureHandlerKey);
        return deferredCompletionFailureHandler == null ? new DeferredCompletionFailureHandler(context) : deferredCompletionFailureHandler;
    }

    public void classSymbolCompleteFailed(Symbol.ClassSymbol classSymbol, Symbol.Completer completer) {
        this.handler.classSymbolCompleteFailed(classSymbol, completer);
    }

    public void classSymbolRemoved(Symbol.ClassSymbol classSymbol) {
        this.handler.classSymbolRemoved(classSymbol);
    }

    public void handleAPICompletionFailure(Symbol.CompletionFailure completionFailure) {
        this.handler.handleAPICompletionFailure(completionFailure);
    }

    public boolean isDeferredCompleter(Symbol.Completer completer) {
        return completer instanceof DeferredCompleter;
    }

    public Handler setHandler(Handler handler) {
        Handler handler2 = this.handler;
        if (handler == handler2) {
            return handler2;
        }
        handler2.uninstall();
        Handler handler3 = this.handler;
        this.handler = handler;
        handler.install();
        return handler3;
    }
}
