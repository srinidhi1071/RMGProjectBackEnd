package com.rmgYantra.loginapp.model;

import org.hibernate.annotations.GenericGenerator;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.file.Path;

import javax.persistence.*;

@Entity
@Table(name = "files")
@NoArgsConstructor
@Getter
@Setter
public class DBFile {
	
    @Id
    @GeneratedValue
    private int id;

    private String fileName;

    private String fileType;
    
    @Convert(converter = PathConverter.class)
    private Path path;
    
    public DBFile(String fileName, String fileType, Path path) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.path = path;
    }
}
