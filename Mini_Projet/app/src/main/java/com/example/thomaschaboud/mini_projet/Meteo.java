package com.example.thomaschaboud.mini_projet;

import java.io.Serializable;

/**
 * Created by thomaschaboud on 21/03/2017.
 */

public class Meteo implements Serializable{
    private String nomVille;
    private String title;
    private String date;
    private String jour;
    private String tempe_matin;
    private String vent_matin;
    private String seeing_matin;
    private String precipitations_matin;
    private String tempe_midi;
    private String vent_midi;
    private String seeing_midi;
    private String precipitations_midi;
    private String tempe_apmidi;
    private String vent_apmidi;
    private String seeing_apmidi;
    private String precipitations_apmidi;
    private String tempe_soir;
    private String vent_soir;
    private String seeing_soir;
    private String precipitations_soir;
    private String pubdate;
    private String picto_matin;
    private String picto_midi;
    private String picto_apmidi;
    private String picto_soir;

    public Meteo() {
        this.nomVille = "undefined";
        this.title = "undefined";
        this.date = "undefined";
        this.jour = "undefined";
        this.seeing_matin = "-";
        this.vent_matin = "-";
        this.tempe_matin = "-";
        this.precipitations_matin = "-";
        this.seeing_midi = "-";
        this.vent_midi = "-";
        this.tempe_midi = "-";
        this.precipitations_midi = "-";
        this.tempe_apmidi = "-";
        this.vent_apmidi = "-";
        this.seeing_apmidi = "-";
        this.precipitations_apmidi = "-";
        this.tempe_soir = "-";
        this.vent_soir = "-";
        this.seeing_soir = "-";
        this.precipitations_soir = "-";
        this.pubdate = "undefined";
        this.picto_matin = "undefined";
        this.picto_midi = "undefined";
        this.picto_apmidi = "undefined";
        this.picto_soir = "undefined";
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getJour() {
        return jour;
    }

    public String getTempe_midi() {
        return tempe_midi;
    }

    public String getVent_midi() {
        return vent_midi;
    }

    public String getSeeing_midi() {
        return seeing_midi;
    }

    public String getPrecipitations_midi() {
        return precipitations_midi;
    }

    public String getTempe_apmidi() {
        return tempe_apmidi;
    }

    public String getVent_apmidi() {
        return vent_apmidi;
    }

    public String getSeeing_apmidi() {
        return seeing_apmidi;
    }

    public String getPrecipitations_apmidi() {
        return precipitations_apmidi;
    }

    public String getTempe_soir() {
        return tempe_soir;
    }

    public String getVent_soir() {
        return vent_soir;
    }

    public String getSeeing_soir() {
        return seeing_soir;
    }

    public String getPrecipitations_soir() {
        return precipitations_soir;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTempe_apmidi(String tempe_apmidi) {
        this.tempe_apmidi = tempe_apmidi;
    }

    public void setVent_midi(String vent_midi) {
        this.vent_midi = vent_midi;
    }

    public void setSeeing_midi(String seeing_midi) {
        this.seeing_midi = seeing_midi;
    }

    public void setPrecipitations_midi(String precipitations_midi) {
        this.precipitations_midi = precipitations_midi;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public void setTempe_midi(String tempe_midi) {
        this.tempe_midi = tempe_midi;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public void setVent_apmidi(String vent_apmidi) {
        this.vent_apmidi = vent_apmidi;
    }

    public void setSeeing_apmidi(String seeing_apmidi) {
        this.seeing_apmidi = seeing_apmidi;
    }

    public void setPrecipitations_apmidi(String precipitations_apmidi) {
        this.precipitations_apmidi = precipitations_apmidi;
    }

    public void setTempe_soir(String tempe_soir) {
        this.tempe_soir = tempe_soir;
    }

    public void setVent_soir(String vent_soir) {
        this.vent_soir = vent_soir;
    }

    public void setPrecipitations_soir(String precipitations_soir) {
        this.precipitations_soir = precipitations_soir;
    }

    public void setSeeing_soir(String seeing_soir) {
        this.seeing_soir = seeing_soir;
    }

    public String getTempe_matin() {
        return tempe_matin;
    }

    public void setTempe_matin(String tempe_matin) {
        this.tempe_matin = tempe_matin;
    }

    public String getVent_matin() {
        return vent_matin;
    }

    public void setVent_matin(String vent_matin) {
        this.vent_matin = vent_matin;
    }

    public String getSeeing_matin() {
        return seeing_matin;
    }

    public void setSeeing_matin(String seeing_matin) {
        this.seeing_matin = seeing_matin;
    }

    public String getPrecipitations_matin() {
        return precipitations_matin;
    }

    public void setPrecipitations_matin(String precipitations_matin) {
        this.precipitations_matin = precipitations_matin;
    }

    public String getPicto_matin() {
        return picto_matin;
    }

    public void setPicto_matin(String picto_matin) {
        this.picto_matin = picto_matin;
    }

    public String getPicto_midi() {
        return picto_midi;
    }

    public void setPicto_midi(String picto_midi) {
        this.picto_midi = picto_midi;
    }

    public String getPicto_apmidi() {
        return picto_apmidi;
    }

    public void setPicto_apmidi(String picto_apmidi) {
        this.picto_apmidi = picto_apmidi;
    }

    public String getPicto_soir() {
        return picto_soir;
    }

    public void setPicto_soir(String picto_soir) {
        this.picto_soir = picto_soir;
    }

    public String getNomVille() { return nomVille; }

    public void setNomVille(String nomVille) { this.nomVille = nomVille; }

    @Override
    public String toString() {
        return "Meteo{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", jour='" + jour + '\'' +
                ", tempe_matin='" + tempe_matin + '\'' +
                ", vent_matin='" + vent_matin + '\'' +
                ", seeing_matin='" + seeing_matin + '\'' +
                ", precipitations_matin='" + precipitations_matin + '\'' +
                ", tempe_midi='" + tempe_midi + '\'' +
                ", vent_midi='" + vent_midi + '\'' +
                ", seeing_midi='" + seeing_midi + '\'' +
                ", precipitations_midi='" + precipitations_midi + '\'' +
                ", tempe_apmidi='" + tempe_apmidi + '\'' +
                ", vent_apmidi='" + vent_apmidi + '\'' +
                ", seeing_apmidi='" + seeing_apmidi + '\'' +
                ", precipitations_apmidi='" + precipitations_apmidi + '\'' +
                ", tempe_soir='" + tempe_soir + '\'' +
                ", vent_soir='" + vent_soir + '\'' +
                ", seeing_soir='" + seeing_soir + '\'' +
                ", precipitations_soir='" + precipitations_soir + '\'' +
                ", pubdate='" + pubdate + '\'' +
                ", picto_matin='" + picto_matin + '\'' +
                ", picto_midi='" + picto_midi + '\'' +
                ", picto_apmidi='" + picto_apmidi + '\'' +
                ", picto_soir='" + picto_soir + '\'' +
                '}';
    }
}
