package Game;

public class RenderMap {

    public void render(Map map){
        for (int i = 0; i < map.getWidth(); i++) {
            System.out.print("-");
        }
        System.out.println();
        for (int i = 0; i < map.getHeight(); i++) {
            System.out.print("|");
            String line = "";
            for (int j = 0; j < map.getWidth(); j++) {
                Coordinates coordinates = new Coordinates(i, j);
                if(map.isBoxEmpty(coordinates)){
                    line += " ";
                }else {
                    line += map.spriteEntity(map.getEntity(coordinates));
                }
            }
            System.out.print(line);
            System.out.print("|");
            System.out.println();
        }
        for (int i = 0; i < map.getWidth(); i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
