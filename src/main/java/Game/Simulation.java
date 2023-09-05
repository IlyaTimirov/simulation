package Game;
import Actions.Action;
import java.util.Scanner;

public class Simulation {
    private Scanner scanner = new Scanner(System.in);
    private Map map = new Map(50, 20);
    private RenderMap renderMap = new RenderMap();
    private Action action = new Action();
    private boolean check;

    public Simulation() {
        startSimulation();
    }

    private void nextTurn(){
        renderMap.render(map);
        action.getTurnAction().makeMoves(map);
        action.getTurnAction().addEntity(map);
        System.out.print("Enter 'p' pause the simulation: ");
    }

    private void startSimulation() {
        action.getInitAction().initEntity(map);
        Thread start = new Thread(() -> {
                while (true){
                    nextTurn();
                    try {
                        if(check){
                            pauseSimulation();
                        }
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
        });

        Thread pause = new Thread(() -> {
            if(scanner.nextLine().equals("p")){ check = true;}
        });

        start.start();
        pause.start();
    }

    private void pauseSimulation(){
        System.out.print("Simulation is paused, enter 's' unpause and enter 'q' stop simulation: ");
        String input = scanner.nextLine();
        if(input.equals("q")){
            System.exit(0);
        } else if (input.equals("s")) {
            check = false;
        }
    }
}
