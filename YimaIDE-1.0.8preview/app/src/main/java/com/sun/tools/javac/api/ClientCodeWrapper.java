package com.sun.tools.javac.api;

import com.sun.source.util.TaskEvent;
import com.sun.source.util.TaskListener;
import com.sun.tools.javac.util.ClientCodeException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.JCDiagnostic;
import defpackage.t02;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.Set;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.NestingKind;
import javax.tools.Diagnostic;
import javax.tools.DiagnosticListener;
import javax.tools.FileObject;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ClientCodeWrapper {
    Map<Class<?>, Boolean> trustedClasses = new HashMap();

    public class DiagnosticSourceUnwrapper implements Diagnostic<JavaFileObject> {
        public final JCDiagnostic d;

        public DiagnosticSourceUnwrapper(JCDiagnostic jCDiagnostic) {
            this.d = jCDiagnostic;
        }

        @Override // javax.tools.Diagnostic
        public String getCode() {
            return this.d.getCode();
        }

        @Override // javax.tools.Diagnostic
        public long getColumnNumber() {
            return this.d.getColumnNumber();
        }

        @Override // javax.tools.Diagnostic
        public long getEndPosition() {
            return this.d.getEndPosition();
        }

        @Override // javax.tools.Diagnostic
        public Diagnostic.Kind getKind() {
            return this.d.getKind();
        }

        @Override // javax.tools.Diagnostic
        public long getLineNumber() {
            return this.d.getLineNumber();
        }

        @Override // javax.tools.Diagnostic
        public String getMessage(Locale locale) {
            return this.d.getMessage(locale);
        }

        @Override // javax.tools.Diagnostic
        public long getPosition() {
            return this.d.getPosition();
        }

        @Override // javax.tools.Diagnostic
        public JavaFileObject getSource() {
            return ClientCodeWrapper.this.unwrap(this.d.getSource());
        }

        @Override // javax.tools.Diagnostic
        public long getStartPosition() {
            return this.d.getStartPosition();
        }

        public String toString() {
            return this.d.toString();
        }
    }

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    public @interface Trusted {
    }

    public class WrappedDiagnosticListener<T> implements DiagnosticListener<T> {
        protected DiagnosticListener<T> clientDiagnosticListener;

        public WrappedDiagnosticListener(DiagnosticListener<T> diagnosticListener) {
            Objects.requireNonNull(diagnosticListener);
            this.clientDiagnosticListener = diagnosticListener;
        }

        public void report(Diagnostic<? extends T> diagnostic) {
            try {
                this.clientDiagnosticListener.report(ClientCodeWrapper.this.unwrap(diagnostic));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
            }
        }

        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientDiagnosticListener);
        }
    }

    public class WrappedFileObject implements FileObject {
        protected FileObject clientFileObject;

        public WrappedFileObject(FileObject fileObject) {
            Objects.requireNonNull(fileObject);
            this.clientFileObject = fileObject;
        }

        @Override // javax.tools.FileObject
        public boolean delete() {
            try {
                return this.clientFileObject.delete();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return false;
            }
        }

        @Override // javax.tools.FileObject
        public CharSequence getCharContent(boolean z) throws IOException {
            try {
                return this.clientFileObject.getCharContent(z);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.FileObject
        public long getLastModified() {
            try {
                return this.clientFileObject.getLastModified();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return 0L;
            }
        }

        @Override // javax.tools.FileObject
        public String getName() {
            try {
                return this.clientFileObject.getName();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.FileObject
        public InputStream openInputStream() throws IOException {
            try {
                return this.clientFileObject.openInputStream();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.FileObject
        public OutputStream openOutputStream() throws IOException {
            try {
                return this.clientFileObject.openOutputStream();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.FileObject
        public Reader openReader(boolean z) throws IOException {
            try {
                return this.clientFileObject.openReader(z);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.FileObject
        public Writer openWriter() throws IOException {
            try {
                return this.clientFileObject.openWriter();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientFileObject);
        }

        @Override // javax.tools.FileObject
        public URI toUri() {
            try {
                return this.clientFileObject.toUri();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }
    }

    public class WrappedJavaFileObject extends WrappedFileObject implements JavaFileObject {
        public WrappedJavaFileObject(JavaFileObject javaFileObject) {
            super(javaFileObject);
        }

        @Override // javax.tools.JavaFileObject
        public Modifier getAccessLevel() {
            try {
                return ((JavaFileObject) this.clientFileObject).getAccessLevel();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileObject
        public JavaFileObject.Kind getKind() {
            try {
                return ((JavaFileObject) this.clientFileObject).getKind();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileObject
        public NestingKind getNestingKind() {
            try {
                return ((JavaFileObject) this.clientFileObject).getNestingKind();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileObject
        public boolean isNameCompatible(String str, JavaFileObject.Kind kind) {
            try {
                return ((JavaFileObject) this.clientFileObject).isNameCompatible(str, kind);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return false;
            }
        }

        @Override // com.sun.tools.javac.api.ClientCodeWrapper.WrappedFileObject
        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientFileObject);
        }
    }

    public class WrappedTaskListener implements TaskListener {
        protected TaskListener clientTaskListener;

        public WrappedTaskListener(TaskListener taskListener) {
            Objects.requireNonNull(taskListener);
            this.clientTaskListener = taskListener;
        }

        @Override // com.sun.source.util.TaskListener
        public void finished(TaskEvent taskEvent) {
            try {
                this.clientTaskListener.finished(taskEvent);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
            }
        }

        @Override // com.sun.source.util.TaskListener
        public void started(TaskEvent taskEvent) {
            try {
                this.clientTaskListener.started(taskEvent);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
            }
        }

        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientTaskListener);
        }
    }

    public ClientCodeWrapper(Context context) {
    }

    public static ClientCodeWrapper instance(Context context) {
        ClientCodeWrapper clientCodeWrapper = (ClientCodeWrapper) context.get(ClientCodeWrapper.class);
        return clientCodeWrapper == null ? new ClientCodeWrapper(context) : clientCodeWrapper;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String wrappedToString(Class<?> cls, Object obj) {
        return cls.getSimpleName() + "[" + obj + "]";
    }

    public boolean isTrusted(Object obj) {
        Class<?> cls = obj.getClass();
        Boolean boolValueOf = this.trustedClasses.get(cls);
        if (boolValueOf == null) {
            boolValueOf = Boolean.valueOf(cls.getName().startsWith("com.sun.tools.javac.") || cls.isAnnotationPresent(Trusted.class));
            this.trustedClasses.put(cls, boolValueOf);
        }
        return boolValueOf.booleanValue();
    }

    public Collection<TaskListener> unwrap(Collection<? extends TaskListener> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator<? extends TaskListener> it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add(unwrap(it.next()));
        }
        return arrayList;
    }

    public JavaFileManager wrap(JavaFileManager javaFileManager) {
        if (isTrusted(javaFileManager)) {
            return javaFileManager;
        }
        return javaFileManager instanceof StandardJavaFileManager ? new WrappedStandardJavaFileManager((StandardJavaFileManager) javaFileManager) : new WrappedJavaFileManager(javaFileManager);
    }

    public Iterable<JavaFileObject> wrapJavaFileObjects(Iterable<? extends JavaFileObject> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<? extends JavaFileObject> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(wrap(it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public class WrappedStandardJavaFileManager extends WrappedJavaFileManager implements StandardJavaFileManager {
        public WrappedStandardJavaFileManager(StandardJavaFileManager standardJavaFileManager) {
            super(standardJavaFileManager);
        }

        @Override // javax.tools.StandardJavaFileManager
        public Path asPath(FileObject fileObject) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).asPath(fileObject);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjects(File... fileArr) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).getJavaFileObjects(fileArr);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromFiles(Iterable<? extends File> iterable) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).getJavaFileObjectsFromFiles(iterable);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromPaths(Collection<? extends Path> collection) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).getJavaFileObjectsFromPaths(collection);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromStrings(Iterable<String> iterable) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).getJavaFileObjectsFromStrings(iterable);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends File> getLocation(JavaFileManager.Location location) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).getLocation(location);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends Path> getLocationAsPaths(JavaFileManager.Location location) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).getLocationAsPaths(location);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public void setLocation(JavaFileManager.Location location, Iterable<? extends File> iterable) throws IOException {
            try {
                ((StandardJavaFileManager) this.clientJavaFileManager).setLocation(location, iterable);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public void setLocationForModule(JavaFileManager.Location location, String str, Collection<? extends Path> collection) throws IOException {
            try {
                System.out.println("invoking wrapped setLocationForModule");
                ((StandardJavaFileManager) this.clientJavaFileManager).setLocationForModule(location, str, collection);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public void setLocationFromPaths(JavaFileManager.Location location, Collection<? extends Path> collection) throws IOException {
            try {
                ((StandardJavaFileManager) this.clientJavaFileManager).setLocationFromPaths(location, collection);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public void setPathFactory(StandardJavaFileManager.PathFactory pathFactory) {
            try {
                ((StandardJavaFileManager) this.clientJavaFileManager).setPathFactory(pathFactory);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjects(Path... pathArr) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).getJavaFileObjects(pathArr);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        @Deprecated
        public Iterable<? extends JavaFileObject> getJavaFileObjectsFromPaths(Iterable<? extends Path> iterable) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).getJavaFileObjectsFromPaths(iterable);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.StandardJavaFileManager
        public Iterable<? extends JavaFileObject> getJavaFileObjects(String... strArr) {
            try {
                return ((StandardJavaFileManager) this.clientJavaFileManager).getJavaFileObjects(strArr);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }
    }

    public class WrappedJavaFileManager implements JavaFileManager {
        protected JavaFileManager clientJavaFileManager;

        public WrappedJavaFileManager(JavaFileManager javaFileManager) {
            Objects.requireNonNull(javaFileManager);
            this.clientJavaFileManager = javaFileManager;
        }

        @Override // javax.tools.JavaFileManager, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            try {
                this.clientJavaFileManager.close();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
            }
        }

        @Override // javax.tools.JavaFileManager
        public boolean contains(JavaFileManager.Location location, FileObject fileObject) throws IOException {
            try {
                return this.clientJavaFileManager.contains(location, ClientCodeWrapper.this.unwrap(fileObject));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return false;
            }
        }

        @Override // javax.tools.JavaFileManager, java.io.Flushable
        public void flush() throws IOException {
            try {
                this.clientJavaFileManager.flush();
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
            }
        }

        @Override // javax.tools.JavaFileManager
        public ClassLoader getClassLoader(JavaFileManager.Location location) {
            try {
                return this.clientJavaFileManager.getClassLoader(location);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public FileObject getFileForInput(JavaFileManager.Location location, String str, String str2) throws IOException {
            try {
                return ClientCodeWrapper.this.wrap(this.clientJavaFileManager.getFileForInput(location, str, str2));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public FileObject getFileForOutput(JavaFileManager.Location location, String str, String str2, FileObject fileObject) throws IOException {
            try {
                ClientCodeWrapper clientCodeWrapper = ClientCodeWrapper.this;
                return clientCodeWrapper.wrap(this.clientJavaFileManager.getFileForOutput(location, str, str2, clientCodeWrapper.unwrap(fileObject)));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public FileObject getFileForOutputForOriginatingFiles(JavaFileManager.Location location, String str, String str2, FileObject... fileObjectArr) throws IOException {
            try {
                return ClientCodeWrapper.this.wrap(this.clientJavaFileManager.getFileForOutputForOriginatingFiles(location, str, str2, fileObjectArr));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public JavaFileObject getJavaFileForInput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind) throws IOException {
            try {
                return ClientCodeWrapper.this.wrap(this.clientJavaFileManager.getJavaFileForInput(location, str, kind));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject fileObject) throws IOException {
            try {
                ClientCodeWrapper clientCodeWrapper = ClientCodeWrapper.this;
                return clientCodeWrapper.wrap(this.clientJavaFileManager.getJavaFileForOutput(location, str, kind, clientCodeWrapper.unwrap(fileObject)));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public JavaFileObject getJavaFileForOutputForOriginatingFiles(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject... fileObjectArr) throws IOException {
            try {
                return ClientCodeWrapper.this.wrap(this.clientJavaFileManager.getJavaFileForOutputForOriginatingFiles(location, str, kind, fileObjectArr));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, JavaFileObject javaFileObject) throws IOException {
            try {
                return this.clientJavaFileManager.getLocationForModule(location, ClientCodeWrapper.this.unwrap(javaFileObject));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public <S> ServiceLoader<S> getServiceLoader(JavaFileManager.Location location, Class<S> cls) throws IOException {
            try {
                return this.clientJavaFileManager.getServiceLoader(location, cls);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public boolean handleOption(String str, Iterator<String> it) {
            try {
                return this.clientJavaFileManager.handleOption(str, it);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return false;
            }
        }

        @Override // javax.tools.JavaFileManager
        public boolean hasLocation(JavaFileManager.Location location) {
            try {
                return this.clientJavaFileManager.hasLocation(location);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return false;
            }
        }

        @Override // javax.tools.JavaFileManager
        public String inferBinaryName(JavaFileManager.Location location, JavaFileObject javaFileObject) {
            try {
                return this.clientJavaFileManager.inferBinaryName(location, ClientCodeWrapper.this.unwrap(javaFileObject));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public String inferModuleName(JavaFileManager.Location location) throws IOException {
            try {
                return this.clientJavaFileManager.inferModuleName(location);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public boolean isSameFile(FileObject fileObject, FileObject fileObject2) {
            try {
                return this.clientJavaFileManager.isSameFile(ClientCodeWrapper.this.unwrap(fileObject), ClientCodeWrapper.this.unwrap(fileObject2));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return false;
            }
        }

        @Override // javax.tools.OptionChecker
        public int isSupportedOption(String str) {
            try {
                return this.clientJavaFileManager.isSupportedOption(str);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return 0;
            }
        }

        @Override // javax.tools.JavaFileManager
        public Iterable<JavaFileObject> list(JavaFileManager.Location location, String str, Set<JavaFileObject.Kind> set, boolean z) throws IOException {
            try {
                return ClientCodeWrapper.this.wrapJavaFileObjects(this.clientJavaFileManager.list(location, str, set, z));
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        @Override // javax.tools.JavaFileManager
        public Iterable<Set<JavaFileManager.Location>> listLocationsForModules(JavaFileManager.Location location) throws IOException {
            try {
                return this.clientJavaFileManager.listLocationsForModules(location);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }

        public String toString() {
            return ClientCodeWrapper.this.wrappedToString(getClass(), this.clientJavaFileManager);
        }

        @Override // javax.tools.JavaFileManager
        public JavaFileManager.Location getLocationForModule(JavaFileManager.Location location, String str) throws IOException {
            try {
                return this.clientJavaFileManager.getLocationForModule(location, str);
            } catch (ClientCodeException e) {
                throw e;
            } catch (Error | RuntimeException e2) {
                t02.a(e2);
                return null;
            }
        }
    }

    public FileObject wrap(FileObject fileObject) {
        return (fileObject == null || isTrusted(fileObject)) ? fileObject : new WrappedFileObject(fileObject);
    }

    public JavaFileObject wrap(JavaFileObject javaFileObject) {
        return (javaFileObject == null || isTrusted(javaFileObject)) ? javaFileObject : new WrappedJavaFileObject(javaFileObject);
    }

    public <T> DiagnosticListener<T> wrap(DiagnosticListener<T> diagnosticListener) {
        return isTrusted(diagnosticListener) ? diagnosticListener : new WrappedDiagnosticListener(diagnosticListener);
    }

    public TaskListener wrap(TaskListener taskListener) {
        return isTrusted(taskListener) ? taskListener : new WrappedTaskListener(taskListener);
    }

    public JavaFileObject unwrap(JavaFileObject javaFileObject) {
        return javaFileObject instanceof WrappedJavaFileObject ? (JavaFileObject) ((WrappedJavaFileObject) javaFileObject).clientFileObject : javaFileObject;
    }

    public TaskListener unwrap(TaskListener taskListener) {
        return taskListener instanceof WrappedTaskListener ? ((WrappedTaskListener) taskListener).clientTaskListener : taskListener;
    }

    public FileObject unwrap(FileObject fileObject) {
        return fileObject instanceof WrappedFileObject ? ((WrappedFileObject) fileObject).clientFileObject : fileObject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public <T> Diagnostic<T> unwrap(Diagnostic<T> diagnostic) {
        return diagnostic instanceof JCDiagnostic ? new DiagnosticSourceUnwrapper((JCDiagnostic) diagnostic) : diagnostic;
    }
}
