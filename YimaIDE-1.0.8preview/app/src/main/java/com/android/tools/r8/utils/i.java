package com.android.tools.r8.utils;

import com.android.tools.r8.AndroidResourceInput;
import com.android.tools.r8.AndroidResourceProvider;
import com.android.tools.r8.ClassFileConsumer;
import com.android.tools.r8.ClassFileResourceProvider;
import com.android.tools.r8.DataDirectoryResource;
import com.android.tools.r8.DataEntryResource;
import com.android.tools.r8.DataResource;
import com.android.tools.r8.DataResourceProvider;
import com.android.tools.r8.DexFilePerClassFileConsumer;
import com.android.tools.r8.DexIndexedConsumer;
import com.android.tools.r8.DirectoryClassFileProvider;
import com.android.tools.r8.FeatureSplit;
import com.android.tools.r8.OutputMode;
import com.android.tools.r8.ProgramResource;
import com.android.tools.r8.ProgramResourceProvider;
import com.android.tools.r8.Resource;
import com.android.tools.r8.ResourceException;
import com.android.tools.r8.Version;
import com.android.tools.r8.internal.AbstractC0551Hu;
import com.android.tools.r8.internal.AbstractC0706Nu;
import com.android.tools.r8.internal.AbstractC2653t4;
import com.android.tools.r8.internal.C0378Bd;
import com.android.tools.r8.internal.C0613Ke;
import com.android.tools.r8.internal.C0714Oc;
import com.android.tools.r8.internal.C0831Sp;
import com.android.tools.r8.internal.C0882Uo;
import com.android.tools.r8.internal.C0929Wj;
import com.android.tools.r8.internal.C0961Xp;
import com.android.tools.r8.internal.C1152bV;
import com.android.tools.r8.internal.C1249ce0;
import com.android.tools.r8.internal.C1405eW;
import com.android.tools.r8.internal.C1448f;
import com.android.tools.r8.internal.C1586gd;
import com.android.tools.r8.internal.C1641hB;
import com.android.tools.r8.internal.C1727iB;
import com.android.tools.r8.internal.C2098md;
import com.android.tools.r8.internal.C2742u50;
import com.android.tools.r8.internal.C2752uB;
import com.android.tools.r8.internal.C2855vT;
import com.android.tools.r8.internal.C3043xe0;
import com.android.tools.r8.internal.C3057xm;
import com.android.tools.r8.internal.Ck0;
import com.android.tools.r8.internal.H4;
import com.android.tools.r8.internal.K7;
import com.android.tools.r8.internal.Kk0;
import com.android.tools.r8.internal.P40;
import com.android.tools.r8.internal.Wf0;
import com.android.tools.r8.origin.ArchiveEntryOrigin;
import com.android.tools.r8.origin.Origin;
import com.android.tools.r8.origin.PathOrigin;
import com.android.tools.r8.profile.art.ArtProfileProvider;
import com.android.tools.r8.shaking.K0;
import com.android.tools.r8.startup.StartupProfileProvider;
import com.android.tools.r8.synthesis.J;
import com.android.tools.r8.t0;
import com.android.tools.r8.utils.i;
import defpackage.whe;
import defpackage.xhe;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class i {
    public static final /* synthetic */ boolean j = true;
    public final AbstractC0551Hu a;
    public final AbstractC0706Nu b;
    public final AbstractC0551Hu c;
    public final AbstractC0551Hu d;
    public final AbstractC0551Hu e;
    public final t0 f;
    public final t0 g;
    public final List h;
    public final List i;

    public i(AbstractC0551Hu abstractC0551Hu, AbstractC0706Nu abstractC0706Nu, AbstractC0551Hu abstractC0551Hu2, AbstractC0551Hu abstractC0551Hu3, AbstractC0551Hu abstractC0551Hu4, t0 t0Var, t0 t0Var2, List list, List list2) {
        this.a = abstractC0551Hu;
        this.b = abstractC0706Nu;
        this.c = abstractC0551Hu2;
        this.d = abstractC0551Hu3;
        this.e = abstractC0551Hu4;
        this.f = t0Var;
        this.g = t0Var2;
        this.h = list;
        this.i = list2;
        boolean z = j;
        if (!z && !a(abstractC0551Hu2, abstractC0551Hu4)) {
            x1f.a();
            throw null;
        }
        if (z || a(abstractC0551Hu3, abstractC0551Hu4)) {
            return;
        }
        x1f.a();
        throw null;
    }

    public void a(Path path, C3057xm c3057xm, C2752uB c2752uB) {
        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(path, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING));
            try {
                String versionString = Version.getVersionString();
                Charset charset = StandardCharsets.UTF_8;
                v.a(zipOutputStream, "r8-version", versionString.getBytes(charset), 8);
                v.a(zipOutputStream, "build.properties", c3057xm.e().getBytes(charset), 8);
                if (c3057xm.g() != null) {
                    v.a(zipOutputStream, "desugared-library.json", c3057xm.g().getBytes(charset), 8);
                    if (c3057xm.a()) {
                        c2752uB.i.c("Dumping a compilation with desugared library on a file may prevent reproduction, use dumpInputToDirectory property instead.");
                    }
                }
                if (c3057xm.k() != null) {
                    v.a(zipOutputStream, "proguard.config", c3057xm.k().getBytes(charset), 8);
                }
                if (this.g != null) {
                    c2752uB.i.c("Dumping proguard map input data may have side effects due to I/O on Paths.");
                    v.a(zipOutputStream, "proguard_input.config", this.g.a().getBytes(charset), 8);
                }
                if (l()) {
                    ArrayList arrayList = new ArrayList();
                    if (m()) {
                        c2752uB.i.c("Dumping main dex list resources may have side effects due to I/O on Paths.");
                        Iterator it = this.h.iterator();
                        while (it.hasNext()) {
                            arrayList.add(((t0) it.next()).a());
                        }
                    }
                    Iterator it2 = this.i.iterator();
                    while (it2.hasNext()) {
                        arrayList.add(((String) it2.next()).replace(".", "/") + ".class");
                    }
                    v.a(zipOutputStream, "main-dex-list.txt", Wf0.a("\n", (Iterable) arrayList).getBytes(StandardCharsets.UTF_8), 8);
                }
                if (c3057xm.o()) {
                    v.a(zipOutputStream, "main-dex-rules.txt", Wf0.a((Collection) c3057xm.i()).getBytes(StandardCharsets.UTF_8), 8);
                }
                if (c3057xm.n()) {
                    a(c3057xm.c(), zipOutputStream);
                }
                if (c3057xm.p()) {
                    a(c3057xm.l(), c2752uB, zipOutputStream);
                }
                if (c3057xm.m()) {
                    a(c3057xm.b(), zipOutputStream, "app-res.ap_");
                    if (c3057xm.h() != null) {
                        IdentityHashMap identityHashMapA = a(c3057xm.h(), ".ap_");
                        for (FeatureSplit featureSplit : c3057xm.h().a()) {
                            if (featureSplit.getAndroidResourceProvider() != null) {
                                a(featureSplit.getAndroidResourceProvider(), zipOutputStream, (String) identityHashMapA.get(featureSplit));
                            }
                        }
                    }
                }
                a("library.jar", a("classpath.jar", a(c3057xm.h(), zipOutputStream, c2752uB), zipOutputStream, this.c), zipOutputStream, this.d);
                zipOutputStream.close();
            } catch (Throwable th) {
                try {
                    zipOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (ResourceException | IOException e) {
            c2752uB.i.a(new ExceptionDiagnostic(e));
            throw null;
        }
    }

    public final void b(C2742u50 c2742u50) throws IOException {
        Ck0 it = this.a.iterator();
        while (it.hasNext()) {
            ((ProgramResourceProvider) it.next()).finished(c2742u50);
        }
        Ck0 it2 = this.c.iterator();
        while (it2.hasNext()) {
            ((ClassFileResourceProvider) it2.next()).finished(c2742u50);
        }
        Ck0 it3 = this.d.iterator();
        while (it3.hasNext()) {
            ((ClassFileResourceProvider) it3.next()).finished(c2742u50);
        }
    }

    public void c(Path path, OutputMode outputMode) throws Throwable {
        try {
            if (outputMode == OutputMode.DexIndexed) {
                C1405eW c1405eWF = f();
                DexIndexedConsumer.ArchiveConsumer.writeResourcesForTesting(path, g(), (Set) c1405eWF.a(), (Set) c1405eWF.b());
                return;
            }
            if (outputMode != OutputMode.DexFilePerClassFile && outputMode != OutputMode.DexFilePerClass) {
                if (outputMode == OutputMode.ClassFile) {
                    C1405eW c1405eWF2 = f();
                    ClassFileConsumer.ArchiveConsumer.writeResourcesForTesting(path, d(), (Set) c1405eWF2.a(), (Set) c1405eWF2.b());
                    return;
                } else {
                    throw new Kk0("Unsupported output-mode for writing: " + outputMode);
                }
            }
            DexFilePerClassFileConsumer.ArchiveConsumer.writeResourcesForTesting(path, g(), this.b);
        } catch (ResourceException e) {
            dk3.a("Resource Error", e);
        }
    }

    public List<ProgramResource> d() throws IOException {
        try {
            AbstractC0551Hu abstractC0551Hu = this.a;
            ProgramResource.Kind kind = ProgramResource.Kind.CF;
            ArrayList arrayList = new ArrayList();
            Iterator it = abstractC0551Hu.iterator();
            while (it.hasNext()) {
                for (ProgramResource programResource : ((ProgramResourceProvider) it.next()).getProgramResources()) {
                    if (programResource.getKind() == kind) {
                        arrayList.add(programResource);
                    }
                }
            }
            return arrayList;
        } catch (ResourceException e) {
            if (e.getCause() instanceof IOException) {
                throw ((IOException) e.getCause());
            }
            throw new C1727iB(e);
        }
    }

    public List<ClassFileResourceProvider> e() {
        return this.c;
    }

    public final C1405eW f() throws ResourceException {
        TreeSet treeSet = new TreeSet(Comparator.comparing(new Function() { // from class: t5h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DataDirectoryResource) obj).getName();
            }
        }));
        TreeSet treeSet2 = new TreeSet(Comparator.comparing(new Function() { // from class: w5h
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((DataEntryResource) obj).getName();
            }
        }));
        Iterator<ProgramResourceProvider> it = i().iterator();
        while (it.hasNext()) {
            DataResourceProvider dataResourceProvider = it.next().getDataResourceProvider();
            if (dataResourceProvider != null) {
                dataResourceProvider.accept(new com.android.tools.r8.utils.a(treeSet, treeSet2));
            }
        }
        return new C1405eW(treeSet, treeSet2);
    }

    public List<ProgramResource> g() throws IOException {
        try {
            AbstractC0551Hu abstractC0551Hu = this.a;
            ProgramResource.Kind kind = ProgramResource.Kind.DEX;
            ArrayList arrayList = new ArrayList();
            Iterator it = abstractC0551Hu.iterator();
            while (it.hasNext()) {
                for (ProgramResource programResource : ((ProgramResourceProvider) it.next()).getProgramResources()) {
                    if (programResource.getKind() == kind) {
                        arrayList.add(programResource);
                    }
                }
            }
            return arrayList;
        } catch (ResourceException e) {
            if (e.getCause() instanceof IOException) {
                throw ((IOException) e.getCause());
            }
            throw new C1727iB(e);
        }
    }

    public List<ClassFileResourceProvider> h() {
        return this.d;
    }

    public List<ProgramResourceProvider> i() {
        return this.a;
    }

    public t0 j() {
        return this.g;
    }

    public t0 k() {
        return this.f;
    }

    public boolean l() {
        return (this.h.isEmpty() && this.i.isEmpty()) ? false : true;
    }

    public boolean m() {
        return !this.h.isEmpty();
    }

    public final void n() {
        Iterator<ProgramResourceProvider> it = i().iterator();
        while (it.hasNext()) {
            try {
                for (ProgramResource programResource : it.next().getProgramResources()) {
                    try {
                        if (programResource.getKind() != ProgramResource.Kind.DEX) {
                            new C1586gd(programResource.getBytes()).a(new d(new C0714Oc()), 8);
                        }
                    } catch (Throwable th) {
                        throw new C0613Ke("Failed validating " + programResource.getOrigin(), th);
                    }
                }
            } catch (ResourceException e) {
                throw new C0613Ke("Resource exception in validation", e);
            }
        }
    }

    public final i o() {
        AbstractC0551Hu abstractC0551Hu = this.a;
        AbstractC0706Nu abstractC0706Nu = this.b;
        AbstractC0551Hu abstractC0551Hu2 = this.c;
        AbstractC0551Hu abstractC0551Hu3 = this.d;
        AbstractC0551Hu abstractC0551Hu4 = this.e;
        t0 t0Var = this.f;
        t0 t0Var2 = this.g;
        int i = AbstractC0551Hu.c;
        P40 p40 = P40.e;
        return new i(abstractC0551Hu, abstractC0706Nu, abstractC0551Hu2, abstractC0551Hu3, abstractC0551Hu4, t0Var, t0Var2, p40, p40);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        try {
            if (!this.a.isEmpty()) {
                sb.append("  Program resources:");
                sb.append(System.lineSeparator());
                Iterator<E> it = this.a.iterator();
                while (it.hasNext()) {
                    for (ProgramResource programResource : ((ProgramResourceProvider) it.next()).getProgramResources()) {
                        sb.append("    ");
                        sb.append(programResource.getOrigin());
                        Set<String> classDescriptors = programResource.getClassDescriptors();
                        if (classDescriptors != null && !classDescriptors.isEmpty()) {
                            sb.append(" contains ");
                            Wf0.a(sb, classDescriptors);
                        }
                        sb.append(System.lineSeparator());
                    }
                }
            }
            if (!this.c.isEmpty()) {
                sb.append("  Classpath resources:");
                sb.append(System.lineSeparator());
                for (ClassFileResourceProvider classFileResourceProvider : this.c) {
                    Iterator<String> it2 = classFileResourceProvider.getClassDescriptors().iterator();
                    while (it2.hasNext()) {
                        ProgramResource programResource2 = classFileResourceProvider.getProgramResource(it2.next());
                        sb.append("    ");
                        sb.append(programResource2.getOrigin());
                        Set<String> classDescriptors2 = programResource2.getClassDescriptors();
                        if (classDescriptors2 != null && !classDescriptors2.isEmpty()) {
                            sb.append(" contains ");
                            Wf0.a(sb, classDescriptors2);
                        }
                        sb.append(System.lineSeparator());
                    }
                }
            }
            if (!this.d.isEmpty()) {
                sb.append("  Library resources:");
                sb.append(System.lineSeparator());
                for (ClassFileResourceProvider classFileResourceProvider2 : this.d) {
                    Iterator<String> it3 = classFileResourceProvider2.getClassDescriptors().iterator();
                    while (it3.hasNext()) {
                        ProgramResource programResource3 = classFileResourceProvider2.getProgramResource(it3.next());
                        sb.append("    ");
                        sb.append(programResource3.getOrigin());
                        Set<String> classDescriptors3 = programResource3.getClassDescriptors();
                        if (classDescriptors3 != null && !classDescriptors3.isEmpty()) {
                            sb.append(" contains ");
                            Wf0.a(sb, classDescriptors3);
                        }
                        sb.append(System.lineSeparator());
                    }
                }
            }
        } catch (ResourceException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static class a {
        public static final /* synthetic */ boolean n = true;
        public final ArrayList a = new ArrayList();
        public final ArrayList b = new ArrayList();
        public final ArrayList c = new ArrayList();
        public final HashMap d = new HashMap();
        public final ArrayList e = new ArrayList();
        public final ArrayList f = new ArrayList();
        public final ArrayList g = new ArrayList();
        public List h = new ArrayList();
        public List i = new ArrayList();
        public boolean j = false;
        public t0 k;
        public t0 l;
        public final C2742u50 m;

        public a(C2742u50 c2742u50) {
            this.m = c2742u50;
        }

        public final void a(Origin origin, InputStream inputStream) throws IOException {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    }
                    String name = nextEntry.getName();
                    if (v.a(name)) {
                        arrayList.add(C1152bV.a(ProgramResource.Kind.CF, new ArchiveEntryOrigin(name, origin), K7.a(zipInputStream), Collections.singleton(C0929Wj.y(name))));
                    } else if (v.b(name)) {
                        arrayList.add(C1152bV.a(ProgramResource.Kind.DEX, new ArchiveEntryOrigin(name, origin), K7.a(zipInputStream), null));
                    } else if (name.endsWith(".dup")) {
                        System.out.println("WARNING: Duplicate program resource: " + name);
                    } else {
                        arrayList2.add(DataEntryResource.fromBytes(K7.a(zipInputStream), name, origin));
                    }
                } catch (Throwable th) {
                    try {
                        zipInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            zipInputStream.close();
            if (arrayList.isEmpty() && arrayList2.isEmpty()) {
                return;
            }
            a(new f(arrayList, arrayList2));
        }

        public final void b() {
            if (this.b.isEmpty() && this.c.isEmpty()) {
                return;
            }
            this.a.add(new h(AbstractC0551Hu.a(this.b), AbstractC0551Hu.a(this.c)));
            this.b.clear();
            this.c.clear();
        }

        public a c(Path path) {
            if (!Files.exists(path, new LinkOption[0])) {
                this.m.error(new ExceptionDiagnostic(new NoSuchFileException(path.toString()), new PathOrigin(path)));
            }
            if (C0831Sp.d(path)) {
                a(ProgramResource.fromFile(ProgramResource.Kind.DEX, path));
                return this;
            }
            if (C0831Sp.b(path)) {
                a(ProgramResource.fromFile(ProgramResource.Kind.CF, path));
                return this;
            }
            if (Wf0.i(path.getFileName().toString()).endsWith(".aar")) {
                a(new C1448f(path));
                return this;
            }
            if (!C0831Sp.a(path)) {
                throw new C0613Ke(new PathOrigin(path), "Unsupported source file type", null);
            }
            a(ArchiveResourceProvider.fromArchive(path, this.j));
            return this;
        }

        public a d(Collection<K0> collection) {
            Iterator<K0> it = collection.iterator();
            while (it.hasNext()) {
                a(it.next());
            }
            return this;
        }

        public a e(Collection<Path> collection) {
            Iterator<Path> it = collection.iterator();
            while (it.hasNext()) {
                a(it.next(), this.f);
            }
            return this;
        }

        public a f(Collection<Path> collection) {
            Iterator<Path> it = collection.iterator();
            while (it.hasNext()) {
                c(it.next());
            }
            return this;
        }

        public a b(Collection<Path> collection) {
            Iterator<Path> it = collection.iterator();
            while (it.hasNext()) {
                a(it.next(), this.e);
            }
            return this;
        }

        public a b(Path path) {
            a(path, this.f);
            return this;
        }

        public a b(ClassFileResourceProvider classFileResourceProvider) {
            if (classFileResourceProvider instanceof C1641hB) {
                this.g.add((C1641hB) classFileResourceProvider);
            }
            this.f.add(classFileResourceProvider);
            return this;
        }

        public a b(byte[] bArr, Origin origin) {
            a(ProgramResource.fromBytes(origin, ProgramResource.Kind.DEX, bArr, null));
            return this;
        }

        public a b(Path... pathArr) {
            return f(Arrays.asList(pathArr));
        }

        public final boolean c() {
            return (this.h.isEmpty() && this.i.isEmpty()) ? false : true;
        }

        public a c(Collection<K0> collection) {
            for (K0 k0 : collection) {
                if (C0831Sp.a(k0.a)) {
                    try {
                        C0961Xp c0961Xp = new C0961Xp(k0);
                        this.g.add(c0961Xp);
                        this.f.add(c0961Xp);
                    } catch (IOException e) {
                        this.m.error(new ExceptionDiagnostic(e, new PathOrigin(k0.a)));
                    }
                } else {
                    this.m.error(new StringDiagnostic("Unexpected input type. Only archive types are supported, e.g., .jar, .zip, etc.", k0.c, k0.d));
                }
            }
            return this;
        }

        public a a(Path path) throws IOException {
            System.out.println("Reading dump from file: " + path);
            final PathOrigin pathOrigin = new PathOrigin(path);
            v.a(path.toString(), new v.a() { // from class: h6h
                @Override // com.android.tools.r8.utils.v.a
                public final void a(ZipEntry zipEntry, InputStream inputStream) throws IOException {
                    this.a.a(pathOrigin, zipEntry, inputStream);
                }
            });
            return this;
        }

        public final /* synthetic */ void a(Origin origin, ZipEntry zipEntry, InputStream inputStream) throws IOException {
            String name = zipEntry.getName();
            if (name.equals("r8-version")) {
                System.out.println("Dump produced by R8 version: ".concat(new String(K7.a(inputStream), StandardCharsets.UTF_8)));
                return;
            }
            if (name.equals("program.jar")) {
                a(origin, inputStream);
                return;
            }
            if (name.equals("classpath.jar")) {
                a(origin, inputStream, new xhe(this), "classpath");
                return;
            }
            if (name.equals("library.jar")) {
                a(origin, inputStream, new whe(this), "library");
                return;
            }
            System.out.println("WARNING: Unexpected dump file entry: " + zipEntry.getName());
        }

        public static void a(Origin origin, InputStream inputStream, Consumer consumer, String str) throws IOException {
            HashMap map = new HashMap();
            ZipInputStream zipInputStream = new ZipInputStream(inputStream);
            while (true) {
                try {
                    ZipEntry nextEntry = zipInputStream.getNextEntry();
                    if (nextEntry == null) {
                        break;
                    }
                    String name = nextEntry.getName();
                    if (v.a(name)) {
                        ArchiveEntryOrigin archiveEntryOrigin = new ArchiveEntryOrigin(name, origin);
                        String strY = C0929Wj.y(name);
                        map.put(strY, C1152bV.a(ProgramResource.Kind.CF, archiveEntryOrigin, K7.a(zipInputStream), Collections.singleton(strY)));
                    } else if (name.endsWith(".dup")) {
                        System.out.println("WARNING: Duplicate " + str + " resource: " + name);
                    } else {
                        System.out.println("WARNING: Unexpected " + str + " resource: " + name);
                    }
                } catch (Throwable th) {
                    try {
                        zipInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            }
            zipInputStream.close();
            if (map.isEmpty()) {
                return;
            }
            consumer.accept(i.a(map));
        }

        public final ArchiveResourceProvider a(K0 k0) {
            if (C0831Sp.a(k0.a)) {
                ArchiveResourceProvider archiveResourceProvider = new ArchiveResourceProvider(k0, this.j);
                a(archiveResourceProvider);
                return archiveResourceProvider;
            }
            this.m.error(new StringDiagnostic("Unexpected input type. Only archive types are supported, e.g., .jar, .zip, etc.", k0.c, k0.d));
            return null;
        }

        public a a(ProgramResourceProvider programResourceProvider) {
            if (n || programResourceProvider != null) {
                this.a.add(programResourceProvider);
                return this;
            }
            x1f.a();
            return null;
        }

        public a a(ClassFileResourceProvider classFileResourceProvider) {
            this.e.add(classFileResourceProvider);
            return this;
        }

        public a a(Path... pathArr) {
            return e(Arrays.asList(pathArr));
        }

        public a a(byte[]... bArr) {
            return a(Arrays.asList(bArr));
        }

        public a a(Collection<byte[]> collection) {
            Iterator<byte[]> it = collection.iterator();
            while (it.hasNext()) {
                a(it.next(), Origin.unknown());
            }
            return this;
        }

        public a a(byte[] bArr, Origin origin) {
            return a(bArr, origin, (Set<String>) null);
        }

        public a a(byte[] bArr, Origin origin, Set<String> set) {
            a(ProgramResource.fromBytes(origin, ProgramResource.Kind.CF, bArr, set));
            return this;
        }

        public a a(DataResource dataResource) {
            this.c.addAll(Arrays.asList(dataResource));
            return this;
        }

        public a a(String str) {
            this.k = str == null ? null : t0.a(str, Origin.unknown());
            return this;
        }

        public i a() {
            b();
            return new i(AbstractC0551Hu.a(this.a), AbstractC0706Nu.a(this.d), AbstractC0551Hu.a(this.e), AbstractC0551Hu.a(this.f), AbstractC0551Hu.a(this.g), this.k, this.l, this.h, this.i);
        }

        public final void a(ProgramResource... programResourceArr) {
            this.b.addAll(Arrays.asList(programResourceArr));
        }

        public final void a(Path path, ArrayList arrayList) {
            if (!Files.exists(path, new LinkOption[0])) {
                this.m.error(new ExceptionDiagnostic(new NoSuchFileException(path.toString()), new PathOrigin(path)));
            }
            if (C0831Sp.a(path)) {
                try {
                    C1641hB c1641hB = new C1641hB(path);
                    this.g.add(c1641hB);
                    arrayList.add(c1641hB);
                    return;
                } catch (IOException e) {
                    this.m.error(new ExceptionDiagnostic(e, new PathOrigin(path)));
                    return;
                }
            }
            if (Files.isDirectory(path, new LinkOption[0])) {
                arrayList.add(DirectoryClassFileProvider.fromDirectory(path));
                return;
            }
            throw new C0613Ke(new PathOrigin(path), "Unsupported source file type", null);
        }
    }

    public void b(Path path, OutputMode outputMode) throws Throwable {
        List<ProgramResource> listG = g();
        try {
            if (outputMode == OutputMode.DexIndexed) {
                DexIndexedConsumer.DirectoryConsumer.writeResources(path, listG);
            } else {
                DexFilePerClassFileConsumer.DirectoryConsumer.writeResources(path, listG, this.b);
            }
        } catch (ResourceException e) {
            dk3.a("Resource Error", e);
        }
    }

    public static a b() {
        return a(new C2742u50());
    }

    public final void c() throws IOException {
        Ck0 it = this.e.iterator();
        while (it.hasNext()) {
            ((C1641hB) it.next()).close();
        }
    }

    public static IdentityHashMap a(C0882Uo c0882Uo, String str) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        if (c0882Uo != null) {
            Iterator it = c0882Uo.a().iterator();
            int i = 1;
            while (it.hasNext()) {
                identityHashMap.put((FeatureSplit) it.next(), "feature-" + i + str);
                i++;
            }
        }
        return identityHashMap;
    }

    public static boolean a(AbstractC0551Hu abstractC0551Hu, final AbstractC0551Hu abstractC0551Hu2) {
        return abstractC0551Hu.stream().allMatch(new Predicate() { // from class: q5h
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return i.a(abstractC0551Hu2, (ClassFileResourceProvider) obj);
            }
        });
    }

    public static /* synthetic */ boolean a(AbstractC0551Hu abstractC0551Hu, ClassFileResourceProvider classFileResourceProvider) {
        return !(classFileResourceProvider instanceof C1641hB) || abstractC0551Hu.contains(classFileResourceProvider);
    }

    public static a a(i iVar) {
        a aVar = new a(new C2742u50());
        aVar.a.addAll(iVar.a);
        aVar.e.addAll(iVar.c);
        aVar.f.addAll(iVar.d);
        aVar.g.addAll(iVar.e);
        aVar.h = iVar.h;
        aVar.i = iVar.i;
        aVar.l = iVar.g;
        return aVar;
    }

    public static a a(C2742u50 c2742u50) {
        return new a(c2742u50);
    }

    public int a() throws Throwable {
        int length = 0;
        if (!j && g().size() != 0 && d().size() != 0) {
            x1f.a();
            return 0;
        }
        C0378Bd c0378Bd = new C0378Bd(C0378Bd.c);
        try {
            Iterator<ProgramResource> it = g().iterator();
            while (it.hasNext()) {
                length += K7.a((InputStream) c0378Bd.a(it.next().getByteStream())).length;
            }
            Iterator<ProgramResource> it2 = d().iterator();
            while (it2.hasNext()) {
                length += K7.a((InputStream) c0378Bd.a(it2.next().getByteStream())).length;
            }
            c0378Bd.close();
            return length;
        } catch (Throwable th) {
            try {
                c0378Bd.close();
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
            throw th;
        }
    }

    public void a(Path path, OutputMode outputMode) throws Throwable {
        if (C0831Sp.a(path)) {
            c(path, outputMode);
        } else {
            b(path, outputMode);
        }
    }

    public String a(Resource resource) {
        if (j || (resource instanceof ProgramResource)) {
            return (String) this.b.get(resource);
        }
        x1f.a();
        return null;
    }

    public static /* synthetic */ ZipOutputStream a(ZipOutputStream zipOutputStream, String str) {
        return zipOutputStream;
    }

    public static void a(Collection collection, ZipOutputStream zipOutputStream) throws IOException {
        Iterator it = collection.iterator();
        int i = 1;
        while (it.hasNext()) {
            v.a(zipOutputStream, "art-profile-" + i + ".txt", AbstractC2653t4.a((ArtProfileProvider) it.next()).getBytes(StandardCharsets.UTF_8), 8);
            i++;
        }
    }

    public static void a(Collection collection, C2752uB c2752uB, ZipOutputStream zipOutputStream) throws IOException {
        Iterator it = collection.iterator();
        int i = 1;
        while (it.hasNext()) {
            v.a(zipOutputStream, "startup-profile-" + i + ".txt", C1249ce0.a(c2752uB, (StartupProfileProvider) it.next()).getBytes(StandardCharsets.UTF_8), 8);
            i++;
        }
    }

    public static c a(HashMap map) {
        return new c(map);
    }

    public static void a(AndroidResourceProvider androidResourceProvider, ZipOutputStream zipOutputStream, String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ZipOutputStream zipOutputStream2 = new ZipOutputStream(byteArrayOutputStream);
            try {
                for (AndroidResourceInput androidResourceInput : androidResourceProvider.getAndroidResources()) {
                    v.a(zipOutputStream2, androidResourceInput.getPath().location(), androidResourceInput.getByteStream().readAllBytes(), 8);
                }
                zipOutputStream2.close();
                v.a(zipOutputStream, str, byteArrayOutputStream.toByteArray(), 8);
                byteArrayOutputStream.close();
            } catch (Throwable th) {
                try {
                    zipOutputStream2.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public final int a(final C0882Uo c0882Uo, ZipOutputStream zipOutputStream, C2752uB c2752uB) throws IOException {
        IdentityHashMap identityHashMapA = a(c0882Uo, ".jar");
        IdentityHashMap identityHashMap = new IdentityHashMap();
        final IdentityHashMap identityHashMap2 = new IdentityHashMap();
        try {
            final C2752uB c2752uB2 = c2752uB;
            final C2098md c2098mdA = C2098md.a(c2752uB.s(), c0882Uo, c2752uB2.i);
            if (c0882Uo != null) {
                for (FeatureSplit featureSplit : c0882Uo.a) {
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    identityHashMap.put(featureSplit, byteArrayOutputStream);
                    identityHashMap2.put(featureSplit, new ZipOutputStream(byteArrayOutputStream));
                }
            }
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            try {
                final ZipOutputStream zipOutputStream2 = new ZipOutputStream(byteArrayOutputStream2);
                try {
                    C2855vT c2855vT = new C2855vT();
                    C1405eW c1405eWF = f();
                    Iterator it = ((Set) c1405eWF.a()).iterator();
                    while (it.hasNext()) {
                        v.a(zipOutputStream2, ((DataDirectoryResource) it.next()).getName(), new byte[0], 0);
                    }
                    for (DataEntryResource dataEntryResource : (Set) c1405eWF.b()) {
                        String name = dataEntryResource.getName();
                        InputStream byteStream = dataEntryResource.getByteStream();
                        try {
                            v.a(zipOutputStream2, name, K7.a(byteStream), 8);
                            byteStream.close();
                        } catch (Throwable th) {
                            if (byteStream == null) {
                                throw th;
                            }
                            try {
                                byteStream.close();
                                throw th;
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                                throw th;
                            }
                            try {
                                byteArrayOutputStream2.close();
                                throw th;
                            } catch (Throwable th3) {
                                th.addSuppressed(th3);
                                throw th;
                            }
                        }
                    }
                    Ck0 it2 = this.a.iterator();
                    int iA = 0;
                    while (it2.hasNext()) {
                        Iterator<ProgramResource> it3 = ((ProgramResourceProvider) it2.next()).getProgramResources().iterator();
                        while (it3.hasNext()) {
                            iA = a(c2855vT, iA, new Function() { // from class: w4h
                                @Override // java.util.function.Function
                                public final Object apply(Object obj) {
                                    return i.a(c0882Uo, c2752uB2, c2098mdA, identityHashMap2, zipOutputStream2, (String) obj);
                                }
                            }, zipOutputStream2, it3.next());
                            c2752uB2 = c2752uB;
                        }
                        c2752uB2 = c2752uB;
                    }
                    zipOutputStream2.close();
                    v.a(zipOutputStream, "program.jar", byteArrayOutputStream2.toByteArray(), 8);
                    if (c0882Uo != null) {
                        for (FeatureSplit featureSplit2 : c0882Uo.a) {
                            ((ZipOutputStream) identityHashMap2.remove(featureSplit2)).close();
                            v.a(zipOutputStream, (String) identityHashMapA.get(featureSplit2), ((ByteArrayOutputStream) identityHashMap.get(featureSplit2)).toByteArray(), 8);
                        }
                    }
                    byteArrayOutputStream2.close();
                    Iterator it4 = identityHashMap2.values().iterator();
                    IOException iOException = null;
                    RuntimeException runtimeException = null;
                    while (it4.hasNext()) {
                        try {
                            ((OutputStream) it4.next()).close();
                        } catch (IOException e) {
                            iOException = e;
                        } catch (RuntimeException e2) {
                            runtimeException = e2;
                        }
                    }
                    if (iOException != null) {
                        throw iOException;
                    }
                    if (runtimeException == null) {
                        return iA;
                    }
                    throw runtimeException;
                } catch (Throwable th4) {
                    try {
                        zipOutputStream2.close();
                        throw th4;
                    } catch (Throwable th5) {
                        th4.addSuppressed(th5);
                        throw th4;
                    }
                }
            } catch (Throwable th6) {
                byteArrayOutputStream2.close();
                throw th6;
            }
        } catch (Throwable th7) {
            Iterator it5 = identityHashMap2.values().iterator();
            IOException iOException2 = null;
            RuntimeException runtimeException2 = null;
            while (it5.hasNext()) {
                try {
                    ((OutputStream) it5.next()).close();
                } catch (IOException e3) {
                    iOException2 = e3;
                } catch (RuntimeException e4) {
                    runtimeException2 = e4;
                }
            }
            if (iOException2 != null) {
                throw iOException2;
            }
            if (runtimeException2 == null) {
                throw th7;
            }
            throw runtimeException2;
        }
    }

    public static /* synthetic */ ZipOutputStream a(C0882Uo c0882Uo, C2752uB c2752uB, C2098md c2098md, Map map, ZipOutputStream zipOutputStream, String str) {
        FeatureSplit featureSplitA;
        return (c0882Uo == null || (featureSplitA = c2098md.a(c2752uB.s().e(str), (J) null)) == null || featureSplitA.isBase()) ? zipOutputStream : (ZipOutputStream) map.get(featureSplitA);
    }

    public static int a(String str, int i, ZipOutputStream zipOutputStream, AbstractC0551Hu abstractC0551Hu) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            final ZipOutputStream zipOutputStream2 = new ZipOutputStream(byteArrayOutputStream);
            try {
                C2855vT c2855vT = new C2855vT();
                Ck0 it = abstractC0551Hu.iterator();
                while (it.hasNext()) {
                    ClassFileResourceProvider classFileResourceProvider = (ClassFileResourceProvider) it.next();
                    Iterator<String> it2 = classFileResourceProvider.getClassDescriptors().iterator();
                    while (it2.hasNext()) {
                        int iA = a(c2855vT, i, new Function() { // from class: b5h
                            @Override // java.util.function.Function
                            public final Object apply(Object obj) {
                                return i.a(zipOutputStream2, (String) obj);
                            }
                        }, zipOutputStream2, classFileResourceProvider.getProgramResource(it2.next()));
                        if (!j && iA != i) {
                            throw new AssertionError();
                        }
                        i = iA;
                    }
                }
                zipOutputStream2.close();
                v.a(zipOutputStream, str, byteArrayOutputStream.toByteArray(), 8);
                byteArrayOutputStream.close();
                return i;
            } catch (Throwable th) {
                try {
                    zipOutputStream2.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (Throwable th3) {
            try {
                byteArrayOutputStream.close();
            } catch (Throwable th4) {
                th3.addSuppressed(th4);
            }
            throw th3;
        }
    }

    public static int a(C2855vT c2855vT, int i, Function function, ZipOutputStream zipOutputStream, ProgramResource programResource) throws IOException {
        String strB;
        byte[] bArrA = C3043xe0.a(programResource.getByteStream());
        if (programResource.getKind() == ProgramResource.Kind.CF) {
            Set<String> classDescriptors = programResource.getClassDescriptors();
            if (classDescriptors != null && classDescriptors.size() == 1) {
                strB = classDescriptors.iterator().next();
            } else {
                C1586gd c1586gd = new C1586gd(bArrA);
                b bVar = new b();
                c1586gd.a(bVar, new H4[0], 7);
                strB = bVar.b();
            }
            String strJ = C0929Wj.j(strB);
            int iIntValue = ((Integer) c2855vT.getOrDefault(strB, 0)).intValue();
            c2855vT.b(iIntValue + 1, strB);
            if (iIntValue != 0) {
                strJ = strJ + "." + iIntValue + ".dup";
            }
            v.a((ZipOutputStream) function.apply(strB), strJ, bArrA, 8);
            return i;
        }
        if (!j && programResource.getKind() != ProgramResource.Kind.DEX) {
            x1f.a();
            return 0;
        }
        int i2 = i + 1;
        v.a(zipOutputStream, "classes" + i + ".dex", bArrA, 8);
        return i2;
    }
}
