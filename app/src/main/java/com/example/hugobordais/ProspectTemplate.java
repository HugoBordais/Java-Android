package com.example.hugobordais;

import org.json.JSONException;
import org.json.JSONObject;

public class ProspectTemplate {

    // Création des variables.
    private String lastName;
    private String name;
    private String address1;
    private String address2;
    private String postalCode;
    private String city;
    private String phoneNumber;
    private String mail;
    private String companyName;

    public ProspectTemplate() {
        // Instanciations à vide des variables.
        this.lastName = "";
        this.name = "";
        this.address1 = "";
        this.address2 = "";
        this.postalCode = "";
        this.city = "";
        this.phoneNumber = "";
        this.mail = "";
        this.companyName = "";
    }

    public ProspectTemplate(JSONObject object) {

        // On essai de récuperer les objets (Nom, Prénom, Adresse 1...) et on les associes aux variables créées précédement.
        try {
            this.lastName = object.getString("Nom");
            this.name = object.getString("Prénom");
            this.address1 = object.getString("Adresse 1");
            this.address2 = object.getString("Adresse 2");
            this.postalCode = object.getString("Code Postal");
            this.city = object.getString("Ville");
            this.phoneNumber = object.getString("Téléphone");
            this.mail = object.getString("Mail");
            this.companyName = object.getString("Nom Entreprise");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // A partir d'ici on retourne avec des Getter les valeurs misent dans les variables précédentes.
    public String getLastName() {
        return lastName;
    }

    public String name() {
        return name;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public String getCompanyName() {
        return companyName;
    }
}
