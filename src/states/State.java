package states;

import java.awt.*;

public abstract class State {
    public abstract void tick();

    //Every state has it's own render() method
    public abstract void render(Graphics graphics);

}
