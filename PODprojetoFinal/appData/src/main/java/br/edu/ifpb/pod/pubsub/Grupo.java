/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.pod.pubsub;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Objects;

/**
 *
 * @author ajp
 */
public class Grupo implements Serializable, GrupoIF {

    private int id;
    private String descricao;

    public Grupo() {
    }

    public Grupo(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Grupo(String descricao) {
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.descricao);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Grupo other = (Grupo) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grupo1{" + "id=" + id + ", descricao=" + descricao + '}';
    }

    @Override
    public void register(String uuid, Subscriber sub) throws RemoteException {
    }

}
