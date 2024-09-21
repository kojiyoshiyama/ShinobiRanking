package model;

public class Shinobi {

    // each shinobi has a name and picture, as well as a score for each catagory
    protected String name;
    protected int body;
    protected int clan;
    protected int sensei;
    protected int talent;
    protected int mind;
    protected int summon;
    protected int chakra;
    protected int aura;

    // constructor for each unqiue shinobib 
    public Shinobi(String name, int body, int clan, int sensei, int talent, int mind, int summon, int chakra, int aura) {
        this.name = name;
        this.body = body;
        this.clan = clan;
        this.sensei = sensei;
        this.talent = talent;
        this.mind = mind;
        this.summon = summon;
        this.chakra = chakra;
        this.aura = aura;
    }

     // Getter methods to look at the score of each catagory


    public String getName() {
        return name;
    }

    public int getBody() {
        return body;
    }

    public int getClan() {
        return clan;
    }

    public int getSensei() {
        return sensei;
    }

    public int getTalent() {
        return talent;
    }

    public int getMind() {
        return mind;
    }

    public int getSummon() {
        return summon;
    }

    public int getChakra() {
        return chakra;
    }

    public int getAura() {
        return aura;
    }

}
