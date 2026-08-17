package com.sun.tools.javac.file;

import com.sun.tools.javac.code.Lint;
import com.sun.tools.javac.main.Option;
import com.sun.tools.javac.main.OptionHelper;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import defpackage.nrd;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.IllegalCharsetNameException;
import java.nio.charset.UnsupportedCharsetException;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class BaseFileManager implements JavaFileManager {
    private static final byte[] EMPTY_ARRAY = new byte[0];
    protected static final Set<Option> javacFileManagerOptions = Option.getJavacFileManagerOptions();
    public boolean autoClose;
    private byte[] byteArrayCache;
    protected Charset charset;
    private String defaultEncodingName;
    private String encodingName;
    protected Lint lint;
    public Log log;
    protected String multiReleaseValue;
    protected Options options;
    private final HashSet<Path> outputFilesWritten = new HashSet<>();
    private long lastUsedTime = System.currentTimeMillis();
    protected long deferredCloseTimeout = 0;
    protected final Map<JavaFileObject, ContentCacheEntry> contentCache = new HashMap();
    protected final Locations locations = createLocations();

    /* JADX INFO: renamed from: com.sun.tools.javac.file.BaseFileManager$3, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$sun$tools$javac$main$Option;

        static {
            int[] iArr = new int[Option.values().length];
            $SwitchMap$com$sun$tools$javac$main$Option = iArr;
            try {
                iArr[Option.ENCODING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$sun$tools$javac$main$Option[Option.MULTIRELEASE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class ContentCacheEntry {
        final SoftReference<CharBuffer> ref;
        final long timestamp;

        public ContentCacheEntry(JavaFileObject javaFileObject, CharBuffer charBuffer) {
            this.timestamp = javaFileObject.getLastModified();
            this.ref = new SoftReference<>(charBuffer);
        }

        public CharBuffer getValue() {
            return this.ref.get();
        }

        public boolean isValid(JavaFileObject javaFileObject) {
            return this.timestamp == javaFileObject.getLastModified();
        }
    }

    public BaseFileManager(Charset charset) {
        this.charset = charset;
    }

    private String getDefaultEncodingName() {
        if (this.defaultEncodingName == null) {
            this.defaultEncodingName = Charset.defaultCharset().name();
        }
        return this.defaultEncodingName;
    }

    public static JavaFileObject.Kind getKind(String str) {
        JavaFileObject.Kind kind = JavaFileObject.Kind.CLASS;
        if (str.endsWith(kind.extension)) {
            return kind;
        }
        JavaFileObject.Kind kind2 = JavaFileObject.Kind.SOURCE;
        if (str.endsWith(kind2.extension)) {
            return kind2;
        }
        JavaFileObject.Kind kind3 = JavaFileObject.Kind.HTML;
        return str.endsWith(kind3.extension) ? kind3 : JavaFileObject.Kind.OTHER;
    }

    public static <T> Collection<T> nullCheck(Collection<T> collection) {
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            Objects.requireNonNull(it.next());
        }
        return collection;
    }

    public void applyOptions(Options options) {
        String str = options.get("fileManager.deferClose");
        if (str != null) {
            try {
                this.deferredCloseTimeout = (int) (Float.parseFloat(str) * 1000.0f);
            } catch (NumberFormatException unused) {
                this.deferredCloseTimeout = 60000L;
            }
        }
    }

    public void cache(JavaFileObject javaFileObject, CharBuffer charBuffer) {
        this.contentCache.put(javaFileObject, new ContentCacheEntry(javaFileObject, charBuffer));
    }

    public void clear() {
        new HashSet(this.options.keySet()).forEach(new Consumer() { // from class: bo0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.options.remove((String) obj);
            }
        });
    }

    public Locations createLocations() {
        return new Locations();
    }

    public CharBuffer decode(ByteBuffer byteBuffer, boolean z) {
        String encodingName = getEncodingName();
        try {
            CharsetDecoder decoder = getDecoder(encodingName, z);
            CharBuffer charBufferAllocate = CharBuffer.allocate(((int) (byteBuffer.remaining() * ((decoder.averageCharsPerByte() * 0.8f) + (decoder.maxCharsPerByte() * 0.2f)))) + 10);
            while (true) {
                CoderResult coderResultDecode = decoder.decode(byteBuffer, charBufferAllocate, true);
                charBufferAllocate.flip();
                if (coderResultDecode.isUnderflow()) {
                    if (charBufferAllocate.limit() != charBufferAllocate.capacity()) {
                        return charBufferAllocate;
                    }
                    CharBuffer charBufferPut = CharBuffer.allocate(charBufferAllocate.capacity() + 1).put(charBufferAllocate);
                    charBufferPut.flip();
                    return charBufferPut;
                }
                if (coderResultDecode.isOverflow()) {
                    charBufferAllocate = CharBuffer.allocate(charBufferAllocate.capacity() + 10 + ((int) (byteBuffer.remaining() * decoder.maxCharsPerByte()))).put(charBufferAllocate);
                } else {
                    if (!coderResultDecode.isMalformed() && !coderResultDecode.isUnmappable()) {
                        x01.a(coderResultDecode);
                        return null;
                    }
                    StringBuilder sb = new StringBuilder();
                    int length = coderResultDecode.length();
                    for (int i = 0; i < length; i++) {
                        sb.append(String.format("%02X", Byte.valueOf(byteBuffer.get())));
                    }
                    Charset charset = this.charset;
                    this.log.error(charBufferAllocate.limit(), CompilerProperties.Errors.IllegalCharForEncoding(sb.toString(), charset == null ? encodingName : charset.name()));
                    charBufferAllocate.position(charBufferAllocate.limit());
                    charBufferAllocate.limit(charBufferAllocate.capacity());
                    charBufferAllocate.put((char) 65533);
                }
            }
        } catch (IllegalCharsetNameException | UnsupportedCharsetException unused) {
            this.log.error(CompilerProperties.Errors.UnsupportedEncoding(encodingName));
            return (CharBuffer) CharBuffer.allocate(1).flip();
        }
    }

    public void deferredClose() {
        Thread thread = new Thread(getClass().getName().concat(" DeferredClose")) { // from class: com.sun.tools.javac.file.BaseFileManager.1
            @Override // java.lang.Thread, java.lang.Runnable
            public void run() {
                try {
                    synchronized (BaseFileManager.this) {
                        try {
                            long jCurrentTimeMillis = System.currentTimeMillis();
                            while (true) {
                                long j = BaseFileManager.this.lastUsedTime;
                                BaseFileManager baseFileManager = BaseFileManager.this;
                                if (jCurrentTimeMillis < j + baseFileManager.deferredCloseTimeout) {
                                    baseFileManager.wait((baseFileManager.lastUsedTime + BaseFileManager.this.deferredCloseTimeout) - jCurrentTimeMillis);
                                    jCurrentTimeMillis = System.currentTimeMillis();
                                } else {
                                    baseFileManager.deferredCloseTimeout = 0L;
                                    baseFileManager.close();
                                }
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                } catch (IOException | InterruptedException unused) {
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
    }

    public void flushCache(JavaFileObject javaFileObject) {
        this.contentCache.remove(javaFileObject);
    }

    public CharBuffer getCachedContent(JavaFileObject javaFileObject) {
        ContentCacheEntry contentCacheEntry = this.contentCache.get(javaFileObject);
        if (contentCacheEntry == null) {
            return null;
        }
        if (contentCacheEntry.isValid(javaFileObject)) {
            return contentCacheEntry.getValue();
        }
        this.contentCache.remove(javaFileObject);
        return null;
    }

    public ClassLoader getClassLoader(URL[] urlArr) {
        ClassLoader classLoader = getClass().getClassLoader();
        String str = this.options.get("procloader");
        if (str != null) {
            try {
                return (ClassLoader) Class.forName(str).asSubclass(ClassLoader.class).getConstructor(URL[].class, ClassLoader.class).newInstance(urlArr, classLoader);
            } catch (ReflectiveOperationException unused) {
            }
        }
        return new URLClassLoader(urlArr, classLoader);
    }

    public CharsetDecoder getDecoder(String str, boolean z) {
        Charset charsetForName = this.charset;
        if (charsetForName == null) {
            charsetForName = Charset.forName(str);
        }
        CharsetDecoder charsetDecoderNewDecoder = charsetForName.newDecoder();
        CodingErrorAction codingErrorAction = z ? CodingErrorAction.REPLACE : CodingErrorAction.REPORT;
        return charsetDecoderNewDecoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
    }

    public String getEncodingName() {
        String str = this.encodingName;
        return str != null ? str : getDefaultEncodingName();
    }

    public boolean handleOption(Option option, String str) {
        int i = AnonymousClass3.$SwitchMap$com$sun$tools$javac$main$Option[option.ordinal()];
        if (i == 1) {
            this.encodingName = str;
            return true;
        }
        if (i != 2) {
            return this.locations.handleOption(option, str);
        }
        this.multiReleaseValue = str;
        this.locations.setMultiReleaseValue(str);
        return true;
    }

    public boolean handleOptions(Map<Option, String> map) {
        boolean zHandleOption = true;
        for (Map.Entry<Option, String> entry : map.entrySet()) {
            try {
                zHandleOption &= handleOption(entry.getKey(), entry.getValue());
            } catch (IllegalArgumentException e) {
                this.log.error(CompilerProperties.Errors.IllegalArgumentForOption(entry.getKey().getPrimaryName(), e.getMessage()));
                zHandleOption = false;
            }
        }
        return zHandleOption;
    }

    public boolean isDefaultBootClassPath() {
        return this.locations.isDefaultBootClassPath();
    }

    public boolean isDefaultSystemModulesPath() {
        return this.locations.isDefaultSystemModulesPath();
    }

    @Override // javax.tools.OptionChecker
    public int isSupportedOption(String str) {
        Option optionLookup = Option.lookup(str, javacFileManagerOptions);
        if (optionLookup == null) {
            return -1;
        }
        return optionLookup.hasArg() ? 1 : 0;
    }

    public ByteBuffer makeByteBuffer(InputStream inputStream) throws IOException {
        byte[] bArr;
        synchronized (this) {
            try {
                bArr = this.byteArrayCache;
                if (bArr != null) {
                    this.byteArrayCache = null;
                } else {
                    bArr = EMPTY_ARRAY;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        com.sun.tools.javac.util.ByteBuffer byteBuffer = new com.sun.tools.javac.util.ByteBuffer(bArr);
        byteBuffer.appendStream(inputStream);
        return byteBuffer.asByteBuffer();
    }

    public synchronized void newOutputToPath(Path path) throws IOException {
        if (this.lint.isEnabled(Lint.LintCategory.OUTPUT_FILE_CLASH)) {
            try {
                if (!this.outputFilesWritten.add(path.toRealPath(new LinkOption[0]))) {
                    this.log.warning(CompilerProperties.LintWarnings.OutputFileClash(path));
                }
            } catch (NoSuchFileException unused) {
            }
        }
    }

    public void recycleByteBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer.hasArray()) {
            synchronized (this) {
                this.byteArrayCache = byteBuffer.array();
            }
        }
    }

    public synchronized void resetOutputFilesWritten() {
        this.outputFilesWritten.clear();
    }

    public void setContext(Context context) {
        this.log = Log.instance(context);
        this.lint = Lint.instance(context);
        this.options = Options.instance(context);
        this.locations.update(this.log, FSInfo.instance(context));
        this.options.whenReady(new Consumer() { // from class: ao0
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.applyOptions((Options) obj);
            }
        });
    }

    public synchronized void updateLastUsedTime() {
        if (this.deferredCloseTimeout > 0) {
            this.lastUsedTime = System.currentTimeMillis();
        }
    }

    public static <T> T nullCheck(T t) {
        Objects.requireNonNull(t);
        return t;
    }

    @Override // javax.tools.JavaFileManager
    public boolean handleOption(String str, Iterator<String> it) {
        OptionHelper.GrumpyHelper grumpyHelper = new OptionHelper.GrumpyHelper(this.log) { // from class: com.sun.tools.javac.file.BaseFileManager.2
            @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
            public String get(Option option) {
                return BaseFileManager.this.options.get(option);
            }

            @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
            public boolean handleFileManagerOption(Option option, String str2) {
                return BaseFileManager.this.handleOption(option, str2);
            }

            @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
            public void initialize() {
                BaseFileManager.this.options.initialize();
            }

            @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
            public void put(String str2, String str3) {
                BaseFileManager.this.options.put(str2, str3);
            }

            @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
            public void remove(String str2) {
                BaseFileManager.this.options.remove(str2);
            }
        };
        Option optionLookup = Option.lookup(str, javacFileManagerOptions);
        if (optionLookup == null) {
            return false;
        }
        try {
            optionLookup.handleOption(grumpyHelper, str, it);
            return true;
        } catch (Option.InvalidValueException e) {
            nrd.a(e.getMessage(), e);
            return false;
        }
    }

    public static JavaFileObject.Kind getKind(Path path) {
        return getKind(path.getFileName().toString());
    }
}
