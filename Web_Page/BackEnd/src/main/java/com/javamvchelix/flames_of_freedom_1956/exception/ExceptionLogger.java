/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.javamvchelix.flames_of_freedom_1956.exception;

/**
 *
 * @author shado
 */
public class ExceptionLogger {
    
    private Class actualClass;
    
    public ExceptionLogger(Class actualClass) {
        this.actualClass = actualClass;
    }
    
    public void errorLog(Exception ex) {
        StackTraceElement[] stackTrace = ex.getStackTrace();
        if(stackTrace.length > 0) {
            StackTraceElement element = stackTrace[0];
            for(StackTraceElement actualElement : stackTrace) {
                if(actualElement.getClassName().contains(this.actualClass.getName())) {
                    element = actualElement;
                    break;
                }
            }
            
            System.err.println(
                    "\n --------------- Exception ---------------"
                    + "\nStatus: JWT Class Exception"
                    + "\nStaus Code: 500"
                    + "\nException Message: " + ex.getMessage()
                    + "\nException Class: " + ex.getClass().getName()
                    + "\nFile: " + element.getFileName()
                    + "\nFunction: " + element.getMethodName() + "()"
                    + "\nLine: " + element.getLineNumber()
                    + "\nClass Loader Name: " + element.getClassLoaderName()
                    + "\nClass Name: " + element.getClassName()
                    + "\nModul Name: " + element.getModuleName()
                    + "\nModule Version: " + element.getModuleVersion()
                    + "\n--------------- Exception ---------------\n"
            );
        }
    }
}
