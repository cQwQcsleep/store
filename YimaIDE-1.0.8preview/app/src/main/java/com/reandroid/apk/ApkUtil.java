package com.reandroid.apk;

import com.reandroid.arsc.chunk.PackageBlock;
import com.reandroid.utils.CompareUtil;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkUtil {
    public static final String DEF_MODULE_NAME = "base";
    public static final String JSON_FILE_EXTENSION = ".json";
    public static final String NAME_data = "data";
    public static final String NAME_value_type = "value_type";
    public static final String ROOT_NAME = "root";
    public static final String SIGNATURE_DIR_NAME = "signatures";

    private static File getPublicXmlFile(File file) {
        if (!file.isDirectory()) {
            return null;
        }
        File file2 = new File(file, PackageBlock.VALUES_DIRECTORY_NAME);
        if (!file2.isDirectory()) {
            return null;
        }
        File file3 = new File(file2, PackageBlock.PUBLIC_XML);
        if (file3.isFile()) {
            return file3;
        }
        return null;
    }

    private static boolean hasExtension(File file, String str) {
        if (str == null) {
            return true;
        }
        return file.getName().toLowerCase().endsWith(str.toLowerCase());
    }

    private static boolean isPackageDirectory(File file) {
        if (file.isDirectory()) {
            return new File(file, PackageBlock.JSON_FILE_NAME).isFile();
        }
        return false;
    }

    public static boolean isValuesDirectoryName(String str, boolean z) {
        String str2 = PackageBlock.VALUES_DIRECTORY_NAME;
        if (str2.equals(str)) {
            return true;
        }
        if (!z) {
            return false;
        }
        return str.startsWith(str2 + "-");
    }

    public static String jsonToArchiveResourcePath(File file, File file2) {
        String archivePath = toArchivePath(file, file2);
        return archivePath.endsWith(JSON_FILE_EXTENSION) ? archivePath.substring(0, archivePath.length() - 5) : archivePath;
    }

    public static List<File> listDirectories(File file) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isDirectory()) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static List<File> listFiles(File file, String str) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles != null) {
            for (File file2 : fileArrListFiles) {
                if (file2.isFile() && hasExtension(file2, str)) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static List<File> listPackageDirectories(File file) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return arrayList;
        }
        for (File file2 : fileArrListFiles) {
            if (isPackageDirectory(file2)) {
                arrayList.add(file2);
            }
        }
        arrayList.sort(CompareUtil.getComparableComparator());
        return arrayList;
    }

    public static List<File> listPublicXmlFiles(File file) {
        ArrayList arrayList = new ArrayList();
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return arrayList;
        }
        for (File file2 : fileArrListFiles) {
            Iterator<File> it = listDirectories(file2).iterator();
            while (it.hasNext()) {
                File publicXmlFile = getPublicXmlFile(it.next());
                if (publicXmlFile != null) {
                    arrayList.add(publicXmlFile);
                }
            }
        }
        arrayList.sort(CompareUtil.getComparableComparator());
        return arrayList;
    }

    public static List<File> listValuesDirectory(File file, boolean z) {
        File[] fileArrListFiles;
        ArrayList arrayList = new ArrayList();
        if (!file.isDirectory() || (fileArrListFiles = file.listFiles()) == null) {
            return arrayList;
        }
        for (File file2 : fileArrListFiles) {
            if (file2.isDirectory() && isValuesDirectoryName(file2.getName(), z)) {
                arrayList.add(file2);
            }
        }
        arrayList.sort(CompareUtil.getComparableComparator());
        return arrayList;
    }

    public static List<File> recursiveFiles(File file, String str) {
        File[] fileArrListFiles;
        ArrayList arrayList = new ArrayList();
        if (file.isFile()) {
            if (hasExtension(file, str)) {
                arrayList.add(file);
                return arrayList;
            }
        } else if (file.isDirectory() && (fileArrListFiles = file.listFiles()) != null) {
            for (File file2 : fileArrListFiles) {
                if (!file2.isFile()) {
                    arrayList.addAll(recursiveFiles(file2, str));
                } else if (hasExtension(file2, str)) {
                    arrayList.add(file2);
                }
            }
        }
        return arrayList;
    }

    public static String replaceRootDir(String str, String str2) {
        String strSubstring = str.substring(str.indexOf(47) + 1);
        if (str2 == null || str2.length() <= 0) {
            return strSubstring;
        }
        if (!str2.endsWith("/")) {
            str2 = str2.concat("/");
        }
        return str2.concat(strSubstring);
    }

    public static String toArchivePath(File file, File file2) {
        return file2.getAbsolutePath().substring((file.getAbsolutePath() + File.separator).length()).replace(File.separatorChar, '/');
    }

    public static String toModuleName(File file) {
        String name = file.getName();
        int iLastIndexOf = name.lastIndexOf(46);
        return iLastIndexOf > 0 ? name.substring(0, iLastIndexOf) : name;
    }

    public static List<File> listValuesDirectory(File file) {
        return listValuesDirectory(file, true);
    }

    public static List<File> recursiveFiles(File file) {
        return recursiveFiles(file, null);
    }
}
