package com.mycompany.footballroyale.domain;

import jakarta.persistence.*; // Necessario per Hibernate 6+
import java.sql.Blob;
import java.util.Date;

@Entity
@Table(name = "foto")
public class Foto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <--- Fondamentale per l'autoincremento
    @Column(name = "id_foto")
    private Long Id;

    @Lob // Specifica che il campo contiene un oggetto di grandi dimensioni (Binary Large Object)
    @Column(name = "dati_binari", columnDefinition = "LONGBLOB")
    private Blob data;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "estensione", length = 10)
    private String extension;

    public Foto() {}
    
        public Foto(Long id, Blob data, String fileName, String extension) {
        this.Id = id;
        this.data = data;
        this.fileName = fileName;
        this.extension = extension;

    }

    // Getter e Setter
    public Long getId() { return Id; }
    public void setId(Long Id) { this.Id = Id; }

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