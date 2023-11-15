package xyz.nebulaquest;

public class Game {
  public String getGreeting() {
    return "Hello World!";
  }

  public static void main(String[] args) {
    System.out.println(new Game().getGreeting());
  }
}