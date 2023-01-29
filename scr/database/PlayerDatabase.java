package database;

import gameObjects.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerDatabase implements Serializable {
    private String filename;
    private List<Player> playerList;

    public List<Player> getPlayerList() {
        return playerList;
    }

    public PlayerDatabase(String filename) {
        this.filename = filename;
    }

    public void append(Player player) throws IOException, ClassNotFoundException {
        playerList = deserialize();
        playerList.add(player);
        Collections.sort(playerList);

        this.serialize();

        //
    }

    public void serialize()
    {
        System.out.println("Serialization");
        try {
            FileOutputStream fileOut = new FileOutputStream(filename);
            ObjectOutputStream objOut = new ObjectOutputStream(fileOut);

            objOut.writeObject(playerList);
            objOut.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }


    //Reads the file and returns all entries in a list
    public ArrayList<Player> deserialize () {
        ArrayList<Player> persistedEntries = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream(filename);
            ObjectInputStream objIn = new ObjectInputStream(fileIn);

            persistedEntries = (ArrayList<Player>) objIn.readObject();

            objIn.close();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
        }
        System.out.println("success!");

        return persistedEntries;
    }

    public void print() {
        for(Player p : playerList) {
            System.out.println(p.getPlayerName() + "  " + p.getScore());
        }
    }
}
