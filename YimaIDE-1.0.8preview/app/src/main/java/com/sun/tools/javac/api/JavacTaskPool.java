package com.sun.tools.javac.api;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.source.util.TreeScanner;
import com.sun.tools.javac.api.JavacTaskPool;
import com.sun.tools.javac.code.Kinds;
import com.sun.tools.javac.code.LintMapper;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symtab;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.TypeTag;
import com.sun.tools.javac.code.Types;
import com.sun.tools.javac.comp.Annotate;
import com.sun.tools.javac.comp.Check;
import com.sun.tools.javac.comp.CompileStates;
import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.comp.Modules;
import com.sun.tools.javac.main.Arguments;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.platform.PlatformDescription;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import defpackage.ln7;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardLocation;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacTaskPool {
    private int id;
    private final int maxPoolSize;
    private static final JavacTool systemProvider = JavacTool.create();
    private static final Queue<ReusableContext> EMPTY_QUEUE = new ArrayDeque(0);
    private final Map<List<String>, Queue<ReusableContext>> options2Contexts = new HashMap();
    private int statReused = 0;
    private int statNew = 0;
    private int statPolluted = 0;
    private int statRemoved = 0;

    public interface Worker<Z> {
        Z withTask(JavacTask javacTask);
    }

    public JavacTaskPool(int i) {
        this.maxPoolSize = i;
    }

    public static /* synthetic */ Queue a(List list) {
        return new ArrayDeque();
    }

    public static /* synthetic */ int b(ReusableContext reusableContext, ReusableContext reusableContext2) {
        return reusableContext.timeStamp < reusableContext2.timeStamp ? -1 : 1;
    }

    private long cacheSize() {
        return this.options2Contexts.values().stream().flatMap(new ln7()).count();
    }

    public <Z> Z getTask(Writer writer, JavaFileManager javaFileManager, DiagnosticListener<? super JavaFileObject> diagnosticListener, Iterable<String> iterable, Iterable<String> iterable2, Iterable<? extends JavaFileObject> iterable3, Worker<Z> worker) {
        ReusableContext reusableContextRemove;
        ReusableContext reusableContext;
        List list = (List) StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toCollection(new vef()));
        synchronized (this) {
            try {
                Queue<ReusableContext> orDefault = this.options2Contexts.getOrDefault(list, EMPTY_QUEUE);
                if (orDefault.isEmpty()) {
                    reusableContextRemove = new ReusableContext(list);
                    this.statNew++;
                } else {
                    reusableContextRemove = orDefault.remove();
                    this.statReused++;
                }
                reusableContext = reusableContextRemove;
            } catch (Throwable th) {
                throw th;
            }
        }
        reusableContext.useCount++;
        JavacTaskImpl javacTaskImpl = (JavacTaskImpl) systemProvider.getTask(writer, javaFileManager, diagnosticListener, list, iterable2, iterable3, reusableContext);
        javacTaskImpl.addTaskListener(reusableContext);
        if (writer != null) {
            Log.instance(reusableContext).setWriters(new PrintWriter(writer, true));
        }
        Z zWithTask = worker.withTask(javacTaskImpl);
        reusableContext.clear();
        if (reusableContext.polluted) {
            this.statPolluted++;
            return zWithTask;
        }
        javacTaskImpl.cleanup();
        synchronized (this) {
            while (true) {
                try {
                    long jCacheSize = cacheSize() + 1;
                    long j = this.maxPoolSize;
                    Map<List<String>, Queue<ReusableContext>> map = this.options2Contexts;
                    if (jCacheSize > j) {
                        ReusableContext reusableContext2 = (ReusableContext) map.values().stream().flatMap(new ln7()).sorted(new Comparator() { // from class: com.sun.tools.javac.api.a
                            @Override // java.util.Comparator
                            public final int compare(Object obj, Object obj2) {
                                return JavacTaskPool.b((JavacTaskPool.ReusableContext) obj, (JavacTaskPool.ReusableContext) obj2);
                            }
                        }).findFirst().get();
                        this.options2Contexts.get(reusableContext2.arguments).remove(reusableContext2);
                        this.statRemoved++;
                    } else {
                        map.computeIfAbsent(reusableContext.arguments, new Function() { // from class: mn7
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return JavacTaskPool.a((List) obj);
                            }
                        }).add(reusableContext);
                        int i = this.id;
                        this.id = i + 1;
                        reusableContext.timeStamp = i;
                    }
                } catch (Throwable th2) {
                    throw th2;
                }
            }
        }
        return zWithTask;
    }

    public void printStatistics(PrintStream printStream) {
        printStream.println(this.statReused + " reused Contexts");
        printStream.println(this.statNew + " newly created Contexts");
        printStream.println(this.statPolluted + " polluted Contexts");
        printStream.println(this.statRemoved + " removed Contexts");
    }

    public static class ReusableContext extends Context implements TaskListener {
        List<String> arguments;
        long timeStamp;
        int useCount;
        Set<CompilationUnitTree> roots = new HashSet();
        boolean polluted = false;
        TreeScanner<Void, Symtab> pollutionScanner = new TreeScanner<Void, Symtab>() { // from class: com.sun.tools.javac.api.JavacTaskPool.ReusableContext.1
            private boolean isCoreClass(Symbol symbol) {
                return symbol.flatName().toString().startsWith("java.");
            }

            private Type supertype(Symbol symbol) {
                Type type = symbol.type;
                if (type == null || !type.hasTag(TypeTag.CLASS)) {
                    return null;
                }
                return ((Type.ClassType) symbol.type).supertype_field;
            }

            @Override // com.sun.source.util.TreeScanner
            public Void scan(Tree tree, Symtab symtab) {
                if (!(tree instanceof JCTree.LetExpr)) {
                    return (Void) super.scan(tree, symtab);
                }
                JCTree.LetExpr letExpr = (JCTree.LetExpr) tree;
                scan(letExpr.defs, symtab);
                scan((Tree) letExpr.expr, symtab);
                return null;
            }

            @Override // com.sun.source.util.TreeScanner, com.sun.source.tree.TreeVisitor
            public Void visitClass(ClassTree classTree, Symtab symtab) {
                Symbol.ClassSymbol classSymbol = ((JCTree.JCClassDecl) classTree).sym;
                if (classSymbol != null) {
                    symtab.removeClass(classSymbol.packge().modle, classSymbol.flatName());
                    Type typeSupertype = supertype(classSymbol);
                    if (isCoreClass(classSymbol) || (typeSupertype != null && isCoreClass(typeSupertype.tsym) && typeSupertype.tsym.kind != Kinds.Kind.TYP)) {
                        ReusableContext.this.polluted = true;
                    }
                }
                return (Void) super.visitClass(classTree, symtab);
            }
        };

        public static class ReusableJavaCompiler extends JavaCompiler {
            static final Context.Factory<JavaCompiler> factory = new Context.Factory() { // from class: com.sun.tools.javac.api.b
                @Override // com.sun.tools.javac.util.Context.Factory
                public final Object make(Context context) {
                    return new JavacTaskPool.ReusableContext.ReusableJavaCompiler(context);
                }
            };

            public ReusableJavaCompiler(Context context) {
                super(context);
            }

            @Override // com.sun.tools.javac.main.JavaCompiler
            public void checkReusable() {
            }

            public void clear() {
                newRound();
            }

            @Override // com.sun.tools.javac.main.JavaCompiler
            public void close() {
            }
        }

        public static class ReusableLog extends Log {
            static final Context.Factory<Log> factory = new Context.Factory() { // from class: com.sun.tools.javac.api.c
                @Override // com.sun.tools.javac.util.Context.Factory
                public final Object make(Context context) {
                    return new JavacTaskPool.ReusableContext.ReusableLog(context);
                }
            };
            Context context;

            public ReusableLog(Context context) {
                super(context);
                this.context = context;
            }

            @Override // com.sun.tools.javac.util.Log
            public void clear() {
                super.clear();
                this.diagListener = new DiagnosticListener<JavaFileObject>() { // from class: com.sun.tools.javac.api.JavacTaskPool.ReusableContext.ReusableLog.1
                    DiagnosticListener<JavaFileObject> cachedListener;

                    public void report(Diagnostic<? extends JavaFileObject> diagnostic) {
                        if (this.cachedListener == null) {
                            this.cachedListener = (DiagnosticListener) ReusableLog.this.context.get(DiagnosticListener.class);
                        }
                        this.cachedListener.report(diagnostic);
                    }
                };
            }
        }

        public ReusableContext(List<String> list) {
            this.arguments = list;
            put((Context.Key) Log.logKey, (Context.Factory) ReusableLog.factory);
            put((Context.Key) JavaCompiler.compilerKey, (Context.Factory) ReusableJavaCompiler.factory);
        }

        public void clear() {
            this.polluted |= ((JavaFileManager) get(JavaFileManager.class)).hasLocation(StandardLocation.PATCH_MODULE_PATH);
            drop(Arguments.argsKey);
            drop(DiagnosticListener.class);
            drop(Log.outKey);
            drop(Log.errKey);
            drop(JavaFileManager.class);
            drop(JavacTask.class);
            drop(JavacTrees.class);
            drop(JavacElements.class);
            drop(PlatformDescription.class);
            if (this.ht.get(Log.logKey) instanceof ReusableLog) {
                Log.instance(this).clear();
                LintMapper.instance(this).clear();
                Enter.instance(this).newRound();
                ((ReusableJavaCompiler) JavaCompiler.instance(this)).clear();
                Types.instance(this).newRound();
                Check.instance(this).newRound();
                Modules.instance(this).newRound();
                Annotate.instance(this).newRound();
                CompileStates.instance(this).clear();
                MultiTaskListener.instance(this).clear();
                Options.instance(this).clear();
                this.pollutionScanner.scan(this.roots, Symtab.instance(this));
                this.roots.clear();
            }
        }

        public <T> void drop(Class<T> cls) {
            this.ht.remove(key(cls));
        }

        @Override // com.sun.source.util.TaskListener
        public void finished(TaskEvent taskEvent) {
            if (taskEvent.getKind() == TaskEvent.Kind.PARSE) {
                this.roots.add(taskEvent.getCompilationUnit());
            }
        }

        @Override // com.sun.source.util.TaskListener
        public void started(TaskEvent taskEvent) {
        }

        public <T> void drop(Context.Key<T> key) {
            this.ht.remove(key);
        }
    }
}
