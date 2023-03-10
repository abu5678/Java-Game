package game;

import city.cs.engine.*;

public class Gems extends DynamicBody {
    private static final Shape GemShape = new BoxShape(0.3f,0.4f);


    private static final BodyImage image =new BodyImage("level/gem.PNG",1.5f);

    public Gems(World w) {
        super(w,GemShape);
        addImage(image);
    }
}
