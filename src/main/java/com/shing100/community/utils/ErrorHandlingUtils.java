package com.shing100.community.utils;

public class ErrorHandlingUtils {
    /**
     * exception stack trace 반환
     * @param e
     * @return
     */
    public static String getErrorStackTrace(Exception e) {
        StringBuilder sb = new StringBuilder();
        sb.append(e.getMessage() + System.lineSeparator());
        StackTraceElement[] stackTrace = e.getStackTrace();
        for (int i = 0; i < Math.min(10, stackTrace.length); i++) {
            sb.append(String.format("%s %s.%s:%s",stackTrace[i].getFileName(), stackTrace[i].getClassName(),
                    stackTrace[i].getMethodName(), stackTrace[i].getLineNumber()) + System.lineSeparator());
        }
        return sb.toString();
    }

}
