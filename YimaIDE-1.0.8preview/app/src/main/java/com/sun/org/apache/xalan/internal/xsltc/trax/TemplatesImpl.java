package com.sun.org.apache.xalan.internal.xsltc.trax;

import com.sun.org.apache.xalan.internal.utils.ObjectFactory;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.Translet;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Constants;
import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ErrorMsg;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.Serializable;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReader;
import java.lang.module.ModuleReference;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.nio.file.Path;
import java.security.AccessController;
import java.security.CodeSigner;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;
import javax.xml.XMLConstants;
import javax.xml.transform.Templates;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.URIResolver;
import jdk.xml.internal.SecuritySupport;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class TemplatesImpl implements Templates, Serializable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static String ABSTRACT_TRANSLET = "com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet";
    public static final String DESERIALIZE_TRANSLET = "jdk.xml.enableTemplatesImplDeserialization";
    private static final ObjectStreamField[] serialPersistentFields;
    static final long serialVersionUID = 673094361519270707L;
    private transient String _accessExternalStylesheet;
    private transient Map<String, Class<?>> _auxClasses;
    private byte[][] _bytecodes;
    private Class<?>[] _class;
    private int _indentNumber;
    private String _name;
    private Properties _outputProperties;
    private transient boolean _overrideDefaultParser;
    private transient ThreadLocal<DOM> _sdom;
    private transient TransformerFactoryImpl _tfactory;
    private int _transletIndex;
    private transient URIResolver _uriResolver;

    static {
        ObjectStreamField objectStreamField = new ObjectStreamField("_name", String.class);
        ObjectStreamField objectStreamField2 = new ObjectStreamField("_bytecodes", byte[][].class);
        ObjectStreamField objectStreamField3 = new ObjectStreamField("_class", Class[].class);
        Class cls = Integer.TYPE;
        serialPersistentFields = new ObjectStreamField[]{objectStreamField, objectStreamField2, objectStreamField3, new ObjectStreamField("_transletIndex", cls), new ObjectStreamField("_outputProperties", Properties.class), new ObjectStreamField("_indentNumber", cls)};
    }

    public TemplatesImpl(Class<?>[] clsArr, String str, Properties properties, int i, TransformerFactoryImpl transformerFactoryImpl) {
        this._name = null;
        this._bytecodes = null;
        this._class = null;
        this._transletIndex = -1;
        this._auxClasses = null;
        this._uriResolver = null;
        this._sdom = new ThreadLocal<>();
        this._tfactory = null;
        this._accessExternalStylesheet = "all";
        this._class = clsArr;
        this._transletIndex = 0;
        init(str, properties, i, transformerFactoryImpl);
    }

    public static /* synthetic */ ClassLoader b(ClassLoader classLoader, String str) {
        return classLoader;
    }

    private Module createModule(ModuleDescriptor moduleDescriptor, final ClassLoader classLoader) {
        final String strName = moduleDescriptor.name();
        final ModuleReference moduleReference = new ModuleReference(moduleDescriptor, null) { // from class: com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl.1
            public ModuleReader open() {
                throw new UnsupportedOperationException();
            }
        };
        ModuleFinder moduleFinder = new ModuleFinder() { // from class: com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl.2
            public Optional<ModuleReference> find(String str) {
                return str.equals(strName) ? Optional.of(moduleReference) : Optional.empty();
            }

            public Set<ModuleReference> findAll() {
                return Set.of(moduleReference);
            }
        };
        final ModuleLayer moduleLayerBoot = ModuleLayer.boot();
        final Configuration configurationResolve = moduleLayerBoot.configuration().resolve(moduleFinder, ModuleFinder.of(new Path[0]), Set.of(strName));
        return (Module) ((ModuleLayer) AccessController.doPrivileged(new PrivilegedAction() { // from class: o3e
            @Override // java.security.PrivilegedAction
            public final Object run() {
                return TemplatesImpl.lambda$createModule$1(moduleLayerBoot, configurationResolve, classLoader);
            }
        })).findModule(strName).get();
    }

    private void defineTransletClasses() throws TransformerConfigurationException {
        if (this._bytecodes == null) {
            throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.NO_TRANSLET_CLASS_ERR).toString());
        }
        TransletClassLoader transletClassLoader = (TransletClassLoader) AccessController.doPrivileged(new PrivilegedAction<TransletClassLoader>() { // from class: com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl.3
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // java.security.PrivilegedAction
            public TransletClassLoader run() {
                return new TransletClassLoader(ObjectFactory.findClassLoader(), TemplatesImpl.this._tfactory.getExternalExtensionsMap());
            }
        });
        try {
            int length = this._bytecodes.length;
            this._class = new Class[length];
            if (length > 1) {
                this._auxClasses = new HashMap();
            }
            final Module moduleCreateModule = createModule(ModuleDescriptor.newModule("jdk.translet", Set.of(ModuleDescriptor.Modifier.SYNTHETIC)).requires("java.xml").exports(this._tfactory.getPackageName(), Set.of("java.xml")).build(), transletClassLoader);
            final Module module = TemplatesImpl.class.getModule();
            final PermissionCollection permissionCollectionNewPermissionCollection = new RuntimePermission("*").newPermissionCollection();
            Arrays.asList(Constants.PKGS_USED_BY_TRANSLET_CLASSES).forEach(new Consumer() { // from class: p3e
                @Override // java.util.function.Consumer
                public final void accept(Object obj) {
                    TemplatesImpl.lambda$defineTransletClasses$2(module, moduleCreateModule, permissionCollectionNewPermissionCollection, (String) obj);
                }
            });
            ProtectionDomain protectionDomain = new ProtectionDomain(new CodeSource((URL) null, (CodeSigner[]) null), permissionCollectionNewPermissionCollection, transletClassLoader, null);
            module.addReads(moduleCreateModule);
            for (int i = 0; i < length; i++) {
                this._class[i] = transletClassLoader.defineClass(this._bytecodes[i], protectionDomain);
                if (this._class[i].getSuperclass().getName().equals(ABSTRACT_TRANSLET)) {
                    this._transletIndex = i;
                } else {
                    this._auxClasses.put(this._class[i].getName(), this._class[i]);
                }
            }
            if (this._transletIndex < 0) {
                throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.NO_MAIN_TRANSLET_ERR, this._name).toString());
            }
        } catch (ClassFormatError e) {
            throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.TRANSLET_CLASS_ERR, this._name).toString(), e);
        } catch (LinkageError e2) {
            throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.TRANSLET_OBJECT_ERR, this._name).toString(), e2);
        }
    }

    private synchronized byte[][] getTransletBytecodes() {
        return this._bytecodes;
    }

    private synchronized Class<?>[] getTransletClasses() {
        try {
            if (this._class == null) {
                defineTransletClasses();
            }
        } catch (TransformerConfigurationException unused) {
        }
        return this._class;
    }

    private Translet getTransletInstance() throws TransformerConfigurationException {
        try {
            if (this._name == null) {
                return null;
            }
            if (this._class == null) {
                defineTransletClasses();
            }
            AbstractTranslet abstractTranslet = (AbstractTranslet) this._class[this._transletIndex].getConstructor(null).newInstance(null);
            abstractTranslet.postInitialization();
            abstractTranslet.setTemplates(this);
            abstractTranslet.setOverrideDefaultParser(this._overrideDefaultParser);
            abstractTranslet.setAllowedProtocols(this._accessExternalStylesheet);
            Map<String, Class<?>> map = this._auxClasses;
            if (map != null) {
                abstractTranslet.setAuxiliaryClasses(map);
            }
            return abstractTranslet;
        } catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            throw new TransformerConfigurationException(new ErrorMsg(ErrorMsg.TRANSLET_OBJECT_ERR, this._name).toString(), e);
        }
    }

    private void init(String str, Properties properties, int i, TransformerFactoryImpl transformerFactoryImpl) {
        this._name = str;
        this._outputProperties = properties;
        this._indentNumber = i;
        this._tfactory = transformerFactoryImpl;
        this._overrideDefaultParser = transformerFactoryImpl.overrideDefaultParser();
        this._accessExternalStylesheet = (String) transformerFactoryImpl.getAttribute(XMLConstants.ACCESS_EXTERNAL_STYLESHEET);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ModuleLayer lambda$createModule$1(ModuleLayer moduleLayer, Configuration configuration, final ClassLoader classLoader) {
        return moduleLayer.defineModules(configuration, new Function() { // from class: n3e
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return TemplatesImpl.b(classLoader, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$defineTransletClasses$2(Module module, Module module2, PermissionCollection permissionCollection, String str) {
        module.addExports(str, module2);
        permissionCollection.add(new RuntimePermission("accessClassInPackage." + str));
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        String systemProperty;
        if (System.getSecurityManager() != null && ((systemProperty = SecuritySupport.getSystemProperty(DESERIALIZE_TRANSLET)) == null || (systemProperty.length() != 0 && !systemProperty.equalsIgnoreCase("true")))) {
            throw new UnsupportedOperationException(new ErrorMsg(ErrorMsg.DESERIALIZE_TRANSLET_ERR).toString());
        }
        ObjectInputStream.GetField fields = objectInputStream.readFields();
        this._name = (String) fields.get("_name", (Object) null);
        this._bytecodes = (byte[][]) fields.get("_bytecodes", (Object) null);
        this._class = (Class[]) fields.get("_class", (Object) null);
        this._transletIndex = fields.get("_transletIndex", -1);
        this._outputProperties = (Properties) fields.get("_outputProperties", (Object) null);
        this._indentNumber = fields.get("_indentNumber", 0);
        if (objectInputStream.readBoolean()) {
            this._uriResolver = (URIResolver) objectInputStream.readObject();
        }
        this._tfactory = new TransformerFactoryImpl();
    }

    private synchronized void setTransletBytecodes(byte[][] bArr) {
        this._bytecodes = bArr;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        if (this._auxClasses != null) {
            throw new NotSerializableException("com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable");
        }
        ObjectOutputStream.PutField putFieldPutFields = objectOutputStream.putFields();
        putFieldPutFields.put("_name", this._name);
        putFieldPutFields.put("_bytecodes", this._bytecodes);
        putFieldPutFields.put("_class", this._class);
        putFieldPutFields.put("_transletIndex", this._transletIndex);
        putFieldPutFields.put("_outputProperties", this._outputProperties);
        putFieldPutFields.put("_indentNumber", this._indentNumber);
        objectOutputStream.writeFields();
        if (!(this._uriResolver instanceof Serializable)) {
            objectOutputStream.writeBoolean(false);
        } else {
            objectOutputStream.writeBoolean(true);
            objectOutputStream.writeObject((Serializable) this._uriResolver);
        }
    }

    @Override // javax.xml.transform.Templates
    public synchronized Properties getOutputProperties() {
        try {
        } catch (TransformerConfigurationException unused) {
            return null;
        }
        return newTransformer().getOutputProperties();
    }

    public DOM getStylesheetDOM() {
        return this._sdom.get();
    }

    public synchronized int getTransletIndex() {
        try {
            if (this._class == null) {
                defineTransletClasses();
            }
        } catch (TransformerConfigurationException unused) {
        }
        return this._transletIndex;
    }

    public synchronized String getTransletName() {
        return this._name;
    }

    @Override // javax.xml.transform.Templates
    public synchronized Transformer newTransformer() throws TransformerConfigurationException {
        TransformerImpl transformerImpl;
        try {
            transformerImpl = new TransformerImpl(getTransletInstance(), this._outputProperties, this._indentNumber, this._tfactory);
            URIResolver uRIResolver = this._uriResolver;
            if (uRIResolver != null) {
                transformerImpl.setURIResolver(uRIResolver);
            }
            if (this._tfactory.getFeature("http://javax.xml.XMLConstants/feature/secure-processing")) {
                transformerImpl.setSecureProcessing(true);
            }
        } catch (Throwable th) {
            throw th;
        }
        return transformerImpl;
    }

    public boolean overrideDefaultParser() {
        return this._overrideDefaultParser;
    }

    public void setStylesheetDOM(DOM dom) {
        this._sdom.set(dom);
    }

    public synchronized void setTransletName(String str) {
        this._name = str;
    }

    public synchronized void setURIResolver(URIResolver uRIResolver) {
        this._uriResolver = uRIResolver;
    }

    public static final class TransletClassLoader extends ClassLoader {
        private final Map<String, Class<?>> _loadedExternalExtensionFunctions;

        public TransletClassLoader(ClassLoader classLoader) {
            super(classLoader);
            this._loadedExternalExtensionFunctions = null;
        }

        public Class<?> defineClass(byte[] bArr, ProtectionDomain protectionDomain) {
            return defineClass(null, bArr, 0, bArr.length, protectionDomain);
        }

        @Override // java.lang.ClassLoader
        public Class<?> loadClass(String str) throws ClassNotFoundException {
            Map<String, Class<?>> map = this._loadedExternalExtensionFunctions;
            Class<?> cls = map != null ? map.get(str) : null;
            return cls == null ? super.loadClass(str) : cls;
        }

        public TransletClassLoader(ClassLoader classLoader, Map<String, Class<?>> map) {
            super(classLoader);
            this._loadedExternalExtensionFunctions = map;
        }

        public Class<?> defineClass(byte[] bArr) {
            return defineClass(null, bArr, 0, bArr.length);
        }
    }

    public TemplatesImpl(byte[][] bArr, String str, Properties properties, int i, TransformerFactoryImpl transformerFactoryImpl) {
        this._name = null;
        this._bytecodes = null;
        this._class = null;
        this._transletIndex = -1;
        this._auxClasses = null;
        this._uriResolver = null;
        this._sdom = new ThreadLocal<>();
        this._tfactory = null;
        this._accessExternalStylesheet = "all";
        this._bytecodes = bArr;
        init(str, properties, i, transformerFactoryImpl);
    }

    public TemplatesImpl() {
        this._name = null;
        this._bytecodes = null;
        this._class = null;
        this._transletIndex = -1;
        this._auxClasses = null;
        this._uriResolver = null;
        this._sdom = new ThreadLocal<>();
        this._tfactory = null;
        this._accessExternalStylesheet = "all";
    }
}
