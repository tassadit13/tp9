package fr.cnam.tp9;

public interface MenuComponent {

    public String toString();
    public Command getCommand();
    public boolean isMenu();
    public String getShortcut();
}
