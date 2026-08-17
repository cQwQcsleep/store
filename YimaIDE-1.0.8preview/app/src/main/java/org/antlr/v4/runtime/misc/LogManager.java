package org.antlr.v4.runtime.misc;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class LogManager {
    protected List<Record> records;

    public static class Record {
        String component;
        String msg;
        long timestamp = System.currentTimeMillis();
        StackTraceElement location = new Throwable().getStackTrace()[0];

        public String toString() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date(this.timestamp)) + " " + this.component + " " + this.location.getFileName() + ":" + this.location.getLineNumber() + " " + this.msg;
        }
    }

    public static void main(String[] strArr) throws IOException {
        LogManager logManager = new LogManager();
        logManager.log("atn", "test msg");
        logManager.log("dfa", "test msg 2");
        System.out.println(logManager);
        logManager.save();
    }

    public void log(String str, String str2) {
        Record record = new Record();
        record.component = str;
        record.msg = str2;
        if (this.records == null) {
            this.records = new ArrayList();
        }
        this.records.add(record);
    }

    public String save() throws IOException {
        String str = "./antlr-" + new SimpleDateFormat("yyyy-MM-dd-HH.mm.ss").format(new Date()) + ".log";
        save(str);
        return str;
    }

    public String toString() {
        if (this.records == null) {
            return HttpUrl.FRAGMENT_ENCODE_SET;
        }
        String property = System.getProperty("line.separator");
        StringBuilder sb = new StringBuilder();
        Iterator<Record> it = this.records.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(property);
        }
        return sb.toString();
    }

    public void log(String str) {
        log(null, str);
    }

    public void save(String str) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(str));
        try {
            bufferedWriter.write(toString());
        } finally {
            bufferedWriter.close();
        }
    }
}
