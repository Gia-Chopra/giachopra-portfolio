package TwentyFortyEight;

public class Cell {

    private int x;
    private int y;
    public int value;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void place() {
        if (this.value == 0) {
            this.value = (App.random.nextInt(2)+1)*2;
        }
    }

    /**
     * This draws the cell
     */
    public void draw(App app) {
        app.stroke(156, 139, 124);
            if (app.mouseX > x*App.CELLSIZE && app.mouseX < (x+1)*App.CELLSIZE 
                && app.mouseY > y*App.CELLSIZE && app.mouseY < (y+1)*App.CELLSIZE) {
                app.fill(232, 207, 184);
            }
            else {
               app.fill(189, 172, 151);
            }
        if (this.value == 2){
            app.fill(238, 228, 218);
        }
        else if (this.value == 4){
            app.fill(237, 224, 200);
        }
        else if (this.value == 8){
            app.fill(242, 177, 121);
        }
        else if (this.value == 16){
            app.fill(245, 149, 99);
        }
        else if (this.value == 32){
            app.fill(246, 124, 95);
        }
        else if (this.value == 64){
            app.fill(246, 94, 59);
        }
        else if (this.value == 128){
            app.fill(237, 207, 144);
        }
        else if (this.value == 256){
            app.fill(237, 204, 97);
        }
        else if (this.value == 512){
            app.fill(237, 200, 80);
        }
        else if (this.value == 1024){
            app.fill(237, 197, 63);
        }
        else if (this.value == 2048){
            app.fill(237, 194, 46);
        }
        app.rect(x*App.CELLSIZE, (y*App.CELLSIZE)+40, App.CELLSIZE, App.CELLSIZE, 15);

        if (this.value > 0) {
            app.fill(117,109,112);
            app.text(String.valueOf(this.value), (x+0.4f)*App.CELLSIZE, ((y+0.6f)*App.CELLSIZE) + 40);
        }
    }

}