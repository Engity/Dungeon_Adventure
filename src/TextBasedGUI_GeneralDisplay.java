import java.util.Scanner;

public class TextBasedGUI_GeneralDisplay {
    public TextBasedGUI_GeneralDisplay(){
        displayMainMenu();
    }

    private void displayMainMenu(){
        System.out.println("""
                ______                                       ___      _                 _                 
                |  _  \\                                     / _ \\    | |               | |                
                | | | |_   _ _ __   __ _  ___  ___  _ __   / /_\\ \\ __| __   _____ _ __ | |_ _   _ _ __ ___
                | | | | | | | '_ \\ / _` |/ _ \\/ _ \\| '_ \\  |  _  |/ _` \\ \\ / / _ | '_ \\| __| | | | '__/ _ \\
                | |/ /| |_| | | | | (_| |  __| (_) | | | | | | | | (_| |\\ V |  __| | | | |_| |_| | | |  __/
                |___/  \\__,_|_| |_|\\__, |\\___|\\___/|_| |_| \\_| |_/\\__,_| \\_/ \\___|_| |_|\\__|\\__,_|_|  \\___|
                                    __/ |                                                                 
                                   |___/       
                """
        );
        System.out.println("By Group 8, T CSS 360 A: Toan Nguyen, Thao Nguyen, Justin Noel");
        System.out.println("Please select using your keyboard: ");
        System.out.println("""
                1. New Game
                2. Load Game
                3. Exit"""
        );

        Scanner input = new Scanner(System.in);
        String choice = input.next();
        switch (choice) {
            case "1" -> System.out.println("START A NEW GAME NOW! (NI: NOT IMPLEMENTED)");
            case "2" -> System.out.println("Please select save game to load! (NI: NOT IMPLEMENTED)");
            case "3" -> {
                System.out.println("Exit now!");
                return;
            }
        }

        System.out.println("END EXAMPLE for GUI Text based");
    }
}
