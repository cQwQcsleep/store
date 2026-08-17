package com.sun.tools.javac.main;

import com.sun.org.apache.bcel.internal.classfile.JavaClass;
import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import com.sun.tools.javac.api.BasicJavacTask;
import com.sun.tools.javac.file.BaseFileManager;
import com.sun.tools.javac.file.CacheFSInfo;
import com.sun.tools.javac.file.JavacFileManager;
import com.sun.tools.javac.jvm.Target;
import com.sun.tools.javac.processing.AnnotationProcessingError;
import com.sun.tools.javac.resources.CompilerProperties;
import com.sun.tools.javac.util.ClientCodeException;
import com.sun.tools.javac.util.Context;
import com.sun.tools.javac.util.Dependencies;
import com.sun.tools.javac.util.FatalError;
import com.sun.tools.javac.util.JCDiagnostic;
import com.sun.tools.javac.util.Log;
import com.sun.tools.javac.util.Options;
import com.sun.tools.javac.util.PropagatedException;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.tools.JavaFileManager;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Main {
    private static final String ENV_OPT_NAME = "JDK_JAVAC_OPTIONS";
    public static final String javacBundleName = "com.sun.tools.javac.resources.javac";
    boolean apiMode;
    private JavaFileManager fileManager;
    public Log log;
    String ownName;
    PrintWriter stdErr;
    PrintWriter stdOut;

    public enum Result {
        OK(0),
        ERROR(1),
        CMDERR(2),
        SYSERR(3),
        ABNORMAL(4);

        public final int exitCode;

        Result(int i) {
            this.exitCode = i;
        }

        public boolean isOK() {
            return this.exitCode == 0;
        }
    }

    public Main(String str, PrintWriter printWriter) {
        this.ownName = str;
        this.stdErr = printWriter;
        this.stdOut = printWriter;
    }

    private boolean twoClassLoadersInUse(IllegalAccessError illegalAccessError) {
        Matcher matcher = Pattern.compile("(?i)(?<=tried to access class )([a-z_$][a-z\\d_$]*\\.)*[a-z_$][a-z\\d_$]*").matcher(illegalAccessError.getMessage());
        if (matcher.find()) {
            try {
                Class<?> cls = Class.forName(matcher.group(0));
                if (getClass().getClassLoader() != cls.getClassLoader()) {
                    CodeSource codeSource = cls.getProtectionDomain().getCodeSource();
                    CodeSource codeSource2 = getClass().getProtectionDomain().getCodeSource();
                    if (codeSource == null || codeSource2 == null) {
                        this.log.printLines(CompilerProperties.Errors.TwoClassLoaders1);
                        return true;
                    }
                    this.log.printLines(CompilerProperties.Errors.TwoClassLoaders2(codeSource.getLocation(), codeSource2.getLocation()));
                    return true;
                }
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    public void apMessage(AnnotationProcessingError annotationProcessingError) {
        this.log.printLines(Log.PrefixKind.JAVAC, "msg.proc.annotation.uncaught.exception", new Object[0]);
        annotationProcessingError.getCause().printStackTrace(this.log.getWriter(Log.WriterKind.NOTICE));
    }

    public void bugMessage(Throwable th) {
        this.log.printLines(Log.PrefixKind.JAVAC, "msg.bug", JavaCompiler.version());
        th.printStackTrace(this.log.getWriter(Log.WriterKind.NOTICE));
    }

    /* JADX WARN: Code duplicated, block: B:140:0x0211  */
    /* JADX WARN: Code duplicated, block: B:142:0x0216 A[Catch: ClientCodeException -> 0x015d, TRY_ENTER, TRY_LEAVE, TryCatch #1 {ClientCodeException -> 0x015d, blocks: (B:69:0x0159, B:107:0x01c0, B:142:0x0216, B:117:0x01d6, B:125:0x01eb, B:131:0x01fb, B:137:0x020b, B:61:0x0130, B:63:0x0145, B:66:0x014d, B:86:0x0176, B:89:0x0191, B:91:0x0197, B:92:0x019a), top: B:148:0x0130, inners: #0, #4, #5, #7, #9 }] */
    public Result compile(String[] strArr, Context context) throws Throwable {
        boolean zHandleOptions;
        Result result;
        PrintWriter printWriter = this.stdOut;
        if (printWriter != null) {
            context.put(Log.outKey, printWriter);
        }
        PrintWriter printWriter2 = this.stdErr;
        if (printWriter2 != null) {
            context.put(Log.errKey, printWriter2);
        }
        Log logInstance = Log.instance(context);
        this.log = logInstance;
        if (strArr.length == 0) {
            try {
                Option.HELP.process(new OptionHelper.GrumpyHelper(logInstance) { // from class: com.sun.tools.javac.main.Main.1
                    @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
                    public String getOwnName() {
                        return Main.this.ownName;
                    }

                    @Override // com.sun.tools.javac.main.OptionHelper.GrumpyHelper, com.sun.tools.javac.main.OptionHelper
                    public void put(String str, String str2) {
                    }
                }, "-help");
            } catch (Option.InvalidValueException unused) {
            }
            return Result.CMDERR;
        }
        List listAsList = Arrays.asList(strArr);
        Arguments argumentsInstance = Arguments.instance(context);
        argumentsInstance.init(this.ownName, listAsList);
        if (this.log.nerrors > 0) {
            return Result.CMDERR;
        }
        Options optionsInstance = Options.instance(context);
        boolean z = true;
        if (optionsInstance.isSet("stdout")) {
            this.log.flush();
            this.log.setWriters(new PrintWriter((OutputStream) System.out, true));
        }
        if (optionsInstance.isUnset("nonBatchMode") && System.getProperty("nonBatchMode") == null) {
            CacheFSInfo.preRegister(context);
        }
        JavaFileManager baseFileManager = (JavaFileManager) context.get(JavaFileManager.class);
        this.fileManager = baseFileManager;
        if (baseFileManager instanceof DelegatingJavaFileManager) {
            baseFileManager = ((DelegatingJavaFileManager) baseFileManager).getBaseFileManager();
        }
        if (baseFileManager instanceof BaseFileManager) {
            BaseFileManager baseFileManager2 = (BaseFileManager) baseFileManager;
            baseFileManager2.setContext(context);
            zHandleOptions = baseFileManager2.handleOptions(argumentsInstance.getDeferredFileManagerOptions());
        } else {
            zHandleOptions = true;
        }
        String str = optionsInstance.get("showClass");
        if (str != null) {
            if (str.equals("showClass")) {
                str = "com.sun.tools.javac.Main";
            }
            showClass(str);
        }
        if ((!zHandleOptions || !argumentsInstance.validate()) || this.log.nerrors > 0) {
            return Result.CMDERR;
        }
        if (argumentsInstance.isEmpty()) {
            return Result.OK;
        }
        if (optionsInstance.isSet("debug.completionDeps")) {
            Dependencies.GraphDependencies.preRegister(context);
        }
        BasicJavacTask basicJavacTask = (BasicJavacTask) BasicJavacTask.instance(context);
        basicJavacTask.initPlugins(argumentsInstance.getPluginOpts());
        JavaFileManager javaFileManager = this.fileManager;
        Option option = Option.MULTIRELEASE;
        if (javaFileManager.isSupportedOption(option.primaryName) == 1) {
            this.fileManager.handleOption(option.primaryName, com.sun.tools.javac.util.List.of(Target.instance(context).multiReleaseValue()).iterator());
        }
        JavaCompiler javaCompilerInstance = JavaCompiler.instance(context);
        com.sun.tools.javac.util.List<String> docLintOpts = argumentsInstance.getDocLintOpts();
        if (!docLintOpts.isEmpty()) {
            basicJavacTask.initDocLint(docLintOpts);
        }
        if (optionsInstance.get(Option.XSTDOUT) != null) {
            javaCompilerInstance.closeables = javaCompilerInstance.closeables.prepend(this.log.getWriter(Log.WriterKind.NOTICE));
        }
        boolean zIsSet = optionsInstance.isSet("printArgsToFile");
        try {
            try {
                try {
                    try {
                        try {
                            try {
                                try {
                                    try {
                                        try {
                                            javaCompilerInstance.compile(argumentsInstance.getFileObjects(), argumentsInstance.getClassNames(), null, com.sun.tools.javac.util.List.nil());
                                            Set<String> set = this.log.expectDiagKeys;
                                            if (set != null) {
                                                boolean zIsEmpty = set.isEmpty();
                                                Log log = this.log;
                                                if (zIsEmpty) {
                                                    log.printRawLines("all expected diagnostics found");
                                                    result = Result.OK;
                                                    if (zIsSet) {
                                                        printArgumentsToFile(strArr);
                                                    }
                                                } else {
                                                    log.printRawLines("expected diagnostic keys not found: " + this.log.expectDiagKeys);
                                                    result = Result.ERROR;
                                                    if (zIsSet) {
                                                        printArgumentsToFile(strArr);
                                                    }
                                                }
                                            } else {
                                                result = javaCompilerInstance.errorCount() == 0 ? Result.OK : Result.ERROR;
                                                if (zIsSet) {
                                                    printArgumentsToFile(strArr);
                                                }
                                            }
                                            javaCompilerInstance.close();
                                            return result;
                                        } catch (OutOfMemoryError | StackOverflowError e) {
                                            resourceMessage(e);
                                            Result result2 = Result.SYSERR;
                                            if (zIsSet) {
                                                printArgumentsToFile(strArr);
                                            }
                                            if (javaCompilerInstance != null) {
                                                javaCompilerInstance.close();
                                            }
                                            return result2;
                                        }
                                    } catch (Throwable th) {
                                        th = th;
                                        z = zIsSet;
                                        if (z) {
                                            printArgumentsToFile(strArr);
                                        }
                                        if (javaCompilerInstance != null) {
                                            javaCompilerInstance.close();
                                        }
                                        throw th;
                                    }
                                } catch (Throwable th2) {
                                    th = th2;
                                    if (z) {
                                        printArgumentsToFile(strArr);
                                    }
                                    if (javaCompilerInstance != null) {
                                        javaCompilerInstance.close();
                                    }
                                    throw th;
                                }
                            } catch (FatalError e2) {
                                feMessage(e2, optionsInstance);
                                Result result3 = Result.SYSERR;
                                if (zIsSet) {
                                    printArgumentsToFile(strArr);
                                }
                                if (javaCompilerInstance != null) {
                                    javaCompilerInstance.close();
                                }
                                return result3;
                            }
                        } catch (Throwable th3) {
                            if (javaCompilerInstance == null || javaCompilerInstance.errorCount() == 0 || optionsInstance.isSet("dev")) {
                                bugMessage(th3);
                            }
                            Result result4 = Result.ABNORMAL;
                            printArgumentsToFile(strArr);
                            if (javaCompilerInstance != null) {
                                javaCompilerInstance.close();
                            }
                            return result4;
                        }
                    } catch (AnnotationProcessingError e3) {
                        apMessage(e3);
                        Result result5 = Result.SYSERR;
                        if (zIsSet) {
                            printArgumentsToFile(strArr);
                        }
                        if (javaCompilerInstance != null) {
                            javaCompilerInstance.close();
                        }
                        return result5;
                    }
                } catch (PropagatedException e4) {
                    throw e4.getCause();
                }
            } catch (IllegalAccessError e5) {
                if (twoClassLoadersInUse(e5)) {
                    bugMessage(e5);
                }
                Result result6 = Result.ABNORMAL;
                printArgumentsToFile(strArr);
                if (javaCompilerInstance != null) {
                    javaCompilerInstance.close();
                }
                return result6;
            }
        } catch (ClientCodeException e6) {
            rc6.a(e6.getCause());
            return null;
        }
    }

    public void feMessage(Throwable th, Options options) {
        this.log.printRawLines(th.getMessage());
        if (th.getCause() == null || !options.isSet("dev")) {
            return;
        }
        th.getCause().printStackTrace(this.log.getWriter(Log.WriterKind.NOTICE));
    }

    public void ioMessage(Throwable th) {
        this.log.printLines(Log.PrefixKind.JAVAC, "msg.io", new Object[0]);
        th.printStackTrace(this.log.getWriter(Log.WriterKind.NOTICE));
    }

    public void pluginMessage(Throwable th) {
        this.log.printLines(Log.PrefixKind.JAVAC, "msg.plugin.uncaught.exception", new Object[0]);
        th.printStackTrace(this.log.getWriter(Log.WriterKind.NOTICE));
    }

    public void printArgumentsToFile(String... strArr) {
        Path pathResolve = Paths.get(System.getProperty("java.io.tmpdir"), new String[0]).resolve(String.format("javac.%s.args", new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())));
        String str = "# javac crashed, this report includes the parameters passed to it in the @-file format\n";
        try {
            BufferedWriter bufferedWriterNewBufferedWriter = Files.newBufferedWriter(pathResolve, new OpenOption[0]);
            try {
                for (String str2 : strArr) {
                    String strReplaceAll = str2.replaceAll("\\\\", "\\\\\\\\");
                    if (strReplaceAll.matches(".*\\s+.*")) {
                        strReplaceAll = "\"" + strReplaceAll + "\"";
                    }
                    str = str + strReplaceAll + '\n';
                }
                bufferedWriterNewBufferedWriter.write(str);
                bufferedWriterNewBufferedWriter.close();
                this.log.printLines(Log.PrefixKind.JAVAC, "msg.parameters.output", pathResolve.toAbsolutePath());
            } catch (Throwable th) {
                if (bufferedWriterNewBufferedWriter != null) {
                    try {
                        bufferedWriterNewBufferedWriter.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                }
                throw th;
            }
        } catch (IOException unused) {
            this.log.printLines(Log.PrefixKind.JAVAC, "msg.parameters.output.error", pathResolve.toAbsolutePath());
            System.err.println(str);
            System.err.println();
        }
    }

    public void reportDiag(JCDiagnostic.DiagnosticInfo diagnosticInfo) {
        if (this.apiMode) {
            throw new PropagatedException(new IllegalStateException(this.log.localize(diagnosticInfo)));
        }
        reportHelper(diagnosticInfo);
        this.log.printLines(Log.PrefixKind.JAVAC, "msg.usage", this.ownName);
    }

    public void reportHelper(JCDiagnostic.DiagnosticInfo diagnosticInfo) {
        String strLocalize = this.log.localize(diagnosticInfo);
        String strLocalize2 = this.log.localize(CompilerProperties.Errors.Error);
        if (!strLocalize.startsWith(strLocalize2)) {
            strLocalize = strLocalize2 + strLocalize;
        }
        this.log.printRawLines(strLocalize);
    }

    public void resourceMessage(Throwable th) {
        this.log.printLines(Log.PrefixKind.JAVAC, "msg.resource", new Object[0]);
        th.printStackTrace(this.log.getWriter(Log.WriterKind.NOTICE));
    }

    public void showClass(String str) {
        PrintWriter writer = this.log.getWriter(Log.WriterKind.NOTICE);
        writer.println("javac: show class: " + str);
        URL resource = getClass().getResource(PsuedoNames.PSEUDONAME_ROOT + str.replace('.', '/') + JavaClass.EXTENSION);
        if (resource != null) {
            writer.println("  " + resource);
        }
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream(PsuedoNames.PSEUDONAME_ROOT + str.replace('.', '/') + JavaClass.EXTENSION);
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                DigestInputStream digestInputStream = new DigestInputStream(resourceAsStream, messageDigest);
                try {
                    while (digestInputStream.read(new byte[8192]) > 0) {
                    }
                    byte[] bArrDigest = messageDigest.digest();
                    digestInputStream.close();
                    StringBuilder sb = new StringBuilder();
                    for (byte b : bArrDigest) {
                        sb.append(String.format("%02x", Byte.valueOf(b)));
                    }
                    writer.println("  SHA-256 checksum: " + ((Object) sb));
                    if (resourceAsStream != null) {
                        resourceAsStream.close();
                    }
                } catch (Throwable th) {
                    try {
                        digestInputStream.close();
                    } catch (Throwable th2) {
                        th.addSuppressed(th2);
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                if (resourceAsStream != null) {
                    try {
                        resourceAsStream.close();
                    } catch (Throwable th4) {
                        th3.addSuppressed(th4);
                    }
                }
                throw th3;
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            writer.println("  cannot compute digest: " + e);
        }
    }

    public Main(String str) {
        this.ownName = str;
    }

    public Main(String str, PrintWriter printWriter, PrintWriter printWriter2) {
        this.ownName = str;
        this.stdOut = printWriter;
        this.stdErr = printWriter2;
    }

    public Result compile(String[] strArr) throws Throwable {
        Context context = new Context();
        JavacFileManager.preRegister(context);
        Result resultCompile = compile(strArr, context);
        try {
            JavaFileManager javaFileManager = this.fileManager;
            if (javaFileManager == null) {
                return resultCompile;
            }
            javaFileManager.close();
            return resultCompile;
        } catch (IOException e) {
            bugMessage(e);
            return resultCompile;
        }
    }
}
