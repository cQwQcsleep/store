package com.sun.tools.javac.api;

import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.JavacTask;
import com.sun.source.util.ParameterNameProvider;
import com.sun.source.util.Plugin;
import com.sun.source.util.TaskListener;
import com.sun.tools.doclint.DocLint;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.code.MissingInfoHandler;
import com.sun.tools.javac.main.JavaCompiler;
import com.sun.tools.javac.model.JavacElements;
import com.sun.tools.javac.model.JavacTypes;
import com.sun.tools.javac.platform.PlatformDescription;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.List;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.ModuleHelper;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.PropagatedException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.annotation.processing.Processor;
import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;
import javax.tools.JavaFileObject;
import nbjavac.ModuleWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class BasicJavacTask extends JavacTask {
    protected Context context;
    protected Options options;
    private TaskListener taskListener;

    public BasicJavacTask(Context context, boolean z) {
        this.context = context;
        this.options = Options.instance(context);
        if (z) {
            this.context.put((Class<BasicJavacTask>) JavacTask.class, this);
        }
    }

    public static /* synthetic */ String a(Map.Entry entry) {
        return ((String) entry.getKey()) + "=" + ((String) entry.getValue());
    }

    private void initPlugin(Plugin plugin, String... strArr) {
        ModuleWrapper module = ModuleWrapper.getModule(plugin.getClass());
        if (module.isNamed() && this.options.isSet("accessInternalAPI")) {
            ModuleHelper.addExports(ModuleWrapper.getModule(getClass()), module);
        }
        plugin.init(this, strArr);
    }

    public static JavacTask instance(Context context) {
        JavacTask javacTask = (JavacTask) context.get(JavacTask.class);
        return javacTask == null ? new BasicJavacTask(context, true) : javacTask;
    }

    @Override // javax.tools.JavaCompiler.CompilationTask
    public void addModules(Iterable<String> iterable) {
        throw new IllegalStateException();
    }

    @Override // com.sun.source.util.JavacTask
    public void addTaskListener(TaskListener taskListener) {
        MultiTaskListener.instance(this.context).add(taskListener);
    }

    @Override // com.sun.source.util.JavacTask
    public Iterable<? extends Element> analyze() {
        throw new IllegalStateException();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // javax.tools.JavaCompiler.CompilationTask, java.util.concurrent.Callable
    public Boolean call() {
        throw new IllegalStateException();
    }

    @Override // com.sun.source.util.JavacTask
    public Iterable<? extends JavaFileObject> generate() {
        throw new IllegalStateException();
    }

    public Context getContext() {
        return this.context;
    }

    @Override // com.sun.source.util.JavacTask
    public Elements getElements() {
        Context context = this.context;
        if (context != null) {
            return JavacElements.instance(context);
        }
        g33.a();
        return null;
    }

    public Collection<TaskListener> getTaskListeners() {
        return MultiTaskListener.instance(this.context).getTaskListeners();
    }

    @Override // com.sun.source.util.JavacTask
    public TypeMirror getTypeMirror(Iterable<? extends Tree> iterable) {
        Tree tree = null;
        for (Tree tree2 : iterable) {
            Objects.requireNonNull(tree2);
            tree = tree2;
        }
        if (tree != null) {
            return ((JCTree) tree).type;
        }
        w01.a("empty path");
        return null;
    }

    @Override // com.sun.source.util.JavacTask
    public Types getTypes() {
        Context context = this.context;
        if (context != null) {
            return JavacTypes.instance(context);
        }
        g33.a();
        return null;
    }

    public void initDocLint(List<String> list) {
        if (list.isEmpty()) {
            return;
        }
        try {
            DocLint.newDocLint().init(this, (String[]) list.toArray(new String[list.size()]));
            JavaCompiler.instance(this.context).keepComments = true;
        } catch (IllegalStateException unused) {
            Log.instance(this.context).warning(CompilerProperties.Warnings.DoclintNotAvailable);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$ArrayArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void initPlugins(Set<List<String>> set) {
        PlatformDescription platformDescription = (PlatformDescription) this.context.get(PlatformDescription.class);
        if (platformDescription != null) {
            for (PlatformDescription.PluginInfo<Plugin> pluginInfo : platformDescription.getPlugins()) {
                java.util.List list = (java.util.List) pluginInfo.getOptions().entrySet().stream().map(new Function() { // from class: tp0
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return BasicJavacTask.a((Map.Entry) obj);
                    }
                }).collect(Collectors.toList());
                try {
                    initPlugin(pluginInfo.getPlugin(), (String[]) list.toArray(new String[list.size()]));
                } catch (RuntimeException e) {
                    throw new PropagatedException(e);
                }
            }
        }
        LinkedHashSet<List> linkedHashSet = new LinkedHashSet(set);
        ServiceLoader<Plugin> serviceLoader = JavacProcessingEnvironment.instance(this.context).getServiceLoader(Plugin.class);
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        for (Plugin plugin : serviceLoader) {
            if (plugin.autoStart()) {
                linkedHashSet2.add(plugin);
            }
            for (List list2 : linkedHashSet) {
                if (plugin.getName().equals(list2.head)) {
                    linkedHashSet.remove(list2);
                    linkedHashSet2.remove(plugin);
                    try {
                        List<A> list3 = list2.tail;
                        initPlugin(plugin, (String[]) list3.toArray(new String[list3.size()]));
                        break;
                    } catch (RuntimeException e2) {
                        throw new PropagatedException(e2);
                    }
                }
            }
        }
        Iterator it = linkedHashSet.iterator();
        while (it.hasNext()) {
            Log.instance(this.context).error(CompilerProperties.Errors.PluginNotFound((String) ((List) it.next()).head));
        }
        Iterator it2 = linkedHashSet2.iterator();
        while (it2.hasNext()) {
            try {
                initPlugin((Plugin) it2.next(), new String[0]);
            } catch (RuntimeException e3) {
                throw new PropagatedException(e3);
            }
        }
    }

    @Override // com.sun.source.util.JavacTask
    public Iterable<? extends CompilationUnitTree> parse() {
        throw new IllegalStateException();
    }

    @Override // com.sun.source.util.JavacTask
    public void removeTaskListener(TaskListener taskListener) {
        MultiTaskListener.instance(this.context).remove(taskListener);
    }

    @Override // javax.tools.JavaCompiler.CompilationTask
    public void setLocale(Locale locale) {
        throw new IllegalStateException();
    }

    @Override // com.sun.source.util.JavacTask
    public void setParameterNameProvider(ParameterNameProvider parameterNameProvider) {
        MissingInfoHandler.instance(this.context).setDelegate(parameterNameProvider);
    }

    @Override // javax.tools.JavaCompiler.CompilationTask
    public void setProcessors(Iterable<? extends Processor> iterable) {
        throw new IllegalStateException();
    }

    @Override // com.sun.source.util.JavacTask
    public void setTaskListener(TaskListener taskListener) {
        MultiTaskListener multiTaskListenerInstance = MultiTaskListener.instance(this.context);
        TaskListener taskListener2 = this.taskListener;
        if (taskListener2 != null) {
            multiTaskListenerInstance.remove(taskListener2);
        }
        if (taskListener != null) {
            multiTaskListenerInstance.add(taskListener);
        }
        this.taskListener = taskListener;
    }
}
