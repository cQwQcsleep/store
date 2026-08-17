package org.jetbrains.kotlin.cli.common.modules;

import com.intellij.util.SmartList;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSeverity;
import org.jetbrains.kotlin.cli.common.messages.MessageCollector;
import org.jetbrains.kotlin.cli.common.messages.MessageCollectorUtil;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.modules.JavaRootPath;
import org.jetbrains.kotlin.modules.Module;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class ModuleXmlParser {
    public static final String CLASSPATH = "classpath";
    public static final String COMMON_SOURCES = "commonSources";
    public static final String FRIEND_DIR = "friendDir";
    public static final String JAVA_SOURCE_PACKAGE_PREFIX = "packagePrefix";
    public static final String JAVA_SOURCE_ROOTS = "javaSourceRoots";
    public static final String MODULAR_JDK_ROOT = "modularJdkRoot";
    public static final String MODULE = "module";
    public static final String MODULES = "modules";
    public static final String NAME = "name";
    public static final String OUTPUT_DIR = "outputDir";
    public static final String PATH = "path";
    public static final String SOURCES = "sources";
    public static final String TYPE = "type";
    public static final String TYPE_PRODUCTION = "java-production";
    public static final String TYPE_TEST = "java-test";
    private DefaultHandler currentState;
    private final DefaultHandler initial;
    private final DefaultHandler insideModules;
    private final MessageCollector messageCollector;
    private final List<Module> modules;

    public class InsideModule extends DefaultHandler {
        private final ModuleBuilder moduleBuilder;
        final /* synthetic */ ModuleXmlParser this$0;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 1:
                    objArr[0] = "uri";
                    break;
                case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                case 5:
                    objArr[0] = "localName";
                    break;
                case 3:
                case 6:
                    objArr[0] = "qName";
                    break;
                case 4:
                    objArr[0] = "attributes";
                    break;
                default:
                    objArr[0] = ModuleXmlParser.TYPE;
                    break;
            }
            objArr[1] = "org/jetbrains/kotlin/cli/common/modules/ModuleXmlParser$InsideModule";
            switch (i) {
                case 1:
                case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                case 3:
                case 4:
                    objArr[2] = "startElement";
                    break;
                case 5:
                case 6:
                    objArr[2] = "endElement";
                    break;
                default:
                    objArr[2] = "<init>";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private InsideModule(ModuleXmlParser moduleXmlParser, String str, String str2, String str3) {
            if (str3 == null) {
                $$$reportNull$$$0(0);
            }
            this.this$0 = moduleXmlParser;
            ModuleBuilder moduleBuilder = new ModuleBuilder(str, str2, str3);
            this.moduleBuilder = moduleBuilder;
            moduleXmlParser.modules.add(moduleBuilder);
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void endElement(String str, String str2, String str3) throws SAXException {
            if (str2 == null) {
                $$$reportNull$$$0(5);
            }
            if (str3 == null) {
                $$$reportNull$$$0(6);
            }
            if (ModuleXmlParser.MODULE.equalsIgnoreCase(str3)) {
                ModuleXmlParser moduleXmlParser = this.this$0;
                moduleXmlParser.setCurrentState(moduleXmlParser.insideModules);
            }
        }

        @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
        public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
            if (str == null) {
                $$$reportNull$$$0(1);
            }
            if (str2 == null) {
                $$$reportNull$$$0(2);
            }
            if (str3 == null) {
                $$$reportNull$$$0(3);
            }
            if (attributes == null) {
                $$$reportNull$$$0(4);
            }
            if (ModuleXmlParser.SOURCES.equalsIgnoreCase(str3)) {
                this.moduleBuilder.addSourceFiles(ModuleXmlParser.getAttribute(attributes, ModuleXmlParser.PATH, str3));
                return;
            }
            if (ModuleXmlParser.COMMON_SOURCES.equalsIgnoreCase(str3)) {
                this.moduleBuilder.addCommonSourceFiles(ModuleXmlParser.getAttribute(attributes, ModuleXmlParser.PATH, str3));
                return;
            }
            if (ModuleXmlParser.FRIEND_DIR.equalsIgnoreCase(str3)) {
                this.moduleBuilder.addFriendDir(ModuleXmlParser.getAttribute(attributes, ModuleXmlParser.PATH, str3));
                return;
            }
            if (ModuleXmlParser.CLASSPATH.equalsIgnoreCase(str3)) {
                this.moduleBuilder.addClasspathEntry(ModuleXmlParser.getAttribute(attributes, ModuleXmlParser.PATH, str3));
            } else if (ModuleXmlParser.JAVA_SOURCE_ROOTS.equalsIgnoreCase(str3)) {
                this.moduleBuilder.addJavaSourceRoot(new JavaRootPath(ModuleXmlParser.getAttribute(attributes, ModuleXmlParser.PATH, str3), ModuleXmlParser.getNullableAttribute(attributes, ModuleXmlParser.JAVA_SOURCE_PACKAGE_PREFIX)));
            } else {
                if (!ModuleXmlParser.MODULAR_JDK_ROOT.equalsIgnoreCase(str3)) {
                    throw ModuleXmlParser.createError(str3);
                }
                this.moduleBuilder.setModularJdkRoot(ModuleXmlParser.getAttribute(attributes, ModuleXmlParser.PATH, str3));
            }
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 2 || i == 3 || i == 7) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 2 || i == 3 || i == 7) ? 2 : 3];
        switch (i) {
            case 1:
            case 4:
                objArr[0] = "messageCollector";
                break;
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
            case 7:
                objArr[0] = "org/jetbrains/kotlin/cli/common/modules/ModuleXmlParser";
                break;
            case 5:
                objArr[0] = "currentState";
                break;
            case 6:
                objArr[0] = "xml";
                break;
            default:
                objArr[0] = "xmlFile";
                break;
        }
        if (i == 2 || i == 3) {
            objArr[1] = "parseModuleScript";
        } else if (i != 7) {
            objArr[1] = "org/jetbrains/kotlin/cli/common/modules/ModuleXmlParser";
        } else {
            objArr[1] = "getAttribute";
        }
        switch (i) {
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
            case 7:
                break;
            case 4:
                objArr[2] = "<init>";
                break;
            case 5:
                objArr[2] = "setCurrentState";
                break;
            case 6:
                objArr[2] = "parse";
                break;
            default:
                objArr[2] = "parseModuleScript";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 2 && i != 3 && i != 7) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    private ModuleXmlParser(MessageCollector messageCollector) {
        if (messageCollector == null) {
            $$$reportNull$$$0(4);
        }
        this.modules = new SmartList();
        this.initial = new DefaultHandler() { // from class: org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser.2
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i == 1) {
                    objArr[0] = "localName";
                } else if (i == 2) {
                    objArr[0] = "qName";
                } else if (i != 3) {
                    objArr[0] = "uri";
                } else {
                    objArr[0] = "attributes";
                }
                objArr[1] = "org/jetbrains/kotlin/cli/common/modules/ModuleXmlParser$2";
                objArr[2] = "startElement";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
            public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
                if (str == null) {
                    $$$reportNull$$$0(0);
                }
                if (str2 == null) {
                    $$$reportNull$$$0(1);
                }
                if (str3 == null) {
                    $$$reportNull$$$0(2);
                }
                if (attributes == null) {
                    $$$reportNull$$$0(3);
                }
                if (!ModuleXmlParser.MODULES.equalsIgnoreCase(str3)) {
                    throw ModuleXmlParser.createError(str3);
                }
                ModuleXmlParser moduleXmlParser = ModuleXmlParser.this;
                moduleXmlParser.setCurrentState(moduleXmlParser.insideModules);
            }
        };
        this.insideModules = new DefaultHandler() { // from class: org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser.3
            static final /* synthetic */ boolean $assertionsDisabled = false;

            /* JADX WARN: Code duplicated, block: B:10:0x001c  */
            /* JADX WARN: Code duplicated, block: B:11:0x0021  */
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i == 1) {
                    objArr[0] = "localName";
                } else if (i == 2) {
                    objArr[0] = "qName";
                } else if (i == 3) {
                    objArr[0] = "attributes";
                } else if (i == 4) {
                    objArr[0] = "localName";
                } else if (i != 5) {
                    objArr[0] = "uri";
                } else {
                    objArr[0] = "qName";
                }
                objArr[1] = "org/jetbrains/kotlin/cli/common/modules/ModuleXmlParser$3";
                if (i == 4 || i == 5) {
                    objArr[2] = "endElement";
                } else {
                    objArr[2] = "startElement";
                }
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
            public void endElement(String str, String str2, String str3) throws SAXException {
                if (str2 == null) {
                    $$$reportNull$$$0(4);
                }
                if (str3 == null) {
                    $$$reportNull$$$0(5);
                }
                if (ModuleXmlParser.MODULE.equalsIgnoreCase(str3) || ModuleXmlParser.MODULES.equalsIgnoreCase(str3)) {
                    ModuleXmlParser moduleXmlParser = ModuleXmlParser.this;
                    moduleXmlParser.setCurrentState(moduleXmlParser.insideModules);
                }
            }

            @Override // org.xml.sax.helpers.DefaultHandler, org.xml.sax.ContentHandler
            public void startElement(String str, String str2, String str3, Attributes attributes) throws SAXException {
                if (str == null) {
                    $$$reportNull$$$0(0);
                }
                if (str2 == null) {
                    $$$reportNull$$$0(1);
                }
                if (str3 == null) {
                    $$$reportNull$$$0(2);
                }
                if (attributes == null) {
                    $$$reportNull$$$0(3);
                }
                if (!ModuleXmlParser.MODULE.equalsIgnoreCase(str3)) {
                    throw ModuleXmlParser.createError(str3);
                }
                String attribute = ModuleXmlParser.getAttribute(attributes, ModuleXmlParser.TYPE, str3);
                ModuleXmlParser moduleXmlParser = ModuleXmlParser.this;
                moduleXmlParser.setCurrentState(new InsideModule(ModuleXmlParser.getAttribute(attributes, ModuleXmlParser.NAME, str3), ModuleXmlParser.getAttribute(attributes, ModuleXmlParser.OUTPUT_DIR, str3), attribute));
            }
        };
        this.messageCollector = messageCollector;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SAXException createError(String str) throws SAXException {
        return new SAXException("Unexpected tag: " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getAttribute(Attributes attributes, String str, String str2) throws SAXException {
        String value = attributes.getValue(str);
        if (value != null) {
            return value;
        }
        throw new SAXException("No '" + str + "' attribute for " + str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String getNullableAttribute(Attributes attributes, String str) throws SAXException {
        return attributes.getValue(str);
    }

    private ModuleChunk parse(InputStream inputStream) {
        if (inputStream == null) {
            $$$reportNull$$$0(6);
        }
        try {
            setCurrentState(this.initial);
            SAXParserFactory sAXParserFactoryNewInstance = SAXParserFactory.newInstance();
            sAXParserFactoryNewInstance.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            sAXParserFactoryNewInstance.setFeature("http://xml.org/sax/features/external-general-entities", false);
            sAXParserFactoryNewInstance.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
            sAXParserFactoryNewInstance.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
            sAXParserFactoryNewInstance.newSAXParser().parse(inputStream, (DefaultHandler) new DelegatedSaxHandler() { // from class: org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser.1
                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "org/jetbrains/kotlin/cli/common/modules/ModuleXmlParser$1", "getDelegate"));
                }

                public DefaultHandler getDelegate() {
                    DefaultHandler defaultHandler = ModuleXmlParser.this.currentState;
                    if (defaultHandler == null) {
                        $$$reportNull$$$0(0);
                    }
                    return defaultHandler;
                }
            });
            return new ModuleChunk(this.modules);
        } catch (IOException | ParserConfigurationException e) {
            MessageCollectorUtil.reportException(this.messageCollector, e);
            return ModuleChunk.EMPTY;
        } catch (SAXException e2) {
            this.messageCollector.report(CompilerMessageSeverity.ERROR, "Build file does not have a valid XML: " + e2, null);
            return ModuleChunk.EMPTY;
        }
    }

    public static ModuleChunk parseModuleScript(String str, MessageCollector messageCollector) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        if (messageCollector == null) {
            $$$reportNull$$$0(1);
        }
        try {
            FileInputStream fileInputStream = new FileInputStream(str);
            try {
                ModuleChunk moduleChunk = new ModuleXmlParser(messageCollector).parse(new BufferedInputStream(fileInputStream));
                fileInputStream.close();
                if (moduleChunk == null) {
                    $$$reportNull$$$0(2);
                }
                return moduleChunk;
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    try {
                        fileInputStream.close();
                    } catch (Throwable th3) {
                        th.addSuppressed(th3);
                    }
                    throw th2;
                }
            }
        } catch (IOException e) {
            MessageCollectorUtil.reportException(messageCollector, e);
            ModuleChunk moduleChunk2 = ModuleChunk.EMPTY;
            if (moduleChunk2 == null) {
                $$$reportNull$$$0(3);
            }
            return moduleChunk2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentState(DefaultHandler defaultHandler) {
        if (defaultHandler == null) {
            $$$reportNull$$$0(5);
        }
        this.currentState = defaultHandler;
    }
}
