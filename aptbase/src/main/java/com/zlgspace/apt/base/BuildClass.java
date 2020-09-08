package com.zlgspace.apt.base;

public class BuildClass {

    private String packageStr;

    private String name;

    private String clzModifiers;

    private String clzSuffix = "";

    private StringBuilder importBuilder = new StringBuilder();

    private StringBuilder fieldBuilder = new StringBuilder();

    private StringBuilder methodBuilder = new StringBuilder();

    public BuildClass(){
    }

    public BuildClass(String name){
        this.name = name;
    }

    public String getPackage() {
        return packageStr;
    }

    public void setPackage(String packageStr) {
        this.packageStr = packageStr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassFullName(){
        return packageStr+"."+this.name;
    }

    public String getClzModifiers() {
        return clzModifiers;
    }

    public void setClzModifiers(String clzModifiers) {
        this.clzModifiers = clzModifiers;
    }

    public void setClzSuffix(String suffix){
        if(suffix==null)
            suffix = "";
        this.clzSuffix = suffix;
    }

    public void appendImport(String importStr){
        if(importBuilder.toString().contains(importStr))
            return;
        importBuilder.append(importStr);
    }

    public void appendField(String fieldStr){
        fieldBuilder.append(fieldStr);
    }

    public void appendMethod(String methodStr){
        methodBuilder.append(methodStr);
    }

    @Override
    public String toString() {
        StringBuilder clzBuilder = new StringBuilder();
        clzBuilder.append("package " + getPackage() + ";\n");
        clzBuilder.append(importBuilder);
        clzBuilder.append("\n"+clzModifiers+" "+name+" "+clzSuffix+" {\n\n");
        clzBuilder.append(fieldBuilder);
        clzBuilder.append("\n");
        clzBuilder.append(methodBuilder);
        clzBuilder.append("\n\n");
        clzBuilder.append("}");
        return clzBuilder.toString();
    }
}
