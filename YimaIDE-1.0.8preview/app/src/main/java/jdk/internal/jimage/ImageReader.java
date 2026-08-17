package jdk.internal.jimage;

import defpackage.ek6;
import defpackage.sle;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ImageReader implements AutoCloseable {
    private volatile boolean closed;
    private final SharedImageReader reader;

    public static final class Directory extends Node {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private List<Node> children;

        private Directory(String str, BasicFileAttributes basicFileAttributes) {
            super(str, basicFileAttributes);
            this.children = null;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setChildren(List<Node> list) {
            this.children = Collections.unmodifiableList(list);
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public Stream<String> getChildNames() {
            List<Node> list = this.children;
            if (list != null) {
                return list.stream().map(new ek6());
            }
            sle.a("Cannot get child nodes of an incomplete directory: ", getName());
            return null;
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public boolean isCompleted() {
            return this.children != null;
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public boolean isDirectory() {
            return true;
        }
    }

    public static class LinkNode extends Node {
        private final Supplier<Node> link;

        private LinkNode(String str, Supplier<Node> supplier, BasicFileAttributes basicFileAttributes) {
            super(str, basicFileAttributes);
            this.link = supplier;
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public boolean isLink() {
            return true;
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public Node resolveLink(boolean z) {
            return this.link.get();
        }
    }

    public static class Resource extends Node {
        private final ImageLocation loc;

        private Resource(String str, ImageLocation imageLocation, BasicFileAttributes basicFileAttributes) {
            super(str, basicFileAttributes);
            this.loc = imageLocation;
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public long compressedSize() {
            return this.loc.getCompressedSize();
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public String extension() {
            return this.loc.getExtension();
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public ImageLocation getLocation() {
            return this.loc;
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public boolean isResource() {
            return true;
        }

        @Override // jdk.internal.jimage.ImageReader.Node
        public long size() {
            return this.loc.getUncompressedSize();
        }
    }

    public static final class SharedImageReader extends BasicImageReader {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final int INITIAL_NODE_CACHE_CAPACITY = 2000;
        private static final String MODULES_ROOT = "/modules";
        private static final Map<Path, SharedImageReader> OPEN_FILES = new HashMap();
        private static final String PACKAGES_ROOT = "/packages";
        private final BasicFileAttributes imageFileAttributes;
        private final int modulesStringOffset;
        private final Map<String, Node> nodes;
        private final Set<ImageReader> openers;
        private final int packagesStringOffset;

        private SharedImageReader(Path path, ByteOrder byteOrder) throws IOException {
            super(path, byteOrder);
            this.openers = new HashSet();
            this.imageFileAttributes = Files.readAttributes(path, BasicFileAttributes.class, new LinkOption[0]);
            HashMap map = new HashMap(INITIAL_NODE_CACHE_CAPACITY);
            this.nodes = map;
            this.modulesStringOffset = getModuleOffset("/modules/java.base");
            this.packagesStringOffset = getModuleOffset("/packages/java.lang");
            Directory directoryNewDirectory = newDirectory(PACKAGES_ROOT);
            map.put(directoryNewDirectory.getName(), directoryNewDirectory);
            Directory directoryNewDirectory2 = newDirectory(MODULES_ROOT);
            map.put(directoryNewDirectory2.getName(), directoryNewDirectory2);
            Directory directoryNewDirectory3 = newDirectory("/");
            directoryNewDirectory3.setChildren(Arrays.asList(directoryNewDirectory, directoryNewDirectory2));
            map.put(directoryNewDirectory3.getName(), directoryNewDirectory3);
        }

        private Node buildModulesNode(String str) {
            ImageLocation imageLocationFindLocation = findLocation(str);
            if (imageLocationFindLocation != null) {
                return completeModuleDirectory(newDirectory(str), imageLocationFindLocation);
            }
            ImageLocation imageLocationFindLocation2 = findLocation(str.substring(8));
            if (imageLocationFindLocation2 == null || !isResource(imageLocationFindLocation2)) {
                return null;
            }
            return lambda$completeModuleDirectory$0(str, imageLocationFindLocation2);
        }

        private Node buildPackagesNode(String str) {
            ImageLocation imageLocationFindLocation;
            int iIndexOf = str.indexOf(47, 10);
            if (iIndexOf == -1) {
                ImageLocation imageLocationFindLocation2 = findLocation(str);
                if (imageLocationFindLocation2 != null) {
                    return completePackageDirectory(newDirectory(str), imageLocationFindLocation2);
                }
                return null;
            }
            String strSubstring = str.substring(0, iIndexOf);
            if (this.nodes.containsKey(strSubstring) || (imageLocationFindLocation = findLocation(strSubstring)) == null) {
                return null;
            }
            this.nodes.put(strSubstring, completePackageDirectory(newDirectory(strSubstring), imageLocationFindLocation));
            return this.nodes.get(str);
        }

        private void completeDirectory(Directory directory) {
            String name = directory.getName();
            ImageLocation imageLocationFindLocation = findLocation(name);
            if (name.charAt(1) == 'm') {
                completeModuleDirectory(directory, imageLocationFindLocation);
            } else {
                completePackageDirectory(directory, imageLocationFindLocation);
            }
        }

        private Directory completeModuleDirectory(Directory directory, ImageLocation imageLocation) {
            directory.setChildren(createChildNodes(imageLocation, new Function() { // from class: jdk.internal.jimage.b
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.lambda$completeModuleDirectory$1((ImageLocation) obj);
                }
            }));
            return directory;
        }

        private Directory completePackageDirectory(Directory directory, ImageLocation imageLocation) {
            List<Node> listCreateChildNodes;
            if (directory.getName().equals(PACKAGES_ROOT)) {
                listCreateChildNodes = createChildNodes(imageLocation, new Function() { // from class: jdk.internal.jimage.e
                    @Override // java.util.function.Function
                    public final Object apply(Object obj) {
                        return this.b.lambda$completePackageDirectory$2((ImageLocation) obj);
                    }
                });
            } else {
                IntBuffer offsetBuffer = getOffsetBuffer(imageLocation);
                int iCapacity = offsetBuffer.capacity();
                ArrayList arrayList = new ArrayList(iCapacity / 2);
                for (int i = 1; i < iCapacity; i += 2) {
                    final String string = lambda$getResourceBuffer$3(offsetBuffer.get(i));
                    arrayList.add(this.nodes.computeIfAbsent(directory.getName() + "/" + string, new Function() { // from class: jdk.internal.jimage.f
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return this.b.lambda$completePackageDirectory$3(string, (String) obj);
                        }
                    }));
                }
                listCreateChildNodes = arrayList;
            }
            directory.setChildren(listCreateChildNodes);
            return directory;
        }

        private List<Node> createChildNodes(ImageLocation imageLocation, Function<ImageLocation, Node> function) {
            IntBuffer offsetBuffer = getOffsetBuffer(imageLocation);
            int iCapacity = offsetBuffer.capacity();
            ArrayList arrayList = new ArrayList(iCapacity);
            for (int i = 0; i < iCapacity; i++) {
                arrayList.add(function.apply(getLocation(offsetBuffer.get(i))));
            }
            return arrayList;
        }

        private int getModuleOffset(String str) {
            return findLocation(str).getModuleOffset();
        }

        private IntBuffer getOffsetBuffer(ImageLocation imageLocation) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(getResource(imageLocation));
            byteBufferWrap.order(getByteOrder());
            return byteBufferWrap.asIntBuffer();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public byte[] getResource(Node node) throws IOException {
            if (node.isResource()) {
                return super.getResource(node.getLocation());
            }
            r8g.a("Not a resource: ", node);
            return null;
        }

        private boolean isModulesSubdirectory(ImageLocation imageLocation) {
            if (imageLocation.getModuleOffset() == this.modulesStringOffset) {
                return true;
            }
            return $assertionsDisabled;
        }

        private boolean isResource(ImageLocation imageLocation) {
            int moduleOffset = imageLocation.getModuleOffset();
            if (moduleOffset == 0 || moduleOffset == this.modulesStringOffset || moduleOffset == this.packagesStringOffset) {
                return $assertionsDisabled;
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Node lambda$completeModuleDirectory$1(final ImageLocation imageLocation) {
            if (isModulesSubdirectory(imageLocation)) {
                return this.nodes.computeIfAbsent(imageLocation.getFullName(), new c(this));
            }
            return this.nodes.computeIfAbsent(imageLocation.getFullName(true), new Function() { // from class: jdk.internal.jimage.d
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.b.lambda$completeModuleDirectory$0(imageLocation, (String) obj);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Node lambda$completePackageDirectory$2(ImageLocation imageLocation) {
            return this.nodes.computeIfAbsent(imageLocation.getFullName(), new c(this));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ Node lambda$completePackageDirectory$3(String str, String str2) {
            return newLinkNode(str2, "/modules/" + str);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Directory newDirectory(String str) {
            return new Directory(str, this.imageFileAttributes);
        }

        private LinkNode newLinkNode(String str, final String str2) {
            return new LinkNode(str, new Supplier() { // from class: jdk.internal.jimage.a
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.b.lambda$newLinkNode$4(str2);
                }
            }, this.imageFileAttributes);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: newResource, reason: merged with bridge method [inline-methods] */
        public Resource lambda$completeModuleDirectory$0(String str, ImageLocation imageLocation) {
            return new Resource(str, imageLocation, this.imageFileAttributes);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static ImageReader open(Path path, ByteOrder byteOrder) throws IOException {
            ImageReader imageReader;
            Objects.requireNonNull(path);
            Objects.requireNonNull(byteOrder);
            Map<Path, SharedImageReader> map = OPEN_FILES;
            synchronized (map) {
                try {
                    SharedImageReader sharedImageReader = map.get(path);
                    if (sharedImageReader == null) {
                        sharedImageReader = new SharedImageReader(path, byteOrder);
                        map.put(path, sharedImageReader);
                    } else if (sharedImageReader.getByteOrder() != byteOrder) {
                        throw new IOException("\"" + sharedImageReader.getName() + "\" is not an image file");
                    }
                    imageReader = new ImageReader(sharedImageReader);
                    sharedImageReader.openers.add(imageReader);
                } catch (Throwable th) {
                    throw th;
                }
            }
            return imageReader;
        }

        public void close(ImageReader imageReader) throws IOException {
            Objects.requireNonNull(imageReader);
            Map<Path, SharedImageReader> map = OPEN_FILES;
            synchronized (map) {
                try {
                    if (!this.openers.remove(imageReader)) {
                        throw new IOException("image file already closed");
                    }
                    if (this.openers.isEmpty()) {
                        close();
                        this.nodes.clear();
                        if (!map.remove(getImagePath(), this)) {
                            throw new IOException("image file not found in open list");
                        }
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }

        public boolean containsResource(String str, String str2) {
            if (str.indexOf(47) >= 0) {
                w01.a("invalid module name: ".concat(str));
                return $assertionsDisabled;
            }
            ImageLocation imageLocationFindLocation = findLocation(str, str2);
            if (imageLocationFindLocation == null || !isResource(imageLocationFindLocation)) {
                return $assertionsDisabled;
            }
            return true;
        }

        /* JADX INFO: renamed from: findNode, reason: merged with bridge method [inline-methods] */
        public synchronized Node lambda$newLinkNode$4(String str) {
            Node nodeBuildPackagesNode;
            try {
                nodeBuildPackagesNode = this.nodes.get(str);
                if (nodeBuildPackagesNode == null) {
                    if (str.startsWith("/modules/")) {
                        nodeBuildPackagesNode = buildModulesNode(str);
                    } else if (str.startsWith("/packages/")) {
                        nodeBuildPackagesNode = buildPackagesNode(str);
                    }
                    if (nodeBuildPackagesNode != null) {
                        this.nodes.put(nodeBuildPackagesNode.getName(), nodeBuildPackagesNode);
                    }
                } else if (!nodeBuildPackagesNode.isCompleted()) {
                    completeDirectory((Directory) nodeBuildPackagesNode);
                }
            } catch (Throwable th) {
                throw th;
            }
            return nodeBuildPackagesNode;
        }

        public Node findResourceNode(String str, String str2) {
            if (str.indexOf(47) >= 0) {
                w01.a("invalid module name: ".concat(str));
                return null;
            }
            String str3 = "/modules/" + str + "/" + str2;
            synchronized (this) {
                try {
                    Node nodeLambda$completeModuleDirectory$0 = this.nodes.get(str3);
                    if (nodeLambda$completeModuleDirectory$0 != null) {
                        return nodeLambda$completeModuleDirectory$0.isResource() ? nodeLambda$completeModuleDirectory$0 : null;
                    }
                    ImageLocation imageLocationFindLocation = findLocation(str, str2);
                    if (imageLocationFindLocation != null && isResource(imageLocationFindLocation)) {
                        nodeLambda$completeModuleDirectory$0 = lambda$completeModuleDirectory$0(str3, imageLocationFindLocation);
                        this.nodes.put(nodeLambda$completeModuleDirectory$0.getName(), nodeLambda$completeModuleDirectory$0);
                    }
                    return nodeLambda$completeModuleDirectory$0;
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private ImageReader(SharedImageReader sharedImageReader) {
        this.reader = sharedImageReader;
    }

    private void ensureOpen() throws IOException {
        if (this.closed) {
            a16.a("image file closed");
        }
    }

    public static ImageReader open(Path path, ByteOrder byteOrder) throws IOException {
        Objects.requireNonNull(path);
        Objects.requireNonNull(byteOrder);
        return SharedImageReader.open(path, byteOrder);
    }

    public static void releaseByteBuffer(ByteBuffer byteBuffer) {
        BasicImageReader.releaseByteBuffer(byteBuffer);
    }

    private void requireOpen() {
        if (this.closed) {
            k2d.a("image file closed");
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.closed) {
            a16.a("image file already closed");
        } else {
            this.reader.close(this);
            this.closed = true;
        }
    }

    public boolean containsResource(String str, String str2) throws IOException {
        ensureOpen();
        return this.reader.containsResource(str, str2);
    }

    public Node findNode(String str) throws IOException {
        ensureOpen();
        return this.reader.lambda$newLinkNode$4(str);
    }

    public Node findResourceNode(String str, String str2) throws IOException {
        ensureOpen();
        return this.reader.findResourceNode(str, str2);
    }

    public byte[] getResource(Node node) throws IOException {
        ensureOpen();
        return this.reader.getResource(node);
    }

    public ByteBuffer getResourceBuffer(Node node) {
        requireOpen();
        if (node.isResource()) {
            return this.reader.getResourceBuffer(node.getLocation());
        }
        aca.a("Not a resource node: ", node);
        return null;
    }

    public static abstract class Node {
        private final BasicFileAttributes fileAttrs;
        private final String name;

        public Node(String str, BasicFileAttributes basicFileAttributes) {
            Objects.requireNonNull(str);
            this.name = str;
            Objects.requireNonNull(basicFileAttributes);
            this.fileAttrs = basicFileAttributes;
        }

        public long compressedSize() {
            return 0L;
        }

        public final boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj instanceof Node) {
                return this.name.equals(((Node) obj).name);
            }
            return false;
        }

        public String extension() {
            return null;
        }

        public Stream<String> getChildNames() {
            throw new IllegalStateException("not a directory: " + getName());
        }

        public final BasicFileAttributes getFileAttributes() {
            return this.fileAttrs;
        }

        public ImageLocation getLocation() {
            throw new IllegalStateException("not a resource: " + getName());
        }

        public final String getName() {
            return this.name;
        }

        public final int hashCode() {
            return this.name.hashCode();
        }

        public boolean isCompleted() {
            return true;
        }

        public boolean isDirectory() {
            return false;
        }

        public boolean isLink() {
            return false;
        }

        public boolean isResource() {
            return false;
        }

        public final Node resolveLink() {
            return resolveLink(false);
        }

        public long size() {
            return 0L;
        }

        public final String toString() {
            return getName();
        }

        public Node resolveLink(boolean z) {
            return this;
        }
    }

    public static ImageReader open(Path path) throws IOException {
        return open(path, ByteOrder.nativeOrder());
    }
}
