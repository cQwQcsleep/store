package org.jetbrains.kotlin.cli.jvm.modules;

import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileSystem;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.jvm.modules.CtSymDirectoryContainer;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0011\n\u0002\b\f\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BK\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0001\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u0007\u0012\u0006\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rJ\b\u0010\"\u001a\u00020\u0007H\u0016J\r\u0010#\u001a\u00070\u0014¢\u0006\u0002\b\u0015H\u0016J\b\u0010$\u001a\u00020\u0007H\u0016J\b\u0010%\u001a\u00020\bH\u0016J\b\u0010&\u001a\u00020\bH\u0016J\b\u0010'\u001a\u00020\bH\u0016J\n\u0010(\u001a\u0004\u0018\u00010\u0001H\u0016J\u0015\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0001\u0018\u00010\u001eH\u0016¢\u0006\u0002\u0010 J\"\u0010*\u001a\u00020+2\b\u0010,\u001a\u0004\u0018\u00010-2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/H\u0016J\b\u00101\u001a\u000202H\u0016J\b\u00103\u001a\u00020+H\u0016J\b\u00104\u001a\u00020+H\u0016J\"\u00105\u001a\u0002062\u0006\u0010,\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b2\b\u00100\u001a\u0004\u0018\u000107H\u0016J\b\u00108\u001a\u000209H\u0016R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u00078BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010R \u0010\u0013\u001a\u00070\u0014¢\u0006\u0002\b\u00158BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0018\u0010\u0012\u001a\u0004\b\u0016\u0010\u0017R\u001b\u0010\u0019\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0012\u001a\u0004\b\u001a\u0010\u001bR!\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00010\u001e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\u0012\u001a\u0004\b\u001f\u0010 ¨\u0006:"}, d2 = {"Lorg/jetbrains/kotlin/cli/jvm/modules/CtSymDirectoryContainer;", "Lcom/intellij/openapi/vfs/VirtualFile;", "parent", "rootOrPackageParts", Argument.Delimiters.none, "packages", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "currentPackage", "moduleRoot", "skipPackageCheck", "<init>", "(Lcom/intellij/openapi/vfs/VirtualFile;Ljava/util/List;Ljava/util/Map;Ljava/lang/String;Ljava/lang/String;Z)V", "_path", "get_path", "()Ljava/lang/String;", "_path$delegate", "Lkotlin/Lazy;", "_fileSystem", "Lcom/intellij/openapi/vfs/VirtualFileSystem;", "Lorg/jetbrains/annotations/NotNull;", "get_fileSystem", "()Lcom/intellij/openapi/vfs/VirtualFileSystem;", "_fileSystem$delegate", "_isValid", "get_isValid", "()Z", "_isValid$delegate", "_children", Argument.Delimiters.none, "get_children", "()[Lcom/intellij/openapi/vfs/VirtualFile;", "_children$delegate", "getName", "getFileSystem", "getPath", "isWritable", "isDirectory", "isValid", "getParent", "getChildren", "getOutputStream", Argument.Delimiters.none, "p0", Argument.Delimiters.none, "p1", Argument.Delimiters.none, "p2", "contentsToByteArray", Argument.Delimiters.none, "getTimeStamp", "getLength", "refresh", Argument.Delimiters.none, "Ljava/lang/Runnable;", "getInputStream", "Ljava/io/InputStream;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CtSymDirectoryContainer extends VirtualFile {

    /* JADX INFO: renamed from: _children$delegate, reason: from kotlin metadata */
    private final Lazy _children;

    /* JADX INFO: renamed from: _fileSystem$delegate, reason: from kotlin metadata */
    private final Lazy _fileSystem;

    /* JADX INFO: renamed from: _isValid$delegate, reason: from kotlin metadata */
    private final Lazy _isValid;

    /* JADX INFO: renamed from: _path$delegate, reason: from kotlin metadata */
    private final Lazy _path;
    private final String currentPackage;
    private final String moduleRoot;
    private final Map<String, Boolean> packages;
    private final VirtualFile parent;
    private final List<VirtualFile> rootOrPackageParts;
    private final boolean skipPackageCheck;

    public CtSymDirectoryContainer(VirtualFile virtualFile, List<? extends VirtualFile> list, Map<String, Boolean> map, String str, String str2, boolean z) {
        list.getClass();
        map.getClass();
        str.getClass();
        str2.getClass();
        this.parent = virtualFile;
        this.rootOrPackageParts = list;
        this.packages = map;
        this.currentPackage = str;
        this.moduleRoot = str2;
        this.skipPackageCheck = z;
        this._path = LazyKt.lazy(new Function0() { // from class: c33
            public final Object invoke() {
                return CtSymDirectoryContainer.Ed(this.b);
            }
        });
        this._fileSystem = LazyKt.lazy(new Function0() { // from class: d33
            public final Object invoke() {
                return CtSymDirectoryContainer.Cd(this.b);
            }
        });
        this._isValid = LazyKt.lazy(new Function0() { // from class: e33
            public final Object invoke() {
                return Boolean.valueOf(CtSymDirectoryContainer.Bd(this.b));
            }
        });
        this._children = LazyKt.lazy(new Function0() { // from class: f33
            public final Object invoke() {
                return CtSymDirectoryContainer.Dd(this.b);
            }
        });
    }

    public static boolean Bd(CtSymDirectoryContainer ctSymDirectoryContainer) {
        List<VirtualFile> list = ctSymDirectoryContainer.rootOrPackageParts;
        if ((list instanceof Collection) && list.isEmpty()) {
            return true;
        }
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            if (!((VirtualFile) it.next()).isValid()) {
                return false;
            }
        }
        return true;
    }

    public static VirtualFileSystem Cd(CtSymDirectoryContainer ctSymDirectoryContainer) {
        VirtualFile virtualFile = ctSymDirectoryContainer.parent;
        virtualFile.getClass();
        return virtualFile.getFileSystem();
    }

    public static VirtualFile[] Dd(CtSymDirectoryContainer ctSymDirectoryContainer) {
        CtSymDirectoryContainer ctSymDirectoryContainer2;
        List listEmptyList;
        List<VirtualFile> list = ctSymDirectoryContainer.rootOrPackageParts;
        ArrayList<VirtualFile> arrayList = new ArrayList();
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            VirtualFile[] children = ((VirtualFile) it.next()).getChildren();
            if (children == null || (listEmptyList = ArraysKt.toList(children)) == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            CollectionsKt.addAll(arrayList, listEmptyList);
        }
        if (arrayList.isEmpty()) {
            return new VirtualFile[0];
        }
        boolean z = ctSymDirectoryContainer.skipPackageCheck || ctSymDirectoryContainer.packages.getOrDefault(ctSymDirectoryContainer.currentPackage, Boolean.FALSE).booleanValue();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        ArrayList arrayList2 = new ArrayList();
        for (VirtualFile virtualFile : arrayList) {
            CtSymDirectoryContainer ctSymClassVirtualFile = null;
            if (virtualFile.isDirectory()) {
                String name = ctSymDirectoryContainer.currentPackage.length() == 0 ? virtualFile.getName() : ctSymDirectoryContainer.currentPackage + '.' + virtualFile.getName();
                name.getClass();
                if (ctSymDirectoryContainer.skipPackageCheck || ctSymDirectoryContainer.packages.containsKey(name)) {
                    Object obj = linkedHashMap.get(name);
                    if (obj == null) {
                        ArrayList arrayList3 = new ArrayList();
                        ctSymDirectoryContainer2 = ctSymDirectoryContainer;
                        CtSymDirectoryContainer ctSymDirectoryContainer3 = new CtSymDirectoryContainer(ctSymDirectoryContainer2, arrayList3, ctSymDirectoryContainer.packages, name, ctSymDirectoryContainer.moduleRoot, ctSymDirectoryContainer.skipPackageCheck);
                        obj = TuplesKt.to(ctSymDirectoryContainer3, arrayList3);
                        linkedHashMap.put(name, obj);
                        ctSymClassVirtualFile = ctSymDirectoryContainer3;
                    } else {
                        ctSymDirectoryContainer2 = ctSymDirectoryContainer;
                    }
                    ((List) ((Pair) obj).getSecond()).add(virtualFile);
                } else {
                    ctSymDirectoryContainer2 = ctSymDirectoryContainer;
                }
            } else {
                ctSymDirectoryContainer2 = ctSymDirectoryContainer;
                if (z) {
                    ctSymClassVirtualFile = new CtSymClassVirtualFile(ctSymDirectoryContainer2, virtualFile);
                }
            }
            if (ctSymClassVirtualFile != null) {
                arrayList2.add(ctSymClassVirtualFile);
            }
            ctSymDirectoryContainer = ctSymDirectoryContainer2;
        }
        return (VirtualFile[]) arrayList2.toArray(new VirtualFile[0]);
    }

    public static String Ed(CtSymDirectoryContainer ctSymDirectoryContainer) {
        StringBuilder sb = new StringBuilder();
        VirtualFile virtualFile = ctSymDirectoryContainer.parent;
        sb.append(virtualFile != null ? virtualFile.getPath() : null);
        sb.append(ctSymDirectoryContainer.currentPackage);
        sb.append('/');
        return sb.toString();
    }

    private final VirtualFile[] get_children() {
        return (VirtualFile[]) this._children.getValue();
    }

    private final VirtualFileSystem get_fileSystem() {
        return (VirtualFileSystem) this._fileSystem.getValue();
    }

    private final boolean get_isValid() {
        return ((Boolean) this._isValid.getValue()).booleanValue();
    }

    private final String get_path() {
        return (String) this._path.getValue();
    }

    public byte[] contentsToByteArray() {
        throw new IllegalStateException("not supported");
    }

    public VirtualFile[] getChildren() {
        return get_children();
    }

    public VirtualFileSystem getFileSystem() {
        return get_fileSystem();
    }

    public InputStream getInputStream() {
        throw new IllegalStateException("not supported");
    }

    public /* bridge */ /* synthetic */ long getLength() {
        return ((Number) m24getLength()).longValue();
    }

    public String getName() {
        return StringsKt.substringAfterLast$default(this.currentPackage, ".", (String) null, 2, (Object) null);
    }

    /* JADX INFO: renamed from: getOutputStream, reason: collision with other method in class */
    public Void m25getOutputStream(Object p0, long p1, long p2) {
        throw new IllegalStateException("not supported");
    }

    public VirtualFile getParent() {
        return this.parent;
    }

    public String getPath() {
        return get_path();
    }

    public /* bridge */ /* synthetic */ long getTimeStamp() {
        return ((Number) m26getTimeStamp()).longValue();
    }

    public boolean isDirectory() {
        return true;
    }

    public boolean isValid() {
        return get_isValid();
    }

    public boolean isWritable() {
        return false;
    }

    public void refresh(boolean p0, boolean p1, Runnable p2) {
    }

    public /* bridge */ /* synthetic */ OutputStream getOutputStream(Object obj, long j, long j2) {
        return (OutputStream) m25getOutputStream(obj, j, j2);
    }

    /* JADX INFO: renamed from: getLength, reason: collision with other method in class */
    public Void m24getLength() {
        throw new IllegalStateException("not supported");
    }

    /* JADX INFO: renamed from: getTimeStamp, reason: collision with other method in class */
    public Void m26getTimeStamp() {
        throw new IllegalStateException("not supported");
    }
}
