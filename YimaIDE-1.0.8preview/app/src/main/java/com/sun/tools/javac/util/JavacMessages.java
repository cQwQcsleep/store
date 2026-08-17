package com.sun.tools.javac.util;

import com.sun.tools.javac.api.DiagnosticFormatter;
import com.sun.tools.javac.api.Messages;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.Options;
import java.lang.ref.SoftReference;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class JavacMessages implements Messages {
    private static ResourceBundle defaultBundle = null;
    private static final String defaultBundleName = "com.sun.tools.javac.resources.compiler";
    private static JavacMessages defaultMessages;
    public static final Context.Key<JavacMessages> messagesKey = new Context.Key<>();
    private Map<Locale, SoftReference<List<ResourceBundle>>> bundleCache;
    private List<ResourceBundleHelper> bundleHelpers;
    Context context;
    private List<ResourceBundle> currentBundles;
    private Locale currentLocale;
    private JCDiagnostic.Factory diagFactory;
    private DiagnosticFormatter<JCDiagnostic> diagFormatter;

    public interface ResourceBundleHelper {
        ResourceBundle getResourceBundle(Locale locale);
    }

    public JavacMessages(Context context) {
        this(defaultBundleName, (Locale) context.get(Locale.class));
        this.context = context;
        context.put(messagesKey, this);
        this.diagFormatter = new BasicDiagnosticFormatter(this);
        Options.instance(context).whenReady(new Consumer() { // from class: nm7
            @Override // java.util.function.Consumer
            public final void accept(Object obj) {
                this.b.initOptions((Options) obj);
            }
        });
    }

    public static ResourceBundle getDefaultBundle() {
        try {
            if (defaultBundle == null) {
                defaultBundle = ResourceBundle.getBundle(defaultBundleName);
            }
            return defaultBundle;
        } catch (MissingResourceException e) {
            throw new Error("Fatal: Resource for compiler is missing", e);
        }
    }

    public static String getDefaultLocalizedString(String str, Object... objArr) {
        return getLocalizedString((List<ResourceBundle>) List.of(getDefaultBundle()), str, objArr);
    }

    @Deprecated
    public static JavacMessages getDefaultMessages() {
        if (defaultMessages == null) {
            defaultMessages = new JavacMessages(defaultBundleName);
        }
        return defaultMessages;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private String getLocalizedString(List<ResourceBundle> list, JCDiagnostic.DiagnosticInfo diagnosticInfo) {
        String string = null;
        for (List list2 = list; list2.nonEmpty() && string == null; list2 = list2.tail) {
            try {
                string = ((ResourceBundle) list2.head).getString(diagnosticInfo.key());
            } catch (MissingResourceException unused) {
            }
        }
        if (string == null) {
            string = "compiler message file broken: key=" + diagnosticInfo.key() + " arguments={0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}";
        }
        return diagnosticInfo == CompilerProperties.Errors.Error ? MessageFormat.format(string, new Object[0]) : this.diagFormatter.format(getDiagFactory().create(DiagnosticSource.NO_SOURCE, null, diagnosticInfo), getCurrentLocale());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initOptions(Options options) {
        this.diagFormatter = options.isSet("rawDiagnostics") ? new RawDiagnosticFormatter(options) : new BasicDiagnosticFormatter(options, this);
    }

    public static JavacMessages instance(Context context) {
        JavacMessages javacMessages = (JavacMessages) context.get(messagesKey);
        return javacMessages == null ? new JavacMessages(context) : javacMessages;
    }

    public void add(ResourceBundleHelper resourceBundleHelper) {
        this.bundleHelpers = this.bundleHelpers.prepend(resourceBundleHelper);
        if (!this.bundleCache.isEmpty()) {
            this.bundleCache.clear();
        }
        this.currentBundles = null;
    }

    public List<ResourceBundle> getBundles(Locale locale) {
        List<ResourceBundle> list;
        if (locale == this.currentLocale && (list = this.currentBundles) != null) {
            return list;
        }
        SoftReference<List<ResourceBundle>> softReference = this.bundleCache.get(locale);
        List<ResourceBundle> listNil = softReference == null ? null : softReference.get();
        if (listNil == null) {
            listNil = List.nil();
            Iterator<ResourceBundleHelper> it = this.bundleHelpers.iterator();
            while (it.hasNext()) {
                try {
                    listNil = listNil.prepend(it.next().getResourceBundle(locale));
                } catch (MissingResourceException e) {
                    throw new InternalError("Cannot find requested resource bundle for locale " + locale, e);
                }
            }
            this.bundleCache.put(locale, new SoftReference<>(listNil));
        }
        return listNil;
    }

    public Locale getCurrentLocale() {
        return this.currentLocale;
    }

    public JCDiagnostic.Factory getDiagFactory() {
        if (this.diagFactory == null) {
            this.diagFactory = JCDiagnostic.Factory.instance(this.context);
        }
        return this.diagFactory;
    }

    public void setCurrentLocale(Locale locale) {
        if (locale == null) {
            locale = Locale.getDefault();
        }
        this.currentBundles = getBundles(locale);
        this.currentLocale = locale;
    }

    @Override // com.sun.tools.javac.api.Messages
    public void add(final String str) throws MissingResourceException {
        add(new ResourceBundleHelper() { // from class: om7
            @Override // com.sun.tools.javac.util.JavacMessages.ResourceBundleHelper
            public final ResourceBundle getResourceBundle(Locale locale) {
                return ResourceBundle.getBundle(str, locale);
            }
        });
    }

    public JavacMessages(String str) throws MissingResourceException {
        this(str, null);
    }

    public JavacMessages(String str, Locale locale) throws MissingResourceException {
        this.bundleHelpers = List.nil();
        this.bundleCache = new HashMap();
        add(str);
        setCurrentLocale(locale);
    }

    public JavacMessages() throws MissingResourceException {
        this(defaultBundleName);
    }

    public String getLocalizedString(JCDiagnostic.DiagnosticInfo diagnosticInfo) {
        return getLocalizedString(this.currentLocale, diagnosticInfo);
    }

    @Override // com.sun.tools.javac.api.Messages
    public String getLocalizedString(Locale locale, String str, Object... objArr) {
        if (locale == null) {
            locale = getCurrentLocale();
        }
        return getLocalizedString(getBundles(locale), str, objArr);
    }

    public String getLocalizedString(Locale locale, JCDiagnostic.DiagnosticInfo diagnosticInfo) {
        if (locale == null) {
            locale = getCurrentLocale();
        }
        return getLocalizedString(getBundles(locale), diagnosticInfo);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static String getLocalizedString(List<ResourceBundle> list, String str, Object... objArr) {
        String string = null;
        for (List list2 = list; list2.nonEmpty() && string == null; list2 = list2.tail) {
            try {
                string = ((ResourceBundle) list2.head).getString(str);
            } catch (MissingResourceException unused) {
            }
        }
        if (string == null) {
            string = "compiler message file broken: key=" + str + " arguments={0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}";
        }
        return MessageFormat.format(string, objArr);
    }

    public String getLocalizedString(String str, Object... objArr) {
        return getLocalizedString(this.currentLocale, str, objArr);
    }
}
