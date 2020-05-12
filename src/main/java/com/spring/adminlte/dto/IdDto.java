package com.spring.adminlte.dto;

public class IdDto {
    private  int id;

    public IdDto(int id) {
        this.id = id;
    }

    public IdDto() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "IdDto{" +
                "id=" + id +
                '}';
    }
}
