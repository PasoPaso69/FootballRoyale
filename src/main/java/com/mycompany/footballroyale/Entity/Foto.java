/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.footballroyale.Entity;

import java.sql.Blob;


public class Foto {
    private String Id;
    private Blob data;
    private String fileName;
    private String extension;

    public Foto() {}

    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public Blob getData() { return data; }
    public void setdata(Blob data) { this.data = data; }
    
    public String getFileName() { return fileName; }
    public void setExtension(String fileName) { this.fileName = fileName; }

    public String getExstension() { return extension; }
    public void setDescrizione(String extension) { this.extension = extension; }
}
