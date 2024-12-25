package com.example.library_IssuerMs.Domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="issuertable")
public class Issuer {
    @Id
    private Integer isbn;
    private Integer custId;
    private Integer no_of_Copies;
    public Integer getIsbn() {
        return isbn;
    }
    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }
    public Integer getCustId() {
        return custId;
    }
    public void setCustId(Integer custId) {
        this.custId = custId;
    }
    public Integer getNo_of_Copies() {
        return no_of_Copies;
    }
    public void setNo_of_Copies(Integer no_of_Copies) {
        this.no_of_Copies = no_of_Copies;
    }
    

}
