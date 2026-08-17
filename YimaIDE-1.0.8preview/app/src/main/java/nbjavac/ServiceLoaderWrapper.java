package nbjavac;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Spliterator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import javax.tools.ToolProvider;
import nbjavac.ServiceLoaderWrapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ServiceLoaderWrapper<T> implements Iterable<T> {
    private final ServiceLoader<T> loader;

    public interface Provider<T> {
        T get();

        Class<? extends T> type();
    }

    public ServiceLoaderWrapper(ServiceLoader<T> serviceLoader) {
        this.loader = serviceLoader;
    }

    public static /* synthetic */ Provider a(ServiceLoaderWrapper serviceLoaderWrapper, final Object obj) {
        serviceLoaderWrapper.getClass();
        return new Provider<T>(serviceLoaderWrapper) { // from class: nbjavac.ServiceLoaderWrapper.1
            final /* synthetic */ ServiceLoaderWrapper this$0;

            {
                this.this$0 = serviceLoaderWrapper;
            }

            @Override // nbjavac.ServiceLoaderWrapper.Provider
            public T get() {
                return (T) obj;
            }

            @Override // nbjavac.ServiceLoaderWrapper.Provider
            public Class<? extends T> type() {
                return (Class<? extends T>) obj.getClass();
            }
        };
    }

    public static <T> ServiceLoaderWrapper<T> load(Class<T> cls) {
        ModuleWrapper.ensureUses(cls);
        return new ServiceLoaderWrapper<>(ServiceLoader.load(cls));
    }

    public static <T> ServiceLoader<T> loadTool(Class<T> cls) {
        ModuleWrapper.ensureUses(cls);
        ServiceLoader<T> serviceLoaderLoad = ServiceLoader.load(cls, ToolProvider.class.getClassLoader());
        return serviceLoaderLoad.iterator().hasNext() ? serviceLoaderLoad : ServiceLoader.load(cls, ClassLoader.getSystemClassLoader());
    }

    public static <T> ServiceLoader<T> loadWithClassLoader(Class<T> cls, ClassLoader classLoader) {
        ModuleWrapper.ensureUses(cls);
        return ServiceLoader.load(cls, classLoader);
    }

    @Override // java.lang.Iterable
    public void forEach(Consumer<? super T> consumer) {
        this.loader.forEach(consumer);
    }

    @Override // java.lang.Iterable
    public Iterator<T> iterator() {
        return this.loader.iterator();
    }

    @Override // java.lang.Iterable
    public Spliterator<T> spliterator() {
        return this.loader.spliterator();
    }

    public Stream<Provider<T>> stream() {
        return StreamSupport.stream(this.loader.spliterator(), false).map(new Function() { // from class: d8d
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ServiceLoaderWrapper.a(this.b, obj);
            }
        });
    }

    public static <T> ServiceLoader<T> load(ModuleWrapper.ModuleLayer moduleLayer, Class<T> cls) {
        ModuleWrapper.ensureUses(cls);
        return ServiceLoader.load(cls);
    }

    public static <T> ServiceLoaderWrapper<T> load(Class<T> cls, ClassLoader classLoader) {
        ModuleWrapper.ensureUses(cls);
        return new ServiceLoaderWrapper<>(ServiceLoader.load(cls, classLoader));
    }
}
