package com.sun.tools.javac.api;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class WrappingJavaFileManager<M extends JavaFileManager> extends ForwardingJavaFileManager<M> {
    public WrappingJavaFileManager(M m) {
        super(m);
    }

    @Override // javax.tools.ForwardingJavaFileManager, javax.tools.JavaFileManager
    public boolean contains(JavaFileManager.Location location, FileObject fileObject) throws IOException {
        return super.contains(location, unwrap(fileObject));
    }

    @Override // javax.tools.ForwardingJavaFileManager, javax.tools.JavaFileManager
    public FileObject getFileForInput(JavaFileManager.Location location, String str, String str2) throws IOException {
        return wrap(super.getFileForInput(location, str, str2));
    }

    @Override // javax.tools.ForwardingJavaFileManager, javax.tools.JavaFileManager
    public FileObject getFileForOutput(JavaFileManager.Location location, String str, String str2, FileObject fileObject) throws IOException {
        return wrap(super.getFileForOutput(location, str, str2, unwrap(fileObject)));
    }

    @Override // javax.tools.ForwardingJavaFileManager, javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForInput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind) throws IOException {
        return wrap(super.getJavaFileForInput(location, str, kind));
    }

    @Override // javax.tools.ForwardingJavaFileManager, javax.tools.JavaFileManager
    public JavaFileObject getJavaFileForOutput(JavaFileManager.Location location, String str, JavaFileObject.Kind kind, FileObject fileObject) throws IOException {
        return wrap(super.getJavaFileForOutput(location, str, kind, unwrap(fileObject)));
    }

    @Override // javax.tools.ForwardingJavaFileManager, javax.tools.JavaFileManager
    public String inferBinaryName(JavaFileManager.Location location, JavaFileObject javaFileObject) {
        return super.inferBinaryName(location, unwrap(javaFileObject));
    }

    @Override // javax.tools.ForwardingJavaFileManager, javax.tools.JavaFileManager
    public Iterable<JavaFileObject> list(JavaFileManager.Location location, String str, Set<JavaFileObject.Kind> set, boolean z) throws IOException {
        return wrap(super.list(location, str, set, z));
    }

    public JavaFileObject unwrap(JavaFileObject javaFileObject) {
        return (JavaFileObject) unwrap((FileObject) javaFileObject);
    }

    public Iterable<JavaFileObject> wrap(Iterable<JavaFileObject> iterable) {
        ArrayList arrayList = new ArrayList();
        Iterator<JavaFileObject> it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(wrap(it.next()));
        }
        return Collections.unmodifiableList(arrayList);
    }

    public FileObject unwrap(FileObject fileObject) {
        return fileObject;
    }

    public URI unwrap(URI uri) {
        return uri;
    }

    public JavaFileObject wrap(JavaFileObject javaFileObject) {
        return (JavaFileObject) wrap((FileObject) javaFileObject);
    }

    public FileObject wrap(FileObject fileObject) {
        return fileObject;
    }
}
