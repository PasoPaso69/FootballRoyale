package com.mycompany.footballroyale.domain;

import jakarta.persistence.*; // Necessario per Hibernate 6+
import java.sql.Blob;

@Entity
@Table(name = "foto")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // Genera ID stringa univoci automaticamente
    @Column(name = "id_foto")
    private String Id;

    @Lob // Specifica che il campo contiene un oggetto di grandi dimensioni (Binary Large Object)
    @Column(name = "dati_binari", columnDefinition = "LONGBLOB")
    private Blob data;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "estensione", length = 10)
    private String extension;

    public Foto() {}

    // Getter e Setter
    public String getId() { return Id; }
    public void setId(String Id) { this.Id = Id; }

    public Blob getData() { return data; }
    public void setData(Blob data) { this.data = data; } // Corretto CamelCase
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; } // Corretto nome metodo

    public String getExtension() { return extension; } // Corretto typo 'Exstension'
    public void setExtension(String extension) { this.extension = extension; } // Corretto nome metodo
    
    @Override
    public String toString() {
        return "Foto{" +
                "Id='" + Id + '\'' +
                ", fileName='" + fileName + '\'' +
                ", extension='" + extension + '\'' +
                ", data=" + (data != null ? "[Presente]" : "[Vuoto]") +
                '}';
    }
}