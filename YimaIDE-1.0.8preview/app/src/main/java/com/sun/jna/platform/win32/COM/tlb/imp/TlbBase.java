package com.sun.jna.platform.win32.COM.tlb.imp;

import com.sun.jna.platform.win32.COM.TypeInfoUtil;
import com.sun.jna.platform.win32.COM.TypeLibUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public abstract class TlbBase {
    public static final String CR = "\n";
    public static final String CRCR = "\n\n";
    public static final String TAB = "\t";
    public static final String TABTAB = "\t\t";
    protected String bindingMode;
    protected StringBuffer classBuffer;
    protected String content;
    protected String filename;
    protected int index;
    protected String name;
    protected StringBuffer templateBuffer;
    protected TypeInfoUtil typeInfoUtil;
    protected TypeLibUtil typeLibUtil;
    public static String[] IUNKNOWN_METHODS = {"QueryInterface", "AddRef", "Release"};
    public static String[] IDISPATCH_METHODS = {"GetTypeInfoCount", "GetTypeInfo", "GetIDsOfNames", "Invoke"};

    public TlbBase(int i, TypeLibUtil typeLibUtil, TypeInfoUtil typeInfoUtil, String str) throws Throwable {
        this.content = "";
        this.filename = "DefaultFilename";
        this.name = "DefaultName";
        this.index = i;
        this.typeLibUtil = typeLibUtil;
        this.typeInfoUtil = typeInfoUtil;
        this.bindingMode = str;
        try {
            readTemplateFile(getClassTemplate());
            this.classBuffer = this.templateBuffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
    }

    public void createClassName(String str) {
        replaceVariable("classname", str);
    }

    public void createContent(String str) {
        replaceVariable("content", str);
    }

    public void createPackageName(String str) {
        replaceVariable("packagename", str);
    }

    public StringBuffer getClassBuffer() {
        return this.classBuffer;
    }

    public abstract String getClassTemplate();

    public String getFilename() {
        return this.filename;
    }

    public String getName() {
        return this.name;
    }

    public boolean isDispIdMode() {
        return this.bindingMode.equalsIgnoreCase(TlbConst.BINDING_MODE_DISPID);
    }

    public boolean isReservedMethod(String str) {
        int i = 0;
        while (true) {
            String[] strArr = IUNKNOWN_METHODS;
            if (i >= strArr.length) {
                int i2 = 0;
                while (true) {
                    String[] strArr2 = IDISPATCH_METHODS;
                    if (i2 >= strArr2.length) {
                        return false;
                    }
                    if (strArr2[i2].equalsIgnoreCase(str)) {
                        return true;
                    }
                    i2++;
                }
            } else {
                if (strArr[i].equalsIgnoreCase(str)) {
                    return true;
                }
                i++;
            }
        }
    }

    public boolean isVTableMode() {
        return this.bindingMode.equalsIgnoreCase(TlbConst.BINDING_MODE_VTABLE);
    }

    public void log(String str, String str2) {
        System.out.println(str + " " + getTime() + " : " + str2);
    }

    public void logError(String str) {
        log("ERROR", str);
    }

    public void logInfo(String str) {
        log("INFO", str);
    }

    public void readTemplateFile(String str) throws Throwable {
        this.templateBuffer = new StringBuffer();
        BufferedReader bufferedReader = null;
        try {
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(str)));
            while (true) {
                try {
                    String line = bufferedReader2.readLine();
                    if (line == null) {
                        bufferedReader2.close();
                        return;
                    }
                    this.templateBuffer.append(line + "\n");
                } catch (Throwable th) {
                    th = th;
                    bufferedReader = bufferedReader2;
                    if (bufferedReader != null) {
                        bufferedReader.close();
                    }
                    throw th;
                }
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public void replaceVariable(String str, String str2) {
        String strReplaceAll = "";
        if (str2 == null) {
            str2 = "";
        }
        Matcher matcher = Pattern.compile("\\$\\{" + str + "\\}").matcher(this.classBuffer);
        while (matcher.find()) {
            strReplaceAll = matcher.replaceAll(str2);
        }
        if (strReplaceAll.length() > 0) {
            this.classBuffer = new StringBuffer(strReplaceAll);
        }
    }

    public void setFilename(String str) {
        if (!str.endsWith("java")) {
            str = str.concat(".java");
        }
        this.filename = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public TlbBase(int i, TypeLibUtil typeLibUtil, TypeInfoUtil typeInfoUtil) {
        this(i, typeLibUtil, typeInfoUtil, TlbConst.BINDING_MODE_DISPID);
    }
}
